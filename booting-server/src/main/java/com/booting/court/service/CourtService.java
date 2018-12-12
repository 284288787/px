/** create by auto at 2017-06-23 16:07:39**/
package com.booting.court.service;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.jdbc.service.BaseService;
import com.booting.court.dto.CourtDTO;
import com.booting.court.entity.CourtEntity;

/**
 * 球场服务
 *
 * @author auto
 *
 */
public interface CourtService extends BaseService<CourtEntity, CourtDTO> {

	PageList<CourtDTO> usableCourts(QueryParam queryParam);

}