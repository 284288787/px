/** create by auto at 2018-04-19 11:44:40**/
package com.booting.kindergarten.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.kindergarten.dto.CoachDTO;
import com.booting.kindergarten.entity.CoachEntity;
import com.booting.kindergarten.service.CoachService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("coachService")
public class CoachServiceImpl extends JDBCSupport<CoachEntity, CoachDTO> implements CoachService{

	private static final long serialVersionUID = 1L;

	@Override
	public CoachDTO save(CoachEntity coachEntity) {
		long id = this.persist(coachEntity);
		return get(id);
	}

	@Override
	public CoachDTO update(CoachEntity coachEntity) {
		this.dynamicMerge(coachEntity);
		return get(coachEntity.getCoachId());
	}

	@Override
	public CoachDTO updateAll(CoachEntity coachEntity) {
		this.merge(coachEntity);
		return get(coachEntity.getCoachId());
	}

	@Override
	public CoachDTO updateBySql(CoachDTO coachDTO) {
		if(null == coachDTO) return null;
		this.execute("coach.updateCoach", toMap(coachDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == coachDTO.getCoachId()) return null;
		return get(coachDTO.getCoachId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<CoachEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("coach.insertCoach", params);
	}

	@Override
	public void batchUpdate(List<CoachDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("coach.updateCoach", params);
	}

	@Override
	public CoachDTO get(long coachId) {
		return getById(coachId);
	}

	@Override
	public CoachDTO get(CoachDTO coachDTO) {
		if(null == coachDTO) {
			return null;
		}
		Map<String, Object> param = toMap(coachDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("coach.getSimpleCoachList", param);
	}

	@Override
	public List<CoachDTO> getSimpleList(CoachDTO coachDTO) {
		Map<String, Object> param = toMap(coachDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("coach.getSimpleCoachList", param);
	}

	@Override
	public PageList<CoachDTO> getSimpleListForPage(CoachDTO coachDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(coachDTO);
		return this.queryForPage("coach.getSimpleCoachListCount", "coach.getSimpleCoachList", queryParam);
	}

	@Override
	public PageList<CoachDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("coach.getSimpleCoachListCount", "coach.getSimpleCoachList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("coach.getCoachList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("coach.getCoachListCount", "coach.getCoachList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("coach.getCoachListCount", "coach.getCoachList", queryParam, clazz);
	}

}