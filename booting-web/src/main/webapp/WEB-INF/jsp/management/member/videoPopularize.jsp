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
			<input type="hidden" name="type" value="2">
			<input type="hidden" name="videoUrl">
			<div class="line"><strong>标题：</strong><span><input type="text" class="ipt400" name="title" placeholder="资讯标题，必填" value="${popularize.title}"></span></div>
			<div class="line"><strong>视频：</strong><input type="button" id="uploadBtn" value="选择视频"></div>
		</form>
		<c:if test="${popularize.videoUrl == null}">
		<video id="mp4File" style="width:100%;display:none">
		</c:if>
		<c:if test="${popularize.videoUrl != null}">
		<video id="mp4File" style="width:100%" autoplay="autoplay" controls="controls" src="${popularize.videoUrl}">
		</c:if>
		不支持播放的文件类型
		</video>
		<div class="btnGroup">
			<input type="button" value="保存" class="button blue" id="saveBtn">
			<input type="button" value="关闭" class="button grey" onclick="art.dialog.close();">
		</div>
	</div>
	<div id="uploadDiv" style="display:none">
		<input type="file" name="uploadImg" id="uploadImg" accept="video/*">
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js"></script>
	<script charset="utf-8" src="${basePath}static/js/tools/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${basePath}static/js/tools/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${basePath}static/js/tools/kindeditor/plugins/code/prettify.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/member/videoPopularize.js"></script>
	<script type="text/javascript">
	$(function(){
		var basePath=$("#basePath").val();
		var uploadFunc = function(){
			$.ajaxFileUpload({
				fileElementId: "uploadImg",
	    		url: basePath+'common/uploadFile',
	    		dataType: 'json',
	    		data: { star: 'lh'},
	    		beforeSend: function (XMLHttpRequest) {
	      			$("#saveBtn").attr("disabled", true);
	    		},
	    		success: function (data, textStatus) {
	    			var pic = data.data;
	    			//$("#uploadBtn").next().attr("src", basePath+pic).show();
	    			$(":hidden[name=videoUrl]").val(pic);
	    			$("#mp4File").attr({"src":basePath+pic, "autoplay":"autoplay","controls":"controls"}).show();
	    		},
	    		error: function (XMLHttpRequest, textStatus, errorThrown) {
		      		var msg = "服务器出错，错误内容：" + XMLHttpRequest.responseText;
		     		alert(msg)
		    	},
	    		complete: function (XMLHttpRequest, textStatus) {
	      			$("#saveBtn").attr("disabled", false);
	      			$("#uploadDiv").html('<input type="file" name="uploadImg" id="uploadImg">');
	      			$("#uploadDiv #uploadImg").on("change", uploadFunc);
	    		}
	  		});
		}
		$("#uploadImg").on("change", uploadFunc);
		$("#uploadBtn").click(function(){
			$("#uploadImg").click();
		});
	});
	</script>
</body>
</html>