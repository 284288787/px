/**create by liuhua at 2017年8月15日 上午10:13:21**/
package com.booting.service;

import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.result.v2.ApiResult;

public interface CouponWebService {

	public ApiResult searchCoupons(QueryParam queryParam);

}
