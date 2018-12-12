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
	ul li.n{
		border: 1px solid gray;
		text-align: center;
		width: 33% !important;
	}
	.tit{
		color: green;
		text-align:left;
	}
	ul.table li.n{
	    width: 16% !important;
	}
	strong{
		font-weight: bold;
		float: none;
	}
	.width10{
		width: 10% !important;
	}
	.width16{
		width: 16% !important;
	}
</style>
</head>
<body>
	<div class="edit-container">
	<div>
	<ul >
		<li class="n"><strong>订单号：</strong><span>${order.orderNo}</span></li>
		<li class="n"><strong>订单状态：</strong><span>${order.status==1?'待支付':(order.status==2?'已支付':(order.status==3?'已取消':''))}</span></li>
		<li><strong>订单类型：</strong><span>${order.type==1?'购买套餐':(order.type==2?'订场地':'')}</span></li>
		<li class="n"><strong>用户姓名：</strong><span>${order.userName}</span></li>
		<li class="n"><strong>电话号码：</strong><span>${order.mobile}</span></li>
		<li><strong>购买内容：</strong><span>${order.subject}</span></li>
		<c:if test="${order.status==2}">
		<li class="n"><strong>支付方式：</strong><span>${order.payType==1?'支付宝':(order.payType==2?'微信':'')}</span></li>
		<li class="n"><strong>支付时间：</strong><span>${order.payTime}</span></li>
		<li><strong>支付流水：</strong><span>${order.outTradeNo}</span></li>
		</c:if>
	</ul>
	</div>
	<br/>
	<div class="tit">订单明细：</div>
	<ul class="table">
		<li class="n width16"><strong>产品ID</strong></li>
		<li class="n width16"><strong>产品类型</strong></li>
		<li class="n width16"><strong>产品名称</strong></li>
		<li class="n width16"><strong>产品单价</strong></li>
		<li class="n width16"><strong>购买数量</strong></li>
		<li ><strong>合计</strong></li>
	<c:forEach items="${order.details}" var="detail">
		<li class="n width16"><strong>${detail.productId}</strong></li>
		<li class="n width16"><strong>${detail.productType==1?'套餐':'场地'}</strong></li>
		<li class="n width16"><strong>${detail.productName}</strong></li>
		<li class="n width16"><strong><fmt:formatNumber value="${detail.price/100}" pattern="#.00"></fmt:formatNumber> </strong></li>
		<li class="n width16"><strong>${detail.quantity}</strong></li>
		<li ><strong><fmt:formatNumber value="${detail.amount/100}" pattern="#.00"></fmt:formatNumber></strong></li>
	</c:forEach>
	</ul>
	</div>
</body>
</html>