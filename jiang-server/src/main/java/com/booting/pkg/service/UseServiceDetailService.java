/** create by auto at 2017-08-04 11:36:30**/
package com.booting.pkg.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.pkg.dto.UseServiceDetailDTO;
import com.booting.pkg.entity.UseServiceDetailEntity;

/**
 * 使用服务明细服务
 *
 * @author auto
 *
 */
public interface UseServiceDetailService extends BaseService<UseServiceDetailEntity, UseServiceDetailDTO> {

	public Integer getUseServiceNum(Long userId, Long serviceId);

	public Integer getUsedServiceNumOfTeam(Long teamId, Long serviceId);

}