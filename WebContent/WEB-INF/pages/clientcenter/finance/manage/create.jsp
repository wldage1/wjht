<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="application/xhtml+xml;  charset=UTF-8">
<title></title>
<script type="text/javascript">
//国际化文件
var pleaseSelect = "<msg:message code='please.select'/>";
$(document).ready(function(){
	//存储路径
	var filePath = "";
	$("#uploadify").uploadify({
    	'swf'            : '${base}/${common}/flash/uploadify/uploadify.swf',//指定uploadify.swf路径   
       	'uploader'       : '${base}/clientcenter/finance/manage/uploadfile',//后台处理的请求的servlet,同时传递参数,由于servlet只能接收一个参数，所以将两个参数合并成一个。   
        'buttonClass'    : 'btn2',
        'queueID'        : 'fileQueue',
        'auto'           : true,//是否自动上传，即选择了文件即刻上传。  
        'multi'          : false,//是否允许同时上传多文件，默认false
        'fileTypeDesc'   : "<msg:message code='wealthManagement.fileTypeDesc'/>",               
        'fileTypeExts'   : "<msg:message code='wealthManagement.fileTypeExts'/>", //控制可上传文件的扩展名，启用本项时需同时声明fileDesc
        'buttonText'     : "<msg:message code='info.prompt.select'/>",   
		'width'          : 60,
		'height'         : 24,
		//'queueSizeLimit' : 1,
		//'uploadLimit'    : 1,
		'onInit'      	 : function() {
			var imgUrl = $("#image").val();
		     if(imgUrl != ""){
		    	 var id = (imgUrl).substring((imgUrl).lastIndexOf("/")+1, (imgUrl).lastIndexOf("."));
			     var fileName = imgUrl;
			     var showpath = "<div id='"+id+"'><span>";
			        showpath += fileName+"</span>";
			        showpath += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			        showpath += "<a href='#' class='delete' id='"+id+"' onclick=\"del('"+id+"','"+fileName+"')\">删除</a><br></div>";
			        $(showpath).appendTo($("#mytest"));
			      //显示图片
			        //var imgUrls = (imgUrl).substring(1, (imgUrl).lastIndexOf("/"));
			      if( $("#img").text() == ""){
				      $("#img").append("<img src='${base}"+"/"+${imagePath}+"/"+fileName+"' />");
				      $("#img").show();
			      }
			        $("#SWFUpload_0").width("0");
		     }else{
		    	 $("#img").hide();
		     }
		},
		 onUploadSuccess : function (file,data,response) {
	     	if(response==true){
	     		filePaht=$("#image").val();
	        	filePath=data;
		        $("#image").val(filePath);
		        //未上传时文件ID为上传后的文件名
		        var pathid = (data).substring((data).lastIndexOf("/")+1, (data).lastIndexOf("."));
		        var fileName = (data).substring((data).lastIndexOf("/")+1);
		        //显示图片
		        var imgUrl = (data).substring(1, (data).lastIndexOf("/"));
		        $("#img").text("");
		        $("#img").append("<img src='${base}"+"/"+imgUrl+"/"+fileName+"' />");
		        $("#img").show();
		        var showpath = "<div id='"+pathid+"'><span>";
		        showpath += file.name+"</span>";
		        showpath += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
		        showpath += "<a href='#' class='delete' id='"+pathid+"' onclick=\"del('" +pathid + "','" + fileName + "')\">删除</a><br></div>";
		        $(showpath).appendTo($("#mytest"));
		       
	         }else{
	               window.parent.Boxy.alert("<msg:message code='product.pdfFail'/>", null, {title: "<msg:message code='info.prompt'/>"});
	              }
	     	$("#SWFUpload_0").width("0");
	     }
    });
});
//删除上传文件
function del(id, fileName){
	$.post("deleteImgFile.json", {image :fileName },function(data){});
	$("#image").val("");
	$('#'+id).remove();
	$("#img").text("");
	$("#img").hide();
	$("#SWFUpload_0").width("60");
}
</script>

</head>
<body class="skinMain">
<form:form method="post" action="save" commandName="wealthManagement">
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
		<td width="100%">
				<input type="hidden" name="c" value="${wealthManagement.c}"/>
				<input type="hidden" name="prompt" value="name" />
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="wealthManagement.name"/><msg:message code="system.common.sign.colon"/></td>
						<td width="85%" class="skinLayout_lable">
						<form:input path="name" onchange="this.value=$.trim(this.value)" /><font color="red">&nbsp;*&nbsp;<form:errors path="name" /></font>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="wealthManagement.address"/><msg:message code="system.common.sign.colon"/></td>
						<td width="85%" class="skinLayout_lable">
						<form:input path="address" onchange="this.value=$.trim(this.value)" /><font color="red">&nbsp;*&nbsp;<form:errors path="address" /></font>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="wealthManagement.phone"/><msg:message code="system.common.sign.colon"/></td>
						<td width="70%" class="skinLayout_lable">
						<form:input path="phone" onchange="this.value=$.trim(this.value)" /><font color="red"><form:errors path="phone" /></font>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="wealthManagement.fax"/><msg:message code="system.common.sign.colon"/></td>
						<td width="70%" class="skinLayout_lable">
						<form:input path="fax" onchange="this.value=$.trim(this.value)" /><font color="red"><form:errors path="fax" /></font>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="wealthManagement.accuracy"/><msg:message code="system.common.sign.colon"/></td>
						<td width="70%" class="skinLayout_lable">
						<form:input path="accuracy" onchange="this.value=$.trim(this.value)" /><font color="red">&nbsp;&nbsp;<form:errors path="accuracy" /></font>
						</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="wealthManagement.latitude"/><msg:message code="system.common.sign.colon"/></td>
						<td width="70%" class="skinLayout_lable">
						<form:input path="latitude" onchange="this.value=$.trim(this.value)" /><font color="red">&nbsp;&nbsp;<form:errors path="latitude" /></font>
						</td>
					</tr>
					<tr>
						<td  width="15%" align="right" class="skinLayout_info">
							<msg:message code="wealthManagement.image"/><msg:message code="system.common.sign.colon"/>
						</td>
						<td width="85%" class="skinLayout_lable">
							<form:hidden path="image" />
							<p>
							<input type="file" name="uploadify" id="uploadify" />		
							</p>
							<div id="mytest"></div>
							<div id="img" />
						</td>
					</tr>
					<tr>
						<td width="15%" align="right" class="skinLayout_info"><msg:message code="wealthManagement.description"/><msg:message code="system.common.sign.colon"/></td>
						<td width="70%" class="skinLayout_lable">
						<form:textarea path="description" onchange="this.value=$.trim(this.value)" rows="6" cols="40"/><font color="red">&nbsp;&nbsp;<form:errors path="description" /></font>
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
			<td></td>
		</tr>
	</table>
</form:form>
</body>
</html>
