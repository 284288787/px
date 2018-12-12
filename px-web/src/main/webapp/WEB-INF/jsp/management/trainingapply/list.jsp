<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri ="http://www.springframework.org/security/tags" prefix ="sec"%>
<!DOCTYPE html>
<html>
<head>
	<title>列表</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/jquery-ui-1.12.1/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/jqGrid/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/buttons.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/js/tools/artDialog4.1.2/skins/blue.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/pageList.css?1" />
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
				<span>电话： <input type="text" name="mobile" placeholder="预留的电话"></span>
				<span>身份：<select name="type"><option value="">全部</option><option value="1">幼儿园管理员</option><option value="2">青少年</option><option value="3">教练员</option><option value="4">团体</option></select> </span>
				<a onclick="trainingapplyHandle.query()" class="button blue">查询</a>
				<a onclick="trainingapplyHandle.reset()" class="button grey">清空</a>
			</form>
		</div>
		<div class="data-container">
			<!-- <div class="btnGroup">
				<a onclick="trainingHandle.remove()" class="button yellow">删除</a>
			</div> -->
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/js/management/common.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/trainingapply/list.js"></script>
</body>
</html>