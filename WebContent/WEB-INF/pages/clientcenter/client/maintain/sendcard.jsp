<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript">

</script>
</head>
<body class="skinMain">
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
					<input type="hidden" name="id" value="${client.id}" />
					<input type="hidden" name="action" value="sendcard"/>
					<input type="hidden" name="cardStatus" value="1" />
					<input type="hidden" name="prompt" value="name" />
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.cardNum"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="cardNum" maxlength="25" /><font color="red">&nbsp;*&nbsp;<form:errors path="cardNum" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.confirmCardNum"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="confirmCardNum" maxlength="25"/><font color="red">&nbsp;*&nbsp;<form:errors path="confirmCardNum" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.cardType"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<select name="cardType" id="cardType">
							        <option value="0" selected>请选择</option>
							        <option value="1" <c:if test="${client.cardType==1}">selected</c:if> >银卡</option>
							        <option value="2" <c:if test="${client.cardType==2}">selected</c:if> >金卡</option>
							        <option value="3" <c:if test="${client.cardType==3}">selected</c:if> >钻石卡</option>
						    	</select>
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
