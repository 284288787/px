/**create by liuhua at 2019年3月27日 下午3:12:08**/
package com.booting.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.kindergarten.dto.KindergartenDTO;
import com.booting.service.impl.KindergartenWebService;
import com.star.framework.jdbc.dao.result.PageList;
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
@RequestMapping("/api/kindergarten")
@Api(value = "学校", description = "学校")
public class KindergartenApiController {
  
  @Autowired
  private KindergartenWebService kindergartenWebService;
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/list", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
    @ApiImplicitParam(name = "pageNo", value = "页码 默认1", paramType = "query", required = true, dataType = "int"),
    @ApiImplicitParam(name = "pageSize", value = "默认10", paramType = "query", required = true, dataType = "int"),
  })
  @ApiOperation(value = "获取学校列表", notes = "获取学校列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String list(@ApiIgnore String params) throws Exception {
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
    PageList<KindergartenDTO> list = this.kindergartenWebService.getListForPageKindergarten(queryParam, null);
    ApiResult apiResult = new ApiResult(list);
    return ParamHandler.objToString(apiResult);
  }
}
