<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri ="http://www.springframework.org/security/tags" prefix ="sec"%>
<!DOCTYPE html>
<html> 
<head> 
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
				<span>姓名：<input type="text" name="teacherName" placeholder="幼师姓名" > </span> 
				<span>电话：<input type="text" name="teacherMobile" placeholder="幼师电话" > </span> 
				<a onclick="kindergartenTeacherHandle.query()" class="button blue">查询</a> 
				<a onclick="kindergartenTeacherHandle.reset()" class="button grey">清空</a> 
			</form>
		</div> 
		<div class="data-container">
			<div class="btnGroup"> 
				<a onclick="kindergartenTeacherHandle.choose()" class="button grey">确定选择</a>
			</div> 
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div> 
	</div> 
	<input id="basePath" type="hidden" value="${basePath}">
	<input id="teacherId" type="hidden" value="${classDTO.teacherId}">
	<input id="teacherIds" type="hidden" value="${teacherIds}">
	<script type="text/javascript" src="${basePath}static/js/management/common.js"></script> 
	<script type="text/javascript" src="${basePath}static/js/management/utils.js"></script>
	<script type="text/javascript">
	var basePath = $('#basePath').val();
	var teacherId = $('#teacherId').val();
	var teacherIds = $('#teacherIds').val();
	var args = artDialog.data("args");
	var kindergartenTeacherHandle = new ListHandle({ 
		basePath: $('#basePath').val(),
		tableId: '#grid-table',
		pagerId: '#grid-pager',
		formId: '#queryForm',
		entityName: '幼师信息',
		winWidth: '900px', 
		winHeight: '400px',
		primaryKey: 'teacherId',
		urls:{ 
			list: basePath+'teacher/list?schoolId='+args.schoolId+'&classId='+args.classId,//列表
		}
	},{ 
		choose: function(){
			var ids = kindergartenTeacherHandle.getIds();
			var params = {schoolId: args.schoolId, classId: args.classId, teacherIds: ids};
			$.ajax({
				contentType:'application/json', 
				url: basePath+'teacher/saveRelationByClassTeacher', 
				data: JSON.stringify(params), 
				type: 'post', 
				dataType: 'json', 
				success: function(res){ 
					if(res.status=='SUCCESS'){
						artDialog.alert("设置成功");
						args.parentHandle.query();
						art.dialog.close(); 
					}else{
						artDialog.alert(res.errorMessage) 
					} 
				} 
			}); 
		}
	}); 
	$(function(){ 
		var colNames = ['teacherId', '幼儿园', '姓名', '电话', '简介', '是否可用', '创建时间'];
		var colModel = [ 
			{name: 'teacherId', index: 'teacherId', width: 30, align: 'center'}, 
			{name: 'schoolName', index: 'schoolName', width: 70, align: 'center'}, 
			{name: 'teacherName', index: 'teacherName', width: 70, align: 'center'}, 
			{name: 'teacherMobile', index: 'teacherMobile', width: 70, align: 'center'}, 
			{name: 'intro', index: 'intro', width: 70, align: 'center'}, 
			{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
			{name: 'createTime', index: 'createTime', width: 60, align: 'center'}
		]; 
		var rowList = [50];
		var rownumbers = true; 
		var multiselect = true;
		var config={caption: '幼师列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect, callback: function(table){
			var ids=table.jqGrid('getDataIDs');
			$.each(ids, function(i, rowIdx){
				var curChk = $("#"+rowIdx+"").find(":checkbox");
				var rowData = table.jqGrid("getRowData", rowIdx);
				if(rowData.teacherId == teacherId){
					curChk.attr({"checked": true, "disabled": true});
					kindergartenTeacherHandle.setId(rowIdx);
				}else if(teacherIds.indexOf(","+rowData.teacherId+",") != -1){
					curChk.attr({"checked": true});
					kindergartenTeacherHandle.setId(rowIdx);
				}
			});
		}};
		window.setTimeout(function(){kindergartenTeacherHandle.init(config); }, 500);
	}); 
	</script>
</body>
</html>