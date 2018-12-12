<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.99love.net/jsp/tag/star-tags" prefix="st"%>
<%@ taglib uri="http://www.99love.net/jsp/tag/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/jquery-ui-1.12.1/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/buttons.css" /> 
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/js/tools/artDialog4.1.2/skins/blue.css" />
<link rel="stylesheet" href="${basePath}static/js/tools/kindeditor/themes/default/default.css" /> 
<style type="text/css">
html, body{
	margin:0;
	padding:0
}
div{
	font-size: 14px;
}
.head{
	height: 40px;
	line-height: 40px;
	margin: 0px;
	font-size: 14px;
	background-color: #eeeeee;
}
.title{
	height: 40px;
	line-height: 40px;
	margin: 5px 0px;
	font-size: 14px;
	font-weight: bold;
	background-color: #eeeee0;
}
.title span{
	float: right;
	color: red;
	padding-right:30px;
}
.btns{
	display:none;
}
.result{
	height: 706px;
	width: 100%;
	background-color: #dfdfdf;
}
.gy_table{ margin:5px auto 8px auto; background:#fff; border:1px solid #d8d8d8;}
.gy_table th{ padding:10px 0px;background:#eeeeee;}
.gy_table td{ padding:10px 0px; text-align: center; border:1px solid #d8d8d8;}
</style>
</head> 
<body>
	<div class="edit-container"> 
		<div class="head">
			选择Excel文件：<input type="button" value="选择体测数据Excel文件" class="button red" id="uploadBtn">
			<input type="checkbox" id="errorImport" checked disabled>有错误数据时，所有数据禁止导入？
		</div>
		<div class="title">导入结果 <span class="btns"><input type="button" value="错误数据" class="button yellow error" onclick="viewResult(0)"> <input type="button" value="正确数据" class="button green success" onclick="viewResult()"></span> <span></span></div>
		<div class="result">
			<table width="100%" cellspacing="0" cellpadding="0" class="gy_table">
			</table>
		</div>
	</div>
	<div id="uploadDiv" style="display:none">
		<input type="file" name="uploadExcel" id="uploadExcel" multiple="multiple" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.source.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/utils.js?5"></script>
	<script type="text/javascript">
	var basePath=$("#basePath").val();
	var errorRecords, successRecords;
	$(function(){
		var utilsHandle = new UtilsHandle({
			basePath: basePath,
			uploadFile: {
				uploadBtn: $("#uploadBtn"),
				uploadFileId: "uploadExcel",
				data: {'type': 'excel', 'handle': 'com.booting.service.importdata.PhysicalDataImport', 'errorImport': 1},
				beforeSend: function(xhr){
					$(".title span:not(.btns)").text("导入中...");
				},
				success: function (data, textStatus) {
					$(".title span:not(.btns)").text("");
					var empty = data.data[0].empty;
					if(empty){
						artDialog.alert("没有数据，请填写一些数据后再导入");
						return;
					}
					var heads = data.data[0].heads;
					errorRecords = data.data[0].errorRecords;
					successRecords = data.data[0].successRecords;
					var html="<tr>";
					html+="<th>所在行</th>";
					for(var i in heads){
						html+="<th>"+heads[i]+"</th>";
					}
					$(".gy_table").html(html + "</tr>");
					if(errorRecords && errorRecords.length>0){
						$(".title span:not(.btns)").text("导入被终止，存在异常数据");
						viewResult(0);
					}else{
						$(".title span:not(.btns)").text("导入完成");
						viewResult();
					}
					$(".title span.btns").show();
					$("#uploadDiv").html('<input type="file" name="uploadExcel" id="uploadExcel" multiple="multiple" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">');
					$("#uploadDiv #uploadExcel").on("change", utilsHandle.uploadFunc);
				},
				complete: function (XMLHttpRequest, textStatus) {
		  			
				}
			}
		});
	});
	function viewResult(success){
		var list = successRecords;
		if(success == 0){
			list = errorRecords;
			$("input.success").removeClass("green").addClass("yellow");
			$("input.error").removeClass("yellow").addClass("green");
		}else{
			$("input.error").removeClass("green").addClass("yellow");
			$("input.success").removeClass("yellow").addClass("green");
		}
		$(".gy_table tr:not(:eq(0))").remove();
		var html ="<tr>";
		if(!list || list.length==0){
			var len=$(".gy_table th").length;
			html+="<td colspan='"+len+"'>没有相关数据</td>";
		}else{
			html="";
			for(var i in list){
				html+="<tr>";
				html+="<td>@hang@</td>";
				for(var j in list[i]){
					var temp = list[i][j];
					if(temp.valid){
						html+="<td>"+temp.value+"</td>";
					}else{
						html+="<td style='color:red' title='"+temp.desc+"'>"+temp.value+"</td>";
					}
					html = html.replace("@hang@", temp.rowNum);
				}
				html+="</tr>"
			}
		}
		$(".gy_table").append(html);
	}
	</script>
</body>
</html>