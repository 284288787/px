/**create by liuhua at 2019年2月28日 上午10:46:05**/
package com.booting.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.training.dto.StudyAddressDTO;
import com.booting.training.facade.TrainingFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service("studyAddressWebService")
public class StudyAddressWebService {

  @Autowired
  private TrainingFacade trainingFacade;

  public PageList<StudyAddressDTO> getListForPageStudyAddress(QueryParam queryParam, Class<StudyAddressDTO> class1) {
    return trainingFacade.getStudyAddressListForPage(queryParam);
  }

  public StudyAddressDTO getStudyAddress(Long addrId) {
    return trainingFacade.getStudyAddress(addrId);
  }

  public void updateStudyAddress(StudyAddressDTO studyAddressDTO) {
    if (null == studyAddressDTO || null == studyAddressDTO.getAddrId() || StringUtils.isBlank(studyAddressDTO.getAddrName())) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    trainingFacade.updateStudyAddress(studyAddressDTO);
  }

  public void saveStudyAddress(StudyAddressDTO studyAddressDTO) {
    if (null == studyAddressDTO || StringUtils.isBlank(studyAddressDTO.getAddrName()) || null == studyAddressDTO.getViewFront()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    studyAddressDTO.setEnabled(1);
    studyAddressDTO.setDeleted(0);
    studyAddressDTO.setCreateTime(new Date());
    trainingFacade.saveStudyAddress(studyAddressDTO);
  }

  public void enabledStudyAddress(String ids) {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    StudyAddressDTO dto = new StudyAddressDTO();
    dto.setEnabled(1);
    dto.setAddrIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public void disabledStudyAddress(String ids) {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    StudyAddressDTO dto = new StudyAddressDTO();
    dto.setEnabled(0);
    dto.setAddrIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public void deleteStudyAddress(String ids) {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    StudyAddressDTO dto = new StudyAddressDTO();
    dto.setDeleted(1);
    dto.setAddrIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public List<StudyAddressDTO> studyAddresses(StudyAddressDTO studyAddressDTO) {
    List<StudyAddressDTO> list = this.trainingFacade.getStudyAddressList(studyAddressDTO);
    return list;
  }
}
