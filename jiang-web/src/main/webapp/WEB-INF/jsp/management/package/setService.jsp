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
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${basePath}static/jqGrid/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="${basePath}static/jqGrid/js/i18n/grid.locale-cn.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
	<style type="text/css">
	.gy_table{ margin:0 auto 5px auto; background:#fff; border:1px solid #d8d8d8;}
	.gy_table2{ margin:0 0 5px 0; background:#fff; border:1px solid #d8d8d8;}
	.gy_table th{ padding:10px 5px; border-bottom:1px solid #d8d8d8;}
	.gy_table td{ padding:10px 5px; border-bottom:1px solid #d8d8d8;}
	.ipt{
		width:100px;
		height:30px;
		padding-left:5px;
	}
	</style>
</head>
<body>
	<div class="main-container" id="main-container">
		<input type="button" value="确认选中的服务给套餐：${packageDTO.packageName}" class="button blue" id="saveBtn"><br><br>
		<table width="100%" cellspacing="0" cellpadding="0" class="gy_table">
			<tr>
				<td width="30px"></td>
				<td>服务名称</td>
				<td>属性名称</td>
				<td width="120px">属性值</td>
			</tr>
			<c:forEach items="${services}" var="serv">
			<tr>
				<td rowspan="${serv.attributes.size() + 1}"><input type="checkbox" value="check-${serv.serviceId}" <c:if test="${choosedServices[serv.serviceId] != null}">checked</c:if>></td>
				<td rowspan="${serv.attributes.size() + 1}">${serv.serviceName}</td>
				<td>数量</td>
				<td><input value="${choosedServices[serv.serviceId].count}" type="text" name="serviceCount" serviceId="${serv.serviceId}" class="ipt check-${serv.serviceId}" placeholder="${serv.serviceName}数量"></td>
			</tr>
			<c:forEach items="${serv.attributes}" var="attr">
			<tr>
				<td>${attr.attributeName}</td>
				<c:forEach items="${choosedServices[serv.serviceId].attributes}" var="attrVal">
					<c:if test="${attrVal.attributeId==attr.attributeId }">
						<c:set var="attrValue" value="${attrVal.attributeValue}"></c:set>
					</c:if>
				</c:forEach>
				<td><input value="${attrValue}" type="text" name="attributeValue" serviceId="${serv.serviceId}" attributeId="${attr.attributeId}" class="ipt check-${serv.serviceId}" placeholder="${attr.attributeName}属性值"> </td>
				<c:set var="attrValue" value=""></c:set>
			</tr>
			</c:forEach>
			</c:forEach>
		</table>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<input id="packageId" type="hidden" value="${packageDTO.packageId}">
	<script type="text/javascript">
	var basePath = $("#basePath").val();
	$(function(){
		var packageId=$("#packageId").val();
		$("#saveBtn").click(function(){
			var attrs=new Array();
			var services=new Array();
			$(":checked").each(function(){
				var val=this.value;
				var $num=$("."+val+"[name=serviceCount]");
				var count=$num.val();
				if(checkNumber(count)){
					services.push({serviceId: $num.attr("serviceId"), count: count});
				}else{
					artDialog.alert("数量需为正整数");
					$(this).select();
					return;
				}
				$("."+val+"[name=attributeValue]").each(function(){
					var serviceId=$(this).attr("serviceId");
					var attributeId=$(this).attr("attributeId");
					var attributeValue=$(this).val();
					if(attributeValue){
						attrs.push({packageId: packageId, serviceId: serviceId, attributeId: attributeId, attributeValue: attributeValue});
					}
				});
			});
			$.ajax({
				url: basePath+'package/saveServOfPackage',
				data: {packageId: packageId, services: JSON.stringify(services), attrs: JSON.stringify(attrs)},
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
		});
	});
	
	function checkNumber(str){
		var reg = new RegExp("^[1-9]{1}\\d*$");
		return reg.test(str)
	}
	</script>
</body>
</html>