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
<style type="text/css">
html,body{
	margin: 0;
	padding: 0;
}
.btnGroup{
    margin-bottom: 12px;
    text-align: center;
    position: fixed;
    bottom: 0;
    width: 100%;
}
ul{
	margin: 0 0 0 25px;
	padding: 0;
    list-style-type: none;
    display: block;
}
ul li{
	margin: 3px 3px;
	padding: 0;
    display: inline;
    white-space:nowrap;
    height:30px;
    line-height:30px;
    color: blue;
    cursor: default;
}
.province{
	font-weight:bold;
	height:35px;
	line-height:32px;
	background-color: #fefefe;
}
.on{
	color:red
}
</style>
</head>
<body>
	<div class="edit-container">
		<div style="height:550px;overflow-y: scroll;">
		<c:forEach items="${areas}" var="area">
			<c:if test="${area.type==3}">
			<div class="item">
				<div class="province">${area.shortName}</div>
				<ul>
				<c:forEach items="${area.children}" var="city">
					<li dataId="${city.areaId}">${city.shortName}</li>
				</c:forEach>
				</ul>
			</div>
			</c:if>
		</c:forEach>
		</div>
		<div class="btnGroup">
			<input type="button" value="关闭" class="button grey" onclick="art.dialog.close();">
		</div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.source.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/additional-methods-local.js?1"></script>
	<script type="text/javascript">
	var opts=artDialog.data("opts");
	$(function(){
		if(opts.areaId){
			$("li[dataId="+opts.areaId+"]").addClass("on");
			var clone=$("li.on").parents("div.item").remove();
			$("div.item:first").before(clone);
		}
		$("li").click(function(){
			var areaId=$(this).attr("dataId");
			var areaName=$.trim($(this).text());
			opts.callback(areaId, areaName);
			art.dialog.close();
		});
	});
	</script>
</body>
</html>