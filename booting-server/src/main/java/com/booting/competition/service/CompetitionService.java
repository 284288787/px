/** create by auto at 2017-07-15 16:10:54**/
package com.booting.competition.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.competition.dto.CompetitionDTO;
import com.booting.competition.entity.CompetitionEntity;

/**
 * 赛事信息服务
 *
 * @author auto
 *
 */
public interface CompetitionService extends BaseService<CompetitionEntity, CompetitionDTO> {

	CompetitionDTO getNewCompetition();

}