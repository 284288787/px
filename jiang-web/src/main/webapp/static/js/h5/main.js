$(function(){
	var infoHtml=
		'<div class="wtleft">                                                                                '+
		'	<a href="'+basePath+'o-user"><img class="headPic" src="{{headPic}}"></a>                                  '+
		'</div>                                                                                              '+
		'<div class="info">                                                                                  '+
		'	<div>昵称：<span class="nickname">{{nickname}}</span></div>                                          '+
		'	<div>余额：<span class="price">{{goldNums}}</span> 金币 <a href="'+basePath+'o-balance" class="rightBtn">提现</a></div>       '+
		'	<div>第一轮可选<span class="price">{{firstNums}}</span>个号码 <a href="'+basePath+'o-more" class="rightBtn">获取更多</a></div>'+
		'</div>                                                                                              ';
	Lottery.config({
		basePath: basePath
	});
	var num = 3;
	var nums_1 = 1;
	var login = Lottery.isLogin();
	if(login){
		var mem = Lottery.getMember();
		$(".dh").text((mem.ballNums ? mem.ballNums : 0) + "个");
		nums_1 = mem.ballNums ? (mem.ballNums*1>10 ? 12 : mem.ballNums*1+1) : 1;
		Lottery.initData();
	}
	
	Lottery.init({moneyView: moneyView, isLogin: login, "num": 3, "nums": [nums_1,1,1], "container": $(".main"), callback: function(){
		
	}, beforeClick: function(success, fail){
		var login = Lottery.isLogin();
		if(login){
			var mem = Lottery.getMember();
			var mobile = mem.mobile;
			$.ajax({
				url: basePath + "api/1.0/getQuestion",
				async: false,
				type: 'post',
				dataType: 'json',
				success: function(res){
					if(res.status == 'SUCCESS'){
						Lottery.question({
							content: res.data.ask,
							answers: res.data.answers,
							yzm: basePath + 'ocode?mobile='+mobile,
							questionRight: function(answer, code){
								var msg = "";
								$.ajax({
									url: basePath + "api/1.0/questionRight",
									async: false,
									data: {params: JSON.stringify({'questionId': res.data.questionId, 'answer': answer, 'code': code, 'mobile': mobile})},
									type: 'post',
									dataType: 'json',
									success: function(res){
										if(res.status == 'SUCCESS'){
											if(res.data)msg = "";
											else msg="回答错误";
										}else{
											msg = res.errorMessage;
										}
									}
								});
								return msg;
							},
							enter: success,
	//						cancel: fail
						});
					}
				}
			});
		}
	}});
});