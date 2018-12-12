var basePath=$("#basePath").val();
$(function(){
	$("#editAppVersionForm").validate({
		rules: {
			version: {
				required: true,
				rangelength: [3, 20]
			},
			url: {
				required: true,
				rangelength: [20, 200]
			},
			upgrade: {
				required: true
			}
		},
		messages: {
			version: {
				required: "必填",
				rangelength: "长度在3至20个字"
			},
			url: {
				required: "必填",
				rangelength: "长度在20至200个字"
			},
			upgrade: {
				required: "必选"
			}
		}
	});
	
	$("#saveBtn").click(function(){
		var flag = $("#editAppVersionForm").valid();
		if(! flag) return;
		var data=$("#editAppVersionForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		$.ajax({
			url: basePath+"appv/edit",
			data: params,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status=='SUCCESS'){
					parent.appHandle.query();
					art.dialog.close();
				}else{
					artDialog.alert(res.errorMessage)
				}
			}
		});
	});
});