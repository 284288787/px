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
  <style type="text/css">
  .alignCenter{
    margin-top:10px;
    text-align: center;
  }
  </style> 
</head>
<body> 
	<div class="main-container" id="main-container"> 
		<div class="search-container"> 
			<form action="${basePath}" id="queryForm"> 
				<span>课程标题：<input type="text" name="title" placeholder="输入课程标题" > </span> 
				<span>上课时间大于：<input type="text" name="beginSchoolTime" placeholder="选择时间" class="Wdate" onclick="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"> </span> 
				<a onclick="physicalClassHandle.query()" class="button blue">查询</a> 
				<a onclick="physicalClassHandle.reset()" class="button grey">清空</a> 
			</form>
		</div> 
		<div class="data-container"> 
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div> 
    <div class="btnGroup alignCenter">
      <input type="button" value="确定" class="button blue" id="enterBtn">
      <input type="button" value="关闭" class="button grey" onclick="art.dialog.close();">
    </div> 
	</div> 
	<input id="basePath" type="hidden" value="${basePath}">
  <script type="text/javascript" src="${basePath}static/js/tools/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/common.js"></script> 
	<script type="text/javascript" src="${basePath}static/js/management/utils.js"></script>
	<script type="text/javascript">
	var opts=artDialog.data("opts");
	var basePath = $('#basePath').val();
	var physicalClassHandle = new ListHandle({ 
	  basePath: $('#basePath').val(),
	  tableId: '#grid-table',
	  pagerId: '#grid-pager',
	  formId: '#queryForm',
	  entityName: '体测课信息',
	  winWidth: '600px', 
	  winHeight: '300px',
	  primaryKey: 'physicalClassId',
	  urls:{ 
	    list: basePath+'physicalClass/list?enabled=1',//列表
	  }
	},{ 
	   
	}); 
	$(function(){ 
	  var colNames = ['id', '课程标题', '上课时间', '报名截止时间', '价格', '上课地点', '是否可用', '创建时间'];
	  var colModel = [ 
	    {name: 'physicalClassId', index: 'physicalClassId', width: 40, align: 'center'}, 
	    {name: 'title', index: 'title', width: 120, align: 'center'}, 
	    {name: 'schoolTime', index: 'schoolTime', width: 120, align: 'center'}, 
	    {name: 'deadlineTime', index: 'deadlineTime', width: 120, align: 'center'}, 
	    {name: 'price', index: 'price', width: 80, align: 'center', formatter: function(cellvalue, options, rowObject){
        if(!cellvalue) return '';
        return (cellvalue / 100.0).toFixed(2);
      }},
	    {name: 'address', index: 'address', width: 200, align: 'center'}, 
	    {name: 'enabled', index: 'enabled', width: 60, align: 'center', formatter: 'select', editoptions:{value:'1:可用;0:禁用'}}, 
	    {name: 'createTime', index: 'createTime', width: 120, align: 'center'}, 
	  ]; 
	  var rowList = [10, 20, 30, 50];
	  var rownumbers = true; 
	  var multiselect = false;
	  var config={caption: '体测课列表', rowNum: 50, autowidth: true, dataType:'local', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	  setTimeout(function(){
  	  physicalClassHandle.init(config); 
	    physicalClassHandle.query();
    }, 300);
	  $("#enterBtn").click(function() {
      //var rowId=$('#grid-table').jqGrid('getGridParam','selrow'); //selarrrow
      if(opts.multiselect){
        var ids = dialogHandle.getSelectedRows();
        dialogHandle.resetSelectedIds();
        var da = new Array();
        for(var rid in ids){
          var rowData = $('#grid-table').jqGrid('getRowData', rid);
          da.push(rowData);
        }
        if (opts) {
          opts.callback(da);
          art.dialog.close();
        }
      }else{
        var rowId=$('#grid-table').jqGrid('getGridParam','selrow'); //selarrrow
        var rowData = $('#grid-table').jqGrid('getRowData', rowId);
        if (opts) {
          opts.callback(rowData);
          art.dialog.close();
        }
      }
    });
	}); 
	</script>
</body>
</html>