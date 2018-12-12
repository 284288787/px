/**create by liuhua at 2018年3月2日 上午9:57:31**/
package com.booting.lottery.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "邀请明细")
@Entity(name = "lottery_invitation_detail")
public class InvitationDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id ;
	private Long inviterId;  //邀请人id
	private Long inviteeId;  //被邀请人id
	private Integer ballNum; //赠送的个数
	private Date createTime; //关系建立的时间
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "inviterId")
	public Long getInviterId() {
		return inviterId;
	}
	@Column(name = "inviteeId")
	public Long getInviteeId() {
		return inviteeId;
	}
	@Column(name = "ballNum")
	public Integer getBallNum() {
		return ballNum;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setInviterId(Long inviterId) {
		this.inviterId = inviterId;
	}
	public void setInviteeId(Long inviteeId) {
		this.inviteeId = inviteeId;
	}
	public void setBallNum(Integer ballNum) {
		this.ballNum = ballNum;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
