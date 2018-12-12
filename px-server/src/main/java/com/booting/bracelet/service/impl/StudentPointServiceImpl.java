/** create by auto at 2018-09-12 16:14:42**/
package com.booting.bracelet.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.bracelet.dto.StudentPointDTO;
import com.booting.bracelet.entity.StudentPointEntity;
import com.booting.bracelet.service.StudentPointService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("studentPointService")
public class StudentPointServiceImpl extends JDBCSupport<StudentPointEntity, StudentPointDTO> implements StudentPointService{

	private static final long serialVersionUID = 1L;

	@Override
	public StudentPointDTO save(StudentPointEntity studentPointEntity) {
		long id = this.persist(studentPointEntity);
		return get(id);
	}

	@Override
	public StudentPointDTO update(StudentPointEntity studentPointEntity) {
		this.dynamicMerge(studentPointEntity);
		return get(studentPointEntity.getStudentId());
	}

	@Override
	public StudentPointDTO updateAll(StudentPointEntity studentPointEntity) {
		this.merge(studentPointEntity);
		return get(studentPointEntity.getStudentId());
	}

	@Override
	public StudentPointDTO updateBySql(StudentPointDTO studentPointDTO) {
		if(null == studentPointDTO) return null;
		this.execute("studentPoint.updateStudentPoint", toMap(studentPointDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == studentPointDTO.getStudentId()) return null;
		return get(studentPointDTO.getStudentId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<StudentPointEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("studentPoint.insertStudentPoint", params);
	}

	@Override
	public void batchUpdate(List<StudentPointDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("studentPoint.updateStudentPoint", params);
	}

	@Override
	public StudentPointDTO get(long studentId) {
		return getById(studentId);
	}

	@Override
	public StudentPointDTO get(StudentPointDTO studentPointDTO) {
		if(null == studentPointDTO) {
			return null;
		}
		Map<String, Object> param = toMap(studentPointDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("studentPoint.getSimpleStudentPointList", param);
	}

	@Override
	public List<StudentPointDTO> getSimpleList(StudentPointDTO studentPointDTO) {
		Map<String, Object> param = toMap(studentPointDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("studentPoint.getSimpleStudentPointList", param);
	}

	@Override
	public PageList<StudentPointDTO> getSimpleListForPage(StudentPointDTO studentPointDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(studentPointDTO);
		return this.queryForPage("studentPoint.getSimpleStudentPointListCount", "studentPoint.getSimpleStudentPointList", queryParam);
	}

	@Override
	public PageList<StudentPointDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("studentPoint.getSimpleStudentPointListCount", "studentPoint.getSimpleStudentPointList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("studentPoint.getStudentPointList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("studentPoint.getStudentPointListCount", "studentPoint.getStudentPointList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("studentPoint.getStudentPointListCount", "studentPoint.getStudentPointList", queryParam, clazz);
	}

}