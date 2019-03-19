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
    <div class="data-container">
      <table id="grid-table"></table>
      <div id="grid-pager"></div>
    </div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/js/management/common.js"></script>
  <script type="text/javascript">
  var basePath = $("#basePath").val();
  var params = artDialog.data("params");
  var applyDetailHandle = new ListHandle({
    basePath: $("#basePath").val(),
    tableId: '#grid-table',
    pagerId: '#grid-pager',
    formId: '#queryForm',
    entityName: '提成明细',
    winWidth: '90%',
    winHeight: '90%',
    primaryKey: 'itemId',
    urls:{
      list: basePath+'trainingApply/list?promoterId='+params.promoterId,        //列表
    }
  },{});
  $(function(){
    var colNames = [ '主键', '订单号', '交易单号', '金额', '提成率', '提成金额', '培训项目', '上课地点', '家长姓名', '家长电话', '创建时间'];
    var colModel = [
      {name: 'applyId', index: 'applyId', width: 40, align: "center"}, 
      {name: 'orderNo', index: 'orderNo', width: 220, align: "center"}, 
      {name: 'transactionId', index: 'transactionId', width: 200, align: "center"}, 
      {name: 'totalFee', width: 80,editable: false, sortable: false, align: "center", formatter: function(cellvalue, options, rowObject){
        if(!cellvalue) return '';
        return (cellvalue / 100.0).toFixed(2);
      }}, 
      {name: 'brokerageRate', index: 'brokerageRate', width: 80, align: "center"}, 
      {width: 80,editable: false, sortable: false, align: "center", formatter: function(cellvalue, options, rowObject){
        return (rowObject.totalFee / 100.0 * rowObject.brokerageRate).toFixed(4);
      }}, 
      {name: 'itemName',  width: 80, align: "center",editable: false, sortable: false}, 
      {name: 'addrName', index: 'addrName', width: 140, align: "center"}, 
      {name: 'name', index: 'name', width: 60, align: "center"}, 
      {name: 'mobile', index: 'mobile', width: 120, align: "center"}, 
      {name: 'createTime', index: 'createTime', width: 150, align: "center"}, 
    ];
    var rowList = [10, 20, 30, 50];
    var rownumbers = true;
    var multiselect = false;
    var config={rowNum: 50, autowidth: false, caption: "提成明细", dataType:'local', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
    applyDetailHandle.init(config);
    setTimeout(function(){
      applyDetailHandle.query();
    }, 500)
  });
  </script>
</body>
</html>