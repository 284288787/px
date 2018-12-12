/**create by liuhua at 2018年6月24日 下午6:27:15**/
package com.booting.business.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.star.framework.aop.annotation.Description;

@Description(name = "客户")
@Entity(name = "mgr_customer")
public class CustomerEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long memberId;
  private String orgName;
  private String orgAddress;     //机构地址
  private String contactName;    //联系人
  private String contactPhone;   //联系电话
  private Integer type;          //机构类型 1幼儿园 2小学 3中学 4小区 5其他
  private Integer peopleNum;     //规模人数
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "memberId")
  public Long getMemberId() {
    return memberId;
  }

  @Column(name = "orgName")
  public String getOrgName() {
    return orgName;
  }

  @Column(name = "orgAddress")
  public String getOrgAddress() {
    return orgAddress;
  }

  @Column(name = "contactName")
  public String getContactName() {
    return contactName;
  }

  @Column(name = "contactPhone")
  public String getContactPhone() {
    return contactPhone;
  }

  @Column(name = "type")
  public Integer getType() {
    return type;
  }

  @Column(name = "peopleNum")
  public Integer getPeopleNum() {
    return peopleNum;
  }

  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  public void setOrgAddress(String orgAddress) {
    this.orgAddress = orgAddress;
  }

  public void setContactName(String contactName) {
    this.contactName = contactName;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public void setPeopleNum(Integer peopleNum) {
    this.peopleNum = peopleNum;
  }
}
