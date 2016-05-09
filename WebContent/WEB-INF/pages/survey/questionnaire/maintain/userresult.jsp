<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
    jQuery("#userresultlist").jqGrid({
        treeGridModel: 'adjacency',
        url: 'userresultgrid.json',
        ExpandColumn: 'name',
        ExpandColClick: true,
        datatype: 'json',
        colNames: ["<msg:message code='questionnaire.userresult.username'/>",
        	"<msg:message code='questionnaire.userresult.sex'/>",
        	"<msg:message code='questionnaire.userresult.city'/>",
        	"<msg:message code='questionnaire.userresult.mobile'/>",
        	"<msg:message code='questionnaire.userresult.email'/>","<msg:message code='message.operate'/>"],
        colModel: [{name: 'memberName',index: 'memberName',width:'30%',align:'center',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'sex',index: 'sex',width:'5%',align:'center',hidden: false,sortable: false,formatter:formatData}, 
                   {name: 'city',index: 'city',width:'20%',align:'center',hidden: false,sortable: false},
                   {name: 'mobile',index: 'mobile',width:'10%',align:'center',hidden: false,sortable: false}, 
                   {name: 'email',index: 'email',width:'25%',align:'center',hidden: false,sortable: false}, 
                   {name:'act',index:'act', width:'10%',align:'center',sortable:false,title:false}],
        rowNum:10,     
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        viewrecords: true,
        mtype:"POST",
        postData:{memberName:"${questionnaire.memberName}",id:"${questionnaire.id}"}, 
        multiselect: true,
    	gridComplete: function(){
    		var ids = jQuery("#userresultlist").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var content = "";
    			var rowdata = jQuery("#userresultlist").jqGrid('getRowData',id);
    	
    
    		    <c:if test="${!empty userresult_info}">
    		    <security:authorize ifAnyGranted="${userresult_info.code}">	
    		    	content += "<a href='#' id='" + id + "' class='shortcut_${userresult_info.index}' ";
    		    	content += " title='${userresult_info.name}' >";
        			content += "<img src='${base}/${common}/${style}/images/icon/detail.png' weight='18' height='18' border='0' align='absmiddle'/>${userresult_info.name}";
        			content += "</a>";
    		    </security:authorize>
    		    </c:if>
    		   
    		    
    		   jQuery("#userresultlist").jqGrid('setRowData',ids[i],{act:"<div class='jqgridContainer'>" + content + "</div>"});
    		}	
    	},
    	loadComplete:function(data){
    		$(".shortcut_${userresult_info.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    			window.location.href = "${base}${userresult_info.controller}?c=${userresult_info.code}&userID="+rowid + "&id=" + "${questionnaire.id}";
    	    });
    	},
        caption:'<msg:message code="questionnaire.userresult.list"/>',
        toolbar: [true,"top"]
    });
});

</script>

<form:form method="post" action="userresult?id=${questionnaire.id}" commandName="questionnaire" >
	<table width="100%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
		<tr>
			<td width="100%">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"
					class="skinNav">
					<tr>
						<th width="2%">
							<img src="${base}/${common}/${style}/images/nav/bg_07.gif"
								width="10" height="9" />
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
			<td width="100%">
				<table cellpadding="0" cellspacing="0" border="0" width="100%" class="skinMain" >
					<tr>
						<td align="center">
							<form:hidden path="c"/>
							<table cellpadding="0" cellspacing="1" border="0" width="100%" class="search">
								<tr>
									<td align="right" width="10%" class="search_info">
										<msg:message code='questionnaire.userresult.username' /><msg:message code="system.common.sign.colon"/>
									</td>
									<td align="left" width="20%" class="search_lable">
									     <form:input path="memberName" onchange="this.value=$.trim(this.value)"/>
									</td>
									<td width="10%" class="button_area" align="center">
										<input type="submit" class="btn2" id="select" value="<msg:message code='button.search' />">
									</td>											
                                        </tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td>
										<table id="userresultlist">
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
						</td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form:form>
<div style="padding:10px">
<input type="button" class="btn2" onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}&responseNum=${queResponseNum }'" name="btnback" value="<msg:message code="button.back"/>" id="btnback" />
</div>