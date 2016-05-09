<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script language="JavaScript">
var setting = {
		async: {
			enable: true,
			url:"${base}/usercenter/system/organization/stree.json",
			dataType:"json",
			autoParam:["id"],
			dataFilter: filter
		},
		callback: {
			beforeAsync: beforeAsync,
			onClick: onClick
		}
	};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		if (childNodes.stree){
			childNodes = childNodes.stree;
		}
		return childNodes;
	}
	function beforeAsync(treeId, treeNode) {
		return treeNode ? treeNode.level < 5 : true;
	}

	function onClick(e, treeId, treeNode) {
		var id = treeNode.id;
		var name = treeNode.name;
		var code = treeNode.code;
		var path = treeNode.path;
		var level = treeNode.olevel;
		var childNode = treeNode.childNode;
		$("#orgID").attr("value", id);
		$("#orgName").attr("value", name);
		$("#path").attr("value", path);
		$("#level").attr("value", level);
		$("#childNode").attr("value", childNode);
		hideMenu();
	}

	function showMenu() {
		var cityObj = $("#orgName");
		var cityOffset = $("#orgName").offset();
		$("#menuContent").css({
			left : cityOffset.left + "px",
			top : cityOffset.top + cityObj.outerHeight() + "px"
		}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").hide();
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
				event.target).parents("#menuContent").length > 0)) {
			hideMenu();
		}
	}
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting);
	});
   function saveUser(form){
	   if($("#orgID").val() == null || $("#orgID").val()== '' || $("#orgName").val() == null || $("#orgName").val()== ''){
		   window.parent.Boxy.alert("<msg:message code='user.select.org.tip' />", null, {title: "<msg:message code='info.prompt'/>"});
	   }else{
		   form.submit();
	   }
   }	
	
</script>
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
			<td width="100%"></td>
	</tr>
	<tr>
		<td width="100%">
			<div id="menuContent" class="menuContent" style="display: none; position: absolute;">
				<ul id="treeDemo" class="ztree" style="margin-top: 0; width: 160px;"></ul>
			</div>
			<form:form method="post" action="save" commandName="user">
			<input type="hidden" name="id" value="${user.id}"/>
			<input type="hidden" name="c" value="${user.c}"/>
			<input type="hidden" name="prompt" value="name" />
			<form:hidden path="orgID" value="${user.orgID}"/>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
				<tr>
					<td width="15%" align="right" class="skinLayout_info">用户类别<msg:message code="system.common.sign.colon"/></td>
					<td width="85%">
						<input name="userType" type="radio" <c:if test="${user.userType=='1'}">checked</c:if> value="1"/>公司管理员
						<input name="userType" type="radio" <c:if test="${user.userType=='0'}">checked</c:if> value="0" />地区代理
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="user.username"/><msg:message code="system.common.sign.colon"/></td>
					<td width="85%" class="skinLayout_lable">
					<form:input path="account" value="${user.account}" maxlength="25"/><font color="red">&nbsp;*&nbsp;<form:errors path="account" delimiter=","/></font>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="user.name"/><msg:message code="system.common.sign.colon"/></td>
					<td width="85%" class="skinLayout_lable">
					<form:input path="name" value="${user.name}" maxlength="50"/><font color="red">&nbsp;*&nbsp;<form:errors path="name" delimiter=","/></font>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="user.mobilePhone"/><msg:message code="system.common.sign.colon"/></td>
					<td width="85%" class="skinLayout_lable">
					<form:input path="mobilePhone" value="${user.mobilePhone}" maxlength="50"/><font color="red">&nbsp;*&nbsp;<form:errors path="mobilePhone" delimiter=","/></font>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="user.description"/><msg:message code="system.common.sign.colon"/></td>
					<td width="70%" class="skinLayout_lable">
						<table width="300" border="0" cellspacing="1" cellpadding="3" bgcolor="#cccccc" style="border: 1px solid #ddd;">
							    <c:forEach var="org" items ="${orgList}" varStatus="index">
							    <tr style="border: 1px solid #ddd;">
									<td width="75" align="right" bgcolor="#D6D6D6">
										<span style="margin: 8px 6px;"><strong>${org.name}:&nbsp</strong></span>
									</td>
									<td width="210" bgcolor="#FFFFFF" >
										<c:forEach var="role" items = "${roleList}" varStatus="index1">													
											 <c:if test="${org.id == role.orgId}">	
											 	 <div>	
													<input name="roleID" type="radio" style="margin-top:7px;width:12px;height:12px;position: absolute;" value="${role.id}" <c:if test="${user.roleID==role.id}">checked</c:if> />
													<span style="margin-left: 20px;">${role.name}</span>
												 </div>
											 </c:if>
										</c:forEach>
									</td>
								</tr>
							   </c:forEach>
						</table>
					</td>
				</tr>	
				<tr>
					<td width="15%" align="right">&nbsp;</td>
					<td width="85%" colspan="2" align="left">
					<input type="submit" class="btn2" name="btnok" value="<msg:message code="button.confirm"/>" id="btnok"/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="btn2" onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}'" name="btnback" value="<msg:message code="button.back"/>" id="btnback"/>
					</td>
				</tr>
			</table>
			</form:form>
			</td>
		</tr>
	</table>
	</body>
</html>
