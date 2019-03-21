/**create by liuhua at 2019年3月21日 上午10:35:30**/
package com.booting.training.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "回扣发放明细")
@Entity(name = "px_kickback_detail")
public class KickbackDetailEntity {

  private Long id;
  private Long promoterId;
  private Integer money; // 发放金额 单位分
  private String wxNumber; // 收款微信账号
  private String wxOrderNumber; // 交易流水
  private Date createTime; // 发放时间
  private String createUser; // 发放人
  // 日期节点 起
  private Date pointBeginTime;
  // 日期节点 止
  private Date pointEndTime;
  // 订单状态 1处理中 2已完成
  private Integer state;
  // 最后更新日期
  private Date updateTime;

  @Id
  @Column(name = "id")
  public Long getId() {
    return id;
  }

  @Column(name = "promoterId")
  public Long getPromoterId() {
    return promoterId;
  }

  @Column(name = "money")
  public Integer getMoney() {
    return money;
  }

  @Column(name = "createTime")
  public Date getCreateTime() {
    return createTime;
  }

  @Column(name = "createUser")
  public String getCreateUser() {
    return createUser;
  }

  @Column(name = "wxNumber")
  public String getWxNumber() {
    return wxNumber;
  }

  @Column(name = "wxOrderNumber")
  public String getWxOrderNumber() {
    return wxOrderNumber;
  }
  
  @Column(name = "pointBeginTime")
  public Date getPointBeginTime() {
    return pointBeginTime;
  }

  @Column(name = "pointEndTime")
  public Date getPointEndTime() {
    return pointEndTime;
  }

  @Column(name = "state")
  public Integer getState() {
    return state;
  }

  @Column(name = "updateTime")
  public Date getUpdateTime() {
    return updateTime;
  }
  
  public void setId(Long id) {
    this.id = id;
  }

  public void setPromoterId(Long promoterId) {
    this.promoterId = promoterId;
  }

  public void setMoney(Integer money) {
    this.money = money;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  public void setWxNumber(String wxNumber) {
    this.wxNumber = wxNumber;
  }

  public void setWxOrderNumber(String wxOrderNumber) {
    this.wxOrderNumber = wxOrderNumber;
  }
  public void setPointBeginTime(Date pointBeginTime) {
    this.pointBeginTime = pointBeginTime;
  }

  public void setPointEndTime(Date pointEndTime) {
    this.pointEndTime = pointEndTime;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}
