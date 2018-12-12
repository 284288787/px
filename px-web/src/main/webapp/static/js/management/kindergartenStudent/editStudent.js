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
				$(':hidden[name=classId]').val("");
				$(':input[name=className]').val("");
			}
		}
	},{
		'title': '选择班级',
		'object': $('input[name=className]'),
		'url': 'class/chooseClass',
		'urlParams':[{
			type: 'input',
			key: 'schoolId',
			value: $(':hidden[name=schoolId]')
		}],
		'width': '70%',
		'height': '600px', 
		'choosedId': $(':hidden[name=classId]'), 
		'callback':function(classId, className, teacherName){ 
			$(':hidden[name=classId]').val(classId); 
			$(':input[name=className]').val(className);
			$(':input[name=teacherName]').val(teacherName);
		}
	}],
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
	$('#editStudentForm').validate({ 
		rules: {
			schoolName: {
				required: true, 
			},
			className: {
				required: true, 
			},
			name: {
				required: true, 
			},
			sex: {
				required: true, 
			},
			birth: {
				required: false, 
			},
			stature: {
				required: true,
				number: true
			},
			weight: {
				required: true, 
				weight: true
			},
			guardianName: {
				required: false, 
			},
			guardianMobile: {
				required: false, 
				mobile: true,
			}
		},
		messages: { 
			schoolName: {
				required: '必填', 
			},
			className: {
				required: '必填', 
			},
			name: {
				required: '必填', 
			},
			sex: {
				required: '必填', 
			},
			birth: {
				required: '必填', 
			},
			stature: {
				required: '必填', 
				number: '错误的身高(cm)'
			},
			weight: {
				required: '必填', 
				weight: '错误的体重(kg)'
			},
			guardianName: {
				required: '必填', 
			},
			guardianMobile: {
				required: '必填', 
				mobile: '手机号错误',
			}
		} 
	}); 
	
	$('#saveBtn').click(function(){ 
		var flag = $('#editStudentForm').valid();
		if(! flag) return;
		var data=$('#editStudentForm').serializeArray(); 
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value; 
			console.log(name + '' + field.value)
		}); 
		params.weight=params.weight*100;
		params.birth=params.birth + " 00:00:00";
		console.log(JSON.stringify(params));
		$.ajax({
			contentType:'application/json', 
			url: basePath+'student/edit', 
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