/** create by auto at 2018-06-21 14:14:57**/
package com.booting.product.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.product.dto.CurriculumDTO;
import com.booting.product.entity.CurriculumEntity;
import com.booting.product.service.CurriculumService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("curriculumService")
public class CurriculumServiceImpl extends JDBCSupport<CurriculumEntity, CurriculumDTO> implements CurriculumService{

	private static final long serialVersionUID = 1L;

	@Override
	public CurriculumDTO save(CurriculumEntity curriculumEntity) {
		long id = this.persist(curriculumEntity);
		return get(id);
	}

	@Override
	public CurriculumDTO update(CurriculumEntity curriculumEntity) {
		this.dynamicMerge(curriculumEntity);
		return get(curriculumEntity.getCurriculumId());
	}

	@Override
	public CurriculumDTO updateAll(CurriculumEntity curriculumEntity) {
		this.merge(curriculumEntity);
		return get(curriculumEntity.getCurriculumId());
	}

	@Override
	public CurriculumDTO updateBySql(CurriculumDTO curriculumDTO) {
		if(null == curriculumDTO) return null;
		this.execute("curriculum.updateCurriculum", toMap(curriculumDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == curriculumDTO.getCurriculumId()) return null;
		return get(curriculumDTO.getCurriculumId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<CurriculumEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("curriculum.insertCurriculum", params);
	}

	@Override
	public void batchUpdate(List<CurriculumDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("curriculum.updateCurriculum", params);
	}

	@Override
	public CurriculumDTO get(long curriculumId) {
		return getById(curriculumId);
	}

	@Override
	public CurriculumDTO get(CurriculumDTO curriculumDTO) {
		if(null == curriculumDTO) {
			return null;
		}
		Map<String, Object> param = toMap(curriculumDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("curriculum.getSimpleCurriculumList", param);
	}

	@Override
	public List<CurriculumDTO> getSimpleList(CurriculumDTO curriculumDTO) {
		Map<String, Object> param = toMap(curriculumDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("curriculum.getSimpleCurriculumList", param);
	}

	@Override
	public PageList<CurriculumDTO> getSimpleListForPage(CurriculumDTO curriculumDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(curriculumDTO);
		return this.queryForPage("curriculum.getSimpleCurriculumListCount", "curriculum.getSimpleCurriculumList", queryParam);
	}

	@Override
	public PageList<CurriculumDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("curriculum.getSimpleCurriculumListCount", "curriculum.getSimpleCurriculumList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("curriculum.getCurriculumList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("curriculum.getCurriculumListCount", "curriculum.getCurriculumList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("curriculum.getCurriculumListCount", "curriculum.getCurriculumList", queryParam, clazz);
	}

}