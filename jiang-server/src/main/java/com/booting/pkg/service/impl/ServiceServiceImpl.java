/** create by auto at 2017-06-02 22:16:20**/
package com.booting.pkg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pkg.dto.ServiceDTO;
import com.booting.pkg.entity.ServiceEntity;
import com.booting.pkg.service.ServiceService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("serviceService")
public class ServiceServiceImpl extends JDBCSupport<ServiceEntity, ServiceDTO> implements ServiceService{

	private static final long serialVersionUID = 1L;

	@Override
	public ServiceDTO save(ServiceEntity serviceEntity) {
		long id = this.persist(serviceEntity);
		return get(id);
	}

	@Override
	public ServiceDTO update(ServiceEntity serviceEntity) {
		this.dynamicMerge(serviceEntity);
		return get(serviceEntity.getServiceId());
	}

	@Override
	public ServiceDTO updateAll(ServiceEntity serviceEntity) {
		this.merge(serviceEntity);
		return get(serviceEntity.getServiceId());
	}

	@Override
	public ServiceDTO updateBySql(ServiceDTO serviceDTO) {
		if(null == serviceDTO) return null;
		this.execute("service.updateService", toMap(serviceDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == serviceDTO.getServiceId()) return null;
		return get(serviceDTO.getServiceId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ServiceEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("service.insertService", params);
	}

	@Override
	public void batchUpdate(List<ServiceDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("service.updateService", params);
	}

	@Override
	public ServiceDTO get(long serivceId) {
		return getById(serivceId);
	}

	@Override
	public ServiceDTO get(ServiceDTO serviceDTO) {
		if(null == serviceDTO) {
			return null;
		}
		Map<String, Object> param = toMap(serviceDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("service.getSimpleServiceList", param);
	}

	@Override
	public List<ServiceDTO> getSimpleList(ServiceDTO serviceDTO) {
		Map<String, Object> param = toMap(serviceDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("service.getSimpleServiceList", param);
	}

	@Override
	public PageList<ServiceDTO> getSimpleListForPage(ServiceDTO serviceDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(serviceDTO);
		return this.queryForPage("service.getSimpleServiceListCount", "service.getSimpleServiceList", queryParam);
	}

	@Override
	public PageList<ServiceDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("service.getSimpleServiceListCount", "service.getSimpleServiceList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("service.getServiceList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("service.getServiceListCount", "service.getServiceList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("service.getServiceListCount", "service.getServiceList", queryParam, clazz);
	}

}