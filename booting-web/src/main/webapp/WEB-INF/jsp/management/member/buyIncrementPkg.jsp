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
</head>
<body>
	<div class="edit-container">
	<c:if test="${errorCode==1}">该用户不能购买增值套餐，请联系管理员</c:if>
	<c:if test="${errorCode==null}">
		<form action="" id="becomeEnterpriseForm">
		<input type="hidden" name="userId" id="userId" value="${userAccount.userId}">
		<ul>
			<li><strong>选择套餐：</strong><span>
			<select name="packageId">
				<option value="">请选择套餐</option>
				<c:forEach items="${packages}" var="pkg">
					<option value="${pkg.packageId}">${pkg.packageName}</option>
				</c:forEach>
			</select>
			</span></li>
			<li><strong>套餐数量：</strong><span>
			<select name="count">
				<option value="">请选择数量</option>
				<c:forEach begin="1" end="5" var="yyy">
					<option value="${yyy}">${yyy}个</option>
				</c:forEach>
			</select>
			</span></li>
		</ul>
		</form>
		<div class="btnGroup">
			<input type="button" value="保存" class="button blue" id="saveBtn">
			<input type="button" value="关闭" class="button grey" onclick="art.dialog.close();">
		</div>
	</c:if>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/member/buyIncrementPkg.js"></script>
</body>
</html>