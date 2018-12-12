/**create by liuhua at 2018年6月11日 下午5:45:43**/
package com.booting.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.star.framework.aop.annotation.Description;

@Description(name = "图片")
@Entity(name = "mgr_business_picture")
public class BusinessPictureEntity {

  private Long id;
  private Long memberId;
  private Integer type;      //BusinessPictureType
  private Integer childType; //子类型
  private String childTypeName; //子类型名称
  private String picPath;
  
  @Id
  @Column(name = "id")
  public Long getId() {
    return id;
  }
  @Column(name = "memberId")
  public Long getMemberId() {
    return memberId;
  }
  @Column(name = "type")
  public Integer getType() {
    return type;
  }
  @Column(name = "childType")
  public Integer getChildType() {
    return childType;
  }
  @Column(name = "childTypeName")
  public String getChildTypeName() {
    return childTypeName;
  }
  @Column(name = "picPath")
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
