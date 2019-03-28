/**create by liuhua at 2019年3月27日 下午3:29:35**/
package com.booting.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.kindergarten.dto.TeacherDTO;
import com.booting.service.impl.KindergartenWebService;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.version.InterfaceVersion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/teacher/")
@Api(value = "教师", description = "教师")
public class TeacherApiController {
  
  @Autowired
  private KindergartenWebService kindergartenWebService;
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/list", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "schoolId", value = "学校id", paramType = "query", required = true, dataType = "Long"),
  })
  @ApiOperation(value = "获取教师列表", notes = "获取教师列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String list(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    Long schoolId = paramHandler.getLong("schoolId");
    if (null == schoolId) {
      throw new ArgsException(FailureCode.ERR_002, "schoolId 必传");
    }
    List<TeacherDTO> teachers = kindergartenWebService.getTeachers(schoolId);
    ApiResult apiResult = new ApiResult(teachers);
    return ParamHandler.objToString(apiResult);
  }
}
