/** create by auto at 2018-05-07 09:32:07**/
package com.booting.coupon.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.coupon.dto.CouponRelationDTO;
import com.booting.coupon.entity.CouponRelationEntity;
import com.booting.coupon.service.CouponRelationService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("couponRelationService")
public class CouponRelationServiceImpl extends JDBCSupport<CouponRelationEntity, CouponRelationDTO> implements CouponRelationService{

	private static final long serialVersionUID = 1L;

	@Override
	public CouponRelationDTO save(CouponRelationEntity couponRelationEntity) {
		long id = this.persist(couponRelationEntity);
		return get(id);
	}

	@Override
	public CouponRelationDTO update(CouponRelationEntity couponRelationEntity) {
		this.dynamicMerge(couponRelationEntity);
		return get(couponRelationEntity.getId());
	}

	@Override
	public CouponRelationDTO updateAll(CouponRelationEntity couponRelationEntity) {
		this.merge(couponRelationEntity);
		return get(couponRelationEntity.getId());
	}

	@Override
	public CouponRelationDTO updateBySql(CouponRelationDTO couponRelationDTO) {
		if(null == couponRelationDTO) return null;
		this.execute("couponRelation.updateCouponRelation", toMap(couponRelationDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == couponRelationDTO.getId()) return null;
		return get(couponRelationDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<CouponRelationEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("couponRelation.insertCouponRelation", params);
	}

	@Override
	public void batchUpdate(List<CouponRelationDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("couponRelation.updateCouponRelation", params);
	}

	@Override
	public CouponRelationDTO get(long id) {
		return getById(id);
	}

	@Override
	public CouponRelationDTO get(CouponRelationDTO couponRelationDTO) {
		if(null == couponRelationDTO) {
			return null;
		}
		Map<String, Object> param = toMap(couponRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("couponRelation.getSimpleCouponRelationList", param);
	}

	@Override
	public List<CouponRelationDTO> getSimpleList(CouponRelationDTO couponRelationDTO) {
		Map<String, Object> param = toMap(couponRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("couponRelation.getSimpleCouponRelationList", param);
	}

	@Override
	public PageList<CouponRelationDTO> getSimpleListForPage(CouponRelationDTO couponRelationDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(couponRelationDTO);
		return this.queryForPage("couponRelation.getSimpleCouponRelationListCount", "couponRelation.getSimpleCouponRelationList", queryParam);
	}

	@Override
	public PageList<CouponRelationDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("couponRelation.getSimpleCouponRelationListCount", "couponRelation.getSimpleCouponRelationList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("couponRelation.getCouponRelationList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("couponRelation.getCouponRelationListCount", "couponRelation.getCouponRelationList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("couponRelation.getCouponRelationListCount", "couponRelation.getCouponRelationList", queryParam, clazz);
	}

	@Override
	public void deleteCouponRelationByBusiness(String businessType, Long businessId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("businessName", businessType);
		paramMap.put("businessId", businessId);
		this.execute("couponRelation.deleteCouponRelation", paramMap);
	}

}