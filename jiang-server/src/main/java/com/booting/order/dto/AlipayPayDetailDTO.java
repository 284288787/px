/**create by liuhua at 2017年6月7日 上午9:59:58**/
package com.booting.order.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "支付宝回调数据")
public class AlipayPayDetailDTO implements Serializable {
	private static final long serialVersionUID = 6321457523960955887L;
	
	private Long id;
	// - 买家id
	private String buyerId;
	// - 买家帐号
	private String buyerEmail;
	// - 卖家id
	private String sellerId;
	// - 卖家帐号
	private String sellerEmail;
	// -
	private String paymentType;
	// -
	private String subject;
	// -
	private String tradeNo;
	// -
	private Date gmtCreate;
	// -
	private Double price;
	// -
	private Double discount;
	// -
	private String notifyType;
	// -
	private Date notifyTime;
	// -
	private String notifyId;
	// -
	private String quantity;
	// -
	private String outTradeNo;
	// -
	private String body;
	// -
	private String tradeStatus;
	// -
	private String isTotalFeeAdjust;
	// -
	private Double totalFee;
	// -
	private Date gmtPayment;
	// -
	private String useCoupon;
	// -
	private String signType;
	// 消息，原样返回
	private String passbackParams;
		
	public Long getId() {
		return id;
	}
	
	public String getBuyerId() {
		return buyerId;
	}
	
	public String getBuyerEmail() {
		return buyerEmail;
	}
	
	public String getSellerId() {
		return sellerId;
	}
	
	public String getSellerEmail() {
		return sellerEmail;
	}
	
	public String getPaymentType() {
		return paymentType;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getTradeNo() {
		return tradeNo;
	}
	
	public Date getGmtCreate() {
		return gmtCreate;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public Double getDiscount() {
		return discount;
	}
	
	public String getNotifyType() {
		return notifyType;
	}
	
	public Date getNotifyTime() {
		return notifyTime;
	}
	
	public String getNotifyId() {
		return notifyId;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public String getOutTradeNo() {
		return outTradeNo;
	}
	
	public String getBody() {
		return body;
	}
	
	public String getTradeStatus() {
		return tradeStatus;
	}
	
	public String getIsTotalFeeAdjust() {
		return isTotalFeeAdjust;
	}
	
	public Double getTotalFee() {
		return totalFee;
	}
	
	public Date getGmtPayment() {
		return gmtPayment;
	}
	
	public String getUseCoupon() {
		return useCoupon;
	}
	
	public String getSignType() {
		return signType;
	}
	
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	public void setNotifyTime(Date notifyTime) {
		this.notifyTime = notifyTime;
	}
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public void setIsTotalFeeAdjust(String isTotalFeeAdjust) {
		this.isTotalFeeAdjust = isTotalFeeAdjust;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	public void setGmtPayment(Date gmtPayment) {
		this.gmtPayment = gmtPayment;
	}
	public void setUseCoupon(String useCoupon) {
		this.useCoupon = useCoupon;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPassbackParams() {
		return passbackParams;
	}

	public void setPassbackParams(String passbackParams) {
		this.passbackParams = passbackParams;
	}
}
