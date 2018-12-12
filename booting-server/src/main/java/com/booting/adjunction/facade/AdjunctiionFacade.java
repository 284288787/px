/** create by auto at 2017-10-17 10:14:20**/
package com.booting.adjunction.facade;

import java.io.Serializable;
import java.util.List;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.booting.adjunction.dto.PopularizeDTO;
import com.booting.adjunction.entity.PopularizeEntity;

public interface AdjunctiionFacade extends Serializable {

	/**
	 * 新增 推广信息
	 */
	public Long savePopularize(PopularizeDTO popularizeDTO);

	/**
	 * 批量新增 推广信息
	 */
	public void batchSavePopularize(List<PopularizeDTO> dtos);

	/**
	 * 更新 推广信息
	 */
	public int updatePopularize(PopularizeDTO popularizeDTO);

	/**
	 * 批量 推广信息
	 */
	public void batchUpdatePopularize(List<PopularizeDTO> dtos);

	/**
	 * 删除 推广信息
	 */
	public int deletePopularize(long id);

	/**
	 * 根据主键获取 推广信息
	 */
	public PopularizeDTO getPopularize(long id);

	/**
	 * 根据条件获取一条 推广信息
	 */
	public PopularizeDTO getPopularize(PopularizeDTO popularizeDTO);

	/**
	 * 查询满足条件的 推广信息 列表(单表)
	 */
	public List<PopularizeDTO> getPopularizeList(PopularizeDTO popularizeDTO);

	/**
	 * 查询满足条件的 推广信息 列表(分页)(单表)
	 */
	public PageList<PopularizeDTO> getPopularizeListForPage(PopularizeDTO popularizeDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 推广信息 列表(分页)(单表)
	 */
	public PageList<PopularizeDTO> getPopularizeListForPage(QueryParam queryParam);

	/**
	 * 推广信息DTO 转换成 Entity
	 */
	public PopularizeEntity toPopularizeEntity(PopularizeDTO popularizeDTO);

	/**
	 * 推广信息DTOs 转换成 Entities
	 */
	public List<PopularizeEntity> toPopularizeEntities(List<PopularizeDTO> dtoes);

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

}