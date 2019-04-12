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
				<span>教练姓名：<input type="text" name="name" placeholder="教练姓名" > </span> 
        <span>教练电话：<input type="text" name="mobile" placeholder="教练电话" > </span> 
        <span>是否可用：<select name="enabled"><option value="">全部</option><option value="1" >可用</option><option value="0" >禁用</option></select> </span> 
        <a onclick="coachHandle.query()" class="button blue">查询</a> 
        <a onclick="coachHandle.reset()" class="button grey">清空</a> 
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
	var coachHandle = new ListHandle({ 
	  basePath: $('#basePath').val(),
	  tableId: '#grid-table',
	  pagerId: '#grid-pager',
	  formId: '#queryForm',
	  entityName: '教练',
	  winWidth: '600px', 
	  winHeight: '300px',
	  primaryKey: 'coachId',
	  urls:{ 
	    list: basePath+'coach/list?enabled=1',//列表
	  }
	},{ 
	   
	}); 
	$(function(){ 
	  var colNames = ['coachId', '头像', '教练姓名', '教练电话', '性别', '年龄', '证件类型', '证件号码', '级别', '联系地址'];
	  var colModel = [ 
	    {name: 'coachId', index: 'coachId', width: 30, align: 'center'}, 
	    {name: 'pic', index: 'pic', width: 30, align: 'center', formatter: function(value, options, rowObject){
	      return "<img class='dataImg' src='"+basePath+value+"' height='60px'>";
	    }}, 
	    {name: 'name', index: 'name', width: 70, align: 'center'}, 
	    {name: 'mobile', index: 'mobile', width: 70, align: 'center'}, 
	    {name: 'sex', index: 'sex', width: 30, align: 'center', formatter: 'select', editoptions:{value:'1:男;0:女'}}, 
	    {name: 'age', index: 'age', width: 30, align: 'center'}, 
	    {name: 'certificateType', index: 'certificateType', width: 40, align: 'center', formatter: 'select', editoptions:{value:'1:身份证号'}}, 
	    {name: 'certificateCode', index: 'certificateCode', width: 70, align: 'center'}, 
	    {name: 'level', index: 'level', width: 30, align: 'center'}, 
	    {name: 'address', index: 'address', width: 70, align: 'center'}, 
	  ]; 
	  var rowList = [10, 20, 30, 50];
	  var rownumbers = true; 
	  var multiselect = true;
	  var config={caption: '教练列表', rowNum: 50, autowidth: true, dataType:'local', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};
	  setTimeout(function(){
  	  coachHandle.init(config); 
	    coachHandle.query();
    }, 300);
	  $("#enterBtn").click(function() {
      //var rowId=$('#grid-table').jqGrid('getGridParam','selrow'); //selarrrow
      if(opts.multiselect){
        var ids = coachHandle.getSelectedRows();
        coachHandle.resetSelectedIds();
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