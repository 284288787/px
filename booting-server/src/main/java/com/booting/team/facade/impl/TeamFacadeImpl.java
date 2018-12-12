/** create by auto at 2017-06-23 16:08:02**/
package com.booting.team.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.team.dto.TeamAttentionDTO;
import com.booting.team.dto.TeamDTO;
import com.booting.team.dto.TeamHabitCourtDTO;
import com.booting.team.dto.TeamHabitNumberDTO;
import com.booting.team.dto.TeamHabitTimeDTO;
import com.booting.team.dto.TeamMemberDTO;
import com.booting.team.dto.TeamMemberLogDTO;
import com.booting.team.entity.TeamAttentionEntity;
import com.booting.team.entity.TeamEntity;
import com.booting.team.entity.TeamHabitCourtEntity;
import com.booting.team.entity.TeamHabitNumberEntity;
import com.booting.team.entity.TeamHabitTimeEntity;
import com.booting.team.entity.TeamMemberEntity;
import com.booting.team.entity.TeamMemberLogEntity;
import com.booting.team.facade.TeamFacade;
import com.booting.team.service.TeamAttentionService;
import com.booting.team.service.TeamHabitCourtService;
import com.booting.team.service.TeamHabitNumberService;
import com.booting.team.service.TeamHabitTimeService;
import com.booting.team.service.TeamMemberLogService;
import com.booting.team.service.TeamMemberService;
import com.booting.team.service.TeamService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.utils.CglibBeanUtils;

