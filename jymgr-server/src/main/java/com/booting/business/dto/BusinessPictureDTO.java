/**create by liuhua at 2018年6月11日 下午5:45:43**/
package com.booting.business.dto;

import com.star.framework.aop.annotation.Description;

@Description(name = "图片")
public class BusinessPictureDTO {

  private Long id;
  private Long memberId;
  private Integer type;      //BusinessPictureType
  private Integer childType; //子类型
  private String childTypeName; //子类型名称
  private String picPath;
    
  public Long getId() {
    return id;
  }
  
  public Long getMemberId() {
    return memberId;
  }
  
  public Integer getType() {
    return type;
  }
  
  public Integer getChildType() {
    return childType;
  }
  
  public String getChildTypeName() {
    return childTypeName;
  }
  
  public String getPicPath() {
    return picPath;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }
  public void setType(Integer type) {
    this.type = type;
  }
  public void setChildType(Integer childType) {
    this.childType = childType;
  }
  public void setChildTypeName(String childTypeName) {
    this.childTypeName = childTypeName;
  }
  public void setPicPath(String picPath) {
    this.picPath = picPath;
  }
}
