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
			<form action="${basePath}" method="post" id="queryForm"> 
				<span>所属幼儿园：<input type="text" name="schoolName" placeholder="选择所属幼儿园" readonly><input type="hidden" name="schoolId" > </span>
				<span>幼儿姓名：<input type="text" name="name" placeholder="输入幼儿姓名" > </span> 
				<span>监护人电话：<input type="text" name="guardianMobile" placeholder="输入监护人电话" > </span> 
				<span>是否可用：<select name="enabled"><option value="">全部</option><option value="1" >可用</option><option value="0" >禁用</option></select> </span> 
				<a onclick="kindergartenStudentHandle.query()" class="button blue">查询</a> 
				<a onclick="kindergartenStudentHandle.reset()" class="button grey">清空</a> 
			</form>
		</div> 
		<div class="data-container"> 
				<div class="btnGroup"> 
				<a onclick="kindergartenStudentHandle.addNew()" class="button grey">新增</a>
				<a onclick="kindergartenStudentHandle.enabled()" class="button blue">启用</a> 
				<a onclick="kindergartenStudentHandle.disabled()" class="button yellow">禁用</a>
				<a onclick="kindergartenStudentHandle.remove()" class="button yellow">删除</a>
				<a onclick="kindergartenStudentHandle.exportModel()" class="button red">导出模板</a>
				<a onclick="kindergartenStudentHandle.importData()" class="button red">导入幼儿信息</a>
				</div> 
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div> 
	</div> 
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/js/management/common.js"></script> 
	<script type="text/javascript" src="${basePath}static/js/management/utils.js?1"></script>
	<script type="text/javascript" src="${basePath}static/js/management/kindergartenStudent/list.js?2"></script>
</body>
</html>