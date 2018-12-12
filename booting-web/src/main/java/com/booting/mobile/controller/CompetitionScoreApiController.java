/**create by liuhua at 2017年8月30日 上午10:38:43**/
package com.booting.mobile.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.competition.dto.CompetitionScoreDTO;
import com.booting.service.CompetitionWebService;
import com.google.gson.reflect.TypeToken;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.version.InterfaceVersion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
@Api(value = "赛事成绩接口", description = "赛事成绩接口")
public class CompetitionScoreApiController extends ApiBaseController{

	@Autowired
	private CompetitionWebService competitionWebService;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/uploadCompetitionScore", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "上传比赛成绩", notes = "上传比赛成绩", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "competitionId", value = "赛事ID", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "userId", value = "球员的用户Id", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "goal", value = "进球个数，没有填0", paramType = "query", required = false, dataType = "int"),
		@ApiImplicitParam(name = "yellow", value = "黄牌数，没有填0", paramType = "query", required = false, dataType = "int"),
		@ApiImplicitParam(name = "red", value = "红牌数，没有填0", paramType = "query", required = false, dataType = "int"),
	})
	public String uploadCompetitionScore(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		String scores = paramHandler.getString("scores");
		Type type = new TypeToken<List<CompetitionScoreDTO>>(){}.getType();
		List<CompetitionScoreDTO> scores1 = ParamHandler.gson.fromJson(scores, type);
		this.competitionWebService.uploadCompetitionScore(loginUserId, scores1);
		return null;
	}

	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/searchCompetitionScore", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiOperation(value = "查看比赛成绩", notes = "查看比赛成绩", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "competitionId", value = "赛事ID", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "teamId", value = "球队Id", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer"),
	})
	public String searchCompetitionScore(@ApiIgnore String params) throws Exception{
		ParamHandler paramHandler = new ParamHandler(params);
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		CompetitionScoreDTO competitionScoreDTO = paramHandler.getDTO(CompetitionScoreDTO.class);
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		queryParam.setParam(competitionScoreDTO);
		ApiResult apiResult = this.competitionWebService.searchCompetitionScore(queryParam);
		return ParamHandler.gson.toJson(apiResult);
	}
}
