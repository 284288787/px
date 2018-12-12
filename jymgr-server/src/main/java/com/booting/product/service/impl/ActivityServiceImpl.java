/** create by auto at 2018-06-21 14:14:57**/
package com.booting.product.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.product.dto.ActivityDTO;
import com.booting.product.entity.ActivityEntity;
import com.booting.product.service.ActivityService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("activityService")
public class ActivityServiceImpl extends JDBCSupport<ActivityEntity, ActivityDTO> implements ActivityService{

	private static final long serialVersionUID = 1L;

	@Override
	public ActivityDTO save(ActivityEntity activityEntity) {
		long id = this.persist(activityEntity);
		return get(id);
	}

	@Override
	public ActivityDTO update(ActivityEntity activityEntity) {
		this.dynamicMerge(activityEntity);
		return get(activityEntity.getActivityId());
	}

	@Override
	public ActivityDTO updateAll(ActivityEntity activityEntity) {
		this.merge(activityEntity);
		return get(activityEntity.getActivityId());
	}

	@Override
	public ActivityDTO updateBySql(ActivityDTO activityDTO) {
		if(null == activityDTO) return null;
		this.execute("activity.updateActivity", toMap(activityDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == activityDTO.getActivityId()) return null;
		return get(activityDTO.getActivityId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ActivityEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("activity.insertActivity", params);
	}

	@Override
	public void batchUpdate(List<ActivityDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("activity.updateActivity", params);
	}

	@Override
	public ActivityDTO get(long activityId) {
		return getById(activityId);
	}

	@Override
	public ActivityDTO get(ActivityDTO activityDTO) {
		if(null == activityDTO) {
			return null;
		}
		Map<String, Object> param = toMap(activityDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("activity.getSimpleActivityList", param);
	}

	@Override
	public List<ActivityDTO> getSimpleList(ActivityDTO activityDTO) {
		Map<String, Object> param = toMap(activityDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("activity.getSimpleActivityList", param);
	}

	@Override
	public PageList<ActivityDTO> getSimpleListForPage(ActivityDTO activityDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(activityDTO);
		return this.queryForPage("activity.getSimpleActivityListCount", "activity.getSimpleActivityList", queryParam);
	}

	@Override
	public PageList<ActivityDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("activity.getSimpleActivityListCount", "activity.getSimpleActivityList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("activity.getActivityList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("activity.getActivityListCount", "activity.getActivityList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("activity.getActivityListCount", "activity.getActivityList", queryParam, clazz);
	}

}