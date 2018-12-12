var basePath = $('#basePath').val();
var kindergartenTeacherHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '幼师信息',
	winWidth: '900px', 
	winHeight: '400px',
	primaryKey: 'teacherId',
	urls:{ 
		list: basePath+'teacher/list',//列表
		addBefore: basePath+'common/management/kindergartenTeacher/addTeacher', //添加之前 
		editBefore: basePath+'teacher/editBefore',//编辑之前
		enabled: basePath+'teacher/enabled',//启用
		disabled: basePath+'teacher/disabled',//禁用
		deleted: basePath+'teacher/delete',//删除 
	}
},{ 
	setClasses: function(schoolId, teacherId, teacherName){
		artDialog.data("args", {'schoolId': schoolId, 'teacherId': teacherId});
		artDialog.open(basePath + "class/setClasses/" + schoolId + "_" + teacherId,{
			title: "给幼师【"+teacherName+"】设置班级",
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
		_form.attr({"action": basePath+"teacher/exportModel", "id": "exportModel"});
		$("body").append(_form)
		_form.submit();
		$("#exportModel").remove();
	},
	importData: function(){
		artDialog.data("importHandle", "com.booting.service.importdata.TeacherImport");
		artDialog.open(basePath + "common/management/common/importData",{
			title: "导入幼师信息",
			width : '90%',
			height: '800px',
			drag:true,
			resize:true,
			lock:true ,
			close:function(){
				kindergartenTeacherHandle.query();;
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
	var colNames = ['teacherId', '头像', '幼师姓名', '电话', '简介', '所属幼儿园', '是否可用', '创建时间', '操作'];
	var colModel = [ 
		{name: 'teacherId', index: 'teacherId', width: 30, align: 'center'}, 
		{name: 'pic', index: 'pic', width: 30, align: 'center', formatter: function(value, options, rowObject){
			return "<img class='dataImg' src='"+basePath+value+"' height='60px'>";
		}}, 
		{name: 'teacherName', index: 'teacherName', width: 70, align: 'center'}, 
		{name: 'teacherMobile', index: 'teacherMobile', width: 70, align: 'center'}, 
		{name: 'intro', index: 'intro', width: 70, align: 'center'}, 
		{name: 'schoolName', index: 'schoolName', width: 70, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{name: 'createTime', index: 'createTime', width: 60, align: 'center'}, 
		{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: kindergartenTeacherHandle.setClasses(\'' + rowObject.schoolId + '\', \'' + rowObject.teacherId + '\', \'' + rowObject.teacherName + '\');" >设置班级</a>';
			temp += '<a class="linetaga" href="javascript: kindergartenTeacherHandle.edit(\'' + rowObject.teacherId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: kindergartenTeacherHandle.disabled(\'' + rowObject.teacherId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: kindergartenTeacherHandle.enabled(\'' + rowObject.teacherId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '幼师列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect, callback: function(table){
		$(".dataImg").unbind().click(function(){
			if($("#viewImg").length>0){
				$("#viewImg").remove();
			}
			$("body").append('<img id="viewImg" style="display:none" src="'+$(this).attr("src")+'">');
			$("#viewImg").load(function(){
				var w=$(this).width();
				var h=$(this).height();
				var limit=1200;
				if(w > limit){
					h = limit * (h/w);
					w = limit;
				}
				artDialog.alert2('<div style="width:'+w+'px;height:'+h+'px"><img width="'+w+'" src="'+$(this).attr("src")+'">')
			});
			return false;
		});
	}};
	kindergartenTeacherHandle.init(config); 
}); 
