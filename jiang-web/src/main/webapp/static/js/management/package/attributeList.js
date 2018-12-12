var basePath = $("#basePath").val();
var attrHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '属性信息',
	winWidth: '456px',
	winHeight: '145px',
	primaryKey: 'attributeId',
	urls:{
		list: basePath+'package/listAttribute',        //列表
		addBefore: basePath+'common/management/package/addAttribute',   //添加之前
		editBefore: basePath+'package/editAttributeBefore',          //编辑之前
		enabled: basePath+'package/enabledAttribute',      //启用
		disabled: basePath+'package/disabledAttribute',    //禁用
		deleted: basePath+'package/deletedAttribute',      //删除
	}
},{});
$(function(){
	var colNames = [ 'attributeId', '属性名称', '是否可用','创建时间', '操作' ];
	var colModel = [
		{name: 'attributeId', index: 'attributeId', width: 55, align: "center"}, 
		{name: 'attributeName', index: 'attributeName', width: 90, align: "center"}, 
		{name: 'enabled', index: 'enabled', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
		{width: 150, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: attrHandle.edit(\'' + rowObject.attributeId + '\');" >编辑</a>';
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: attrHandle.disabled(\'' + rowObject.attributeId + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: attrHandle.enabled(\'' + rowObject.attributeId + '\');" >启用</a>';
			}
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "属性列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	attrHandle.init(config);
});