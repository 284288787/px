/** create by auto at 2017-08-08 15:31:24**/
package com.booting.coupon.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.coupon.dto.CourtCouponDTO;
import com.booting.coupon.entity.CourtCouponEntity;
import com.booting.coupon.service.CourtCouponService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("courtCouponService")
public class CourtCouponServiceImpl extends JDBCSupport<CourtCouponEntity, CourtCouponDTO> implements CourtCouponService{

	private static final long serialVersionUID = 1L;

	@Override
	public CourtCouponDTO save(CourtCouponEntity courtCouponEntity) {
		long id = this.persist(courtCouponEntity);
		return get(id);
	}

	@Override
	public CourtCouponDTO update(CourtCouponEntity courtCouponEntity) {
		this.dynamicMerge(courtCouponEntity);
		return get(courtCouponEntity.getId());
	}

	@Override
	public CourtCouponDTO updateAll(CourtCouponEntity courtCouponEntity) {
		this.merge(courtCouponEntity);
		return get(courtCouponEntity.getId());
	}

	@Override
	public CourtCouponDTO updateBySql(CourtCouponDTO courtCouponDTO) {
		if(null == courtCouponDTO) return null;
		this.execute("courtCoupon.updateCourtCoupon", toMap(courtCouponDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == courtCouponDTO.getId()) return null;
		return get(courtCouponDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<CourtCouponEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("courtCoupon.insertCourtCoupon", params);
	}

	@Override
	public void batchUpdate(List<CourtCouponDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("courtCoupon.updateCourtCoupon", params);
	}

	@Override
	public CourtCouponDTO get(long id) {
		return getById(id);
	}

	@Override
	public CourtCouponDTO get(CourtCouponDTO courtCouponDTO) {
		if(null == courtCouponDTO) {
			return null;
		}
		Map<String, Object> param = toMap(courtCouponDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("courtCoupon.getSimpleCourtCouponList", param);
	}

	@Override
	public List<CourtCouponDTO> getSimpleList(CourtCouponDTO courtCouponDTO) {
		Map<String, Object> param = toMap(courtCouponDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("courtCoupon.getSimpleCourtCouponList", param);
	}

	@Override
	public PageList<CourtCouponDTO> getSimpleListForPage(CourtCouponDTO courtCouponDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(courtCouponDTO);
		return this.queryForPage("courtCoupon.getSimpleCourtCouponListCount", "courtCoupon.getSimpleCourtCouponList", queryParam);
	}

	@Override
	public PageList<CourtCouponDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("courtCoupon.getSimpleCourtCouponListCount", "courtCoupon.getSimpleCourtCouponList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("courtCoupon.getCourtCouponList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("courtCoupon.getCourtCouponListCount", "courtCoupon.getCourtCouponList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("courtCoupon.getCourtCouponListCount", "courtCoupon.getCourtCouponList", queryParam, clazz);
	}

}