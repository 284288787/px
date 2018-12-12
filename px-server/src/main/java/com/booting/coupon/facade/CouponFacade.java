/** create by auto at 2018-05-07 09:32:07**/
package com.booting.coupon.facade;

import java.io.Serializable;
import java.util.List;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.booting.coupon.dto.CouponRelationDTO;
import com.booting.coupon.entity.CouponRelationEntity;

public interface CouponFacade extends Serializable {

	/**
	 * 新增 关系
	 */
	public Long saveCouponRelation(CouponRelationDTO couponRelationDTO);

	/**
	 * 批量新增 关系
	 */
	public void batchSaveCouponRelation(List<CouponRelationDTO> dtos);

	/**
	 * 更新 关系
	 */
	public int updateCouponRelation(CouponRelationDTO couponRelationDTO);

	/**
	 * 批量 关系
	 */
	public void batchUpdateCouponRelation(List<CouponRelationDTO> dtos);

	/**
	 * 删除 关系
	 */
	public int deleteCouponRelation(long id);

	/**
	 * 根据主键获取 关系
	 */
	public CouponRelationDTO getCouponRelation(long id);

	/**
	 * 根据条件获取一条 关系
	 */
	public CouponRelationDTO getCouponRelation(CouponRelationDTO couponRelationDTO);

	/**
	 * 查询满足条件的 关系 列表(单表)
	 */
	public List<CouponRelationDTO> getCouponRelationList(CouponRelationDTO couponRelationDTO);

	/**
	 * 查询满足条件的 关系 列表(分页)(单表)
	 */
	public PageList<CouponRelationDTO> getCouponRelationListForPage(CouponRelationDTO couponRelationDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 关系 列表(分页)(单表)
	 */
	public PageList<CouponRelationDTO> getCouponRelationListForPage(QueryParam queryParam);

	/**
	 * 关系DTO 转换成 Entity
	 */
	public CouponRelationEntity toCouponRelationEntity(CouponRelationDTO couponRelationDTO);

	/**
	 * 关系DTOs 转换成 Entities
	 */
	public List<CouponRelationEntity> toCouponRelationEntities(List<CouponRelationDTO> dtoes);

	/**
	 * 查询满足条件的 列表(多表)
	 */
	public <T> List<T> getList(T dto);

	/**
	 * 查询满足条件的列表(分页)(多表)
	 */
	public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的列表(分页)(多表)
	 */
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz);

	public void deleteCouponRelationByBusiness(String businessType, Long businessId);

}