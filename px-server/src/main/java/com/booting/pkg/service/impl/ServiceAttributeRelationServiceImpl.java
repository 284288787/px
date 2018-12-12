/** create by auto at 2017-06-02 22:16:20**/
package com.booting.pkg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pkg.dto.ServiceAttributeRelationDTO;
import com.booting.pkg.entity.ServiceAttributeRelationEntity;
import com.booting.pkg.service.ServiceAttributeRelationService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("serviceAttributeRelationService")
public class ServiceAttributeRelationServiceImpl extends JDBCSupport<ServiceAttributeRelationEntity, ServiceAttributeRelationDTO> implements ServiceAttributeRelationService{

	private static final long serialVersionUID = 1L;

	@Override
	public ServiceAttributeRelationDTO save(ServiceAttributeRelationEntity serviceAttributeRelationEntity) {
		long id = this.persist(serviceAttributeRelationEntity);
		return get(id);
	}

	@Override
	public ServiceAttributeRelationDTO update(ServiceAttributeRelationEntity serviceAttributeRelationEntity) {
		this.dynamicMerge(serviceAttributeRelationEntity);
		return get(serviceAttributeRelationEntity.getId());
	}

	@Override
	public ServiceAttributeRelationDTO updateAll(ServiceAttributeRelationEntity serviceAttributeRelationEntity) {
		this.merge(serviceAttributeRelationEntity);
		return get(serviceAttributeRelationEntity.getId());
	}

	@Override
	public ServiceAttributeRelationDTO updateBySql(ServiceAttributeRelationDTO serviceAttributeRelationDTO) {
		if(null == serviceAttributeRelationDTO) return null;
		this.execute("serviceAttributeRelation.updateServiceAttributeRelation", toMap(serviceAttributeRelationDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == serviceAttributeRelationDTO.getId()) return null;
		return get(serviceAttributeRelationDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ServiceAttributeRelationEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("serviceAttributeRelation.insertServiceAttributeRelation", params);
	}

	@Override
	public void batchUpdate(List<ServiceAttributeRelationDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("serviceAttributeRelation.updateServiceAttributeRelation", params);
	}

	@Override
	public ServiceAttributeRelationDTO get(long id) {
		return getById(id);
	}

	@Override
	public ServiceAttributeRelationDTO get(ServiceAttributeRelationDTO serviceAttributeRelationDTO) {
		if(null == serviceAttributeRelationDTO) {
			return null;
		}
		Map<String, Object> param = toMap(serviceAttributeRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("serviceAttributeRelation.getSimpleServiceAttributeRelationList", param);
	}

	@Override
	public List<ServiceAttributeRelationDTO> getSimpleList(ServiceAttributeRelationDTO serviceAttributeRelationDTO) {
		Map<String, Object> param = toMap(serviceAttributeRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("serviceAttributeRelation.getSimpleServiceAttributeRelationList", param);
	}

	@Override
	public PageList<ServiceAttributeRelationDTO> getSimpleListForPage(ServiceAttributeRelationDTO serviceAttributeRelationDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(serviceAttributeRelationDTO);
		return this.queryForPage("serviceAttributeRelation.getSimpleServiceAttributeRelationListCount", "serviceAttributeRelation.getSimpleServiceAttributeRelationList", queryParam);
	}

	@Override
	public PageList<ServiceAttributeRelationDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("serviceAttributeRelation.getSimpleServiceAttributeRelationListCount", "serviceAttributeRelation.getSimpleServiceAttributeRelationList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("serviceAttributeRelation.getServiceAttributeRelationList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("serviceAttributeRelation.getServiceAttributeRelationListCount", "serviceAttributeRelation.getServiceAttributeRelationList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("serviceAttributeRelation.getServiceAttributeRelationListCount", "serviceAttributeRelation.getServiceAttributeRelationList", queryParam, clazz);
	}

	@Override
	public void deleteServiceAttributeRelationBySql(ServiceAttributeRelationDTO dto) {
		this.execute("serviceAttributeRelation.deleteServiceAttributeRelationBySql", toMap(dto, "yyyy-MM-dd HH:mm:ss"));
	}

}