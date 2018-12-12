<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@ taglib uri="http://www.99love.net/jsp/tag/star-tags" prefix="st"%><!DOCTYPE html>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
    	<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    	<meta http-equiv="Cache-Control" content="no-transform">
    	<meta http-equiv="Cache-Control" content="no-siteapp">
    	<title>个人信息</title>
    	<meta name="keywords" content="">
    	<meta name="description" content="">
		<link rel="stylesheet" href="${basePath}static/css/dialog.css">
		<style type="text/css">
			.menu{
				margin: 1px 10px;
				min-height: 40px;
				line-height: 40px;
				border-bottom: 1px solid #9db8a7;
				font-size: 14px;
				padding-left: 0px;
				text-align: right;
				color: gray;
			}
			.menu span{
				float:left;
				color: #000;
			}
			.headPic{
				border-radius: 50%;
				width: 40px !important;
				height: 40px !important;
			    vertical-align: middle;
			}
			.img{
				height: 50px;
				line-height: 50px;
				padding: 5px 0px;
			}
		</style>
		<script src="${basePath}static/js/jquery.min-1.11.3.js"></script>
		<script src="${basePath}static/js/h5/lottery.js"></script>
		<script type="text/javascript">
			var basePath='${basePath}';
			var moneyView='${moneyView}';
			$(function(){
				Lottery.config({
					basePath: basePath
				});
				var login = Lottery.isLogin();
				if(login){
					var mem = Lottery.getMember();
					$(".nickname lable").text(mem.name ? mem.name : '未设置');
					$(".headPic").attr("src", mem.headPic ? basePath+mem.headPic : basePath+'static/images/defpic.jpg');
					$(".phone label").text(mem.mobile);
				}
				$("#uploadImg").on("change", function(){
					var formData = new FormData();
					var files=$(this)[0].files;
					for(var i in files){
						formData.append(files[i].name, files[i]);
					}
					$.ajax({
					    url: basePath+'common/uploadFile',
					    type: 'POST',
					    cache: false,
					    data: formData,
					    processData: false,
					    contentType: false,
					    beforeSend: function(xhr){
					    	Lottery.loading();
					    }
					}).done(function(res) {
						var pic = res.data[0];
						$.ajax({
							url: basePath+'api/1.0/updateMember',
							data: {params: JSON.stringify({'token': Lottery.getMemberToken(), 'headPic': pic})},
							type: 'post',
							dataType: 'json',
							success: function(res){
								$(".headPic").attr({src: basePath+pic});
								Lottery.loadingEnd();
							}
						});
						
					}).fail(function(res) {
						var msg = "服务器出错，错误内容：" + XMLHttpRequest.responseText;
			     		alert(msg)
					});
				});
				$(".headPic").on("tap, click", function(){
					$("#uploadImg").click();
				});
				$(".nickname").on("tap, click", function(){
					var nn = $.trim($(".nickname lable").text());
					Lottery.dialogContent({
						title: '修改昵称',
						content: '<div style="text-align:center"><input type="text" name="nickname" value="'+nn+'"><br><span style="color:red"></span></div>',
						dispose: function(){
							var nnn = $("input[name=nickname]").val();
							if(nnn){
								if(nnn == nn){
									Lottery.dialogClose();
								}else{
									$.ajax({
										url: basePath+'api/1.0/updateMember',
										data: {params: JSON.stringify({'token': Lottery.getMemberToken(), 'name': nnn})},
										type: 'post',
										dataType: 'json',
										success: function(res){
											$(".nickname lable").text(nnn);
											Lottery.dialogClose();
										}
									});
								}
							}else{
								$("input[name=nickname]").next().text("昵称不能为空");
								window.setTimeout(function(){
									$("input[name=nickname]").next().text("");
								}, 2000);
							}
						}
					});
				});
			});
		</script>
	</head>
	<body>
		<div class="container">
			<div class="menu img">
			<span>头像</span> <img class="headPic" src="${basePath}static/images/defpic.jpg"> &gt;
			</div>
			<div class="menu nickname">
			<span>昵称</span><lable>未设置</lable> &gt;
			</div>
			<div class="menu phone">
			<span>电话</span><lable>152****0085</lable>
			</div>
		</div>
		<div id="uploadDiv" style="display:none">
			<input type="file" name="uploadImg" id="uploadImg">
		</div> 
	</body>
</html>