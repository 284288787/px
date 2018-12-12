var basePath=$('#basePath').val(); 
var parentParams=artDialog.data('params'); 
$(function(){
	$('#editCustomerForm').validate({ 
		rules: {
			orgName: {
				required: true,
				rangelength: [2, 50]
			},
			contactName: {
				required: true, 
				rangelength: [2, 20]
			},
			contactPhone: {
				required: true, 
				mobile: true,
			},
			orgAddress: {
				required: true, 
			},
			peopleNum: {
				required: true,
				number: true
			},
			type: {
				required: true, 
			}
		},
		messages: { 
			orgName: {
				required: '必填',
				rangelength: '2至30个字'
			},
			contactName: {
				required: '必填', 
				rangelength: '2至10个字'
			},
			contactPhone: {
				required: '必填', 
				mobile: '填写正确的手机号',
			},
			orgAddress: {
				required: '必填', 
			},
			peopleNum: {
				required: '必填',
				number: '必须是正整数',
			},
			type: {
				required: '必选', 
			}
		} 
	}); 
	
	$('#saveBtn').click(function(){ 
		var flag = $('#editCustomerForm').valid();
		if(! flag) return;
		var data=$('#editCustomerForm').serializeArray(); 
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value; 
			console.log(name + '' + field.value)
		}); 
		console.log(JSON.stringify(params));
		$.ajax({
			contentType:'application/json', 
			url: basePath+'customer/edit', 
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