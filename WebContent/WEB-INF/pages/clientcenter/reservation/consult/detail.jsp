<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body class="skinMain">
<form:form method="post" action="save" commandName="consult">
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
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="consult.name"/><msg:message code="system.common.sign.colon"/></td>
					<td width="85%" class="skinLayout_lable">
					<form:input path="name" value="${consult.name}" disabled='true'/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="consult.gender"/><msg:message code="system.common.sign.colon"/></td>
					<td width="85%" class="skinLayout_lable">
					<form:input path="genderName" value="${consult.genderName}" disabled='true'/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="consult.mobilePhone"/><msg:message code="system.common.sign.colon"/></td>
					<td width="70%" class="skinLayout_lable">
					<form:input path="mobilePhone" value="${consult.mobilePhone}" disabled='true'/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="consult.content"/><msg:message code="system.common.sign.colon"/></td>
					<td width="70%" class="skinLayout_lable">
					<form:textarea cols="40" rows="6" path="content" value="${consult.content}" disabled='true'/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info">&nbsp;</td>
					<td width="85%" colspan="2" align="left">
					<input type="button" class="btn2" onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}'" name="btnback" value="<msg:message code="button.back"/>" id="btnback"/>
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
