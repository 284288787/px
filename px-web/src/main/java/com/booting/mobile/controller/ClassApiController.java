/**create by liuhua at 2019年3月27日 下午3:29:35**/
package com.booting.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.kindergarten.dto.ClassDTO;
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
@RequestMapping("/api/class/")
@Api(value = "班级", description = "班级")
public class ClassApiController {
  
  @Autowired
  private KindergartenWebService kindergartenWebService;
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/add", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "schoolId", value = "学校id", paramType = "query", required = true, dataType = "Long"),
    @ApiImplicitParam(name = "teacherId", value = "班主任id", paramType = "query", required = true, dataType = "Long"),
    @ApiImplicitParam(name = "name", value = "班级名称", paramType = "query", required = true, dataType = "String"),
    @ApiImplicitParam(name = "intro", value = "班级简介", paramType = "query", required = false, dataType = "String"),
  })
  @ApiOperation(value = "添加班级信息", notes = "添加班级信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String add(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    ClassDTO classDto = paramHandler.getDTO(ClassDTO.class);
    kindergartenWebService.saveClass(classDto);
    return null;
  }
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/edit", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "classId", value = "班级id", paramType = "query", required = true, dataType = "Long"),
    @ApiImplicitParam(name = "schoolId", value = "学校id", paramType = "query", required = false, dataType = "Long"),
    @ApiImplicitParam(name = "teacherId", value = "班主任id", paramType = "query", required = false, dataType = "Long"),
    @ApiImplicitParam(name = "name", value = "班级名称", paramType = "query", required = false, dataType = "String"),
    @ApiImplicitParam(name = "intro", value = "班级简介", paramType = "query", required = false, dataType = "String"),
  })
  @ApiOperation(value = "编辑班级信息", notes = "编辑班级信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String edit(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    ClassDTO classDto = paramHandler.getDTO(ClassDTO.class);
    kindergartenWebService.updateClass(classDto);
    return null;
  }
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/list", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "schoolId", value = "学校id", paramType = "query", required = true, dataType = "Long"),
  })
  @ApiOperation(value = "获取班级列表", notes = "获取班级列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String list(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    Long schoolId = paramHandler.getLong("schoolId");
    if (null == schoolId) {
      throw new ArgsException(FailureCode.ERR_002, "schoolId 必传");
    }
    List<ClassDTO> classes = kindergartenWebService.getClasses(schoolId);
    ApiResult apiResult = new ApiResult(classes);
    return ParamHandler.objToString(apiResult);
  }
}
