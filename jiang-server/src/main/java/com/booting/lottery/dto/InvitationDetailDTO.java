/**create by liuhua at 2018年3月2日 上午9:57:31**/
package com.booting.lottery.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "邀请明细")
public class InvitationDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id ;
	private Long inviterId;  //邀请人id
	private Long inviteeId;  //被邀请人id
	private Integer ballNum; //赠送的个数
	private Date createTime; //关系建立的时间
	
		
	public Long getId() {
		return id;
	}
	
	public Long getInviterId() {
		return inviterId;
	}
	
	public Long getInviteeId() {
		return inviteeId;
	}
	
	public Integer getBallNum() {
		return ballNum;
	}
	
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
