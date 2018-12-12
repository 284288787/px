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
<link rel="stylesheet" type="text/css" media="screen" href="${basePath}static/css/editEntity.css" />
<style type="text/css">
	ul li {
		padding-left: 10%;
	}
	strong{
		text-align: right;
		width: 160px !important;
		font-weight: bold;
	}
	.gy_table{ margin:0 auto 5px auto; background:#fff; border:1px solid #d8d8d8;padding: 10px 20px 0px 20px;border-radius:10px;}
	.gy_table2{ margin:0 0 5px 0; background:#fff; border:1px solid #d8d8d8;}
	.gy_table td{ padding:5px 2px; border-bottom:1px solid #d8d8d8;}
	.gy_table input[type=text]{
		height:20px;
		padding-left:3px;
	}
	.gy_table select{
		height:26px;
	}
	.gy_table .strong2{
		text-align: right;
	}
	.gy_table .btnGroup2{
	    margin: 12px 0px;
	    text-align: center;
	    bottom: 0;
	    width: 100%;
	}
	.gy_table .btnGroup2 input{
	    height: 30px;
	    width: 80px;
	}
	.item{
		width: 73px;
		text-align: center;
		float: left;
	}
	.item input[type=button]{
		height: 96px;
		width: 96%;
		font-size: 50px;
	}
	.item .left{
		width: 75px;
		white-space: nowrap;
		overflow: hidden;
		text-overflow:ellipsis;
	}
</style>
</head> 
<body>
	<div class="edit-container"> 
		<table width="80%" border="0" cellspacing="0" cellpadding="0" class="gy_table">
			<tr>
				<td width="160px"><strong>头像：</strong></td><td>
				<c:forEach items="${coachDTO.headPic}" var="hp">
				<span style="position: relative;"><img class="dataImg" width="70px" height="70px" src="${basePath}${hp.picPath}" data="${hp.picPath}"></span>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<td><strong>姓名：</strong></td><td>${coachDTO.name}</td>
			</tr>
			<tr>
				<td><strong>手机号：</strong></td><td>${coachDTO.mobile}</td>
			</tr>
			<tr>
				<td><strong>性别：</strong></td><td>
				<c:if test="${coachDTO.sex == 1}">男</c:if>
				<c:if test="${coachDTO.sex == 0}">女</c:if>
				</td>
			</tr>
			<tr>
				<td><strong>出生日期：</strong></td><td><fmt:formatDate value="${coachDTO.birthTime}" pattern="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td><strong>证件类型：</strong></td><td>
				<c:if test="${coachDTO.certificateType == 1}">身份证</c:if>
				<c:if test="${coachDTO.certificateType == 2}">护照</c:if>
				</td>
			</tr>
			<tr>
				<td><strong>证件号码：</strong></td><td>${coachDTO.certificateCode}</td>
			</tr>
			<tr>
				<td><strong>证件电子档：</strong></td><td>
				<div class="certificateType certificateType_1" <c:if test="${coachDTO.certificateType == 2}">style="display:none"</c:if>>
					<c:forEach items="${coachDTO.certificatePics}" var="cp">
					<div class="item">
						<div class="right">
						<c:if test="${cp.childType == 1}">
						<img src="${basePath}${cp.picPath}" class="dataImg" height="72px" width="72px" data="${cp.picPath}">
						</c:if>
						<c:if test="${cp.childType == 2}">
						<img src="${basePath}${cp.picPath}" class="dataImg" height="72px" width="72px" data="${cp.picPath}">
						</c:if>
						</div>
						<div class="left">${cp.childTypeName}</div>
					</div>
					</c:forEach>
				</div>
				<div class="certificateType certificateType_2" <c:if test="${coachDTO.certificateType == 1}">style="display:none"</c:if>>
					<c:forEach items="${coachDTO.certificatePics}" var="cp">
					<c:if test="${cp.childType == 1}">
					<div class="item">
						<div class="right">
						<img src="${basePath}${cp.picPath}" class="dataImg" height="72px" width="72px" data="${cp.picPath}">
						</div>
						<div class="left">${cp.childTypeName}</div>
					</div>
					</c:if>
					</c:forEach>
				</div>
				</td>
			</tr>
			<tr>
				<td><strong>住址：</strong></td><td>${coachDTO.address}</td>
			</tr>
			<tr>
				<td><strong>紧急联系人：</strong></td><td>${coachDTO.emergencyContact}</td>
			</tr>
			<tr>
				<td><strong>紧急联系人电话：</strong></td><td>${coachDTO.emergencyContactNumber}</td>
			</tr>
			<tr>
				<td><strong>紧急联系人住址：</strong></td><td>${coachDTO.emergencyContactAddress}</td>
			</tr>
			<tr>
				<td><strong>学历：</strong></td><td>${coachDTO.educationBackground}</td>
			</tr>
			<tr>
				<td><strong>学历电子档：</strong></td><td>
				<img src="${basePath}${coachDTO.eduPics[0].picPath}" class="dataImg" height="72px" width="72px" data="${coachDTO.eduPics[0].picPath}">
				</td>
			</tr>
			<tr>
				<td><strong>毕业学校：</strong></td><td>${coachDTO.school}</td>
			</tr>
			<tr>
				<td><strong>专业名称：</strong></td><td>${coachDTO.specialty}</td>
			</tr>
			<tr>
				<td><strong>职业退役：</strong></td><td>
				<c:if test="${coachDTO.professionalService == 1}">是</c:if>
				<c:if test="${coachDTO.professionalService == 0}">否</c:if>
				</td>
			</tr>
			<tr>
				<td><strong>执教证书：</strong></td><td>
				<div>
					<c:forEach items="${coachDTO.teachingCertPics}" var="tcp">
					<div class="item cert">
						<div class="left" title="${tcp.childTypeName}">${tcp.childTypeName}</div>
						<div class="right">
						<img src="${basePath}${tcp.picPath}" class="dataImg" class="teachingCertUpload" height="72px" width="72px" data="${tcp.picPath}">
						</div>
					</div>
					</c:forEach>
				</div>
				</td>
			</tr>
			<tr>
				<td><strong>工作经历：</strong></td><td height="180px">${coachDTO.workExperience}</td>
			</tr>
			<tr>
				<td><strong>获奖履历：</strong></td><td height="120px">${coachDTO.awardResume}</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="btnGroup2"> 
						<input type="button" value="关闭" class="button grey" onclick="art.dialog.close();"> 
					</div> 
				</td>
			</tr>
		</table>
	</div> 
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.source.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/utils.js"></script>
	<script type="text/javascript">
	$(function(){
		var handle = artDialog.data("params");
		var screenHeight = handle.getScreenHeight();
		$('.dataImg').unbind().click(function(){
			if($('#viewImg').length>0){ 
				$('#viewImg').remove(); 
			} 
			$('body').append('<img id="viewImg" style="display:none" src="'+$(this).attr("src")+'">');
			$('#viewImg').load(function(){
				var w=$(this).width();
				var h=$(this).height();
				var l = w / h;
				if(h > screenHeight * 0.85){
					h = screenHeight * 0.85;
					w = h * l;
				}
				artDialog.alert2('<div style="width:'+w+'px;height:'+h+'px;"><img style="height:'+h+'px" src="'+$(this).attr("src")+'">') 
			}); 
		}); 
	});
	</script>
</body>
</html>