/** create by auto at 2018-01-31 14:43:35**/
package com.booting.kindergarten.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.kindergarten.dto.StudentParentRelationDTO;
import com.booting.kindergarten.entity.StudentParentRelationEntity;
import com.booting.kindergarten.service.StudentParentRelationService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("studentParentRelationService")
public class StudentParentRelationServiceImpl extends JDBCSupport<StudentParentRelationEntity, StudentParentRelationDTO> implements StudentParentRelationService{

	private static final long serialVersionUID = 1L;

	@Override
	public StudentParentRelationDTO save(StudentParentRelationEntity studentParentRelationEntity) {
		long id = this.persist(studentParentRelationEntity);
		return get(id);
	}

	@Override
	public StudentParentRelationDTO update(StudentParentRelationEntity studentParentRelationEntity) {
		this.dynamicMerge(studentParentRelationEntity);
		return get(studentParentRelationEntity.getId());
	}

	@Override
	public StudentParentRelationDTO updateAll(StudentParentRelationEntity studentParentRelationEntity) {
		this.merge(studentParentRelationEntity);
		return get(studentParentRelationEntity.getId());
	}

	@Override
	public StudentParentRelationDTO updateBySql(StudentParentRelationDTO studentParentRelationDTO) {
		if(null == studentParentRelationDTO) return null;
		this.execute("studentParentRelation.updateStudentParentRelation", toMap(studentParentRelationDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == studentParentRelationDTO.getId()) return null;
		return get(studentParentRelationDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<StudentParentRelationEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("studentParentRelation.insertStudentParentRelation", params);
	}

	@Override
	public void batchUpdate(List<StudentParentRelationDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("studentParentRelation.updateStudentParentRelation", params);
	}

	@Override
	public StudentParentRelationDTO get(long id) {
		return getById(id);
	}

	@Override
	public StudentParentRelationDTO get(StudentParentRelationDTO studentParentRelationDTO) {
		if(null == studentParentRelationDTO) {
			return null;
		}
		Map<String, Object> param = toMap(studentParentRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("studentParentRelation.getSimpleStudentParentRelationList", param);
	}

	@Override
	public List<StudentParentRelationDTO> getSimpleList(StudentParentRelationDTO studentParentRelationDTO) {
		Map<String, Object> param = toMap(studentParentRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("studentParentRelation.getSimpleStudentParentRelationList", param);
	}

	@Override
	public PageList<StudentParentRelationDTO> getSimpleListForPage(StudentParentRelationDTO studentParentRelationDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(studentParentRelationDTO);
		return this.queryForPage("studentParentRelation.getSimpleStudentParentRelationListCount", "studentParentRelation.getSimpleStudentParentRelationList", queryParam);
	}

	@Override
	public PageList<StudentParentRelationDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("studentParentRelation.getSimpleStudentParentRelationListCount", "studentParentRelation.getSimpleStudentParentRelationList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("studentParentRelation.getStudentParentRelationList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("studentParentRelation.getStudentParentRelationListCount", "studentParentRelation.getStudentParentRelationList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("studentParentRelation.getStudentParentRelationListCount", "studentParentRelation.getStudentParentRelationList", queryParam, clazz);
	}

}