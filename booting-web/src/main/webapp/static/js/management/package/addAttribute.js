var basePath=$("#basePath").val();
$(function(){
	$("#editAttributeForm").validate({
		rules: {
			attributeName: {
				required: true,
				rangelength: [1, 8]
			}
		},
		messages: {
			attributeName: {
				required: "必填",
				rangelength: "长度在1至8个字"
			}
		}
	});
	
	$("#saveBtn").click(function(){
		var flag = $("#editAttributeForm").valid();
		if(! flag) return;
		var data=$("#editAttributeForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		$("#saveBtn").attr("disabled", true);
		$.ajax({
			url: basePath+"package/addAttribute",
			data: params,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status=='SUCCESS'){
					parent.attrHandle.query();
					art.dialog.close();
				}else{
					artDialog.alert(res.errorMessage)
				}
				$("#saveBtn").removeAttr("disabled");
			}
		});
	});
});