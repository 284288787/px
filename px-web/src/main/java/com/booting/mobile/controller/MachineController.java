/** create by liuhua at 2017年7月6日 下午4:45:23 **/
package com.booting.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.service.impl.BraceletWebServiceImpl;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.version.InterfaceVersion;

@RestController
@RequestMapping("/api")
public class MachineController {

  @Autowired
  private BraceletWebServiceImpl braceletWebService;

  @InterfaceVersion("1.0")
  @RequestMapping(value = "/{version}/transportData", method = {RequestMethod.POST, RequestMethod.GET})
  public String transportData(HttpServletRequest request) throws Exception {
    String beacons = request.getParameter("beacons");
    System.out.println(ParamHandler.getDateStringOfNow("yyyy-MM-dd HH:mm:ss") + " -> \n" + beacons);
    braceletWebService.transportData(beacons);
    ApiResult apiResult = new ApiResult("ok");
    return ParamHandler.objToString(apiResult);
  }
}
