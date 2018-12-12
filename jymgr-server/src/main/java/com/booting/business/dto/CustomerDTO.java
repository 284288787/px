/** create by liuhua at 2018年6月24日 下午6:27:15 **/
package com.booting.business.dto;

import org.apache.commons.lang3.StringUtils;
import com.star.framework.aop.annotation.Description;

@Description(name = "客户")
public class CustomerDTO extends MemberDTO {
  private static final long serialVersionUID = 1L;

  private String orgName;
  private String orgAddress; // 机构地址
  private String contactName; // 联系人
  private String contactPhone; // 联系电话
  private Integer type; // 机构类型 1幼儿园 2小学 3中学 4小区 5其他
  private Integer peopleNum; // 规模人数

  public boolean checkSaveData(){
    if (StringUtils.isBlank(orgName) || StringUtils.isBlank(orgAddress) || StringUtils.isBlank(contactPhone) 
        || StringUtils.isBlank(contactPhone) || null == type || null == peopleNum) {
      return false;
    }
    return true;
  }
  
  public String getOrgName() {
    return orgName;
  }

  public String getOrgAddress() {
    return orgAddress;
  }

  public String getContactName() {
    return contactName;
  }

  public String getContactPhone() {
    return contactPhone;
  }

  public Integer getType() {
    return type;
  }

  public Integer getPeopleNum() {
    return peopleNum;
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
