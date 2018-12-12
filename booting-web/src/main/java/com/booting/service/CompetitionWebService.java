/**create by liuhua at 2017年7月15日 下午4:13:45**/
package com.booting.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.booting.competition.dto.CompetitionDTO;
import com.booting.competition.dto.CompetitionScoreDTO;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.v2.ApiResult;

public interface CompetitionWebService {

	public CompetitionDTO checkCompetition(CompetitionDTO competitionDTO) throws ArgsException;

	public Long enterCompetition(CompetitionDTO competitionDTO) throws ArgsException;

	public ApiResult usableCourts(QueryParam queryParam);

	public ApiResult recommendTeams(Long loginUserId, Integer recommend, QueryParam queryParam) throws ArgsException;

	public ApiResult recommendTeams(Long loginUserId, QueryParam queryParam) throws ArgsException, ParseException;

	public ApiResult mineCompetitions(Long loginUserId, QueryParam queryParam) throws ArgsException;

	public CompetitionDTO checkAcceptCompetition(CompetitionDTO competitionDTO) throws ArgsException;

	public void acceptEnterCompetition(CompetitionDTO competitionDTO) throws ArgsException;

	public CompetitionDTO competitionDetail(Long competitionId) throws ArgsException;

	public void cancelCompetition(Long loginUserId, Long competitionId) throws ArgsException;

	public void remindCompetition(Long loginUserId, Long competitionId) throws ArgsException;

	public Map<String, Object> homePage() throws ArgsException;

	public ApiResult findCompetitions(Long loginUserId, QueryParam queryParam) throws ArgsException;

	public ApiResult searchCompetitions(QueryParam queryParam) throws ArgsException;

	public void uploadCompetitionScore(Long loginUserId, List<CompetitionScoreDTO> scores) throws ArgsException;

	public ApiResult searchCompetitionScore(QueryParam queryParam);

	public Long addCompetition(Long loginUserId, CompetitionDTO competitionDTO) throws ArgsException;

	public void updateCompetition(Long loginUserId, CompetitionDTO competitionDTO) throws ArgsException;

}
