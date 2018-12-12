/**create by liuhua at 2017年9月15日 下午4:45:38**/
package com.booting.competition.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "保险订单详情")
@Entity(name = "insurance_order_detail")
public class InsuranceOrderDetailEntity implements Serializable {
	private static final long serialVersionUID = -1252849482640605781L;
	
	private Long id;
	private Long ioId;
	private String orderId;
	private String insuredBgnTime;
	private String insuredEndTime;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "ioId")
	public Long getIoId() {
		return ioId;
	}
	@Column(name = "orderId")
	public String getOrderId() {
		return orderId;
	}
	@Column(name = "insuredBgnTime")
	public String getInsuredBgnTime() {
		return insuredBgnTime;
	}
	@Column(name = "insuredEndTime")
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
