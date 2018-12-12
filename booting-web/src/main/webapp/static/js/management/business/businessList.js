var basePath = $("#basePath").val();
var businessHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '行业',
	winWidth: '556px',
	winHeight: '260px',
	primaryKey: 'businessId',
	urls:{
		list: basePath+'business/list',        //列表
		addBefore: basePath+'common/management/business/addBusiness',   //添加之前
		editBefore: basePath+'business/editBefore',          //编辑之前
		enabled: basePath+'business/enabled',      //启用
		disabled: basePath+'business/disabled',    //禁用
	}
},{});
$(function(){
	var colNames = [ 'businessId', '行业名称', '是否可用', '创建时间', '操作' ];
	var colModel = [
		{name: 'businessId', index: 'businessId', width: 55, align: "center"}, 
		{name: 'businessName', index: 'businessName', width: 90, align: "center"}, 
		{name: 'enabled', index: 'enabled', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
		{width: 150, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: businessHandle.edit(\'' + rowObject.businessId + '\');" >编辑</a>';
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: businessHandle.disabled(\'' + rowObject.businessId + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: businessHandle.enabled(\'' + rowObject.businessId + '\');" >启用</a>';
			}
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "行业列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	businessHandle.init(config);
});