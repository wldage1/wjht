<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
var ids = "";
var verifyString ="";
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
				var verify = allList[i].verify; //产品属性验证信息
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
		function showTypeChoose(showType,isRequired,id,checkValue,prdValue){
			var show = "";
			if(showType){
				//文本框
				if(showType == 1){
					show +="<input type =text id= 'dt"+id+"' name = '"+id+"' value='"+prdValue+"'/>";
				}
				//下拉框
				if(showType == 2){
					var chkValue = checkValue.split("&");
					show = "<select  id= 'dt"+id+"' name='"+id+"'>"; 
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
				//单选框
				if(showType == 3){
					show ="";
					if(checkValue!=null && checkValue !=''){
					var chkValue = checkValue.split("&");
						for(var i=0;i<chkValue.length;i++){
							var value = chkValue[i].split("|");
							if(prdValue==value[0]){
								show += " <input type='radio' value='"+value[0]+"'id= 'dt"+id+"' name='"+id+"' checked>"+value[1]+"";
							}else{
								show += " <input type='radio' value='"+value[0]+"'id= 'dt"+id+"' name='"+id+"'>"+value[1]+"";
							}
						}
					}
				}
				//复选框
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
									show += " <input type='checkbox' value='"+value[0]+"' id= 'dt"+id+"'  name='"+id+"' checked='checked'>"+value[1]+"";
									str = prdCkb[j];
								}
							}
							if(str==""){
								show += " <input type='checkbox' value='"+value[0]+"' id= 'dt"+id+"'  name='"+id+"'>"+value[1]+"";
								
							}
						}		
					}
				}
				//文本域
				if(showType==5){
					show ="<textarea  id= 'dt"+id+"' name='"+id+"'>"+prdValue+"</textarea>";
				}
				//日期
				if(showType == 6){
					show="<input id= 'dt"+id+"'  name='"+id+"' type='text' onFocus='WdatePicker()' class='Wdate' style='width:88px;' readonly='readonly' value='"+prdValue+"' >"
				}
				//是否必填 0为不验证
				if(isRequired !=0 && isRequired!=""){
					show += "<font color='red'>&nbsp;*&nbsp;</font>";
				}
			}
			return show;
		}
	}); 

	//存储路径
	var filePathIPad = "";
	//国际化文件
	var deleteFile="<msg:message code='button.delete'/>";
	var downLoadPath="<msg:message code='product.downLoadPath'/>";
	var downLoadPathSyn="<msg:message code='product.downLoadPathSyn'/>";
	//pdf上传(IPad)
	$("#uploadify").uploadify({
                'swf'            : '${base}/${common}/flash/uploadify/uploadify.swf',//指定uploadify.swf路径   
       			'uploader'       : '${base}/productcenter/product/maintain/uploadfile',//后台处理的请求的servlet,同时传递参数,由于servlet只能接收一个参数，所以将两个参数合并成一个。   
                'buttonClass'    : 'btn2',
                'queueID'        : 'fileQueue',
                'auto'           : true,//是否允许同时上传多文件，默认false   
                'multi'          : true,//是否自动上传，即选择了文件即刻上传。  
                'fileTypeDesc'   : "pdf文件",               
        		'fileTypeExts'   : "*.pdf;", //控制可上传文件的扩展名，启用本项时需同时声明fileDesc    
                'buttonText'     : '<msg:message code='info.prompt.select'/>',   
		        'width'          : 60,
		        'height'         : 24,
	            onUploadSuccess: function (file,data,response) {	
                if(response==true){
                	filePathIPad = $("#pdfFileIPad").val();
                	var realFileName = (file.name).substring(0, (file.name).lastIndexOf("."));
                	var fileAtt = data.split("&");
	        		//文件上传后完整路径
	        		var filepath = fileAtt[0];
	        		//文件上传后文件名称
	        		var filename = (filepath).substring((filepath).lastIndexOf("/")+1,(filepath).length);
	        		//未上传时文件ID为上传后的文件名
	        		var pathid = (filename).substring(0, (filename).lastIndexOf("."));
                	filePathIPad+=realFileName+"#"+filename+"@";
	        		$("#pdfFileIPad").val(filePathIPad);
	        		var synUploadUrl = fileAtt[2];
	        		var showpath = "<div id='"+pathid+"'><span>";
	        		showpath += realFileName+"&nbsp;&nbsp;<font color='blue'>『"+filename+"』</font></span>";
	        		showpath += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	        		showpath += "<a href='#' class='delete' id='"+pathid+"' onclick=\"delIpad('"+pathid+"')\">"+deleteFile+"</a>";
	        		showpath += "&nbsp;&nbsp;&nbsp;&nbsp;";
	        		showpath += "<a href='${base}/"+filepath+"' class='delete' id='"+pathid+"' >"+downLoadPath+"</a>";
	        		showpath += "&nbsp;&nbsp;&nbsp;&nbsp";
	        		if(fileAtt[1] == 1){
	        			//同步成功
	        			showpath += "<a href='"+synUploadUrl+"/"+filepath+"' class='delete' id='"+pathid+"' >"+downLoadPathSyn+"</a><br></div>";
	        		}else{
	        			//同步失败
	        			showpath += "<span><font color='black'>"+downLoadPathSyn+"</font><span></a><br></div>";
	        			//同步失败不可保存
	        			$('#btnok').attr('disabled','true');
	        		}
	        		$(showpath).appendTo($("#pdfIpad"));
                }else{
                	window.parent.Boxy.alert("<msg:message code='product.pdfFail'/>", null, {title: "<msg:message code='info.prompt'/>"});
                }
         }  
    });
	
		//存储路径
	var filePathIPhone = "";
	//pdf上传(IPhone)
	$("#uploadify1").uploadify({
                'swf'            : '${base}/${common}/flash/uploadify/uploadify.swf',//指定uploadify.swf路径   
       			'uploader'       : '${base}/productcenter/product/maintain/uploadfile',//后台处理的请求的servlet,同时传递参数,由于servlet只能接收一个参数，所以将两个参数合并成一个。   
                'buttonClass'    : 'btn2',
                'queueID'        : 'fileQueue1',
                'auto'           : true,//是否允许同时上传多文件，默认false   
                'multi'          : true,//是否自动上传，即选择了文件即刻上传。  
                'fileTypeDesc'   : "pdf文件",               
        		'fileTypeExts'   : "*.pdf;", //控制可上传文件的扩展名，启用本项时需同时声明fileDesc    
                'buttonText'     : '<msg:message code='info.prompt.select'/>',   
		        'width'          : 60,
		        'height'         : 24,
	            onUploadSuccess: function (file,data,response) {	
                if(response==true){
                	filePathIPhone = $("#pdfFileIPhone").val();
                	var realFileName = (file.name).substring(0, (file.name).lastIndexOf("."));
                	var fileAtt = data.split("&");
                	//文件上传后完整路径
	        		var filepath = fileAtt[0];
	        		//文件上传后文件名称
	        		var filename = (filepath).substring((filepath).lastIndexOf("/")+1,(filepath).length);
	        		//未上传时文件ID为上传后的文件名
	        		var pathid = (filename).substring(0, (filename).lastIndexOf("."));
	        		var synUploadUrl = fileAtt[2];
	        		filePathIPhone+=realFileName+"#"+filename+"@";
                	$("#pdfFileIPhone").val(filePathIPhone);
	        		var showpath = "<div id='"+pathid+"'><span>";
	        		showpath += realFileName+"&nbsp;&nbsp;<font color='blue'>『"+filename+"』</font></span>";
	        		showpath += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	        		showpath += "<a href='#' class='delete' id='"+pathid+"' onclick=\"delIphone('"+pathid+"')\">"+deleteFile+"</a>";
	        		showpath += "&nbsp;&nbsp;&nbsp;&nbsp;";
	        		showpath += "<a href='${base}/"+filepath+"' class='delete' id='"+pathid+"' >"+downLoadPath+"</a>";
	        		showpath += "&nbsp;&nbsp;&nbsp;&nbsp";
	        		if(fileAtt[1] == 1){
	        			//同步成功
	        			showpath += "<a href='"+synUploadUrl+"/"+filepath+"' class='delete' id='"+pathid+"' >"+downLoadPathSyn+"</a><br></div>";
	        		}else{
	        			//同步失败
	        			showpath += "<span><font color='black'>"+downLoadPathSyn+"</font><span></a><br></div>";
	        			//同步失败不可保存
	        			$('#btnok').attr('disabled','true');
	        		}
	        		$(showpath).appendTo($("#pdfIphone"));
                }else{
                	window.parent.Boxy.alert("<msg:message code='product.pdfFail'/>", null, {title: "<msg:message code='info.prompt'/>"});
                }
         }  
    });
		
});

