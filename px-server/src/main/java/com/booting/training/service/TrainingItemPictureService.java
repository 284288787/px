/** create by auto at 2017-12-19 15:16:14**/
package com.booting.training.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.training.dto.TrainingItemPictureDTO;
import com.booting.training.entity.TrainingItemPictureEntity;

/**
 * 培训项目 海报服务
 *
 * @author auto
 *
 */
public interface TrainingItemPictureService extends BaseService<TrainingItemPictureEntity, TrainingItemPictureDTO> {

	public void deleteTrainingItemPictureByItemId(Long itemId);

}