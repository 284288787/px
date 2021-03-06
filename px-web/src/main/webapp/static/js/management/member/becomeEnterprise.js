var basePath=$("#basePath").val();
$(function(){
	$("#becomeEnterpriseForm").validate({
		rules: {
			userId: {
				required: true,
			},
			packageId: {
				required: true,
			}
		},
		messages: {
			userId: {
				required: '必填'
			},
			packageId: {
				required: "必选",
			}
		}
	});
	
	$("#saveBtn").click(function(){
		var flag = $("#becomeEnterpriseForm").valid();
		if(! flag) return;
		var data=$("#becomeEnterpriseForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		$.ajax({
			url: basePath+"member/levelup",
			data: params,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status=='SUCCESS'){
					art.dialog.close();
					artDialog.alert("成功");
				}else{
					artDialog.alert(res.errorMessage);
				}
			}
		});
	});
});