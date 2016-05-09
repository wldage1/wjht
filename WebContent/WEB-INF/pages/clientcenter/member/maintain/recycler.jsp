<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.sw.core.common.Constant" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#memberList").jqGrid({
        url: 'grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='member.userName'/>",
				   "<msg:message code='member.crmID'/>", 
                   "<msg:message code='member.pf'/>",
                   "<msg:message code='member.branchOrg'/>",
                   "<msg:message code='member.registPhone'/>",
                   "<msg:message code='member.crmlevel'/>",
                   "<msg:message code='member.source'/>",
                   "<msg:message code='member.registrationTimee'/>",
                   "<msg:message code='member.operate'/>"],
        colModel: [{name: 'userName',index:'userName',width:'13%',align:'center',hidden: false,sortable:false},
				   {name: 'crmId',index:'crmId',width:'8%',align:'center',hidden: false,sortable:false},
                   {name: 'financialPlanner',index:'financialPlanner',width:'6%',align:'center',hidden: false,sortable:false},
                   {name: 'mobilePhone',index:'mobilePhone',width:'16%',align:'center',hidden: false,sortable:false},
                   {name: 'registPhone',index:'registPhone',width:'7%',align:'center',hidden: false,sortable:false},
                   {name: 'memberLevel',index:'memberLevel',width:'8%',align:'center',hidden: false,sortable:false},
                   {name: 'source',index:'source',width:'5%',align:'center',hidden: false,sortable:false},
                   {name: 'crateTime',index:'crateTime',width:'11%',align:'center',hidden: false,sortable:false},
                   {name:'operate',index:'operate', width:'26%',align:'center',sortable:false}],
        mtype:"POST",
        postData:{c:"${member.c}",financialPlanner:"${member.financialPlanner}",registPhone:"${member.registPhone}",memberLevel:"${member.memberLevel}",delStatus:"${member.delStatus}"},
        rowNum:10,     
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        viewrecords: true,
        multiselect: true,
        gridComplete: function(){
    		var ids = jQuery("#memberList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var rowdata = jQuery("#memberList").jqGrid('getRowData',id);
    			
    			var content = "";
    			<c:if test="${!empty maintain_restore}">
    		    <security:authorize ifAnyGranted="${maintain_restore.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_restore.index}' title='${maintain_restore.name}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/restore.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_restore.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if> 
    		    <c:if test="${!empty maintain_delete}">
    		    <security:authorize ifAnyGranted="${maintain_delete.code}">
   		    	content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_delete.index}' title='${maintain_delete.name}'>";
   		    	content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_delete.name}";
   		    	content += "</a>";
    		    </security:authorize>
    		    </c:if>   
    			jQuery("#memberList").jqGrid('setRowData',ids[i],{operate:content});
    		}	
    	},
    	loadComplete:function(){
    	    $(".shortcut_${maintain_restore.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#memberList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.restore.confirm'/>",  function(){
    				var url = "${base}${maintain_restore.controller}.json?c=${maintain_restore.code}&id="+rowid+"&prompt=memberName&memberName="+encodeURIComponent(encodeURIComponent(data.memberName));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								$("#memberList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });
    	    $(".shortcut_${maintain_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#memberList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${maintain_delete.controller}.json?c=${maintain_delete.code}&id="+rowid+"&prompt=memberName&memberName="+encodeURIComponent(encodeURIComponent(data.memberName));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								$("#memberList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    }); 
    	},
        caption:'<msg:message code="member.recycler"/>',
        toolbar: [true,"top"]
    });
    <c:if test="${!empty maintain_restore}">
    <security:authorize ifAnyGranted="${maintain_restore.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${maintain_restore.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/restore.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${maintain_restore.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${maintain_restore.index}","#t_memberList").click(function(){
    	var ids = jQuery("#memberList").jqGrid("getGridParam","selarrrow");
    	var names = "";
    	for(var i=0;i < ids.length;i++){
    		var id = ids[i];
    		var data = jQuery("#memberList").jqGrid("getRowData",id);
    		names += data.memberName;
    		if(i!= ids.length-1)
    			names += ",";
    	}
		if (ids != null && ids!=""){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.restore.confirm'/>",  function(){
				var url = "${base}${maintain_restore.controller}.json?c=${maintain_restore.code}&ids="+ids+"&memberName="+encodeURIComponent(encodeURIComponent(names))+"&prompt=memberName";
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								$("#memberList").trigger("reloadGrid");    
							}
						}
					}
				});
			}, {title: "<msg:message code='info.prompt'/>"});
		}
		else{
    		window.parent.Boxy.alert("<msg:message code='info.prompt.restore'/>", null, {title: "<msg:message code='info.prompt'/>"});
   		}    	
    });    
    </security:authorize>
    </c:if>
    <c:if test="${!empty maintain_delete}">
    <security:authorize ifAnyGranted="${maintain_delete.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${maintain_delete.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${maintain_delete.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${maintain_delete.index}","#t_memberList").click(function(){
    	var ids = jQuery("#memberList").jqGrid("getGridParam","selarrrow");
    	var names = "";
    	for(var i=0;i < ids.length;i++){
    		var id = ids[i];
    		var data = jQuery("#memberList").jqGrid("getRowData",id);
    		names += data.memberName;
    		if(i!= ids.length-1)
    			names += ",";
    	}
		if (ids != null && ids!=""){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "${base}${maintain_delete.controller}.json?c=${maintain_delete.code}&ids="+ids+"&memberName="+encodeURIComponent(encodeURIComponent(names))+"&prompt=memberName";
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								$("#memberList").trigger("reloadGrid");    
							}
						}
					}
				});
			}, {title: "<msg:message code='info.prompt'/>"});
		}
		else{
    		window.parent.Boxy.alert("<msg:message code='info.prompt.delete'/>", null, {title: "<msg:message code='info.prompt'/>"});
   		}    	
    });    
    </security:authorize>
    </c:if>
});


