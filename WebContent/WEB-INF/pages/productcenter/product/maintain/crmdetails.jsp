<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body class="skinMain">
		<table width="100%" border="0" cellspacing="1" cellpadding="0"
			class="skinMain">
			<tr>
				<td width="100%">
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
				</td>
			</tr>
			<tr>
				<td width="100%">
					<form:form method="post" action="save" commandName="crmProduct" onsubmit="return formSubmit();">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" id="prdTable" class="skinLayout">
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.prdtype"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<input type="text"  name="prdTypeChn" value="${crmProduct.productType}" disabled="disabled">
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.prdname"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="productName" maxlength ="50" disabled="true"/>
									<font color="red">&nbsp;*&nbsp;
									</font>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.prdSerialNo"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="serialNo" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.source"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="source" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.productDesc"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="productDesc" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.productIncome"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="productIncome" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.createTimeCRM"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="createTimeCRM" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.productFoundTime"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="productFoundTime" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.purchasePhase"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="purchasePhase" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.purchaseStartPoint"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="purchaseStartPoint" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.saleStartTime"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="saleStartTime" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.saleEndTime"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="saleEndTime" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.commisionRate"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="commisionRate" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.ladderCommisionRate"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="ladderCommisionRate" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.factCommisionRate"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="factCommisionRate" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.productTerm"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="productTerm" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.productFinishTime"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="productFinishTime" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.ventureCharacter"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="ventureCharacter" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.productAdvice"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="productAdvice" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.productStatus"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="productStatus" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.pad1"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="pad1" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.pad2"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="pad2" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.pad4"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="pad4" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.pad6"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="pad6" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.pad7"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="pad7" maxlength ="50" disabled="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="crmproduct.pad8"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="pad8" maxlength ="50" disabled="true"/>
								</td>
							</tr>
						</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
							<tr>
								<td width="15%" align="right">
									&nbsp;
								</td>
								<td width="85%" colspan="2" align="left">
									<input type="button" class="btn2" onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}'" name="btnback" value="<msg:message code="button.back"/>" id="btnback" />
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
	</body>
</html>
