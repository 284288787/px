/**create by liuhua at 2018年6月1日 上午10:30:07**/
package com.booting.bracelet.dto;

import java.io.Serializable;
import java.util.Date;
import com.star.framework.aop.annotation.Description;

@Description(name = "手环信息")
public class BraceletDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Long braceletId;
  private String factoryCode;
  private String deviceName;
  private String mac;             //设备mac
  private String gatewaymac;      //蓝牙网管mac
  private Integer stepNum;        //步数
  private Integer heartRate;      //心率
  private Integer calorie;        //卡路里
  private Integer distance;       //距离
  private String quantity;        //电量
  private String other;           //其他
  private Date createTime;
  private String type;
  private String rssi;
  private String data;            //原始数据
  private Long studentId;
  
  private Integer dateType;
  private String startDate;
  private String endDate;
  
  private String date;
  private Integer heartRateMax;
  private Double heartRateAvg;
  private Integer heartRateMin;
  private Integer stepNumMax;
  private Integer calorieMax;
  private Integer distanceMax;
  
  public String toString(){
    return "厂商编号[" + factoryCode + "],mac[" + mac + "],步数[" + stepNum + "],心率[" + heartRate + "]"
        + "卡路里[" + calorie + "],距离[" + distance + "],电量[" + quantity + "],其他[" + other + "]";
  }
    
  public Long getBraceletId() {
    return braceletId;
  }
  
  public String getFactoryCode() {
    return factoryCode;
  }
  
  public String getMac() {
    return mac;
  }
  
  public Integer getStepNum() {
    return stepNum;
  }
  
  public Integer getCalorie() {
    return calorie;
  }
  
  public Integer getDistance() {
    return distance;
  }
  
  public String getOther() {
    return other;
  }
  
  public void setBraceletId(Long braceletId) {
    this.braceletId = braceletId;
  }
  public void setFactoryCode(String factoryCode) {
    this.factoryCode = factoryCode;
  }
  public void setMac(String mac) {
    this.mac = mac;
  }
  public void setStepNum(Integer stepNum) {
    this.stepNum = stepNum;
  }
  public void setCalorie(Integer calorie) {
    this.calorie = calorie;
  }
  public void setDistance(Integer distance) {
    this.distance = distance;
  }
  public void setOther(String other) {
    this.other = other;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getGatewaymac() {
    return gatewaymac;
  }

  public Integer getHeartRate() {
    return heartRate;
  }

  public void setGatewaymac(String gatewaymac) {
    this.gatewaymac = gatewaymac;
  }

  public void setHeartRate(Integer heartRate) {
    this.heartRate = heartRate;
  }

  public String getType() {
    return type;
  }

  public String getRssi() {
    return rssi;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setRssi(String rssi) {
    this.rssi = rssi;
  }

  public Long getStudentId() {
    return studentId;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public Integer getDateType() {
    return dateType;
  }

  public void setDateType(Integer dateType) {
    this.dateType = dateType;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getDate() {
    return date;
  }

  public Integer getHeartRateMax() {
    return heartRateMax;
  }

  public Double getHeartRateAvg() {
    return heartRateAvg;
  }

  public Integer getHeartRateMin() {
    return heartRateMin;
  }

  public Integer getStepNumMax() {
    return stepNumMax;
  }

  public Integer getCalorieMax() {
    return calorieMax;
  }

  public Integer getDistanceMax() {
    return distanceMax;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public void setHeartRateMax(Integer heartRateMax) {
    this.heartRateMax = heartRateMax;
  }

  public void setHeartRateAvg(Double heartRateAvg) {
    this.heartRateAvg = heartRateAvg;
  }

  public void setHeartRateMin(Integer heartRateMin) {
    this.heartRateMin = heartRateMin;
  }

  public void setStepNumMax(Integer stepNumMax) {
    this.stepNumMax = stepNumMax;
  }

  public void setCalorieMax(Integer calorieMax) {
    this.calorieMax = calorieMax;
  }

  public void setDistanceMax(Integer distanceMax) {
    this.distanceMax = distanceMax;
  }
}
