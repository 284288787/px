/**create by liuhua at 2017年6月8日 下午4:55:14**/
package com.booting.mobile.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.service.SystemWebService;
import com.booting.system.dto.UserInfoDTO;
import com.booting.system.facade.SystemFacade;
import com.star.framework.jdbc.dao.result.QueryParam;
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
@Api(value = "会员操作", description = "会员操作")
public class MemberApiController extends ApiBaseController{
	
	@Autowired
	private SystemFacade systemFacade;
	@Autowired
	private SystemWebService systemWebService;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getMemberInfo", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "获取已登录会员信息", notes = "获取已登录会员信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String")
	})
	public String getMemberInfo(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		Map<String, Object> data = this.systemWebService.getUserInfo(userId);
		ApiResult apiResult = new ApiResult(data);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getTeamFans", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "获取球队的粉丝列表", notes = "获取球队的粉丝列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer"),
	})
	public String getTeamFans(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long teamId = paramHandler.getLong("teamId");
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		queryParam.addParam("teamId", teamId);
		ApiResult apiResult = this.systemWebService.getTeamFans(queryParam);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getTeamAttention", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "获取用户的关注列表", notes = "获取用户的关注列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "userId", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer"),
	})
	public String getTeamAttention(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = paramHandler.getLong("userId");
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		queryParam.addParam("userId", userId);
		ApiResult apiResult = this.systemWebService.getTeamAttention(queryParam);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getMemberInfoByMobile", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "根据用户手机号获取会员信息", notes = "根据用户手机号获取会员信息，仅用在添加球员，根据手机号获取姓名等信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "mobile", value = "手机号码", paramType = "query", required = true, dataType = "String"),
	})
	public String getMemberInfoByMobile(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		getLoginUserId(paramHandler);
		String mobile = paramHandler.getString("mobile");
		Map<String, Object> data = this.systemWebService.getUserInfo(mobile);
		ApiResult apiResult = new ApiResult(data);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/updateMemberInfo", method = {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "编辑会员信息", notes = "编辑会员信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "name", value = "姓名", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "address", value = "地址", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "sex", value = "性别", paramType = "query", required = true, dataType = "Integer", allowableValues = "1男,0女"),
		@ApiImplicitParam(name = "photo", value = "头像路径", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "age", value = "年龄 不得超过99", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "stature", value = "身高 cm", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "weight", value = "体重 * 10 例如655 表示65.5kg", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "poloShirtNo", value = "球衣编号 最大99 不重复", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "teamLocation", value = "踢球的位置", paramType = "query", required = true, dataType = "Integer", allowableValues = "1门将,2后卫,3中场,4前锋"),
		@ApiImplicitParam(name = "remark", value = "签名备注", paramType = "query", required = true, dataType = "String")
	})
	public String updateMemberInfo(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		UserInfoDTO userInfoDTO = paramHandler.getDTO(UserInfoDTO.class);
		userInfoDTO.setUserId(userId);
		this.systemFacade.updateUserInfo(userInfoDTO);
		return null;
	}
	
}
