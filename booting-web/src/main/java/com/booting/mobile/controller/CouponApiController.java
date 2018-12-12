/**create by liuhua at 2017年8月15日 上午10:07:22**/
package com.booting.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.coupon.dto.CouponDTO;
import com.booting.service.CouponWebService;
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
@Api(value = "优惠券接口", description = "优惠券接口")
public class CouponApiController extends ApiBaseController{

	@Autowired
	private CouponWebService couponWebService;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/searchCoupons", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "查找约球券", notes = "根据条件查找约球券", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "couponScope", value = "券范围 1所有球场 2专属球场", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "courtId", value = "球馆ID，如果是专属球场情况下", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "couponType", value = "类型 1约球 2保险", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer"),
	})
	public String searchCoupons(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long courtId = paramHandler.getLong("courtId");
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		CouponDTO couponDTO = paramHandler.getDTO(CouponDTO.class);
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		queryParam.setParam(couponDTO);
		queryParam.addParam("courtId", courtId);
		ApiResult apiResult = this.couponWebService.searchCoupons(queryParam);
		return ParamHandler.gson.toJson(apiResult);
	}
}
