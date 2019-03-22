var basePath = $('#basePath').val();
var kickbackDetailHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '回扣发放明细信息',
	winWidth: '500px', 
	winHeight: '200px',
	primaryKey: 'id',
	urls:{ 
		list: basePath+'kickbackDetail/list',//列表
		addBefore: basePath+'common/management/kickbackDetail/addkickbackDetail', //添加之前 
		editBefore: basePath+'kickbackDetail/editBefore',//编辑之前
		enabled: basePath+'kickbackDetail/enabled',//启用
		disabled: basePath+'kickbackDetail/disabled',//禁用
		deleted: basePath+'kickbackDetail/delete',//删除 
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
	var colNames = ['操作', 'id', '推广员', '申请金额', '申请时间', '收款微信', '金额时间范围起', '金额时间范围止', '交易流水号'];
	var colModel = [ 
	  {width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
      var temp = ''; 
      if(rowObject.state == 1){
        temp += '<a class="linetaga" href="javascript: kickbackDetailHandle.edit(\'' + rowObject.id + '\');" >填写交易流水号</a>'; 
      }else{
        temp += '<span class="linetaga">已完成</span>';
      }
      return '<span class="listBtnsSpan">'+temp+'</span>'; 
    }},
		{name: 'id', index: 'id', width: 30, align: 'center'}, 
		{name: 'promoterName', index: 'promoterName', width: 70, align: 'center'}, 
		{name: 'money', index: 'money', width: 50, align: 'center', formatter: function(cellvalue, options, rowObject){ 
      return (cellvalue / 100.0).toFixed(2); 
    }}, 
    {name: 'createTime', index: 'createTime', width: 50, align: 'center'}, 
		{name: 'wxNumber', index: 'wxNumber', width: 50, align: 'center'}, 
		{name: 'pointBeginTime', editable: false, sortable: false, width: 50, align: 'center'}, 
		{name: 'pointEndTime', editable: false, sortable: false, width: 50, align: 'center'}, 
		{name: 'wxOrderNumber', index: 'wxOrderNumber', width: 200, align: 'center'}, 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '回扣发放明细列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	kickbackDetailHandle.init(config); 
}); 
