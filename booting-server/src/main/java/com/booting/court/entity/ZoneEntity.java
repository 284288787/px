/**create by liuhua at 2017年6月17日 上午9:40:43**/
package com.booting.court.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "区块")
@Entity(name = "court_zone")
public class ZoneEntity implements Serializable {
	private static final long serialVersionUID = 1236451244130176490L;
	
	private Long zoneId;
	private String zoneName;
	private Long siteId;
	private Long parentId;
	private String parentIds;
	private Date createTime;
	private Date modifyTime;
	private Integer level;          //级别1：11人场 2：8人场 3：5人场
	
	@Id
	@Column(name = "zoneId")
	public Long getZoneId() {
		return zoneId;
	}
	@Column(name = "zoneName")
	public String getZoneName() {
		return zoneName;
	}
	@Column(name = "siteId")
	public Long getSiteId() {
		return siteId;
	}
	@Column(name = "parentId")
	public Long getParentId() {
		return parentId;
	}
	@Column(name = "parentIds")
	public String getParentIds() {
		return parentIds;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "modifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	@Column(name = "level")
	public Integer getLevel() {
		return level;
	}
	
	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}
