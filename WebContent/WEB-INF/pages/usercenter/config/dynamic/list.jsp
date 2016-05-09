<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#dictionaryList").jqGrid({
    	treeGrid: true,
        treeGridModel: 'adjacency',
        ExpandColumn: 'name',
        ExpandColClick: true,
        url: 'grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='organization.isChildNode'/>",
                   "<msg:message code='dictionary.name'/>",
                   "<msg:message code='dictionary.value'/>",
                   "<msg:message code='info.operate'/>"],
        colModel: [{name: 'childNode',index: 'childNode',width:'1%',align:'left',hidden: true,sortable: false},
                   {name: 'name',index:'name',width:'25%',align:'center',sortable:false},
                   {name: 'value',index:'value',width:'25%',align:'center',sortable:false},
                   {name: 'act',index:'act', width:'25%',align:'center',sortable:false,title:false}],
        rowNum:10,     
        rowList: [10, 20, 30],
        pager: false,
        height:'auto',
        autowidth: true,
        viewrecords: true,
    	gridComplete: function(){
    		var ids = jQuery("#dictionaryList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var rowdata = jQuery("#orgList").jqGrid('getRowData',id);
    			var content = "";
    			 if (id.indexOf("a")<=0){
    				 <c:if test="${!empty dynamic_modify}">
    	    		    <security:authorize ifAnyGranted="${dynamic_modify.code}">	
    	    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${dynamic_modify.index}'";
    	    			content += "title='${dynamic_modify.name}' >";
    	    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${dynamic_modify.name}";
    	    			content += "</a>";
    	    			 </security:authorize>
    	    			</c:if> 
    	    			<c:if test="${!empty dynamic_delete}">
    	    		    <security:authorize ifAnyGranted="${dynamic_delete.code}">	
    	    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${dynamic_delete.index}'";
    	    			content += "title='${dynamic_delete.name}' >";
    	    			content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${dynamic_delete.name}";
    	    			content += "</a>";
    	    		    </security:authorize>
    	    		    </c:if>
    			 }
    			 if($.trim(content)==""){
    				 content = "&nbsp;";
    			 }
    		    jQuery("#dictionaryList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(){
    	    $(".shortcut_${dynamic_modify.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${dynamic_modify.controller}?c=${dynamic_modify.code}&id="+rowid;
    	    });
    	    $(".shortcut_${dynamic_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#dictionaryList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${dynamic_delete.controller}.json?c=${dynamic_delete.code}&ids="+rowid+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(data.name));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								jQuery("#dictionaryList").jqGrid("delRowData",rowid);
    								$("#dictionaryList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });  		
    	},
        caption:'<msg:message code="dictionary.list"/>',
        toolbar: [true,"top"]
    });
    
    <c:if test="${!empty dynamic_create}">
    <security:authorize ifAnyGranted="${dynamic_create.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${dynamic_create.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${dynamic_create.name}");
	$("#t_dictionaryList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${dynamic_create.index}","#t_dictionaryList").click(function(){
    	window.location.href = "${base}${dynamic_create.controller}?c=${dynamic_create.code}";
    });    
    </security:authorize>
    </c:if>
    
});

</script>
</head >
<body class="skinMain">
<form:form method="post" action="list" commandName="dictionary">
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
				<td align="center">
					<input type="hidden" name="c" value="${dictionary.c}" />
				</td>
			</tr>
			<tr>
				<td>
					<table id="dictionaryList"><tr><td>&nbsp;</td></tr></table>
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
</form:form>	
</body>
</html>