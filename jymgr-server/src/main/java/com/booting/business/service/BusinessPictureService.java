/** create by auto at 2018-06-12 09:48:37**/
package com.booting.business.service;

import com.star.framework.jdbc.service.BaseService;
import java.util.Map;
import com.booting.business.dto.BusinessPictureDTO;
import com.booting.business.entity.BusinessPictureEntity;

/**
 * 图片服务
 *
 * @author auto
 *
 */
public interface BusinessPictureService extends BaseService<BusinessPictureEntity, BusinessPictureDTO> {

  public void deleteBySql(Map<String, Object> param);

}