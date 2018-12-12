/**create by liuhua at 2017年6月6日 下午3:04:44**/
package com.booting.pkg.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户服务")
@Entity(name = "pkg_userservice")
public class UserServiceEntity implements Serializable {
	private static final long serialVersionUID = -8330441767455254412L;
	
	private Long usId;                //主键
	private Long userId;              //用户id
	private Long upId;                //UserPackageEntity 的主键
	private Long serviceId;
	private String serviceName;       //购买时，服务的名称
	private Integer serviceCount;
	private Date createTime;
	private Date beginTime;          //服务生效时间
	private Date endTime;            //服务失效时间
	private Long teamId;
	
	@Id
	@Column(name = "usId")
	public Long getUsId() {
		return usId;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "upId")
	public Long getUpId() {
		return upId;
	}
	@Column(name = "serviceId")
	public Long getServiceId() {
		return serviceId;
	}
	@Column(name = "serviceName")
	public String getServiceName() {
		return serviceName;
	}
	@Column(name = "serviceCount")
	public Integer getServiceCount() {
		return serviceCount;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "beginTime")
	public Date getBeginTime() {
		return beginTime;
	}
	@Column(name = "endTime")
	public Date getEndTime() {
		return endTime;
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
	public void setServiceCount(Integer serviceCount) {
		this.serviceCount = serviceCount;
	}
	public void setUpId(Long upId) {
		this.upId = upId;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public void setUsId(Long usId) {
		this.usId = usId;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name = "teamId")
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
}
