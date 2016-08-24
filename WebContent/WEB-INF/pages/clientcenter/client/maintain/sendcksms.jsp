<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript">
$(document).ready(function(){
	$("#btnok").click(function(){
		var sms = $('#sms').val();
		if(sms == null || sms=="" || sms.length==0){
			alert("请输入短信内容");
			return;
		}
		var url = "${base}/clientcenter/client/maintain/dosendcksms.json" + "";
		$.ajax({
			url:url,
			data: {id:$('#clientId').val(),c:$('#c').val(),sms:sms},
			dataType: "json",
			type:'post',
			timeout:'60000',
			dataType:'json',
			success:function(jsonData,textStatus){
				if (textStatus == "success"){
					$('#sms').val("");
					alert('短信发送成功！');
				}else{
					alert('短信发送失败！');
				}
			}
		});
	});
});

</script>
</head>
<body class="skinMain">
	<table width="100%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
		<tr>
			<td width="100%">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinNav">
					<tr>
						<th width="2%"><img src="${base}/${common}/${style}/images/nav/bg_07.gif" width="10" height="9" /></th>
						<th width="98%"><msg:message code="navigation.title"/>&nbsp;${navigation}</th>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<form:form method="post" action="" commandName="client" >
					<input type="hidden" id="c" name="c" value="${client.c}"/>
					<input type="hidden" id="clientId" name="id" value="${client.id}" />
					<input type="hidden" name="prompt" value="${client.name}" />
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="skinLayout">
						<tr>
							<td width="15%" align="right" class="skinLayout_info">短信示例<msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								 &nbsp短信模板：尊敬的会员{1}。请您在三日内进行还款。<br>
								 &nbsp示例： 尊敬的会员xxx您好，{&nbsp您本月消费金额为xx，本月消费利息为xx，应还款xx，如未能全额还款，需偿还利息xx&nbsp}，请您在三日内进行还款。<br>
								 &nbsp<span style="color:red;"><b>注意：您只需要填写{}内的内容，注意不包括{}；&nbsp短信内容（包括标点符号）不能超过65个字。</b></span>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" class="skinLayout_info">短信内容<msg:message code="system.common.sign.colon"/></td>
							<td width="85%" class="skinLayout_lable">
								<span>尊敬的会员&nbsp${client.name}&nbsp您好，</span><br>
								<form:input type="text" id="sms" path="sms" placeholder="您本月消费金额为xx，本月消费利息为xx，应还款xx，如未能全额还款，需还利息xx" maxlength="100" style="width: 600px;" /><font color="red">&nbsp;*&nbsp;<form:errors path="sms" delimiter=","/></font><br>
								<span>，请您在三日内进行还款。</span><br>
							</td>
						</tr>

						<tr>
							<td width="15%" align="right">&nbsp;</td>
							<td width="85%" colspan="2" align="left">
								<input id="btnok" type="button" class="btn2" name="btnok" value="发送短信" id="btnok"/>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" class="btn2" onclick="window.location.href='${base}${elf:parentcontroller(requestScope,sessionScope)}'" name="btnback" value="<msg:message code="button.back"/>" id="btnback"/>
							</td>
						</tr>
					</table>
				</form:form>
			</td>
		</tr>
	</table> 
</html>
