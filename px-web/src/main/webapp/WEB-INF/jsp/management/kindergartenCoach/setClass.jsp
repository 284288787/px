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
				<span>幼儿园：<input type="text" name="schoolName" placeholder="幼儿园" > <input type="hidden" name="schoolId"></span> 
				<span>班级：<input type="text" name="className" placeholder="班级名称" > </span> 
				<a onclick="chooseHandle.query()" class="button blue">查询</a> 
				<a onclick="chooseHandle.reset()" class="button grey">清空</a> 
			</form>
		</div> 
		<div class="data-container">
			<div class="btnGroup"> 
				<a onclick="chooseHandle.choose()" class="button grey">确定选择</a>
			</div> 
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div> 
	</div> 
	<input id="basePath" type="hidden" value="${basePath}">
	<input id="classIds" type="hidden" value="${classIds}">
	<script type="text/javascript" src="${basePath}static/js/management/common.js"></script> 
	<script type="text/javascript" src="${basePath}static/js/management/utils.js"></script>
	<script type="text/javascript">
	var basePath = $('#basePath').val();
	var classIdsByHeadTeacher = $('#classIdsByHeadTeacher').val();
	var classIds = $('#classIds').val();
	var args = artDialog.data("args");
	var chooseHandle = new ListHandle({ 
		basePath: $('#basePath').val(),
		tableId: '#grid-table',
		pagerId: '#grid-pager',
		formId: '#queryForm',
		entityName: '班级信息',
		winWidth: '800px', 
		winHeight: '400px',
		primaryKey: 'classId',
		urls:{ 
			list: basePath+'class/list',//列表
		}
	},{ 
		choose: function(){
			var ids = chooseHandle.getIds();
			var params = {coachId: args.coachId, classIds: ids};
			$.ajax({
				contentType:'application/json', 
				url: basePath+'coach/saveRelationByClassCoach', 
				data: JSON.stringify(params), 
				type: 'post', 
				dataType: 'json', 
				success: function(res){ 
					if(res.status=='SUCCESS'){
						artDialog.alert("设置成功") 
						art.dialog.close(); 
					}else{
						artDialog.alert(res.errorMessage) 
					} 
				} 
			}); 
		}
	}); 
	var utilsHandle = new UtilsHandle({ 
		basePath: basePath,
		choose: [{
			'title': '选择幼儿园',
			'object': $('input[name=schoolName]'),
			'url': 'kindergarten/chooseSchool',
			'width': '70%',
			'height': '600px', 
			'choosedId': $(':hidden[name=schoolId]'), 
			'callback':function(schoolId, schoolName){ 
				$(':hidden[name=schoolId]').val(schoolId); 
				$(':input[name=schoolName]').val(schoolName);
			}
		}], 
	}); 
	$(function(){ 
		var colNames = ['classId', '幼儿园', '班级名称', '班主任', '班主任电话', '简介', '是否可用', '创建时间'];
		var colModel = [ 
			{name: 'classId', index: 'classId', width: 30, align: 'center'}, 
			{name: 'schoolName', index: 'schoolName', width: 70, align: 'center'}, 
			{name: 'name', index: 'name', width: 70, align: 'center'}, 
			{name: 'teacherName', index: 'teacherName', width: 50, align: 'center'}, 
			{name: 'teacherMobile', index: 'teacherMobile', width: 70, align: 'center'}, 
			{name: 'intro', index: 'intro', width: 70, align: 'center'}, 
			{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
			{name: 'createTime', index: 'createTime', width: 60, align: 'center'}
		]; 
		var rowList = [50];
		var rownumbers = true; 
		var multiselect = true;
		var config={caption: '班级列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect, callback: function(table){
			var classIdsByHeadTeacher;
			$.ajax({
				async: false,
				url: basePath+'coach/getCoachClassIds/'+args.coachId, 
				type: 'post', 
				dataType: 'json', 
				success: function(res){ 
					if(res.status=='SUCCESS'){
						classIdsByHeadTeacher=res.data;
					}else{
						artDialog.alert(res.errorMessage) 
					} 
				} 
			}); 
			var ids=table.jqGrid('getDataIDs');
			$.each(ids, function(i, rowIdx){
				var curChk = $("#"+rowIdx+"").find(":checkbox");
				var rowData = table.jqGrid("getRowData", rowIdx);
				if(classIdsByHeadTeacher.indexOf(","+rowData.classId+",") != -1){
					curChk.attr({"checked": true, "disabled": true});
					chooseHandle.setId(rowIdx);
				}else if(classIds.indexOf(","+rowData.classId+",") != -1){
					curChk.attr({"checked": true});
					chooseHandle.setId(rowIdx);
				}
			});
		}};
		window.setTimeout(function(){chooseHandle.init(config); }, 500);
	}); 

	</script>
</body>
</html>