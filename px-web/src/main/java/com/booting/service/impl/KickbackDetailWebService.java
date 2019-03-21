/**create by liuhua at 2019年3月21日 上午10:49:06**/
package com.booting.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.booting.training.dto.KickbackDetailDTO;
import com.booting.training.dto.PromoterDTO;
import com.booting.training.facade.TrainingFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.security.core.StarUserInfo;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service
public class KickbackDetailWebService {

  @Autowired
  private TrainingFacade trainingFacade;

  public PageList<KickbackDetailDTO> getListForPageKickbackDetail(QueryParam queryParam, Class<KickbackDetailDTO> class1) {
    return trainingFacade.getKickbackDetailListForPage(queryParam);
  }

  public KickbackDetailDTO getKickbackDetail(Long id) {
    return trainingFacade.getKickbackDetail(id);
  }

  public void updateKickbackDetail(KickbackDetailDTO kickbackDetailDTO) {
    if (null == kickbackDetailDTO || null == kickbackDetailDTO.getId() ||
        null == kickbackDetailDTO.getMoney()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String createUser = ((StarUserInfo) auth.getPrincipal()).getName();
    kickbackDetailDTO.setCreateUser(createUser);
    trainingFacade.updateKickbackDetail(kickbackDetailDTO);
  }

  public void saveKickbackDetail(KickbackDetailDTO kickbackDetailDTO) {
    if (null == kickbackDetailDTO || null == kickbackDetailDTO.getPromoterId() || null == kickbackDetailDTO.getMoney()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    PromoterDTO promoterDTO = new PromoterDTO();
    promoterDTO.setPromoterId(kickbackDetailDTO.getPromoterId());
    promoterDTO = trainingFacade.getPromoter(promoterDTO);
    if (null == promoterDTO || null == promoterDTO.getPromoterId()) {
      throw new ArgsException(FailureCode.ERR_002, "推广员不存在"); 
    }
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String createUser = ((StarUserInfo) auth.getPrincipal()).getName();
    kickbackDetailDTO.setCreateUser(createUser);
    kickbackDetailDTO.setCreateTime(new Date());
    trainingFacade.saveKickbackDetail(kickbackDetailDTO);
  }
}
