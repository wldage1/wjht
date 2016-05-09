<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#messageList").jqGrid({
        treeGridModel: 'adjacency',
        ExpandColumn: 'name',
        ExpandColClick: true,
        url: 'grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='salonMessage.title'/>","<msg:message code='salonMessage.meetingTime'/>",
                   "<msg:message code='salonMessage.meetingPlace'/>","<msg:message code='salonMessage.status'/>",
                   "<msg:message code='salonMessage.status'/>", "<msg:message code='info.operate'/>"],
        colModel: [{name: 'title',index:'title',width:'25%',align:'center',sortable:false,formatter:formatData},
                   {name: 'meetingTime',index:'meetingTime',width:'25%',align:'center',sortable:false},
                   {name: 'meetingPlace',index:'meetingPlace',width:'25%',align:'center',sortable:false,formatter:formatData},
                   {name: 'status',index:'status',width:'10%',align:'center',sortable:false},
                   {name: 'statusValue',index: 'statusValue',width:'10%',align:'center',hidden: true,sortable: false}, 
                   {name: 'act',index:'act', width:'15%',align:'center',sortable:false,title:false}],
        rowNum:10,     
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        mtype:"POST",
        postData:{title:"${salonMessage.title}",status:"${salonMessage.status}",starTime:"${salonMessage.starTime}",endTime:"${salonMessage.endTime}",c:"${salonMessage.c}"}, 
        autowidth: true,
        viewrecords: true,
        multiselect: true,
    	gridComplete: function(){
    		var ids = jQuery("#messageList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			var rowdata = jQuery("#messageList").jqGrid('getRowData',id);
    			<c:if test="${!empty modify}">
    		    <security:authorize ifAnyGranted="${modify.code}">	
    		    if(rowdata.statusValue == 1){
    		    	content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${detail.index}'";
        			content += "title='${detail.name}' >";
        			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${detail.name}";
        			content += "</a>";
    		    	
    		    	content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/><font color='#EFEBDE'>${modify.name}</a></font>";
    			}else{
    				jQuery("#messageList").jqGrid('setRowData',id,{status:"<msg:message code='salonMessage.stop'/>"});
    				content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${start.index}'";
        			content += "title='${start.name}' >";
        			content += "<img src='${base}/${common}/${style}/images/icon/Enabled.png' weight='18' height='18' border='0' align='absmiddle'/>${start.name}";
        			content += "</a>";
    				
    				content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${modify.index}'";
        			content += "title='${modify.name}' >";
        			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${modify.name}";
        			content += "</a>";
    			}
    			 </security:authorize>
    			</c:if> 
    			<c:if test="${!empty delete}">
    		    <security:authorize ifAnyGranted="${delete.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${delete.index}'";
    			content += "title='${delete.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${delete.name}";
    			content += "</a>";
    		    </security:authorize>
    			</c:if>
    			jQuery("#messageList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}
    	},
    	loadComplete:function(){
    	    $(".shortcut_${detail.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${detail.controller}?c=${detail.code}&id="+rowid;
    	    });
    	    $(".shortcut_${modify.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${modify.controller}?c=${modify.code}&id="+rowid;
    	    });
    	    $(".shortcut_${delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#messageList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${delete.controller}.json?c=${delete.code}&ids="+rowid+"&prompt=title&title="+encodeURIComponent(encodeURIComponent(data.title));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								jQuery("#messageList").jqGrid("delRowData",rowid);
    								$("#messageList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });
    	    $(".shortcut_${start.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#messageList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='salonMessage.start.confirm'/>",  function(){
    				var url = "${base}${start.controller}.json?c=${start.code}&id="+rowid+"&prompt=title&title="+encodeURIComponent(encodeURIComponent(data.title));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								jQuery("#messageList").jqGrid("delRowData",rowid);
    								$("#messageList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });
    	},
        caption:'<msg:message code="salonMessage.list"/>',
        toolbar: [true,"top"]
    });
    
    <c:if test="${!empty create}">
    <security:authorize ifAnyGranted="${create.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${create.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${create.name}");
	$("#t_messageList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${create.index}","#t_messageList").click(function(){
    	window.location.href = "${base}${create.controller}?c=${create.code}";
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
	$("#t_messageList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${delete.index}","#t_messageList").click(function(){
    	var selrow = jQuery("#messageList").jqGrid("getGridParam","selarrrow");
    	var titles = "";
    	for(var i=0;i < selrow.length;i++){
    		var id = selrow[i];
    		var data = jQuery("#messageList").jqGrid("getRowData",id);
    		titles += data.title;
    		if(i!= selrow.length-1)
    			titles += ",";
    	}
		if (selrow != null && selrow != ""){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "${base}${delete.controller}.json?c=${delete.code}&state=1&ids="+selrow+"&prompt=title&title="+encodeURIComponent(encodeURIComponent(titles));
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								jQuery("#messageList").jqGrid("delRowData",selrow);
								$("#messageList").trigger("reloadGrid");    
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
</head >
<body class="skinMain">
<form:form method="post" action="list" commandName="salonMessage">
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
					<input type="hidden" name="c" value="${salonMessage.c}" />
						<table cellpadding="0" cellspacing="0" border="1" width="100%" class="skinLayout">
							<tr>
								<td align="right" width="10%" class="search_info">
								<msg:message code="salonMessage.title"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="18%" class="search_lable">
								<form:input path="title" value="${salonMessage.title}" onchange="this.value=$.trim(this.value)"/>
								</td>
								<td align="right" width="10%" class="search_info">
								<msg:message code="salonMessage.meetingTimeRegion"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="18%" class="search_lable">
								<input type="text" name="starTime" id="starTime" value="${salonMessage.starTime}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2080-10-01\'}'})" style="width:90px;" readonly="readonly" class="Wdate">
								<msg:message code="consult.and"/>
								<input type="text" name="endTime" id="endTime" value="${salonMessage.endTime}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starTime\')}',maxDate:'2080-10-01'})" style="width:90px;" readonly="readonly" class="Wdate">
								</td>
								<td align="right" width="10%" class="search_info">
								<msg:message code="salonMessage.status"/>
								</td>
								<td align="left" width="18%" class="search_lable" >
								<form:select path="status" >
									<form:option value="">--<msg:message code='please.select'/>--</form:option>
									<form:options items="${messageStatusList}" itemValue="value" itemLabel="name"/>
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
		<table id="messageList"><tr><td>&nbsp;</td></tr></table>
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