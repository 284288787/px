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
		<form action="" id="editActivityForm"> 
		<input type="hidden" name="activityId" value="${activityDTO.activityId}">
		<table width="80%" border="0" cellspacing="0" cellpadding="0" class="gy_table">
			<tr>
				<td width="160px"><strong>活动类型：</strong></td><td><select name="typeId" data="${activityDTO.typeId}"></select></td>
			</tr>
			<tr>
				<td><strong>活动名称：</strong></td><td><input type="text" name="name" placeholder="活动名称" class="ipt300" value="${activityDTO.name}"></td>
			</tr>
			<tr>
				<td><strong>活动价格：</strong></td><td><input type="text" name="price" placeholder="活动价格" value="<fmt:formatNumber value="${activityDTO.price/100}" pattern="#0.00"></fmt:formatNumber>"></td>
			</tr>
			<tr>
				<td><strong>有效性：</strong></td><td>
					<input type="radio" name="state" id="state_1" value="1" <c:if test="${activityDTO.state == 1}">checked</c:if>><label for="state_1">永久有效</label>
					<input type="radio" name="state" id="state_2" value="2" <c:if test="${activityDTO.state == 2}">checked</c:if>><label for="state_2">限时有效</label>
				</td>
			</tr>
			<tr>
				<td><strong>生效时间：</strong></td><td><input type="text" name="effectiveTime" id="effectiveTime" placeholder="生效时间" value="<fmt:formatDate value="${activityDTO.effectiveTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'failureTime\')}',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'})"></td>
			</tr>
			<tr>
				<td><strong>失效时间：</strong></td><td><input type="text" name="failureTime" id="failureTime" placeholder="失效时间" value="<fmt:formatDate value="${activityDTO.failureTime}" pattern="yyyy-MM-dd"/>" class="Wdate" onclick="WdatePicker({minDate:'#F{$dp.$D(\'effectiveTime\')}',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'})"></td>
			</tr>
			<tr>
				<td width="160px"><strong>活动图片：</strong></td><td>
				<img src="${basePath}static/images/uploadBtn.png" id="uploadPicBtn"> <img style="display:none" width="70px" height="70px">
				<c:forEach items="${activityDTO.pictures}" var="hp">
				<span style="position: relative;"><img class="dataImg" width="70px" height="70px" src="${basePath}${hp.picPath}" data="${hp.picPath}"><div class="close">X</div></span>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<td><strong>活动简介：</strong></td><td><textarea rows="1" cols="1" style="width:88%; height:120px;margin: 4px 0px" name="intro" placeholder="活动简介">${activityDTO.intro}</textarea></td>
			</tr>
			<tr>
				<td><strong>活动详介：</strong></td><td><textarea rows="1" cols="1" style="width:90%; height:300px;margin: 4px 0px" name="content" placeholder="活动详介">${activityDTO.content}</textarea></td>
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
	<script type="text/javascript" src="${basePath}static/js/management/activity/editActivity.js"></script> 
</body>
</html>