<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<script type="text/javascript">
$(document).ready(function(){
    jQuery("#recycledList").jqGrid({
        treeGridModel: 'adjacency',
        url: 'sendusersgrid.json',
        ExpandColumn: 'name',
        ExpandColClick: true,
        datatype: 'json',
        colNames: ["<msg:message code='member.name'/>",
        	 "<msg:message code='member.sex'/>",
        	"<msg:message code='message.send.city'/>",
        	"<msg:message code='member.mobilePhone'/>",
        	 "<msg:message code='message.send.email'/>",
        	 "<msg:message code='message.send.time'/>",
        	 "<msg:message code='member.operate'/>"],
        colModel: [{name: 'memberName',index: 'memberName',width:'40%',align:'center',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'sex',index: 'sex',width:'30%',align:'center',hidden: false,sortable: false},
                   {name: 'city',index: 'city',width:'30%',align:'center',hidden: false,sortable: false}, 
                   {name: 'mobile',index: 'mobile',width:'10%',align:'center',hidden: true,sortable: false}, 
                   {name: 'email',index: 'email',width:'10%',align:'center',hidden: false,sortable: false}, 
                   {name:'sendDate',index:'sendDate', width:'20%',align:'center',sortable:false},
                   {name:'act',index:'act', width:'30%',align:'center',sortable:false}],
        rowNum:10,     
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        viewrecords: true,
        multiselect: true,
        mtype:"POST",
        postData:{memberName:"${sendRecord.memberName}",msgId:"${sendRecord.msgId}"},
    	gridComplete: function(){
    		var ids = jQuery("#recycledList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
				<c:if test="${!empty sendrecord_delete}">
    		    <security:authorize ifAnyGranted="${sendrecord_delete.code}">	
    			content += "<a href='#' id='" + id + "' class='shortcut_${sendrecord_delete.index}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${sendrecord_delete.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    		    jQuery("#recycledList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(){
    		$(".shortcut_${sendrecord_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${sendrecord_delete.controller}.json?c=${sendrecord_delete.code}&id="+rowid;
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								//jQuery("#logList").jqGrid("delRowData",rowid);
    								$("#recycledList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			},{title: "<msg:message code='info.prompt'/>"});
    	    });
    	},
        caption:'<msg:message code="message.senduserlist"/>',
        toolbar: [true,"top"]
    });
    
    <c:if test="${!empty sendrecord_delete}">
    <security:authorize ifAnyGranted="${sendrecord_delete.code}">	
    var $ea = $("<a></a>").attr("href","#")
			  .attr("id","${sendrecord_delete.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${sendrecord_delete.name}");
	$("#t_recycledList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${sendrecord_delete.index}","#t_recycledList").click(function(){
    	var selrow = jQuery("#recycledList").jqGrid("getGridParam","selarrrow");
		if (selrow != null){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "${base}${sendrecord_delete.controller}.json?c=${sendrecord_delete.code}&ids="+selrow;
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								jQuery("#recycledList").jqGrid("delRowData",selrow);
								$("#recycledList").trigger("reloadGrid");    
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
		<table width="100%" border="0" cellspacing="1" cellpadding="0"
				class="skinMain">
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
			<form:form method="post" action="sendusers" commandName="sendRecord">
				<input type="hidden" name="c" value="${sendRecord.c}"/>
				<input type="hidden" name="msgId" value="${sendRecord.msgId}"/>
				<table cellpadding="0" cellspacing="1" border="0" width="100%" class="search">
					<tr>
						<td align="right" width="10%" class="search_info"><msg:message code='message.send.memebername' /><msg:message code="system.common.sign.colon"/></td>
						<td align="left" width="20%" class="search_lable"><form:input  path="memberName"/></td>
						<td rowspan="2" width="10%" class="button_area" align="center">	
						     <input type=submit class="btn2"  value="<msg:message code='button.search' />"/>
						</td>
					</tr>				
				</table>
			</form:form>
		</td>
	</tr>
	<tr>
		<td>
			<table id="recycledList"><tr><td>&nbsp;</td></tr></table>
			<div id="pagered"></div>
		</td>
	</tr>
	</table>
	</body>
</html>