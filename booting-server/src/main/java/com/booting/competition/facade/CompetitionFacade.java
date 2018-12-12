/** create by auto at 2017-07-15 16:10:54**/
package com.booting.competition.facade;

import java.io.Serializable;
import java.util.List;

import com.booting.competition.dto.CompetitionDTO;
import com.booting.competition.dto.CompetitionInsuranceDTO;
import com.booting.competition.dto.CompetitionScoreDTO;
import com.booting.competition.dto.InsuranceOrderDTO;
import com.booting.competition.dto.InsuranceOrderDetailDTO;
import com.booting.competition.entity.CompetitionEntity;
import com.booting.competition.entity.CompetitionInsuranceEntity;
import com.booting.competition.entity.CompetitionScoreEntity;
import com.booting.competition.entity.InsuranceOrderDetailEntity;
import com.booting.competition.entity.InsuranceOrderEntity;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

public interface CompetitionFacade extends Serializable {

	/**
	 * 新增 赛事信息
	 */
	public Long saveCompetition(CompetitionDTO competitionDTO);

	/**
	 * 批量新增 赛事信息
	 */
	public void batchSaveCompetition(List<CompetitionDTO> dtos);

	/**
	 * 更新 赛事信息
	 */
	public int updateCompetition(CompetitionDTO competitionDTO);

	/**
	 * 批量 赛事信息
	 */
	public void batchUpdateCompetition(List<CompetitionDTO> dtos);

	/**
	 * 删除 赛事信息
	 */
	public int deleteCompetition(long competitionId);

	/**
	 * 根据主键获取 赛事信息
	 */
	public CompetitionDTO getCompetition(long competitionId);

	/**
	 * 根据条件获取一条 赛事信息
	 */
	public CompetitionDTO getCompetition(CompetitionDTO competitionDTO);

	/**
	 * 查询满足条件的 赛事信息 列表(单表)
	 */
	public List<CompetitionDTO> getCompetitionList(CompetitionDTO competitionDTO);

