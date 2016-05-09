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
			<table cellpadding="2" cellspacing="2" border="0" width="100%">
			<tr>
				<td align="center">
					<input type="hidden" name="c" value="${investment.c}" />
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
							<tr>
								<td width="15%" align="right" class="skinLayout_info"><msg:message code="investment.name"/><msg:message code="system.common.sign.colon"/></td>
								<td width="85%" align="left" class="skinLayout_lable">
								<label><c:out value="${investment.name}"/></label>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="skinLayout_info"><msg:message code="investment.gender"/><msg:message code="system.common.sign.colon"/></td>
								<td width="85%" align="left" class="skinLayout_lable">
								<label><c:out value="${investment.gender}"/></label>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="skinLayout_info"><msg:message code="investment.mobilePhone"/><msg:message code="system.common.sign.colon"/></td>
								<td width="70%" align="left" class="skinLayout_lable">
								<label><c:out value="${investment.mobilePhone}"/></label>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="skinLayout_info"><msg:message code="investment.investScale"/><msg:message code="system.common.sign.colon"/></td>
								<td width="70%" align="left" class="skinLayout_lable">
								<label><c:out value="${investment.investScale}"/></label>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="skinLayout_info"><msg:message code="investment.productName"/><msg:message code="system.common.sign.colon"/></td>
								<td width="70%" align="left" class="skinLayout_lable">
								<label><c:out value="${investment.productName}"/></label>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="skinLayout_info"><msg:message code="investment.invesDeadline"/><msg:message code="system.common.sign.colon"/></td>
								<td width="70%" align="left" class="skinLayout_lable">
								<label><c:out value="${investment.invesDeadlineName}"/></label>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="skinLayout_info"><msg:message code="investment.yield"/><msg:message code="system.common.sign.colon"/></td>
								<td width="70%" align="left" class="skinLayout_lable">
								<label><c:out value="${investment.yieldName}"/></label>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="skinLayout_info"><msg:message code="investment.predictTime"/><msg:message code="system.common.sign.colon"/></td>
								<td width="70%" align="left" class="skinLayout_lable">
								<label><c:out value="${investment.predictTimeName}"/></label>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="skinLayout_info"><msg:message code="investment.investmentTime"/><msg:message code="system.common.sign.colon"/></td>
								<td width="70%" align="left" class="skinLayout_lable">
								<label><c:out value="${investment.createTimeView}"/></label>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="skinLayout_info"><msg:message code="investment.description"/><msg:message code="system.common.sign.colon"/></td>
								<td width="70%" align="left" class="skinLayout_lable">
								<label><c:out value="${investment.description}"/></label>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right">&nbsp;</td>
								<td width="85%" colspan="2" align="left">
								<input type="button" class="btn2" onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}'" name="btnback" value="<msg:message code="button.back"/>" id="btnback"/>
								</td>
							</tr>
						</table>
				</td>
			</tr>
			<tr>
			<td>
		<table id="investmentList"><tr><td>&nbsp;</td></tr></table>
			<div id="pagered"></div>
		</td>
			</tr>
		</table>
			</td>
		</tr>
		<tr>
			<td></td>
		</tr>
	</table>
</body>
</html>
