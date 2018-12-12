var Lottery = (function (){
	var L = {};
	
	var options = {
		"num": 3
	};
	var lun = {"lun0": "一","lun1": "二","lun2": "三","lun3": "四","lun4": "五","lun5": "六","lun6": "七","lun7": "八","lun8": "九"};
	
	var choosedNums={};
	
	var jiangHtml=
		'<div class="active-item jiang" lun="{{lunIdx}}">               '+
		'	<div class="title">                                         '+
		'		第{{lun}}轮，预计奖金金额为{{gold}}金币                 '+
		'	</div>                                                      '+
		'	<div class="qiu">                                           '+
		'		<div class="mui-table">                                 '+
		'			<div class="mui-table-cell mui-col-xs-2">           '+
		'				<div class="circle num" data="1">1</div> '+
		'			</div>                                              '+
		'			<div class="mui-table-cell mui-col-xs-2">           '+
		'				<div class="circle num" data="2">2</div>        '+
		'			</div>                                              '+
		'			<div class="mui-table-cell mui-col-xs-2">           '+
		'				<div class="circle num" data="3">3</div>        '+
		'			</div>                                              '+
		'			<div class="mui-table-cell mui-col-xs-2">           '+
		'				<div class="circle num" data="4">4</div>        '+
		'			</div>                                              '+
		'			<div class="mui-table-cell mui-col-xs-2">           '+
		'				<div class="circle num" data="5">5</div>        '+
		'			</div>                                              '+
		'			<div class="mui-table-cell mui-col-xs-2">           '+
		'				<div class="circle num" data="6">6</div>        '+
		'			</div>                                              '+
		'		</div>                                                  '+
		'		<div class="mui-table">                                 '+
		'			<div class="mui-table-cell mui-col-xs-2">           '+
		'				<div class="circle num" data="7">7</div>        '+
		'			</div>                                              '+
		'			<div class="mui-table-cell mui-col-xs-2">           '+
		'				<div class="circle num" data="8">8</div>        '+
		'			</div>                                              '+
		'			<div class="mui-table-cell mui-col-xs-2">           '+
		'				<div class="circle num" data="9">9</div>        '+
		'			</div>                                              '+
		'			<div class="mui-table-cell mui-col-xs-2">           '+
		'				<div class="circle num" data="10">10</div>      '+
		'			</div>                                              '+
		'			<div class="mui-table-cell mui-col-xs-2">           '+
		'				<div class="circle num" data="11">11</div>      '+
		'			</div>                                              '+
		'			<div class="mui-table-cell mui-col-xs-2">           '+
		'				<div class="circle num" data="12">12</div>      '+
		'			</div>                                              '+
		'		</div>                                                  '+
		'	</div>                                                      '+
		'	<div class="mui-table btns">                                '+
		'		<div class="mui-table-cell mui-col-xs-6 clear">         '+
		'			清空                                                '+
		'		</div>                                                  '+
		'		<div class="mui-table-cell mui-col-xs-6 enter">         '+
		'			确认下注                                            '+
		'		</div>                                                  '+
		'	</div>                                                      '+
		'</div>';
	
	var dialogHtml=
	'<div class="lf-mask shade"></div>'+
	'<div class="dialog lf-tk-container">            '+
	'	<div class="content wt">'+
	'	<div class="title">{{title}}</div>'+
	'	<div class="tm bot content"> '+
	'		{{content}}'+
	'	</div>                       '+
	'	<div class="btns"><div class="mui-table"><div class="mui-table-cell mui-col-xs-6 close"><button>关闭</button></div><div class="mui-table-cell mui-col-xs-6 enter"><button class="zy">确定</button><div>'+
	'	</div></div>                       '+
	'</div>                          ';
	
	var loadingHtml=
	'<div class="lf-mask shade"></div>'+
	'<div class="dialog loading lf-tk-container">                       '+
	'	<img src="/static/images/loading.png">'+
	'</div>                                             ';
	
	var scrollDataItemHtml=
	'<div id="loading lf-tk-container">                                                                                                                          '+
	'	<a class="btn-more" href="javascript: Lottery.loadMore();">点击加载更多...</a>                                      '+
	'	<a class="btn-loading" style="display: none;" href="javascript:void(0);"><i class="mui-spinner"></i> 加载中...</a>'+
	'</div>                                                                                                                                      ';
	
	var userAgent=navigator.userAgent;
	
	L.config = function(config){
		$.extend(options, config);
	};
	
	L.isAndroid = function(){
		return userAgent.indexOf("lml_android")!=-1;
	};
	L.isIos = function(){
		return userAgent.indexOf("lml_ios")!=-1;
	};
	L.sendParams = function(params){
		if(L.isAndroid){
			android.send(params);
		}else if(L.isIos){
			connectWebViewJavascriptBridge(function(bridge) {
				bridge.send(params);
			});
		}
	};
	function connectWebViewJavascriptBridge(callback) {
	    if (window.WebViewJavascriptBridge) {
	        callback(WebViewJavascriptBridge)
	    } else {
	        document.addEventListener('WebViewJavascriptBridgeReady', function() {
	        	callback(WebViewJavascriptBridge)
	        }, false)
	    }
	};
	
	/**
	 * run: 是否马上执行一次 true是 false否
	 * url: 远程数据api地址
	 * params: 参数
	 * 
	 * **/
	var pageNo = 1;
	var loading=false;
	var scrollDataConfig;
	L.scrollData = function(config){
		scrollDataConfig = config;
		$("body").append(scrollDataItemHtml);
		var lastTop = 0;
		$(document).scroll(function(){
			var h = $(document).height()-$(window).height();
			var top = $(this).scrollTop();
			if(top > lastTop && top + 100 >= h){
				lastTop = top;
				window.setTimeout(function(){
					if(! loading){
						L.loadMore();
					}
				}, 300)
			}else{
				lastTop = top;
			}
		});
		if(config.run){
			L.loadMore();
		}
	};
	
	L.loadMore = function(){
		loading=true;
		$(".btn-more").hide();
		$(".btn-loading").show();
		$.ajax({
    	  	url: scrollDataConfig.url,
    	  	data: {params: JSON.stringify($.extend(scrollDataConfig.params, {pageNo: pageNo+''}))},
    	  	type: 'post',
    	  	dataType: 'json',
    	  	success: function(res){
    	  		if(res.status=='SUCCESS'){
    	  			var data=res.data;
    	  			if(data.length==0){
    	  				if(pageNo == 1){
    	  					$(".btn-more").hide();
    	  					$(".btn-loading").hide();
    	  					scrollDataConfig.container.append("<div style='text-align:center;margin-top:50px;'>没有记录</div>");
    	  				}else{
    	  					$(".btn-more").text("没有更多数据").show();
    	  					$(".btn-loading").hide();
    	  					window.setTimeout(function(){
    	  						$(".btn-more").hide();
    	  					}, 1000);
    	  				}
    	  				return false;
    	  			}
    	  			var html='';
    	  			for(var o in data){
    	  				html+=scrollDataConfig.line(data[o]);
    	  			}
    	  			scrollDataConfig.container.append(html);
	    	  		if(pageNo==1 && data.length < 20){
    	  				$(".btn-more").hide();
	  					$(".btn-loading").hide();
    	  			}else{
    	  				$(".btn-more").show();
    					$(".btn-loading").hide();
    	  			}
	    	  		pageNo++;
	    	  		loading=false;
    	  		}else{
    	  			L.alert(res.errorMessage);
    	  		}
    	  	},
    	  	error: function(){
    	  		loading=false;
    	  		$(".btn-more").show();
				$(".btn-loading").hide();
    	  	}
      	});
	};
	
	/**
	 * opts.num 开num轮 默认为3
	 *
	 */
	L.init = function(opts){
		if(opts)
			options.num = opts.num || options.num;
		var golds=opts.moneyView.split(",");
		for(var i = 0; i < options.num; i++){
			opts.container.append(jiangHtml.replace("{{lun}}", lun['lun'+i]).replace("{{lunIdx}}", i).replace("{{gold}}", golds[i]));
		}
		if(opts.ininData)
		choosedNums = opts.initData;
		if(choosedNums){
			for(var o in choosedNums){
				var lu = o.replace("lun", "");
				var jiang = $(".jiang[lun="+lu+"]");
				var cd = choosedNums[o];
				var nums = cd.nums.split(",");
				for(var n in nums){
					$(".num[data="+nums[n]+"]", jiang).addClass("active");
				}
			}
		}
		if(opts.callback) opts.callback();
		L.local.init(opts.isLogin);
		L.local.tapBall(opts.isLogin, opts.nums, opts.beforeClick);
	}
	
	L.setData = function(data){
		choosedNums = data;
		if(choosedNums){
			for(var o in choosedNums){
				var lu = o.replace("lun", "");
				var jiang = $(".jiang[lun="+lu+"]");
				var cd = choosedNums[o];
				var nums = cd.nums.split(",");
				for(var n in nums){
					$(".num[data="+nums[n]+"]", jiang).addClass("active");
				}
			}
		}
	}
	
	L.loading = function(){
		$(".shade").remove();
		$(".dialog").remove();
		$("body").append(loadingHtml);
//		var h1=$("body").height();
//		var h2=$(document).height();
//		$(".shade").css({"height": h2 > h1 ? h2 : h1});
//		var w=$(window).width();
//		var w2=$(".dialog").width();
//		var st = $(document).scrollTop();
//		$(".dialog").css({"top": st + 220, "left": (w-w2)/2});
	};
	L.loadingEnd = function(){
		$(".shade").remove();
		$(".dialog").remove();
	};
	
	L.alert = function(content, title){
		if(!title) title="提示";
		if(!content) content = "";
		var html = dialogHtml.replace("{{title}}", title).replace("{{content}}", content);
		$(".shade").remove();
		$(".dialog").remove();
		$("body").append(html);
//		var h1=$("body").height();
//		var h2=$(document).height();
//		$(".shade").css({"height": h2 > h1 ? h2 : h1});
		$(".dialog .close").remove();
//		var w=$(window).width();
//		var w2=$(".dialog").width();
//		var st = $(document).scrollTop();
//		$(".dialog").css({"top": st + 120, "left": (w-w2)/2});
		$(".dialog .enter").on("tap, click", function(){
			$(".shade").remove();
			$(".dialog").remove();
			return false;
		});
	};
	L.dialogPic = function(opts){
		if(!opts.title) opts.title="提示";
		if(!opts.content) opts.content = "";
		var html = dialogHtml.replace("{{title}}", opts.title)
		if(opts.contentUrl){
			var now=new Date();
			html=html.replace("{{content}}", "<img src='"+opts.contentUrl+"&v="+now.getTime()+"'><input type='text' class='yzm' maxlength='4' placeholder='验证码'><span style='position: absolute;left: 139px;top: 75px;color: red'></span>");
		}else{
			html=html.replace("{{content}}", opts.content);
		}
		$(".shade").remove();
		$(".dialog").remove();
		$("body").append(html);
//		var h1=$("body").height();
//		var h2=$(document).height();
//		$(".shade").css({"height": h2 > h1 ? h2 : h1});
//		if(opts.top){
//			var w=$(window).width();
//			var w2=$(".dialog").width();
//			var st = $(document).scrollTop();
//			$(".dialog").css({"top": st + opts.top, "left": (w-w2)/2});
//		}
		$(".dialog .close").on("tap, click", function(){
			$(".shade").remove();
			$(".dialog").remove();
			return false;
		});
		$(".dialog .enter").on("tap, click", function(){
			$(".dialog .yzm").next().html("");
			var code=$(".dialog .yzm").val();
			if(!code){
				L.alert("请填写验证码");
				return false;
			}
			opts.params["code"]=code
			$.ajax({
				url: opts.checkCodeUrl,
				data: opts.params,
				type: 'post',
				dataType: 'json',
				success: function(res){
					if(res.status=='SUCCESS'){
						$(".dialog .close").click();
						opts.success(code);
					}else{
						$(".dialog .yzm").next().html("验证码错误");
					}
				}
			});
			return false;
		});
	};
	/**
	 * title: 标题
	 * content: 内容
	 * dispose：处理方法
	 * **/
	L.dialogContent = function(opts){
		if(!opts.title) opts.title="提示";
		if(!opts.content) opts.content = "";
		var html = dialogHtml.replace("{{title}}", opts.title)
		html=html.replace("{{content}}", opts.content);
		$(".shade").remove();
		$(".dialog").remove();
		$("body").append(html);
//		var h1=$("body").height();
//		var h2=$(document).height();
//		$(".shade").css({"height": h2 > h1 ? h2 : h1});
//		if(opts.top){
//			var w=$(window).width();
//			var w2=$(".dialog").width();
//			var st = $(document).scrollTop();
//			$(".dialog").css({"top": st + opts.top, "left": (w-w2)/2});
//		}
		$(".dialog .close").on("tap, click", function(){
			$(".shade").remove();
			$(".dialog").remove();
			return false;
		});
		$(".dialog .enter").on("tap, click", function(){
			opts.dispose();
			return false;
		});
	};
	L.dialogClose = function(){
		$(".shade").remove();
		$(".dialog").remove();
	};
	L.confirm = function(opts){
		if(!opts.title) opts.title="提示";
		if(!opts.content) opts.content = "";
		var html = dialogHtml.replace("{{title}}", opts.title)
		html=html.replace("{{content}}", opts.content);
		$(".shade").remove();
		$(".dialog").remove();
		$("body").append(html);
//		var h1=$("body").height();
//		var h2=$(document).height();
//		$(".shade").css({"height": h2 > h1 ? h2 : h1});
//		if(opts.top){
//			var w=$(window).width();
//			var w2=$(".dialog").width();
//			var st = $(document).scrollTop();
//			$(".dialog").css({"top": st + opts.top, "left": (w-w2)/2});
//		}
		$(".dialog .close").on("tap, click", function(){
			if(opts.cancel)
				opts.cancel();
			$(".shade").remove();
			$(".dialog").remove();
			return false;
		});
		$(".dialog .enter").on("tap, click", function(){
			if(opts.enter)
				opts.enter();
			$(".shade").remove();
			$(".dialog").remove();
			return false;
		});
	};
	var questionHtml = 
	'<div class="lf-mask shade"></div>                                       '+
	'<div class="lf-tk-container dialog">                                     '+
	'	<div class="content wt">                                       '+
	'		<div class="title">                                        '+
	'			{{title}}'+
	'		</div>                                                     '+
	'		<div class="tm">                                           '+
	'			{{ask}}'+
	'		</div>                                                     '+
	'		<div class="xzda">                                         '+
	'			<div class="mui-table">                                '+
	'				{{content}}'+
	'			</div>                                                 '+
	'		</div>                                                     '+
	'		<div class="yzm">                                          '+
	'			<div class="title">输入验证码<span class="result" style="float:right;color:red"></span></div>                    '+
	'			<div class="input">                                    '+
	'				<div class="codeimg">                              '+
	'					<img src="{{yzm}}" />            '+
	'				</div>                                             '+
	'				<input type="text"/>                              '+
	'			</div>                                                 '+
	'		</div>                                                     '+
	'		<div class="btns">                                         '+
	'			<div class="mui-table">                                '+
	'				<div class="mui-table-cell mui-col-xs-6">          '+
	'					<button class="close">关闭</button>                          '+
	'				</div>                                             '+
	'				<div class="mui-table-cell mui-col-xs-6">          '+
	'					<button class="zy enter">确认</button>               '+
	'				</div>                                             '+
	'			</div>                                                 '+
	'		</div>                                                     '+
	'	</div>                                                         '+
	'</div>                                                            ';
	L.question = function(opts){
		var now = new Date();
		if(!opts.title) opts.title="请听题";
		if(!opts.content) opts.content = "";
		var html = questionHtml.replace("{{title}}", opts.title);
		html = html.replace("{{ask}}", opts.content);
		html = html.replace("{{yzm}}", opts.yzm+"&v="+now.getTime());
		var ct = '';
		var answers = opts.answers.split(",");
		for(var i in answers){
			ct += '				<div class="mui-table-cell mui-col-xs-3">          '+
			'					<div class="mui-input-row mui-radio mui-left"> '+
			'						<label for="a_'+i+'">'+answers[i]+'</label>'+
			'						<input type="radio" name="answer" id="a_'+i+'" value="'+answers[i]+'">'+
			'					</div>                                         '+
			'				</div>                                             ';
		}
		html=html.replace("{{content}}", ct);
		$(".shade").remove();
		$(".dialog").remove();
		$("body").append(html);
//		var h1=$("body").height();
//		var h2=$(document).height();
//		$(".shade").css({"height": h2 > h1 ? h2 : h1});
//		var w=$(window).width();
//		var w2=$(".dialog").width();
//		var st = $(document).scrollTop();
//		$(".dialog").css({"top": st + 100, "left": (w-w2)/2});
		$(".dialog .close").on("tap, click", function(){
			if(opts.cancel)
				opts.cancel();
			$(".shade").remove();
			$(".dialog").remove();
			return false;
		});
		$(".dialog :radio[name=answer]").change(function(){
			$(".result").hide();
		});
		$(".dialog .enter").on("tap, click", function(){
			var an=$(":radio[name=answer]:checked").val();
			if(!an) {
				$(".result").text("请选择答案").show();
				return false;
			}
			var code=$(".dialog .yzm input").val();
			if(!code) {
				$(".result").text("请输入验证码").show();
				return false;
			}
			var msg = opts.questionRight(an, code);
			if(!msg){
				opts.enter();
				$(".shade").remove();
				$(".dialog").remove();
			}else{
				now = new Date();
				$(".result").text(msg).show();
				if(msg!='回答错误'){
					$(".dialog .yzm img").attr("src", opts.yzm+"&v="+now.getTime());
				}
			}
			return false;
		});
	};
	
	L.cacheMember = function(map){
		var tokenKey = "sys_user_info_token_key";
		var token = map.token;
		L.local.setCookie(tokenKey, token);
		L.local.setCacheInfo(tokenKey, token);
		L.local.setCacheInfo(token, JSON.stringify(map));
	};
	
	L.allowBuy = function(){
		var bool = false;
		$.ajax({
			url: options.basePath + "api/1.0/allowBuy",
			async: false,
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status == 'SUCCESS'){
					bool = res.data;
				}
			}
		});
		return bool;
	};
	
	L.isLogin = function(){
		var token = L.getMemberToken();
		var bool = false;
		$.ajax({
			url: options.basePath + "api/1.0/getLoginInfo",
			async: false,
			data: {params: JSON.stringify({token: token})},
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status == 'SUCCESS'){
					L.cacheMember(res.data);
					bool = true;
				}
			}
		});
		return bool;
	};
	
	L.initData = function(){
		var token = L.getMemberToken();
		$.ajax({
			url: options.basePath + "api/1.0/getMemberData",
			data: {params: JSON.stringify({token: token})},
			type: 'post',
			dataType: 'json',
			success: function(res){
				if(res.status == 'SUCCESS'){
					L.setData(res.data);
				}
			}
		});
	};
	
	L.getMemberToken = function(){
		var tokenKey = "sys_user_info_token_key";
		var token = L.local.getCacheInfo(tokenKey);
		return token;
	};
	
	L.getMember = function(){
		var tokenKey = "sys_user_info_token_key";
		var token = L.local.getCacheInfo(tokenKey);
		var map = L.local.getCacheInfo(token);
		return JSON.parse(map);
	};
	
