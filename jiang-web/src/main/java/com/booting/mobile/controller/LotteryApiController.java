/**create by liuhua at 2018年3月2日 下午2:04:12**/
package com.booting.mobile.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.lottery.dto.LotteryDTO;
import com.booting.lottery.dto.LotteryDetailDTO;
import com.booting.service.impl.LotteryWebServiceImpl;
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
@Api(value = "这里", description = "这里")
public class LotteryApiController extends ApiBaseController{

	@Autowired
	private LotteryWebServiceImpl lotteryWebServiceImpl;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/allowBuy", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "是否可以下注", notes = "是否可以下注", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String login(@ApiIgnore String params) throws Exception {
		ApiResult apiResult = new ApiResult(LotteryWebServiceImpl.allowBuy);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getMemberData", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "得到用户的下注信息", notes = "得到用户的下注信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "唯一标识", paramType = "query", required = true, dataType = "String"),
	})
	public String getMemberData(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		Map<String, Map<String, Object>> map = this.lotteryWebServiceImpl.getMemberData(userId);
		ApiResult apiResult = new ApiResult(map);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getQuestion", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "获取题目", notes = "获取题目", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String getQuestion(@ApiIgnore String params) throws Exception {
		Map<String, Object> map = this.lotteryWebServiceImpl.getQuestion();
		ApiResult apiResult = new ApiResult(map);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/questionRight", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "是否正确", notes = "是否正确", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "questionId", value = "题目id", paramType = "query", required = true, dataType = "long"),
		@ApiImplicitParam(name = "answer", value = "答案", paramType = "query", required = true, dataType = "String"),
	})
	public String questionRight(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long questionId = paramHandler.getLong("questionId");
		String answer = paramHandler.getString("answer");
		String mobile = paramHandler.getString("mobile");
		String code = paramHandler.getString("code");
		Boolean bool = this.lotteryWebServiceImpl.questionRight(questionId, answer, mobile, code);
		ApiResult apiResult = new ApiResult(bool);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/xiazhu", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "下注", notes = "下注", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "唯一标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "lun", value = "轮：0 1 2", paramType = "query", required = true, dataType = "int", allowableValues = "0,1,2"),
		@ApiImplicitParam(name = "nums", value = "选择的号码", paramType = "query", required = true, dataType = "String"),
	})
	public String buy(@ApiIgnore String params, HttpServletRequest request) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long userId = getLoginUserId(paramHandler);
		Integer lun = paramHandler.getInteger("lun");
		String nums = paramHandler.getString("nums");
		LotteryDetailDTO detail = new LotteryDetailDTO();
		detail.setUserId(userId);
		detail.setChooseNums(nums);
		detail.setLun(lun);
		detail.setFormIp(getIpAddr(request));
		this.lotteryWebServiceImpl.buy(detail);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/open", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "开", notes = "开", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
	})
	public String open(@ApiIgnore String params) throws Exception {
		LotteryDTO lottery = this.lotteryWebServiceImpl.open();
		ApiResult apiResult = new ApiResult(lottery);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/history", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "最近12次开奖记录", notes = "最近12次开奖记录", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
	})
	public String history(@ApiIgnore String params) throws Exception {
		Map<String, List<Map<String, Object>>> lotteries = this.lotteryWebServiceImpl.history();
		ApiResult apiResult = new ApiResult(lotteries);
		return ParamHandler.gson.toJson(apiResult);
	}
}
