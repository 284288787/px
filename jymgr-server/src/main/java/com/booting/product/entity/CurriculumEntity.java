/**create by liuhua at 2018年6月21日 上午10:54:59**/
package com.booting.product.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.star.framework.aop.annotation.Description;

@Description(name = "课程")
@Entity(name = "mgr_product_curriculum")
public class CurriculumEntity implements Serializable {
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
  
  @Id
  @Column(name = "curriculumId")
  public Long getCurriculumId() {
    return curriculumId;
  }
  @Column(name = "name")
  public String getName() {
    return name;
  }
  @Column(name = "typeId")
  public Long getTypeId() {
    return typeId;
  }
  @Column(name = "price")
  public Integer getPrice() {
    return price;
  }
  @Column(name = "intro")
  public String getIntro() {
    return intro;
  }
  @Column(name = "content")
  public String getContent() {
    return content;
  }
  @Column(name = "enabled")
  public Integer getEnabled() {
    return enabled;
  }
  @Column(name = "deleted")
  public Integer getDeleted() {
    return deleted;
  }
  @Column(name = "createTime")
  public Date getCreateTime() {
    return createTime;
  }
  @Column(name = "updateTime")
  public Date getUpdateTime() {
    return updateTime;
  }
  
  public void setCurriculumId(Long curriculumId) {
    this.curriculumId = curriculumId;
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
  public void setName(String name) {
    this.name = name;
  }
}
