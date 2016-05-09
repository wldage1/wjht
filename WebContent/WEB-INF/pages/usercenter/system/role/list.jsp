<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#roleList").jqGrid({
        treeGridModel: 'adjacency',
        ExpandColumn: 'name',
        ExpandColClick: true,
        url: 'grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='role.orgName'/>","<msg:message code='role.name'/>", "<msg:message code='role.description'/>","<msg:message code='info.operate'/>"],
        colModel: [{name: 'orgName',index: 'orgName',width:'30%',align:'center',hidden: false,sortable: false,formatter:formatData},
                   {name: 'name',index: 'name',width:'30%',align:'center',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'description',index:'description',width:'35%',align:'center',hidden: false,sortable: false,formatter:formatData},
                   {name:'act',index:'act', width:'25%',align:'center',sortable:false,sortable: false}],
        mtype:"POST",
        postData:{c:"${role.c}"},
        rowNum:10,
        page:"${role.page}",
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        viewrecords: true,
    	gridComplete: function(){
    		var ids = jQuery("#roleList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			var space = "&nbsp;&nbsp;";
    			<c:if test="${!empty role_modify}">
    		    <security:authorize ifAnyGranted="${role_modify.code}">	
    			content += "<a href='javascript:void(0);' title='${role_modify.name}' id='" + id + "' class='shortcut_${role_modify.index}' title='${role_modify.name}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${role_modify.name}";
    			content += "</a>";
    			content += space;
    		    </security:authorize>
    		    </c:if>
    			<c:if test="${!empty role_auth}">
    		    <security:authorize ifAnyGranted="${role_auth.code}">	
    			content += "<a href='javascript:void(0);' title='${role_auth.name}' id='" + id + "' class='shortcut_${role_auth.index}' title='${role_auth.name}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/auth.png' weight='18' height='18' border='0' align='absmiddle'/>${role_auth.name}";
    			content += "</a>";
    			content += space;
    		    </security:authorize>
    		    </c:if>
    		    <c:if test="${!empty role_delete}">
    		    <security:authorize ifAnyGranted="${role_delete.code}">
   		    	content += "<a href='javascript:void(0);' title='${role_delete.name}' id='" + id + "' class='shortcut_${role_delete.index}' title='${role_delete.name}'>";
   		    	content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${role_delete.name}";
   		    	content += "</a>";
       			content += space;
    		    </security:authorize>
    		    </c:if>      	
    			jQuery("#roleList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(){
    	    $(".shortcut_${role_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#roleList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${role_delete.controller}.json?c=${role_delete.code}&id="+rowid+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(data.name));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								//jQuery("#roleList").jqGrid("delRowData",rowid);
    								$("#roleList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });     		
    	    $(".shortcut_${role_modify.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${role_modify.controller}?c=${role_modify.code}&id="+rowid;
    	    });    
    	    $(".shortcut_${role_auth.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${role_auth.controller}?c=${role_auth.code}&id="+rowid;
    	    }); 
    	},
        caption:'<msg:message code="role.list"/>',
        toolbar: [true,"top"]
    });
    
    <c:if test="${!empty role_create}">
    <security:authorize ifAnyGranted="${role_create.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
   			  .attr("id","${role_create.index}")
   			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${role_create.name}");
    $("#t_roleList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));   
    $("#${role_create.index}","#t_roleList").click(function(){
    	window.location.href = "${base}${role_create.controller}?c=${role_create.code}";
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
						<table id="roleList"><tr><td>&nbsp;</td></tr></table>	
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