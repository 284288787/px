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
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/editEntity.css" />
<style type="text/css">
	.edit-container {
		margin: 0;
	}
	ul{
		border: 1px solid gray;
	}
	ul li{
		border: 1px solid gray;
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
		<li class="nb"></li>
		<li class="n "><strong>套餐名称</strong></li>
		<li class="n width10"><strong>购买时间</strong></li>
		<li class="n"><strong>有效时间</strong></li>
		<li class="n"><strong>总价</strong></li>
		<li><strong>订单编号</strong></li>
	<c:forEach items="${userPackages}" var="pkg">
	<c:if test="${pkg.packageType==1}">
		<li class="tit">基础套餐：</li>
		<li class="nb"></li>
		<li class="n "><span>${pkg.packageName}</span></li>
		<li class="n width10"><span><fmt:formatDate value="${pkg.createTime}" pattern="yyyy-MM-dd"/></span></li>
		<li class="n"><span><fmt:formatDate value="${pkg.beginTime}" pattern="yyyy-MM-dd"/> / <fmt:formatDate value="${pkg.endTime}" pattern="yyyy-MM-dd"/></span></li>
		<li class="n"><span><fmt:formatNumber value="${not empty pkg.discount ? (pkg.price/100)*(pkg.discount / 100): pkg.price/ 100}" pattern="##.00"></fmt:formatNumber> </span></li>
		<li><span>${pkg.sourceFrom==1? pkg.orderId: '后台设置'}</span></li>
	</c:if>
	<c:if test="${pkg.packageType==2}">
		<li class="tit">增增套餐：</li>
		<li class="nb"></li>
		<li class="n "><span>${pkg.packageName}</span></li>
		<li class="n width10"><span><fmt:formatDate value="${pkg.createTime}" pattern="yyyy-MM-dd"/></span></li>
		<li class="n"><span>随基础套餐</span></li>
		<li class="n"><span><fmt:formatNumber value="${(not empty pkg.discount ? (pkg.price/100)*(pkg.discount / 10): pkg.price/ 100)}" pattern="##.00"></fmt:formatNumber> * 3 = <fmt:formatNumber value="${(not empty pkg.discount ? (pkg.price/100)*(pkg.discount / 10): pkg.price/ 100) * pkg.count}" pattern="##.00"></fmt:formatNumber></span></li>
		<li><span>${pkg.sourceFrom==1? pkg.orderId: '后台设置'}</span></li>
	</ul>
	</c:if>
	</c:forEach>
	<br/>
	<div class="tit">所有服务：</div>
	<ul class="table">
		<li class="n width70"><strong>服务名称</strong></li>
		<li class="n width10"><strong>总数</strong></li>
		<li class="n width10"><strong>已用</strong></li>
		<li ><strong>剩余</strong></li>
	<c:forEach items="${userServices}" var="ser">
		<li class="n width70"><strong>${ser.serviceName}</strong></li>
		<li class="n width10"><strong>${ser.serviceCount}</strong></li>
		<li class="n width10"><strong>0</strong></li>
		<li ><strong>${ser.serviceCount}</strong></li>
	</c:forEach>
	</ul>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
</body>
</html>