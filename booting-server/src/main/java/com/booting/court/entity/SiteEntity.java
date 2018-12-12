/**create by liuhua at 2017年6月17日 上午9:38:12**/
package com.booting.court.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "场地")
@Entity(name = "court_site")
public class SiteEntity implements Serializable {
	private static final long serialVersionUID = 7303922715166884731L;
	
	private Long siteId;
	private String siteName;
	private Long courtId;
	private Integer enabled;         //1可用 0禁用
	private Date createTime;
	private Date modifyTime;
	private Integer specification;  //场地规格 1:11人场,2:8人场,3:5人场
	private Integer knifing;        //是否分隔成多个小场  只有11人场可以分   1可分 0不可分
	
	@Id
	@Column(name = "siteId")
	public Long getSiteId() {
		return siteId;
	}
	@Column(name = "siteName")
	public String getSiteName() {
		return siteName;
	}
	@Column(name = "courtId")
	public Long getCourtId() {
		return courtId;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "modifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	@Column(name = "specification")
	public Integer getSpecification() {
		return specification;
	}
	@Column(name = "knifing")
	public Integer getKnifing() {
		return knifing;
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
	public void setSpecification(Integer specification) {
		this.specification = specification;
	}
	public void setKnifing(Integer knifing) {
		this.knifing = knifing;
	}
}
