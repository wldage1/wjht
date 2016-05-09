<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
var addedStatus=${addedStatus}
if(addedStatus == 1){
	window.parent.Boxy.alert("<msg:message code='product.addedExist'/>", null, {title: "<msg:message code='info.prompt'/>"});
}
var ids = "";
var verifyString ="";
var pleaseSelect = "<msg:message code='please.select'/>";
$(document).ready(function(){
	$.post("getCrmPrd.json", { c: "${product.c}", prdType:"${product.prdType}",sysCrmId:"${product.sysCrmId}" },
		function(data) {
		// 当前产品属性和值所包含的属性List
		var allList = data.allList;
		//属性长度
		var size = data.listSize;
		var typeNo = data.typeNo;
		if(typeNo=="true"){
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
					var verify = allList[i].verify; //下拉框、单选、多选框值显示
					if(isEnabled != 0){
						showName += "<tr><td width='15%' align='right' class='skinLayout_info'>"+chineseName+"</td>";
						showName +="<td width='85%'>";
						var show =showTypeChoose(showType,isRequired,id,checkValue,prdValue);
						showName +=show;
						showName +="</td></tr>";
						if(i != 0)
							ids+=",";
						ids +=id;
						if(isRequired==1){
							verifyString +=id+"#"+isRequired+"#"+verify+"#"+chineseName+"@";
						}	
						$(showName).appendTo($("#prdTable"));
					}
			}
		}else{
			$('#btnok').attr('disabled',"true");
		}
		function showTypeChoose(showType,isRequired,id,checkValue,prdValue){
			var show = "";
			if(showType){
				if(showType == 1){
					show +="<input type =text id= '"+id+"' name = '"+id+"' value='"+prdValue+"'/>";
				}
				if(showType == 2){
					var chkValue = checkValue.split("&");
					show = "<select  id= '"+id+"' name='"+id+"'>"; 
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
								show += " <input type='radio' value='"+value[0]+"'id= '"+id+"' name='"+id+"' checked>"+value[1]+"";
							}else{
								show += " <input type='radio' value='"+value[0]+"'id= '"+id+"' name='"+id+"'>"+value[1]+"";
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
									show += " <input type='checkbox' value='"+value[0]+"' id= '"+id+"'  name='"+id+"' checked='checked'>"+value[1]+"";
									str = prdCkb[j];
								}
							}
							if(str==""){
								show += " <input type='checkbox' value='"+value[0]+"' id= '"+id+"'  name='"+id+"'>"+value[1]+"";
							}
						}	
					}
				}
				if(showType==5){
					show ="<textarea  id= '"+id+"' name='"+id+"'>"+prdValue+"</textarea>";
				}
				if(showType == 6){
					show="<input id= '"+id+"'  name='"+id+"' type='text' onFocus='WdatePicker()' class='Wdate' style='width:88px;' readonly='readonly'  value='"+prdValue+"' >"
				}
				//是否必填
				if(isRequired !=0 && isRequired!=""){
					show += "<font color='red'>&nbsp;*&nbsp;</font>";
				}
			}
			return show;
		}
	});
});

//国际化提示文件
var prdTypeChecking = "<msg:message code='checking.prdtype'/>";
var prdNameChecking = "<msg:message code='checking.prdname'/>";
var prdNoChecking = "<msg:message code='checking.prdNo'/>";
var finchinaSymbolChecking = "<msg:message code='checking.finchinaSymbol'/>";
var notNull = "<msg:message code='checking.notNull'/>";

