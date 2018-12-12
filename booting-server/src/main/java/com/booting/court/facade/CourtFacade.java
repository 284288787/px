/** create by auto at 2017-06-23 16:07:39**/
package com.booting.court.facade;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.exception.ArgsException;
import com.booting.court.dto.CourtDTO;
import com.booting.court.entity.CourtEntity;
import com.booting.court.dto.SiteDTO;
import com.booting.court.entity.SiteEntity;
import com.booting.court.dto.ZoneDTO;
import com.booting.court.entity.ZoneEntity;
import com.booting.court.dto.ZoneUseDetailDTO;
import com.booting.court.entity.ZoneUseDetailEntity;

public interface CourtFacade extends Serializable {

	/**
	 * 新增 球场
	 */
	public Long saveCourt(CourtDTO courtDTO) throws ArgsException;

	/**
	 * 批量新增 球场
	 */
	public void batchSaveCourt(List<CourtDTO> dtos);

	/**
	 * 更新 球场
	 */
	public int updateCourt(CourtDTO courtDTO);

	/**
	 * 批量 球场
	 */
	public void batchUpdateCourt(List<CourtDTO> dtos);

	/**
	 * 删除 球场
	 */
	public int deleteCourt(long courtId);

	/**
	 * 根据主键获取 球场
	 */
	public CourtDTO getCourt(long courtId);

	/**
	 * 根据条件获取一条 球场
	 */
	public CourtDTO getCourt(CourtDTO courtDTO);

	/**
	 * 查询满足条件的 球场 列表(单表)
	 */
	public List<CourtDTO> getCourtList(CourtDTO courtDTO);

