<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@ taglib uri="http://www.99love.net/jsp/tag/star-tags" prefix="st"%><!DOCTYPE html>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
    	<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    	<meta http-equiv="Cache-Control" content="no-transform">
    	<meta http-equiv="Cache-Control" content="no-siteapp">
    	<title>来米啦 - 免注册登录</title>
    	<meta name="keywords" content="">
    	<meta name="description" content="">
		<link rel="stylesheet" href="${basePath}static/css/header.css">
		<link rel="stylesheet" href="${basePath}static/css/dialog.css">
		<style type="text/css">
		.main{
			margin: 0px auto;
			text-align:center;
		}
		.main .ipts{
			background-color: #dce8ed;
			margin: 5px 5px;
			padding: 10px;
			border-radius: 8px;
		}
		.line{
			font-weight:bold;
			height: 40px;
			line-height: 40px;
			margin: 5px;
		}
		.line input {
			height: 28px;
			font-size: 14px;
			padding-left: 5px;
			width: 160px;
			border: 1px solid #000;
		}
		.line input:hover {
			border: 1px solid #FF0000;
		}
		.line .code {
			width: 160px;
		}
		.line .enter{
			padding: 6px ;
			border: 1px solid green ;
			font-size: 16px ;
			border-radius: 5px;
			font-weight: bold;
			background-color: cadetblue;
			width: 90%
		}
		.mt{
			margin-top: 15px;
		}
		.codeBtn{
		    position: absolute;
		    z-index: 10;
		    right: 0px;
		}
		.oinfo{
			text-align: right;
			padding-right:5px;
		}
		</style>
		<script src="${basePath}static/js/jquery.min-1.11.3.js"></script>
		<script src="${basePath}static/js/h5/lottery.js"></script>
		<script type="text/javascript">
		var returnUrl = '${returnUrl}';
		$(function(){
			Lottery.config({
				basePath: '${basePath}'
			});
			if(Lottery.isLogin()) document.location.href="/lottery";
			$(".codeBtn").css("right", $("input.code").offset().left - 50);
			$(window).resize(function(){
				$(".codeBtn").css("right", $("input.code").offset().left - 50);
			});
			$(".codeBtn").on("tap, click", function(){
				var sended=$(this).attr("sended");
				if(sended && sended=='1') return false;
				var mobile=$("input[name='mobile']").val();
				if(!mobile){
					Lottery.alert("请填写手机号码");
					return;
				}
				Lottery.dialogPic({
					title: '图形验证码',
					contentUrl: '${basePath}ocode?mobile='+mobile,
					top: 40,
					left: '60px',
					checkCodeUrl: '${basePath}ocodecheck',
					params: {"mobile": mobile},
					success: function(code){
						$.ajax({
							url: '${basePath}api/1.0/sendSms',
							data: {params: JSON.stringify({mobile: mobile, tag: '1', code: code})},
							type: 'post',
							dataType: 'json',
							success: function(res){
								if(res.status=='SUCCESS'){
									$(".codeBtn").attr("sended", 1);
									$(".codeBtn").text("59秒");
									var len = 59;
									var inter = setInterval(function(){
										$(".codeBtn").text((--len) + "秒");
										if(len == 0){
											$(".codeBtn").text("获取验证码");
											$(".codeBtn").attr("sended", 0);
											clearInterval(inter);
										}
									}, 1000);
								}else{
									Lottery.alert(res.errorMessage);
								}
							}
						})
					}
				});
				return false;
			});
			
			$(".enter").on("tap, click", function(){
				var mobile=$("input[name='mobile']").val();
				var code=$("input[name='code']").val();
				if(mobile && code){
					$.ajax({
						url: '${basePath}api/1.0/login',
						data: {params: JSON.stringify({mobile: mobile, code: code})},
						type: 'post',
						dataType: 'json',
						async: false,
						success: function(res){
							if(res.status == 'SUCCESS'){
								Lottery.cacheMember(res.data);
								if(returnUrl) document.location.href=returnUrl;
								else document.location.href="/lottery";
							}else{
								Lottery.alert(res.errorMessage);
							}
						}
					});
				}else{
					Lottery.alert("手机号或验证码不能为空");
				}
				return false;
			});
		});
		</script>
	</head>
	<body>
		<div class="container">
			<div class="header">来米啦 - 免注册登录</div>
			<div class="main">
				<div class="ipts">
					<div class="line">
						手机号：<input type="tel" name="mobile" maxlength="11" placeholder="手机号">
					</div>
					<div class="line">
						验证码：<input class="code" type="text" name="code" maxlength="4" placeholder="验证码"><a class="codeBtn" href="#">获取验证码</a>
					</div>
				</div>
				<div class="line mt">
					<button href="#" class="enter">登 &nbsp;  &nbsp;  &nbsp; 录</button>
				</div>
				<div class="oinfo">
					您还可以，<a href="${basePath}o-loginp<c:if test="${not empty returnUrl}">?returnUrl=${returnUrl}</c:if>">使用密码登录</a>
				</div>
			</div>
		</div>
	</body>
</html>