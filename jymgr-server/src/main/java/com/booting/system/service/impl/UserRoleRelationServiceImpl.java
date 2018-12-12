/** create by auto at 2017-05-24 16:40:27**/
package com.booting.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.system.dto.UserRoleRelationDTO;
import com.booting.system.entity.UserRoleRelationEntity;
import com.booting.system.service.UserRoleRelationService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("userRoleRelationService")
public class UserRoleRelationServiceImpl extends JDBCSupport<UserRoleRelationEntity, UserRoleRelationDTO> implements UserRoleRelationService{

	private static final long serialVersionUID = 1L;

	@Override
	public UserRoleRelationDTO save(UserRoleRelationEntity userRoleRelationEntity) {
		long id = this.persist(userRoleRelationEntity);
		return get(id);
	}

	@Override
	public UserRoleRelationDTO update(UserRoleRelationEntity userRoleRelationEntity) {
		this.dynamicMerge(userRoleRelationEntity);
		return get(userRoleRelationEntity.getId());
	}

	@Override
	public UserRoleRelationDTO updateAll(UserRoleRelationEntity userRoleRelationEntity) {
		this.merge(userRoleRelationEntity);
		return get(userRoleRelationEntity.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<UserRoleRelationEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("userRoleRelation.insertUserRoleRelation", params);
	}

	@Override
	public void batchUpdate(List<UserRoleRelationDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("userRoleRelation.updateUserRoleRelation", params);
	}

	@Override
	public UserRoleRelationDTO get(long id) {
		return getById(id);
	}

	@Override
	public UserRoleRelationDTO get(UserRoleRelationDTO userRoleRelationDTO) {
		if(null == userRoleRelationDTO) {
			return null;
		}
		Map<String, Object> param = toMap(userRoleRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("userRoleRelation.getSimpleUserRoleRelationList", param);
	}

	@Override
	public List<UserRoleRelationDTO> getSimpleList(UserRoleRelationDTO userRoleRelationDTO) {
		Map<String, Object> param = toMap(userRoleRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("userRoleRelation.getSimpleUserRoleRelationList", param);
	}

	@Override
	public PageList<UserRoleRelationDTO> getSimpleListForPage(UserRoleRelationDTO userRoleRelationDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(userRoleRelationDTO);
		return this.queryForPage("userRoleRelation.getSimpleUserRoleRelationListCount", "userRoleRelation.getSimpleUserRoleRelationList", queryParam);
	}

	@Override
	public PageList<UserRoleRelationDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("userRoleRelation.getSimpleUserRoleRelationListCount", "userRoleRelation.getSimpleUserRoleRelationList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("userRoleRelation.getUserRoleRelationList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("userRoleRelation.getUserRoleRelationListCount", "userRoleRelation.getUserRoleRelationList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("userRoleRelation.getUserRoleRelationListCount", "userRoleRelation.getUserRoleRelationList", queryParam, clazz);
	}

	@Override
	public UserRoleRelationDTO updateBySql(UserRoleRelationDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteBySql(UserRoleRelationDTO dto) {
		return this.execute("userRoleRelation.deleteUserRoleRelation", toMap(dto, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public PageList<UserRoleRelationDTO> getUserRole(QueryParam queryParam) {
		return this.queryForPage("userRoleRelation.getUserRoleListCount", "userRoleRelation.getUserRoleList", queryParam, UserRoleRelationDTO.class);
	}

}