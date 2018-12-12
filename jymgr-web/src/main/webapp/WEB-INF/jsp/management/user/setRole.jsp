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
				<span>角色：<input type="text" name="roleRemark"> </span>
				<a onclick="urHandle.query()" class="button blue">查询</a>
				<a onclick="urHandle.reset()" class="button grey">清空</a>
			</form>
		</div>
		<div class="data-container">
			<div class="btnGroup">
				<a onclick="urHandle.setRole()" class="button grey" id="enterBtn">确认分配给用户</a>
			</div>
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/js/management/common.js?2"></script>
	<script type="text/javascript">
	var basePath = $("#basePath").val();
	var params = artDialog.data("params");
	var urHandle = new ListHandle({
		basePath: $("#basePath").val(),
		tableId: '#grid-table',
		pagerId: '#grid-pager',
		formId: '#queryForm',
		primaryKey: 'roleId',
		urls:{
			list: basePath+'userAccount/setRole/'+params.userId,        //列表
		}
	},{
		setRole: function(){
			var ids = urHandle.getIds();
			$.ajax({
				url: basePath+'userAccount/saveRoleRelation',
				data: {userId: params.userId, roleIds: ids},
				type: 'post',
				dataType: 'json',
				success: function(res){
					if(res.status=='SUCCESS'){
						artDialog.alert("分配成功")
					}else{
						artDialog.alert(res.errorMessage)
					}
				}
			});
		}
	});
	$(function(){
		$("#enterBtn").text("确认分配给用户 " + params.name);
		var colNames = ['roleId', 'userId', '角色', '角色编码'];
		var colModel = [
			{name: 'roleId', index: 'roleId', hidden: true},
			{name: 'userId', index: 'userId', hidden: true},
			{name: 'roleRemark', index: 'roleRemark', align: "left", sortable: false}, 
			{name: 'roleName', index: 'roleName', width: 90, align: "center", sortable: false}
		];
		var rowList = [10, 20, 30];
		var rownumbers = true;
		var multiselect = true;
		var config={caption: "", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect, height: '430px', callback: function(table){
			var ids=table.jqGrid('getDataIDs');
			$.each(ids, function(i, rowIdx){
				var curChk = $("#"+rowIdx+"").find(":checkbox");
				var rowData = table.jqGrid("getRowData", rowIdx);
				if(rowData.userId){
					curChk.attr("checked", true);
					urHandle.setId(rowIdx);
				}
			});
		}};
		window.setTimeout(function(){
			urHandle.init(config);
		}, 100)
	});
	</script>
</body>
</html>