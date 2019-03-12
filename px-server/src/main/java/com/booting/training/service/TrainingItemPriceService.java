/** create by auto at 2019-03-12 14:26:03**/
package com.booting.training.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.training.dto.TrainingItemPriceDTO;
import com.booting.training.entity.TrainingItemPriceEntity;

/**
 * 培训项目服务
 *
 * @author auto
 *
 */
public interface TrainingItemPriceService extends BaseService<TrainingItemPriceEntity, TrainingItemPriceDTO> {

  public void deleteTrainingItemPriceByItemId(Long itemId);

}