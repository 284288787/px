/** create by auto at 2017-07-14 16:22:09**/
package com.booting.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.order.dto.WeixinPayDetailDTO;
import com.booting.order.entity.WeixinPayDetailEntity;
import com.booting.order.service.WeixinPayDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("weixinPayDetailService")
public class WeixinPayDetailServiceImpl extends JDBCSupport<WeixinPayDetailEntity, WeixinPayDetailDTO> implements WeixinPayDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public WeixinPayDetailDTO save(WeixinPayDetailEntity weixinPayDetailEntity) {
		long id = this.persist(weixinPayDetailEntity);
		return get(id);
	}

	@Override
	public WeixinPayDetailDTO update(WeixinPayDetailEntity weixinPayDetailEntity) {
		this.dynamicMerge(weixinPayDetailEntity);
		return get(weixinPayDetailEntity.getId());
	}

	@Override
	public WeixinPayDetailDTO updateAll(WeixinPayDetailEntity weixinPayDetailEntity) {
		this.merge(weixinPayDetailEntity);
		return get(weixinPayDetailEntity.getId());
	}

	@Override
	public WeixinPayDetailDTO updateBySql(WeixinPayDetailDTO weixinPayDetailDTO) {
		if(null == weixinPayDetailDTO) return null;
		this.execute("weixinPayDetail.updateWeixinPayDetail", toMap(weixinPayDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == weixinPayDetailDTO.getId()) return null;
		return get(weixinPayDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<WeixinPayDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("weixinPayDetail.insertWeixinPayDetail", params);
	}

	@Override
	public void batchUpdate(List<WeixinPayDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("weixinPayDetail.updateWeixinPayDetail", params);
	}

	@Override
	public WeixinPayDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public WeixinPayDetailDTO get(WeixinPayDetailDTO weixinPayDetailDTO) {
		if(null == weixinPayDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(weixinPayDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("weixinPayDetail.getSimpleWeixinPayDetailList", param);
	}

	@Override
	public List<WeixinPayDetailDTO> getSimpleList(WeixinPayDetailDTO weixinPayDetailDTO) {
		Map<String, Object> param = toMap(weixinPayDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("weixinPayDetail.getSimpleWeixinPayDetailList", param);
	}

	@Override
	public PageList<WeixinPayDetailDTO> getSimpleListForPage(WeixinPayDetailDTO weixinPayDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(weixinPayDetailDTO);
		return this.queryForPage("weixinPayDetail.getSimpleWeixinPayDetailListCount", "weixinPayDetail.getSimpleWeixinPayDetailList", queryParam);
	}

	@Override
	public PageList<WeixinPayDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("weixinPayDetail.getSimpleWeixinPayDetailListCount", "weixinPayDetail.getSimpleWeixinPayDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("weixinPayDetail.getWeixinPayDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("weixinPayDetail.getWeixinPayDetailListCount", "weixinPayDetail.getWeixinPayDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("weixinPayDetail.getWeixinPayDetailListCount", "weixinPayDetail.getWeixinPayDetailList", queryParam, clazz);
	}

}