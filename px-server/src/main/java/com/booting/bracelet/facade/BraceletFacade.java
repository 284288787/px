/** create by auto at 2018-06-01 11:34:19**/
package com.booting.bracelet.facade;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.booting.bracelet.dto.BraceletDTO;
import com.booting.bracelet.dto.TotalData;
import com.booting.bracelet.entity.BraceletEntity;

public interface BraceletFacade extends Serializable {

	/**
	 * 新增 手环信息
	 */
	public Long saveBracelet(BraceletDTO braceletDTO);

	/**
	 * 批量新增 手环信息
	 */
	public void batchSaveBracelet(List<BraceletDTO> dtos);

	/**
	 * 更新 手环信息
	 */
	public int updateBracelet(BraceletDTO braceletDTO);

	/**
	 * 批量 手环信息
	 */
	public void batchUpdateBracelet(List<BraceletDTO> dtos);

	/**
	 * 删除 手环信息
	 */
	public int deleteBracelet(long braceletId);

	/**
	 * 根据主键获取 手环信息
	 */
	public BraceletDTO getBracelet(long braceletId);

	/**
	 * 根据条件获取一条 手环信息
	 */
	public BraceletDTO getBracelet(BraceletDTO braceletDTO);

	/**
	 * 查询满足条件的 手环信息 列表(单表)
	 */
	public List<BraceletDTO> getBraceletList(BraceletDTO braceletDTO);

	/**
	 * 查询满足条件的 手环信息 列表(分页)(单表)
	 */
	public PageList<BraceletDTO> getBraceletListForPage(BraceletDTO braceletDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 手环信息 列表(分页)(单表)
	 */
	public PageList<BraceletDTO> getBraceletListForPage(QueryParam queryParam);

	/**
	 * 手环信息DTO 转换成 Entity
	 */
	public BraceletEntity toBraceletEntity(BraceletDTO braceletDTO);

	/**
	 * 手环信息DTOs 转换成 Entities
	 */
	public List<BraceletEntity> toBraceletEntities(List<BraceletDTO> dtoes);

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

  public List<BraceletDTO> getBraceletList(BraceletDTO braceletDTO, Integer num);

  public PageList<TotalData> getStudentTotalData(QueryParam queryParam);

  public Map<String, Object> getExtremeValue(BraceletDTO bracelet, String field);

  public PageList<BraceletDTO> getBraceletListForPageGroupDate(QueryParam queryParam);

  public List<BraceletDTO> getDataByHour(BraceletDTO bracelet);

  public Integer getRankingOfStepNum(Long studentId);

}