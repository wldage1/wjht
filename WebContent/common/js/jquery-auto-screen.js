//页面自动布满浏览器脚本
 $(document).ready(function(){
	var bodyWidth = window.document.body.clientWidth >= 1024 ? window.document.body.clientWidth : 1024;
	var bodyHeight = window.document.body.clientHeight >= 600 ? window.document.body.clientHeight : 600;
	$("#windowTable").width(bodyWidth);
	$("#windowTable").height(bodyHeight);
	$("#windowTop").height("40");
	$("#windowTab").height("30");
	$("#windowBottom").height("33");
	var rheight = bodyHeight - (40+30+33);
	$("#windowMain").height(rheight);
	$("#zleftmenu").height(rheight - 30 - 8);
	$("#content").height(rheight - 8);
 });
$.autoScreen = function(){
//	//当前窗口可视区宽度
//	var wwidth = $(window).width();
//	//当前窗口可视区高度
//	var wheight = $(window).height();
//	//分辨率宽
//	var swidth = screen.width;
//	//分辨率高
//	var sheight = screen.height;
//	var showWidth = screen.width - 20;
//	var showHeight = screen.height - 120;
	var bodyWidth = document.body.clientWidth;
	var bodyHeight = document.body.clientHeight;
//	//如果分辨率的宽度减去当前窗口可视区宽度大于30
//	//说明窗口属于最小化状态
//	if((swidth -wwidth) < 30){
//		showHeight = wheight;
//		showWidth = swidth - (swidth -wwidth);
//	}
	$("#windowTable").width(bodyWidth);
	$("#windowTable").height(bodyHeight);
	$("#windowTop").height("40");
	$("#windowTab").height("30");
	$("#windowBottom").height("33");
	var rheight = bodyHeight - (40+30+33);
	$("#windowMain").height(rheight);
	$("#zleftmenu").height(rheight - 30 - 8);
	$("#content").height(rheight - 8);
	
};