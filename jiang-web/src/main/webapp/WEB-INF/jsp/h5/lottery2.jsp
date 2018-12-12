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
    	<title>来米啦</title>
    	<meta name="keywords" content="">
    	<meta name="description" content="">
		<link rel="stylesheet" href="${basePath}static/css/header.css">
		<link rel="stylesheet" href="${basePath}static/css/jiang.css">
		<link rel="stylesheet" href="${basePath}static/css/dialog.css">
		<script src="${basePath}static/js/jquery.min-1.11.3.js"></script>
		<script src="${basePath}static/js/h5/lottery.js"></script>
		<script src="${basePath}static/js/h5/main.js"></script>
		<script type="text/javascript">
			var basePath='${basePath}';
			var moneyView='${moneyView}';
		</script>
	</head>
	<body>
		<div class="container">
			<!--<div class="header">来米啦</div> -->
			<div class="header2">
				<div class="wtop">
					<div class="wcenter">
						<a href="${basePath}o-login">免注册登录</a>
					</div>
				</div>
				<div class="wbottom">
					<a href="${basePath}o-oi">玩法介绍</a>
					<a href="${basePath}o-history">开奖走势</a>
					<a href="${basePath}o-openAward">点击开奖</a>
				</div>
			</div>
			<div class="zmd">
				<div>
				选择人数最少的号码为开奖号码，开奖时间为${openTime}，每期第一轮最多可选12个号码。
				</div>
			</div>
		</div>
	</body>
</html>