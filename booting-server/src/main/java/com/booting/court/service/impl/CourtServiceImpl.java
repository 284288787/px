/** create by auto at 2017-06-23 16:07:39**/
package com.booting.court.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.court.dto.CourtDTO;
import com.booting.court.entity.CourtEntity;
import com.booting.court.service.CourtService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("courtService")
public class CourtServiceImpl extends JDBCSupport<CourtEntity, CourtDTO> implements CourtService{

	private static final long serialVersionUID = 1L;

	@Override
	public CourtDTO save(CourtEntity courtEntity) {
		long id = this.persist(courtEntity);
		return get(id);
	}

	@Override
	public CourtDTO update(CourtEntity courtEntity) {
		this.dynamicMerge(courtEntity);
		return get(courtEntity.getCourtId());
	}

	@Override
	public CourtDTO updateAll(CourtEntity courtEntity) {
		this.merge(courtEntity);
		return get(courtEntity.getCourtId());
	}

	@Override
	public CourtDTO updateBySql(CourtDTO courtDTO) {
		if(null == courtDTO) return null;
		this.execute("court.updateCourt", toMap(courtDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == courtDTO.getCourtId()) return null;
		return get(courtDTO.getCourtId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<CourtEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("court.insertCourt", params);
	}

	@Override
	public void batchUpdate(List<CourtDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("court.updateCourt", params);
	}

	@Override
	public CourtDTO get(long courtId) {
		return getById(courtId);
	}

	@Override
	public CourtDTO get(CourtDTO courtDTO) {
		if(null == courtDTO) {
			return null;
		}
		Map<String, Object> param = toMap(courtDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("court.getSimpleCourtList", param);
	}

	@Override
	public List<CourtDTO> getSimpleList(CourtDTO courtDTO) {
		Map<String, Object> param = toMap(courtDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("court.getSimpleCourtList", param);
	}

	@Override
	public PageList<CourtDTO> getSimpleListForPage(CourtDTO courtDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(courtDTO);
		return this.queryForPage("court.getSimpleCourtListCount", "court.getSimpleCourtList", queryParam);
	}

	@Override
	public PageList<CourtDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("court.getSimpleCourtListCount", "court.getSimpleCourtList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("court.getCourtList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("court.getCourtListCount", "court.getCourtList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("court.getCourtListCount", "court.getCourtList", queryParam, clazz);
	}

	@Override
	public PageList<CourtDTO> usableCourts(QueryParam queryParam) {
		return this.queryForPage("court.usableCourtsCount", "court.usableCourts", queryParam);
	}

}