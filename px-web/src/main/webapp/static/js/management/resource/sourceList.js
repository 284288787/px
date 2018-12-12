var basePath = $("#basePath").val();
var sourceHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '资源信息',
	winWidth: '456px',
	winHeight: '345px',
	primaryKey: 'sourceId',
	urls:{
		list: basePath+'resource/sourceList',        //列表
		addBefore: basePath+'common/management/resource/addResource',   //添加之前
		editBefore: basePath+'resource/editBefore',          //编辑之前
		enabled: basePath+'resource/enabled',      //启用
		disabled: basePath+'resource/disabled',    //禁用
		deleted: basePath+'resource/deleted',      //删除
	}
},{});
$(function(){
	var colNames = [ 'sourceId', '资源名称', '资源类型', '父级资源', '资源说明', '资源链接', '资源样式', '是否可用','创建时间', '操作' ];
	var colModel = [
		{name: 'sourceId', index: 'sourceId', width: 55, align: "center"}, 
		{name: 'sourceName', index: 'sourceName', width: 90, align: "center"}, 
		{name: 'type', index: 'type', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:系统;2:一级菜单;3:二级菜单;4:三级菜单;5:按钮;6:其他url"}}, 
		{name: 'parentId', index: 'parentId', width: 90, align: "center"}, 
		{name: 'sourceRemark', index: 'sourceRemark', width: 90, align: "left"}, 
		{name: 'sourceUrl', index: 'sourceUrl', width: 90, align: "left"}, 
		{name: 'sourceIcoCls', index: 'sourceIcoCls', width: 90, align: "center"}, 
		{name: 'enabled', index: 'enabled', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
		{width: 150, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: sourceHandle.edit(\'' + rowObject.sourceId + '\');" >编辑</a>';
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: sourceHandle.disabled(\'' + rowObject.sourceId + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: sourceHandle.enabled(\'' + rowObject.sourceId + '\');" >启用</a>';
			}
			temp += '<a class="linetaga" href="javascript: setRole(\'' + rowObject.sourceId + '\');" >指定角色</a>';
			temp += '<a class="linetaga" href="javascript: viewRole(\'' + rowObject.sourceId + '\');" >查看角色</a>';
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "资源列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	sourceHandle.init(config);
});