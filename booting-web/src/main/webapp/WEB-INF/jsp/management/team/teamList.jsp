<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>企业列表</title>
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
				<span>所有者：<input type="text" name="userName" placeholder="请输入要查找的所有者"></span>
				<span>球队名称：<input type="text" name="teamName" placeholder="请输入要查找的球队名称"></span>
				<span>所属企业：<input type="text" name="companyName" placeholder="请输入要查找的企业名称"></span>
				<span>是否可用：<select name="enabled"><option value="">全部</option><option value="1">可用</option><option value="0">禁用</option></select> </span>
				<a onclick="teamHandle.query()" class="button blue">查询</a>
				<a onclick="teamHandle.reset()" class="button grey">清空</a>
			</form>
		</div>
		<div class="data-container">
				<div class="btnGroup">
<!-- 				<a onclick="teamHandle.addNew()" class="button grey">新增</a> -->
				<a onclick="teamHandle.enabled()" class="button blue">启用</a>
				<a onclick="teamHandle.disabled()" class="button yellow">禁用</a>
<!-- 				<a onclick="teamHandle.remove()" class="button red">删除</a> -->
				</div>
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/js/management/common.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/team/teamList.js?4"></script>
</body>
</html>