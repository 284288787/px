var basePath = $("#basePath").val();
var companyHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '企业',
	winWidth: '556px',
	winHeight: '260px',
	primaryKey: 'companyId',
	urls:{
		list: basePath+'company/list',        //列表
		addBefore: basePath+'common/management/company/addCompany',   //添加之前
		editBefore: basePath+'company/editBefore',          //编辑之前
		enabled: basePath+'company/enabled',      //启用
		disabled: basePath+'company/disabled',    //禁用
	}
},{});
function initBusiness(){
	var html='<option value="">全部</option>';
	for(var i in businesses){
		var business=businesses[i];
		html+='<option value="'+business.businessId+'">'+business.businessName+'</option>';
	}
	$("select[name=businessId]").html(html);
}
$(function(){
	initBusiness();
	var colNames = [ 'companyId', '企业名称', '发票抬头', '城市', '行业', '是否可用', '创建时间', '操作' ];
	var colModel = [
		{name: 'companyId', index: 'companyId', width: 35, align: "center"}, 
		{name: 'companyName', index: 'companyName', width: 60, align: "center"}, 
		{name: 'invoiceTitle', index: 'invoiceTitle', width: 100, align: "center"}, 
		{name: 'areaName', index: 'areaName', width: 40, align: "center"}, 
		{name: 'businessName', index: 'businessName', width: 50, align: "center"}, 
		{name: 'enabled', index: 'enabled', width: 30, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
		{width: 150, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: companyHandle.edit(\'' + rowObject.companyId + '\');" >编辑</a>';
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: companyHandle.disabled(\'' + rowObject.companyId + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: companyHandle.enabled(\'' + rowObject.companyId + '\');" >启用</a>';
			}
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "企业列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	companyHandle.init(config);
});