/**create by liuhua at 2017年6月30日 下午2:35:08**/
package com.booting.mobile.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.court.dto.CourtDTO;
import com.booting.court.dto.SiteDTO;
import com.booting.service.CourtWebService;
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
@Api(value = "球场", description = "球场")
public class CourtApiController extends ApiBaseController {

	@Autowired
	private CourtWebService courtWebService;
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/courtStatus", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "courtId", value = "球馆ID", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "time", value = "时间", paramType = "query", required = false,  dataType = "String"),
	})
	@ApiOperation(value = "球场状态", notes = "球场状态", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String courtStatus(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long courtId = paramHandler.getLong("courtId");
		Date time = paramHandler.getDate("time", ParamHandler.ymd);
		Map<String, Object> data = this.courtWebService.courtStatus(courtId, time);
		ApiResult apiResult = new ApiResult(data);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/zoneStatusChange", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "zoneId", value = "区域ID", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "status", value = "状态 1被预定 2禁用 3历史记录(已经使用过)", paramType = "query", required = true, dataType = "int"),
		@ApiImplicitParam(name = "time", value = "时间 yyyy-MM-dd HH:mm:ss", paramType = "query", required = false,  dataType = "String"),
	})
	@ApiOperation(value = "球场状态", notes = "球场状态", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String zoneStatusChange(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long zoneId = paramHandler.getLong("zoneId");
		Integer status = paramHandler.getInteger("status");
		Date time = paramHandler.getDate("time", ParamHandler.ymdhms);
		if (null == zoneId || null == status || null == time) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		this.courtWebService.zoneStatusChange(zoneId, time, status);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/allCourts", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pageNo", value = "当前页面 默认 1", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "pageSize", value = "一页最大记录数 默认10", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "areaId", value = "城市ID", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "mobile", value = "球场联系人手机号", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "courtName", value = "球场名称", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "startMinute", value = "开始营业时间(分)，例如9点结束，endMinute为540(9*60),会查找大于等于540分的记录", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "endMinute", value = "结束营业时间(分)，例如12点结束，endMinute为720(12*60),会查找小于等于720分的记录", paramType = "query", required = false, dataType = "Integer"),
	})
	@ApiOperation(value = "所有球场", notes = "所有球场", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String allCourts(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Integer pageNo = paramHandler.getInteger("pageNo");
		Integer pageSize = paramHandler.getInteger("pageSize");
		QueryParam queryParam = new QueryParam();
		if (null != pageNo) {
			queryParam.setPageNo(pageNo);
		}
		if (null != pageSize) {
			queryParam.setPageSize(pageSize);
		}
		CourtDTO courtDTO = paramHandler.getDTO(CourtDTO.class);
		courtDTO.setEnabled(1);
		queryParam.setParam(courtDTO);
		ApiResult apiResult = this.courtWebService.getAllCourts(queryParam);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/courts", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "我的球场", notes = "我的球场", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String courts(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		List<Map<String, Object>> courts = this.courtWebService.getCourtsByLoginUserId(loginUserId);
		ApiResult apiResult = new ApiResult(courts);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getCourt", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "courtId", value = "球场Id", paramType = "query", required = true, dataType = "Long"),
	})
	@ApiOperation(value = "根据球场ID获取球场", notes = "根据球场ID获取球场", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String getCourt(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long courtId = paramHandler.getLong("courtId");
		Map<String, Object> court = this.courtWebService.getCourt(courtId);
		ApiResult apiResult = new ApiResult(court);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/addCourt", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "courtName", value = "球场名称", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "linkman", value = "联系人", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "mobile", value = "联系电话", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "areaId", value = "城市Id", paramType = "query", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "address", value = "详细地址", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "intro", value = "简介", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "longitude", value = "经度", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "latitude", value = "纬度", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "startMinute", value = "开始营业时间(分)", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "endMinute", value = "结束营业时间(分)", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "oneMinute", value = "一场时间(分)", paramType = "query", required = true, dataType = "Integer"),
		@ApiImplicitParam(name = "logo", value = "logo", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "添加球场", notes = "添加球场", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String addCourt(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		CourtDTO court = paramHandler.getDTO(CourtDTO.class);
		court.setUserId(loginUserId);
		this.courtWebService.addCourt(court);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/updateCourt", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "courtId", value = "球场Id", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "courtName", value = "球场名称", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "linkman", value = "联系人", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "mobile", value = "联系电话", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "areaId", value = "城市Id", paramType = "query", required = false, dataType = "Long"),
		@ApiImplicitParam(name = "address", value = "详细地址", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "intro", value = "简介", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "longitude", value = "经度", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "latitude", value = "纬度", paramType = "query", required = false, dataType = "String"),
		@ApiImplicitParam(name = "startMinute", value = "开始营业时间(分)", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "endMinute", value = "结束营业时间(分)", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "oneMinute", value = "一场时间(分)", paramType = "query", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "logo", value = "logo", paramType = "query", required = false, dataType = "String"),
	})
	@ApiOperation(value = "更新球场", notes = "更新球场", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String updateCourt(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		CourtDTO court = paramHandler.getDTO(CourtDTO.class);
		this.courtWebService.updateCourt(loginUserId, court);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/delCourt", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "courtId", value = "球场Id", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "删除球场", notes = "删除球场", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String delCourt(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		CourtDTO court = paramHandler.getDTO(CourtDTO.class);
		this.courtWebService.delCourt(loginUserId, court);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/sites", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "courtId", value = "球场Id", paramType = "query", required = true, dataType = "String"),
	})
	@ApiOperation(value = "根据球场Id获取场地", notes = "根据球场Id获取场地", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String sites(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long courtId = paramHandler.getLong("courtId");
		List<Map<String, Object>> sites = this.courtWebService.getSites(courtId);
		ApiResult apiResult = new ApiResult(sites);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/getSite", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "siteId", value = "场地Id", paramType = "query", required = true, dataType = "Long"),
	})
	@ApiOperation(value = "根据场地ID获取场地", notes = "根据场地ID获取场地", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String getSite(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		getLoginUserId(paramHandler);
		Long siteId = paramHandler.getLong("siteId");
		Map<String, Object> site = this.courtWebService.getSite(siteId);
		ApiResult apiResult = new ApiResult(site);
		return ParamHandler.gson.toJson(apiResult);
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/addSite", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "courtId", value = "球场Id", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "siteName", value = "场地名称", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "specification", value = "场地规格 1:11人场,2:8人场,3:5人场", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2,3"),
		@ApiImplicitParam(name = "knifing", value = "是否分隔成多个小场  只有11人场可以分   1可分 0不可分", paramType = "query", required = true, dataType = "Integer", allowableValues = "0,1"),
	})
	@ApiOperation(value = "添加场地", notes = "添加场地", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String addSite(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		SiteDTO site = paramHandler.getDTO(SiteDTO.class);
		this.courtWebService.addSite(loginUserId, site);
		return null;
	}
	
	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/updateSite", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "登录标识", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "courtId", value = "球场Id", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "siteId", value = "场地Id", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "siteName", value = "场地名称", paramType = "query", required = true, dataType = "String"),
		@ApiImplicitParam(name = "enabled", value = "1可用 0禁用", paramType = "query", required = true, dataType = "int"),
		@ApiImplicitParam(name = "specification", value = "场地规格 1:11人场,2:8人场,3:5人场", paramType = "query", required = true, dataType = "Integer", allowableValues = "1,2,3"),
		@ApiImplicitParam(name = "knifing", value = "是否分隔成多个小场  只有11人场可以分   1可分 0不可分", paramType = "query", required = true, dataType = "Integer", allowableValues = "0,1"),
	})
	@ApiOperation(value = "更新场地", notes = "更新场地", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
	public String updateSite(@ApiIgnore String params) throws Exception {
		ParamHandler paramHandler = new ParamHandler(params);
		Long loginUserId = getLoginUserId(paramHandler);
		SiteDTO site = paramHandler.getDTO(SiteDTO.class);
		this.courtWebService.updateSite(loginUserId, site);
		return null;
	}
}
