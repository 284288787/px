/** create by auto at 2017-07-15 16:10:54**/
package com.booting.competition.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.competition.dto.CompetitionInsuranceDTO;
import com.booting.competition.entity.CompetitionInsuranceEntity;
import com.booting.competition.service.CompetitionInsuranceService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("competitionInsuranceService")
public class CompetitionInsuranceServiceImpl extends JDBCSupport<CompetitionInsuranceEntity, CompetitionInsuranceDTO> implements CompetitionInsuranceService{

	private static final long serialVersionUID = 1L;

	@Override
	public CompetitionInsuranceDTO save(CompetitionInsuranceEntity competitionInsuranceEntity) {
		long id = this.persist(competitionInsuranceEntity);
		return get(id);
	}

	@Override
	public CompetitionInsuranceDTO update(CompetitionInsuranceEntity competitionInsuranceEntity) {
		this.dynamicMerge(competitionInsuranceEntity);
		return get(competitionInsuranceEntity.getId());
	}

	@Override
	public CompetitionInsuranceDTO updateAll(CompetitionInsuranceEntity competitionInsuranceEntity) {
		this.merge(competitionInsuranceEntity);
		return get(competitionInsuranceEntity.getId());
	}

	@Override
	public CompetitionInsuranceDTO updateBySql(CompetitionInsuranceDTO competitionInsuranceDTO) {
		if(null == competitionInsuranceDTO) return null;
		this.execute("competitionInsurance.updateCompetitionInsurance", toMap(competitionInsuranceDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == competitionInsuranceDTO.getId()) return null;
		return get(competitionInsuranceDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<CompetitionInsuranceEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("competitionInsurance.insertCompetitionInsurance", params);
	}

	@Override
	public void batchUpdate(List<CompetitionInsuranceDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("competitionInsurance.updateCompetitionInsurance", params);
	}

	@Override
	public CompetitionInsuranceDTO get(long id) {
		return getById(id);
	}

	@Override
	public CompetitionInsuranceDTO get(CompetitionInsuranceDTO competitionInsuranceDTO) {
		if(null == competitionInsuranceDTO) {
			return null;
		}
		Map<String, Object> param = toMap(competitionInsuranceDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("competitionInsurance.getSimpleCompetitionInsuranceList", param);
	}

	@Override
	public List<CompetitionInsuranceDTO> getSimpleList(CompetitionInsuranceDTO competitionInsuranceDTO) {
		Map<String, Object> param = toMap(competitionInsuranceDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("competitionInsurance.getSimpleCompetitionInsuranceList", param);
	}

	@Override
	public PageList<CompetitionInsuranceDTO> getSimpleListForPage(CompetitionInsuranceDTO competitionInsuranceDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(competitionInsuranceDTO);
		return this.queryForPage("competitionInsurance.getSimpleCompetitionInsuranceListCount", "competitionInsurance.getSimpleCompetitionInsuranceList", queryParam);
	}

	@Override
	public PageList<CompetitionInsuranceDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("competitionInsurance.getSimpleCompetitionInsuranceListCount", "competitionInsurance.getSimpleCompetitionInsuranceList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("competitionInsurance.getCompetitionInsuranceList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("competitionInsurance.getCompetitionInsuranceListCount", "competitionInsurance.getCompetitionInsuranceList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("competitionInsurance.getCompetitionInsuranceListCount", "competitionInsurance.getCompetitionInsuranceList", queryParam, clazz);
	}

}