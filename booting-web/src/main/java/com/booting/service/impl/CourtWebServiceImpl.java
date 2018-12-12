/**create by liuhua at 2017年6月30日 下午2:38:28**/
package com.booting.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.booting.common.CommonConstants;
import com.booting.common.CommonConstants.UserIdentity;
import com.booting.competition.dto.CompetitionDTO;
import com.booting.competition.facade.CompetitionFacade;
import com.booting.court.dto.CourtDTO;
import com.booting.court.dto.SiteDTO;
import com.booting.court.dto.ZoneDTO;
import com.booting.court.dto.ZoneUseDetailDTO;
import com.booting.court.facade.CourtFacade;
import com.booting.service.CourtWebService;
import com.booting.system.dto.UserAccountDTO;
import com.booting.system.facade.SystemFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.PageInfo;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.utils.CglibBeanUtils;

@Service("courtWebService")
public class CourtWebServiceImpl implements CourtWebService {

	@Autowired
	private CourtFacade courtFacade;
	@Autowired
	private SystemFacade systemFacade;
	@Autowired
	private CompetitionFacade competitionFacade;
	
	private List<CourtDTO> getCourtsByUserId(Long userId){
		CourtDTO courtDTO = new CourtDTO();
		courtDTO.setUserId(userId);
		List<CourtDTO> courts = courtFacade.getCourtList(courtDTO);
		return courts;
	}
	
	private List<CourtDTO> getCourtsByName(String courtName){
		CourtDTO courtDTO = new CourtDTO();
		courtDTO.setCourtName(courtName);
		List<CourtDTO> courts = courtFacade.getCourtList(courtDTO);
		return courts;
	}
	
	@Override
	public List<Map<String, Object>> getCourtsByLoginUserId(Long loginUserId) throws ArgsException {
		List<CourtDTO> courts = getCourtsByUserId(loginUserId);
		if (null == courts || courts.isEmpty()) {
			throw new ArgsException(FailureCode.ERR_201);
		}
		List<Map<String, Object>> res = new ArrayList<>();
		for (CourtDTO courtDTO : courts) {
			Map<String, Object> temp = new HashMap<>();
			CglibBeanUtils.addToMap(courtDTO, temp);
			res.add(temp);
		}
		return res;
	}

	@Override
	public void addCourt(CourtDTO court) throws ArgsException {
		if (null == court || null == court.getStartMinute() || null == court.getEndMinute() || null == court.getOneMinute() || null == court.getCourtName()) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		List<CourtDTO> courts = getCourtsByName(court.getCourtName());
		if (null != courts && ! courts.isEmpty()) {
			throw new ArgsException(FailureCode.ERR_202);
		}
//		int sm = court.getStartMinute();
//		int em = court.getEndMinute();
//		int om = court.getOneMinute();
//		if ((em - sm) % om != 0) {
//			throw new ArgsException(FailureCode.ERR_002.getCode(), "营业总时长和每场时长不能整除，请修改营业时间");
//		}
		court.setEnabled(1);
		court.setCreateTime(new Date());
		court.setModifyTime(court.getCreateTime());
		this.courtFacade.saveCourt(court);
	}

	@Override
	public void updateCourt(Long loginUserId, CourtDTO court) throws ArgsException {
		CourtDTO courtDTO = this.courtFacade.getCourt(court.getCourtId());
		if (null == courtDTO) {
			throw new ArgsException(FailureCode.ERR_205);
		}
		if (courtDTO.getUserId().longValue() != loginUserId) {
			throw new ArgsException(FailureCode.ERR_203);
		}
		List<CourtDTO> courts = getCourtsByName(court.getCourtName());
		if (null != courts && courts.size() > 0) {
			if (courts.size() == 1) {
				CourtDTO temp = courts.get(0);
				if (temp.getCourtId() != court.getCourtId()) {
					throw new ArgsException(FailureCode.ERR_202);
				}
			}else{
				throw new ArgsException(FailureCode.ERR_202);
			}
		}
		this.courtFacade.updateCourt(court);
	}

