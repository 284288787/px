/** create by auto at 2017-06-10 18:39:02**/
package com.booting.pkg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pkg.dto.UserPackageDTO;
import com.booting.pkg.entity.UserPackageEntity;
import com.booting.pkg.service.UserPackageService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("userPackageService")
public class UserPackageServiceImpl extends JDBCSupport<UserPackageEntity, UserPackageDTO> implements UserPackageService{

	private static final long serialVersionUID = 1L;

	@Override
	public UserPackageDTO save(UserPackageEntity userPackageEntity) {
		long id = this.persist(userPackageEntity);
		return get(id);
	}

	@Override
	public UserPackageDTO update(UserPackageEntity userPackageEntity) {
		this.dynamicMerge(userPackageEntity);
		return get(userPackageEntity.getUpId());
	}

	@Override
	public UserPackageDTO updateAll(UserPackageEntity userPackageEntity) {
		this.merge(userPackageEntity);
		return get(userPackageEntity.getUpId());
	}

	@Override
	public UserPackageDTO updateBySql(UserPackageDTO userPackageDTO) {
		if(null == userPackageDTO) return null;
		this.execute("userPackage.updateUserPackage", toMap(userPackageDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == userPackageDTO.getUpId()) return null;
		return get(userPackageDTO.getUpId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<UserPackageEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("userPackage.insertUserPackage", params);
	}

	@Override
	public void batchUpdate(List<UserPackageDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("userPackage.updateUserPackage", params);
	}

	@Override
	public UserPackageDTO get(long upId) {
		return getById(upId);
	}

	@Override
	public UserPackageDTO get(UserPackageDTO userPackageDTO) {
		if(null == userPackageDTO) {
			return null;
		}
		Map<String, Object> param = toMap(userPackageDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("userPackage.getSimpleUserPackageList", param);
	}

	@Override
	public List<UserPackageDTO> getSimpleList(UserPackageDTO userPackageDTO) {
		Map<String, Object> param = toMap(userPackageDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("userPackage.getSimpleUserPackageList", param);
	}

	@Override
	public PageList<UserPackageDTO> getSimpleListForPage(UserPackageDTO userPackageDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(userPackageDTO);
		return this.queryForPage("userPackage.getSimpleUserPackageListCount", "userPackage.getSimpleUserPackageList", queryParam);
	}

	@Override
	public PageList<UserPackageDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("userPackage.getSimpleUserPackageListCount", "userPackage.getSimpleUserPackageList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("userPackage.getUserPackageList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("userPackage.getUserPackageListCount", "userPackage.getUserPackageList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("userPackage.getUserPackageListCount", "userPackage.getUserPackageList", queryParam, clazz);
	}

}