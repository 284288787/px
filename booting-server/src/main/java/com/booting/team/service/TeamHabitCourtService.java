/** create by auto at 2017-06-23 16:08:02**/
package com.booting.team.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.team.dto.TeamHabitCourtDTO;
import com.booting.team.entity.TeamHabitCourtEntity;

/**
 * 球队习惯_球场服务
 *
 * @author auto
 *
 */
public interface TeamHabitCourtService extends BaseService<TeamHabitCourtEntity, TeamHabitCourtDTO> {

	void deleteByTeamId(Long teamId);

}