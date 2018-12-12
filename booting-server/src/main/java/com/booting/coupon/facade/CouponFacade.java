/** create by auto at 2017-08-08 15:31:24**/
package com.booting.coupon.facade;

import java.io.Serializable;
import java.util.List;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.booting.coupon.dto.CouponDTO;
import com.booting.coupon.entity.CouponEntity;
import com.booting.coupon.dto.CourtCouponDTO;
import com.booting.coupon.entity.CourtCouponEntity;
import com.booting.coupon.dto.UsedCouponDetailDTO;
import com.booting.coupon.entity.UsedCouponDetailEntity;
import com.booting.coupon.dto.UserCouponDTO;
import com.booting.coupon.entity.UserCouponEntity;

public interface CouponFacade extends Serializable {

	/**
	 * 新增 优惠券
	 */
	public Long saveCoupon(CouponDTO couponDTO);

	/**
	 * 批量新增 优惠券
	 */
	public void batchSaveCoupon(List<CouponDTO> dtos);

	/**
	 * 更新 优惠券
	 */
	public int updateCoupon(CouponDTO couponDTO);

	/**
	 * 批量 优惠券
	 */
	public void batchUpdateCoupon(List<CouponDTO> dtos);

	/**
	 * 删除 优惠券
	 */
	public int deleteCoupon(long couponId);

	/**
	 * 根据主键获取 优惠券
	 */
	public CouponDTO getCoupon(long couponId);

	/**
	 * 根据条件获取一条 优惠券
	 */
	public CouponDTO getCoupon(CouponDTO couponDTO);

	/**
	 * 查询满足条件的 优惠券 列表(单表)
	 */
	public List<CouponDTO> getCouponList(CouponDTO couponDTO);

