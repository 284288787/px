/** create by auto at 2019-04-09 10:52:01**/
package com.booting.training.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.training.dto.PhysicalClassDTO;
import com.booting.training.entity.PhysicalClassEntity;
import com.booting.training.service.PhysicalClassService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("physicalClassService")
public class PhysicalClassServiceImpl extends JDBCSupport<PhysicalClassEntity, PhysicalClassDTO> implements PhysicalClassService{

	private static final long serialVersionUID = 1L;

	@Override
	public PhysicalClassDTO save(PhysicalClassEntity physicalClassEntity) {
		long id = this.persist(physicalClassEntity);
		return get(id);
	}

	@Override
	public PhysicalClassDTO update(PhysicalClassEntity physicalClassEntity) {
		this.dynamicMerge(physicalClassEntity);
		return get(physicalClassEntity.getPhysicalClassId());
	}

	@Override
	public PhysicalClassDTO updateAll(PhysicalClassEntity physicalClassEntity) {
		this.merge(physicalClassEntity);
		return get(physicalClassEntity.getPhysicalClassId());
	}

	@Override
	public PhysicalClassDTO updateBySql(PhysicalClassDTO physicalClassDTO) {
		if(null == physicalClassDTO) return null;
		this.execute("physicalClass.updatePhysicalClass", toMap(physicalClassDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == physicalClassDTO.getPhysicalClassId()) return null;
		return get(physicalClassDTO.getPhysicalClassId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<PhysicalClassEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("physicalClass.insertPhysicalClass", params);
	}

	@Override
	public void batchUpdate(List<PhysicalClassDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("physicalClass.updatePhysicalClass", params);
	}

	@Override
	public PhysicalClassDTO get(long physicalClassId) {
		return getById(physicalClassId);
	}

	@Override
	public PhysicalClassDTO get(PhysicalClassDTO physicalClassDTO) {
		if(null == physicalClassDTO) {
			return null;
		}
		Map<String, Object> param = toMap(physicalClassDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("physicalClass.getSimplePhysicalClassList", param);
	}

	@Override
	public List<PhysicalClassDTO> getSimpleList(PhysicalClassDTO physicalClassDTO) {
		Map<String, Object> param = toMap(physicalClassDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("physicalClass.getSimplePhysicalClassList", param);
	}

	@Override
	public PageList<PhysicalClassDTO> getSimpleListForPage(PhysicalClassDTO physicalClassDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(physicalClassDTO);
		return this.queryForPage("physicalClass.getSimplePhysicalClassListCount", "physicalClass.getSimplePhysicalClassList", queryParam);
	}

	@Override
	public PageList<PhysicalClassDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("physicalClass.getSimplePhysicalClassListCount", "physicalClass.getSimplePhysicalClassList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("physicalClass.getPhysicalClassList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("physicalClass.getPhysicalClassListCount", "physicalClass.getPhysicalClassList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("physicalClass.getPhysicalClassListCount", "physicalClass.getPhysicalClassList", queryParam, clazz);
	}

}