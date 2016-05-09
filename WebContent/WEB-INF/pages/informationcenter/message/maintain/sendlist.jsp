<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
var sv = "${member.selectValues}";
var idsString =  "${member.idsString}";
sv.replace("\"","\\\"");
$(document).ready(function(){
    jQuery("#memberList").jqGrid({
        url: 'sendgrid.json',
        datatype: 'json',
        colNames: ["<msg:message code='member.name'/>", 
                   "<msg:message code='member.sex'/>",
                   "<msg:message code='member.mobilePhone'/>",
                   "<msg:message code='member.level'/>",
                   "<msg:message code='member.source'/>",
                   "<msg:message code='member.operate'/>"],
        colModel: [{name: 'memberName',index:'memberName',width:'20%',align:'center',hidden: false,formatter:formatData},
                   {name: 'sex',index:'sex',width:'10%',align:'center',hidden: false},
                   {name: 'mobilePhone',index:'mobilePhone',width:'20%',align:'center',hidden: true},
                   {name: 'level',index:'level',width:'15%',align:'center',hidden: false},
                   {name: 'source',index:'source',width:'15%',align:'center',hidden: false},
                   {name:'operate',index:'operate', width:'20%',align:'center',sortable:false}],
        mtype:"POST",
        postData:{c:"${member.c}",memberName:"${member.memberName}",level:"${member.level}",delStatus:"${member.delStatus}",msgId:"${message.msgId}",idsString:"${member.idsString}",sqlCondition:"${member.sqlCondition}",complexSearchFlag:"${member.complexSearchFlag}", msgTitle:"${message.msgTitle}",selectValues:sv},
        rowNum:10,    
        page:"${member.page}",
        rowList: [10, 20, 30],
        pager: '#pagered',
        height:'auto',
        autowidth: true,
        shrinkToFit:true,
        viewrecords: true,
        multiselect: true,
        gridComplete: function(){
    		var ids = jQuery("#memberList").jqGrid('getDataIDs');
    		for(var i=0;i < ids.length;i++){
    			var id = ids[i];
    			var rowdata = jQuery("#memberList").jqGrid('getRowData',id);
    			var content = "";
    			<c:if test="${!empty msg_sending}">
    		    <security:authorize ifAnyGranted="${msg_sending.code}">	
    			content += "<a href='#' id='" + id + "' class='shortcut_${msg_sending.index}'  title='${msg_sending.name}'>";
    			content += "<img src='${base}/${common}/${style}/images/icon/send.png' weight='18' height='18' border='0' align='absmiddle'/>${msg_sending.name}";
    			content += "</a>";
    		    </security:authorize>
    		    </c:if>     
    			jQuery("#memberList").jqGrid('setRowData',ids[i],{operate:content});
    		}	
    	},
    	loadComplete:function(){
    	    $(".shortcut_${msg_sending.index}").click(function(){
    	    	var rowid = $(this).attr("id");
    	    	var data = jQuery("#memberList").jqGrid("getRowData",rowid);
    	    	var sendAppFlag ="";    	    	
    	    	if($("input[name='sendApp']:checked").length!=0){
						sendAppFlag = "1";
					}
    			window.parent.Boxy.confirm("<msg:message code='message.prompt.send'/>",  function(){
    				var url = "${base}${msg_sending.controller}.json?msgId=${message.msgId}&&c=${msg_sending.code}&sendApp="+sendAppFlag+"&id="+rowid+"&ids="+rowid;
    				$.ajax({
    					url:url,
    					type:'post',
    					timeout:'60000',
    					dataType:'json',
    					success:function(jsonData,textStatus){
    						if (textStatus == "success"){
    							if (jsonData.status == "success"){
    								window.parent.Boxy.alert("<msg:message code='message.prompt.send.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
    								$("#memberList").trigger("reloadGrid");    
    							}else if(jsonData.status == "fail"){
    								window.parent.Boxy.alert("<msg:message code='message.prompt.send.apperror'/>", null, {title: "<msg:message code='info.prompt'/>"});
    								$("#memberList").trigger("reloadGrid");
    							}else{
    								window.parent.Boxy.alert("<msg:message code='message.prompt.send.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
    								$("#memberList").trigger("reloadGrid");
    							}
    						}
    					}
    				});
    			}, {title: "<msg:message code='info.prompt'/>"});
    	    });     		
    	},
        caption:'<msg:message code="member.list"/>',
        toolbar: [true,"top"]
    });
    
    
    var sendAppName = "<msg:message code='message.send.app'/>";
    var sendApp = "<input type='checkbox' id= 'sendApp'  name='sendApp'>"+sendAppName;
    $("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append(sendApp));  
    
    
    <c:if test="${!empty msg_sending}">
    <security:authorize ifAnyGranted="${msg_sending.code}">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${msg_sending.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/send.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${msg_sending.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#${msg_sending.index}","#t_memberList").click(function(){
    	var selrow = jQuery("#memberList").jqGrid("getGridParam","selarrrow");
		if (selrow != null && selrow!=""){
			var sendAppFlag ="";
			if($("input[name='sendApp']:checked").length!=0){
				sendAppFlag = "1";
			}
			window.parent.Boxy.confirm("<msg:message code='message.prompt.send'/>",  function(){
				var url = "${base}${msg_sending.controller}.json?c=${msg_sending.code}&msgId=${message.msgId}&sendApp="+sendAppFlag+"&ids="+selrow;
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
    								window.parent.Boxy.alert("<msg:message code='message.prompt.send.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
    								$("#memberList").trigger("reloadGrid");    
    							}else{
    								window.parent.Boxy.alert("<msg:message code='message.prompt.send.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
    								$("#memberList").trigger("reloadGrid");
    							}
						}
					}
				});
			}, {title: "<msg:message code='info.prompt'/>"});
		}
		else{
    		window.parent.Boxy.alert("<msg:message code='message.prompt.send.chooseuser'/>", null, {title: "<msg:message code='info.prompt'/>"});
   		}    	
    });    
    </security:authorize>
    </c:if>
    <c:if test="${!empty msg_sendall}">
    <security:authorize ifAnyGranted="${msg_sendall.code}">	
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${msg_sendall.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/send.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${msg_sendall.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
    $("#${msg_sendall.index}","#t_memberList").click(function(){
    	var selrow = jQuery("#memberList").jqGrid("getGridParam","selarrrow");
    	var sendAppFlag ="";
		if($("input[name='sendApp']:checked").length!=0){
			sendAppFlag = "1";
		}
			window.parent.Boxy.confirm("<msg:message code='message.prompt.send'/>",  function(){
				var url = "${base}${msg_sendall.controller}.json?c=${msg_sendall.code}&msgId=${message.msgId}&sendApp="+sendAppFlag+"&ids="+selrow;
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
    								window.parent.Boxy.alert("<msg:message code='message.prompt.send.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
    								$("#memberList").trigger("reloadGrid");    
    							}else{
    								window.parent.Boxy.alert("<msg:message code='message.prompt.send.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
    								$("#memberList").trigger("reloadGrid");
    							}
						}
					}
				});
			}, {title: "<msg:message code='info.prompt'/>"});	
    });    
    </security:authorize>
    </c:if>
    
    <c:if test="${!empty msg_sendby}">
    <security:authorize ifAnyGranted="${msg_sendby.code}">	
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","${msg_sendby.index}")
			  .append($("<img/>").attr("src","${base}/${common}/${style}/images/icon/send.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${msg_sendby.name}");
	$("#t_memberList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));    
	$("#${msg_sendby.index}","#t_memberList").click(function(){
    	if(idsString !=null && idsString !=""){
    		var sendAppFlag ="";
			if($("input[name='sendApp']:checked").length!=0){
				sendAppFlag = "1";
			}
			window.parent.Boxy.confirm("<msg:message code='message.prompt.send'/>",  function(){
				var url = "${base}${msg_sendby.controller}.json?c=${msg_sendby.code}&idsString=${member.idsString}&msgId=${message.msgId}&sendApp="+sendAppFlag ;
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
    								window.parent.Boxy.alert("<msg:message code='message.prompt.send.success'/>", null, {title: "<msg:message code='info.prompt'/>"});
    								$("#memberList").trigger("reloadGrid");    
    							}else{
    								window.parent.Boxy.alert("<msg:message code='message.prompt.send.error'/>", null, {title: "<msg:message code='info.prompt'/>"});
    								$("#memberList").trigger("reloadGrid");
    							}
						}
					}
				});
			}, {title: "<msg:message code='info.prompt'/>"});	
			}else{
				window.parent.Boxy.alert("<msg:message code='message.prompt.sendbyselect'/>", null, {title: "<msg:message code='info.prompt'/>"});
			}
    });       
    </security:authorize>
    </c:if> 

    //高级查询初始化
    searchTB.setSearchData(jsonData);
    searchTB.setElementID('searchTools');
    searchTB.setRowCount(3);  
    searchTB.execute();
    //设置查询表单显示
    <c:if test="${!empty member.complexSearchFlag}">
    	$('#searchForm').css('display','none');
    	$('#complexSearchForm').css('display','block');
    	var tagName = document.getElementsByTagName("input");
    	selectValues = JSON.parse("${member.selectValues}");
    	for(var i = 0; i < selectValues.length; i++){
    		selectValue = selectValues[i];
    		if(selectValue.type == 'radio'){
    			for ( var j = 0; j < tagName.length; j++){
    				if (tagName[j].type == "radio" && tagName[j].value == selectValue.value){
    					tagName[j].checked = 'checked';
    				}
    			}
    		}else{
    			if((selectValue.id).indexOf("column") != -1){
    	    		$('#'+selectValue.id).val(selectValue.value);
    	    		$('#'+selectValue.id).change();
    			}
	    		$('#'+selectValue.id).val(selectValue.value);
    		}
    		
    	}
    </c:if>
        
});
var and = "<msg:message code='condition.and'/>";
var or = "<msg:message code='condition.or'/>";
var pleaseSelect = "<msg:message code='please.select'/>";
var elementIDError = "<msg:message code='searchToolBar.elementID.error'/>";
var searchDataError = "<msg:message code='searchToolBar.searchData.error'/>";
var jsonData = {
    		   	searchFuncName:'',	
    			columns:
		        [
			        {
			            name:'M.MemberName',desc:"<msg:message code='member.name'/>",dataType:'',isshow:'0',primKey:'',checkbox:'1',link:{funcName:'',url:''}, 
			            width:'10%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'input',sid:''},alias:''
			        },
			        {	 
			            name:'M.Sex',desc:"<msg:message code='member.sex'/>",dataType:'',isshow:'1',primKey:'0',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'select',sid:''},alias:''
			        },
			        {
			            name:'ME.Level',desc:"<msg:message code='member.level'/>",dataType:'',isshow:'1',primKey:'1',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'select',sid:''},alias:''
			        },
			        {
			            name:'ME.Status',desc:"<msg:message code='member.status'/>",dataType:'',isshow:'1',primKey:'1',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'select',sid:''},alias:''
			        },
			        {
			            name:'ME.Source',desc:"<msg:message code='member.source'/>",dataType:'',isshow:'0',primKey:'1',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'select',sid:''},alias:''
			        },
			        {
			            name:'ME.RegistrationTime',desc:"<msg:message code='member.registrationTime'/>",dataType:'',isshow:'0',primKey:'1',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'date',sid:''},alias:''
			        },
			        {
			            name:'M.FinancialPlanner',desc:"<msg:message code='member.financialPlanner'/>",dataType:'',isshow:'0',primKey:'1',checkbox:'0',link:{funcName:'',url:''}, 
			            width:'20%',height:'22',order:{isorder:'1',tname:''},search:{iscol:'1',type:'input',sid:''},alias:''
			        }
		    	],		
    			expressions:
				[
					{
						type:'date',option:[
						                        {name:'equal',value:'=',desc:"<msg:message code='condition.equal'/>",isValid:'1'},
							                	{name:'notEqual',value:'<>',desc:"<msg:message code='condition.notEqual'/>",isValid:'1'},
							                	{name:'greaterThan',value:'>',desc:"<msg:message code='condition.greaterThan'/>",isValid:'1'},
							                	{name:'greaterEqual',value:'>=',desc:"<msg:message code='condition.greaterEqual'/>",isValid:'1'},
							                	{name:'lessThan',value:'<',desc:"<msg:message code='condition.lessThan'/>",isValid:'1'},
							                	{name:'lessEqual',value:'<=',desc:"<msg:message code='condition.lessEqual'/>",isValid:'1'},
							                	{name:'like',value:'like',desc:"<msg:message code='condition.like'/>",isValid:'1'},
						 	                	{name:'noLike',value:'not like',desc:"<msg:message code='condition.notLike'/>",isValid:'1'}
					                       ]
					},
					{
						type:'select',option:[
						                        {name:'equal',value:'=',desc:"<msg:message code='condition.equal'/>",isValid:'1'},
							                	{name:'notEqual',value:'<>',desc:"<msg:message code='condition.notEqual'/>",isValid:'1'},
							                	{name:'greaterThan',value:'>',desc:"<msg:message code='condition.greaterThan'/>",isValid:'1'},
							                	{name:'greaterEqual',value:'>=',desc:"<msg:message code='condition.greaterEqual'/>",isValid:'1'},
							                	{name:'lessThan',value:'<',desc:"<msg:message code='condition.lessThan'/>",isValid:'1'},
							                	{name:'lessEqual',value:'<=',desc:"<msg:message code='condition.lessEqual'/>",isValid:'1'},
							                	{name:'like',value:'like',desc:"<msg:message code='condition.like'/>",isValid:'1'},
						 	                	{name:'noLike',value:'not like',desc:"<msg:message code='condition.notLike'/>",isValid:'1'}
						                     ]
					},
					{
						type:'input',option:[
						                        {name:'equal',value:'=',desc:"<msg:message code='condition.equal'/>",isValid:'1'},
							                	{name:'notEqual',value:'<>',desc:"<msg:message code='condition.notEqual'/>",isValid:'1'},
							                	{name:'greaterThan',value:'>',desc:"<msg:message code='condition.greaterThan'/>",isValid:'1'},
							                	{name:'greaterEqual',value:'>=',desc:"<msg:message code='condition.greaterEqual'/>",isValid:'1'},
							                	{name:'lessThan',value:'<',desc:"<msg:message code='condition.lessThan'/>",isValid:'1'},
							                	{name:'lessEqual',value:'<=',desc:"<msg:message code='condition.lessEqual'/>",isValid:'1'},
							                	{name:'like',value:'like',desc:"<msg:message code='condition.like'/>",isValid:'1'},
						 	                	{name:'noLike',value:'not like',desc:"<msg:message code='condition.notLike'/>",isValid:'1'}
						                    ]
					}
				],
				selectValues:
				[
	            	{
	            		name:"M.Sex",value:[
												<c:forEach var="sex" items="${sexList}" varStatus="stat">
													<c:if test="${!stat.last}">
													{name:"${sex.name}",value:"${sex.value}"},
													</c:if>
													<c:if test="${stat.last}">
													{name:"${sex.name}",value:"${sex.value}"}
													</c:if>														
												</c:forEach>
	            		                   ]
	            	},
	            	{
	            		name:"ME.Level",value:[
						  						  <c:forEach var="level" items="${levelList}" varStatus="stat">
														<c:if test="${!stat.last}">
														{name:"${level.name}",value:"${level.value}"},
														</c:if>
														<c:if test="${stat.last}">
														{name:"${level.name}",value:"${level.value}"}
														</c:if>			            		                    	  	  
	            		                    	  </c:forEach>
	            		                   	  ]
	            	},
	            	{
	            		name:"ME.Source",value:[
													<c:forEach var="source" items="${sourceList}" varStatus="stat">
														<c:if test="${!stat.last}">
														{name:"${source.name}",value:"${source.value}"},
														</c:if>
														<c:if test="${stat.last}">
														{name:"${source.name}",value:"${source.value}"}
														</c:if>														
													</c:forEach>
	            		                       ]
	            	},
	            	{
	            		name:"ME.Status",value:[
													<c:forEach var="status" items="${statusList}" varStatus="stat">
														<c:if test="${!stat.last}">
														{name:"${status.name}",value:"${status.value}"},
														</c:if>
														<c:if test="${stat.last}">
														{name:"${status.name}",value:"${status.value}"}
														</c:if>
													</c:forEach>
	            		                       ]
	            	}
	            ]
			};   
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
		<td>
			<table cellpadding="0" cellspacing="1" border="0" width="100%" height="25px" class="search">
				<tr>
					<td align="right" width="10%" class="search_info"><msg:message code='message.msgtitle' /><msg:message code="system.common.sign.colon"/></td>
					<td align="left" width="15%" class="search_lable"><font color="red">${message.msgTitle}</font></td>
					<td align="right" width="10%" class="search_info"><msg:message code='message.msgcontent' /><msg:message code="system.common.sign.colon"/></td>
					<td align="left" width="65%" class="search_lable"><font color="red">${message.msgContent}</font></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">
			<form:form method="post" action="sendlist" commandName="member" name="searchForm" id="searchForm">
				<input type="hidden" name="c" value="${member.c}"/>
				<input type="hidden" name="msgId" value="${message.msgId}"/>
				<input type="hidden" name="idsString" value="${member.idsString}"/>
				
				<table cellpadding="0" cellspacing="1" border="0" width="100%" class="search">
					<tr>
						<td align="right" width="10%" class="search_info"><msg:message code='member.name'/><msg:message code="system.common.sign.colon"/></td>
						<td align="left" width="18%" class="search_lable">
							<form:input path="memberName" maxlength="25"/>
						</td>
						<td align="right" width="10%" class="search_info"><msg:message code='member.level'/><msg:message code="system.common.sign.colon"/></td>
						<td align="left" width="18%" class="search_lable">
							<form:select path="level" >
								<form:option value="0">--<msg:message code='please.select'/>--</form:option>
								<form:options items="${levelList}" itemValue="value" itemLabel="name"/>
							</form:select>
						</td>
						<td width="16%" class="button_area" align="center">
							<input type=submit class="btn2" id="select" value="<msg:message code='member.button.search'/>">
							<input type="button" class="btn2" onclick="this.form.style.display='none';document.complexSearchForm.style.display='block';" value="<msg:message code='member.button.complexSearch'/>">
							<input type=button class="btn2" id="select" onclick="this.form.memberName.value = '';this.form.level.value = '';" value="<msg:message code='button.reset'/>">
						</td>
					</tr>			
				</table>
			</form:form>
			<form:form method="post" action="sendlist" commandName="member" name="complexSearchForm" id="complexSearchForm" cssStyle="display:none;">
				<input type="hidden" name="c" value="${member.c}"/>
				<input type="hidden" name="msgId" value="${message.msgId}"/>
				<input type="hidden" name="idsString" value="${member.idsString}"/>
				<form:hidden path="complexSearchFlag" value="1"/>
				<form:hidden path="selectValues" value=""/>
				<form:hidden path="sqlCondition" value=""/>
				<table cellpadding="0" cellspacing="0" border="0" width="100%" style="border: #CCF solid 1px;">
					<tr>
						<td>
							<div id="searchTools"></div>
						</td>
						<td style="border-left:#CCCCFF solid 1px;" align="center" valign="middle">
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