var basePath = $('#basePath').val();
var franchiseeHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '加盟商信息',
	winWidth: '60%', 
	winHeight: '50%',
	primaryKey: 'memberId',
	urls:{ 
		list: basePath+'franchisee/list',//列表
		addBefore: basePath+'common/management/franchisee/addFranchisee', //添加之前 
		editBefore: basePath+'franchisee/editBefore',//编辑之前
		viewBefore: basePath+'franchisee/viewBefore',
		enabled: basePath+'franchisee/enabled',//启用
		disabled: basePath+'franchisee/disabled',//禁用
		deleted: basePath+'franchisee/delete',//删除 
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
	var colNames = ['memberId', '公司名称', '法人', '联系电话', '加盟商等级', '公司地址', '添加时间', '更新时间', '状态', '操作'];
	var colModel = [ 
		{name: 'memberId', index: 'memberId', width: 30, align: 'center'}, 
		{name: 'companyName', index: 'companyName', width: 70, align: 'center', formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: franchiseeHandle.view(\'' + rowObject.memberId + '\', \''+cellvalue+'\');" >'+cellvalue+'</a>'; 
			return temp; 
		}}, 
		{name: 'legalPerson', index: 'legalPerson', width: 40, align: 'center'}, 
		{name: 'contactPhone', index: 'contactPhone', width: 50, align: 'center'}, 
		{name: 'level', index: 'level', width: 30, align: 'center'}, 
		{name: 'companyAddress', index: 'companyAddress', width: 50, align: 'center'}, 
		{name: 'createTime', index: 'createTime', width: 50, align: 'center'}, 
		{name: 'updateTime', index: 'updateTime', width: 50, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: franchiseeHandle.edit(\'' + rowObject.memberId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: franchiseeHandle.disabled(\'' + rowObject.memberId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: franchiseeHandle.enabled(\'' + rowObject.memberId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '加盟商列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	franchiseeHandle.init(config); 
}); 
