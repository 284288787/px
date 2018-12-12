/** create by auto at 2018-03-02 10:12:43**/
package com.booting.lottery.service;

import com.star.framework.jdbc.service.BaseService;

import java.util.List;
import java.util.Map;

import com.booting.lottery.dto.GoldDrawDetailDTO;
import com.booting.lottery.entity.GoldDrawDetailEntity;

/**
 * 金币提现明细服务
 *
 * @author auto
 *
 */
public interface GoldDrawDetailService extends BaseService<GoldDrawDetailEntity, GoldDrawDetailDTO> {

	public List<GoldDrawDetailDTO> getGoldDrawDetailList(Map<String, Object> params);

}