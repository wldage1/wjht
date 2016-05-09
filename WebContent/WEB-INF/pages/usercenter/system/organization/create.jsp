<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript">
var setting = {
	async: {
		enable: true,
		url:"stree.json",
		dataType:"json",
		autoParam:["id"],
		dataFilter: filter
	},
	callback: {
		beforeAsync: beforeAsync,
		//beforeClick: beforeClick,
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
	$("#parentId").attr("value", id);
	$("#parentName").attr("value", name);
	$("#path").attr("value", path);
	$("#level").attr("value", level);
	$("#childNode").attr("value", childNode);
	hideMenu();
}

function showMenu() {
	var cityObj = $("#parentName");
	var cityOffset = $("#parentName").offset();
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
</script>
</head>
<body class="skinMain">
<div id="menuContent" class="menuContent" style="display: none; position: absolute;">
	<ul id="treeDemo" class="ztree" style="margin-top: 0; width: 160px;"></ul>
</div>
<form:form method="post" action="save" commandName="organization">
<input type="hidden" name="c" value="${organization.c}"/>
<input type="hidden" name="prompt" value="name" />
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
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="organization.parent"/><msg:message code="system.common.sign.colon"/></td>
					<td width="85%" class="skinLayout_lable">
					<input name="parentId" type="hidden" id="parentId"/>
					<input name="level" type="hidden" id="level"/> 
					<input name="path" type="hidden" id="path"/>  
					<input name="childNode" type="hidden" id="childNode"/>  
					<input name="parentName" type="text" maxlength="20" value="<msg:message code="organization.tree.root"/>" readonly id="parentName" style="width: 200;"/> 
					<a id="menuBtn" href="#" onclick="showMenu(); return false;"><msg:message code="organization.select"/></a>
					<font color="red">&nbsp;*&nbsp;<form:errors path="parentName" /></font>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="organization.code"/><msg:message code="system.common.sign.colon"/></td>
					<td width="85%" class="skinLayout_lable">
					<form:input path="code" maxlength="30"/><font color="red">&nbsp;*&nbsp;<form:errors path="code" /></font>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="organization.name"/><msg:message code="system.common.sign.colon"/></td>
					<td width="85%" class="skinLayout_lable">
					<form:input path="name" maxlength="50" /><font color="red">&nbsp;*&nbsp;<form:errors path="name" delimiter=","/></font>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="organization.phone"/><msg:message code="system.common.sign.colon"/></td>
					<td width="85%" class="skinLayout_lable">
					<form:input path="phone" maxlength="15"/><font color="red">&nbsp;&nbsp;<form:errors path="phone" delimiter=","/> </font>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="organization.fax"/><msg:message code="system.common.sign.colon"/></td>
					<td width="85%" class="skinLayout_lable">
					<form:input path="fax" maxlength="15"/><font color="red">&nbsp;&nbsp;<form:errors path="fax" delimiter=","/></font>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="organization.postcode"/><msg:message code="system.common.sign.colon"/></td>
					<td width="85%" class="skinLayout_lable">
					<form:input path="postCode" maxlength="10" /><font color="red">&nbsp;&nbsp;<form:errors path="postCode" delimiter=","/></font>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="organization.address"/><msg:message code="system.common.sign.colon"/></td>
					<td width="85%" class="skinLayout_lable">
					<form:input path="address" maxlength="250"/><font color="red">&nbsp;&nbsp;<form:errors path="address" /></font>
					</td>
				</tr>																														
				<tr>
					<td width="15%" align="right" class="skinLayout_info"><msg:message code="organization.description"/><msg:message code="system.common.sign.colon"/></td>
					<td width="70%" class="skinLayout_lable">
					<form:input path="description" maxlength="250"/><font color="red">&nbsp;&nbsp;<form:errors path="description" /></font>
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
			</td>
		</tr>
		<tr>
			<td class="tableMargin"></td>
		</tr>
		<tr>
			<td></td>
		</tr>
	</table> 
</form:form>
</body>
</html>
