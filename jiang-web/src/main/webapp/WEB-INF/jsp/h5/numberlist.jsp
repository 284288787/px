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
    	<title>号码明细</title>
    	<meta name="keywords" content="">
    	<meta name="description" content="">
		<link rel="stylesheet" href="${basePath}static/css/user.css">
		<link rel="stylesheet" href="${basePath}static/css/dialog.css">
		<style type="text/css">
			.list-item{
				margin: 1px 10px;
				height: 40px;
				line-height: 40px;
				border-bottom: 1px solid #9db8a7;
				font-size: 14px;
				padding-left: 0px;
			}
			.list-item span{
				float: right;
				color: lightgray;
			}
			.list-item span lable{
				font-size: 12px;
				color: #979595;
			}
			#loading{
				height:30px;
				line-height: 30px;
				text-align: center;
			}
		</style>
		<script src="${basePath}static/js/jquery.min-1.11.3.js"></script>
		<script src="${basePath}static/js/h5/lottery.js"></script>
		<script type="text/javascript">
			var basePath='${basePath}';
			$(function(){
				Lottery.scrollData({
					container: $(".container"),
					url: basePath+'api/1.0/numberList',
					params: {token: Lottery.getMemberToken()},
					line: function(item){
						var temp = "";
						if(item.num<0){
							temp = "<span style='color:red'>"+item.num+"个号码</span>";
						}else{
							temp = "<span style='color:green'>+"+item.num+"个号码</span>";
						}
						return '<div class="list-item">'+item.createTime+(item.businessType==1?' 邀请人':' 下注')+'<span><lable>'+temp+'</lable></span></div>'
					},
					run: true
				});
			});
		</script>
	</head>
	<body>
		<div class="container"></div>
	</body>
</html>