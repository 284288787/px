/** create by auto at 2018-04-19 11:44:40**/
package com.booting.kindergarten.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.kindergarten.dto.ClassCoachRelationDTO;
import com.booting.kindergarten.entity.ClassCoachRelationEntity;
import com.booting.kindergarten.service.ClassCoachRelationService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("classCoachRelationService")
public class ClassCoachRelationServiceImpl extends JDBCSupport<ClassCoachRelationEntity, ClassCoachRelationDTO> implements ClassCoachRelationService{

	private static final long serialVersionUID = 1L;

	@Override
	public ClassCoachRelationDTO save(ClassCoachRelationEntity classCoachRelationEntity) {
		long id = this.persist(classCoachRelationEntity);
		return get(id);
	}

	@Override
	public ClassCoachRelationDTO update(ClassCoachRelationEntity classCoachRelationEntity) {
		this.dynamicMerge(classCoachRelationEntity);
		return get(classCoachRelationEntity.getId());
	}

	@Override
	public ClassCoachRelationDTO updateAll(ClassCoachRelationEntity classCoachRelationEntity) {
		this.merge(classCoachRelationEntity);
		return get(classCoachRelationEntity.getId());
	}

	@Override
	public ClassCoachRelationDTO updateBySql(ClassCoachRelationDTO classCoachRelationDTO) {
		if(null == classCoachRelationDTO) return null;
		this.execute("classCoachRelation.updateClassCoachRelation", toMap(classCoachRelationDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == classCoachRelationDTO.getId()) return null;
		return get(classCoachRelationDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ClassCoachRelationEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("classCoachRelation.insertClassCoachRelation", params);
	}

	@Override
	public void batchUpdate(List<ClassCoachRelationDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("classCoachRelation.updateClassCoachRelation", params);
	}

	@Override
	public ClassCoachRelationDTO get(long id) {
		return getById(id);
	}

	@Override
	public ClassCoachRelationDTO get(ClassCoachRelationDTO classCoachRelationDTO) {
		if(null == classCoachRelationDTO) {
			return null;
		}
		Map<String, Object> param = toMap(classCoachRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("classCoachRelation.getSimpleClassCoachRelationList", param);
	}

	@Override
	public List<ClassCoachRelationDTO> getSimpleList(ClassCoachRelationDTO classCoachRelationDTO) {
		Map<String, Object> param = toMap(classCoachRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("classCoachRelation.getSimpleClassCoachRelationList", param);
	}

	@Override
	public PageList<ClassCoachRelationDTO> getSimpleListForPage(ClassCoachRelationDTO classCoachRelationDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(classCoachRelationDTO);
		return this.queryForPage("classCoachRelation.getSimpleClassCoachRelationListCount", "classCoachRelation.getSimpleClassCoachRelationList", queryParam);
	}

	@Override
	public PageList<ClassCoachRelationDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("classCoachRelation.getSimpleClassCoachRelationListCount", "classCoachRelation.getSimpleClassCoachRelationList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("classCoachRelation.getClassCoachRelationList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("classCoachRelation.getClassCoachRelationListCount", "classCoachRelation.getClassCoachRelationList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("classCoachRelation.getClassCoachRelationListCount", "classCoachRelation.getClassCoachRelationList", queryParam, clazz);
	}

	@Override
	public void deleteClassCoachRelationByParams(Map<String, Object> params) {
		this.execute("classCoachRelation.deleteClassCoachRelationByParams", params);
	}

}