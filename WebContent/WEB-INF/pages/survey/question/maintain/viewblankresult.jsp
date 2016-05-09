<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<style>
.viewBlankResult{
  padding:0 10px;
}
.viewBlankResult .data_table{
     text-align: center;
     width:100%;
     table-layout: fixed;
     border-collapse: collapse;
}
.viewBlankResult .data_table td{
    border: 1px solid #DCE0E1;
    padding: 6px;
    word-wrap: break-word;
}

.viewBlankResult .data_table th{
  background-color: #F5F5F5;
   font-weight: bold;
   padding: 6px;
    border: 1px solid #DCE0E1;
}
.viewBlankResult .quesiton_name{
   font-size: 14px;
   color: #00568f;
   font-weight: bold;
   padding: 10px;
}
</style>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="skinNav">
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
<div class="viewBlankResult">
	
<h3 class="quesiton_name">${question.questionName }</h3>
<table class="data_table" cellpadding="0" cellspacing="0" border="0">
<tr>
<th style="width: 15%"><msg:message code="question.option.user"/></th>
<%-- <th style="width: 15%"><msg:message code="question.option.start.time"/></th> --%>
<th style="width: 15%"><msg:message code="question.option.submit.time"/></th>
<th><msg:message code="question.option.response.content"/></th>
</tr>
<c:forEach var="answer" items="${answerList }">
<tr>
<td>${ answer.MemberName}</td>
<%-- <td><fmt:formatDate value="${answer.JoinTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td> --%>
<td><fmt:formatDate value="${answer.OverTime}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
<td>${ answer.BLANKANSWER}</td>
</tr>
</c:forEach>
</table>
<div style="padding:10px 0">
<input type="button" class="btn2" onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}&responseNum=${queResponseNum }'" name="btnback" value="<msg:message code="button.back"/>" id="btnback" />
</div>
</div>