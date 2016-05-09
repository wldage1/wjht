<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script language="JavaScript">
var setting = {
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};
var zNodes = ${json};
$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.setting.check.chkboxType = {"Y":"ps", "N":"s"};
	$("#btnok").click(function(){
		var sval = zTree.getCheckedNodes();
		if (!sval || sval.length <= 0){
			window.parent.Boxy.alert("<msg:message code='info.prompt.bind'/>", null, {title: "<msg:message code='info.prompt'/>"});
		}
		else{
			var bind = "";
			for(var i=0; i<sval.length; i++){
				if (i ==0){
					bind = sval[i].id;
				}
				else{
					bind += "," + sval[i].id;
				}
			}
			$("#bind").val(bind);
			$("form").submit();
		}
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
			<form:form method="post" action="saveBind" commandName="role">
			<input type="hidden" name="id" value="${user.id}"/>
			<input type="hidden" name="c" value="${user.c}"/>
			<input type="hidden" name="prompt" value="name" />
			<input type="hidden" name="bind" id="bind"/>
			<input type="hidden" name="operate" value="bind"/>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="user.name"/><msg:message code="system.common.sign.colon"/></td>
					<td width="85%" >${user.name}</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="user.select.bind"/><msg:message code="system.common.sign.colon"/></td>
					<td width="70%">
						<div>
							<ul id="treeDemo" class="ztree" style="margin-top:0px;width:90%;"></ul>
						</div>
					</td>
				</tr>	
				<tr>
					<td width="15%" align="right">&nbsp;</td>
					<td width="85%" colspan="2" align="left">
					<input type="button" class="btn2" name="btnok" value="<msg:message code="button.confirm"/>" id="btnok"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn2" onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}'" name="btnback" value="<msg:message code="button.back"/>" id="btnback"/>
					</td>
				</tr>
			</table>
			</form:form>
			</td>
		</tr>
		<tr>
			<td class="tableMargin"></td>
		</tr>
		<tr>
			<td></td>
		</tr>
	</table> 
</html>
