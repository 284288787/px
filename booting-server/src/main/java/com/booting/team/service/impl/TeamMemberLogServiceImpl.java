/** create by auto at 2017-06-28 09:27:44**/
package com.booting.team.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.team.dto.TeamMemberLogDTO;
import com.booting.team.entity.TeamMemberLogEntity;
import com.booting.team.service.TeamMemberLogService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("teamMemberLogService")
public class TeamMemberLogServiceImpl extends JDBCSupport<TeamMemberLogEntity, TeamMemberLogDTO> implements TeamMemberLogService{

	private static final long serialVersionUID = 1L;

	@Override
	public TeamMemberLogDTO save(TeamMemberLogEntity teamMemberLogEntity) {
		long id = this.persist(teamMemberLogEntity);
		return get(id);
	}

	@Override
	public TeamMemberLogDTO update(TeamMemberLogEntity teamMemberLogEntity) {
		this.dynamicMerge(teamMemberLogEntity);
		return get(teamMemberLogEntity.getId());
	}

	@Override
	public TeamMemberLogDTO updateAll(TeamMemberLogEntity teamMemberLogEntity) {
		this.merge(teamMemberLogEntity);
		return get(teamMemberLogEntity.getId());
	}

	@Override
	public TeamMemberLogDTO updateBySql(TeamMemberLogDTO teamMemberLogDTO) {
		if(null == teamMemberLogDTO) return null;
		this.execute("teamMemberLog.updateTeamMemberLog", toMap(teamMemberLogDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == teamMemberLogDTO.getId()) return null;
		return get(teamMemberLogDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<TeamMemberLogEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("teamMemberLog.insertTeamMemberLog", params);
	}

	@Override
	public void batchUpdate(List<TeamMemberLogDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("teamMemberLog.updateTeamMemberLog", params);
	}

	@Override
	public TeamMemberLogDTO get(long id) {
		return getById(id);
	}

	@Override
	public TeamMemberLogDTO get(TeamMemberLogDTO teamMemberLogDTO) {
		if(null == teamMemberLogDTO) {
			return null;
		}
		Map<String, Object> param = toMap(teamMemberLogDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("teamMemberLog.getSimpleTeamMemberLogList", param);
	}

	@Override
	public List<TeamMemberLogDTO> getSimpleList(TeamMemberLogDTO teamMemberLogDTO) {
		Map<String, Object> param = toMap(teamMemberLogDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("teamMemberLog.getSimpleTeamMemberLogList", param);
	}

	@Override
	public PageList<TeamMemberLogDTO> getSimpleListForPage(TeamMemberLogDTO teamMemberLogDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(teamMemberLogDTO);
		return this.queryForPage("teamMemberLog.getSimpleTeamMemberLogListCount", "teamMemberLog.getSimpleTeamMemberLogList", queryParam);
	}

	@Override
	public PageList<TeamMemberLogDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("teamMemberLog.getSimpleTeamMemberLogListCount", "teamMemberLog.getSimpleTeamMemberLogList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("teamMemberLog.getTeamMemberLogList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("teamMemberLog.getTeamMemberLogListCount", "teamMemberLog.getTeamMemberLogList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("teamMemberLog.getTeamMemberLogListCount", "teamMemberLog.getTeamMemberLogList", queryParam, clazz);
	}

}