/**create by liuhua at 2017年7月15日 下午4:12:45**/
package com.booting.mobile.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.competition.dto.CompetitionDTO;
import com.booting.service.CompetitionWebService;
import com.star.framework.jdbc.dao.result.QueryParam;
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
@Api(value = "赛事接口", description = "赛事接口")
public class CompetitionApiController extends ApiBaseController{

	@Autowired
	private CompetitionWebService competitionWebService;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/searchCompetitions", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "查找赛事", notes = "根据条件查找赛事", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "teamId", value = "球队Id，查找该队发起或应战的赛事，空则查所有", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "competitionType", value = "比赛类型 1邀请赛 2友谊赛 3挑战赛", paramType = "query", required = false, dataType = "Integer", allowableValues = "1,2,3"),
		@ApiImplicitParam(name = "competitionTime", value = "比赛时间 yyyy-MM-dd HH:mm:ss", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "initiatorTeamName", value = "发起方球队名称", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "competitionFormat", value = "比赛赛制 1:11人制;2:8人制", paramType = "query", required = false, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "qj", value = "是否球局 0否 1是 2全", paramType = "query", required = false, dataType = "Integer", allowableValues = "0,1,2"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "states", value = "状态 1发起赛事 2建立赛事 3发起方取消赛事 4挑战方取消赛事 5系统取消 6赛事结束 7删除赛事，组合之间用逗号分隔", paramType = "query", required = false, dataType = "String"),
	})
	public String searchCompetitions(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long teamId = paramHandler.getLong("teamId");
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		CompetitionDTO competitionDTO = paramHandler.getDTO(CompetitionDTO.class);
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		queryParam.setParam(competitionDTO);
		queryParam.addParam("teamId", teamId);
		ApiResult apiResult = this.competitionWebService.searchCompetitions(queryParam);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/findCompetitions", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "寻找赛事", notes = "寻找赛事，提及我的和全部", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "findAll", value = "1公开的未应战赛事 2提及我的未应战赛事", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "competitionType", value = "比赛类型 1邀请赛 2友谊赛 3挑战赛", paramType = "query", required = false, dataType = "Integer", allowableValues = "1,2,3"),
		@ApiImplicitParam(name = "competitionTime", value = "比赛时间 yyyy-MM-dd HH:mm:ss", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "initiatorTeamName", value = "发起方球队名称", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "competitionFormat", value = "比赛赛制 1:11人制;2:8人制", paramType = "query", required = false, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "qj", value = "是否球局 0否 1是 2全", paramType = "query", required = false, dataType = "Integer", allowableValues = "0,1,2"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer"),
	})
	public String findCompetitions(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		Integer findAll = paramHandler.getInteger("findAll");
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		CompetitionDTO competitionDTO = paramHandler.getDTO(CompetitionDTO.class);
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		queryParam.setParam(competitionDTO);
		queryParam.addParam("findAll", findAll);
		ApiResult apiResult = this.competitionWebService.findCompetitions(loginUserId, queryParam);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/mineCompetitions", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "我的赛事", notes = "我的所有赛事，包含主动发起记录，和应战记录", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "queryType", value = "1发起的赛事 2应战的赛事 3全部 4球局全部", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "competitionType", value = "比赛类型 1邀请赛 2友谊赛 3挑战赛", paramType = "query", required = false, dataType = "Integer", allowableValues = "1,2,3"),
		@ApiImplicitParam(name = "competitionTime", value = "比赛时间 yyyy-MM-dd HH:mm:ss", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "courtId", value = "比赛球馆ID", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "type", value = "类型 1锁定场;2预定场", paramType = "query", required = false, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "competitionFormat", value = "比赛赛制 1:11人制;2:8人制", paramType = "query", required = false, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer"),
	})
	public String mineCompetitions(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		Integer queryType = paramHandler.getInteger("queryType");
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		CompetitionDTO competitionDTO = paramHandler.getDTO(CompetitionDTO.class);
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		queryParam.setParam(competitionDTO);
		queryParam.addParam("queryType", queryType);
		ApiResult apiResult = this.competitionWebService.mineCompetitions(loginUserId, queryParam);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/competitionDetail", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "赛事详情", notes = "赛事详情", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "competitionId", value = "赛事ID", paramType = "query", required = true, dataType = "Long"),
	})
	public String competitionDetail(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long competitionId = paramHandler.getLong("competitionId");
		CompetitionDTO competitionDTO = this.competitionWebService.competitionDetail(competitionId);
		ApiResult apiResult = new ApiResult(competitionDTO);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/cancelCompetition", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "取消赛事", notes = "取消赛事", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "competitionId", value = "赛事ID", paramType = "query", required = true, dataType = "Long"),
	})
	public String cancelCompetition(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		Long competitionId = paramHandler.getLong("competitionId");
		this.competitionWebService.cancelCompetition(loginUserId, competitionId);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/remindCompetition", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "提醒赛事", notes = "提醒赛事", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "competitionId", value = "赛事ID", paramType = "query", required = true, dataType = "Long"),
	})
	public String remindCompetition(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		Long competitionId = paramHandler.getLong("competitionId");
		this.competitionWebService.remindCompetition(loginUserId, competitionId);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/acceptCompetition", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "接受赛事", notes = "用户选择赛事，并且填写信息后，校验", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "competitionId", value = "赛事ID", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "challengerColor", value = "球队颜色", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "challengerTeach", value = "是否有教练 1是 0否", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,0"),
		@ApiImplicitParam(name = "challengerPayType", value = "发起人支付方式 1约球券", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "challengerBuyInsurance", value = "是否买保险 1是 0否", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,0"),
		@ApiImplicitParam(name = "challengerInsuranceList", value = "买保险的人员名单 initiatorBuyInsurance=1时 [{userId:'1',identityNo:'430112'},{userId:'1',identityNo:'430112'}]", paramType = "query", required = true, dataType = "Json"),
	})
	public String acceptCompetition(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		CompetitionDTO competitionDTO = paramHandler.getDTO(CompetitionDTO.class);
		competitionDTO.setChallengerUserId(loginUserId);
		competitionDTO = this.competitionWebService.checkAcceptCompetition(competitionDTO);
		ApiResult apiResult = new ApiResult(competitionDTO);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/acceptEnterCompetition", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "接受赛事", notes = "用户选择赛事，并且填写信息后，校验", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "competitionId", value = "赛事ID", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "challengerColor", value = "球队颜色", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "challengerTeach", value = "是否有教练 1是 0否", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,0"),
		@ApiImplicitParam(name = "challengerPayType", value = "发起人支付方式 1约球券", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "challengerBuyInsurance", value = "是否买保险 1是 0否", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,0"),
		@ApiImplicitParam(name = "challengerInsuranceList", value = "买保险的人员名单 initiatorBuyInsurance=1时 [{userId:'1',identityNo:'430112'},{userId:'1',identityNo:'430112'}]", paramType = "query", required = true, dataType = "Json"),
	})
	public String acceptEnterCompetition(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		CompetitionDTO competitionDTO = paramHandler.getDTO(CompetitionDTO.class);
		competitionDTO.setChallengerUserId(loginUserId);
		this.competitionWebService.acceptEnterCompetition(competitionDTO);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/initiateCompetition", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "发起赛事", notes = "用户填写信息后提交，校验", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "competitionType", value = "比赛类型 1邀请赛 2友谊赛 3挑战赛", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2,3"),
		@ApiImplicitParam(name = "competitionTime", value = "比赛时间 yyyy-MM-dd HH:mm:ss", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "courtId", value = "比赛球馆ID", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "type", value = "类型 1锁定场;2预定场", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "competitionFormat", value = "比赛赛制 1:11人制;2:8人制", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "initiatorColor", value = "球队颜色", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "initiatorTeach", value = "是否有教练 1是 0否", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,0"),
		@ApiImplicitParam(name = "open", value = "是否公开 1是 0否，只有挑战赛才有此项", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,0"),
		@ApiImplicitParam(name = "noOpenType", value = "不公开时候的类型 1球队 2行业，只有open=0时需要填写，open=1则为空", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "typeValue", value = "根据比赛类型和是否公开决定的值，球队IDs 或 行业IDs，多个id用英文逗号分隔", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "initiatorPayType", value = "发起人支付方式 1约球券", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "initiatorBuyInsurance", value = "是否买保险 1是 0否", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,0"),
		@ApiImplicitParam(name = "initiatorInsuranceList", value = "买保险的人员名单 initiatorBuyInsurance=1时 [{userId:'1',identityNo:'430112'},{userId:'1',identityNo:'430112'}]", paramType = "query", required = true, dataType = "Json"),
	})
	public String initiateCompetition(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		CompetitionDTO competitionDTO = paramHandler.getDTO(CompetitionDTO.class);
		competitionDTO.setInitiateUserId(loginUserId);
		competitionDTO = this.competitionWebService.checkCompetition(competitionDTO);
		ApiResult apiResult = new ApiResult(competitionDTO);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/enterCompetition", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "确认创建赛事", notes = "发起方创建赛事，等待挑战方应战", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "competitionType", value = "比赛类型 1邀请赛 2友谊赛 3挑战赛", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2,3"),
		@ApiImplicitParam(name = "competitionTime", value = "比赛时间 yyyy-MM-dd HH:mm:ss", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "courtId", value = "比赛球馆ID", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "type", value = "类型 1锁定场;2预定场", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "competitionFormat", value = "比赛赛制 1:11人制;2:8人制", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "initiatorColor", value = "球队颜色", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "initiatorTeach", value = "是否有教练 1是 0否", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,0"),
		@ApiImplicitParam(name = "open", value = "是否公开 1是 0否，只有挑战赛才有此项", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,0"),
		@ApiImplicitParam(name = "noOpenType", value = "不公开时候的类型 1球队 2行业，只有open=0时需要填写，open=1则为空", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "typeValue", value = "根据比赛类型和是否公开决定的值，球队IDs 或 行业IDs，多个id用英文逗号分隔", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "initiatorPayType", value = "发起人支付方式 1约球券", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "initiatorBuyInsurance", value = "是否买保险 1是 0否", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,0"),
		@ApiImplicitParam(name = "initiatorInsuranceList", value = "买保险的人员名单 initiatorBuyInsurance=1时 [{userId:'1',identityNo:'430112'},{userId:'1',identityNo:'430112'}]", paramType = "query", required = true, dataType = "Json"),
	})
	public String enterCompetition(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		CompetitionDTO competitionDTO = paramHandler.getDTO(CompetitionDTO.class);
		competitionDTO.setInitiateUserId(loginUserId);
		Long competitionId = this.competitionWebService.enterCompetition(competitionDTO);
		ApiResult apiResult = new ApiResult(competitionId);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/usableCourts", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "满足条件的可用球场", notes = "满足条件的可用球场，当选择赛制和时间后选择球场时使用", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
//		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "competitionTime", value = "比赛时间 yyyy-MM-dd HH:mm:ss", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "competitionFormat", value = "比赛赛制 1:11人制;2:8人制", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2"),
	})
	public String usableCourts(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
//		getLoginUserId(paramHandler);
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		CompetitionDTO competitionDTO = paramHandler.getDTO(CompetitionDTO.class);
//		competitionDTO.setInitiateUserId(loginUserId);
		if (null == competitionDTO.getCompetitionFormat() || null == competitionDTO.getCompetitionTime()) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(competitionDTO.getCompetitionTime());
		calendar.add(Calendar.HOUR_OF_DAY, 2);
		queryParam.setParam(competitionDTO);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		queryParam.addParam("competitionTimeEnd", sdf.format(calendar.getTime()));
		ApiResult apiResult = this.competitionWebService.usableCourts(queryParam);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/recommendTeams", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "所有的球队", notes = "推荐的球队，当选择赛制和时间后选择球场时使用", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "recommend", value = "推荐类型 0所有 1场地 2时间 3赛制", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "teamName", value = "球队名称", paramType = "query", required = false, dataType = "String"),
	})
	public String recommendTeams(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		Integer recommend = paramHandler.getInteger("recommend");
		String teamName = paramHandler.getString("teamName");
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		queryParam.addParam("teamName", teamName);
		ApiResult apiResult = this.competitionWebService.recommendTeams(loginUserId, recommend, queryParam);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/recmdTeams", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "推荐的球队", notes = "推荐的球队，当选择赛制和时间后选择球场时使用", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "areaId", value = "地区ID", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "businessId", value = "行业ID", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "courtId", value = "已选定的球场ID", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "competitionTime", value = "比赛时间 yyyy-MM-dd HH:mm:ss", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "competitionFormat", value = "比赛赛制 1:11人制;2:8人制", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2"),
	})
	public String recmdTeams(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		Long areaId = paramHandler.getLong("areaId");
		Long businessId = paramHandler.getLong("businessId");
		Long courtId = paramHandler.getLong("courtId");
		String competitionTime = paramHandler.getString("competitionTime");
		Integer competitionFormat = paramHandler.getInteger("competitionFormat");
		if (null == competitionFormat || null == courtId || null == areaId || StringUtils.isBlank(competitionTime)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = sdf.parse(competitionTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date1);
		calendar.add(Calendar.HOUR_OF_DAY, 2);
		queryParam.addParam("areaId", areaId);
		queryParam.addParam("businessId", businessId);
		queryParam.addParam("courtId", courtId);
		queryParam.addParam("competitionTime", competitionTime);
		queryParam.addParam("competitionTimeEnd", sdf.format(calendar.getTime()));
		queryParam.addParam("competitionFormat", competitionFormat);
		ApiResult apiResult = this.competitionWebService.recommendTeams(loginUserId, queryParam);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/addCompetition", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "添加赛事", notes = "添加赛事", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "zoneId", value = "比赛球馆区域ID", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "competitionTime", value = "比赛时间 yyyy-MM-dd HH:mm:ss", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "competitionFormat", value = "比赛赛制 1:11人制;2:8人制", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "initiatorMobile", value = "联系电话", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "initiatorTeamName", value = "球队名称", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "initiatorColor", value = "球队颜色", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "initiatorRemark", value = "发起方备注", paramType = "query", required = false, dataType = "String"),
	})
	public String addCompetition(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		CompetitionDTO competitionDTO = paramHandler.getDTO(CompetitionDTO.class);
		Long competitionId = this.competitionWebService.addCompetition(loginUserId, competitionDTO);
		ApiResult apiResult = new ApiResult(competitionId);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/updateCompetition", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "编辑赛事", notes = "编辑赛事", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "competitionId", value = "比赛id", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "competitionFormat", value = "比赛赛制 1:11人制;2:8人制", paramType = "query", required = false, dataType = "Integer", allowableValues = "1,2"),
		@ApiImplicitParam(name = "initiatorMobile", value = "联系电话", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "initiatorTeamName", value = "球队名称", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "initiatorColor", value = "球队颜色", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "initiatorRemark", value = "发起方备注", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "status", value = "3发起方取消赛事 7删除赛事", paramType = "query", required = false, dataType = "Integer"),
	})
	public String updateCompetition(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		CompetitionDTO competitionDTO = paramHandler.getDTO(CompetitionDTO.class);
		this.competitionWebService.updateCompetition(loginUserId, competitionDTO);
		return null;
	}
}
