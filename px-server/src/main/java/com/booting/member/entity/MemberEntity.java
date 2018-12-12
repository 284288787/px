/**create by liuhua at 2018年1月16日 下午4:04:13**/
package com.booting.member.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户信息")
@Entity(name = "px_member")
public class MemberEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long memberId;
	private String openId;
	private String mobile;
	private String name;
	private String headPic;
	private Date createTime;
	
	@Id
	@Column(name = "memberId")
	public Long getMemberId() {
		return memberId;
	}
	@Column(name = "openId")
	public String getOpenId() {
		return openId;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	@Column(name = "headPic")
	public String getHeadPic() {
		return headPic;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
