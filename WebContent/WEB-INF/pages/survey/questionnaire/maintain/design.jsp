<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#questionList").jqGrid({
        datatype: 'local',
        colNames: ["<msg:message code='question.name'/>",
                  "<msg:message code='question.type'/>",
                  "sd",
                  "<msg:message code='questionnaire.sort' />",
                  "queID",
        	      "<msg:message code='message.operate'/>"],
        colModel: [{name: 'questionName',index: 'questionName',width:'35%',align:'center',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'questionType',index: 'questionType',width:'15%',align:'center',hidden: false,sortable: false}, 
                   {name: 'id',index: 'id',width:'22',align:'center',hidden: true,sortable: false}, 
                   {name: 'orderId',index: 'orderId',width:'15%',align:'center',hidden: false,sortable: false, title:false}, 
                   {name: 'queID',index: 'queID',width:'15%',align:'center',hidden: true,sortable: false}, 
                   {name: 'act',index:'act', width:'30%',align:'center',sortable:false}],
        height:'auto',
        autowidth: true,
        viewrecords: true,
        multiselect: true,
        gridComplete: function(){
    		var ids = jQuery("#questionList").jqGrid('getDataIDs');
      		
    			var content = "";
    			var orderImg = "";
    			var rowdata = jQuery("#questionList").jqGrid('getRowData',ids.length);
    			var id = rowdata.id;
    			if(ids.length > 1){
    				var uprowdata = jQuery("#questionList").jqGrid('getRowData',ids.length-1);
    				var upid = uprowdata.id;
    				var uporderid = uprowdata.orderId;
    			}
    			<security:authorize ifAnyGranted="${question_modify.code}">
	    			content += "<a href='#' id='" + id + "' class='shortcut_${question_modify.index}' onclick='editQuestion(" + id +","+ rowdata.questionType + ")' ";
	    			content += "title='${question_modify.name}' >";
	    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${question_modify.name}";
	    			content += "</a>";
 		        </security:authorize>
 		        if($("#questionList").getGridParam("reccount") != 1){
 		        	orderImg += "<a href='#' id='" + id + "' onclick='orderQuestion(" + id +"," + rowdata.orderId + ","  + rowdata.queID + "," + upid + ")' >";
 	 		       	orderImg += "<img src='${base}/${common}/${style}/images/icon/arrow_up.gif' weight='18' height='18' border='0' align='absmiddle' title='";
 	 		        orderImg += "<msg:message code='question.order.up' />";
 	 		       	orderImg += "' /></a>";
 		        }else{
 		        	orderImg += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
 		        }
 		       	if(${gridJsonData}.length != $("#questionList").getGridParam("reccount")){
 		       		orderImg += "<a href='#' id='" + id + "' onclick='orderQuestion(" + id +"," + rowdata.orderId + "," + rowdata.queID + ")' >";
		       		orderImg += "<img src='${base}/${common}/${style}/images/icon/arrow_down.gif' weight='18' height='18' border='0' align='absmiddle' title='";
		       		orderImg += "<msg:message code='question.order.down' />";
		       		orderImg += "' /></a>&nbsp;&nbsp;&nbsp;&nbsp;";
 		       	}
 		       else{
		        	orderImg += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		        }
		       	orderImg += "<input type='text' id='o" + id + "' value='" + rowdata.orderId + "' size='1' readonly='readonly' />";
 		      	jQuery("#questionList").jqGrid('setRowData',ids[i],{orderId:"<div class='jqgridContainer'>" + orderImg + "</div>"});
 		      	
 		        <c:if test="${!empty question_delete}">
   		        <security:authorize ifAnyGranted="${question_delete.code}">
   		           content += "<a href='#' id='" + id + "' class='shortcut_${question_delete.index}' onclick='delQuestion(" + id + ")' ";
   		           content += "title='${question_delete.name}' >";
   		           content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${question_delete.name}";
   		           content += "</a>";	
   		        </security:authorize>
   		        </c:if> 
 		       if(rowdata.questionType == 1){
   				   jQuery("#questionList").jqGrid('setRowData',ids[i],{questionType:"<msg:message code='question.singlechoice'/>"});
   			   }else if(rowdata.questionType == 2){
   				   jQuery("#questionList").jqGrid('setRowData',ids[i],{questionType:"<msg:message code='question.multiplechoice'/>"});
   			   }else if(rowdata.questionType == 3){
   				   jQuery("#questionList").jqGrid('setRowData',ids[i],{questionType:"<msg:message code='question.blank'/>"});
   			   }else if(rowdata.questionType == 4){
   				   jQuery("#questionList").jqGrid('setRowData',ids[i],{questionType:"<msg:message code='question.shortanswer'/>"});
   			   }
 		           jQuery("#questionList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
 		     	
        },
        caption:'<msg:message code="question.list"/>',
        toolbar: [true,"top"]
    });
    
    
    var gridJsonData = ${gridJsonData};
    for(var i=0;i<=gridJsonData.length;i++){
    	jQuery("#questionList").jqGrid('addRowData',i+1,gridJsonData[i]);
    }
    
    <c:if test="${!empty question_create}">
    <security:authorize ifAnyGranted="${question_create.code}">	 
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${question_create.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${question_create.name}");
	$("#t_questionList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${question_create.index}","#t_questionList").click(function(){
    	var url = "${base}${question_create.controller}?c=${question_create.code}&queID=${questionnaire.id}";
    	top.createQuestionTypeBox = new window.parent.Boxy("<iframe src='"+url+"' width='400' height='300' frameborder='0' scrolling='no'></iframe>", 
    						{title: "<msg:message code='info.prompt'/>",closeText:"<msg:message code='info.prompt.close'/>",modal:true});
			
    });    
    </security:authorize>
    </c:if>
    <c:if test="${!empty question_delete}">
    <security:authorize ifAnyGranted="${question_delete.code}">	
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${question_delete.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${question_delete.name}");
	$("#t_questionList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${question_delete.index}","#t_questionList").click(function(){
    	var selrow = jQuery("#questionList").jqGrid("getGridParam","selarrrow");
    	var ids = "";
    	for(var i=0; i<selrow.length; i++){
    		var data = $("#questionList").jqGrid('getRowData',selrow[i]);
    		ids += data.id;
    		if(i!= selrow.length-1)
    			ids += ",";
    	}
		if (selrow != null&&selrow!=""){
			var data = $("#questionList").jqGrid('getRowData',selrow[0]);
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "${base}${question_delete.controller}.json?c=${question_delete.code}&ids="+ids;
								var data = $("#questionList").jqGrid('getRowData',1);
								var queId = data.queID;
								var url = "${base}${question_delete.controller}?c=${question_delete.code}&ids="+ids + "&queID=" + queId;
								window.location.href = url;
			}, {title: "<msg:message code='info.prompt'/>"});
		}
		else{
    		window.parent.Boxy.alert("<msg:message code='info.prompt.delete'/>", null, {title: "<msg:message code='info.prompt'/>"});
   		}    	
    });    
    </security:authorize>
    </c:if> 
    <c:if test="${!empty questionnaire_preview}">
    <security:authorize ifAnyGranted="${questionnaire_preview.code}">	 
    var preUrl = "${base}${questionnaire_preview.controller}?c=${questionnaire_preview.code}&id=${questionnaire.id}";
    var $ea = $("<a></a>").attr("href",preUrl)
			  .attr("id","${questionnaire_preview.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle")).attr("target","_blank")
			  .append("${questionnaire_preview.name}");
	$("#t_questionList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    </security:authorize>
    </c:if>
});
function editQuestion(id,questionType){
	var url = "${base}${question_modify.controller}?c=${question_modify.code}&id="+id + "&questionType=" + questionType ;
	top.createQuestionBox = new window.parent.Boxy("<iframe src='"+url+"' width='900' height='500' frameborder='0' ></iframe>", 
				{title: "<msg:message code='info.prompt'/>",closeText:"<msg:message code='info.prompt.close'/>",modal:true});
}
function delQuestion(id){
	window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
		var url = "${base}${question_delete.controller}?c=${question_delete.code}&id="+id + "&queID=" + "${questionnaire.id}";
		window.location.href = url;
	}, {title: "<msg:message code='info.prompt'/>"});
}

function orderQuestion(id, orderId, queID, swapId){
	var url = "${base}/survey/question/maintain/order?c=5010105&queID=" + queID + "&id="+id+"&swapId="+swapId+"&orderId="+orderId;
	window.location.href = url;
}
</script>
</head>
<body class="skinMain">
<form:form method="post" action="list"  >
	<table width="100%" border="0" cellspacing="1" cellpadding="0"
		class="skinMain">
		<tr>
			<td width="100%">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="skinNav">
					<tr>
						<th width="2%">
							<img src="${base}/${common}/${style}/images/nav/bg_07.gif"
								width="10" height="9" />
						</th>
						<th width="98%">
							<msg:message code="navigation.title" />
							&nbsp;${navigation}
						</th>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="skinMain" >
		
					<tr>
						<td width="100%">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td>
										<table id="questionList">
											<tr>
												<td>
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form:form>
<div style="padding:10px">
<input type="button" class="btn2" onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}&responseNum=${queResponseNum }'" name="btnback" value="<msg:message code="button.back"/>" id="btnback" />
</div>
</body>
</html>