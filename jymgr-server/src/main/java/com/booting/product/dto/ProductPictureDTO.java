/**create by liuhua at 2018年6月21日 下午10:19:02**/
package com.booting.product.dto;

import com.star.framework.aop.annotation.Description;

@Description(name = "产品图片")
public class ProductPictureDTO {
  
  private Long id;
  private Integer business;   //ProductType 1活动 2设备 3课程
  private Long businessId;
  private String picPath;
    
  public Long getId() {
    return id;
  }
  
  public Integer getBusiness() {
    return business;
  }
  
  public Long getBusinessId() {
    return businessId;
  }
  
  public String getPicPath() {
    return picPath;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  public void setBusiness(Integer business) {
    this.business = business;
  }
  public void setBusinessId(Long businessId) {
    this.businessId = businessId;
  }
  public void setPicPath(String picPath) {
    this.picPath = picPath;
  }
}
