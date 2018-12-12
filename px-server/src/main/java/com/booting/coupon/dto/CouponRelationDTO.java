/**create by liuhua at 2018年5月7日 上午9:15:53**/
package com.booting.coupon.dto;

import java.io.Serializable;

import com.star.framework.aop.annotation.Description;

@Description(name = "关系")
public class CouponRelationDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cardId;          //卡券id
	private String cardType;        //卡类型 团购券：GROUPON; 折扣券：DISCOUNT; 礼品券：GIFT; 代金券：CASH; 通用券：GENERAL_COUPON;
	private String cardName;        //卡券名称
	private Integer getLimit;       //没人限领次数
	private String businessType;    //CouponBusinessType
	private Long businessId;
	
	public Long getId() {
		return id;
	}
	
	public String getCardId() {
		return cardId;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public String getBusinessType() {
		return businessType;
	}
	
	public Long getBusinessId() {
		return businessId;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Integer getGetLimit() {
		return getLimit;
	}

	public void setGetLimit(Integer getLimit) {
		this.getLimit = getLimit;
	}
}
