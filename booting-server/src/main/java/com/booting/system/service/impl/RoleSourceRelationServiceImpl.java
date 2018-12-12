/** create by auto at 2017-05-24 16:40:27**/
package com.booting.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.system.dto.RoleSourceRelationDTO;
import com.booting.system.entity.RoleSourceRelationEntity;
import com.booting.system.service.RoleSourceRelationService;
import com.star.framework.jdbc.dao.JDBCSupport;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

@Service("roleSourceRelationService")
public class RoleSourceRelationServiceImpl extends JDBCSupport<RoleSourceRelationEntity, RoleSourceRelationDTO> implements RoleSourceRelationService{

	private static final long serialVersionUID = 1L;

	@Override
	public RoleSourceRelationDTO save(RoleSourceRelationEntity roleSourceRelationEntity) {
		long id = this.persist(roleSourceRelationEntity);
		return get(id);
	}

	@Override
	public RoleSourceRelationDTO update(RoleSourceRelationEntity roleSourceRelationEntity) {
		this.dynamicMerge(roleSourceRelationEntity);
		return get(roleSourceRelationEntity.getId());
	}

	@Override
	public RoleSourceRelationDTO updateAll(RoleSourceRelationEntity roleSourceRelationEntity) {
		this.merge(roleSourceRelationEntity);
		return get(roleSourceRelationEntity.getId());
	}

	@Override
	public RoleSourceRelationDTO updateBySql(RoleSourceRelationDTO roleSourceRelationDTO) {
		return null;
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<RoleSourceRelationEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("roleSourceRelation.insertRoleSourceRelation", params);
	}

	@Override
	public void batchUpdate(List<RoleSourceRelationDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("roleSourceRelation.updateRoleSourceRelation", params);
	}

	@Override
	public RoleSourceRelationDTO get(long id) {
		return getById(id);
	}

	@Override
	public RoleSourceRelationDTO get(RoleSourceRelationDTO roleSourceRelationDTO) {
		if(null == roleSourceRelationDTO) {
			return null;
		}
		Map<String, Object> param = toMap(roleSourceRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("roleSourceRelation.getSimpleRoleSourceRelationList", param);
	}

	@Override
	public List<RoleSourceRelationDTO> getSimpleList(RoleSourceRelationDTO roleSourceRelationDTO) {
		Map<String, Object> param = toMap(roleSourceRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("roleSourceRelation.getSimpleRoleSourceRelationList", param);
	}

	@Override
	public PageList<RoleSourceRelationDTO> getSimpleListForPage(RoleSourceRelationDTO roleSourceRelationDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(roleSourceRelationDTO);
		return this.queryForPage("roleSourceRelation.getSimpleRoleSourceRelationListCount", "roleSourceRelation.getSimpleRoleSourceRelationList", queryParam);
	}

	@Override
	public PageList<RoleSourceRelationDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("roleSourceRelation.getSimpleRoleSourceRelationListCount", "roleSourceRelation.getSimpleRoleSourceRelationList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("roleSourceRelation.getRoleSourceRelationList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("roleSourceRelation.getRoleSourceRelationListCount", "roleSourceRelation.getRoleSourceRelationList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("roleSourceRelation.getRoleSourceRelationListCount", "roleSourceRelation.getRoleSourceRelationList", queryParam, clazz);
	}
	
	@Override
	public PageList<RoleSourceRelationDTO> getRoleSourcesForPage(QueryParam queryParam) {
		return this.queryForPage("roleSourceRelation.getRoleSourcesListCount", "roleSourceRelation.getRoleSourcesList", queryParam, RoleSourceRelationDTO.class);
	}

	@Override
	public PageList<RoleSourceRelationDTO> getRoleSourceForPage(QueryParam queryParam) {
		return this.queryForPage("roleSourceRelation.getRoleSourceListCount", "roleSourceRelation.getRoleSourceList", queryParam, RoleSourceRelationDTO.class);
	}

	@Override
	public int deleteBySql(RoleSourceRelationDTO dto) {
		return this.execute("roleSourceRelation.deleteRoleSourceRelation", toMap(dto, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public List<RoleSourceRelationDTO> getAllRoleSourceRelation() {
		Map<String, Object> paramMap = new HashMap<>();
		return this.queryForList("roleSourceRelation.getAllRoleSourceRelation", paramMap);
	}

	@Override
	public List<RoleSourceRelationDTO> getUserResources(String roleIds) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleIds", roleIds);
		return this.queryForList("roleSourceRelation.getUserResources", paramMap);
	}

}