/** create by auto at 2018-06-12 09:48:37**/
package com.booting.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.business.dto.SalesmanDTO;
import com.booting.business.entity.SalesmanEntity;
import com.booting.business.service.SalesmanService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("salesmanService")
public class SalesmanServiceImpl extends JDBCSupport<SalesmanEntity, SalesmanDTO> implements SalesmanService{

	private static final long serialVersionUID = 1L;

	@Override
	public SalesmanDTO save(SalesmanEntity salesmanEntity) {
		long id = this.persist(salesmanEntity);
		return get(id);
	}

	@Override
	public SalesmanDTO update(SalesmanEntity salesmanEntity) {
		this.dynamicMerge(salesmanEntity);
		return get(salesmanEntity.getMemberId());
	}

	@Override
	public SalesmanDTO updateAll(SalesmanEntity salesmanEntity) {
		this.merge(salesmanEntity);
		return get(salesmanEntity.getMemberId());
	}

	@Override
	public SalesmanDTO updateBySql(SalesmanDTO salesmanDTO) {
		if(null == salesmanDTO) return null;
		this.execute("salesman.updateSalesman", toMap(salesmanDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == salesmanDTO.getMemberId()) return null;
		return get(salesmanDTO.getMemberId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<SalesmanEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("salesman.insertSalesman", params);
	}

	@Override
	public void batchUpdate(List<SalesmanDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("salesman.updateSalesman", params);
	}

	@Override
	public SalesmanDTO get(long memberId) {
		return getById(memberId);
	}

	@Override
	public SalesmanDTO get(SalesmanDTO salesmanDTO) {
		if(null == salesmanDTO) {
			return null;
		}
		Map<String, Object> param = toMap(salesmanDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("salesman.getSimpleSalesmanList", param);
	}

	@Override
	public List<SalesmanDTO> getSimpleList(SalesmanDTO salesmanDTO) {
		Map<String, Object> param = toMap(salesmanDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("salesman.getSimpleSalesmanList", param);
	}

	@Override
	public PageList<SalesmanDTO> getSimpleListForPage(SalesmanDTO salesmanDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(salesmanDTO);
		return this.queryForPage("salesman.getSimpleSalesmanListCount", "salesman.getSimpleSalesmanList", queryParam);
	}

	@Override
	public PageList<SalesmanDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("salesman.getSimpleSalesmanListCount", "salesman.getSimpleSalesmanList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("salesman.getSalesmanList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("salesman.getSalesmanListCount", "salesman.getSalesmanList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("salesman.getSalesmanListCount", "salesman.getSalesmanList", queryParam, clazz);
	}

}