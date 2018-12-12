/** create by auto at 2017-07-14 16:22:09**/
package com.booting.order.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.order.dto.AlipayPayDetailDTO;
import com.booting.order.entity.AlipayPayDetailEntity;
import com.booting.order.service.AlipayPayDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("alipayPayDetailService")
public class AlipayPayDetailServiceImpl extends JDBCSupport<AlipayPayDetailEntity, AlipayPayDetailDTO> implements AlipayPayDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public AlipayPayDetailDTO save(AlipayPayDetailEntity alipayPayDetailEntity) {
		long id = this.persist(alipayPayDetailEntity);
		return get(id);
	}

	@Override
	public AlipayPayDetailDTO update(AlipayPayDetailEntity alipayPayDetailEntity) {
		this.dynamicMerge(alipayPayDetailEntity);
		return get(alipayPayDetailEntity.getId());
	}

	@Override
	public AlipayPayDetailDTO updateAll(AlipayPayDetailEntity alipayPayDetailEntity) {
		this.merge(alipayPayDetailEntity);
		return get(alipayPayDetailEntity.getId());
	}

	@Override
	public AlipayPayDetailDTO updateBySql(AlipayPayDetailDTO alipayPayDetailDTO) {
		if(null == alipayPayDetailDTO) return null;
		this.execute("alipayPayDetail.updateAlipayPayDetail", toMap(alipayPayDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == alipayPayDetailDTO.getId()) return null;
		return get(alipayPayDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<AlipayPayDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("alipayPayDetail.insertAlipayPayDetail", params);
	}

	@Override
	public void batchUpdate(List<AlipayPayDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("alipayPayDetail.updateAlipayPayDetail", params);
	}

	@Override
	public AlipayPayDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public AlipayPayDetailDTO get(AlipayPayDetailDTO alipayPayDetailDTO) {
		if(null == alipayPayDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(alipayPayDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("alipayPayDetail.getSimpleAlipayPayDetailList", param);
	}

	@Override
	public List<AlipayPayDetailDTO> getSimpleList(AlipayPayDetailDTO alipayPayDetailDTO) {
		Map<String, Object> param = toMap(alipayPayDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("alipayPayDetail.getSimpleAlipayPayDetailList", param);
	}

	@Override
	public PageList<AlipayPayDetailDTO> getSimpleListForPage(AlipayPayDetailDTO alipayPayDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(alipayPayDetailDTO);
		return this.queryForPage("alipayPayDetail.getSimpleAlipayPayDetailListCount", "alipayPayDetail.getSimpleAlipayPayDetailList", queryParam);
	}

	@Override
	public PageList<AlipayPayDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("alipayPayDetail.getSimpleAlipayPayDetailListCount", "alipayPayDetail.getSimpleAlipayPayDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("alipayPayDetail.getAlipayPayDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("alipayPayDetail.getAlipayPayDetailListCount", "alipayPayDetail.getAlipayPayDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("alipayPayDetail.getAlipayPayDetailListCount", "alipayPayDetail.getAlipayPayDetailList", queryParam, clazz);
	}

}