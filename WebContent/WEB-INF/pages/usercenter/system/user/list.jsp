<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ taglib prefix="msg" uri="/WEB-INF/tlds/springframework-message.tld" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#userList").jqGrid({
        url: 'grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='user.name'/>", 
                   "<msg:message code='user.username'/>",
                   "<msg:message code='user.orgName'/>",
                   "<msg:message code='user.roleName'/>",
                   "<msg:message code='user.operate'/>"
                   ],
        colModel: [{name: 'name',index: 'name',width:'20%',align:'center',hidden: false,sortable:false,formatter:formatData},
                   {name: 'username',index:'username',width:'20%',align:'center',hidden: false,sortable:false,formatter:formatData},
                   {name: 'orgName',index:'orgName',width:'20%',align:'center',hidden: false,sortable:false,formatter:formatData},
                   {name: 'roleName',index:'roleName',width:'20%',align:'center',hidden: false,sortable:false,formatter:formatData},
                   {name:'act',index:'act', width:'30%',align:'center',sortable:false,sortable:false}],
        mtype:"POST",
        postData:{c:"${user.c}",name:"${user.name}"},
        rowNum:10,     
        page:"${user.page}",
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        viewrecords: true,
    	gridComplete: function(){
    		var ids = jQuery("#userList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			<c:if test="${!empty user_bind}">
    		    <security:authorize ifAnyGranted="${user_bind.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${user_bind.index}' ";
    			content += " title='${user_bind.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/auth.png' weight='18' height='18' border='0' align='absmiddle'/>${user_bind.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>  
    			<c:if test="${!empty user_modify_cmpwd}">
    		    <security:authorize ifAnyGranted="${user_modify_cmpwd.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${user_modify_cmpwd.index}' ";
    			content += " title='${user_modify_cmpwd.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/password.png' weight='18' height='18' border='0' align='absmiddle'/>${user_modify_cmpwd.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    			<c:if test="${!empty user_modify}">
    		    <security:authorize ifAnyGranted="${user_modify.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${user_modify.index}'";
    			content += " title='${user_modify.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${user_modify.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>  
    		    <c:if test="${!empty user_delete}">
    		    <security:authorize ifAnyGranted="${user_delete.code}">
    		    content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${user_delete.index}'";
    		    content += " title='${user_delete.name}' >";
    		    content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${user_delete.name}";
    		    content += "</a>";	
    		    </security:authorize>
    		    </c:if>      	
    			jQuery("#userList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(){
    	    $(".shortcut_${user_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#userList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${user_delete.controller}.json?c=${user_delete.code}&id="+rowid+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(data.name));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								//jQuery("#userList").jqGrid("delRowData",rowid);
    								$("#userList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });     		
    	    $(".shortcut_${user_modify.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${user_modify.controller}?c=${user_modify.code}&id="+ rowid;
    	    });   
    	    $(".shortcut_${user_modify_cmpwd.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${user_modify_cmpwd.controller}?c=${user_modify_cmpwd.code}&id="+rowid;
    	    });
    	    $(".shortcut_${user_dataauth.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${user_dataauth.controller}?c=${user_dataauth.code}&id="+rowid;
    	    }); 
    	    $(".shortcut_${user_bind.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${user_bind.controller}?c=${user_bind.code}&id="+rowid;
    	    });     	    
    	},
        caption:'<msg:message code="user.list"/>',
        toolbar: [true,"top"]
    });
    
    <c:if test="${!empty user_create}">
    <security:authorize ifAnyGranted="${user_create.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${user_create.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${user_create.name}");
	$("#t_userList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${user_create.index}","#t_userList").click(function(){
    	window.location.href = "${base}${user_create.controller}?c=${user_create.code}";
    });    
    </security:authorize>
    </c:if>
    
    $("#sbtn").click(function(){
    	var name = $("#name").val();
    	var url = "grid.json?c=1&name="+name;
    	jQuery("#bigset").jqGrid('setGridParam',{url:url,page:1}).trigger("reloadGrid");
    });
    
});
function submitForm(){
	$('#name').val($.trim($('#name').val()));
	document.f.submit();
}
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
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td align="center">
					<form:form method="post" action="list" commandName="user" name="f">
						<input type="hidden" name="c" value="${user.c}"/>
						<table cellpadding="0" cellspacing="1" border="0" width="100%" class="search">
							<tr>
								<td align="right" width="10%" class="search_info"><msg:message code='user.name'/><msg:message code="system.common.sign.colon"/></td>
								<td align="left" width="30%"  class="search_lable">
									<form:input path="name" htmlEscape="true" />
								</td>
								<%-- <td align="right" width="10%" class="search_info"><msg:message code='user.username'/><msg:message code="system.common.sign.colon"/></td>
								<td align="left" width="30%"  class="search_lable">
									<form:input path="account" htmlEscape="true" />
								</td> --%>
								<td rowspan="2" width="20%" class="button_area">
									<input type=button class="btn2" id="select" value="<msg:message code='button.search'/>" onclick="submitForm();">
								</td>				
							</tr>		
						</table>
					</form:form>
				</td>
			</tr>
			<tr>
				<td>
					<table id="userList"><tr><td>&nbsp;</td></tr></table>	
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