@Service("teamFacade")
public class TeamFacadeImpl implements TeamFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private TeamService teamService;

	@Autowired
	private TeamHabitCourtService teamHabitCourtService;

	@Autowired
	private TeamHabitNumberService teamHabitNumberService;

	@Autowired
	private TeamHabitTimeService teamHabitTimeService;

	@Autowired
	private TeamMemberService teamMemberService;

	@Autowired
	private TeamMemberLogService teamMemberLogService;
	
	@Autowired
	private TeamAttentionService teamAttentionService;
	
	@Override
	public Long saveTeam(TeamDTO teamDTO){
		if (null == teamDTO) {
			return null;
		}
		TeamEntity entity = toTeamEntity(teamDTO);
		entity.setEnabled(1);
		entity.setCreateTime(new Date());
		entity.setModifyTime(entity.getCreateTime());
		teamDTO = teamService.save(entity);
		return teamDTO.getTeamId();
	}

	@Override
	public void batchSaveTeam(List<TeamDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<TeamEntity> entities = toTeamEntities(dtos);
		teamService.batchSave(entities);
	}

	@Override
	public int updateTeam(TeamDTO teamDTO){
		teamDTO = teamService.updateBySql(teamDTO);
		return 1;
	}

	@Override
	public void batchUpdateTeam(List<TeamDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		teamService.batchUpdate(dtos);
	}

	@Override
	public int deleteTeam(long teamId){
		return teamService.delete(teamId);
	}

	@Override
	public TeamDTO getTeam(long teamId){
		return teamService.get(teamId);
	}

	@Override
	public TeamDTO getTeam(TeamDTO teamDTO){
		return teamService.get(teamDTO);
	}

	@Override
	public List<TeamDTO> getTeamList(TeamDTO teamDTO){
		return teamService.getSimpleList(teamDTO);
	}

	@Override
	public PageList<TeamDTO> getTeamListForPage(TeamDTO teamDTO, int pageNumber, int pageSize){
		return teamService.getSimpleListForPage(teamDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<TeamDTO> getTeamListForPage(QueryParam queryParam){
		return teamService.getListForPage(queryParam, TeamDTO.class); //getSimpleListForPage(queryParam);
	}

	@Override
	public TeamEntity toTeamEntity(TeamDTO dto){
		TeamEntity entity = new TeamEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<TeamEntity> toTeamEntities(List<TeamDTO> dtos){
		List<TeamEntity> entities = new ArrayList<>();
		for(TeamDTO dto : dtos){
			entities.add(toTeamEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveTeamHabitCourt(TeamHabitCourtDTO teamHabitCourtDTO){
		if (null == teamHabitCourtDTO) {
			return null;
		}
		TeamHabitCourtEntity entity = toTeamHabitCourtEntity(teamHabitCourtDTO);
		teamHabitCourtDTO = teamHabitCourtService.save(entity);
		return teamHabitCourtDTO.getId();
	}

	@Override
	public void batchSaveTeamHabitCourt(List<TeamHabitCourtDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<TeamHabitCourtEntity> entities = toTeamHabitCourtEntities(dtos);
		teamHabitCourtService.batchSave(entities);
	}

	@Override
	public int updateTeamHabitCourt(TeamHabitCourtDTO teamHabitCourtDTO){
		teamHabitCourtDTO = teamHabitCourtService.updateBySql(teamHabitCourtDTO);
		return 1;
	}

	@Override
	public void batchUpdateTeamHabitCourt(List<TeamHabitCourtDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		teamHabitCourtService.batchUpdate(dtos);
	}

	@Override
	public int deleteTeamHabitCourt(long id){
		return teamHabitCourtService.delete(id);
	}

	@Override
	public TeamHabitCourtDTO getTeamHabitCourt(long id){
		return teamHabitCourtService.get(id);
	}

	@Override
	public TeamHabitCourtDTO getTeamHabitCourt(TeamHabitCourtDTO teamHabitCourtDTO){
		return teamHabitCourtService.get(teamHabitCourtDTO);
	}

	@Override
	public List<TeamHabitCourtDTO> getTeamHabitCourtList(TeamHabitCourtDTO teamHabitCourtDTO){
		return teamHabitCourtService.getSimpleList(teamHabitCourtDTO);
	}

	@Override
	public PageList<TeamHabitCourtDTO> getTeamHabitCourtListForPage(TeamHabitCourtDTO teamHabitCourtDTO, int pageNumber, int pageSize){
		return teamHabitCourtService.getSimpleListForPage(teamHabitCourtDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<TeamHabitCourtDTO> getTeamHabitCourtListForPage(QueryParam queryParam){
		return teamHabitCourtService.getSimpleListForPage(queryParam);
	}

	@Override
	public TeamHabitCourtEntity toTeamHabitCourtEntity(TeamHabitCourtDTO dto){
		TeamHabitCourtEntity entity = new TeamHabitCourtEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<TeamHabitCourtEntity> toTeamHabitCourtEntities(List<TeamHabitCourtDTO> dtos){
		List<TeamHabitCourtEntity> entities = new ArrayList<>();
		for(TeamHabitCourtDTO dto : dtos){
			entities.add(toTeamHabitCourtEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveTeamHabitNumber(TeamHabitNumberDTO teamHabitNumberDTO){
		if (null == teamHabitNumberDTO) {
			return null;
		}
		TeamHabitNumberEntity entity = toTeamHabitNumberEntity(teamHabitNumberDTO);
		teamHabitNumberDTO = teamHabitNumberService.save(entity);
		return teamHabitNumberDTO.getId();
	}

	@Override
	public void batchSaveTeamHabitNumber(List<TeamHabitNumberDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<TeamHabitNumberEntity> entities = toTeamHabitNumberEntities(dtos);
		teamHabitNumberService.batchSave(entities);
	}

	@Override
	public int updateTeamHabitNumber(TeamHabitNumberDTO teamHabitNumberDTO){
		teamHabitNumberDTO = teamHabitNumberService.updateBySql(teamHabitNumberDTO);
		return 1;
	}

	@Override
	public void batchUpdateTeamHabitNumber(List<TeamHabitNumberDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		teamHabitNumberService.batchUpdate(dtos);
	}

	@Override
	public int deleteTeamHabitNumber(long id){
		return teamHabitNumberService.delete(id);
	}

	@Override
	public TeamHabitNumberDTO getTeamHabitNumber(long id){
		return teamHabitNumberService.get(id);
	}

	@Override
	public TeamHabitNumberDTO getTeamHabitNumber(TeamHabitNumberDTO teamHabitNumberDTO){
		return teamHabitNumberService.get(teamHabitNumberDTO);
	}

	@Override
	public List<TeamHabitNumberDTO> getTeamHabitNumberList(TeamHabitNumberDTO teamHabitNumberDTO){
		return teamHabitNumberService.getSimpleList(teamHabitNumberDTO);
	}

	@Override
	public PageList<TeamHabitNumberDTO> getTeamHabitNumberListForPage(TeamHabitNumberDTO teamHabitNumberDTO, int pageNumber, int pageSize){
		return teamHabitNumberService.getSimpleListForPage(teamHabitNumberDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<TeamHabitNumberDTO> getTeamHabitNumberListForPage(QueryParam queryParam){
		return teamHabitNumberService.getSimpleListForPage(queryParam);
	}

	@Override
	public TeamHabitNumberEntity toTeamHabitNumberEntity(TeamHabitNumberDTO dto){
		TeamHabitNumberEntity entity = new TeamHabitNumberEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<TeamHabitNumberEntity> toTeamHabitNumberEntities(List<TeamHabitNumberDTO> dtos){
		List<TeamHabitNumberEntity> entities = new ArrayList<>();
		for(TeamHabitNumberDTO dto : dtos){
			entities.add(toTeamHabitNumberEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveTeamHabitTime(TeamHabitTimeDTO teamHabitTimeDTO){
		if (null == teamHabitTimeDTO) {
			return null;
		}
		TeamHabitTimeEntity entity = toTeamHabitTimeEntity(teamHabitTimeDTO);
		teamHabitTimeDTO = teamHabitTimeService.save(entity);
		return teamHabitTimeDTO.getId();
	}

	@Override
	public void batchSaveTeamHabitTime(List<TeamHabitTimeDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<TeamHabitTimeEntity> entities = toTeamHabitTimeEntities(dtos);
		teamHabitTimeService.batchSave(entities);
	}

	@Override
	public int updateTeamHabitTime(TeamHabitTimeDTO teamHabitTimeDTO){
		teamHabitTimeDTO = teamHabitTimeService.updateBySql(teamHabitTimeDTO);
		return 1;
	}

	@Override
	public void batchUpdateTeamHabitTime(List<TeamHabitTimeDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		teamHabitTimeService.batchUpdate(dtos);
	}

	@Override
	public int deleteTeamHabitTime(long id){
		return teamHabitTimeService.delete(id);
	}

	@Override
	public TeamHabitTimeDTO getTeamHabitTime(long id){
		return teamHabitTimeService.get(id);
	}

	@Override
	public TeamHabitTimeDTO getTeamHabitTime(TeamHabitTimeDTO teamHabitTimeDTO){
		return teamHabitTimeService.get(teamHabitTimeDTO);
	}

	@Override
	public List<TeamHabitTimeDTO> getTeamHabitTimeList(TeamHabitTimeDTO teamHabitTimeDTO){
		return teamHabitTimeService.getSimpleList(teamHabitTimeDTO);
	}

	@Override
	public PageList<TeamHabitTimeDTO> getTeamHabitTimeListForPage(TeamHabitTimeDTO teamHabitTimeDTO, int pageNumber, int pageSize){
		return teamHabitTimeService.getSimpleListForPage(teamHabitTimeDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<TeamHabitTimeDTO> getTeamHabitTimeListForPage(QueryParam queryParam){
		return teamHabitTimeService.getSimpleListForPage(queryParam);
	}

	@Override
	public TeamHabitTimeEntity toTeamHabitTimeEntity(TeamHabitTimeDTO dto){
		TeamHabitTimeEntity entity = new TeamHabitTimeEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<TeamHabitTimeEntity> toTeamHabitTimeEntities(List<TeamHabitTimeDTO> dtos){
		List<TeamHabitTimeEntity> entities = new ArrayList<>();
		for(TeamHabitTimeDTO dto : dtos){
			entities.add(toTeamHabitTimeEntity(dto));
		}
		return entities;
	}

	@Override
	public <T> List<T> getList(T dto){
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize){
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz){
		return null;
	}

	@Override
	public void enabledTeam(String teamIds) throws ArgsException {
		if (StringUtils.isBlank(teamIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		TeamDTO teamDTO = new TeamDTO();
		teamDTO.setEnabled(1);
		teamDTO.setTeamIds(teamIds);
		teamService.updateBySql(teamDTO);
	}

	@Override
	public void disabledTeam(String teamIds) throws ArgsException {
		if (StringUtils.isBlank(teamIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		TeamDTO teamDTO = new TeamDTO();
		teamDTO.setEnabled(0);
		teamDTO.setTeamIds(teamIds);
		teamService.updateBySql(teamDTO);
	}

	@Override
	public Long saveTeamMember(TeamMemberDTO teamMemberDTO){
		if (null == teamMemberDTO) {
			return null;
		}
		TeamMemberEntity entity = toTeamMemberEntity(teamMemberDTO);
		teamMemberDTO = teamMemberService.save(entity);
		return teamMemberDTO.getId();
	}

	@Override
	public void batchSaveTeamMember(List<TeamMemberDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<TeamMemberEntity> entities = toTeamMemberEntities(dtos);
		teamMemberService.batchSave(entities);
	}

	@Override
	public int updateTeamMember(TeamMemberDTO teamMemberDTO){
		teamMemberDTO = teamMemberService.updateBySql(teamMemberDTO);
		return 1;
	}

	@Override
	public void batchUpdateTeamMember(List<TeamMemberDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		teamMemberService.batchUpdate(dtos);
	}

	@Override
	public int deleteTeamMember(long id){
		return teamMemberService.delete(id);
	}

	@Override
	public TeamMemberDTO getTeamMember(long id){
		return teamMemberService.get(id);
	}

	@Override
	public TeamMemberDTO getTeamMember(TeamMemberDTO teamMemberDTO){
		return teamMemberService.get(teamMemberDTO);
	}

	@Override
	public List<TeamMemberDTO> getTeamMemberList(TeamMemberDTO teamMemberDTO){
		return teamMemberService.getList(teamMemberDTO);
	}

	@Override
	public PageList<TeamMemberDTO> getTeamMemberListForPage(TeamMemberDTO teamMemberDTO, int pageNumber, int pageSize){
		return teamMemberService.getSimpleListForPage(teamMemberDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<TeamMemberDTO> getTeamMemberListForPage(QueryParam queryParam){
		return teamMemberService.getSimpleListForPage(queryParam);
	}

	@Override
	public TeamMemberEntity toTeamMemberEntity(TeamMemberDTO dto){
		TeamMemberEntity entity = new TeamMemberEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<TeamMemberEntity> toTeamMemberEntities(List<TeamMemberDTO> dtos){
		List<TeamMemberEntity> entities = new ArrayList<>();
		for(TeamMemberDTO dto : dtos){
			entities.add(toTeamMemberEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveTeamMemberLog(TeamMemberLogDTO teamMemberLogDTO){
		if (null == teamMemberLogDTO) {
			return null;
		}
		TeamMemberLogEntity entity = toTeamMemberLogEntity(teamMemberLogDTO);
		teamMemberLogDTO = teamMemberLogService.save(entity);
		return teamMemberLogDTO.getId();
	}

	@Override
	public void batchSaveTeamMemberLog(List<TeamMemberLogDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<TeamMemberLogEntity> entities = toTeamMemberLogEntities(dtos);
		teamMemberLogService.batchSave(entities);
	}

	@Override
	public int updateTeamMemberLog(TeamMemberLogDTO teamMemberLogDTO){
		teamMemberLogDTO = teamMemberLogService.updateBySql(teamMemberLogDTO);
		return 1;
	}

	@Override
	public void batchUpdateTeamMemberLog(List<TeamMemberLogDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		teamMemberLogService.batchUpdate(dtos);
	}

	@Override
	public int deleteTeamMemberLog(long id){
		return teamMemberLogService.delete(id);
	}

	@Override
	public TeamMemberLogDTO getTeamMemberLog(long id){
		return teamMemberLogService.get(id);
	}

	@Override
	public TeamMemberLogDTO getTeamMemberLog(TeamMemberLogDTO teamMemberLogDTO){
		return teamMemberLogService.get(teamMemberLogDTO);
	}

	@Override
	public List<TeamMemberLogDTO> getTeamMemberLogList(TeamMemberLogDTO teamMemberLogDTO){
		return teamMemberLogService.getSimpleList(teamMemberLogDTO);
	}

	@Override
	public PageList<TeamMemberLogDTO> getTeamMemberLogListForPage(TeamMemberLogDTO teamMemberLogDTO, int pageNumber, int pageSize){
		return teamMemberLogService.getSimpleListForPage(teamMemberLogDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<TeamMemberLogDTO> getTeamMemberLogListForPage(QueryParam queryParam){
		return teamMemberLogService.getSimpleListForPage(queryParam);
	}

	@Override
	public TeamMemberLogEntity toTeamMemberLogEntity(TeamMemberLogDTO dto){
		TeamMemberLogEntity entity = new TeamMemberLogEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<TeamMemberLogEntity> toTeamMemberLogEntities(List<TeamMemberLogDTO> dtos){
		List<TeamMemberLogEntity> entities = new ArrayList<>();
		for(TeamMemberLogDTO dto : dtos){
			entities.add(toTeamMemberLogEntity(dto));
		}
		return entities;
	}

	@Override
	public void deleteTeamHabitTimeByTeamId(Long teamId) {
		this.teamHabitTimeService.deleteByTeamId(teamId);
	}

	@Override
	public void deleteTeamHabitCourtByTeamId(Long teamId) {
		this.teamHabitCourtService.deleteByTeamId(teamId);
	}

	@Override
	public void deleteTeamHabitNumberByTeamId(Long teamId) {
		this.teamHabitNumberService.deleteByTeamId(teamId);
	}
	
	@Override
	public Long saveTeamAttention(TeamAttentionDTO teamAttentionDTO){
		if (null == teamAttentionDTO) {
			return null;
		}
		TeamAttentionEntity entity = toTeamAttentionEntity(teamAttentionDTO);
		teamAttentionDTO = teamAttentionService.save(entity);
		return teamAttentionDTO.getId();
	}

	@Override
	public void batchSaveTeamAttention(List<TeamAttentionDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<TeamAttentionEntity> entities = toTeamAttentionEntities(dtos);
		teamAttentionService.batchSave(entities);
	}

	@Override
	public int updateTeamAttention(TeamAttentionDTO teamAttentionDTO){
		teamAttentionDTO = teamAttentionService.updateBySql(teamAttentionDTO);
		return 1;
	}

	@Override
	public void batchUpdateTeamAttention(List<TeamAttentionDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		teamAttentionService.batchUpdate(dtos);
	}

	@Override
	public int deleteTeamAttention(long id){
		return teamAttentionService.delete(id);
	}

	@Override
	public TeamAttentionDTO getTeamAttention(long id){
		return teamAttentionService.get(id);
	}

	@Override
	public TeamAttentionDTO getTeamAttention(TeamAttentionDTO teamAttentionDTO){
		return teamAttentionService.get(teamAttentionDTO);
	}

	@Override
	public List<TeamAttentionDTO> getTeamAttentionList(TeamAttentionDTO teamAttentionDTO){
		return teamAttentionService.getSimpleList(teamAttentionDTO);
	}

	@Override
	public PageList<TeamAttentionDTO> getTeamAttentionListForPage(TeamAttentionDTO teamAttentionDTO, int pageNumber, int pageSize){
		return teamAttentionService.getSimpleListForPage(teamAttentionDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<TeamAttentionDTO> getTeamAttentionListForPage(QueryParam queryParam){
		return teamAttentionService.getSimpleListForPage(queryParam);
	}

	@Override
	public TeamAttentionEntity toTeamAttentionEntity(TeamAttentionDTO dto){
		TeamAttentionEntity entity = new TeamAttentionEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<TeamAttentionEntity> toTeamAttentionEntities(List<TeamAttentionDTO> dtos){
		List<TeamAttentionEntity> entities = new ArrayList<>();
		for(TeamAttentionDTO dto : dtos){
			entities.add(toTeamAttentionEntity(dto));
		}
		return entities;
	}

	@Override
	public Integer getTeamAttentionCount(TeamAttentionDTO teamAttentionDTO) {
		return this.teamAttentionService.getTeamAttentionCount(teamAttentionDTO);
	}

	@Override
	public PageList<TeamDTO> recommendTeams(QueryParam queryParam) {
		return teamService.recommendTeams(queryParam);
	}

	@Override
	public TeamDTO getNewTeam() {
		return teamService.getNewTeam();
	}

	@Override
	public List<TeamDTO> getActiveTeams(int num) {
		return teamService.getActiveTeams(num);
	}
}