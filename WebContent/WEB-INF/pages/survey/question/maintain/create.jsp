<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<script type="text/javascript">
function sendAddQuestion (type){
	var queID = $('#queID').val();
	var code = $('#c').val();
	
	if(top.createQuestionBox != null && top.createQuestionBox != 'undefined'){
		top.createQuestionBox.hide();
	}
	var url = "${base}/survey/question/maintain/addQuestion?queID=" + queID + "&questionType=" + type + "&c=" + code;
	var dialogContent = "<iframe src='"+ url +"' width='900' height='500' frameborder='0' ></iframe>";
	top.createQuestionBox = new window.parent.Boxy(dialogContent, 
						{title: "<msg:message code='info.prompt'/>",closeText:"<msg:message code='info.prompt.close'/>",modal:true});
	
}
</script>
<table width="100%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
			<tr>
				<td width="100%">
					<form:form method="post" action="save" commandName="question">
						<form:hidden path="c"/>	
						<form:hidden path="queID"/>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="skinLayout" >
							<tr>
								<td  width="25%" align="center" class="skinLayout_info">
									<a href="#" onclick="sendAddQuestion('1')"><msg:message code="question.singlechoice"/></a>
								</td>
								<td  width="25%" align="center" class="skinLayout_info">
									<a href="#" onclick="sendAddQuestion('2')"><msg:message code="question.multiplechoice"/></a>
								</td>
								<td  width="25%" align="center" class="skinLayout_info">
									<a href="#" onclick="sendAddQuestion('3')"><msg:message code="question.blank"/></a>
								</td>
								<td  width="25%" align="center" class="skinLayout_info">
									<a href="#" onclick="sendAddQuestion('4')"><msg:message code="question.shortanswer"/></a>
								</td>
							</tr>
						</table>
					</form:form>
				</td>
			</tr>
</table>
