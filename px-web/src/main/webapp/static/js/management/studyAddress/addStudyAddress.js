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
	$('#editStudyAddressForm').validate({ 
		rules: {
			addrName: {
			},
			enabled: {
			}
		},
		messages: { 
			addrName: {
			},
			enabled: {
			}
		} 
	}); 
	
	$('#saveBtn').click(function(){ 
		var flag = $('#editStudyAddressForm').valid();
		if(! flag) return;
		var data=$('#editStudyAddressForm').serializeArray(); 
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value; 
			console.log(name + '' + field.value)
		}); 
		console.log(JSON.stringify(params));
		$.ajax({
			contentType:'application/json', 
			url: basePath+'studyAddress/add', 
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