/**create by liuhua at 2018年6月21日 下午2:57:24**/
package com.booting.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.booting.product.conf.ProductConst.ProductType;
import com.booting.product.dto.ActivityDTO;
import com.booting.product.dto.ProductPictureDTO;
import com.booting.product.facade.ProductFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service("activityWebService")
public class ActivityWebService {

  @Autowired
  private ProductFacade productFacade;

  public PageList<ActivityDTO> getListForPageActivity(QueryParam queryParam, Class<ActivityDTO> class1) {
    return productFacade.getActivityListForPage(queryParam);
  }

  public ActivityDTO getActivity(Long activityId) {
    ActivityDTO param = new ActivityDTO();
    param.setActivityId(activityId);
    ActivityDTO activityDTO = productFacade.getActivity(param);
    ProductPictureDTO ppd = new ProductPictureDTO();
    ppd.setBusiness(ProductType.activity.getBusiness());
    ppd.setBusinessId(activityId);
    List<ProductPictureDTO> pics = this.productFacade.getProductPictureList(ppd);
    activityDTO.setPictures(pics);
    return activityDTO;
  }

  public void saveActivity(ActivityDTO activityDTO) throws ArgsException {
    if (null == activityDTO || ! activityDTO.checkSaveParam()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ActivityDTO param = new ActivityDTO();
    param.setName(activityDTO.getName());
    param.setDeleted(0);
    List<ActivityDTO> list = this.productFacade.getActivityList(param);
    if (null != list && list.size() > 0) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "添加失败，同样标题的活动已经存在");
    }
    activityDTO.setEnabled(1);
    activityDTO.setDeleted(0);
    activityDTO.setCreateTime(new Date());
    activityDTO.setUpdateTime(activityDTO.getCreateTime());
    Long activityId = this.productFacade.saveActivity(activityDTO);
    List<ProductPictureDTO> pictures = activityDTO.getPictures();
    for (ProductPictureDTO productPictureDTO : pictures) {
      productPictureDTO.setBusiness(ProductType.activity.getBusiness());
      productPictureDTO.setBusinessId(activityId);
    }
    this.productFacade.batchSaveProductPicture(pictures);
  }

  public void updateActivity(ActivityDTO activityDTO) throws ArgsException {
    if (null == activityDTO || null == activityDTO.getActivityId() || ! activityDTO.checkSaveParam()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ActivityDTO param = new ActivityDTO();
    param.setName(activityDTO.getName());
    param.setDeleted(0);
    List<ActivityDTO> list = this.productFacade.getActivityList(param);
    if (null != list && list.size() > 0) {
      if (list.size() == 1) {
        if (activityDTO.getActivityId().longValue() != list.get(0).getActivityId()) {
          throw new ArgsException(FailureCode.ERR_002.getCode(), "更新失败，同样标题的活动已经存在");
        }
      }else{
        throw new ArgsException(FailureCode.ERR_002.getCode(), "更新失败，同样标题的活动存在太多，联系管理员");
      }
    }
    this.productFacade.updateActivity(activityDTO);
    this.productFacade.deleteProductPictureBy(ProductType.activity.getBusiness(), activityDTO.getActivityId());
    List<ProductPictureDTO> pictures = activityDTO.getPictures();
    for (ProductPictureDTO productPictureDTO : pictures) {
      productPictureDTO.setBusiness(ProductType.activity.getBusiness());
      productPictureDTO.setBusinessId(activityDTO.getActivityId());
    }
    this.productFacade.batchSaveProductPicture(pictures);
  }

  public void enabledActivity(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ActivityDTO dto = new ActivityDTO();
    dto.setEnabled(1);
    dto.setActivityIds(ids);
    productFacade.updateBySql(dto);
  }

  public void disabledActivity(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ActivityDTO dto = new ActivityDTO();
    dto.setEnabled(0);
    dto.setActivityIds(ids);
    productFacade.updateBySql(dto);
  }

  public void deleteActivity(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ActivityDTO dto = new ActivityDTO();
    dto.setDeleted(1);
    dto.setActivityIds(ids);
    productFacade.updateBySql(dto);
  }
}
