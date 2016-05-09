<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
var count = 0;
var showType = "<option value=\"${showTypeText}\"><msg:message code='structure.showType.text'/></option>";
	showType += "<option value=\"${showTypeSelect}\"><msg:message code='structure.showType.select'/></option>";
	showType += "<option value=\"${showTypeRadio}\"><msg:message code='structure.showType.radio'/></option>";
	showType += "<option value=\"${showTypeCheckbox}\"><msg:message code='structure.showType.checkbox'/></option>";
	showType += "<option value=\"${showTypeTextArea}\"><msg:message code='structure.showType.textarea'/></option>";
	showType += "<option value=\"${showTypeDate}\"><msg:message code='structure.showType.date'/></option>";
	
var verify = "";
	<c:forEach var="verify" items="${productStructureVerifyList}">
		verify += "<option value=\"${verify.value}\">${verify.name}</option>";
	</c:forEach>
$(document).ready(function(){
    jQuery("#structureTable").jqGrid({
    	datatype: "local",
        colNames: ["<msg:message code='structure.chineseName'/><font color=\"red\">&nbsp;*&nbsp;</font>", 
                   "<msg:message code='structure.englishName'/><font color=\"red\">&nbsp;*&nbsp;</font>",
                   "<msg:message code='structure.isRequired'/>",
                   "<msg:message code='structure.isEnabled'/>",
                   "<msg:message code='structure.showType'/>",
                   "<msg:message code='structure.verify'/>",
                   "<msg:message code='structure.verifyValue'/>",
                   "<msg:message code='structure.isSreach'/>",
                   "<msg:message code='structure.isShowMyProduct'/>",
                   "<msg:message code='structure.isShowProductCounter'/>",
                   "<msg:message code='structure.isShowOnList'/>",
                   "<msg:message code='structure.isShowOnContent'/>",
                   "<msg:message code='structure.isOrder'/>",
                   "<msg:message code='structure.value'/>",
                   "<img id='addRow' src='${base}/${common}/${style}/images/icon/Add.png' width='20' height='20' />"
                   ],
        colModel: [
                   {name: 'chineseName',index:'chineseName',width:'13%',align:'center',hidden: false,sortable:false},
                   {name: 'englishName',index:'englishName',width:'13%',align:'center',hidden: false,sortable:false},
                   {name: 'isRequired',index:'isRequired',width:'4%',align:'center',hidden: false,sortable:false},
                   {name: 'isEnabled',index:'isEnabled',width:'4%',align:'center',hidden: false,sortable:false},
                   {name: 'showType',index:'showType',width:'7%',align:'center',hidden: false,sortable:false},
                   {name: 'verify',index:'verify',width:'9%',align:'center',hidden: false,sortable:false},
                   {name: 'verifyValue',index:'verifyValue',width:'0%',align:'center',hidden: true,sortable:false},
                   {name: 'isSreach',index:'isSreach',width:'8%',align:'center',hidden: false,sortable:false},
                   {name: 'isShowMyProduct',index:'isShowMyProduct',width:'8%',align:'center',hidden: false,sortable:false},
                   {name: 'isShowProductCounter',index:'isShowProductCounter',width:'8%',align:'center',hidden: false,sortable:false},
                   {name: 'isShowOnList',index:'isShowOnList',width:'8%',align:'center',hidden: false,sortable:false},
                   {name: 'isShowOnContent',index:'isShowOnContent',width:'8%',align:'center',hidden: false,sortable:false},
                   {name: 'isOrder',index:'isOrder',width:'4%',align:'center',hidden: false,sortable:false},
                   {name: 'value',index:'value',width:'4%',align:'center',hidden: false,sortable:false},
                   {name: 'operate',index:'operate',width:'2%',align:'center',hidden: false,sortable:false}
                   ],
        height:'auto',
        autowidth: true,
        shrinkToFit:true
    });
    $('#addRow').click(function(){
    	var data={
    			chineseName:"<input type='text' id='chineseName"+(parseInt(count)+1)+"' maxlength='25' onclick=\"changeStyle('chineseName"+(parseInt(count)+1)+"');\" onkeyup=\"checkChineseName(event,this,'edit');\" onfocusout=\"checkChineseName(event,this,'paste');\" onpropertychange=\"checkChineseName(event,this,'edit');\" oninput=\"checkChineseName(event,this,'edit');\"/>",
    			englishName:"<input type='text' id='englishName"+(parseInt(count)+1)+"' maxlength='30' onclick=\"changeStyle('englishName"+(parseInt(count)+1)+"');\" onkeyup=\"checkEnglishName(event,this,'edit');\" onfocusout=\"checkEnglishName(event,this,'paste');\" onpropertychange=\"checkEnglishName(event,this,'edit');\" oninput=\"checkEnglishName(event,this,'edit');\"/>",
    			isRequired:"<select id='isRequired"+(parseInt(count)+1)+"'><option value='0'><msg:message code='no'/></option><option value='1'><msg:message code='yes'/></option></select>",
    			isEnabled:"<select id='isEnabled"+(parseInt(count)+1)+"'><option value='1'><msg:message code='yes'/></option><option value='0'><msg:message code='no'/></option></select>",
    			showType:"<select id='showType"+(parseInt(count)+1)+"' onchange=\"changeShowType('"+(parseInt(count)+1)+"');\">"+showType+"</select>",
    			verify:"<select id='verify"+(parseInt(count)+1)+"' onchange=\"changeVerify('"+(parseInt(count)+1)+"')\">"+verify+"</select>",
    			verifyValue:"<input type='hidden' id='verifyValue"+(parseInt(count)+1)+"' />",
    			isSreach:"<select id='isSreach"+(parseInt(count)+1)+"'><option value='0'><msg:message code='no'/></option><option value='1'><msg:message code='yes'/></option></select>",
    			isShowMyProduct:"<select id='isShowMyProduct"+(parseInt(count)+1)+"'><option value='1'><msg:message code='yes'/></option><option value='0'><msg:message code='no'/></option></select>",
    			isShowProductCounter:"<select id='isShowProductCounter"+(parseInt(count)+1)+"'><option value='1'><msg:message code='yes'/></option><option value='0'><msg:message code='no'/></option></select>",
    			isShowOnList:"<select id='isShowOnList"+(parseInt(count)+1)+"'><option value='1'><msg:message code='structure.myProduct'/></option><option value='2'><msg:message code='structure.productCounter'/></option><option value='3'><msg:message code='structure.all.show'/></option><option value='4'><msg:message code='structure.all.noshow'/></option></select>",
    			isShowOnContent:"<select id='isShowOnContent"+(parseInt(count)+1)+"'><option value='1'><msg:message code='structure.myProduct'/></option><option value='2'><msg:message code='structure.productCounter'/></option><option value='3'><msg:message code='structure.all.show'/></option><option value='4'><msg:message code='structure.all.noshow'/></option></select>",
    			//isOrder:"<select id='isOrder"+(parseInt(count)+1)+"'><option value='1'><msg:message code='yes'/></option><option value='0'><msg:message code='no'/></option></select>",
    			isOrder:"<input type='text' id='isOrder"+(parseInt(count)+1)+"' maxlength='5' value='"+(parseInt(count)+1)+"' style='width:35px;' onkeyup=\"checkNumber(event,this,'edit');\" onfocusout=\"checkNumber(event,this,'paste');\" onpropertychange=\"checkNumber(event,this,'edit');\" oninput=\"checkNumber(event,this,'edit');\"/>",
    			//value:"<input type='text'  id='value"+(parseInt(count)+1)+"' onfocus=\"setValue('"+(parseInt(count)+1)+"');\" readonly=\"readonly\" disabled=\"disabled\" style='cursor:pointer;'/>",
    			value:"<img id='img"+(parseInt(count)+1)+"' src='${base}/${common}/${style}/images/icon/detail.png' width='20' height='20' style='cursor:pointer;'/><input type='hidden' id='value"+(parseInt(count)+1)+"' />",
    			operate:"<a href='javascript:void(0);' id='" + (parseInt(count)+1) + "' onclick='delRow(this);'><img src='${base}/${common}/${style}/images/icon/Del.png' width='20' height='20'/></a>"
				};
		jQuery("#structureTable").jqGrid('addRowData',(parseInt(count)+1),data);  
		count++;
     });
});

