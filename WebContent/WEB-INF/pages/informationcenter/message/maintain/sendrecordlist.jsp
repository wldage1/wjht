<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){

    jQuery("#msgList").jqGrid({
        treeGridModel: 'adjacency',
        url: 'sendrecordgrid.json',
        ExpandColumn: 'name',
        ExpandColClick: true,
        datatype: 'json',
        colNames: ["<msg:message code='message.msgtitle'/>",
        	"<msg:message code='message.published'/>",
        	"<msg:message code='message.msgFrom'/>",
        	"<msg:message code='message.operate'/>"],
        colModel: [{name: 'msgTitle',index: 'msgTitle',width:'40%',align:'center',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'sendDate',index: 'sendDate',width:'30%',align:'center',hidden: false,sortable: false}, 
                   {name: 'msgFrom',index: 'msgFrom',width:'10%',align:'center',hidden: false,sortable: false}, 
                   {name:'act',index:'act', width:'20%',align:'center',sortable:false}],
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        viewrecords: true,
        caption:'<msg:message code="message.list"/>',
        toolbar: [true,"top"],
        mtype:"POST",
        postData:{msgTitle:"${sendRecord.msgTitle}",msgFrom:"${sendRecord.msgFrom}",startTime:"${sendRecord.startTime}",endTime:"${sendRecord.endTime}"},
    	gridComplete: function(){
    		var ids = jQuery("#msgList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			<c:if test="${!empty sendrecord_mdetails}">
    		    <security:authorize ifAnyGranted="${sendrecord_mdetails.code}">	
    			content += "<a href='#' id='" + id + "' class='shortcut_${sendrecord_mdetails.index}' title='${sendrecord_mdetails.name}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${sendrecord_mdetails.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    			<c:if test="${!empty sendrecord_sendusers}">
    		    <security:authorize ifAnyGranted="${sendrecord_sendusers.code}">	
    			content += "<a href='#' id='" + id + "' class='shortcut_${sendrecord_sendusers.index}' title='${sendrecord_sendusers.name}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${sendrecord_sendusers.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    		    jQuery("#msgList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(){
    		$(".shortcut_${sendrecord_mdetails.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${sendrecord_mdetails.controller}?c=${sendrecord_mdetails.code}&id="+rowid;
    	    });
    		$(".shortcut_${sendrecord_sendusers.index}").click(function(){
    	    	var msgid = $(this).attr("id");
    			window.location.href = "${base}${sendrecord_sendusers.controller}?c=${sendrecord_sendusers.code}&msgId="+msgid;
    	    });
    	},
        caption:'<msg:message code="message.sendedlist"/>',
        toolbar: [false,"top"]
    });
});
function formSubmit(){
	var msgTitle = $("#msgTitle").val();
	$("#msgTitle").val($.trim(msgTitle));
}
</script>
	</head>
	<body class="skinMain">
		<table width="100%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
			<tr>
				<td width="100%">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinNav">
						<tr>
							<th width="2%">
								<img src="${base}/${common}/${style}/images/nav/bg_07.gif" width="20" height="20" />
							</th>
							<th width="98%">
								<msg:message code="navigation.title" />&nbsp;${navigation}
							</th>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center" width="100%">
					<form:form method="post" action="sendrecordlist" commandName="sendRecord" onsubmit="return formSubmit();">
						<input type="hidden" name="c" value="${sendRecord.c}" />
						<table cellpadding="0" cellspacing="1" border="0" width="100%"
							class="search">
							<tr>
								<td align="right" width="10%" class="search_info">
									<msg:message code='message.msgtitle' />
									<msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="18%" class="search_lable">
									<form:input path="msgTitle"  maxlength="50"/>
								</td>
								<td align="right" width="10%" class="search_info">
									<msg:message code='message.msgFrom' />
									<msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="18%" class="search_lable">
									<form:select path="msgFrom">
										<form:option value="0">
											<msg:message code='please.select' />
										</form:option>
										<form:options items="${msgSourceList}" itemValue="value" itemLabel="name" />
									</form:select>
								</td>
								<td align="right" width="10%" class="search_info">
									<msg:message code='message.published' />
									<msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="18%" class="search_lable" colspan="3">
									<input type="text"  id="startTime"  name="startTime"  value="${message.startTime}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})" class="Wdate" style="width:88px;" readonly="readonly" >
									<msg:message code="message.zhi" />
									<input type="text" id="endTime" name="endTime"  value="${message.endTime}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})" class="Wdate" style="width:88px;" readonly="readonly" >
								</td>
								<td rowspan="2" width="10%" class="button_area" align="center">
									<input type="submit" class="btn2" id="select"
										value="<msg:message code='button.search' />">
										<input type=button class="btn2" id="select" onclick="this.form.msgTitle.value = '';this.form.msgFrom.value = '0';this.form.startTime.value = '';this.form.endTime.value = '';" value="<msg:message code='button.reset'/>">
								</td>
							</tr>
						</table>
					</form:form>
				</td>
			</tr>
			<tr>
				<td>
					<table id="msgList">
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
	</body>
</html>