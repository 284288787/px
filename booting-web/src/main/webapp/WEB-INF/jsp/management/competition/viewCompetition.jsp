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
	.edit-container {
		margin: 0;
	}
	ul li.n{
	    width: 33%;
	}
	.red-word{
		color: red;
	}
</style>
</head>
<body>
	<div class="edit-container">
	<ul class="table">
		<li class="nb red-word">赛事信息</li>
		<li class="n"><strong>类型</strong>${competitionDTO.initiatorTeamId==null?'球局':'赛事'}</li>
		<li class="n"><strong>比赛状态</strong>${competitionDTO.status==1?'已发起':competitionDTO.status==2?'已建立':competitionDTO.status==3?'发起方取消':competitionDTO.status==4?'应战方取消':competitionDTO.status==5?'系统取消':competitionDTO.status==6?'赛事结束':'已删除'}</li>
		<li class=""><strong>比赛赛制</strong>${competitionDTO.competitionFormat==1?'11人制':competitionDTO.competitionFormat==2?'8人制':'5人制'}</li>
		<li class="n"><strong>创建时间</strong>${competitionDTO.createTime}</li>
		<li class="n"><strong>比赛时间</strong>${competitionDTO.competitionTime}</li>
		<li class=""><strong>球馆名称</strong>${competitionDTO.courtName}</li>
		<li class="n"><strong>球馆地址</strong>${competitionDTO.courtAddr} </li>
		<li class="n"><strong>场地名称</strong>${competitionDTO.zoneName} </li>
		<li class=""><strong>是否公开赛</strong>${competitionDTO.open==1?'是':'否'}</li>
	</ul>
	<br>
	<ul class="table">
		<li class="nb red-word">发起方</li>
		<li class="n"><strong>球队名称</strong>${competitionDTO.initiatorTeamName}</li>
		<li class="n"><strong>联系电话</strong>${competitionDTO.initiatorMobile}</li>
		<li class=""><strong>是否有教练</strong>${competitionDTO.challengerTeach==1?'有':'无'}</li>
	</ul>
	<br>
	<c:if test="${competitionDTO.challengerTeamId!=null}">
	<ul class="table">
		<li class="nb red-word">应战方</li>
		<li class="n"><strong>球队名称</strong>${competitionDTO.challengerTeamName}</li>
		<li class="n"><strong>联系电话</strong>${competitionDTO.challengerMobile}</li>
		<li class=""><strong>是否有教练</strong>${competitionDTO.challengerTeach==1?'有':'无'}</li>
	</ul>
	</c:if>
	</div>
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js"></script>
</body>
</html>