	@Override
	public void delCourt(Long loginUserId, CourtDTO court) throws ArgsException {
		CourtDTO courtDTO = this.courtFacade.getCourt(court.getCourtId());
		if (null == courtDTO) {
			throw new ArgsException(FailureCode.ERR_205);
		}
		if (courtDTO.getUserId().longValue() != loginUserId) {
			throw new ArgsException(FailureCode.ERR_203);
		}
		this.courtFacade.deleteCourt(court.getCourtId());
	}

	@Override
	public void addSite(Long loginUserId, SiteDTO site) throws ArgsException {
		CourtDTO courtDTO = this.courtFacade.getCourt(site.getCourtId());
		if (null == courtDTO) {
			throw new ArgsException(FailureCode.ERR_205);
		}
		if (courtDTO.getUserId().longValue() != loginUserId) {
			throw new ArgsException(FailureCode.ERR_203);
		}
		saveSite(site);
	}

	@Override
	public void updateSite(Long loginUserId, SiteDTO site) throws ArgsException {
		SiteDTO siteDTO = this.courtFacade.getSite(site.getSiteId());
		if (null == siteDTO) {
			throw new ArgsException(FailureCode.ERR_301);
		}
		if (siteDTO.getCourtId().longValue() != site.getCourtId()) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		CourtDTO courtDTO = this.courtFacade.getCourt(site.getCourtId());
		if (null == courtDTO) {
			throw new ArgsException(FailureCode.ERR_205);
		}
		if (courtDTO.getUserId().longValue() != loginUserId) {
			throw new ArgsException(FailureCode.ERR_203);
		}
		int oldSpec = siteDTO.getSpecification();
		int oldKnif = siteDTO.getKnifing();
		if (oldSpec != site.getSpecification() || oldKnif != site.getKnifing()) { //说明只是修改了基本信息，不会修改场地
			ZoneDTO zoneDTO = new ZoneDTO();
			zoneDTO.setSiteId(site.getSiteId());
			List<ZoneDTO> allZones = this.courtFacade.getZoneList(zoneDTO);
			if (null != allZones && allZones.size() > 0) {
				for (ZoneDTO zone : allZones) {
					ZoneUseDetailDTO zoneUseDetailDTO = new ZoneUseDetailDTO();
					zoneUseDetailDTO.setZoneId(zone.getZoneId());
					zoneUseDetailDTO.setStatus(1);
					zoneUseDetailDTO = this.courtFacade.getZoneUseDetail(zoneUseDetailDTO);
					if (null != zoneUseDetailDTO && null != zoneUseDetailDTO.getId()) {
						throw new ArgsException(FailureCode.ERR_302);
					}
				}
				for (ZoneDTO zoneDTO2 : allZones) {
					this.courtFacade.deleteZone(zoneDTO2.getZoneId());
				}
				this.courtFacade.updateSite(site);
				siteDTO = this.courtFacade.getSite(site.getSiteId());
				createZone(siteDTO, siteDTO.getSiteId(), siteDTO.getSpecification(), siteDTO.getKnifing());
			}
		}else{
			this.courtFacade.updateSite(site);
		}
	}

	@Override
	public List<Map<String, Object>> getSites(Long courtId) throws ArgsException {
		CourtDTO courtDTO = this.courtFacade.getCourt(courtId);
		if (null == courtDTO) {
			throw new ArgsException(FailureCode.ERR_205);
		}
		SiteDTO siteDTO = new SiteDTO();
		siteDTO.setCourtId(courtId);
		List<SiteDTO> sites = this.courtFacade.getSiteList(siteDTO);
		List<Map<String, Object>> res = new ArrayList<>();
		for (SiteDTO site : sites) {
			Map<String, Object> temp = new HashMap<>();
			CglibBeanUtils.addToMap(site, temp);
			temp.remove("siteIds");
			res.add(temp);
		}
		return res;
	}

