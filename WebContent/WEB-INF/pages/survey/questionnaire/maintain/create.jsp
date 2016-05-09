<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript">
var editor;
KindEditor.ready(function(K) {
        editor = K.create('#queDescribe_kind');
});

function selectQueStartTime(){
	WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queEndTime\')||\'2080-01-01\'}'});
}
function selectQueEndTime(){
	WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queStartTime\')||\'2080-01-01\'}'});
}

$(document).ready(function(){
	  var deleteFile="<msg:message code='button.delete'/>";
	  var downLoadPath="<msg:message code='product.downLoadPath'/>";
	  var downLoadPathSyn="<msg:message code='product.downLoadPathSyn'/>";
	  var  purl = "${base}/survey/questionnaire/maintain/uploadfile;jsessionid=<%=session.getId()%>?" ;
	  var  uploadUrlIpad = purl + "queImgType=Ipad"; 
	  var  uploadUrlIphone = purl + "queImgType=Iphone"; 
	  
	$("#uploadify").uploadify({
        'swf'            : '${base}/${common}/flash/uploadify/uploadify.swf',//指定uploadify.swf路径   
		'uploader'       : uploadUrlIpad,  
        'buttonClass'    : 'btn2',
        'queueID'        : 'fileQueue',
        'multi'          : false,
        'sizeLimit'		 : 86400 ,     //这里是文件最大值 byte
        'fileTypeDesc'   : "jpg文件",               
		'fileTypeExts'   : "*.jpg;", //控制可上传文件的扩展名，启用本项时需同时声明fileDesc           		
        'buttonText'     : '<msg:message code='info.prompt.select'/>',   
        'width'          : 60,
        'height'         : 24,
        onUploadSuccess: function (file,data,response) {	
        if(response==true){
        	var objdata = JSON.parse(data);
        	//原始文件名称
        	var realFileName = (file.name).substring(0, (file.name).lastIndexOf("."));
    		//文件上传后文件名称
    		var filename = objdata['filename'];
    		//上传路径
    		var  imgPath  = objdata['imgPath'];
    		var fileid = (filename).substring(0, (filename).lastIndexOf("."));
    		var synUploadUrl = objdata['synUploadUrl'];
    		//删除之前页面显示的附件信息
    		$('.imgInfoShowIpad').remove();
    		$("#queImgIpad").val(filename);
    		$('#uploadify').uploadify('settings','formData',{'queImgId':$('#queImgIpad').val()}); 
    		var showpath = "<div id='"+fileid+"' class='imgInfoShowIpad'><span>";
    		showpath += realFileName+"&nbsp;&nbsp;<font color='blue'>『"+filename+"』</font></span>";
    		showpath += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    		showpath += "<a href='#' class='delete' id='"+fileid+"' onclick=\"deleteImg('"+fileid+"','Ipad" +"')\">"+deleteFile+"</a>";
    		showpath += "&nbsp;&nbsp;&nbsp;&nbsp;";
    		showpath += "<a href='#' onclick=\"viewQueImg('" +imgPath +"','"+filename +"')\" class='delete' id='"+fileid+"' >"+downLoadPath+"</a>";
    		showpath += "&nbsp;&nbsp;&nbsp;&nbsp";
    		var fileUpdateStatus = objdata['updateStatus'];
    		if(fileUpdateStatus == 1){
    			//同步成功
    			var synImgurl = synUploadUrl+"/"+ imgPath+ filename;
    			showpath += "<a href='#' onclick=\"viewSynUploadImg('"+ synImgurl +"')\" class='delete' id='"+fileid+"' >"+downLoadPathSyn+"</a><br></div>";
    		}else if (fileUpdateStatus == 2){
    			//同步功能关闭
    		}else {
    			//同步失败
    			showpath += "<span><font color='black'>"+downLoadPathSyn+"</font><span></a><br></div>";
    			//同步失败不可保存
    			$('#btnok').attr('disabled','true');
    		}
    		$(showpath).appendTo($("#imgIpadContainer"));
        }else{
        	window.parent.Boxy.alert("<msg:message code='product.pdfFail'/>", null, {title: "<msg:message code='info.prompt'/>"});
        }
 }  
});
	
	$("#uploadify1").uploadify({
        'swf'            : '${base}/${common}/flash/uploadify/uploadify.swf',//指定uploadify.swf路径   
		'uploader'       : uploadUrlIphone,  
        'buttonClass'    : 'btn2',
        'queueID'        : 'fileQueue1',
        'multi'          : false,
        'sizeLimit'		 : 86400 ,     //这里是文件最大值 byte
        'fileTypeDesc'   : "jpg文件",               
		'fileTypeExts'   : "*.jpg;", //控制可上传文件的扩展名，启用本项时需同时声明fileDesc           		
        'buttonText'     : '<msg:message code='info.prompt.select'/>',   
        'width'          : 60,
        'height'         : 24,
        onUploadSuccess: function (file,data,response) {	
        if(response==true){
        	var objdata = JSON.parse(data);
        	//原始文件名称
        	var realFileName = (file.name).substring(0, (file.name).lastIndexOf("."));
    		//文件上传后文件名称
    		var filename = objdata['filename'];
    		//上传路径
    		var  imgPath = objdata['imgPath'];
    		var fileid = (filename).substring(0, (filename).lastIndexOf("."));
    		var synUploadUrl = objdata['synUploadUrl'];
    		//删除之前页面显示的附件信息
    		$('.imgInfoShowIphone').remove();
    		$("#queImgIphone").val(filename);
    		$('#uploadify1').uploadify('settings','formData',{'queImgId':$('#queImgIphone').val()}); 
    		var showpath = "<div id='"+fileid+"' class='imgInfoShowIphone'><span>";
    		showpath += realFileName+"&nbsp;&nbsp;<font color='blue'>『"+filename+"』</font></span>";
    		showpath += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    		showpath += "<a href='#' class='delete' id='"+fileid+"' onclick=\"deleteImg('"+fileid+"','Iphone" +"')\">"+deleteFile+"</a>";
    		showpath += "&nbsp;&nbsp;&nbsp;&nbsp;";
    		showpath += "<a href='#' onclick=\"viewQueImg('" +imgPath +"','"+filename +"')\" class='delete' id='"+fileid+"' >"+downLoadPath+"</a>";
    		showpath += "&nbsp;&nbsp;&nbsp;&nbsp";
    		var fileUpdateStatus = objdata['updateStatus'];
    		if(fileUpdateStatus == 1){
    			//同步成功
    			var synImgurl = synUploadUrl+"/"+ imgPath+ filename;
    			showpath += "<a href='#' onclick=\"viewSynUploadImg('"+ synImgurl +"')\" class='delete' id='"+fileid+"' >"+downLoadPathSyn+"</a><br></div>";
    		}else if (fileUpdateStatus == 2){
    			//同步功能关闭
    		}else{
    			//同步失败
    			showpath += "<span><font color='black'>"+downLoadPathSyn+"</font><span></a><br></div>";
    			//同步失败不可保存
    			$('#btnok').attr('disabled','true');
    		}
    		$(showpath).appendTo($("#imgIphoneContainer"));
        }else{
        	window.parent.Boxy.alert("<msg:message code='product.pdfFail'/>", null, {title: "<msg:message code='info.prompt'/>"});
        }
 }  
});
	
	
});

