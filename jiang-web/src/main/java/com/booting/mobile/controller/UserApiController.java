/**create by liuhua at 2017年6月3日 下午3:28:46**/
package com.booting.mobile.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.pkg.dto.PackageDTO;
import com.booting.pkg.dto.UseServiceDetailDTO;
import com.booting.pkg.dto.UserPackageDTO;
import com.booting.service.SystemWebService;
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
@Api(value = "用户", description = "用户")
public class UserApiController extends ApiBaseController{

	@Autowired
	private SystemWebService systemWebService;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/allPackage", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "所有可用的服务", notes = "获取所有可用的服务", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String allPackage(@ApiIgnore String params) throws Exception {
		PackageDTO packageDTO = new PackageDTO();
		packageDTO.setEnabled(1);
		List<PackageDTO> list = this.systemWebService.getPackage(packageDTO);
		ApiResult apiResult = new ApiResult(list);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/serviceUsedList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer")
	})
	@ApiOperation(value = "服务使用列表", notes = "服务使用列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String serviceUsedList(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		UseServiceDetailDTO useServiceDetailDTO = new UseServiceDetailDTO();
		useServiceDetailDTO.setUserId(userId);
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		queryParam.setParam(useServiceDetailDTO);
		ApiResult apiResult = this.systemWebService.serviceUsedList(queryParam);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/serviceBuyList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer")
	})
	@ApiOperation(value = "服务购买列表", notes = "服务购买列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String serviceBuyList(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		UserPackageDTO userPackageDTO = new UserPackageDTO();
		userPackageDTO.setUserId(userId);
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		queryParam.setParam(userPackageDTO);
		ApiResult apiResult = this.systemWebService.serviceBuyList(queryParam);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/mineService", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "我的服务", notes = "我的服务", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String mineService(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		Map<String, Object> services = this.systemWebService.mineService(userId);
		ApiResult apiResult = new ApiResult(services);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/changePassword", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "修改密码", notes = "修改密码", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "passwd", value = "原密码", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "newpasswd", value = "新密码", paramType = "query", required = true, dataType = "String")
	})
	public String changePassword(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String username = getLoginUsername(paramHandler);
		String passwd = paramHandler.getString("passwd");
		String newpasswd = paramHandler.getString("newpasswd");
		this.systemWebService.changePassword(username, newpasswd, passwd);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/findPassword", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "找回密码", notes = "找回密码", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "帐号(手机号)", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "code", value = "手机验证码", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "newpasswd", value = "新密码", paramType = "query", required = true, dataType = "String")
	})
	public String findPassword(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String username = paramHandler.getString("username");
		String code = paramHandler.getString("code");
		String newpasswd = paramHandler.getString("newpasswd");
		this.systemWebService.findPassword(username, newpasswd, code);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/checkUsername", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "校验手机号", notes = "校验手机号", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "帐号(手机号)", paramType = "query", required = true, dataType = "String")
	})
	public String checkUsername(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String username = paramHandler.getString("username");
		this.systemWebService.checkUsername(username);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/register", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "注册", notes = "注册", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "帐号(手机号)", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "code", value = "手机验证码", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "passwd", value = "密码", paramType = "query", required = true, dataType = "String")
	})
	public String register(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String username = paramHandler.getString("username");
		String authcode = paramHandler.getString("code");
		String passwd = paramHandler.getString("passwd");
		String clientid = paramHandler.getString("clientid");
		Integer sourceFrom = paramHandler.getInteger("sourceFrom");
		Map<String, Object> data = this.systemWebService.register(username, passwd, authcode, clientid, sourceFrom);
		ApiResult apiResult = new ApiResult(data);
		return ParamHandler.gson.toJson(apiResult);
	}
}

