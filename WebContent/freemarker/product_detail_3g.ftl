<!DOCTYPE html>
<html>
<head>
	<title>${productName}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="../commons/css/jquery.mobile-1.3.1.css" />
	<script src="../commons/js/jquery.js"></script>
	<script src="../commons/js/jquery.mobile-1.3.1.js"></script>
</head>
<body>
<div data-role="page">
	<div data-role="header" data-theme="b">
		<h1>${productType}</h1>
	</div>
	<div data-role="content">
		<p class='ui-li-desc-blue'><strong>产品名称</strong></p>
		<p>${productName}</p>
		<#list atrribute as at>
		<p class='ui-li-desc-blue'><strong>${at.chineseName}</strong></p>
		<p>${at.value}</p>
		</#list>
	</div>
</div>
</body>
</html>