/** create by auto at 2018-06-01 11:34:19**/
package com.booting.bracelet.service;

import java.util.List;
import java.util.Map;

import com.booting.bracelet.dto.BraceletDTO;
import com.booting.bracelet.dto.TotalData;
import com.booting.bracelet.entity.BraceletEntity;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.jdbc.service.BaseService;

/**
 * 手环信息服务
 *
 * @author auto
 *
 */
public interface BraceletService extends BaseService<BraceletEntity, BraceletDTO> {

  public List<BraceletDTO> getBraceletList(BraceletDTO braceletDTO, Integer num);

  public PageList<TotalData> getStudentTotalData(QueryParam queryParam);

  public Map<String, Object> getExtremeValue(BraceletDTO bracelet, String field);

  public PageList<BraceletDTO> getBraceletListForPageGroupDate(QueryParam queryParam);

  public List<BraceletDTO> getDataByHour(BraceletDTO bracelet);

  public Integer getRankingOfStepNum(Long studentId);

}