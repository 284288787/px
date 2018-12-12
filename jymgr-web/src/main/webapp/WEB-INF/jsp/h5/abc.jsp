<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>玩转spring boot——国际化</h1>
	<br />
	<br />
	<a href="/?lang=en_US">English(US)</a>
	<a href="/?lang=zh_CN">简体中文</a>
	<br />

	<h3><spring:message code="name"></spring:message> </h3>
	<br />
	<br />
	<a href="http://www.cnblogs.com/GoodHelper/">点击访问原版博客(www.cnblogs.com/GoodHelper)</a>
</body>
</html>