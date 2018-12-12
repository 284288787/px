/**create by liuhua at 2018年6月21日 上午10:54:59**/
package com.booting.product.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.star.framework.aop.annotation.Description;

@Description(name = "课程")
public class CurriculumDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Long curriculumId;
  private String name;
  private Long typeId;         //ProductTypeEntity
  private Integer price;       //价格 分
  private String intro;
  private String content;
  private Integer enabled;     //1可用 0不可用
  private Integer deleted;     //1已删 0未删
  private Date createTime;
  private Date updateTime;
  
  private String typeName;
  private String curriculumIds;
  private List<ProductPictureDTO> pictures;
  
  public boolean checkSaveParam(){
    if (StringUtils.isBlank(name) || null == typeId || null == price || StringUtils.isBlank(content) || StringUtils.isBlank(intro)) {
      return false;
    }
    return true;
  }
  
  public Long getCurriculumId() {
    return curriculumId;
  }
  
  public Long getTypeId() {
    return typeId;
  }
  
  public String getTypeName() {
    return typeName;
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
  
  public void setCurriculumId(Long curriculumId) {
    this.curriculumId = curriculumId;
  }
  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }
  public void setTypeName(String typeName) {
    this.typeName = typeName;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCurriculumIds() {
    return curriculumIds;
  }

  public List<ProductPictureDTO> getPictures() {
    return pictures;
  }

  public void setCurriculumIds(String curriculumIds) {
    this.curriculumIds = curriculumIds;
  }

  public void setPictures(List<ProductPictureDTO> pictures) {
    this.pictures = pictures;
  }
}
