var basePath = $('#basePath').val();
var producttypeHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '活动类型信息',
	winWidth: '400px', 
	winHeight: '70px',
	primaryKey: 'typeId',
	urls:{ 
		list: basePath+'productTypeActivity/listActivityType',//列表
		addBefore: basePath+'common/management/producttype/addProductTypeActivity', //添加之前 
		editBefore: basePath+'productTypeActivity/editBefore',//编辑之前
		enabled: basePath+'productTypeActivity/enabled',//启用
		disabled: basePath+'productTypeActivity/disabled',//禁用
		deleted: basePath+'productTypeActivity/delete',//删除 
	}
},{ 
	 
}); 
var utilsHandle = new UtilsHandle({ 
	basePath: basePath,
	chooseCity: {
		'object': $('input[name=areaName]'), 
		'width': '70%',
		'height': '600px', 
		'areaId': $(':hidden[name=areaId]').val(), 
		'callback':function(areaId, areaName){ 
			$(':hidden[name=areaId]').val(areaId); 
			$(':input[name=areaName]').val(areaName);
		}
	}, 
}); 
$(function(){ 
	var colNames = ['typeId', '活动类型名称', '添加时间', '更新时间', '状态', '操作'];
	var colModel = [ 
		{name: 'typeId', index: 'typeId', width: 30, align: 'center'}, 
		{name: 'typeName', index: 'typeName', width: 70, align: 'center'}, 
		{name: 'createTime', index: 'createTime', width: 50, align: 'center'}, 
		{name: 'updateTime', index: 'updateTime', width: 50, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: producttypeHandle.edit(\'' + rowObject.typeId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: producttypeHandle.disabled(\'' + rowObject.typeId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: producttypeHandle.enabled(\'' + rowObject.typeId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '活动类型列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	producttypeHandle.init(config); 
}); 
