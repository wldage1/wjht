<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#questionnaireList").jqGrid({
        treeGridModel: 'adjacency',
        url: 'grid.json',
        ExpandColumn: 'name',
        ExpandColClick: true,
        datatype: 'json',
        colNames: ["<msg:message code='questionnaire.name'/>",
        	"<msg:message code='questionnaire.title'/>",
        	"<msg:message code='questionnaire.status'/>",
        	"<msg:message code='questionnaire.response.number'/>",
        	"<msg:message code='message.operate'/>"],
        colModel: [{name: 'queName',index: 'queName',width:'15%',align:'center',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'queTitle',index: 'queTitle',width:'25%',align:'center',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'openFlag',index: 'openFlag',width:'5%',align:'center',hidden: false,sortable: false},
                   {name: 'responseNum',index: 'responseNum',width:'5%',align:'center',hidden: false,sortable: false},
                   {name:'act',index:'act', width:'45%',align:'center',sortable:false,title:false}],
        rowNum:10,     
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        viewrecords: true,
        mtype:"POST",
        postData:{queName:"${questionnaire.queName}",queTitle:"${questionnaire.queTitle}",queStartTime:"${questionnaire.queStartTime}",queEndTime:"${questionnaire.queEndTime}",c:"${questionnaire.c}"}, 
        multiselect: true,
    	gridComplete: function(){
    		var ids = jQuery("#questionnaireList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			var rowdata = jQuery("#questionnaireList").jqGrid('getRowData',id);
    			if (rowdata.openFlag == 1){
    				 jQuery("#questionnaireList").jqGrid('setRowData',id,{openFlag:"<msg:message code='questionnaire.isopen.true' />"});
    				 var currentCheckbox = document.getElementById("jqg_questionnaireList_"+id);
    				 currentCheckbox.parentNode.removeChild(currentCheckbox);
    				// $("#jqg_questionnaireList_"+id).attr("disabled",true);
    			}else{
    				 jQuery("#questionnaireList").jqGrid('setRowData',id,{openFlag:"<msg:message code='questionnaire.isopen.false' />"});
    			}
    			<c:if test="${!empty questionnaire_design}">
    		    <security:authorize ifAnyGranted="${questionnaire_design.code}">
    		    if(rowdata.openFlag == 1){
    		    	content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/><font color='#EFEBDE' title='${questionnaire_design.name}'>${questionnaire_design.name}</font>";
    		    }else{
    		    	content += "<a href='#' id='" + id + "' class='shortcut_${questionnaire_design.index}' ";
    		    	content += " title='${questionnaire_design.name}' >";
        			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${questionnaire_design.name}";
        			content += "</a>";
    		    }
    		    </security:authorize>
    		    </c:if>
    		    <c:if test="${!empty questionnaire_release}">
    		    <security:authorize ifAnyGranted="${questionnaire_release.code}">	
    		    if(rowdata.openFlag == 1){
    		    	content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/><font color='#EFEBDE' title='${questionnaire_release.name}'>${questionnaire_release.name}</font>";
    		    }else{
    		    	content += "<a href='#' id='" + id + "' class='shortcut_${questionnaire_release.index}'";
    		    	content += " title='${questionnaire_release.name}' >";
        			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${questionnaire_release.name}";
        			content += "</a>";
    		    }
    		    </security:authorize>
    		    </c:if>
    		    <c:if test="${!empty questionnaire_preview_1}">
    		    <security:authorize ifAnyGranted="${questionnaire_preview_1.code}">	
    		    	content += "<a href='#' id='" + id + "' class='shortcut_${questionnaire_preview_1.index}' ";
    		    	content += " title='${questionnaire_preview_1.name}' >";
        			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${questionnaire_preview_1.name}";
        			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    		    <%--<c:if test="${!empty questionnaire_link}">
    		    <security:authorize ifAnyGranted="${questionnaire_link.code}">	
    		    if(rowdata.openFlag != 1){
    		    	content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/><font color='#EFEBDE' title='${questionnaire_link.name}'>${questionnaire_link.name}</font>";
    		    }else{
    		    	content += "<a href='#' id='" + id + "' class='shortcut_${questionnaire_link.index}'";
    		    	content += " title='${questionnaire_link.name}' >";
        			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${questionnaire_link.name}";
        			content += "</a>";
    		    }
    		    </security:authorize>
    		    </c:if>--%>
    		    
    		    <c:if test="${!empty questionnaire_message}">
    		    <security:authorize ifAnyGranted="${questionnaire_message.code}">	
    		    if(rowdata.openFlag != 1){
    		    	content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/><font color='#EFEBDE' title='${questionnaire_message.name}'>${questionnaire_message.name}</font>";
    		    }else{
    		    	content += "<a href='#' id='" + id + "' class='shortcut_${questionnaire_message.index}'";
    		    	content += " title='${questionnaire_message.name}' >";
        			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${questionnaire_message.name}";
        			content += "</a>";
    		    }
    		    </security:authorize>
    		    </c:if>
    		    
    		    
    		    <c:if test="${!empty questionnaire_result}">
    		    <security:authorize ifAnyGranted="${questionnaire_result.code}">	
    		        if(rowdata.openFlag == 1){
    		        	content += "<a href='#' id='" + id + "' class='shortcut_${questionnaire_result.index}'";
    		        	content += " title='${questionnaire_result.name}' >";
           			    content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${questionnaire_result.name}";
           			    content += "</a>";
    		        }else{
    		        	content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/><font color='#EFEBDE' title='${questionnaire_result.name}'>${questionnaire_result.name}</font>";
    		        }
    		    </security:authorize>
    		    </c:if>
    		    <c:if test="${!empty questionnaire_userresult}">
    		    <security:authorize ifAnyGranted="${questionnaire_userresult.code}">	
    		        if(rowdata.openFlag == 1){
    		        	content += "<a href='#' id='" + id + "' class='shortcut_${questionnaire_userresult.index}'";
    		        	content += " title='${questionnaire_userresult.name}' >";
           			    content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${questionnaire_userresult.name}";
           			    content += "</a>";
    		        }else{
    		        	content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/><font color='#EFEBDE' title='${questionnaire_result.name}'>${questionnaire_userresult.name}</font>";
    		        }
    		    </security:authorize>
    		    </c:if>
    		    <c:if test="${!empty questionnaire_deleteResult}">
    		    <security:authorize ifAnyGranted="${questionnaire_deleteResult.code}">	
    		        if(rowdata.openFlag == 1){
    		        	content += "<a href='#' id='" + id + "' class='shortcut_${questionnaire_deleteResult.index}'";
    		        	content += " title='${questionnaire_deleteResult.name}' >";
           			    content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${questionnaire_deleteResult.name}";
           			    content += "</a>";
    		        }else{
    		        	content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/><font color='#EFEBDE' title='${questionnaire_deleteResult.name}'>${questionnaire_deleteResult.name}</font>";
    		        }
    		    </security:authorize>
    		    </c:if>
    		    <c:if test="${!empty questionnaire_modify}">
    		    <security:authorize ifAnyGranted="${questionnaire_modify.code}">
    		    	if(true){
	    			  content += "<a href='#' id='" + id + "' class='shortcut_${questionnaire_modify.index}'";
	    			  content += " title='${questionnaire_modify.name}' >";
	    			  content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${questionnaire_modify.name}";
	    			  content += "</a>";
	    			}else{
	    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/><font color='#EFEBDE'>${msg_modify.name}</font>";
	    			}
    		    </security:authorize>
    		    </c:if>  
    		    
    		    <c:if test="${!empty questionnaire_delete}">
    		    <security:authorize ifAnyGranted="${questionnaire_delete.code}">
    		        if(rowdata.openFlag == 1){
    		        	content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/><font color='#EFEBDE' title='${questionnaire_delete.name}'>${questionnaire_delete.name}</font>";
    		        }else{
    		            content += "<a href='#' id='" + id + "' class='shortcut_${questionnaire_delete.index}'";
    		            content += " title='${questionnaire_delete.name}' >";
    	    		    content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${questionnaire_delete.name}";
    	    		    content += "</a>";
    		        }
    			
    		    </security:authorize>
    		    </c:if> 
    		   jQuery("#questionnaireList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(data){
    		$(".shortcut_${questionnaire_design.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			var url = "${base}${questionnaire_design.controller}?c=${questionnaire_design.code}&id="+rowid;
    			window.location.href = url;
    	    });
    		$(".shortcut_${questionnaire_release.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var checkUrl = "${base}/survey/questionnaire/maintain/countquequestion.json?c=${questionnaire_design.code}&id="+rowid;
    			$.ajax({
					url:checkUrl,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
							if(jsonData.questionRecord > 0 ){
								window.parent.Boxy.confirm("<msg:message code='questionnaire.confirm.release' />",  function(){
									var rowdata = jQuery("#questionnaireList").jqGrid('getRowData',rowid);
					    			var url = "${base}${questionnaire_release.controller}.json?c=${questionnaire_release.code}&prompt=queName&id="+rowid;
					    			//var tempForm = $("#que_tempFormSubmit");
					    			//$("#que_tempQueName").val(rowdata.queName);
					    			//$("#que_tempFormSubmit").attr("action",url).submit();
					    			$.ajax({
				    					url:url,
				    					type:'post',
				    					timeout:'60000',
				    					data:{queName:rowdata.queName},
				    					dataType:'json',
				    					success:function(jsonData,textStatus){
				    						if (textStatus == "success"){
				    							if (jsonData.status == "success"){
				    								$("#questionnaireList").trigger("reloadGrid");    
				    							}
				    						}
				    					}
				    				});
								}, {title: "<msg:message code='info.prompt'/>"});
							}else{
								window.parent.Boxy.alert("<msg:message code='questionnaire.tip.noquestion' />", null, {title: "<msg:message code='info.prompt'/>"});
							}
					}
				});
    	    });
    		$(".shortcut_${questionnaire_preview_1.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.open("${base}${questionnaire_preview_1.controller}?c=${questionnaire_preview_1.code}&id="+rowid);
    	    });
    	    <%--$(".shortcut_${questionnaire_link.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var url = "http://<%=request.getServerName()+":"+request.getServerPort()%>${base}/survey/question/maintain/join?id="+ rowid; 
    	    	var content = "<div style='height:200px;width:400px;font-size:14px;'>"  + url + "</div>";
    	    	 new window.parent.Boxy(content, 
    	 				{title: "<msg:message code='questionnaire.copy.link'/>",closeText:"<msg:message code='info.prompt.close'/>",modal:true,width:600, height:500});
    	    });--%>
    		$(".shortcut_${questionnaire_message.index}").click(function(){
    			var rowid = $(this).attr("id");
    	    	var url = "${base}${questionnaire_message.controller}.json?c=${questionnaire_message.code}&id="+rowid ;
    			$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							window.parent.Boxy.alert("<msg:message code='questionnaire.message.send.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
						}
					}
				});
    	    });
    		$(".shortcut_${questionnaire_result.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var rowdata = jQuery("#questionnaireList").jqGrid('getRowData',rowid);
    	    	window.location.href = "${base}${questionnaire_result.controller}?c=${questionnaire_result.code}&id="+rowid ;
    	    });
    		$(".shortcut_${questionnaire_userresult.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var rowdata = jQuery("#questionnaireList").jqGrid('getRowData',rowid);
    	    	window.location.href = "${base}${questionnaire_userresult.controller}?c=${questionnaire_userresult.code}&id="+rowid ;
    	    });
    		$(".shortcut_${questionnaire_deleteResult.index}").click(function(){
    			var rowid = $(this).attr("id");
    			window.parent.Boxy.confirm("<msg:message code='questionnaire.confirm.delete.result' />",  function(){
    				var url = "${base}${questionnaire_deleteResult.controller}.json?c=${questionnaire_deleteResult.code}&id="+rowid;
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								$("#questionnaireList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });
    		$(".shortcut_${questionnaire_modify.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${questionnaire_modify.controller}?c=${questionnaire_modify.code}&id="+rowid;
    	    });
    	    $(".shortcut_${questionnaire_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${questionnaire_delete.controller}.json?c=${questionnaire_delete.code}&id="+rowid;
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								$("#questionnaireList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });    
    	},
        caption:'<msg:message code="questionnaire.list"/>',
        toolbar: [true,"top"]
    });
    
    <c:if test="${!empty questionnaire_create}">
    <security:authorize ifAnyGranted="${questionnaire_create.code}">	 
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${questionnaire_create.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${questionnaire_create.name}");
	$("#t_questionnaireList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${questionnaire_create.index}","#t_questionnaireList").click(function(){
    	window.location.href = "${base}${questionnaire_create.controller}?c=${questionnaire_create.code}";
    });    
    </security:authorize>
    </c:if>
    <c:if test="${!empty questionnaire_delete}">
    <security:authorize ifAnyGranted="${questionnaire_delete.code}">	
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${questionnaire_delete.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${questionnaire_delete.name}");
	$("#t_questionnaireList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${questionnaire_delete.index}","#t_questionnaireList").click(function(){
    	var selrow = jQuery("#questionnaireList").jqGrid("getGridParam","selarrrow");
		if (selrow != null&&selrow!=""){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "${base}${questionnaire_delete.controller}.json?c=${questionnaire_delete.code}&ids="+selrow;
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								jQuery("#questionnaireList").jqGrid("delRowData",selrow);
								$("#questionnaireList").trigger("reloadGrid");    
							}
						}
					}
				});
			}, {title: "<msg:message code='info.prompt'/>"});
		}
		else{
    		window.parent.Boxy.alert("<msg:message code='info.prompt.delete'/>", null, {title: "<msg:message code='info.prompt'/>"});
   		}    	
    });    
    </security:authorize>
    </c:if> 
});


