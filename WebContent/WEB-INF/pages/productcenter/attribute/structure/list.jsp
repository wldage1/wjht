<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#structureList").jqGrid({
        url: 'grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='product.type'/>", 
                   "<msg:message code='member.operate'/>"],
        colModel: [{name: 'productTypeName',index:'productTypeName',width:'20%',align:'center',hidden: false,sortable:false},
                   {name:'operate',index:'operate', width:'20%',align:'center',sortable:false}],
        mtype:"POST",
        postData:{c:"${structure.c}"},
        height:'auto',
        autowidth: true,
        shrinkToFit:true,
        viewrecords: true,
        gridComplete: function(){
    		var ids = jQuery("#structureList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var rowdata = jQuery("#structureList").jqGrid('getRowData',id);
    			var content = "";
    			<c:if test="${!empty structure_detail}">
    		    <security:authorize ifAnyGranted="${structure_detail.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${structure_detail.index}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${structure_detail.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if> 
    		    <c:if test="${!empty structure_modify}">
    		    <security:authorize ifAnyGranted="${structure_modify.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${structure_modify.index}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${structure_modify.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if> 
    		    <c:if test="${!empty structure_delete}">
    		    <security:authorize ifAnyGranted="${structure_delete.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${structure_delete.index}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${structure_delete.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if> 
    			jQuery("#structureList").jqGrid('setRowData',ids[i],{operate:content});
    		}	
    	},
    	loadComplete:function(){
    		$(".shortcut_${structure_detail.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${structure_detail.controller}?c=${structure_detail.code}&productTypeId="+rowid;
    	    });
    		$(".shortcut_${structure_modify.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${structure_modify.controller}?c=${structure_modify.code}&productTypeId="+rowid;
    	    });
    		$(".shortcut_${structure_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#structureList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${structure_delete.controller}.json?c=${structure_delete.code}&productTypeId="+rowid+"&prompt=productTypeName&productTypeName="+encodeURIComponent(encodeURIComponent(data.productTypeName));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								$("#structureList").trigger("reloadGrid");    
    							}else{
    								window.parent.Boxy.alert("<msg:message code='product.structure.isUse'/>",function(){return false;} ,{title: "<msg:message code='info.prompt'/>"});
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });     		
    	},
        caption:'<msg:message code="product.structure"/>',
        toolbar: [true,"top"]
    });
    <c:if test="${!empty structure_create}">
    <security:authorize ifAnyGranted="${structure_create.code}">	
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${structure_create.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${structure_create.name}");
	$("#t_structureList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${structure_create.index}","#t_structureList").click(function(){
    	window.location.href = "${base}${structure_create.controller}?c=${structure_create.code}";
    });    
    </security:authorize>
    </c:if>
});
</script>
<style type="text/css">
	.orandTD{
		border-left:#CCCCFF solid 1px;
		border-right:#CCCCFF solid 1px;
		width:6%;
	}
	.group{
		width:47%;
	}
	.rowtable td{
		padding: 2px 4px 2px;
	}
	.rowtable td select{
		width:100%;
	}
	.rowtable td input{
		width:97%;
	}
</style>
</head>
<body class="skinMain">
<table cellpadding="0" cellspacing="1" border="0" width="100%" class="skinMain">
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
		<td>
			<table id="structureList"><tr><td>&nbsp;</td></tr></table>
			<div id="pagered"></div>
		</td>
	</tr>
</table>
</body>
</html>