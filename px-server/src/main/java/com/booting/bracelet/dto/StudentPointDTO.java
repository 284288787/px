/**create by liuhua at 2018年9月12日 下午3:25:03**/
package com.booting.bracelet.dto;

import java.util.Date;
import com.star.framework.aop.annotation.Description;

@Description(name = "学生级别")
public class StudentPointDTO {

  private Long studentId;
  private Integer level;          //级别
  private Integer caloriePoint;   //卡路里积分
  private Integer distancePoint;  //距离积分
  private Integer calorie;        //上次加分时的卡路里
  private Integer distance;       //上次加分时的距离
  private Date updateTime;
  
  public Long getStudentId() {
    return studentId;
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
  
  public Integer getCalorie() {
    return calorie;
  }
  
  public Integer getDistance() {
    return distance;
  }
  
  public Date getUpdateTime() {
    return updateTime;
  }
  
  public void setStudentId(Long studentId) {
    this.studentId = studentId;
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
  public void setCalorie(Integer calorie) {
    this.calorie = calorie;
  }
  public void setDistance(Integer distance) {
    this.distance = distance;
  }
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}
