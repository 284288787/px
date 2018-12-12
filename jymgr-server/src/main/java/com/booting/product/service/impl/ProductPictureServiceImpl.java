/** create by auto at 2018-06-21 22:25:28 **/
package com.booting.product.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.booting.product.dto.ProductPictureDTO;
import com.booting.product.entity.ProductPictureEntity;
import com.booting.product.service.ProductPictureService;
import com.star.framework.jdbc.dao.JDBCSupport;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

@Service("productPictureService")
public class ProductPictureServiceImpl extends JDBCSupport<ProductPictureEntity, ProductPictureDTO> implements ProductPictureService {

  private static final long serialVersionUID = 1L;

  @Override
  public ProductPictureDTO save(ProductPictureEntity productPictureEntity) {
    long id = this.persist(productPictureEntity);
    return get(id);
  }

  @Override
  public ProductPictureDTO update(ProductPictureEntity productPictureEntity) {
    this.dynamicMerge(productPictureEntity);
    return get(productPictureEntity.getId());
  }

  @Override
  public ProductPictureDTO updateAll(ProductPictureEntity productPictureEntity) {
    this.merge(productPictureEntity);
    return get(productPictureEntity.getId());
  }

  @Override
  public ProductPictureDTO updateBySql(ProductPictureDTO productPictureDTO) {
    if (null == productPictureDTO)
      return null;
    this.execute("productPicture.updateProductPicture", toMap(productPictureDTO, "yyyy-MM-dd HH:mm:ss"));
    if (null == productPictureDTO.getId())
      return null;
    return get(productPictureDTO.getId());
  }

  @Override
  public int delete(long id) {
    return this.del(id);
  }

  @Override
  public void batchSave(List<ProductPictureEntity> entities) {
    if (null == entities || entities.isEmpty()) {
      return;
    }
    Map<String, Object>[] params = toMap(entities);
    this.batch("productPicture.insertProductPicture", params);
  }

  @Override
  public void batchUpdate(List<ProductPictureDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    Map<String, Object>[] params = toMap(dtos);
    this.batch("productPicture.updateProductPicture", params);
  }

  @Override
  public ProductPictureDTO get(long id) {
    return getById(id);
  }

  @Override
  public ProductPictureDTO get(ProductPictureDTO productPictureDTO) {
    if (null == productPictureDTO) {
      return null;
    }
    Map<String, Object> param = toMap(productPictureDTO, "yyyy-MM-dd HH:mm:ss");
    return this.queryForObject("productPicture.getSimpleProductPictureList", param);
  }

  @Override
  public List<ProductPictureDTO> getSimpleList(ProductPictureDTO productPictureDTO) {
    Map<String, Object> param = toMap(productPictureDTO, "yyyy-MM-dd HH:mm:ss");
    return this.queryForList("productPicture.getSimpleProductPictureList", param);
  }

  @Override
  public PageList<ProductPictureDTO> getSimpleListForPage(ProductPictureDTO productPictureDTO, int pageNo, int pageSize) {
    QueryParam queryParam = new QueryParam();
    queryParam.setPageNo(pageNo);
    queryParam.setPageSize(pageSize);
    queryParam.setParam(productPictureDTO);
    return this.queryForPage("productPicture.getSimpleProductPictureListCount", "productPicture.getSimpleProductPictureList", queryParam);
  }

  @Override
  public PageList<ProductPictureDTO> getSimpleListForPage(QueryParam queryParam) {
    return this.queryForPage("productPicture.getSimpleProductPictureListCount", "productPicture.getSimpleProductPictureList", queryParam);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> List<T> getList(T dto) {
    Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
    return (List<T>) this.queryForList("productPicture.getProductPictureList", param);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
    QueryParam queryParam = new QueryParam();
    queryParam.setPageNo(pageNo);
    queryParam.setPageSize(pageSize);
    queryParam.setParam(dto);
    return (PageList<T>) this.queryForPage("productPicture.getProductPictureListCount", "productPicture.getProductPictureList", queryParam, dto.getClass());
  }

  @Override
  public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
    return (PageList<T>) this.queryForPage("productPicture.getProductPictureListCount", "productPicture.getProductPictureList", queryParam, clazz);
  }

  @Override
  public void deleteBySql(Map<String, Object> param) {
    this.execute("productPicture.deleteProductPicture", param);
  }

}
