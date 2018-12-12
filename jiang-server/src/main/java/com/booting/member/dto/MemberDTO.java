/**create by liuhua at 2018年1月16日 下午4:04:13**/
package com.booting.member.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户信息")
public class MemberDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long memberId;
	private String openId;
	private String mobile;
	private String name;
	private String headPic;
	private Date createTime;
	private String code;
	private Integer tag;
	private String password;
	private Long inviterId;
	private Integer ballNums;
	private Integer goldNums;
	
	private Date loginTime;
	private String token;
	
	public Long getMemberId() {
		return memberId;
	}
	
	public String getOpenId() {
		return openId;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public String getName() {
		return name;
	}
	
	public String getHeadPic() {
		return headPic;
	}
	
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

	public String getCode() {
		return code;
	}

	public Integer getTag() {
		return tag;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getInviterId() {
		return inviterId;
	}

	public void setInviterId(Long inviterId) {
		this.inviterId = inviterId;
	}

	public Integer getBallNums() {
		return ballNums;
	}

	public Integer getGoldNums() {
		return goldNums;
	}

	public void setBallNums(Integer ballNums) {
		this.ballNums = ballNums;
	}

	public void setGoldNums(Integer goldNums) {
		this.goldNums = goldNums;
	}

}
