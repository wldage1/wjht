<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
var pleaseSelect = "<msg:message code='please.select'/>";
$(document).ready(function(){
	$.post("getAttrAndVal.json", { c: "${product.c}", prdId:"${product.prdId}" },
		function(data) {
		// 当前产品属性和值所包含的属性List
		var allList = data.allList;
		//属性长度
		var size = data.listSize;
		//循环属性值
		for(var i=0;i<size;i++){
			var showName ="";
				var id = allList[i].id   //属性ID
				var chineseName = allList[i].chineseName;  //中文名
				var showType = allList[i].showType; //显示类型
				var isRequired = allList[i].isRequired; //是否必填
				var isEnabled = allList[i].isEnabled; //是否启用
				var checkValue = allList[i].checkValue; //下拉框、单选、多选框值显示
				var prdValue = allList[i].prdValue; //产品属性对应值
				if(isEnabled != 0){
					showName += "<tr><td width='15%' align='right' class='skinLayout_info'>"+chineseName+"</td>";
					showName +="<td width='85%'>";
					var show =showTypeChoose(showType,isRequired,id,checkValue,prdValue);
					showName +=show;
					showName +="</td></tr>";
					$(showName).appendTo($("#prdTable"));
				}
		}
		function showTypeChoose(showType,isRequired,id,checkValue,prdValue){
			var show = "";
			if(showType){
				if(showType == 1){
					show +="<input type =text id= '"+id+"' name = '"+id+"' value='"+prdValue+"' disabled='true' />";
				}
				if(showType == 2){
					var chkValue = checkValue.split("&");
					show = "<select  id= '"+id+"' name='"+id+"' disabled='true'>"; 
					show+="<option value=''>"+pleaseSelect+"</option>";
					if(checkValue!=null && checkValue !=''){
						for(var i=0;i<chkValue.length;i++){
							var value = chkValue[i].split("|");
							if(prdValue==value[0]){
								show+="<option value='"+value[0]+"' selected >"+value[1]+"</option>";	     
							}else{
								show+="<option value='"+value[0]+"'>"+value[1]+"</option>";	    
							}
						}
					}
					show+="</select>"  						
				}
				if(showType == 3){
					show ="";
					if(checkValue!=null && checkValue !=''){
					var chkValue = checkValue.split("&");
						for(var i=0;i<chkValue.length;i++){
							var value = chkValue[i].split("|");
							if(prdValue==value[0]){
								show += " <input type='radio' value='"+value[0]+"'id= '"+id+"' name='"+id+"' checked='true' disabled='true'>"+value[1]+"";
							}else{
								show += " <input type='radio' value='"+value[0]+"'id= '"+id+"' name='"+id+"' disabled='true'>"+value[1]+"";
							}
						}
					}
				}
				if(showType == 4){
					show ="";
					if(checkValue!=null && checkValue !=''){
						var chkValue = checkValue.split("&"); //属性中所包含的所有选项
						var prdCkb = prdValue.split("-");	//产品值中保存的选项
						for(var i=0;i<chkValue.length;i++){
							var value = chkValue[i].split("|");
							var str = "";
							for(var j=0;j<prdCkb.length;j++){
								if(prdCkb[j]==value[0]){
									show += " <input type='checkbox' value='"+value[0]+"' id= '"+id+"'  name='"+id+"' checked='checked' disabled='true'>"+value[1]+"";
									str = prdCkb[j];
								}
							}
							if(str==""){
								show += " <input type='checkbox' value='"+value[0]+"' id= '"+id+"'  name='"+id+"' disabled='true'>"+value[1]+"";
							}
						}	
					}
				}
				if(showType==5){
					show ="<textarea  id= '"+id+"' name='"+id+"' disabled='true'>"+prdValue+"</textarea>";
				}
				if(showType == 6){
					show="<input id= '"+id+"'  name='"+id+"' type='text' onFocus='WdatePicker()' class='Wdate' style='width:88px;' readonly='readonly'  value='"+prdValue+"' disabled='true'>"
				}
				if(isRequired ==1){
					show += "<font color='red'>&nbsp;*&nbsp;</font>";
				}
			}
			return show;
		}
	});
});
</script>

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
					<form:form method="post" action="save" commandName="product" onsubmit="return formSubmit();">
						<form:hidden path="prdId" value="${product.prdId}"/>
						<form:hidden path="id"/>
						<form:hidden path="c"/>
						<form:hidden path="prompt" value="prdName"/>
						<form:hidden path="attributeAndValue" />
						<input type="hidden" name="prdFrom" value="1" />
						<table width="100%" border="0" cellspacing="0" cellpadding="0" id="prdTable" class="skinLayout">
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.prdtype"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
								<form:select path="prdType" disabled="true" >
									<form:option value="0">--<msg:message code="please.select"/>--</form:option>
									<form:options items="${productTypeList}" itemValue="value" itemLabel="name"/>
								</form:select>
								<font color="red">&nbsp;*&nbsp;<form:errors path="prdType"/></font>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.prdname"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="prdName"  disabled='true'/>
									<font color="red">&nbsp;*&nbsp;<form:errors path="prdName" />
									</font>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.No"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="prdNo" disabled='true'/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.prdSerialNo"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="crmPrdId" disabled='true'/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.prddescribe"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:textarea cols="40" rows="6" path="prdDescribe"  disabled='true'/>
									<font color="red"><form:errors path="prdDescribe" />
									</font>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.CHDBNo"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="finchinaSymbol"  disabled='true'/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.productFinishTime"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="productFinishTime" disabled="true" class="Wdate" onfocus="WdatePicker({minDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									 <msg:message code="product.prdstatus"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%">
									<form:radiobuttons path="prdStatus" items="${productStatueList}" itemValue="value" itemLabel="name" disabled='true'/><font color="red">&nbsp;*&nbsp;<form:errors path="prdStatus"/></font>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.uploadpdfipad"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<c:forEach items="${product.ipadFileList}" var="item" varStatus="i">
										<font color="black">${item.iPadFileName}</font>&nbsp;&nbsp;
										<font color="blue">『${item.iPadUrl}』</font>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${base}/${product.filePath}${item.iPadUrl}" id="${item.id}" ><msg:message code="product.downLoadPath"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${product.synURL}/${product.filePath}${item.iPadUrl}" id="${item.id}" ><msg:message code="product.downLoadPathSyn"/></a>	<br>
									</c:forEach>
									<div id="pdfIpad"></div>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.uploadpdfiphone"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<c:forEach items="${product.iphoneFileList}" var="item" varStatus="i">
										<font color="black">${item.iPhoneFileName}</font>&nbsp;&nbsp;
										<font color="blue">『${item.iPhoneUrl}』</font>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${base}/${product.filePath}${item.iPhoneUrl}" id="${item.id}" ><msg:message code="product.downLoadPath"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${product.synURL}/${product.filePath}${item.iPhoneUrl}" id="${item.id}" ><msg:message code="product.downLoadPathSyn"/></a>
										<br>
									</c:forEach>
									<div id="pdfIphone"></div>
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
