/** create by auto at 2017-05-24 09:23:10**/
package com.booting.system.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.system.dto.UserInfoDTO;
import com.booting.system.entity.UserInfoEntity;

/**
 * 用户信息服务
 *
 * @author auto
 *
 */
public interface UserInfoService extends BaseService<UserInfoEntity, UserInfoDTO> {

	public int getCount(UserInfoDTO userInfoDTO);

}