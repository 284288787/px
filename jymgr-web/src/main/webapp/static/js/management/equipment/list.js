var basePath = $('#basePath').val();
var equipmentHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '设备信息',
	winWidth: '60%', 
	winHeight: '90%',
	primaryKey: 'equipmentId',
	urls:{ 
		list: basePath+'equipment/list',//列表
		addBefore: basePath+'common/management/equipment/addEquipment', //添加之前 
		editBefore: basePath+'equipment/editBefore',//编辑之前
		viewBefore: basePath+'equipment/viewBefore',//编辑之前
		enabled: basePath+'equipment/enabled',//启用
		disabled: basePath+'equipment/disabled',//禁用
		deleted: basePath+'equipment/delete',//删除 
	}
},{ 
	 
}); 
$(function(){ 
	var colNames = ['equipmentId', '设备类型', '设备名称', '设备价格', '添加时间', '更新时间', '状态', '操作'];
	var colModel = [ 
		{name: 'equipmentId', index: 'equipmentId', width: 30, align: 'center'}, 
		{name: 'typeName', index: 'typeName', width: 30, align: 'center'}, 
		{name: 'name', index: 'name', width: 70, align: 'center', formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: equipmentHandle.view(\'' + rowObject.equipmentId + '\', \''+cellvalue+'\');" >'+cellvalue+'</a>'; 
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
			temp += '<a class="linetaga" href="javascript: equipmentHandle.edit(\'' + rowObject.equipmentId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: equipmentHandle.disabled(\'' + rowObject.equipmentId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: equipmentHandle.enabled(\'' + rowObject.equipmentId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '设备列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	equipmentHandle.init(config); 
}); 
