/**create by liuhua at 2018年11月20日 上午11:07:54**/
package com.booting.bracelet.dto;

import java.util.Date;
import java.util.List;

public class BraceletRequestDto {

  private String deviceName;
  private String mac;             //设备mac
  private Integer stepNum;        //步数
  private List<Integer> hrList;   //心率
  private Integer calorie;        //卡路里
  private Integer distance;       //距离
  private Date createTime;
  private Long studentId;
  
  public String getDeviceName() {
    return deviceName;
  }
  public String getMac() {
    return mac;
  }
  public Integer getStepNum() {
    return stepNum;
  }
  public List<Integer> getHrList() {
    return hrList;
  }
  public Integer getCalorie() {
    return calorie;
  }
  public Integer getDistance() {
    return distance;
  }
  public Date getCreateTime() {
    return createTime;
  }
  public Long getStudentId() {
    return studentId;
  }
  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }
  public void setMac(String mac) {
    this.mac = mac;
  }
  public void setStepNum(Integer stepNum) {
    this.stepNum = stepNum;
  }
  public void setHrList(List<Integer> hrList) {
    this.hrList = hrList;
  }
  public void setCalorie(Integer calorie) {
    this.calorie = calorie;
  }
  public void setDistance(Integer distance) {
    this.distance = distance;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }
}
