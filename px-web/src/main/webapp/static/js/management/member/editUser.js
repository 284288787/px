var basePath=$("#basePath").val();
$(function(){
	var uploadFunc = function(){
		$.ajaxFileUpload({
			fileElementId: "uploadImg",
    		url: basePath+'common/uploadFile',
    		dataType: 'json',
    		data: { star: 'lh'},
    		beforeSend: function (XMLHttpRequest) {
      			$("#saveBtn").attr("disabled", true);
    		},
    		success: function (data, textStatus) {
    			var pic = data.data;
    			$("#uploadBtn").attr("src", basePath+pic);
    			$(":hidden[name=photo]").val(pic);
    		},
    		error: function (XMLHttpRequest, textStatus, errorThrown) {
	      		var msg = "服务器出错，错误内容：" + XMLHttpRequest.responseText;
	     		alert(msg)
	    	},
    		complete: function (XMLHttpRequest, textStatus) {
      			$("#saveBtn").attr("disabled", false);
      			$("#uploadDiv").html('<input type="file" name="uploadImg" id="uploadImg">');
      			$("#uploadDiv #uploadImg").on("change", uploadFunc);
    		}
  		});
	}
	$("#uploadImg").on("change", uploadFunc);
	$("#uploadBtn").click(function(){
		$("#uploadImg").click();
	});
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
			},
			age: {
				number: true,
				rangelength: [2, 2]
			},
			stature: {
				number: true,
				rangelength: [3, 3]
			},
			weight: {
				weight: true
			},
			poloShirtNo: {
				required: true,
				number: true,
				rangelength: [1, 2]
			},
			teamLocation: {
				required: true
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
			},
			age: {
				number: "请填2位数的年龄",
				rangelength: "请填2位数的年龄"
			},
			stature: {
				number: "请填3位数的身高",
				rangelength: "请填3位数的身高"
			},
			weight: {
				weight: "体重必填"
			},
			poloShirtNo: {
				required: "球衣编号必填",
				number: "必须是数字",
				rangelength: "请填3位数以内的数字"
			},
			teamLocation: {
				required: "必选",
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
		var weight=params.weight;
		if(weight){
			params.weight=weight* 10;
		}
		$.ajax({
			url: basePath+"member/edit",
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