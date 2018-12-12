/**create by liuhua at 2018年6月11日 下午4:17:51**/
package com.booting.business.dto;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.star.framework.aop.annotation.Description;

@Description(name = "教练")
public class CoachDTO extends MemberDTO {
  private static final long serialVersionUID = 1L;
  
  private Integer sex;         //性别 1男 0女
  private Date birthTime;
  private Integer certificateType; //证件类型 1身份在 2护照     
  private String certificateCode;  //证件号码
  private String address;
  private String emergencyContact;         //紧急联系人
  private String emergencyContactNumber;   //紧急联系人电话
  private String emergencyContactAddress;  //紧急联系人住址
  private String educationBackground;      //学历
  private String school;               //毕业学校
  private String specialty;            //专业
  private Integer professionalService; //是否职业服役 1是 0否
  private String workExperience;       //工作经历
  private String awardResume;          //获奖履历
  
  private String headPath;
  private List<BusinessPictureDTO> headPic;           //头像
  private List<BusinessPictureDTO> certificatePics;   //证件
  private List<BusinessPictureDTO> eduPics;           //学历
  private List<BusinessPictureDTO> teachingCertPics;  //执教证书
  
  private Boolean precise;
  private String certTypes;
  private String[] certTypeArr;
  
  public boolean checkSaveData(){
    if (null == sex || null == birthTime || null == certificateType || null == professionalService || StringUtils.isBlank(certificateCode)
        || StringUtils.isBlank(address) || StringUtils.isBlank(emergencyContact) || StringUtils.isBlank(emergencyContactNumber)
        || StringUtils.isBlank(emergencyContactAddress) || StringUtils.isBlank(educationBackground) || StringUtils.isBlank(school) 
        || StringUtils.isBlank(specialty) || StringUtils.isBlank(workExperience) || StringUtils.isBlank(getMobile()) || StringUtils.isBlank(getName())
        || null == headPic || null == certificatePics || null == eduPics || null == teachingCertPics) {
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
  
  public String getEducationBackground() {
    return educationBackground;
  }
  
  public String getSchool() {
    return school;
  }
  
  public String getSpecialty() {
    return specialty;
  }
  
  public Integer getProfessionalService() {
    return professionalService;
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
  public void setEducationBackground(String educationBackground) {
    this.educationBackground = educationBackground;
  }
  public void setSchool(String school) {
    this.school = school;
  }
  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }
  public void setProfessionalService(Integer professionalService) {
    this.professionalService = professionalService;
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

  public List<BusinessPictureDTO> getEduPics() {
    return eduPics;
  }

  public List<BusinessPictureDTO> getTeachingCertPics() {
    return teachingCertPics;
  }

  public void setHeadPic(List<BusinessPictureDTO> headPic) {
    this.headPic = headPic;
  }

  public void setCertificatePics(List<BusinessPictureDTO> certificatePics) {
    this.certificatePics = certificatePics;
  }

  public void setEduPics(List<BusinessPictureDTO> eduPics) {
    this.eduPics = eduPics;
  }

  public void setTeachingCertPics(List<BusinessPictureDTO> teachingCertPics) {
    this.teachingCertPics = teachingCertPics;
  }

  public String getHeadPath() {
    return headPath;
  }

  public void setHeadPath(String headPath) {
    this.headPath = headPath;
  }

  public String getCertTypes() {
    return certTypes;
  }

  public void setCertTypes(String certTypes) {
    this.certTypes = certTypes;
  }

  public String[] getCertTypeArr() {
    return certTypeArr;
  }

  public void setCertTypeArr(String[] certTypeArr) {
    this.certTypeArr = certTypeArr;
  }

  public Boolean getPrecise() {
    return precise;
  }

  public void setPrecise(Boolean precise) {
    this.precise = precise;
  }
}