//删除数据库中保存的PDF记录
function delById(id){
	$.post("fileDel.json", {id :id },
        	function(data){
        	if(data.del=="success"){
        		$('#'+id).remove(); 
        	}
     });
}

//修改页面新上传——删除上传文件
function delIpad(id){
	var path = $("#pdfFileIPad").val();
	var newpath = "";
	//删除字符串中Id对应值
	var fileFile = path.split("@");
	for(var i=0;i<fileFile.length-1;i++){
		var fileNameFile = fileFile[i].split("#");
		var pathToId =  (0, (fileNameFile[1]).lastIndexOf("."));
		if(id!=pathToId){
			newpath += fileNameFile[0]+"#"+fileNameFile[1]+"@"
		}
	}
	$.post("deletePdfFile.json", {id :id },function(data){});
	$("#pdfFileIPad").val(newpath);
	$('#'+id).remove();
	$('#btnok').removeAttr('disabled');
}
//删除上传文件
function delIphone(id){
	var path = $("#pdfFileIPhone").val();
	var newpath = "";
	//删除字符串中Id对应值
	var fileFile = path.split("@");
	for(var i=0;i<fileFile.length-1;i++){
		var fileNameFile = fileFile[i].split("#");
		var pathToId =  (fileNameFile[1]).substring(0, (fileNameFile[1]).lastIndexOf("."));
		if(id!=pathToId){
			newpath += fileNameFile[0]+"#"+fileNameFile[1]+"@"
		}
	}
	$.post("deletePdfFile.json", {id :id },function(data){});
	$("#pdfFileIPhone").val(newpath);
	$('#'+id).remove();
	$('#btnok').removeAttr('disabled');
}
//国际化提示文件
var prdNameChecking = "<msg:message code='checking.prdname'/>";
var prdNoChecking = "<msg:message code='checking.prdNo'/>";
var finchinaSymbolChecking = "<msg:message code='checking.finchinaSymbol'/>";
var notNull = "<msg:message code='checking.notNull'/>";
var crmPrdIdChecking = "<msg:message code='checking.crmPrdId'/>";

