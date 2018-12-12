/**create by liuhua at 2018年6月24日 下午6:19:47**/
package com.booting.business.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.star.framework.aop.annotation.Description;

@Description(name = "加盟商")
@Entity(name = "mgr_franchisee")
public class FranchiseeEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long memberId;
  private String companyName;              //公司名称
  private String legalPerson;              //法人
  private String contactPhone;             //联系电话
  private String companyAddress;           //公司地址
  private String compactPath;              //签名合同路径
  private Integer level;                   //加盟商等级
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "memberId")
  public Long getMemberId() {
    return memberId;
  }

  @Column(name = "companyName")
  public String getCompanyName() {
    return companyName;
  }

  @Column(name = "legalPerson")
  public String getLegalPerson() {
    return legalPerson;
  }

  @Column(name = "contactPhone")
  public String getContactPhone() {
    return contactPhone;
  }
  
  @Column(name = "companyAddress")
  public String getCompanyAddress() {
    return companyAddress;
  }

  @Column(name = "compactPath")
  public String getCompactPath() {
    return compactPath;
  }

  @Column(name = "level")
  public Integer getLevel() {
    return level;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public void setLegalPerson(String legalPerson) {
    this.legalPerson = legalPerson;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  public void setCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
  }

  public void setCompactPath(String compactPath) {
    this.compactPath = compactPath;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }
}
