<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#recycledList").jqGrid({
        treeGridModel: 'adjacency',
        url: 'grid.json?auditState=10&delFlag=1',
        ExpandColumn: 'name',
        ExpandColClick: true,
        datatype: 'json',
        colNames: ["<msg:message code='message.msgtitle'/>",
        	"<msg:message code='message.published'/>",
        	"<msg:message code='message.msgFrom'/>",
        	"<msg:message code='message.msgAuditing'/>",
        	"<msg:message code='message.operate'/>"],
        colModel: [{name: 'msgTitle',index: 'msgTitle',width:'35%',align:'center',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'createTime',index: 'createTime',width:'15%',align:'center',hidden: false,sortable: false}, 
                   {name: 'msgFrom',index: 'msgFrom',width:'10%',align:'center',hidden: false,sortable: false}, 
                   {name: 'msgAuditing',index: 'msgAuditing',width:'10%',align:'center',hidden: false,sortable: false}, 
                   {name:'act',index:'act', width:'30%',align:'center',sortable:false}],
        rowNum:10,     
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        viewrecords: true,
        multiselect: true,
        mtype:"POST",
        postData:{msgTitle:"${message.msgTitle}",msgFrom:"${message.msgFrom}",msgAuditing:"${message.msgAuditing}",startTime:"${message.startTime}",endTime:"${message.endTime}",c:"${message.c}"}, 
    	gridComplete: function(){
    		var ids = jQuery("#recycledList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			var rowdata = jQuery("#msgList").jqGrid('getRowData',id);
				<c:if test="${!empty recycled_details}">
    		    <security:authorize ifAnyGranted="${recycled_details.code}">	
    			content += "<a href='#' id='" + id + "' class='shortcut_${recycled_details.index}' title='${recycled_details.name}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${recycled_details.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    		    <c:if test="${!empty recycled_restore}">
    		    <security:authorize ifAnyGranted="${recycled_restore.code}">	
    			content += "<a href='#' id='" + id + "' class='shortcut_${recycled_restore.index}' title='${recycled_restore.name}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/restore.png' weight='18' height='18' border='0' align='absmiddle'/>${recycled_restore.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    			<c:if test="${!empty recycled_tdelete}">
    		    <security:authorize ifAnyGranted="${recycled_tdelete.code}">	
    			content += "<a href='#' id='" + id + "' class='shortcut_${recycled_tdelete.index}' title='${recycled_tdelete.name}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${recycled_tdelete.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    		    jQuery("#recycledList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(){
    		$(".shortcut_${recycled_details.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${recycled_details.controller}?c=${recycled_details.code}&id="+rowid;
    	    });
    		$(".shortcut_${recycled_restore.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#recycledList").jqGrid('getRowData',rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.restore.confirm'/>",  function(){
    				var url = "${base}${recycled_restore.controller}.json?c=${recycled_restore.code}&id="+rowid+"&prompt=msgTitle&msgTitle="+encodeURIComponent(encodeURIComponent(data.msgTitle));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								$("#recycledList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			},{title: "<msg:message code='info.prompt'/>"});
    	    }); 
    		$(".shortcut_${recycled_tdelete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#recycledList").jqGrid('getRowData',rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${recycled_tdelete.controller}.json?c=${recycled_tdelete.code}&id="+rowid+"&prompt=msgTitle&msgTitle="+encodeURIComponent(encodeURIComponent(data.msgTitle));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								//jQuery("#logList").jqGrid("delRowData",rowid);
    								$("#recycledList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			},{title: "<msg:message code='info.prompt'/>"});
    	    });
    	},
        caption:'<msg:message code="message.recycled"/>',
        toolbar: [true,"top"]
    });
    
    <c:if test="${!empty recycled_restore}">
    <security:authorize ifAnyGranted="${recycled_restore.code}">	 
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${recycled_restore.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/restore.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${recycled_restore.name}");
	$("#t_recycledList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${recycled_restore.index}","#t_recycledList").click(function(){
    	var ids = jQuery("#recycledList").jqGrid("getGridParam","selarrrow");
    	var names = "";
    	for(var i=0;i < ids.length;i++){
    		var id = ids[i];
    		var data = jQuery("#recycledList").jqGrid("getRowData",id);
    		names += data.msgTitle;
    		if(i!= ids.length-1)
    			names += ",";
    	}
		if (ids != null&&ids!=""){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.restore.confirm'/>",  function(){
				var url = "${base}${recycled_restore.controller}.json?c=${recycled_restore.code}&ids="+ids+"&prompt=msgTitle&msgTitle="+encodeURIComponent(encodeURIComponent(names));
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								$("#recycledList").trigger("reloadGrid");    
							}
						}
					}
				});
			}, {title: "<msg:message code='info.prompt'/>"});
		}
		else{
    		window.parent.Boxy.alert("<msg:message code='info.prompt.restore' />", null, {title: "<msg:message code='info.prompt'/>"});
   		}    	
    });    
    </security:authorize>
    </c:if>
    
    <c:if test="${!empty recycled_tdelete}">
    <security:authorize ifAnyGranted="${recycled_tdelete.code}">	
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${recycled_tdelete.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${recycled_tdelete.name}");
	$("#t_recycledList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${recycled_tdelete.index}","#t_recycledList").click(function(){
    	var ids = jQuery("#recycledList").jqGrid("getGridParam","selarrrow");
    	var names = "";
    	for(var i=0;i < ids.length;i++){
    		var id = ids[i];
    		var data = jQuery("#recycledList").jqGrid("getRowData",id);
    		names += data.msgTitle;
    		if(i!= ids.length-1)
    			names += ",";
    	}
		if (ids != null&&ids !=""){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "${base}${recycled_tdelete.controller}.json?c=${recycled_tdelete.code}&ids="+ids+"&prompt=msgTitle&msgTitle="+encodeURIComponent(encodeURIComponent(names));
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								$("#recycledList").trigger("reloadGrid");    
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

function formSubmit(){
	var msgTitle = $("#msgTitle").val();
	$("#msgTitle").val($.trim(msgTitle));
}
</script>
</head>
<body class="skinMain">
<form:form method="post" action="list" commandName="message" onsubmit="return formSubmit();">
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
								<td align="center">
									<input type="hidden" name="c" value="${message.c}" />
									<table cellpadding="0" cellspacing="1" border="0" width="100%" class="search">
										<tr>
											<td align="right" width="10%" class="search_info">
												<msg:message code='message.msgtitle' /><msg:message code="system.common.sign.colon"/>
											</td>
											<td align="left" width="20%" class="search_lable">
												<form:input path="msgTitle"  maxlength="50"/>
											</td>
											<td align="right" width="10%" class="search_info">
												<msg:message code='message.msgFrom' /><msg:message code="system.common.sign.colon"/>
											</td>
											<td align="left" width="20%" class="search_lable">											
												<form:select path="msgFrom" >
													<form:option value="0"><msg:message code='please.select'/></form:option>
													<form:options items="${msgSourceList}" itemValue="value" itemLabel="name"/>
												</form:select>
											</td>
											<td rowspan="2" width="10%" class="button_area" align="center">
												<input type="submit" class="btn2" id="select" value="<msg:message code='button.search' />">
												<input type=button class="btn2" id="select" onclick="this.form.msgTitle.value = '';this.form.msgFrom.value = '0';this.form.msgAuditing.value = '0';this.form.startTime.value = '';this.form.endTime.value = '';" value="<msg:message code='button.reset'/>">
											</td>
										</tr>
										<tr>
											<td align="right" width="10%" class="search_info">
												<msg:message code='message.msgAuditing' /><msg:message code="system.common.sign.colon"/>
											</td>
											<td align="left" width="20%" class="search_lable">
												<form:select path="msgAuditing" >
													<form:option value="0"><msg:message code='please.select'/></form:option>
													<form:options items="${auditingStateList}" itemValue="value" itemLabel="name"/>
												</form:select>
											</td>
											<td align="right" width="10%" class="search_info">
												<msg:message code='message.published' /><msg:message code="system.common.sign.colon"/>
											</td>
											<td align="left" width="20%" class="search_lable" >
												<input type="text"  id="startTime"  name="startTime"  value="${message.startTime}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})" class="Wdate" style="width:88px;" readonly="readonly" >
												<msg:message code="message.zhi" />
												<input type="text" id="endTime" name="endTime"  value="${message.endTime}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})" class="Wdate" style="width:88px;" readonly="readonly" >
											</td>
										</tr>

									</table>

								</td>
							</tr>

							<tr>
								<td width="100%">
									<table cellpadding="2" cellspacing="2" border="0" width="100%">
										<tr>
											<td>
												<table id="recycledList">
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
</form:form></body>
</html>