var kk1,kk2;
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
	},
	kindEditor: [{ 
		object: 'textarea[name=trainingRecord]',
		width: '100%',
		height: '200px',
		afterCreate: function(obj){ 
			kk1 = obj; 
		} 
	},{ 
		object: 'textarea[name=workExperience]',
		width: '100%',
		height: '200px',
		afterCreate: function(obj){ 
			kk2 = obj; 
		} 
	}]
});
$(function(){
	$('.close').unbind().click(function(){
		$(this).parent().remove();
	}); 
	$('.dataImg').unbind().click(function(){
		if($('#viewImg').length>0){ 
			$('#viewImg').remove(); 
		} 
		$('body').append('<img id="viewImg" style="display:none" src="'+$(this).attr("src")+'">');
		$('#viewImg').load(function(){
			var w=$(this).width();
			var h=$(this).height(); 
				artDialog.alert2('<div style="width:'+w+'px;height:'+h+'px"><img src="'+$(this).attr("src")+'">') 
		}); 
	}); 
	$('#editCoachForm').validate({ 
		rules: {
			name: {
				required: true, 
			},
			sex: {
				required: true, 
			},
			age: {
				required: true, 
				number: true,
				rangelength: [2, 2]
			},
			certificateCode: {
				required: true, 
			},
			address: {
				required: true, 
			},
			mobile: {
				required: true, 
				mobile: true,
			}
		},
		messages: { 
			name: {
				required: '必填', 
			},
			sex: {
				required: '必选', 
			},
			age: {
				required: '必填',
				number: '输入正确的年龄',
				rangelength: '输入正确的年龄'
			},
			certificateCode: {
				required: '必填', 
			},
			address: {
				required: '必填', 
			},
			mobile: {
				required: '必填', 
				mobile: '手机号错误',
			}
		} 
	}); 
	
	$('#saveBtn').click(function(){ 
		var flag = $('#editCoachForm').valid();
		if(! flag) return;
		var data=$('#editCoachForm').serializeArray(); 
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value; 
			console.log(name + '' + field.value)
		}); 
		params['trainingRecord'] = kk1.html();
		params['workExperience'] = kk2.html();
		console.log(JSON.stringify(params));
		$.ajax({
			contentType:'application/json', 
			url: basePath+'coach/add', 
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