/** create by auto at 2017-06-23 16:08:02**/
package com.booting.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.team.dto.TeamDTO;
import com.booting.team.entity.TeamEntity;
import com.booting.team.service.TeamService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("teamService")
public class TeamServiceImpl extends JDBCSupport<TeamEntity, TeamDTO> implements TeamService{

	private static final long serialVersionUID = 1L;

	@Override
	public TeamDTO save(TeamEntity teamEntity) {
		long id = this.persist(teamEntity);
		return get(id);
	}

	@Override
	public TeamDTO update(TeamEntity teamEntity) {
		this.dynamicMerge(teamEntity);
		return get(teamEntity.getTeamId());
	}

	@Override
	public TeamDTO updateAll(TeamEntity teamEntity) {
		this.merge(teamEntity);
		return get(teamEntity.getTeamId());
	}

	@Override
	public TeamDTO updateBySql(TeamDTO teamDTO) {
		if(null == teamDTO) return null;
		this.execute("team.updateTeam", toMap(teamDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == teamDTO.getTeamId()) return null;
		return get(teamDTO.getTeamId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<TeamEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("team.insertTeam", params);
	}

	@Override
	public void batchUpdate(List<TeamDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("team.updateTeam", params);
	}

	@Override
	public TeamDTO get(long teamId) {
		return getById(teamId);
	}

	@Override
	public TeamDTO get(TeamDTO teamDTO) {
		if(null == teamDTO) {
			return null;
		}
		Map<String, Object> param = toMap(teamDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("team.getSimpleTeamList", param);
	}

	@Override
	public List<TeamDTO> getSimpleList(TeamDTO teamDTO) {
		Map<String, Object> param = toMap(teamDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("team.getSimpleTeamList", param);
	}

	@Override
	public PageList<TeamDTO> getSimpleListForPage(TeamDTO teamDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(teamDTO);
		return this.queryForPage("team.getSimpleTeamListCount", "team.getSimpleTeamList", queryParam);
	}

	@Override
	public PageList<TeamDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("team.getSimpleTeamListCount", "team.getSimpleTeamList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("team.getTeamList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("team.getTeamListCount", "team.getTeamList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("team.getTeamListCount", "team.getTeamList", queryParam, clazz);
	}

	@Override
	public PageList<TeamDTO> recommendTeams(QueryParam queryParam) {
		return this.queryForPage("team.recommendTeamsCount", "team.recommendTeams", queryParam);
	}

	@Override
	public TeamDTO getNewTeam() {
		Map<String, Object> param = new HashMap<>();
		return this.queryForObject("team.getNewTeam", param);
	}

	@Override
	public List<TeamDTO> getActiveTeams(int num) {
		Map<String, Object> param = new HashMap<>();
		param.put("num", num);
		return this.queryForList("team.getActiveTeams", param);
	}

}