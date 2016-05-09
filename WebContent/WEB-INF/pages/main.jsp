<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="decorator" uri="/WEB-INF/tlds/sitemesh-decorator.tld" %>
<%@ taglib prefix="page" uri="/WEB-INF/tlds/sitemesh-page.tld" %>
<%@ taglib prefix="security" uri="/WEB-INF/tlds/springframework-security.tld" %>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/springframework-spring.tld" %>
<%@ taglib prefix="form" uri="/WEB-INF/tlds/springframework-form.tld" %>
<%@ taglib prefix="msg" uri="/WEB-INF/tlds/springframework-message.tld" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><msg:message code="system.name"></msg:message></title>
<link  href="${base}/${common}/${style}/css/skin.css" rel="stylesheet" type="text/css" />
<link href="${base}/${common}/${style}/css/boxy.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${base}/${common}/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${base}/${common}/js/jquery-auto-screen.js"></script>
<script type="text/javascript" src="${base}/${common}/js/interface.js"></script>
<script type="text/javascript" src="${base}/${common}/js/jquery-main-tag.js"></script>
<script type="text/javascript" src="${base}/${common}/js/boxy/jquery.boxy.js"></script>
</head>
<body onload="menuTab();">
<table id="windowTable" align="center" width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr class="topNav">
	<td>
	 <table align="center" width="100%" border="0" cellspacing="0" cellpadding="0" >
	  <tr>
		 <td id="windowTop">
			<table align="center" border="0" cellspacing="0" cellpadding="0" height="100%" width="100%">
				<tr>
					<td width="30%" align="center"></td>
					<td width="40%" align="center"></td>
					<td width="30%" align="center">
						<a href="logout"><msg:message code="user.exit"/></a>
					</td>
				</tr>
			</table>
		 </td>
	  </tr>
	  <tr>
		 <td id="windowTab">
			 <table align="center" border="0" cellspacing="0" cellpadding="0" width="100%">
				  <tr>
					 <td height="30" width="210" align="center">
						<msg:message code="user.welcome"/>${SPRING_SECURITY_LAST_USERNAME}!
					 </td>
					 <td height="30" colspan="2">
						<div class="mainNav">
							<ul id="showTabContainer">
							</ul>
							<div class="more">
								<div class="navMore" id="moreTabBtn"></div>
								<div class="moreList" id="moreTabList">
									<ul id="hideTabContainer">
									</ul>
								</div>
							</div>
						</div> 
					 </td>
				  </tr>		
			  </table>
			 </td>
		  </tr>  
		</table>
	  </td>
	</tr>
	<tr>
	  <td id="windowMain">
	    <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%" class="main">
		    <tr>
			    <td width="168" align="center" valign="top">
			      <table border="0" cellspacing="0" cellpadding="0" width="100%" height="100%">
			          <tr>
			            <td height="30" align="center" id="systemName" >&nbsp;</td>
			          </tr>
			          <tr>
			            <td id="zleftmenu" valign="top">
			            	<c:set var="tempindex" value="0"></c:set>
	                     	<c:forEach var="pms" items="${pmslist}" varStatus="vs">
	                     		<security:authorize ifAnyGranted="${pms.code}">
	                     			<c:set var="tempindex" value="${tempindex+1}"></c:set>
	                     			<c:choose >
									<c:when test="${tempindex == 1}">
	                     				<iframe id="leftMenuFrame_${pms.code}" style="display:block;" width="100%" style="height:100%;margin:0px;" height="100%" name="leftMenuFrame_${pms.code}" src="left?c=${pms.code}" frameborder="0" scrolling="auto"></iframe>
	                     			</c:when>								
									<c:otherwise>
										<iframe id="leftMenuFrame_${pms.code}" style="display:none;" width="100%" style="height:100%;margin:0px;" height="100%" name="leftMenuFrame_${pms.code}" src="left?c=${pms.code}" frameborder="0" scrolling="auto"></iframe>
									</c:otherwise>
									</c:choose>
	                     		</security:authorize>
	                     	</c:forEach>			            
			            </td>
			          </tr>
			      </table>     	
				</td>
			    <td align="center" valign="top" id="content" class="rightContent" height="98%">
			  	  <iframe id="mainFrameWelcome" width="100%" height="100%" name="mainFrameWelcome" src="welcome" frameborder="0" scrolling="auto"></iframe>
			    </td>
			</tr>
		</table>	  
	  </td>
  </tr>    
  <tr>
	  <td id="windowBottom" valign="middle" >
	 	<table align="center" border="0" cellspacing="0" cellpadding="0" width="100%" height="100%" class="bottom">
	 		<tr >
	 			<td width="175" align="center"><span style="color:whitesmoke">微金汇通&nbsp;&copy;&nbsp;2016-2017</span></td>
	 			<%-- <td width="175" align="center"><img src="${base}/${common}/${style}/images/main/footer_copyright.gif"/></td> --%>
	 			<td align="left" id="verticalTab" valign="middle" style="display:none;">
	 				<c:set var="tempindex" value="0"></c:set>
			  		<c:forEach var="pms" items="${pmslist}" varStatus="vs">
				  	<security:authorize ifAnyGranted="${pms.code}">	 
				  	<c:set var="tempindex" value="${tempindex+1}"></c:set>
	 				<table align="left" border="0" cellspacing="0" cellpadding="0">
	 					<tr id="${tempindex}">
						<c:choose >
						<c:when test="${tempindex == 1}">
							<td id="left_${tempindex}" class="lefton">&nbsp;</td>
		 					<td id="center_${tempindex}" class="centeron" valign="middle">
			 					<a href="javascript:void(0);" id="${tempindex}"><img src="${base}/${common}/${style}/${pms.icon}" align="absmiddle" height="24" width="24" id="${pms.code}"/>${pms.name}</a>
		 					</td>
		 					<td id="right_${tempindex}" class="righton">&nbsp;</td>	
						</c:when>								
						<c:otherwise>
							<td id="left_${tempindex}" class="leftout">&nbsp;</td>
		 					<td id="center_${tempindex}" class="centerout" valign="middle">
			 					<a href="javascript:void(0);" id="${tempindex}"><img src="${base}/${common}/${style}/${pms.icon}" align="absmiddle" height="24" width="24" id="${pms.code}"/>${pms.name}</a>
		 					</td>
		 					<td id="right_${tempindex}" class="rightout">&nbsp;</td>	
						</c:otherwise>
						</c:choose>
	 					</tr>
	 				</table>
				  	</security:authorize>
			  		</c:forEach> 	 				
	 			</td>
	 		</tr>
	 	</table>	  
	  </td>
  </tr>  
</table>
</body>
</html>