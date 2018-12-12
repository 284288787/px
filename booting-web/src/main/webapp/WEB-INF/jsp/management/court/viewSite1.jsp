<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.99love.net/jsp/tag/star-tags" prefix="st"%>
<%@ taglib uri="http://www.99love.net/jsp/tag/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/jquery-ui-1.12.1/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/buttons.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/js/tools/artDialog4.1.2/skins/blue.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/editEntity.css?1" />
<style type="text/css">
.edit-container {
		margin: 0;
	}
	ul{
		border: 1px solid gray;
	}
	ul li{
		border-right: 1px solid gray;
		border-top: 1px solid gray;
		text-align: center;
	}
	ul li.nb{
		margin: 0;
		padding: 0;
		float: left;
	    display: inline;
	    width: 10%;
	}
	.tit{
		color: green;
		text-align:left;
	}
	ul.table li.n{
	    width: 20% ;
	}
	strong{
		font-weight: bold;
		float: none;
	}
	.width10{
		width: 10% !important;
	}
	.width70{
		width: 70% !important;
	}
</style>
</head>
<body>
	<div class="edit-container">
		<ul class="table">
		<li class="n "><strong>场地名称</strong></li>
		<li class="n"><strong>开始营业时间</strong></li>
		<li class="n"><strong>结束营业时间</strong></li>
		<li class="n"><strong>一场球的时间</strong></li>
		<li><strong>最小场地</strong></li>
	<c:forEach items="${sites}" var="site">
		<li class="n"><span>${site.siteName}</span></li>
		<li class="n "><span><fmt:formatNumber value="${site.startMinute / 60}" pattern="0"></fmt:formatNumber>:<fmt:formatNumber value="${site.startMinute % 60}" pattern="00"></fmt:formatNumber></span></li>
		<li class="n"><span><fmt:formatNumber value="${site.endMinute / 60}" pattern="0"></fmt:formatNumber>:<fmt:formatNumber value="${site.endMinute % 60}" pattern="00"></fmt:formatNumber></span></li>
		<li class="n"><span>${site.oneMinute} 分</span></li>
		<li><span>${site.minSize==1?'整场':(site.minSize==2?'半场':site.minSize==4?'1/4场':'1/8场')}</span></li>
	</c:forEach>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
</body>
</html>