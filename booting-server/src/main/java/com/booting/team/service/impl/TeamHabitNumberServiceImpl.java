/** create by auto at 2017-06-23 16:08:02**/
package com.booting.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.team.dto.TeamHabitNumberDTO;
import com.booting.team.entity.TeamHabitNumberEntity;
import com.booting.team.service.TeamHabitNumberService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("teamHabitNumberService")
public class TeamHabitNumberServiceImpl extends JDBCSupport<TeamHabitNumberEntity, TeamHabitNumberDTO> implements TeamHabitNumberService{

	private static final long serialVersionUID = 1L;

	@Override
	public TeamHabitNumberDTO save(TeamHabitNumberEntity teamHabitNumberEntity) {
		long id = this.persist(teamHabitNumberEntity);
		return get(id);
	}

	@Override
	public TeamHabitNumberDTO update(TeamHabitNumberEntity teamHabitNumberEntity) {
		this.dynamicMerge(teamHabitNumberEntity);
		return get(teamHabitNumberEntity.getId());
	}

	@Override
	public TeamHabitNumberDTO updateAll(TeamHabitNumberEntity teamHabitNumberEntity) {
		this.merge(teamHabitNumberEntity);
		return get(teamHabitNumberEntity.getId());
	}

	@Override
	public TeamHabitNumberDTO updateBySql(TeamHabitNumberDTO teamHabitNumberDTO) {
		if(null == teamHabitNumberDTO) return null;
		this.execute("teamHabitNumber.updateTeamHabitNumber", toMap(teamHabitNumberDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == teamHabitNumberDTO.getId()) return null;
		return get(teamHabitNumberDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<TeamHabitNumberEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("teamHabitNumber.insertTeamHabitNumber", params);
	}

	@Override
	public void batchUpdate(List<TeamHabitNumberDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("teamHabitNumber.updateTeamHabitNumber", params);
	}

	@Override
	public TeamHabitNumberDTO get(long id) {
		return getById(id);
	}

	@Override
	public TeamHabitNumberDTO get(TeamHabitNumberDTO teamHabitNumberDTO) {
		if(null == teamHabitNumberDTO) {
			return null;
		}
		Map<String, Object> param = toMap(teamHabitNumberDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("teamHabitNumber.getSimpleTeamHabitNumberList", param);
	}

	@Override
	public List<TeamHabitNumberDTO> getSimpleList(TeamHabitNumberDTO teamHabitNumberDTO) {
		Map<String, Object> param = toMap(teamHabitNumberDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("teamHabitNumber.getSimpleTeamHabitNumberList", param);
	}

	@Override
	public PageList<TeamHabitNumberDTO> getSimpleListForPage(TeamHabitNumberDTO teamHabitNumberDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(teamHabitNumberDTO);
		return this.queryForPage("teamHabitNumber.getSimpleTeamHabitNumberListCount", "teamHabitNumber.getSimpleTeamHabitNumberList", queryParam);
	}

	@Override
	public PageList<TeamHabitNumberDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("teamHabitNumber.getSimpleTeamHabitNumberListCount", "teamHabitNumber.getSimpleTeamHabitNumberList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("teamHabitNumber.getTeamHabitNumberList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("teamHabitNumber.getTeamHabitNumberListCount", "teamHabitNumber.getTeamHabitNumberList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("teamHabitNumber.getTeamHabitNumberListCount", "teamHabitNumber.getTeamHabitNumberList", queryParam, clazz);
	}

	@Override
	public void deleteByTeamId(Long teamId) {
		Map<String, Object> params = new HashMap<>();
		params.put("teamId", teamId);
		this.execute("teamHabitNumber.deleteTeamHabitNumber", params);
	}

}