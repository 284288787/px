/**create by liuhua at 2017年9月15日 下午4:57:52**/
package com.booting.insurance;

public class OrderDetail{
	private String orderId;
	private String insuredBgnTime;
	private String insuredEndTime;
	
	public String getOrderId() {
		return orderId;
	}
	public String getInsuredBgnTime() {
		return insuredBgnTime;
	}
	public String getInsuredEndTime() {
		return insuredEndTime;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setInsuredBgnTime(String insuredBgnTime) {
		this.insuredBgnTime = insuredBgnTime;
	}
	public void setInsuredEndTime(String insuredEndTime) {
		this.insuredEndTime = insuredEndTime;
	}
}
