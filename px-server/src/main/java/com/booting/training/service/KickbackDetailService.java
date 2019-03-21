/** create by auto at 2019-03-21 10:42:29**/
package com.booting.training.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.training.dto.KickbackDetailDTO;
import com.booting.training.entity.KickbackDetailEntity;

/**
 * 回扣发放明细服务
 *
 * @author auto
 *
 */
public interface KickbackDetailService extends BaseService<KickbackDetailEntity, KickbackDetailDTO> {

  public KickbackDetailDTO getLatestKickbackDetail(Long promoterId);

}