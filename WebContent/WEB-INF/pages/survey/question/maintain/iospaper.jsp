<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text ml; charset=utf-8" />
<html>
<head>
<title><msg:message  code="questionnaire.survey"/></title>
<script type="text/javascript">
function trim(str){
	str=str.replace(/(^[\s\u3000]*)|([\s\u3000]*$)/g, "");
    return str.replace(/(^\s*)|(\s*$)/g, "");  
} 
function isOptionChecked(optionName){
	var options = document.getElementsByName("option_" + optionName);
	for(var j=0;j<options.length;j++){
		if(options[j].checked){
			return true;
		}
	};
	  alert("带*号的为必填项,请填写！");
	  return false;
}

function checkForm(form){
	var requireChoiceIds = document.getElementById('questionnaireChoiceRequiredEls').getAttribute('els');
	var requireChoiceIdsArray = requireChoiceIds.split(",");
	for(var i=0;i<requireChoiceIdsArray.length;i++){
		if(requireChoiceIdsArray[i] != "" && requireChoiceIdsArray[i] != null && requireChoiceIdsArray[i]!='null' && !isOptionChecked(requireChoiceIdsArray[i])){
			return false;
		}
	}
	
	var text = document.getElementsByTagName("INPUT");
	for(var i=0;i<text.length;i++){
		if(text[i].type == "text"){
			var requiredFlag = text[i].getAttribute('requiredFlag');
			//检查必填的填空题是否填写
			if(requiredFlag == 1 && text[i].value == ""){
				alert("带*号的为必填项,请填写！");
				text[i].focus();
				return false;
			}
			var minlangth = text[i].getAttribute('minlength');
			//检查输入是否小于规定的字数
			if(minlangth != null && text[i].value != ""){
				if(minlangth > text[i].value.length){
					alert("字数不能少于"+minlangth+"个！");
					text[i].focus();
					return false;
				}
			}
		}
	}
	var textarea = document.getElementsByTagName("TEXTAREA");
	for(var i=0;i<textarea.length;i++){
		var requiredFlag_2 =  textarea[i].getAttribute('requiredFlag');
		//检查必填的简答题是否填写
		if(requiredFlag_2 == 1 && trim(textarea[i].value) == ""){
			alert("带*号的为必填项,请填写！");
			textarea[i].focus();
			return false;
		}
		var minlength = textarea[i].getAttribute('minlength');
		//检查输入是否小于规定的字数
		if(minlength != null && trim(textarea[i].value) != ""){
			if(minlength > textarea[i].value.length){
				alert("字数不能少于"+minlength+"个！");
				textarea[i].focus();
				return false;
			}
		}
	}	
	
	return true;
}
function closePreviewPage(){
	if(window.confirm('您确定关闭问卷窗口?')) {window.close();}
}

function paperSubmit(form){
	if(checkForm(form)){
		 var j = elementValues(form);
		 var url="couldNotYiYang";
		 url=url+j;
		 url=url+"buYiYang";
		 document.location = url;
	}
}

function elementValues(theForm){
	 var jsonStr = '';
	 var compareTemp = new Object();
	 var form = theForm || event.srcElement;
	 var count = form.elements.length;
	 for(var i=0;i<count;i++){
		 with(form.elements[i]){
			if(tagName=="TEXTAREA"){
				if( jsonStr == ''){
					jsonStr = name + ':"' + value +'" ';
				}else{
					jsonStr += ',' + name + ':"' + value +'" ';
				}
			}else if(type == "text"){
				if( jsonStr == ''){
					jsonStr = name + ':"' + value +'" ';
				}else{
					jsonStr += ',' + name + ':"' + value +'" ';
				}
			}else if(type == "radio" && checked){
				if( jsonStr == ''){
					jsonStr = name + ':"' + value  +'" ';
				}else{
					jsonStr += ',' + name + ':"' + value  +'" ';
				}
					
			}else if (type == "checkbox" && checked){
					if(checkInObject(name,compareTemp)){
						compareTemp[name] +=  "," + value;
					}else{
						compareTemp[name] = value;
					}
			}
		 }
	 }
	 
	 var value = str2json("{"+jsonStr+"}");
		for ( var k in compareTemp ){ 
			value[k] = compareTemp[k];
		}
		value.queid = '${questionnaire.id }';
		//value.JoinTime  = '';
		//value.JoinTime  = '${JoinTime}';
	return obj2str(value);
}

function checkInObject(name,obj){
	for ( var p in obj ){ 
		if(name == p){
			return true;
		}
	}
	return false;
}
function obj2str(o){  
    var r = [];  
    if(typeof o =="string") return "\""+o.replace(/([\'\"\\])/g,"\\$1").replace(/(\n)/g,"\\n").replace(/(\r)/g,"\\r").replace(/(\t)/g,"\\t")+"\"";  
    if(typeof o =="undefined") return "";  
    if(typeof o == "object"){  
        if(o===null) return "null";  
        else if(!o.sort){  
            for(var i in o)  
                r.push(i+":"+obj2str(o[i]));
            r="{"+r.join()+"}"  ;
        }else{  
            for(var i =0;i<o.length;i++)  
                r.push(obj2str(o[i]));  
            r="["+r.join()+"]" ; 
        }  
        return r;  
    }  
    return o.toString();  
} 

function str2json(obj){
	return eval('(' + obj + ')');
}
</script>
<style>
.layout {
  margin: 0 auto;
  width: 100%;
}
.clearfix:after {
    clear: both;
    content: "\0020";
    display: block;
    height: 0;
}
.clearfix{
   *zoom:1;
}

.que {
	background: #fff;
	
}
.que .title h4{
	color: #CC0000;
    font-size: 24px;
    font-weight: bold;
    letter-spacing: -1px;
    margin: 0 0 0.3em;
    padding: 10px 20px 0;
    text-align: center;
}
.que .title h2{
	color: #000000;
    font-size: 18px;
    font-weight: bold;
    letter-spacing: -1px;
    margin: 0 0 0.3em;
    padding: 0 20px;
    text-align: center;
}

.que .question {
	color: #00568F;
    font-family: verdana;
    font-size: 14px;
    font-weight: bold;
    height: 35px;

}

.que .question .questiontype{
	color: #CF1100;
    font-size: 12px;
}

.note {
    color: #555555;
    font-family: verdana;
    font-size: 14px;
    font-style: italic;
    font-weight: normal;
    padding-left: 10px;
}
.closePreviewPage{
   float: right;
   font-size: 16px;
   padding: 10px;
}
</style>
</head>
<body>
<form:questionnairePreview questionList="${questionList }" questionnaire="${questionnaire }" type="ios"></form:questionnairePreview>
</body>
</html>