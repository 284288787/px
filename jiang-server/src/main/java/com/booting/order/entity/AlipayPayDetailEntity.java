/**create by liuhua at 2017年6月7日 上午9:59:58**/
package com.booting.order.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "支付宝回调数据")
@Entity(name = "order_alipay")
public class AlipayPayDetailEntity implements Serializable {
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
		
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "buyerId")
	public String getBuyerId() {
		return buyerId;
	}
	@Column(name = "buyerEmail")
	public String getBuyerEmail() {
		return buyerEmail;
	}
	@Column(name = "sellerId")
	public String getSellerId() {
		return sellerId;
	}
	@Column(name = "sellerEmail")
	public String getSellerEmail() {
		return sellerEmail;
	}
	@Column(name = "paymentType")
	public String getPaymentType() {
		return paymentType;
	}
	@Column(name = "subject")
	public String getSubject() {
		return subject;
	}
	@Column(name = "tradeNo")
	public String getTradeNo() {
		return tradeNo;
	}
	@Column(name = "gmtCreate")
	public Date getGmtCreate() {
		return gmtCreate;
	}
	@Column(name = "price")
	public Double getPrice() {
		return price;
	}
	@Column(name = "discount")
	public Double getDiscount() {
		return discount;
	}
	@Column(name = "notifyType")
	public String getNotifyType() {
		return notifyType;
	}
	@Column(name = "notifyTime")
	public Date getNotifyTime() {
		return notifyTime;
	}
	@Column(name = "notifyId")
	public String getNotifyId() {
		return notifyId;
	}
	@Column(name = "quantity")
	public String getQuantity() {
		return quantity;
	}
	@Column(name = "outTradeNo")
	public String getOutTradeNo() {
		return outTradeNo;
	}
	@Column(name = "body")
	public String getBody() {
		return body;
	}
	@Column(name = "tradeStatus")
	public String getTradeStatus() {
		return tradeStatus;
	}
	@Column(name = "isTotalFeeAdjust")
	public String getIsTotalFeeAdjust() {
		return isTotalFeeAdjust;
	}
	@Column(name = "totalFee")
	public Double getTotalFee() {
		return totalFee;
	}
	@Column(name = "gmtPayment")
	public Date getGmtPayment() {
		return gmtPayment;
	}
	@Column(name = "useCoupon")
	public String getUseCoupon() {
		return useCoupon;
	}
	@Column(name = "signType")
	public String getSignType() {
		return signType;
	}
	@Column(name = "passbackParams")
	public String getPassbackParams() {
		return passbackParams;
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
	public void setPassbackParams(String passbackParams) {
		this.passbackParams = passbackParams;
	}
}
