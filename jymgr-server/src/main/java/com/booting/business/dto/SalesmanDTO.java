/** create by liuhua at 2018年6月11日 下午4:17:51 **/
package com.booting.business.dto;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.star.framework.aop.annotation.Description;

@Description(name = "业务员")
public class SalesmanDTO extends MemberDTO {
  private static final long serialVersionUID = 1L;

  private Integer sex; // 性别 1男 0女
  private Date birthTime;
  private Integer certificateType; // 证件类型 1身份在 2护照
  private String certificateCode; // 证件号码
  private String address;
  private String emergencyContact; // 紧急联系人
  private String emergencyContactNumber; // 紧急联系人电话
  private String emergencyContactAddress; // 紧急联系人住址
  private String school; // 毕业学校
  private String specialty; // 专业
  private String workExperience; // 工作经历
  private String awardResume; // 获奖履历

  private String headPath;
  private List<BusinessPictureDTO> headPic; // 头像
  private List<BusinessPictureDTO> certificatePics; // 证件

  public boolean checkSaveData() {
    if (null == sex || null == birthTime || null == certificateType || StringUtils.isBlank(certificateCode) 
        || StringUtils.isBlank(address) || StringUtils.isBlank(emergencyContact) || StringUtils.isBlank(emergencyContactNumber) 
        || StringUtils.isBlank(emergencyContactAddress) || StringUtils.isBlank(school) || StringUtils.isBlank(specialty) 
        || StringUtils.isBlank(workExperience) || StringUtils.isBlank(getMobile()) || StringUtils.isBlank(getName()) 
        || null == headPic || null == certificatePics) {
      return false;
    }
    return true;
  }

  public Integer getSex() {
    return sex;
  }

  public Date getBirthTime() {
    return birthTime;
  }

  public Integer getCertificateType() {
    return certificateType;
  }

  public String getCertificateCode() {
    return certificateCode;
  }

  public String getAddress() {
    return address;
  }

  public String getEmergencyContact() {
    return emergencyContact;
  }

  public String getEmergencyContactNumber() {
    return emergencyContactNumber;
  }

  public String getEmergencyContactAddress() {
    return emergencyContactAddress;
  }

  public String getSchool() {
    return school;
  }

  public String getSpecialty() {
    return specialty;
  }

  public String getWorkExperience() {
    return workExperience;
  }

  public String getAwardResume() {
    return awardResume;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public void setBirthTime(Date birthTime) {
    this.birthTime = birthTime;
  }

  public void setCertificateType(Integer certificateType) {
    this.certificateType = certificateType;
  }

  public void setCertificateCode(String certificateCode) {
    this.certificateCode = certificateCode;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setEmergencyContact(String emergencyContact) {
    this.emergencyContact = emergencyContact;
  }

  public void setEmergencyContactNumber(String emergencyContactNumber) {
    this.emergencyContactNumber = emergencyContactNumber;
  }

  public void setEmergencyContactAddress(String emergencyContactAddress) {
    this.emergencyContactAddress = emergencyContactAddress;
  }

  public void setSchool(String school) {
    this.school = school;
  }

  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }

  public void setWorkExperience(String workExperience) {
    this.workExperience = workExperience;
  }

  public void setAwardResume(String awardResume) {
    this.awardResume = awardResume;
  }

  public List<BusinessPictureDTO> getHeadPic() {
    return headPic;
  }

  public List<BusinessPictureDTO> getCertificatePics() {
    return certificatePics;
  }

  public void setHeadPic(List<BusinessPictureDTO> headPic) {
    this.headPic = headPic;
  }

  public void setCertificatePics(List<BusinessPictureDTO> certificatePics) {
    this.certificatePics = certificatePics;
  }

  public String getHeadPath() {
    return headPath;
  }

  public void setHeadPath(String headPath) {
    this.headPath = headPath;
  }
}
