/** create by auto at 2018-03-03 09:21:19**/
package com.booting.lottery.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.lottery.dto.UserNumDetailDTO;
import com.booting.lottery.entity.UserNumDetailEntity;
import com.booting.lottery.service.UserNumDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("userNumDetailService")
public class UserNumDetailServiceImpl extends JDBCSupport<UserNumDetailEntity, UserNumDetailDTO> implements UserNumDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public UserNumDetailDTO save(UserNumDetailEntity userNumDetailEntity) {
		long id = this.persist(userNumDetailEntity);
		return get(id);
	}

	@Override
	public UserNumDetailDTO update(UserNumDetailEntity userNumDetailEntity) {
		this.dynamicMerge(userNumDetailEntity);
		return get(userNumDetailEntity.getId());
	}

	@Override
	public UserNumDetailDTO updateAll(UserNumDetailEntity userNumDetailEntity) {
		this.merge(userNumDetailEntity);
		return get(userNumDetailEntity.getId());
	}

	@Override
	public UserNumDetailDTO updateBySql(UserNumDetailDTO userNumDetailDTO) {
		if(null == userNumDetailDTO) return null;
		this.execute("userNumDetail.updateUserNumDetail", toMap(userNumDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == userNumDetailDTO.getId()) return null;
		return get(userNumDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<UserNumDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("userNumDetail.insertUserNumDetail", params);
	}

	@Override
	public void batchUpdate(List<UserNumDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("userNumDetail.updateUserNumDetail", params);
	}

	@Override
	public UserNumDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public UserNumDetailDTO get(UserNumDetailDTO userNumDetailDTO) {
		if(null == userNumDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(userNumDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("userNumDetail.getSimpleUserNumDetailList", param);
	}

	@Override
	public List<UserNumDetailDTO> getSimpleList(UserNumDetailDTO userNumDetailDTO) {
		Map<String, Object> param = toMap(userNumDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("userNumDetail.getSimpleUserNumDetailList", param);
	}

	@Override
	public PageList<UserNumDetailDTO> getSimpleListForPage(UserNumDetailDTO userNumDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(userNumDetailDTO);
		return this.queryForPage("userNumDetail.getSimpleUserNumDetailListCount", "userNumDetail.getSimpleUserNumDetailList", queryParam);
	}

	@Override
	public PageList<UserNumDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("userNumDetail.getSimpleUserNumDetailListCount", "userNumDetail.getSimpleUserNumDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("userNumDetail.getUserNumDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("userNumDetail.getUserNumDetailListCount", "userNumDetail.getUserNumDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("userNumDetail.getUserNumDetailListCount", "userNumDetail.getUserNumDetailList", queryParam, clazz);
	}

	@Override
	public List<UserNumDetailDTO> getNumberList(Map<String, Object> params) {
		return this.queryForList("userNumDetail.getSimpleUserNumDetailList", params);
	}

}