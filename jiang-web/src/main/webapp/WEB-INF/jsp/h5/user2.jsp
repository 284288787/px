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
    	<title>我</title>
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
			.jg{
				height: 0px !important;
				font-size: 1px;
			}
			.ge{
				background-color: whitesmoke;
				height: 10px;
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
				width:115px;
			}
		</style>
		<script src="${basePath}static/js/jquery.min-1.11.3.js"></script>
		<script src="${basePath}static/js/h5/lottery.js"></script>
 		<script src="${basePath}static/js/h5/user.js"></script>
		<script type="text/javascript">
			var basePath='${basePath}';
			var moneyView='${moneyView}';
		</script>
	</head>
	<body>
		<div class="container">
			<div class="header2">
				<div class="wtop"><a href="${basePath}o-settings">设置</a></div>
				<div class="wcenter">
					<div class="wtleft">
						<a href="${basePath}o-userinfo">
							<img class="headPic" src="${basePath}static/images/defpic.jpg">
							<div class="nickname">未设置</div>
						</a>
					</div>
					<div class="info">
						<div><span>我的金币</span><div class="jg"></div><span class="price"></span></div>
						<div><span>号码余额</span><div class="jg"></div><span class="price"></span></div>
					</div>
				</div>
			</div>
			<div class="invitationCode">
			我的邀请码：<div></div>
			</div>
			<div class="ge"></div>
			<a href="${basePath}o-invitation"><div class="menu">
			输入邀请码<span><lable> </lable> &gt;</span>
			</div></a>
			<a href="${basePath}o-goldlist"><div class="menu">
			金币明细<span><lable> </lable> &gt;</span>
			</div></a>
			<a href="${basePath}o-drawlist"><div class="menu">
			提现记录<span><lable> </lable> &gt;</span>
			</div></a>
			<a href="${basePath}o-numberlist"><div class="menu">
			号码明细<span><lable> </lable> &gt;</span>
			</div></a>
			<a href="${basePath}o-invitationlist"><div class="menu">
			邀请记录<span><lable>想第一轮100%中奖吗？</lable> &gt;</span>
			</div></a>
			<a href="${basePath}o-help"><div class="menu">
			常见问题<span><lable> </lable> &gt;</span>
			</div></a>
		</div>
	</body>
</html>