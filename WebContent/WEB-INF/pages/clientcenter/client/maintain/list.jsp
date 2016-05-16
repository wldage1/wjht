<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
var area;
var province;
var city;
var District;	 	
$(document).ready(function(){
    jQuery("#clientList").jqGrid({
        url: 'grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='client.name'/>",
				   "<msg:message code='client.phone'/>", 
                   "<msg:message code='client.permit'/>",
                   "<msg:message code='client.cardNum'/>",
                   "<msg:message code='client.cardStatus'/>",
                   "<msg:message code='client.cardType'/>",
                   "<msg:message code='client.credit'/>",
                   "<msg:message code='client.userName'/>",
                   "<msg:message code='client.createTime'/>",
                   "<msg:message code='client.operate'/>"],
        colModel: [{name: 'mame',index:'mame',width:'8%',align:'center',hidden: false,sortable:false},
				   {name: 'phone',index:'phone',width:'10%',align:'center',hidden: false,sortable:false},
                   {name: 'permit',index:'permit',width:'6%',align:'center',hidden: false,sortable:false,
					   formatter: function(cellvalue, options, rowObject){
	   	            	  	var value = "";
		   	            	if(cellvalue == '1'){
		   	            		value = "通过";
		   	        		}else if(cellvalue == '2'){
		   	        			value = "拒绝";
		   	        		}else{
		   	        			value = "未审";
		   	        		}
	               			return value;
	         	 	 	}	
				   },
                   {name: 'cardNum',index:'cardNum',width:'16%',align:'center',hidden: false,sortable:false},
                   {name: 'cardStatus',index:'cardStatus',width:'7%',align:'center',hidden: false,sortable:false,
                	   formatter: function(cellvalue, options, rowObject){
   	            	  	var value = "";
   	            		if(cellvalue == '0'){
   	            			value = "未发卡";
   	            		}else if(cellvalue == '1'){
   	            			value = "未激活";
   	            		}else if(cellvalue == '2'){
   	            			value = "已激活";
   	            		}else if(cellvalue == '3'){
   	            			value = "卡拒绝";
   	            		}
               			return value;
         	 	 	  }	
                   },
                   {name: 'cardType',index:'cardType',width:'8%',align:'center',hidden: false,sortable:false,
                	   formatter: function(cellvalue, options, rowObject){
      	            	  	var value = "";
      	            		 if(cellvalue == '1'){
      	            			value = "银卡";
      	            		}else if(cellvalue == '2'){
      	            			value = "金卡";
      	            		}else if(cellvalue == '3'){
      	            			value = "钻石卡";
      	            		}
                  			return value;
            	   		}	   
                   },
                   {name: 'credit',index:'credit',width:'8%',align:'center',hidden: false,sortable:false},
                   {name: 'userName',index:'userName',width:'5%',align:'center',hidden: false,sortable:false},
                   {name: 'crateTime',index:'crateTime',width:'11%',align:'center',hidden: false,sortable:false},
                   {name:'operate',index:'operate', width:'26%',align:'left',sortable:false}],
        mtype:"POST",
        postData:{c:"${client.c}",name:"${client.name}",phone:"${client.phone}",cardNum:"${client.cardNum}",permit:"${client.permit}",cardStatus:"${client.cardStatus}",userId:"${client.userId}",startTime:"${client.startTime}",endTime:"${client.endTime}",areaId:$('#areaId').val()},
        rowNum:10,    
        page:"${client.page}",
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        shrinkToFit:true,
        viewrecords: true,
        gridComplete: function(){
    		var ids = jQuery("#clientList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var rowdata = jQuery("#clientList").jqGrid('getRowData',id);
    			var content = "";
				<c:if test="${!empty maintain_detail}">
    		    <security:authorize ifAnyGranted="${maintain_detail.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_detail.index}' title='${maintain_detail.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_detail.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    		    if(rowdata.permit == '通过'){//'1'
     	    		if(rowdata.cardStatus == '未发卡'){//未发0->发卡
     	    			<c:if test="${!empty maintain_sendcard}">
     	    		    <security:authorize ifAnyGranted="${maintain_sendcard.code}">	
     	    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_sendcard.index}' title='${maintain_sendcard.name}' >";
     	    			content += "<img src='${base}/${common}/${style}/images/icon/information_center.gif' weight='18' height='18' border='0' align='absmiddle'/>${maintain_sendcard.name}";
     	    			content += "</a>";
     	    		    </security:authorize>
     	    		    </c:if>
            		}else if(rowdata.cardStatus == '未激活'){//未激活1->未激活/拒绝
            			<c:if test="${!empty maintain_activation}">
     	    		    <security:authorize ifAnyGranted="${maintain_activation.code}">	
     	    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_activation.index}' title='${maintain_activation.name}' >";
     	    			content += "<img src='${base}/${common}/${style}/images/icon/information_center.gif' weight='18' height='18' border='0' align='absmiddle'/>${maintain_activation.name}";
     	    			content += "</a>";
     	    		    </security:authorize>
     	    		    </c:if>
     	    		   <c:if test="${!empty maintain_refuse}">
    	    		    <security:authorize ifAnyGranted="${maintain_refuse.code}">	
    	    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_refuse.index}' title='${maintain_refuse.name}' >";
    	    			content += "<img src='${base}/${common}/${style}/images/icon/outage.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_refuse.name}";
    	    			content += "</a>";
    	    		    </security:authorize>
    	    		    </c:if>
            		}else if(rowdata.cardStatus == '已激活'){//已激活2->消费/拒绝
            			<c:if test="${!empty maintain_consume}">
    	    		    <security:authorize ifAnyGranted="${maintain_consume.code}">	
    	    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_consume.index}' title='${maintain_consume.name}' >";
    	    			content += "<img src='${base}/${common}/${style}/images/icon/cc_salon.gif' weight='18' height='18' border='0' align='absmiddle'/>${maintain_consume.name}";
    	    			content += "</a>";
    	    		    </security:authorize>
    	    		    </c:if>
    	    		    <c:if test="${!empty maintain_refuse}">
    	    		    <security:authorize ifAnyGranted="${maintain_refuse.code}">	
    	    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_refuse.index}' title='${maintain_refuse.name}' >";
    	    			content += "<img src='${base}/${common}/${style}/images/icon/outage.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_refuse.name}";
    	    			content += "</a>";
    	    		    </security:authorize>
    	    		    </c:if>
            		}else if(rowdata.cardStatus == '卡拒绝'){//拒绝3->重新激活
            			<c:if test="${!empty maintain_activation}">
    	    		    <security:authorize ifAnyGranted="${maintain_activation.code}">	
    	    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_activation.index}' title='重新激活' >";
    	    			content += "<img src='${base}/${common}/${style}/images/icon/information_center.gif' weight='18' height='18' border='0' align='absmiddle'/>重新激活";
    	    			content += "</a>";
    	    		    </security:authorize>
    	    		    </c:if>
            		}
     	    	}else{
     	    		<c:if test="${!empty maintain_check}">
	    		    <security:authorize ifAnyGranted="${maintain_check.code}">	
	    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_check.index}' title='${maintain_check.name}' >";
	    			content += "<img src='${base}/${common}/${style}/images/icon/information_center.gif' weight='18' height='18' border='0' align='absmiddle'/>${maintain_check.name}";
	    			content += "</a>";
	    		    </security:authorize>
	    		    </c:if>
	    		    <c:if test="${!empty maintain_refuse}">
	    		    <security:authorize ifAnyGranted="${maintain_refuse.code}">	
	    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_refuse.index}' title='${maintain_refuse.name}' >";
	    			content += "<img src='${base}/${common}/${style}/images/icon/outage.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_refuse.name}";
	    			content += "</a>";
	    		    </security:authorize>
	    		    </c:if>
     	    	}
    		    
    		    <c:if test="${!empty maintain_delete}">
    		    <security:authorize ifAnyGranted="${maintain_delete.code}">
   		    	content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_delete.index}' title='${maintain_delete.name}' >";
   		    	content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_delete.name}";
   		    	content += "</a>";
    		    </security:authorize>
    		    </c:if>
    			<c:if test="${!empty maintain_modify}">
    		    <security:authorize ifAnyGranted="${maintain_modify.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_modify.index}' title='${maintain_modify.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_modify.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>  
    		    
    			jQuery("#clientList").jqGrid('setRowData',ids[i],{operate:content});
    		}	
    	},
    	loadComplete:function(){
    	    $(".shortcut_${maintain_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#clientList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${maintain_delete.controller}.json?c=${maintain_delete.code}&id="+rowid+"&status=9";
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								$("#clientList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });   
    	    
    	    //审核
    	    $(".shortcut_${maintain_check.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#clientList").jqGrid("getRowData",rowid);
    	    	var url = "${base}${maintain_check.controller}.json?c=${maintain_check.code}&id="+rowid+"&permit=1";
    			window.parent.Boxy.confirm("您确定要通过审核吗?",  function(){
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								$("#clientList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });
    	    //发卡
    	    $(".shortcut_${maintain_sendcard.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#clientList").jqGrid("getRowData",rowid);
    	    	window.location.href = "${base}${maintain_sendcard.controller}?c=${maintain_sendcard.code}&id="+rowid;
    	    });
    	    //激活卡
    	    $(".shortcut_${maintain_activation.index}").click(function(){
    	     	var rowid = $(this).attr("id");
    	    	var data = jQuery("#clientList").jqGrid("getRowData",rowid);
    	    	var msg = '您确定激活 ( '+data.mame+' : '+data.cardNum+' ) 这张卡吗？';
    	    	/* if(status == 3){
    	    		msg = '该用户已被拒绝,您确定重新激活( '+name+' ： '+bankNum+' ) 这张卡吗？';
    	    	} */
    	    	var url = "${base}${maintain_activation.controller}.json?c=${maintain_activation.code}&id="+rowid+"&cardStatus=2";
    			window.parent.Boxy.confirm(msg,  function(){
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								$("#clientList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });
    	    //拒绝
    	    $(".shortcut_${maintain_refuse.index}").click(function(){
    	     	var rowid = $(this).attr("id");
    	    	var data = jQuery("#clientList").jqGrid("getRowData",rowid);
    	    	var msg = '';
    	    	var url = "";
    	    	if(data.permit != '2'){
    	    		msg = '您确定拒绝此用户吗？';
    	    		url = "${base}${maintain_refuse.controller}.json?c=${maintain_refuse.code}&id="+rowid+"&permit=2";
    	    	}
    	    	if(data.cardStatus == '已激活'){
    	    		msg = '您确定拒绝 ( '+data.mame+' : '+data.cardNum+' ) 这张卡吗？'
    	    		url = "${base}${maintain_refuse.controller}.json?c=${maintain_refuse.code}&id="+rowid+"&cardStatus=3";
    	    	}
    			window.parent.Boxy.confirm(msg,  function(){
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								$("#clientList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });
    	    //消费
    	    $(".shortcut_${maintain_consume.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#clientList").jqGrid("getRowData",rowid);
    	    	window.location.href = "${base}${maintain_consume.controller}?c=${maintain_consume.code}&clientId="+rowid;
    	    });
    	    $(".shortcut_${maintain_detail.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${maintain_detail.controller}?c=${maintain_detail.code}&id="+rowid;
    	    });
			$(".shortcut_${maintain_modify.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${maintain_modify.controller}?c=${maintain_modify.code}&id="+rowid;
    	    });
    	},
        caption:'<msg:message code="client.list"/>',
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
	$("#t_clientList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${maintain_create.index}","#t_clientList").click(function(){
    	window.location.href = "${base}${maintain_create.controller}?c=${maintain_create.code}";
    });    
    </security:authorize>
    </c:if>
    
    $.ajax({
		url:"orgMap.json",
		type:'post',
		timeout:'60000',
		dataType:'json',
		success:function(jsonData,textStatus){
			if (textStatus == "success"){
				area =jsonData.area;
				province=jsonData.province;
				city=jsonData.city;
				District=jsonData.district;	
			}
		}
	});
    
    $("#selCompany").change(function () {
        var selValue = $(this).val(); 
        if (selValue == 0) {
	       //$('#areaId').val("");
           $("#selArea").empty();
		 	   $("#selProvince").empty();
           $("#selCity").empty();
		 	   $("#selDistrict").empty();
        } else {  
        	//$('#areaId').val(selValue);
			$("#selArea").append("<option value='0'>请选择</option>");
            $.each(area, function (k, p) { 
                 if (p.parentId == selValue) {
                     var option = "<option value='" + p.id + "'>" + p.name + "</option>";
                     $("#selArea").append(option);
                 }
            });
        }
    });		
	$("#selArea").change(function () {
        var selValue = $(this).val();
        $('#areaId').val(selValue);
        $("#selProvince").empty();
        $("#selCity").empty();
	 	$("#selDistrict").empty();
		$("#selProvince").append("<option value='0'>请选择</option>");
        $.each(province, function (k, p) { 
             if (p.parentId == selValue) {
                 var option = "<option value='" + p.id + "'>" + p.name + "</option>";
                 $("#selProvince").append(option);
             }
         });
    }); 		
    $("#selProvince").change(function () {
          var selValue = $(this).val();
          $('#areaId').val(selValue);
          $("#selCity").empty();
	 	  $("#selDistrict").empty();
		  $("#selCity").append("<option value='0'>请选择</option>");
          $.each(city, function (k, p) { 
               if (p.parentId == selValue) {
                   var option = "<option value='" + p.id + "'>" + p.name + "</option>";
                   $("#selCity").append(option);
               }
           });
     });            
     $("#selCity").change(function () {
          var selValue = $(this).val();
          $('#areaId').val(selValue);
          $("#selDistrict").empty(); 
		  $("#selDistrict").append("<option value='0'>请选择</option>");
          $.each(District, function (k, p) {
          if (p.parentId == selValue) {
              var option = "<option value='" + p.id + "'>" + p.name + "</option>";
              $("#selDistrict").append(option);
           }
         }); 
     });
	 $("#selArea option:first").remove();
	 $("#selProvince option:first").remove();
	 $("#selCity option:first").remove();
	 $("#selDistrict option:first").remove();
});

function sendCard(id, cardNum, cardType){
	window.location.href = "${base}${maintain_sendcard.controller}?c=${maintain_sendcard.code}&id="+id+"&cardNum="+cardNum +"&cardType="+cardType;
}

function submitForm(){
	$('#financialPlanner').val($.trim($('#financialPlanner').val()));
	$('#registPhone').val($.trim($('#registPhone').val()));
	document.searchForm.submit();
}

</script>
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
			<form:form method="post" action="list" commandName="client" name="searchForm" id="searchForm">
				<input type="hidden" name="c" value="${client.c}"/>
				<table cellpadding="0" cellspacing="1" border="0" width="100%" class="search">
					<tr>
						<td align="right" width="10%" class="search_info"><msg:message code='client.name'/><msg:message code='system.common.sign.colon'/></td>
						<td align="left" width="18%" class="search_lable">
							<form:input path="name" />
						</td>
						<td align="right" width="10%" class="search_info"><msg:message code='client.phone'/><msg:message code="system.common.sign.colon"/></td>
						<td align="left" width="18%" class="search_lable">
							<form:input path="phone"/>
						</td>
						<td align="right" width="10%" class="search_info"><msg:message code='client.cardNum'/><msg:message code="system.common.sign.colon"/></td>
						<td align="left" width="18%" class="search_lable">
							<form:input path="cardNum"/>
						</td>
						<td rowspan="3" class="button_area" align="center" width="10%">
							<input type="submit" class="btn2" id="select" value="<msg:message code="button.search"/>">
						</td>
					</tr>	
					<tr>
						<td align="right" width="10%" class="search_info"><msg:message code='client.clientStatus'/><msg:message code="system.common.sign.colon"/></td>
						<td align="left" width="18%" class="search_lable">
							<select name="permit" id="permit">
						      <option value="" selected="">全部</option>
						      <option value="0">未审</option>
						      <option value="1">通过</option>
						      <option value="2">拒绝</option>
						    </select>
						</td>
						<td align="right" width="10%" class="search_info"><msg:message code='client.cardStatus'/><msg:message code='system.common.sign.colon'/></td>
						<td align="left" width="18%" class="search_lable">
							<select name="cardStatus" id="cardStatus">
						      <option value="" selected="">全部</option>
						      <option value="0">未发卡</option>
						      <option value="1">未激活</option>
						      <option value="2">已激活</option>
						      <option value="3">卡拒绝</option>
						    </select>
						</td>
						<td align="right" width="10%" class="search_info"><msg:message code='client.userName'/><msg:message code='system.common.sign.colon'/></td>
						<td align="left" width="18%" class="search_lable">
							<form:select path="userId">
								<form:option value=""><msg:message code="please.select"/></form:option>
								<form:options items="${userList}" itemValue="id" itemLabel="name"/>
							</form:select>
						</td>
					</tr>	
					<tr>
						<td align="right" width="10%" class="search_info"><msg:message code='client.createTime'/><msg:message code='system.common.sign.colon'/></td>
						<td align="left" width="18%" class="search_lable">
							<input type="text"  id="startTime"  name="startTime"  value="${message.startTime}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})" class="Wdate" style="width:88px;" readonly="readonly" >
							至
							<input type="text" id="endTime" name="endTime"  value="${message.endTime}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})" class="Wdate" style="width:88px;" readonly="readonly" >
						</td>
						<td align="right" width="10%" class="search_info"><msg:message code='client.area'/><msg:message code="system.common.sign.colon"/></td>
						<td colspan="3" align="left" width="18%" class="search_lable">
							<input type="hidden" name="areaId" id="areaId" value="">
							<select id="selCompany" name="selCompany" style="width: auto;">
						    	<option value="0">请选择</option>
							    <option value="1">总公司</option>
					      	</select>
					    	<select id="selArea" style="width: auto;">
						        <option selected="" value="0"></option>
						    </select>
						    <select id="selProvince" style="width: auto;">
						        <option selected="" value="0"></option>
						    </select>
						    <select id="selCity" style="width: auto;">
						        <option selected="" value="0"></option>
						    </select>
						    <select id="selDistrict" style="width: auto;">
						        <option selected="" value="0"></option>
						    </select>
						</td>
					</tr>			
				</table>
			</form:form>
		</td>
	</tr>
	<tr>
		<td>
			<table id="clientList"><tr><td>&nbsp;</td></tr></table>
			<div id="pagered"></div>
		</td>
	</tr>
</table>
</body>
</html>