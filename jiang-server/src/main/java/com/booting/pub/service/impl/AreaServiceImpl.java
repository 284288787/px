/** create by auto at 2017-06-22 15:36:53**/
package com.booting.pub.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pub.dto.AreaDTO;
import com.booting.pub.entity.AreaEntity;
import com.booting.pub.service.AreaService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("areaService")
public class AreaServiceImpl extends JDBCSupport<AreaEntity, AreaDTO> implements AreaService{

	private static final long serialVersionUID = 1L;

	@Override
	public AreaDTO save(AreaEntity areaEntity) {
		long id = this.persist(areaEntity);
		return get(id);
	}

	@Override
	public AreaDTO update(AreaEntity areaEntity) {
		this.dynamicMerge(areaEntity);
		return get(areaEntity.getAreaId());
	}

	@Override
	public AreaDTO updateAll(AreaEntity areaEntity) {
		this.merge(areaEntity);
		return get(areaEntity.getAreaId());
	}

	@Override
	public AreaDTO updateBySql(AreaDTO areaDTO) {
		if(null == areaDTO) return null;
		this.execute("area.updateArea", toMap(areaDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == areaDTO.getAreaId()) return null;
		return get(areaDTO.getAreaId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<AreaEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("area.insertArea", params);
	}

	@Override
	public void batchUpdate(List<AreaDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("area.updateArea", params);
	}

	@Override
	public AreaDTO get(long areaId) {
		return getById(areaId);
	}

	@Override
	public AreaDTO get(AreaDTO areaDTO) {
		if(null == areaDTO) {
			return null;
		}
		Map<String, Object> param = toMap(areaDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("area.getSimpleAreaList", param);
	}

	@Override
	public List<AreaDTO> getSimpleList(AreaDTO areaDTO) {
		Map<String, Object> param = toMap(areaDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("area.getSimpleAreaList", param);
	}

	@Override
	public PageList<AreaDTO> getSimpleListForPage(AreaDTO areaDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(areaDTO);
		return this.queryForPage("area.getSimpleAreaListCount", "area.getSimpleAreaList", queryParam);
	}

	@Override
	public PageList<AreaDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("area.getSimpleAreaListCount", "area.getSimpleAreaList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("area.getAreaList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("area.getAreaListCount", "area.getAreaList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("area.getAreaListCount", "area.getAreaList", queryParam, clazz);
	}

}