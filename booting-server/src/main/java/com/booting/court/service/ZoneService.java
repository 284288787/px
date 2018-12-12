/** create by auto at 2017-06-23 16:07:39**/
package com.booting.court.service;

import com.star.framework.jdbc.service.BaseService;

import java.util.Map;

import com.booting.court.dto.ZoneDTO;
import com.booting.court.entity.ZoneEntity;

/**
 * 区块服务
 *
 * @author auto
 *
 */
public interface ZoneService extends BaseService<ZoneEntity, ZoneDTO> {

	ZoneDTO getUsableZone(Map<String, Object> params);

}