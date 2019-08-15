<%@ page language="java" pageEncoding="UTF-8"%><%@ taglib uri ="http://www.springframework.org/security/tags" prefix ="sec"%>
<!DOCTYPE html>
<html>
<head>
	<title>列表</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/jquery-ui-1.12.1/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/jqGrid/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/buttons.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/js/tools/artDialog4.1.2/skins/blue.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/pageList.css?1" />
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${basePath}static/jqGrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="${basePath}static/jqGrid/js/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
</head>
<body>
	<div class="main-container" id="main-container">
		<div class="search-container">
			<form action="${basePath}" id="queryForm">
				<span>家长电话： <input type="text" name="mobile" placeholder="家长电话"></span>
				<span>推广员： <input type="text" name="promoter" placeholder="推广员姓名"></span>
				<span>报名时间： 
          <input type="text" class="Wdate" id="beginCreateTime" name="beginCreateTime" placeholder="起始日期" 
                onclick="WdatePicker({startDate:'%y-%M-%d',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"> 
          <input type="text" class="Wdate" id="endCreateTime" name="endCreateTime" placeholder="终止日期" 
                onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginCreateTime\')}',isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd'})"> 
        </span>
        <span>推广员电话： <input type="text" name="promoterMobile" placeholder="推广员电话"></span>
				<span>状态： 
          <select name="status"><option value="2" >已支付</option><option value="1" >待处理</option></select>
        </span>
				<span>交易单号： <input type="text" name="transactionId" placeholder="微信交易单号"></span>
				<a onclick="trainingapplyHandle.query()" class="button blue">查询</a>
				<a onclick="trainingapplyHandle.reset()" class="button grey">清空</a>
			</form>
		</div>
		<div class="data-container">
			<div class="btnGroup">
				<a onclick="trainingapplyHandle.exportInfo()" class="button yellow">导出已支付的报名信息</a>
			</div>
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
  <script type="text/javascript" src="${basePath}static/js/tools/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/common.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/trainingapply/list.js"></script>
  <script type="text/javascript">
    //对Date的扩展，将 Date 转化为指定格式的String
    //月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
    //年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
    //例子：
    //(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
    //(new Date()).Format("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
    Date.prototype.format = function (fmt) {
    var o = {
      "M+": this.getMonth() + 1, // 月份
      "d+": this.getDate(), // 日
      "h+": this.getHours(), // 小时
      "m+": this.getMinutes(), // 分
      "s+": this.getSeconds(), // 秒
      "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
      "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
    }
  </script>
</body>
</html>