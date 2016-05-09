<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
    jQuery("#reservationList").jqGrid({
        treeGridModel: 'adjacency',
        ExpandColumn: 'name',
        ExpandColClick: true,
        url: 'grid.json?delState=0',
        datatype: 'json',
        colNames: [ "<msg:message code='reservation.name'/>","<msg:message code='reservation.sexName'/>",
                   "<msg:message code='reservation.clientGrade'/>","<msg:message code='reservation.disposeStateName'/>",
                   "<msg:message code='reservation.createTime'/>","<msg:message code='info.operate'/>"],
        colModel: [{name: 'name',index:'name',width:'15',align:'center',hidden: false,sortable:false},
                   {name: 'gender',index:'gender',width:'5%',align:'center',hidden: false,sortable:false},
                   {name: 'clientGrade',index:'clientGrade',width:'10%',align:'center',hidden: false,sortable:false},
                   {name: 'disposeState',index:'disposeState',width:'10%',align:'center',hidden: false,sortable:false},
                   {name: 'createTime',index:'createTime',width:'15%',align:'center',hidden: false,sortable:false},
                   {name:'act',index:'act', width:'15%',align:'center',sortable:false}],
        rowNum:10,
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        mtype:"POST",
        postData:{name:"${reservation.name}",starTime:"${reservation.starTime}",endTime:"${reservation.endTime}",disposeState:"${reservation.disposeState}",cancelState:"${reservation.cancelState}",c:"${reservation.c}"}, 
        autowidth: true,
        viewrecords: true,
        multiselect: true,
    	gridComplete: function(){
    		var ids = jQuery("#reservationList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var rowdata = jQuery("#reservationList").jqGrid('getRowData',id);
    			if (rowdata.disposeState == 1){
    				 jQuery("#reservationList").jqGrid('setRowData',id,{disposeState:"<msg:message code='reservation.disposeState'/>"});
    			}else{
    				 jQuery("#reservationList").jqGrid('setRowData',id,{disposeState:"<msg:message code='reservation.noDisposeState'/>"});
    			}
    			var content = "";
    			<c:if test="${!empty res_detail}">
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${res_detail.index}'";
    			content += "title='${res_detail.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${res_detail.name}";
    			content += "</a>";
    			</c:if>
    			<c:if test="${!empty res_delete}">
    		    <security:authorize ifAnyGranted="${res_delete.code}">
    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_${res_delete.index}'";
    			content += "title='${res_delete.name}' >";
    			content += "<img src='${base}/${common}/${style}/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/>${res_delete.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    		    jQuery("#reservationList").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}
    	},
    	loadComplete:function(){
    	    $(".shortcut_${res_detail.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${res_detail.controller}?c=${res_detail.code}&id="+rowid;
    	    });
    	    $(".shortcut_${res_delete.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#reservationList").jqGrid("getRowData",rowid);
    			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
    				var url = "${base}${res_delete.controller}.json?c=${res_delete.code}&delState=1&ids="+rowid+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(data.name));
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								jQuery("#reservationList").jqGrid("delRowData",rowid);
    								$("#reservationList").trigger("reloadGrid");    
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });	
    	},
        caption:'<msg:message code="reservation.list"/>',
        toolbar: [true,"top"]
    });
    
    <c:if test="${!empty res_delete}">
    <security:authorize ifAnyGranted="${res_delete.code}">	
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${res_delete.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${res_delete.name}");
	$("#t_reservationList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));
    $("#${res_delete.index}","#t_reservationList").click(function(){
    	var selrow = jQuery("#reservationList").jqGrid("getGridParam","selarrrow");
    	var names = "";
    	for(var i=0;i < selrow.length;i++){
    		var id = selrow[i];
    		var data = jQuery("#reservationList").jqGrid("getRowData",id);
    		names += data.name;
    		if(i!= selrow.length-1)
    			names += ",";
    	}
		if (selrow != null && selrow != ""){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "${base}${res_delete.controller}.json?c=${res_delete.code}&delState=1&ids="+selrow+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(names));
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								jQuery("#reservationList").jqGrid("delRowData",selrow);
								$("#reservationList").trigger("reloadGrid");    
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
    
    <c:if test="${!empty res_recycle}">
    <security:authorize ifAnyGranted="${res_recycle.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${res_recycle.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/Recycler.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${res_recycle.name}");
	$("#t_reservationList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${res_recycle.index}","#t_reservationList").click(function(){
    	window.location.href = "${base}${res_recycle.controller}?c=${res_recycle.code}";
    });    
    </security:authorize>
    </c:if>
    
});

</script>
</head>
<body class="skinMain">
<form:form method="post" action="list" commandName="reservation">
<input type="hidden" name="c" value="${reservation.c}" />
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
		<table cellpadding="0" cellspacing="0" border="1" width="100%" class="skinLayout">
			<tr>
				<td align="right" width="10%" class="search_info">
				<msg:message code="reservation.name"/><msg:message code="system.common.sign.colon"/>
				</td>
				<td align="left" width="18%" class="search_lable">
				<form:input path="name" value="${reservation.name}" onchange="this.value=$.trim(this.value)"/>
				</td>
				<td align="right" width="10%" class="search_info">
				<msg:message code="reservation.timeRegion"/><msg:message code="system.common.sign.colon"/>
				</td>
				<td align="left" width="18%" class="search_lable">
				<input name="starTime" id="starTime" value="${reservation.starTime}" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endTime\')||\'2080-10-01\'}'})" style="width:90px;" readonly="readonly" class="Wdate"/>
				<msg:message code="consult.and"/>
				<input name="endTime" id="endTime" value="${reservation.endTime}" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starTime\')}',maxDate:'2080-10-01'})" style="width:90px;" readonly="readonly" class="Wdate"/>
				</td>
				</td>
				<td rowspan="2" class="button_area" align="center" width="10%">
				<input type="submit" class="btn2" id="select" value="<msg:message code="button.search"/>">
				</td>
				</tr>
				<tr>
				<td align="right" width="10%" class="search_info">
				<msg:message code="reservation.isDisposeState"/><msg:message code="system.common.sign.colon"/>
				</td>
				<td align="left" width="15%" class="search_lable">
				<form:select path="disposeState">
					<form:option value="">--<msg:message code='please.select'/>--</form:option>
					<form:option value="0"><msg:message code="reservation.noDisposeState"/></form:option>
					<form:option value="1"><msg:message code="reservation.disposeState"/></form:option>
				</form:select>
				</td>
				<td align="right" width="10%" class="search_info">
				<msg:message code="reservation.isCancelState"/><msg:message code="system.common.sign.colon"/>
				</td>
				<td align="left" width="15%" class="search_lable">
				<form:select path="cancelState">
					<form:option value="">--<msg:message code='please.select'/>--</form:option>
					<form:option value="0"><msg:message code="reservation.noCancelState"/></form:option>
					<form:option value="1"><msg:message code="reservation.cancelState"/></form:option>
				</form:select>
			</tr>			
		</table>
		</td>
	</tr>
	<tr>
		<td>
			<table id="reservationList"><tr><td>&nbsp;</td></tr></table>
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