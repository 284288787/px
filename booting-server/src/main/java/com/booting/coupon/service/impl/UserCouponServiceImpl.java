/** create by auto at 2017-08-08 15:31:24**/
package com.booting.coupon.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.coupon.dto.UserCouponDTO;
import com.booting.coupon.entity.UserCouponEntity;
import com.booting.coupon.service.UserCouponService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("userCouponService")
public class UserCouponServiceImpl extends JDBCSupport<UserCouponEntity, UserCouponDTO> implements UserCouponService{

	private static final long serialVersionUID = 1L;

	@Override
	public UserCouponDTO save(UserCouponEntity userCouponEntity) {
		long id = this.persist(userCouponEntity);
		return get(id);
	}

	@Override
	public UserCouponDTO update(UserCouponEntity userCouponEntity) {
		this.dynamicMerge(userCouponEntity);
		return get(userCouponEntity.getId());
	}

	@Override
	public UserCouponDTO updateAll(UserCouponEntity userCouponEntity) {
		this.merge(userCouponEntity);
		return get(userCouponEntity.getId());
	}

	@Override
	public UserCouponDTO updateBySql(UserCouponDTO userCouponDTO) {
		if(null == userCouponDTO) return null;
		this.execute("userCoupon.updateUserCoupon", toMap(userCouponDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == userCouponDTO.getId()) return null;
		return get(userCouponDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<UserCouponEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("userCoupon.insertUserCoupon", params);
	}

	@Override
	public void batchUpdate(List<UserCouponDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("userCoupon.updateUserCoupon", params);
	}

	@Override
	public UserCouponDTO get(long id) {
		return getById(id);
	}

	@Override
	public UserCouponDTO get(UserCouponDTO userCouponDTO) {
		if(null == userCouponDTO) {
			return null;
		}
		Map<String, Object> param = toMap(userCouponDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("userCoupon.getSimpleUserCouponList", param);
	}

	@Override
	public List<UserCouponDTO> getSimpleList(UserCouponDTO userCouponDTO) {
		Map<String, Object> param = toMap(userCouponDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("userCoupon.getSimpleUserCouponList", param);
	}

	@Override
	public PageList<UserCouponDTO> getSimpleListForPage(UserCouponDTO userCouponDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(userCouponDTO);
		return this.queryForPage("userCoupon.getSimpleUserCouponListCount", "userCoupon.getSimpleUserCouponList", queryParam);
	}

	@Override
	public PageList<UserCouponDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("userCoupon.getSimpleUserCouponListCount", "userCoupon.getSimpleUserCouponList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("userCoupon.getUserCouponList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("userCoupon.getUserCouponListCount", "userCoupon.getUserCouponList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("userCoupon.getUserCouponListCount", "userCoupon.getUserCouponList", queryParam, clazz);
	}

}