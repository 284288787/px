/** create by auto at 2018-03-03 09:21:19**/
package com.booting.lottery.service;

import com.star.framework.jdbc.service.BaseService;

import java.util.List;
import java.util.Map;

import com.booting.lottery.dto.UserNumDetailDTO;
import com.booting.lottery.entity.UserNumDetailEntity;

/**
 * 用户号码明细服务
 *
 * @author auto
 *
 */
public interface UserNumDetailService extends BaseService<UserNumDetailEntity, UserNumDetailDTO> {

	public List<UserNumDetailDTO> getNumberList(Map<String, Object> params);

}