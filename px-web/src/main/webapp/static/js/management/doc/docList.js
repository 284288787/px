var basePath = $("#basePath").val();
var docHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '资源信息',
	winWidth: '80%',
	winHeight: '80%',
	primaryKey: 'sourceId',
	urls:{
		list: basePath+'doc/docList',        //列表
		addBefore: basePath+'common/management/doc/addDoc',   //添加之前
		editBefore: basePath+'doc/editBefore',          //编辑之前
		enabled: basePath+'doc/enabled',      //启用
		disabled: basePath+'doc/disabled',    //禁用
	}
},{});
$(function(){
	var colNames = [ 'artId', '资讯标题', '资讯类型', '修改时间', '是否可用', '位置', '操作' ];
	var colModel = [
		{name: 'docId', index: 'docId', width: 55, align: "center"}, 
		{name: 'title', index: 'title', width: 90, align: "center"}, 
		{name: 'type', index: 'type', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:轮播图;2:文章"}}, 
		{name: 'modifyTime', index: 'modifyTime', width: 80, align: "center"}, 
		{name: 'enabled', index: 'enabled', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'position', index: 'position', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:正常;2:置顶"}}, 
		{width: 150, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: docHandle.edit(\'' + rowObject.docId + '\');" >编辑</a>';
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: docHandle.disabled(\'' + rowObject.docId + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: docHandle.enabled(\'' + rowObject.docId + '\');" >启用</a>';
			}
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "文章列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	docHandle.init(config);
});