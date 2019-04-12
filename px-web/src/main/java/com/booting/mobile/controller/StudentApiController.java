/**create by liuhua at 2019年3月27日 下午3:36:11**/
package com.booting.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.kindergarten.dto.StudentDTO;
import com.booting.service.impl.KindergartenWebService;
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
@RequestMapping("/api/student")
@Api(value = "学生", description = "学生")
public class StudentApiController {

  @Autowired
  private KindergartenWebService kindergartenWebService;
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/add", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "classId", value = "班级id，空则表示该学生没有班级", paramType = "query", required = false, dataType = "Long"),
    @ApiImplicitParam(name = "name", value = "学生姓名", paramType = "query", required = true, dataType = "String"),
    @ApiImplicitParam(name = "sex", value = "性别 1男 0女", paramType = "query", required = true, dataType = "int"),
    @ApiImplicitParam(name = "birth", value = "生日 yyyy-MM-dd 00:00:00", paramType = "query", required = true, dataType = "String"),
    @ApiImplicitParam(name = "guardianName", value = "监护人姓名", paramType = "query", required = true, dataType = "String"),
    @ApiImplicitParam(name = "guardianMobile", value = "监护人手机", paramType = "query", required = true, dataType = "String"),
    @ApiImplicitParam(name = "guardianType", value = "监护人关系: 1父亲 2母亲 3爷爷 4奶奶 5外公 6外婆 7其他", paramType = "query", required = true, dataType = "int"),
    @ApiImplicitParam(name = "braceletMac", value = "手环mac地址", paramType = "query", required = false, dataType = "String"),
    @ApiImplicitParam(name = "type", value = "1正式 2临时 默认正式", paramType = "query", required = false, dataType = "String"),
  })
  @ApiOperation(value = "添加学生信息", notes = "添加学生信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String add(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    StudentDTO studentDTO = paramHandler.getDTO(StudentDTO.class);
    kindergartenWebService.saveStudent(studentDTO);
    return null;
  }
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/edit", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "studentId", value = "学生id", paramType = "query", required = true, dataType = "Long"),
    @ApiImplicitParam(name = "classId", value = "班级id", paramType = "query", required = false, dataType = "Long"),
    @ApiImplicitParam(name = "name", value = "学生姓名", paramType = "query", required = false, dataType = "String"),
    @ApiImplicitParam(name = "sex", value = "性别 1男 0女", paramType = "query", required = false, dataType = "int"),
    @ApiImplicitParam(name = "birth", value = "生日 yyyy-MM-dd 00:00:00", paramType = "query", required = false, dataType = "String"),
    @ApiImplicitParam(name = "guardianName", value = "监护人姓名", paramType = "query", required = false, dataType = "String"),
    @ApiImplicitParam(name = "guardianMobile", value = "监护人手机", paramType = "query", required = false, dataType = "String"),
    @ApiImplicitParam(name = "guardianType", value = "监护人关系: 1父亲 2母亲 3爷爷 4奶奶 5外公 6外婆 7其他", paramType = "query", required = true, dataType = "int"),
    @ApiImplicitParam(name = "intro", value = "班级简介", paramType = "query", required = false, dataType = "String"),
  })
  @ApiOperation(value = "编辑学生信息", notes = "编辑学生信息", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String edit(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    StudentDTO studentDTO = paramHandler.getDTO(StudentDTO.class);
    kindergartenWebService.updateStudent(studentDTO);
    return null;
  }
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/list", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "physicalClassId", value = "体测课Id", paramType = "query", required = false, dataType = "String"), 
    @ApiImplicitParam(name = "type", value = "1正式 2临时", paramType = "query", required = false, dataType = "int"), 
    @ApiImplicitParam(name = "attendanceState", value = "1签到 2迟到 3请假 4其他", paramType = "query", required = false, dataType = "int"), 
    @ApiImplicitParam(name = "pageNo", value = "页码 默认1", paramType = "query", required = false, dataType = "int"), 
    @ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "int"), 
  })
  @ApiOperation(value = "体测课列表", notes = "体测课列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String list(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    Integer pageNo = paramHandler.getInteger("pageNo");
    Integer pageSize = paramHandler.getInteger("pageSize");
    StudentDTO studentDTO = paramHandler.getDTO(StudentDTO.class);
    studentDTO.setDeleted(0);
    studentDTO.setEnabled(1);
    QueryParam queryParam = new QueryParam();
    if (null != pageNo) {
      queryParam.setPageNo(pageNo);
    }
    if (null != pageSize) {
      queryParam.setPageSize(pageSize);
    }
    queryParam.setParam(studentDTO);
    ApiResult apiResult = kindergartenWebService.searchStudents(queryParam);
    return ParamHandler.objToString(apiResult);
  }
}
