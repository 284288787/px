/**create by liuhua at 2017年12月18日 上午11:47:48**/
package com.booting.training.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "培训报名信息")
@Entity(name = "px_apply_info")
public class ApplyInfoEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long applyId;
  private Long itemId;
  private Long userId;
  private Integer type; // 类型 1幼儿园管理员 2青少年 3教练员 4团体
  private String organizationName;// 团体名称
  private String name; // 姓名
  private String mobile; // 电话
  private String address; // 地址
  private Integer certificateType;// 证件类型 1身份证
  private String certificateCode; // 证件号码
  private Integer sourceFrom; // 来源 1安卓 2ios 3后台
  private Date createTime;
  private Integer status; // 状态 1待支付 2已支付 3已退款
  // 新增 下面 5个字段
  private String childName;
  private Date childBirth;
  private Integer childAge;
  private Integer childSex;
  private Long addrId;
  private Long applyItemId;
  private Long promoterId;
  
  @Id
  @Column(name = "applyId")
  public Long getApplyId() {
    return applyId;
  }
  
  @Column(name = "promoterId")
  public Long getPromoterId() {
    return promoterId;
  }

  @Column(name = "itemId")
  public Long getItemId() {
    return itemId;
  }

  @Column(name = "type")
  public Integer getType() {
    return type;
  }

  @Column(name = "organizationName")
  public String getOrganizationName() {
    return organizationName;
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

  @Column(name = "sourceFrom")
  public Integer getSourceFrom() {
    return sourceFrom;
  }

  @Column(name = "createTime")
  public Date getCreateTime() {
    return createTime;
  }

  @Column(name = "status")
  public Integer getStatus() {
    return status;
  }

  @Column(name = "userId")
  public Long getUserId() {
    return userId;
  }

  @Column(name = "childName")
  public String getChildName() {
    return childName;
  }

  @Column(name = "childBirth")
  public Date getChildBirth() {
    return childBirth;
  }

  @Column(name = "childSex")
  public Integer getChildSex() {
    return childSex;
  }

  @Column(name = "addrId")
  public Long getAddrId() {
    return addrId;
  }

  @Column(name = "applyItemId")
  public Long getApplyItemId() {
    return applyItemId;
  }
  @Column(name = "childAge")
  public Integer getChildAge() {
    return childAge;
  }
  
  public void setApplyId(Long applyId) {
    this.applyId = applyId;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
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

  public void setSourceFrom(Integer sourceFrom) {
    this.sourceFrom = sourceFrom;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setChildName(String childName) {
    this.childName = childName;
  }

  public void setChildBirth(Date childBirth) {
    this.childBirth = childBirth;
  }

  public void setChildSex(Integer childSex) {
    this.childSex = childSex;
  }

  public void setAddrId(Long addrId) {
    this.addrId = addrId;
  }

  public void setApplyItemId(Long applyItemId) {
    this.applyItemId = applyItemId;
  }

  public void setPromoterId(Long promoterId) {
    this.promoterId = promoterId;
  }

  public void setChildAge(Integer childAge) {
    this.childAge = childAge;
  }
}
