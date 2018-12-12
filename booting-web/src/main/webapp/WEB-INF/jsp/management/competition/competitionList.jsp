<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri ="http://www.springframework.org/security/tags" prefix ="sec"%>
<!DOCTYPE html>
<html>
<head>
	<title>赛事列表</title>
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
				<span>发起方手机号：<input type="text" name="initiatorMobile" placeholder=""></span>
				<span>应战方手机号：<input type="text" name="challengerMobile" placeholder=""></span>
				<span>赛事状态：<select name="status">
					<option value="">全部</option>
					<option value="1">已发起</option>
					<option value="2">已建立</option>
					<option value="3">发起方取消</option>
					<option value="4">应战方取消</option>
					<option value="5">系统取消</option>
					<option value="6">已结束</option>
					<option value="7">已删除</option>
				</select> </span>
				<a onclick="competitionHandle.query()" class="button blue">查询</a>
				<a onclick="competitionHandle.reset()" class="button grey">清空</a>
			</form>
		</div>
		<div class="data-container">
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/js/management/common.js?1"></script>
	<script type="text/javascript" src="${basePath}static/js/management/competition/competitionList.js?2"></script>
</body>
</html>