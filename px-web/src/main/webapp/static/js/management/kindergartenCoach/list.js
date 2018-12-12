var basePath = $('#basePath').val();
var kindergartenCoachHandle = new ListHandle({ 
	basePath: $('#basePath').val(),
	tableId: '#grid-table',
	pagerId: '#grid-pager',
	formId: '#queryForm',
	entityName: '教练信息',
	winWidth: '900px', 
	winHeight: '850px',
	primaryKey: 'coachId',
	urls:{ 
		list: basePath+'coach/list',//列表
		addBefore: basePath+'common/management/kindergartenCoach/addCoach', //添加之前 
		editBefore: basePath+'coach/editBefore',//编辑之前
		enabled: basePath+'coach/enabled',//启用
		disabled: basePath+'coach/disabled',//禁用
		deleted: basePath+'coach/delete',//删除 
	}
},{ 
	setClasses: function(coachId, coachName){
		artDialog.data("args", {'coachId': coachId, 'coachName': coachName});
		artDialog.open(basePath + "common/management/kindergartenCoach/setClass",{
			title: "给教练【"+coachName+"】设置班级",
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
			title: "导入教练信息",
			width : '90%',
			height: '800px',
			drag:true,
			resize:true,
			lock:true ,
			close:function(){
				kindergartenCoachHandle.query();;
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
	var colNames = ['coachId', '头像', '教练姓名', '教练电话', '性别', '年龄', '证件类型', '证件号码', '级别', '联系地址', '是否可用', '创建时间', '操作'];
	var colModel = [ 
		{name: 'coachId', index: 'coachId', width: 30, align: 'center'}, 
		{name: 'pic', index: 'pic', width: 30, align: 'center', formatter: function(value, options, rowObject){
			return "<img class='dataImg' src='"+basePath+value+"' height='60px'>";
		}}, 
		{name: 'name', index: 'name', width: 70, align: 'center'}, 
		{name: 'mobile', index: 'mobile', width: 70, align: 'center'}, 
		{name: 'sex', index: 'sex', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:男;0:女'}}, 
		{name: 'age', index: 'age', width: 30, align: 'center'}, 
		{name: 'certificateType', index: 'certificateType', width: 40, align: 'center', formatter: 'select', editoptions:{value:'1:身份证号'}}, 
		{name: 'certificateCode', index: 'certificateCode', width: 70, align: 'center'}, 
		{name: 'level', index: 'level', width: 30, align: 'center'}, 
		{name: 'address', index: 'address', width: 70, align: 'center'}, 
		{name: 'enabled', index: 'enabled', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
		{name: 'createTime', index: 'createTime', width: 60, align: 'center'}, 
		{width: 70, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ 
			var temp = ''; 
			temp += '<a class="linetaga" href="javascript: kindergartenCoachHandle.setClasses(\'' + rowObject.coachId + '\', \'' + rowObject.name + '\');" >设置班级</a>';
			temp += '<a class="linetaga" href="javascript: kindergartenCoachHandle.edit(\'' + rowObject.coachId + '\');" >编辑</a>'; 
			if(rowObject.enabled==1){
				temp += '<a class="linetaga" href="javascript: kindergartenCoachHandle.disabled(\'' + rowObject.coachId + '\');" >禁用</a>'; 
			}else{ 
				temp += '<a class="linetaga" href="javascript: kindergartenCoachHandle.enabled(\'' + rowObject.coachId + '\');" >启用</a>';
			}
			return '<span class="listBtnsSpan">'+temp+'</span>'; 
		}} 
	]; 
	var rowList = [10, 20, 30, 50];
	var rownumbers = true; 
	var multiselect = true;
	var config={caption: '教练列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect, callback: function(table){
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
	kindergartenCoachHandle.init(config); 
}); 
