/**create by liuhua at 2018年6月1日 上午10:30:07**/
package com.booting.bracelet.dto;

import java.io.Serializable;

public class TotalData implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Long studentId;
  private String date;
  private Integer step;
  private Integer calorie;
  private Integer distance;
  private Integer maxHeartRate;
  private Integer minHeartRate;
  private Double  avgHeartRate;
  
  public Long getStudentId() {
    return studentId;
  }
  public String getDate() {
    return date;
  }
  public Integer getStep() {
    return step;
  }
  public Integer getCalorie() {
    return calorie;
  }
  public Integer getDistance() {
    return distance;
  }
  public Integer getMaxHeartRate() {
    return maxHeartRate;
  }
  public Integer getMinHeartRate() {
    return minHeartRate;
  }
  public Double getAvgHeartRate() {
    return avgHeartRate;
  }
  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public void setStep(Integer step) {
    this.step = step;
  }
  public void setCalorie(Integer calorie) {
    this.calorie = calorie;
  }
  public void setDistance(Integer distance) {
    this.distance = distance;
  }
  public void setMaxHeartRate(Integer maxHeartRate) {
    this.maxHeartRate = maxHeartRate;
  }
  public void setMinHeartRate(Integer minHeartRate) {
    this.minHeartRate = minHeartRate;
  }
  public void setAvgHeartRate(Double avgHeartRate) {
    this.avgHeartRate = avgHeartRate;
  }

}
