/** create by auto at 2018-01-02 14:44:30**/
package com.booting.kindergarten.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.kindergarten.dto.ParentAssessmentDTO;
import com.booting.kindergarten.entity.ParentAssessmentEntity;
import com.booting.kindergarten.service.ParentAssessmentService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("parentAssessmentService")
public class ParentAssessmentServiceImpl extends JDBCSupport<ParentAssessmentEntity, ParentAssessmentDTO> implements ParentAssessmentService{

	private static final long serialVersionUID = 1L;

	@Override
	public ParentAssessmentDTO save(ParentAssessmentEntity parentAssessmentEntity) {
		long id = this.persist(parentAssessmentEntity);
		return get(id);
	}

	@Override
	public ParentAssessmentDTO update(ParentAssessmentEntity parentAssessmentEntity) {
		this.dynamicMerge(parentAssessmentEntity);
		return get(parentAssessmentEntity.getInfoId());
	}

	@Override
	public ParentAssessmentDTO updateAll(ParentAssessmentEntity parentAssessmentEntity) {
		this.merge(parentAssessmentEntity);
		return get(parentAssessmentEntity.getInfoId());
	}

	@Override
	public ParentAssessmentDTO updateBySql(ParentAssessmentDTO parentAssessmentDTO) {
		if(null == parentAssessmentDTO) return null;
		this.execute("parentAssessment.updateParentAssessment", toMap(parentAssessmentDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == parentAssessmentDTO.getInfoId()) return null;
		return get(parentAssessmentDTO.getInfoId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ParentAssessmentEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("parentAssessment.insertParentAssessment", params);
	}

	@Override
	public void batchUpdate(List<ParentAssessmentDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("parentAssessment.updateParentAssessment", params);
	}

	@Override
	public ParentAssessmentDTO get(long infoId) {
		return getById(infoId);
	}

	@Override
	public ParentAssessmentDTO get(ParentAssessmentDTO parentAssessmentDTO) {
		if(null == parentAssessmentDTO) {
			return null;
		}
		Map<String, Object> param = toMap(parentAssessmentDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("parentAssessment.getSimpleParentAssessmentList", param);
	}

	@Override
	public List<ParentAssessmentDTO> getSimpleList(ParentAssessmentDTO parentAssessmentDTO) {
		Map<String, Object> param = toMap(parentAssessmentDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("parentAssessment.getSimpleParentAssessmentList", param);
	}

	@Override
	public PageList<ParentAssessmentDTO> getSimpleListForPage(ParentAssessmentDTO parentAssessmentDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(parentAssessmentDTO);
		return this.queryForPage("parentAssessment.getSimpleParentAssessmentListCount", "parentAssessment.getSimpleParentAssessmentList", queryParam);
	}

	@Override
	public PageList<ParentAssessmentDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("parentAssessment.getSimpleParentAssessmentListCount", "parentAssessment.getSimpleParentAssessmentList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("parentAssessment.getParentAssessmentList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("parentAssessment.getParentAssessmentListCount", "parentAssessment.getParentAssessmentList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("parentAssessment.getParentAssessmentListCount", "parentAssessment.getParentAssessmentList", queryParam, clazz);
	}

}