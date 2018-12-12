/** create by auto at 2017-08-04 11:36:30**/
package com.booting.pkg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pkg.dto.UseServiceDetailDTO;
import com.booting.pkg.entity.UseServiceDetailEntity;
import com.booting.pkg.service.UseServiceDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("useServiceDetailService")
public class UseServiceDetailServiceImpl extends JDBCSupport<UseServiceDetailEntity, UseServiceDetailDTO> implements UseServiceDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public UseServiceDetailDTO save(UseServiceDetailEntity useServiceDetailEntity) {
		long id = this.persist(useServiceDetailEntity);
		return get(id);
	}

	@Override
	public UseServiceDetailDTO update(UseServiceDetailEntity useServiceDetailEntity) {
		this.dynamicMerge(useServiceDetailEntity);
		return get(useServiceDetailEntity.getId());
	}

	@Override
	public UseServiceDetailDTO updateAll(UseServiceDetailEntity useServiceDetailEntity) {
		this.merge(useServiceDetailEntity);
		return get(useServiceDetailEntity.getId());
	}

	@Override
	public UseServiceDetailDTO updateBySql(UseServiceDetailDTO useServiceDetailDTO) {
		if(null == useServiceDetailDTO) return null;
		this.execute("useServiceDetail.updateUseServiceDetail", toMap(useServiceDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == useServiceDetailDTO.getId()) return null;
		return get(useServiceDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<UseServiceDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("useServiceDetail.insertUseServiceDetail", params);
	}

	@Override
	public void batchUpdate(List<UseServiceDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("useServiceDetail.updateUseServiceDetail", params);
	}

	@Override
	public UseServiceDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public UseServiceDetailDTO get(UseServiceDetailDTO useServiceDetailDTO) {
		if(null == useServiceDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(useServiceDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("useServiceDetail.getSimpleUseServiceDetailList", param);
	}

	@Override
	public List<UseServiceDetailDTO> getSimpleList(UseServiceDetailDTO useServiceDetailDTO) {
		Map<String, Object> param = toMap(useServiceDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("useServiceDetail.getSimpleUseServiceDetailList", param);
	}

	@Override
	public PageList<UseServiceDetailDTO> getSimpleListForPage(UseServiceDetailDTO useServiceDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(useServiceDetailDTO);
		return this.queryForPage("useServiceDetail.getSimpleUseServiceDetailListCount", "useServiceDetail.getSimpleUseServiceDetailList", queryParam);
	}

	@Override
	public PageList<UseServiceDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("useServiceDetail.getSimpleUseServiceDetailListCount", "useServiceDetail.getSimpleUseServiceDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("useServiceDetail.getUseServiceDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("useServiceDetail.getUseServiceDetailListCount", "useServiceDetail.getUseServiceDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("useServiceDetail.getUseServiceDetailListCount", "useServiceDetail.getUseServiceDetailList", queryParam, clazz);
	}

	@Override
	public Integer getUseServiceNum(Long userId, Long serviceId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("serviceId", serviceId);
		int num = queryForObject("useServiceDetail.getUseServiceNum", paramMap, Integer.class);
		return num;
	}

	@Override
	public Integer getUsedServiceNumOfTeam(Long teamId, Long serviceId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("teamId", teamId);
		paramMap.put("serviceId", serviceId);
		int num = queryForObject("useServiceDetail.getUseServiceNum", paramMap, Integer.class);
		return num;
	}

}