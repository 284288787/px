/** create by auto at 2017-09-15 16:51:59**/
package com.booting.competition.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.competition.dto.InsuranceOrderDTO;
import com.booting.competition.entity.InsuranceOrderEntity;
import com.booting.competition.service.InsuranceOrderService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("insuranceOrderService")
public class InsuranceOrderServiceImpl extends JDBCSupport<InsuranceOrderEntity, InsuranceOrderDTO> implements InsuranceOrderService{

	private static final long serialVersionUID = 1L;

	@Override
	public InsuranceOrderDTO save(InsuranceOrderEntity insuranceOrderEntity) {
		long id = this.persist(insuranceOrderEntity);
		return get(id);
	}

	@Override
	public InsuranceOrderDTO update(InsuranceOrderEntity insuranceOrderEntity) {
		this.dynamicMerge(insuranceOrderEntity);
		return get(insuranceOrderEntity.getIoId());
	}

	@Override
	public InsuranceOrderDTO updateAll(InsuranceOrderEntity insuranceOrderEntity) {
		this.merge(insuranceOrderEntity);
		return get(insuranceOrderEntity.getIoId());
	}

	@Override
	public InsuranceOrderDTO updateBySql(InsuranceOrderDTO insuranceOrderDTO) {
		if(null == insuranceOrderDTO) return null;
		this.execute("insuranceOrder.updateInsuranceOrder", toMap(insuranceOrderDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == insuranceOrderDTO.getIoId()) return null;
		return get(insuranceOrderDTO.getIoId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<InsuranceOrderEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("insuranceOrder.insertInsuranceOrder", params);
	}

	@Override
	public void batchUpdate(List<InsuranceOrderDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("insuranceOrder.updateInsuranceOrder", params);
	}

	@Override
	public InsuranceOrderDTO get(long ioId) {
		return getById(ioId);
	}

	@Override
	public InsuranceOrderDTO get(InsuranceOrderDTO insuranceOrderDTO) {
		if(null == insuranceOrderDTO) {
			return null;
		}
		Map<String, Object> param = toMap(insuranceOrderDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("insuranceOrder.getSimpleInsuranceOrderList", param);
	}

	@Override
	public List<InsuranceOrderDTO> getSimpleList(InsuranceOrderDTO insuranceOrderDTO) {
		Map<String, Object> param = toMap(insuranceOrderDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("insuranceOrder.getSimpleInsuranceOrderList", param);
	}

	@Override
	public PageList<InsuranceOrderDTO> getSimpleListForPage(InsuranceOrderDTO insuranceOrderDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(insuranceOrderDTO);
		return this.queryForPage("insuranceOrder.getSimpleInsuranceOrderListCount", "insuranceOrder.getSimpleInsuranceOrderList", queryParam);
	}

	@Override
	public PageList<InsuranceOrderDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("insuranceOrder.getSimpleInsuranceOrderListCount", "insuranceOrder.getSimpleInsuranceOrderList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("insuranceOrder.getInsuranceOrderList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("insuranceOrder.getInsuranceOrderListCount", "insuranceOrder.getInsuranceOrderList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("insuranceOrder.getInsuranceOrderListCount", "insuranceOrder.getInsuranceOrderList", queryParam, clazz);
	}

}