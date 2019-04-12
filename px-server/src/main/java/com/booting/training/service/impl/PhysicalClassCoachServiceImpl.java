/** create by auto at 2019-04-12 15:10:57**/
package com.booting.training.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.training.dto.PhysicalClassCoachDTO;
import com.booting.training.entity.PhysicalClassCoachEntity;
import com.booting.training.service.PhysicalClassCoachService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("physicalClassCoachService")
public class PhysicalClassCoachServiceImpl extends JDBCSupport<PhysicalClassCoachEntity, PhysicalClassCoachDTO> implements PhysicalClassCoachService{

	private static final long serialVersionUID = 1L;

	@Override
	public PhysicalClassCoachDTO save(PhysicalClassCoachEntity physicalClassCoachEntity) {
		long id = this.persist(physicalClassCoachEntity);
		return get(id);
	}

	@Override
	public PhysicalClassCoachDTO update(PhysicalClassCoachEntity physicalClassCoachEntity) {
		this.dynamicMerge(physicalClassCoachEntity);
		return get(physicalClassCoachEntity.getId());
	}

	@Override
	public PhysicalClassCoachDTO updateAll(PhysicalClassCoachEntity physicalClassCoachEntity) {
		this.merge(physicalClassCoachEntity);
		return get(physicalClassCoachEntity.getId());
	}

	@Override
	public PhysicalClassCoachDTO updateBySql(PhysicalClassCoachDTO physicalClassCoachDTO) {
		if(null == physicalClassCoachDTO) return null;
		this.execute("physicalClassCoach.updatePhysicalClassCoach", toMap(physicalClassCoachDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == physicalClassCoachDTO.getId()) return null;
		return get(physicalClassCoachDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<PhysicalClassCoachEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("physicalClassCoach.insertPhysicalClassCoach", params);
	}

	@Override
	public void batchUpdate(List<PhysicalClassCoachDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("physicalClassCoach.updatePhysicalClassCoach", params);
	}

	@Override
	public PhysicalClassCoachDTO get(long id) {
		return getById(id);
	}

	@Override
	public PhysicalClassCoachDTO get(PhysicalClassCoachDTO physicalClassCoachDTO) {
		if(null == physicalClassCoachDTO) {
			return null;
		}
		Map<String, Object> param = toMap(physicalClassCoachDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("physicalClassCoach.getSimplePhysicalClassCoachList", param);
	}

	@Override
	public List<PhysicalClassCoachDTO> getSimpleList(PhysicalClassCoachDTO physicalClassCoachDTO) {
		Map<String, Object> param = toMap(physicalClassCoachDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("physicalClassCoach.getSimplePhysicalClassCoachList", param);
	}

	@Override
	public PageList<PhysicalClassCoachDTO> getSimpleListForPage(PhysicalClassCoachDTO physicalClassCoachDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(physicalClassCoachDTO);
		return this.queryForPage("physicalClassCoach.getSimplePhysicalClassCoachListCount", "physicalClassCoach.getSimplePhysicalClassCoachList", queryParam);
	}

	@Override
	public PageList<PhysicalClassCoachDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("physicalClassCoach.getSimplePhysicalClassCoachListCount", "physicalClassCoach.getSimplePhysicalClassCoachList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("physicalClassCoach.getPhysicalClassCoachList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("physicalClassCoach.getPhysicalClassCoachListCount", "physicalClassCoach.getPhysicalClassCoachList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("physicalClassCoach.getPhysicalClassCoachListCount", "physicalClassCoach.getPhysicalClassCoachList", queryParam, clazz);
	}

}