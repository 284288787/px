/** create by auto at 2018-03-02 10:12:43**/
package com.booting.lottery.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.lottery.dto.GoldDetailDTO;
import com.booting.lottery.entity.GoldDetailEntity;
import com.booting.lottery.service.GoldDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("goldDetailService")
public class GoldDetailServiceImpl extends JDBCSupport<GoldDetailEntity, GoldDetailDTO> implements GoldDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public GoldDetailDTO save(GoldDetailEntity goldDetailEntity) {
		long id = this.persist(goldDetailEntity);
		return get(id);
	}

	@Override
	public GoldDetailDTO update(GoldDetailEntity goldDetailEntity) {
		this.dynamicMerge(goldDetailEntity);
		return get(goldDetailEntity.getId());
	}

	@Override
	public GoldDetailDTO updateAll(GoldDetailEntity goldDetailEntity) {
		this.merge(goldDetailEntity);
		return get(goldDetailEntity.getId());
	}

	@Override
	public GoldDetailDTO updateBySql(GoldDetailDTO goldDetailDTO) {
		if(null == goldDetailDTO) return null;
		this.execute("goldDetail.updateGoldDetail", toMap(goldDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == goldDetailDTO.getId()) return null;
		return get(goldDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<GoldDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("goldDetail.insertGoldDetail", params);
	}

	@Override
	public void batchUpdate(List<GoldDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("goldDetail.updateGoldDetail", params);
	}

	@Override
	public GoldDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public GoldDetailDTO get(GoldDetailDTO goldDetailDTO) {
		if(null == goldDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(goldDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("goldDetail.getSimpleGoldDetailList", param);
	}

	@Override
	public List<GoldDetailDTO> getSimpleList(GoldDetailDTO goldDetailDTO) {
		Map<String, Object> param = toMap(goldDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("goldDetail.getSimpleGoldDetailList", param);
	}

	@Override
	public PageList<GoldDetailDTO> getSimpleListForPage(GoldDetailDTO goldDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(goldDetailDTO);
		return this.queryForPage("goldDetail.getSimpleGoldDetailListCount", "goldDetail.getSimpleGoldDetailList", queryParam);
	}

	@Override
	public PageList<GoldDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("goldDetail.getSimpleGoldDetailListCount", "goldDetail.getSimpleGoldDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("goldDetail.getGoldDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("goldDetail.getGoldDetailListCount", "goldDetail.getGoldDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("goldDetail.getGoldDetailListCount", "goldDetail.getGoldDetailList", queryParam, clazz);
	}

	@Override
	public List<GoldDetailDTO> getGoldDetailList(Map<String, Object> params) {
		return this.queryForList("goldDetail.getSimpleGoldDetailList", params);
	}

}