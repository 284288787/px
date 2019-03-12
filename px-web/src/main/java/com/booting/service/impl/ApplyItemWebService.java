/**create by liuhua at 2019年3月12日 下午8:59:34**/
package com.booting.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.training.dto.ApplyItemDTO;
import com.booting.training.facade.TrainingFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service("applyItemWebService")
public class ApplyItemWebService {

  @Autowired
  private TrainingFacade trainingFacade;
  
  public PageList<ApplyItemDTO> getListForPageApplyItem(QueryParam queryParam, Class<ApplyItemDTO> class1) {
    return trainingFacade.getApplyItemListForPage(queryParam);
  }

  public ApplyItemDTO getApplyItem(Long applyItemId) {
    ApplyItemDTO item = trainingFacade.getApplyItem(applyItemId);
    return item;
  }

  public void updateApplyItem(ApplyItemDTO applyItemDTO) {
    if (null == applyItemDTO || null == applyItemDTO.getApplyItemId() || StringUtils.isBlank(applyItemDTO.getItemName())) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    trainingFacade.updateApplyItem(applyItemDTO);
  }

  public void saveApplyItem(ApplyItemDTO applyItemDTO) {
    if (null == applyItemDTO || StringUtils.isBlank(applyItemDTO.getItemName())) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    applyItemDTO.setEnabled(1);
    applyItemDTO.setDeleted(0);
    applyItemDTO.setCreateTime(new Date());
    trainingFacade.saveApplyItem(applyItemDTO);
  }

  public void enabledApplyItem(String ids) {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ApplyItemDTO dto = new ApplyItemDTO();
    dto.setEnabled(1);
    dto.setApplyItemIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public void disabledApplyItem(String ids) {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ApplyItemDTO dto = new ApplyItemDTO();
    dto.setEnabled(0);
    dto.setApplyItemIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public void deleteApplyItem(String ids) {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ApplyItemDTO dto = new ApplyItemDTO();
    dto.setDeleted(1);
    dto.setApplyItemIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public List<ApplyItemDTO> getApplyItems() {
    ApplyItemDTO applyItemDTO = new ApplyItemDTO();
    applyItemDTO.setDeleted(0);
    applyItemDTO.setEnabled(1);
    List<ApplyItemDTO> items = this.trainingFacade.getApplyItemList(applyItemDTO);
    return items;
  }

}
