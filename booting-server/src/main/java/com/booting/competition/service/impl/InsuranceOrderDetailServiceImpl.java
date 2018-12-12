/** create by auto at 2017-09-15 16:51:59**/
package com.booting.competition.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.competition.dto.InsuranceOrderDetailDTO;
import com.booting.competition.entity.InsuranceOrderDetailEntity;
import com.booting.competition.service.InsuranceOrderDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("insuranceOrderDetailService")
public class InsuranceOrderDetailServiceImpl extends JDBCSupport<InsuranceOrderDetailEntity, InsuranceOrderDetailDTO> implements InsuranceOrderDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public InsuranceOrderDetailDTO save(InsuranceOrderDetailEntity insuranceOrderDetailEntity) {
		long id = this.persist(insuranceOrderDetailEntity);
		return get(id);
	}

	@Override
	public InsuranceOrderDetailDTO update(InsuranceOrderDetailEntity insuranceOrderDetailEntity) {
		this.dynamicMerge(insuranceOrderDetailEntity);
		return get(insuranceOrderDetailEntity.getId());
	}

	@Override
	public InsuranceOrderDetailDTO updateAll(InsuranceOrderDetailEntity insuranceOrderDetailEntity) {
		this.merge(insuranceOrderDetailEntity);
		return get(insuranceOrderDetailEntity.getId());
	}

	@Override
	public InsuranceOrderDetailDTO updateBySql(InsuranceOrderDetailDTO insuranceOrderDetailDTO) {
		if(null == insuranceOrderDetailDTO) return null;
		this.execute("insuranceOrderDetail.updateInsuranceOrderDetail", toMap(insuranceOrderDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == insuranceOrderDetailDTO.getId()) return null;
		return get(insuranceOrderDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<InsuranceOrderDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("insuranceOrderDetail.insertInsuranceOrderDetail", params);
	}

	@Override
	public void batchUpdate(List<InsuranceOrderDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("insuranceOrderDetail.updateInsuranceOrderDetail", params);
	}

	@Override
	public InsuranceOrderDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public InsuranceOrderDetailDTO get(InsuranceOrderDetailDTO insuranceOrderDetailDTO) {
		if(null == insuranceOrderDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(insuranceOrderDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("insuranceOrderDetail.getSimpleInsuranceOrderDetailList", param);
	}

	@Override
	public List<InsuranceOrderDetailDTO> getSimpleList(InsuranceOrderDetailDTO insuranceOrderDetailDTO) {
		Map<String, Object> param = toMap(insuranceOrderDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("insuranceOrderDetail.getSimpleInsuranceOrderDetailList", param);
	}

	@Override
	public PageList<InsuranceOrderDetailDTO> getSimpleListForPage(InsuranceOrderDetailDTO insuranceOrderDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(insuranceOrderDetailDTO);
		return this.queryForPage("insuranceOrderDetail.getSimpleInsuranceOrderDetailListCount", "insuranceOrderDetail.getSimpleInsuranceOrderDetailList", queryParam);
	}

	@Override
	public PageList<InsuranceOrderDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("insuranceOrderDetail.getSimpleInsuranceOrderDetailListCount", "insuranceOrderDetail.getSimpleInsuranceOrderDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("insuranceOrderDetail.getInsuranceOrderDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("insuranceOrderDetail.getInsuranceOrderDetailListCount", "insuranceOrderDetail.getInsuranceOrderDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("insuranceOrderDetail.getInsuranceOrderDetailListCount", "insuranceOrderDetail.getInsuranceOrderDetailList", queryParam, clazz);
	}

}