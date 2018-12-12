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
    	<title>来米啦 - 输入邀请码</title>
    	<meta name="keywords" content="">
    	<meta name="description" content="">
		<link rel="stylesheet" href="${basePath}static/css/header.css">
		<link rel="stylesheet" href="${basePath}static/css/dialog.css?12">
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
			line-height: 30px;
			margin: 5px;
			text-align:left;
		}
		.line input {
			height: 28px;
			font-size: 14px;
			padding-left: 3%;
			width: 97%;
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
			text-align:center;
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
			$(".enter").on("tap, click", function(){
				var invitationCode=$("input[name='invitationCode']").val();
				if(!invitationCode){
					Lottery.alert("请输入邀请码");
					return false;
				}
				$.ajax({
					url: '${basePath}api/1.0/addInviter',
					data: {params: JSON.stringify({token: Lottery.getMemberToken(), invitationCode: invitationCode})},
					type: 'post',
					dataType: 'json',
					async: false,
					success: function(res){
						if(res.status == 'SUCCESS'){
							Lottery.isLogin();
							$("input").val("");
							Lottery.alert("被邀请成功！");
						}else{
							Lottery.alert(res.errorMessage);
						}
					}
				});
				return false;
			});
		});
		</script>
	</head>
	<body>
		<div class="container">
			<div class="header">来米啦 - 输入邀请码</div>
			<div class="main">
				<div class="ipts">
					<div class="line">
						邀请码：<br><input type="text" name="invitationCode" maxlength="64" placeholder="输入邀请人的邀请码">
					</div>
					<div class="line">
						&nbsp;<br>邀请人和被邀请人双方都可获得8个号码数量
					</div>
				</div>
				<div class="line mt">
					<button href="#" class="enter">确&nbsp;认</button>
				</div>
			</div>
		</div>
	</body>
</html>