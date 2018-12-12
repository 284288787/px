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
		<link rel="stylesheet" href="${basePath}static/css/mui.min.css">
        <link rel="stylesheet" href="${basePath}static/css/app.css">
        <script src="${basePath}static/js/flexible.js"></script>
        <style type="text/css">
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
	<body class="bg-write">
		<header class="mui-bar mui-bar-nav lf-header">
            <a class="mui-icon mui-icon-left-nav mui-pull-left link" href="${basePath}o-user">返回</a>
            <h1 class="mui-title">设置</h1>
        </header>
		<div class="mui-content person-content">
			<ul class="mui-table-view person-list">
                <li class="mui-table-view-cell">
                    <a class="mui-navigate-right link" href="${basePath}o-changepwd">
                       修改密码
                    </a>
                </li>
                <li class="mui-table-view-cell">
                    <a class="mui-navigate-right link" href="${basePath}o-userinfo">
                        完善资料
                    </a>
                </li>
                <li class="mui-table-view-cell">
                    <a class="mui-navigate-right link" href="${basePath}o-about">
                       关于我们
                    </a>
                </li>
            </ul>
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