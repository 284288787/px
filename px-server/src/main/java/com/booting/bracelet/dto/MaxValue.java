/**create by liuhua at 2018年9月2日 下午3:10:31**/
package com.booting.bracelet.dto;

public class MaxValue {

  private String date;
  private Integer heartRateMax;
  private Double heartRateAvg;
  private Integer heartRateMin;
  private Integer stepNumMax;
  private Integer calorieMax;
  private Integer distanceMax;
  
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
