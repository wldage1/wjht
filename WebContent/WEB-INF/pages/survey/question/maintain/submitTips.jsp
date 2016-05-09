<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<table align="center" height="100%" width="100%" border="0" cellpadding="0" cellspacing="0" class="tablemain">
	<tr>
		<td align="center" valign="top">
			<table align="center" border="0" cellpadding="0" cellspacing="0" class="tablemain">
				<tr>
					<input type="hidden" name="isNextCreate" id="isNextCreate" value="${isNextCreate }" />
					<td align="center" height="150">&nbsp;</td>
				</tr>
				<tr>
					<td align="center">
						<table border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td >
									<table width="100%" border="0" cellpadding="0" cellspacing="1" >
										<tr>
											<td align="center" style="font-size: 16px;font-weight: bold;">
						                    <img align="absmiddle" src="${pageContext.request.contextPath}/common/blue/images/icon/success.png">
						                     <msg:message code="questionnaire.thanks.join"/>	                    
				                            </td>
										</tr>
										<tr>
										    <td>
										    <a href="#" onclick="window.close();" style="float:right;font-size: 14px;font-weight: bold;"><msg:message code="button.colse"/></a>
										    </td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="center" height="20"></td>
							</tr>				
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>