var basePath=$("#basePath").val();
var args=artDialog.data("params");
$(function(){
	$("#courtName").text(args.courtName);
	$("#courtId").val(args.courtId);
	$("#editSiteForm").validate({
		rules: {
			siteName: {
				required: true,
				rangelength: [2, 50]
			},
			specification: {
				required: true
			},
			knifing: {
				required: true
			}
		},
		messages: {
			siteName: {
				required: "必填",
				rangelength: "长度在2至50个字"
			},
			specification: {
				required: "必选",
			},
			knifing: {
				required: "必选"
			}
		}
	});
	
	$("select[name=specification]").change(function(){
		var val=this.value;
		if(val==1){
			$("#knifing1").removeAttr("disabled");
			$("#knifing1").attr("checked", true);
		}else{
			$("#knifing1").attr("disabled", true);
			$("#knifing2").attr("checked", true);
		}
	});
	$("#saveBtn").click(function(){
		var flag = $("#editSiteForm").valid();
		if(! flag) return;
		var data=$("#editSiteForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		$("#saveBtn").attr("disabled", true);
		$.ajax({
			url: basePath+"court/addSite",
			data: params,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status=='SUCCESS'){
					args.courtHandle.siteHandle.query();
					art.dialog.close();
				}else{
					artDialog.alert(res.errorMessage)
				}
				$("#saveBtn").removeAttr("disabled");
			}
		});
	});
});
