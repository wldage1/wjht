<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript">
	$(document).ready(function(){
		if("${sharepluginList}" != ""){
			var con = "";
			<c:forEach var="item" items="${sharepluginList}" varStatus="ap">
				con += "<tr><td><input type='hidden' name='ids' value='${item.id}'/><input type='hidden' id='status_${ap.index}' name='statuses' value='${item.status}'/><input type='checkbox' onclick='setVal(${ap.index})' style='width:15px;height:15px;border:0px'";
				con += "<c:if test='${item.status == 1}'>checked = true</c:if> /></td><td width='200'>${item.name}</td>";
				con += "<td><a href='javascript:void(0)' onclick='up(this);'>↑</a>&nbsp;&nbsp;<a href='javascript:void(0)' onclick='down(this);'>↓</a></td></tr>";
			</c:forEach>
			$('#spList').append(con);
		}
	});
	
	// 移动 向上
	function up(obj){ 
        var current=$(obj).parent().parent();
        var prev=current.prev(); 
        current.insertBefore(prev); 
    }
	// 移动 向下
	function down(obj){
        var current=$(obj).parent().parent();  
        var next=current.next();  
        current.insertAfter(next); 		
    } 
	// 设置启用的平台
	function setVal(index){
		var va = $('#status_'+index).val();
		if(va == 1){
			$('#status_'+index).val(0);
		}else{
			$('#status_'+index).val(1);
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
		<td class="tableMargin" width="100%"></td>
	</tr>
	<tr>
		<td width="100%">
			<form:form method="post" action="save_config" commandName="shareplugin">
			<input type="hidden" name="c" value="${shareplugin.c}"/>
			<input type="hidden" name="prompt" value="name"/>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">		
					<tr>
						<td width="15%" align="right" class="skinLayout_info" valign="top"><msg:message code="shareplugin.select"/><msg:message code="system.common.sign.colon"/></td>
						<td width="85%" class="skinLayout_lable">
							<table id="spList"></table>
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
		<tr>
			<td class="tableMargin"></td>
		</tr>
		<tr>
			<td></td>
		</tr>
	</table>
</html>
