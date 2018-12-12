var basePath=$("#basePath").val();
$(function(){
	$("#editUserInfoForm").validate({
		rules: {
			name: {
				required: true,
				rangelength: [2, 8]
			},
			account: {
				required: true,
				mobile: true,
				rangelength: [11, 11]
			}
		},
		messages: {
			name: {
				required: "必填",
				rangelength: "长度在2至8个字"
			},
			account: {
				required: "必填",
				rangelength: "请输入正确的手机号"
			}
		}
	});
	
	$("#saveBtn").click(function(){
		var flag = $("#editUserInfoForm").valid();
		if(! flag) return;
		var data=$("#editUserInfoForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		$.ajax({
			url: basePath+"userAccount/edit",
			data: params,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status=='SUCCESS'){
					parent.userHandle.query();
					art.dialog.close();
				}else{
					artDialog.alert(res.errorMessage)
				}
			}
		});
	});
});