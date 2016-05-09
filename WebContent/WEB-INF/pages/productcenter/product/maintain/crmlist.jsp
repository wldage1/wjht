<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
//国际化文件
var loading="<msg:message code='product.loading'/>";
var synchronousTrustCodeloading="<msg:message code='product.synchronousTrustCode.loading'/>";
$(document).ready(function(){
    jQuery("#prdList").jqGrid({
        treeGridModel: 'adjacency',
        url: 'crmgrid.json',
        ExpandColumn: 'name',
        ExpandColClick: true,
        datatype: 'json',
        colNames: ["<msg:message code='product.prdname'/>",
        	"<msg:message code='product.prdSerialNo'/>",
        	"<msg:message code='product.prdtype'/>",
        	"<msg:message code='product.sellstatus'/>",
        	"<msg:message code='product.buystartingpoint'/>",
        	"<msg:message code='product.earningsbyyear'/>",
        	"<msg:message code='product.deadline'/>",
        	"<msg:message code='product.pad7'/>",
        	"<msg:message code='product.addedflag'/>",
        	"<msg:message code='product.addedflag'/>",
        	"<msg:message code='product.updflag'/>",
        	"<msg:message code='info.operate'/>"
        	],
        colModel: [{name: 'name',index: 'name',width:'30%',align:'left',hidden: false,sortable: false,formatter:formatData}, 
        		   {name: 'prdserialno',index: 'prdserialno',width:'10%',align:'center',hidden: false,sortable: false}, 
                   {name: 'type',index: 'type',width:'10%',align:'center',hidden: false,sortable: false}, 
                   {name: 'sellstatus',index: 'sellstatus',width:'10%',align:'center',hidden: false,sortable: false}, 
                   {name: 'startpoint',index: 'startpoint',width:'10%',align:'center',hidden: false,sortable: false}, 
                   {name: 'earnyear',index: 'earnyear',width:'10%',align:'center',hidden: false,sortable: false}, 
                   {name: 'deadline',index: 'deadline',width:'10%',align:'center',hidden: false,sortable: false}, 
                   {name: 'pad7',index: 'pad7',width:'10%',align:'center',hidden: false,sortable: false}, 
                   {name: 'addedflag',index: 'addedflag',width:'10%',align:'center',hidden: false,sortable: false}, 
                   {name: 'added',index: 'added',width:'10%',align:'center',hidden: true,sortable: false}, 
                   {name: 'updflag',index: 'updflag',width:'10%',align:'center',hidden: true,sortable: false}, 
                   {name:'act',index:'act', width:'15%',align:'center',sortable:false}],
        rowNum:10,  
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        viewrecords: true,
        caption:'<msg:message code="product.crmlist"/>',
        toolbar: [true,"top"],
        multiselect: true,
        mtype:"POST",
        postData:{c:"${crmProduct.c}",productName:"${crmProduct.productName}",productType:"${crmProduct.productType}",purchasePhase:"${crmProduct.purchasePhase}",purchaseStartPoint:"${crmProduct.purchaseStartPoint}",productIncome:"${crmProduct.productIncome}",productTerm:"${crmProduct.productTerm}"},
    	gridComplete: function(){
    		var ids = jQuery("#prdList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var rowdata = jQuery("#prdList").jqGrid('getRowData',id);
    			if(rowdata.updflag == 1){
    				document.getElementById(id).style.color="#FF2400";   
    			}
    			var content = "";
    			<c:if test="${!empty prd_crmdetails}">
    		    <security:authorize ifAnyGranted="${prd_crmdetails.code}">	
    		    content += "<a href='#' id='" + id + "' class='shortcut_${prd_crmdetails.index}' title='${prd_crmdetails.name}'>";
	    		content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${prd_crmdetails.name}";
	    		content += "</a>";
    		    </security:authorize>
    		    </c:if>
    			<c:if test="${!empty prd_added}">
    		    <security:authorize ifAnyGranted="${prd_added.code}">	
    		    if(rowdata.added==0){
	    			content += "<a href='#' id='" + id + "' class='shortcut_${prd_added.index}' title='${prd_added.name}'>";
	    			content += "<img src='${base}/${common}/${style}/images/icon/shangjia.png' weight='18' height='18' border='0' align='absmiddle'/>${prd_added.name}";
	    			content += "</a>";
    			}else{
    				content += "<img src='${base}/${common}/${style}/images/icon/shangjia.png' weight='18' height='18' border='0' align='absmiddle'/><font color='#EFEBDE' title='${prd_added.name}'>${prd_added.name}</font>";
    				var currentCheckbox = document.getElementById("jqg_prdList_"+id);
	    			currentCheckbox.parentNode.removeChild(currentCheckbox);
    			}
    		    </security:authorize>
    		    </c:if>
    		    <c:if test="${!empty prd_upd}">
    		    	if(rowdata.updflag == 1){
		    		    <security:authorize ifAnyGranted="${prd_upd.code}">	
		    			content += "<a href='#' id='" + id + "' class='shortcut_${prd_upd.index}' title='${prd_upd.name}'>";
		    			content += "<img src='${base}/${common}/${style}/images/icon/EXPort.png' weight='18' height='18' border='0' align='absmiddle'/>${prd_upd.name}";
		    			content += "</a>";
	    				</security:authorize>
	    			}else{
	    				content += "<img src='${base}/${common}/${style}/images/icon/audit.png' weight='18' height='18' border='0' align='absmiddle'/><font color='#EFEBDE' title='<msg:message code='product.normal'/>'><msg:message code='product.normal'/></font>";
	    			}
    		    </c:if>
    		    jQuery("#prdList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
 	loadComplete:function(){
    		$(".shortcut_${prd_crmdetails.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${prd_crmdetails.controller}?c=${prd_crmdetails.code}&id="+rowid;
    	    });
    		$(".shortcut_${prd_added.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${prd_added.controller}?c=${prd_added.code}&id="+rowid;
    	    });
    		 $(".shortcut_${prd_upd.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#prdList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='product.updatePrd'/>",  function(){
    				var url = "${base}${prd_upd.controller}.json?c=${prd_upd.code}&id="+rowid;
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
        caption:'<msg:message code="product.crmlist"/>',
        toolbar: [true,"top"]
    });

    <c:if test="${!empty prd_addedby}">
    <security:authorize ifAnyGranted="${prd_addedby.code}">	 
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${prd_addedby.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/shangjia.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${prd_addedby.name}");
	$("#t_prdList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${prd_addedby.index}","#t_prdList").click(function(){
    	var selrow = jQuery("#prdList").jqGrid("getGridParam","selarrrow");
		if(selrow!=null&&selrow!=""){
			window.parent.Boxy.confirm("<msg:message code='product.arrayAdded'/>",  function(){
				var dialogContent = "<div width='200' height='100' >"+loading+"</div>";
				top.promptBox = new window.parent.Boxy(dialogContent, 
						{title: "<msg:message code='info.prompt'/>",closeText:"",modal:true});
				var url = "${base}${prd_addedby.controller}.json?c=${prd_addedby.code}&ids="+selrow;
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								top.promptBox.hide();
								if(jsonData.CRMProduct.pad10 !=""){
									window.parent.Boxy.alert("<msg:message code='product.choosePrd'/>"+jsonData.CRMProduct.pad10+"<msg:message code='product.addedSuccess'/>", null, {title: "<msg:message code='info.prompt'/>"});
								}else{
									window.parent.Boxy.alert("<msg:message code='product.typeNull'/>", null, {title: "<msg:message code='info.prompt'/>"});
								}
								$("#prdList").trigger("reloadGrid");    
							}else{
								window.parent.Boxy.alert("<msg:message code='product.addedFail'/>", null, {title: "<msg:message code='info.prompt'/>"});
							}
						}
					}
				});
			}, {title: "<msg:message code='info.prompt'/>"});
		}
		else{
    		window.parent.Boxy.alert("<msg:message code='product.emptyAdded'/>", null, {title: "<msg:message code='info.prompt'/>"});
   		}    	
    });    
    </security:authorize>
    </c:if>
    
   	<c:if test="${!empty prd_pulling}">
    <security:authorize ifAnyGranted="${prd_pulling.code}">	 
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${prd_pulling.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/shangjia.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${prd_pulling.name}");
	$("#t_prdList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${prd_pulling.index}","#t_prdList").click(function(){
			window.parent.Boxy.confirm("<msg:message code='product.Pulling'/>",function(){
				var dialogContent = "<div width='200' height='100' >"+loading+"</div>";
				top.promptBox = new window.parent.Boxy(dialogContent, 
						{title: "<msg:message code='info.prompt'/>",closeText:"",modal:true});
				var url = "${base}${prd_pulling.controller}.json?c=${prd_pulling.code}";
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								top.promptBox.hide();
								window.parent.Boxy.alert("<msg:message code='product.PullingSuccess'/>", null, {title: "<msg:message code='info.prompt'/>"});
								$("#prdList").trigger("reloadGrid");    
							}else{
								top.promptBox.hide();
								window.parent.Boxy.alert("<msg:message code='product.PullingFail'/>", null, {title: "<msg:message code='info.prompt'/>"});
								$("#prdList").trigger("reloadGrid");    
							}
						}
					}
				});
			});	
    });    
    </security:authorize>
    </c:if>
    <c:if test="${!empty prd_synchronousTrustCode}">
    <security:authorize ifAnyGranted="${prd_synchronousTrustCode.code}">	 
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${prd_synchronousTrustCode.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/sync.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${prd_synchronousTrustCode.name}");
	$("#t_prdList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${prd_synchronousTrustCode.index}","#t_prdList").click(function(){
			window.parent.Boxy.confirm("<msg:message code='product.synchronousTrustCode'/>",function(){
				var dialogContent = "<div width='200' height='100' >"+synchronousTrustCodeloading+"</div>";
				top.promptBox = new window.parent.Boxy(dialogContent, 
						{title: "<msg:message code='info.prompt'/>",closeText:"",modal:true});
				var url = "${base}${prd_synchronousTrustCode.controller}.json?c=${prd_synchronousTrustCode.code}";
				$.ajax({
					url:url,
					type:'post',
					timeout:'600000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								top.promptBox.hide();
								window.parent.Boxy.alert("<msg:message code='product.synchronousTrustCode.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
							}else{
								top.promptBox.hide();
								window.parent.Boxy.alert("<msg:message code='product.synchronousTrustCode.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
							}
						}else{
							top.promptBox.hide();
							window.parent.Boxy.alert("<msg:message code='product.synchronousTrustCode.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
						}
					}
				});
			});	
    });    
    </security:authorize>
    </c:if>
 });

