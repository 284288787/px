/**create by liuhua at 2017年6月21日 下午4:19:16**/
package com.booting.pub.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "企业")
@Entity(name = "pub_company")
public class CompanyEntity implements Serializable {
	private static final long serialVersionUID = -1890823855880548494L;
	
	private Long companyId;
	private String companyName;   //简称
	private Long businessId;      //行业
	private Long areaId;
	private String invoiceTitle;  //发票抬头
	private String address;
	private Integer enabled;     //1可用 0不可用
	private Date createTime;
	private Date modifyTime;
	private String certificateCode; //识别码
	
	@Id
	@Column(name = "companyId")
	public Long getCompanyId() {
		return companyId;
	}
	@Column(name = "companyName")
	public String getCompanyName() {
		return companyName;
	}
	@Column(name = "businessId")
	public Long getBusinessId() {
		return businessId;
	}
	@Column(name = "areaId")
	public Long getAreaId() {
		return areaId;
	}
	@Column(name = "invoiceTitle")
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
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
	@Column(name = "certificateCode")
	public String getCertificateCode() {
		return certificateCode;
	}
	
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}
}
