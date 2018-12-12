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
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/editEntity.css?2" />
<style type="text/css">
	ul li.n{
	    width: 33%;
	}
	.red-word{
		color: red;
	}
	.black-word{
		color: black;
	}
	.addBtn{
		width: 30px;
		height: 26px;
	}
</style>
</head>
<body>
	<div class="edit-container">
		<form action="" id="useServiceForm">
		<c:if test="${competitionDTO.initiatorTeamId!=null}">
		<ul>
			<li class="n "><strong class="red-word">发起方：</strong><span class="black-word">${competitionDTO.initiatorTeamName}</span></li>
			<li><strong>手机号：</strong><span>${competitionDTO.initiatorMobile}</span></li>
			<li ><strong><input value="增" type="button" class="addBtn"></strong></li>
			<li class="n service"><strong>选择服务：</strong><span><select name="initiatorService" class="select">
				<option value="">请选择</option>
				<c:forEach items="${initiatorServices}" var="is">
				<option value="${is.serviceId}" data-num="${is.serviceCount}" data-usednum="${is.serviceUsedNum}">${is.serviceName}</option>
				</c:forEach>
			</select></span></li>
			<li class="n service"><strong>可用总数：</strong><span class="temp">0</span></li>
			<li class="service"><strong>使用数量：</strong><span><input type="text" teamId="${competitionDTO.initiatorTeamId}" class="useServiceNum" placeholder="填0或不填表示本次不使用"></span></li>
		</ul>
		<br>
		</c:if>
		<c:if test="${competitionDTO.challengerTeamId!=null}">
		<ul>
			<li class="n "><strong class="red-word">应战方：</strong><span class="black-word">${competitionDTO.challengerTeamName}</span></li>
			<li><strong>手机号：</strong><span>${competitionDTO.challengerMobile}</span></li>
			<li ><strong><input value="增" type="button" class="addBtn"></strong></li>
			<li class="n service"><strong>选择服务：</strong><span><select name="challengerService" class="select">
				<option value="">请选择</option>
				<c:forEach items="${challengerServices}" var="is">
				<option value="${is.serviceId}" data-num="${is.serviceCount}" data-usednum="${is.serviceUsedNum}">${is.serviceName}</option>
				</c:forEach>
			</select></span></li>
			<li class="n service"><strong>可用总数：</strong><span class="temp">0</span></li>
			<li class="service"><strong>使用数量：</strong><span><input type="text" teamId="${competitionDTO.challengerTeamId}" class="useServiceNum" placeholder="填0或不填表示本次不使用"></span></li>
		</ul>
		</c:if>
		</form>
		<div class="btnGroup">
			<input type="button" value="保存" class="button blue" id="saveBtn">
			<input type="button" value="关闭" class="button grey" onclick="art.dialog.close();">
		</div>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<input id="subjectId" type="hidden" value="${competitionDTO.competitionId}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/additional-methods-local.js"></script>
	<script type="text/javascript">
	var basePath=$("#basePath").val();
	$(function(){
		$(".select").change(function(){
			var serviceId = $(this).val();
			if(serviceId){
				var num=$(this).find("option:selected").attr("data-num") * 1;
				var usednum=$(this).find("option:selected").attr("data-usednum") * 1;
				$(this).parent().parent().next().find("span").text(num - usednum);
				$(this).parent().parent().next().next().find("input").attr({"serviceId": serviceId, "num": num - usednum});
			}
		});
		$(".addBtn").click(function(){
			var fb = $(this).parents("ul").find(".service").clone(true);
			fb.removeClass("service");
			fb.find("input").val("").removeAttr("serviceId");
			fb.find("span.temp").text("0");
			$(this).parents("ul").append(fb);
		});
		$("#saveBtn").click(function(){
			var subjectId=$("#subjectId").val();
			var data=new Array();
			var serviceIds={};
			var bool=true;
			$(".useServiceNum").each(function(){
				var teamId = $(this).attr("teamId");
				var serviceId = $(this).attr("serviceId");
				var num = $(this).attr("num") * 1;
				var useNum = $(this).val() * 1 ;
				console.log(teamId + " " + serviceId + " " + num + " " + useNum);
				if(!serviceId){
					alert("选择服务");
					bool = false;
					return false;
				}
				serviceIds["s"+serviceId] = serviceIds["s"+serviceId]?serviceIds["s"+serviceId]+1:1;
				if(serviceIds["s"+serviceId] > 1){
					alert("已选择过该服务");
					bool = false;
					return false;
				}
				if(!checkNumber(useNum)){
					$(this).select();
					alert("填数量");
					bool = false;
					return false;
				}
				if(useNum > num){
					alert("超出了拥有的可用数量");
					bool = false;
					return false;
				}
				if(useNum > 0)
				data.push({"teamId": teamId, "serviceId": serviceId, "num": useNum, "subjectId": subjectId});
			});
			if(bool)
			$.ajax({
				url: basePath+'competition/saveUsedService',
				data: {"services": JSON.stringify(data)},
				type: 'post',
				dataType: 'json',
				success: function(res){
					document.location.reload();
				}
			});
		});
	});
	function checkNumber(value){
		var moblieReg = new RegExp("^[1-9]{1}\\d*$");
		return moblieReg.test(value);
	}
	</script>
</body>
</html>