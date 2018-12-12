var basePath = $('#basePath').val();
var customerHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '客户信息',
	winWidth: '60%', 
	winHeight: '50%',
	primaryKey: 'memberId',
	urls:{ 
		list: basePath+'customer/list',//列表
		addBefore: basePath+'common/management/customer/addCustomer', //添加之前 
		editBefore: basePath+'customer/editBefore',//编辑之前
		viewBefore: basePath+'customer/viewBefore',
		enabled: basePath+'customer/enabled',//启用
		disabled: basePath+'customer/disabled',//禁用
		deleted: basePath+'customer/delete',//删除 
	}
},{ 
	 
}); 
$(function(){ 
	var colNames = ['memberId', '机构名称', '机构地址', '联系人', '联系电话', '机构类型', '规模人数', '添加时间', '更新时间', '状态', '操作'];
	var colModel = [ 
		{name: 'memberId', index: 'memberId', width: 30, align: 'center'}, 
		{name: 'orgName', index: 'orgName', width: 70, align: 'center', formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: customerHandle.view(\'' + rowObject.memberId + '\', \''+cellvalue+'\');" >'+cellvalue+'</a>'; 
			return temp; 
		}}, 
		{name: 'orgAddress', index: 'orgAddress', width: 40, align: 'center'}, 
		{name: 'contactName', index: 'contactName', width: 30, align: 'center'}, 
		{name: 'contactPhone', index: 'contactPhone', width: 50, align: 'center'},
		{name: 'type', index: 'type', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:幼儿园;2:小学;3:中学;4:小区;5:其他'}},
		{name: 'peopleNum', index: 'peopleNum', width: 50, align: 'center'}, 
		{name: 'createTime', index: 'createTime', width: 50, align: 'center'}, 
		{name: 'updateTime', index: 'updateTime', width: 50, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: customerHandle.edit(\'' + rowObject.memberId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: customerHandle.disabled(\'' + rowObject.memberId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: customerHandle.enabled(\'' + rowObject.memberId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '客户列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	customerHandle.init(config); 
}); 