	/**
	 * 查询满足条件的 球场 列表(分页)(单表)
	 */
	public PageList<CourtDTO> getCourtListForPage(CourtDTO courtDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 球场 列表(分页)(单表)
	 */
	public PageList<CourtDTO> getCourtListForPage(QueryParam queryParam);

	/**
	 * 球场DTO 转换成 Entity
	 */
	public CourtEntity toCourtEntity(CourtDTO courtDTO);

	/**
	 * 球场DTOs 转换成 Entities
	 */
	public List<CourtEntity> toCourtEntities(List<CourtDTO> dtoes);

	/**
	 * 新增 场地
	 */
	public Long saveSite(SiteDTO siteDTO) throws ArgsException;

	/**
	 * 批量新增 场地
	 */
	public void batchSaveSite(List<SiteDTO> dtos);

	/**
	 * 更新 场地
	 */
	public int updateSite(SiteDTO siteDTO);

	/**
	 * 批量 场地
	 */
	public void batchUpdateSite(List<SiteDTO> dtos);

	/**
	 * 删除 场地
	 */
	public int deleteSite(long siteId);

	/**
	 * 根据主键获取 场地
	 */
	public SiteDTO getSite(long siteId);

	/**
	 * 根据条件获取一条 场地
	 */
	public SiteDTO getSite(SiteDTO siteDTO);

	/**
	 * 查询满足条件的 场地 列表(单表)
	 */
	public List<SiteDTO> getSiteList(SiteDTO siteDTO);

	/**
	 * 查询满足条件的 场地 列表(分页)(单表)
	 */
	public PageList<SiteDTO> getSiteListForPage(SiteDTO siteDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 场地 列表(分页)(单表)
	 */
	public PageList<SiteDTO> getSiteListForPage(QueryParam queryParam);

	/**
	 * 场地DTO 转换成 Entity
	 */
	public SiteEntity toSiteEntity(SiteDTO siteDTO);

	/**
	 * 场地DTOs 转换成 Entities
	 */
	public List<SiteEntity> toSiteEntities(List<SiteDTO> dtoes);

	/**
	 * 新增 区块
	 */
	public Long saveZone(ZoneDTO zoneDTO);

	/**
	 * 批量新增 区块
	 */
	public void batchSaveZone(List<ZoneDTO> dtos);

	/**
	 * 更新 区块
	 */
	public int updateZone(ZoneDTO zoneDTO);

	/**
	 * 批量 区块
	 */
	public void batchUpdateZone(List<ZoneDTO> dtos);

	/**
	 * 删除 区块
	 */
	public int deleteZone(long zoneId);

	/**
	 * 根据主键获取 区块
	 */
	public ZoneDTO getZone(long zoneId);

	/**
	 * 根据条件获取一条 区块
	 */
	public ZoneDTO getZone(ZoneDTO zoneDTO);

	/**
	 * 查询满足条件的 区块 列表(单表)
	 */
	public List<ZoneDTO> getZoneList(ZoneDTO zoneDTO);

	/**
	 * 查询满足条件的 区块 列表(分页)(单表)
	 */
	public PageList<ZoneDTO> getZoneListForPage(ZoneDTO zoneDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 区块 列表(分页)(单表)
	 */
	public PageList<ZoneDTO> getZoneListForPage(QueryParam queryParam);

	/**
	 * 区块DTO 转换成 Entity
	 */
	public ZoneEntity toZoneEntity(ZoneDTO zoneDTO);

	/**
	 * 区块DTOs 转换成 Entities
	 */
	public List<ZoneEntity> toZoneEntities(List<ZoneDTO> dtoes);

	/**
	 * 新增 区块使用明细
	 */
	public Long saveZoneUseDetail(ZoneUseDetailDTO zoneUseDetailDTO);

	/**
	 * 批量新增 区块使用明细
	 */
	public void batchSaveZoneUseDetail(List<ZoneUseDetailDTO> dtos);

	/**
	 * 更新 区块使用明细
	 */
	public int updateZoneUseDetail(ZoneUseDetailDTO zoneUseDetailDTO);

	/**
	 * 批量 区块使用明细
	 */
	public void batchUpdateZoneUseDetail(List<ZoneUseDetailDTO> dtos);

	/**
	 * 删除 区块使用明细
	 */
	public int deleteZoneUseDetail(long id);

	/**
	 * 根据主键获取 区块使用明细
	 */
	public ZoneUseDetailDTO getZoneUseDetail(long id);

	/**
	 * 根据条件获取一条 区块使用明细
	 */
	public ZoneUseDetailDTO getZoneUseDetail(ZoneUseDetailDTO zoneUseDetailDTO);

	/**
	 * 查询满足条件的 区块使用明细 列表(单表)
	 */
	public List<ZoneUseDetailDTO> getZoneUseDetailList(ZoneUseDetailDTO zoneUseDetailDTO);

	/**
	 * 查询满足条件的 区块使用明细 列表(分页)(单表)
	 */
	public PageList<ZoneUseDetailDTO> getZoneUseDetailListForPage(ZoneUseDetailDTO zoneUseDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 区块使用明细 列表(分页)(单表)
	 */
	public PageList<ZoneUseDetailDTO> getZoneUseDetailListForPage(QueryParam queryParam);

	/**
	 * 区块使用明细DTO 转换成 Entity
	 */
	public ZoneUseDetailEntity toZoneUseDetailEntity(ZoneUseDetailDTO zoneUseDetailDTO);

	/**
	 * 区块使用明细DTOs 转换成 Entities
	 */
	public List<ZoneUseDetailEntity> toZoneUseDetailEntities(List<ZoneUseDetailDTO> dtoes);

	/**
	 * 查询满足条件的 列表(多表)
	 */
	public <T> List<T> getList(T dto);

	/**
	 * 查询满足条件的列表(分页)(多表)
	 */
	public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的列表(分页)(多表)
	 */
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz);

	public void enabledCourt(String ids) throws ArgsException;

	public void disabledCourt(String ids) throws ArgsException;

	public void enabledSite(String ids) throws ArgsException;

	public void disabledSite(String ids) throws ArgsException;

	public PageList<CourtDTO> usableCourts(QueryParam queryParam);

	public ZoneDTO getUsableZone(Map<String, Object> params);

}