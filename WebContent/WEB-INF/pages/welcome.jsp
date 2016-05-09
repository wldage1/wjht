<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib prefix="security" uri="/WEB-INF/tlds/springframework-security.tld" %>
<%@ taglib prefix="msg" uri="/WEB-INF/tlds/springframework-message.tld" %>
<html>
<head>
<title><msg:message code="system.name"></msg:message></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link  href="${base}/${common}/${style}/css/skin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/${common}/js/jquery-1.6.min.js"></script>
<script type="text/javascript">
function funcWelcomeLoad(){
	$(".mapa").click(function(){
		var code = $(this).attr("id");
		var txt = $(this).text();
		window.parent.menuTab(code,txt);
	});
}
</script>
</head>
<body class="skinMain" onload="funcWelcomeLoad();">
<table width="100%" border="0" cellspacing="1" cellpadding="1" class="skinMain" >
	<tr>
		<td width="100%">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinNav">
				<tr>
					<th width="2%"><img src="${base}/${common}/${style}/images/nav/bg_07.gif" width="10" height="9" /></th>
					<th width="98%"><msg:message code="system.name"/></th>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="100%" valign="top">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" style="padding:5px;" > 
				<tr>
					<td valign="top" colspan="2">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="welcomeSkin">
							<tr>
								<th><msg:message code="info.prompt.system.map"/></th>
							</tr>
							<c:forEach var="auth1" items="${authlist1}" varStatus="vsindex1">
							<tr>
								<td id="menus">
									<%-- <b style="color:#8486C6;"><img src="${base}/${common}/${style}/${auth1.icon}" width="18" height="18" align="absmiddle">${auth1.name}</b> --%>
									<c:forEach var="auth2" items="${authlist2}" varStatus="vsindex2">
										<c:if test="${auth1.code == auth2.parentCode}">&nbsp;
											<font color="#3165CE"><img src="${base}/${common}/${style}/${auth2.icon}" width="18" height="18" align="absmiddle">${auth2.name}</font>
											<c:forEach var="auth3" items="${authlist3}" varStatus="vsindex3">
												<c:if test="${auth2.code == auth3.parentCode}">
													<security:authorize ifAnyGranted="${auth3.code}" >
														&nbsp;<a href="#" class="mapa" id="${auth3.code}"><font>${auth3.name}</font></a>
													</security:authorize>
													<%-- <security:authorize ifNotGranted="${auth3.code}">
														&nbsp;<font>${auth3.name}</font>
													</security:authorize> --%>
												</c:if>
											</c:forEach>
											</br>	
										</c:if>
									</c:forEach>
								</td>
							</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="welcomeSkin">
							<tr>
								<th><msg:message code="info.prompt.system.info"/></th>
							</tr>
							<tr>
								<td><msg:message code="info.prompt.system.os.version"/>${osName} ${osVersion}</td>
							</tr>
							<tr>
								<td><msg:message code="info.prompt.system.browser.version"/>${bversion}</td>
							</tr>	
							<tr>
								<td><msg:message code="info.prompt.system.server.version"/>${serverInfo}</td>
							</tr>	
							<tr>
								<td><msg:message code="info.prompt.system.servlet.version"/>${servletVersion}</td>
							</tr>																					
							<tr>
								<td><msg:message code="info.prompt.system.vm.version"/>${vmVendor} ${runtimeName} ${runtimeVersion}</td>
							</tr>	
							<tr>
								<td><msg:message code="info.prompt.system.mtotal"/>${totalkb}KB</td>
							</tr>		
							<tr>
								<td><msg:message code="info.prompt.system.mfree"/>${freekb}KB</td>
							</tr>					
						</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td class="tableMargin"></td>
		</tr>
		<tr>
			<td></td>
		</tr>
	</table> 
</body>
</html>
