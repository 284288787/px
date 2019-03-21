var basePath = $('#basePath').val();
var promoterHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '推广员信息',
	winWidth: '400px', 
	winHeight: '200px',
	primaryKey: 'promoterId',
	urls:{ 
		list: basePath+'promoter/list',//列表
		addBefore: basePath+'common/management/promoter/addPromoter', //添加之前 
		editBefore: basePath+'promoter/editBefore',//编辑之前
		enabled: basePath+'promoter/enabled',//启用
		disabled: basePath+'promoter/disabled',//禁用
		deleted: basePath+'promoter/delete',//删除 
	}
},{ 
  downloadEwm: function(promoterId){
    window.open(basePath+'common/ewm/'+promoterId);
  },
  promotDetail: function(promoterId){
    artDialog.data("params", {"promoterId":promoterId});
    artDialog.open(basePath+'common/management/promoter/detail',{
      title: "推广明细",
      width : "800px",
      height: "500px",
      drag:true,
      resize:false,
      lock:true
    });
  }
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
	var colNames = ['id', '推广二维码', '已推广人数', '总提成', '姓名', '电话', '微信号', '添加时间', '是否可用', '操作'];
	var colModel = [ 
		{name: 'promoterId', index: 'promoterId', width: 30, align: 'center'}, 
		{width: 140, align: 'center', formatter: function(cellvalue, options, rowObject){
		  return '<a class="linetaga" href="javascript: promoterHandle.downloadEwm(\'' + rowObject.promoterId + '\');" >下载</a>'
		}}, 
		{name: 'count', index: 'count', width: 70, align: 'center', formatter: function(cellvalue, options, rowObject){
		  if(cellvalue==0) return '<a class="linetaga">'+cellvalue+'</a>';
      return '<a class="linetaga" href="javascript: promoterHandle.promotDetail(\'' + rowObject.promoterId + '\');" >'+cellvalue+'</a>'
    }}, 
    {name: 'totalTc', width: 70, align: 'center', formatter: function(cellvalue, options, rowObject){
      return (cellvalue / 100.0).toFixed(4);
    }}, 
		{name: 'name', index: 'name', width: 70, align: 'center'}, 
		{name: 'mobile', index: 'mobile', width: 110, align: 'center'}, 
		{name: 'wxNumber', index: 'wxNumber', width: 110, align: 'center'}, 
		{name: 'createTime', index: 'createTime', width: 140, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: promoterHandle.edit(\'' + rowObject.promoterId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: promoterHandle.disabled(\'' + rowObject.promoterId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: promoterHandle.enabled(\'' + rowObject.promoterId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '推广员列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	promoterHandle.init(config); 
}); 
