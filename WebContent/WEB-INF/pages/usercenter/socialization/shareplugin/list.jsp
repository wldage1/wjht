<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.shareplugin/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#sharepluginList").jqGrid({
        treeGridModel: 'adjacency',
        url: 'grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='shareplugin.name'/>", "<msg:message code='shareplugin.key'/>","<msg:message code='info.operate'/>"],
        colModel: [{name: 'name',index: 'name',width:'40%',align:'center',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'key',index:'key',width:'35%',align:'center',hidden: false,sortable: false,formatter:formatData},
                   {name:'act',index:'act', width:'25%',align:'center',sortable:false,sortable: false}],
        mtype:"POST",
        postData:{c:"${shareplugin.c}"},
        rowNum:10,
        page:"${shareplugin.page}",
        rowList: [10, 20, 30],
        pager: false,
        height:'auto',
        autowidth: true,
        viewrecords: true,
        multiselect: false,
    	gridComplete: function(){
    		var ids = jQuery("#sharepluginList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			var space = "&nbsp;&nbsp;";
				
				
				<c:if test="${!empty shareplugin_modify}">
					<security:authorize ifAnyGranted="${shareplugin_modify.code}">	
						content += "<a href='javascript:void(0);' title='${shareplugin_modify.name}' id='" + id + "' class='shortcut_${shareplugin_modify.index}' title='${shareplugin_modify.name}'>";
						content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${shareplugin_modify.name}";
						content += "</a>";
						content += space;
					</security:authorize>
    		    </c:if>
				
				<c:if test="${!empty shareplugin_delete}">
					<security:authorize ifAnyGranted="${shareplugin_delete.code}">	
						content += "<a href='javascript:void(0);' title='${shareplugin_delete.name}' id='" + id + "' class='shortcut_${shareplugin_delete.index}' title='${shareplugin_delete.name}'>";
						content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${shareplugin_delete.name}";
						content += "</a>";
						content += space;
					</security:authorize>
    		    </c:if>
    			     	
    			jQuery("#sharepluginList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(){

			$(".shortcut_${shareplugin_modify.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${shareplugin_modify.controller}?c=${shareplugin_modify.code}&id="+rowid;
    	    }); 

			$(".shortcut_${shareplugin_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#sharepluginList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${shareplugin_delete.controller}.json?c=${shareplugin_delete.code}&id="+rowid+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(data.name));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								//jQuery("#sharepluginList").jqGrid("delRowData",rowid);
    								$("#sharepluginList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    }); 			
    	   
    	},
        caption:'<msg:message code="shareplugin.list"/>',
        toolbar: [true,"top"]
    });
	
	<c:if test="${!empty shareplugin_create}">
		<security:authorize ifAnyGranted="${shareplugin_create.code}">	 
			var $ea = $("<a></a>").attr("href","javascript:void(0)")
					  .attr("id","${shareplugin_create.index}")
					  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
					  .attr("width","18").attr("height","18").attr("border","0")
					  .attr("border","0").attr("align","absmiddle"))
					  .append("${shareplugin_create.name}");
			$("#t_sharepluginList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));   
			$("#${shareplugin_create.index}","#t_sharepluginList").click(function(){
				window.location.href = "${base}${shareplugin_create.controller}?c=${shareplugin_create.code}";
			});    
		</security:authorize>
    </c:if>
	
	<c:if test="${!empty shareplugin_config}">
		<security:authorize ifAnyGranted="${shareplugin_config.code}">	 
			var $ea = $("<a></a>").attr("href","javascript:void(0)")
					  .attr("id","${shareplugin_config.index}")
					  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
					  .attr("width","18").attr("height","18").attr("border","0")
					  .attr("border","0").attr("align","absmiddle"))
					  .append("${shareplugin_config.name}");
			$("#t_sharepluginList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));   
			$("#${shareplugin_config.index}","#t_sharepluginList").click(function(){
				window.location.href = "${base}${shareplugin_config.controller}?c=${shareplugin_config.code}";
			});    
		</security:authorize>
    </c:if>
 
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
						<table id="sharepluginList"><tr><td>&nbsp;</td></tr></table>	
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