/** create by auto at 2017-08-08 15:31:24**/
package com.booting.coupon.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.coupon.dto.UsedCouponDetailDTO;
import com.booting.coupon.entity.UsedCouponDetailEntity;

/**
 * 球场优惠券服务
 *
 * @author auto
 *
 */
public interface UsedCouponDetailService extends BaseService<UsedCouponDetailEntity, UsedCouponDetailDTO> {

	int getUsedCouponCountOfTeam(Long teamId, Long couponId);

	int getUsedCouponCount(Long userId, Long couponId);

}