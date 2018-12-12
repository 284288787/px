/**create by liuhua at 2017年6月1日 下午2:45:19**/
package com.booting.mobile.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.service.SystemWebService;
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
@Api(value = "登录与退出", description = "登录与退出")
public class LoginApiController extends ApiBaseController{

	@Autowired
	private SystemWebService systemWebService;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/login", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "APP登录", notes = "APP端登录，版本1.0", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "username", value = "帐号(手机号)", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "passwd", value = "密码", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "clientid", value = "帐号(手机号)", paramType = "query", required = true, dataType = "String")
	})
	public String login(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String username = paramHandler.getString("username");
		String passwd = paramHandler.getString("passwd");
		String clientid = paramHandler.getString("clientid");
		Integer sourceFrom = paramHandler.getInteger("sourceFrom");
		Map<String, Object> data = this.systemWebService.login(username, passwd);
		String token = data.get("token").toString();
		paramHandler.addParam("token", token);
		Long userId = getLoginUserId(paramHandler);
		this.systemWebService.updateClientid(userId, clientid, sourceFrom);
		ApiResult apiResult = new ApiResult(data);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/logout", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "退出APP登录", notes = "退出APP登录，版本1.0", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "唯一标识", paramType = "query", required = true, dataType = "String"),
	})
	public String logout(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		String token = paramHandler.getString("token");
		this.systemWebService.logout(token);
		return null;
	}
}
