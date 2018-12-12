/** create by auto at 2018-03-02 10:12:43**/
package com.booting.lottery.service;

import com.star.framework.jdbc.service.BaseService;

import java.util.List;
import java.util.Map;

import com.booting.lottery.dto.GoldDetailDTO;
import com.booting.lottery.entity.GoldDetailEntity;

/**
 * 金币获得明细服务
 *
 * @author auto
 *
 */
public interface GoldDetailService extends BaseService<GoldDetailEntity, GoldDetailDTO> {

	public List<GoldDetailDTO> getGoldDetailList(Map<String, Object> params);

}