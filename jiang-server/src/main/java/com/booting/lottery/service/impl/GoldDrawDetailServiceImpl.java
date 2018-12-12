/** create by auto at 2018-03-02 10:12:43**/
package com.booting.lottery.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.lottery.dto.GoldDrawDetailDTO;
import com.booting.lottery.entity.GoldDrawDetailEntity;
import com.booting.lottery.service.GoldDrawDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("goldDrawDetailService")
public class GoldDrawDetailServiceImpl extends JDBCSupport<GoldDrawDetailEntity, GoldDrawDetailDTO> implements GoldDrawDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public GoldDrawDetailDTO save(GoldDrawDetailEntity goldDrawDetailEntity) {
		long id = this.persist(goldDrawDetailEntity);
		return get(id);
	}

	@Override
	public GoldDrawDetailDTO update(GoldDrawDetailEntity goldDrawDetailEntity) {
		this.dynamicMerge(goldDrawDetailEntity);
		return get(goldDrawDetailEntity.getId());
	}

	@Override
	public GoldDrawDetailDTO updateAll(GoldDrawDetailEntity goldDrawDetailEntity) {
		this.merge(goldDrawDetailEntity);
		return get(goldDrawDetailEntity.getId());
	}

	@Override
	public GoldDrawDetailDTO updateBySql(GoldDrawDetailDTO goldDrawDetailDTO) {
		if(null == goldDrawDetailDTO) return null;
		this.execute("goldDrawDetail.updateGoldDrawDetail", toMap(goldDrawDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == goldDrawDetailDTO.getId()) return null;
		return get(goldDrawDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<GoldDrawDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("goldDrawDetail.insertGoldDrawDetail", params);
	}

	@Override
	public void batchUpdate(List<GoldDrawDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("goldDrawDetail.updateGoldDrawDetail", params);
	}

	@Override
	public GoldDrawDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public GoldDrawDetailDTO get(GoldDrawDetailDTO goldDrawDetailDTO) {
		if(null == goldDrawDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(goldDrawDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("goldDrawDetail.getSimpleGoldDrawDetailList", param);
	}

	@Override
	public List<GoldDrawDetailDTO> getSimpleList(GoldDrawDetailDTO goldDrawDetailDTO) {
		Map<String, Object> param = toMap(goldDrawDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("goldDrawDetail.getSimpleGoldDrawDetailList", param);
	}

	@Override
	public PageList<GoldDrawDetailDTO> getSimpleListForPage(GoldDrawDetailDTO goldDrawDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(goldDrawDetailDTO);
		return this.queryForPage("goldDrawDetail.getSimpleGoldDrawDetailListCount", "goldDrawDetail.getSimpleGoldDrawDetailList", queryParam);
	}

	@Override
	public PageList<GoldDrawDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("goldDrawDetail.getSimpleGoldDrawDetailListCount", "goldDrawDetail.getSimpleGoldDrawDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("goldDrawDetail.getGoldDrawDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("goldDrawDetail.getGoldDrawDetailListCount", "goldDrawDetail.getGoldDrawDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("goldDrawDetail.getGoldDrawDetailListCount", "goldDrawDetail.getGoldDrawDetailList", queryParam, clazz);
	}

	@Override
	public List<GoldDrawDetailDTO> getGoldDrawDetailList(Map<String, Object> params) {
		return this.queryForList("goldDrawDetail.getSimpleGoldDrawDetailList", params);
	}

}