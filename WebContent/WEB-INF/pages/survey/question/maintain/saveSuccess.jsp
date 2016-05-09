<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<script type="text/javascript">
	var currentCode = '${question.c}';
	var questionListCode = currentCode.substring(0,5);
	top.document.getElementById("content_" + questionListCode).contentWindow.location.reload();
	top.createQuestionBox.hide();
	var isNextCreate = '${question.isNextCreate}';
	if(isNextCreate == '1' && isNextCreate != ""){
		top.createQuestionTypeBox.show();
	}else{
		top.createQuestionTypeBox.hide();
	}
</script>
<table align="center" height="100%" width="100%" border="0" cellpadding="0" cellspacing="0" class="tablemain">
	<tr>
		<td align="center" valign="top">
			<table align="center" border="0" cellpadding="0" cellspacing="0" class="tablemain">
				<tr>
					<input type="hidden" name="isNextCreate" id="isNextCreate" value="${isNextCreate }" />
					<td align="center" height="150">&nbsp;</td>
				</tr>
				<tr>
					<td align="center">
						<table border="0" cellpadding="0" cellspacing="0" >
							<tr>
								<td >
									<table width="100%" border="0" cellpadding="0" cellspacing="1" >
										<tr>
											<td align="center">
						                    <img align="absmiddle" src="${base}/${common}/${style}/images/icon/success.png">
						                     <msg:message code="question.save.success"></msg:message>		                    
				                            </td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="center" height="20"></td>
							</tr>				
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>