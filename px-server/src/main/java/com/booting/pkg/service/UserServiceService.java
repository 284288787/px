/** create by auto at 2017-06-07 10:16:54**/
package com.booting.pkg.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.pkg.dto.UserServiceDTO;
import com.booting.pkg.entity.UserServiceEntity;

/**
 * 用户服务服务
 *
 * @author auto
 *
 */
public interface UserServiceService extends BaseService<UserServiceEntity, UserServiceDTO> {

	public Integer getUserServiceNum(Long userId, Long serviceId);

	public Integer getServiceNumOfTeam(Long teamId, Long serviceId);

}