var basePath = $('#basePath').val();
var curriculumHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '课程信息',
	winWidth: '60%', 
	winHeight: '90%',
	primaryKey: 'curriculumId',
	urls:{ 
		list: basePath+'curriculum/list',//列表
		addBefore: basePath+'common/management/curriculum/addCurriculum', //添加之前 
		editBefore: basePath+'curriculum/editBefore',//编辑之前
		viewBefore: basePath+'curriculum/viewBefore',//编辑之前
		enabled: basePath+'curriculum/enabled',//启用
		disabled: basePath+'curriculum/disabled',//禁用
		deleted: basePath+'curriculum/delete',//删除 
	}
},{ 
	 
}); 
$(function(){ 
	var colNames = ['curriculumId', '课程类型', '课程名称', '课程价格', '添加时间', '更新时间', '状态', '操作'];
	var colModel = [ 
		{name: 'curriculumId', index: 'curriculumId', width: 30, align: 'center'}, 
		{name: 'typeName', index: 'typeName', width: 30, align: 'center'}, 
		{name: 'name', index: 'name', width: 70, align: 'center', formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: curriculumHandle.view(\'' + rowObject.curriculumId + '\', \''+cellvalue+'\');" >'+cellvalue+'</a>'; 
			return temp; 
		}},
		{name: 'price', index: 'price', width: 50, align: 'center', formatter: function(cellvalue, options, rowObject){ 
			return (cellvalue / 100.0).toFixed(2); 
		}}, 
		{name: 'createTime', index: 'createTime', width: 50, align: 'center'}, 
		{name: 'updateTime', index: 'updateTime', width: 50, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: curriculumHandle.edit(\'' + rowObject.curriculumId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: curriculumHandle.disabled(\'' + rowObject.curriculumId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: curriculumHandle.enabled(\'' + rowObject.curriculumId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '课程列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	curriculumHandle.init(config); 
}); 