	@Override
	public Map<String, Object> getCourt(Long courtId) throws ArgsException {
		CourtDTO court = new CourtDTO();
		court.setCourtId(courtId);
		CourtDTO courtDTO = this.courtFacade.getCourt(court);
		if (null == courtDTO) {
			throw new ArgsException(FailureCode.ERR_205);
		}
		
		Map<String, Object> temp = new HashMap<>();
		CglibBeanUtils.addToMap(courtDTO, temp);
		return temp;
	}

	@Override
	public Map<String, Object> getSite(Long siteId) throws ArgsException {
		SiteDTO siteDTO = this.courtFacade.getSite(siteId);
		ZoneDTO zoneDTO = new ZoneDTO();
		zoneDTO.setSiteId(siteId);
		List<ZoneDTO> zones = this.courtFacade.getZoneList(zoneDTO);
		Map<String, Object> temp = new HashMap<>();
		CglibBeanUtils.addToMap(siteDTO, temp);
		temp.remove("siteIds");
		temp.put("zones", zones);
		return temp;
	}

	@Override
	public ApiResult getAllCourts(QueryParam queryParam) {
		PageList<CourtDTO> courtPageList = this.courtFacade.getCourtListForPage(queryParam);
		List<CourtDTO> courts = courtPageList.getDataList();
		List<Map<String, Object>> res = new ArrayList<>();
		if (null != courts && ! courts.isEmpty()) {
			for (CourtDTO court : courts) {
				Map<String, Object> rec = new HashMap<>();
				CglibBeanUtils.addToMap(court, rec);
				rec.remove("courtIds");
				res.add(rec);
			}
		}
		ApiResult apiResult = new ApiResult();
		apiResult.setData(res);
		PageInfo pageInfo = new PageInfo(courtPageList.getPageNo(), courtPageList.getPageSize(), courtPageList.getTotalRecord());
		apiResult.setPageInfo(pageInfo);
		return apiResult;
	}

	@Override
	public void saveCourt(CourtDTO courtDTO) throws ArgsException {
		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setAccount(courtDTO.getAccount());
		userAccountDTO = systemFacade.getUserAccount(userAccountDTO);
		if (null == userAccountDTO || null == userAccountDTO.getUserId()) {
//			throw new ArgsException(FailureCode.ERR_401);
			Md5PasswordEncoder encode = new Md5PasswordEncoder();
			userAccountDTO = new UserAccountDTO();
			userAccountDTO.setAccount(courtDTO.getAccount());
			userAccountDTO.setMobile(courtDTO.getMobile());
			userAccountDTO.setPassword(encode.encodePassword("123456", null));
			userAccountDTO.setIdentity(UserIdentity.normal.getIdentity());
			userAccountDTO.setName(courtDTO.getCourtName());
			userAccountDTO.setAddress(courtDTO.getAddress());
			userAccountDTO.setSourceFrom(3);
			Long userId = systemFacade.saveUserAccount(userAccountDTO);
			courtDTO.setUserId(userId);
		}else{
			courtDTO.setUserId(userAccountDTO.getUserId());
		}
		addCourt(courtDTO);
	}

	@Override
	public void saveSite(SiteDTO site) throws ArgsException {
		site.setEnabled(1);
		site.setCreateTime(new Date());
		site.setModifyTime(site.getCreateTime());
		Long siteId = this.courtFacade.saveSite(site);
		Integer specification = site.getSpecification();
		Integer knifing = site.getKnifing();
		createZone(site, siteId, specification, knifing);
	}

