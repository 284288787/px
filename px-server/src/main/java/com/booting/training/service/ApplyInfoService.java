/** create by auto at 2017-12-19 15:16:14**/
package com.booting.training.service;

import com.star.framework.jdbc.service.BaseService;

import java.util.Date;
import java.util.Map;

import com.booting.training.dto.ApplyInfoDTO;
import com.booting.training.entity.ApplyInfoEntity;

/**
 * 培训报名信息服务
 *
 * @author auto
 *
 */
public interface ApplyInfoService extends BaseService<ApplyInfoEntity, ApplyInfoDTO> {

  public Map<String, Object> totalMoneyByPromoter(Long promoterId, Date beginDatePoint);

}