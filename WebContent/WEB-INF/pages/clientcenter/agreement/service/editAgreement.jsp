<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<script type="text/javascript">
	
	function saveImage(form){
		 var file1 = $('#imageIpad').val();
		 var file2 = $('#imageIphone').val();
		 if(file1 != null && file1 != ''){
			 var postfix1 = file1.match(/^(.*)(\.)(.{1,8})$/)[3].toLowerCase(); 
			 if( !(postfix1 == 'pdf')){
				 window.parent.Boxy.alert("<msg:message code='agreement.image.filetype' />", null, {title: "<msg:message code='info.prompt'/>"});
				 return false;
			 }
		 }
		 if(file2 != null && file2 != ''){
			 var postfix2 = file2.match(/^(.*)(\.)(.{1,8})$/)[3].toLowerCase(); 
			 if( !(postfix2 == 'pdf' )){
				 window.parent.Boxy.alert("<msg:message code='agreement.image.filetype' />", null, {title: "<msg:message code='info.prompt'/>"});
				 return false;
			 }
		 }
		form.submit();
	}
	
	
</script>

<form:form action="save" enctype="multipart/form-data" method="post" commandName="agreement">  
<form:hidden path="c"/>  
<table width="100%" height="100%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
		<tr>
			<td width="100%">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinNav">
					<tr>
						<th width="2%"><img src="${base}/${common}/${style}/images/nav/bg_07.gif" width="10" height="9" />
					</th>
					<th width="98%"><msg:message code="navigation.title" />&nbsp;${navigation}</th>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
				<tr>
					<td width="15%" align="right"><msg:message code='agreement.ipad.image'/></td>
					<td width="85%">
						<c:if test="${ipadimage != null}">
					   <a href="${base}/clientcenter/agreement/service/download?type=ipad" style="width: 200px;height: 200px;">下载</a>
					   </c:if>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right"><msg:message code="agreement.ipad.upload"/></td>
					<td width="85%"><input type="file" id="imageIpad" name="imageIpad"  size="40" style="height: 26px;" />
					</td>
				</tr>
				<tr>
					<td width="15%" align="right"><msg:message code="agreement.iphone.image" /></td>
					<td width="85%">
					<c:if test="${iphoneimage != null}">
					   <a href="${base}/clientcenter/agreement/service/download?type=iphone" style="width: 200px;height: 200px;">下载</a>
					</c:if>   
					</td>
				</tr>
					<tr>
					<td width="15%" align="right"><msg:message code="agreement.iphone.upload"/></td>
					<td width="85%"><input type="file" id="imageIphone" name="imageIphone" size="40" style="height: 26px;"/>
					</td>
				</tr>
				<tr>
					<td width="15%" align="right"></td>
					<td width="85%">
				    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button"  value="<msg:message code='button.save'/>" class="btn2" onclick="saveImage(this.form)"/>
					
					</td>
				</tr>
				
			</table>		
		</td>
	</tr>
</table>
</form:form>	
