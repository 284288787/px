/**create by liuhua at 2017年12月18日 下午2:55:30**/
package com.booting.training.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "培训团体报名，成员信息")
@Entity(name = "px_apply_detail")
public class ApplyDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long applyId;
	private String name;
	private String mobile;
	private String address;         //地址
	private Integer certificateType;//证件类型 1身份证
	private String certificateCode; //证件号码
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "applyId")
	public Long getApplyId() {
		return applyId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	@Column(name = "certificateType")
	public Integer getCertificateType() {
		return certificateType;
	}
	@Column(name = "certificateCode")
	public String getCertificateCode() {
		return certificateCode;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}
	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}
}