	private void createZone(SiteDTO site, Long siteId, Integer specification, Integer knifing) {
		Date now = new Date();
		if (knifing == 1) {
			if (specification == 1) {
				ZoneDTO zoneDTO = new ZoneDTO();
				zoneDTO.setCreateTime(now);
				zoneDTO.setLevel(1);
				zoneDTO.setModifyTime(now);
				zoneDTO.setSiteId(siteId);
				zoneDTO.setZoneName(site.getSiteName() + "-11人场");
				Long zoneId = this.courtFacade.saveZone(zoneDTO);
				ZoneDTO zoneDTO2 = new ZoneDTO();
				zoneDTO2.setCreateTime(now);
				zoneDTO2.setLevel(2);
				zoneDTO2.setModifyTime(now);
				zoneDTO2.setSiteId(siteId);
				zoneDTO2.setParentId(zoneId);
				zoneDTO2.setParentIds(zoneId + "");
				zoneDTO2.setZoneName(site.getSiteName() + "-8人场A");
				this.courtFacade.saveZone(zoneDTO2);
				ZoneDTO zoneDTO3 = new ZoneDTO();
				zoneDTO3.setCreateTime(now);
				zoneDTO3.setLevel(2);
				zoneDTO3.setModifyTime(now);
				zoneDTO3.setSiteId(siteId);
				zoneDTO3.setParentId(zoneId);
				zoneDTO3.setParentIds(zoneId + "");
				zoneDTO3.setZoneName(site.getSiteName() + "-8人场B");
				this.courtFacade.saveZone(zoneDTO3);
			}else if (specification == 2) {
				ZoneDTO zoneDTO = new ZoneDTO();
				zoneDTO.setCreateTime(now);
				zoneDTO.setLevel(2);
				zoneDTO.setModifyTime(now);
				zoneDTO.setSiteId(siteId);
				zoneDTO.setZoneName(site.getSiteName() + "-8人场");
				Long zoneId = this.courtFacade.saveZone(zoneDTO);
				ZoneDTO zoneDTO2 = new ZoneDTO();
				zoneDTO2.setCreateTime(now);
				zoneDTO2.setLevel(3);
				zoneDTO2.setModifyTime(now);
				zoneDTO2.setSiteId(siteId);
				zoneDTO2.setParentId(zoneId);
				zoneDTO2.setParentIds(zoneId + "");
				zoneDTO2.setZoneName(site.getSiteName() + "-5人场A");
				this.courtFacade.saveZone(zoneDTO2);
				ZoneDTO zoneDTO3 = new ZoneDTO();
				zoneDTO3.setCreateTime(now);
				zoneDTO3.setLevel(3);
				zoneDTO3.setModifyTime(now);
				zoneDTO3.setSiteId(siteId);
				zoneDTO3.setParentId(zoneId);
				zoneDTO3.setParentIds(zoneId + "");
				zoneDTO3.setZoneName(site.getSiteName() + "-5人场B");
				this.courtFacade.saveZone(zoneDTO3);
			}else{
				ZoneDTO zoneDTO = new ZoneDTO();
				zoneDTO.setCreateTime(now);
				zoneDTO.setLevel(3);
				zoneDTO.setModifyTime(now);
				zoneDTO.setSiteId(siteId);
				zoneDTO.setZoneName(site.getSiteName() + "-5人场");
				this.courtFacade.saveZone(zoneDTO);
			}
		}else{
			ZoneDTO zoneDTO = new ZoneDTO();
			zoneDTO.setCreateTime(now);
			zoneDTO.setLevel(specification);
			zoneDTO.setModifyTime(now);
			zoneDTO.setSiteId(siteId);
			zoneDTO.setZoneName(site.getSiteName() + "-" + CommonConstants.levelSpecification.get(specification));
			this.courtFacade.saveZone(zoneDTO);
		}
	}

