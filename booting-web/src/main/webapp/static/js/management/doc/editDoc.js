var basePath=$("#basePath").val();
$(function(){
	$("#editDocForm").validate({
		rules: {
			title: {
				required: true,
				rangelength: [2, 25]
			}/*,
			content: {
				required: true,
				rangelength: [2, 100]
			}*/
		},
		messages: {
			title: {
				required: "必填",
				rangelength: "长度在2至25个字"
			}/*,
			content: {
				required: "必填",
				rangelength: "长度在2至100个字"
			}*/
		}
	});
	
	$("#saveBtn").click(function(){
		var flag = $("#editDocForm").valid();
		if(! flag) return;
		var data=$("#editDocForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		if(params.type==2){
			params.content=kk.html();
			if(!params.content) artDialog.alert("资讯内容不能为空");
		}
		$.ajax({
			url: basePath+"doc/edit",
			data: params,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status=='SUCCESS'){
					parent.docHandle.query();
					art.dialog.close();
				}else{
					artDialog.alert(res.errorMessage)
				}
			}
		});
	});
});