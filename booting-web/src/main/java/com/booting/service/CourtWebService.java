/**create by liuhua at 2017年6月30日 下午2:36:27**/
package com.booting.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.booting.court.dto.CourtDTO;
import com.booting.court.dto.SiteDTO;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.v2.ApiResult;

public interface CourtWebService {

	public List<Map<String, Object>> getCourtsByLoginUserId(Long loginUserId) throws ArgsException;

	public void addCourt(CourtDTO court) throws ArgsException;

	public void updateCourt(Long loginUserId, CourtDTO court) throws ArgsException;

	public void delCourt(Long loginUserId, CourtDTO court) throws ArgsException;

	public void addSite(Long loginUserId, SiteDTO site) throws ArgsException;

	public void updateSite(Long loginUserId, SiteDTO site) throws ArgsException;

	public List<Map<String, Object>> getSites(Long courtId) throws ArgsException;

	public Map<String, Object> getCourt(Long courtId) throws ArgsException;

	public Map<String, Object> getSite(Long siteId) throws ArgsException;

	public ApiResult getAllCourts(QueryParam queryParam);

	public void saveCourt(CourtDTO courtDTO) throws ArgsException;

	public void saveSite(SiteDTO siteDTO) throws ArgsException;

	public Map<String, Object> courtStatus(Long courtId, Date time) throws ArgsException;

	public void zoneStatusChange(Long zoneId, Date time, Integer status) throws ArgsException;

}