function checkNumber(evt,object,flag){
	var key = window.event?event.keyCode:evt.which;
	var re = /^[0-9]*[1-9][0-9]*$/;
	if(key != 8){
		var value = $(object).val();
		if(!re.test(value)){
			if("paste" == flag){
				$(object).val("");
			}else{
				if(value != null && value != ""){
					$(object).val(value.substring(0,value.length-1));
				}
			}
		}
	}
}

function delRow(o){
  	var rowid = $(o).attr("id");
	jQuery("#structureTable").jqGrid('delRowData',rowid);  
}
function getArrayCount(temp,array){
	var count = 0;
	for(var i=0; i < array.length; i++){
		if(temp == array[i]){
			count++;
		}
	}
	return count;
}


function formSubmit(){
	var errorCount = 0;
	if($("#productTypeId").val()==""){
		$("#productTypeIdError").html("<msg:message code='please.selectProductType'/>");
		errorCount++;
	}
	var structureString = "";
	var ids = jQuery("#structureTable").jqGrid('getDataIDs');
	var englishNames = new Array();
	for(var i=0;i < ids.length;i++){
		var id = ids[i];
		var englishName = $("#englishName"+id).val();
		englishNames.push(englishName);
	}
	var enflag = 0;
	for(var i=0;i < ids.length;i++){
		var id = ids[i];
		var chineseName = $("#chineseName"+id).val();
		var englishName = $("#englishName"+id).val();
		var isRequired = $("#isRequired"+id).val();
		var isEnabled = $("#isEnabled"+id).val();
		var showType = $("#showType"+id).val();
		var value = $("#value"+id).val();
		var verify = $("#verify"+id).val();
		var verifyValue = $("#verifyValue"+id).val();
		var isSreach = $("#isSreach"+id).val();
		var isShowMyProduct = $("#isShowMyProduct"+id).val();
		var isShowProductCounter = $("#isShowProductCounter"+id).val();
		var isShowOnList = $("#isShowOnList"+id).val();
		var isShowOnContent = $("#isShowOnContent"+id).val();
		var isOrder = $("#isOrder"+id).val();
		if(chineseName == "" || $("#chineseName"+id).attr("class") == "prompt"){
			$("#chineseName"+id).attr("class","prompt");
			$("#chineseName"+id).val("<msg:message code='please.input'/>");
			errorCount++;
		}
		if(englishName == "" || $("#englishName"+id).attr("class") == "prompt"){
			$("#englishName"+id).attr("class","prompt");
			$("#englishName"+id).val("<msg:message code='please.input'/>");
			errorCount++;
		}else{
			if(getArrayCount(englishName,englishNames) > 1){
				if(enflag == 0){
					enflag++;					
				}else{
					$("#englishName"+id).attr("class","prompt");
					$("#englishName"+id).val("<msg:message code='engilshname.already'/>");
					errorCount++;
				}
			}
		}
		if(errorCount==0){
			structureString += chineseName+"#";
			structureString += englishName+"#";
			structureString += isRequired+"#";
			structureString += isEnabled+"#";
			structureString += showType+"#";
			structureString += value+"#";
			structureString += verify+"#";
			structureString += verifyValue+"#";
			structureString += isSreach+"#";
			structureString += isShowMyProduct+"#";
			structureString += isShowProductCounter+"#";
			structureString += isShowOnList+"#";
			structureString += isShowOnContent+"#";
			structureString += isOrder+"#0";
			if(i != ids.length-1)
				structureString += "@";
		}
	}
	if(errorCount==0){
		$("#structureString").val(structureString);
		return true;
	}else{
		return false;
	}
 }
