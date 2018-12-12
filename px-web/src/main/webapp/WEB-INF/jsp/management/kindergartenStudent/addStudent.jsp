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
		<form action="" id="editStudentForm"> 
		<ul> 
			<li><strong>幼儿园：</strong><span><input type="text" name="schoolName" placeholder="选择幼儿园" readonly><input type="hidden" name="schoolId"></span></li>
			<li><strong>班级：</strong><span><input type="text" name="className" placeholder="选择班级" readonly><input type="hidden" name="classId"></span></li>
			<li><strong>班主任：</strong><span><input type="text" name="teacherName" placeholder="先选择班级" readonly disabled></span></li>
			<li><strong>姓名：</strong><span><input type="text" name="name" placeholder="姓名" ></span></li>
			<li><strong>性别：</strong><span><input type="radio" name="sex" value="1" placeholder="性别" >男<input type="radio" name="sex" value="0" placeholder="性别" >女</span></li>
			<li><strong>生日：</strong><span><input type="text" name="birth" placeholder="选择生日" class="Wdate" onclick="WdatePicker({isShowClear:false,readOnly:true})"></span></li>
			<li><strong>身高：</strong><span><input type="text" name="stature" placeholder="身高 cm" ></span></li>
			<li><strong>体重：</strong><span><input type="text" name="weight" placeholder="体重 kg" ></span></li>
			<li><strong>手环Mac：</strong><span><input type="text" name="braceletMac" placeholder="手环mac" ></span></li>
			<li><strong>监护人：</strong><span><input type="text" name="guardianName" placeholder="监护人" ></span></li>
			<li><strong>监护人电话：</strong><span><input type="text" name="guardianMobile" placeholder="监护人电话" ></span></li>
			<li><strong>与监护人关系：</strong><span><select name="guardianType">
			<option value="1">父亲</option>
			<option value="2">母亲</option>
			<option value="3">爷爷</option>
			<option value="4">奶奶</option>
			<option value="5">外公</option>
			<option value="6">外婆</option>
			<option value="7">其他</option>
			</select> </span></li>
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
	<script type="text/javascript" src="${basePath}static/js/management/kindergartenStudent/addStudent.js?1"></script> 
</body>
</html>