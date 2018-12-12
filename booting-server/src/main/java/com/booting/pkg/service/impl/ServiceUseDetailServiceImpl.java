/** create by auto at 2017-08-04 11:36:30**/
package com.booting.pkg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pkg.dto.ServiceUseDetailDTO;
import com.booting.pkg.entity.ServiceUseDetailEntity;
import com.booting.pkg.service.ServiceUseDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("serviceUseDetailService")
public class ServiceUseDetailServiceImpl extends JDBCSupport<ServiceUseDetailEntity, ServiceUseDetailDTO> implements ServiceUseDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public ServiceUseDetailDTO save(ServiceUseDetailEntity serviceUseDetailEntity) {
		long id = this.persist(serviceUseDetailEntity);
		return get(id);
	}

	@Override
	public ServiceUseDetailDTO update(ServiceUseDetailEntity serviceUseDetailEntity) {
		this.dynamicMerge(serviceUseDetailEntity);
		return get(serviceUseDetailEntity.getId());
	}

	@Override
	public ServiceUseDetailDTO updateAll(ServiceUseDetailEntity serviceUseDetailEntity) {
		this.merge(serviceUseDetailEntity);
		return get(serviceUseDetailEntity.getId());
	}

	@Override
	public ServiceUseDetailDTO updateBySql(ServiceUseDetailDTO serviceUseDetailDTO) {
		if(null == serviceUseDetailDTO) return null;
		this.execute("serviceUseDetail.updateServiceUseDetail", toMap(serviceUseDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == serviceUseDetailDTO.getId()) return null;
		return get(serviceUseDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ServiceUseDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("serviceUseDetail.insertServiceUseDetail", params);
	}

	@Override
	public void batchUpdate(List<ServiceUseDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("serviceUseDetail.updateServiceUseDetail", params);
	}

	@Override
	public ServiceUseDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public ServiceUseDetailDTO get(ServiceUseDetailDTO serviceUseDetailDTO) {
		if(null == serviceUseDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(serviceUseDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("serviceUseDetail.getSimpleServiceUseDetailList", param);
	}

	@Override
	public List<ServiceUseDetailDTO> getSimpleList(ServiceUseDetailDTO serviceUseDetailDTO) {
		Map<String, Object> param = toMap(serviceUseDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("serviceUseDetail.getSimpleServiceUseDetailList", param);
	}

	@Override
	public PageList<ServiceUseDetailDTO> getSimpleListForPage(ServiceUseDetailDTO serviceUseDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(serviceUseDetailDTO);
		return this.queryForPage("serviceUseDetail.getSimpleServiceUseDetailListCount", "serviceUseDetail.getSimpleServiceUseDetailList", queryParam);
	}

	@Override
	public PageList<ServiceUseDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("serviceUseDetail.getSimpleServiceUseDetailListCount", "serviceUseDetail.getSimpleServiceUseDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("serviceUseDetail.getServiceUseDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("serviceUseDetail.getServiceUseDetailListCount", "serviceUseDetail.getServiceUseDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("serviceUseDetail.getServiceUseDetailListCount", "serviceUseDetail.getServiceUseDetailList", queryParam, clazz);
	}

}