/** create by auto at 2017-08-08 15:31:24**/
package com.booting.coupon.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.coupon.dto.UsedCouponDetailDTO;
import com.booting.coupon.entity.UsedCouponDetailEntity;
import com.booting.coupon.service.UsedCouponDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("usedCouponDetailService")
public class UsedCouponDetailServiceImpl extends JDBCSupport<UsedCouponDetailEntity, UsedCouponDetailDTO> implements UsedCouponDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public UsedCouponDetailDTO save(UsedCouponDetailEntity usedCouponDetailEntity) {
		long id = this.persist(usedCouponDetailEntity);
		return get(id);
	}

	@Override
	public UsedCouponDetailDTO update(UsedCouponDetailEntity usedCouponDetailEntity) {
		this.dynamicMerge(usedCouponDetailEntity);
		return get(usedCouponDetailEntity.getId());
	}

	@Override
	public UsedCouponDetailDTO updateAll(UsedCouponDetailEntity usedCouponDetailEntity) {
		this.merge(usedCouponDetailEntity);
		return get(usedCouponDetailEntity.getId());
	}

	@Override
	public UsedCouponDetailDTO updateBySql(UsedCouponDetailDTO usedCouponDetailDTO) {
		if(null == usedCouponDetailDTO) return null;
		this.execute("usedCouponDetail.updateUsedCouponDetail", toMap(usedCouponDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == usedCouponDetailDTO.getId()) return null;
		return get(usedCouponDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<UsedCouponDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("usedCouponDetail.insertUsedCouponDetail", params);
	}

	@Override
	public void batchUpdate(List<UsedCouponDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("usedCouponDetail.updateUsedCouponDetail", params);
	}

	@Override
	public UsedCouponDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public UsedCouponDetailDTO get(UsedCouponDetailDTO usedCouponDetailDTO) {
		if(null == usedCouponDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(usedCouponDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("usedCouponDetail.getSimpleUsedCouponDetailList", param);
	}

	@Override
	public List<UsedCouponDetailDTO> getSimpleList(UsedCouponDetailDTO usedCouponDetailDTO) {
		Map<String, Object> param = toMap(usedCouponDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("usedCouponDetail.getSimpleUsedCouponDetailList", param);
	}

	@Override
	public PageList<UsedCouponDetailDTO> getSimpleListForPage(UsedCouponDetailDTO usedCouponDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(usedCouponDetailDTO);
		return this.queryForPage("usedCouponDetail.getSimpleUsedCouponDetailListCount", "usedCouponDetail.getSimpleUsedCouponDetailList", queryParam);
	}

	@Override
	public PageList<UsedCouponDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("usedCouponDetail.getSimpleUsedCouponDetailListCount", "usedCouponDetail.getSimpleUsedCouponDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("usedCouponDetail.getUsedCouponDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("usedCouponDetail.getUsedCouponDetailListCount", "usedCouponDetail.getUsedCouponDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("usedCouponDetail.getUsedCouponDetailListCount", "usedCouponDetail.getUsedCouponDetailList", queryParam, clazz);
	}
	
	@Override
	public int getUsedCouponCountOfTeam(Long teamId, Long couponId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("teamId", teamId);
		paramMap.put("couponId", couponId);
		int num = queryForObject("usedCouponDetail.getUsedCouponCountOfTeam", paramMap, Integer.class);
		return num;
	}

	@Override
	public int getUsedCouponCount(Long userId, Long couponId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userId", userId);
		paramMap.put("couponId", couponId);
		int num = queryForObject("usedCouponDetail.getUsedCouponCountOfTeam", paramMap, Integer.class);
		return num;
	}

}