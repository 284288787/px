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
.photoDiv{
	height: 90px;
	line-height:90px;
	border-bottom: 1px dotted #a2a2a2;
}
.photoDiv strong{
	width: 80px;
}
</style>
</head>
<body>
	<div class="edit-container">
		<div class="photoDiv">
			<strong>头像：</strong><c:if test="${empty userAccount.photo}"><img src="/static/images/defaultHead.png" id="uploadBtn" width="80px" height="80px"></c:if><c:if test="${not empty userAccount.photo}"><img src="${basePath}${userAccount.photo}" id="uploadBtn" width="80px" height="80px"></c:if>
		</div>
		<form action="" id="editUserInfoForm">
		<input type="hidden" name="userId" value="${userAccount.userId}">
		<input type="hidden" name="photo">
		<ul>
			<li><strong>帐号：</strong><span><input type="text" placeholder="帐号即为手机号" value="${userAccount.account}" readonly="true"></span></li>
			<li><strong>姓名：</strong><span><input type="text" name="name" placeholder="姓名" value="${userAccount.name}"></span></li>
			<li><strong>性别：</strong><span><input type="radio" name="sex" value="1" id="sex_1" <c:if test="${userAccount.sex==1}">checked</c:if> ><label for="sex_1">男</label><input type="radio" name="sex" value="0" id="sex_0" <c:if test="${userAccount.sex==0}">checked</c:if> ><label for="sex_0">女</label> </span></li>
			<li><strong>年龄：</strong><span><input type="text" name="age" placeholder="年龄" value="${userAccount.age}"></span></li>
			<li><strong>身高：</strong><span><input type="text" name="stature" placeholder="身高 cm" value="${userAccount.stature}"></span></li>
			<li><strong>体重：</strong><span>
			<c:if test="${empty userAccount.weight}">
			<input type="text" name="weight" placeholder="体重 kg" value="">
			</c:if>
			<c:if test="${not empty userAccount.weight}">
			<input type="text" name="weight" placeholder="体重 kg" value='<fmt:formatNumber value="${userAccount.weight / 10.0}" pattern="##.0"></fmt:formatNumber>'>
			</c:if>
			</span></li>
			<li><strong>球衣编号：</strong><span><input type="text" name="poloShirtNo" placeholder="球衣编号" value="${userAccount.poloShirtNo}"></span></li>
			<li><strong>踢球位置：</strong><span>
			<select name="teamLocation">
				<option value="">请选择...</option>
				<option value="1" <c:if test="${userAccount.teamLocation==1}">selected</c:if> >门将</option>
				<option value="2" <c:if test="${userAccount.teamLocation==2}">selected</c:if> >后卫</option>
				<option value="3" <c:if test="${userAccount.teamLocation==3}">selected</c:if> >中场</option>
				<option value="4" <c:if test="${userAccount.teamLocation==4}">selected</c:if> >前锋</option>
			</select>
			</span></li>
			<li><strong>地址：</strong><span><input type="text" name="address" class="ipt300" placeholder="地址" value="${userAccount.address}"></span></li>
		</ul>
		</form>
		<div class="btnGroup">
			<input type="button" value="保存" class="button blue" id="saveBtn">
			<input type="button" value="关闭" class="button grey" onclick="art.dialog.close();">
		</div>
	</div>
	<div id="uploadDiv" style="display:none">
		<input type="file" name="uploadImg" id="uploadImg">
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/additional-methods-local.js?1"></script>
	<script type="text/javascript" src="${basePath}static/js/management/member/editUser.js?1"></script>
</body>
</html>