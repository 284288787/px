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
    	<title>我</title>
    	<meta name="keywords" content="">
    	<meta name="description" content="">
		<link rel="stylesheet" href="${basePath}static/css/mui.min.css">
        <link rel="stylesheet" href="${basePath}static/css/app.css">
        <script src="${basePath}static/js/flexible.js"></script>
		<script src="${basePath}static/js/jquery.min-1.11.3.js"></script>
		<script src="${basePath}static/js/h5/common.js"></script>
		<script src="${basePath}static/js/h5/lottery.js"></script>
 		<script src="${basePath}static/js/h5/user.js"></script>
		<script type="text/javascript">
			var basePath='${basePath}';
			var moneyView='${moneyView}';
		</script>
	</head>
	<body>
		<nav class="mui-bar mui-bar-tab lf-bar">
            <a class="mui-tab-item link" href="${basePath}lottery">
                <span class="icon hb "></span>
                <span class="mui-tab-label">红包广场</span>
            </a>
            <a class="mui-tab-item link" href="${basePath}o-openAward">
                <span class="icon kf"></span>
                <span class="mui-tab-label">开奖大厅</span>
            </a>
            <a class="mui-tab-item  mui-active" href="#">
                <span class="icon persion active"></span>
                <span class="mui-tab-label">个人中心</span>
            </a>
        </nav>
        <div class="mui-content person-content">
            <div class="head">
                <div class="set link" href="${basePath}o-settings">
                    <span class="mui-icon mui-icon-gear"></span>设置
                </div>
                <div class="head-img link" href="${basePath}o-userinfo">
                    <img src="${basePath}static/images/head.png"/>
                    <span>点击登录</span>
                </div>
                <div class="info">
                    <div class="mui-table">
                        <div class="mui-table-cell mui-col-xs-6">
                            <div class="t1">我的金币</div>
                            <div class="t2 goldNums">0</div>
                        </div>
                        <div class="mui-table-cell mui-col-xs-6">
                            <div class="t1">赠送号码(个)</div>
                            <div class="t2 ballNums">0</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="yqm">
                我的邀请码：<span>请先登录</span>
            </div>
            <ul class="mui-table-view person-list">
                <li class="mui-table-view-cell">
                    <a class="mui-navigate-right link" href="${basePath}o-invitation">
                        输入邀请码
                    </a>
                </li>
                <li class="mui-table-view-cell">
                    <a class="mui-navigate-right link" href="${basePath}o-goldlist">
                        金币明细
                    </a>
                </li>
                <li class="mui-table-view-cell">
                    <a class="mui-navigate-right link" href="${basePath}o-drawApply">
                        申请提现
                    </a>
                </li>
                <li class="mui-table-view-cell">
                    <a class="mui-navigate-right link" href="${basePath}o-drawlist">
                        提现记录
                    </a>
                </li>
                <li class="mui-table-view-cell">
                    <a class="mui-navigate-right link" href="${basePath}o-numberlist">
                        号码明细
                    </a>
                </li>
                <li class="mui-table-view-cell">
                    <a class="mui-navigate-right link" href="${basePath}o-invitationlist">
                        邀请记录
                    </a>
                </li>
                <li class="mui-table-view-cell">
                    <a class="mui-navigate-right link" href="${basePath}o-help">
                        规格&常见问题
                    </a>
                </li>
            </ul>
        </div>
        <script src="${basePath}static/js/mui.min.js"></script>
	</body>
</html>