/**create by liuhua at 2017年8月8日 下午3:37:02**/
package com.booting.mobile.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.service.CompetitionWebService;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.version.InterfaceVersion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
@Api(value = "首页", description = "首页")
public class HomePageApiController extends ApiBaseController {

	@Autowired
	private CompetitionWebService competitionWebService;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/home", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "首页", notes = "首页:最新签约球队、最新约战、最活跃球队", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String home(@ApiIgnore String params) throws Exception {
		Map<String, Object> data = competitionWebService.homePage();
		ApiResult apiResult = new ApiResult(data);
		return ParamHandler.gson.toJson(apiResult);
	}
}
