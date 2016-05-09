<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
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
		<td width="100%">
			<form:form method="post" action="save" commandName="salonMessage">
			<input type="hidden" name="c" value="${salonMessage.c}"/>
			<input type="hidden" name="id" value="${salonMessage.id}"/>
			<input type="hidden" name="status" value="${salonMessage.status}"/>
			<input type="hidden" name="prompt" value="title" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
				<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="salonMessage.title"/><msg:message code="system.common.sign.colon"/></td>
						<td width="85%" class="skinLayout_lable">
						<form:input path="title" value="${salonMessage.title}" /><font color="red">&nbsp;*&nbsp;<form:errors path="title" /></font>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="salonMessage.meetingPlace"/><msg:message code="system.common.sign.colon"/></td>
						<td width="70%" class="skinLayout_lable">
						<form:input path="meetingPlace" value="${salonMessage.meetingPlace}" /><font color="red">&nbsp;*&nbsp;<form:errors path="meetingPlace" /></font>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="salonMessage.meetingContent"/><msg:message code="system.common.sign.colon"/></td>
						<td width="70%" class="skinLayout_lable">
						<form:textarea cols="40" rows="6" path="meetingContent" value="${salonMessage.meetingContent}" /><font color="red">&nbsp;*&nbsp;<form:errors path="meetingContent" /></font>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="salonMessage.meetingTime"/><msg:message code="system.common.sign.colon"/></td>
						<td width="85%" >
						<input type="text" name="meetingTime" value="${salonMessage.meetingTime}" onfocus="WdatePicker({minDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" class="Wdate">
						<font color="red">&nbsp;*&nbsp;<form:errors path="meetingTime" /></font>
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
		<tr>
			<td></td>
		</tr>
	</table>
</html>
