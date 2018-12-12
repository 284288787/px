var basePath=$("#basePath").val();
$(function(){
	$("#editUserInfoForm").validate({
		rules: {
			name: {
				required: true,
				rangelength: [2, 8]
			},
			password: {
				required: true,
				rangelength: [5, 32]
			},
			password2: {
				required: true,
				rangelength: [5, 32],
				equalTo: "#password"
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
			password: {
				required: "必填",
				rangelength: "长度在5至32位"
			},
			password2: {
				required: "必填",
				rangelength: "长度在5至32位",
				equalTo: "密码必须一致"
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
		$("#saveBtn").attr("disabled", true);
		$.ajax({
			url: basePath+"userAccount/add",
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
				$("#saveBtn").removeAttr("disabled");
			}
		});
	});
});