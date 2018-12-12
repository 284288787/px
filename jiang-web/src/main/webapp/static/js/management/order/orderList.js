var basePath = $("#basePath").val();
var orderHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '订单信息',
	winWidth: '80%',
	winHeight: '80%',
	primaryKey: 'orderId',
	urls:{
		list: basePath+'order/list',        //列表
	}
},{
	view: function(orderId){
		artDialog.open(basePath+'order/view/'+orderId,{
			title: "订单信息",
			width : '80%',
			height: '80%',
			drag:true,
			resize:true,
			lock:true
		});
	}
});
$(function(){
	var colNames = [ 'orderId', '订单编号', '购买内容', '订单状态', '用户ID', '用户姓名', '电话号码', '订单类型', '支付方式', '支付时间', '支付流水', '操作' ];
	var colModel = [
		{name: 'orderId', index: 'orderId', width: 30, align: "center"}, 
		{name: 'orderNo', index: 'orderNo', width: 90, align: "center"}, 
		{name: 'subject', index: 'subject', width: 90, align: "center"}, 
		{name: 'status', index: 'status', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:待支付;2:已支付;3:已取消"}}, 
		{name: 'userId', index: 'userId', width: 30, align: "center"}, 
		{name: 'userName', index: 'userName', width: 80, align: "center"}, 
		{name: 'mobile', index: 'mobile', width: 80, align: "center"}, 
		{name: 'type', index: 'type', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:购买套餐;2:订场地"}}, 
		{name: 'payType', index: 'payType', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:支付宝;2:微信"}}, 
		{name: 'payTime', index: 'payTime', width: 80, align: "center", formatter: function(cellvalue, options, rowObject){
			if(cellvalue) return cellvalue;
			return "未支付";
		}}, 
		{name: 'outTradeNo', index: 'outTradeNo', width: 80, align: "center", formatter: function(cellvalue, options, rowObject){
			if(cellvalue) return cellvalue;
			return "未支付";
		}}, 
		{width: 60, align: "center", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
			temp += '<a class="linetaga" href="javascript: orderHandle.view(\'' + rowObject.orderId + '\');" >查看</a>';
			return temp;
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "订单列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	orderHandle.init(config);
});