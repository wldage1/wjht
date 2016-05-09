<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.shareword/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#sharewordList").jqGrid({
        treeGridModel: 'adjacency',
        url: 'grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='shareword.title'/>", "<msg:message code='shareword.code'/>","<msg:message code='info.operate'/>"],
        colModel: [{name: 'title',index: 'title',width:'40%',align:'center',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'code',index:'code',width:'35%',align:'center',hidden: false,sortable: false,formatter:formatData},
                   {name:'act',index:'act', width:'25%',align:'center',sortable:false,sortable: false}],
        mtype:"POST",
        postData:{c:"${shareword.c}"},
        rowNum:10,
        page:"${shareword.page}",
        rowList: [10, 20, 30],
        pager: false,
        height:'auto',
        autowidth: true,
        viewrecords: true,
        multiselect: false,
    	gridComplete: function(){
    		var ids = jQuery("#sharewordList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			var space = "&nbsp;&nbsp;";
				
    			<c:if test="${!empty shareword_edit}">
					<security:authorize ifAnyGranted="${shareword_edit.code}">	
						content += "<a href='javascript:void(0);' title='${shareword_edit.name}' id='" + id + "' class='shortcut_${shareword_edit.index}' title='${shareword_edit.name}'>";
						content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${shareword_edit.name}";
						content += "</a>";
						content += space;
					</security:authorize>
    		    </c:if>
    			     	
    			jQuery("#sharewordList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(){
    	        		
    	    $(".shortcut_${shareword_edit.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${shareword_edit.controller}?c=${shareword_edit.code}&id="+rowid;
    	    });    
    	   
    	},
        caption:'<msg:message code="shareword.list"/>',
        toolbar: [true,"top"]
    });
 
});

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
						<table id="sharewordList"><tr><td>&nbsp;</td></tr></table>	
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