var basePath = $('#basePath').val();
var applyItemHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '报名项信息',
	winWidth: '350px', 
	winHeight: '100px',
	primaryKey: 'applyItemId',
	urls:{ 
		list: basePath+'applyItem/list',//列表
		addBefore: basePath+'common/management/applyItem/addApplyItem', //添加之前 
		editBefore: basePath+'applyItem/editBefore',//编辑之前
		enabled: basePath+'applyItem/enabled',//启用
		disabled: basePath+'applyItem/disabled',//禁用
		deleted: basePath+'applyItem/delete',//删除 
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
	var colNames = ['applyItemId', '报名项', '添加时间', '是否可用', '操作'];
	var colModel = [ 
		{name: 'applyItemId', index: 'applyItemId', width: 30, align: 'center'}, 
		{name: 'itemName', index: 'itemName', width: 70, align: 'center'}, 
		{name: 'createTime', index: 'createTime', width: 50, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: applyItemHandle.edit(\'' + rowObject.applyItemId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: applyItemHandle.disabled(\'' + rowObject.applyItemId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: applyItemHandle.enabled(\'' + rowObject.applyItemId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '报名项列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	applyItemHandle.init(config); 
}); 
