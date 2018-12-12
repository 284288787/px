/** create by auto at 2018-01-02 14:44:30**/
package com.booting.kindergarten.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.kindergarten.dto.PhysicalDataDTO;
import com.booting.kindergarten.entity.PhysicalDataEntity;
import com.booting.kindergarten.service.PhysicalDataService;
import com.star.framework.jdbc.dao.JDBCSupport;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

@Service("physicalDataService")
public class PhysicalDataServiceImpl extends JDBCSupport<PhysicalDataEntity, PhysicalDataDTO> implements PhysicalDataService{

	private static final long serialVersionUID = 1L;

	@Override
	public PhysicalDataDTO save(PhysicalDataEntity physicalDataEntity) {
		long id = this.persist(physicalDataEntity);
		return get(id);
	}

	@Override
	public PhysicalDataDTO update(PhysicalDataEntity physicalDataEntity) {
		this.dynamicMerge(physicalDataEntity);
		return get(physicalDataEntity.getId());
	}

	@Override
	public PhysicalDataDTO updateAll(PhysicalDataEntity physicalDataEntity) {
		this.merge(physicalDataEntity);
		return get(physicalDataEntity.getId());
	}

	@Override
	public PhysicalDataDTO updateBySql(PhysicalDataDTO physicalDataDTO) {
		if(null == physicalDataDTO) return null;
		this.execute("physicalData.updatePhysicalData", toMap(physicalDataDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == physicalDataDTO.getId()) return null;
		return get(physicalDataDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<PhysicalDataEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("physicalData.insertPhysicalData", params);
	}

	@Override
	public void batchUpdate(List<PhysicalDataDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("physicalData.updatePhysicalData", params);
	}

	@Override
	public PhysicalDataDTO get(long id) {
		return getById(id);
	}

	@Override
	public PhysicalDataDTO get(PhysicalDataDTO physicalDataDTO) {
		if(null == physicalDataDTO) {
			return null;
		}
		Map<String, Object> param = toMap(physicalDataDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("physicalData.getSimplePhysicalDataList", param);
	}
	
	@Override
	public List<PhysicalDataDTO> getSimpleList(PhysicalDataDTO physicalDataDTO) {
	  Map<String, Object> param = toMap(physicalDataDTO, "yyyy-MM-dd HH:mm:ss");
	  return this.queryForList("physicalData.getSimplePhysicalDataList", param);
	}

	@Override
	public List<PhysicalDataDTO> getPhysicalDataList(PhysicalDataDTO physicalDataDTO, Integer num) {
		Map<String, Object> param = toMap(physicalDataDTO, "yyyy-MM-dd HH:mm:ss");
		if (null == param) {
	      param = new HashMap<>();
	    }
	    param.put("_num", num);
		return this.queryForList("physicalData.getSimplePhysicalDataList", param);
	}
	  
	@Override
	public PageList<PhysicalDataDTO> getSimpleListForPage(PhysicalDataDTO physicalDataDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(physicalDataDTO);
		return this.queryForPage("physicalData.getSimplePhysicalDataListCount", "physicalData.getSimplePhysicalDataList", queryParam);
	}

	@Override
	public PageList<PhysicalDataDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("physicalData.getSimplePhysicalDataListCount", "physicalData.getSimplePhysicalDataList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("physicalData.getPhysicalDataList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("physicalData.getPhysicalDataListCount", "physicalData.getPhysicalDataList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("physicalData.getPhysicalDataListCount", "physicalData.getPhysicalDataList", queryParam, clazz);
	}

	@Override
	public void deletePhysicalDataBySql(PhysicalDataDTO params) {
		this.execute("physicalData.deletePhysicalData", toMap(params, "yyyy-MM-dd HH:mm:ss"));
	}

}