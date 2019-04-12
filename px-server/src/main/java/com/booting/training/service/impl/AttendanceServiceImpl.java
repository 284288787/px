/** create by auto at 2019-04-12 14:30:49**/
package com.booting.training.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.training.dto.AttendanceDTO;
import com.booting.training.entity.AttendanceEntity;
import com.booting.training.service.AttendanceService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("attendanceService")
public class AttendanceServiceImpl extends JDBCSupport<AttendanceEntity, AttendanceDTO> implements AttendanceService{

	private static final long serialVersionUID = 1L;

	@Override
	public AttendanceDTO save(AttendanceEntity attendanceEntity) {
		long id = this.persist(attendanceEntity);
		return get(id);
	}

	@Override
	public AttendanceDTO update(AttendanceEntity attendanceEntity) {
		this.dynamicMerge(attendanceEntity);
		return get(attendanceEntity.getId());
	}

	@Override
	public AttendanceDTO updateAll(AttendanceEntity attendanceEntity) {
		this.merge(attendanceEntity);
		return get(attendanceEntity.getId());
	}

	@Override
	public AttendanceDTO updateBySql(AttendanceDTO attendanceDTO) {
		if(null == attendanceDTO) return null;
		this.execute("attendance.updateAttendance", toMap(attendanceDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == attendanceDTO.getId()) return null;
		return get(attendanceDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<AttendanceEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("attendance.insertAttendance", params);
	}

	@Override
	public void batchUpdate(List<AttendanceDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("attendance.updateAttendance", params);
	}

	@Override
	public AttendanceDTO get(long id) {
		return getById(id);
	}

	@Override
	public AttendanceDTO get(AttendanceDTO attendanceDTO) {
		if(null == attendanceDTO) {
			return null;
		}
		Map<String, Object> param = toMap(attendanceDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("attendance.getSimpleAttendanceList", param);
	}

	@Override
	public List<AttendanceDTO> getSimpleList(AttendanceDTO attendanceDTO) {
		Map<String, Object> param = toMap(attendanceDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("attendance.getSimpleAttendanceList", param);
	}

	@Override
	public PageList<AttendanceDTO> getSimpleListForPage(AttendanceDTO attendanceDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(attendanceDTO);
		return this.queryForPage("attendance.getSimpleAttendanceListCount", "attendance.getSimpleAttendanceList", queryParam);
	}

	@Override
	public PageList<AttendanceDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("attendance.getSimpleAttendanceListCount", "attendance.getSimpleAttendanceList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("attendance.getAttendanceList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("attendance.getAttendanceListCount", "attendance.getAttendanceList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("attendance.getAttendanceListCount", "attendance.getAttendanceList", queryParam, clazz);
	}

}