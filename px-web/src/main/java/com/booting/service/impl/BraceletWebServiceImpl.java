/**create by liuhua at 2018年6月1日 上午11:56:04**/
package com.booting.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.bracelet.dto.BeaconsInfo;
import com.booting.bracelet.dto.BraceletDTO;
import com.booting.bracelet.dto.BraceletRequestDto;
import com.booting.bracelet.dto.TotalData;
import com.booting.bracelet.facade.BraceletFacade;
import com.booting.bracelet.facade.PointFacade;
import com.booting.common.Parse;
import com.booting.kindergarten.dto.StudentDTO;
import com.booting.kindergarten.facade.KindergartenFacade;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.PageInfo;
import com.star.framework.specification.result.v2.ApiResult;

@Service("braceletWebServiceImpl")
public class BraceletWebServiceImpl {

  @Autowired
  private BraceletFacade braceletFacade;
  @Autowired
  private PointFacade pointFacade;
  @Autowired
  private KindergartenFacade kindergartenFacade;
  ObjectMapper objectMapper = new ObjectMapper();
  
  public ApiResult getDataByHour(BraceletDTO bracelet) throws ArgsException {
    List<BraceletDTO> extremeValue = this.braceletFacade.getDataByHour(bracelet);
    ApiResult apiResult = new ApiResult(extremeValue);
    return apiResult;
  }
  
  public ApiResult getDataByDay(BraceletDTO bracelet, Integer pageNo, Integer pageSize) throws ArgsException {
    QueryParam queryParam = new QueryParam();
    if (null != pageNo) {
      queryParam.setPageNo(pageNo);
    }
    if (null != pageSize) {
      queryParam.setPageSize(pageSize);
    }
    
    queryParam.setParam(bracelet);
    PageList<BraceletDTO> pageList = this.braceletFacade.getBraceletListForPageGroupDate(queryParam);
    
//    Map<String, Object> extremeValue = this.braceletFacade.getExtremeValue(bracelet, "heartRate");
//    extremeValue.put("list", pageList.getDataList());
    ApiResult apiResult = new ApiResult(pageList.getDataList());
    PageInfo pageInfo = new PageInfo(pageList.getPageNo(), pageList.getPageSize(), pageList.getTotalRecord());
    apiResult.setPageInfo(pageInfo);
    return apiResult;
  }
  
  public ApiResult getData(BraceletDTO bracelet, Integer pageNo, Integer pageSize) throws ArgsException {
    QueryParam queryParam = new QueryParam();
    if (null != pageNo) {
        queryParam.setPageNo(pageNo);
    }
    if (null != pageSize) {
        queryParam.setPageSize(pageSize);
    }
    
    queryParam.setParam(bracelet);
    PageList<BraceletDTO> pageList = this.braceletFacade.getBraceletListForPage(queryParam);
    
    Map<String, Object> extremeValue = this.braceletFacade.getExtremeValue(bracelet, "heartRate");
    extremeValue.put("list", pageList.getDataList());
    ApiResult apiResult = new ApiResult();
    apiResult.setData(extremeValue);
    PageInfo pageInfo = new PageInfo(pageList.getPageNo(), pageList.getPageSize(), pageList.getTotalRecord());
    apiResult.setPageInfo(pageInfo);
    return apiResult;
  }

  public void transportData(String beacons) throws JsonParseException, JsonMappingException, IOException {
    List<BeaconsInfo> list = objectMapper.readValue(beacons, new TypeReference<List<BeaconsInfo>>() {});
    List<BraceletDTO> bracelets = new ArrayList<>();
    for (BeaconsInfo beaconsInfo : list) {
      BraceletDTO b = Parse.getBracelet(beaconsInfo);
      StudentDTO studentDTO = new StudentDTO();
      studentDTO.setBraceletMac(b.getMac());
      studentDTO = kindergartenFacade.getStudent(studentDTO);
      if (null != studentDTO) {
        b.setStudentId(studentDTO.getStudentId());
      }
      bracelets.add(b);
//      this.braceletFacade.saveBracelet(b);
    }
    this.braceletFacade.batchSaveBracelet(bracelets);
    this.pointFacade.batchUpdatePoint(bracelets);
  }

  public ApiResult getStudentTotalData(TotalData totalData, Integer pageNo, Integer pageSize) {
    QueryParam queryParam = new QueryParam();
    if (null != pageNo) {
        queryParam.setPageNo(pageNo);
    }
    if (null != pageSize) {
        queryParam.setPageSize(pageSize);
    }
    
    queryParam.setParam(totalData);
    PageList<TotalData> pageList = this.braceletFacade.getStudentTotalData(queryParam);
    
    ApiResult apiResult = new ApiResult();
    apiResult.setData(pageList.getDataList());
    PageInfo pageInfo = new PageInfo(pageList.getPageNo(), pageList.getPageSize(), pageList.getTotalRecord());
    apiResult.setPageInfo(pageInfo);
    return apiResult;
  }

  public void postData(BraceletRequestDto request) throws ArgsException {
    if (null == request || null == request.getStepNum() || null == request.getCalorie() || null == request.getDistance() || null == request.getStudentId()
        || StringUtils.isBlank(request.getDeviceName()) || StringUtils.isBlank(request.getMac()) || null == request.getHrList() || request.getHrList().isEmpty()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    List<BraceletDTO> bracelets = new ArrayList<>();
    List<Integer> hrList = request.getHrList();
    Calendar calendar = Calendar.getInstance();
    for (Integer hr : hrList) {
      BraceletDTO braceletDTO = new BraceletDTO();
      braceletDTO.setFactoryCode("88888888");
      braceletDTO.setDeviceName(request.getDeviceName());
      braceletDTO.setMac(request.getMac());
      braceletDTO.setStepNum(request.getStepNum());
      braceletDTO.setCalorie(request.getCalorie());
      braceletDTO.setDistance(request.getDistance());
      braceletDTO.setStudentId(request.getStudentId());
      braceletDTO.setCreateTime(calendar.getTime());
      calendar.add(Calendar.SECOND, -30);
      braceletDTO.setHeartRate(hr);
      bracelets.add(braceletDTO);
    }
    
    this.braceletFacade.batchSaveBracelet(bracelets);
    this.pointFacade.batchUpdatePoint(bracelets);
  }

  public Map<String, Integer> getRankingOfStepNum(Long studentId) {
    Integer ranking = this.braceletFacade.getRankingOfStepNum(studentId);
    if (null == ranking) {
      ranking = 0;
    }
    Integer count = this.braceletFacade.getRankingOfStepNumCount();
    Map<String, Integer> map = new HashMap<>();
    map.put("ranking", ranking);
    map.put("count", count);
    return map;
  }

}
