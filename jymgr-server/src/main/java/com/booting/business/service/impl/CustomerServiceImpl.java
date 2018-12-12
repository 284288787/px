/** create by auto at 2018-06-25 10:42:18**/
package com.booting.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.business.dto.CustomerDTO;
import com.booting.business.entity.CustomerEntity;
import com.booting.business.service.CustomerService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("customerService")
public class CustomerServiceImpl extends JDBCSupport<CustomerEntity, CustomerDTO> implements CustomerService{

	private static final long serialVersionUID = 1L;

	@Override
	public CustomerDTO save(CustomerEntity customerEntity) {
		long id = this.persist(customerEntity);
		return get(id);
	}

	@Override
	public CustomerDTO update(CustomerEntity customerEntity) {
		this.dynamicMerge(customerEntity);
		return get(customerEntity.getMemberId());
	}

	@Override
	public CustomerDTO updateAll(CustomerEntity customerEntity) {
		this.merge(customerEntity);
		return get(customerEntity.getMemberId());
	}

	@Override
	public CustomerDTO updateBySql(CustomerDTO customerDTO) {
		if(null == customerDTO) return null;
		this.execute("customer.updateCustomer", toMap(customerDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == customerDTO.getMemberId()) return null;
		return get(customerDTO.getMemberId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<CustomerEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("customer.insertCustomer", params);
	}

	@Override
	public void batchUpdate(List<CustomerDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("customer.updateCustomer", params);
	}

	@Override
	public CustomerDTO get(long memberId) {
		return getById(memberId);
	}

	@Override
	public CustomerDTO get(CustomerDTO customerDTO) {
		if(null == customerDTO) {
			return null;
		}
		Map<String, Object> param = toMap(customerDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("customer.getSimpleCustomerList", param);
	}

	@Override
	public List<CustomerDTO> getSimpleList(CustomerDTO customerDTO) {
		Map<String, Object> param = toMap(customerDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("customer.getSimpleCustomerList", param);
	}

	@Override
	public PageList<CustomerDTO> getSimpleListForPage(CustomerDTO customerDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(customerDTO);
		return this.queryForPage("customer.getSimpleCustomerListCount", "customer.getSimpleCustomerList", queryParam);
	}

	@Override
	public PageList<CustomerDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("customer.getSimpleCustomerListCount", "customer.getSimpleCustomerList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("customer.getCustomerList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("customer.getCustomerListCount", "customer.getCustomerList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("customer.getCustomerListCount", "customer.getCustomerList", queryParam, clazz);
	}

}