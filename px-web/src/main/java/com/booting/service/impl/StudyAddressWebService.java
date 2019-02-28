/**create by liuhua at 2019年2月28日 上午10:46:05**/
package com.booting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.training.dto.StudyAddressDTO;
import com.booting.training.facade.TrainingFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

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
    
  }

  public void saveStudyAddress(StudyAddressDTO studyAddressDTO) {
    // TODO Auto-generated method stub
    
  }

  public void enabledStudyAddress(String ids) {
    // TODO Auto-generated method stub
    
  }

  public void disabledStudyAddress(String ids) {
    // TODO Auto-generated method stub
    
  }

  public void deleteStudyAddress(String ids) {
    // TODO Auto-generated method stub
    
  }
}