function deleteImg(id,type){
	var url = "deleteImgFile.json";
	$.ajax({
		url:url,
		type:'post',
		timeout:'60000',
		data:{queImgId:id,queImgType:type},
		dataType:'json',
		success:function(jsonData,textStatus){
			if (textStatus == "success"){
				//清空参数
				//清空参数
				if(type == 'Ipad'){
					$('#uploadify').uploadify('settings','formData',{'queImgId':''});
				}else if (type == 'Iphone'){
					$('#uploadify1').uploadify('settings','formData',{'queImgId':''}); 
				}
				$('#'+id).remove();
				$('#queImg'+ type).val('');
				//window.parent.Boxy.alert("<msg:message code='questionnaire.image.delete.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
			}
		}
	});
}

function viewQueImg(filePath,fileName){
	var url = "${base}/" + filePath + fileName;
    new window.parent.Boxy("<iframe src='"+url+"' width='600' height='400' frameborder='0' scrolling='auto'></iframe>", 
						{title: "<msg:message code='info.prompt'/>",closeText:"<msg:message code='info.prompt.close'/>",modal:true});
}

function viewSynUploadImg(url){
    new window.parent.Boxy("<iframe src='"+url+"' width='600' height='400' frameborder='0' scrolling='auto'></iframe>", 
						{title: "<msg:message code='info.prompt'/>",closeText:"<msg:message code='info.prompt.close'/>",modal:true});
}


