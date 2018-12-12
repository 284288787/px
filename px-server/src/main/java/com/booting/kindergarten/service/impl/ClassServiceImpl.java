/** create by auto at 2018-01-02 14:44:30**/
package com.booting.kindergarten.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.kindergarten.dto.ClassDTO;
import com.booting.kindergarten.entity.ClassEntity;
import com.booting.kindergarten.service.ClassService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("classService")
public class ClassServiceImpl extends JDBCSupport<ClassEntity, ClassDTO> implements ClassService{

	private static final long serialVersionUID = 1L;

	@Override
	public ClassDTO save(ClassEntity classEntity) {
		long id = this.persist(classEntity);
		return get(id);
	}

	@Override
	public ClassDTO update(ClassEntity classEntity) {
		this.dynamicMerge(classEntity);
		return get(classEntity.getClassId());
	}

	@Override
	public ClassDTO updateAll(ClassEntity classEntity) {
		this.merge(classEntity);
		return get(classEntity.getClassId());
	}

	@Override
	public ClassDTO updateBySql(ClassDTO classDTO) {
		if(null == classDTO) return null;
		this.execute("class.updateClass", toMap(classDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == classDTO.getClassId()) return null;
		return get(classDTO.getClassId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ClassEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("class.insertClass", params);
	}

	@Override
	public void batchUpdate(List<ClassDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("class.updateClass", params);
	}

	@Override
	public ClassDTO get(long classId) {
		return getById(classId);
	}

	@Override
	public ClassDTO get(ClassDTO classDTO) {
		if(null == classDTO) {
			return null;
		}
		Map<String, Object> param = toMap(classDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("class.getSimpleClassList", param);
	}

	@Override
	public List<ClassDTO> getSimpleList(ClassDTO classDTO) {
		Map<String, Object> param = toMap(classDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("class.getSimpleClassList", param);
	}

	@Override
	public PageList<ClassDTO> getSimpleListForPage(ClassDTO classDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(classDTO);
		return this.queryForPage("class.getSimpleClassListCount", "class.getSimpleClassList", queryParam);
	}

	@Override
	public PageList<ClassDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("class.getSimpleClassListCount", "class.getSimpleClassList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("class.getClassList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("class.getClassListCount", "class.getClassList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("class.getClassListCount", "class.getClassList", queryParam, clazz);
	}

}