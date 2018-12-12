var basePath=$("#basePath").val();
var parentParams=artDialog.data('params');
$(function(){
	new UtilsHandle({
		basePath: basePath,
		uploadFile: {uploadFileId: 'uploadImg', items: [{
			uploadBtn: $('#uploadBtn'), 
			success: function (data, textStatus) {
				var pic = data.data;
				$('#uploadBtn').attr({"src": basePath+pic, "data": pic}); 
				$('input[name=icon]').val(pic);
			}
		}]}
	});
	$("#editPointLevelForm").validate({
		rules: {
			level: {
				required: true,
				numberOrZero: true,
			},
			name: {
				required: true,
				rangelength: [2, 10]
			},
			icon: {
				required: true
			},
			pointStep: {
				required: true,
				number: true
			},
			point: {
				required: true,
				number: true
			},
			calorieStep: {
				required: true,
				number: true
			},
			distanceStep: {
				required: true,
				number: true
			}
		},
		messages: {
			level: {
				required: "必填"
			},
			name: {
				required: "必填",
				rangelength: "2至10个字"
			},
			icon: {
				required: "必填"
			},
			pointStep: {
				required: "必填"
			},
			point: {
				required: "必填"
			},
			calorieStep: {
				required: "必填"
			},
			distanceStep: {
				required: "必填"
			}
		}
	});
	
	$("#saveBtn").click(function(){
		var flag = $("#editPointLevelForm").valid();
		if(! flag) return;
		var data=$("#editPointLevelForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		$("#saveBtn").attr("disabled", true);
		$.ajax({
			contentType:'application/json', 
			url: basePath+"pointConfig/edit",
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
				$("#saveBtn").removeAttr("disabled");
			}
		});
	});
});