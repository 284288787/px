/** create by auto at 2018-06-21 14:14:57**/
package com.booting.product.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.product.dto.EquipmentDTO;
import com.booting.product.entity.EquipmentEntity;
import com.booting.product.service.EquipmentService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("equipmentService")
public class EquipmentServiceImpl extends JDBCSupport<EquipmentEntity, EquipmentDTO> implements EquipmentService{

	private static final long serialVersionUID = 1L;

	@Override
	public EquipmentDTO save(EquipmentEntity equipmentEntity) {
		long id = this.persist(equipmentEntity);
		return get(id);
	}

	@Override
	public EquipmentDTO update(EquipmentEntity equipmentEntity) {
		this.dynamicMerge(equipmentEntity);
		return get(equipmentEntity.getEquipmentId());
	}

	@Override
	public EquipmentDTO updateAll(EquipmentEntity equipmentEntity) {
		this.merge(equipmentEntity);
		return get(equipmentEntity.getEquipmentId());
	}

	@Override
	public EquipmentDTO updateBySql(EquipmentDTO equipmentDTO) {
		if(null == equipmentDTO) return null;
		this.execute("equipment.updateEquipment", toMap(equipmentDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == equipmentDTO.getEquipmentId()) return null;
		return get(equipmentDTO.getEquipmentId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<EquipmentEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("equipment.insertEquipment", params);
	}

	@Override
	public void batchUpdate(List<EquipmentDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("equipment.updateEquipment", params);
	}

	@Override
	public EquipmentDTO get(long equipmentId) {
		return getById(equipmentId);
	}

	@Override
	public EquipmentDTO get(EquipmentDTO equipmentDTO) {
		if(null == equipmentDTO) {
			return null;
		}
		Map<String, Object> param = toMap(equipmentDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("equipment.getSimpleEquipmentList", param);
	}

	@Override
	public List<EquipmentDTO> getSimpleList(EquipmentDTO equipmentDTO) {
		Map<String, Object> param = toMap(equipmentDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("equipment.getSimpleEquipmentList", param);
	}

	@Override
	public PageList<EquipmentDTO> getSimpleListForPage(EquipmentDTO equipmentDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(equipmentDTO);
		return this.queryForPage("equipment.getSimpleEquipmentListCount", "equipment.getSimpleEquipmentList", queryParam);
	}

	@Override
	public PageList<EquipmentDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("equipment.getSimpleEquipmentListCount", "equipment.getSimpleEquipmentList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("equipment.getEquipmentList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("equipment.getEquipmentListCount", "equipment.getEquipmentList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("equipment.getEquipmentListCount", "equipment.getEquipmentList", queryParam, clazz);
	}

}