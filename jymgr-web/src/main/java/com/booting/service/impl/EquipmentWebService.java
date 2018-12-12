/**create by liuhua at 2018年6月21日 下午2:57:24**/
package com.booting.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.booting.product.conf.ProductConst.ProductType;
import com.booting.product.dto.EquipmentDTO;
import com.booting.product.dto.ProductPictureDTO;
import com.booting.product.facade.ProductFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service("equipmentWebService")
public class EquipmentWebService {

  @Autowired
  private ProductFacade productFacade;

  public PageList<EquipmentDTO> getListForPageEquipment(QueryParam queryParam, Class<EquipmentDTO> class1) {
    return productFacade.getEquipmentListForPage(queryParam);
  }

  public EquipmentDTO getEquipment(Long equipmentId) {
    EquipmentDTO param = new EquipmentDTO();
    param.setEquipmentId(equipmentId);
    EquipmentDTO equipmentDTO = productFacade.getEquipment(param);
    ProductPictureDTO ppd = new ProductPictureDTO();
    ppd.setBusiness(ProductType.equipment.getBusiness());
    ppd.setBusinessId(equipmentId);
    List<ProductPictureDTO> pics = this.productFacade.getProductPictureList(ppd);
    equipmentDTO.setPictures(pics);
    return equipmentDTO;
  }

  public void saveEquipment(EquipmentDTO equipmentDTO) throws ArgsException {
    if (null == equipmentDTO || ! equipmentDTO.checkSaveParam()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    EquipmentDTO param = new EquipmentDTO();
    param.setName(equipmentDTO.getName());
    param.setDeleted(0);
    List<EquipmentDTO> list = this.productFacade.getEquipmentList(param);
    if (null != list && list.size() > 0) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "添加失败，同样标题的活动已经存在");
    }
    equipmentDTO.setEnabled(1);
    equipmentDTO.setDeleted(0);
    equipmentDTO.setCreateTime(new Date());
    equipmentDTO.setUpdateTime(equipmentDTO.getCreateTime());
    Long equipmentId = this.productFacade.saveEquipment(equipmentDTO);
    List<ProductPictureDTO> pictures = equipmentDTO.getPictures();
    for (ProductPictureDTO productPictureDTO : pictures) {
      productPictureDTO.setBusiness(ProductType.equipment.getBusiness());
      productPictureDTO.setBusinessId(equipmentId);
    }
    this.productFacade.batchSaveProductPicture(pictures);
  }

  public void updateEquipment(EquipmentDTO equipmentDTO) throws ArgsException {
    if (null == equipmentDTO || null == equipmentDTO.getEquipmentId() || ! equipmentDTO.checkSaveParam()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    EquipmentDTO param = new EquipmentDTO();
    param.setName(equipmentDTO.getName());
    param.setDeleted(0);
    List<EquipmentDTO> list = this.productFacade.getEquipmentList(param);
    if (null != list && list.size() > 0) {
      if (list.size() == 1) {
        if (equipmentDTO.getEquipmentId().longValue() != list.get(0).getEquipmentId()) {
          throw new ArgsException(FailureCode.ERR_002.getCode(), "更新失败，同样标题的活动已经存在");
        }
      }else{
        throw new ArgsException(FailureCode.ERR_002.getCode(), "更新失败，同样标题的活动存在太多，联系管理员");
      }
    }
    this.productFacade.updateEquipment(equipmentDTO);
    this.productFacade.deleteProductPictureBy(ProductType.equipment.getBusiness(), equipmentDTO.getEquipmentId());
    List<ProductPictureDTO> pictures = equipmentDTO.getPictures();
    for (ProductPictureDTO productPictureDTO : pictures) {
      productPictureDTO.setBusiness(ProductType.equipment.getBusiness());
      productPictureDTO.setBusinessId(equipmentDTO.getEquipmentId());
    }
    this.productFacade.batchSaveProductPicture(pictures);
  }

  public void enabledEquipment(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    EquipmentDTO dto = new EquipmentDTO();
    dto.setEnabled(1);
    dto.setEquipmentIds(ids);
    productFacade.updateBySql(dto);
  }

  public void disabledEquipment(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    EquipmentDTO dto = new EquipmentDTO();
    dto.setEnabled(0);
    dto.setEquipmentIds(ids);
    productFacade.updateBySql(dto);
  }

  public void deleteEquipment(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    EquipmentDTO dto = new EquipmentDTO();
    dto.setDeleted(1);
    dto.setEquipmentIds(ids);
    productFacade.updateBySql(dto);
  }
}
