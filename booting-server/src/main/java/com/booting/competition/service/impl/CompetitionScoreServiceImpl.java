/** create by auto at 2017-07-15 16:10:54**/
package com.booting.competition.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.competition.dto.CompetitionScoreDTO;
import com.booting.competition.entity.CompetitionScoreEntity;
import com.booting.competition.service.CompetitionScoreService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("competitionScoreService")
public class CompetitionScoreServiceImpl extends JDBCSupport<CompetitionScoreEntity, CompetitionScoreDTO> implements CompetitionScoreService{

	private static final long serialVersionUID = 1L;

	@Override
	public CompetitionScoreDTO save(CompetitionScoreEntity competitionScoreEntity) {
		long id = this.persist(competitionScoreEntity);
		return get(id);
	}

	@Override
	public CompetitionScoreDTO update(CompetitionScoreEntity competitionScoreEntity) {
		this.dynamicMerge(competitionScoreEntity);
		return get(competitionScoreEntity.getId());
	}

	@Override
	public CompetitionScoreDTO updateAll(CompetitionScoreEntity competitionScoreEntity) {
		this.merge(competitionScoreEntity);
		return get(competitionScoreEntity.getId());
	}

	@Override
	public CompetitionScoreDTO updateBySql(CompetitionScoreDTO competitionScoreDTO) {
		if(null == competitionScoreDTO) return null;
		this.execute("competitionScore.updateCompetitionScore", toMap(competitionScoreDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == competitionScoreDTO.getId()) return null;
		return get(competitionScoreDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<CompetitionScoreEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("competitionScore.insertCompetitionScore", params);
	}

	@Override
	public void batchUpdate(List<CompetitionScoreDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("competitionScore.updateCompetitionScore", params);
	}

	@Override
	public CompetitionScoreDTO get(long id) {
		return getById(id);
	}

	@Override
	public CompetitionScoreDTO get(CompetitionScoreDTO competitionScoreDTO) {
		if(null == competitionScoreDTO) {
			return null;
		}
		Map<String, Object> param = toMap(competitionScoreDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("competitionScore.getSimpleCompetitionScoreList", param);
	}

	@Override
	public List<CompetitionScoreDTO> getSimpleList(CompetitionScoreDTO competitionScoreDTO) {
		Map<String, Object> param = toMap(competitionScoreDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("competitionScore.getSimpleCompetitionScoreList", param);
	}

	@Override
	public PageList<CompetitionScoreDTO> getSimpleListForPage(CompetitionScoreDTO competitionScoreDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(competitionScoreDTO);
		return this.queryForPage("competitionScore.getSimpleCompetitionScoreListCount", "competitionScore.getSimpleCompetitionScoreList", queryParam);
	}

	@Override
	public PageList<CompetitionScoreDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("competitionScore.getSimpleCompetitionScoreListCount", "competitionScore.getSimpleCompetitionScoreList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("competitionScore.getCompetitionScoreList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("competitionScore.getCompetitionScoreListCount", "competitionScore.getCompetitionScoreList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("competitionScore.getCompetitionScoreListCount", "competitionScore.getCompetitionScoreList", queryParam, clazz);
	}

	@Override
	public void deleteCompetitionScoreByCompetitionId(Long competitionId) {
		Map<String, Object> params = new HashMap<>();
		params.put("competitionId", competitionId);
		this.execute("competitionScore.deleteCompetitionScore", params);
	}

	@Override
	public void deleteCompetitionScoreByTeamId(Long teamId) {
		Map<String, Object> params = new HashMap<>();
		params.put("teamId", teamId);
		this.execute("competitionScore.deleteCompetitionScore", params);
	}
	
	@Override
	public Integer getScore(Long competitionId, Long teamId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("competitionId", competitionId);
		paramMap.put("teamId", teamId);
		int num = queryForObject("competitionScore.getScore", paramMap, Integer.class);
		return num;
	}
}