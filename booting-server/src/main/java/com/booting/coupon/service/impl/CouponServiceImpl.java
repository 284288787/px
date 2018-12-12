/** create by auto at 2017-08-08 15:31:24**/
package com.booting.coupon.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.coupon.dto.CouponDTO;
import com.booting.coupon.entity.CouponEntity;
import com.booting.coupon.service.CouponService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("couponService")
public class CouponServiceImpl extends JDBCSupport<CouponEntity, CouponDTO> implements CouponService{

	private static final long serialVersionUID = 1L;

	@Override
	public CouponDTO save(CouponEntity couponEntity) {
		long id = this.persist(couponEntity);
		return get(id);
	}

	@Override
	public CouponDTO update(CouponEntity couponEntity) {
		this.dynamicMerge(couponEntity);
		return get(couponEntity.getCouponId());
	}

	@Override
	public CouponDTO updateAll(CouponEntity couponEntity) {
		this.merge(couponEntity);
		return get(couponEntity.getCouponId());
	}

	@Override
	public CouponDTO updateBySql(CouponDTO couponDTO) {
		if(null == couponDTO) return null;
		this.execute("coupon.updateCoupon", toMap(couponDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == couponDTO.getCouponId()) return null;
		return get(couponDTO.getCouponId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<CouponEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("coupon.insertCoupon", params);
	}

	@Override
	public void batchUpdate(List<CouponDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("coupon.updateCoupon", params);
	}

	@Override
	public CouponDTO get(long couponId) {
		return getById(couponId);
	}

	@Override
	public CouponDTO get(CouponDTO couponDTO) {
		if(null == couponDTO) {
			return null;
		}
		Map<String, Object> param = toMap(couponDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("coupon.getSimpleCouponList", param);
	}

	@Override
	public List<CouponDTO> getSimpleList(CouponDTO couponDTO) {
		Map<String, Object> param = toMap(couponDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("coupon.getSimpleCouponList", param);
	}

	@Override
	public PageList<CouponDTO> getSimpleListForPage(CouponDTO couponDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(couponDTO);
		return this.queryForPage("coupon.getSimpleCouponListCount", "coupon.getSimpleCouponList", queryParam);
	}

	@Override
	public PageList<CouponDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("coupon.getSimpleCouponListCount", "coupon.getSimpleCouponList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("coupon.getCouponList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("coupon.getCouponListCount", "coupon.getCouponList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("coupon.getCouponListCount", "coupon.getCouponList", queryParam, clazz);
	}

}