/**create by liuhua at 2019年3月2日 下午8:39:57**/
package com.booting.mobile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.service.impl.ApplyItemWebService;
import com.booting.service.impl.StudyAddressWebService;
import com.booting.training.dto.ApplyItemDTO;
import com.booting.training.dto.StudyAddressDTO;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.version.InterfaceVersion;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api")
@Api(value = "校区", description = "校区")
public class StudyAddressApiController {

  @Autowired
  private StudyAddressWebService studyAddressWebService;
  @Autowired
  private ApplyItemWebService applyItemWebService;
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/studyAddresses", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
  })
  @ApiOperation(value = "上课地点列表", notes = "上课地点列表", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String studyAddresses(@ApiIgnore String params) throws Exception {
    ParamHandler paramHandler = new ParamHandler(params);
    StudyAddressDTO studyAddressDTO = paramHandler.getDTO(StudyAddressDTO.class);
    studyAddressDTO.setDeleted(0);
    studyAddressDTO.setEnabled(1);
    studyAddressDTO.setViewFront(1);
    List<StudyAddressDTO> list = studyAddressWebService.studyAddresses(studyAddressDTO);
    ApiResult apiResult = new ApiResult(list);
    return ParamHandler.objToString(apiResult);
  }
  
  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/applyItems", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
  @ApiImplicitParams({
  })
  @ApiOperation(value = "报名项目", notes = "报名项目", httpMethod = "POST", response = String.class, produces = "text/html;charset=UTF-8")
  public String applyItems(@ApiIgnore String params) throws Exception {
//      ParamHandler paramHandler = new ParamHandler(params);
    List<ApplyItemDTO> items = applyItemWebService.getApplyItems();
    ApiResult apiResult = new ApiResult(items);
    return ParamHandler.objToString(apiResult);
  }
}
