var basePath = $("#basePath").val();
var trainingHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '培训项目信息',
	winWidth: '90%',
	winHeight: '90%',
	primaryKey: 'itemId',
	urls:{
		list: basePath+'trainingItem/list',        //列表
		addBefore: basePath+'trainingItem/addBefore',   //添加之前
		editBefore: basePath+'trainingItem/editBefore',          //编辑之前
		enabled: basePath+'trainingItem/enabled',      //启用
		disabled: basePath+'trainingItem/disabled',    //禁用
		deleted: basePath+'trainingItem/delete',      //删除
	}
},{
	
});
$(function(){
	var colNames = [ 'itemId', '项目类型', '体测课标题', '标题', '类型', '地区', '课程介绍', '是否可用', '开始时间', '结束时间','创建时间', '操作' ];
	var colModel = [
		{name: 'itemId', index: 'itemId', width: 20, align: "center"}, 
		{name: 'subType', index: 'subType', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:普通项目;2:体测课项目"}}, 
		{name: 'physicalClassName', index: 'physicalClassName', width: 70, align: "center"}, 
		{name: 'title', index: 'title', width: 70, align: "center"}, 
		{name: 'type', index: 'type', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:幼儿园足球体能发开课程;2:青少年足球培训;3:教练员培训"}}, 
		{name: 'areaName', index: 'areaName', width: 60, align: "center"}, 
		{name: 'intro', index: 'intro', width: 190, align: "left", hidden:true}, 
		{name: 'enabled', index: 'enabled', width: 40, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'beginTime', index: 'beginTime', width: 60, align: "center"}, 
		{name: 'endTime', index: 'endTime', width: 60, align: "center"}, 
		{name: 'createTime', index: 'createTime', width: 60, align: "center"}, 
		{width: 50, align: "left", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: trainingHandle.edit(\'' + rowObject.itemId + '\');" >编辑</a>';
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: trainingHandle.disabled(\'' + rowObject.itemId + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: trainingHandle.enabled(\'' + rowObject.itemId + '\');" >启用</a>';
			}
			return "<span class='listBtnsSpan'>"+temp+"</span>";
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "培训项目列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	trainingHandle.init(config);
});