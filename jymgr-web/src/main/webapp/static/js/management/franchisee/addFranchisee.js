//var kk;
var basePath=$('#basePath').val(); 
var parentParams=artDialog.data('params'); 
var utilsHandle = new UtilsHandle({
	basePath: basePath, 
	uploadFile: {uploadFileId: 'uploadImg', items: [{
		uploadBtn: $('#uploadCompactPathBtn'), 
		success: function (data, textStatus) {
			var pic = data.data;
			$('#uploadCompactPathBtn').parent().find("a").remove();
			$('#uploadCompactPathBtn').parent().append('<a target="_blank" href="'+basePath+pic+'" data="'+pic+'">合同.pdf</a>'); 
		},
		complete: function (XMLHttpRequest, textStatus) {} 
	}]}
});
$(function(){
	$('#editFranchiseeForm').validate({ 
		rules: {
			companyName: {
				required: true, 
			},
			legalPerson: {
				required: true, 
				rangelength: [2, 20]
			},
			contactPhone: {
				required: true, 
				mobile: true,
			},
			companyAddress: {
				required: true, 
			},
			compactPath: {
				required: true, 
			}
		},
		messages: { 
			companyName: {
				required: '必填', 
			},
			legalPerson: {
				required: '必填', 
				rangelength: '2至20个字'
			},
			contactPhone: {
				required: '必填', 
				mobile: '手机号错误',
			},
			companyAddress: {
				required: '必填', 
			},
			compactPath: {
				required: '必填', 
			}
		} 
	}); 
	
	$('#saveBtn').click(function(){ 
		var flag = $('#editFranchiseeForm').valid();
		if(! flag) return;
		var data=$('#editFranchiseeForm').serializeArray(); 
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value; 
			console.log(name + '' + field.value)
		}); 
		params["compactPath"] = $('#uploadCompactPathBtn').parent().find("a").attr("data");
		if(! params.compactPath){
			artDialog.alert("请上传签名合同")
			return false;
		}
		console.log(JSON.stringify(params));
		$.ajax({
			contentType:'application/json', 
			url: basePath+'franchisee/add', 
			data: JSON.stringify(params), 
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