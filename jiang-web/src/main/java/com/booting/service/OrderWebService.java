/**create by liuhua at 2017年7月14日 下午4:28:49**/
package com.booting.service;

import java.util.Map;

import com.booting.order.dto.AlipayPayDetailDTO;
import com.booting.order.dto.OrderDTO;
import com.booting.order.dto.WeixinPayDetailDTO;
import com.star.framework.specification.exception.ArgsException;

public interface OrderWebService {

	public OrderDTO getOrder(Long orderId);
	
	public OrderDTO getOrder(String orderNo);

	public Map<String, Object> unifiedOrder(OrderDTO orderDTO) throws ArgsException;

	public boolean wxpayCallback(WeixinPayDetailDTO payOrder);

	public boolean alipayCallback(AlipayPayDetailDTO alipayOrder);
}
