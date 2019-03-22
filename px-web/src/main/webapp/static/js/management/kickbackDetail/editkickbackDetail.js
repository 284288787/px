var basePath=$('#basePath').val(); 
var parentParams=artDialog.data('params'); 
$(function(){
	$('#editkickbackDetailForm').validate({ 
		rules: {
			wxOrderNumber: {
			  required: true, 
			}
		},
		messages: { 
			wxOrderNumber: {
			  required: "必填", 
			}
		} 
	}); 
	
	$('#saveBtn').click(function(){ 
		var flag = $('#editkickbackDetailForm').valid();
		if(! flag) return;
		var data=$('#editkickbackDetailForm').serializeArray(); 
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value; 
			console.log(name + '' + field.value)
		}); 
		console.log(JSON.stringify(params));
		$.ajax({
			contentType:'application/json', 
			url: basePath+'kickbackDetail/edit', 
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