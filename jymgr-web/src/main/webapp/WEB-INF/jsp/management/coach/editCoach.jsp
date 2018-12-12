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
	.item .left select{
		width: 70px;
	}
</style>
</head> 
<body>
	<div class="edit-container"> 
		<form action="" id="editCoachForm"> 
		<input type="hidden" name="memberId" value="${coachDTO.memberId}">
		<table width="80%" border="0" cellspacing="0" cellpadding="0" class="gy_table">
			<tr>
				<td width="160px"><strong>头像：</strong></td><td>
				<img src="${basePath}static/images/uploadBtn.png" id="uploadHeadPicBtn">
				<c:forEach items="${coachDTO.headPic}" var="hp">
				<span style="position: relative;"><img class="dataImg" width="70px" height="70px" src="${basePath}${hp.picPath}" data="${hp.picPath}"><div class="close">X</div></span>
				</c:forEach>
				</td>
			</tr>
			<tr>
				<td><strong>姓名：</strong></td><td><input type="text" name="name" placeholder="姓名" value="${coachDTO.name}"></td>
			</tr>
			<tr>
				<td><strong>手机号：</strong></td><td><input type="text" name="mobile" placeholder="手机号" value="${coachDTO.mobile}"></td>
			</tr>
			<tr>
				<td><strong>性别：</strong></td><td>
				<input type="radio" name="sex" id="sex_1" value="1" <c:if test="${coachDTO.sex == 1}">checked</c:if>><label for="sex_1">男</label>
				<input type="radio" name="sex" id="sex_0" value="0" <c:if test="${coachDTO.sex == 0}">checked</c:if>><label for="sex_0">女</label>
				</td>
			</tr>
			<tr>
				<td><strong>出生日期：</strong></td><td><input type="text" name="birthTime" placeholder="出生日期" class="Wdate" onclick="WdatePicker({maxDate:'{%y-20}-%M-%d',isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<fmt:formatDate value="${coachDTO.birthTime}" pattern="yyyy-MM-dd"/>"></td>
			</tr>
			<tr>
				<td><strong>证件类型：</strong></td><td>
				<input type="radio" name="certificateType" id="certificateType_1" value="1" <c:if test="${coachDTO.certificateType == 1}">checked</c:if>><label for="certificateType_1">身份证</label>
				<input type="radio" name="certificateType" id="certificateType_2" value="2" <c:if test="${coachDTO.certificateType == 2}">checked</c:if>><label for="certificateType_2">护照</label>
				</td>
			</tr>
			<tr>
				<td><strong>证件号码：</strong></td><td><input type="text" name="certificateCode" placeholder="证件号码" class="ipt300" value="${coachDTO.certificateCode}"></td>
			</tr>
			<tr>
				<td><strong>证件电子档：</strong></td><td>
				<div class="certificateType certificateType_1" <c:if test="${coachDTO.certificateType == 2}">style="display:none"</c:if>>
					<c:forEach items="${coachDTO.certificatePics}" var="cp">
					<div class="item">
						<div class="right">
						<c:if test="${cp.childType == 1}">
						<img src="${basePath}${cp.picPath}" id="uploadIdCardZBtn" height="72px" width="72px" data="${cp.picPath}">
						</c:if>
						<c:if test="${cp.childType == 2}">
						<img src="${basePath}${cp.picPath}" id="uploadIdCardFBtn" height="72px" width="72px" data="${cp.picPath}">
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
						<img src="${basePath}${cp.picPath}" id="uploadFzBtn" height="72px" width="72px" data="${cp.picPath}">
						</div>
						<div class="left">${cp.childTypeName}</div>
					</div>
					</c:if>
					</c:forEach>
				</div>
				</td>
			</tr>
			<tr>
				<td><strong>住址：</strong></td><td><input type="text" name="address" placeholder="住址" class="ipt400" value="${coachDTO.address}"></td>
			</tr>
			<tr>
				<td><strong>紧急联系人：</strong></td><td><input type="text" name="emergencyContact" placeholder="紧急联系人" value="${coachDTO.emergencyContact}"></td>
			</tr>
			<tr>
				<td><strong>紧急联系人电话：</strong></td><td><input type="text" name="emergencyContactNumber" placeholder="紧急联系人电话" value="${coachDTO.emergencyContactNumber}"></td>
			</tr>
			<tr>
				<td><strong>紧急联系人住址：</strong></td><td><input type="text" name="emergencyContactAddress" placeholder="紧急联系人住址" class="ipt400" value="${coachDTO.emergencyContactAddress}"></td>
			</tr>
			<tr>
				<td><strong>学历：</strong></td><td>
				<input type="radio" name="educationBackground" id="educationBackground_1" value="博士" <c:if test="${coachDTO.educationBackground == '博士'}">checked</c:if>><label for="educationBackground_1">博士</label>
				<input type="radio" name="educationBackground" id="educationBackground_2" value="硕士" <c:if test="${coachDTO.educationBackground == '硕士'}">checked</c:if>><label for="educationBackground_2">硕士</label>
				<input type="radio" name="educationBackground" id="educationBackground_3" value="本科" <c:if test="${coachDTO.educationBackground == '本科'}">checked</c:if>><label for="educationBackground_3">本科</label>
				<input type="radio" name="educationBackground" id="educationBackground_4" value="大专" <c:if test="${coachDTO.educationBackground == '大专'}">checked</c:if>><label for="educationBackground_4">大专</label>
				<input type="radio" name="educationBackground" id="educationBackground_5" value="高中" <c:if test="${coachDTO.educationBackground == '高中'}">checked</c:if>><label for="educationBackground_5">高中</label>
				<input type="radio" name="educationBackground" id="educationBackground_6" value="初中" <c:if test="${coachDTO.educationBackground == '初中'}">checked</c:if>><label for="educationBackground_6">初中</label>
				</td>
			</tr>
			<tr>
				<td><strong>学历电子档：</strong></td><td>
				<img src="${basePath}${coachDTO.eduPics[0].picPath}" id="uploadEduBtn" height="72px" width="72px" data="${coachDTO.eduPics[0].picPath}">
				</td>
			</tr>
			<tr>
				<td><strong>毕业学校：</strong></td><td><input type="text" name="school" placeholder="毕业学校名称" class="ipt300" value="${coachDTO.school}"></td>
			</tr>
			<tr>
				<td><strong>专业名称：</strong></td><td><input type="text" name="specialty" placeholder="专业名称" value="${coachDTO.specialty}"></td>
			</tr>
			<tr>
				<td><strong>职业退役：</strong></td><td>
				<input type="radio" name="professionalService" id="professionalService_1" value="1" <c:if test="${coachDTO.professionalService == 1}">checked</c:if>><label for="professionalService_1">是</label>
				<input type="radio" name="professionalService" id="professionalService_0" value="0" <c:if test="${coachDTO.professionalService == 0}">checked</c:if>><label for="professionalService_0">否</label>
				</td>
			</tr>
			<tr>
				<td><strong>执教证书：</strong></td><td>
				<div>
					<c:forEach items="${coachDTO.teachingCertPics}" var="tcp">
					<div class="item cert">
						<div class="left"><select class="teachingCertSelect"><option value="">请选择...</option>
						<c:forEach items="${types}" var="t">
						<option value="${t.childType}" <c:if test="${tcp.childType == t.childType}">selected</c:if>>${t.childTypeName}</option>
						</c:forEach>
						</select></div>
						<div class="right">
						<img src="${basePath}${tcp.picPath}" id="uploadTeachingCertBtn" class="teachingCertUpload" height="72px" width="72px" data="${tcp.picPath}">
						</div>
					</div>
					</c:forEach>
					<div class="item">
						<input type="button" value="+" class="button blue" id="addTeachingCertBtn">
					</div>
				</div>
				</td>
			</tr>
			<tr>
				<td><strong>工作经历：</strong></td><td><textarea rows="1" cols="1" style="width:90%; height:180px;margin: 4px 0px" name="workExperience" placeholder="工作经历">${coachDTO.workExperience}</textarea></td>
			</tr>
			<tr>
				<td><strong>获奖履历：</strong></td><td><textarea rows="1" cols="1" style="width:90%; height:120px;margin: 4px 0px" name="awardResume" placeholder="获奖履历">${coachDTO.awardResume}</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="btnGroup2"> 
						<input type="button" value="保存" class="button blue" id="saveBtn">
						<input type="button" value="关闭" class="button grey" onclick="art.dialog.close();"> 
					</div> 
				</td>
			</tr>
		</table>
		</form>
	</div> 
	<div id="uploadDiv" style="display:none">
		<input type="file" name="uploadImg" id="uploadImg">
	</div> 
	<input id="basePath" type="hidden" value="${basePath}">
	<script type="text/javascript" src="${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.source.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/ajaxfileupload.js"></script>
	<script charset="utf-8" src="${basePath}static/js/tools/kindeditor/kindeditor.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js"></script> 
	<script type="text/javascript" src="${basePath}static/js/tools/jquery-validation-1.15.0/dist/additional-methods-local.js"></script>
	<script type="text/javascript" src="${basePath}static/js/tools/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/utils.js"></script>
	<script type="text/javascript" src="${basePath}static/js/management/coach/editCoach.js"></script> 
	<script type="text/javascript">
	$(function(){
		$("#addTeachingCertBtn").click(function(){
			var divObj = $(".cert:first").clone();
			var bool = false, boolPic = false;
			$(".teachingCertSelect").each(function(){
				var val = $(this).val();
				if(val){
					$(".teachingCertSelect option[value="+val+"]", divObj).remove();
					bool = false;
				} else {
					bool = true;
				}
				var picPath = $("img", $(this).parent().parent()).attr("data");
				if(!picPath) boolPic = true;
				else boolPic = false;
			});
			if(bool) {
				artDialog.alert("请先选择证书");
				return;
			}
			if(boolPic) {
				artDialog.alert("请先上传证书");
				return;
			}
			if($(".teachingCertSelect option", divObj).length<=1){
				artDialog.alert("没有更多证书可以选择");
				return;
			}
			var id = "uploadTeachingCertBtn" + $("img.teachingCertUpload").length;
			$("img.teachingCertUpload", divObj).attr({"id": id, "src": basePath+"static/images/uploadBtn.png", "data": ""});
			
			$(this).parent().before(divObj);
			utilsHandle.addUploadFile({
				uploadBtn: $('#'+id), 
				success: function (data, textStatus) {
					var pic = data.data;
					$('#'+id).attr({"src": basePath+pic, "data": pic}); 
				}
			})
		});
		$(":radio[name=certificateType]").change(function(){
			var val = $(this).val();
			$(".certificateType").hide();
			$(".certificateType_"+val).show();
		});
	});
	</script>
</body>
</html>