/** create by auto at 2017-06-23 16:08:27**/
package com.booting.pub.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pub.dto.BusinessDTO;
import com.booting.pub.entity.BusinessEntity;
import com.booting.pub.service.BusinessService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("businessService")
public class BusinessServiceImpl extends JDBCSupport<BusinessEntity, BusinessDTO> implements BusinessService{

	private static final long serialVersionUID = 1L;

	@Override
	public BusinessDTO save(BusinessEntity businessEntity) {
		long id = this.persist(businessEntity);
		return get(id);
	}

	@Override
	public BusinessDTO update(BusinessEntity businessEntity) {
		this.dynamicMerge(businessEntity);
		return get(businessEntity.getBusinessId());
	}

	@Override
	public BusinessDTO updateAll(BusinessEntity businessEntity) {
		this.merge(businessEntity);
		return get(businessEntity.getBusinessId());
	}

	@Override
	public BusinessDTO updateBySql(BusinessDTO businessDTO) {
		if(null == businessDTO) return null;
		this.execute("business.updateBusiness", toMap(businessDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == businessDTO.getBusinessId()) return null;
		return get(businessDTO.getBusinessId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<BusinessEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("business.insertBusiness", params);
	}

	@Override
	public void batchUpdate(List<BusinessDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("business.updateBusiness", params);
	}

	@Override
	public BusinessDTO get(long businessId) {
		return getById(businessId);
	}

	@Override
	public BusinessDTO get(BusinessDTO businessDTO) {
		if(null == businessDTO) {
			return null;
		}
		Map<String, Object> param = toMap(businessDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("business.getSimpleBusinessList", param);
	}

	@Override
	public List<BusinessDTO> getSimpleList(BusinessDTO businessDTO) {
		Map<String, Object> param = toMap(businessDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("business.getSimpleBusinessList", param);
	}

	@Override
	public PageList<BusinessDTO> getSimpleListForPage(BusinessDTO businessDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(businessDTO);
		return this.queryForPage("business.getSimpleBusinessListCount", "business.getSimpleBusinessList", queryParam);
	}

	@Override
	public PageList<BusinessDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("business.getSimpleBusinessListCount", "business.getSimpleBusinessList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("business.getBusinessList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("business.getBusinessListCount", "business.getBusinessList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("business.getBusinessListCount", "business.getBusinessList", queryParam, clazz);
	}

}