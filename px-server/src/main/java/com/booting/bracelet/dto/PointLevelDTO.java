/**create by liuhua at 2018年9月12日 下午3:36:27**/
package com.booting.bracelet.dto;

import org.apache.commons.lang3.StringUtils;
import com.star.framework.aop.annotation.Description;

@Description(name = "级别配置")
public class PointLevelDTO {

  private Long id;
  private Integer level;
  private String name;
  private String icon;
  private Integer pointStep;           //升级到level需要的经验
  private Integer point;               //每次可以增加的经验
  private Integer calorieStep;         //增加point积分需要的卡路里
  private Integer distanceStep;        //增加point积分需要的距离
  
  public boolean checkeSaveData() {
    if (null != id || null == level || null == point || null == pointStep || null == calorieStep 
        || null == distanceStep || StringUtils.isBlank(name) || StringUtils.isBlank(icon)) {
      return false;
    }
    return true;
  }
  
  public boolean checkeUpdateData() {
    if (null == id || null == level || null == point || null == pointStep || null == calorieStep 
        || null == distanceStep || StringUtils.isBlank(name) || StringUtils.isBlank(icon)) {
      return false;
    }
    return true;
  }
  
  public Long getId() {
    return id;
  }
  
  public Integer getLevel() {
    return level;
  }
  
  public String getName() {
    return name;
  }
  
  public String getIcon() {
    return icon;
  }
  
  public Integer getPointStep() {
    return pointStep;
  }
  
  public Integer getPoint() {
    return point;
  }
  
  public Integer getCalorieStep() {
    return calorieStep;
  }
  
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
