var basePath = $('#basePath').val();
var kindergartenHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '幼儿园信息',
	winWidth: '800px', 
	winHeight: '500px',
	primaryKey: 'schoolId',
	urls:{ 
		list: basePath+'kindergarten/list',//列表
		addBefore: basePath+'common/management/kindergarten/addKindergarten', //添加之前 
		editBefore: basePath+'kindergarten/editBefore',//编辑之前
		enabled: basePath+'kindergarten/enabled',//启用
		disabled: basePath+'kindergarten/disabled',//禁用
		deleted: basePath+'kindergarten/delete',//删除 
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
	var colNames = ['schoolId', '登陆帐号', '幼儿园名称', '联系人', '联系电话', '座机', '地区', '详细地址', '简介', '是否可用', '创建时间', '操作'];
	var colModel = [ 
		{name: 'schoolId', index: 'schoolId', width: 30, align: 'center'}, 
		{name: 'account', index: 'account', width: 70, align: 'center'}, 
		{name: 'name', index: 'name', width: 70, align: 'center'}, 
		{name: 'linkman', index: 'linkman', width: 50, align: 'center'}, 
		{name: 'mobile', index: 'mobile', width: 70, align: 'center'}, 
		{name: 'tel', index: 'tel', width: 70, align: 'center'}, 
		{name: 'areaName', index: 'areaName', width: 50, align: 'center'}, 
		{name: 'address', index: 'address', width: 120, align: 'center'}, 
		{name: 'intro', index: 'intro', width: 160, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{name: 'createTime', index: 'createTime', width: 60, align: 'center'}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: kindergartenHandle.edit(\'' + rowObject.schoolId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: kindergartenHandle.disabled(\'' + rowObject.schoolId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: kindergartenHandle.enabled(\'' + rowObject.schoolId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '幼儿园列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	kindergartenHandle.init(config); 
}); 
