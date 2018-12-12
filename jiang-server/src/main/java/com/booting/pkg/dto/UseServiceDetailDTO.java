/**create by liuhua at 2017年8月4日 上午11:30:21**/
package com.booting.pkg.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "使用服务明细")
public class UseServiceDetailDTO implements Serializable {
	private static final long serialVersionUID = 6912081036164563221L;
	
	private Long id;
	private Long userId;
	private Long serviceId;
	private Long subjectId;     //相关主题ID
	private Integer num;
	private String descrition;
	private Date createTime;
	private Long teamId;
	private String serviceName;
	
	public Long getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Long getServiceId() {
		return serviceId;
	}
	
	public Long getSubjectId() {
		return subjectId;
	}
	
	public Integer getNum() {
		return num;
	}
	
	public String getDescrition() {
		return descrition;
	}
	
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

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
