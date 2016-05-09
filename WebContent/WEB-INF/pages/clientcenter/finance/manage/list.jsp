<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">


$(document).ready(function(){
    jQuery("#wealthCenterList").jqGrid({
        treeGridModel: 'adjacency',
        ExpandColumn: 'name',
        ExpandColClick: true,
        url: 'grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='wealthManagement.name'/>","<msg:message code='wealthManagement.address'/>",
                   "<msg:message code='wealthManagement.phone'/>", "<msg:message code='info.operate'/>"],
        colModel: [{name: 'name',index:'name',width:'25%',align:'center',sortable:false,formatter:formatData},
                   {name: 'address',index:'address',width:'25%',align:'center',sortable:false,formatter:formatData},
                   {name: 'phone',index:'phone',width:'25%',align:'center',sortable:false},
                   {name: 'act',index:'act', width:'15%',align:'center',sortable:false,title:false}],
        rowNum:10,     
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        mtype:"POST",
        postData:{name:"${wealthManagement.name}",phone:"${wealthManagement.phone}",c:"${wealthManagement.c}"}, 
        autowidth: true,
        viewrecords: true,
        multiselect: true,
    	gridComplete: function(){
    		var ids = jQuery("#wealthCenterList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			var rowdata = jQuery("#wealthCenterList").jqGrid('getRowData',id);
    			
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${detail.index}'";
    			content += "title='${detail.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${detail.name}";
    			content += "</a>";
    			
    			<c:if test="${!empty modify}">
    		    <security:authorize ifAnyGranted="${modify.code}">	
    				content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${modify.index}'";
        			content += "title='${modify.name}' >";
        			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${modify.name}";
        			content += "</a>";
    			 </security:authorize>
    			</c:if> 
    			<c:if test="${!empty delete}">
    		    <security:authorize ifAnyGranted="${delete.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${delete.index}'";
    			content += "title='${delete.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${delete.name}";
    			content += "</a>";
    		    </security:authorize>
    			</c:if>
    			jQuery("#wealthCenterList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}
    	},
    	loadComplete:function(){
    	    $(".shortcut_${detail.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${detail.controller}?c=${detail.code}&id="+rowid;
    	    });
    	    $(".shortcut_${modify.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${modify.controller}?c=${modify.code}&id="+rowid;
    	    });
    	    $(".shortcut_${delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#wealthCenterList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${delete.controller}.json?c=${delete.code}&ids="+rowid+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(data.name));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								jQuery("#wealthCenterList").jqGrid("delRowData",rowid);
    								$("#wealthCenterList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });
    	},
        caption:'<msg:message code="wealthManagement.list"/>',
        toolbar: [true,"top"]
    });
    
    <c:if test="${!empty create}">
    <security:authorize ifAnyGranted="${create.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${create.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${create.name}");
	$("#t_wealthCenterList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${create.index}","#t_wealthCenterList").click(function(){
    	window.location.href = "${base}${create.controller}?c=${create.code}";
    });    
    </security:authorize>
    </c:if>
    
    <c:if test="${!empty delete}">
    <security:authorize ifAnyGranted="${delete.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${delete.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${delete.name}");
	$("#t_wealthCenterList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${delete.index}","#t_wealthCenterList").click(function(){
    	var selrow = jQuery("#wealthCenterList").jqGrid("getGridParam","selarrrow");
    	var names = "";
    	for(var i=0;i < selrow.length;i++){
    		var id = selrow[i];
    		var data = jQuery("#wealthCenterList").jqGrid("getRowData",id);
    		names += data.name;
    		if(i!= selrow.length-1)
    			names += ",";
    	}
		if (selrow != null && selrow != ""){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "${base}${delete.controller}.json?c=${delete.code}&ids="+selrow+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(names));
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								jQuery("#wealthCenterList").jqGrid("delRowData",selrow);
								$("#wealthCenterList").trigger("reloadGrid");    
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
</head >
<body class="skinMain">
<form:form method="post" action="list" commandName="wealthManagement">
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
		<td width="100%">
			<table cellpadding="2" cellspacing="2" border="0" width="100%">
			<tr>
				<td align="center">
					<input type="hidden" name="c" value="${wealthManagement.c}" />
						<table cellpadding="0" cellspacing="0" border="1" width="100%" class="skinLayout">
							<tr>
								<td align="right" width="10%" class="search_info">
								<msg:message code="wealthManagement.name"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="18%" class="search_lable">
								<form:input path="name" onchange="this.value=$.trim(this.value)"/>
								</td>
								<td align="right" width="10%" class="search_info">
								<msg:message code="wealthManagement.phone"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="18%" class="search_lable">
								<form:input path="phone" onchange="this.value=$.trim(this.value)"/>
								</td>
								<td align="left" width="15%" >
								</td>
								<td align="center" width="10%" class="button_area" rowspan="2">
								<input type="submit" class="btn2" id="select" value="<msg:message code="button.search"/>">
								</td>
							</tr>			
						</table>
				</td>
			</tr>
			<tr>
			<td>
		<table id="wealthCenterList"><tr><td>&nbsp;</td></tr></table>
			<div id="pagered"></div>
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