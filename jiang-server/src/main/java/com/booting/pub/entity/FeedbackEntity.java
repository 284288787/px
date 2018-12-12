/**create by liuhua at 2017年6月13日 上午10:21:37**/
package com.booting.pub.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "意见与反馈")
@Entity(name = "pub_feedback")
public class FeedbackEntity implements Serializable {
	private static final long serialVersionUID = -7947872249731179060L;
	
	private Long id;
	private String title;
	private String content;
	private String contact; //联系方式
	private Date createTime;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	@Column(name = "contact")
	public String getContact() {
		return contact;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
}
