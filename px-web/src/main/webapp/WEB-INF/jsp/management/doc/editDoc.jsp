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
		<form action="" id="editDocForm">
		<input type="hidden" name="docId" value="${documentDTO.docId}">
		<input type="hidden" name="logo">
			<div class="line"><strong>资讯类型：</strong><span><select name="type"><option value="1" <c:if test="${documentDTO.type==1}">selected</c:if> >轮播图</option><option value="2" <c:if test="${documentDTO.type==2}">selected</c:if> >文章</option></select></span></div>
			<div class="line"style="display:none"><strong>资讯位置：</strong><span><select name="position"><option value="1" <c:if test="${documentDTO.position==1}">selected</c:if> >正常</option><option value="2" <c:if test="${documentDTO.position==2}">selected</c:if> >置顶</option></select></span></div>
			<div class="line"><strong>资讯标题：</strong><span><input type="text" class="ipt400" name="title" placeholder="资讯标题，必填" value="${documentDTO.title}"></span></div>
			<div class="line"><strong>资讯简介：</strong><span><input type="text" class="ipt400" name="intro" placeholder="资讯简介" value="${documentDTO.intro}"></span></div>
			<div class="photoDiv">
				<strong><c:if test="${documentDTO.type==1}">轮播图片：</c:if><c:if test="${documentDTO.type==2}">资讯图片：</c:if></strong><img src="${basePath}static/images/uploadBtn.png" id="uploadBtn"><c:if test="${empty documentDTO.logo}"><img style="display:none" width="70px" height="70px"></c:if><c:if test="${not empty documentDTO.logo}"><img src="${basePath}${documentDTO.logo}" width="70px" height="70px"></c:if>
			</div>
			<div class="contentDiv"><c:if test="${documentDTO.type==1}"><strong>资讯URL：</strong><span><input type="text" class="ipt700" name="content" placeholder="资讯URL，选填" value="${documentDTO.content}"></span></c:if>
			<c:if test="${documentDTO.type==2}"><strong>资讯内容：</strong><span><textarea col="1" row="1" name="content" placeholder="资讯URL，选填"></textarea></span></c:if>
			</div>
		</form>
		<div class="btnGroup">
			<input type="button" value="保存" class="button blue" id="saveBtn">
			<input type="button" value="关闭" class="button grey" onclick="art.dialog.close();">
		</div>
	</div>
	<div id="uploadDiv" style="display:none">
		<input type="file" name="uploadImg" id="uploadImg" accept="image/*">
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<textarea rows="1" cols="1" style="display:none" id="docContent">${documentDTO.content}</textarea>
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js"></script>
	<script charset="utf-8" src="${basePath}static/js/tools/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${basePath}static/js/tools/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${basePath}static/js/tools/kindeditor/plugins/code/prettify.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/doc/editDoc.js?3"></script>
	<script type="text/javascript">
	var kk;
	$(function(){
		var kindEditorOptions={
			cssPath : '${basePath}static/js/tools/kindeditor/plugins/code/prettify.css',
			uploadJson : '${basePath}static/js/tools/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '${basePath}static/js/tools/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			width: '100%',
			height: '350px',
			resizeType:1,
			autoHeightMode : false,
			items : [
				'source', '|', 'justifyleft', 'justifycenter', 'justifyright',
				'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'lineheight', 'removeformat', '|', 'image',
				'hr', 'emoticons','link', 'unlink'
			],
			afterCreate : function() {
			},
			afterChange: function(){
			}
		};
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
	    			$("#uploadBtn").next().attr("src", basePath+pic).show();
	    			$(":hidden[name=logo]").val(pic);
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
		var type=$("select[name=type]").val();
		var content=$("#docContent").html();
		$("select[name=type]").change(function(){
			var val=$(this).val();
			if(val==2){
				var cont="";
				if(type==2){
					cont=content;
				}
				$(".contentDiv").html('<strong>资讯内容：</strong><span><textarea col="1" row="1" name="content" placeholder="资讯URL，必填"></textarea></span>');
				$("select[name=position]").parent().parent().show();
				$(".photoDiv strong").text("资讯封面：");
				kk=KindEditor.create('textarea[name=content]', kindEditorOptions);
				kk.html(cont);
			}else{
				var cont="";
				if(type==1){
					cont=content;
				}
				$(".contentDiv").html('<strong>资讯URL：</strong><span><input type="text" class="ipt700" name="content" placeholder="资讯URL，必填" value="'+cont+'"></span>');
				$("select[name=position]").parent().parent().hide();
				$(".photoDiv strong").text("轮播图片：");
			}
		});
		$("select[name=type]").change();
	});
	</script>
</body>
</html>