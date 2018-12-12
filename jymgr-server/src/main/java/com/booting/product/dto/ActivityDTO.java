/**create by liuhua at 2018年6月21日 上午10:54:59**/
package com.booting.product.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import com.star.framework.aop.annotation.Description;

@Description(name = "活动")
public class ActivityDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long activityId;
  private String name;
  private Long typeId;         //ProductTypeEntity
  private Integer price;       //价格 分
  private String intro;
  private String content;
  private Integer state;       //1永久有效 2限时有效
  private Date effectiveTime;  //生效时间
  private Date failureTime;    //失效时间
  private Integer enabled;     //1可用 0不可用
  private Integer deleted;     //1已删 0未删
  private Date createTime;
  private Date updateTime;
  
  private List<ProductPictureDTO> pictures;
  private String typeName;
  private Integer valid;      //1可售 0不可售
  private String activityIds;
  
  public boolean checkSaveParam(){
    if (StringUtils.isBlank(name) || null == typeId || null == price || StringUtils.isBlank(content) || StringUtils.isBlank(intro)
        || null == state || (state == 2 && (null == effectiveTime || null == failureTime)) || null == pictures || pictures.isEmpty()) {
      return false;
    }
    return true;
  }
  
  public Integer getValid(){
    return valid;
  }

  public Long getActivityId() {
    return activityId;
  }
  
  public Long getTypeId() {
    return typeId;
  }
  
  public Integer getPrice() {
    return price;
  }
  
  public String getIntro() {
    return intro;
  }
  
  public String getContent() {
    return content;
  }
  
  public Integer getState() {
    return state;
  }
  
  public Date getEffectiveTime() {
    return effectiveTime;
  }
  
  public Date getFailureTime() {
    return failureTime;
  }
  
  public Integer getEnabled() {
    return enabled;
  }
  
  public Integer getDeleted() {
    return deleted;
  }
  
  public Date getCreateTime() {
    return createTime;
  }
  
  public Date getUpdateTime() {
    return updateTime;
  }
  
  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }
  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }
  public void setPrice(Integer price) {
    this.price = price;
  }
  public void setIntro(String intro) {
    this.intro = intro;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public void setState(Integer state) {
    this.state = state;
  }
  public void setEffectiveTime(Date effectiveTime) {
    this.effectiveTime = effectiveTime;
  }
  public void setFailureTime(Date failureTime) {
    this.failureTime = failureTime;
  }
  public void setEnabled(Integer enabled) {
    this.enabled = enabled;
  }
  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getActivityIds() {
    return activityIds;
  }

  public void setActivityIds(String activityIds) {
    this.activityIds = activityIds;
  }

  public List<ProductPictureDTO> getPictures() {
    return pictures;
  }

  public void setPictures(List<ProductPictureDTO> pictures) {
    this.pictures = pictures;
  }

  public void setValid(Integer valid) {
    this.valid = valid;
  }
}