</script>
</head>
<body class="skinMain">
<table cellpadding="2" cellspacing="2" border="0" width="100%" class="skinMain">
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
		<td align="center">
			<form:form method="post" action="recycler" commandName="member" name="searchForm" id="searchForm">
				<input type="hidden" name="c" value="${member.c}"/>
				<table cellpadding="0" cellspacing="1" border="0" width="100%" class="search">
					<tr>
						<td align="right" width="10%" class="search_info"><msg:message code='member.pf'/><msg:message code='system.common.sign.colon'/></td>
						<td align="left" width="18%" class="search_lable">
							<form:input path="financialPlanner" />
						</td>
						<td align="right" width="10%" class="search_info"><msg:message code='member.registPhone'/><msg:message code="system.common.sign.colon"/></td>
						<td align="left" width="18%" class="search_lable">
							<form:input path="registPhone"/>
						</td>
						<td align="right" width="10%" class="search_info"><msg:message code='member.crmlevel'/><msg:message code="system.common.sign.colon"/></td>
						<td align="left" width="18%" class="search_lable">
							<form:select path="memberLevel" >
								<form:option value="">--<msg:message code='please.select'/>--</form:option>
								<form:options items="${levelList}" itemValue="name" itemLabel="name"/>
							</form:select>
						</td>
						<td width="16%" class="button_area" align="center">
							<input type=submit class="btn2" id="select" value="<msg:message code='member.button.search'/>">
							<input type=button class="btn2" id="select" onclick="this.form.financialPlanner.value = '';this.form.registPhone.value = '';this.form.memberLevel.value = '';" value="<msg:message code='button.reset'/>">
						</td>
					</tr>			
				</table>
			</form:form>
		</td>
	</tr>
	<tr>
		<td>
			<table id="memberList"><tr><td>&nbsp;</td></tr></table>
			<div id="pagered"></div>
		</td>
	</tr>
</table>
</body>
</html>