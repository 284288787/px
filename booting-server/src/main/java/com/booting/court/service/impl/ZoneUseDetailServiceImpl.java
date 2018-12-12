/** create by auto at 2017-06-23 16:07:39**/
package com.booting.court.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.court.dto.ZoneUseDetailDTO;
import com.booting.court.entity.ZoneUseDetailEntity;
import com.booting.court.service.ZoneUseDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("zoneUseDetailService")
public class ZoneUseDetailServiceImpl extends JDBCSupport<ZoneUseDetailEntity, ZoneUseDetailDTO> implements ZoneUseDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public ZoneUseDetailDTO save(ZoneUseDetailEntity zoneUseDetailEntity) {
		long id = this.persist(zoneUseDetailEntity);
		return get(id);
	}

	@Override
	public ZoneUseDetailDTO update(ZoneUseDetailEntity zoneUseDetailEntity) {
		this.dynamicMerge(zoneUseDetailEntity);
		return get(zoneUseDetailEntity.getId());
	}

	@Override
	public ZoneUseDetailDTO updateAll(ZoneUseDetailEntity zoneUseDetailEntity) {
		this.merge(zoneUseDetailEntity);
		return get(zoneUseDetailEntity.getId());
	}

	@Override
	public ZoneUseDetailDTO updateBySql(ZoneUseDetailDTO zoneUseDetailDTO) {
		if(null == zoneUseDetailDTO) return null;
		this.execute("zoneUseDetail.updateZoneUseDetail", toMap(zoneUseDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == zoneUseDetailDTO.getId()) return null;
		return get(zoneUseDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ZoneUseDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("zoneUseDetail.insertZoneUseDetail", params);
	}

	@Override
	public void batchUpdate(List<ZoneUseDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("zoneUseDetail.updateZoneUseDetail", params);
	}

	@Override
	public ZoneUseDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public ZoneUseDetailDTO get(ZoneUseDetailDTO zoneUseDetailDTO) {
		if(null == zoneUseDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(zoneUseDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("zoneUseDetail.getSimpleZoneUseDetailList", param);
	}

	@Override
	public List<ZoneUseDetailDTO> getSimpleList(ZoneUseDetailDTO zoneUseDetailDTO) {
		Map<String, Object> param = toMap(zoneUseDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("zoneUseDetail.getSimpleZoneUseDetailList", param);
	}

	@Override
	public PageList<ZoneUseDetailDTO> getSimpleListForPage(ZoneUseDetailDTO zoneUseDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(zoneUseDetailDTO);
		return this.queryForPage("zoneUseDetail.getSimpleZoneUseDetailListCount", "zoneUseDetail.getSimpleZoneUseDetailList", queryParam);
	}

	@Override
	public PageList<ZoneUseDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("zoneUseDetail.getSimpleZoneUseDetailListCount", "zoneUseDetail.getSimpleZoneUseDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("zoneUseDetail.getZoneUseDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("zoneUseDetail.getZoneUseDetailListCount", "zoneUseDetail.getZoneUseDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("zoneUseDetail.getZoneUseDetailListCount", "zoneUseDetail.getZoneUseDetailList", queryParam, clazz);
	}

}