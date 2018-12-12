/** create by auto at 2018-01-02 14:44:30**/
package com.booting.kindergarten.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.kindergarten.dto.ClassTeacherRelationDTO;
import com.booting.kindergarten.entity.ClassTeacherRelationEntity;
import com.booting.kindergarten.service.ClassTeacherRelationService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("classTeacherRelationService")
public class ClassTeacherRelationServiceImpl extends JDBCSupport<ClassTeacherRelationEntity, ClassTeacherRelationDTO> implements ClassTeacherRelationService{

	private static final long serialVersionUID = 1L;

	@Override
	public ClassTeacherRelationDTO save(ClassTeacherRelationEntity classTeacherRelationEntity) {
		long id = this.persist(classTeacherRelationEntity);
		return get(id);
	}

	@Override
	public ClassTeacherRelationDTO update(ClassTeacherRelationEntity classTeacherRelationEntity) {
		this.dynamicMerge(classTeacherRelationEntity);
		return get(classTeacherRelationEntity.getId());
	}

	@Override
	public ClassTeacherRelationDTO updateAll(ClassTeacherRelationEntity classTeacherRelationEntity) {
		this.merge(classTeacherRelationEntity);
		return get(classTeacherRelationEntity.getId());
	}

	@Override
	public ClassTeacherRelationDTO updateBySql(ClassTeacherRelationDTO classTeacherRelationDTO) {
		if(null == classTeacherRelationDTO) return null;
		this.execute("classTeacherRelation.updateClassTeacherRelation", toMap(classTeacherRelationDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == classTeacherRelationDTO.getId()) return null;
		return get(classTeacherRelationDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ClassTeacherRelationEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("classTeacherRelation.insertClassTeacherRelation", params);
	}

	@Override
	public void batchUpdate(List<ClassTeacherRelationDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("classTeacherRelation.updateClassTeacherRelation", params);
	}

	@Override
	public ClassTeacherRelationDTO get(long id) {
		return getById(id);
	}

	@Override
	public ClassTeacherRelationDTO get(ClassTeacherRelationDTO classTeacherRelationDTO) {
		if(null == classTeacherRelationDTO) {
			return null;
		}
		Map<String, Object> param = toMap(classTeacherRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("classTeacherRelation.getSimpleClassTeacherRelationList", param);
	}

	@Override
	public List<ClassTeacherRelationDTO> getSimpleList(ClassTeacherRelationDTO classTeacherRelationDTO) {
		Map<String, Object> param = toMap(classTeacherRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("classTeacherRelation.getSimpleClassTeacherRelationList", param);
	}

	@Override
	public PageList<ClassTeacherRelationDTO> getSimpleListForPage(ClassTeacherRelationDTO classTeacherRelationDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(classTeacherRelationDTO);
		return this.queryForPage("classTeacherRelation.getSimpleClassTeacherRelationListCount", "classTeacherRelation.getSimpleClassTeacherRelationList", queryParam);
	}

	@Override
	public PageList<ClassTeacherRelationDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("classTeacherRelation.getSimpleClassTeacherRelationListCount", "classTeacherRelation.getSimpleClassTeacherRelationList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("classTeacherRelation.getClassTeacherRelationList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("classTeacherRelation.getClassTeacherRelationListCount", "classTeacherRelation.getClassTeacherRelationList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("classTeacherRelation.getClassTeacherRelationListCount", "classTeacherRelation.getClassTeacherRelationList", queryParam, clazz);
	}

	@Override
	public void deleteClassTeacherRelationByParams(Map<String, Object> params) {
		this.execute("classTeacherRelation.deleteClassTeacherRelation", params);
	}

}