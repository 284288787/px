/** create by auto at 2017-06-23 16:08:02**/
package com.booting.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.team.dto.TeamHabitTimeDTO;
import com.booting.team.entity.TeamHabitTimeEntity;
import com.booting.team.service.TeamHabitTimeService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("teamHabitTimeService")
public class TeamHabitTimeServiceImpl extends JDBCSupport<TeamHabitTimeEntity, TeamHabitTimeDTO> implements TeamHabitTimeService{

	private static final long serialVersionUID = 1L;

	@Override
	public TeamHabitTimeDTO save(TeamHabitTimeEntity teamHabitTimeEntity) {
		long id = this.persist(teamHabitTimeEntity);
		return get(id);
	}

	@Override
	public TeamHabitTimeDTO update(TeamHabitTimeEntity teamHabitTimeEntity) {
		this.dynamicMerge(teamHabitTimeEntity);
		return get(teamHabitTimeEntity.getId());
	}

	@Override
	public TeamHabitTimeDTO updateAll(TeamHabitTimeEntity teamHabitTimeEntity) {
		this.merge(teamHabitTimeEntity);
		return get(teamHabitTimeEntity.getId());
	}

	@Override
	public TeamHabitTimeDTO updateBySql(TeamHabitTimeDTO teamHabitTimeDTO) {
		if(null == teamHabitTimeDTO) return null;
		this.execute("teamHabitTime.updateTeamHabitTime", toMap(teamHabitTimeDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == teamHabitTimeDTO.getId()) return null;
		return get(teamHabitTimeDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<TeamHabitTimeEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("teamHabitTime.insertTeamHabitTime", params);
	}

	@Override
	public void batchUpdate(List<TeamHabitTimeDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("teamHabitTime.updateTeamHabitTime", params);
	}

	@Override
	public TeamHabitTimeDTO get(long id) {
		return getById(id);
	}

	@Override
	public TeamHabitTimeDTO get(TeamHabitTimeDTO teamHabitTimeDTO) {
		if(null == teamHabitTimeDTO) {
			return null;
		}
		Map<String, Object> param = toMap(teamHabitTimeDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("teamHabitTime.getSimpleTeamHabitTimeList", param);
	}

	@Override
	public List<TeamHabitTimeDTO> getSimpleList(TeamHabitTimeDTO teamHabitTimeDTO) {
		Map<String, Object> param = toMap(teamHabitTimeDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("teamHabitTime.getSimpleTeamHabitTimeList", param);
	}

	@Override
	public PageList<TeamHabitTimeDTO> getSimpleListForPage(TeamHabitTimeDTO teamHabitTimeDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(teamHabitTimeDTO);
		return this.queryForPage("teamHabitTime.getSimpleTeamHabitTimeListCount", "teamHabitTime.getSimpleTeamHabitTimeList", queryParam);
	}

	@Override
	public PageList<TeamHabitTimeDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("teamHabitTime.getSimpleTeamHabitTimeListCount", "teamHabitTime.getSimpleTeamHabitTimeList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("teamHabitTime.getTeamHabitTimeList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("teamHabitTime.getTeamHabitTimeListCount", "teamHabitTime.getTeamHabitTimeList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("teamHabitTime.getTeamHabitTimeListCount", "teamHabitTime.getTeamHabitTimeList", queryParam, clazz);
	}

	@Override
	public void deleteByTeamId(Long teamId) {
		Map<String, Object> params = new HashMap<>();
		params.put("teamId", teamId);
		this.execute("teamHabitTime.deleteTeamHabitTime", params);
	}

}