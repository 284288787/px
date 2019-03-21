/** create by auto at 2019-03-21 10:42:29**/
package com.booting.training.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.training.dto.KickbackDetailDTO;
import com.booting.training.entity.KickbackDetailEntity;
import com.booting.training.service.KickbackDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("kickbackDetailService")
public class KickbackDetailServiceImpl extends JDBCSupport<KickbackDetailEntity, KickbackDetailDTO> implements KickbackDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public KickbackDetailDTO save(KickbackDetailEntity kickbackDetailEntity) {
		long id = this.persist(kickbackDetailEntity);
		return get(id);
	}

	@Override
	public KickbackDetailDTO update(KickbackDetailEntity kickbackDetailEntity) {
		this.dynamicMerge(kickbackDetailEntity);
		return get(kickbackDetailEntity.getId());
	}

	@Override
	public KickbackDetailDTO updateAll(KickbackDetailEntity kickbackDetailEntity) {
		this.merge(kickbackDetailEntity);
		return get(kickbackDetailEntity.getId());
	}

	@Override
	public KickbackDetailDTO updateBySql(KickbackDetailDTO kickbackDetailDTO) {
		if(null == kickbackDetailDTO) return null;
		this.execute("kickbackDetail.updateKickbackDetail", toMap(kickbackDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == kickbackDetailDTO.getId()) return null;
		return get(kickbackDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<KickbackDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("kickbackDetail.insertKickbackDetail", params);
	}

	@Override
	public void batchUpdate(List<KickbackDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("kickbackDetail.updateKickbackDetail", params);
	}

	@Override
	public KickbackDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public KickbackDetailDTO get(KickbackDetailDTO kickbackDetailDTO) {
		if(null == kickbackDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(kickbackDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("kickbackDetail.getSimpleKickbackDetailList", param);
	}

	@Override
	public List<KickbackDetailDTO> getSimpleList(KickbackDetailDTO kickbackDetailDTO) {
		Map<String, Object> param = toMap(kickbackDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("kickbackDetail.getSimpleKickbackDetailList", param);
	}

	@Override
	public PageList<KickbackDetailDTO> getSimpleListForPage(KickbackDetailDTO kickbackDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(kickbackDetailDTO);
		return this.queryForPage("kickbackDetail.getSimpleKickbackDetailListCount", "kickbackDetail.getSimpleKickbackDetailList", queryParam);
	}

	@Override
	public PageList<KickbackDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("kickbackDetail.getSimpleKickbackDetailListCount", "kickbackDetail.getSimpleKickbackDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("kickbackDetail.getKickbackDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("kickbackDetail.getKickbackDetailListCount", "kickbackDetail.getKickbackDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("kickbackDetail.getKickbackDetailListCount", "kickbackDetail.getKickbackDetailList", queryParam, clazz);
	}

  @Override
  public KickbackDetailDTO getLatestKickbackDetail(Long promoterId) {
    Map<String, Object> param = new HashMap<>();
    param.put("promoterId", promoterId);
    return this.queryForObject("kickbackDetail.getLatestKickbackDetail", param);
  }

}