<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#salonFeedbackList").jqGrid({
        treeGridModel: 'adjacency',
        ExpandColumn: 'name',
        ExpandColClick: true,
        url: 'grid.json?status=0',
        datatype: 'json',
        colNames: ["<msg:message code='salonMessage.title'/>","<msg:message code='salonFeedback.peopleMobilePhone'/>",
                   "<msg:message code='salonFeedback.isJoin'/>","<msg:message code='salonFeedback.peopleNumber'/>",
                   "<msg:message code='info.operate'/>"],
        colModel: [{name: 'title',index:'title',width:'20%',align:'center',sortable:false},
                   {name: 'peopleMobilePhone',index:'peopleMobilePhone',width:'15%',align:'center',sortable:false},
                   {name: 'isJoin',index:'isJoin',width:'20%',align:'center',sortable:false},
                   {name: 'peopleNumber',index:'peopleNumbe',width:'15%',align:'center',sortable:false},
                   {name: 'act',index:'act', width:'10%',align:'center',sortable:false,title:false}],
        rowNum:10,     
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        mtype:"POST",
        postData:{peopleMobilePhone:"${salonFeedback.peopleMobilePhone}",isJoin:"${salonFeedback.isJoin}",title:"${salonFeedback.title}",c:"${salonFeedback.c}"}, 
        autowidth: true,
        viewrecords: true,
        multiselect: true,
    	gridComplete: function(){
    		var ids = jQuery("#salonFeedbackList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			var rowdata = jQuery("#salonFeedbackList").jqGrid('getRowData',id);
    			if (rowdata.isJoin == 1){
    				 jQuery("#salonFeedbackList").jqGrid('setRowData',id,{isJoin:"<msg:message code='salonFeedback.yesJoin'/>"});
    			}else{
    				 jQuery("#salonFeedbackList").jqGrid('setRowData',id,{isJoin:"<msg:message code='salonFeedback.noJoin'/>"});
    			}
    			<c:if test="${!empty detail}">
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${detail.index}'";
    			content += "title='${detail.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${detail.name}";
    			content += "</a>";
    			</c:if> 
    			<c:if test="${!empty delete}">
    		    <security:authorize ifAnyGranted="${delete.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${delete.index}'";
    			content += "title='${delete.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${delete.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    		    jQuery("#salonFeedbackList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}
    	},
    	loadComplete:function(){
    	    $(".shortcut_${detail.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${detail.controller}?c=${detail.code}&id="+rowid;
    	    });
    	    $(".shortcut_${delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#salonFeedbackList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${delete.controller}.json?c=${delete.code}&status=1&ids="+rowid+"&prompt=title&title="+encodeURIComponent(encodeURIComponent(data.title));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								jQuery("#salonFeedbackList").jqGrid("delRowData",rowid);
    								$("#salonFeedbackList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });  		
    	},
        caption:'<msg:message code="salonFeedback.list"/>',
        toolbar: [true,"top"]
    });
    
    <c:if test="${!empty export}">
    <security:authorize ifAnyGranted="${export.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
    		  .attr("id","${export.index}")
    		  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/EXPort.png")
    		  .attr("width","18").attr("height","18").attr("border","0")
    		  .attr("border","0").attr("align","absmiddle"))
    		  .append("${export.name}");
    $("#t_salonFeedbackList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));
    var title = $("#title").val();
	var peopleMobilePhone = $("#peopleMobilePhone").val();
	var isJoin = $("#isJoin").val();
    $("#${export.index}","#t_salonFeedbackList").click(function(){
    	var ids = jQuery("#salonFeedbackList").jqGrid('getDataIDs');
		if(ids.length > 0){
			window.location.href = "${base}${export.controller}?c=${export.code}&status=0&title="+title
	    		+"&peopleMobilePhone="+peopleMobilePhone+"&isJoin="+isJoin;
		}
		else{
			window.parent.Boxy.alert("<msg:message code='info.prompt.export.confirm'/>", null, {title: "<msg:message code='info.prompt'/>"});
		}
	});		
    </security:authorize>
    </c:if>
    
    <c:if test="${!empty delete}">
    <security:authorize ifAnyGranted="${delete.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${delete.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${delete.name}");
	$("#t_salonFeedbackList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${delete.index}","#t_salonFeedbackList").click(function(){
    	var selrow = jQuery("#salonFeedbackList").jqGrid("getGridParam","selarrrow");
    	var titles = "";
    	for(var i=0;i < selrow.length;i++){
    		var id = selrow[i];
    		var data = jQuery("#salonFeedbackList").jqGrid("getRowData",id);
    		titles += data.title;
    		if(i!= selrow.length-1)
    			titles += ",";
    	}
		if (selrow != null && selrow != ""){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "${base}${delete.controller}.json?c=${delete.code}&status=1&ids="+selrow+"&prompt=title&title="+encodeURIComponent(encodeURIComponent(titles));
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								jQuery("#salonFeedbackList").jqGrid("delRowData",selrow);
								$("#salonFeedbackList").trigger("reloadGrid");    
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
	
    <c:if test="${!empty recycle}">
    <security:authorize ifAnyGranted="${recycle.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${recycle.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/Recycler.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${recycle.name}");
	$("#t_salonFeedbackList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${recycle.index}","#t_salonFeedbackList").click(function(){
    	window.location.href = "${base}${recycle.controller}?c=${recycle.code}";
    });    
    </security:authorize>
    </c:if>
});
</script>
</head>
<body class="skinMain">
<form:form method="post" action="list" commandName="salonFeedback">
<table width="100%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
		<tr>
			<td width="100%">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinNav">
					<tr>
						<th width="2%"><img src="${base}/${common}/${style}/images/nav/bg_07.gif" width="10" height="9" />
					</th>
					<th width="98%"><msg:message code="navigation.title"/>&nbsp;${navigation}</th>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="100%">
			<table cellpadding="2" cellspacing="2" border="0" width="100%">
			<tr>
				<td align="center">
					<input type="hidden" name="c" value="${salonFeedback.c}" />
						<table cellpadding="0" cellspacing="0" border="1" width="100%"  class="skinLayout">
							<tr>
								<td align="right" width="10%" class="search_info">
								<msg:message code="salonMessage.title"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="15%" class="search_lable">
								<form:input path="title" onchange="this.value=$.trim(this.value)"/>
								</td>
								<td align="right" width="10%" class="search_info">
								<msg:message code="salonFeedback.peopleMobilePhone"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="15%" class="search_lable">
								<form:input path="peopleMobilePhone" onchange="this.value=$.trim(this.value)"/>
								</td>
								<td align="right" width="10%" class="search_info">
								<msg:message code="salonFeedback.isJoin"/>
								</td>
								<td align="left" width="15%" class="search_lable" >
								<form:select path="isJoin" >
									<form:option value="">--<msg:message code='please.select'/>--</form:option>
									<form:option value="0" ><msg:message code='salonFeedback.noJoin'/></form:option>
									<form:option value="1" ><msg:message code='salonFeedback.yesJoin'/></form:option>
								</form:select>
								</td>
								<td align="center" width="10%" class="button_area" rowspan="2">
								<input type="submit" class="btn2" id="select" value="<msg:message code="button.search"/>">
								</td>
							</tr>
						</table>
				</td>
			</tr>
			<tr>
			<td>
		<table id="salonFeedbackList"><tr><td>&nbsp;</td></tr></table>
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
</form:form>
</body>
</html>