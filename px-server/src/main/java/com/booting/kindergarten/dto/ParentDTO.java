/**create by liuhua at 2018年1月2日 下午1:46:32**/
package com.booting.kindergarten.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "父母信息")
public class ParentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long parentId;
	private String name;
	private String mobile;
	private Integer type;      //关系 1父 2母 3其他
	private String pic;
	private Integer age;
	private Date createTime;
	
	private Long studentId;
	private String studentName;
	private String studentBirth;
	private String openId;
	private String beginTime, endTime;
		
	public Long getParentId() {
		return parentId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public Integer getType() {
		return type;
	}
	
	public String getPic() {
		return pic;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getStudentId() {
		return studentId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStudentBirth() {
		return studentBirth;
	}

	public void setStudentBirth(String studentBirth) {
		this.studentBirth = studentBirth;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
