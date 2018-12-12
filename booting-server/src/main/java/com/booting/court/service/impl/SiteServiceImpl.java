/** create by auto at 2017-06-23 16:07:39**/
package com.booting.court.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.court.dto.SiteDTO;
import com.booting.court.entity.SiteEntity;
import com.booting.court.service.SiteService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("siteService")
public class SiteServiceImpl extends JDBCSupport<SiteEntity, SiteDTO> implements SiteService{

	private static final long serialVersionUID = 1L;

	@Override
	public SiteDTO save(SiteEntity siteEntity) {
		long id = this.persist(siteEntity);
		return get(id);
	}

	@Override
	public SiteDTO update(SiteEntity siteEntity) {
		this.dynamicMerge(siteEntity);
		return get(siteEntity.getSiteId());
	}

	@Override
	public SiteDTO updateAll(SiteEntity siteEntity) {
		this.merge(siteEntity);
		return get(siteEntity.getSiteId());
	}

	@Override
	public SiteDTO updateBySql(SiteDTO siteDTO) {
		if(null == siteDTO) return null;
		this.execute("site.updateSite", toMap(siteDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == siteDTO.getSiteId()) return null;
		return get(siteDTO.getSiteId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<SiteEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("site.insertSite", params);
	}

	@Override
	public void batchUpdate(List<SiteDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("site.updateSite", params);
	}

	@Override
	public SiteDTO get(long siteId) {
		return getById(siteId);
	}

	@Override
	public SiteDTO get(SiteDTO siteDTO) {
		if(null == siteDTO) {
			return null;
		}
		Map<String, Object> param = toMap(siteDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("site.getSimpleSiteList", param);
	}

	@Override
	public List<SiteDTO> getSimpleList(SiteDTO siteDTO) {
		Map<String, Object> param = toMap(siteDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("site.getSimpleSiteList", param);
	}

	@Override
	public PageList<SiteDTO> getSimpleListForPage(SiteDTO siteDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(siteDTO);
		return this.queryForPage("site.getSimpleSiteListCount", "site.getSimpleSiteList", queryParam);
	}

	@Override
	public PageList<SiteDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("site.getSimpleSiteListCount", "site.getSimpleSiteList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("site.getSiteList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("site.getSiteListCount", "site.getSiteList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("site.getSiteListCount", "site.getSiteList", queryParam, clazz);
	}

}