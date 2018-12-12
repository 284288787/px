/**create by liuhua at 2018年3月13日 下午3:18:51**/
package com.booting.mobile.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.lottery.dto.GoldDetailDTO;
import com.booting.lottery.dto.GoldDrawDetailDTO;
import com.booting.lottery.dto.InvitationDetailDTO;
import com.booting.lottery.dto.UserNumDetailDTO;
import com.booting.member.dto.MemberDTO;
import com.booting.service.impl.MemberWebServiceImpl;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.version.InterfaceVersion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
@Api(value = "邀请", description = "邀请")
public class MemberApiController extends ApiBaseController{

	@Autowired
	private MemberWebServiceImpl memberWebServiceImpl;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/addInviter", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "添加一个邀请人", notes = "添加一个邀请人", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "唯一标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "invitationCode", value = "邀请码", paramType = "query", required = true, dataType = "String"),
	})
	public String addInviter(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		String invitationCode = paramHandler.getString("invitationCode");
		this.memberWebServiceImpl.addInviter(userId, invitationCode);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/updatePwd", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "更新密码", notes = "更新密码", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "唯一标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "oldpwd", value = "密码", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "password", value = "密码", paramType = "query", required = true, dataType = "String"),
	})
	public String updatePwd(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		MemberDTO loginUser = getLoginUserInfo(paramHandler);
		String oldpwd = paramHandler.getString("oldpwd");
		String password = paramHandler.getString("password");
		String oldPassword = loginUser.getPassword();
		if (StringUtils.isNotBlank(oldPassword)) {
			if (! oldPassword.equals(oldpwd)) {
				throw new ArgsException(FailureCode.ERR_004.getCode(), "密码错误");
			}
		}
		this.memberWebServiceImpl.updatePwd(loginUser.getMemberId(), password);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/updateMember", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "更新用户信息", notes = "更新用户信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "唯一标识", paramType = "query", required = true, dataType = "String"),
	})
	public String updateMember(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		MemberDTO member = paramHandler.getDTO(MemberDTO.class);
		member.setMemberId(userId);
		this.memberWebServiceImpl.updateMember(member);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/goldList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "金币明细-列表", notes = "金币明细-列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "唯一标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pageNo", value = "页码", paramType = "query", required = true, dataType = "int"),
		@ApiImplicitParam(name = "orderBy", paramType = "query", dataType = "String", value = "排序关键字", required = false),
		@ApiImplicitParam(name = "orderType", paramType = "query", dataType = "String", value = "方式", required = false),
	})
	public String goldList(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		Integer pageNo = paramHandler.getInteger("pageNo");
		String orderBy = paramHandler.getString("orderBy");
		String orderType = paramHandler.getString("orderType");
		List<GoldDetailDTO> goldDetails = this.memberWebServiceImpl.getGoldList(userId, pageNo, orderBy, orderType);
		ApiResult apiResult = new ApiResult(goldDetails);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/drawList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "提现明细-列表", notes = "提现明细-列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "唯一标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pageNo", value = "页码", paramType = "query", required = true, dataType = "int"),
		@ApiImplicitParam(name = "orderBy", paramType = "query", dataType = "String", value = "排序关键字", required = false),
		@ApiImplicitParam(name = "orderType", paramType = "query", dataType = "String", value = "方式", required = false),
	})
	public String drawList(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		Integer pageNo = paramHandler.getInteger("pageNo");
		String orderBy = paramHandler.getString("orderBy");
		String orderType = paramHandler.getString("orderType");
		List<GoldDrawDetailDTO> drawDetails = this.memberWebServiceImpl.drawList(userId, pageNo, orderBy, orderType);
		ApiResult apiResult = new ApiResult(drawDetails);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/invitationList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "邀请明细-列表", notes = "邀请明细-列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "唯一标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pageNo", value = "页码", paramType = "query", required = true, dataType = "int"),
		@ApiImplicitParam(name = "orderBy", paramType = "query", dataType = "String", value = "排序关键字", required = false),
		@ApiImplicitParam(name = "orderType", paramType = "query", dataType = "String", value = "方式", required = false),
	})
	public String invitationList(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		Integer pageNo = paramHandler.getInteger("pageNo");
		String orderBy = paramHandler.getString("orderBy");
		String orderType = paramHandler.getString("orderType");
		List<InvitationDetailDTO> invitationDetails = this.memberWebServiceImpl.invitationList(userId, pageNo, orderBy, orderType);
		ApiResult apiResult = new ApiResult(invitationDetails);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/numberList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "号码明细-列表", notes = "号码明细-列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "唯一标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pageNo", value = "页码", paramType = "query", required = true, dataType = "int"),
		@ApiImplicitParam(name = "orderBy", paramType = "query", dataType = "String", value = "排序关键字", required = false),
		@ApiImplicitParam(name = "orderType", paramType = "query", dataType = "String", value = "方式", required = false),
	})
	public String numberList(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		Integer pageNo = paramHandler.getInteger("pageNo");
		String orderBy = paramHandler.getString("orderBy");
		String orderType = paramHandler.getString("orderType");
		List<UserNumDetailDTO> invitationDetails = this.memberWebServiceImpl.numberList(userId, pageNo, orderBy, orderType);
		ApiResult apiResult = new ApiResult(invitationDetails);
		return ParamHandler.gson.toJson(apiResult);
	}
}
