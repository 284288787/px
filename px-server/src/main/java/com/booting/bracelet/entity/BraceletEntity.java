/**create by liuhua at 2018年6月1日 上午10:30:07**/
package com.booting.bracelet.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.star.framework.aop.annotation.Description;

@Description(name = "手环信息")
@Entity(name = "bracelet_info")
public class BraceletEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Long braceletId;
  private String factoryCode;
  private String deviceName;
  private String mac;             //设备mac
  private String gatewaymac;      //蓝牙网管mac
  private Integer stepNum;        //步数
  private Integer heartRate;      //实时心率
  private Integer calorie;        //累计卡路里
  private Integer distance;       //累计距离
  private String quantity;        //电量
  private String other;           //其他
  private Date createTime;
  private String type;
  private String rssi;
  private String data;            //原始数据
  private Long studentId;
  
  @Id
  @Column(name = "braceletId")
  public Long getBraceletId() {
    return braceletId;
  }
  @Column(name = "factoryCode")
  public String getFactoryCode() {
    return factoryCode;
  }
  @Column(name = "mac")
  public String getMac() {
    return mac;
  }
  @Column(name = "stepNum")
  public Integer getStepNum() {
    return stepNum;
  }
  @Column(name = "calorie")
  public Integer getCalorie() {
    return calorie;
  }
  @Column(name = "distance")
  public Integer getDistance() {
    return distance;
  }
  @Column(name = "quantity")
  public String getQuantity() {
    return quantity;
  }
  @Column(name = "other")
  public String getOther() {
    return other;
  }
  @Column(name = "createTime")
  public Date getCreateTime() {
    return createTime;
  }
  @Column(name = "data")
  public String getData() {
    return data;
  }
  @Column(name = "gatewaymac")
  public String getGatewaymac() {
    return gatewaymac;
  }
  @Column(name = "heartRate")
  public Integer getHeartRate() {
    return heartRate;
  }
  @Column(name = "type")
  public String getType() {
    return type;
  }
  @Column(name = "rssi")
  public String getRssi() {
    return rssi;
  }
  @Column(name = "studentId")
  public Long getStudentId() {
    return studentId;
  }
  @Column(name = "deviceName")
  public String getDeviceName() {
    return deviceName;
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
  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public void setData(String data) {
    this.data = data;
  }
  public void setGatewaymac(String gatewaymac) {
    this.gatewaymac = gatewaymac;
  }
  public void setHeartRate(Integer heartRate) {
    this.heartRate = heartRate;
  }
  public void setType(String type) {
    this.type = type;
  }
  public void setRssi(String rssi) {
    this.rssi = rssi;
  }
  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }
  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }
}
