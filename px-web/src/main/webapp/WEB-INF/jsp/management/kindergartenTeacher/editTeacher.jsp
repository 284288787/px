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
		<form action="" id="editTeacherForm"> 
		<input type="hidden" name="teacherId" value="${teacherDTO.teacherId}">
		<input type="hidden" name="pic" value="${teacherDTO.pic}">
		<ul> 
			<li><strong>幼儿园：</strong><span><input type="text" name="schoolName" placeholder="幼儿园" value="${teacherDTO.schoolName}" readonly><input type="hidden" name="schoolId" value="${teacherDTO.schoolId}"></span></li>
			<li style="height:75px;"><strong>头像：</strong><span>
				<img src="${basePath}static/images/uploadBtn.png" id="uploadBtn"> <img class="dataImg" width="70px" height="70px" src="${basePath}${teacherDTO.pic}" data="${teacherDTO.pic}">
			</span></li>
			<li><strong>姓名：</strong><span><input type="text" name="teacherName" placeholder="姓名" value="${teacherDTO.teacherName}"></span></li>
			<li><strong>电话：</strong><span><input type="text" name="teacherMobile" placeholder="电话" value="${teacherDTO.teacherMobile}"></span></li>
			<li style="height:90px;"><strong>简介：</strong><span> 
				<textarea rows="1" cols="1" style="width:800px; height:80px;" name="intro" placeholder="简介">${teacherDTO.intro}</textarea>
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
	<script type="text/javascript" src="${basePath}static/js/management/kindergartenTeacher/editTeacher.js?2"></script> 
</body>
</html>