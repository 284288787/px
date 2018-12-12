/** create by auto at 2018-01-02 14:44:30**/
package com.booting.kindergarten.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.kindergarten.dto.StudentDTO;
import com.booting.kindergarten.entity.StudentEntity;
import com.booting.kindergarten.service.StudentService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("studentService")
public class StudentServiceImpl extends JDBCSupport<StudentEntity, StudentDTO> implements StudentService{

	private static final long serialVersionUID = 1L;

	@Override
	public StudentDTO save(StudentEntity studentEntity) {
		long id = this.persist(studentEntity);
		return get(id);
	}

	@Override
	public StudentDTO update(StudentEntity studentEntity) {
		this.dynamicMerge(studentEntity);
		return get(studentEntity.getStudentId());
	}

	@Override
	public StudentDTO updateAll(StudentEntity studentEntity) {
		this.merge(studentEntity);
		return get(studentEntity.getStudentId());
	}

	@Override
	public StudentDTO updateBySql(StudentDTO studentDTO) {
		if(null == studentDTO) return null;
		this.execute("student.updateStudent", toMap(studentDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == studentDTO.getStudentId()) return null;
		return get(studentDTO.getStudentId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<StudentEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("student.insertStudent", params);
	}

	@Override
	public void batchUpdate(List<StudentDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("student.updateStudent", params);
	}

	@Override
	public StudentDTO get(long studentId) {
		return getById(studentId);
	}

	@Override
	public StudentDTO get(StudentDTO studentDTO) {
		if(null == studentDTO) {
			return null;
		}
		Map<String, Object> param = toMap(studentDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("student.getSimpleStudentList", param);
	}

	@Override
	public List<StudentDTO> getSimpleList(StudentDTO studentDTO) {
		Map<String, Object> param = toMap(studentDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("student.getSimpleStudentList", param);
	}

	@Override
	public PageList<StudentDTO> getSimpleListForPage(StudentDTO studentDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(studentDTO);
		return this.queryForPage("student.getSimpleStudentListCount", "student.getSimpleStudentList", queryParam);
	}

	@Override
	public PageList<StudentDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("student.getSimpleStudentListCount", "student.getSimpleStudentList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("student.getStudentList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("student.getStudentListCount", "student.getStudentList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("student.getStudentListCount", "student.getStudentList", queryParam, clazz);
	}

}