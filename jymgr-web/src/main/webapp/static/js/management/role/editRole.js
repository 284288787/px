var basePath=$("#basePath").val();
var parentParams=artDialog.data('params');
$(function(){
	$("#editRoleForm").validate({
		rules: {
			roleName: {
				required: true,
				rangelength: [2, 8]
			},
			roleRemark: {
				required: true,
				maxlength: 30
			}
		},
		messages: {
			sourceName: {
				required: "必填",
				rangelength: "长度在2至8个字"
			},
			sourceUrl: {
				required: "必填",
				maxlength: "不能超过30个字"
			}
		}
	});
	
	$("#saveBtn").click(function(){
		var flag = $("#editRoleForm").valid();
		if(! flag) return;
		var data=$("#editRoleForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		$.ajax({
			url: basePath+"role/edit",
			data: params,
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