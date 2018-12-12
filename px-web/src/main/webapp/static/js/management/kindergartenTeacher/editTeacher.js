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
			$(':hidden[name=schoolId]').val(schoolId); 
			$(':input[name=schoolName]').val(schoolName);
		}
	}], 
	uploadFile: {
		uploadBtn: $("#uploadBtn"),
		uploadFileId: "uploadImg",
		success: function (data, textStatus) {
			var pic = data.data[0];
			$(".dataImg").attr({src: basePath+pic, data: pic}).show();
			$(":hidden[name=pic]").val(pic);
		},
		complete: function (XMLHttpRequest, textStatus) {
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
  			});
		}
	}
//	kindEditor: { 
//		object: 'textarea[name=notice]',
//		width: '100%',
//		height: '350px',
//		afterCreate: function(obj){ 
//			kk = obj; 
//		} 
//	}
});
$(function(){
//	$('.close').unbind().click(function(){
//		$(this).parent().remove();
//	}); 
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
	});
	$('#editTeacherForm').validate({ 
		rules: {
			schoolName: {
				required: true, 
			},
			pic: {
				required: false, 
			},
			teacherName: {
				required: true, 
			},
			teacherMobile: {
				required: true, 
				mobile: true,
			},
			intro: {
				required: true, 
				rangelength: [2, 1000]
			}
		},
		messages: { 
			schoolName: {
				required: '必填', 
			},
			pic: {
				required: '必传', 
			},
			teacherName: {
				required: '必填', 
			},
			teacherMobile: {
				required: '必填', 
				mobile: '手机号错误',
			},
			intro: {
				required: '必填', 
				rangelength: '长度在2至1000个字'
			}
		} 
	}); 
	
	$('#saveBtn').click(function(){ 
		var flag = $('#editTeacherForm').valid();
		if(! flag) return;
		var data=$('#editTeacherForm').serializeArray(); 
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value; 
			console.log(name + '' + field.value)
		}); 
		console.log(JSON.stringify(params));
		$.ajax({
			contentType:'application/json', 
			url: basePath+'teacher/edit', 
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