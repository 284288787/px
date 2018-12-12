var basePath=$("#basePath").val();
$(function(){
	$("#editResourceForm").validate({
		rules: {
			sourceName: {
				required: true,
				rangelength: [2, 20]
			},
			sourceUrl: {
				maxlength: 100
			},
			type: {
				required: true
			},
			parentId: {
				required: true
			}
		},
		messages: {
			sourceName: {
				required: "必填",
				rangelength: "长度在2至20个字"
			},
			sourceUrl: {
				maxlength: "不能超过100个字符"
			},
			type: {
				required: "必选"
			},
			parentId: {
				required: "必选"
			}
		}
	});
	$("select[name=type]").change(function(){
		var val = this.value;
		if(val){
			$.ajax({
				url: basePath+"resource/getParentTypeBySourceType/" + val,
				type: 'get',
				dataType: 'json',
				success: function(res){
					if(res.status=='SUCCESS'){
						var val=$("select[name=parentId]").attr("value");
						var html = '<option value="">请选择</option>';
						var data = res.data;
						for(var o in data){
							var data2=data[o];
							if(o!='all'){
								html+='<optgroup label="'+o+'"></optgroup>';
							}
							var selected='';
							for(var i in data2){
								var key = data2[i].sourceId;
								if(val==key) selected='selected';
								html+='<option value="'+key+'" '+selected+'>'+data2[i].sourceName+'</option>';
								selected='';
							}
						}
						$("select[name=parentId]").html(html);
					}else{
						artDialog.alert(res.errorMessage)
					}
				}
			});
		}
	});
	$("select[name=type]").change();
	$("#saveBtn").click(function(){
		var flag = $("#editResourceForm").valid();
		if(! flag) return;
		var data=$("#editResourceForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		$.ajax({
			url: basePath+"resource/edit",
			data: params,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status=='SUCCESS'){
					parent.sourceHandle.query();
					art.dialog.close();
				}else{
					artDialog.alert(res.errorMessage)
				}
			}
		});
	});
});