</script>
</head>
<body class="skinMain">
<form:form method="post" action="list" commandName="questionnaire" >
	<table width="100%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
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
						<td align="center">
							<form:hidden path="c"/>
							<table cellpadding="0" cellspacing="1" border="0" width="100%" class="search">
								<tr>
									<td align="right" width="10%" class="search_info">
										<msg:message code='questionnaire.name' /><msg:message code="system.common.sign.colon"/>
									</td>
									<td align="left" width="20%" class="search_lable">
									     <form:input path="queName" onchange="this.value=$.trim(this.value)"/>
									</td>
									<td align="right" width="10%" class="search_info">
										<msg:message code='questionnaire.title' /><msg:message code="system.common.sign.colon"/>
									</td>
									<td align="left" width="20%" class="search_lable">											
									     <form:input path="queTitle" onchange="this.value=$.trim(this.value)"/>
									</td>
									<td rowspan="1" width="10%" class="button_area" align="center">
										<input type="submit" class="btn2" id="select" value="<msg:message code='button.search' />">
									</td>											
                                        </tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td>
										<table id="questionnaireList">
											<tr>
												<td>
													&nbsp;
												</td>
											</tr>
										</table>
										<div id="pagered"></div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form:form>
</body>
</html>
		<%--<form id="que_tempFormSubmit" action="" method="post">
		<input type="hidden" name="queName" id="que_tempQueName"/>
		</form> --%>
