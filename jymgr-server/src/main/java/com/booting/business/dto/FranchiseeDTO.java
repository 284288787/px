/** create by liuhua at 2018年6月24日 下午6:19:47 **/
package com.booting.business.dto;

import org.apache.commons.lang3.StringUtils;
import com.star.framework.aop.annotation.Description;

@Description(name = "加盟商")
public class FranchiseeDTO extends MemberDTO {
  private static final long serialVersionUID = 1L;

  private String companyName; // 公司名称
  private String legalPerson; // 法人
  private String contactPhone; // 联系电话
  private String companyAddress; // 公司地址
  private String compactPath; // 签名合同路径
  private Integer level; // 加盟商等级

  public boolean checkSaveData(){
    if (StringUtils.isBlank(companyName) || StringUtils.isBlank(legalPerson) || StringUtils.isBlank(contactPhone) 
        || StringUtils.isBlank(companyAddress) || StringUtils.isBlank(compactPath)) {
      return false;
    }
    return true;
  }
  
  public String getCompanyName() {
    return companyName;
  }

  public String getLegalPerson() {
    return legalPerson;
  }

  public String getContactPhone() {
    return contactPhone;
  }

  public String getCompanyAddress() {
    return companyAddress;
  }

  public String getCompactPath() {
    return compactPath;
  }

  public Integer getLevel() {
    return level;
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
