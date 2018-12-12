/**create by liuhua at 2017年6月17日 上午9:40:43**/
package com.booting.court.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "区块")
public class ZoneDTO implements Serializable {
	private static final long serialVersionUID = 1236451244130176490L;
	
	private Long zoneId;
	private String zoneName;
	private Long siteId;
	private Long parentId;
	private String parentIds;
	private Date createTime;
	private Date modifyTime;
	private Integer level;          //级别1：11人场 2：8人场 3：5人场
		
	public Long getZoneId() {
		return zoneId;
	}
	
	public String getZoneName() {
		return zoneName;
	}
	
	public Long getSiteId() {
		return siteId;
	}
	
	public Long getParentId() {
		return parentId;
	}
	
	public String getParentIds() {
		return parentIds;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getModifyTime() {
		return modifyTime;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
