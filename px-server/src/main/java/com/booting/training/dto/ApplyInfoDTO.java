/**create by liuhua at 2017年12月18日 上午11:47:48**/
package com.booting.training.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.star.framework.aop.annotation.Description;

@Description(name = "培训报名信息")
public class ApplyInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long applyId;
	private Long itemId;
	private String title;
	private Long userId;
	private Integer type;           //类型 1幼儿园管理员 2青少年 3教练员 4团体
	private String organizationName;//团体名称
	private String name;            //姓名
	private String mobile;          //电话
	
	//新增 下面6个字段
	private String childName;
	private Date childBirth;
	private Integer childAge;
	private Integer childSex;
	private Long addrId;
    private Long applyItemId;
    private Long promoterId;
    
    private String itemName;
    private Double brokerageRate;
    private Long totalFee;
	
    private String promoter;
    private String promoterMobile;
    private String address;         //地址
	private Integer certificateType;//证件类型 1身份证
	private String certificateCode; //证件号码
	private String openId;
	private Integer sourceFrom;     //来源 1安卓 2ios 3后台
	private Date createTime;
	private Integer status;         //状态 1待支付 2已支付 3已退款
	private String addrName;
	private String transactionId;
	private String orderNo;
	
	private TrainingItemDTO trainingItem; //培训项目
	private List<ApplyDetailDTO> details; //团体明细
	private String code;
	
	private String beginCreateTime;
	private String endCreateTime;
	
	public Long getApplyId() {
		return applyId;
	}
	
	public Integer getType() {
		return type;
	}
	
	public String getOrganizationName() {
		return organizationName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public String getAddress() {
		return address;
	}
	
	public Integer getCertificateType() {
		return certificateType;
	}
	
	public String getCertificateCode() {
		return certificateCode;
	}
	public Integer getSourceFrom() {
		return sourceFrom;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}
	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}
	public void setSourceFrom(Integer sourceFrom) {
		this.sourceFrom = sourceFrom;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public TrainingItemDTO getTrainingItem() {
		return trainingItem;
	}

	public List<ApplyDetailDTO> getDetails() {
		return details;
	}

	public void setTrainingItem(TrainingItemDTO trainingItem) {
		this.trainingItem = trainingItem;
	}

	public void setDetails(List<ApplyDetailDTO> details) {
		this.details = details;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

  public String getChildName() {
    return childName;
  }

  public Date getChildBirth() {
    return childBirth;
  }

  public Integer getChildSex() {
    return childSex;
  }

  public void setChildName(String childName) {
    this.childName = childName;
  }

  public void setChildBirth(Date childBirth) {
    this.childBirth = childBirth;
  }

  public void setChildSex(Integer childSex) {
    this.childSex = childSex;
  }

  public Long getAddrId() {
    return addrId;
  }

  public void setAddrId(Long addrId) {
    this.addrId = addrId;
  }

  public Long getApplyItemId() {
    return applyItemId;
  }

  public void setApplyItemId(Long applyItemId) {
    this.applyItemId = applyItemId;
  }

  public String getAddrName() {
    return addrName;
  }

  public void setAddrName(String addrName) {
    this.addrName = addrName;
  }

  public Long getPromoterId() {
    return promoterId;
  }

  public void setPromoterId(Long promoterId) {
    this.promoterId = promoterId;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getPromoter() {
    return promoter;
  }

  public String getPromoterMobile() {
    return promoterMobile;
  }

  public void setPromoter(String promoter) {
    this.promoter = promoter;
  }

  public void setPromoterMobile(String promoterMobile) {
    this.promoterMobile = promoterMobile;
  }

  public Integer getChildAge() {
    return childAge;
  }

  public void setChildAge(Integer childAge) {
    this.childAge = childAge;
  }

  public String getItemName() {
    return itemName;
  }

  public Double getBrokerageRate() {
    return brokerageRate;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public void setBrokerageRate(Double brokerageRate) {
    this.brokerageRate = brokerageRate;
  }

  public Long getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Long totalFee) {
    this.totalFee = totalFee;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getBeginCreateTime() {
    return beginCreateTime;
  }

  public String getEndCreateTime() {
    return endCreateTime;
  }

  public void setBeginCreateTime(String beginCreateTime) {
    this.beginCreateTime = beginCreateTime;
  }

  public void setEndCreateTime(String endCreateTime) {
    this.endCreateTime = endCreateTime;
  }
}
