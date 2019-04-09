/**create by liuhua at 2019年4月9日 上午10:55:56**/
package com.booting.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.training.dto.PhysicalClassDTO;
import com.booting.training.facade.TrainingFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service
public class PhysicalClassWebService {

  @Autowired
  private TrainingFacade trainingFacade;
  
  public PageList<PhysicalClassDTO> getListForPagePhysicalClass(QueryParam queryParam, Class<PhysicalClassDTO> class1) {
    return trainingFacade.getPhysicalClassListForPage(queryParam);
  }

  public PhysicalClassDTO getPhysicalClass(Long physicalClassId) {
    PhysicalClassDTO physicalClass = trainingFacade.getPhysicalClass(physicalClassId);
    return physicalClass;
  }

  public void updatePhysicalClass(PhysicalClassDTO physicalClassDTO) {
    if (null == physicalClassDTO || null == physicalClassDTO.getPhysicalClassId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    this.trainingFacade.updatePhysicalClass(physicalClassDTO);
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
    this.trainingFacade.savePhysicalClass(physicalClassDTO);
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

}
