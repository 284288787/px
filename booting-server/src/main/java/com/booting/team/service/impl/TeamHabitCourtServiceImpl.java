/** create by auto at 2017-06-23 16:08:02**/
package com.booting.team.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.team.dto.TeamHabitCourtDTO;
import com.booting.team.entity.TeamHabitCourtEntity;
import com.booting.team.service.TeamHabitCourtService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("teamHabitCourtService")
public class TeamHabitCourtServiceImpl extends JDBCSupport<TeamHabitCourtEntity, TeamHabitCourtDTO> implements TeamHabitCourtService{

	private static final long serialVersionUID = 1L;

	@Override
	public TeamHabitCourtDTO save(TeamHabitCourtEntity teamHabitCourtEntity) {
		long id = this.persist(teamHabitCourtEntity);
		return get(id);
	}

	@Override
	public TeamHabitCourtDTO update(TeamHabitCourtEntity teamHabitCourtEntity) {
		this.dynamicMerge(teamHabitCourtEntity);
		return get(teamHabitCourtEntity.getId());
	}

	@Override
	public TeamHabitCourtDTO updateAll(TeamHabitCourtEntity teamHabitCourtEntity) {
		this.merge(teamHabitCourtEntity);
		return get(teamHabitCourtEntity.getId());
	}

	@Override
	public TeamHabitCourtDTO updateBySql(TeamHabitCourtDTO teamHabitCourtDTO) {
		if(null == teamHabitCourtDTO) return null;
		this.execute("teamHabitCourt.updateTeamHabitCourt", toMap(teamHabitCourtDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == teamHabitCourtDTO.getId()) return null;
		return get(teamHabitCourtDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<TeamHabitCourtEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("teamHabitCourt.insertTeamHabitCourt", params);
	}

	@Override
	public void batchUpdate(List<TeamHabitCourtDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("teamHabitCourt.updateTeamHabitCourt", params);
	}

	@Override
	public TeamHabitCourtDTO get(long id) {
		return getById(id);
	}

	@Override
	public TeamHabitCourtDTO get(TeamHabitCourtDTO teamHabitCourtDTO) {
		if(null == teamHabitCourtDTO) {
			return null;
		}
		Map<String, Object> param = toMap(teamHabitCourtDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("teamHabitCourt.getSimpleTeamHabitCourtList", param);
	}

	@Override
	public List<TeamHabitCourtDTO> getSimpleList(TeamHabitCourtDTO teamHabitCourtDTO) {
		Map<String, Object> param = toMap(teamHabitCourtDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("teamHabitCourt.getSimpleTeamHabitCourtList", param);
	}

	@Override
	public PageList<TeamHabitCourtDTO> getSimpleListForPage(TeamHabitCourtDTO teamHabitCourtDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(teamHabitCourtDTO);
		return this.queryForPage("teamHabitCourt.getSimpleTeamHabitCourtListCount", "teamHabitCourt.getSimpleTeamHabitCourtList", queryParam);
	}

	@Override
	public PageList<TeamHabitCourtDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("teamHabitCourt.getSimpleTeamHabitCourtListCount", "teamHabitCourt.getSimpleTeamHabitCourtList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("teamHabitCourt.getTeamHabitCourtList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("teamHabitCourt.getTeamHabitCourtListCount", "teamHabitCourt.getTeamHabitCourtList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("teamHabitCourt.getTeamHabitCourtListCount", "teamHabitCourt.getTeamHabitCourtList", queryParam, clazz);
	}

	@Override
	public void deleteByTeamId(Long teamId) {
		Map<String, Object> params = new HashMap<>();
		params.put("teamId", teamId);
		this.execute("teamHabitCourt.deleteTeamHabitCourt", params);
	}

}