var basePath=$("#basePath").val();
$(function(){
	$("#editBusinessForm").validate({
		rules: {
			businessName: {
				required: true,
				rangelength: [2, 20]
			}
		},
		messages: {
			businessName: {
				required: "必填",
				rangelength: "长度在2至20个字"
			}
		}
	});
	
	$("#saveBtn").click(function(){
		var flag = $("#editBusinessForm").valid();
		if(! flag) return;
		var data=$("#editBusinessForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		$.ajax({
			url: basePath+"business/edit",
			data: params,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status=='SUCCESS'){
					parent.businessHandle.query();
					art.dialog.close();
				}else{
					artDialog.alert(res.errorMessage)
				}
			}
		});
	});
});