var basePath = $("#basePath").val();
var appHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: 'APP版本信息',
	winWidth: '556px',
	winHeight: '260px',
	primaryKey: 'sourceId',
	urls:{
		list: basePath+'appv/appList',        //列表
		addBefore: basePath+'common/management/app/addApp',   //添加之前
		editBefore: basePath+'appv/editBefore',          //编辑之前
		enabled: basePath+'appv/enabled',      //启用
		disabled: basePath+'appv/disabled',    //禁用
	}
},{});
$(function(){
	var colNames = [ 'id', '版本号', 'MD5', 'URL', '是否可用', '强制升级', '描述', '创建时间', '操作' ];
	var colModel = [
		{name: 'id', index: 'id', width: 55, align: "center"}, 
		{name: 'version', index: 'version', width: 90, align: "center"}, 
		{name: 'md5', index: 'md5', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:轮播图;2:文章"}}, 
		{name: 'url', index: 'url', width: 80, align: "left"}, 
		{name: 'status', index: 'status', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'upgrade', index: 'upgrade', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:非强制;2:强制"}}, 
		{name: 'description', index: 'description', width: 80, align: "center"}, 
		{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
		{width: 150, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: appHandle.edit(\'' + rowObject.id + '\');" >编辑</a>';
			if(rowObject.status==1){
				temp += '<a class="linetaga" href="javascript: appHandle.disabled(\'' + rowObject.id + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: appHandle.enabled(\'' + rowObject.id + '\');" >启用</a>';
			}
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "APP版本列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	appHandle.init(config);
});