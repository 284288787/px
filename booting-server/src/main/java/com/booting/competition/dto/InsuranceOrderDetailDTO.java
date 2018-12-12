/**create by liuhua at 2017年9月15日 下午4:45:38**/
package com.booting.competition.dto;

import java.io.Serializable;

import com.star.framework.aop.annotation.Description;

@Description(name = "保险订单详情")
public class InsuranceOrderDetailDTO implements Serializable {
	private static final long serialVersionUID = -1252849482640605781L;
	
	private Long id;
	private Long ioId;
	private String orderId;
	private String insuredBgnTime;
	private String insuredEndTime;
	
		
	public Long getId() {
		return id;
	}
	
	public Long getIoId() {
		return ioId;
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public String getInsuredBgnTime() {
		return insuredBgnTime;
	}
	
	public String getInsuredEndTime() {
		return insuredEndTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIoId(Long ioId) {
		this.ioId = ioId;
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
