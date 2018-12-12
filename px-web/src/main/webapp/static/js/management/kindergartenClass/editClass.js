//var kk;
var basePath=$('#basePath').val(); 
var parentParams=artDialog.data('params'); 
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
			if(schoolId != $(':hidden[name=schoolId]').val()){
				$(':hidden[name=schoolId]').val(schoolId); 
				$(':input[name=schoolName]').val(schoolName);
				$(':hidden[name=teacherId]').val("");
				$(':input[name=teacherName]').val("");
			}
		}
	},{
		'title': '选择班主任',
		'object': $('input[name=teacherName]'),
		'url': 'teacher/chooseTeacher',
		'urlParams':[{
			type: 'input',
			key: 'schoolId',
			value: $(':hidden[name=schoolId]')
		}],
		'width': '70%',
		'height': '600px', 
		'choosedId': $(':hidden[name=teacherId]'), 
		'callback':function(teacherId, teacherName, teacherMobile){ 
			$(':hidden[name=teacherId]').val(teacherId); 
			$(':input[name=teacherName]').val(teacherName);
			$(':input[name=teacherMobile]').val(teacherMobile);
		}
	}],
//	chooseCity: { 
//		'object': $('input[name=areaName]'),
//		'width': '70%', 
//		'height': '600px',
//		'areaId': $(':hidden[name=areaId]').val(),
//		'callback':function(areaId, areaName){
//			$(':hidden[name=areaId]').val(areaId);
//			$(':input[name=areaName]').val(areaName); 
//		} 
//	},
//	kindEditor: { 
//		object: 'textarea[name=notice]',
//		width: '100%',
//		height: '350px',
//		afterCreate: function(obj){ 
//			kk = obj; 
//		} 
//	},
//	uploadFile: { 
//		uploadBtn: $('#uploadBtn'), 
//		uploadFileId: 'uploadImg',
//		success: function (data, textStatus) {
//			var pic = data.data;
//			$('#uploadBtn').parent().append('<span style="position: relative;"><img class="dataImg" width="70px" height="70px" src="'+(basePath+pic)+'" data="'+pic+'"><div class="close">X</div></span>'); 
//		},
//		complete: function (XMLHttpRequest, textStatus) { 
//			$('.close').unbind().click(function(){
//				$(this).parent().remove();
//			}); 
//			$('.dataImg').unbind().click(function(){
//				if($('#viewImg').length>0){ 
//					$('#viewImg').remove(); 
//				} 
//				$('body').append('<img id="viewImg" style="display:none" src="'+$(this).attr("src")+'">');
//				$('#viewImg').load(function(){
//					var w=$(this).width();
//					var h=$(this).height(); 
//				artDialog.alert2('<div style="width:'+w+'px;height:'+h+'px"><img src="'+$(this).attr("src")+'">') 
//				}); 
//			}); 
//		} 
//	} 
});
$(function(){
//	$('.close').unbind().click(function(){
//		$(this).parent().remove();
//	}); 
//	$('.dataImg').unbind().click(function(){
//		if($('#viewImg').length>0){ 
//			$('#viewImg').remove(); 
//		} 
//		$('body').append('<img id="viewImg" style="display:none" src="'+$(this).attr("src")+'">');
//		$('#viewImg').load(function(){
//			var w=$(this).width();
//			var h=$(this).height(); 
//				artDialog.alert2('<div style="width:'+w+'px;height:'+h+'px"><img src="'+$(this).attr("src")+'">') 
//		}); 
//	}); 
	$('#editClassForm').validate({ 
		rules: {
			name: {
				required: true, 
				rangelength: [2, 20]
			},
			schoolName: {
				required: true, 
			},
			teacherName: {
				required: true, 
			},
			intro: {
				required: true, 
				rangelength: [2, 1000]
			}
		},
		messages: { 
			name: {
				required: '必填', 
				rangelength: '长度在2至20个字'
			},
			schoolName: {
				required: '必填', 
			},
			teacherName: {
				required: '必填', 
			},
			intro: {
				required: '必填', 
				rangelength: '长度在2至1000个字'
			}
		} 
	}); 
	
	$('#saveBtn').click(function(){ 
		var flag = $('#editClassForm').valid();
		if(! flag) return;
		var data=$('#editClassForm').serializeArray(); 
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value; 
			console.log(name + '' + field.value)
		}); 
		console.log(JSON.stringify(params));
		$.ajax({
			contentType:'application/json', 
			url: basePath+'class/edit', 
			data: JSON.stringify(params), 
			type: 'post', 
			dataType: 'json', 
			success: function(res){ 
				if(res.status=='SUCCESS'){
					parentParams.query(); 
					art.dialog.close(); 
				}else{
					artDialog.alert(res.errorMessage) 
				} 
			} 
		}); 
	});
});