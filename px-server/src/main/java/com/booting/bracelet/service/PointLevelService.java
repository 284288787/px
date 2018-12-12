/** create by auto at 2018-09-12 16:14:42**/
package com.booting.bracelet.service;

import com.booting.bracelet.dto.PointLevelDTO;
import com.booting.bracelet.entity.PointLevelEntity;
import com.star.framework.jdbc.service.BaseService;

/**
 * 级别配置服务
 *
 * @author auto
 *
 */
public interface PointLevelService extends BaseService<PointLevelEntity, PointLevelDTO> {

  public PointLevelDTO getByLevel(int level);

}