/** create by auto at 2018-05-07 09:32:07**/
package com.booting.coupon.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.coupon.dto.CouponRelationDTO;
import com.booting.coupon.entity.CouponRelationEntity;

/**
 * 关系服务
 *
 * @author auto
 *
 */
public interface CouponRelationService extends BaseService<CouponRelationEntity, CouponRelationDTO> {

	public void deleteCouponRelationByBusiness(String businessType, Long businessId);

}