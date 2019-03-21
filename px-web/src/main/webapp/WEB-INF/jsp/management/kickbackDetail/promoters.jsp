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
				<span>姓名：<input type="text" name="name" placeholder="输入姓名" > </span> 
				<span>电话：<input type="text" name="mobile" placeholder="输入电话" > </span> 
				<a onclick="promoterHandle.query()" class="button blue">查询</a> 
				<a onclick="promoterHandle.reset()" class="button grey">清空</a> 
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
	<script type="text/javascript" src="${basePath}static/js/management/common.js"></script> 
	<script type="text/javascript" src="${basePath}static/js/management/utils.js"></script>
	<script type="text/javascript">
	var opts=artDialog.data("opts");
	var basePath = $('#basePath').val();
	var promoterHandle = new ListHandle({ 
	  basePath: $('#basePath').val(),
	  tableId: '#grid-table',
	  pagerId: '#grid-pager',
	  formId: '#queryForm',
	  entityName: '推广员信息',
	  winWidth: '320px', 
	  winHeight: '200px',
	  primaryKey: 'promoterId',
	  urls:{ 
	    list: basePath+'promoter/list?enabled=1',//列表
	  }
	},{ 
	}); 
	$(function(){ 
	  var colNames = ['id', '姓名', '电话', '添加时间'];
	  var colModel = [ 
	    {name: 'promoterId', index: 'promoterId', width: 30, align: 'center'}, 
	    {name: 'name', index: 'name', width: 70, align: 'center'}, 
	    {name: 'mobile', index: 'mobile', width: 110, align: 'center'}, 
	    {name: 'createTime', index: 'createTime', width: 140, align: 'center'}, 
	  ]; 
	  var rowList = [10, 20, 30, 50];
	  var rownumbers = true; 
	  var multiselect = false;
	  var config={caption: '推广员列表', dataType: "local", colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	  setTimeout(function(){
	   promoterHandle.init(config); 
	   promoterHandle.query();
    }, 320);
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