var basePath = $('#basePath').val();
var studyAddressHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '校区信息',
	winWidth: '350px', 
	winHeight: '200px',
	primaryKey: 'addrId',
	urls:{ 
		list: basePath+'studyAddress/list',//列表
		addBefore: basePath+'common/management/studyAddress/addStudyAddress', //添加之前 
		editBefore: basePath+'studyAddress/editBefore',//编辑之前
		enabled: basePath+'studyAddress/enabled',//启用
		disabled: basePath+'studyAddress/disabled',//禁用
		deleted: basePath+'studyAddress/delete',//删除 
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
	var colNames = ['addrId', '校区名', '前端显示', '添加时间', '是否可用', '操作'];
	var colModel = [ 
		{name: 'addrId', index: 'addrId', width: 30, align: 'center'}, 
		{name: 'addrName', index: 'addrName', width: 70, align: 'center'}, 
		{name: 'viewFront', index: 'viewFront', width: 40, align: 'center', formatter: 'select', editoptions:{value:'1:是;0:否'}}, 
		{name: 'createTime', index: 'createTime', width: 50, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: studyAddressHandle.edit(\'' + rowObject.addrId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: studyAddressHandle.disabled(\'' + rowObject.addrId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: studyAddressHandle.enabled(\'' + rowObject.addrId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '校区列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	studyAddressHandle.init(config); 
}); 
