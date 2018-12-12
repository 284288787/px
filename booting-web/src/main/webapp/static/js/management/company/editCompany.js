var basePath=$("#basePath").val();
var currAreaId=$("#currAreaId").val();
var currBusinessId=$("#currBusinessId").val();
$(function(){
	initArea(currAreaId);
	initBusiness(currBusinessId);
	$("#editCompanyForm").validate({
		rules: {
			companyName: {
				required: true,
				rangelength: [2, 50]
			},
			invoiceTitle: {
				required: true,
				rangelength: [2, 50]
			},
			areaId: {
				required: true
			},
			address: {
				required: true,
				rangelength: [2, 100]
			}
		},
		messages: {
			companyName: {
				required: "必填",
				rangelength: "长度在2至50个字"
			},
			invoiceTitle: {
				required: "必填",
				rangelength: "长度在2至50个字"
			},
			areaId: {
				required: "必选",
			},
			address: {
				required: "必填",
				rangelength: "长度在2至100个字"
			}
		}
	});
	
	$("#saveBtn").click(function(){
		var flag = $("#editCompanyForm").valid();
		if(! flag) return;
		var data=$("#editCompanyForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		$.ajax({
			url: basePath+"company/edit",
			data: params,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status=='SUCCESS'){
					parent.companyHandle.query();
					art.dialog.close();
				}else{
					artDialog.alert(res.errorMessage)
				}
			}
		});
	});
});
function initArea(currId){
	var sheng='';
	var zxs='<optgroup label="直辖市"/>';
	var gat='<optgroup label="港澳台"/>';
	for(var i in areas){
		var province=areas[i];
		if(province.type==3){
			sheng+='<optgroup label="'+province.areaName+'"/>';
			var citys = province.children;
			for(var j in citys){
				var city = citys[j];
				sheng+='<option value="'+city.areaId+'" '+(city.areaId==currId? 'selected': '')+'>'+city.shortName+'</option>';
			}
		}else if(province.type==1){
			zxs+='<option value="'+province.areaId+'" '+(province.areaId==currId? 'selected': '')+'>'+province.shortName+'</option>';
		}else if(province.type==2){
			gat+='<option value="'+province.areaId+'" '+(province.areaId==currId? 'selected': '')+'>'+province.shortName+'</option>';
		}
	}
	var html='<option value="">请选择城市</option>';
	if(zxs.length>25){
		html += zxs;
	}
	if(sheng.length>0){
		html += sheng;
	}
	if(gat.length>25){
		html += gat;
	}
	$("select[name=areaId]").html(html);
}
function initBusiness(currId){
	var html='<option value="">请选择行业</option>';
	for(var i in businesses){
		var business=businesses[i];
		html+='<option value="'+business.businessId+'" '+(business.businessId==currId? 'selected': '')+'>'+business.businessName+'</option>';
	}
	$("select[name=businessId]").html(html);
}