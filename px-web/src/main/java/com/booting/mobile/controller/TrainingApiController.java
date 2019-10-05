/**create by liuhua at 2017年12月25日 上午11:53:07**/
package com.booting.mobile.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.service.impl.TrainingWebService;
import com.booting.training.dto.ApplyInfoDTO;
import com.booting.training.dto.PromoterDTO;
import com.booting.training.dto.TrainingItemDTO;
import com.star.framework.jdbc.dao.result.QueryParam;
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
@RequestMapping("/api")
@Api(value = "培训相关", description = "培训相关")
public class TrainingApiController {

  @Autowired
  private TrainingWebService trainingWebService;

  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/getTrainingItems", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({ @ApiImplicitParam(name = "type", value = "类型 1幼儿园足球体能发开课程 2青少年足球培训 3教练员培训", paramType = "query", required = false, dataType = "int"), @ApiImplicitParam(name = "areaId", value = "地区id", paramType = "query", required = false, dataType = "long"), @ApiImplicitParam(name = "openId", value = "如果有则传，在返回的列表中会加一个标识：标识这个项目是否已报名", paramType = "query", required = false, dataType = "long"),
      @ApiImplicitParam(name = "pageNo", value = "页码 默认1", paramType = "query", required = false, dataType = "int"), @ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "int"), })
  @ApiOperation(value = "培训项目列表", notes = "培训项目列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String getTrainingItems(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    Integer pageNo = paramHandler.getInteger("pageNo");
    Integer pageSize = paramHandler.getInteger("pageSize");
    String openId = paramHandler.getString("openId");
    TrainingItemDTO trainingItemDTO = paramHandler.getDTO(TrainingItemDTO.class);
    trainingItemDTO.setDeleted(0);
    trainingItemDTO.setEnabled(1);
    QueryParam queryParam = new QueryParam();
    if (null != pageNo) {
      queryParam.setPageNo(pageNo);
    }
    if (null != pageSize) {
      queryParam.setPageSize(pageSize);
    }
    queryParam.setParam(trainingItemDTO);
    ApiResult apiResult = trainingWebService.searchTrainingItems(openId, queryParam);
    return ParamHandler.objToString(apiResult);
  }

  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/getTrainingItem", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({ @ApiImplicitParam(name = "itemId", value = "培训项目Id", paramType = "query", required = true, dataType = "long"), })
  @ApiOperation(value = "根据培训项目id获取详情", notes = "根据培训项目id获取详情", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String getTrainingItem(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    Long itemId = paramHandler.getLong("itemId");
    TrainingItemDTO trainingItemDTO = this.trainingWebService.getTrainingItem(itemId);
    ApiResult apiResult = new ApiResult(trainingItemDTO);
    return ParamHandler.objToString(apiResult);
  }
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/trainingApply", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({ 
    @ApiImplicitParam(name = "itemId", value = "培训项目Id", paramType = "query", required = true, dataType = "int"), 
    @ApiImplicitParam(name = "name", value = "家长姓名", paramType = "query", required = true, dataType = "String"), 
    @ApiImplicitParam(name = "mobile", value = "家长电话", paramType = "query", required = true, dataType = "String"), 
    @ApiImplicitParam(name = "childName", value = "孩子姓名", paramType = "query", required = true, dataType = "String"),
    @ApiImplicitParam(name = "childBirth", value = "孩子生日 yyyy-MM-dd 00:00:00", paramType = "query", required = true, dataType = "String"), @ApiImplicitParam(name = "childSex", value = "孩子性别 1男 0女", paramType = "query", required = true, dataType = "int"), @ApiImplicitParam(name = "address", value = "地址", paramType = "query", required = false, dataType = "String"), @ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"),
    @ApiImplicitParam(name = "sourceFrom", value = "来源 1安卓 2ios 3后台", paramType = "query", required = false, dataType = "String"), })
  @ApiOperation(value = "项目报名", notes = "项目报名", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String trainingApply(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    ApplyInfoDTO applyInfoDTO = paramHandler.getDTO(ApplyInfoDTO.class);
    Long applyId = trainingWebService.saveApplyInfo(applyInfoDTO);
    ApiResult apiResult = new ApiResult(applyId);
    return ParamHandler.objToString(apiResult);
  }

  @InterfaceVersion("1.1")
  @RequestMapping(value = "/{version}/trainingApply", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({ 
    @ApiImplicitParam(name = "itemId", value = "培训项目Id", paramType = "query", required = true, dataType = "int"), 
    @ApiImplicitParam(name = "name", value = "家长姓名", paramType = "query", required = true, dataType = "String"), 
    @ApiImplicitParam(name = "mobile", value = "家长电话", paramType = "query", required = true, dataType = "String"), 
    @ApiImplicitParam(name = "childName", value = "孩子姓名", paramType = "query", required = true, dataType = "String"),
      @ApiImplicitParam(name = "childBirth", value = "孩子生日 yyyy-MM-dd 00:00:00", paramType = "query", required = true, dataType = "String"), @ApiImplicitParam(name = "childSex", value = "孩子性别 1男 0女", paramType = "query", required = true, dataType = "int"), @ApiImplicitParam(name = "address", value = "地址", paramType = "query", required = false, dataType = "String"), @ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"),
      @ApiImplicitParam(name = "sourceFrom", value = "来源 1安卓 2ios 3后台", paramType = "query", required = false, dataType = "String"), })
  @ApiOperation(value = "项目报名", notes = "项目报名", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String trainingApply3(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    ApplyInfoDTO applyInfoDTO = paramHandler.getDTO(ApplyInfoDTO.class);
    Long applyId = trainingWebService.saveApplyInfo3(applyInfoDTO);
    ApiResult apiResult = new ApiResult(applyId);
    return ParamHandler.objToString(apiResult);
  }

  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/getApplyInfo", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({ @ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"), @ApiImplicitParam(name = "itemId", value = "培训项目Id", paramType = "query", required = true, dataType = "String"), })
  @ApiOperation(value = "获取某人某项目的报名信息，未报名则data为null", notes = "获取某人某项目的报名信息，未报名则data为null", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String getApplyInfo(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    String openId = paramHandler.getString("openId");
    Long itemId = paramHandler.getLong("itemId");
    if (StringUtils.isBlank(openId) && null == itemId) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ApplyInfoDTO applyInfoDTO = trainingWebService.getApplyInfo(itemId, openId);
    ApiResult apiResult = new ApiResult(applyInfoDTO);
    return ParamHandler.objToString(apiResult);
  }

  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/getApplyInfos", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({ 
    @ApiImplicitParam(name = "openId", value = "微信唯一标识", paramType = "query", required = true, dataType = "String"),
    @ApiImplicitParam(name = "pageNo", value = "页码 默认1", paramType = "query", required = false, dataType = "int"), 
    @ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "int"), })
  @ApiOperation(value = "获取某人所有的已报名项目列表", notes = "获取某人所有的已报名项目列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String getApplyInfos(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    Integer pageNo = paramHandler.getInteger("pageNo");
    Integer pageSize = paramHandler.getInteger("pageSize");
    ApplyInfoDTO applyInfoDTO = paramHandler.getDTO(ApplyInfoDTO.class);
    QueryParam queryParam = new QueryParam();
    if (null != pageNo) {
      queryParam.setPageNo(pageNo);
    }
    if (null != pageSize) {
      queryParam.setPageSize(pageSize);
    }
    queryParam.setParam(applyInfoDTO);
    ApiResult apiResult = trainingWebService.searchApplyInfos(queryParam);
    return ParamHandler.objToString(apiResult);
  }

  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/apply/total", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiOperation(value = "报名人数统计", notes = "报名人数统计及返回最新的10条报名记录", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String applyTotal(@ApiIgnore String params) throws Exception {
    Map<String, Object> map = trainingWebService.applyTotal();
    ApiResult apiResult = new ApiResult(map);
    return ParamHandler.objToString(apiResult);
  }
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/promoter/info", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiOperation(value = "推广员推广信息", notes = "推广员推广信息及提成", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({ 
    @ApiImplicitParam(name = "openId", value = "openId", paramType = "query", required = true, dataType = "String"), 
  })
  public String applyForPromoter(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    String openId = paramHandler.getString("openId");
    PromoterDTO promoter = trainingWebService.applyForPromoter(openId);
    ApiResult apiResult = new ApiResult(promoter);
    return ParamHandler.objToString(apiResult);
  }
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/promoter/apply/kickback", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiOperation(value = "推广员申请提现", notes = "推广员申请提现", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({ 
    @ApiImplicitParam(name = "openId", value = "openId", paramType = "query", required = true, dataType = "String"), 
    @ApiImplicitParam(name = "wxNumber", value = "wxNumber", paramType = "query", required = true, dataType = "String"), 
  })
  public String applyKickback(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    String openId = paramHandler.getString("openId");
    String wxNumber = paramHandler.getString("wxNumber");
    trainingWebService.applyKickback(openId, wxNumber);
    return null;
  }

  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/promoter/kickback/list", method = { RequestMethod.POST, RequestMethod.GET }, produces = "text/html;charset=UTF-8")
  @ApiOperation(value = "推广员提现记录", notes = "推广员提现记录", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({ 
    @ApiImplicitParam(name = "openId", value = "openId", paramType = "query", required = true, dataType = "String"), 
    @ApiImplicitParam(name = "pageNo", value = "页码 默认1", paramType = "query", required = false, dataType = "int"), 
    @ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "int")
  })
  public String kickbackList(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    String openId = paramHandler.getString("openId");
    Integer pageNo = paramHandler.getInteger("pageNo");
    Integer pageSize = paramHandler.getInteger("pageSize");
    QueryParam queryParam = new QueryParam();
    if (null != pageNo) {
      queryParam.setPageNo(pageNo);
    }
    if (null != pageSize) {
      queryParam.setPageSize(pageSize);
    }
    ApiResult apiResult = trainingWebService.kickbackList(openId, queryParam);
    return ParamHandler.objToString(apiResult);
  }
}
