/** create by auto at 2017-12-19 15:16:14**/
package com.booting.training.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.training.dto.ApplyDetailDTO;
import com.booting.training.entity.ApplyDetailEntity;
import com.booting.training.service.ApplyDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("applyDetailService")
public class ApplyDetailServiceImpl extends JDBCSupport<ApplyDetailEntity, ApplyDetailDTO> implements ApplyDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public ApplyDetailDTO save(ApplyDetailEntity applyDetailEntity) {
		long id = this.persist(applyDetailEntity);
		return get(id);
	}

	@Override
	public ApplyDetailDTO update(ApplyDetailEntity applyDetailEntity) {
		this.dynamicMerge(applyDetailEntity);
		return get(applyDetailEntity.getId());
	}

	@Override
	public ApplyDetailDTO updateAll(ApplyDetailEntity applyDetailEntity) {
		this.merge(applyDetailEntity);
		return get(applyDetailEntity.getId());
	}

	@Override
	public ApplyDetailDTO updateBySql(ApplyDetailDTO applyDetailDTO) {
		if(null == applyDetailDTO) return null;
		this.execute("applyDetail.updateApplyDetail", toMap(applyDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == applyDetailDTO.getId()) return null;
		return get(applyDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ApplyDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("applyDetail.insertApplyDetail", params);
	}

	@Override
	public void batchUpdate(List<ApplyDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("applyDetail.updateApplyDetail", params);
	}

	@Override
	public ApplyDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public ApplyDetailDTO get(ApplyDetailDTO applyDetailDTO) {
		if(null == applyDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(applyDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("applyDetail.getSimpleApplyDetailList", param);
	}

	@Override
	public List<ApplyDetailDTO> getSimpleList(ApplyDetailDTO applyDetailDTO) {
		Map<String, Object> param = toMap(applyDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("applyDetail.getSimpleApplyDetailList", param);
	}

	@Override
	public PageList<ApplyDetailDTO> getSimpleListForPage(ApplyDetailDTO applyDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(applyDetailDTO);
		return this.queryForPage("applyDetail.getSimpleApplyDetailListCount", "applyDetail.getSimpleApplyDetailList", queryParam);
	}

	@Override
	public PageList<ApplyDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("applyDetail.getSimpleApplyDetailListCount", "applyDetail.getSimpleApplyDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("applyDetail.getApplyDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("applyDetail.getApplyDetailListCount", "applyDetail.getApplyDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("applyDetail.getApplyDetailListCount", "applyDetail.getApplyDetailList", queryParam, clazz);
	}

}