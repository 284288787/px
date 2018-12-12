/**create by liuhua at 2017年8月15日 上午10:13:46**/
package com.booting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.coupon.dto.CouponDTO;
import com.booting.coupon.facade.CouponFacade;
import com.booting.service.CouponWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.result.PageInfo;
import com.star.framework.specification.result.v2.ApiResult;

@Service("couponWebService")
public class CouponWebServiceImpl extends BaseWebService implements CouponWebService {

	@Autowired
	private CouponFacade couponFacade;
	
	@Override
	public ApiResult searchCoupons(QueryParam queryParam) {
		PageList<CouponDTO> couponPageList = this.couponFacade.getCouponListForPage(queryParam);
		List<CouponDTO> list = couponPageList.getDataList();
		ApiResult apiResult = new ApiResult();
		apiResult.setData(list);
		PageInfo pageInfo = new PageInfo(couponPageList.getPageNo(), couponPageList.getPageSize(), couponPageList.getTotalRecord());
		apiResult.setPageInfo(pageInfo);
		return apiResult;
	}

}
