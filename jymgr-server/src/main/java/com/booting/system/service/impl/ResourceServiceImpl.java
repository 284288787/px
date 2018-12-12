/** create by auto at 2017-05-24 16:40:27**/
package com.booting.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.system.dto.ResourceDTO;
import com.booting.system.entity.ResourceEntity;
import com.booting.system.service.ResourceService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("resourceService")
public class ResourceServiceImpl extends JDBCSupport<ResourceEntity, ResourceDTO> implements ResourceService{

	private static final long serialVersionUID = 1L;

	@Override
	public ResourceDTO save(ResourceEntity resourceEntity) {
		long id = this.persist(resourceEntity);
		return get(id);
	}

	@Override
	public ResourceDTO update(ResourceEntity resourceEntity) {
		this.dynamicMerge(resourceEntity);
		return get(resourceEntity.getSourceId());
	}

	@Override
	public ResourceDTO updateAll(ResourceEntity resourceEntity) {
		this.merge(resourceEntity);
		return get(resourceEntity.getSourceId());
	}

	@Override
	public ResourceDTO updateBySql(ResourceDTO resourceDTO) {
		if(null == resourceDTO) return null;
		this.execute("resource.updateResource", toMap(resourceDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == resourceDTO.getSourceId()) return null;
		return get(resourceDTO.getSourceId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ResourceEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("resource.insertResource", params);
	}

	@Override
	public void batchUpdate(List<ResourceDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("resource.updateResource", params);
	}

	@Override
	public ResourceDTO get(long sourceId) {
		return getById(sourceId);
	}

	@Override
	public ResourceDTO get(ResourceDTO resourceDTO) {
		if(null == resourceDTO) {
			return null;
		}
		Map<String, Object> param = toMap(resourceDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("resource.getSimpleResourceList", param);
	}

	@Override
	public List<ResourceDTO> getSimpleList(ResourceDTO resourceDTO) {
		Map<String, Object> param = toMap(resourceDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("resource.getSimpleResourceList", param);
	}

	@Override
	public PageList<ResourceDTO> getSimpleListForPage(ResourceDTO resourceDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(resourceDTO);
		return this.queryForPage("resource.getSimpleResourceListCount", "resource.getSimpleResourceList", queryParam);
	}

	@Override
	public PageList<ResourceDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("resource.getSimpleResourceListCount", "resource.getSimpleResourceList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("resource.getResourceList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("resource.getResourceListCount", "resource.getResourceList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("resource.getResourceListCount", "resource.getResourceList", queryParam, clazz);
	}

}