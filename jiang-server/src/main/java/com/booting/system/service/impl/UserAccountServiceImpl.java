/** create by auto at 2017-05-24 09:04:42**/
package com.booting.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.system.dto.UserAccountDTO;
import com.booting.system.entity.UserAccountEntity;
import com.booting.system.service.UserAccountService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("userAccountService")
public class UserAccountServiceImpl extends JDBCSupport<UserAccountEntity, UserAccountDTO> implements UserAccountService{

	private static final long serialVersionUID = 1L;

	@Override
	public UserAccountDTO save(UserAccountEntity userAccountEntity) {
		long id = this.persist(userAccountEntity);
		return get(id);
	}

	@Override
	public UserAccountDTO update(UserAccountEntity userAccountEntity) {
		this.dynamicMerge(userAccountEntity);
		return get(userAccountEntity.getUserId());
	}

	@Override
	public UserAccountDTO updateAll(UserAccountEntity userAccountEntity) {
		this.merge(userAccountEntity);
		return get(userAccountEntity.getUserId());
	}

	@Override
	public UserAccountDTO updateBySql(UserAccountDTO userAccountDTO) {
		if (null == userAccountDTO) {
			return null;
		}
		this.execute("userAccount.updateUserAccount", toMap(userAccountDTO, "yyyy-MM-dd HH:mm:ss"));
		if (null == userAccountDTO.getUserId()) {
			return null;
		}
		return get(userAccountDTO.getUserId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<UserAccountEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("userAccount.insertUserAccount", params);
	}

	@Override
	public void batchUpdate(List<UserAccountDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("userAccount.updateUserAccount", params);
	}

	@Override
	public UserAccountDTO get(long userId) {
		return getById(userId);
	}

	@Override
	public UserAccountDTO get(UserAccountDTO userAccountDTO) {
		if(null == userAccountDTO) {
			return null;
		}
		Map<String, Object> param = toMap(userAccountDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("userAccount.getSimpleUserAccountList", param);
	}

	@Override
	public List<UserAccountDTO> getSimpleList(UserAccountDTO userAccountDTO) {
		Map<String, Object> param = toMap(userAccountDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("userAccount.getSimpleUserAccountList", param);
	}

	@Override
	public PageList<UserAccountDTO> getSimpleListForPage(UserAccountDTO userAccountDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(userAccountDTO);
		return this.queryForPage("userAccount.getSimpleUserAccountListCount", "userAccount.getSimpleUserAccountList", queryParam);
	}

	@Override
	public PageList<UserAccountDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("userAccount.getSimpleUserAccountListCount", "userAccount.getSimpleUserAccountList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("userAccount.getUserAccountList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("userAccount.getUserAccountListCount", "userAccount.getUserAccountList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("userAccount.getUserAccountListCount", "userAccount.getUserAccountList", queryParam, clazz);
	}

}