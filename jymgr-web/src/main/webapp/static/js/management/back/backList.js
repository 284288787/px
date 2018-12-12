var basePath = $("#basePath").val();
var backHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '建议与反馈',
	winWidth: '80%',
	winHeight: '80%',
	primaryKey: 'sourceId',
	urls:{
		list: basePath+'back/backList',        //列表
	}
},{});
$(function(){
	var colNames = [ 'id', '标题', '联系方式', '反馈内容', '反馈时间'];
	var colModel = [
		{name: 'id', index: 'id', width: 25, align: "center"}, 
		{name: 'title', index: 'title', width: 50, align: "center"}, 
		{name: 'contact', index: 'contact', width: 80, align: "center"}, 
		{name: 'content', index: 'content', width: 180, align: "left"}, 
		{name: 'createTime', index: 'createTime', width: 70, align: "center"}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = false;
	var config={caption: "建议与反馈列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	backHandle.init(config);
});