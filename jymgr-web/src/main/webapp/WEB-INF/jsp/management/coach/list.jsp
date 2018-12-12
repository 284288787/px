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
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/pageList.css?2" /> 
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/jquery-ui.min.js"></script> 
	<script type="text/javascript" src="${basePath}static/jqGrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="${basePath}static/jqGrid/js/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script> 
	<style type="text/css">
		select.mult{height: auto !important}
		input[type=checkbox]{
			vertical-align: middle;
		}
	</style>
</head>
<body> 
	<div class="main-container" id="main-container"> 
		<div class="search-container"> 
			<form action="${basePath}" id="queryForm"> 
				<span>手机号：<input type="text" name="mobile" placeholder="输入手机号" > </span> 
				<span>姓名：<input type="text" name="name" placeholder="输入姓名" > </span> 
				<span>证件号：<input type="text" name="certificateCode" placeholder="输入证件号码" > </span> 
				<span>职业退役与否：<select name="professionalService"><option value="">全部</option><option value="1" >是</option><option value="0" >否</option></select> </span> 
				<span>状态：<select name="enabled"><option value="">全部</option><option value="1" >可用</option><option value="0" >禁用</option></select> </span> <br>
				<div style="float: left; padding-top:5px">持证情况：<br>(按Ctrl+单击,可多选)<br><br><input type="checkbox"id="precisecheckbox"><label for="precisecheckbox">持有所有选中证件</label> </div>
				<span style="display:block;padding-top:5px;"><select class="mult" name="certTypes" size="5" multiple></select>
				</span> 
				<a onclick="coachHandle.query()" class="button blue">查询</a>
				<a onclick="coachHandle.reset()" class="button grey">清空</a>
				<input type="hidden" name="precise" id="precise"> 
			</form>
		</div> 
		<div class="data-container"> 
				<div class="btnGroup"> 
				<a onclick="coachHandle.addNew()" class="button grey">新增</a>
				<a onclick="coachHandle.enabled()" class="button blue">启用</a> 
				<a onclick="coachHandle.disabled()" class="button yellow">禁用</a>
				<a onclick="coachHandle.remove()" class="button yellow">删除</a>
				</div> 
			<table id="grid-table"></table>
			<div id="grid-pager"></div>
		</div> 
	</div> 
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/js/management/common.js?3"></script> 
	<script type="text/javascript" src="${basePath}static/js/management/utils.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/coach/list.js?2"></script>
	<script type="text/javascript">
	$(function(){
		$("#precisecheckbox").change(function(){
			$("input#precise").val(this.checked);
		});
		$.ajax({
			async: true,
			url: basePath+'coach/getTeachingCerts',
			type: 'post',
			dataType: 'json',
			success: function(res){
				var teachingCert = res.data;
				html='';
				for(var o in teachingCert){
					var t = teachingCert[o];
					html+='<option value="'+t.childType+'">'+t.childTypeName+'</option>'
				}
				$("#queryForm span:last select").html(html);
			}
		});
	});
	</script>
</body>
</html>