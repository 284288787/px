/**create by liuhua at 2017年11月4日 上午10:25:25**/
package com.booting.management.controller.competition;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booting.common.CommonConstants.UserService;
import com.booting.competition.dto.CompetitionDTO;
import com.booting.competition.facade.CompetitionFacade;
import com.booting.pkg.dto.ServiceDTO;
import com.booting.pkg.dto.UseServiceDetailDTO;
import com.booting.pkg.dto.UserServiceDTO;
import com.booting.pkg.facade.PackageFacade;
import com.booting.service.CompetitionWebService;
import com.booting.team.dto.TeamDTO;
import com.booting.team.facade.TeamFacade;
import com.google.gson.reflect.TypeToken;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.utils.CglibBeanUtils;

@Controller
@RequestMapping("/competition")
public class CompetitionController {
	
	@Autowired
	private CompetitionFacade competitionFacade;
	@Autowired
	private CompetitionWebService competitionWebService;
	@Autowired
	private TeamFacade teamFacade;
	@Autowired
	private PackageFacade packageFacade;
	
	@RequestMapping(value = "/viewCompetition/{competitionId}")
	public String viewCompetition(@PathVariable Long competitionId, Model model) throws ArgsException{
		CompetitionDTO competitionDTO = this.competitionWebService.competitionDetail(competitionId);
		model.addAttribute("competitionDTO", competitionDTO);
		return "management/competition/viewCompetition";
	}
	
	@RequestMapping(value = "/useService/{competitionId}")
	public String useService(@PathVariable Long competitionId, Model model) throws ArgsException{
		CompetitionDTO competitionDTO = this.competitionWebService.competitionDetail(competitionId);
		if (null != competitionDTO.getInitiatorTeamId()) {
			UserServiceDTO iusd = new UserServiceDTO();
			iusd.setTeamId(competitionDTO.getInitiatorTeamId());
			List<UserServiceDTO> initiatorServices = this.packageFacade.getUserServiceList(iusd);
			model.addAttribute("initiatorServices", filterServices(initiatorServices));
		}
		if (null != competitionDTO.getChallengerTeamId()) {
			UserServiceDTO cusd = new UserServiceDTO();
			cusd.setTeamId(competitionDTO.getChallengerTeamId());
			List<UserServiceDTO> challengerServices = this.packageFacade.getUserServiceList(cusd);
			model.addAttribute("challengerServices", filterServices(challengerServices));
		}
		model.addAttribute("competitionDTO", competitionDTO);
		return "management/competition/useService";
	}
	
	@RequestMapping(value = "/useServiceByTeam/{teamId}")
	public String useServiceByTeam(@PathVariable Long teamId, Model model) throws ArgsException{
		TeamDTO teamDTO = this.teamFacade.getTeam(teamId);
		model.addAttribute("team", teamDTO);
		
		UserServiceDTO iusd = new UserServiceDTO();
		iusd.setTeamId(teamId);
		List<UserServiceDTO> initiatorServices = this.packageFacade.getUserServiceList(iusd);
		model.addAttribute("initiatorServices", filterServices(initiatorServices));
		return "management/team/useService";
	}
	
	private List<Map<String, Object>> filterServices(List<UserServiceDTO> services){
		List<Map<String, Object>> list = new ArrayList<>();
		for (UserServiceDTO userServiceDTO : services) {
			if(userServiceDTO.getServiceId().longValue() == UserService.ball_ticket.getServiceId() || 
			   userServiceDTO.getServiceId().longValue() == UserService.coach_ticket.getServiceId() || 
			   userServiceDTO.getServiceId().longValue() == UserService.insurance_ticket.getServiceId()){
				continue;
			}
			Map<String, Object> map = new HashMap<>();
			CglibBeanUtils.addToMap(userServiceDTO, map);
			Integer usedNum = this.packageFacade.getUsedServiceNumOfTeam(userServiceDTO.getTeamId(), userServiceDTO.getServiceId());
			map.put("serviceUsedNum", usedNum);
			list.add(map);
		}
		return list;
	}
	
	private Map<Long, ServiceDTO> services = new HashMap<>();
	private Map<Long, TeamDTO> teams = new HashMap<>();
	
	@ResponseBody
	@RequestMapping(value = "/saveUsedService", method = RequestMethod.POST)
	public ResultMessage saveUsedService(String services){
		Type type = new TypeToken<List<UseServiceDetailDTO>>() {}.getType();
		List<UseServiceDetailDTO> details = ParamHandler.gson.fromJson(services, type);
		for (UseServiceDetailDTO useServiceDetailDTO : details) {
			ServiceDTO service = this.services.get(useServiceDetailDTO.getServiceId());
			if (null == service) {
				service = this.packageFacade.getService(useServiceDetailDTO.getServiceId());
				this.services.put(service.getServiceId(), service);
			}
			TeamDTO team = this.teams.get(useServiceDetailDTO.getTeamId());
			if (null == team) {
				team = this.teamFacade.getTeam(useServiceDetailDTO.getTeamId());
				this.teams.put(team.getTeamId(), team);
			}
			useServiceDetailDTO.setUserId(team.getUserId());
			useServiceDetailDTO.setServiceName(service.getServiceName());
			useServiceDetailDTO.setCreateTime(new Date());
			useServiceDetailDTO.setDescrition(service.getDescription() + "-由后台录入");
		}
		this.packageFacade.batchSaveUseServiceDetail(details);
		ResultMessage resultMessage = new ResultMessage("ok", "");
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/newRecord", method = RequestMethod.POST)
	public ResultMessage newRecord(Integer step){
		if (null == step) {
			step = 10;
		}
		CompetitionDTO competitionDTO = new CompetitionDTO();
		competitionDTO.setStatus(2);
		Calendar calendar = Calendar.getInstance();
		competitionDTO.setEndTime(calendar.getTime());
		calendar.add(Calendar.MINUTE, step);
		competitionDTO.setBeginTime(calendar.getTime());
		List<CompetitionDTO> list = competitionFacade.getCompetitionList(competitionDTO);
		ResultMessage resultMessage = new ResultMessage(list.size(), "");
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(CompetitionDTO competitionDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(competitionDTO);
		PageList<CompetitionDTO> pageList = competitionFacade.getCompetitionListForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
}
