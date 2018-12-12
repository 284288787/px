/**create by liuhua at 2017年6月27日 下午2:31:18**/
package com.booting.mobile.controller;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.adjunction.dto.PopularizeDTO;
import com.booting.pub.dto.CompanyDTO;
import com.booting.service.SystemWebService;
import com.booting.service.TeamWebService;
import com.booting.system.dto.UserAccountDTO;
import com.booting.team.dto.TeamDTO;
import com.booting.team.dto.TeamHabitCourtDTO;
import com.booting.team.dto.TeamHabitNumberDTO;
import com.booting.team.dto.TeamHabitTimeDTO;
import com.booting.team.dto.TeamMemberDTO;
import com.google.gson.reflect.TypeToken;
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
@Api(value = "球队", description = "球队")
public class TeamApiController extends ApiBaseController{
	
	@Autowired
	private TeamWebService teamWebService;
	@Autowired
	private SystemWebService systemWebService; 
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/textPopularize", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "文化推广券(图文)详情", notes = "文化推广券(图文)详情", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String textPopularize(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long teamId = paramHandler.getLong("teamId");
		PopularizeDTO popularize = this.systemWebService.getPopularize(teamId, 1);
		ApiResult apiResult = new ApiResult(popularize);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/vedioPopularize", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "文化推广券(视频)详情", notes = "文化推广券(视频)详情", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String vedioPopularize(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long teamId = paramHandler.getLong("teamId");
		PopularizeDTO popularize = this.systemWebService.getPopularize(teamId, 2);
		ApiResult apiResult = new ApiResult(popularize);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/team", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "我的球队", notes = "我的球队，只要是球队的管理员或成员就可以得到球队信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String team(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		TeamDTO teamDTO = this.teamWebService.getTeamByLoginUserId(loginUserId);
		ApiResult apiResult = new ApiResult(teamDTO);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/teamInfo", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "teamId", value = "球队ID", paramType = "query", required = true, dataType = "Long"),
	})
	@ApiOperation(value = "根据球队ID获取球队信息", notes = "根据球队ID获取球队信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String teamInfo(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long teamId = paramHandler.getLong("teamId");
		TeamDTO teamDTO = this.teamWebService.getTeamByTeamId(teamId);
		ApiResult apiResult = new ApiResult(teamDTO);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/addTeam", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "teamName", value = "队名", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "logo", value = "队徽", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "areaId", value = "城市ID", paramType = "query", required = true, dataType = "Long")
	})
	@ApiOperation(value = "添加球队", notes = "添加球队，只能有一个球队", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String addTeam(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		TeamDTO teamDTO = paramHandler.getDTO(TeamDTO.class);
		teamDTO.setUserId(loginUserId);
		this.teamWebService.saveTeam(teamDTO);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/updateTeam", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "teamName", value = "队名", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "logo", value = "队徽", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "areaId", value = "城市ID", paramType = "query", required = false, dataType = "Long"),
	})
	@ApiOperation(value = "更新球队", notes = "更新球队", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String updateTeam(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		TeamDTO teamDTO = paramHandler.getDTO(TeamDTO.class);
		this.teamWebService.updateTeam(loginUserId, teamDTO);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/members", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "states", value = "状态 1待审核 2审核通过 3审核不通过, 组合逗号分开", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "成员列表", notes = "成员列表，只要是有所属球队，就可以得到球队的所有成员", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String members(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		String states = paramHandler.getString("states");
		List<Map<String, Object>> members = this.teamWebService.getMembersByLoginUserId(loginUserId, states);
		ApiResult apiResult = new ApiResult(members);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/habitCourt", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "常用球场", notes = "常用球场", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String habitCourt(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		List<TeamHabitCourtDTO> teamHabitCourts = this.teamWebService.getTeamHabitCourts(loginUserId);
		ApiResult apiResult = new ApiResult(teamHabitCourts);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/habitTime", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "常用时间", notes = "常用时间", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String habitTime(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		List<TeamHabitTimeDTO> teamHabitTime = this.teamWebService.getTeamHabitTime(loginUserId);
		ApiResult apiResult = new ApiResult(teamHabitTime);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/habitNumber", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "常用赛制", notes = "常用赛制", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String habitNumber(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		List<TeamHabitNumberDTO> teamHabitNumber = this.teamWebService.getTeamHabitNumber(loginUserId);
		ApiResult apiResult = new ApiResult(teamHabitNumber);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/transferTeam", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "userId", value = "被转让成员用户ID", paramType = "query", required = true, dataType = "Long"),
	})
	@ApiOperation(value = "转让球队", notes = "转让球队，把球队管理员身份转让给球队成员，自己变成普通成员", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String transferTeam(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		Long userId = paramHandler.getLong("userId");
		this.teamWebService.transferTeam(loginUserId, userId);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/setManager", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "userId", value = "被指定为副管理员的成员用户ID", paramType = "query", required = true, dataType = "Long"),
	})
	@ApiOperation(value = "设置副管理员", notes = "设置副管理员，一个球队可以由管理员指定一名球队成员为副管理员", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String setManager(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		Long userId = paramHandler.getLong("userId");
		this.teamWebService.setManager(loginUserId, userId);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/updateMember", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "name", value = "成员姓名", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "userId", value = "成员用户id", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "teamIdentity", value = "球队身份", paramType = "query", required = false, dataType = "Integer", allowableValues = "1,2,3,4"),
		@ApiImplicitParam(name = "poloShirtNo", value = "球衣编号 最大99 不重复", paramType = "query", required = false, dataType = "Integer"),
	})
	@ApiOperation(value = "编辑成员", notes = "编辑成员", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String updateMember(@ApiIgnore String params) throws Exception {
		//如果加的球员 已经注册 直接绑定关系，如果是别的队的成员，绑定失败。没注册 则注册发短信，并绑定关系
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler); //操作人
		Integer teamIdentity = paramHandler.getInteger("teamIdentity");
		UserAccountDTO userInfoDTO = paramHandler.getDTO(UserAccountDTO.class);
		if (null == userInfoDTO.getUserId()) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		this.teamWebService.updateMember(loginUserId, teamIdentity, userInfoDTO);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/addMember", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "name", value = "成员姓名", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "mobile", value = "手机号", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "teamIdentity", value = "球队身份", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2,3,4"),
		@ApiImplicitParam(name = "poloShirtNo", value = "球衣编号 最大99 不重复", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "teamLocation", value = "踢球的位置", paramType = "query", required = false, dataType = "Integer"),
	})
	@ApiOperation(value = "添加成员", notes = "添加成员", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String addMember(@ApiIgnore String params) throws Exception {
		//如果加的球员 已经注册 直接绑定关系，如果是别的队的成员，绑定失败。没注册 则注册发短信，并绑定关系
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler); //操作人
		Long teamId = paramHandler.getLong("teamId");
		Integer teamIdentity = paramHandler.getInteger("teamIdentity");
		UserAccountDTO userInfoDTO = paramHandler.getDTO(UserAccountDTO.class);
		this.teamWebService.addMember(loginUserId, teamId, teamIdentity, userInfoDTO, true);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/applyMember", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "teamIdentity", value = "球队身份 1教练 2队医 3球员 4啦啦队", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2,3,4"),
		@ApiImplicitParam(name = "name", value = "成员姓名", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "poloShirtNo", value = "球衣编号 最大99 不重复", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "teamLocation", value = "踢球的位置", paramType = "query", required = false, dataType = "Integer"),
	})
	@ApiOperation(value = "申请成为球队成员", notes = "申请成为球队成员", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String applyMember(@ApiIgnore String params) throws Exception {
		//如果加的球员 已经注册 直接绑定关系，如果是别的队的成员，绑定失败。没注册 则注册发短信，并绑定关系
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler); //操作人
		Long teamId = paramHandler.getLong("teamId");
		Integer teamIdentity = paramHandler.getInteger("teamIdentity");
		UserAccountDTO userInfoDTO = paramHandler.getDTO(UserAccountDTO.class);
		this.teamWebService.applyMember(loginUserId, teamId, teamIdentity, userInfoDTO);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/auditMember", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "userId", value = "被审核人的用户Id", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "status", value = "2审核通过 3审核不通过", paramType = "query", required = true, dataType = "int", allowableValues = "2,3"),
		@ApiImplicitParam(name = "noPassReason", value = "不通过原因", paramType = "query", required = false, dataType = "String"),
	})
	@ApiOperation(value = "审核申请成为球队的成员", notes = "审核申请成为球队的成员", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String auditMember(@ApiIgnore String params) throws Exception {
		//如果加的球员 已经注册 直接绑定关系，如果是别的队的成员，绑定失败。没注册 则注册发短信，并绑定关系
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler); //操作人
		TeamMemberDTO teamMemberDTO = paramHandler.getDTO(TeamMemberDTO.class);
		this.teamWebService.auditMember(loginUserId, teamMemberDTO);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/delMember", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "userId", value = "成员用户Id", paramType = "query", required = true, dataType = "Long"),
	})
	@ApiOperation(value = "删除成员", notes = "删除成员", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String delMember(@ApiIgnore String params) throws Exception {
		//如果加的球员 已经注册 直接绑定关系，如果是别的队的成员，绑定失败。没注册 则注册发短信，并绑定关系
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler); //操作人
		TeamMemberDTO tmd = paramHandler.getDTO(TeamMemberDTO.class);
		this.teamWebService.delMember(loginUserId, tmd);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/setFreeTime", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "datas", value = "空闲时间数组，元素为下面3个", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "week", value = "星期几", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2,3,4,5,6,7"),
		@ApiImplicitParam(name = "beginMinute", value = "开始时间(分)", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "endMinute", value = "结束时间(分)", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "intro", value = "描述信息", paramType = "query", required = false, dataType = "Integer"),
	})
	@ApiOperation(value = "设置空闲时间", notes = "设置空闲时间，参数有三个token, teamId, datas(数组，包含week, beginMinute, endMinute)", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String setFreeTime(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler); //操作人
		Long teamId = paramHandler.getLong("teamId");
		String datas = paramHandler.getString("datas");
		Type type = new TypeToken<List<TeamHabitTimeDTO>>() {}.getType();
		List<TeamHabitTimeDTO> thtds = ParamHandler.gson.fromJson(datas, type);
		this.teamWebService.setFreeTime(loginUserId, teamId, thtds);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/setCourt", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "courtIds", value = "场地Ids，多个场地用英文逗号分隔，例如：1,2", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "设置场地", notes = "设置场地", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String setCourt(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler); //操作人
		Long teamId = paramHandler.getLong("teamId");
		String courtIds = paramHandler.getString("courtIds");
		this.teamWebService.setCourt(loginUserId, teamId, courtIds);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/setNumber", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "numbers", value = "赛制编号，多个人数用英文逗号分隔，例如：1,2", paramType = "query", required = true, dataType = "String", allowableValues = "1,2"),
	})
	@ApiOperation(value = "设置比赛类型(人数)", notes = "设置比赛类型(人数)，参数应该是一个list，每个list的元素必须包含下面的属性", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String setNumber(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler); //操作人
		Long teamId = paramHandler.getLong("teamId");
		String numbers = paramHandler.getString("numbers");
		this.teamWebService.setNumber(loginUserId, teamId, numbers);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/isAttentionTeam", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = true, dataType = "Long"),
	})
	@ApiOperation(value = "是否关注球队", notes = "是否关注球队", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String isAttentionTeam(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler); //操作人
		Long teamId = paramHandler.getLong("teamId");
		boolean attention = this.teamWebService.isAttentionTeam(loginUserId, teamId);
		ApiResult apiResult = new ApiResult(attention);
		return ParamHandler.gson.toJson(apiResult);
	}

	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/attentionTeam", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = true, dataType = "Long"),
	})
	@ApiOperation(value = "关注球队", notes = "关注球队", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String attentionTeam(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler); //操作人
		Long teamId = paramHandler.getLong("teamId");
		this.teamWebService.attentionTeam(loginUserId, teamId);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/company", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "获取企业信息", notes = "获取企业信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String company(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler); //操作人
		Map<String, Object> company = this.teamWebService.getCompanyByLoginUserId(loginUserId);
		ApiResult apiResult = new ApiResult(company);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/updateCompany", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "companyId", value = "企业Id", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "areaId", value = "城市Id", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "businessId", value = "行业Id", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "invoiceTitle", value = "发票抬头", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "address", value = "地址", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "certificateCode", value = "识别码", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "from", value = "来源 1购买", paramType = "query", required = false, dataType = "int"),
	})
	@ApiOperation(value = "更新企业信息", notes = "更新企业信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String updateCompany(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler); //操作人
		CompanyDTO companyDTO = paramHandler.getDTO(CompanyDTO.class);
		this.teamWebService.updateCompany(loginUserId, companyDTO);
		return null;
	}
}
