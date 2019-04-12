/**create by liuhua at 2017年12月18日 上午10:25:31**/
package com.booting.training.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.booting.coupon.dto.CouponRelationDTO;
import com.star.framework.aop.annotation.Description;

@Description(name = "培训项目")
public class TrainingItemDTO implements Serializable{
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
	private Integer price;         //* 100
	private String address;
	private Integer subType; // 项目类型 1普通项目 2体测项目
	private Long physicalClassId;  //体测课id
	
	private String cardIds;        //优惠券id,多个逗号分隔
	private String physicalClassName;  //体测课名称
	private Date physicalClassSchoolTime;
	private Integer physicalClassState;
	
	private List<TrainingItemPictureDTO> pictures;
	private String itemIds;
	private String areaName;
	private List<CouponRelationDTO> coupons;
	private List<TrainingItemPriceDTO> prices;
	private PhysicalClassDTO physicalClass;
	
	private boolean applied;
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Long getItemId() {
		return itemId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Long getAreaId() {
		return areaId;
	}
	
	public Date getBeginTime() {
		return beginTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public Integer getType() {
		return type;
	}
	
	public String getIntro() {
		return intro;
	}
	
	public String getNotice() {
		return notice;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public String getCreateUser() {
		return createUser;
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

	public Integer getEnabled() {
		return enabled;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public List<TrainingItemPictureDTO> getPictures() {
		return pictures;
	}

	public void setPictures(List<TrainingItemPictureDTO> pictures) {
		this.pictures = pictures;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public Integer getPrice() {
		return price;
	}

	public String getAddress() {
		return address;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean getApplied() {
		return applied;
	}

	public void setApplied(boolean applied) {
		this.applied = applied;
	}

	public String getCardIds() {
		return cardIds;
	}

	public void setCardIds(String cardIds) {
		this.cardIds = cardIds;
	}

	public List<CouponRelationDTO> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<CouponRelationDTO> coupons) {
		this.coupons = coupons;
	}

  public List<TrainingItemPriceDTO> getPrices() {
    return prices;
  }

  public void setPrices(List<TrainingItemPriceDTO> prices) {
    this.prices = prices;
  }

  public Integer getSubType() {
    return subType;
  }

  public void setSubType(Integer subType) {
    this.subType = subType;
  }

  public Long getPhysicalClassId() {
    return physicalClassId;
  }

  public void setPhysicalClassId(Long physicalClassId) {
    this.physicalClassId = physicalClassId;
  }

  public String getPhysicalClassName() {
    return physicalClassName;
  }

  public void setPhysicalClassName(String physicalClassName) {
    this.physicalClassName = physicalClassName;
  }

  public Date getPhysicalClassSchoolTime() {
    return physicalClassSchoolTime;
  }

  public Integer getPhysicalClassState() {
    return physicalClassState;
  }

  public void setPhysicalClassSchoolTime(Date physicalClassSchoolTime) {
    this.physicalClassSchoolTime = physicalClassSchoolTime;
  }

  public void setPhysicalClassState(Integer physicalClassState) {
    this.physicalClassState = physicalClassState;
  }

  public PhysicalClassDTO getPhysicalClass() {
    return physicalClass;
  }

  public void setPhysicalClass(PhysicalClassDTO physicalClass) {
    this.physicalClass = physicalClass;
  }
}
