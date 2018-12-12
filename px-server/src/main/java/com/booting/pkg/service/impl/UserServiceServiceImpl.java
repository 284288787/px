/** create by auto at 2017-06-07 10:16:54**/
package com.booting.pkg.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pkg.dto.UserServiceDTO;
import com.booting.pkg.entity.UserServiceEntity;
import com.booting.pkg.service.UserServiceService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("userServiceService")
public class UserServiceServiceImpl extends JDBCSupport<UserServiceEntity, UserServiceDTO> implements UserServiceService{

	private static final long serialVersionUID = 1L;

	@Override
	public UserServiceDTO save(UserServiceEntity userServiceEntity) {
		long id = this.persist(userServiceEntity);
		return get(id);
	}

	@Override
	public UserServiceDTO update(UserServiceEntity userServiceEntity) {
		this.dynamicMerge(userServiceEntity);
		return get(userServiceEntity.getUsId());
	}

	@Override
	public UserServiceDTO updateAll(UserServiceEntity userServiceEntity) {
		this.merge(userServiceEntity);
		return get(userServiceEntity.getUsId());
	}

	@Override
	public UserServiceDTO updateBySql(UserServiceDTO userServiceDTO) {
		if(null == userServiceDTO) return null;
		this.execute("userService.updateUserService", toMap(userServiceDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == userServiceDTO.getUsId()) return null;
		return get(userServiceDTO.getUsId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<UserServiceEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("userService.insertUserService", params);
	}

	@Override
	public void batchUpdate(List<UserServiceDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("userService.updateUserService", params);
	}

	@Override
	public UserServiceDTO get(long id) {
		return getById(id);
	}

	@Override
	public UserServiceDTO get(UserServiceDTO userServiceDTO) {
		if(null == userServiceDTO) {
			return null;
		}
		Map<String, Object> param = toMap(userServiceDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("userService.getSimpleUserServiceList", param);
	}

	@Override
	public List<UserServiceDTO> getSimpleList(UserServiceDTO userServiceDTO) {
		Map<String, Object> param = toMap(userServiceDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("userService.getSimpleUserServiceList", param);
	}

	@Override
	public PageList<UserServiceDTO> getSimpleListForPage(UserServiceDTO userServiceDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(userServiceDTO);
		return this.queryForPage("userService.getSimpleUserServiceListCount", "userService.getSimpleUserServiceList", queryParam);
	}

	@Override
	public PageList<UserServiceDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("userService.getSimpleUserServiceListCount", "userService.getSimpleUserServiceList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("userService.getUserServiceList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("userService.getUserServiceListCount", "userService.getUserServiceList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("userService.getUserServiceListCount", "userService.getUserServiceList", queryParam, clazz);
	}

	@Override
	public Integer getUserServiceNum(Long userId, Long serviceId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("serviceId", serviceId);
		int num = queryForObject("userService.getUserServiceNum", paramMap, Integer.class);
		return num;
	}

	@Override
	public Integer getServiceNumOfTeam(Long teamId, Long serviceId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("teamId", teamId);
		paramMap.put("serviceId", serviceId);
		int num = queryForObject("userService.getUserServiceNum", paramMap, Integer.class);
		return num;
	}

}