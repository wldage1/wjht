<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
	input[type="radio"],input[type="checkbox"]{
		height:13px;
		width:13px;
		vertical-align:middle;
		border:0;
	}
</style>
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
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
					<c:forEach var="structure" items="${structureList}">
						<tr>
							<td width="15%" align="right" class="skinLayout_info">${structure.chineseName}</td>
							<td width="85%" class="skinLayout_lable">
								<c:if test="${structure.showType == showTypeText}">
									<input value="${structure.value}"></input>
								</c:if>
								<c:if test="${structure.showType == showTypeSelect}">
									<select>
										<c:forTokens items="${structure.value}" delims="&" var="option">
											<option value="${fn:split(option,'|')[0]}">${fn:split(option,'|')[1]}</option>
										</c:forTokens>
									</select>
								</c:if>
								<c:if test="${structure.showType == showTypeRadio}">
									<c:forTokens items="${structure.value}" delims="&" var="option">
										<input type="radio" name="radioValue" value="${fn:split(option,'|')[0]}"/>${fn:split(option,'|')[1]}
									</c:forTokens>
								</c:if>
								<c:if test="${structure.showType == showTypeCheckbox}">
									<c:forTokens items="${structure.value}" delims="&" var="option">
										<input type="checkbox" name="checkboxValue" value="${fn:split(option,'|')[0]}"/>${fn:split(option,'|')[1]}
									</c:forTokens>
								</c:if>
								<c:if test="${structure.showType == showTypeTextArea}">
									<textarea>${structure.value}</textarea>
								</c:if>
								<c:if test="${structure.showType == showTypeDate}">
									<input type="text" readonly="readonly" class="Wdate" onfocus="WdatePicker({isShowClear:false,readOnly:true})" value="${structure.value}"/>
								</c:if>
								<c:if test="${structure.isRequired == 1}">
									<font color="red">&nbsp;*&nbsp;</font>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td width="15%" align="right">&nbsp;</td>
						<td width="85%" colspan="2" align="left">
							<input type="button" class="btn2" onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}'" name="btnback" value="<msg:message code="button.back"/>" id="btnback"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table> 
</body>
</html>