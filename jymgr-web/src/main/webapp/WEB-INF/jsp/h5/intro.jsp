<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.99love.net/jsp/tag/star-tags" prefix="st"%>
<html>
<head>
	<title>精茵体育</title>
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style type="text/css">
		html, body{
			margin:0;
			padding:0;
		}
		img{
			width: 100%;
		}
		.main{
			position: relative
		}
		.btns{
			position: absolute;
			top: 420px;
			height: 40px;
			display: none;
		}
		.btns img{
			height: 40px;
			width: 42%;
		}
	</style>
	<script type="text/javascript" src="${basePath}static/js/jquery.min-1.11.3.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#firstImg").load(function(){
			var top = $("#firstImg").height() * 0.85;
			var left=  ($("#firstImg").width() - $("#firstImg").width() * 0.42 * 2)/2 + 5;
			$(".btns").css({"top":top,"left":left}).show();
			$(".button").click(function(){
				var link=$(this).attr("link");
				document.location.href=link;
			});
		});
	});
	</script>
</head>
<body>
	<div class="main">
		<img id="firstImg" src="${basePath}static/images/intro/pag000.png">
		<img src="${basePath}static/images/intro/pag01.png">
		<img src="${basePath}static/images/intro/pag02.png">
		<img src="${basePath}static/images/intro/pag03.png">
		<div class="btns">
			<img class="button" src="${basePath}static/images/intro/Android_download.png" link="http://app.hicloud.com/app/C100127243">
			&nbsp;&nbsp;
			<img class="button" src="${basePath}static/images/intro/iOS_download.png" link="https://itunes.apple.com/cn/app/%E7%B2%BE%E8%8C%B5%E4%BD%93%E8%82%B2/id1297192555?mt=8">
		</div>
	</div>
</body>
</html>