function formSubmit(){
	$("#productName").val($.trim($("#productName").val()));
	$("#purchasePhase").val($.trim($("#purchasePhase").val()));
	$("#productIncome").val($.trim($("#productIncome").val()));
	$("#productTerm").val($.trim($("#productTerm").val()));
}
</script>
	</head>
	<body class="skinMain">
		<table width="100%" border="0" cellspacing="1" cellpadding="0"
			class="skinMain">
			<tr>
				<td width="100%">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="skinNav">
						<tr>
							<th width="2%">
								<img src="${base}/${common}/${style}/images/nav/bg_07.gif"
									width="20" height="20" />
							</th>
							<th width="98%">
								<msg:message code="navigation.title" />
								&nbsp;${navigation}
							</th>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="center" width="100%">
					<form:form method="post" action="crmlist" commandName="crmProduct" onsubmit="return formSubmit();">
						<input type="hidden" name="c" value="${crmProduct.c}" />
						${crmProduct.pad10}
						<table cellpadding="0" cellspacing="1" border="0" width="100%" class="search">
							<tr>
								<td align="right" width="10%" class="search_info">
									<msg:message code='product.prdname' /><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="20%" class="search_lable">
									<form:input path="productName" />
								</td>
								<td align="right" width="10%" class="search_info">
									<msg:message code='product.prdtype' /><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="20%" class="search_lable">
									<form:select path="productType">
										<form:option value="">--<msg:message code="please.select"/>--</form:option>
										<form:options items="${productTypeList}" itemValue="name" itemLabel="name"/>
									</form:select>
								</td>
								<td align="right" width="10%" class="search_info">
									<msg:message code='product.sellstatus' /><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="15%" class="search_lable">
									<form:input path="purchasePhase" />
								</td>
								<td rowspan="2" width="25%" class="button_area" align="center">
									<input type="submit" class="btn2" id="select" value="<msg:message code='button.search' />">
								</td>
							</tr>	
							<tr>
								<td align="right" width="10%" class="search_info">
									<msg:message code='product.earningsbyyear' /><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="20%" class="search_lable">
									<form:input path="productIncome" />
								</td>
								<td align="right" width="10%" class="search_info">
									<msg:message code='product.deadline' /><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="15%" class="search_lable" colspan="3">
									<form:input path="productTerm" onkeyup="this.value=this.value.replace(/\D/g,'')" />
								</td>
							</tr>
						</table>
					</form:form>
				</td>
			</tr>
			<tr>
				<td>
					<table id="prdList">
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
					</table>
					<div id="pagered"></div>
				</td>
			</tr>
		</table>
	</body>
</html>