<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.99love.net/jsp/tag/star-tags" prefix="st"%>
<%@ taglib uri="http://www.99love.net/jsp/tag/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/jquery-ui-1.12.1/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/jqGrid/css/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/buttons.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/js/tools/artDialog4.1.2/skins/blue.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/pageList.css" />
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${basePath}static/jqGrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="${basePath}static/jqGrid/js/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
</head>
<body>
	<div class="main-container" id="main-container">
	<input type="button" value="确认指定属性给服务：${serviceDTO.serviceName}" class="button blue" id="saveBtn"><br><br>
		<c:forEach items="${attributes}" var="attr">
			<input type="checkbox" <c:if test="${choosedAttributes[attr.attributeId] != null}">checked</c:if> name="check-${attr.attributeId}" id="check-${attr.attributeId}" value="${attr.attributeId}"><label for="check-${attr.attributeId}">${attr.attributeName}</label>
		</c:forEach>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<input id="serviceId" type="hidden" value="${serviceDTO.serviceId}">
	<script type="text/javascript">
	var basePath = $("#basePath").val();
	$(function(){
		$("#saveBtn").click(function(){
			var serviceId=$("#serviceId").val();
			var ids="";
			$(":checked").each(function(){
				ids += "," + this.value;
			});
			if(ids){
				ids = ids.substring(1);
			}
			$.ajax({
				url: basePath+'package/saveAttrOfService',
				data: {serviceId: serviceId, attributeIds: ids},
				type: 'post',
				dataType: 'json',
				success: function(res){
					if(res.status=='SUCCESS'){
						art.dialog.close();
						artDialog.alert("设置成功")
					}else{
						artDialog.alert(res.errorMessage)
					}
				}
			});
		})
	});
	</script>
</body>
</html>