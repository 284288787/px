//var kk;
var basePath=$('#basePath').val(); 
var parentParams=artDialog.data('params'); 
var utilsHandle = new UtilsHandle({
	basePath: basePath, 
	chooseCity: { 
		'object': $('input[name=areaName]'),
		'width': '70%', 
		'height': '600px',
		'areaId': $(':hidden[name=areaId]').val(),
		'callback':function(areaId, areaName){
			$(':hidden[name=areaId]').val(areaId);
			$(':input[name=areaName]').val(areaName); 
		} 
	},
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
	$('#editKindergartenForm').validate({ 
		rules: {
			account: {
				required: true, 
				rangelength: [6, 20]
			},
			password: {
				required: true, 
				rangelength: [6, 128]
			},
			name: {
				required: true, 
				rangelength: [3, 20]
			},
			linkman: {
				required: false, 
				rangelength: [2, 10]
			},
			mobile: {
				required: false, 
				mobile: true,
				rangelength: [11, 11]
			},
			tel: {
				rangelength: [9, 12]
			},
			areaId: {
				required: true, 
			},
			address: {
				required: true, 
				rangelength: [2, 100]
			},
			intro: {
				required: true, 
				rangelength: [2, 1000]
			}
		},
		messages: { 
			account: {
				required: '必填', 
				rangelength: '长度在6至20个字符'
			},
			password: {
				required: '必填', 
				rangelength: '长度在6至128个字符'
			},
			name: {
				required: '必填', 
				rangelength: '长度在3至20个字'
			},
			linkman: {
				required: '必填', 
				rangelength: '长度在2至10个字'
			},
			mobile: {
				required: '必填', 
				mobile: '手机号错误',
				rangelength: '手机号错误'
			},
			tel: {
				rangelength: '长度在9至12位'
			},
			areaId: {
				required: '必选', 
			},
			address: {
				required: '必填', 
				rangelength: '长度在2至100个字'
			},
			intro: {
				required: '必填', 
				rangelength: '长度在2至1000个字'
			}
		} 
	}); 
	
	$('#saveBtn').click(function(){ 
		var flag = $('#editKindergartenForm').valid();
		if(! flag) return;
		var data=$('#editKindergartenForm').serializeArray(); 
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value; 
			console.log(name + '' + field.value)
		}); 
		console.log(JSON.stringify(params));
		$.ajax({
			contentType:'application/json', 
			url: basePath+'kindergarten/add', 
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