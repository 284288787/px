var basePath = $('#basePath').val();
var kindergartenPhysicalDataHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '体测数据信息',
	winWidth: '600px',
	winHeight: '550px', 
	primaryKey: 'id',
	urls:{ 
		list: basePath+'physicalData/list',//列表
		addBefore: basePath+'common/management/kindergartenPhysicalData/addPhysicalData', //添加之前 
		editBefore: basePath+'physicalData/editBefore',//编辑之前
		enabled: basePath+'physicalData/enabled',//启用
		disabled: basePath+'physicalData/disabled',//禁用
		deleted: basePath+'physicalData/delete',//删除 
	}
},{ 
	batchExport: function(){
		var data=$('#queryForm').serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		$('#queryForm').submit();
	},
	exportModel: function(){
		var _form=$('#queryForm').clone(true);
		_form.attr({"action": basePath+"physicalData/exportModel", "id": "exportModel"});
		$("body").append(_form)
		_form.submit();
		$("#exportModel").remove();
	},
	importData: function(){
		artDialog.data("importHandle", "com.booting.service.importdata.PhysicalDataImport");
		artDialog.open(basePath + "common/management/common/importData",{
			title: "导入体测数据",
			width : '90%',
			height: '800px',
			drag:true,
			resize:true,
			lock:true ,
			close:function(){
				kindergartenPhysicalDataHandle.query();;
			} 
		});
	},
}); 
var utilsHandle = new UtilsHandle({ 
	basePath: basePath,
	choose: [{
		'title': '选择幼儿园',
		'object': $('input[name=schoolName]'),
		'url': 'kindergarten/chooseSchool',
		'width': '70%',
		'height': '600px', 
		'choosedId': $(':hidden[name=schoolId]'), 
		'callback':function(schoolId, schoolName){ 
			$(':hidden[name=schoolId]').val(schoolId); 
			$(':input[name=schoolName]').val(schoolName);
		}
	}], 
}); 
$(function(){ 
	var colNames = ['id', '幼儿姓名', '性别', '生日', '幼儿园', '监护人', '身高(cm)', '体重(kg)', '坐位体前屈', '立定跳远', '网球投掷', '双脚连续跳', '10米折返跑', '走平衡木', '测试时间', '描述', '是否可用', '创建时间', '操作'];
	var colModel = [ 
		{name: 'id', index: 'id', width: 30, align: 'center'}, 
		{name: 'name', index: 'name', width: 40, align: 'center'}, 
		{name: 'sex', index: 'sex', width: 20, align: 'center', formatter: 'select', editoptions:{value:'1:男;0:女'}}, 
		{name: 'birth', index: 'birth', width: 60, align: 'center', formatter : function(value, options, rData) {
			var timestamp = "";
			if (value != null) {
				timestamp = (new Date(value)).format("yyyy-MM-dd");
			}
			return timestamp;
		}}, 
		{name: 'schoolName', index: 'schoolName', width: 50, align: 'center'}, 
		{name: 'guardianName', index: 'guardianName', width: 50, align: 'center'}, 
		{name: 'stature', index: 'stature', width: 50, align: 'center', formatter: function(value, options, rowObject){ 
			return value + "cm，"+rowObject.statureScore+"分";;
		}}, 
		{name: 'weight', index: 'weight', width: 50, align: 'center', formatter: function(value, options, rowObject){ 
			return value /100 + "kg，"+rowObject.weightScore+"分";;
		}}, 
		{name: 'sitReach', index: 'sitReach', width: 50, align: 'center', formatter: function(value, options, rowObject){ 
			return value /100 + "cm，"+rowObject.sitReachScore+"分";
		}}, 
		{name: 'jump', index: 'jump', width: 50, align: 'center', formatter: function(value, options, rowObject){ 
			return value /100 + "cm，"+rowObject.jumpScore+"分";
		}}, 
		{name: 'throwTennis', index: 'throwTennis', width: 50, align: 'center', formatter: function(value, options, rowObject){ 
			return value /100 + "米，"+rowObject.throwTennisScore+"分";
		}}, 
		{name: 'doubleJump', index: 'doubleJump', width: 50, align: 'center', formatter: function(value, options, rowObject){ 
			return value /100 + "秒，"+rowObject.doubleJumpScore+"分";
		}}, 
		{name: 'run10', index: 'run10', width: 50, align: 'center', formatter: function(value, options, rowObject){ 
			return value /100 + "秒，"+rowObject.run10Score+"分";
		}}, 
		{name: 'balance', index: 'balance', width: 50, align: 'center', formatter: function(value, options, rowObject){ 
			return value /100 + "秒，"+rowObject.balanceScore+"分";
		}}, 
		{name: 'testTime', index: 'testTime', width: 60, align: 'center', formatter : function(value, options, rData) {
			var timestamp = "";
			if (value != null) {
				timestamp = (new Date(value)).format("yyyy-MM-dd");
			}
			return timestamp;
		}}, 
		{name: 'intro', index: 'intro', width: 50, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{name: 'createTime', index: 'createTime', width: 60, align: 'center'}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: kindergartenPhysicalDataHandle.edit(\'' + rowObject.id + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: kindergartenPhysicalDataHandle.disabled(\'' + rowObject.id + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: kindergartenPhysicalDataHandle.enabled(\'' + rowObject.id + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '体测数据列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	kindergartenPhysicalDataHandle.init(config); 
}); 
