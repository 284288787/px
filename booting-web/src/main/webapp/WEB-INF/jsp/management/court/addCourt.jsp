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
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/editEntity.css?2" />
</head>
<body>
	<div class="edit-container">
		<form action="" id="editCourtForm">
		<ul>
			<li><strong>球场名称：</strong><span><input type="text" name="courtName" placeholder="球场名称，必填"></span></li>
			<li><strong>联系人：</strong><span><input type="text" name="linkman" placeholder="联系人，必填"></span></li>
			<li><strong>联系电话：</strong><span><input type="text" name="account" style="width:400px" placeholder="联系电话，必填，为球场登录帐号"></span></li>
			<li><strong>手机号码：</strong><span><input type="text" name="mobile" placeholder="手机号码，必填"></span></li>
			<li><strong>开始营业时间：</strong><span><input type="text" name="startMinute" placeholder="开始营业时间，必填"></span></li>
			<li><strong>结束营业时间：</strong><span><input type="text" name="endMinute" placeholder="结束营业时间，必填"></span></li>
			<li><strong>一场时长(分)：</strong><span><input type="text" name="oneMinute" placeholder="一场时长(分)，必填"></span></li>
			<li><strong>所在城市：</strong><span><select name="areaId">
				<option value="">请选择</option>
				<optgroup label="直辖市"/>
				<option value="340">深圳</option>
			</select></span></li>
			<li><strong>详细地址：</strong><span><input type="text" name="address" class="ipt400" placeholder="详细地址，必填"></span></li>
			<li><strong>球场简介：</strong><span><input type="text" name="intro" class="ipt400" placeholder="球场简介"></span></li>
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
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/additional-methods-local.js"></script>
	<script type="text/javascript" src="${basePath}static/js/areadata.json?<%=System.currentTimeMillis()%>"></script>
<%-- 	<script type="text/javascript" src="${basePath}static/js/businessdata.json?<%=System.currentTimeMillis()%>"></script> --%>
	<script type="text/javascript" src="${basePath}static/js/management/court/addCourt.js"></script>
</body>
</html>