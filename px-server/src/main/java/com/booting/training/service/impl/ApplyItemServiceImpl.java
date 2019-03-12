/** create by auto at 2019-03-12 20:50:38**/
package com.booting.training.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.training.dto.ApplyItemDTO;
import com.booting.training.entity.ApplyItemEntity;
import com.booting.training.service.ApplyItemService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("applyItemService")
public class ApplyItemServiceImpl extends JDBCSupport<ApplyItemEntity, ApplyItemDTO> implements ApplyItemService{

	private static final long serialVersionUID = 1L;

	@Override
	public ApplyItemDTO save(ApplyItemEntity applyItemEntity) {
		long id = this.persist(applyItemEntity);
		return get(id);
	}

	@Override
	public ApplyItemDTO update(ApplyItemEntity applyItemEntity) {
		this.dynamicMerge(applyItemEntity);
		return get(applyItemEntity.getApplyItemId());
	}

	@Override
	public ApplyItemDTO updateAll(ApplyItemEntity applyItemEntity) {
		this.merge(applyItemEntity);
		return get(applyItemEntity.getApplyItemId());
	}

	@Override
	public ApplyItemDTO updateBySql(ApplyItemDTO applyItemDTO) {
		if(null == applyItemDTO) return null;
		this.execute("applyItem.updateApplyItem", toMap(applyItemDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == applyItemDTO.getApplyItemId()) return null;
		return get(applyItemDTO.getApplyItemId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ApplyItemEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("applyItem.insertApplyItem", params);
	}

	@Override
	public void batchUpdate(List<ApplyItemDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("applyItem.updateApplyItem", params);
	}

	@Override
	public ApplyItemDTO get(long applyItemId) {
		return getById(applyItemId);
	}

	@Override
	public ApplyItemDTO get(ApplyItemDTO applyItemDTO) {
		if(null == applyItemDTO) {
			return null;
		}
		Map<String, Object> param = toMap(applyItemDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("applyItem.getSimpleApplyItemList", param);
	}

	@Override
	public List<ApplyItemDTO> getSimpleList(ApplyItemDTO applyItemDTO) {
		Map<String, Object> param = toMap(applyItemDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("applyItem.getSimpleApplyItemList", param);
	}

	@Override
	public PageList<ApplyItemDTO> getSimpleListForPage(ApplyItemDTO applyItemDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(applyItemDTO);
		return this.queryForPage("applyItem.getSimpleApplyItemListCount", "applyItem.getSimpleApplyItemList", queryParam);
	}

	@Override
	public PageList<ApplyItemDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("applyItem.getSimpleApplyItemListCount", "applyItem.getSimpleApplyItemList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("applyItem.getApplyItemList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("applyItem.getApplyItemListCount", "applyItem.getApplyItemList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("applyItem.getApplyItemListCount", "applyItem.getApplyItemList", queryParam, clazz);
	}

}