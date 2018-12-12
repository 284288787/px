/**create by liuhua at 2017年8月4日 上午11:30:21**/
package com.booting.pkg.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "使用服务明细")
@Entity(name = "pkg_useservicedetail")
public class UseServiceDetailEntity implements Serializable {
	private static final long serialVersionUID = 6912081036164563221L;
	
	private Long id;
	private Long userId;
	private Long serviceId;
	private Long subjectId;     //相关主题ID
	private Integer num;
	private String descrition;
	private Date createTime;
	private Long teamId;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "serviceId")
	public Long getServiceId() {
		return serviceId;
	}
	@Column(name = "subjectId")
	public Long getSubjectId() {
		return subjectId;
	}
	@Column(name = "num")
	public Integer getNum() {
		return num;
	}
	@Column(name = "descrition")
	public String getDescrition() {
		return descrition;
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
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	@Column(name = "teamId")
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
}
