<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#consultList").jqGrid({
        treeGridModel: 'adjacency',
        ExpandColumn: 'name',
        ExpandColClick: true,
        url: 'grid.json?state=0',
        datatype: 'json',
        colNames: ["<msg:message code='consult.name'/>","<msg:message code='consult.gender'/>",
                   "<msg:message code='consult.mobilePhone'/>","<msg:message code='consult.createTime'/>",
                   "<msg:message code='info.operate'/>"],
        colModel: [{name: 'name',index:'name',width:'20%',align:'center',sortable:false},
                   {name: 'gender',index:'gender',width:'10%',align:'center',sortable:false},
                   {name: 'mobilePhone',index:'mobilePhone',width:'30%',align:'center',sortable:false},
                   {name: 'createTime',index:'createTime',width:'25%',align:'center',sortable:false},
                   {name: 'act',index:'act', width:'15%',align:'center',sortable:false,title:false}],
        rowNum:10,     
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        mtype:"POST",
        postData:{mobilePhone:"${consult.mobilePhone}",name:"${consult.name}",starTime:"${consult.starTime}",endTime:"${consult.endTime}",c:"${consult.c}"}, 
        autowidth: true,
        viewrecords: true,
        multiselect: true,
    	gridComplete: function(){
    		var ids = jQuery("#consultList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			<c:if test="${!empty consult_detail}">
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${consult_detail.index}'";
    			content += "title='${consult_detail.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${consult_detail.name}";
    			content += "</a>";
    			</c:if> 
    			<c:if test="${!empty consult_modify}">
    		    <security:authorize ifAnyGranted="${consult_modify.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${consult_modify.index}'";
    			content += "title='${consult_modify.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/>${consult_modify.name}";
    			content += "</a>";
    			 </security:authorize>
    			</c:if> 
    			<c:if test="${!empty consult_delete}">
    		    <security:authorize ifAnyGranted="${consult_delete.code}">	
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${consult_delete.index}'";
    			content += "title='${consult_delete.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${consult_delete.name}";
    			content += "</a>";
    		    </security:authorize>
    			</c:if>
    			jQuery("#consultList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}
    	},
    	loadComplete:function(){
    	    $(".shortcut_${consult_detail.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${consult_detail.controller}?c=${consult_detail.code}&id="+rowid;
    	    });
    	    $(".shortcut_${consult_modify.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${consult_modify.controller}?c=${consult_modify.code}&id="+rowid;
    	    });
    	    $(".shortcut_${consult_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#consultList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${consult_delete.controller}.json?c=${consult_delete.code}&state=1&ids="+rowid+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(data.name));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								jQuery("#consultList").jqGrid("delRowData",rowid);
    								$("#consultList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });  		
    	},
        caption:'<msg:message code="consult.list"/>',
        toolbar: [true,"top"]
    });
    
    <c:if test="${!empty consult_create}">
    <security:authorize ifAnyGranted="${consult_create.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${consult_create.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${consult_create.name}");
	$("#t_consultList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${consult_create.index}","#t_consultList").click(function(){
    	window.location.href = "${base}${consult_create.controller}?c=${consult_create.code}";
    });    
    </security:authorize>
    </c:if>
    
    <c:if test="${!empty consult_delete}">
    <security:authorize ifAnyGranted="${consult_delete.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${consult_delete.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${consult_delete.name}");
	$("#t_consultList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${consult_delete.index}","#t_consultList").click(function(){
    	var selrow = jQuery("#consultList").jqGrid("getGridParam","selarrrow");
    	var names = "";
    	for(var i=0;i < selrow.length;i++){
    		var id = selrow[i];
    		var data = jQuery("#consultList").jqGrid("getRowData",id);
    		names += data.name;
    		if(i!= selrow.length-1)
    			names += ",";
    	}
		if (selrow != null && selrow != ""){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "${base}${consult_delete.controller}.json?c=${consult_delete.code}&state=1&ids="+selrow+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(names));
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								jQuery("#consultList").jqGrid("delRowData",selrow);
								$("#consultList").trigger("reloadGrid");    
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
    
    <c:if test="${!empty consult_recycle}">
    <security:authorize ifAnyGranted="${consult_recycle.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${consult_recycle.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/Recycler.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${consult_recycle.name}");
	$("#t_consultList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${consult_recycle.index}","#t_consultList").click(function(){
    	window.location.href = "${base}${consult_recycle.controller}?c=${consult_recycle.code}";
    });    
    </security:authorize>
    </c:if>
	    
});



</script>
</head >
<body class="skinMain">
<form:form method="post" action="list" commandName="consult" >
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
					<input type="hidden" name="c" value="${consult.c}" />
						<table cellpadding="0" cellspacing="0" border="1" width="100%" class="skinLayout">
							<tr>
								<td align="right" width="10%" class="search_info">
								<msg:message code="consult.name"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="18%" class="search_lable">
								<form:input path="name" onchange="this.value=$.trim(this.value)"/>
								</td>
								<td align="right" width="10%" class="search_info">
								<msg:message code="consult.mobilePhone"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="18%" class="search_lable">
								<form:input path="mobilePhone" onchange="this.value=$.trim(this.value)"/>
								</td>
								<td align="right" width="10%" class="search_info">
								<msg:message code="consult.timeRegion"/><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="18%" class="search_lable">
								<input type="text" name="starTime" id="starTime" value="${consult.starTime}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2080-10-01\'}'})" style="width:90px;" readonly="readonly" class="Wdate">
								<msg:message code="consult.and"/>
								<input type="text" name="endTime" id="endTime" value="${consult.endTime}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starTime\')}',maxDate:'2080-10-01'})" style="width:90px;" readonly="readonly" class="Wdate">
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
		<table id="consultList"><tr><td>&nbsp;</td></tr></table>
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