/**create by liuhua at 2017年7月13日 下午2:21:40**/
package com.booting.order.dto;

import java.io.Serializable;

import com.star.framework.aop.annotation.Description;

@Description(name = "订单详情")
public class OrderDetailDTO implements Serializable {
	private static final long serialVersionUID = -8730327166078371217L;
	
	private Long id;
	private Long orderId;
	private Long productId;        //购买的产品
	private Integer productType;   //产品的类型 1套餐 2场地 3优惠券
	private String productName;    //产品的名称
	private Integer price;         //单价：实际金额*100
	private Integer quantity;      //购买的数量
	private Integer amount;        //总价：单价*数量
	
		
	public Long getId() {
		return id;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	
	public Long getProductId() {
		return productId;
	}
	
	public Integer getProductType() {
		return productType;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public void setProductType(Integer productType) {
		this.productType = productType;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
