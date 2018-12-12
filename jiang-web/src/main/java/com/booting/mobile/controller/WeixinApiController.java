/**create by liuhua at 2017年12月10日 下午4:53:17**/
package com.booting.mobile.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.service.impl.MemberWebServiceImpl;
import com.booting.service.impl.WeixinWebServiceImpl;
import com.booting.vo.AccessToken;
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
@Api(value = "微信相關操作", description = "微信相關操作")
public class WeixinApiController extends ApiBaseController{

	@Autowired
	private WeixinWebServiceImpl weixinWebServiceImpl;
	@Autowired
	private MemberWebServiceImpl MemberWebServiceImpl;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getAccessToken", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "获取access_token", notes = "获取access_token", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "code", paramType = "query", required = true, dataType = "String"),
	})
	public String getAccessToken(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String code = paramHandler.getString("code");
		AccessToken accessToken = weixinWebServiceImpl.getAccessToken(code);
		ApiResult apiResult = new ApiResult(accessToken);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/wxLogin", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "微信登录", notes = "微信登录", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "code", value = "code", paramType = "query", required = true, dataType = "String"),
	})
	public String wxLogin(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String code = paramHandler.getString("code");
		Map<String, Object> member = MemberWebServiceImpl.wxLogin(code);
		ApiResult apiResult = new ApiResult(member);
		return ParamHandler.gson.toJson(apiResult);
	}
}
