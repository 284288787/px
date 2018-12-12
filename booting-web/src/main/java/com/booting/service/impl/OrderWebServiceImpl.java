/**create by liuhua at 2017年7月14日 下午4:29:33**/
package com.booting.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.common.CommonConstants;
import com.booting.common.WeixinConst;
import com.booting.common.CommonConstants.CouponIds;
import com.booting.common.CommonConstants.ProductType;
import com.booting.common.CommonConstants.UserService;
import com.booting.coupon.dto.CouponDTO;
import com.booting.coupon.dto.UserCouponDTO;
import com.booting.coupon.facade.CouponFacade;
import com.booting.order.dto.AlipayPayDetailDTO;
import com.booting.order.dto.OrderDTO;
import com.booting.order.dto.OrderDetailDTO;
import com.booting.order.dto.WeixinPayDetailDTO;
import com.booting.order.facade.OrderFacade;
import com.booting.pkg.dto.AttributeDTO;
import com.booting.pkg.dto.PackageDTO;
import com.booting.pkg.dto.ServiceDTO;
import com.booting.pkg.dto.UserPackageDTO;
import com.booting.pkg.dto.UserServiceDTO;
import com.booting.pkg.entity.UserAttributeEntity;
import com.booting.service.OrderWebService;
import com.star.framework.pay.alipay.AlipayUtil;
import com.star.framework.pay.alipay.domain.AlipayReqData;
import com.star.framework.pay.weixin.WeixinUtil;
import com.star.framework.pay.weixin.domain.PayReqData;
import com.star.framework.pay.weixin.domain.PayResData;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.utils.CglibBeanUtils;

@Service("orderWebService")
public class OrderWebServiceImpl extends BaseWebService implements OrderWebService {

	@Autowired
	private OrderFacade orderFacade;
	@Autowired
	private CouponFacade couponFacade;

