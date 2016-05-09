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
        url: 'statisticGrid.json',
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
                   {name: 'financialPlanner',index:'financialPlanner',width:'12%',align:'center',hidden: false,sortable:false},
                   {name: 'mobilePhone',index:'mobilePhone',width:'24%',align:'center',hidden: false,sortable:false},
                   {name: 'registPhone',index:'registPhone',width:'9%',align:'center',hidden: false,sortable:false},
                   {name: 'memberLevel',index:'memberLevel',width:'8%',align:'center',hidden: false,sortable:false},
                   {name: 'source',index:'source',width:'7%',align:'center',hidden: false,sortable:false},
                   {name: 'crateTime',index:'crateTime',width:'13%',align:'center',hidden: false,sortable:false},
                   {name:'operate',index:'operate', width:'6%',align:'center',sortable:false}],
        mtype:"POST",
        postData:{c:"${member.c}",startTime:"${member.startTime}",endTime:"${member.endTime}",resource:"${member.resource}",sqlCondition:"${member.sqlCondition}"},
        rowNum:10,    
        page:"${member.page}",
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        shrinkToFit:true,
        viewrecords: true,
        multiselect: false,
        gridComplete: function(){
    		var ids = jQuery("#memberList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var rowdata = jQuery("#memberList").jqGrid('getRowData',id);
    			var content = "";
				<c:if test="${!empty maintain_statistic_detail}">
    		    <security:authorize ifAnyGranted="${maintain_statistic_detail.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${maintain_statistic_detail.index}' title='${maintain_statistic_detail.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${maintain_statistic_detail.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    			jQuery("#memberList").jqGrid('setRowData',ids[i],{operate:content});
    		}	
    	},
    	loadComplete:function(){
			$('#newTotal').text(jQuery("#memberList").getGridParam('userData').newTotal);
			$('#total').text(jQuery("#memberList").getGridParam("records"));
			
			$(".shortcut_${maintain_statistic_detail.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${maintain_statistic_detail.controller}?c=${maintain_statistic_detail.code}&id="+rowid+"&sqlCondition=${member.sqlCondition}";
    	    });
    	},
        caption:'<msg:message code="member.statistic"/>',
        toolbar: [true,"top"]
    });
  	
    var $ea = "<msg:message code='member.statistic.total'/>:<font color='red' id='newTotal'></font>&nbsp;&nbsp;<msg:message code='member.statistic.curcount'/>:<font color='red' id='total'></font>";
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));

	$('#startTime').val('${member.startTime}');
	$('#endTime').val('${member.endTime}');
	$('#source').val('${member.resource}');
   
});

function submitForm(){
	var $params = "ME.IsNewClient = 1";
	var $startTime = $.trim($('#startTime').val());
	var $endTime = $.trim($('#endTime').val());
	var $source = $.trim($('#source').val());

	
	if($startTime && $startTime != ''){
		$params +=  " and ME.RegistrationTime >= '"+$startTime+"'";
	}
	if($endTime && $endTime != ''){
		$params +=  " and ME.RegistrationTime <= '"+$endTime+"'";
	}
	if($source && $source != ''){
		$params +=  " and ME.Source = '"+$source+"'";
	}
	jQuery("#memberList").jqGrid("setGridParam",{postData:{c:"${member.c}",startTime:$startTime,endTime:$endTime,resource:$source,sqlCondition:$params}});
	jQuery("#memberList").trigger("reloadGrid");
	

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
						<td align="right" width="10%" class="search_info"><msg:message code='member.statistic.time'/><msg:message code='system.common.sign.colon'/></td>
						<td align="left" width="50%" class="search_lable">
							<input type="text"  id="startTime"  name="startTime"  value="${message.startTime}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2020-10-01\'}'})" class="Wdate" style="width:200px;" readonly="readonly" >
							<msg:message code="message.zhi" />
							<input type="text" id="endTime" name="endTime"  value="${message.endTime}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'2020-10-01'})" class="Wdate" style="width:200px;" readonly="readonly" >
						</td>
						<td align="right" width="10%" class="search_info"><msg:message code='member.source'/><msg:message code="system.common.sign.colon"/></td>
						<td align="left" width="20%" class="search_lable">
							<form:select path="source" >
								<form:option value="">--<msg:message code='please.select'/>--</form:option>
								<form:options items="${dataSourceList}" itemValue="value" itemLabel="name"/>
							</form:select>
						</td>
						<td width="10%" class="button_area" align="center">
							<input type=button class="btn2" id="select" value="<msg:message code='member.button.search'/>" onclick="submitForm();">
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