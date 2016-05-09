<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#consumeList").jqGrid({
        url: 'consumeGrid.json',
        datatype: 'json',
        colNames: ["<msg:message code='consume.createTime'/>",
                   "<msg:message code='consume.money'/>",
                   "<msg:message code='consume.desc'/>",
                   "<msg:message code='consume.userName'/>",
                   "<msg:message code='info.operate'/>"],
        colModel: [{name: 'createTime',index:'createTime',width:'8%',align:'center',hidden: false,sortable:false},
                   {name: 'money',index:'money',width:'8%',align:'center',hidden: false,sortable:false},
                   {name: 'desc',index:'desc',width:'15%',align:'center',hidden: false,sortable:false},
                   {name: 'userName',index:'userName',width:'5%',align:'center',hidden: false,sortable:false},
                   {name:'operate',index:'operate', width:'20%',align:'left',sortable:false}],
        mtype:"POST",
        postData:{c:"${consume.c}",clientId:"${consume.clientId}"},
        rowNum:10,    
        page:"${client.page}",
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        shrinkToFit:true,
        viewrecords: true,
        gridComplete: function(){
    		var ids = jQuery("#consumeList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var rowdata = jQuery("#consumeList").jqGrid('getRowData',id);
    			var content = "";
   		    	content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_consume_delete' title='删除' onclick=del("+id+") >";
   		    	content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>删除";
   		    	content += "</a>";
    			jQuery("#consumeList").jqGrid('setRowData',ids[i],{operate:content});
    		}	
    	},
    	loadComplete:function(){
    		/* $(".shortcut_consume_delete").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#consumeList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}/clientcenter/client/maintain/delete_consume.json?c=103010402&id="+rowid+"&status=9";
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								$("#consumeList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });    */
    	},
        caption:'客户消费列表',
        toolbar: [true,"top"]
    });
    var $ea = $("<a onclick='add();'></a>").attr("href","javascript:void(0)")
			  .attr("id","consume_create")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("创建");
	$("#t_consumeList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
   /*  $("#consume_create").click(function(){
    	//window.location.href = "${base}${consume_create.controller}?c=${consume_create.code}";
    	window.location.href = "${base}/clientcenter/client/maintain/carete_consume?c=103010401&clientId="+ $('#clientId').val();
    });  */   
});
function add(){
	window.location.href = "${base}/clientcenter/client/maintain/carete_consume?c=103010401&clientId="+ $('#clientId').val();
}
function del(id){
	window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
		var url = "${base}/clientcenter/client/maintain/delete_consume.json?c=103010402&id="+id+"&status=9"+${consume.clientId};
		$.ajax({
			url:url,
			type:'post',
			timeout:'60000',
			dataType:'json',
			success:function(jsonData,textStatus){
				if (textStatus == "success"){
					if (jsonData.status == "success"){
						$("#consumeList").trigger("reloadGrid");    
					}
				}
			}
		});
	}, {title: "<msg:message code='info.prompt'/>"});
}
</script>
</head>
<body class="skinMain">
<input type="hidden" name="c" value="${consume.c}"/>
<input type="hidden" id="clientId" name="clientId" value="${consume.clientId}"/>
<table cellpadding="0" cellspacing="1" border="0" width="100%" class="skinMain">
	<tr>
		<td width="100%">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinNav">
				<tr>
					<th width="2%"><img src="${base}/${common}/${style}/images/nav/bg_07.gif" width="20" height="20" /></th>
					<th width="98%"><msg:message code="navigation.title"/>&nbsp;${navigation}</th>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table id="consumeList"><tr><td>&nbsp;</td></tr></table>
			<div id="pagered"></div>
		</td>
	</tr>
</table>
</body>
</html>