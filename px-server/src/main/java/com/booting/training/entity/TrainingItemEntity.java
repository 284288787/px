/**create by liuhua at 2017年12月18日 上午10:25:31**/
package com.booting.training.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "培训项目")
@Entity(name = "px_training_item")
public class TrainingItemEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long itemId;        //培训项目id
	private String title;       //标题
	private Long areaId;        //地区id
	private Date beginTime;
	private Date endTime;       
	private Integer type;       //类型 1幼儿园足球体能发开课程 2青少年足球培训 3教练员培训
	private String intro;       //课程介绍
	private String notice;      //报名须知
	private Date createTime;
	private String createUser;
	private Integer enabled;       //是否有效 1有效     0无效
	private Integer deleted;       //是否删除 1已删除     0未删除
	private Integer price;
	private String address;
	
	@Id
	@Column(name = "itemId")
	public Long getItemId() {
		return itemId;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	@Column(name = "areaId")
	public Long getAreaId() {
		return areaId;
	}
	@Column(name = "beginTime")
	public Date getBeginTime() {
		return beginTime;
	}
	@Column(name = "endTime")
	public Date getEndTime() {
		return endTime;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}
	@Column(name = "intro")
	public String getIntro() {
		return intro;
	}
	@Column(name = "notice")
	public String getNotice() {
		return notice;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "createUser")
	public String getCreateUser() {
		return createUser;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	@Column(name = "deleted")
	public Integer getDeleted() {
		return deleted;
	}
	@Column(name = "price")
	public Integer getPrice() {
		return price;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
