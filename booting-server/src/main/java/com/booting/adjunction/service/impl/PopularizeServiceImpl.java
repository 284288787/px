/** create by auto at 2017-10-17 10:14:20**/
package com.booting.adjunction.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.adjunction.dto.PopularizeDTO;
import com.booting.adjunction.entity.PopularizeEntity;
import com.booting.adjunction.service.PopularizeService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("popularizeService")
public class PopularizeServiceImpl extends JDBCSupport<PopularizeEntity, PopularizeDTO> implements PopularizeService{

	private static final long serialVersionUID = 1L;

	@Override
	public PopularizeDTO save(PopularizeEntity popularizeEntity) {
		long id = this.persist(popularizeEntity);
		return get(id);
	}

	@Override
	public PopularizeDTO update(PopularizeEntity popularizeEntity) {
		this.dynamicMerge(popularizeEntity);
		return get(popularizeEntity.getId());
	}

	@Override
	public PopularizeDTO updateAll(PopularizeEntity popularizeEntity) {
		this.merge(popularizeEntity);
		return get(popularizeEntity.getId());
	}

	@Override
	public PopularizeDTO updateBySql(PopularizeDTO popularizeDTO) {
		if(null == popularizeDTO) return null;
		this.execute("popularize.updatePopularize", toMap(popularizeDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == popularizeDTO.getId()) return null;
		return get(popularizeDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<PopularizeEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("popularize.insertPopularize", params);
	}

	@Override
	public void batchUpdate(List<PopularizeDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("popularize.updatePopularize", params);
	}

	@Override
	public PopularizeDTO get(long id) {
		return getById(id);
	}

	@Override
	public PopularizeDTO get(PopularizeDTO popularizeDTO) {
		if(null == popularizeDTO) {
			return null;
		}
		Map<String, Object> param = toMap(popularizeDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("popularize.getSimplePopularizeList", param);
	}

	@Override
	public List<PopularizeDTO> getSimpleList(PopularizeDTO popularizeDTO) {
		Map<String, Object> param = toMap(popularizeDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("popularize.getSimplePopularizeList", param);
	}

	@Override
	public PageList<PopularizeDTO> getSimpleListForPage(PopularizeDTO popularizeDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(popularizeDTO);
		return this.queryForPage("popularize.getSimplePopularizeListCount", "popularize.getSimplePopularizeList", queryParam);
	}

	@Override
	public PageList<PopularizeDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("popularize.getSimplePopularizeListCount", "popularize.getSimplePopularizeList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("popularize.getPopularizeList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("popularize.getPopularizeListCount", "popularize.getPopularizeList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("popularize.getPopularizeListCount", "popularize.getPopularizeList", queryParam, clazz);
	}

}