/** create by auto at 2018-03-02 10:12:43**/
package com.booting.lottery.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.lottery.dto.LotteryDTO;
import com.booting.lottery.entity.LotteryEntity;

/**
 * 抽奖主体信息服务
 *
 * @author auto
 *
 */
public interface LotteryService extends BaseService<LotteryEntity, LotteryDTO> {

	public LotteryDTO getNewestLottery(int end);

}