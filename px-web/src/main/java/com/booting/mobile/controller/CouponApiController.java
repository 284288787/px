/**create by liuhua at 2018年5月7日 上午10:01:58**/
package com.booting.mobile.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.service.impl.CouponWebService;
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
@Api(value = "卡券", description = "卡券")
public class CouponApiController extends ApiBaseController{

	@Autowired
	private CouponWebService couponWebService;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/allCards", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "pageNo", value = "页码 默认1", paramType = "query", required = false, dataType = "int"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "int"),
	})
	@ApiOperation(value = "所有卡券", notes = "所有卡券", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String allCards(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		String openId = paramHandler.getString("openId");
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		ApiResult apiResult = couponWebService.searchCards(openId, queryParam);
		return ParamHandler.objToString(apiResult);
	} 
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/mineCards", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "businessType", value = "业务类型 固定为 training", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "businessId", value = "培训项目的id", paramType = "query", required = true, dataType = "long"),
	})
	@ApiOperation(value = "我的卡券", notes = "我的卡券", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String mineCards(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String openId = paramHandler.getString("openId");
		String businessType = paramHandler.getString("businessType");
		Long businessId = paramHandler.getLong("businessId");
		List<Map<String, Object>> list = this.couponWebService.mineCards(openId, businessType, businessId);
		ApiResult apiResult = new ApiResult(list);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getSignature", method = {RequestMethod.POST, RequestMethod.GET})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "cardId", value = "卡券id", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "获取签名", notes = "获取签名", httpMethod = "POST", response = String.class)
	public String getSignature(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String cardId = paramHandler.getString("cardId");
		Map<String, Object> map = this.couponWebService.getSignature(cardId);
		ApiResult apiResult = new ApiResult(map);
		return ParamHandler.objToString(apiResult);
	}
}
