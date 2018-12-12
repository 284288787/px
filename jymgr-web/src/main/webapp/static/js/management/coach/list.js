var basePath = $('#basePath').val();
var coachHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '教练员信息',
	winWidth: '60%', 
	winHeight: '90%',
	primaryKey: 'memberId',
	urls:{ 
		list: basePath+'coach/list',//列表
		addBefore: basePath+'common/management/coach/addCoach', //添加之前 
		editBefore: basePath+'coach/editBefore',//编辑之前
		viewBefore: basePath+'coach/viewBefore',//查看
		enabled: basePath+'coach/enabled',//启用
		disabled: basePath+'coach/disabled',//禁用
		deleted: basePath+'coach/delete',//删除 
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
	var colNames = ['memberId', '姓名', '电话', '性别', '生日', '职业退役', '证件类型', '证件号码', '添加时间', '状态', '操作'];
	var colModel = [ 
		{name: 'memberId', index: 'memberId', width: 30, align: 'center'}, 
		{name: 'name', index: 'name', width: 70, align: 'center', formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: coachHandle.view(\'' + rowObject.memberId + '\', \''+cellvalue+'\');" >'+cellvalue+'</a>'; 
			return temp; 
		}}, 
		{name: 'mobile', index: 'mobile', width: 70, align: 'center'}, 
		{name: 'sex', index: 'sex', width: 20, align: 'center', formatter: 'select', editoptions:{value:'1:男;0:女'}}, 
		{name: 'birthTime', index: 'birthTime', width: 60, align: 'center', formatter:'date', formatoptions: {newformat:'Y-m-d'}}, 
		{name: 'professionalService', index: 'professionalService', width: 70, align: 'center', formatter: 'select', editoptions:{value:'1:是;0:否'}}, 
		{name: 'certificateType', index: 'certificateType', width: 70, align: 'center', formatter: 'select', editoptions:{value:'1:身份证;2:护照'}}, 
		{name: 'certificateCode', index: 'certificateCode', width: 70, align: 'center'}, 
		{name: 'createTime', index: 'createTime', width: 50, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: coachHandle.edit(\'' + rowObject.memberId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: coachHandle.disabled(\'' + rowObject.memberId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: coachHandle.enabled(\'' + rowObject.memberId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '教练员列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	coachHandle.init(config); 
}); 