//	L.zmd = function(obj){
//		setInterval(function(){
//			var left = obj.offset().left;
//			var len = obj.width();
//			if(left * -1 >= len){
//				left = $(window).width();
//			}
//			obj.css("left", left - 2);
//		}, 100);
//	};
	
	L.local = {};
	
	L.local.setCookie = function(key, value){
		document.cookie=key+"="+value;
	};
	L.local.setCacheInfo = function(key, value){
		if(window.localStorage){
			window.localStorage.setItem(key, value);
		}
	};

	L.local.getCacheInfo = function(key){
		if(window.localStorage){
			return window.localStorage.getItem(key);
		}
		return null;
	}
	
	L.local.init = function(isLogin){
		$(".choose").html("");
		$("div.clear").on("tap, click", function(){
			var jiang = $(this).parents(".jiang");
			var lun = jiang.attr("lun") * 1;
			//jiang.find(".choose").attr("data-choose", "");
			choosedNums["lun" + lun]={num: 0, nums: ""};
			jiang.find(".active").removeClass("active");
			return false;
		});
		$("div.enter").on("tap, click", function(){
			if(! isLogin){
				L.confirm({
					content: '你还未登录，是否立即去登录？',
					enter: function(){
						document.location = options.basePath + "o-loginp";
					}
				})
				return false;
			}
			if(!L.allowBuy()){
				L.alert("正在开奖，开奖后可继续下注");
				return false;
			}
			var jiang = $(this).parents(".jiang");
			var lun = jiang.attr("lun") * 1;
			var tem;
			var choosed;
			try{
				tem = choosedNums["lun" + lun]
			}catch(e){}
			var bool = true;
			if(tem){
				choosed = tem.nums;
				if(choosed){
					bool = false;
				}
			}
			if(bool){
				L.alert("请先选择号码");
				return;
			}
			L.confirm({title: '提示', content: '已选号码为：' + choosed + "<br>是否确定下注？", enter: function(){
				var token = L.getMemberToken();
				$.ajax({
					url: options.basePath + "api/1.0/xiazhu",
					data: {params: JSON.stringify({token: token, lun: ''+lun, nums: choosed, answer: '1221'})},
					type: 'post',
					dataType: 'json',
					beforeSend: function(xhr){
						L.loading();
					},
					success: function(res){
						if(res.status == 'SUCCESS'){
							L.alert("下注成功，请关注开奖结果");
						}else{
							L.alert(res.errorMessage);
						}
					},
					error: function(a,b,c){
						L.alert("系统繁忙，请稍后重试");
					}
				});
			}});
			return false;
		});
	}
	L.local.tapBall = function(isLogin, nums, beforeClick){            //nums 允许的个数
		$(".num").on("tap, click", function(){
			if(! isLogin){
				L.confirm({
					content: '你还未登录，是否立即去登录？',
					enter: function(){
						document.location = options.basePath + "o-loginp";
					}
				})
				return false;
			}
//			if(!L.allowBuy()){
//				L.alert("开奖时间，开奖后可继续下注");
//				return false;
//			}
			var thisObj = $(this);
			var jiang = $(this).parents(".jiang");
			var num=$(this).attr("data");
			var lu = jiang.attr("lun") * 1;
			var thisNums = nums[lu];
			var cd = choosedNums["lun" + lu];
			if(cd){
				if(cd.num >= thisNums && !thisObj.hasClass("active")){
					var content = "第" + lun['lun'+lu] + "轮，你最多可选" + thisNums + "个号码";
					if(lu == 0){
						content += "，<a href='share.html' class='share'>邀请</a>朋友可选择更多号码";
					}
					L.alert(content);
					return;
				}
			}else{
				cd = {num: 0, nums: ""};
			}
			var choose=jiang.find(".choose");
			var text = cd.nums;
			if(thisObj.hasClass("active")){
				thisObj.removeClass("active")
				cd.num --;
				var temp = (","+text+",").replace(","+num+",", ",");
				if(temp.length>0 && temp.indexOf(",")==0){
					temp = temp.substring(1, temp.length - 1);
				}
				if(temp==",") temp="";
				cd.nums = temp;
				choosedNums["lun" + lu] = cd;
				return;
			}
			if(text && (","+text+",").indexOf(","+num+",") !=-1) return;
			if(beforeClick){
				beforeClick(function(){
					if(thisNums == 1 || ! text){
						//choose.attr("data-choose", num);
						cd.nums = num;
					}else{
						text = text + "," + num;
						//choose.attr("data-choose", text);
						cd.nums = text;
					}
					cd.num ++;
					//cd.nums=$.trim(choose.attr("data-choose", ""));
					choosedNums["lun" + lu] = cd;
					thisObj.addClass("active");
				}, function(){
					alert("fail")
				});
			}
			return false;
		});
	}
	
	return L;
})();