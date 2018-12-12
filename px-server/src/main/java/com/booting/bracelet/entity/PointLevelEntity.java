/**create by liuhua at 2018年9月12日 下午3:36:27**/
package com.booting.bracelet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.star.framework.aop.annotation.Description;

@Description(name = "级别配置")
@Entity(name = "point_level")
public class PointLevelEntity {

  private Long id;
  private Integer level;
  private String name;
  private String icon;
  private Integer pointStep;           //升级到level需要的经验
  private Integer point;               //每次可以增加的积分
  private Integer calorieStep;         //增加point积分需要的卡路里
  private Integer distanceStep;        //增加point积分需要的距离
  
  @Id
  @Column(name = "id")
  public Long getId() {
    return id;
  }
  @Column(name = "level")
  public Integer getLevel() {
    return level;
  }
  @Column(name = "name")
  public String getName() {
    return name;
  }
  @Column(name = "icon")
  public String getIcon() {
    return icon;
  }
  @Column(name = "pointStep")
  public Integer getPointStep() {
    return pointStep;
  }
  @Column(name = "point")
  public Integer getPoint() {
    return point;
  }
  @Column(name = "calorieStep")
  public Integer getCalorieStep() {
    return calorieStep;
  }
  @Column(name = "distanceStep")
  public Integer getDistanceStep() {
    return distanceStep;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  public void setLevel(Integer level) {
    this.level = level;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setIcon(String icon) {
    this.icon = icon;
  }
  public void setPointStep(Integer pointStep) {
    this.pointStep = pointStep;
  }
  public void setPoint(Integer point) {
    this.point = point;
  }
  public void setCalorieStep(Integer calorieStep) {
    this.calorieStep = calorieStep;
  }
  public void setDistanceStep(Integer distanceStep) {
    this.distanceStep = distanceStep;
  }
}
