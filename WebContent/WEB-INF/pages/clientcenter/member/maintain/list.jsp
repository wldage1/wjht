<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
var sv = "${member.selectValues}";
sv.replace("\"","\\\"");
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
        postData:{c:"${member.c}",financialPlanner:"${member.financialPlanner}",registPhone:"${member.registPhone}",memberLevel:"${member.memberLevel}",delStatus:"${member.delStatus}",sqlCondition:"${member.sqlCondition}",complexSearchFlag:"${member.complexSearchFlag}",selectValues:sv},
        rowNum:10,    
        page:"${member.page}",
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        shrinkToFit:true,
        viewrecords: true,
        gridComplete: function(){
    		var ids = jQuery("#memberList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var rowdata = jQuery("#memberList").jqGrid('getRowData',id);
    			var content = "";
   				if("${statusStop}"==rowdata.operate){
					<c:if test="${!empty maintain_start}">
		    		    <security:authorize ifAnyGranted="${maintain_start.code}">
			   		    	content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_start.index}' title='${maintain_start.name}'>";
			   		    	content += "<img src='${base}/${common}/${style}/images/icon/Enabled.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_start.name}";
			   		    	content += "</a>";
		    		    </security:authorize>
	   		    	</c:if>
				}else{
					<c:if test="${!empty maintain_stop}">
		    		   <security:authorize ifAnyGranted="${maintain_stop.code}">
			   			   	content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_stop.index}' title='${maintain_stop.name}'>";
			   		    	content += "<img src='${base}/${common}/${style}/images/icon/outage.png' weight='18' height='18' border='0' align='absmiddle' />${maintain_stop.name}";
			   		    	content += "</a>";
		    		    </security:authorize>
	   		    	</c:if>
   					
   				}
    			<c:if test="${!empty maintain_modify}">
    		    <security:authorize ifAnyGranted="${maintain_modify.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_modify.index}' title='${maintain_modify.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_modify.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>  
    		    <c:if test="${!empty maintain_pseudoDelete}">
    		    <security:authorize ifAnyGranted="${maintain_pseudoDelete.code}">
   		    	content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_pseudoDelete.index}' title='${maintain_pseudoDelete.name}' >";
   		    	content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_pseudoDelete.name}";
   		    	content += "</a>";
    		    </security:authorize>
    		    </c:if>
    		    <c:if test="${!empty maintain_empty}">
    		    <security:authorize ifAnyGranted="${maintain_empty.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_empty.index}' title='${maintain_empty.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/empty.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_empty.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>  
				<c:if test="${!empty maintain_detail}">
    		    <security:authorize ifAnyGranted="${maintain_detail.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_detail.index}' title='${maintain_detail.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_detail.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    			jQuery("#memberList").jqGrid('setRowData',ids[i],{operate:content});
    		}	
    	},
    	loadComplete:function(){
    	    $(".shortcut_${maintain_pseudoDelete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#memberList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${maintain_pseudoDelete.controller}.json?c=${maintain_pseudoDelete.code}&id="+rowid+"&prompt=memberName&memberName="+encodeURIComponent(encodeURIComponent(data.memberName));
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
    	    $(".shortcut_${maintain_modify.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${maintain_modify.controller}?c=${maintain_modify.code}&id="+rowid;
    	    });
    	    $(".shortcut_${maintain_start.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#memberList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.start.confirm'/>",  function(){
    				var url = "${base}${maintain_start.controller}.json?c=${maintain_start.code}&id="+rowid+"&prompt=memberName&memberName="+encodeURIComponent(encodeURIComponent(data.memberName));
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
    	    $(".shortcut_${maintain_stop.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#memberList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.stop.confirm'/>",  function(){
    				var url = "${base}${maintain_stop.controller}.json?c=${maintain_stop.code}&id="+rowid+"&prompt=memberName&memberName="+encodeURIComponent(encodeURIComponent(data.memberName));
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
    	    $(".shortcut_${maintain_empty.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#memberList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.emptyauthenticationcode.confirm'/>",  function(){
    				var url = "${base}${maintain_empty.controller}.json?c=${maintain_empty.code}&id="+rowid+"&prompt=memberName&memberName="+encodeURIComponent(encodeURIComponent(data.memberName));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								//$("#memberList").trigger("reloadGrid");
    								window.parent.Boxy.alert("<msg:message code='info.prompt.emptyauthenticationcode.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
    							}else{
    								window.parent.Boxy.alert("<msg:message code='info.prompt.emptyauthenticationcode.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
    							}
    						}else{
    							window.parent.Boxy.alert("<msg:message code='info.prompt.emptyauthenticationcode.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });
			$(".shortcut_${maintain_detail.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${maintain_detail.controller}?c=${maintain_detail.code}&id="+rowid;
    	    });
    	},
        caption:'<msg:message code="member.list"/>',
        toolbar: [true,"top"]
    });
    <c:if test="${!empty maintain_create}">
    <security:authorize ifAnyGranted="${maintain_create.code}">	
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${maintain_create.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${maintain_create.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${maintain_create.index}","#t_memberList").click(function(){
    	window.location.href = "${base}${maintain_create.controller}?c=${maintain_create.code}";
    });    
    </security:authorize>
    </c:if>
    <c:if test="${!empty maintain_pseudoDelete}">
    <security:authorize ifAnyGranted="${maintain_pseudoDelete.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${maintain_pseudoDelete.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${maintain_pseudoDelete.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${maintain_pseudoDelete.index}","#t_memberList").click(function(){
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
				var url = "${base}${maintain_pseudoDelete.controller}.json?c=${maintain_pseudoDelete.code}&ids="+ids+"&memberName="+encodeURIComponent(encodeURIComponent(names))+"&prompt=memberName";
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
    <c:if test="${!empty maintain_start}">
    <security:authorize ifAnyGranted="${maintain_start.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${maintain_start.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/Enabled.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${maintain_start.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${maintain_start.index}","#t_memberList").click(function(){
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
			window.parent.Boxy.confirm("<msg:message code='info.prompt.start.confirm'/>",  function(){
				var url = "${base}${maintain_start.controller}.json?c=${maintain_start.code}&ids="+ids+"&memberName="+encodeURIComponent(encodeURIComponent(names))+"&prompt=memberName";
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
    		window.parent.Boxy.alert("<msg:message code='info.prompt.start'/>", null, {title: "<msg:message code='info.prompt'/>"});
   		}    	
    });    
    </security:authorize>
    </c:if>
    <c:if test="${!empty maintain_stop}">
    <security:authorize ifAnyGranted="${maintain_stop.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${maintain_stop.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/outage.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${maintain_stop.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${maintain_stop.index}","#t_memberList").click(function(){
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
			window.parent.Boxy.confirm("<msg:message code='info.prompt.stop.confirm'/>",  function(){
				var url = "${base}${maintain_stop.controller}.json?c=${maintain_stop.code}&ids="+ids+"&memberName="+encodeURIComponent(encodeURIComponent(names))+"&prompt=memberName";
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
    		window.parent.Boxy.alert("<msg:message code='info.prompt.stop'/>", null, {title: "<msg:message code='info.prompt'/>"});
   		}    	
    });    
    </security:authorize>
    </c:if>
    <c:if test="${!empty maintain_recycler}">
    <security:authorize ifAnyGranted="${maintain_recycler.code}">	
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${maintain_recycler.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/Recycler.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${maintain_recycler.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${maintain_recycler.index}","#t_memberList").click(function(){
    	window.location.href = "${base}${maintain_recycler.controller}?c=${maintain_recycler.code}";
    });    
    </security:authorize>
    </c:if>
	 <c:if test="${!empty maintain_statistic}">
    <security:authorize ifAnyGranted="${maintain_statistic.code}">	
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${maintain_statistic.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/modify.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${maintain_statistic.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${maintain_statistic.index}","#t_memberList").click(function(){
    	window.location.href = "${base}${maintain_statistic.controller}?c=${maintain_statistic.code}";
    });    
    </security:authorize>
    </c:if>
    
    <c:if test="${!empty maintain_crm_synchronous}">
    <security:authorize ifAnyGranted="${maintain_crm_synchronous.code}">	
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${maintain_crm_synchronous.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/sync.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${maintain_crm_synchronous.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${maintain_crm_synchronous.index}","#t_memberList").click(function(){
    	var ids = jQuery("#memberList").jqGrid("getGridParam","selarrrow");
    	var crmids = "";
    	for(var i=0;i < ids.length;i++){
    		var id = ids[i];
    		var data = jQuery("#memberList").jqGrid("getRowData",id);
    		if(data.crmId != null && data.crmId != ""){
    			crmids += data.crmId;
	    		if(i!= ids.length-1)
	    			crmids += ",";
    		}
    	}
		if (ids != null && ids!=""){
			window.parent.Boxy.confirm("<msg:message code='member.crmsynchronous.confirm'/>",  function(){
				var dialogContent = "<div width='200' height='100' ><msg:message code='member.crmsynchronous.wait'/></div>";
	    		top.promptBox = new window.parent.Boxy(dialogContent,{title: "<msg:message code='info.prompt'/>",closeText:"[<msg:message code='button.colse'/>]",modal:true,center:true});
				var url = "${base}${maintain_crm_synchronous.controller}.json?c=${maintain_crm_synchronous.code}&ids="+crmids;
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						top.promptBox.hide();
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								window.parent.Boxy.alert("<msg:message code='member.crmsynchronous.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
								$("#memberList").trigger("reloadGrid");    
							}
						}else{
							window.parent.Boxy.alert("<msg:message code='member.crmsynchronous.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
						}
					},
					error:function(e){
						top.promptBox.hide();
						window.parent.Boxy.alert("<msg:message code='member.crmsynchronous.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
					}
				});
			}, {title: "<msg:message code='info.prompt'/>"});
		}
		else{
    		window.parent.Boxy.alert("<msg:message code='member.crmsynchronous.noselect'/>", null, {title: "<msg:message code='info.prompt'/>"});
   		}  
    });    
    </security:authorize>
    </c:if>
    
    
    <c:if test="${!empty maintain_certification}">
    <security:authorize ifAnyGranted="${maintain_certification.code}">	
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${maintain_certification.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/certification.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${maintain_certification.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${maintain_certification.index}","#t_memberList").click(function(){
		window.parent.Boxy.confirm("<msg:message code='member.certification.confirm'/>",  function(){
			var dialogContent = "<div width='200' height='100' ><msg:message code='member.certification.wait'/></div>";
    		top.promptBox = new window.parent.Boxy(dialogContent,{title: "<msg:message code='info.prompt'/>",closeText:"[<msg:message code='button.colse'/>]",modal:true,center:true});
			var url = "${base}${maintain_certification.controller}.json?c=${maintain_certification.code}";
			$.ajax({
				url:url,
				type:'post',
				timeout:'60000',
				dataType:'json',
				success:function(jsonData,textStatus){
					top.promptBox.hide();
					if (textStatus == "success"){
						if (jsonData.status == "success"){
							window.parent.Boxy.alert("<msg:message code='member.certification.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
							$("#memberList").trigger("reloadGrid");    
						}
					}else{
						window.parent.Boxy.alert("<msg:message code='member.certification.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
					}
				},
				error:function(e){
					top.promptBox.hide();
					window.parent.Boxy.alert("<msg:message code='member.certification.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
				}
			});
		}, {title: "<msg:message code='info.prompt'/>"});
    });    
    </security:authorize>
    </c:if>
    
    <c:if test="${!empty maintain_export}">
    <security:authorize ifAnyGranted="${maintain_export.code}">	
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${maintain_export.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/EXPort.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${maintain_export.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${maintain_export.index}","#t_memberList").click(function(){
    	window.parent.Boxy.confirm("<msg:message code='member.export.confirm'/>",function(){
    		var dialogContent = "<div width='200' height='100' ><msg:message code='member.export.wait'/></div>";
    		top.promptBox = new window.parent.Boxy(dialogContent,{title: "<msg:message code='info.prompt'/>",closeText:"[<msg:message code='button.colse'/>]",modal:true,center:true});
    		var dataparam = {
    			    c:"${maintain_export.code}",
    			    financialPlanner:"${member.financialPlanner}",
    			    registPhone:"${member.registPhone}",
    			    memberLevel:"${member.memberLevel}",
    			    delStatus:"${member.delStatus}",
    			    sqlCondition:"${member.sqlCondition}",
    			    complexSearchFlag:"${member.complexSearchFlag}"
    			 };

    			$.ajax({
    				type : 'POST',
    				dataType : 'json',
    				contentType : 'application/x-www-form-urlencoded',
    				data:dataparam,
    				url : '${base}${maintain_export.controller}',
    				success : function(data) {
    					top.promptBox.hide();
    					if(data.status==1){
    						var fileName = data.filename;
    						var path = data.path;
    						window.location.href="${base}/clientcenter/member/maintain/downloadExcel?n="+encodeURI(encodeURI(fileName))+"&p="+encodeURI(encodeURI(path));
    					}else if(data.status==0){
    						window.parent.Boxy.alert("<msg:message code='member.export.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
    					}
    				},
    				error:function(e){
    					top.promptBox.hide();
    					window.parent.Boxy.alert("<msg:message code='member.export.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
    				}
    			});
    	});
    });    
    </security:authorize>
    </c:if> 
    
    
    
    //高级查询初始化
    searchTB.setSearchData(jsonData);
    searchTB.setElementID('searchTools');
    searchTB.setRowCount(3);  
    searchTB.execute();
    //设置查询表单显示
    <c:if test="${!empty member.complexSearchFlag}">
    	$('#searchForm').css('display','none');
    	$('#complexSearchForm').css('display','block');
    	var tagName = document.getElementsByTagName("input");
    	selectValues = JSON.parse("${member.selectValues}");
    	for(var i = 0; i < selectValues.length; i++){
    		selectValue = selectValues[i];
    		if(selectValue.type == 'radio'){
    			for ( var j = 0; j < tagName.length; j++){
    				if (tagName[j].type == "radio" && tagName[j].value == selectValue.value){
    					tagName[j].checked = 'checked';
    				}
    			}
    		}else{
    			if((selectValue.id).indexOf("column") != -1){
    	    		$('#'+selectValue.id).val(selectValue.value);
    	    		$('#'+selectValue.id).change();
    			}
	    		$('#'+selectValue.id).val(selectValue.value);
    		}
    		
    	}
    </c:if>
});
var and = "<msg:message code='condition.and'/>";
var or = "<msg:message code='condition.or'/>";
var pleaseSelect = "<msg:message code='please.select'/>";
var elementIDError = "<msg:message code='searchToolBar.elementID.error'/>";
var searchDataError = "<msg:message code='searchToolBar.searchData.error'/>";
var jsonData = {
    		   	searchFuncName:'',	
    			columns:
		        [
			        {
			            name:'ME.UserName',desc:"<msg:message code='member.userName'/>",dataType:'',isshow:'0',primKey:'',checkbox:'1',link:{funcName:'',url:''}, 
			            width:'10%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'input',sid:''},alias:''
			        },
					{
			            name:'M.MemberName',desc:"<msg:message code='member.name'/>",dataType:'',isshow:'0',primKey:'',checkbox:'1',link:{funcName:'',url:''}, 
			            width:'10%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'input',sid:''},alias:''
			        },
			        {
			            name:'M.MobilePhone',desc:"<msg:message code='member.mobilePhone'/>",dataType:'',isshow:'1',primKey:'0',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'input',sid:''},alias:''
			        },
			        {
			            name:'ME.RegistPhone',desc:"<msg:message code='member.registPhone'/>",dataType:'',isshow:'1',primKey:'0',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'input',sid:''},alias:''
			        },
			        {	 
			            name:'M.Sex',desc:"<msg:message code='member.sex'/>",dataType:'',isshow:'1',primKey:'0',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'select',sid:''},alias:''
			        },
			        {
			            name:'M.MemberLevel',desc:"<msg:message code='member.crmlevel'/>",dataType:'',isshow:'1',primKey:'1',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'select',sid:''},alias:''
			        },
			        {
			            name:'ME.Status',desc:"<msg:message code='member.status'/>",dataType:'',isshow:'1',primKey:'1',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'select',sid:''},alias:''
			        },
			        {
			            name:'ME.Source',desc:"<msg:message code='member.source'/>",dataType:'',isshow:'0',primKey:'1',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'select',sid:''},alias:''
			        },
			        {
			            name:'ME.RegistrationTime',desc:"<msg:message code='member.registrationTime'/>",dataType:'',isshow:'0',primKey:'1',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'date',sid:''},alias:''
			        },
			        {
			            name:'M.FinancialPlanner',desc:"<msg:message code='member.financialPlanner'/>",dataType:'',isshow:'0',primKey:'1',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'input',sid:''},alias:''
			        },
			        {
			            name:'M.CrmID',desc:"<msg:message code='member.crmID'/>",dataType:'',isshow:'0',primKey:'1',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'input',sid:''},alias:''
			        },
			        {
			            name:'M.BranchOrg',desc:"<msg:message code='member.branchOrg'/>",dataType:'',isshow:'0',primKey:'1',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'input',sid:''},alias:''
			        }
		    	],		
    			expressions:
				[
					{
						type:'date',option:[
						                        {name:'equal',value:'=',desc:"<msg:message code='condition.equal'/>",isValid:'1'},
							                	{name:'notEqual',value:'<>',desc:"<msg:message code='condition.notEqual'/>",isValid:'1'},
							                	{name:'greaterThan',value:'>',desc:"<msg:message code='condition.greaterThan'/>",isValid:'1'},
							                	{name:'greaterEqual',value:'>=',desc:"<msg:message code='condition.greaterEqual'/>",isValid:'1'},
							                	{name:'lessThan',value:'<',desc:"<msg:message code='condition.lessThan'/>",isValid:'1'},
							                	{name:'lessEqual',value:'<=',desc:"<msg:message code='condition.lessEqual'/>",isValid:'1'},
							                	{name:'like',value:'like',desc:"<msg:message code='condition.like'/>",isValid:'1'},
						 	                	{name:'noLike',value:'not like',desc:"<msg:message code='condition.notLike'/>",isValid:'1'}
					                       ]
					},
					{
						type:'select',option:[
						                        {name:'equal',value:'=',desc:"<msg:message code='condition.equal'/>",isValid:'1'},
							                	{name:'notEqual',value:'<>',desc:"<msg:message code='condition.notEqual'/>",isValid:'1'},
							                	{name:'greaterThan',value:'>',desc:"<msg:message code='condition.greaterThan'/>",isValid:'1'},
							                	{name:'greaterEqual',value:'>=',desc:"<msg:message code='condition.greaterEqual'/>",isValid:'1'},
							                	{name:'lessThan',value:'<',desc:"<msg:message code='condition.lessThan'/>",isValid:'1'},
							                	{name:'lessEqual',value:'<=',desc:"<msg:message code='condition.lessEqual'/>",isValid:'1'},
							                	{name:'like',value:'like',desc:"<msg:message code='condition.like'/>",isValid:'1'},
						 	                	{name:'noLike',value:'not like',desc:"<msg:message code='condition.notLike'/>",isValid:'1'}
						                     ]
					},
					{
						type:'input',option:[
						                        {name:'equal',value:'=',desc:"<msg:message code='condition.equal'/>",isValid:'1'},
							                	{name:'notEqual',value:'<>',desc:"<msg:message code='condition.notEqual'/>",isValid:'1'},
							                	{name:'greaterThan',value:'>',desc:"<msg:message code='condition.greaterThan'/>",isValid:'1'},
							                	{name:'greaterEqual',value:'>=',desc:"<msg:message code='condition.greaterEqual'/>",isValid:'1'},
							                	{name:'lessThan',value:'<',desc:"<msg:message code='condition.lessThan'/>",isValid:'1'},
							                	{name:'lessEqual',value:'<=',desc:"<msg:message code='condition.lessEqual'/>",isValid:'1'},
							                	{name:'like',value:'like',desc:"<msg:message code='condition.like'/>",isValid:'1'},
						 	                	{name:'noLike',value:'not like',desc:"<msg:message code='condition.notLike'/>",isValid:'1'}
						                    ]
					}
				],
				selectValues:
				[
	            	{
	            		name:"M.Sex",value:[
												<c:forEach var="sex" items="${sexList}" varStatus="stat">
													<c:if test="${!stat.last}">
													{name:"${sex.name}",value:"${sex.value}"},
													</c:if>
													<c:if test="${stat.last}">
													{name:"${sex.name}",value:"${sex.value}"}
													</c:if>														
												</c:forEach>
	            		                   ]
	            	},
	            	{
	            		name:"M.MemberLevel",value:[
						  						  <c:forEach var="level" items="${levelList}" varStatus="stat">
														<c:if test="${!stat.last}">
														{name:"${level.name}",value:"${level.name}"},
														</c:if>
														<c:if test="${stat.last}">
														{name:"${level.name}",value:"${level.name}"}
														</c:if>			            		                    	  	  
	            		                    	  </c:forEach>
	            		                   	  ]
	            	},
	            	{
	            		name:"ME.Source",value:[
													<c:forEach var="source" items="${sourceList}" varStatus="stat">
														<c:if test="${!stat.last}">
														{name:"${source.name}",value:"${source.value}"},
														</c:if>
														<c:if test="${stat.last}">
														{name:"${source.name}",value:"${source.value}"}
														</c:if>														
													</c:forEach>
	            		                       ]
	            	},
	            	{
	            		name:"ME.Status",value:[
													<c:forEach var="status" items="${statusList}" varStatus="stat">
														<c:if test="${!stat.last}">
														{name:"${status.name}",value:"${status.value}"},
														</c:if>
														<c:if test="${stat.last}">
														{name:"${status.name}",value:"${status.value}"}
														</c:if>
													</c:forEach>
	            		                       ]
	            	}
	            ]
			}; 
