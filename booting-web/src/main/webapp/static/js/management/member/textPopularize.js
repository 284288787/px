var basePath=$("#basePath").val();
$(function(){
	$("#editPopularizeForm").validate({
		rules: {
			title: {
				required: true,
				rangelength: [2, 25]
			}
		},
		messages: {
			title: {
				required: "必填",
				rangelength: "长度在2至25个字"
			}
		}
	});
	
	$("#saveBtn").click(function(){
		var flag = $("#editPopularizeForm").valid();
		if(! flag) return;
		var data=$("#editPopularizeForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		params.content=kk.html();
		if(!params.content) artDialog.alert("图文不能为空");
		$.ajax({
			url: basePath+"member/savePopularize",
			data: params,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status=='SUCCESS'){
					art.dialog.close();
				}else{
					artDialog.alert(res.errorMessage)
				}
			}
		});
	});
});