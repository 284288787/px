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
	
	private boolean teacher;    //true 是老师
	private boolean schoolMgr;  //true 是幼儿园管理员
	private boolean parent;     //true 是监护人
	private boolean children;   //true 是长辈 可以看体智能
	private boolean coach;      //true 是教练
	
	public boolean isCoach() {
		return coach;
	}

	public void setCoach(boolean coach) {
		this.coach = coach;
	}

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

	public boolean isTeacher() {
		return teacher;
	}

	public boolean isSchoolMgr() {
		return schoolMgr;
	}

	public boolean isParent() {
		return parent;
	}

	public boolean isChildren() {
		return children;
	}

	public void setTeacher(boolean teacher) {
		this.teacher = teacher;
	}

	public void setSchoolMgr(boolean schoolMgr) {
		this.schoolMgr = schoolMgr;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}

	public void setChildren(boolean children) {
		this.children = children;
	}
}
