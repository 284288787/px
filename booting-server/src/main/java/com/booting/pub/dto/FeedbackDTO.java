/**create by liuhua at 2017年6月13日 上午10:21:37**/
package com.booting.pub.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "意见与反馈")
public class FeedbackDTO implements Serializable {
	private static final long serialVersionUID = -7947872249731179060L;
	
	private Long id;
	private String title;
	private String content;
	private String contact;
	private Date createTime;
	
		
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
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

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
}
