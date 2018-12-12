/**create by liuhua at 2018年6月21日 下午2:57:24**/
package com.booting.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.booting.product.conf.ProductConst.ProductType;
import com.booting.product.dto.CurriculumDTO;
import com.booting.product.dto.ProductPictureDTO;
import com.booting.product.facade.ProductFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service("curriculumWebService")
public class CurriculumWebService {

  @Autowired
  private ProductFacade productFacade;

  public PageList<CurriculumDTO> getListForPageCurriculum(QueryParam queryParam, Class<CurriculumDTO> class1) {
    return productFacade.getCurriculumListForPage(queryParam);
  }

  public CurriculumDTO getCurriculum(Long curriculumId) {
    CurriculumDTO param = new CurriculumDTO();
    param.setCurriculumId(curriculumId);
    CurriculumDTO curriculumDTO = productFacade.getCurriculum(param);
    ProductPictureDTO ppd = new ProductPictureDTO();
    ppd.setBusiness(ProductType.curriculum.getBusiness());
    ppd.setBusinessId(curriculumId);
    List<ProductPictureDTO> pics = this.productFacade.getProductPictureList(ppd);
    curriculumDTO.setPictures(pics);
    return curriculumDTO;
  }

  public void saveCurriculum(CurriculumDTO curriculumDTO) throws ArgsException {
    if (null == curriculumDTO || ! curriculumDTO.checkSaveParam()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    CurriculumDTO param = new CurriculumDTO();
    param.setName(curriculumDTO.getName());
    param.setDeleted(0);
    List<CurriculumDTO> list = this.productFacade.getCurriculumList(param);
    if (null != list && list.size() > 0) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "添加失败，同样标题的活动已经存在");
    }
    curriculumDTO.setEnabled(1);
    curriculumDTO.setDeleted(0);
    curriculumDTO.setCreateTime(new Date());
    curriculumDTO.setUpdateTime(curriculumDTO.getCreateTime());
    Long curriculumId = this.productFacade.saveCurriculum(curriculumDTO);
    List<ProductPictureDTO> pictures = curriculumDTO.getPictures();
    for (ProductPictureDTO productPictureDTO : pictures) {
      productPictureDTO.setBusiness(ProductType.curriculum.getBusiness());
      productPictureDTO.setBusinessId(curriculumId);
    }
    this.productFacade.batchSaveProductPicture(pictures);
  }

  public void updateCurriculum(CurriculumDTO curriculumDTO) throws ArgsException {
    if (null == curriculumDTO || null == curriculumDTO.getCurriculumId() || ! curriculumDTO.checkSaveParam()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    CurriculumDTO param = new CurriculumDTO();
    param.setName(curriculumDTO.getName());
    param.setDeleted(0);
    List<CurriculumDTO> list = this.productFacade.getCurriculumList(param);
    if (null != list && list.size() > 0) {
      if (list.size() == 1) {
        if (curriculumDTO.getCurriculumId().longValue() != list.get(0).getCurriculumId()) {
          throw new ArgsException(FailureCode.ERR_002.getCode(), "更新失败，同样标题的活动已经存在");
        }
      }else{
        throw new ArgsException(FailureCode.ERR_002.getCode(), "更新失败，同样标题的活动存在太多，联系管理员");
      }
    }
    this.productFacade.updateCurriculum(curriculumDTO);
    this.productFacade.deleteProductPictureBy(ProductType.curriculum.getBusiness(), curriculumDTO.getCurriculumId());
    List<ProductPictureDTO> pictures = curriculumDTO.getPictures();
    for (ProductPictureDTO productPictureDTO : pictures) {
      productPictureDTO.setBusiness(ProductType.curriculum.getBusiness());
      productPictureDTO.setBusinessId(curriculumDTO.getCurriculumId());
    }
    this.productFacade.batchSaveProductPicture(pictures);
  }

  public void enabledCurriculum(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    CurriculumDTO dto = new CurriculumDTO();
    dto.setEnabled(1);
    dto.setCurriculumIds(ids);
    productFacade.updateBySql(dto);
  }

  public void disabledCurriculum(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    CurriculumDTO dto = new CurriculumDTO();
    dto.setEnabled(0);
    dto.setCurriculumIds(ids);
    productFacade.updateBySql(dto);
  }

  public void deleteCurriculum(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    CurriculumDTO dto = new CurriculumDTO();
    dto.setDeleted(1);
    dto.setCurriculumIds(ids);
    productFacade.updateBySql(dto);
  }
}
