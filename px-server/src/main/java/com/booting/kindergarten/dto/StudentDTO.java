/** create by liuhua at 2018年1月2日 上午10:25:51 **/
package com.booting.kindergarten.dto;

import java.io.Serializable;
import java.util.Date;
import com.booting.bracelet.dto.BraceletDTO;
import com.star.framework.aop.annotation.Description;

@Description(name = "幼儿园学生信息")
public class StudentDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long studentId;
  private Long schoolId;
  private Long classId;
  private String name;
  private String guardianName; // 监护人姓名
  private String guardianMobile; // 监护人手机
  private Integer guardianType; // 监护人关系 ParentType
  private Integer sex; // 性别 1男 0女
  private Date birth;
  private Integer stature; // 身高 cm
  private Integer weight; // 体重 kg * 100;
  private Date createTime;
  private Integer enabled; // 是否有效 1有效 0无效
  private Integer deleted; // 是否删除 1已删除 0未删除
  private String braceletMac; // 手环mac地址
  private Integer type; // 类型 1正式 2临时
  private Long physicalClassId; //通过体测课报名的学员
  private String physicalClassName;
  
  private String schoolName;
  private String className;
  private String teacherName;
  private String studentIds;

  private Integer level;
  private Integer caloriePoint; // 卡路里积分
  private Integer distancePoint; // 距离积分
  private String levelName;
  private String levelIcon;

  private Boolean hasBraceletMac;

  private BraceletDTO braceletInfo;
  
  private PhysicalDataDTO physicalData;

  private Integer attendanceState;
  private String attendanceRemark;
  private Date attendanceTime;
  
  public Long getStudentId() {
    return studentId;
  }

  public Long getSchoolId() {
    return schoolId;
  }

  public Long getClassId() {
    return classId;
  }

  public String getName() {
    return name;
  }

  public String getGuardianName() {
    return guardianName;
  }

  public String getGuardianMobile() {
    return guardianMobile;
  }

  public Integer getSex() {
    return sex;
  }

  public Date getBirth() {
    return birth;
  }

  public Integer getStature() {
    return stature;
  }

  public Integer getWeight() {
    return weight;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }

  public void setSchoolId(Long schoolId) {
    this.schoolId = schoolId;
  }

  public void setClassId(Long classId) {
    this.classId = classId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setGuardianName(String guardianName) {
    this.guardianName = guardianName;
  }

  public void setGuardianMobile(String guardianMobile) {
    this.guardianMobile = guardianMobile;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public void setBirth(Date birth) {
    this.birth = birth;
  }

  public void setStature(Integer stature) {
    this.stature = stature;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Integer getEnabled() {
    return enabled;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public String getStudentIds() {
    return studentIds;
  }

  public void setEnabled(Integer enabled) {
    this.enabled = enabled;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }

  public void setStudentIds(String studentIds) {
    this.studentIds = studentIds;
  }

  public String getSchoolName() {
    return schoolName;
  }

  public void setSchoolName(String schoolName) {
    this.schoolName = schoolName;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public Integer getGuardianType() {
    return guardianType;
  }

  public void setGuardianType(Integer guardianType) {
    this.guardianType = guardianType;
  }

  public String getBraceletMac() {
    return braceletMac;
  }

  public void setBraceletMac(String braceletMac) {
    this.braceletMac = braceletMac;
  }

  public Boolean getHasBraceletMac() {
    return hasBraceletMac;
  }

  public void setHasBraceletMac(Boolean hasBraceletMac) {
    this.hasBraceletMac = hasBraceletMac;
  }

  public BraceletDTO getBraceletInfo() {
    return braceletInfo;
  }

  public void setBraceletInfo(BraceletDTO braceletInfo) {
    this.braceletInfo = braceletInfo;
  }

  public Integer getLevel() {
    return level;
  }

  public Integer getCaloriePoint() {
    return caloriePoint;
  }

  public Integer getDistancePoint() {
    return distancePoint;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public void setCaloriePoint(Integer caloriePoint) {
    this.caloriePoint = caloriePoint;
  }

  public void setDistancePoint(Integer distancePoint) {
    this.distancePoint = distancePoint;
  }

  public String getLevelName() {
    return levelName;
  }

  public String getLevelIcon() {
    return levelIcon;
  }

  public void setLevelName(String levelName) {
    this.levelName = levelName;
  }

  public void setLevelIcon(String levelIcon) {
    this.levelIcon = levelIcon;
  }

  public PhysicalDataDTO getPhysicalData() {
    return physicalData;
  }

  public void setPhysicalData(PhysicalDataDTO physicalData) {
    this.physicalData = physicalData;
  }

  public Integer getType() {
    return type;
  }

  public Long getPhysicalClassId() {
    return physicalClassId;
  }

  public String getPhysicalClassName() {
    return physicalClassName;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public void setPhysicalClassId(Long physicalClassId) {
    this.physicalClassId = physicalClassId;
  }

  public void setPhysicalClassName(String physicalClassName) {
    this.physicalClassName = physicalClassName;
  }

  public Integer getAttendanceState() {
    return attendanceState;
  }

  public String getAttendanceRemark() {
    return attendanceRemark;
  }

  public Date getAttendanceTime() {
    return attendanceTime;
  }

  public void setAttendanceState(Integer attendanceState) {
    this.attendanceState = attendanceState;
  }

  public void setAttendanceRemark(String attendanceRemark) {
    this.attendanceRemark = attendanceRemark;
  }

  public void setAttendanceTime(Date attendanceTime) {
    this.attendanceTime = attendanceTime;
  }
}
