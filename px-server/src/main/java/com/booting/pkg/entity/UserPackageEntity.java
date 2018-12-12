/**create by liuhua at 2017年6月10日 下午6:10:05**/
package com.booting.pkg.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户套餐")
@Entity(name = "pkg_userpackage")
public class UserPackageEntity implements Serializable {
	private static final long serialVersionUID = -5220528249928445008L;
	
	private Long upId;
	private Long userId;
	private Long packageId;
	private String packageName;      //购买时，套餐的名称
	private Integer packageType;     //购买时，套餐的类型 1基础套餐 2增值套餐
	private Integer price;           //价格 * 100
	private Integer discount;        //折扣 * 10
	private Integer count;           //购买的个数：基础套餐购买的年限 增值套餐购买的是个数
	private Date createTime;          
	private Date beginTime;          //套餐生效时间  基础套餐才有值
	private Date endTime;            //套餐失效时间  基础套餐才有值
	private Integer sourceFrom;      //来源 1订单 2后台
	private Long orderId;            //来源为1时，为订单id
	private Long teamId;
	
	@Id
	@Column(name = "upId")
	public Long getUpId() {
		return upId;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "packageId")
	public Long getPackageId() {
		return packageId;
	}
	@Column(name = "packageName")
	public String getPackageName() {
		return packageName;
	}
	@Column(name = "packageType")
	public Integer getPackageType() {
		return packageType;
	}
	@Column(name = "price")
	public Integer getPrice() {
		return price;
	}
	@Column(name = "discount")
	public Integer getDiscount() {
		return discount;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "beginTime")
	public Date getBeginTime() {
		return beginTime;
	}
	@Column(name = "count")
	public Integer getCount() {
		return count;
	}
	@Column(name = "endTime")
	public Date getEndTime() {
		return endTime;
	}
	@Column(name = "sourceFrom")
	public Integer getSourceFrom() {
		return sourceFrom;
	}
	@Column(name = "orderId")
	public Long getOrderId() {
		return orderId;
	}
	public void setUpId(Long upId) {
		this.upId = upId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public void setSourceFrom(Integer sourceFrom) {
		this.sourceFrom = sourceFrom;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Column(name = "teamId")
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
}
