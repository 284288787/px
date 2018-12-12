/** create by auto at 2017-06-02 22:16:20**/
package com.booting.pkg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pkg.dto.AttributeDTO;
import com.booting.pkg.entity.AttributeEntity;
import com.booting.pkg.service.AttributeService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("attributeService")
public class AttributeServiceImpl extends JDBCSupport<AttributeEntity, AttributeDTO> implements AttributeService{

	private static final long serialVersionUID = 1L;

	@Override
	public AttributeDTO save(AttributeEntity attributeEntity) {
		long id = this.persist(attributeEntity);
		return get(id);
	}

	@Override
	public AttributeDTO update(AttributeEntity attributeEntity) {
		this.dynamicMerge(attributeEntity);
		return get(attributeEntity.getAttributeId());
	}

	@Override
	public AttributeDTO updateAll(AttributeEntity attributeEntity) {
		this.merge(attributeEntity);
		return get(attributeEntity.getAttributeId());
	}

	@Override
	public AttributeDTO updateBySql(AttributeDTO attributeDTO) {
		if(null == attributeDTO) return null;
		this.execute("attribute.updateAttribute", toMap(attributeDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == attributeDTO.getAttributeId()) return null;
		return get(attributeDTO.getAttributeId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<AttributeEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("attribute.insertAttribute", params);
	}

	@Override
	public void batchUpdate(List<AttributeDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("attribute.updateAttribute", params);
	}

	@Override
	public AttributeDTO get(long attributeId) {
		return getById(attributeId);
	}

	@Override
	public AttributeDTO get(AttributeDTO attributeDTO) {
		if(null == attributeDTO) {
			return null;
		}
		Map<String, Object> param = toMap(attributeDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("attribute.getSimpleAttributeList", param);
	}

	@Override
	public List<AttributeDTO> getSimpleList(AttributeDTO attributeDTO) {
		Map<String, Object> param = toMap(attributeDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("attribute.getSimpleAttributeList", param);
	}

	@Override
	public PageList<AttributeDTO> getSimpleListForPage(AttributeDTO attributeDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(attributeDTO);
		return this.queryForPage("attribute.getSimpleAttributeListCount", "attribute.getSimpleAttributeList", queryParam);
	}

	@Override
	public PageList<AttributeDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("attribute.getSimpleAttributeListCount", "attribute.getSimpleAttributeList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("attribute.getAttributeList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("attribute.getAttributeListCount", "attribute.getAttributeList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("attribute.getAttributeListCount", "attribute.getAttributeList", queryParam, clazz);
	}

}