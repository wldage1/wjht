<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		<td class="tableMargin" width="100%"></td>
	</tr>
	<tr>
		<td width="100%">
			<form:form method="post" action="save" commandName="appVersion">
			<input type="hidden" name="c" value="${appversion.c}"/>
			<input type="hidden" name="id" value="${appversion.id}"/>
			<input type="hidden" name="prompt" value="vertion"/>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">		
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="appversion.deviceType"/><msg:message code="system.common.sign.colon"/></td>
						<td width="85%" class="skinLayout_lable">
						<select name="deviceType" disabled="true">
						<c:forEach items="${devicetype}" var="item">
							<option value="${item.key}" <c:if test="${appversion.deviceType == item.key}">selected='true'</c:if>>${item.value}</option>
						</c:forEach>
						</select>
						<font color="red">&nbsp;*&nbsp;</font>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="appversion.version"/><msg:message code="system.common.sign.colon"/></td>
						<td width="70%" class="skinLayout_lable">
						<form:input path="version" maxlength="25" value="${appversion.version}"/><font color="red">&nbsp;*&nbsp;<form:errors path="version" delimiter=","/>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="appversion.downloadURL"/><msg:message code="system.common.sign.colon"/></td>
						<td width="70%" class="skinLayout_lable">
						<form:input path="downloadURL" maxlength="200" value="${appversion.downloadURL}"/><font color="red">&nbsp;*&nbsp;<form:errors path="downloadURL" delimiter=","/>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="appversion.noticeRate"/><msg:message code="system.common.sign.colon"/></td>
						<td width="70%" class="skinLayout_lable">
						<select name="noticeRate">
						<c:forEach items="${noticerate}" var="item">
							<option value="${item.key}" <c:if test="${appversion.noticeRate == item.key}">selected='true'</c:if>>${item.value}</option>
						</c:forEach>
						</select>
						<font color="red">&nbsp;&nbsp;<form:errors path="noticeRate" delimiter=","/>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="appversion.info"/><msg:message code="system.common.sign.colon"/></td>
						<td width="70%" class="skinLayout_lable">
						<textarea name="info"  cols="40" rows="6">${appversion.info}</textarea><font color="red">&nbsp;&nbsp;<form:errors path="info" delimiter=","/></font>
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
			<td class="tableMargin"></td>
		</tr>
		<tr>
			<td></td>
		</tr>
	</table>
</html>
