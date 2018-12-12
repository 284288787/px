var basePath = $("#basePath").val();
var serviceHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '服务信息',
	winWidth: '456px',
	winHeight: '175px',
	primaryKey: 'serviceId',
	urls:{
		list: basePath+'package/listService',        //列表
		addBefore: basePath+'common/management/package/addService',   //添加之前
		editBefore: basePath+'package/editServiceBefore',          //编辑之前
		enabled: basePath+'package/enabledService',      //启用
		disabled: basePath+'package/disabledService',    //禁用
		deleted: basePath+'package/deletedService',      //删除
	}
},{
	setAttribute: function(serviceId){
		artDialog.open(basePath+'package/setAttrOfService/'+serviceId,{
			title: "设置属性",
			width : '456px',
			height: '290px',
			drag:true,
			resize:true,
			lock:true
		});
	}
});
$(function(){
	var colNames = [ 'serviceId', '服务名称', '服务描述', '是否可用','创建时间', '操作' ];
	var colModel = [
		{name: 'serviceId', index: 'serviceId', width: 55, align: "center"}, 
		{name: 'serviceName', index: 'serviceName', width: 90, align: "center"}, 
		{name: 'description', index: 'description', width: 100, align: "left"}, 
		{name: 'enabled', index: 'enabled', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
		{width: 150, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: serviceHandle.edit(\'' + rowObject.serviceId + '\');" >编辑</a>';
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: serviceHandle.disabled(\'' + rowObject.serviceId + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: serviceHandle.enabled(\'' + rowObject.serviceId + '\');" >启用</a>';
			}
			temp += '<a class="linetaga" href="javascript: serviceHandle.setAttribute(\'' + rowObject.serviceId + '\');" >设置属性</a>';
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "服务列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	serviceHandle.init(config);
});