	@Override
	public Map<String, Object> courtStatus(Long courtId, Date time) throws ArgsException {
		CourtDTO courtDTO = this.courtFacade.getCourt(courtId);
		if (null == courtDTO) {
			throw new ArgsException(FailureCode.ERR_205);
		}
		SiteDTO siteDTO = new SiteDTO();
		siteDTO.setCourtId(courtId);
		List<SiteDTO> sites = this.courtFacade.getSiteList(siteDTO);
		for (SiteDTO site : sites) {
			ZoneDTO zoneDTO = new ZoneDTO();
			zoneDTO.setSiteId(site.getSiteId());
			List<ZoneDTO> zones = this.courtFacade.getZoneList(zoneDTO);
			site.setZones(zones);
		}
		Map<String, Object> temp = new HashMap<>();
		CglibBeanUtils.addToMap(courtDTO, temp);
		temp.remove("siteNum");
		temp.remove("areaName");
		temp.remove("userName");
		temp.put("sites", sites);
		ZoneUseDetailDTO zoneUseDetailDTO = new ZoneUseDetailDTO();
		zoneUseDetailDTO.setCourtId(courtId);
		zoneUseDetailDTO.setBeginDate(time);
		Calendar calendar = Calendar.getInstance();
		if (null != time) {
			calendar.setTime(time);
		}
		calendar.add(Calendar.MINUTE, 23 * 60 + 59);
		zoneUseDetailDTO.setEndDate(calendar.getTime());
		List<ZoneUseDetailDTO> useDetails = this.courtFacade.getZoneUseDetailList(zoneUseDetailDTO);
		Map<String, ZoneUseDetailDTO> uses = new HashMap<>();
		for (ZoneUseDetailDTO zoneUseDetailDTO2 : useDetails) {
			String key = zoneUseDetailDTO2.getZoneId() + "_" + ParamHandler.getDateString(zoneUseDetailDTO2.getBeginTime(), "yyyy-MM-dd HH:mm:ss");
			if (null != zoneUseDetailDTO2.getCompetitionId()) {
				CompetitionDTO competition = this.competitionFacade.getCompetition(zoneUseDetailDTO2.getCompetitionId());
				zoneUseDetailDTO2.setCompetition(competition);
			}
			uses.put(key, zoneUseDetailDTO2);
		}
		temp.put("useDetails", uses);
		return temp;
	}

	@Override
	public void zoneStatusChange(Long zoneId, Date time, Integer status) throws ArgsException {
		ZoneDTO zoneDTO = this.courtFacade.getZone(zoneId);
		if (null == zoneDTO) {
			throw new ArgsException(FailureCode.ERR_301);
		}
		SiteDTO siteDTO = this.courtFacade.getSite(zoneDTO.getSiteId());
		if (null == siteDTO) {
			throw new ArgsException(FailureCode.ERR_301);
		}
		
		ZoneUseDetailDTO zoneUseDetailDTO = new ZoneUseDetailDTO();
		zoneUseDetailDTO.setZoneId(zoneId);
		zoneUseDetailDTO.setBeginTime(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		calendar.add(Calendar.HOUR_OF_DAY, 2);
		zoneUseDetailDTO.setEndTime(calendar.getTime());
		zoneUseDetailDTO = this.courtFacade.getZoneUseDetail(zoneUseDetailDTO);
		if (null == zoneUseDetailDTO || null == zoneUseDetailDTO.getId()) {
			zoneUseDetailDTO = new ZoneUseDetailDTO();
			zoneUseDetailDTO.setCourtId(siteDTO.getCourtId());
			zoneUseDetailDTO.setSiteId(siteDTO.getSiteId());
			zoneUseDetailDTO.setZoneId(zoneId);
			zoneUseDetailDTO.setBeginTime(time);
			zoneUseDetailDTO.setEndTime(calendar.getTime());
			zoneUseDetailDTO.setBeginMinute(ParamHandler.getMinute(time));
			zoneUseDetailDTO.setEndMinute(ParamHandler.getMinute(calendar.getTime()));
			zoneUseDetailDTO.setStatus(status);
			zoneUseDetailDTO.setCreateTime(new Date());
			zoneUseDetailDTO.setModifyTime(zoneUseDetailDTO.getCreateTime());
			this.courtFacade.saveZoneUseDetail(zoneUseDetailDTO);
		}else{
			if (zoneUseDetailDTO.getStatus() == status.intValue()) {
				throw new ArgsException(FailureCode.ERR_303);
			}
			zoneUseDetailDTO.setStatus(status);
			this.courtFacade.updateZoneUseDetail(zoneUseDetailDTO);
		}
	}

}
