/**create by liuhua at 2018年5月7日 上午9:15:53**/
package com.booting.coupon.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "关系")
@Entity(name = "coupon_relation")
public class CouponRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cardId;          //卡券id
	private String cardType;        //卡类型 团购券：GROUPON; 折扣券：DISCOUNT; 礼品券：GIFT; 代金券：CASH; 通用券：GENERAL_COUPON;
	private String cardName;        //卡券名称
	private Integer getLimit;       //没人限领次数
	private String businessType;    //CouponBusinessType
	private Long businessId;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "cardId")
	public String getCardId() {
		return cardId;
	}
	@Column(name = "cardType")
	public String getCardType() {
		return cardType;
	}
	@Column(name = "cardName")
	public String getCardName() {
		return cardName;
	}
	@Column(name = "businessType")
	public String getBusinessType() {
		return businessType;
	}
	@Column(name = "businessId")
	public Long getBusinessId() {
		return businessId;
	}
	@Column(name = "getLimit")
	public Integer getGetLimit() {
		return getLimit;
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
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public void setGetLimit(Integer getLimit) {
		this.getLimit = getLimit;
	}
}
