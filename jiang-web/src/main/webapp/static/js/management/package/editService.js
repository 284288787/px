var basePath=$("#basePath").val();
$(function(){
	$("#editServiceForm").validate({
		rules: {
			serviceName: {
				required: true,
				rangelength: [1, 20]
			}
		},
		messages: {
			serviceName: {
				required: "必填",
				rangelength: "长度在1至20个字"
			}
		}
	});
	
	$("#saveBtn").click(function(){
		var flag = $("#editServiceForm").valid();
		if(! flag) return;
		var data=$("#editServiceForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		$.ajax({
			url: basePath+"package/editService",
			data: params,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status=='SUCCESS'){
					parent.serviceHandle.query();
					art.dialog.close();
				}else{
					artDialog.alert(res.errorMessage)
				}
			}
		});
	});
});