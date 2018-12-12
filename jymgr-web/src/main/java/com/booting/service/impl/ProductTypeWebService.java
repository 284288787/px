/**create by liuhua at 2018年6月21日 下午2:36:05**/
package com.booting.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.booting.product.dto.ProductTypeDTO;
import com.booting.product.facade.ProductFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service("productTypeWebService")
public class ProductTypeWebService {

  @Autowired
  private ProductFacade productFacade;
  
  public PageList<ProductTypeDTO> getListForPageProductType(QueryParam queryParam, Class<ProductTypeDTO> class1) {
    return productFacade.getProductTypeListForPage(queryParam);
  }

  public void saveProductType(ProductTypeDTO productTypeDTO) throws ArgsException {
    if (null == productTypeDTO || StringUtils.isBlank(productTypeDTO.getTypeName()) || null == productTypeDTO.getBusiness()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ProductTypeDTO param = new ProductTypeDTO();
    param.setTypeName(productTypeDTO.getTypeName());
    param.setBusiness(productTypeDTO.getBusiness());
    List<ProductTypeDTO> list = productFacade.getProductTypeList(param);
    if (null != list && ! list.isEmpty()) {
      if (list.size() == 1) {
        ProductTypeDTO temp = list.get(0);
        if (temp.getDeleted() == 1) {
          temp.setDeleted(0);
          temp.setUpdateTime(new Date());
          this.productFacade.updateProductType(temp);
          return;
        }
        throw new ArgsException(FailureCode.ERR_002.getCode(), "该分类已经存在，不要重复添加");  
      }else{
        throw new ArgsException(FailureCode.ERR_002.getCode(), "请联系管理员");
      }
    }
    productTypeDTO.setDeleted(0);
    productTypeDTO.setEnabled(1);
    productTypeDTO.setCreateTime(new Date());
    productTypeDTO.setUpdateTime(productTypeDTO.getCreateTime());
    productFacade.saveProductType(productTypeDTO);
  }

  public ProductTypeDTO getProductType(Long typeId) {
    ProductTypeDTO type = this.productFacade.getProductType(typeId);
    return type;
  }

  public void updateProductType(ProductTypeDTO productTypeDTO) throws ArgsException {
    if (null == productTypeDTO || StringUtils.isBlank(productTypeDTO.getTypeName()) || null == productTypeDTO.getBusiness() || null == productTypeDTO.getTypeId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ProductTypeDTO old = this.productFacade.getProductType(productTypeDTO.getTypeId());
    if (null == old || null == old.getTypeId()) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "要修改的分类不存在");
    }
    ProductTypeDTO param = new ProductTypeDTO();
    param.setTypeName(productTypeDTO.getTypeName());
    param.setBusiness(productTypeDTO.getBusiness());
    List<ProductTypeDTO> list = productFacade.getProductTypeList(param);
    if (null != list && ! list.isEmpty()) {
      if (list.size() == 1) {
        ProductTypeDTO temp = list.get(0);
        if (temp.getTypeId() != productTypeDTO.getTypeId().intValue()) {
          throw new ArgsException(FailureCode.ERR_002.getCode(), "修改失败，该分类名称已经存在");  
        }
      }else{
        throw new ArgsException(FailureCode.ERR_002.getCode(), "请联系管理员");
      }
    }
    productTypeDTO.setUpdateTime(new Date());
    productFacade.updateProductType(productTypeDTO);
  }

  public void enabledProductType(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ProductTypeDTO dto = new ProductTypeDTO();
    dto.setEnabled(1);
    dto.setTypeIds(ids);
    productFacade.updateBySql(dto);
  }

  public void disabledProductType(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ProductTypeDTO dto = new ProductTypeDTO();
    dto.setEnabled(0);
    dto.setTypeIds(ids);
    productFacade.updateBySql(dto);
  }

  public void deleteProductType(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ProductTypeDTO dto = new ProductTypeDTO();
    dto.setDeleted(1);
    dto.setTypeIds(ids);
    productFacade.updateBySql(dto);
  }

  public List<ProductTypeDTO> getProductTypeList(ProductTypeDTO productTypeDTO) {
    return productFacade.getProductTypeList(productTypeDTO);
  }
  

}
