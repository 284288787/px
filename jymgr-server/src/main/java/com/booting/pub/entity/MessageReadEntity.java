/**create by liuhua at 2017年7月7日 下午5:44:15**/
package com.booting.pub.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "已读消息")
@Entity(name = "pub_messageread")
public class MessageReadEntity implements Serializable {
	private static final long serialVersionUID = 1866694051218760953L;
	
	private Long id;
	private Long messageId;   //读的消息
	private Long userId;      //读消息人
	private Date createTime;  //读的时间
	private Integer status;   //状态 1正常 2已删
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "messageId")
	public Long getMessageId() {
		return messageId;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "status")
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
