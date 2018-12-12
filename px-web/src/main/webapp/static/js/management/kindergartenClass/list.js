var basePath = $('#basePath').val();
var kindergartenClassHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '班级信息',
	winWidth: '800px', 
	winHeight: '400px',
	primaryKey: 'classId',
	urls:{ 
		list: basePath+'class/list',//列表
		addBefore: basePath+'common/management/kindergartenClass/addClass', //添加之前 
		editBefore: basePath+'class/editBefore',//编辑之前
		enabled: basePath+'class/enabled',//启用
		disabled: basePath+'class/disabled',//禁用
		deleted: basePath+'class/delete',//删除 
	}
},{ 
	setTeachers: function(schoolId, classId, className){
		artDialog.data("args", {'schoolId': schoolId, 'classId': classId, parentHandle: kindergartenClassHandle});
		artDialog.open(basePath + "teacher/setTeachers/" + schoolId + "_" + classId,{
			title: "给班级【"+className+"】设置幼师",
			width : '90%',
			height: '500px',
			drag:true,
			resize:true,
			lock:true/* ,
			close:function(){
				document.location.reload();
			} */
		});
	},
	exportModel: function(){
		var _form=$('#queryForm').clone(true);
		_form.attr({"action": basePath+"class/exportModel", "id": "exportModel"});
		$("body").append(_form)
		_form.submit();
		$("#exportModel").remove();
	},
	importData: function(){
		artDialog.data("importHandle", "com.booting.service.importdata.ClassImport");
		artDialog.open(basePath + "common/management/common/importData",{
			title: "导入班级信息",
			width : '90%',
			height: '800px',
			drag:true,
			resize:true,
			lock:true ,
			close:function(){
				kindergartenClassHandle.query();;
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
	var colNames = ['classId', '班级名称', '所属幼儿园', '班主任', '班主任电话', '其他老师', '简介', '是否可用', '创建时间', '操作'];
	var colModel = [ 
		{name: 'classId', index: 'classId', width: 30, align: 'center'}, 
		{name: 'name', index: 'name', width: 70, align: 'center'}, 
		{name: 'schoolName', index: 'schoolName', width: 70, align: 'center'}, 
		{name: 'teacherName', index: 'teacherName', width: 50, align: 'center'}, 
		{name: 'teacherMobile', index: 'teacherMobile', width: 70, align: 'center'}, 
		{name: 'otherTeacherNames', index: 'otherTeacherNames', width: 70, align: 'center'}, 
		{name: 'intro', index: 'intro', width: 70, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{name: 'createTime', index: 'createTime', width: 60, align: 'center'}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: kindergartenClassHandle.setTeachers(\'' + rowObject.schoolId + '\', \'' + rowObject.classId + '\', \'' + rowObject.name + '\');" >设置幼师</a>'; 
			temp += '<a class="linetaga" href="javascript: kindergartenClassHandle.edit(\'' + rowObject.classId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: kindergartenClassHandle.disabled(\'' + rowObject.classId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: kindergartenClassHandle.enabled(\'' + rowObject.classId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '班级列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	kindergartenClassHandle.init(config); 
}); 
