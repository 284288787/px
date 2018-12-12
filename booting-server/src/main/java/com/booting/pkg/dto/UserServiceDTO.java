/**create by liuhua at 2017年6月6日 下午3:04:44**/
package com.booting.pkg.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户服务")
public class UserServiceDTO implements Serializable {
	private static final long serialVersionUID = -8330441767455254412L;
	
	private Long usId;                  //主键
	private Long userId;              //用户id
	private Long upId;
	private Long serviceId;
	private String serviceName;       //购买时，服务的名称
	private String description;       //服务描述
	private Integer serviceCount;
	private Date createTime;
	private Date beginTime;          //服务生效时间
	private Date endTime;            //服务失效时间
	private Long teamId;
	private Integer teamIdEmpty;     //1空 2非空
	
	private String packageName;
	private List<UserAttributeDTO> attributes;
	
	public Long getUserId() {
		return userId;
	}
	
	public Long getServiceId() {
		return serviceId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getServiceName() {
		return serviceName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public List<UserAttributeDTO> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<UserAttributeDTO> attributes) {
		this.attributes = attributes;
	}

	public Long getUpId() {
		return upId;
	}

	public void setUpId(Long upId) {
		this.upId = upId;
	}

	public Long getUsId() {
		return usId;
	}

	public void setUsId(Long usId) {
		this.usId = usId;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Integer getTeamIdEmpty() {
		return teamIdEmpty;
	}

	public void setTeamIdEmpty(Integer teamIdEmpty) {
		this.teamIdEmpty = teamIdEmpty;
	}
}
