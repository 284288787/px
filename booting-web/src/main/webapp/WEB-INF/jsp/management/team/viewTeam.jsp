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
.photoDiv{
	height: 90px;
	line-height:90px;
	border-bottom: 1px dotted #a2a2a2;
}
.photoDiv .items{
	height: 30px;
	line-height:30px;
}
.photoDiv strong{
	width: 80px;
}
.item{
}
.item .items{
	height: 30px;
	line-height:30px;
}
.item strong{
	width: 80px;
}
</style>
</head>
<body>
	<div class="edit-container">
		<div class="photoDiv">
			<strong>头像：</strong><img src="${basePath}${team.logo}" id="uploadBtn" width="80px" height="80px">
		</div>
		<ul>
			<li><strong>球队名称：</strong><span>${team.teamName}</span></li>
			<li><strong>所属企业：</strong><span>${team.companyName}</span></li>
			<li><strong>所在地区：</strong><span>${team.areaName}</span></li>
			<li><strong>创建时间：</strong><span>${team.createTime}</span></li>
			<li><strong>习惯球场：</strong><span>
			<c:forEach items="${team.teamHabitCourts}" var="court">
				${court.courtName}
			</c:forEach>
			</span></li>
			<li><strong>踢球人数：</strong><span>
			<c:forEach items="${team.teamHabitNumbers}" var="num">
				${num.number}人 
			</c:forEach>
			</span></li>
		</ul>
		<div class="item">
			<div class="items">
			<c:forEach items="${team.teamHabitTimes}" var="tim" varStatus="idx">
				<strong><c:if test="${idx.count==1}">踢球时间：</c:if><c:if test="${idx.count>1}">&nbsp;</c:if></strong>
				每${fn: getWeekName(tim.week)}：<fmt:formatNumber value="${tim.beginMinute / 60}" pattern="0"></fmt:formatNumber>:<fmt:formatNumber value="${tim.beginMinute % 60}" pattern="00"></fmt:formatNumber> - <fmt:formatNumber value="${tim.endMinute / 60}" pattern="0"></fmt:formatNumber>:<fmt:formatNumber value="${tim.endMinute % 60}" pattern="00"></fmt:formatNumber> <br>
			</c:forEach>
			</div>
		</div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
</body>
</html>