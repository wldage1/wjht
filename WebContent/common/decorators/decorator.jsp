<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="decorator" uri="/WEB-INF/tlds/sitemesh-decorator.tld" %>
<html>
<head>
<title><decorator:title /></title>
<link  href="${base}/${common}/${style}/css/skin.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" media="screen" href="${base}/${common}/${style}/css/jquery.ui.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${base}/${common}/${style}/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" href="${base}/${common}/${style}/css/uploadify.css">
<link href="${base}/${common}/${style}/css/ztree.css" rel="stylesheet" type="text/css">
<link href="${base}/${common}/${style}/css/demo.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${base}/${common}/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="${base}/${common}/js/jquery.json-2.2.min.js"></script>
<script src="${base}/${common}/js/jqgrid/grid.locale-cn.js" type="text/javascript"></script>
<script src="${base}/${common}/js/jqgrid/jquery.jqGrid.src.js" type="text/javascript"></script>
<script type="text/javascript" src="${base}/${common}/js/tree/jquery.ztree.core-3.0.min.js"></script>
<script type="text/javascript" src="${base}/${common}/js/tree/jquery.ztree.exedit-3.0.min.js"></script>
<script type="text/javascript" src="${base}/${common}/js/tree/jquery.ztree.excheck-3.0.min.js"></script>
<script  type="text/javascript" language="javascript" src="${base}/${common}/js/uploadify/jquery.uploadify-3.1.min.js"></script>
<script src="${base}/${common}/js/datePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${base}/${common}/js/searchToolBar.js" type="text/javascript"></script>
<script src="${base}/${common}/js/json2.js" type="text/javascript"></script>
<script src="${base}/${common}/js/kindeditor/kindeditor-min.js" type="text/javascript"></script>



<decorator:head />
</head>
<body onload="<decorator:getProperty property="body.onload"></decorator:getProperty>" class="<decorator:getProperty property="body.class"></decorator:getProperty>">
 <decorator:body />   
</body>
</html>