function changeStyle(object){
	if($("#"+object).attr("class") == "prompt"){
		$("#"+object).val("");
		$("#"+object).attr("class","normal");
	}
}
function changeShowType(index){
	if($("#showType"+index).val()=="${showTypeText}" || $("#showType"+index).val()=="${showTypeTextArea}" || $("#showType"+index).val()=="${showTypeDate}"){
		document.getElementById('img'+index).onclick ='';
	}else{
		document.getElementById('img'+index).onclick =function onclick(){ setValue(''+index);};
		 
	}
	$("#value"+index).val("");
}
function changeVerify(index){
	var v = $("#verify"+index).val();
	<c:forEach var="verify" items="${productStructureVerifyList}">
		if("${verify.value}"==v){
	 		$("#verifyValue"+index).val("${verify.description}");
		}
	</c:forEach>
}
function changeProductType(){
	$("#productTypeName").val(document.getElementById("productTypeId").options[document.getElementById("productTypeId").selectedIndex].text); 
}
function addValue(){
	$("#structureValueTable",window.parent.document).append("<tr><td><input type='text'/></td><td><a href='javascript:void(0);' onclick='content_${fn:substring(structure.c, 0, fn:length(structure.c)-2)}.window.delValue(this);'><img src='${base}/${common}/${style}/images/icon/Del.png' width='20' height='20'/></a></td></tr>");
}
function delValue(object){
	$(object).parent("td").parent("tr").remove();  
}
function setValue(index){
	var value = $("#value"+index).val();
	var inputHtml = "";
		inputHtml += "<table>";
		inputHtml += "<tr>";
		inputHtml += "<td>";
		inputHtml += "<table id='structureValueTable'>";
	if(value == ""){
		inputHtml += "<tr>";
		inputHtml += "<td><input type='text' maxlength='20'/></td>";
		inputHtml += "<td><a href='javascript:void(0);' onclick=\"content_${fn:substring(structure.c, 0, fn:length(structure.c)-2)}.window.addValue();\"><img src='${base}/${common}/${style}/images/icon/Add.png' width='20' height='20'/></a></td>";
		inputHtml += "</tr>";
	}else{
		for(var i = 0; i < value.split("&").length; i++){
			inputHtml += "<tr>";
			inputHtml += "<td><input type='text' value='"+value.split("&")[i].split("|")[1]+"' maxlength='20' /></td>";
			inputHtml += "<td>";
			if(i==0){
				inputHtml += "<a href='javascript:void(0);' onclick=\"content_${fn:substring(structure.c, 0, fn:length(structure.c)-2)}.window.addValue();\"><img src='${base}/${common}/${style}/images/icon/Add.png' width='20' height='20'/></a>";
			}else{
				inputHtml += "<a href='javascript:void(0);' onclick='content_${fn:substring(structure.c, 0, fn:length(structure.c)-2)}.window.delValue(this);'><img src='${base}/${common}/${style}/images/icon/Del.png' width='20' height='20'/></a>";
			}
			inputHtml += "</td>";
			inputHtml += "</tr>";
		}
	}
		inputHtml += "</table>";
		inputHtml += "</td>";
		inputHtml += "</tr>";
		inputHtml += "<tr>";
		inputHtml += "<td align='right'><span id='errorValueMessage' style='color:red;'></span>&nbsp;&nbsp;&nbsp;<input type='button' value='<msg:message code='button.confirm'/>' class='boxy-btn1' onclick=\"content_${fn:substring(structure.c, 0, fn:length(structure.c)-2)}.window.structureValueBoxySubmit('"+index+"');\" style='cursor:pointer'/></td>";
		inputHtml += "</tr>";
		inputHtml += "</table>";
	if(top.structureValueBoxy != null){
		top.structureValueBoxy.setContent(inputHtml);
		top.structureValueBoxy.show();
	}else{
		top.structureValueBoxy = new window.parent.Boxy(inputHtml,{title:"<msg:message code='add.option.value'/>",closeText:"[<msg:message code='button.colse'/>]",modal:true,center:true});
	}
}
function structureValueBoxySubmit(index){
	var value = "";
	var array = new Array();
	var errorFlag = false;
	$("#structureValueTable input[type='text']",window.parent.document).each(function(i){ 
		if(errorFlag == false){
			if($(this).val() != ""){
			  if($.inArray($(this).val(),array) == -1){
				  array.push($(this).val()); 
				  value += (i+1)+"|"+$(this).val()+"&";
			  }else{
				  errorFlag = true;
			  }			
			}
		}
	});
	if(errorFlag){
		$("#errorValueMessage",window.parent.document).html("<msg:message code='value.repeat'/>");
	}else{
	    $("#value"+index).val(value.substring(0, value.length-1));
	    top.structureValueBoxy.hide();
	}
}
//控制中文
function checkChineseName(evt,object,flag){
	var key = window.event?event.keyCode:evt.which;
	var re = /^(?!_)(?!.*?_$)[a-zA-Z0-9_\u4e00-\u9fa5]+$/;
	if(key != 8){
		var value = $(object).val();
		if(!re.test(value)){
			if("paste" == flag){
				$(object).val("");
			}else{
				if(value != null && value != ""){
					$(object).val(value.substring(0,value.length-1));
				}
			}
		}
	}
}
//控制英文
function checkEnglishName(evt,object,flag){
	var key = window.event?event.keyCode:evt.which;
	var re = /^\w+$/;
	if(key != 8 && $(object).val() != "<msg:message code='please.input'/>" && $(object).val() != "<msg:message code='engilshname.already'/>"){
		var value = $(object).val();
		if(!re.test(value)){
			if("paste" == flag){
				$(object).val("");
			}else{
				if(value != null && value != ""){
					$(object).val(value.substring(0,value.length-1));
				}
			}
		}
	}
}
</script>
<style type="text/css">
	.prompt{
		color:red;
	}
	.normal{
		color:black;
	}
