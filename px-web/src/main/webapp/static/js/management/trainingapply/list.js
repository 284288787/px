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
	var colNames = [ '主键', '状态', '培训项目', '上课地点', '家长姓名', '家长电话', '孩子姓名', '孩子性别', '孩子生日', '家庭地址', '创建时间'];
	var colModel = [
		{name: 'applyId', index: 'applyId', width: 20, align: "center"}, 
		{name: 'status', index: 'status', width: 60, align: "center", formatter: 'select', editoptions:{value:"1:待处理"}}, 
		{name: 'applyItemId', index: 'applyItemId', width: 80, align: "center", formatter: 'select', editoptions:{value:"1:体质检测;2:2节体验课"}}, 
		{name: 'addrName', index: 'addrName', width: 140, align: "center"}, 
		{name: 'name', index: 'name', width: 60, align: "center"}, 
		{name: 'mobile', index: 'mobile', width: 60, align: "center"}, 
		{name: 'childName', index: 'childName', width: 60, align: "center"}, 
		{name: 'childSex', index: 'childSex', width: 40, align: "center", formatter: 'select', editoptions:{value:"1:男;0:女"}}, 
		{name: 'childBirth', index: 'childBirth', width: 60, align: "center", formatter:'date', formatoptions: {srcformat: 'Y-m-d H:i:s', newformat:'Y-m-d'}}, 
		{name: 'address', index: 'address', width: 190, align: "left"}, 
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
	var config={caption: "项目报名列表", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	trainingapplyHandle.init(config);
});