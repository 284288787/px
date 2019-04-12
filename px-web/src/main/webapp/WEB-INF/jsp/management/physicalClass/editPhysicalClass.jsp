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
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/editEntity.css" />
</head> 
<body>
	<div class="edit-container"> 
		<form action="" id="editPhysicalClassForm"> 
		<input type="hidden" name="physicalClassId" value="${physicalClassDTO.physicalClassId}">
		<ul> 
			<li><strong>课程标题：</strong><span><input type="text" class="ipt300" name="title" placeholder="课程标题" value="${physicalClassDTO.title}"></span></li>
			<li><strong>上课时间：</strong><span><input type="text" id="schoolTime" name="schoolTime" placeholder="上课时间" value="<fmt:formatDate value="${physicalClassDTO.schoolTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate" onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"></span></li>
			<li><strong>报名截止时间：</strong><span><input type="text" name="deadlineTime" placeholder="不填则至上课时间截止报名" value="<fmt:formatDate value="${physicalClassDTO.deadlineTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'schoolTime\')}',isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"></span></li>
			<li><strong>价格：</strong><span><input type="text" name="price" placeholder="价格" value="${physicalClassDTO.price}"></span></li>
			<li><strong>上课地点：</strong><span><input type="text" class="ipt400" name="address" placeholder="上课地点" value="${physicalClassDTO.address}"></span></li>
			<li><strong>选择教练：</strong><span>
        <input type="text" class="ipt300" name="coachNames" placeholder="选择教练" readonly value="${physicalClassDTO.coachNames}">
        <input type="hidden" name="coachIds" value="${physicalClassDTO.coachIds}">
      </span></li>
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
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.source.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/ajaxfileupload.js"></script>
	<script charset="utf-8" src="${basePath}static/js/tools/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js"></script> 
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/additional-methods-local.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/utils.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/physicalClass/editPhysicalClass.js"></script> 
</body>
</html>