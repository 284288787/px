var kk;
var basePath=$("#basePath").val();
var parentParams=artDialog.data("params");
var utilsHandle = new UtilsHandle({
	basePath: basePath,
	chooseCity: {
		"object": $("input[name=areaName]"),
		"width": "70%",
		"height": "600px",
		"areaId": $(":hidden[name=areaId]").val(), 
		"callback":function(areaId, areaName){
			$(":hidden[name=areaId]").val(areaId);
			$(":input[name=areaName]").val(areaName);
		}
	},
	kindEditor: [{
		object: 'textarea[name=notice]',
		width: '100%',
		height: '350px',
		afterCreate: function(obj){
			kk = obj;
		}
	}],
	uploadFile: {
		uploadBtn: $("#uploadBtn"),
		uploadFileId: "uploadImg",
		success: function (data, textStatus) {
			var pics = data.data;
			for(var i in pics){
				var pic = pics[i];
				$("#uploadBtn").parent().append('<span style="position: relative;"><img class="dataImg" width="70px" height="70px" src="'+(basePath+pic)+'" data="'+pic+'"><div class="close">X</div></span>');
			}
		},
		complete: function (XMLHttpRequest, textStatus) {
  			$(".close").unbind().click(function(){
  				$(this).parent().remove();
  			});
  			$(".dataImg").unbind().click(function(){
  				if($("#viewImg").length>0){
  					$("#viewImg").remove();
  				}
				$("body").append('<img id="viewImg" style="display:none" src="'+$(this).attr("src")+'">');
				$("#viewImg").load(function(){
					var limit=1200;
					if(w > limit){
						h = limit * (h/w);
						w = limit;
					}
      				artDialog.alert2('<div style="width:'+w+'px;height:'+h+'px"><img width="'+w+'" src="'+$(this).attr("src")+'">')
				});
  			});
		}
	}
});
$(function(){
	$(".close").unbind().click(function(){
		$(this).parent().remove();
	});
	$(".dataImg").unbind().click(function(){
		if($("#viewImg").length>0){
			$("#viewImg").remove();
		}
		$("body").append('<img id="viewImg" style="display:none" src="'+$(this).attr("src")+'">');
		$("#viewImg").load(function(){
			var w=$(this).width();
			var h=$(this).height();
				artDialog.alert2('<div style="width:'+w+'px;height:'+h+'px"><img src="'+$(this).attr("src")+'">')
		});
	});
	$("#editTrainingItemForm").validate({
		rules: {
			title: {
				required: true,
				rangelength: [2, 50]
			},
			areaName: {
				required: true
			},
			beginTime: {
				required: true
			},
			endTime: {
				required: true
			},
			type: {
				required: true
			},
			price: {
			  required: true,
			  money: true
			},
			address: {
				required: true,
				rangelength: [2, 100]
			},
			intro: {
				required: true,
			}
		},
		messages: {
			title: {
				required: "必填",
				rangelength: "长度在2至8个字"
			},
			areaName: {
				required: "必选"
			},
			type: {
				required: "必选"
			},
			beginTime: {
				required: "必选"
			},
			endTime: {
				required: "必选"
			},
			price: {
			  required: "必填",
        money: "价格有误"
      },
			address: {
				required: "必填",
				rangelength: "长度在2至100个字"
			},
			intro: {
				required: "必填"
			}
		}
	});
	
	$("#saveBtn").click(function(){
		var flag = $("#editTrainingItemForm").valid();
		if(! flag) return;
		var data=$("#editTrainingItemForm").serializeArray();
		var params = {};
		$.each(data, function(i, field){
			var name = field.name;
			params[name] = field.value;
			console.log(name + "  " + field.value)
		});
		params["notice"]=kk.html();
		if(!params.notice) artDialog.alert("报名须知必填");
		var pictures=new Array();
		$(".dataImg").each(function(){
			var path=$(this).attr("data");
			pictures.push({"picturePath": path});
		});
		var prices=new Array();
		$("li .price").each(function(){
		  var applyItemId=$(this).attr("applyItemId");
		  var price=$(this).val();
		  prices.push({"applyItemId": applyItemId, "price": price * 100});
		});
		params["beginTime"]=params.beginTime+" 00:00:00";
		params["endTime"]=params.endTime+" 00:00:00";
		params["price"]='';
		params["pictures"]=pictures;
		params["prices"]=prices;
		console.log(JSON.stringify(params));
		$.ajax({
			contentType:"application/json",
			url: basePath+"trainingItem/edit",
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