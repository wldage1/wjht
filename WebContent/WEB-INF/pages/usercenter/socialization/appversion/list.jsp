<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.appversion/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#appversionList").jqGrid({
        treeGridModel: 'adjacency',
        url: 'grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='appversion.deviceType'/>", "<msg:message code='appversion.version'/>","<msg:message code='appversion.downloadURL'/>","<msg:message code='appversion.noticeRate'/>","<msg:message code='info.operate'/>"],
        colModel: [{name: 'deviceType',index: 'deviceType',width:'15%',align:'center',hidden: false,sortable: false,formatter:deviceTypeFmatter}, 
                   {name: 'version',index:'version',width:'15%',align:'center',hidden: false,sortable: false},
				   {name: 'downloadURL',index:'downloadURL',width:'50%',align:'center',hidden: false,sortable: false},
				   {name: 'noticeRate',index:'noticeRate',width:'10%',align:'center',hidden: false,sortable: false,formatter:noticeRateFmatter},
                   {name:'act',index:'act', width:'10%',align:'center',sortable:false,sortable: false}],
        mtype:"POST",
        postData:{c:"${appversion.c}"},
        rowNum:10,
        page:"${appversion.page}",
        rowList: [10, 20, 30],
        pager: false,
        height:'auto',
        autowidth: true,
        viewrecords: true,
        multiselect: false,
    	gridComplete: function(){
    		var ids = jQuery("#appversionList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			var space = "&nbsp;&nbsp;";
				
    			<c:if test="${!empty appversion_edit}">
					<security:authorize ifAnyGranted="${appversion_edit.code}">	
						content += "<a href='javascript:void(0);' title='${appversion_edit.name}' id='" + id + "' class='shortcut_${appversion_edit.index}' title='${appversion_edit.name}'>";
						content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${appversion_edit.name}";
						content += "</a>";
						content += space;
					</security:authorize>
    		    </c:if>
    			     	
    			jQuery("#appversionList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(){
    	        		
    	    $(".shortcut_${appversion_edit.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${appversion_edit.controller}?c=${appversion_edit.code}&id="+rowid;
    	    });    
    	   
    	},
        caption:'<msg:message code="appversion.list"/>',
        toolbar: [true,"top"]
    });
 
});

function deviceTypeFmatter(cellvalue, options, rowObject){   
	<c:forEach items="${devicetype}" var="item">
	if(cellvalue && cellvalue == '${item.key}'){
		return '${item.value}';
	}
	</c:forEach>
}; 

function noticeRateFmatter(cellvalue, options, rowObject){   
	<c:forEach items="${noticerate}" var="item">
	if(cellvalue && cellvalue == '${item.key}'){
		return '${item.value}';
	}
	</c:forEach>
}; 
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
			<table cellpadding="2" cellspacing="2" border="0" width="100%">
				<tr>
					<td>
						<table id="appversionList"><tr><td>&nbsp;</td></tr></table>	
						<div id="pagered"></div>
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