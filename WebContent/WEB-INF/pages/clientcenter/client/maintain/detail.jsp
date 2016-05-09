<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<style type="text/css">
	.radioTD input,label,font{
		vertical-align:middle;
	}
</style>
</head>
<body class="skinMain">
	<div id="menuContent" class="menuContent" style="display: none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top: 0; width: 160px;"></ul>
	</div>
	<table width="100%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
		<tr>
			<td width="100%">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinNav">
					<tr>
						<th width="2%"><img src="${base}/${common}/${style}/images/nav/bg_07.gif" width="10" height="9" /></th>
						<th width="98%"><msg:message code="navigation.title"/>&nbsp;${navigation}</th>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<form:form method="post" action="save" commandName="client" >
					<input type="hidden" name="c" value="${client.c}"/>
					<input type="hidden" name="prompt" value="name" />
					<input type="hidden" name="action" value="create"/>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="organization.parent"/><msg:message code="system.common.sign.colon"/></td>
						<td width="85%" class="skinLayout_lable">
							<form:input path="orgName" maxlength="25" disabled="true"/>
						</td>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.userName"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="userName" maxlength="25" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.name"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="name" maxlength="25" disabled="true"/><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;<form:errors path="name" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.phone"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="phone" maxlength="11" disabled="true"/><font color="red">&nbsp;*&nbsp;<form:errors path="phone" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.contactAddress"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="email" maxlength="11" readonly="true" disabled="true"/>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.idCard"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="idCard" maxlength="18" disabled="true"/><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;<form:errors path="idCard" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.sex"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="radioTD">
								<form:radiobuttons path="sex" items="${genderList}" itemValue="value" itemLabel="name" disabled="true" />
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.bankName"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="bankName" disabled="true" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;<form:errors path="bankName" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.bankNum"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="bankNum" maxlength="18" disabled="true"/><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;<form:errors path="bankNum" delimiter=","/></font>
							</td>
						</tr>
						
						<tr>
							<td width="15%" align="right">&nbsp;</td>
							<td width="85%" colspan="2" align="left">
								<input type="submit" class="btn2" name="btnok" value="<msg:message code="button.save"/>" id="btnok"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn2" onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}'" name="btnback" value="<msg:message code="button.back"/>" id="btnback"/>
							</td>
						</tr>
					</table>
				</form:form>
			</td>
		</tr>
	</table> 
</html>
