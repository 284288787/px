var basePath = $('#basePath').val();
var activityHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '活动信息',
	winWidth: '60%', 
	winHeight: '90%',
	primaryKey: 'activityId',
	urls:{ 
		list: basePath+'activity/list',//列表
		addBefore: basePath+'common/management/activity/addActivity', //添加之前 
		editBefore: basePath+'activity/editBefore',//编辑之前
		viewBefore: basePath+'activity/viewBefore',//编辑之前
		enabled: basePath+'activity/enabled',//启用
		disabled: basePath+'activity/disabled',//禁用
		deleted: basePath+'activity/delete',//删除 
	}
},{ 
	 
}); 
$(function(){ 
	var colNames = ['activityId', '活动类型', '活动名称', '活动价格', '有效期', '生效时间', '失效时间', '是否可售', '添加时间', '更新时间', '状态', '操作'];
	var colModel = [ 
		{name: 'activityId', index: 'activityId', width: 30, align: 'center'}, 
		{name: 'typeName', index: 'typeName', width: 30, align: 'center'}, 
		{name: 'name', index: 'name', width: 70, align: 'center', formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: activityHandle.view(\'' + rowObject.activityId + '\', \''+cellvalue+'\');" >'+cellvalue+'</a>'; 
			return temp; 
		}},
		{name: 'price', index: 'price', width: 50, align: 'center', formatter: function(cellvalue, options, rowObject){ 
			return (cellvalue / 100.0).toFixed(2); 
		}}, 
		{name: 'state', index: 'state', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:永久有效;2:限时有效'}}, 
		{name: 'effectiveTime', index: 'effectiveTime', width: 50, align: 'center', formatter:'date', formatoptions: {newformat:'Y-m-d'}}, 
		{name: 'failureTime', index: 'failureTime', width: 50, align: 'center', formatter:'date', formatoptions: {newformat:'Y-m-d'}}, 
		{name: 'valid', index: 'valid', width: 30, align: 'center', formatter:'select', editoptions:{value:'1:是;0:否'}}, 
		{name: 'createTime', index: 'createTime', width: 50, align: 'center'}, 
		{name: 'updateTime', index: 'updateTime', width: 50, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: activityHandle.edit(\'' + rowObject.activityId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: activityHandle.disabled(\'' + rowObject.activityId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: activityHandle.enabled(\'' + rowObject.activityId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '活动列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	activityHandle.init(config); 
}); 
