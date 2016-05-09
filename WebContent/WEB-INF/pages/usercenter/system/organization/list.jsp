<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#orgList").jqGrid({
        treeGrid: true,
        treeGridModel: 'adjacency',
        ExpandColumn: 'name',
        ExpandColClick: true,
        url: 'tree.json',
        datatype: 'json',
        colNames: ["<msg:message code='organization.isChildNode'/>","<msg:message code='organization.name'/>", "<msg:message code='organization.code'/>","<msg:message code='info.operate'/>"],
        colModel: [{name: 'childNode',index: 'childNode',width:'1%',align:'left',hidden: true,sortable: false}, 
                   {name: 'name',index: 'name',width:'45%',align:'left',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'code',index:'code',width:'40%',align:'center',hidden: false,sortable: false},
                   {name:'act',index:'act', width:'15%',align:'center',sortable:false,sortable: false}],
        pager: false,
        height:'auto',
        autowidth: true,
        viewrecords: true,
    	gridComplete: function(){
    		var ids = jQuery("#orgList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var rowdata = jQuery("#orgList").jqGrid('getRowData',id);
    			var content = "";
    			<c:if test="${!empty org_modify}">
    		    <security:authorize ifAnyGranted="${org_modify.code}">	
    		    if (rowdata.level == '1'){
    		    	content += "<span style='color:#CFC09F;'><img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${org_modify.name}</span>"; 
    		    }
    		    else{
	    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${org_modify.index}'";
	    			content += " title='${org_modify.name}' >";
	    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${org_modify.name}";
	    			content += "</a>";
    		    }
    		    </security:authorize>
    		    </c:if>  
    		    <c:if test="${!empty org_delete}">
    		    <security:authorize ifAnyGranted="${org_delete.code}">
    		    var dis = "";
    		    if (rowdata.level == '1'){
    		    	content += "<span style='color:#CFC09F;'><img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${org_delete.name}</span>"; 
    		    }
    		    else{
    		    	content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${org_delete.index}'";
    		    	content += " title='${org_delete.name}' >";
    		    	content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${org_delete.name}";
    		    	content += "</a>";
    		    }
    		    </security:authorize>
    		    </c:if>      	
    			jQuery("#orgList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(){
    	    $(".shortcut_${org_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#orgList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${org_delete.controller}.json?c=${org_delete.code}&id="+rowid+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(data.name));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								//jQuery("#orgList").jqGrid("delRowData",rowid);
    								$("#orgList").trigger("reloadGrid");    
    							}else{
    								window.parent.Boxy.alert("<msg:message code='organization.hasuser'/>",null,{title: "<msg:message code='info.prompt'/>"});
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });     		
    	    $(".shortcut_${org_modify.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${org_modify.controller}?c=${org_modify.code}&id="+rowid;
    	    });     		
    	},
        caption:'<msg:message code="organization.list"/>',
        toolbar: [true,"top"]
    });
    <c:if test="${!empty org_create}">
    <security:authorize ifAnyGranted="${org_create.code}">	
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${org_create.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${org_create.name}");
	$("#t_orgList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${org_create.index}","#t_orgList").click(function(){
    	window.location.href = "${base}${org_create.controller}?c=${org_create.code}";
    });    
    </security:authorize>
    </c:if>
     
    
    $("#upload").click(function(){
    	//参数说明
    	//tab标签id（必须）
    	var iframeId = "";
    	//自己定义控制器名称（必须）
    	var controllerName = "/usercenter/system/organization/uploadfile";
    	//文件类型描述
    	var fileTypeDesc = "";
    	//文件类型
    	var fileTypeExts = "";
    	//允许多文件生成时，设置选择文件的个数
    	var queueSizeLimit = 2;
    	//多文件上传时，同时上传文件数目限制 
    	var uploadLimit = 2;
    	//上传页面元素回写id，多个id值，用逗号分割
    	var inputIds = "filepath";
    	//上传页面图片展示回写id，多个id值，用逗号分割
    	var imgIds = "";
    	$(".contentIframe",window.parent.document).each(function(){
    		var dis = $(this).css("display");
    		if (dis == 'inline'){
    			iframeId = $(this).attr("id");
    		}
    	});
    	var url = "upload?iframeId=" +iframeId 
    			+ "&controllerName=" + controllerName
    			+ "&fileTypeDesc=" + fileTypeDesc
    			+ "&fileTypeExts=" + fileTypeExts
    			+ "&queueSizeLimit=" + queueSizeLimit
    			+ "&uploadLimit=" + uploadLimit
    			+ "&inputIds=" + inputIds
    			+ "&imgIds=" + imgIds;
    			
    	new window.parent.Boxy("<iframe src='"+url+"' width='400' height='300' frameborder='0' scrolling='no'></iframe>", {title: "<msg:message code='info.prompt'/>",closeText:"<msg:message code='info.prompt.close'/>",modal:true});
    });
});

</script>
</head>

<body class="skinMain">
<!-- 
<input type="button" id="upload" value="ttttttt">
<input type="text" id="filepath">
 -->
<table width="100%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
		<tr>
			<td width="100%">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinNav">
					<tr>
						<th width="2%"><img src="${base}/${common}/${style}/images/nav/bg_07.gif" width="20" height="20" />
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
			<table cellpadding="2" cellspacing="2" border="0" width="100%">
				<tr>
					<td>
						<table id="orgList"><tr><td>&nbsp;</td></tr></table>
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
</body>
</html>