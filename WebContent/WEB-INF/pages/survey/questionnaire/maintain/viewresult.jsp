<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinNav">
	<tr>
		<th width="2%">
			<img src="${base}/${common}/${style}/images/nav/bg_07.gif"
				width="10" height="9" />
		</th>
		<th width="98%">
			<msg:message code="navigation.title" />
			&nbsp;${navigation}
		</th>
	</tr>
</table>
<form:questionnaireResult questionList="${questionList }" questionnaire="${questionnaire }" viewUrl="${base}${result_blankresult.controller}?c=${result_blankresult.code}"></form:questionnaireResult>
<div style="padding:10px">
<input type="button" class="btn2" onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}'" name="btnback" value="<msg:message code="button.back"/>" id="btnback" />
</div>