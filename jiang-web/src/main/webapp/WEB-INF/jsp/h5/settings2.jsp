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
    	<title>设置</title>
    	<meta name="keywords" content="">
    	<meta name="description" content="">
		<link rel="stylesheet" href="${basePath}static/css/user.css">
		<link rel="stylesheet" href="${basePath}static/css/dialog.css">
		<style type="text/css">
			.menu{
				margin: 1px 10px;
				height: 40px;
				line-height: 40px;
				border-bottom: 1px solid #9db8a7;
				font-size: 14px;
				padding-left: 0px;
			}
			.menu span{
				float: right;
				color: lightgray;
			}
			.menu span lable{
				margin-right: 10px;
				font-size: 12px;
				color: #979595;
			}
			.logout{
				left: 0;  
			    position: fixed;  
			    bottom: 0;
			    width: 100%;  
			    z-index: 100;
			    height: 40px;
			    line-height: 40px;
			    background-color: #ef7070;
			    text-align: center;
			    font-size: 16px;
			    font-weight: bold;
			    letter-spacing:3px;
			}
			.logout a{
				color: #F8f5f5;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<a href="${basePath}o-changepwd"><div class="menu">
			修改密码<span><lable></lable> &gt;</span>
			</div></a>
			<a href="${basePath}o-userinfo"><div class="menu">
			完善资料<span><lable></lable> &gt;</span>
			</div></a>
			<a href="${basePath}o-about"><div class="menu">
			关于我们<span><lable></lable> &gt;</span>
			</div></a>
			<div class="logout">
				<a href="#">退出登录</a>
			</div>
		</div>
		<script src="${basePath}static/js/jquery.min-1.11.3.js"></script>
		<script src="${basePath}static/js/h5/lottery.js"></script>
		<script type="text/javascript">
			var basePath='${basePath}';
			var moneyView='${moneyView}';
			$(function(){
				$(".logout a").on("tap, click", function(){
					var token = Lottery.getMemberToken();
					$.ajax({
						url: basePath+"api/1.0/logout",
						data: {params: JSON.stringify({'token': token})},
						type: 'post',
						dataType: 'json',
						success: function(res){
							document.location.href=basePath+"lottery";
						}
					});
				});
			});
		</script>
	</body>
</html>