var basePath = $('#basePath').val();
var physicalClassHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '体测课信息',
	winWidth: '600px', 
	winHeight: '300px',
	primaryKey: 'physicalClassId',
	urls:{ 
		list: basePath+'physicalClass/list',//列表
		addBefore: basePath+'common/management/physicalClass/addPhysicalClass', //添加之前 
		editBefore: basePath+'physicalClass/editBefore',//编辑之前
		enabled: basePath+'physicalClass/enabled',//启用
		disabled: basePath+'physicalClass/disabled',//禁用
		deleted: basePath+'physicalClass/delete',//删除 
	}
},{ 
	 
}); 
var utilsHandle = new UtilsHandle({ 
	basePath: basePath,
	chooseCity: {
		'object': $('input[name=areaName]'), 
		'width': '70%',
		'height': '600px', 
		'areaId': $(':hidden[name=areaId]').val(), 
		'callback':function(areaId, areaName){ 
			$(':hidden[name=areaId]').val(areaId); 
			$(':input[name=areaName]').val(areaName);
		}
	}, 
}); 
$(function(){ 
	var colNames = ['id', '课程标题', '报名截止时间', '上课时间', '状态', '价格', '上课地点', '是否可用', '创建时间', '操作'];
	var colModel = [ 
		{name: 'physicalClassId', index: 'physicalClassId', width: 30, align: 'center'}, 
		{name: 'title', index: 'title', width: 70, align: 'center'}, 
		{name: 'deadlineTime', index: 'deadlineTime', width: 50, align: 'center'}, 
		{name: 'schoolTime', index: 'schoolTime', width: 50, align: 'center'}, 
		{name: 'state', editable: false, sortable: false, width: 50, align: 'center', formatter: 'select', editoptions:{value:'1:未开始;2:已开始'}}, 
		{name: 'price', index: 'price', width: 50, align: 'center', formatter: function(cellvalue, options, rowObject){
      if(!cellvalue) return '';
      return (cellvalue / 100.0).toFixed(2);
    }},
		{name: 'address', index: 'address', width: 200, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{name: 'createTime', index: 'createTime', width: 50, align: 'center'}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: physicalClassHandle.edit(\'' + rowObject.physicalClassId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: physicalClassHandle.disabled(\'' + rowObject.physicalClassId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: physicalClassHandle.enabled(\'' + rowObject.physicalClassId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '体测课列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	physicalClassHandle.init(config); 
}); 
