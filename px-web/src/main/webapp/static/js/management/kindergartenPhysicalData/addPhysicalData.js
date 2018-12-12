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
			if(classId != $(':hidden[name=classId]').val()){
				$(':hidden[name=classId]').val(classId); 
				$(':input[name=className]').val(className);
				$(':hidden[name=studentId]').val(""); 
				$(':input[name=name]').val("");
			}
		}
	},{
		'title': '选择幼儿',
		'object': $('input[name=name]'),
		'url': 'student/chooseStudent',
		'urlParams':[{
			type: 'input',
			key: 'classId',
			value: $(':hidden[name=classId]')
		}],
		'width': '70%',
		'height': '600px', 
		'choosedId': $(':hidden[name=studentId]'), 
		'callback':function(studentId, studentName){ 
			$(':hidden[name=studentId]').val(studentId); 
			$(':input[name=name]').val(studentName);
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
	$('#editPhysicalDataForm').validate({ 
		rules: {
			name: {
				required: true,
			},
			stature: {
				required: true,
				number: true,
				rangelength: [1, 3]
			},
			weight: {
				required: true,
				weight: true,
				rangelength: [1, 4]
			},
			sitReach: {
				required: true,
				weight: true,
				rangelength: [1, 4]
			},
			jump: {
				required: true,
				weight: true,
				rangelength: [1, 4]
			},
			throwTennis: {
				required: true,
				weight: true,
				rangelength: [1, 4]
			},
			doubleJump: {
				required: true,
				weight: true,
				rangelength: [1, 4]
			},
			run10: {
				required: true,
				weight: true,
				rangelength: [1, 4]
			},
			balance: {
				required: true,
				weight: true,
				rangelength: [1, 4]
			},
			testTime: {
				required: true, 
			},
			intro: {
				rangelength: [10, 200]
			}
		},
		messages: {
			name: {
				required: '必选',
			},
			stature: {
				required: '必填',
				weight: '错误的值',
				rangelength: '错误的值'
			},
			weight: {
				required: '必填',
				weight: '错误的值',
				rangelength: '错误的值'
			},
			sitReach: {
				required: '必填',
				weight: '错误的值',
				rangelength: '错误的值'
			},
			jump: {
				required: '必填',
				weight: '错误的值',
				rangelength: '错误的值'
			},
			throwTennis: {
				required: '必填',
				weight: '错误的值',
				rangelength: '错误的值'
			},
			doubleJump: {
				required: '必填',
				weight: '错误的值',
				rangelength: '错误的值'
			},
			run10: {
				required: '必填',
				weight: '错误的值',
				rangelength: '错误的值'
			},
			balance: {
				required: '必填',
				weight: '错误的值',
				rangelength: '错误的值'
			},
			testTime: {
				required: '必选', 
			},
			intro: {
				rangelength: '10字以上'
			}
		} 
	}); 
	
	$('#saveBtn').click(function(){ 
		var flag = $('#editPhysicalDataForm').valid();
		if(! flag) return;
		var data=$('#editPhysicalDataForm').serializeArray(); 
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value; 
			console.log(name + '' + field.value)
		}); 
		params.testTime=params.testTime + ":00";
		params.weight=params.weight*100;
		params.sitReach=params.sitReach*100;
		params.jump=params.jump*100;
		params.throwTennis=params.throwTennis*100;
		params.doubleJump=params.doubleJump*100;
		params.run10=params.run10*100;
		params.balance=params.balance*100;
		console.log(JSON.stringify(params));
		$.ajax({
			contentType:'application/json', 
			url: basePath+'physicalData/add', 
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