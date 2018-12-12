var basePath = $("#basePath").val();
var packageHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '套餐信息',
	winWidth: '456px',
	winHeight: '245px',
	primaryKey: 'packageId',
	urls:{
		list: basePath+'package/listPackage',        //列表
		addBefore: basePath+'common/management/package/addPackage',   //添加之前
		editBefore: basePath+'package/editPackageBefore',          //编辑之前
		enabled: basePath+'package/enabledPackage',      //启用
		disabled: basePath+'package/disabledPackage',    //禁用
		deleted: basePath+'package/deletedPackage',      //删除
	}
},{
	setService: function(packageId){
		artDialog.open(basePath+'package/setServOfPackage/'+packageId,{
			title: "设置服务",
			width : '600px',
			height: '80%',
			drag:true,
			resize:true,
			lock:true
		});
	}
});
$(function(){
	var colNames = [ 'packageId', '套餐类型', '套餐名称', '套餐描述', '原价', '折扣', '折后价', '是否可用','创建时间', '操作' ];
	var colModel = [
		{name: 'packageId', index: 'packageId', width: 55, align: "center"}, 
		{name: 'packageType', index: 'packageType', width: 90, align: "center", formatter: 'select', editoptions:{value:"1:基础套餐;2:增值套餐"}}, 
		{name: 'packageName', index: 'packageName', width: 90, align: "center"}, 
		{name: 'packageName', index: 'description', width: 90, align: "center"}, 
		{name: 'price', index: 'price', width: 90, align: "center", formatter: function(cellvalue, options, rowObject){
			return (cellvalue/100).toFixed(2);
		}}, 
		{name: 'discount', index: 'discount', width: 90, align: "center", formatter: function(cellvalue, options, rowObject){
			if(!cellvalue) return "无折扣";
			if(cellvalue<10){
				return cellvalue;
			}else{
				return (cellvalue/10).toFixed(1);
			}
		}}, 
		{name: 'realPrice', index: 'realPrice', width: 90, align: "center", formatter: function(cellvalue, options, rowObject){
			if(!cellvalue) return (rowObject.price/100).toFixed(2);
			return (cellvalue/100).toFixed(2);
		}}, 
		{name: 'enabled', index: 'enabled', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:可用;0:禁用"}}, 
		{name: 'createTime', index: 'createTime', width: 80, align: "center"}, 
		{width: 150, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: packageHandle.edit(\'' + rowObject.packageId + '\');" >编辑</a>';
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: packageHandle.disabled(\'' + rowObject.packageId + '\');" >禁用</a>';
			}else{
				temp += '<a class="linetaga" href="javascript: packageHandle.enabled(\'' + rowObject.packageId + '\');" >启用</a>';
			}
			temp += '<a class="linetaga" href="javascript: packageHandle.setService(\'' + rowObject.packageId + '\');" >设置服务</a>';
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "套餐列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	packageHandle.init(config);
});