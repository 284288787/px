<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.99love.net/jsp/tag/star-tags" prefix="st"%>
<html>
<head>
<title>登录</title>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/login.css" />
</head>
<body>
	<form method="post" id="loginForm" action="${basePath}j_spring_security_check">
		<div><label for="username">手机:</label><input type="text" class="placeholder" maxlength="11" id="j_username" name="j_username" placeholder="手机号"></div>
		<div><label for="password">密码:</label><input type="password" class="placeholder" maxlength="32" id="j_password" name="j_password" placeholder="密码"></div>
		<div><input type="checkbox" name="remember-me" id="remember-me" /><label for="remember-me">记住我</label></div>
		<button type="button" id="enterBtn">登   录</button><br/><br/>
		<label id="errorMsg" style="color:red">${SPRING_SECURITY_LAST_EXCEPTION.message}</label>
	</form>
	<input type="hidden" id="basePath" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/js/jquery.min-1.11.3.js"></script>
	<script type="text/javascript">
	var basePath=$("#basePath").val();
	$(function(){
		$("#enterBtn").click(function(){
			var phone=$("#j_username").val();
			var password=$("#j_password").val();
			if(phone && password){
				document.getElementById("loginForm").submit();
			}else{
				$("#errorMsg").text("帐号和密码必填");
			}
		});
	});
	</script>
</body>
</html>