	/**
	 * 查询满足条件的 赛事信息 列表(分页)(单表)
	 */
	public PageList<CompetitionDTO> getCompetitionListForPage(CompetitionDTO competitionDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 赛事信息 列表(分页)(单表)
	 */
	public PageList<CompetitionDTO> getCompetitionListForPage(QueryParam queryParam);

	/**
	 * 赛事信息DTO 转换成 Entity
	 */
	public CompetitionEntity toCompetitionEntity(CompetitionDTO competitionDTO);

	/**
	 * 赛事信息DTOs 转换成 Entities
	 */
	public List<CompetitionEntity> toCompetitionEntities(List<CompetitionDTO> dtoes);

	/**
	 * 新增 赛事保险
	 */
	public Long saveCompetitionInsurance(CompetitionInsuranceDTO competitionInsuranceDTO);

	/**
	 * 批量新增 赛事保险
	 */
	public void batchSaveCompetitionInsurance(List<CompetitionInsuranceDTO> dtos);

	/**
	 * 更新 赛事保险
	 */
	public int updateCompetitionInsurance(CompetitionInsuranceDTO competitionInsuranceDTO);

	/**
	 * 批量 赛事保险
	 */
	public void batchUpdateCompetitionInsurance(List<CompetitionInsuranceDTO> dtos);

	/**
	 * 删除 赛事保险
	 */
	public int deleteCompetitionInsurance(long id);

	/**
	 * 根据主键获取 赛事保险
	 */
	public CompetitionInsuranceDTO getCompetitionInsurance(long id);

	/**
	 * 根据条件获取一条 赛事保险
	 */
	public CompetitionInsuranceDTO getCompetitionInsurance(CompetitionInsuranceDTO competitionInsuranceDTO);

	/**
	 * 查询满足条件的 赛事保险 列表(单表)
	 */
	public List<CompetitionInsuranceDTO> getCompetitionInsuranceList(CompetitionInsuranceDTO competitionInsuranceDTO);

	/**
	 * 查询满足条件的 赛事保险 列表(分页)(单表)
	 */
	public PageList<CompetitionInsuranceDTO> getCompetitionInsuranceListForPage(CompetitionInsuranceDTO competitionInsuranceDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 赛事保险 列表(分页)(单表)
	 */
	public PageList<CompetitionInsuranceDTO> getCompetitionInsuranceListForPage(QueryParam queryParam);

	/**
	 * 赛事保险DTO 转换成 Entity
	 */
	public CompetitionInsuranceEntity toCompetitionInsuranceEntity(CompetitionInsuranceDTO competitionInsuranceDTO);

	/**
	 * 赛事保险DTOs 转换成 Entities
	 */
	public List<CompetitionInsuranceEntity> toCompetitionInsuranceEntities(List<CompetitionInsuranceDTO> dtoes);

	/**
	 * 新增 赛事成绩
	 */
	public Long saveCompetitionScore(CompetitionScoreDTO competitionScoreDTO);

	/**
	 * 批量新增 赛事成绩
	 */
	public void batchSaveCompetitionScore(List<CompetitionScoreDTO> dtos);

	/**
	 * 更新 赛事成绩
	 */
	public int updateCompetitionScore(CompetitionScoreDTO competitionScoreDTO);

	/**
	 * 批量 赛事成绩
	 */
	public void batchUpdateCompetitionScore(List<CompetitionScoreDTO> dtos);

	/**
	 * 删除 赛事成绩
	 */
	public int deleteCompetitionScore(long id);

	/**
	 * 根据主键获取 赛事成绩
	 */
	public CompetitionScoreDTO getCompetitionScore(long id);

	/**
	 * 根据条件获取一条 赛事成绩
	 */
	public CompetitionScoreDTO getCompetitionScore(CompetitionScoreDTO competitionScoreDTO);

	/**
	 * 查询满足条件的 赛事成绩 列表(单表)
	 */
	public List<CompetitionScoreDTO> getCompetitionScoreList(CompetitionScoreDTO competitionScoreDTO);

	/**
	 * 查询满足条件的 赛事成绩 列表(分页)(单表)
	 */
	public PageList<CompetitionScoreDTO> getCompetitionScoreListForPage(CompetitionScoreDTO competitionScoreDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 赛事成绩 列表(分页)(单表)
	 */
	public PageList<CompetitionScoreDTO> getCompetitionScoreListForPage(QueryParam queryParam);

	/**
	 * 赛事成绩DTO 转换成 Entity
	 */
	public CompetitionScoreEntity toCompetitionScoreEntity(CompetitionScoreDTO competitionScoreDTO);

	/**
	 * 赛事成绩DTOs 转换成 Entities
	 */
	public List<CompetitionScoreEntity> toCompetitionScoreEntities(List<CompetitionScoreDTO> dtoes);

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

	public CompetitionDTO getNewCompetition();

	/**
	 * 新增 保险订单详情
	 */
	public Long saveInsuranceOrderDetail(InsuranceOrderDetailDTO insuranceOrderDetailDTO);

	/**
	 * 批量新增 保险订单详情
	 */
	public void batchSaveInsuranceOrderDetail(List<InsuranceOrderDetailDTO> dtos);

	/**
	 * 更新 保险订单详情
	 */
	public int updateInsuranceOrderDetail(InsuranceOrderDetailDTO insuranceOrderDetailDTO);

	/**
	 * 批量 保险订单详情
	 */
	public void batchUpdateInsuranceOrderDetail(List<InsuranceOrderDetailDTO> dtos);

	/**
	 * 删除 保险订单详情
	 */
	public int deleteInsuranceOrderDetail(long id);

	/**
	 * 根据主键获取 保险订单详情
	 */
	public InsuranceOrderDetailDTO getInsuranceOrderDetail(long id);

	/**
	 * 根据条件获取一条 保险订单详情
	 */
	public InsuranceOrderDetailDTO getInsuranceOrderDetail(InsuranceOrderDetailDTO insuranceOrderDetailDTO);

	/**
	 * 查询满足条件的 保险订单详情 列表(单表)
	 */
	public List<InsuranceOrderDetailDTO> getInsuranceOrderDetailList(InsuranceOrderDetailDTO insuranceOrderDetailDTO);

	/**
	 * 查询满足条件的 保险订单详情 列表(分页)(单表)
	 */
	public PageList<InsuranceOrderDetailDTO> getInsuranceOrderDetailListForPage(InsuranceOrderDetailDTO insuranceOrderDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 保险订单详情 列表(分页)(单表)
	 */
	public PageList<InsuranceOrderDetailDTO> getInsuranceOrderDetailListForPage(QueryParam queryParam);

	/**
	 * 保险订单详情DTO 转换成 Entity
	 */
	public InsuranceOrderDetailEntity toInsuranceOrderDetailEntity(InsuranceOrderDetailDTO insuranceOrderDetailDTO);

	/**
	 * 保险订单详情DTOs 转换成 Entities
	 */
	public List<InsuranceOrderDetailEntity> toInsuranceOrderDetailEntities(List<InsuranceOrderDetailDTO> dtoes);

	/**
	 * 新增 保险订单
	 */
	public Long saveInsuranceOrder(InsuranceOrderDTO insuranceOrderDTO);

	/**
	 * 批量新增 保险订单
	 */
	public void batchSaveInsuranceOrder(List<InsuranceOrderDTO> dtos);

	/**
	 * 更新 保险订单
	 */
	public int updateInsuranceOrder(InsuranceOrderDTO insuranceOrderDTO);

	/**
	 * 批量 保险订单
	 */
	public void batchUpdateInsuranceOrder(List<InsuranceOrderDTO> dtos);

	/**
	 * 删除 保险订单
	 */
	public int deleteInsuranceOrder(long ioId);

	/**
	 * 根据主键获取 保险订单
	 */
	public InsuranceOrderDTO getInsuranceOrder(long ioId);

	/**
	 * 根据条件获取一条 保险订单
	 */
	public InsuranceOrderDTO getInsuranceOrder(InsuranceOrderDTO insuranceOrderDTO);

	/**
	 * 查询满足条件的 保险订单 列表(单表)
	 */
	public List<InsuranceOrderDTO> getInsuranceOrderList(InsuranceOrderDTO insuranceOrderDTO);

	/**
	 * 查询满足条件的 保险订单 列表(分页)(单表)
	 */
	public PageList<InsuranceOrderDTO> getInsuranceOrderListForPage(InsuranceOrderDTO insuranceOrderDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 保险订单 列表(分页)(单表)
	 */
	public PageList<InsuranceOrderDTO> getInsuranceOrderListForPage(QueryParam queryParam);

	/**
	 * 保险订单DTO 转换成 Entity
	 */
	public InsuranceOrderEntity toInsuranceOrderEntity(InsuranceOrderDTO insuranceOrderDTO);

	/**
	 * 保险订单DTOs 转换成 Entities
	 */
	public List<InsuranceOrderEntity> toInsuranceOrderEntities(List<InsuranceOrderDTO> dtoes);

	public void deleteCompetitionScoreByCompetitionId(Long competitionId);

	public void deleteCompetitionScoreByTeamId(Long teamId);

	public Integer getScore(Long competitionId, Long teamId);

	
}