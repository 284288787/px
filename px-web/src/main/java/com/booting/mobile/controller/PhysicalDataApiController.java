/** create by auto at 2018-01-15 10:25:41**/
package com.booting.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.kindergarten.dto.PhysicalDataDTO;
import com.booting.service.impl.KindergartenWebService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.version.InterfaceVersion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/physicalData")
@Api(value = "体测数据", description = "体测数据")
public class PhysicalDataApiController{

	@Autowired
	private KindergartenWebService kindergartenWebService;
	
	@InterfaceVersion("1.0")
    @RequestMapping(value = "/{version}/postData", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
    @ApiImplicitParams({
      @ApiImplicitParam(name = "datas", value = "数组[{},{}]", paramType = "query", required = true, dataType = "array"),
    })
    @ApiOperation(value = "传输体测数据", notes = "传输体测数据", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
    public String postData(@ApiIgnore String params) throws Exception {
      ParamHandler paramHandler = new ParamHandler(params);
      String datas = paramHandler.getString("datas");
      List<PhysicalDataDTO> request = ParamHandler.strToObj(datas, new TypeReference<List<PhysicalDataDTO>>() {});
      this.kindergartenWebService.postData(request);
      ApiResult apiResult = new ApiResult("ok");
      return ParamHandler.objToString(apiResult);
    }
}