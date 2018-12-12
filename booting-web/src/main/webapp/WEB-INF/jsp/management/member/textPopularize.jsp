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
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/editEntity.css?2" />
<style type="text/css">
.photoDiv{
	height: 80px;
	line-height:80px;
	border-bottom: 1px dotted #a2a2a2;
}
.photoDiv strong{
	width: 80px;
}
.contentDiv{
	margin-top:5px;
}
.line{
	height:30px;
}
</style>
</head>
<body>
	<div class="edit-container">
		<form action="" id="editPopularizeForm">
		<input type="hidden" name="id" value="${popularize.id}">
		<input type="hidden" name="userId" value="${popularize.userId}">
		<input type="hidden" name="type" value="1">
		<input type="hidden" name="logo">
			<div class="line"><strong>标题：</strong><span><input type="text" class="ipt400" name="title" placeholder="标题，必填" value="${popularize.title}"></span></div>
			<strong>图文：</strong><span><textarea col="1" row="1" name="content" placeholder="图文，必填"></textarea></span>
			</div>
		</form>
		<div class="btnGroup">
			<input type="button" value="保存" class="button blue" id="saveBtn">
			<input type="button" value="关闭" class="button grey" onclick="art.dialog.close();">
		</div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<textarea rows="1" cols="1" style="display:none" id="docContent">${popularize.content}</textarea>
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js"></script>
	<script charset="utf-8" src="${basePath}static/js/tools/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${basePath}static/js/tools/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${basePath}static/js/tools/kindeditor/plugins/code/prettify.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/member/textPopularize.js"></script>
	<script type="text/javascript">
	var kk;
	$(function(){
		var kindEditorOptions={
			cssPath : '${basePath}static/js/tools/kindeditor/plugins/code/prettify.css',
			uploadJson : '${basePath}static/js/tools/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '${basePath}static/js/tools/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			width: '100%',
			height: '450px',
			resizeType:1,
			autoHeightMode : false,
			items : [
				'justifyleft', 'justifycenter', 'justifyright',
				'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'lineheight', 'removeformat', '|', 'image',
				'hr', 'emoticons','link', 'unlink'
			],
			afterCreate : function() {
			},
			afterChange: function(){
			}
		};
		var content=$("#docContent").html();
		kk=KindEditor.create('textarea[name=content]', kindEditorOptions);
		kk.html(content);
	});
	</script>
</body>
</html>