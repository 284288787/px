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
<style type="text/css">
strong{
	width: 160px !important;
}
</style>
</head> 
<body>
	<div class="edit-container"> 
		<form action="" id="editPhysicalDataForm"> 
		<ul> 
			<li><strong>所属幼儿园：</strong><span><input type="text" name="schoolName" placeholder="选择幼儿园" readonly><input type="hidden" name="schoolId"> </span></li>
			<li><strong>所属班级：</strong><span><input type="text" name="className" placeholder="选择班级" readonly><input type="hidden" name="classId"></span></li>
			<li><strong>幼儿姓名：</strong><span><input type="text" name="name" placeholder="选择幼儿" readonly><input type="hidden" name="studentId"></span></li>
			<li><strong>身高(cm)：</strong><span><input type="text" name="stature" placeholder="身高(cm)" ></span></li>
			<li><strong>体重(kg)：</strong><span><input type="text" name="weight" placeholder="体重(kg)" ></span></li>
			<li><strong>坐位体前屈(cm)：</strong><span><input type="text" name="sitReach" placeholder="坐位体前屈(cm)" ></span></li>
			<li><strong>立定跳远(cm)：</strong><span><input type="text" name="jump" placeholder="立定跳远(cm)" ></span></li>
			<li><strong>网球投掷(米)：</strong><span><input type="text" name="throwTennis" placeholder="网球投掷(米)" ></span></li>
			<li><strong>双脚连续跳(秒)：</strong><span><input type="text" name="doubleJump" placeholder="双脚连续跳(秒)" ></span></li>
			<li><strong>10米折返跑(秒)：</strong><span><input type="text" name="run10" placeholder="10米折返跑(秒)" ></span></li>
			<li><strong>走平衡木(秒)：</strong><span><input type="text" name="balance" placeholder="走平衡木(秒)" ></span></li>
			<li><strong>测试时间：</strong><span><input type="text" name="testTime" placeholder="测试时间" class="Wdate" onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm'})"></span></li>
			<li style="height:70px;"><strong>描述：</strong><span>
				<textarea rows="1" cols="1" name="intro" placeholder="描述" style="width:400px;height:60px"></textarea>
			</span></li>
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
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/additional-methods-local.js?2"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/utils.js?9"></script>
	<script type="text/javascript" src="${basePath}static/js/management/kindergartenPhysicalData/addPhysicalData.js?1"></script> 
</body>
</html>