/** create by auto at 2017-06-23 16:07:39**/
package com.booting.court.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.court.dto.ZoneDTO;
import com.booting.court.entity.ZoneEntity;
import com.booting.court.service.ZoneService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("zoneService")
public class ZoneServiceImpl extends JDBCSupport<ZoneEntity, ZoneDTO> implements ZoneService{

	private static final long serialVersionUID = 1L;

	@Override
	public ZoneDTO save(ZoneEntity zoneEntity) {
		long id = this.persist(zoneEntity);
		return get(id);
	}

	@Override
	public ZoneDTO update(ZoneEntity zoneEntity) {
		this.dynamicMerge(zoneEntity);
		return get(zoneEntity.getZoneId());
	}

	@Override
	public ZoneDTO updateAll(ZoneEntity zoneEntity) {
		this.merge(zoneEntity);
		return get(zoneEntity.getZoneId());
	}

	@Override
	public ZoneDTO updateBySql(ZoneDTO zoneDTO) {
		if(null == zoneDTO) return null;
		this.execute("zone.updateZone", toMap(zoneDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == zoneDTO.getZoneId()) return null;
		return get(zoneDTO.getZoneId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ZoneEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("zone.insertZone", params);
	}

	@Override
	public void batchUpdate(List<ZoneDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("zone.updateZone", params);
	}

	@Override
	public ZoneDTO get(long zoneId) {
		return getById(zoneId);
	}

	@Override
	public ZoneDTO get(ZoneDTO zoneDTO) {
		if(null == zoneDTO) {
			return null;
		}
		Map<String, Object> param = toMap(zoneDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("zone.getSimpleZoneList", param);
	}

	@Override
	public List<ZoneDTO> getSimpleList(ZoneDTO zoneDTO) {
		Map<String, Object> param = toMap(zoneDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("zone.getSimpleZoneList", param);
	}

	@Override
	public PageList<ZoneDTO> getSimpleListForPage(ZoneDTO zoneDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(zoneDTO);
		return this.queryForPage("zone.getSimpleZoneListCount", "zone.getSimpleZoneList", queryParam);
	}

	@Override
	public PageList<ZoneDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("zone.getSimpleZoneListCount", "zone.getSimpleZoneList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("zone.getZoneList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("zone.getZoneListCount", "zone.getZoneList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("zone.getZoneListCount", "zone.getZoneList", queryParam, clazz);
	}

	@Override
	public ZoneDTO getUsableZone(Map<String, Object> params) {
		return this.queryForObject("zone.getUsableZone", params);
	}

}