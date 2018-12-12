var contentEditor;
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
		object: 'textarea[name=content]',
		width: '100%',
		height: '300px',
		afterCreate: function(obj){ 
			contentEditor = obj; 
		} 
	}],
	uploadFile: {uploadFileId: 'uploadImg', items: [{
		uploadBtn: $('#uploadPicBtn'), 
		success: function (data, textStatus) {
			var pic = data.data;
			$('#uploadPicBtn').parent().append('<span style="position: relative;margin-right: 3px;"><img class="dataImg" width="70px" height="70px" src="'+(basePath+pic)+'" data="'+pic+'"><div class="close">X</div></span>'); 
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
	}]}
});
$(function(){
	parentParams.ajax({
		url: basePath+"productTypeCurriculum/allCurriculumType",
		success: function(res){
			html='<option value="">请选择...</option>';
			for(var o in res){
				var t = res[o];
				html+='<option value="'+t.typeId+'">'+t.typeName+'</option>'
			}
			$("select[name=typeId]").html(html);
		}
	});
	$(":radio[name=state]").change(function(){
		var val = $(this).val();
		if(val == 1){
			$("input[name=effectiveTime], input[name=failureTime]").attr({"readOnly": true, "disabled": true});
			$("input[name=effectiveTime], input[name=failureTime]").parents("tr").find("strong").css("color", "gray");
		}else{
			$("input[name=effectiveTime], input[name=failureTime]").removeAttr("readOnly disabled");
			$("input[name=effectiveTime], input[name=failureTime]").parents("tr").find("strong").css("color", "black");
		}
	});
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
	$('#editCurriculumForm').validate({ 
		rules: {
			typeId: {
				required: true, 
			},
			name: {
				required: true, 
				rangelength: [2, 10]
			},
			price: {
				required: true, 
				money: true,
			},
			state: {
				required: true, 
			},
			effectiveTime: {
				required: true, 
			},
			failureTime: {
				required: true, 
			},
			intro: {
				required: true, 
			},
			content: {
				required: true, 
			}
		},
		messages: { 
			typeId: {
				required: '必选', 
			},
			name: {
				required: '必填', 
				rangelength: '2至10个字'
			},
			price: {
				required: '必填', 
				money: '请填写正确的金额',
			},
			state: {
				required: '必选', 
			},
			effectiveTime: {
				required: '必选', 
			},
			failureTime: {
				required: '必填', 
			},
			intro: {
				required: '必填', 
			},
			content: {
				required: '必填', 
			}
		} 
	}); 
	
	$('#saveBtn').click(function(){ 
		var flag = $('#editCurriculumForm').valid();
		if(! flag) return;
		var data=$('#editCurriculumForm').serializeArray(); 
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value; 
			console.log(name + '\t' + field.value)
		});
		
		var pictures = new Array();
		$(".dataImg").each(function(){
			var path = $(this).attr("data");
			if(path) pictures.push({"picPath": path});
		});
		if(pictures.length == 0){
			artDialog.alert("请上传课程图片");
			return;
		}
		params["pictures"] = pictures;
		params["content"] = contentEditor.html();
		if(! params.content){
			artDialog.alert("课程详介不能为空"); 
		}
		if(params.price){
			params.price = params.price * 100;
		}
		console.log(JSON.stringify(params));
		
		$.ajax({
			contentType:'application/json', 
			url: basePath+'curriculum/add', 
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