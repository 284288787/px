var basePath = $("#basePath").val();
var courtHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '球场信息',
	winWidth: '756px',
	winHeight: '400px',
	primaryKey: 'courtId',
	urls:{
		list: basePath+'court/list',        //列表
		addBefore: basePath+'common/management/court/addCourt',   //添加之前
		editBefore: basePath+'court/editBefore',          //编辑之前
		enabled: basePath+'court/enabled',      //启用
		disabled: basePath+'court/disabled',    //禁用
	}
},{
	view: function(courtId, courtName){
		var params={courtId: courtId, courtName, courtName, courtHandle: courtHandle};
		artDialog.data("params", params);
		artDialog.open(basePath+'common/management/court/siteList',{
			title: "场地管理",
			width : '80%',
			height: '80%',
			drag:true,
			resize:true,
			lock:true
		});
	}
});
$(function(){
	var colNames = [ 'courtId', '球场名称', '场地个数', '创建者', '联系人', '联系电话', '开始营业时间', '结束营业时间', '一场球时间(分)', '城市', '地址', '简介', '是否可用', '创建时间', '操作' ];
	var colModel = [
        {name: 'courtId', index: 'courtId', width: 35, align: "center"}, 
        {name: 'courtName', index: 'courtName', width: 35, align: "center"}, 
		{name: 'siteNum', index: 'siteNum', width: 35, align: "center"}, 
		{name: 'userName', index: 'userName', width: 60, align: "center"},
		{name: 'linkman', index: 'linkman', width: 60, align: "center"}, 
		{name: 'mobile', index: 'mobile', width: 40, align: "center"}, 
		{name: 'startMinute', index: 'startMinute', width: 35, align: "center", formatter: function(cellvalue, options, rowObject){
			var a = Math.floor(cellvalue / 60);
			var b = cellvalue % 60;
			if(b < 10){
				b = "0" + b;
			}
			return a + ":" + b;
		}}, 
		{name: 'endMinute', index: 'endMinute', width: 60, align: "center", formatter: function(cellvalue, options, rowObject){
			var a = Math.floor(cellvalue / 60);
			var b = cellvalue % 60;
			if(b < 10){
				b = "0" + b;
			}
			return a + ":" + b;
		}}, 
		{name: 'oneMinute', index: 'oneMinute', width: 60, align: "center"}, 
		{name: 'areaName', index: 'areaName', width: 40, align: "center"}, 
		{name: 'address', index: 'address', width: 40, align: "center"}, 
		{name: 'intro', index: 'intro', width: 80, align: "center"}, 
		{name: 'enabled', index: 'enabled', width: 30, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
		{width: 70, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: courtHandle.view(\'' + rowObject.courtId + '\',\''+rowObject.courtName+'\');" >场地管理</a>';
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: courtHandle.disabled(\'' + rowObject.courtId + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: courtHandle.enabled(\'' + rowObject.courtId + '\');" >启用</a>';
			}
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "球场列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	courtHandle.init(config);
});