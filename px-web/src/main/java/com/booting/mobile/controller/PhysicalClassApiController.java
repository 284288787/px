/**create by liuhua at 2019年4月10日 上午11:00:46**/
package com.booting.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.service.impl.PhysicalClassWebService;
import com.booting.training.dto.AttendanceDTO;
import com.booting.training.dto.PhysicalClassDTO;
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
@RequestMapping("/api/physicalClass")
@Api(value = "体测课相关", description = "体测课相关")
public class PhysicalClassApiController {
  
  @Autowired
  private PhysicalClassWebService physicalClassWebService;
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/list", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "coachIds", value = "教练ids(英文逗号分隔),如果只查看该教练相关的体测课可以传这个参数", paramType = "query", required = false, dataType = "long"), 
    @ApiImplicitParam(name = "title", value = "体测课标题", paramType = "query", required = false, dataType = "String"), 
    @ApiImplicitParam(name = "beginSchoolTime", value = "上课时间大于，格式：yyyy-MM-dd HH:mm:ss", paramType = "query", required = false, dataType = "String"), 
    @ApiImplicitParam(name = "state", value = "状态 1未开始 2已开始", paramType = "query", required = false, dataType = "int"), 
    @ApiImplicitParam(name = "pageNo", value = "页码 默认1", paramType = "query", required = false, dataType = "int"), 
    @ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "int"), 
  })
  @ApiOperation(value = "体测课列表", notes = "体测课列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String list(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    Integer pageNo = paramHandler.getInteger("pageNo");
    Integer pageSize = paramHandler.getInteger("pageSize");
    PhysicalClassDTO physicalClassDTO = paramHandler.getDTO(PhysicalClassDTO.class);
    physicalClassDTO.setDeleted(0);
    physicalClassDTO.setEnabled(1);
    QueryParam queryParam = new QueryParam();
    if (null != pageNo) {
      queryParam.setPageNo(pageNo);
    }
    if (null != pageSize) {
      queryParam.setPageSize(pageSize);
    }
    queryParam.setParam(physicalClassDTO);
    ApiResult apiResult = physicalClassWebService.searchPhysicalClasses(queryParam);
    return ParamHandler.objToString(apiResult);
  }
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/detail", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "physicalClassId", value = "体测课Id", paramType = "query", required = true, dataType = "long"), 
  })
  @ApiOperation(value = "体测课详细", notes = "体测课详细", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String detail(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    Long physicalClassId = paramHandler.getLong("physicalClassId");
    PhysicalClassDTO physicalClassDTO = this.physicalClassWebService.getPhysicalClass(physicalClassId);
    ApiResult apiResult = new ApiResult(physicalClassDTO);
    return ParamHandler.objToString(apiResult);
  }
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/attendance", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "physicalClassId", value = "体测课Id", paramType = "query", required = true, dataType = "long"), 
    @ApiImplicitParam(name = "studentId", value = "学生Id", paramType = "query", required = true, dataType = "long"), 
    @ApiImplicitParam(name = "state", value = "1签到 2迟到 3请假 4其他", paramType = "query", required = true, dataType = "int"), 
    @ApiImplicitParam(name = "remark", value = "备注说明，空则填状态值 签到 迟到 等", paramType = "query", required = true, dataType = "String"), 
  })
  @ApiOperation(value = "签到出席", notes = "签到出席", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String attendance(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    Long physicalClassId = paramHandler.getLong("physicalClassId");
    Long studentId = paramHandler.getLong("studentId");
    AttendanceDTO attendance = paramHandler.getDTO(AttendanceDTO.class);
    if (null != attendance) {
      attendance.setBusinessId(physicalClassId);
      attendance.setAttendanceId(studentId);
      attendance.setType(1);
    }
    this.physicalClassWebService.attendance(attendance);
    return null;
  }
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/finish", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "physicalClassId", value = "体测课Id", paramType = "query", required = true, dataType = "long"), 
  })
  @ApiOperation(value = "结束体测课", notes = "结束体测课", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String finish(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    Long physicalClassId = paramHandler.getLong("physicalClassId");
    this.physicalClassWebService.finished(physicalClassId);
    return null;
  }
}
