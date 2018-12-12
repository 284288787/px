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
    	<title>获取更多</title>
    	<meta name="keywords" content="">
    	<meta name="description" content="">
    	<meta name="shareTitle" content="分享标题">
    	<meta name="shareContent" content="分享内容">
    	<meta name="shareContentWeibo" content="分享内容 微博专用">
    	<meta name="shareUrl" content="">
    	<link rel="stylesheet" href="${basePath}static/css/dialog.css">
    	<style type="text/css">
    		.top{
    			margin: 20px 20px 50px 20px;
    		}
    		.center{
    			text-align: center;
    		}
    		.invitationCode{
				height: 40px;
				line-height: 40px;
				font-size: 14px;
				text-align:center;
			}
			.invitationCode div{
				display: inline-flex;
				font-size:18px;
				color: red;
				font-weight:bold;
			}
			.enter{
				padding: 6px ;
				border: 1px solid green ;
				font-size: 16px ;
				border-radius: 5px;
				font-weight: bold;
				background-color: cadetblue;
				width: 40%
			}
    	</style>
    	<script src="${basePath}static/js/jquery.min-1.11.3.js"></script>
    	<script src="${basePath}static/js/h5/lottery.js"></script>
    	<script type="text/javascript">
    	var basePath='${basePath}';
    	$(function(){
    		Lottery.config({
    			basePath: basePath
    		});
    		var login = Lottery.isLogin();
    		if(login){
    			var mem = Lottery.getMember();
    			$(".invitationCode div").text("P"+mem.memberId);
    			$("meta[name=shareUrl]").attr("content", basePath+"o-reg?from=P"+mem.memberId);
    		}
    	});
    	</script>
	</head>
	<body>
		<div class="container">
			<div class="top">
				<div>12选1还觉得中奖机会不够大？想提高中奖几率？</div>
				<div>现在只需要邀请好友下载注册来米啦，输入你的邀请码，你和他就可以各获得在第一轮5个额外下注的机会，就是说可以在12个号码里面选6个号码，而且额外下注机会没有使用限制，每集满11个额外下注机会，就可以在第一轮包场，百分之百中奖哦。
		另外，你邀请的好友以后每下注6次，你就可以获得一个第一轮额外下注的机会，长期有效，快去邀请好友下载注册吧，躺着就可以赚钱。
				</div>
			</div>
			<div class="center">
				<div class="invitationCode">
				我的邀请码：<div></div>
				</div>
				<div>
					<button id="shareBtn" class="enter">邀请好友</button>
				</div>
			</div>
		</div>
		
    	
	</body>
</html>