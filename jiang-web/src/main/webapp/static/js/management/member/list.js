var basePath = $('#basePath').val();
var memberHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '会员信息',
	winWidth: '90%', 
	winHeight: '90%',
	primaryKey: 'memberId',
	urls:{ 
		list: basePath+'member/list',//列表
		addBefore: basePath+'common/management/member/addMember', //添加之前 
		editBefore: basePath+'member/editBefore',//编辑之前
		enabled: basePath+'member/enabled',//启用
		disabled: basePath+'member/disabled',//禁用
		deleted: basePath+'member/delete',//删除 
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
	var colNames = ['memberId', '手机号', '昵称', '金币余额', '赠送号码', '邀请人', '注册日期'];
	var colModel = [ 
		{name: 'memberId', index: 'memberId', width: 30, align: 'center'}, 
		{name: 'mobile', index: 'mobile', width: 70, align: 'center'}, 
		{name: 'name', index: 'name', width: 60, align: 'center'}, 
		{name: 'goldNums', index: 'goldNums', width: 70, align: 'center'}, 
		{name: 'ballNums', index: 'ballNums', width: 70, align: 'center'}, 
		{name: 'inviterId', index: 'inviterId', width: 50, align: 'center'}, 
		{name: 'createTime', index: 'createTime', width: 50, align: 'center'}
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '会员列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	memberHandle.init(config); 
}); 
