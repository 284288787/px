/** create by auto at 2017-06-13 14:16:44**/
package com.booting.pub.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pub.dto.AppVersionDTO;
import com.booting.pub.entity.AppVersionEntity;
import com.booting.pub.service.AppVersionService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("appVersionService")
public class AppVersionServiceImpl extends JDBCSupport<AppVersionEntity, AppVersionDTO> implements AppVersionService{

	private static final long serialVersionUID = 1L;

	@Override
	public AppVersionDTO save(AppVersionEntity appVersionEntity) {
		long id = this.persist(appVersionEntity);
		return get(id);
	}

	@Override
	public AppVersionDTO update(AppVersionEntity appVersionEntity) {
		this.dynamicMerge(appVersionEntity);
		return get(appVersionEntity.getId());
	}

	@Override
	public AppVersionDTO updateAll(AppVersionEntity appVersionEntity) {
		this.merge(appVersionEntity);
		return get(appVersionEntity.getId());
	}

	@Override
	public AppVersionDTO updateBySql(AppVersionDTO appVersionDTO) {
		if(null == appVersionDTO) return null;
		this.execute("appVersion.updateAppVersion", toMap(appVersionDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == appVersionDTO.getId()) return null;
		return get(appVersionDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<AppVersionEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("appVersion.insertAppVersion", params);
	}

	@Override
	public void batchUpdate(List<AppVersionDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("appVersion.updateAppVersion", params);
	}

	@Override
	public AppVersionDTO get(long id) {
		return getById(id);
	}

	@Override
	public AppVersionDTO get(AppVersionDTO appVersionDTO) {
		if(null == appVersionDTO) {
			return null;
		}
		Map<String, Object> param = toMap(appVersionDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("appVersion.getSimpleAppVersionList", param);
	}

	@Override
	public List<AppVersionDTO> getSimpleList(AppVersionDTO appVersionDTO) {
		Map<String, Object> param = toMap(appVersionDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("appVersion.getSimpleAppVersionList", param);
	}

	@Override
	public PageList<AppVersionDTO> getSimpleListForPage(AppVersionDTO appVersionDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(appVersionDTO);
		return this.queryForPage("appVersion.getSimpleAppVersionListCount", "appVersion.getSimpleAppVersionList", queryParam);
	}

	@Override
	public PageList<AppVersionDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("appVersion.getSimpleAppVersionListCount", "appVersion.getSimpleAppVersionList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("appVersion.getAppVersionList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("appVersion.getAppVersionListCount", "appVersion.getAppVersionList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("appVersion.getAppVersionListCount", "appVersion.getAppVersionList", queryParam, clazz);
	}

}