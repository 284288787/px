/**create by liuhua at 2017年9月15日 下午4:00:16**/
package com.booting.insurance;

import java.util.List;

public class InsuranceResult {
	
	private String RtnCode;
	private String RtnMsg;
	private String orderGroupId;
	private List<OrderDetail> orderIdList;
	
	public String toString(){
		return RtnCode + " " + RtnMsg + " " + orderGroupId;
	}
	public String getOrderGroupId() {
		return orderGroupId;
	}
	public List<OrderDetail> getOrderIdList() {
		return orderIdList;
	}
	public void setOrderGroupId(String orderGroupId) {
		this.orderGroupId = orderGroupId;
	}
	public void setOrderIdList(List<OrderDetail> orderIdList) {
		this.orderIdList = orderIdList;
	}
	public String getRtnCode() {
		return RtnCode;
	}
	public String getRtnMsg() {
		return RtnMsg;
	}
	public void setRtnCode(String rtnCode) {
		RtnCode = rtnCode;
	}
	public void setRtnMsg(String rtnMsg) {
		RtnMsg = rtnMsg;
	}
}
