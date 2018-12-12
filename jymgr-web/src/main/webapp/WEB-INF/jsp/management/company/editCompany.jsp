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
		<form action="" id="editCompanyForm">
		<input type="hidden" name="companyId" value="${company.companyId}">
		<ul>
			<li><strong>企业名称：</strong><span><input type="text" name="companyName" placeholder="企业名称，必填" value="${company.companyName}"></span></li>
			<li><strong>发票抬头：</strong><span><input type="text" name="invoiceTitle" placeholder="发票抬头，必填" value="${company.invoiceTitle}"></span></li>
			<li><strong>所在城市：</strong><span><select name="areaId">
				<option value="">请选择</option>
				<optgroup label="直辖市"/>
				<option value="340">深圳</option>
			</select></span></li>
			<li><strong>所属行业：</strong><span><select name="businessId">
				<option value="">请选择</option>
			</select></span></li>
			<li><strong>寄件地址：</strong><span><input type="text" name="address" placeholder="寄件地址" value="${company.address}"></span></li>
		</ul>
		</form>
		<div class="btnGroup">
			<input type="button" value="保存" class="button blue" id="saveBtn">
			<input type="button" value="关闭" class="button grey" onclick="art.dialog.close();">
		</div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<input id="currAreaId" type="hidden" value="${company.areaId}">
	<input id="currBusinessId" type="hidden" value="${company.businessId}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/areadata.json?<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${basePath}static/js/businessdata.json?<%=System.currentTimeMillis()%>"></script>
	<script type="text/javascript" src="${basePath}static/js/management/company/editCompany.js"></script>
</body>
</html>