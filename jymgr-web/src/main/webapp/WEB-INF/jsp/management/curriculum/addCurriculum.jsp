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
	ul li {
		padding-left: 10%;
	}
	strong{
		text-align: right;
		width: 160px !important;
	}
	.gy_table{ margin:0 auto 5px auto; background:#fff; border:1px solid #d8d8d8;padding: 10px 20px 0px 20px;border-radius:10px;}
	.gy_table2{ margin:0 0 5px 0; background:#fff; border:1px solid #d8d8d8;}
	.gy_table td{ padding:5px 2px; border-bottom:1px solid #d8d8d8;}
	.gy_table input[type=text]{
		height:20px;
		padding-left:3px;
	}
	.gy_table select{
		height:26px;
	}
	.gy_table .strong2{
		text-align: right;
	}
	.gy_table .btnGroup2{
	    margin: 12px 0px;
	    text-align: center;
	    bottom: 0;
	    width: 100%;
	}
	.gy_table .btnGroup2 input{
	    height: 30px;
	    width: 80px;
	}
	.item{
		width: 73px;
		text-align: center;
		float: left;
	}
	.item input[type=button]{
		height: 96px;
		width: 96%;
		font-size: 50px;
	}
	.item .left select{
		width: 70px;
	}
</style>
</head> 
<body>
	<div class="edit-container"> 
		<form action="" id="editCurriculumForm"> 
		<table width="80%" border="0" cellspacing="0" cellpadding="0" class="gy_table">
			<tr>
				<td width="160px"><strong>课程类型：</strong></td><td><select name="typeId"></select></td>
			</tr>
			<tr>
				<td><strong>课程名称：</strong></td><td><input type="text" name="name" placeholder="课程名称" class="ipt300"></td>
			</tr>
			<tr>
				<td><strong>课程价格：</strong></td><td><input type="text" name="price" placeholder="课程价格" ></td>
			</tr>
			<tr>
				<td width="160px"><strong>课程图片：</strong></td><td>
				<img src="${basePath}static/images/uploadBtn.png" id="uploadPicBtn"> <img style="display:none" width="70px" height="70px">
				</td>
			</tr>
			<tr>
				<td><strong>课程简介：</strong></td><td><textarea rows="1" cols="1" style="width:88%; height:120px;margin: 4px 0px" name="intro" placeholder="课程简介"></textarea></td>
			</tr>
			<tr>
				<td><strong>课程详介：</strong></td><td><textarea rows="1" cols="1" style="width:90%; height:300px;margin: 4px 0px" name="content" placeholder="课程详介"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="btnGroup2"> 
						<input type="button" value="保存" class="button blue" id="saveBtn">
						<input type="button" value="关闭" class="button grey" onclick="art.dialog.close();"> 
					</div> 
				</td>
			</tr>
		</table>
		</form>
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
	<script type="text/javascript" src="${basePath}static/js/management/curriculum/addCurriculum.js"></script> 
</body>
</html>