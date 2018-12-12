var basePath=$("#basePath").val();
$(function(){
	$("#editPackageForm").validate({
		rules: {
			packageName: {
				required: true,
				rangelength: [1, 8]
			},
			price: {
				required: true,
				rangelength: [1, 9],
				money: true
			},
			discount:{
				rangelength: [1, 3],
				discount: true
			}
		},
		messages: {
			packageName: {
				required: "必填",
				rangelength: "长度在1至8个字"
			},
			price: {
				required: "必填",
				rangelength: "只能输入8位有效数字的金额",
				money: "只能输入8位有效数字的金额"
			},
			discount:{
				rangelength: "只能输入10以内的小数或不输入",
				discount: "只能输入10以内的小数或不输入"
			}
		}
	});
	
	$("#saveBtn").click(function(){
		var flag = $("#editPackageForm").valid();
		if(! flag) return;
		var data=$("#editPackageForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			var value= field.value;
			if(name=='price'){
				value=value*100;
			}
			if(name=='discount' && value){
				value=value*10;
			}
			params[name] = value;
			console.log(name + "  " + value)
		});
		$.ajax({
			url: basePath+"package/editPackage",
			data: params,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status=='SUCCESS'){
					parent.packageHandle.query();
					art.dialog.close();
				}else{
					artDialog.alert(res.errorMessage)
				}
			}
		});
	});
});