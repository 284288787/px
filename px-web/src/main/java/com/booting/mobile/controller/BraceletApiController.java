/**create by liuhua at 2018年1月31日 下午3:29:54**/
package com.booting.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.bracelet.dto.BraceletDTO;
import com.booting.bracelet.dto.BraceletRequestDto;
import com.booting.bracelet.dto.TotalData;
import com.booting.service.impl.BraceletWebServiceImpl;
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
@Api(value = "手环", description = "手环")
public class BraceletApiController extends ApiBaseController{

	@Autowired
	private BraceletWebServiceImpl braceletWebService;
	
    @InterfaceVersion("1.0")
    @RequestMapping(value = "/{version}/bracelet/postData", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "datas", value = "数组[{factoryCode:'', deviceName: '', mac: '', stepNum: 123, heartRate: 12, calorie: 21, distance: 123, quantity: '电量', other: '', studentId: 学生id},{}]", paramType = "query", required = true, dataType = "array"),
    })
	@ApiOperation(value = "传输体测数据", notes = "传输体测数据", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String postData(@ApiIgnore String params) throws Exception {
	  ParamHandler paramHandler = new ParamHandler(params);
	  String datas = paramHandler.getString("datas");
	  BraceletRequestDto request = ParamHandler.strToObj(datas, BraceletRequestDto.class);
	  this.braceletWebService.postData(request);
	  ApiResult apiResult = new ApiResult("ok");
	  return ParamHandler.objToString(apiResult);
	}
	  
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getDataByDay", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
	  @ApiImplicitParam(name = "studentId", value = "学生id(即编号去掉前缀)", paramType = "query", required = true, dataType = "long"),
	  @ApiImplicitParam(name = "startDate", value = "开始日期 yyyy-MM-dd", paramType = "query", required = true, dataType = "String"),
	  @ApiImplicitParam(name = "endDate", value = "结束日期 yyyy-MM-dd", paramType = "query", required = true, dataType = "String"),
	  @ApiImplicitParam(name = "dateType", value = "1:前1天  7:前7天 30:前30天", paramType = "query", required = true, dataType = "String"),
	  @ApiImplicitParam(name = "pageNo", value = "页码 默认1", paramType = "query", required = true, dataType = "int"),
	  @ApiImplicitParam(name = "pageSize", value = "默认10", paramType = "query", required = true, dataType = "int"),
	})
	@ApiOperation(value = "获取用户每天最值", notes = "获取用户每天最值", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String getDataByDay(@ApiIgnore String params) throws Exception {
	  ParamHandler paramHandler = new ParamHandler(params);
	  BraceletDTO bracelet = paramHandler.getDTO(BraceletDTO.class);
	  Integer pageNo = paramHandler.getInteger("pageNo");
	  Integer pageSize = paramHandler.getInteger("pageSize");
	  ApiResult apiResult = this.braceletWebService.getDataByDay(bracelet, pageNo, pageSize);
	  return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getDataByHour", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
	  @ApiImplicitParam(name = "studentId", value = "学生id(即编号去掉前缀)", paramType = "query", required = true, dataType = "long"),
	  @ApiImplicitParam(name = "startDate", value = "开始日期 yyyy-MM-dd", paramType = "query", required = true, dataType = "String"),
	  @ApiImplicitParam(name = "endDate", value = "结束日期 yyyy-MM-dd", paramType = "query", required = true, dataType = "String"),
	  @ApiImplicitParam(name = "dateType", value = "1:前1天  7:前7天 30:前30天", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "获取用户每小时详细数据", notes = "获取用户每小时详细数据", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String getDataByHour(@ApiIgnore String params) throws Exception {
	  ParamHandler paramHandler = new ParamHandler(params);
	  BraceletDTO bracelet = paramHandler.getDTO(BraceletDTO.class);
	  ApiResult apiResult = this.braceletWebService.getDataByHour(bracelet);
	  return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getData", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
	  @ApiImplicitParam(name = "studentId", value = "学生id(即编号去掉前缀)", paramType = "query", required = true, dataType = "long"),
	  @ApiImplicitParam(name = "date", value = "日期 yyyy-MM-dd", paramType = "query", required = true, dataType = "String"),
	  @ApiImplicitParam(name = "dateType", value = "1:前1天  7:前7天 30:前30天", paramType = "query", required = true, dataType = "String"),
	  @ApiImplicitParam(name = "pageNo", value = "页码 默认1", paramType = "query", required = true, dataType = "int"),
	  @ApiImplicitParam(name = "pageSize", value = "默认10", paramType = "query", required = true, dataType = "int"),
	})
	@ApiOperation(value = "获取用户每天详细数据", notes = "获取用户每天详细数据", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String getData(@ApiIgnore String params) throws Exception {
	  ParamHandler paramHandler = new ParamHandler(params);
	  BraceletDTO bracelet = paramHandler.getDTO(BraceletDTO.class);
	  String date = paramHandler.getString("date");
	  bracelet.setStartDate(date);
	  bracelet.setEndDate(date);
	  Integer pageNo = paramHandler.getInteger("pageNo");
	  Integer pageSize = paramHandler.getInteger("pageSize");
	  ApiResult apiResult = this.braceletWebService.getData(bracelet, pageNo, pageSize);
	  return ParamHandler.objToString(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getStudentTotalData", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
	  @ApiImplicitParam(name = "studentId", value = "学生id(即编号去掉前缀)", paramType = "query", required = true, dataType = "long"),
	  @ApiImplicitParam(name = "pageNo", value = "页码 默认1", paramType = "query", required = true, dataType = "int"),
	  @ApiImplicitParam(name = "pageSize", value = "默认10", paramType = "query", required = true, dataType = "int"),
	})
	@ApiOperation(value = "获取用户所有天数记录：按日期分组，获取每天的最高心率，最低心率，总步数，卡路里，距离", notes = "获取用户所有天数记录：按日期分组，获取每天的最高心率，最低心率，总步数，卡路里，距离", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String getStudentTotalData(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		TotalData totalData = paramHandler.getDTO(TotalData.class);
        Integer pageNo = paramHandler.getInteger("pageNo");
        Integer pageSize = paramHandler.getInteger("pageSize");
		ApiResult apiResult = this.braceletWebService.getStudentTotalData(totalData, pageNo, pageSize);
		return ParamHandler.objToString(apiResult);
	}
	
}
