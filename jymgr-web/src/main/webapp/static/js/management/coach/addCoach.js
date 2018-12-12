var workExperienceEditor, awardResumeEditor;
var basePath=$('#basePath').val(); 
var parentParams=artDialog.data('params');
var screenHeight = parentParams.getScreenHeight();
var utilsHandle = new UtilsHandle({
	basePath: basePath, 
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
	kindEditor: [{ 
		object: 'textarea[name=workExperience]',
		width: '100%',
		height: '180px',
		afterCreate: function(obj){ 
			workExperienceEditor = obj; 
		} 
	},{ 
		object: 'textarea[name=awardResume]',
		width: '100%',
		height: '120px',
		afterCreate: function(obj){ 
			awardResumeEditor = obj; 
		} 
	}],
	uploadFile: {uploadFileId: 'uploadImg', items: [{
		uploadBtn: $('#uploadHeadPicBtn'), 
		success: function (data, textStatus) {
			var pic = data.data;
			$('#uploadHeadPicBtn').parent().append('<span style="position: relative;"><img class="dataImg" width="70px" height="70px" src="'+(basePath+pic)+'" data="'+pic+'"><div class="close">X</div></span>'); 
		},
		complete: function (XMLHttpRequest, textStatus) { 
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
					var l = w / h;
					if(h > screenHeight * 0.85){
						h = screenHeight * 0.85;
						w = h * l;
					}
					artDialog.alert2('<div style="width:'+w+'px;height:'+h+'px;"><img style="height:'+h+'px" src="'+$(this).attr("src")+'">') 
				}); 
			}); 
		} 
	},{
		uploadBtn: $('#uploadIdCardZBtn'), 
		success: function (data, textStatus) {
			var pic = data.data;
			$('#uploadIdCardZBtn').attr({"src": basePath+pic, "data": pic}); 
		}
	},{
		uploadBtn: $('#uploadIdCardFBtn'), 
		success: function (data, textStatus) {
			var pic = data.data;
			$('#uploadIdCardFBtn').attr({"src": basePath+pic, "data": pic}); 
		}
	},{
		uploadBtn: $('#uploadFzBtn'), 
		success: function (data, textStatus) {
			var pic = data.data;
			$('#uploadFzBtn').attr({"src": basePath+pic, "data": pic}); 
		}
	},{
		uploadBtn: $('#uploadEduBtn'), 
		success: function (data, textStatus) {
			var pic = data.data;
			$('#uploadEduBtn').attr({"src": basePath+pic, "data": pic}); 
		}
	},{
		uploadBtn: $('#uploadTeachingCertBtn'), 
		success: function (data, textStatus) {
			var pic = data.data;
			$('#uploadTeachingCertBtn').attr({"src": basePath+pic, "data": pic}); 
		}
	}]}
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
			var l = w / h;
			if(h > screenHeight * 0.85){
				h = screenHeight * 0.85;
				w = h * l;
			}
			artDialog.alert2('<div style="width:'+w+'px;height:'+h+'px;"><img style="height:'+h+'px" src="'+$(this).attr("src")+'">') 
		}); 
	}); 
	$('#editCoachForm').validate({ 
		rules: {
			name: {
				required: true, 
				rangelength: [2, 100]
			},
			mobile: {
				required: true, 
				mobile: true,
			},
			sex: {
				required: true, 
			},
			birthTime: {
				required: true, 
			},
			certificateType: {
				required: true, 
			},
			certificateCode: {
				required: true, 
			},
			address: {
				required: true, 
			},
			emergencyContact: {
				required: true, 
			},
			emergencyContactNumber: {
				required: true, 
			},
			emergencyContactAddress: {
				required: true, 
			},
			educationBackground: {
				required: true, 
			},
			school: {
				required: true, 
			},
			specialty: {
				required: true, 
			},
			professionalService: {
				required: true, 
			},
			workExperience: {
				required: true, 
			},
			awardResume: {
				required: true, 
			}
		},
		messages: { 
			name: {
				required: '必填', 
				rangelength: '最少2个字'
			},
			mobile: {
				required: '必填', 
				mobile: '手机号错误',
			},
			sex: {
				required: '必选', 
			},
			birthTime: {
				required: '必选', 
			},
			certificateType: {
				required: '必选', 
			},
			certificateCode: {
				required: '必填', 
			},
			address: {
				required: '必填', 
			},
			emergencyContact: {
				required: '必填', 
			},
			emergencyContactNumber: {
				required: '必填', 
			},
			emergencyContactAddress: {
				required: '必填', 
			},
			educationBackground: {
				required: '必填', 
			},
			school: {
				required: '必填', 
			},
			specialty: {
				required: '必填', 
			},
			professionalService: {
				required: '必选', 
			},
			workExperience: {
				required: '必填', 
			},
			awardResume: {
				required: '必填', 
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
		params.birthTime = params.birthTime + " 00:00:00";
		var headPics = new Array();
		var certificatePics = new Array();
		var eduPics = new Array();
		var teachingCertPics = new Array();
		$(".dataImg").each(function(){
			var path = $(this).attr("data");
			if(path) headPics.push({"picPath": path, "childType": 1, "childTypeName": "头像", "type": 1});
		});
		if(headPics.length == 0){
			artDialog.alert("请上传头像");
			return;
		}
		params["headPic"] = headPics;
		
		if(params.certificateType == 1){
			var path = $("#uploadIdCardZBtn").attr("data");
			if(!path){
				artDialog.alert("请上传身份证正面图片");
				return;
			}
			certificatePics.push({"picPath": path, "childType": 1, "childTypeName": "正面", "type": 2});
			path = $("#uploadIdCardFBtn").attr("data");
			if(!path){
				artDialog.alert("请上传身份证反面图片");
				return;
			}
			certificatePics.push({"picPath": path, "childType": 2, "childTypeName": "反面", "type": 2});
		}else{
			var path = $("#uploadFzBtn").attr("data");
			if(!path){
				artDialog.alert("请上传护照图片");
				return;
			}
			certificatePics.push({"picPath": path, "childType": 1, "childTypeName": "正面", "type": 2});
		}
		params["certificatePics"] = certificatePics;
		
		var path = $("#uploadEduBtn").attr("data");
		if(!path){
			artDialog.alert("请上传学历图片");
			return;
		}
		eduPics.push({"picPath": path, "childType": 1, "childTypeName": "学历", "type": 3});
		params["eduPics"] = eduPics;
		
		$(".cert").each(function(){
			var obj = $(this);
			var type = $("select.teachingCertSelect", obj).val();
			var typeName = $.trim($("select.teachingCertSelect option:selected", obj).text());
			var path = $("img.teachingCertUpload", obj).attr("data");
			if(type && path && typeName){
				teachingCertPics.push({"picPath": path, "childType": type, "childTypeName": typeName, "type": 4});
			}
		});
		if(teachingCertPics.length == 0){
			artDialog.alert("请上传执教证书图片");
			return;
		}
		params["teachingCertPics"] = teachingCertPics;
		
		params["workExperience"]=workExperienceEditor.html();
		params["awardResume"]=awardResumeEditor.html();
		if(!params.workExperience){
			artDialog.alert("请填写工作经历");
			return;
		}
		if(params.name == params.emergencyContact){
			artDialog.alert("紧急联系人不能与教练姓名相同");
			return;
		}
		if(params.mobile == params.emergencyContactNumber){
			artDialog.alert("紧急联系人电话不能与教练手机号相同");
			return;
		}
		if(params.address == params.emergencyContactAddress){
			artDialog.alert("紧急联系人地址不能与教练地址相同");
			return;
		}
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