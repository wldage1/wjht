<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<table width="100%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
			<tr>
				<td width="100%">
					<form:form method="post" action="save" commandName="question" >
						<form:hidden path="c"/>	
						<form:hidden path="queID"/>
						<form:hidden path="isNextCreate"/>
						<form:hidden path="questionType" value="4"/>
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							class="skinLayout">
							<tr>
							<td  colspan="2"><b><msg:message code="question.shortanswer"/></b></td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="question.name" /><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:textarea path="questionName"  rows="6" cssStyle="width:700px;font-size:12px;"/>
									<font color="red">&nbsp;*&nbsp;<br/><form:errors path="questionName" />
									</font>
								</td>
							</tr>
							<tr>
								<td  width="15%" align="right" class="skinLayout_info">
									<msg:message code="question.note" /><msg:message code="system.common.sign.colon"/>
								</td>
								<td width="85%" class="skinLayout_lable">
									<form:textarea path="questionNotes"  rows="3" cssStyle="width:700px;font-size:12px;"/>
								</td>
							</tr>
							<tr>
							<td  colspan="2"><b><msg:message code="question.viewSet" /></b></td>
							</tr>
							<tr>
							    <td colspan="2">
							     	<msg:message code="question.minWordNumber" /><form:input path="minWordNumber" size="1"/>
							     	<msg:message code="question.wordNumber" />&nbsp;&nbsp;
									<msg:message code="question.maxWordNumber" /><form:input path="maxWordNumber" size="1"/>
									<msg:message code="question.wordNumber" />&nbsp;&nbsp;
									<msg:message code="question.fieldWidth" /><form:input path="fieldWidth" size="1"/>&nbsp;&nbsp;
									<msg:message code="question.fieldHeight" /><form:input path="fieldHeight" size="1"/>&nbsp;&nbsp;
									<font color="red">&nbsp;&nbsp;
										<form:errors path="minWordNumber" />&nbsp;&nbsp;&nbsp;&nbsp;
										<form:errors path="maxWordNumber" />&nbsp;&nbsp;&nbsp;&nbsp;
										<form:errors path="fieldWidth" />&nbsp;&nbsp;&nbsp;&nbsp;
										<form:errors path="fieldHeight" />
									</font>
							    </td>
							</tr>
							<tr>
							<td  colspan="2"><b><msg:message code="question.controlSet" /></b></td>
							</tr>
							<tr>
							    <td colspan="2">
		                          <input type="checkbox" name="requiredFlag" value="1"><msg:message code="question.requiredFlag" />
							    </td>
							</tr>
							<tr>
								<td width="85%" colspan="2" align="left">
									<input type="button" class="btn3" name="bt1"
										value="<msg:message code="question.saveNext" />" id="bt1" onclick="saveSubmit(1)"/>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="btn3" name="bt2"
										value="<msg:message code="question.saveEnd" />" id="bt2" onclick="saveSubmit()"/>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="btn2" name="bt3"
										value="<msg:message code="question.close" />" id="bt3" onclick="closes()" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
						</table>
					</form:form>
				</td>
			</tr>
</table>
<script type="text/javascript">
function saveSubmit(saveType){
	var maxWordNumber = $("#maxWordNumber").val();
	var minWordNumber = $("#minWordNumber").val();
	var fieldWidth = $("#fieldWidth").val();
	var fieldHeight = $("#fieldHeight").val();
	if(isNaN($.trim(maxWordNumber))){
		window.parent.Boxy.alert('<msg:message code="question.blank.maxWordNumber.tip" />', null, {title: "<msg:message code='info.prompt'/>"});
		$("#maxWordNumber").focus();
		return false;
	}
	if(isNaN($.trim(minWordNumber))){
		window.parent.Boxy.alert('<msg:message code="question.blank.minWordNumber.tip"/>', null, {title: "<msg:message code='info.prompt'/>"});
		$("#minWordNumber").focus();
		return false;
	}
	if(isNaN($.trim(fieldWidth))){
		window.parent.Boxy.alert('<msg:message code="question.blank.fieldWidth.tip"/>', null, {title: "<msg:message code='info.prompt'/>"});
		$("#fieldWidth").focus();
		return false;
	}
	if(isNaN($.trim(fieldHeight))){
	    window.parent.Boxy.alert('<msg:message code="question.blank.fieldHeight.tip"/>', null, {title: "<msg:message code='info.prompt'/>"});
		$("#fieldHeight").focus();
		return false;
	}
	if(parseInt(maxWordNumber) < parseInt(minWordNumber)){
		window.parent.Boxy.alert('<msg:message code="question.blank.minWordNumber.maxWordNumber.tip"/>', null, {title: "<msg:message code='info.prompt'/>"});
		$("#minWordNumber").focus();
		return false;
	}
//	if(parseInt(maxWordNumber) > parseInt(fieldWidth)){
//		window.parent.Boxy.alert('<msg:message code="question.blank.fieldWidth.maxWordNumber.tip"/>', null, {title: "<msg:message code='info.prompt'/>"});
//		$("#fieldWidth").focus();
//		return false;
//	}
	if(saveType == 1){
		$("#isNextCreate").val("1");
		$("form:first").submit();
	}else{
		$("form:first").submit();
	}
}
function closes(){
	if(top.createQuestionBox != null && top.createQuestionBox != 'undefined'){
		top.createQuestionBox.hide();
		top.createQuestionTypeBox.hide();
	}
}
</script>