	/**
	 * 查询满足条件的 优惠券 列表(分页)(单表)
	 */
	public PageList<CouponDTO> getCouponListForPage(CouponDTO couponDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 优惠券 列表(分页)(单表)
	 */
	public PageList<CouponDTO> getCouponListForPage(QueryParam queryParam);

	/**
	 * 优惠券DTO 转换成 Entity
	 */
	public CouponEntity toCouponEntity(CouponDTO couponDTO);

	/**
	 * 优惠券DTOs 转换成 Entities
	 */
	public List<CouponEntity> toCouponEntities(List<CouponDTO> dtoes);

	/**
	 * 新增 球场优惠券
	 */
	public Long saveCourtCoupon(CourtCouponDTO courtCouponDTO);

	/**
	 * 批量新增 球场优惠券
	 */
	public void batchSaveCourtCoupon(List<CourtCouponDTO> dtos);

	/**
	 * 更新 球场优惠券
	 */
	public int updateCourtCoupon(CourtCouponDTO courtCouponDTO);

	/**
	 * 批量 球场优惠券
	 */
	public void batchUpdateCourtCoupon(List<CourtCouponDTO> dtos);

	/**
	 * 删除 球场优惠券
	 */
	public int deleteCourtCoupon(long id);

	/**
	 * 根据主键获取 球场优惠券
	 */
	public CourtCouponDTO getCourtCoupon(long id);

	/**
	 * 根据条件获取一条 球场优惠券
	 */
	public CourtCouponDTO getCourtCoupon(CourtCouponDTO courtCouponDTO);

	/**
	 * 查询满足条件的 球场优惠券 列表(单表)
	 */
	public List<CourtCouponDTO> getCourtCouponList(CourtCouponDTO courtCouponDTO);

	/**
	 * 查询满足条件的 球场优惠券 列表(分页)(单表)
	 */
	public PageList<CourtCouponDTO> getCourtCouponListForPage(CourtCouponDTO courtCouponDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 球场优惠券 列表(分页)(单表)
	 */
	public PageList<CourtCouponDTO> getCourtCouponListForPage(QueryParam queryParam);

	/**
	 * 球场优惠券DTO 转换成 Entity
	 */
	public CourtCouponEntity toCourtCouponEntity(CourtCouponDTO courtCouponDTO);

	/**
	 * 球场优惠券DTOs 转换成 Entities
	 */
	public List<CourtCouponEntity> toCourtCouponEntities(List<CourtCouponDTO> dtoes);

	/**
	 * 新增 球场优惠券
	 */
	public Long saveUsedCouponDetail(UsedCouponDetailDTO usedCouponDetailDTO);

	/**
	 * 批量新增 球场优惠券
	 */
	public void batchSaveUsedCouponDetail(List<UsedCouponDetailDTO> dtos);

	/**
	 * 更新 球场优惠券
	 */
	public int updateUsedCouponDetail(UsedCouponDetailDTO usedCouponDetailDTO);

	/**
	 * 批量 球场优惠券
	 */
	public void batchUpdateUsedCouponDetail(List<UsedCouponDetailDTO> dtos);

	/**
	 * 删除 球场优惠券
	 */
	public int deleteUsedCouponDetail(long id);

	/**
	 * 根据主键获取 球场优惠券
	 */
	public UsedCouponDetailDTO getUsedCouponDetail(long id);

	/**
	 * 根据条件获取一条 球场优惠券
	 */
	public UsedCouponDetailDTO getUsedCouponDetail(UsedCouponDetailDTO usedCouponDetailDTO);

	/**
	 * 查询满足条件的 球场优惠券 列表(单表)
	 */
	public List<UsedCouponDetailDTO> getUsedCouponDetailList(UsedCouponDetailDTO usedCouponDetailDTO);

	/**
	 * 查询满足条件的 球场优惠券 列表(分页)(单表)
	 */
	public PageList<UsedCouponDetailDTO> getUsedCouponDetailListForPage(UsedCouponDetailDTO usedCouponDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 球场优惠券 列表(分页)(单表)
	 */
	public PageList<UsedCouponDetailDTO> getUsedCouponDetailListForPage(QueryParam queryParam);

	/**
	 * 球场优惠券DTO 转换成 Entity
	 */
	public UsedCouponDetailEntity toUsedCouponDetailEntity(UsedCouponDetailDTO usedCouponDetailDTO);

	/**
	 * 球场优惠券DTOs 转换成 Entities
	 */
	public List<UsedCouponDetailEntity> toUsedCouponDetailEntities(List<UsedCouponDetailDTO> dtoes);

	/**
	 * 新增 用户优惠券
	 */
	public Long saveUserCoupon(UserCouponDTO userCouponDTO);

	/**
	 * 批量新增 用户优惠券
	 */
	public void batchSaveUserCoupon(List<UserCouponDTO> dtos);

	/**
	 * 更新 用户优惠券
	 */
	public int updateUserCoupon(UserCouponDTO userCouponDTO);

	/**
	 * 批量 用户优惠券
	 */
	public void batchUpdateUserCoupon(List<UserCouponDTO> dtos);

	/**
	 * 删除 用户优惠券
	 */
	public int deleteUserCoupon(long id);

	/**
	 * 根据主键获取 用户优惠券
	 */
	public UserCouponDTO getUserCoupon(long id);

	/**
	 * 根据条件获取一条 用户优惠券
	 */
	public UserCouponDTO getUserCoupon(UserCouponDTO userCouponDTO);

	/**
	 * 查询满足条件的 用户优惠券 列表(单表)
	 */
	public List<UserCouponDTO> getUserCouponList(UserCouponDTO userCouponDTO);

	/**
	 * 查询满足条件的 用户优惠券 列表(分页)(单表)
	 */
	public PageList<UserCouponDTO> getUserCouponListForPage(UserCouponDTO userCouponDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 用户优惠券 列表(分页)(单表)
	 */
	public PageList<UserCouponDTO> getUserCouponListForPage(QueryParam queryParam);

	/**
	 * 用户优惠券DTO 转换成 Entity
	 */
	public UserCouponEntity toUserCouponEntity(UserCouponDTO userCouponDTO);

	/**
	 * 用户优惠券DTOs 转换成 Entities
	 */
	public List<UserCouponEntity> toUserCouponEntities(List<UserCouponDTO> dtoes);

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

	public int getUsedCouponCountOfTeam(Long teamId, Long couponId);
	
	public int getUsedCouponCount(Long userId, Long couponId);

}