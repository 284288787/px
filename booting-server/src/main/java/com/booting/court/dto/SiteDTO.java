/**create by liuhua at 2017年6月17日 上午9:38:12**/
package com.booting.court.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.star.framework.aop.annotation.Description;

@Description(name = "场地")
public class SiteDTO implements Serializable {
	private static final long serialVersionUID = 7303922715166884731L;
	
	private Long siteId;
	private String siteName;
	private Long courtId;
	private Integer enabled;         //1可用 0禁用
	private Date createTime;
	private Date modifyTime;
	private Integer specification;  //场地规格 1:11人场,2:8人场,3:5人场
	private Integer knifing;        //是否分隔成多个小场  只有11人场可以分   1可分 0不可分

	private String siteIds;
	
	private List<ZoneDTO> zones;
	public Long getSiteId() {
		return siteId;
	}
	
	public String getSiteName() {
		return siteName;
	}
	
	public Long getCourtId() {
		return courtId;
	}
	
	public Integer getEnabled() {
		return enabled;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getModifyTime() {
		return modifyTime;
	}
	
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public void setCourtId(Long courtId) {
		this.courtId = courtId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public String getSiteIds() {
		return siteIds;
	}
	public void setSiteIds(String siteIds) {
		this.siteIds = siteIds;
	}
	public Integer getSpecification() {
		return specification;
	}
	public Integer getKnifing() {
		return knifing;
	}
	public void setSpecification(Integer specification) {
		this.specification = specification;
	}
	public void setKnifing(Integer knifing) {
		this.knifing = knifing;
	}

	public List<ZoneDTO> getZones() {
		return zones;
	}

	public void setZones(List<ZoneDTO> zones) {
		this.zones = zones;
	}
}
