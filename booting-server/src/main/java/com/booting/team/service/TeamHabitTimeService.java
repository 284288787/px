/** create by auto at 2017-06-23 16:08:02**/
package com.booting.team.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.team.dto.TeamHabitTimeDTO;
import com.booting.team.entity.TeamHabitTimeEntity;

/**
 * 球队习惯_时间服务
 *
 * @author auto
 *
 */
public interface TeamHabitTimeService extends BaseService<TeamHabitTimeEntity, TeamHabitTimeDTO> {

	void deleteByTeamId(Long teamId);

}