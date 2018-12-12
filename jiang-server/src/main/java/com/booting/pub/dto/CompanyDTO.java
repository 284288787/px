/**create by liuhua at 2017年6月21日 下午4:19:16**/
package com.booting.pub.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "企业")
public class CompanyDTO implements Serializable {
	private static final long serialVersionUID = -1890823855880548494L;
	
	private Long companyId;
	private String companyName;
	private Long areaId;
	private Long businessId;      //行业
	private String invoiceTitle;  //发票抬头
	private String address;
	private Integer enabled;     //1可用 0不可用
	private Date createTime;
	private Date modifyTime;
	private String certificateCode; //识别码
	private String companyIds;
	
	private String areaName;
	private String businessName;
	private Integer from;
	
	public Long getCompanyId() {
		return companyId;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public Long getAreaId() {
		return areaId;
	}
	
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	
	public String getAddress() {
		return address;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getModifyTime() {
		return modifyTime;
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

	public String getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(String companyIds) {
		this.companyIds = companyIds;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}
}
