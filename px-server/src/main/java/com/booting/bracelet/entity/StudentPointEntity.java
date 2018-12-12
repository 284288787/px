/**create by liuhua at 2018年9月12日 下午3:25:03**/
package com.booting.bracelet.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.star.framework.aop.annotation.Description;

@Description(name = "学生级别")
@Entity(name = "student_point")
public class StudentPointEntity {

  private Long studentId;
  private Integer level;          //级别
  private Integer caloriePoint;   //卡路里积分
  private Integer distancePoint;  //距离积分
  private Integer calorie;        //上次加分时的卡路里
  private Integer distance;       //上次加分时的距离
  private Date updateTime;
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "studentId")
  public Long getStudentId() {
    return studentId;
  }
  @Column(name = "level")
  public Integer getLevel() {
    return level;
  }
  @Column(name = "caloriePoint")
  public Integer getCaloriePoint() {
    return caloriePoint;
  }
  @Column(name = "distancePoint")
  public Integer getDistancePoint() {
    return distancePoint;
  }
  @Column(name = "calorie")
  public Integer getCalorie() {
    return calorie;
  }
  @Column(name = "distance")
  public Integer getDistance() {
    return distance;
  }
  @Column(name = "updateTime")
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
