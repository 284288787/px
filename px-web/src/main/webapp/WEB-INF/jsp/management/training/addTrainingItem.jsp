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
<link rel="stylesheet" href="${basePath}static/js/tools/kindeditor/themes/default/default.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/editEntity.css?1" />
</head>
<body>
	<div class="edit-container">
		<form action="" id="editTrainingItemForm">
		<input type="hidden" name="itemId" value="${trainingItemDTO.itemId}">
		<input type="hidden" name="areaId" value="${trainingItemDTO.areaId}">
		<ul>
			<li><strong>项目类型：</strong><span>
      <select name="subType">
        <option value="1" <c:if test="${trainingItemDTO.subType==1}">selected</c:if> >普通项目</option>
        <option value="2" <c:if test="${trainingItemDTO.subType==2}">selected</c:if> >体测课项目</option>
      </select>
      </span></li>
			<li style="display:none"><strong>体测课：</strong><span>
        <input type="text" class="ipt300" name="physicalClassName" placeholder="选择体测课" value="${trainingItemDTO.physicalClassName}">
        <input type="hidden" name="physicalClassId" value="${trainingItemDTO.physicalClassId}">
      </span></li>
			<li><strong>标题：</strong><span><input type="text" class="ipt400" name="title" placeholder="项目标题" value="${trainingItemDTO.title}"></span></li>
			<li><strong>地区：</strong><span><input type="text" name="areaName" placeholder="选择地区" value="${trainingItemDTO.areaName}" readonly></span></li>
			<li><strong>详细地址：</strong><span><input class="ipt400" type="text" name="address" placeholder="输入详细地址" value="${trainingItemDTO.address}"></span></li>
			<li><strong>类型：</strong><span>
			<select name="type">
				<option value="">请选择...</option>
				<option value="1" <c:if test="${trainingItemDTO.type==1}">selected</c:if> >幼儿园足球体能发开课程</option>
				<option value="2" <c:if test="${trainingItemDTO.type==2}">selected</c:if> >青少年足球培训</option>
				<option value="3" <c:if test="${trainingItemDTO.type==3}">selected</c:if> >教练员培训</option>
			</select>
			</span></li>
      <c:forEach items="${items}" var="price">
      <li><strong>${price.itemName}价格：</strong><span><input type="text" class="price" applyItemId="${price.applyItemId}" name="price" placeholder="输入${price.itemName}价格"></span></li>
      </c:forEach>
			<%-- <li><strong>价格：</strong><span><input type="text" name="price" placeholder="输入价格"></span></li> --%>
			<li><strong>优惠券id：</strong><span><input type="text" name="cardIds" placeholder="多个用英文逗号分隔"></span></li>
			<li ><strong>开始日期：</strong><span><input type="text" name="beginTime" id="beginTime" placeholder="选择日期" value="<fmt:formatDate value="${trainingItemDTO.beginTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onclick="WdatePicker({isShowClear:false,readOnly:true})"></span></li>
			<li><strong>结束日期：</strong><span><input type="text" name="endTime" id="endTime" placeholder="选择日期" value="<fmt:formatDate value="${trainingItemDTO.endTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginTime\')}',isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})"></span></li>
			<li style="height:90px;"><strong>简介：</strong><span>
				<textarea rows="1" cols="1" style="width:800px; height:80px;" name="intro" placeholder="课程简介">${trainingItemDTO.intro}</textarea>
			</span></li>
			<li style="height:75px;"><strong>活动图片：</strong><span>
				<img src="${basePath}static/images/uploadBtn.png" id="uploadBtn"> <img style="display:none" width="70px" height="70px">
				<c:forEach items="${trainingItemDTO.pictures}" var="pic">
				<span style="position: relative;"><img class="dataImg" width="70px" height="70px" src="${basePath}${pic.picturePath}" data="${pic.picturePath}"><div class="close">X</div></span>
				</c:forEach>
			</span></li>
			<li><strong>报名须知：</strong><span>
				<textarea rows="1" cols="1" name="notice" placeholder="报名须知">${trainingItemDTO.notice}</textarea>
			</span></li>
		</ul>
		</form>
		<div class="btnGroup">
			<input type="button" value="保存" class="button blue" id="saveBtn">
			<input type="button" value="关闭" class="button grey" onclick="art.dialog.close();">
		</div>
	</div>
	<div id="uploadDiv" style="display:none">
		<input type="file" name="uploadImg" id="uploadImg" multiple="multiple">
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.source.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/ajaxfileupload.js?10"></script>
	<script charset="utf-8" src="${basePath}static/js/tools/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/additional-methods-local.js?2"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/utils.js?10"></script>
	<script type="text/javascript" src="${basePath}static/js/management/training/addTrainingItem.js?2"></script>
</body>
</html>