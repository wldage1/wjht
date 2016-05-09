<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.sw.core.common.Constant" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="${base}/${common}/${style}/css/boxy.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${base}/${common}/js/boxy/jquery.boxy.js"></script>
<script type="text/javascript">
var generateBoxy;
$(document).ready(function(){
    jQuery("#generateAccountList").jqGrid({
        url: 'grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='generateAccount.crmId'/>",
				   "<msg:message code='generateAccount.mobilePhone'/>", 
                   "<msg:message code='generateAccount.userName'/>",
                   "<msg:message code='generateAccount.generateTime'/>"
                   //,"<msg:message code='member.operate'/>"
                   ],
        colModel: [{name: 'crmId',index:'crmId',width:'20%',align:'center',hidden: false,sortable:false},
				   {name: 'mobilePhone',index:'mobilePhone',width:'20%',align:'center',hidden: false,sortable:false},
                   {name: 'userName',index:'userName',width:'20%',align:'center',hidden: false,sortable:false},
                   {name: 'generateTime',index:'generateTime',width:'20%',align:'center',hidden: false,sortable:false}
                   //,{name:'operate',index:'operate', width:'20%',align:'center',sortable:false}
                   ],
        mtype:"POST",
        postData:{c:"${generateAccount.c}",crmId:"${generateAccount.crmId}",mobilePhone:"${generateAccount.mobilePhone}",startTime:"${generateAccount.startTime}",endTime:"${generateAccount.endTime}"},
        rowNum:10,     
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        viewrecords: true,
        gridComplete: function(){
    		
    	},
    	loadComplete:function(){
    	   
    	},
        caption:'<msg:message code="generateAccount.table"/>',
        toolbar: [true,"top"]
    });
    <c:if test="${!empty generateaccount_generate}">
    <security:authorize ifAnyGranted="${generateaccount_generate.code}">	
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${generateaccount_generate.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/generate_account.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${generateaccount_generate.name}");
	$("#t_generateAccountList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${generateaccount_generate.index}","#t_generateAccountList").click(function(){
    	//window.location.href = "${base}${generateaccount_generate.controller}?c=${generateaccount_generate.code}";
    	$("#stime").val("");
		$("#etime").val("");
		generateBoxy.center();
		generateBoxy.show();
    });    
    </security:authorize>
    </c:if>
    initGenerateDiv();
});

function initGenerateDiv(){
	var html = "<div align='center'>";
	html += "<msg:message code='generateAccount.stime'/><msg:message code='system.common.sign.colon'/><input type='text' name='stime' id='stime' onFocus=\"WdatePicker({maxDate:'#F{$dp.$D(etime)}'})\" class=\"Wdate\" style=\"width:100px;padding-left:5px;\" readonly=\"readonly\" /><br><br>";
	html +=	"<msg:message code='generateAccount.etime'/><msg:message code='system.common.sign.colon'/><input type='text' name='etime' id='etime' onFocus=\"WdatePicker({minDate:'#F{$dp.$D(stime)}'})\" class=\"Wdate\" style=\"width:100px;padding-left:5px;\" readonly=\"readonly\" /><br><br>";
	html +=	"<input type='button' name='submit' id='submit' class='btn2' value='<msg:message code='generateAccount.generate'/>' style='cursor:pointer;' onclick='submitPDF();' />";
	html += "</div>";
	if(generateBoxy != null){
		generateBoxy.setContent(html);
	}else{
		generateBoxy = new Boxy(html,{title:"<msg:message code='generateAccount.generate.title'/>",closeText:"[<msg:message code='button.colse'/>]",modal:true,center:true,show:false});
	}
}

function submitPDF(){
	var stime = $("#stime").val();
	var etime = $("#etime").val();
	if(stime == null || stime == ''){
		window.Boxy.alert("<msg:message code='generateAccount.generate.stime.null'/>", null, {title: "<msg:message code='info.prompt'/>"});
		return;
	}
	if(etime == null || etime == ''){
		window.Boxy.alert("<msg:message code='generateAccount.generate.etime.null'/>", null, {title: "<msg:message code='info.prompt'/>"});
		return;
	}
	generateBoxy.hide();
	var dialogContent = "<div width='200' height='100' ><msg:message code='generateAccount.generate.wait'/></div>";
	var promptBox = new window.Boxy(dialogContent,{title: "<msg:message code='info.prompt'/>",closeText:"[<msg:message code='button.colse'/>]",modal:true,center:true});
	var url = "${base}${generateaccount_generate.controller}.json";
	var dataparam = {
		    c:"${generateaccount_generate.code}",
		    startTime:stime,
		    endTime:etime
		 };
	$.ajax({
		url:url,
		type:'post',
		timeout:'60000',
		dataType:'json',
		data:dataparam,
		success:function(jsonData,textStatus){
			promptBox.hide();
			if (textStatus == "success"){
				if (jsonData.status == "success"){
					window.Boxy.alert("<msg:message code='generateAccount.generate.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
					$("#generateAccountList").trigger("reloadGrid");    
				}
			}else{
				window.Boxy.alert("<msg:message code='generateAccount.generate.fail'/>", null, {title: "<msg:message code='info.prompt'/>"});
			}
		},
		error:function(e){
			promptBox.hide();
			window.Boxy.alert("<msg:message code='generateAccount.generate.fail'/>", null, {title: "<msg:message code='info.prompt'/>"});
		}
	});
}
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
			<form:form method="post" action="list" commandName="generateAccount" name="searchForm" id="searchForm">
				<input type="hidden" name="c" value="${generateAccount.c}"/>
				<table cellpadding="0" cellspacing="1" border="0" width="100%" class="search">
					<tr>
						<!-- 
						<td align="right" width="9%" class="search_info"><msg:message code='generateAccount.crmId'/><msg:message code='system.common.sign.colon'/></td>
						<td align="left" width="15%" class="search_lable">
							<form:input path="crmId" />
						</td>
						 -->
						<td align="right" width="10%" class="search_info"><msg:message code='generateAccount.mobilePhone'/><msg:message code='system.common.sign.colon'/></td>
						<td align="left" width="30%" class="search_lable">
							<form:input path="mobilePhone" />
						</td>
						<td align="right" width="10%" class="search_info"><msg:message code='generateAccount.generateTime'/><msg:message code='system.common.sign.colon'/></td>
						<td align="left" width="30%" class="search_lable">
							<input type="text"  id="startTime"  name="startTime"  value="${generateAccount.startTime}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')}'})" class="Wdate" style="width:100px;" readonly="readonly" >
							<msg:message code="message.zhi" />
							<input type="text" id="endTime" name="endTime"  value="${generateAccount.endTime}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" class="Wdate" style="width:100px;" readonly="readonly" >
						</td>
						<td width="20%" class="button_area" align="center">
							<input type=submit class="btn2" id="select" value="<msg:message code='member.button.search'/>">
							<input type=button class="btn2" id="select" onclick="this.form.mobilePhone.value = '';this.form.startTime.value = '';this.form.endTime.value = '';" value="<msg:message code='button.reset'/>">
						</td>
					</tr>			
				</table>
			</form:form>
		</td>
	</tr>
	<tr>
		<td>
			<table id="generateAccountList"><tr><td>&nbsp;</td></tr></table>
			<div id="pagered"></div>
		</td>
	</tr>
</table>
</body>
</html>