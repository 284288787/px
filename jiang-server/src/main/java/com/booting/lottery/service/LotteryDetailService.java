/** create by auto at 2018-03-02 10:12:43**/
package com.booting.lottery.service;

import java.util.List;
import java.util.Map;

import com.booting.lottery.dto.LotteryDTO;
import com.booting.lottery.dto.LotteryDetailDTO;
import com.booting.lottery.entity.LotteryDetailEntity;
import com.star.framework.jdbc.service.BaseService;

/**
 * 抽奖明细信息服务
 *
 * @author auto
 *
 */
public interface LotteryDetailService extends BaseService<LotteryDetailEntity, LotteryDetailDTO> {

	public Map<String, Integer> getDetail(LotteryDTO lottery, int lun, String awardUserIds);

	public List<Map<String, Object>> getHistory(int lun, int size);

}