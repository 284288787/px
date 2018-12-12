var basePath = $("#basePath").val();
var levelHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '级别',
	winWidth: '556px',
	winHeight: '450px',
	primaryKey: 'id',
	urls:{
		list: basePath+'pointConfig/list',        //列表
		addBefore: basePath+'common/management/bracelet/addPointLevel',   //添加之前
		editBefore: basePath+'pointConfig/editBefore',          //编辑之前
		deleted: basePath+'pointConfig/delete',      //删除
	}
},{});
$(function(){
	var colNames = [ 'id', '级别', '称号', '图标', '升级需经验(点)', '一次增加经验(点)', '一次增加经验需卡路里(小卡)', '一次增加经验需跑步距离(m)', '操作' ];
	var colModel = [
		{name: 'id', index: 'id', width: 55, align: "center"}, 
		{name: 'level', index: 'level', width: 60, align: "center"}, 
		{name: 'name', index: 'name', width: 60, align: "center"}, 
		{name: 'icon', index: 'icon', width: 60, align: "center", formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<img src="'+cellvalue+'" height="60px">';
			return temp;
		}}, 
		{name: 'pointStep', index: 'pointStep', width: 60, align: "center"}, 
		{name: 'point', index: 'point', width: 60, align: "center"}, 
		{name: 'calorieStep', index: 'calorieStep', width: 60, align: "center"}, 
		{name: 'distanceStep', index: 'distanceStep', width: 60, align: "center"}, 
		{width: 90, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: levelHandle.edit(\'' + rowObject.id + '\');" >编辑</a>';
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "级别列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	levelHandle.init(config);
});