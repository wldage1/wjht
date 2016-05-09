<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript">
function checkName(evt,object,flag){
	//var key = window.event?event.keyCode:evt.which;  
	var re = /^[a-zA-Z\u4e00-\u9fa5]+$/;
	//if(key != 8){
		if(!re.test($(object).val())){
			if("paste" == flag){
				$(object).val("");
			}else{
				$(object).val($(object).val().substring(0,$(object).val().length-1));
			}
		}
	//}
}
</script>
<style type="text/css">
	.radioTD input,label,font{
		vertical-align:middle;
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
				<form:form method="post" action="save" commandName="member" >
					<input type="hidden" name="c" value="${member.c}"/>
					<input type="hidden" name="prompt" value="memberName" />
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="member.name"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="memberName" maxlength="25" /><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;<form:errors path="memberName" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="member.userName"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="userName" maxlength="25"/><font color="red">&nbsp;*&nbsp;<form:errors path="userName" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="member.mobilePhone"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="mobilePhone" maxlength="11" onkeydown="setTimeout('$(\"#registPhone\").val($(\"#mobilePhone\").val())',0)"/><font color="red">&nbsp;*&nbsp;<form:errors path="mobilePhone" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="member.registPhone"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="registPhone" maxlength="11" readonly="true"/>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="member.passWord"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:password path="passWord" maxlength="6" onpaste="return false;"/><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;<form:errors path="passWord" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="member.confirmPassWord"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:password path="confirmPassWord" maxlength="6" onpaste="return false;"/><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;<form:errors path="confirmPassWord" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="member.pwQuestion"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="pwQuestion" maxlength="25"/><font color="red">&nbsp;&nbsp;<form:errors path="pwQuestion" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="member.pwResult"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="pwResult" maxlength="25"/><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;<form:errors path="pwResult" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="member.level"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:select path="level">
									<form:option value="">--<msg:message code="please.select"/>--</form:option>
									<form:options items="${levelList}" itemValue="value" itemLabel="name"/>
								</form:select>
								<font color="red">&nbsp;*&nbsp;<form:errors path="level"/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="member.sex"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="radioTD">
								<form:radiobuttons path="sex" items="${genderList}" itemValue="value" itemLabel="name" />
								<font color="red">&nbsp;*&nbsp;<form:errors path="sex"/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="member.status"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="radioTD">
								<form:radiobuttons path="status" items="${memberStatusList}" itemValue="value" itemLabel="name" cssClass="radio"/>
								<font color="red">&nbsp;*&nbsp;<form:errors path="status"/></font>
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
