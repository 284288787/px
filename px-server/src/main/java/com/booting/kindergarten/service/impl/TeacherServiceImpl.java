/** create by auto at 2018-01-02 14:44:30**/
package com.booting.kindergarten.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.kindergarten.dto.TeacherDTO;
import com.booting.kindergarten.entity.TeacherEntity;
import com.booting.kindergarten.service.TeacherService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("teacherService")
public class TeacherServiceImpl extends JDBCSupport<TeacherEntity, TeacherDTO> implements TeacherService{

	private static final long serialVersionUID = 1L;

	@Override
	public TeacherDTO save(TeacherEntity teacherEntity) {
		long id = this.persist(teacherEntity);
		return get(id);
	}

	@Override
	public TeacherDTO update(TeacherEntity teacherEntity) {
		this.dynamicMerge(teacherEntity);
		return get(teacherEntity.getTeacherId());
	}

	@Override
	public TeacherDTO updateAll(TeacherEntity teacherEntity) {
		this.merge(teacherEntity);
		return get(teacherEntity.getTeacherId());
	}

	@Override
	public TeacherDTO updateBySql(TeacherDTO teacherDTO) {
		if(null == teacherDTO) return null;
		this.execute("teacher.updateTeacher", toMap(teacherDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == teacherDTO.getTeacherId()) return null;
		return get(teacherDTO.getTeacherId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<TeacherEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("teacher.insertTeacher", params);
	}

	@Override
	public void batchUpdate(List<TeacherDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("teacher.updateTeacher", params);
	}

	@Override
	public TeacherDTO get(long teacherId) {
		return getById(teacherId);
	}

	@Override
	public TeacherDTO get(TeacherDTO teacherDTO) {
		if(null == teacherDTO) {
			return null;
		}
		Map<String, Object> param = toMap(teacherDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("teacher.getSimpleTeacherList", param);
	}

	@Override
	public List<TeacherDTO> getSimpleList(TeacherDTO teacherDTO) {
		Map<String, Object> param = toMap(teacherDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("teacher.getSimpleTeacherList", param);
	}

	@Override
	public PageList<TeacherDTO> getSimpleListForPage(TeacherDTO teacherDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(teacherDTO);
		return this.queryForPage("teacher.getSimpleTeacherListCount", "teacher.getSimpleTeacherList", queryParam);
	}

	@Override
	public PageList<TeacherDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("teacher.getSimpleTeacherListCount", "teacher.getSimpleTeacherList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("teacher.getTeacherList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("teacher.getTeacherListCount", "teacher.getTeacherList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("teacher.getTeacherListCount", "teacher.getTeacherList", queryParam, clazz);
	}

}