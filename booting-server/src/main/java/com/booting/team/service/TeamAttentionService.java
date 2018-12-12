/** create by auto at 2017-07-11 10:00:35**/
package com.booting.team.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.team.dto.TeamAttentionDTO;
import com.booting.team.entity.TeamAttentionEntity;

/**
 * 球队关注服务
 *
 * @author auto
 *
 */
public interface TeamAttentionService extends BaseService<TeamAttentionEntity, TeamAttentionDTO> {

	Integer getTeamAttentionCount(TeamAttentionDTO teamAttentionDTO);

}