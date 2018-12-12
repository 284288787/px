/** create by liuhua at 2018年9月13日 上午11:14:13 **/
package com.booting.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.booting.bracelet.dto.PointLevelDTO;
import com.booting.bracelet.facade.PointFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service
public class PointWebService {

  @Autowired
  private PointFacade pointFacade;

  public PageList<PointLevelDTO> getListForPagePointLevel(QueryParam queryParam, Class<PointLevelDTO> class1) {
    return pointFacade.getPointLevelListForPage(queryParam);
  }

  public PointLevelDTO getPointLevel(Long id) {
    return pointFacade.getPointLevel(id);
  }

  public void savePointLevel(PointLevelDTO pointLevelDTO) throws ArgsException {
    if (null == pointLevelDTO || ! pointLevelDTO.checkeSaveData()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    PointLevelDTO temp = pointFacade.getByLevel(pointLevelDTO.getLevel());
    if (null != temp && temp.getLevel() == pointLevelDTO.getLevel().intValue()) {
      throw new ArgsException(FailureCode.ERR_002, pointLevelDTO.getLevel() + "级已经存在");
    }
    this.pointFacade.savePointLevel(pointLevelDTO);
  }

  public void updatePointLevel(PointLevelDTO pointLevelDTO) throws ArgsException {
    if (null == pointLevelDTO || ! pointLevelDTO.checkeUpdateData()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    PointLevelDTO temp = pointFacade.getPointLevel(pointLevelDTO.getId());
    if (null == temp) {
      throw new ArgsException(FailureCode.ERR_002, "记录不存在");
    }
    this.pointFacade.updatePointLevel(pointLevelDTO);
  }

  public void deletePointLevel(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    String[] id = ids.split(",");
    for (String i : id) {
      pointFacade.deletePointLevel(Long.parseLong(i));
    }
  }

}
