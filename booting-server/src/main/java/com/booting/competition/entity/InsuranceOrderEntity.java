/**create by liuhua at 2017年9月15日 下午4:45:38**/
package com.booting.competition.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "保险订单")
@Entity(name = "insurance_order")
public class InsuranceOrderEntity implements Serializable {
	private static final long serialVersionUID = -1252849482640605781L;
	
	private Long ioId;
	private Long competitionId;
	private String orderGroupId;
	private Date createTime;
	
	@Id
	@Column(name = "ioId")
	public Long getIoId() {
		return ioId;
	}
	@Column(name = "competitionId")
	public Long getCompetitionId() {
		return competitionId;
	}
	@Column(name = "orderGroupId")
	public String getOrderGroupId() {
		return orderGroupId;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}
	public void setOrderGroupId(String orderGroupId) {
		this.orderGroupId = orderGroupId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setIoId(Long ioId) {
		this.ioId = ioId;
	}
}
