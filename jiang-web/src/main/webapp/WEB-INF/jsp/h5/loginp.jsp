<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%><%@ taglib uri="http://www.99love.net/jsp/tag/star-tags" prefix="st"%><!DOCTYPE html>
<!DOCTYPE html>
<html class="bg-write">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
    	<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
    	<meta http-equiv="Cache-Control" content="no-transform">
    	<meta http-equiv="Cache-Control" content="no-siteapp">
    	<title>来米啦 - 登录</title>
		<link rel="stylesheet" href="${basePath}static/css/mui.min.css">
        <link rel="stylesheet" href="${basePath}static/css/app.css">
        <script src="${basePath}static/js/flexible.js"></script>
		<script src="${basePath}static/js/jquery.min-1.11.3.js"></script>
		<script src="${basePath}static/js/h5/lottery.js"></script>
		<script type="text/javascript">
		var returnUrl = '${returnUrl}';
		$(function(){
			Lottery.config({
				basePath: '${basePath}'
			});
			if(Lottery.isLogin()) document.location.href="/lottery";
			
			$(".enter").on("tap, click", function(){
				var mobile=$("input[name='mobile']").val();
				var password=$("input[name='password']").val();
				if(mobile && password){
					$.ajax({
						url: '${basePath}api/1.0/login',
						data: {params: JSON.stringify({mobile: mobile, passwd: password})},
						type: 'post',
						dataType: 'json',
						async: false,
						success: function(res){
							if(res.status == 'SUCCESS'){
								Lottery.cacheMember(res.data);
								if(returnUrl) document.location.href=returnUrl;
								else document.location.href="/lottery";
							}else{
								Lottery.alert(res.errorMessage);
							}
						}
					});
				}else{
					Lottery.alert("手机号或密码不能为空");
				}
				return false;
			});
			
			$(".showpwd").on("tap, click", function(){
				var caption =$.trim($(this).text());
				if(caption=='显示密码'){
					$("input[name=password]").attr("type", "text");
					$(this).text("隐藏密码");
				}else{
					$("input[name=password]").attr("type", "password");
					$(this).text("显示密码");
				}
			});
			
			$(".changeLoginType").on("tap, click", function(){
				document.location.href="${basePath}o-login" + (returnUrl?("?returnUrl="+returnUrl):"");
			});
		});
		</script>
	</head>
	<body class="bg-write">
		<div class="login-content">
		    <div class="close">
		        <img src="${basePath}static/images/close-icon.png" />
		    </div>
		    <div class="lf-form">
		        <div class="lf-form-group">
		            <img class="left-icon" src="${basePath}static/images/icon-iphone.png"/>
		            <input id="mobile" name="mobile" class="lf-input" type="tel" placeholder="请输入手机号"/>
		            <img class="closex mui-hidden" src="${basePath}static/images/icon-close.png"/>
		        </div>
		        <div class="lf-form-group sg">
                    <img class="left-icon" src="${basePath}static/images/icon-pwd.png"/>
                    <input class="lf-input" name="password" type="password" placeholder="请输入6-20位密码"/>
                    <img class="closex mui-hidden" src="${basePath}static/images/icon-close.png"/>
                    <div class="right showpwd">显示密码</div>
                </div>
                <div class="xy">
                    <div class="mui-input-row mui-checkbox mui-left">
                    </div>
                </div>
                <div class="btns">
                    <button class="btn1 enter">登录</button>
                    <button class="btn2 changeLoginType">手机验证码登录</button>
                </div>
		    </div>
		</div>
		<script src="${basePath}static/js/mui.min.js"></script>
		<script>
		    (function($){// 处理文本框清楚按钮
		        $.ready(function(){
		            mui('.lf-form').on('tap', '.closex', function(e){
		                var iEl = this.parentNode.querySelector('.lf-input')
		                iEl.value = ''
		            })
		            mui.each(document.querySelectorAll('.lf-input'),function(e){
		                var _self = this;
		                _self.addEventListener('focus', function(){
		                    var el = this.parentNode.querySelector('.closex')
	                        if (this.value&&el.classList.contains('mui-hidden')) {
	                            
	                            el.classList.remove('mui-hidden')
	                        }
	                    })
		                _self.addEventListener('input', function(){
	                        var el = this.parentNode.querySelector('.closex')
	                        if (this.value&&el.classList.contains('mui-hidden')) {
	                            el.classList.remove('mui-hidden')
	                        }else{
	                            if (!this.value) {
	                                el.classList.add('mui-hidden')
	                            }
	                        }
	                    })
		                _self.addEventListener('blur', function(){
	                        var el = this.parentNode.querySelector('.closex')
	                        el.classList.add('mui-hidden')
	                    })
		            })
		        })
		    })(mui)
		</script>
	</body>
</html>