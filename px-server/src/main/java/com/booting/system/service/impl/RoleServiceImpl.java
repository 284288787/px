/** create by auto at 2017-05-24 16:40:27**/
package com.booting.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.system.dto.RoleDTO;
import com.booting.system.entity.RoleEntity;
import com.booting.system.service.RoleService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("roleService")
public class RoleServiceImpl extends JDBCSupport<RoleEntity, RoleDTO> implements RoleService{

	private static final long serialVersionUID = 1L;

	@Override
	public RoleDTO save(RoleEntity roleEntity) {
		long id = this.persist(roleEntity);
		return get(id);
	}

	@Override
	public RoleDTO update(RoleEntity roleEntity) {
		this.dynamicMerge(roleEntity);
		return get(roleEntity.getRoleId());
	}

	@Override
	public RoleDTO updateAll(RoleEntity roleEntity) {
		this.merge(roleEntity);
		return get(roleEntity.getRoleId());
	}

	@Override
	public RoleDTO updateBySql(RoleDTO roleDTO) {
		if(null == roleDTO) return null;
		this.execute("role.updateRole", toMap(roleDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == roleDTO.getRoleId()) return null;
		return get(roleDTO.getRoleId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<RoleEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("role.insertRole", params);
	}

	@Override
	public void batchUpdate(List<RoleDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("role.updateRole", params);
	}

	@Override
	public RoleDTO get(long roleId) {
		return getById(roleId);
	}

	@Override
	public RoleDTO get(RoleDTO roleDTO) {
		if(null == roleDTO) {
			return null;
		}
		Map<String, Object> param = toMap(roleDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("role.getSimpleRoleList", param);
	}

	@Override
	public List<RoleDTO> getSimpleList(RoleDTO roleDTO) {
		Map<String, Object> param = toMap(roleDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("role.getSimpleRoleList", param);
	}

	@Override
	public PageList<RoleDTO> getSimpleListForPage(RoleDTO roleDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(roleDTO);
		return this.queryForPage("role.getSimpleRoleListCount", "role.getSimpleRoleList", queryParam);
	}

	@Override
	public PageList<RoleDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("role.getSimpleRoleListCount", "role.getSimpleRoleList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("role.getRoleList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("role.getRoleListCount", "role.getRoleList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("role.getRoleListCount", "role.getRoleList", queryParam, clazz);
	}

}