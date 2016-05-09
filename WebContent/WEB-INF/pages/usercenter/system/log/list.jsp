<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#logList").jqGrid({
        treeGridModel: 'adjacency',
        url: 'grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='log.operatePeople'/>","<msg:message code='log.IP'/>", "<msg:message code='log.operateTime'/>","<msg:message code='log.conter'/>"],
        colModel: [{name: 'userName',index: 'userName',width:'15%',align:'center',hidden: false,sortable: false}, 
                   {name: 'accessIp',index: 'accessIp',width:'15%',align:'center',hidden: false,sortable: false}, 
                   {name: 'optTime',index:'optTime',width:'15%',align:'center',hidden: false,sortable: false},
                   {name: 'content',index:'content',width:'40%',align:'center',hidden: false,sortable: false,formatter:formatData}],
        rowNum:20,     
        rowList: [20, 30 ,50],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        viewrecords: true,
    
        caption:'<msg:message code="log.list"/>'
    });
    
});

</script>
</head>
<body class="skinMain">
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
		<td class="tableMargin" width="100%"></td>
	</tr>
	<tr>
		<td width="100%">
			<table cellpadding="2" cellspacing="2" border="0" width="100%">
				<tr>
					<td>
						<table id="logList"><tr><td>&nbsp;</td></tr></table>
						<div id="pagered"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="tableMargin"></td>
		</tr>
		<tr>
			<td></td>
		</tr>
	</table>   

</body>
</html>