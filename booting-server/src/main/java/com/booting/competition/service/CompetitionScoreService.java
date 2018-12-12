/** create by auto at 2017-07-15 16:10:54**/
package com.booting.competition.service;

import com.booting.competition.dto.CompetitionScoreDTO;
import com.booting.competition.entity.CompetitionScoreEntity;
import com.star.framework.jdbc.service.BaseService;

/**
 * 赛事成绩服务
 *
 * @author auto
 *
 */
public interface CompetitionScoreService extends BaseService<CompetitionScoreEntity, CompetitionScoreDTO> {

	public void deleteCompetitionScoreByCompetitionId(Long competitionId);

	public void deleteCompetitionScoreByTeamId(Long teamId);

	public Integer getScore(Long competitionId, Long teamId);

}