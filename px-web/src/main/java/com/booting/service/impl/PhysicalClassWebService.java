/**create by liuhua at 2019年4月9日 上午10:55:56**/
package com.booting.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.training.dto.AttendanceDTO;
import com.booting.training.dto.PhysicalClassCoachDTO;
import com.booting.training.dto.PhysicalClassDTO;
import com.booting.training.facade.TrainingFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.PageInfo;
import com.star.framework.specification.result.v2.ApiResult;

@Service
public class PhysicalClassWebService {

  @Autowired
  private TrainingFacade trainingFacade;
//  @Autowired
//  private KindergartenFacade kindergartenFacade;
  
  public PageList<PhysicalClassDTO> getListForPagePhysicalClass(QueryParam queryParam, Class<PhysicalClassDTO> class1) {
    return trainingFacade.getPhysicalClassListForPage(queryParam);
  }

  public PhysicalClassDTO getPhysicalClass(Long physicalClassId) {
    if (null == physicalClassId) {
      throw new ArgsException("physicalClassId 必填");
    }
    PhysicalClassDTO dto = new PhysicalClassDTO();
    dto.setPhysicalClassId(physicalClassId);
    PhysicalClassDTO physicalClass = this.trainingFacade.getPhysicalClass(dto);
    if (null == physicalClass) {
      throw new ArgsException("没有找到体测课");
    }
    PhysicalClassCoachDTO physicalClassCoachDTO = new PhysicalClassCoachDTO();
    physicalClassCoachDTO.setPhysicalClassId(physicalClassId);
    List<PhysicalClassCoachDTO> coachs = trainingFacade.getPhysicalClassCoachList(physicalClassCoachDTO);
    if (null != coachs && ! coachs.isEmpty()) {
      String names = coachs.stream().map(coach -> coach.getCoachName()).collect(Collectors.joining(","));
      String ids = coachs.stream().map(coach -> String.valueOf(coach.getCoachId())).collect(Collectors.joining(","));
      physicalClass.setCoachIds(ids);
      physicalClass.setCoachNames(names);
    }
//    StudentDTO studentDTO = new StudentDTO();
//    studentDTO.setType(2);
//    studentDTO.setPhysicalClassId(physicalClassId);
//    List<StudentDTO> students = kindergartenFacade.getStudentList(studentDTO);
//    physicalClass.setStudents(students);
    return physicalClass;
  }

  public void updatePhysicalClass(PhysicalClassDTO physicalClassDTO) {
    if (null == physicalClassDTO || null == physicalClassDTO.getPhysicalClassId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    this.trainingFacade.updatePhysicalClass(physicalClassDTO);
    if (StringUtils.isNotBlank(physicalClassDTO.getCoachIds())) {
      this.trainingFacade.deletePhysicalClassCoachByClassId(physicalClassDTO.getPhysicalClassId());
      List<PhysicalClassCoachDTO> list = Arrays.stream(physicalClassDTO.getCoachIds().split(",")).distinct().map(id ->{
        PhysicalClassCoachDTO dto = new PhysicalClassCoachDTO();
        dto.setCoachId(Long.parseLong(id));
        dto.setPhysicalClassId(physicalClassDTO.getPhysicalClassId());
        dto.setCreateTime(new Date());
        return dto;
      }).collect(Collectors.toList());
      this.trainingFacade.batchSavePhysicalClassCoach(list);
    }
  }

  public void savePhysicalClass(PhysicalClassDTO physicalClassDTO) {
    if (null == physicalClassDTO || StringUtils.isBlank(physicalClassDTO.getTitle()) || null == physicalClassDTO.getSchoolTime() ||
        null == physicalClassDTO.getDeadlineTime() || StringUtils.isBlank(physicalClassDTO.getAddress()) || 
        null == physicalClassDTO.getPrice()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    physicalClassDTO.setDeleted(0);
    physicalClassDTO.setEnabled(1);
    physicalClassDTO.setCreateTime(new Date());
    Long physicalClassId = this.trainingFacade.savePhysicalClass(physicalClassDTO);
    if (StringUtils.isNotBlank(physicalClassDTO.getCoachIds())) {
      List<PhysicalClassCoachDTO> list = Arrays.stream(physicalClassDTO.getCoachIds().split(",")).distinct().map(id ->{
        PhysicalClassCoachDTO dto = new PhysicalClassCoachDTO();
        dto.setCoachId(Long.parseLong(id));
        dto.setPhysicalClassId(physicalClassId);
        dto.setCreateTime(new Date());
        return dto;
      }).collect(Collectors.toList());
      this.trainingFacade.batchSavePhysicalClassCoach(list);
    }
  }

  public void enabledPhysicalClass(String ids) {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    PhysicalClassDTO dto = new PhysicalClassDTO();
    dto.setEnabled(1);
    dto.setPhysicalClassIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public void disabledPhysicalClass(String ids) {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    PhysicalClassDTO dto = new PhysicalClassDTO();
    dto.setEnabled(0);
    dto.setPhysicalClassIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public void deletePhysicalClass(String ids) {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    PhysicalClassDTO dto = new PhysicalClassDTO();
    dto.setDeleted(1);
    dto.setPhysicalClassIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public ApiResult searchPhysicalClasses(QueryParam queryParam) {
    PageList<PhysicalClassDTO> pageList = this.trainingFacade.getPhysicalClassListForPage(queryParam);
    List<PhysicalClassDTO> list = pageList.getDataList();
    ApiResult apiResult = new ApiResult();
    apiResult.setData(list);
    PageInfo pageInfo = new PageInfo(pageList.getPageNo(), pageList.getPageSize(), pageList.getTotalRecord());
    apiResult.setPageInfo(pageInfo);
    return apiResult;
  }

  public void attendance(AttendanceDTO attendance) {
    if (null == attendance || null == attendance.getAttendanceId() || 
        null == attendance.getBusinessId() || null == attendance.getState() ||
        StringUtils.isBlank(attendance.getRemark())) {
      throw new ArgsException("studentId physicalClassId state remark 必填");
    }
    attendance.setCreateTime(new Date());
    attendance.setType(1);
    this.trainingFacade.saveAttendance(attendance);
  }

  public void finished(Long physicalClassId) {
    if(null == physicalClassId) {
      throw new ArgsException(FailureCode.ERR_002, "physicalClassId必填");
    }
    PhysicalClassDTO dto = new PhysicalClassDTO();
    dto.setPhysicalClassId(physicalClassId);
    dto = this.trainingFacade.getPhysicalClass(dto);
    if (null == dto || null == dto.getPhysicalClassId()) {
      throw new ArgsException(FailureCode.ERR_002, "记录不存");
    }
    if (dto.getState() == 3) {
      throw new ArgsException(FailureCode.ERR_002, "该项目已经结束，不需要再次结束");
    }
    PhysicalClassDTO dto1 = new PhysicalClassDTO();
    dto1.setFinished(1);
    dto1.setPhysicalClassId(physicalClassId);
    trainingFacade.updateBySql(dto1);
  }

}