function formSubmit(){
	//验证
	var prdName = $("#prdName").val();
	var prdNo = $("#prdNo").val();
	var finchinaSymbol = $("#finchinaSymbol").val();
	//验证产品名称
	if(prdName.length==0){
		alert(prdNameChecking);
		$("#prdName").focus();
		return false;
	}
	//验证财汇数据库编号
	if(finchinaSymbol.length>0&&finchinaSymbol.match(/\D/g,'')){
		alert(finchinaSymbolChecking);
		$("#finchinaSymbol").focus();
		return false;
	}

	//产品属性填入值验证
	if(verifyString!=""){
		var verifyArray = verifyString.split("@");
		if(verifyArray.length>0){
			for(var s=0;s<verifyArray.length-1;s++){
				var verifyOne = verifyArray[s].split("#");
				//取填入值
				var verifyName = $("#"+verifyOne[0]).val();
				//是否必填 0：否 1：是
				var verifyIsRequired = verifyOne[1];
				//提示信息
				var verifyAlert = verifyOne[2];
				//验证规则（正则）
				var verifyRule = verifyOne[3];
				var re = new RegExp(verifyRule,'g');
				//中文名
				var verifyChineseName = verifyOne[4];
				//非空验证（必填为非空）
				if(verifyIsRequired==1){
					if(verifyName.length==0){
						alert("“"+verifyChineseName+"”"+notNull);
						$("#"+verifyName).focus();
						return false;
					}
					//正则验证
					if(verifyAlert!=1&&verifyRule!=1){
						if(verifyName.length>0&&re.exec(verifyName)==null){
							alert("“"+verifyChineseName+"”"+verifyAlert);
							$("#"+verifyName).focus();
							return false;
						}
					}
				}
			}		
		}
	}
	var attributeAndValue = "";
	if(ids !=""){
		var idsArray = ids.split(",");
		for(var j = 0;j<idsArray.length;j++){
			var idByOne = idsArray[j];
			var ttName = $("#"+idByOne).val();
			var inputType =  $("#"+idByOne).attr("type");
			//如果为checkbox获取全部已选ID
			if(inputType=="checkbox"){
				var ckbValue ="";
				var ckb = document.getElementsByName(idByOne);
				for(var t=0;t<ckb.length;t++){
					if(ckb[t].checked){
						ckbValue += ckb[t].value+"-";
					}
				}
				ttName = ckbValue;
			}
			//如果为radio获取当前选中值
			if(inputType=="radio"){
				var val=$("input[name='"+idByOne+"']:checked").val();
				ttName = val;
			}
			if(j!=0){
				attributeAndValue += '@';
			}
			attributeAndValue +=idByOne+"#"+ttName;
		}
		$("#attributeAndValue").val(attributeAndValue);
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
						<input type="hidden" name="addedStatus" value="${addedStatus }"/> 
						<input type="hidden" name="crmPrdId" value="${product.crmPrdId }"/>
						<input type="hidden" name="prdType" value="${product.prdType }"/>
						<input type="hidden" name="c" value="${product.c }"/>
						<input type="hidden" name="prdFrom" value="2"/>
						<input type="hidden" name="sysCrmId" value="${product.sysCrmId}"/>
						<form:hidden path="prompt" value="prdName"/>
						<form:hidden path="attributeAndValue" />
						<table width="100%" border="0" cellspacing="0" cellpadding="0" id="prdTable" class="skinLayout">
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.prdtype"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<input type="text"  name="prdTypeChn" value="${product.prdTypeChn }" readonly="readonly">
									<font color="red">&nbsp;*&nbsp;
									<c:if test="${product.prdType == null }">
										&nbsp;&nbsp;<msg:message code="product.typeNo"/>
									</c:if>
									</font>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.prdname"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="prdName" maxlength ="50"/>
									<font color="red">&nbsp;*&nbsp;
									</font>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.No"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="prdNo" maxlength ="50"/>
								</td>
							</tr>
							<tr>
								<td width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.prddescribe"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:textarea cols="40" rows="6" path="prdDescribe" />
									<font color="red"><form:errors path="prdDescribe" />
									</font>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.CHDBNo"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="finchinaSymbol" maxlength ="50" readonly="true"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.productFinishTime"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="productFinishTime" maxlength ="50" />
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.prdstatus"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%">
									<input type="radio" name="prdStatus"  value="1" /><msg:message code="product.start"/>&nbsp;&nbsp;
									<input type="radio" name="prdStatus"  value="2" checked="checked"/><msg:message code="product.stop"/>
									<font color="red">&nbsp;*&nbsp;<form:errors path="prdStatus" /></font>
								</td>
							</tr>
							</table>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
							<tr>
								<td width="15%" align="right">
									&nbsp;
								</td>
								<td width="85%" colspan="2" align="left">
									<c:if test="${addedStatus != 1}">
										<input type="submit" class="btn2" name="btnok" value="<msg:message code="button.save"/>"  id="btnok" />
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</c:if>
									<input type="button" class="btn2"
										onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}'"
										name="btnback" value="<msg:message code="button.back"/>"
										id="btnback" />
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
