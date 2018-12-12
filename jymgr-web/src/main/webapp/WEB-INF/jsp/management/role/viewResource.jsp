<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/jquery-ui-1.12.1/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/jqGrid/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/buttons.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/js/tools/artDialog4.1.2/skins/blue.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/pageList.css" />
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${basePath}static/jqGrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="${basePath}static/jqGrid/js/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
</head>
<body>
	<div class="main-container" id="main-container">
		<div class="search-container">
			<form action="${basePath}" id="queryForm">
				<span>资源名称：<input type="text" name="sourceName" placeholder="模糊匹配资源名称"> </span>
				<span>父级资源：<input type="text" name="parentId" placeholder="父级资源" style="width:60px"></span>
				<span>资源类型：<select name="type"><option value="">全部</option><option value="1">系统</option><option value="2">一级菜单</option><option value="3">子菜单</option><option value="4">按钮</option><option value="5">其他资源</option></select></span>
				<a onclick="rsHandle.query()" class="button blue">查询</a>
				<a onclick="rsHandle.reset()" class="button grey">清空</a>
			</form>
		</div>
		<div class="data-container">
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/js/management/common.js?2"></script>
	<script type="text/javascript">
	var basePath = $("#basePath").val();
	var params = artDialog.data("params");
	var rsHandle = new ListHandle({
		basePath: $("#basePath").val(),
		tableId: '#grid-table',
		pagerId: '#grid-pager',
		formId: '#queryForm',
		primaryKey: 'sourceId',
		urls:{
			list: basePath+'role/viewResource/'+params.roleId,        //列表
		}
	},{});
	$(function(){
		var colNames = ['资源名称', '资源类型', '父级资源', '资源说明', '资源链接'];
		var colModel = [
			{name: 'sourceName', index: 'sourceName', width: 60, align: "center", sortable: false}, 
			{name: 'type', index: 'type', width: 50, align: "center", formatter: 'select', editoptions:{value:"1:系统;2:一级菜单;3:子菜单;4:按钮;5:其他url"}, sortable: false}, 
			{name: 'parentId', index: 'parentId', width: 30, align: "center", sortable: false}, 
			{name: 'sourceRemark', index: 'sourceRemark', width: 70, align: "left", sortable: false}, 
			{name: 'sourceUrl', index: 'sourceUrl', width: 90, align: "left", sortable: false}, 
		];
		var rowList = [10, 20, 30];
		var rownumbers = true;
		var multiselect = false;
		var config={caption: params.name+" 的资源", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect, height: '430px'};
		window.setTimeout(function(){
			rsHandle.init(config);
		}, 100)
	});
	</script>
</body>
</html>