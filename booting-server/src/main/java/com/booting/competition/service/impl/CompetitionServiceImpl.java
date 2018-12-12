/** create by auto at 2017-07-15 16:10:54**/
package com.booting.competition.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.competition.dto.CompetitionDTO;
import com.booting.competition.entity.CompetitionEntity;
import com.booting.competition.service.CompetitionService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("competitionService")
public class CompetitionServiceImpl extends JDBCSupport<CompetitionEntity, CompetitionDTO> implements CompetitionService{

	private static final long serialVersionUID = 1L;

	@Override
	public CompetitionDTO save(CompetitionEntity competitionEntity) {
		long id = this.persist(competitionEntity);
		return get(id);
	}

	@Override
	public CompetitionDTO update(CompetitionEntity competitionEntity) {
		this.dynamicMerge(competitionEntity);
		return get(competitionEntity.getCompetitionId());
	}

	@Override
	public CompetitionDTO updateAll(CompetitionEntity competitionEntity) {
		this.merge(competitionEntity);
		return get(competitionEntity.getCompetitionId());
	}

	@Override
	public CompetitionDTO updateBySql(CompetitionDTO competitionDTO) {
		if(null == competitionDTO) return null;
		this.execute("competition.updateCompetition", toMap(competitionDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == competitionDTO.getCompetitionId()) return null;
		return get(competitionDTO.getCompetitionId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<CompetitionEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("competition.insertCompetition", params);
	}

	@Override
	public void batchUpdate(List<CompetitionDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("competition.updateCompetition", params);
	}

	@Override
	public CompetitionDTO get(long competitionId) {
		return getById(competitionId);
	}

	@Override
	public CompetitionDTO get(CompetitionDTO competitionDTO) {
		if(null == competitionDTO) {
			return null;
		}
		Map<String, Object> param = toMap(competitionDTO, "yyyy-MM-dd HH:mm:ss");
		List<CompetitionDTO> list = this.queryForList("competition.getSimpleCompetitionList", param);
		if (null != list && list.size() > 0 ) {
			return list.get(0);
		}
		return null;
//		return this.queryForObject("competition.getSimpleCompetitionList", param);
	}

	@Override
	public List<CompetitionDTO> getSimpleList(CompetitionDTO competitionDTO) {
		Map<String, Object> param = toMap(competitionDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("competition.getSimpleCompetitionList", param);
	}

	@Override
	public PageList<CompetitionDTO> getSimpleListForPage(CompetitionDTO competitionDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(competitionDTO);
		return this.queryForPage("competition.getSimpleCompetitionListCount", "competition.getSimpleCompetitionList", queryParam);
	}

	@Override
	public PageList<CompetitionDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("competition.getSimpleCompetitionListCount", "competition.getSimpleCompetitionList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("competition.getCompetitionList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("competition.getCompetitionListCount", "competition.getCompetitionList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("competition.getCompetitionListCount", "competition.getCompetitionList", queryParam, clazz);
	}

	@Override
	public CompetitionDTO getNewCompetition() {
		Map<String, Object> param = new HashMap<String, Object>();
		return this.queryForObject("competition.getNewCompetition", param);
	}

}