</style>
</head>
<body class="skinMain">
	<form:form method="post" action="save" commandName="structure" onsubmit="return formSubmit();">
		<input type="hidden" name="c" value="${structure.c}"/>
		<input type="hidden" name="prompt" value="productTypeName" />
		<form:hidden path="productTypeName"/>
		<form:hidden path="structureString"/>
		<table cellpadding="0" cellspacing="1" border="0" width="100%" class="skinMain">
			<tr>
				<td width="100%">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinNav">
						<tr>
							<th width="2%"><img src="${base}/${common}/${style}/images/nav/bg_07.gif" width="20" height="20" /></th>
							<th width="98%"><msg:message code="navigation.title"/>&nbsp;${navigation}</th>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table cellpadding="0" cellspacing="1" border="0" width="100%" class="search">
						<tr>
							<td align="right" width="10%" class="search_info"><msg:message code='product.type'/><msg:message code="system.common.sign.colon"/></td>
							<td align="left" width="70%" class="search_lable">
								<form:select path="productTypeId" onchange="changeProductType();">
									<form:option value="">--<msg:message code='please.select'/>--</form:option>
									<form:options items="${productSortList}" itemValue="value" itemLabel="name"/>
								</form:select>
								<font color="red">&nbsp;*&nbsp;<span id="productTypeIdError"></span></font>
							</td>
							<td width="20%" class="button_area" align="center">
								<input type=submit class="btn2"  value="<msg:message code='button.save'/>">
								<input type="button" class="btn2" onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}'" name="btnback" value="<msg:message code="button.back"/>" id="btnback"/>
							</td>
						</tr>			
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table id="structureTable"><tr><td>&nbsp;</td></tr></table>
					<div id="pagered"></div>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>