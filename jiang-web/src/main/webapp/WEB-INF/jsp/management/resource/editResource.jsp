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
</head>
<body>
	<div class="edit-container">
		<form action="" id="editResourceForm">
		<input type="hidden" name="sourceId" value="${source.sourceId}">
		<ul>
			<li><strong>资源名称：</strong><span><input type="text" name="sourceName" placeholder="资源名称，必填" value="${source.sourceName}"></span></li>
			<li><strong>资源URL：</strong><span><input type="text" name="sourceUrl" placeholder="资源URL，选填" value="${source.sourceUrl}"></span></li>
			<li><strong>资源类型：</strong><span><select name="type"><option value="">请选择</option>
			<option value="1" <c:if test="${source.type==1}">selected</c:if> >系统</option>
			<option value="2" <c:if test="${source.type==2}">selected</c:if> >一级菜单</option>
			<option value="3" <c:if test="${source.type==3}">selected</c:if> >二级菜单</option>
			<option value="4" <c:if test="${source.type==4}">selected</c:if> >三级菜单</option>
			<option value="5" <c:if test="${source.type==5}">selected</c:if> >按钮</option>
			<option value="6" <c:if test="${source.type==6}">selected</c:if> >其他资源</option></select></span></li>
			<li><strong>父级资源：</strong><span><select name="parentId" value="${source.parentId}"><option value="">请选择</option></select></span></li>
			<li><strong>图标样式：</strong><span><input type="text" name="sourceIcoCls" placeholder="图标样式，选填" value="${source.sourceIcoCls}"></span></li>
			<li><strong>资源说明：</strong><span><input type="text" name="sourceRemark" class="ipt300" placeholder="资源备注，选填" value="${source.sourceRemark}"></span></li>
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
	<script type="text/javascript" src="${basePath}static/js/management/resource/editResource.js"></script>
</body>
</html>