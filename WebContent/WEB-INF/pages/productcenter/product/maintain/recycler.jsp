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
    jQuery("#prdList").jqGrid({
        url: 'grid.json?delFlag=1',
        datatype: 'json',
        colNames: ["<msg:message code="product.prdname"/>", 
        				"<msg:message code="product.prdtype"/>",
        				"<msg:message code="product.No"/>",
        				" <msg:message code="product.prdfrom"/>"," <msg:message code="product.prdfrom"/>","<msg:message code="info.operate"/>"],
        colModel: [{name: 'name',index: 'name',width:'20%',align:'center',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'code',index:'code',width:'20%',align:'center',hidden: false},
                   {name: 'code',index:'code',width:'20%',align:'center',hidden: false},
                   {name: 'code',index:'code',width:'15%',align:'center',hidden: false},
                   {name: 'prdstatus',index:'prdstatus',width:'15%',align:'center',hidden: true},
                   {name:'act',index:'act', width:'25%',align:'center',sortable:false}],
        mtype:"POST",
        postData:{c:"${product.c}"},
        rowNum:10,     
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        viewrecords: true,
        multiselect: true,
    	gridComplete: function(){
    		var ids = jQuery("#prdList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";  
    			<c:if test="${!empty prd_restore}">
    		    <security:authorize ifAnyGranted="${prd_restore.code}">
    		    content += "<a href='#' id='" + id + "' class='shortcut_${prd_restore.index}' title='${prd_restore.name}'>";
    		    content += "<img src='${base}/${common}/${style}/images/icon/restore.png' weight='18' height='18' border='0' align='absmiddle'/>${prd_restore.name}";
    		    content += "</a>";	
    		    </security:authorize>
    		    </c:if> 
    		    <c:if test="${!empty prd_delete}">
    		    <security:authorize ifAnyGranted="${prd_delete.code}">
    		    content += "<a href='#' id='" + id + "' class='shortcut_${prd_delete.index}' title='${prd_delete.name}'>";
    		    content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${prd_delete.name}";
    		    content += "</a>";	
    		    </security:authorize>
    		    </c:if>
    		    jQuery("#prdList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(){   	     	    
    	    $(".shortcut_${prd_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#prdList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${prd_delete.controller}.json?c=${prd_delete.code}&id="+rowid;
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								$("#prdList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    }); 
    	    $(".shortcut_${prd_restore.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#prdList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='product.restore'/>",  function(){
    				var url = "${base}${prd_restore.controller}.json?c=${prd_restore.code}&id="+rowid;
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								$("#prdList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    }); 
    	},
        caption:'<msg:message code="product.list"/>',
        toolbar: [true,"top"]
    });   
    <c:if test="${!empty prd_restore}">
    <security:authorize ifAnyGranted="${prd_restore.code}">	 
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${prd_restore.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/restore.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${prd_restore.name}");
	$("#t_prdList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${prd_restore.index}","#t_prdList").click(function(){
    	var selrow = jQuery("#prdList").jqGrid("getGridParam","selarrrow");
		if (selrow != null){
			window.parent.Boxy.confirm("<msg:message code='product.restore'/>",  function(){
				var url = "${base}${prd_restore.controller}.json?c=${prd_restore.code}&ids="+selrow;
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								//jQuery("#prdList").jqGrid("delRowData",selrow);
								$("#prdList").trigger("reloadGrid");    
							}
						}
					}
				});
			}, {title: "<msg:message code='info.prompt'/>"});
		}
		else{
    		window.parent.Boxy.alert("<msg:message code='product.restore.please'/>", null, {title: "<msg:message code='info.prompt'/>"});
   		}    	
    });    
    </security:authorize> 
    </c:if>
    <c:if test="${!empty prd_delete}">
    <security:authorize ifAnyGranted="${prd_delete.code}">	 
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${prd_delete.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${prd_delete.name}");
	$("#t_prdList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${prd_delete.index}","#t_prdList").click(function(){
    	var selrow = jQuery("#prdList").jqGrid("getGridParam","selarrrow");
		if (selrow != null){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "${base}${prd_delete.controller}.json?c=${prd_delete.code}&ids="+selrow;
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								//jQuery("#prdList").jqGrid("delRowData",selrow);
								$("#prdList").trigger("reloadGrid");    
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
					<form:form method="post" action="list" commandName="product" >
						<input type="hidden" name="c" value="${product.c}"/>
						<input type="hidden" name="delFlag" value="0"/>
					</form:form>
				</td>
			</tr>
			<tr>
				<td>
					<table id="prdList"><tr><td>&nbsp;</td></tr></table>	
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