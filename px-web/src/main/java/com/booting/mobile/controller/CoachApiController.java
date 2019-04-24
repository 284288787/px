/**create by liuhua at 2018年1月31日 下午3:29:54**/
package com.booting.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.booting.kindergarten.dto.ClassCoachRelationDTO;
import com.booting.kindergarten.dto.CoachDTO;
import com.booting.kindergarten.dto.StudentDTO;
import com.booting.service.impl.KindergartenWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.specification.result.PageInfo;
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
@Api(value = "教练", description = "教练")
public class CoachApiController extends ApiBaseController{

	@Autowired
	private KindergartenWebService kindergartenWebService;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getCoach", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "获取教练信息", notes = "获取教练信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String getCoach(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		String openId = paramHandler.getString("openId");
		CoachDTO coachDTO = this.kindergartenWebService.getCoachByOpenId(openId);
		ApiResult apiResult = new ApiResult(coachDTO);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getCoachById", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "coachId", value = "教练id", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "获取教练信息", notes = "获取教练信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String getCoachById(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long coachId = paramHandler.getLong("coachId");
		CoachDTO coachDTO = this.kindergartenWebService.getCoach(coachId);
		ApiResult apiResult = new ApiResult(coachDTO);
		return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/coach/login", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
	  @ApiImplicitParam(name = "mobile", value = "教练手机号", paramType = "query", required = true, dataType = "String"),
	  @ApiImplicitParam(name = "code", value = "短信验证码<br>短信验证码调用发短信接口，tag为3", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "教练登录", notes = "教练登录", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String login(@ApiIgnore String params) throws Exception {
	  ParamHandler paramHandler = new ParamHandler(params);
	  String mobile = paramHandler.getString("mobile");
	  String code = paramHandler.getString("code");
	  CoachDTO coachDTO = this.kindergartenWebService.login(mobile, code);
	  ApiResult apiResult = new ApiResult(coachDTO);
	  return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/coach/classList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
	  @ApiImplicitParam(name = "coachId", value = "教练id", paramType = "query", required = true, dataType = "String"),
	  @ApiImplicitParam(name = "pageNo", value = "页码 默认1", paramType = "query", required = true, dataType = "int"),
	  @ApiImplicitParam(name = "pageSize", value = "默认10", paramType = "query", required = true, dataType = "int"),
	})
	@ApiOperation(value = "获取教练下的班级", notes = "获取教练下的班级", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String classList(@ApiIgnore String params) throws Exception {
	  ParamHandler paramHandler = new ParamHandler(params);
	  Long coachId = paramHandler.getLong("coachId");
	  Integer pageNo = paramHandler.getInteger("pageNo");
	  Integer pageSize = paramHandler.getInteger("pageSize");
	  PageList<ClassCoachRelationDTO> list = this.kindergartenWebService.classList(coachId, pageNo, pageSize);
	  ApiResult apiResult = new ApiResult(list);
	  return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
    @RequestMapping(value = "/{version}/class/studentList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "classId", value = "班级id", paramType = "query", required = true, dataType = "String"),
      @ApiImplicitParam(name = "pageNo", value = "页码 默认1", paramType = "query", required = true, dataType = "int"),
      @ApiImplicitParam(name = "pageSize", value = "默认10", paramType = "query", required = true, dataType = "int"),
    })
    @ApiOperation(value = "获取班级下的学生", notes = "获取班级下的学生", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
    public String studentList(@ApiIgnore String params) throws Exception {
        ParamHandler paramHandler = new ParamHandler(params);
        Long classId = paramHandler.getLong("classId");
        Integer pageNo = paramHandler.getInteger("pageNo");
        Integer pageSize = paramHandler.getInteger("pageSize");
        PageList<StudentDTO> pageList = this.kindergartenWebService.studentList(classId, pageNo, pageSize);
        ApiResult apiResult = new ApiResult();
        apiResult.setData(pageList.getDataList());
        PageInfo pageInfo = new PageInfo(pageList.getPageNo(), pageList.getPageSize(), pageList.getTotalRecord());
        apiResult.setPageInfo(pageInfo);
        return ParamHandler.objToString(apiResult);
    }
    
}