function submitForm(){
	$('#financialPlanner').val($.trim($('#financialPlanner').val()));
	$('#registPhone').val($.trim($('#registPhone').val()));
	document.searchForm.submit();
}

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
		<td align="center">
			<form:form method="post" action="list" commandName="member" name="searchForm" id="searchForm">
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
							<input type=button class="btn2" id="select" value="<msg:message code='member.button.search'/>" onclick="submitForm();">
							<input type="button" class="btn2" onclick="this.form.style.display='none';document.complexSearchForm.style.display='block';" value="<msg:message code='member.button.complexSearch'/>">
							<input type=button class="btn2" id="select" onclick="this.form.financialPlanner.value = '';this.form.registPhone.value = '';this.form.memberLevel.value = '';" value="<msg:message code='button.reset'/>">
						</td>
					</tr>			
				</table>
			</form:form>
			<form:form method="post" action="list" commandName="member" name="complexSearchForm" id="complexSearchForm" cssStyle="display:none;">
				<input type="hidden" name="c" value="${member.c}"/>
				<form:hidden path="complexSearchFlag" value="1"/>
				<form:hidden path="selectValues" value=""/>
				<form:hidden path="sqlCondition" value=""/>
				<table cellpadding="0" cellspacing="0" border="0" width="100%" style="border: #CCF solid 1px;">
					<tr>
						<td width="90%">
							<div id="searchTools"></div>
						</td>
						<td width="10%" style="border-left:#CCCCFF solid 1px;" align="center" valign="middle">
							<input type=button class="btn2" id="select" onclick="$('#selectValues').val(getSelectValues());$('#sqlCondition').val(getSearchParams());this.form.submit();" value="<msg:message code='member.button.search'/>">
							<br>
							<br>
							<input type=button class="btn2" id="select" onclick="searchTB.execute();" value="<msg:message code='button.reset'/>">
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