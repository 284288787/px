/**create by liuhua at 2017年7月6日 下午4:45:23**/
package com.booting.mobile.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.order.dto.OrderDTO;
import com.booting.service.OrderWebService;
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
@Api(value = "支付/订单", description = "支付/订单")
public class OrderApiController extends ApiBaseController{
	
	@Autowired
	private OrderWebService orderWebService;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/buy", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "openId", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "type", value = "订单类型 1 报名培训项目", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "businessId", value = "订单类型下的业务id", paramType = "query", required = true, dataType = "long"),
		@ApiImplicitParam(name = "payType", value = "支付方式 1支付宝 2微信", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "subject", value = "主题", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "remark", value = "备注", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "couponCodes", value = "优惠券code列表，如果有多个则用逗号分隔", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "details", value = "商品明细 [{productId:'1',productType:'1',quantity:'1'},{productId:'2',productType:'1',quantity:'2'}] productId(购买的产品Id，这里是itemId), productType(产品类型 1 报名培训项目), quantity(购买数量, 固定1)", paramType = "query", required = true, dataType = "json"),
	})
	@ApiOperation(value = "立即支付", notes = "返回订单号，金额等信息，使客户端完成支付", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String buy(@ApiIgnore String params, HttpServletRequest request) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		OrderDTO orderDTO = paramHandler.getDTO(OrderDTO.class);
		orderDTO.setUserId(userId);
		orderDTO.setIp(getIpAddr(request));
		Map<String, Object> payInfo = orderWebService.unifiedOrder(orderDTO);
		ApiResult apiResult = new ApiResult(payInfo);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/payList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "openId", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "businessId", value = "订单类型下的业务id", paramType = "query", required = false, dataType = "long"),
		@ApiImplicitParam(name = "payType", value = "支付方式 1支付宝 2微信", paramType = "query", required = false, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer")
	})
	@ApiOperation(value = "支付记录", notes = "支付记录", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String payList(@ApiIgnore String params, HttpServletRequest request) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		OrderDTO orderDTO = paramHandler.getDTO(OrderDTO.class);
		orderDTO.setUserId(userId);
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		queryParam.setParam(orderDTO);
		ApiResult apiResult = orderWebService.payList(queryParam);
		return ParamHandler.objToString(apiResult);
	}
}
