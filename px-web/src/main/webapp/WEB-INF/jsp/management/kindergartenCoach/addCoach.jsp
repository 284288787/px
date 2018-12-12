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
		<form action="" id="editCoachForm"> 
		<input type="hidden" name="pic">
		<ul> 
			<li style="height:75px;"><strong>头像：</strong><span>
				<img src="${basePath}static/images/uploadBtn.png" id="uploadBtn"> <img style="display:none" class="dataImg" width="70px" height="70px">
			</span></li>
			<li><strong>姓名：</strong><span><input type="text" name="name" placeholder="姓名" ></span></li>
			<li><strong>性别：</strong><span><input type="radio" name="sex" value="1" id="sex_1"><label for="sex_1">男</label><input type="radio" name="sex" value="0" id="sex_0"><label for="sex_0">女</label></span></li>
			<li><strong>年龄：</strong><span><input type="text" name="age" placeholder="年龄" ></span></li>
			<li><strong>电话：</strong><span><input type="text" name="mobile" placeholder="电话" ></span></li>
			<li><strong>证件类型：</strong><span>
			<select name="certificateType">
				<option value="1">身份证号</option>
			</select>
			</span></li>
			<li><strong>证件号码：</strong><span><input type="text" name="certificateCode" placeholder="证件号码" ></span></li>
			<li><strong>联系地址：</strong><span><input type="text" name="address" placeholder="联系地址" ></span></li>
			<li><strong>级别：</strong><span>
			<select name="level">
				<option value="初级">初级</option>
				<option value="中级">中级</option>
				<option value="高级">高级</option>
			</select>
			</span></li>
			<li style="height:200px;"><strong>培训记录：</strong><span> 
				<textarea rows="1" cols="1" name="trainingRecord" placeholder="培训记录"></textarea>
			</span></li> 
			<li style="height:200px;"><strong>工作经验：</strong><span> 
				<textarea rows="1" cols="1" name="workExperience" placeholder="工作经验"></textarea>
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
	<script type="text/javascript" src="${basePath}static/js/management/kindergartenCoach/addCoach.js"></script> 
</body>
</html>