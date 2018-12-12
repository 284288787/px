/** create by auto at 2017-12-19 15:16:14**/
package com.booting.training.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.training.dto.ApplyInfoDTO;
import com.booting.training.entity.ApplyInfoEntity;
import com.booting.training.service.ApplyInfoService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("applyInfoService")
public class ApplyInfoServiceImpl extends JDBCSupport<ApplyInfoEntity, ApplyInfoDTO> implements ApplyInfoService{

	private static final long serialVersionUID = 1L;

	@Override
	public ApplyInfoDTO save(ApplyInfoEntity applyInfoEntity) {
		long id = this.persist(applyInfoEntity);
		return get(id);
	}

	@Override
	public ApplyInfoDTO update(ApplyInfoEntity applyInfoEntity) {
		this.dynamicMerge(applyInfoEntity);
		return get(applyInfoEntity.getApplyId());
	}

	@Override
	public ApplyInfoDTO updateAll(ApplyInfoEntity applyInfoEntity) {
		this.merge(applyInfoEntity);
		return get(applyInfoEntity.getApplyId());
	}

	@Override
	public ApplyInfoDTO updateBySql(ApplyInfoDTO applyInfoDTO) {
		if(null == applyInfoDTO) return null;
		this.execute("applyInfo.updateApplyInfo", toMap(applyInfoDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == applyInfoDTO.getApplyId()) return null;
		return get(applyInfoDTO.getApplyId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ApplyInfoEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("applyInfo.insertApplyInfo", params);
	}

	@Override
	public void batchUpdate(List<ApplyInfoDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("applyInfo.updateApplyInfo", params);
	}

	@Override
	public ApplyInfoDTO get(long applyId) {
		return getById(applyId);
	}

	@Override
	public ApplyInfoDTO get(ApplyInfoDTO applyInfoDTO) {
		if(null == applyInfoDTO) {
			return null;
		}
		Map<String, Object> param = toMap(applyInfoDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("applyInfo.getSimpleApplyInfoList", param);
	}

	@Override
	public List<ApplyInfoDTO> getSimpleList(ApplyInfoDTO applyInfoDTO) {
		Map<String, Object> param = toMap(applyInfoDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("applyInfo.getSimpleApplyInfoList", param);
	}

	@Override
	public PageList<ApplyInfoDTO> getSimpleListForPage(ApplyInfoDTO applyInfoDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(applyInfoDTO);
		return this.queryForPage("applyInfo.getSimpleApplyInfoListCount", "applyInfo.getSimpleApplyInfoList", queryParam);
	}

	@Override
	public PageList<ApplyInfoDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("applyInfo.getSimpleApplyInfoListCount", "applyInfo.getSimpleApplyInfoList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("applyInfo.getApplyInfoList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("applyInfo.getApplyInfoListCount", "applyInfo.getApplyInfoList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("applyInfo.getApplyInfoListCount", "applyInfo.getApplyInfoList", queryParam, clazz);
	}

}