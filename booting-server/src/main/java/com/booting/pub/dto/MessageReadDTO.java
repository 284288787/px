/**create by liuhua at 2017年7月7日 下午5:44:15**/
package com.booting.pub.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "已读消息")
public class MessageReadDTO implements Serializable {
	private static final long serialVersionUID = 1866694051218760953L;
	
	private Long id;
	private Long messageId;   //读的消息
	private Long userId;      //读消息人
	private Date createTime;  //读的时间
	private Integer status;   //状态 1正常 2已删
	
		
	public Long getId() {
		return id;
	}
	
	public Long getMessageId() {
		return messageId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
