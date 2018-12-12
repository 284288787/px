/** create by auto at 2018-06-21 22:25:28**/
package com.booting.product.service;

import com.star.framework.jdbc.service.BaseService;
import java.util.Map;
import com.booting.product.dto.ProductPictureDTO;
import com.booting.product.entity.ProductPictureEntity;

/**
 * 产品图片服务
 *
 * @author auto
 *
 */
public interface ProductPictureService extends BaseService<ProductPictureEntity, ProductPictureDTO> {

  public void deleteBySql(Map<String, Object> param);

}