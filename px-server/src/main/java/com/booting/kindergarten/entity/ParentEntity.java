/**create by liuhua at 2018年1月2日 下午1:46:32**/
package com.booting.kindergarten.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "长辈信息")
@Entity(name = "kindergarten_parent")
public class ParentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long parentId;
	private String name;
	private String mobile;
	private String pic;
	private Integer age;
	private Date createTime;
	
	@Id
	@Column(name = "parentId")
	public Long getParentId() {
		return parentId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name = "pic")
	public String getPic() {
		return pic;
	}
	@Column(name = "age")
	public Integer getAge() {
		return age;
	}
	@Column(name = "createTime")
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
	public void setPic(String pic) {
		this.pic = pic;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
