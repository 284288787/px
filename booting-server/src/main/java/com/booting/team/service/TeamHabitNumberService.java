/** create by auto at 2017-06-23 16:08:02**/
package com.booting.team.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.team.dto.TeamHabitNumberDTO;
import com.booting.team.entity.TeamHabitNumberEntity;

/**
 * 球队习惯_人数服务
 *
 * @author auto
 *
 */
public interface TeamHabitNumberService extends BaseService<TeamHabitNumberEntity, TeamHabitNumberDTO> {

	void deleteByTeamId(Long teamId);

}