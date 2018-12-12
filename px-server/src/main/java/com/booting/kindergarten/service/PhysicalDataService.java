/** create by auto at 2018-01-02 14:44:30**/
package com.booting.kindergarten.service;

import com.star.framework.jdbc.service.BaseService;

import java.util.List;

import com.booting.kindergarten.dto.PhysicalDataDTO;
import com.booting.kindergarten.entity.PhysicalDataEntity;

/**
 * 体测信息服务
 *
 * @author auto
 *
 */
public interface PhysicalDataService extends BaseService<PhysicalDataEntity, PhysicalDataDTO> {

	public void deletePhysicalDataBySql(PhysicalDataDTO params);

  public List<PhysicalDataDTO> getPhysicalDataList(PhysicalDataDTO physicalDataDTO, Integer num);

}