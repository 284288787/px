/** create by auto at 2017-06-23 16:07:39**/
package com.booting.court.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.court.dto.CourtDTO;
import com.booting.court.dto.SiteDTO;
import com.booting.court.dto.ZoneDTO;
import com.booting.court.dto.ZoneUseDetailDTO;
import com.booting.court.entity.CourtEntity;
import com.booting.court.entity.SiteEntity;
import com.booting.court.entity.ZoneEntity;
import com.booting.court.entity.ZoneUseDetailEntity;
import com.booting.court.facade.CourtFacade;
import com.booting.court.service.CourtService;
import com.booting.court.service.SiteService;
import com.booting.court.service.ZoneService;
import com.booting.court.service.ZoneUseDetailService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.utils.CglibBeanUtils;

@Service("courtFacade")
public class CourtFacadeImpl implements CourtFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CourtService courtService;

	@Autowired
	private SiteService siteService;

	@Autowired
	private ZoneService zoneService;

	@Autowired
	private ZoneUseDetailService zoneUseDetailService;


	@Override
	public Long saveCourt(CourtDTO courtDTO){
		if (null == courtDTO) {
			return null;
		}
		CourtEntity entity = toCourtEntity(courtDTO);
		entity.setCreateTime(new Date());
		entity.setModifyTime(entity.getCreateTime());
		entity.setEnabled(1);
		courtDTO = courtService.save(entity);
		return courtDTO.getCourtId();
	}

	@Override
	public void batchSaveCourt(List<CourtDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<CourtEntity> entities = toCourtEntities(dtos);
		courtService.batchSave(entities);
	}

	@Override
	public int updateCourt(CourtDTO courtDTO){
		courtDTO = courtService.updateBySql(courtDTO);
		return 1;
	}

	@Override
	public void batchUpdateCourt(List<CourtDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		courtService.batchUpdate(dtos);
	}

	@Override
	public int deleteCourt(long courtId){
		return courtService.delete(courtId);
	}

	@Override
	public CourtDTO getCourt(long courtId){
		return courtService.get(courtId);
	}

	@Override
	public CourtDTO getCourt(CourtDTO courtDTO){
		return courtService.get(courtDTO);
	}

	@Override
	public List<CourtDTO> getCourtList(CourtDTO courtDTO){
		return courtService.getList(courtDTO);
	}

	@Override
	public PageList<CourtDTO> getCourtListForPage(CourtDTO courtDTO, int pageNumber, int pageSize){
		return courtService.getSimpleListForPage(courtDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<CourtDTO> getCourtListForPage(QueryParam queryParam){
		return courtService.getListForPage(queryParam, CourtDTO.class);
	}

	@Override
	public CourtEntity toCourtEntity(CourtDTO dto){
		CourtEntity entity = new CourtEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<CourtEntity> toCourtEntities(List<CourtDTO> dtos){
		List<CourtEntity> entities = new ArrayList<>();
		for(CourtDTO dto : dtos){
			entities.add(toCourtEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveSite(SiteDTO siteDTO){
		if (null == siteDTO) {
			return null;
		}
		SiteEntity entity = toSiteEntity(siteDTO);
		siteDTO = siteService.save(entity);
		return siteDTO.getSiteId();
	}

	@Override
	public void batchSaveSite(List<SiteDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<SiteEntity> entities = toSiteEntities(dtos);
		siteService.batchSave(entities);
	}

	@Override
	public int updateSite(SiteDTO siteDTO){
		siteDTO = siteService.updateBySql(siteDTO);
		return 1;
	}

	@Override
	public void batchUpdateSite(List<SiteDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		siteService.batchUpdate(dtos);
	}

	@Override
	public int deleteSite(long siteId){
		return siteService.delete(siteId);
	}

	@Override
	public SiteDTO getSite(long siteId){
		return siteService.get(siteId);
	}

	@Override
	public SiteDTO getSite(SiteDTO siteDTO){
		return siteService.get(siteDTO);
	}

	@Override
	public List<SiteDTO> getSiteList(SiteDTO siteDTO){
		return siteService.getSimpleList(siteDTO);
	}

	@Override
	public PageList<SiteDTO> getSiteListForPage(SiteDTO siteDTO, int pageNumber, int pageSize){
		return siteService.getSimpleListForPage(siteDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<SiteDTO> getSiteListForPage(QueryParam queryParam){
		return siteService.getSimpleListForPage(queryParam);
	}

	@Override
	public SiteEntity toSiteEntity(SiteDTO dto){
		SiteEntity entity = new SiteEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<SiteEntity> toSiteEntities(List<SiteDTO> dtos){
		List<SiteEntity> entities = new ArrayList<>();
		for(SiteDTO dto : dtos){
			entities.add(toSiteEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveZone(ZoneDTO zoneDTO){
		if (null == zoneDTO) {
			return null;
		}
		ZoneEntity entity = toZoneEntity(zoneDTO);
		zoneDTO = zoneService.save(entity);
		return zoneDTO.getZoneId();
	}

	@Override
	public void batchSaveZone(List<ZoneDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<ZoneEntity> entities = toZoneEntities(dtos);
		zoneService.batchSave(entities);
	}

	@Override
	public int updateZone(ZoneDTO zoneDTO){
		zoneDTO = zoneService.updateBySql(zoneDTO);
		return 1;
	}

	@Override
	public void batchUpdateZone(List<ZoneDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		zoneService.batchUpdate(dtos);
	}

	@Override
	public int deleteZone(long zoneId){
		return zoneService.delete(zoneId);
	}

	@Override
	public ZoneDTO getZone(long zoneId){
		return zoneService.get(zoneId);
	}

	@Override
	public ZoneDTO getZone(ZoneDTO zoneDTO){
		return zoneService.get(zoneDTO);
	}

	@Override
	public List<ZoneDTO> getZoneList(ZoneDTO zoneDTO){
		return zoneService.getSimpleList(zoneDTO);
	}

	@Override
	public PageList<ZoneDTO> getZoneListForPage(ZoneDTO zoneDTO, int pageNumber, int pageSize){
		return zoneService.getSimpleListForPage(zoneDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<ZoneDTO> getZoneListForPage(QueryParam queryParam){
		return zoneService.getSimpleListForPage(queryParam);
	}

	@Override
	public ZoneEntity toZoneEntity(ZoneDTO dto){
		ZoneEntity entity = new ZoneEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<ZoneEntity> toZoneEntities(List<ZoneDTO> dtos){
		List<ZoneEntity> entities = new ArrayList<>();
		for(ZoneDTO dto : dtos){
			entities.add(toZoneEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveZoneUseDetail(ZoneUseDetailDTO zoneUseDetailDTO){
		if (null == zoneUseDetailDTO) {
			return null;
		}
		ZoneUseDetailEntity entity = toZoneUseDetailEntity(zoneUseDetailDTO);
		zoneUseDetailDTO = zoneUseDetailService.save(entity);
		return zoneUseDetailDTO.getId();
	}

	@Override
	public void batchSaveZoneUseDetail(List<ZoneUseDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<ZoneUseDetailEntity> entities = toZoneUseDetailEntities(dtos);
		zoneUseDetailService.batchSave(entities);
	}

	@Override
	public int updateZoneUseDetail(ZoneUseDetailDTO zoneUseDetailDTO){
		zoneUseDetailDTO = zoneUseDetailService.updateBySql(zoneUseDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateZoneUseDetail(List<ZoneUseDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		zoneUseDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteZoneUseDetail(long id){
		return zoneUseDetailService.delete(id);
	}

	@Override
	public ZoneUseDetailDTO getZoneUseDetail(long id){
		return zoneUseDetailService.get(id);
	}

	@Override
	public ZoneUseDetailDTO getZoneUseDetail(ZoneUseDetailDTO zoneUseDetailDTO){
		return zoneUseDetailService.get(zoneUseDetailDTO);
	}

	@Override
	public List<ZoneUseDetailDTO> getZoneUseDetailList(ZoneUseDetailDTO zoneUseDetailDTO){
		return zoneUseDetailService.getSimpleList(zoneUseDetailDTO);
	}

	@Override
	public PageList<ZoneUseDetailDTO> getZoneUseDetailListForPage(ZoneUseDetailDTO zoneUseDetailDTO, int pageNumber, int pageSize){
		return zoneUseDetailService.getSimpleListForPage(zoneUseDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<ZoneUseDetailDTO> getZoneUseDetailListForPage(QueryParam queryParam){
		return zoneUseDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public ZoneUseDetailEntity toZoneUseDetailEntity(ZoneUseDetailDTO dto){
		ZoneUseDetailEntity entity = new ZoneUseDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<ZoneUseDetailEntity> toZoneUseDetailEntities(List<ZoneUseDetailDTO> dtos){
		List<ZoneUseDetailEntity> entities = new ArrayList<>();
		for(ZoneUseDetailDTO dto : dtos){
			entities.add(toZoneUseDetailEntity(dto));
		}
		return entities;
	}

	@Override
	public <T> List<T> getList(T dto){
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize){
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz){
		return null;
	}

	@Override
	public void enabledCourt(String ids) throws ArgsException {
		if (StringUtils.isBlank(ids)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		CourtDTO teamDTO = new CourtDTO();
		teamDTO.setEnabled(1);
		teamDTO.setCourtIds(ids);
		this.courtService.updateBySql(teamDTO);
	}

	@Override
	public void disabledCourt(String ids) throws ArgsException {
		if (StringUtils.isBlank(ids)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		CourtDTO teamDTO = new CourtDTO();
		teamDTO.setEnabled(0);
		teamDTO.setCourtIds(ids);
		this.courtService.updateBySql(teamDTO);
	}

	@Override
	public void enabledSite(String ids) throws ArgsException {
		if (StringUtils.isBlank(ids)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		SiteDTO siteDTO = new SiteDTO();
		siteDTO.setEnabled(1);
		siteDTO.setSiteIds(ids);
		this.siteService.updateBySql(siteDTO);
	}

	@Override
	public void disabledSite(String ids) throws ArgsException {
		if (StringUtils.isBlank(ids)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		SiteDTO siteDTO = new SiteDTO();
		siteDTO.setEnabled(0);
		siteDTO.setSiteIds(ids);
		this.siteService.updateBySql(siteDTO);
	}

	@Override
	public PageList<CourtDTO> usableCourts(QueryParam queryParam) {
		return courtService.usableCourts(queryParam);
	}

	@Override
	public ZoneDTO getUsableZone(Map<String, Object> params) {
		return zoneService.getUsableZone(params);
	}

}