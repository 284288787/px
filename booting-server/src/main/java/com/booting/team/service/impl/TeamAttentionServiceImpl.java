/** create by auto at 2017-07-11 10:00:35**/
package com.booting.team.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.team.dto.TeamAttentionDTO;
import com.booting.team.entity.TeamAttentionEntity;
import com.booting.team.service.TeamAttentionService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("teamAttentionService")
public class TeamAttentionServiceImpl extends JDBCSupport<TeamAttentionEntity, TeamAttentionDTO> implements TeamAttentionService{

	private static final long serialVersionUID = 1L;

	@Override
	public TeamAttentionDTO save(TeamAttentionEntity teamAttentionEntity) {
		long id = this.persist(teamAttentionEntity);
		return get(id);
	}

	@Override
	public TeamAttentionDTO update(TeamAttentionEntity teamAttentionEntity) {
		this.dynamicMerge(teamAttentionEntity);
		return get(teamAttentionEntity.getId());
	}

	@Override
	public TeamAttentionDTO updateAll(TeamAttentionEntity teamAttentionEntity) {
		this.merge(teamAttentionEntity);
		return get(teamAttentionEntity.getId());
	}

	@Override
	public TeamAttentionDTO updateBySql(TeamAttentionDTO teamAttentionDTO) {
		if(null == teamAttentionDTO) return null;
		this.execute("teamAttention.updateTeamAttention", toMap(teamAttentionDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == teamAttentionDTO.getId()) return null;
		return get(teamAttentionDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<TeamAttentionEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("teamAttention.insertTeamAttention", params);
	}

	@Override
	public void batchUpdate(List<TeamAttentionDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("teamAttention.updateTeamAttention", params);
	}

	@Override
	public TeamAttentionDTO get(long id) {
		return getById(id);
	}

	@Override
	public TeamAttentionDTO get(TeamAttentionDTO teamAttentionDTO) {
		if(null == teamAttentionDTO) {
			return null;
		}
		Map<String, Object> param = toMap(teamAttentionDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("teamAttention.getSimpleTeamAttentionList", param);
	}

	@Override
	public List<TeamAttentionDTO> getSimpleList(TeamAttentionDTO teamAttentionDTO) {
		Map<String, Object> param = toMap(teamAttentionDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("teamAttention.getSimpleTeamAttentionList", param);
	}

	@Override
	public PageList<TeamAttentionDTO> getSimpleListForPage(TeamAttentionDTO teamAttentionDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(teamAttentionDTO);
		return this.queryForPage("teamAttention.getSimpleTeamAttentionListCount", "teamAttention.getSimpleTeamAttentionList", queryParam);
	}

	@Override
	public PageList<TeamAttentionDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("teamAttention.getSimpleTeamAttentionListCount", "teamAttention.getSimpleTeamAttentionList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("teamAttention.getTeamAttentionList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("teamAttention.getTeamAttentionListCount", "teamAttention.getTeamAttentionList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("teamAttention.getTeamAttentionListCount", "teamAttention.getTeamAttentionList", queryParam, clazz);
	}

	@Override
	public Integer getTeamAttentionCount(TeamAttentionDTO teamAttentionDTO) {
		int totalRecord = queryForObject("teamAttention.getSimpleTeamAttentionListCount", teamAttentionDTO, Integer.class);
		return totalRecord;
	}

}