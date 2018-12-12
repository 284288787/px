/** create by auto at 2017-06-23 16:08:02**/
package com.booting.team.service;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.jdbc.service.BaseService;

import java.util.List;

import com.booting.team.dto.TeamDTO;
import com.booting.team.entity.TeamEntity;

/**
 * 球队服务
 *
 * @author auto
 *
 */
public interface TeamService extends BaseService<TeamEntity, TeamDTO> {

	PageList<TeamDTO> recommendTeams(QueryParam queryParam);

	TeamDTO getNewTeam();

	List<TeamDTO> getActiveTeams(int num);

}