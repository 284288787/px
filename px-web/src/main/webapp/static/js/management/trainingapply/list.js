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
	
});
$(function(){
	var colNames = [ 'applyId', '状态', '类型', '姓名', '团体名称', '电话', '地址', '证件类型 ','证件号码', '来源', '创建时间', '操作' ];
	var colModel = [
		{name: 'applyId', index: 'applyId', width: 20, align: "center"}, 
		{name: 'status', index: 'status', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:待处理"}}, 
		{name: 'type', index: 'type', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:幼儿园管理员;2:青少年;3:教练员;4:团体"}}, 
		{name: 'name', index: 'name', width: 60, align: "center"}, 
		{name: 'organizationName', index: 'organizationName', width: 60, align: "center"}, 
		{name: 'mobile', index: 'mobile', width: 60, align: "center"}, 
		{name: 'address', index: 'address', width: 190, align: "left"}, 
		{name: 'certificateType', index: 'certificateType', width: 40, align: "center", formatter: 'select', editoptions:{value:"1:身份证;"}}, 
		{name: 'certificateCode', index: 'certificateCode', width: 60, align: "center"}, 
		{name: 'sourceFrom', index: 'sourceFrom', width: 40, align: "center", formatter: 'select', editoptions:{value:"1:安卓;2:ios;3:后台;"}}, 
		{name: 'createTime', index: 'createTime', width: 60, align: "center"}, 
		{width: 50, align: "left", editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){
			var temp = '';
//			temp += '<a class="linetaga" href="javascript: trainingapplyHandle.edit(\'' + rowObject.itemId + '\');" >编辑</a>';
//			if(rowObject.enabled==1){
//				temp += '<a class="linetaga" href="javascript: trainingapplyHandle.disabled(\'' + rowObject.itemId + '\');" >禁用</a>';
//			}else{
//				temp += '<a class="linetaga" href="javascript: trainingapplyHandle.enabled(\'' + rowObject.itemId + '\');" >启用</a>';
//			}
			return "<span class='listBtnsSpan'>"+temp+"</span>";
		}}
	];
	var rowList = [10, 20, 30, 50];
	var rownumbers = true;
	var multiselect = true;
	var config={caption: "项目报名列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	trainingapplyHandle.init(config);
});