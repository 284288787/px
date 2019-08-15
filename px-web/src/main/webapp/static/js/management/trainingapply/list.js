var basePath = $("#basePath").val();
var trainingapplyHandle = new ListHandle({
	basePath: $("#basePath").val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '项目报名信息',
	winWidth: '90%',
	winHeight: '90%',
	primaryKey: 'itemId',
	urls:{
		list: basePath+'trainingApply/list',        //列表
		addBefore: basePath+'common/management/trainingApply/addTrainingApply',   //添加之前
		editBefore: basePath+'trainingApply/editBefore',          //编辑之前
		enabled: basePath+'trainingApply/enabled',      //启用
		disabled: basePath+'trainingApply/disabled',    //禁用
		deleted: basePath+'trainingApply/delete',      //删除
	}
},{
  exportInfo: function(){
    document.location.href=basePath+'trainingApply/exportRecord';
  }
});
$(function(){
  var now = new Date();
  $("#endCreateTime").val(now.format("yyyy-MM-dd"));
  now.setDate(now.getDate() - 1);
  $("#beginCreateTime").val(now.format("yyyy-MM-dd"));
  
	var colNames = [ '主键', '状态', '订单号', '交易单号', '金额', '项目标题', '培训项目', '上课地点', '家长姓名', '家长电话', '孩子姓名', '孩子性别', '孩子年龄', '家庭地址', '推广员', '推广员电话', '创建时间'];
	var colModel = [
		{name: 'applyId', index: 'applyId', width: 20, align: "center"}, 
		{name: 'status', index: 'status', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:待处理;2:已支付"}}, 
		{name: 'orderNo', index: 'orderNo', width: 140, align: "center"}, 
		{name: 'transactionId', index: 'transactionId', width: 150, align: "center"}, 
		{name: 'totalFee', width: 80,editable: false, sortable: false, align: "center", formatter: function(cellvalue, options, rowObject){
		  if(!cellvalue) return '';
		  return (cellvalue / 100.0).toFixed(2);
		}}, 
		{name: 'title',  width: 120, align: "center",editable: false, sortable: false}, 
		{name: 'itemName',  width: 80, align: "center",editable: false, sortable: false}, 
		{name: 'addrName', index: 'addrName', width: 140, align: "center"}, 
		{name: 'name', index: 'name', width: 60, align: "center"}, 
		{name: 'mobile', index: 'mobile', width: 60, align: "center"}, 
		{name: 'childName', index: 'childName', width: 60, align: "center"}, 
		{name: 'childSex', index: 'childSex', width: 40, align: "center", formatter: 'select', editoptions:{value:"1:男;0:女"}}, 
		{name: 'childAge', index: 'childAge', width: 40, align: "center"}, 
		{name: 'address', index: 'address', width: 120, align: "left"}, 
		{name: 'promoter', index: 'promoter', width: 120, align: "center"}, 
		{name: 'promoterMobile', index: 'promoter_mobile', width: 120, align: "center"}, 
		{name: 'createTime', index: 'createTime', width: 60, align: "center"}, 
//		{width: 50, align: "left", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
//			var temp = '';
////			temp += '<a class="linetaga" href="javascript: trainingapplyHandle.edit(\'' + rowObject.itemId + '\');" >编辑</a>';
////			if(rowObject.enabled==1){
////				temp += '<a class="linetaga" href="javascript: trainingapplyHandle.disabled(\'' + rowObject.itemId + '\');" >禁用</a>';
////			}else{
////				temp += '<a class="linetaga" href="javascript: trainingapplyHandle.enabled(\'' + rowObject.itemId + '\');" >启用</a>';
////			}
//			return "<span class='listBtnsSpan'>"+temp+"</span>";
//		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "项目报名列表", dataType:'local', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	trainingapplyHandle.init(config);
	trainingapplyHandle.query();
});