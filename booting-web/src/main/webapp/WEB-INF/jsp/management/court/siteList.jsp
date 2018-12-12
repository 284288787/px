<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>场地列表</title>
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
				<span>场地名称：<input type="text" name="siteName" placeholder="请输入要查找的场地名称"></span>
				<span>是否可用：<select name="enabled"><option value="">全部</option><option value="1">可用</option><option value="0">禁用</option></select> </span>
				<a onclick="siteHandle.query()" class="button blue">查询</a>
				<a onclick="siteHandle.reset()" class="button grey">清空</a>
			</form>
		</div>
		<div class="data-container">
				<div class="btnGroup">
				<a class="button grey" id="addNew">新增</a>
				<a onclick="siteHandle.enabled()" class="button blue">启用</a>
				<a onclick="siteHandle.disabled()" class="button yellow">禁用</a>
<!-- 				<a onclick="siteHandle.remove()" class="button red">删除</a> -->
				</div>
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/js/management/common.js?1"></script>
	<script type="text/javascript">
	var basePath = $("#basePath").val();
	var args=artDialog.data("params");
	var minSizes={1:'整场',2:'半场',4:'1/4场',8:'1/8场'}
	var siteHandle = new ListHandle({
		basePath: basePath,
		tableId: '#grid-table',
		pagerId: '#grid-pager',
		formId: '#queryForm',
		entityName: '场地信息',
		winWidth: '556px',
		winHeight: '260px',
		primaryKey: 'siteId',
		urls:{
			list: basePath+'court/listSite?courtId='+args.courtId,        //列表
			addBefore: basePath+'common/management/court/addSite',   //添加之前
			editBefore: basePath+'court/editBefore',          //编辑之前
			enabled: basePath+'court/enabledSite',      //启用
			disabled: basePath+'court/disabledSite',    //禁用
		}
	},{});
	args.courtHandle.siteHandle=siteHandle;
	$(function(){
		$("#addNew").click(function(){
			siteHandle.addNew(args);
		});
		var colNames = [ 'siteId', '场地名称', '场地规格', '是否分割', '是否可用', '创建时间', '操作' ];
		var colModel = [
	        {name: 'siteId', index: 'siteId', width: 35, align: "center"}, 
	        {name: 'siteName', index: 'siteName', width: 35, align: "center"}, 
			{name: 'specification', index: 'specification', width: 40, align: "center", formatter: 'select', editoptions:{value:"1:11人场;2:8人场;3:5人场"}}, 
			{name: 'knifing', index: 'knifing', width: 40, align: "center", formatter: 'select', editoptions:{value:"1:可划分成2个小场地;2:不可划分"}}, 
			{name: 'enabled', index: 'enabled', width: 30, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
			{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
			{width: 70, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
				var temp = '';
				if(rowObject.enabled==1){
					temp += '<a class="linetaga" href="javascript: siteHandle.disabled(\'' + rowObject.siteId + '\');" >禁用</a>';
				}else{
					temp += '<a class="linetaga" href="javascript: siteHandle.enabled(\'' + rowObject.siteId + '\');" >启用</a>';
				}
				return temp;
			}}
		];
		var rowList = [10, 20, 30, 50];
		var rownumbers = true;
		var multiselect = true;
		var config={caption: "场地列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
		window.setTimeout(function(){
			siteHandle.init(config);
		}, 200);
	});
	</script>
</body>
</html>