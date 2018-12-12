/** create by auto at 2018-01-02 14:44:30**/
package com.booting.kindergarten.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.kindergarten.dto.ParentDTO;
import com.booting.kindergarten.entity.ParentEntity;
import com.booting.kindergarten.service.ParentService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("parentService")
public class ParentServiceImpl extends JDBCSupport<ParentEntity, ParentDTO> implements ParentService{

	private static final long serialVersionUID = 1L;

	@Override
	public ParentDTO save(ParentEntity parentEntity) {
		long id = this.persist(parentEntity);
		return get(id);
	}

	@Override
	public ParentDTO update(ParentEntity parentEntity) {
		this.dynamicMerge(parentEntity);
		return get(parentEntity.getParentId());
	}

	@Override
	public ParentDTO updateAll(ParentEntity parentEntity) {
		this.merge(parentEntity);
		return get(parentEntity.getParentId());
	}

	@Override
	public ParentDTO updateBySql(ParentDTO parentDTO) {
		if(null == parentDTO) return null;
		this.execute("parent.updateParent", toMap(parentDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == parentDTO.getParentId()) return null;
		return get(parentDTO.getParentId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ParentEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("parent.insertParent", params);
	}

	@Override
	public void batchUpdate(List<ParentDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("parent.updateParent", params);
	}

	@Override
	public ParentDTO get(long parentId) {
		return getById(parentId);
	}

	@Override
	public ParentDTO get(ParentDTO parentDTO) {
		if(null == parentDTO) {
			return null;
		}
		Map<String, Object> param = toMap(parentDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("parent.getSimpleParentList", param);
	}

	@Override
	public List<ParentDTO> getSimpleList(ParentDTO parentDTO) {
		Map<String, Object> param = toMap(parentDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("parent.getSimpleParentList", param);
	}

	@Override
	public PageList<ParentDTO> getSimpleListForPage(ParentDTO parentDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(parentDTO);
		return this.queryForPage("parent.getSimpleParentListCount", "parent.getSimpleParentList", queryParam);
	}

	@Override
	public PageList<ParentDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("parent.getSimpleParentListCount", "parent.getSimpleParentList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("parent.getParentList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("parent.getParentListCount", "parent.getParentList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("parent.getParentListCount", "parent.getParentList", queryParam, clazz);
	}

}