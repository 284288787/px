/** create by auto at 2017-06-08 10:32:48**/
package com.booting.pkg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pkg.dto.UserAttributeDTO;
import com.booting.pkg.entity.UserAttributeEntity;
import com.booting.pkg.service.UserAttributeService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("userAttributeService")
public class UserAttributeServiceImpl extends JDBCSupport<UserAttributeEntity, UserAttributeDTO> implements UserAttributeService{

	private static final long serialVersionUID = 1L;

	@Override
	public UserAttributeDTO save(UserAttributeEntity userAttributeEntity) {
		long id = this.persist(userAttributeEntity);
		return get(id);
	}

	@Override
	public UserAttributeDTO update(UserAttributeEntity userAttributeEntity) {
		this.dynamicMerge(userAttributeEntity);
		return get(userAttributeEntity.getId());
	}

	@Override
	public UserAttributeDTO updateAll(UserAttributeEntity userAttributeEntity) {
		this.merge(userAttributeEntity);
		return get(userAttributeEntity.getId());
	}

	@Override
	public UserAttributeDTO updateBySql(UserAttributeDTO userAttributeDTO) {
		if(null == userAttributeDTO) return null;
		this.execute("userAttribute.updateUserAttribute", toMap(userAttributeDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == userAttributeDTO.getId()) return null;
		return get(userAttributeDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<UserAttributeEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("userAttribute.insertUserAttribute", params);
	}

	@Override
	public void batchUpdate(List<UserAttributeDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("userAttribute.updateUserAttribute", params);
	}

	@Override
	public UserAttributeDTO get(long id) {
		return getById(id);
	}

	@Override
	public UserAttributeDTO get(UserAttributeDTO userAttributeDTO) {
		if(null == userAttributeDTO) {
			return null;
		}
		Map<String, Object> param = toMap(userAttributeDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("userAttribute.getSimpleUserAttributeList", param);
	}

	@Override
	public List<UserAttributeDTO> getSimpleList(UserAttributeDTO userAttributeDTO) {
		Map<String, Object> param = toMap(userAttributeDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("userAttribute.getSimpleUserAttributeList", param);
	}

	@Override
	public PageList<UserAttributeDTO> getSimpleListForPage(UserAttributeDTO userAttributeDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(userAttributeDTO);
		return this.queryForPage("userAttribute.getSimpleUserAttributeListCount", "userAttribute.getSimpleUserAttributeList", queryParam);
	}

	@Override
	public PageList<UserAttributeDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("userAttribute.getSimpleUserAttributeListCount", "userAttribute.getSimpleUserAttributeList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("userAttribute.getUserAttributeList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("userAttribute.getUserAttributeListCount", "userAttribute.getUserAttributeList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("userAttribute.getUserAttributeListCount", "userAttribute.getUserAttributeList", queryParam, clazz);
	}

}