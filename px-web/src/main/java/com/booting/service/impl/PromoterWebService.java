/**create by liuhua at 2019年3月8日 下午5:24:24**/
package com.booting.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.training.dto.PromoterDTO;
import com.booting.training.facade.TrainingFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service
public class PromoterWebService {

  @Autowired
  private TrainingFacade trainingFacade;

  public PageList<PromoterDTO> getListForPagePromoter(QueryParam queryParam, Class<PromoterDTO> class1) {
    return trainingFacade.getPromoterListForPage(queryParam);
  }

  public PromoterDTO getPromoter(Long promoterId) {
    return trainingFacade.getPromoter(promoterId);
  }

  public void updatePromoter(PromoterDTO promoterDTO) {
    if (null == promoterDTO || null == promoterDTO.getPromoterId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    trainingFacade.updatePromoter(promoterDTO);
  }

  public void savePromoter(PromoterDTO promoterDTO) {
    if (null == promoterDTO || StringUtils.isBlank(promoterDTO.getName()) || StringUtils.isBlank(promoterDTO.getMobile())) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    promoterDTO.setEnabled(1);
    promoterDTO.setDeleted(0);
    promoterDTO.setCreateTime(new Date());
    trainingFacade.savePromoter(promoterDTO);
  }

  public void enabledPromoter(String ids) {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    PromoterDTO dto = new PromoterDTO();
    dto.setEnabled(1);
    dto.setPromoterIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public void disabledPromoter(String ids) {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    PromoterDTO dto = new PromoterDTO();
    dto.setEnabled(0);
    dto.setPromoterIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public void deletePromoter(String ids) {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    PromoterDTO dto = new PromoterDTO();
    dto.setDeleted(1);
    dto.setPromoterIds(ids);
    trainingFacade.updateBySql(dto);
  }

}
