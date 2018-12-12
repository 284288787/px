/**create by liuhua at 2017年7月7日 上午11:15:28**/
package com.booting.pub.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "消息")
@Entity(name = "pub_message")
public class MessageEntity implements Serializable {
	private static final long serialVersionUID = 8747052053297610045L;
	
	private Long id;
	private Long userId;              //消息所属用户 -1 表示系统消息(全部群发)，-2表示部分群发消息
	private String userIds;           //群发消息，消息所有人的用户Id，userId=-2时，有用
	private Integer messageType;      //消息类型 1系统消息 2约球消息 3球队消息
	private String title;
	private String content;
	private Integer status;           //状态 1正常2已删
	private Date createTime;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "userIds")
	public String getUserIds() {
		return userIds;
	}
	@Column(name = "messageType")
	public Integer getMessageType() {
		return messageType;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
}
