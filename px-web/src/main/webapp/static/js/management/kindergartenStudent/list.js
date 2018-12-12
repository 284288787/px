var basePath = $('#basePath').val();
var kindergartenStudentHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '学生信息',
	winWidth: '500px', 
	winHeight: '440px',
	primaryKey: 'studentId',
	urls:{ 
		list: basePath+'student/list',//列表
		addBefore: basePath+'common/management/kindergartenStudent/addStudent', //添加之前 
		editBefore: basePath+'student/editBefore',//编辑之前
		enabled: basePath+'student/enabled',//启用
		disabled: basePath+'student/disabled',//禁用
		deleted: basePath+'student/delete',//删除 
	}
},{ 
	exportModel: function(){
		var _form=$('#queryForm').clone(true);
		_form.attr({"action": basePath+"student/exportModel", "id": "exportModel"});
		$("body").append(_form)
		_form.submit();
		$("#exportModel").remove();
	},
	importData: function(){
		artDialog.data("importHandle", "com.booting.service.importdata.StudentImport");
		artDialog.open(basePath + "common/management/common/importData",{
			title: "导入幼儿信息",
			width : '90%',
			height: '800px',
			drag:true,
			resize:true,
			lock:true ,
			close:function(){
				kindergartenStudentHandle.query();;
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
var relation={"1": "父亲", "2": "母亲", "3": "爷爷", "4": "奶奶", "5": "外公", "6": "外婆", "7": "其他亲人"};
$(function(){ 
	var colNames = ['studentId', '手环mac', '级别', '经验点', '幼儿姓名', '性别', '生日', '身高(cm)', '体重(kg)', '监护人', '监护人关系', '监护人电话', '所在幼儿园', '所在班级', '班主任', '是否可用', '创建时间', '操作'];
	var colModel = [ 
		{name: 'studentId', index: 'studentId', width: 20, align: 'center'}, 
		{name: 'braceletMac', index: 'braceletMac', width: 60, align: 'center'}, 
		{name: 'level', index: 'level', width: 50, align: 'center', formatter: function(cellvalue, options, rowObject){
			var val=1;
			if(cellvalue) val = cellvalue+"级("+rowObject.levelName+")";
			return val; 
		}}, 
		{width: 40, align: 'center', formatter: function(cellvalue, options, rowObject){
			var val=0;
			if(rowObject.caloriePoint) val = rowObject.caloriePoint;
			if(rowObject.distancePoint) val += rowObject.distancePoint;
			return val; 
		}}, 
		{name: 'name', index: 'name', width: 50, align: 'center'}, 
		{name: 'sex', index: 'sex', width: 20, align: 'center', formatter: 'select', editoptions:{value:'1:男;0:女'}}, 
		{name: 'birth', index: 'birth', width: 60, align: 'center', formatter : function(value, options, rData) {
			var timestamp = "";
			if (value != null) {
				timestamp = (new Date(value)).format("yyyy-MM-dd");
			}
			return timestamp;
		}}, 
		{name: 'stature', index: 'stature', width: 50, align: 'center'}, 
		{name: 'weight', index: 'weight', width: 50, align: 'center', formatter: function(value, options, rowObject){ 
			return value /100;
		}}, 
		{name: 'guardianName', index: 'guardianName', width: 40, align: 'center'}, 
		{name: 'guardianType', index: 'guardianType', width: 50, align: 'center', formatter: function(value, options, rowObject){
			var val = "";
			if(value) val = relation[value]; 
			return val;
		}}, 
		{name: 'guardianMobile', index: 'guardianMobile', width: 60, align: 'center'}, 
		{name: 'schoolName', index: 'schoolName', width: 50, align: 'center'}, 
		{name: 'className', index: 'className', width: 50, align: 'center'}, 
		{name: 'teacherName', index: 'teacherName', width: 50, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{name: 'createTime', index: 'createTime', width: 60, align: 'center'}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: kindergartenStudentHandle.edit(\'' + rowObject.studentId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: kindergartenStudentHandle.disabled(\'' + rowObject.studentId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: kindergartenStudentHandle.enabled(\'' + rowObject.studentId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '学生列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	kindergartenStudentHandle.init(config); 
}); 
