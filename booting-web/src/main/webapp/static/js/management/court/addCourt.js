var basePath=$("#basePath").val();
$(function(){
	initArea();
	$("#editCourtForm").validate({
		rules: {
			courtName: {
				required: true,
				rangelength: [2, 50]
			},
			linkman: {
				required: true,
				rangelength: [2, 8]
			},
			mobile: {
				required: true,
				mobile: true
			},
			startMinute: {
				required: true,
				time: true
			},
			endMinute: {
				required: true,
				time: true
			},
			oneMinute: {
				required: true,
				number: true
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
			courtName: {
				required: "必填",
				rangelength: "长度在2至50个字"
			},
			linkman: {
				required: "必填",
				rangelength: "长度在2至8个字"
			},
			mobile: {
				required: "必填",
				mobile: "手机号错误"
			},
			startMinute: {
				required: "必填",
				time: "时间错误，例如：9:00"
			},
			endMinute: {
				required: "必填",
				time: "时间错误，例如：17:30"
			},
			oneMinute: {
				required: "必填",
				number: "清输入分钟，例如：120"
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
		var flag = $("#editCourtForm").valid();
		if(! flag) return;
		var data=$("#editCourtForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		var sm=timeToNum(params.startMinute);
		var em=timeToNum(params.endMinute);
		params.startMinute=sm;
		params.endMinute=em;
//		var om=params.oneMinute;
//		if((em-sm)%om!=0){
//			artDialog.alert("营业总时长和每场时长不能整除，请修改营业时间")
//			return;
//		}
		$("#saveBtn").attr("disabled", true);
		$.ajax({
			url: basePath+"court/add",
			data: params,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status=='SUCCESS'){
					parent.courtHandle.query();
					art.dialog.close();
				}else if(res.errorCode=="401"){
					artDialog.confirm("不能创建球场，联系号码没有注册，请确认手机号码。是否立即注册？", function(){
						$.ajax({
							url: basePath+"court/addUserAccount",
							data: {account: params.mobile, name: params.linkman, password: '123456'},
							type: 'post',
							dataType: 'json',
							success: function(res){
								if(res.status=='SUCCESS'){
									artDialog.alert("用户创建成功，请再次点击保存，创建球场")
								}else{
									artDialog.alert(res.errorMessage)
								}
							}
						});
					});
				}else{
					artDialog.alert(res.errorMessage);
				}
				$("#saveBtn").removeAttr("disabled");
			}
		});
	});
});

function timeToNum(time){
	var arr=time.split(":");
	return arr[0]*60+arr[1]*1;
}
function initArea(){
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
				sheng+='<option value="'+city.areaId+'">'+city.shortName+'</option>';
			}
		}else if(province.type==1){
			zxs+='<option value="'+province.areaId+'">'+province.shortName+'</option>';
		}else if(province.type==2){
			gat+='<option value="'+province.areaId+'">'+province.shortName+'</option>';
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
function initBusiness(){
	var html='<option value="">请选择行业</option>';
	for(var i in businesses){
		var business=businesses[i];
		html+='<option value="'+business.businessId+'">'+business.businessName+'</option>';
	}
	$("select[name=businessId]").html(html);
}