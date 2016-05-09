<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="/WEB-INF/tlds/c.tld" %>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib prefix="page" uri="/WEB-INF/tlds/sitemesh-page.tld" %>
<%@ taglib prefix="security" uri="/WEB-INF/tlds/springframework-security.tld" %>
<%@ taglib prefix="spring" uri="/WEB-INF/tlds/springframework-spring.tld" %>
<%@ taglib prefix="form" uri="/WEB-INF/tlds/springframework-form.tld" %>
<%@ taglib prefix="msg" uri="/WEB-INF/tlds/springframework-message.tld" %>
<html>
<head>
<title><msg:message code="system.name"></msg:message></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link  href="${base}/${common}/${style}/css/skin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/${common}/js/jquery-1.6.min.js"></script>
<script type="text/javascript">
function funcLeftLoad(){
	//tab标签更多按钮控制事件
	$("html").unbind("click").bind("click",function(){
		$("#moreTabList",window.parent.document).hide();
		$("#moreTabBtn",window.parent.document).removeClass("onMore");
		$("#moreTabBtn",window.parent.document).removeClass("downMore");
	});
	//左侧菜单事件
	$("#sideBar tbody th").unbind("click").bind("click",function(){
		var dis = $("td",$(this).parent().next()).css("display");
		if(dis == 'none'){
			$("td",$(this).parent().next()).css("display","block");
		}
		else{
			$("td",$(this).parent().next()).css("display","none");
		}
	});	
	$("#sideBar tbody a").unbind("click").bind("click",function(){
		var code = $(this).attr("id");
		var txt = $(this).text();
		window.parent.menuTab(code,txt);
	});	
}
</script>
</head>
<!-- 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" class="sideBar" oncontextmenu="window.event.returnValue=false"   onselectstart="event.returnValue=false"   ondragstart="window.event.returnValue=false"   onsource="event.returnValue=false">
 -->
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" class="sideBar" onload="funcLeftLoad();">
<div id="sideBar">
<table width="168" border="0" cellpadding="0" cellspacing="0">
<c:forEach var="pms1" items="${pmsLevelList1}" varStatus="vs1">
<c:if test="${code == pms1.parentCode}">
<security:authorize ifAnyGranted="${pms1.code}">
<tbody>
	<tr>
		<th><img src="${base}/${common}/${style}/${pms1.icon}" align="absmiddle"/>${pms1.name}</th>
	</tr>
	<tr>
		<td>
	        <ul>
	        	<c:forEach var="pms2" items="${pmsLevelList2}" varStatus="vs2">
				  <c:if test="${pms2.parentCode == pms1.code}">
				  	<security:authorize ifAnyGranted="${pms2.code}">
			        <li><a href="#" id="${pms2.code}">${pms2.name}</a></li>
			        </security:authorize>
			      </c:if>
	            </c:forEach>
	        </ul>			
		</td>
	</tr>
</tbody>  
</security:authorize>     
</c:if>             
</c:forEach>
</table>
</div>
</body>
</html>