function formSubmit(){
	//验证
	var prdName = $("#prdName").val();
	var prdNo = $("#prdNo").val();
	var finchinaSymbol = $("#finchinaSymbol").val();
	var crmPrdId = $("#crmPrdId").val();
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
	//验证产品序号 只能为数字
	if(crmPrdId.length>0&&crmPrdId.match(/\D/g,'')){
		alert(crmPrdIdChecking);
		$("#crmPrdId").focus();
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
				if(verifyIsRequired==1 ){
					var inputType =  $("#"+verifyOne[0]).attr("type");
					if(inputType == "radio" || inputType == "checkbox"){
						if($("input[name='"+verifyOne[0]+"']:checked").length==0){
							alert("“"+verifyChineseName+"”"+notNull);
							return false;
						}
					}
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
			var ttName = $("#dt"+idByOne).val();
			var inputType =  $("#dt"+idByOne).attr("type");
			//如果为checkbox获取全部已选ID
			if(inputType=="checkbox"){
				var ckbValue ="";
				var ckb = document.getElementsByName("dt"+idByOne);
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
		$("#attributeAndValue").val(attributeAndValue)  ;
	}
}
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
						<input type="hidden" name="prdFrom" value="${product.prdFrom}" />
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
									<form:input path="prdName" maxlength="50"/>
									<font color="red">&nbsp;*&nbsp;<form:errors path="prdName" />
									</font>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.No"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="prdNo"  maxlength="50"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.prdSerialNo"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="crmPrdId"  maxlength="50"/>
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
									<form:input path="finchinaSymbol" maxlength="50"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.productFinishTime"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:input path="productFinishTime" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									 <msg:message code="product.prdstatus"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%">
									<form:radiobuttons path="prdStatus" items="${productStatueList}" itemValue="value" itemLabel="name"/><font color="red">&nbsp;*&nbsp;<form:errors path="prdStatus"/></font>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.shared"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%">
									<input type="radio" name="shared" value="1" <c:if test="${product.shared == 1}">checked="checked"</c:if>/><msg:message code="product.shared.yes"/>&nbsp;&nbsp;
									<input type="radio" name="shared" value="0" <c:if test="${product.shared == 0}">checked="checked"</c:if>/><msg:message code="product.shared.no"/>
									<font color="red">&nbsp;*&nbsp;<form:errors path="shared" /></font>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.uploadpdfipad"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:hidden path="pdfFileIPad" />
									<div id="fileQueue" ></div>  
									<p>
										<input type="file" name="uploadify" id="uploadify" />		
									</p>
									<c:forEach items="${product.ipadFileList}" var="item" varStatus="i">
										<div id="${item.id}"><span>${item.iPadFileName}&nbsp;&nbsp;
										<font color="blue">『${item.iPadUrl}』</font></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="#" id="${item.id}" onclick="delById(${item.id})"><msg:message code="button.delete"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${base}/${product.filePath}${item.iPadUrl}" id="${item.id}" ><msg:message code="product.downLoadPath"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${product.synURL}/${product.filePath}${item.iPadUrl}" id="${item.id}" ><msg:message code="product.downLoadPathSyn"/></a>
										<br></div>
									</c:forEach>
									<div id="pdfIpad"></div>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="product.uploadpdfiphone"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:hidden path="pdfFileIPhone" />
									<div id="fileQueue1" ></div>  
									<p>
										<input type="file" name="uploadify1" id="uploadify1" />		
									</p>
									<c:forEach items="${product.iphoneFileList}" var="item" varStatus="i">
										<div id="${item.id}"><span>${item.iPhoneFileName}&nbsp;&nbsp;
										<font color="blue">『${item.iPhoneUrl}』</font></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="#" id="${item.id}"  onclick="delById(${item.id})" ><msg:message code="button.delete"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${base}/${product.filePath}${item.iPhoneUrl}" id="${item.id}" ><msg:message code="product.downLoadPath"/></a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="${product.synURL}/${product.filePath}${item.iPhoneUrl}" id="${item.id}" ><msg:message code="product.downLoadPathSyn"/></a>
										<br></div>
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
									<input type="submit" class="btn2" name="btnok" value="<msg:message code="button.save"/>" id="btnok" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
