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
    	<title>来米啦</title>
    	<meta name="keywords" content="">
    	<meta name="description" content="">
		<link rel="stylesheet" href="${basePath}static/css/mui.min.css">
        <link rel="stylesheet" href="${basePath}static/css/app.css">
        <script src="${basePath}static/js/flexible.js"></script>
		<script src="${basePath}static/js/jquery.min-1.11.3.js"></script>
		<script src="${basePath}static/js/h5/lottery.js"></script>
		<script src="${basePath}static/js/h5/main.js"></script>
		<script type="text/javascript">
			var basePath='${basePath}';
			var moneyView='${moneyView}';
		</script>
    	<link rel="shortcut icon" type="image/x-icon" href="${basePath}favicon.ico" />
	</head>
	<body>
		<nav class="mui-bar mui-bar-tab lf-bar">
            <a class="mui-tab-item mui-active" href="#tabbar">
                <span class="icon hb active"></span>
                <span class="mui-tab-label">红包广场</span>
            </a>
            <a class="mui-tab-item link" href="${basePath}o-openAward">
                <span class="icon kf"></span>
                <span class="mui-tab-label">开奖大厅</span>
            </a>
            <a class="mui-tab-item link" href="${basePath}o-user">
                <span class="icon persion"></span>
                <span class="mui-tab-label">个人中心</span>
            </a>
        </nav>
        <div class="mui-content index-content">
            <div id="slider" class="mui-slider">
                <div class="mui-slider-group mui-slider-loop">
                    <!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
                    <div class="mui-slider-item mui-slider-item-duplicate">
                        <a href="#">
                            <img src="images/yuantiao.jpg">
                        </a>
                    </div>
                    <!-- 第一张 -->
                    <div class="mui-slider-item">
                        <a href="#">
                            <img src="${basePath}static/images/banner.png">
                        </a>
                    </div>
                    <!-- 第二张 -->
                    <div class="mui-slider-item">
                        <a href="#">
                            <img src="${basePath}static/images/muwu.jpg">
                        </a>
                    </div>
                    <!-- 第三张 -->
                    <div class="mui-slider-item">
                        <a href="#">
                            <img src="${basePath}static/images/cbd.jpg">
                        </a>
                    </div>
                    <!-- 第四张 -->
                    <div class="mui-slider-item">
                        <a href="#">
                            <img src="${basePath}static/images/yuantiao.jpg">
                        </a>
                    </div>
                    <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一张轮播) -->
                    <div class="mui-slider-item mui-slider-item-duplicate">
                        <a href="#">
                            <img src="${basePath}static/images/banner.png">
                        </a>
                    </div>
                </div>
                <div class="mui-slider-indicator">
                    <div class="mui-indicator mui-active"></div>
                    <div class="mui-indicator"></div>
                    <div class="mui-indicator"></div>
                    <div class="mui-indicator"></div>
                </div>
            </div>
            <div class="lmtt">
                <div class="left">

                </div>
                <div class="right">
                    <div class="sxgd">
                        <ul id="gd" style="-webkit-transform: translate3d(0px, 0px, 0px) translateZ(0px);">
                            <li>1-选择人数最少的号码为开奖号码，开奖时间为9点，14点，19点，每期第一轮最多可选一个号码</li>
<!--                             <li>2-选择人数最少的号码为开奖号码，开奖时间为9点，14点，19点，每期第一轮最多可选一个号码</li> -->
<!--                             <li>3-选择人数最少的号码为开奖号码，开奖时间为9点，14点，19点，每期第一轮最多可选一个号码</li> -->
                        </ul>
                    </div>
                </div>
            </div>
            <div class="mid">
                <div class="mui-table">
                    <div class="mui-table-cell mui-col-xs-4">
                        <div class="circle hs">&nbsp;</div>
                        <div class="name">玩法介绍</div>
                    </div>
                    <div class="mui-table-cell mui-col-xs-4">
                        <div class="circle dh">0个</div>
                        <div class="name">赠送号码</div>
                    </div>
                    <div class="mui-table-cell mui-col-xs-4">
                        <div class="circle h">&nbsp;</div>
                        <div class="name">奖金排名</div>
                    </div>
                </div>
            </div>
            <div class="main">
            </div>
        </div>
        <script src="${basePath}static/js/mui.min.js"></script>
	    <script src="${basePath}static/js/lf-scroll.js"></script>
	    <script src="${basePath}static/js/h5/common.js"></script>
	    <script>
	        mui.ready(function() {
	            var slider = mui("#slider");
	            slider.slider({
	                interval: 3000
	            });
	            window.LfScroll.init()
	        })
	    </script>
	</body>
</html>