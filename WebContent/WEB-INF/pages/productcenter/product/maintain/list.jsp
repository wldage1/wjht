<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ taglib prefix="msg" uri="/WEB-INF/tlds/springframework-message.tld" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="${base}/${common}/${style}/css/boxy.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.uploadify{
	float:left;
}
</style>
<script type="text/javascript" src="${base}/${common}/js/boxy/jquery.boxy.js"></script>
<script type="text/javascript">
var illchar="%";
var attributeAndValue = "";
var ids = "";
var pleaseSelect = "<msg:message code='please.select'/>";
var colon = "<msg:message code='system.common.sign.colon'/>";
var uploadPDFBoxy;
$(document).ready(function(){
	// 选中当前查询的产品状态
	$('#select_prdStatus').val('${product.prdStatus}');

    jQuery("#prdList").jqGrid({
        url: 'grid.json?delFlag=0',
        datatype: 'json',
        colNames: ["<msg:message code="product.prdname"/>", 
        				"<msg:message code="product.prdSerialNo"/>",
        				"<msg:message code="product.prdtype"/>",
        				"<msg:message code="product.No"/>",
        				"<msg:message code="product.prdfrom"/>",
						"",
        				"<msg:message code="info.operate"/>"],
        colModel: [{name: 'prdname',index: 'prdname',width:'40%',align:'left',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'prdserialno',index:'prdserialno',width:'10%',align:'center',hidden: false},
                   {name: 'prdtype',index:'prdtype',width:'10%',align:'center',hidden: false},
                   {name: 'code',index:'code',width:'10%',align:'center',hidden: false},
                   {name: 'prdfrom',index:'prdfrom',width:'8%',align:'center',hidden: false},
				   {name: 'shared',index:'shared',hidden: true},
                   {name:'act',index:'act', width:'32%',align:'center',sortable:false}],
        mtype:"POST",
        postData:{c:"${product.c}",prdType:"${product.prdType}",attributeAndValue:"${product.attributeAndValue}",prdName:"${product.prdName}",prdStatus:"${product.prdStatus}"},
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
    			var rowdata = jQuery("#prdList").jqGrid('getRowData',id);
    			var content = "";
    			if("${statusStop}"==rowdata.act){
    				<c:if test="${!empty prd_start}">
		    		    <security:authorize ifAnyGranted="${prd_start.code}">
			   		    	content += "<a href='#' id='" + id + "' class='shortcut_${prd_start.index}' title='${prd_start.name}'>";
			   		    	content += "<img src='${base}/${common}/${style}/images/icon/Enabled.png' weight='18' height='18' border='0' align='absmiddle'/><font color='red' title='${prd_start.name}'>${prd_start.name}</font>";
			   		    	content += "</a>";
		    		    </security:authorize>
	   		    	</c:if>
	   		    	
    			}else{
	   		    	<c:if test="${!empty prd_stop}">
		    		   <security:authorize ifAnyGranted="${prd_stop.code}">
			   			   	content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${prd_stop.index}' title='${prd_stop.name}'>";
			   		    	content += "<img src='${base}/${common}/${style}/images/icon/outage.png' weight='18' height='18' border='0' align='absmiddle'/><font color='green' title='${prd_start.name}'>${prd_stop.name}</font>";
			   		    	content += "</a>";
		    		    </security:authorize>
	   		    	</c:if>
    			}
				
				<c:if test="${!empty prd_shared}">
		    		<security:authorize ifAnyGranted="${prd_shared.code}">
			   		    content += "<a href='#' id='" + id + "' class='shortcut_${prd_shared.index}' title='${prd_shared.name}'>";
						
						if(rowdata.shared == '1'){
							content += "<img src='${base}/${common}/${style}/images/icon/Enabled.png' weight='18' height='18' border='0' align='absmiddle'/><font color='green' title='${prd_shared.name}'><msg:message code="product.shared.yes"/></font>";
						}else{
							content += "<img src='${base}/${common}/${style}/images/icon/Enabled.png' weight='18' height='18' border='0' align='absmiddle'/><font color='red' title='${prd_shared.name}'><msg:message code="product.shared.no"/></font>";
						}
			   		    content += "</a>";
		    		</security:authorize>
	   		    </c:if>
				
				
    			<c:if test="${!empty prd_detail}">
    		    <security:authorize ifAnyGranted="${prd_detail.code}">	
    			content += "<a href='#' id='" + id + "' class='shortcut_${prd_detail.index}' title='${prd_detail.name}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${prd_detail.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    			<c:if test="${!empty prd_modify}">
    		    <security:authorize ifAnyGranted="${prd_modify.code}">	
    			content += "<a href='#' id='" + id + "' class='shortcut_${prd_modify.index}' title='${prd_modify.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${prd_modify.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>  
    		    <c:if test="${!empty prd_recyclerdelete}">
    		    <security:authorize ifAnyGranted="${prd_recyclerdelete.code}">
    		    content += "<a href='#' id='" + id + "' class='shortcut_${prd_recyclerdelete.index}' title='${prd_recyclerdelete.name}'>";
    		    content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${prd_recyclerdelete.name}";
    		    content += "</a>";	
    		    </security:authorize>
    		    </c:if>  

				<c:if test="${!empty prd_generate}">
		    		<security:authorize ifAnyGranted="${prd_generate.code}">
			   		   
						if(rowdata.shared == '1' && "${statusStop}"!=rowdata.act){
							content += "<a href='#' id='" + id + "' class='shortcut_${prd_generate.index}' title='${prd_generate.name}'>";
							content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/><font color='green' title='${prd_generate.name}'>${prd_generate.name}</font>";
						}else{
							content += "<a id='" + id + "' class='' disabled='disabled' title='${prd_generate.name}'>";
							content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/><font color='gray' title='${prd_generate.name}'>${prd_generate.name}</font>";
						}
			   		    content += "</a>";
		    		</security:authorize>
	   		    </c:if>
				
    		    jQuery("#prdList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(){   	
    		$(".shortcut_${prd_detail.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${prd_detail.controller}?c=${prd_detail.code}&id="+rowid;
    	    });
			
    	    $(".shortcut_${prd_modify.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${prd_modify.controller}?c=${prd_modify.code}&prdId="+rowid;
    	    }); 
			
			$(".shortcut_${prd_shared.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#prdList").jqGrid("getRowData",rowid);
    			
    				var url = "${base}${prd_shared.controller}.json?c=${prd_shared.code}&prdId="+rowid+"&shared="+data.shared;
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
    			
    	    });
			
			$(".shortcut_${prd_generate.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#prdList").jqGrid("getRowData",rowid);
    			
    				var url = "${base}${prd_generate.controller}.json?c=${prd_generate.code}&prdId="+rowid;
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
									window.parent.Boxy.alert("<msg:message code='product.generate.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
    								$("#prdList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			
    	    });
			
    	    $(".shortcut_${prd_start.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#prdList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='product.released'/>",  function(){
    				var url = "${base}${prd_start.controller}.json?c=${prd_start.code}&id="+rowid;
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
    	    
    	    $(".shortcut_${prd_stop.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#prdList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='product.releaseStop'/>",  function(){
    				var url = "${base}${prd_stop.controller}.json?c=${prd_stop.code}&id="+rowid;
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
    	    
    	    $(".shortcut_${prd_recyclerdelete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#prdList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${prd_recyclerdelete.controller}.json?c=${prd_recyclerdelete.code}&id="+rowid;
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
    
    <c:if test="${!empty prd_create}">
    <security:authorize ifAnyGranted="${prd_create.code}">	 
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${prd_create.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${prd_create.name}");
	$("#t_prdList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${prd_create.index}","#t_prdList").click(function(){
    	window.location.href = "${base}${prd_create.controller}?c=${prd_create.code}";
    });    
    </security:authorize>
    </c:if>    
    <c:if test="${!empty prd_recyclerdelete}">
    <security:authorize ifAnyGranted="${prd_recyclerdelete.code}">	 
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${prd_recyclerdelete.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${prd_recyclerdelete.name}");
	$("#t_prdList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${prd_recyclerdelete.index}","#t_prdList").click(function(){
    	var selrow = jQuery("#prdList").jqGrid("getGridParam","selarrrow");
		if (selrow != null && selrow != ""){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "${base}${prd_recyclerdelete.controller}.json?c=${prd_recyclerdelete.code}&ids="+selrow;
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
		}
		else{
    		window.parent.Boxy.alert("<msg:message code='info.prompt.delete'/>", null, {title: "<msg:message code='info.prompt'/>"});
   		}    	
    });    
    </security:authorize>
    </c:if>
	<c:if test="${!empty prd_recycler}">
    <security:authorize ifAnyGranted="${prd_recycler.code}">	
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${prd_recycler.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/Recycler.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${prd_recycler.name}");
	$("#t_prdList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${prd_recycler.index}","#t_prdList").click(function(){
    	window.location.href = "${base}${prd_recycler.controller}?c=${prd_recycler.code}";
    });    
    </security:authorize>
    </c:if>
    //add by baokai
    <c:if test="${!empty prd_export_member_product}">
    <security:authorize ifAnyGranted="${prd_export_member_product.code}">	 
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${prd_export_member_product.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/EXPort.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${prd_export_member_product.name}");
	$("#t_prdList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${prd_export_member_product.index}","#t_prdList").click(function(){
    	window.parent.Boxy.confirm("<msg:message code='product.export.confirm'/>",function(){
    		var dialogContent = "<div width='200' height='100' ><msg:message code='product.export'/></div>";
			top.promptBox = new window.parent.Boxy(dialogContent,{title: "<msg:message code='info.prompt'/>",closeText:"[<msg:message code='button.colse'/>]",modal:true,center:true});
    		window.location.href = "${base}${prd_export_member_product.controller}?c=${prd_export_member_product.code}";
    		setTimeout("top.promptBox.hide()",40000); 
    	});
    });    
    </security:authorize>
    </c:if>   
    
    <c:if test="${!empty prd_upload_pdf}">
    <security:authorize ifAnyGranted="${prd_upload_pdf.code}">	 
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${prd_upload_pdf.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/Import.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${prd_upload_pdf.name}");
	$("#t_prdList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${prd_upload_pdf.index}","#t_prdList").click(function(){
    	var selrow = jQuery("#prdList").jqGrid("getGridParam","selarrrow");
		if (selrow != null && selrow != ""){
			$("#pdfPath").val("");
			$("#pdfDiv").html("");
			uploadPDFBoxy.center();
			uploadPDFBoxy.show();
		}else{
    		window.parent.Boxy.alert("<msg:message code='product.upload.pdf.noselect'/>", null, {title: "<msg:message code='info.prompt'/>"});
   		} 
    });    
    </security:authorize>
    </c:if> 
    
    
    <c:if test="${!empty prd_delete_pdf}">
    <security:authorize ifAnyGranted="${prd_delete_pdf.code}">	 
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${prd_delete_pdf.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${prd_delete_pdf.name}");
	$("#t_prdList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${prd_delete_pdf.index}","#t_prdList").click(function(){
    	var selrow = jQuery("#prdList").jqGrid("getGridParam","selarrrow");
		if (selrow != null && selrow != ""){
			window.parent.Boxy.confirm("<msg:message code='product.delete.pdf.confirm'/>",  function(){
				var url = "${base}${prd_delete_pdf.controller}.json";
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					data:{c:"${prd_delete_pdf.code}",ids:""+selrow},
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								window.parent.Boxy.alert("<msg:message code='product.delete.pdf.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
							}
						}
					}
				});
			}, {title: "<msg:message code='info.prompt'/>"});
		}
		else{
    		window.parent.Boxy.alert("<msg:message code='product.delete.pdf.noselect'/>", null, {title: "<msg:message code='info.prompt'/>"});
   		} 
    });    
    </security:authorize>
    </c:if> 
    
    //如果返回的产品类型不为空则直接加载查询属性条件
	<c:if test="${!empty product.prdType}">
		var value = $("#prdType").val();  //获取产品类型值
		var arrtAndVal= $("#attributeAndValue").val();  //获取产品类型值
		$.post("getSelect.json", { c: "${product.c}", prdType: value},
		function(data) {
			var Structure = data.StructureList;
			var size = data.listSize;
			for(var i=0;i<size;i++){
				var showName ="";
				var id = Structure[i].id   //属性ID
				var chineseName = Structure[i].chineseName;  //中文名
				var showType = Structure[i].showType; //显示类型
				var isRequired = Structure[i].isRequired; //是否必填
				var isEnabled = Structure[i].isEnabled; //是否启用
				var checkValue = Structure[i].checkValue; //下拉框、单选、多选框值显示
				if(isEnabled != 0){
					showName += "<tr><td align='right' width='15%' class='search_info'>"+chineseName+""+colon+"</td>";
					showName +="<td align='left' width='85%'>";
					var show =showTypeValue(showType,isRequired,id,checkValue,arrtAndVal);
					showName +=show;
					showName +="</td></tr>";
					if(i != 0)
						ids+=",";
					ids +=id;
					$(showName).appendTo($("#prdTable"));
				}
			} 
		 });
		//获取已选择的属性和值
		function showTypeValue(showType,isRequired,id,checkValue,arrtAndVal){
			var show = "";
			if(arrtAndVal!=""){
				var attr = arrtAndVal.split("@");
				for(var i=0;i<attr.length;i++){
					var val = attr[i].split("#");
					if(val[0]==id){
						//本文框
						if(showType == 1){
							show +="<input type =text id= '"+id+"' name = '"+id+"' value='"+val[1]+"' />";
						}
						//下拉框
						if(showType == 2){
							var chkValue = checkValue.split("&");
							show = "<select  id= '"+id+"' name='"+id+"'>"; 
							show+="<option value=''>"+pleaseSelect+"</option>";	       
							for(var i=0;i<chkValue.length;i++){
								var value = chkValue[i].split("|");
								if(val[1]==value[0]){
									show+="<option value='"+value[0]+"' selected >"+value[1]+"</option>";	     
								}else{
									show+="<option value='"+value[0]+"'>"+value[1]+"</option>";	    
								}
							}
							show+="</select>";  						
						}
						//单选
						if(showType == 3){
							show ="";
							var chkValue = checkValue.split("&");
							for(var i=0;i<chkValue.length;i++){
								var value = chkValue[i].split("|");
								if(val[1]==value[0]){
									show += " <input type='radio' value='"+value[0]+"'id= '"+id+"' name='"+id+"' checked>"+value[1]+"";
								}else{
									show += " <input type='radio' value='"+value[0]+"'id= '"+id+"' name='"+id+"'>"+value[1]+"";
								}
							}
						}
						//多选
						if(showType == 4){
							show ="";
							var chkValue = checkValue.split("&"); //属性中所包含的所有选项
							var valCkb = val[1].split("-");	//产品值中保存的选项
							for(var i=0;i<chkValue.length;i++){
								var value = chkValue[i].split("|");
								var str = "";
								for(var j=0;j<valCkb.length;j++){
									if(valCkb[j]==value[0]){
										show += " <input type='checkbox' value='"+value[0]+"' id= '"+id+"'  name='"+id+"' checked='checked'>"+value[1]+"";
										str = valCkb[j];
									}
								}
								if(str==""){
									show += " <input type='checkbox' value='"+value[0]+"' id= '"+id+"'  name='"+id+"'>"+value[1]+"";
								}
							}	
						}
						//文本域
						if(showType==5){
							show ="<textarea  id= '"+id+"' name='"+id+"'>"+val[1]+"</textarea>";
						}
						//日期
						if(showType == 6){
							show="<input id= '"+id+"'  name='"+id+"' type='text' onFocus='WdatePicker()' class='Wdate' style='width:88px;' readonly='readonly'  value='"+val[1]+"'>"
						}
					}
				}
			}
			return show
		}
	</c:if>
	
    //触发下拉列表中产品类型查询方法
    $("#prdType").change(function(){
    	var value = $("#prdType").val();  //获取产品类型值
    	if(value != ""){
			$.post("getSelect.json", { c: "${product.c}", prdType: value },
			  	function(data) {
				//清除当前已加载的属性
				$("#prdTable").empty();
				ids ="";  //清空IDS
				// 当前产品属性所包含的属性List
				var Structure = data.StructureList;
				//数组长度
				var size = data.listSize;
				for(var i=0;i<size;i++){
					var showName ="";
					var id = Structure[i].id   //属性ID
					var chineseName = Structure[i].chineseName;  //中文名
					var showType = Structure[i].showType; //显示类型
					var isRequired = Structure[i].isRequired; //是否必填
					var isEnabled = Structure[i].isEnabled; //是否启用
					var checkValue = Structure[i].checkValue; //下拉框、单选、多选框值显示
					if(isEnabled != 0){
						showName += "<tr><td align='right' width='15%' class='search_info'>"+chineseName+""+colon+"</td>";
						showName +="<td align='left' width='85%' >";
						var show =showTypeChoose(showType,isRequired,id,checkValue);
						showName +=show;
						showName +="</td></tr>";
						if(i != 0)
							ids+=",";
						ids +=id;
						$(showName).appendTo($("#prdTable"));
					}	
				} 
			});
		}
	});
     //拼接成页面显示条件
    function showTypeChoose(showType,isRequired,id,checkValue){
			var show = "";
			if(showType){
				//本文框
				if(showType == 1){
					show +="<input type =text id= '"+id+"' name = '"+id+"' />";
				}
				//下拉框
				if(showType == 2){
					var chkValue = checkValue.split("&");
					show = "<select  id= '"+id+"' name='"+id+"'>"; 
					show+="<option value=''>"+pleaseSelect+"</option>";	       
					for(var i=0;i<chkValue.length;i++){
						var value = chkValue[i].split("|");
						show+="<option value='"+value[0]+"'>"+value[1]+"</option>";	       
					}
					show+="</select>";  						
				}
				//单选
				if(showType == 3){
					show ="";
					var chkValue = checkValue.split("&");
					for(var i=0;i<chkValue.length;i++){
						var value = chkValue[i].split("|");
						show += " <input type='radio' value='"+value[0]+"'id= '"+id+"' name='"+id+"'>"+value[1]+"";
					}
				}
				//多选
				if(showType == 4){
					show ="";
					var chkValue = checkValue.split("&");  //属性中所包含的所有选项
					for(var i=0;i<chkValue.length;i++){
						var value = chkValue[i].split("|");
						show += " <input type='checkbox' value='"+value[0]+"' id= '"+id+"'  name='"+id+"'>"+value[1]+"";
					}	
				}
				//文本域
				if(showType==5){
					show ="<textarea  id= '"+id+"' name='"+id+"'></textarea>";
				}
				//日期
				if(showType == 6){
					show="<input id= '"+id+"'  name='"+id+"' type='text' onFocus='WdatePicker()' class='Wdate' style='width:88px;' readonly='readonly' >"
				}
			}
			return show;
		}
     
     
    uploadPDF();
    //存储路径
	var pdfpath = "";
	//国际化文件
	var deleteFile="<msg:message code='button.delete'/>";
	var downLoadPath="<msg:message code='product.downLoadPath'/>";
	var downLoadPathSyn="<msg:message code='product.downLoadPathSyn'/>";
	$("#uploadify").uploadify({
                'swf'            : '${base}/${common}/flash/uploadify/uploadify.swf',//指定uploadify.swf路径   
       			'uploader'       : '${base}/productcenter/product/maintain/uploadfile',//后台处理的请求的servlet,同时传递参数,由于servlet只能接收一个参数，所以将两个参数合并成一个。   
                'buttonClass'    : 'btn2',
                'queueID'        : 'fileQueue',
                'auto'           : true,
                'multi'          : false,
                'fileTypeDesc'   : "pdf文件",               
        		'fileTypeExts'   : "*.pdf;", //控制可上传文件的扩展名，启用本项时需同时声明fileDesc    
                'buttonText'     : '<msg:message code='info.prompt.select'/>',   
		        'width'          : 60,
		        'height'         : 24,
	            onUploadSuccess: function (file,data,response) {	
                if(response==true){
                	pdfpath = $("#pdfPath").val();
                	var realFileName = (file.name).substring(0, (file.name).lastIndexOf("."));
                	var fileAtt = data.split("&");
	        		//文件上传后完整路径
	        		var filepath = fileAtt[0];
	        		//文件上传后文件名称
	        		var filename = (filepath).substring((filepath).lastIndexOf("/")+1,(filepath).length);
	        		//未上传时文件ID为上传后的文件名
	        		var pathid = (filename).substring(0, (filename).lastIndexOf("."));
                	pdfpath = pdfpath + realFileName+"#"+filename+"@";
	        		$("#pdfPath").val(pdfpath);
	        		var synUploadUrl = fileAtt[2];
	        		var showpath = "<div id='"+pathid+"'><span>";
	        		showpath += realFileName+"&nbsp;&nbsp;<font color='blue'>『"+filename+"』</font></span>";
	        		showpath += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	        		showpath += "<a href='#' class='delete' id='"+pathid+"' onclick=\"delpdf('"+pathid+"')\">"+deleteFile+"</a>";
	        		showpath += "&nbsp;&nbsp;&nbsp;&nbsp;";
	        		showpath += "<a href=\"javascript:window.open('${base}/"+filepath+"');\" class='delete' id='"+pathid+"' >"+downLoadPath+"</a>";
	        		showpath += "&nbsp;&nbsp;&nbsp;&nbsp";
	        		if(fileAtt[1] == 1){
	        			//同步成功
	        			showpath += "<a href=\"javascript:window.open('"+synUploadUrl+"/"+filepath+"')\" class='delete' id='"+pathid+"' >"+downLoadPathSyn+"</a><br></div>";
	        		}else{
	        			//同步失败
	        			showpath += "<span><font color='black'>"+downLoadPathSyn+"</font><span></a><br></div>";
	        			//同步失败不可保存
	        			$('#btnok').attr('disabled','true');
	        		}
	        		$(showpath).appendTo($("#pdfDiv"));
                }else{
                	window.parent.Boxy.alert("<msg:message code='product.pdfFail'/>", null, {title: "<msg:message code='info.prompt'/>"});
                }
                uploadPDFBoxy.center();
         }  
    });
     
});

function formSubmit(){
	//查询时去掉字符串两遍的空格
	$("#prdName").val($.trim($("#prdName").val()));
	if(ids !=""){
		var idsArray = ids.split(",");
		for(var j = 0;j<idsArray.length;j++){
			var idByOne = idsArray[j];
			var ttName = $.trim($("#"+idByOne).val());
			var inputType =  $("#"+idByOne).attr("type");
			//如果为checkbox获取全部已选ID
			if(inputType=="checkbox"){
				var ckbValue ="";
				var ckb = document.getElementsByName(idByOne);
				for(var t=0;t<ckb.length;t++){
					if(ckb[t].checked){
						ckbValue += ckb[t].value+"-";
					}
				}
				ttName = ckbValue;
			}
			//如果为radio获取当前选中值
			if(inputType=="radio"){
				var val="";
				var ckb = document.getElementsByName(idByOne);
				for(var t=0;t<ckb.length;t++){
					if(ckb[t].checked){
						val=$("input[name='"+idByOne+"']:checked").val();
					}
				}
				ttName=val;
			}
			if(j!=0){
				attributeAndValue += '@';
			}
			attributeAndValue +=idByOne+"#"+ttName;
		}
		$("#attributeAndValue").val(attributeAndValue);
		ids = "";  //清除以查询的IDS
	}
}

function uploadPDF(){
	var html = "<div id='uploadDiv'>";
	html += "<div id='fileQueue'></div>";
	html += "<div id='pdfDiv'></div>";
	html += "<br>";
	html += "<div align='left'>";
	html += "<input type='hidden' name='pdfPath' id='pdfPath'/>";
	html +=	"<input type='file' name='uploadify' id='uploadify'/>";
	html +=	"<input type='button' name='submit' id='submit' class='btn2' value='<msg:message code='button.save'/>' style='cursor:pointer;' onclick='submitPDF();'/>";
	html += "</div>";
	html += "</div>";
	if(uploadPDFBoxy != null){
		uploadPDFBoxy.setContent(html);
	}else{
		uploadPDFBoxy = new Boxy(html,{title:"<msg:message code='product.upload.pdf.title'/>",closeText:"[<msg:message code='button.colse'/>]",modal:true,center:true,show:false});
	}
}

//删除上传文件
function delpdf(id){
	var path = $("#pdfPath").val();
	var newPath="";
	//删除字符串中Id对应值
	var fileFile = path.split("@");
	for(var i=0;i<fileFile.length-1;i++){
		var fileNameFile = fileFile[i].split("#");
		//文件上传后完整路径
	   var filepath =  fileFile[i].split("#");
	   //文件上传后文件名称
		var pathToId =  (fileNameFile[1]).substring((fileNameFile[1]).lastIndexOf("\\")+1, (fileNameFile[1]).lastIndexOf("."));
		if(id!=pathToId){
			newPath += fileNameFile[0]+"#"+fileNameFile[1]+"@";
		}
	}
	$.post("deletePdfFile.json", {id :id },function(data){});
	$("#pdfPath").val(newPath);
	$('#'+id).remove();
	$('#btnok').removeAttr('disabled');
}

function submitPDF(){
	var pdfPath = $("#pdfPath").val();
	if(pdfPath == null || pdfPath == ''){
		window.parent.Boxy.alert("<msg:message code='product.upload.pdf.please'/>", null, {title: "<msg:message code='info.prompt'/>"});
		return;
	}
	var selrow = jQuery("#prdList").jqGrid("getGridParam","selarrrow");
	if (selrow != null && selrow != ""){
		var url = "${base}${prd_upload_pdf.controller}.json";
		$.ajax({
			url:url,
			type:'post',
			timeout:'60000',
			dataType:'json',
			data:{c:"${prd_upload_pdf.code}",ids:""+selrow,pdfFileIPhone:encodeURI($("#pdfPath").val())},
			success:function(jsonData,textStatus){
				uploadPDFBoxy.hide();
				if (textStatus == "success"){
					if (jsonData.status == "success"){
						window.parent.Boxy.alert("<msg:message code='product.upload.pdf.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
					}
				}
			}
		});
	}
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
			<table cellpadding="0" cellspacing="0" border="0" width="100%" class="skinMain">
			<tr>
				<td align="center">
					<form:form method="post" action="list" commandName="product" onsubmit="return formSubmit();">
						<input type="hidden" name="c" value="${product.c}"/>
						<input type="hidden" name="delFlag" value="0"/>
						<form:hidden path="attributeAndValue" />
						<table cellpadding="0" cellspacing="1" border="0" width="100%" class="search">
							<tr>
								<td align="right" width="10%" class="search_info">
									<msg:message code='product.prdname' /><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="20%" class="search_lable">
									<form:input path="prdName"  maxlength="25"/>			
								</td>
								<td align="right" width="10%" class="search_info">
									<msg:message code='product.prdtype' /><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="20%" class="search_lable">
									<form:select path="prdType">
										<form:option value="">--<msg:message code="please.select"/>--</form:option>
										<form:options items="${productTypeList}" itemValue="value" itemLabel="name"/>
									</form:select>
								</td>
								<td align="right" width="10%" class="search_info">
									<msg:message code='product.release' /><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="10%" class="search_lable">
									<select name="prdStatus" id="select_prdStatus">
										<option value="">--<msg:message code="please.select"/>--</option>
										<option value="${statusStart}"><msg:message code="product.release.yes"/></option>
										<option value="${statusStop}"><msg:message code="product.release.no"/></option>
									<select>			
								</td>
								<td  width="20%" class="button_area" align="center">
									<input type="submit" class="btn2" id="select" value="<msg:message code='button.search' />">
								
								</td>
							</tr>	
							<tr><td><div id="mydiv"></div></td></tr>
						</table>
						<table id="prdTable"  width="100%" border="0" cellspacing="0" cellpadding="0"  class="skinLayout">
						</table>
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