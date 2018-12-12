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
		<form action="" id="editPackageForm">
		<input type="hidden" name="packageId" value="${packageDTO.packageId}">
		<ul>
			<li><strong>套餐名称：</strong><span><input type="text" name="packageName" placeholder="套餐名称" value="${packageDTO.packageName}"></span></li>
			<li><strong>套餐价格：</strong><span><input type="text" name="price" placeholder="套餐价格" value='<fmt:formatNumber value="${packageDTO.price / 100}" pattern="#.00"></fmt:formatNumber>'></span></li>
			<li><strong>套餐描述：</strong><span><input type="text" name="description" placeholder="套餐描述" value="${packageDTO.description}"></span></li>
			<c:if test="${packageDTO.discount == null}">
			<li><strong>套餐折扣：</strong><span><input type="text" name="discount" placeholder="没有折扣不填，如：8.8" value=''></span></li>
			</c:if>
			<c:if test="${packageDTO.discount != null}">
			<li><strong>套餐折扣：</strong><span><input type="text" name="discount" placeholder="没有折扣不填，如：8.8" value='<fmt:formatNumber value="${packageDTO.discount / 10}" pattern="#.0"></fmt:formatNumber>'></span></li>
			</c:if>
		</ul>
		</form>
		<div class="btnGroup">
			<input type="button" value="保存" class="button blue" id="saveBtn">
			<input type="button" value="关闭" class="button grey" onclick="art.dialog.close();">
		</div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/additional-methods-local.js?1"></script>
	<script type="text/javascript" src="${basePath}static/js/management/package/editPackage.js?3"></script>
</body>
</html>