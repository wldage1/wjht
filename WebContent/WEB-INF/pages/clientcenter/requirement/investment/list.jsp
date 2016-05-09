<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#investmentList").jqGrid({
        treeGridModel: 'adjacency',
        ExpandColumn: 'name',
        ExpandColClick: true,
        url: 'grid.json?state=0',
        datatype: 'json',
        colNames: ["<msg:message code='investment.name'/>","<msg:message code='investment.gender'/>",
                   "<msg:message code='investment.mobilePhone'/>","<msg:message code='investment.investScale'/>",
                   "<msg:message code='investment.productId'/>","<msg:message code='info.operate'/>"],
        colModel: [{name: 'name',index:'name',width:'20%',align:'center',sortable:false},
                   {name: 'gender',index:'gender',width:'15%',align:'center',sortable:false},
                   {name: 'mobilePhone',index:'mobilePhone',width:'20%',align:'center',sortable:false},
                   {name: 'investScale',index:'investScale',width:'15%',align:'center',sortable:false},
                   {name: 'productId',index:'productId',width:'20%',align:'center',sortable:false},
                   {name: 'act',index:'act', width:'10%',align:'center',sortable:false,title:false}],
        rowNum:10,     
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        mtype:"POST",
        postData:{minInvestScale:"${investment.minInvestScale}",maxInvestScale:"${investment.maxInvestScale}",productId:"${investment.productId}",c:"${investment.c}"}, 
        autowidth: true,
        viewrecords: true,
        multiselect: true,
    	gridComplete: function(){
    		var ids = jQuery("#investmentList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			<c:if test="${!empty investment_detail}">
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${investment_detail.index}'";
    			content += "title='${investment_detail.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${investment_detail.name}";
    			content += "</a>";
    			</c:if> 
    			<c:if test="${!empty investment_delete}">
    		    <security:authorize ifAnyGranted="${investment_delete.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${investment_delete.index}'";
    			content += "title='${investment_delete.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${investment_delete.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    		    jQuery("#investmentList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}
    	},
    	loadComplete:function(){
    	    $(".shortcut_${investment_detail.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${investment_detail.controller}?c=${investment_detail.code}&id="+rowid;
    	    });
    	    $(".shortcut_${investment_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#investmentList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${investment_delete.controller}.json?c=${investment_delete.code}&state=1&ids="+rowid+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(data.name));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								jQuery("#investmentList").jqGrid("delRowData",rowid);
    								$("#investmentList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });  		
    	},
        caption:'<msg:message code="investment.list"/>',
        toolbar: [true,"top"]
    });
    
    <c:if test="${!empty investment_delete}">
    <security:authorize ifAnyGranted="${investment_delete.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${investment_delete.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${investment_delete.name}");
	$("#t_investmentList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${investment_delete.index}","#t_investmentList").click(function(){
    	var selrow = jQuery("#investmentList").jqGrid("getGridParam","selarrrow");
    	var names = "";
    	for(var i=0;i < selrow.length;i++){
    		var id = selrow[i];
    		var data = jQuery("#investmentList").jqGrid("getRowData",id);
    		names += data.name;
    		if(i!= selrow.length-1)
    			names += ",";
    	}
		if (selrow != null && selrow != ""){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "${base}${investment_delete.controller}.json?c=${investment_delete.code}&state=1&ids="+selrow+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(names));
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								jQuery("#investmentList").jqGrid("delRowData",selrow);
								$("#investmentList").trigger("reloadGrid");    
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

    <c:if test="${!empty investment_recycle}">
    <security:authorize ifAnyGranted="${investment_recycle.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${investment_recycle.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/Recycler.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${investment_recycle.name}");
	$("#t_investmentList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${investment_recycle.index}","#t_investmentList").click(function(){
    	window.location.href = "${base}${investment_recycle.controller}?c=${investment_recycle.code}";
    });    
    </security:authorize>
    </c:if>
});

function checkForm(){
	var minInvestScale = $("#minInvestScale").val();
	var maxInvestScale = $("#maxInvestScale").val();
	if(isNaN(minInvestScale)){
		window.parent.Boxy.alert('<msg:message code="requirementProductSort.investScale" />', null, {title: "<msg:message code='info.prompt'/>"});
		$("#minInvestScale").focus();
		return false;
	}
	if(isNaN(maxInvestScale)){
		window.parent.Boxy.alert('<msg:message code="requirementProductSort.investScale" />', null, {title: "<msg:message code='info.prompt'/>"});
		$("#maxInvestScale").focus();
		return false;
	}
}

</script>
</head>
<body class="skinMain">
<form:form method="post" action="list" commandName="investment" onsubmit="return checkForm();">
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
					<input type="hidden" name="c" value="${investment.c}" />
						<table cellpadding="0" cellspacing="0" border="1" width="100%"  class="skinLayout">
							<tr>
								<td align="right" width="10%" class="search_info">
								<msg:message code="investment.investScaleRegion"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="18%" class="search_lable">
								<input type="text" name="minInvestScale" id="minInvestScale" value="${investment.minInvestScale}"  style="width:90px;" >
								<msg:message code="consult.and"/>
								<input type="text" name="maxInvestScale" id="maxInvestScale" value="${investment.maxInvestScale}"  style="width:90px;" >
								</td>
								<td align="right" width="10%" class="search_info">
								<msg:message code="investment.productId"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="10%" class="search_lable">
								<form:select path="productId" >
									<form:option value="">--<msg:message code='please.select'/>--</form:option>
									<form:options items="${productTypeList}" itemValue="id" itemLabel="name"/>
								</form:select>
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
		<table id="investmentList"><tr><td>&nbsp;</td></tr></table>
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