	@Override
	public OrderDTO getOrder(Long orderId) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(orderId);
		orderDTO = this.orderFacade.getOrder(orderDTO);
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(orderId);
		List<OrderDetailDTO> details = orderFacade.getOrderDetailList(orderDetailDTO);
		orderDTO.setDetails(details);
		return orderDTO;
	}
	
	@Override
	public OrderDTO getOrder(String orderNo) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderNo(orderNo);
		orderDTO = this.orderFacade.getOrder(orderDTO);
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setOrderId(orderDTO.getOrderId());
		List<OrderDetailDTO> details = orderFacade.getOrderDetailList(orderDetailDTO);
		orderDTO.setDetails(details);
		return orderDTO;
	}

	@Override
	public Map<String, Object> unifiedOrder(OrderDTO orderDTO) throws ArgsException {
		String orderNo = UUID.randomUUID().toString().replace("-", "");
		orderDTO.setOrderNo(orderNo);
		orderDTO.setCreateTime(new Date());
		Long teamId = getTeamIdByUserId(orderDTO.getUserId());
		if (null == teamId && orderDTO.getType() == 1) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "请先建立球队，才可以购买套餐");
		}
		List<OrderDetailDTO> details = orderDTO.getDetails();
		if (null == details || details.isEmpty()) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "没有商品明细");
		}
		int money = 0;
		for (OrderDetailDTO orderDetailDTO : details) {
			Integer productType = orderDetailDTO.getProductType();
			if (productType.intValue() == ProductType.pkg.getType()) {
				PackageDTO packageDTO = packageFacade.getPackage(orderDetailDTO.getProductId());
				if (null == packageDTO) {
					throw new ArgsException(FailureCode.ERR_002.getCode(), "套餐没有找到");
				}
				Integer price = packageDTO.getRealPrice();
				Integer quantity = orderDetailDTO.getQuantity();
				if (null == price || null == quantity) {
					throw new ArgsException(FailureCode.ERR_002.getCode(), "套餐数据有误");
				}
				orderDetailDTO.setProductName(packageDTO.getPackageName());
				orderDetailDTO.setPrice(packageDTO.getRealPrice());
				orderDetailDTO.setAmount(price * quantity);
				money += orderDetailDTO.getAmount();
			}else if(productType.intValue() == ProductType.coupon.getType()){
				CouponDTO couponDTO = this.couponFacade.getCoupon(orderDetailDTO.getProductId());
				Date now = new Date();
				if (!(null != couponDTO && couponDTO.getStatus() == 1 && now.after(couponDTO.getMarketTime()) && now.before(couponDTO.getFinishTime()))) {
					throw new ArgsException(FailureCode.ERR_002.getCode(), "优惠券没有找到或已失效");
				}
				Integer price = couponDTO.getPrice();
				Integer quantity = orderDetailDTO.getQuantity();
				if (null == price || null == quantity) {
					throw new ArgsException(FailureCode.ERR_002.getCode(), "套餐数据有误");
				}
				orderDetailDTO.setProductName(couponDTO.getCouponName());
				orderDetailDTO.setPrice(couponDTO.getPrice());
				orderDetailDTO.setAmount(price * quantity);
				money += orderDetailDTO.getAmount();
			} else {
				throw new ArgsException(FailureCode.ERR_002.getCode(), "未知的商品类型");
			}
		}
		orderDTO.setTotalMoney(money);
		orderDTO.setStatus(1);
		Long orderId = this.orderFacade.saveOrder(orderDTO);
		if (null != orderId) {
			for (OrderDetailDTO orderDetailDTO : details) {
				orderDetailDTO.setOrderId(orderId);
			}
			this.orderFacade.batchSaveOrderDetail(details);
		}
		Map<String, Object> payInfo = new HashMap<>();
		if (orderDTO.getPayType().intValue() == 2) {
			WeixinConst const1 = new WeixinConst();
			WeixinUtil weixinUtil = WeixinUtil.getInstance(const1);
			PayReqData prd = new PayReqData(orderDTO.getSubject(), orderDTO.getOrderNo(), orderDTO.getOrderNo(), orderDTO.getTotalMoney(), orderDTO.getCreateTime(), const1.getAppId(), const1.getKey(), const1.getMchId(), const1.getNotifyUrl(), const1.getServerIp());
			PayResData res = weixinUtil.unifiedOrder(prd);
			CglibBeanUtils.addToMap(res, payInfo);
			payInfo.remove("appid");
			payInfo.remove("mch_id");
		}else{
			double f = orderDTO.getTotalMoney() / 100.00;
			AlipayReqData alipayReqData = new AlipayReqData(orderDTO.getOrderNo(), orderDTO.getSubject(), String.format("%.2f", f), orderDTO.getSubject(), orderDTO.getOrderNo());
			AlipayUtil.getParams2(alipayReqData);
			payInfo.put("params", alipayReqData);
		}
		payInfo.put("money", orderDTO.getTotalMoney());
		payInfo.put("orderNo", orderNo);
		return payInfo;
	}

	@Override
	public boolean wxpayCallback(WeixinPayDetailDTO payOrder) {
		boolean bool = false;
		this.orderFacade.saveWeixinPayDetail(payOrder);
		if (null != payOrder && "SUCCESS".equals(payOrder.getResult_code())) {
			bool = hasFinish(payOrder.getAttach());
			if (! bool) {
				bool = finishOrder(payOrder.getAttach());
			}
		}
		return bool;
	}

	@Override
	public boolean alipayCallback(AlipayPayDetailDTO alipayOrder) {
		boolean bool = false;
		this.orderFacade.saveAlipayPayDetail(alipayOrder);
		if (alipayOrder.getTradeStatus().equals("TRADE_SUCCESS") || alipayOrder.getTradeStatus().equals("TRADE_FINISHED")) {
			bool = hasFinish(alipayOrder.getOutTradeNo());
			if (! bool) {
				bool = finishOrder(alipayOrder.getOutTradeNo());
			}
		}
		return bool;
	}
	
	private boolean hasFinish(String orderNo) {
		if (StringUtils.isNotBlank(orderNo)) {
			OrderDTO orderDTO = getOrder(orderNo);
			if (null != orderDTO) {
				if (orderDTO.getStatus().intValue() == 2) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean finishOrder(String orderNo) {
		if (StringUtils.isNotBlank(orderNo)) {
			OrderDTO orderDTO = getOrder(orderNo);
			if (null != orderDTO) {
				orderDTO.setStatus(2);
				orderDTO.setPayTime(new Date());
				orderDTO.setOutTradeNo(orderNo);
				int res = orderFacade.updateOrder(orderDTO);
				if (res > 0) {
					List<OrderDetailDTO> details = orderDTO.getDetails();
					for (OrderDetailDTO orderDetailDTO : details) {
						Integer productType = orderDetailDTO.getProductType();
						if (productType.intValue() == ProductType.pkg.getType()) {
							PackageDTO packageDTO = getPackage(orderDetailDTO.getProductId());
							Integer packageType = packageDTO.getPackageType();
							if (packageType == 1) {
								buyBasePkg(orderDTO.getUserId(), orderDTO.getOrderId(), packageDTO);
							}else{
								buyIncrPkg(orderDTO.getUserId(), orderDetailDTO.getQuantity(), orderDTO.getOrderId(), packageDTO);
							}
						}else if (productType.intValue() == ProductType.coupon.getType()) {
							CouponDTO couponDTO = this.couponFacade.getCoupon(orderDetailDTO.getProductId());
							Long teamId = getTeamIdByUserId(orderDTO.getUserId());
							buyCoupon(orderDTO.getUserId(), teamId, orderDetailDTO.getQuantity(), orderDTO.getOrderId(), couponDTO);
						}
					}
					return true;
				}
			}
		}
		return false;
	}
	
	private void buyCoupon(Long userId, Long teamId, Integer quantity, Long orderId, CouponDTO couponDTO) {
		UserCouponDTO userCouponDTO = new UserCouponDTO();
		userCouponDTO.setCouponId(couponDTO.getCouponId());
		userCouponDTO.setCouponIntro(couponDTO.getCouponIntro());
		userCouponDTO.setCouponScope(couponDTO.getCouponScope());
		userCouponDTO.setCouponType(couponDTO.getCouponType());
		userCouponDTO.setCouponValue(couponDTO.getCouponValue());
		userCouponDTO.setDrawTime(new Date());
		userCouponDTO.setQuantity(quantity);
		userCouponDTO.setStatus(1);
		userCouponDTO.setUserId(userId);
		userCouponDTO.setValidTime(couponDTO.getValidTime());
		userCouponDTO.setDeadTime(couponDTO.getDeadTime());
		userCouponDTO.setValueType(couponDTO.getValueType());
		userCouponDTO.setOrderId(orderId);
		userCouponDTO.setTeamId(teamId);
		this.couponFacade.saveUserCoupon(userCouponDTO);
	}

	private void buyBasePkg(Long userId, Long orderId, PackageDTO packageDTO) {
		Calendar calendar = Calendar.getInstance();
		Date beginTime = calendar.getTime();
		calendar.add(Calendar.YEAR, 1);
		Date endTime = calendar.getTime();
		UserPackageDTO userPackage = new UserPackageDTO();
		userPackage.setUserId(userId);
		userPackage.setPackageId(packageDTO.getPackageId());
		userPackage.setPackageName(packageDTO.getPackageName());
		userPackage.setPackageType(packageDTO.getPackageType());
		userPackage.setPrice(packageDTO.getPrice());
		userPackage.setDiscount(packageDTO.getDiscount());
		userPackage.setCount(1);
		userPackage.setCreateTime(beginTime);
		userPackage.setBeginTime(beginTime);
		userPackage.setEndTime(endTime);
		userPackage.setSourceFrom(1);
		userPackage.setOrderId(orderId);
		userPackage.setTeamId(getTeamIdByUserId(userId));
		Long upId = this.packageFacade.saveUserPackage(userPackage);
		insertServices(userId, userPackage.getTeamId(), packageDTO, beginTime, endTime, upId, CommonConstants.zbxq);
	}
	
	private void buyIncrPkg(Long userId, Integer count, Long orderId, PackageDTO packageDTO) {
		Calendar calendar = Calendar.getInstance();
		Date beginTime = calendar.getTime();
		calendar.add(Calendar.YEAR, 1);
		Date endTime = calendar.getTime();
		UserPackageDTO userPackage = new UserPackageDTO();
		userPackage.setUserId(userId);
		userPackage.setPackageId(packageDTO.getPackageId());
		userPackage.setPackageName(packageDTO.getPackageName());
		userPackage.setPackageType(packageDTO.getPackageType());
		userPackage.setPrice(packageDTO.getPrice());
		userPackage.setDiscount(packageDTO.getDiscount());
		userPackage.setCount(count);
		userPackage.setCreateTime(beginTime);
		userPackage.setBeginTime(null);
		userPackage.setEndTime(null);
		userPackage.setSourceFrom(1);
		userPackage.setOrderId(orderId);
		userPackage.setTeamId(getTeamIdByUserId(userId));
		Long upId = this.packageFacade.saveUserPackage(userPackage);
		
		for (int i = 1; i <= count; i++) {
			insertServices(userId, userPackage.getTeamId(), packageDTO, beginTime, endTime, upId, false);
		}
	}

	private void insertServices(Long userId, Long teamId, PackageDTO packageDTO, Date beginTime, Date endTime, Long upId, boolean zbxq) {
		int count1001 = 0;
		List<ServiceDTO> services = packageDTO.getServices();
		if (null != services && ! services.isEmpty()) {
			List<UserAttributeEntity> attributeEntities = new ArrayList<>();
			for (ServiceDTO service : services) {
				if (zbxq && service.getServiceId().longValue() == UserService.ball_ticket.getServiceId()) {
					count1001 = service.getCount();
				}
				if (service.getServiceId().longValue() == UserService.insurance_ticket_z.getServiceId()) {
					continue;
				}
				UserServiceDTO entity = new UserServiceDTO();
				entity.setUserId(userId);
				entity.setUpId(upId);
				entity.setServiceId(service.getServiceId());
				entity.setServiceName(service.getServiceName());
				entity.setServiceCount(service.getCount());
				entity.setCreateTime(beginTime);
				entity.setBeginTime(beginTime);
				entity.setEndTime(endTime);
				entity.setTeamId(teamId);
				Long usId = this.packageFacade.saveUserService(entity);
				List<AttributeDTO> attributes = service.getAttributes();
				if (null != attributes && ! attributes.isEmpty()) {
					for (AttributeDTO attribute : attributes) {
						UserAttributeEntity attributeEntity = new UserAttributeEntity();
						attributeEntity.setUsId(usId);
						attributeEntity.setAttributeId(attribute.getAttributeId());
						attributeEntity.setAttributeName(attribute.getAttributeName());
						attributeEntity.setAttributeValue(attribute.getAttributeValue());
						attributeEntity.setCreateTime(new Date());
						attributeEntity.setUserId(userId);
						attributeEntity.setTeamId(teamId);
						attributeEntities.add(attributeEntity);
					}
				}
			}
			this.packageFacade.batchSaveUserAttribute(attributeEntities);
		}
		if (zbxq && count1001 > 0) {
			CouponDTO couponDTO = this.couponFacade.getCoupon(CouponIds.insurance_ticket_z.getCouponId());
			buyCoupon(userId, teamId, count1001, null, couponDTO);
		}
	}
}