</script>
</head>
<body class="skinMain">
<form:form method="post" action="save" commandName="questionnaire">
<form:hidden path="c"/>	
<input type="hidden" name="prompt" value="queName" />
<table width="100%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
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
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="skinLayout">
				<tr>
					<td  width="15%" align="right" class="skinLayout_info">
						<msg:message code="questionnaire.name" /><msg:message code="system.common.sign.colon"/>
					</td>
					<td width="85%" class="skinLayout_lable">
						<form:input path="queName" maxlength="100" onchange="this.value=$.trim(this.value)"/>
						<font color="red">&nbsp;*&nbsp;<form:errors
								path="queName" />
						</font>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info">
						<msg:message code="questionnaire.title" /><msg:message code="system.common.sign.colon"/>
					</td>
					<td width="85%" class="skinLayout_lable">
						<form:input  path="queTitle" maxlength="100" onchange="this.value=$.trim(this.value)" />
						<font color="red">&nbsp;*&nbsp;<form:errors
								path="queTitle" />
						</font>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info">
						<msg:message code="questionnaire.subtitle" /><msg:message code="system.common.sign.colon" />
					</td>
					<td width="85%" class="skinLayout_lable">
						<form:input  path="queSubTitle" maxlength="100" onchange="this.value=$.trim(this.value)" /><font color="red">&nbsp;&nbsp;<form:errors path="queSubTitle" /></font>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info">
						<msg:message code="questionnaire.startTime" /><msg:message code="system.common.sign.colon"/>
					</td>
					<td width="85%" class="skinLayout_lable">
						<form:input  path="queStartTime" onfocus="selectQueStartTime()" cssClass="Wdate" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info">
						<msg:message code="questionnaire.endTime" /><msg:message code="system.common.sign.colon"/>
					</td>
					<td width="85%" class="skinLayout_lable">
						<form:input  path="queEndTime" onfocus="selectQueEndTime()" cssClass="Wdate" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right" class="skinLayout_info">
						<msg:message code="questionnaire.desc" /><msg:message code="system.common.sign.colon"/>
					</td>
					<td width="85%" class="skinLayout_lable">
						<form:textarea path="queDescribe" id="queDescribe_kind" cssStyle="height:400px;width:600px;"/>
						<font color="red">&nbsp;&nbsp;<form:errors path="queDescribe" /></font>
					</td>
				</tr>
			    <tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message
									code="questionnaire.image.ipad" />
								<msg:message code="system.common.sign.colon" /></td>
							<td width="85%" class="skinLayout_lable"><form:hidden
									path="queImgIpad" /> 
								<div id="fileQueue"></div>
								<p>
									<input type="file" name="uploadify" id="uploadify" />
								</p>
								<div id="imgIpadContainer"></div></td>
			    </tr>
			     <tr>
							<td width="15%" align="right" class="skinLayout_info"><msg:message
									code="questionnaire.image.iphone" />
								<msg:message code="system.common.sign.colon" /></td>
							<td width="85%" class="skinLayout_lable"><form:hidden
									path="queImgIphone" /> 
								<div id="fileQueue1"></div>
								<p>
									<input type="file" name="uploadify1" id="uploadify1" />
								</p>
								<div id="imgIphoneContainer"></div></td>
					</tr>
				    <tr>
					<td width="15%" align="right">
						&nbsp;
					</td>
					<td width="85%" colspan="2" align="left">
						<input type="submit" class="btn2" name="btnok"
							value="<msg:message code="button.save"/>" id="btnok" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" class="btn2"
							onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}'"
							name="btnback" value="<msg:message code="button.back"/>"
							id="btnback" />
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