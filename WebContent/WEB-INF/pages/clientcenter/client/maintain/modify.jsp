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
		$("#areaId").attr("value", id);
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
<style type="text/css">
	.radioTD input,label,font{
		vertical-align:middle;
	}
</style>
</head>
<body class="skinMain">
	<div id="menuContent" class="menuContent" style="display: none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top: 0; width: 160px;"></ul>
	</div>
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
				<form:form method="post" action="save" commandName="client" >
					<input type="hidden" name="c" value="${client.c}"/>
					<input type="hidden" name="id" value="${client.id}"/>
					<input type="hidden" name="prompt" value="name" />
					<input type="hidden" name="action" value="create"/>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="organization.parent"/><msg:message code="system.common.sign.colon"/></td>
						<td width="85%" class="skinLayout_lable">
							<input name="areaId" type="hidden" id="areaId"/>
							<input name="parentId" type="hidden" id="parentId"/>
							<input name="level" type="hidden" id="level"/> 
							<input name="path" type="hidden" id="path"/>  
							<input name="childNode" type="hidden" id="childNode"/>  
							<input name="parentName" type="text" maxlength="20" value="${client.orgName}" readonly id="parentName" style="width: 200;"/> 
							<a id="menuBtn" href="#" onclick="showMenu(); return false;"><msg:message code="organization.select"/></a>
							<%-- <font color="red">&nbsp;*&nbsp;<form:errors path="name" /></font> --%>
						</td>
						<c:if test="${roleInfo=='2'}">
							<tr>
								<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.userName"/><msg:message code="system.common.sign.colon"/></td>
								<td width="85%" class="skinLayout_lable">
									<form:select path="userId">
										<form:option value=""><msg:message code="please.select"/></form:option>
										<form:options items="${userList}" itemValue="id" itemLabel="name"/>
									</form:select>
									<%-- <font color="red">&nbsp;*&nbsp;<form:errors path="userName"/></font> --%>
								</td>
							</tr>
						</c:if>
						<c:if test="${roleInfo=='1'}">
							<input name="userId" type="hidden" id="userId" value="${client.creator}"/>  
						</c:if>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.name"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="name" maxlength="25" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;<form:errors path="name" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.phone"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="phone" maxlength="15"/><font color="red">&nbsp;*&nbsp;<form:errors path="phone" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.contactAddress"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="email" maxlength="50" readonly="false"/><font color="red">&nbsp;*&nbsp;<form:errors path="email" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.idCard"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="idCard" maxlength="25"/><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;<form:errors path="idCard" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.sex"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="radioTD">
								<form:radiobuttons path="sex" items="${genderList}" itemValue="value" itemLabel="name" />
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.bankName"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="bankName" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;<form:errors path="bankName" delimiter=","/></font>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message code="client.bankNum"/><msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<form:input path="bankNum" maxlength="25" /><font color="red">&nbsp;&nbsp;*&nbsp;&nbsp;<form:errors path="bankNum" delimiter=","/></font>
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
