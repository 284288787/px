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
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/editEntityTable.css" />
<style type="text/css">
.ipt100{width:100px}
.ipt150{width:150px}
.ipt200{width:200px}
</style>
</head>
<body>
	<div class="edit-container">
		<form action="" id="editPointLevelForm">
		<table class="gy_table">
				<tr>
					<td class="alignright" width="150px"><strong>级别：</strong></td>
					<td><input type="text" name="level" class="ipt100" placeholder="级别，例如：3" maxlength="3"></td>
				</tr>
				<tr>
					<td class="alignright"><strong>称号：</strong></td>
					<td><input type="text" name="name" placeholder="称号" maxlength="10"></td>
				</tr>
				<tr>
					<td class="alignright"><strong>图标：</strong></td>
					<td>
					<img src="${basePath}static/images/uploadBtn.png" id="uploadBtn" height="72px" width="72px">
					<input type="hidden" name="icon">
					</td>
				</tr>
				<tr>
					<td class="alignright"><strong>升级所需经验(点)：</strong></td>
					<td><input type="text" name="pointStep" class="ipt100" placeholder="例如：1000" maxlength="5"></td>
				</tr>
				<tr>
					<td class="alignright"><strong>每次增长经验(点)：</strong></td>
					<td><input type="text" name="point" class="ipt100" placeholder="例如：10" maxlength="5"></td>
				</tr>
				<tr>
					<td class="alignright"><strong>每次增长经验所需卡路里(小卡)：</strong></td>
					<td><input type="text" name="calorieStep" class="ipt150" placeholder="例如：1000" maxlength="8"></td>
				</tr>
				<tr>
					<td class="alignright"><strong>每次增长经验所需距离(m)：</strong></td>
					<td><input type="text" name="distanceStep" class="ipt150" placeholder="例如：1000" maxlength="8"></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="btnGroup">
							<input type="button" value="保存" class="button blue" id="saveBtn"> <input type="button" value="关闭" class="button grey" onclick="art.dialog.close();">
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<div id="uploadDiv" style="display:none">
		<input type="file" name="uploadImg" id="uploadImg">
	</div> 
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/additional-methods-local.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/utils2.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/bracelet/addPointLevel.js"></script>
</body>
</html>