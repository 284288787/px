/** create by auto at 2017-05-24 09:23:10**/
package com.booting.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.system.dto.UserInfoDTO;
import com.booting.system.entity.UserInfoEntity;
import com.booting.system.service.UserInfoService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("userInfoService")
public class UserInfoServiceImpl extends JDBCSupport<UserInfoEntity, UserInfoDTO> implements UserInfoService{

	private static final long serialVersionUID = 1L;

	@Override
	public UserInfoDTO save(UserInfoEntity userInfoEntity) {
		long id = this.persist(userInfoEntity);
		return get(id);
	}

	@Override
	public UserInfoDTO update(UserInfoEntity userInfoEntity) {
		this.dynamicMerge(userInfoEntity);
		return get(userInfoEntity.getUserId());
	}

	@Override
	public UserInfoDTO updateAll(UserInfoEntity userInfoEntity) {
		this.merge(userInfoEntity);
		return get(userInfoEntity.getUserId());
	}

	@Override
	public UserInfoDTO updateBySql(UserInfoDTO userInfoDTO) {
		this.execute("userInfo.updateUserInfo", toMap(userInfoDTO, "yyyy-MM-dd HH:mm:ss"));
		return get(userInfoDTO.getUserId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<UserInfoEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("userInfo.insertUserInfo", params);
	}

	@Override
	public void batchUpdate(List<UserInfoDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("userInfo.updateUserInfo", params);
	}

	@Override
	public UserInfoDTO get(long userId) {
		return getById(userId);
	}

	@Override
	public UserInfoDTO get(UserInfoDTO userInfoDTO) {
		if(null == userInfoDTO) {
			return null;
		}
		Map<String, Object> param = toMap(userInfoDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("userInfo.getSimpleUserInfoList", param);
	}

	@Override
	public List<UserInfoDTO> getSimpleList(UserInfoDTO userInfoDTO) {
		Map<String, Object> param = toMap(userInfoDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("userInfo.getSimpleUserInfoList", param);
	}

	@Override
	public PageList<UserInfoDTO> getSimpleListForPage(UserInfoDTO userInfoDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(userInfoDTO);
		return this.queryForPage("userInfo.getSimpleUserInfoListCount", "userInfo.getSimpleUserInfoList", queryParam);
	}

	@Override
	public PageList<UserInfoDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("userInfo.getSimpleUserInfoListCount", "userInfo.getSimpleUserInfoList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("userInfo.getUserInfoList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("userInfo.getUserInfoListCount", "userInfo.getUserInfoList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("userInfo.getUserInfoListCount", "userInfo.getUserInfoList", queryParam, clazz);
	}

	@Override
	public int getCount(UserInfoDTO userInfoDTO) {
		Map<String, Object> param = toMap(userInfoDTO, "yyyy-MM-dd HH:mm:ss");
		int totalRecord = queryForObject("userInfo.getUserInfoListCount", param, Integer.class);
		return totalRecord;
	}

}