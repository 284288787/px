/** create by auto at 2017-07-15 16:10:54**/
package com.booting.competition.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.competition.facade.CompetitionFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;

import com.booting.competition.dto.CompetitionDTO;
import com.booting.competition.entity.CompetitionEntity;
import com.booting.competition.service.CompetitionService;
import com.booting.competition.service.InsuranceOrderDetailService;
import com.booting.competition.service.InsuranceOrderService;
import com.booting.competition.dto.CompetitionInsuranceDTO;
import com.booting.competition.entity.CompetitionInsuranceEntity;
import com.booting.competition.service.CompetitionInsuranceService;
import com.booting.competition.dto.CompetitionScoreDTO;
import com.booting.competition.dto.InsuranceOrderDTO;
import com.booting.competition.dto.InsuranceOrderDetailDTO;
import com.booting.competition.entity.CompetitionScoreEntity;
import com.booting.competition.entity.InsuranceOrderDetailEntity;
import com.booting.competition.entity.InsuranceOrderEntity;
import com.booting.competition.service.CompetitionScoreService;

@Service("competitionFacade")
public class CompetitionFacadeImpl implements CompetitionFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CompetitionService competitionService;

	@Autowired
	private CompetitionInsuranceService competitionInsuranceService;

	@Autowired
	private CompetitionScoreService competitionScoreService;

	@Autowired
	private InsuranceOrderDetailService insuranceOrderDetailService;

	@Autowired
	private InsuranceOrderService insuranceOrderService;
	
	@Override
	public Long saveInsuranceOrderDetail(InsuranceOrderDetailDTO insuranceOrderDetailDTO){
		if (null == insuranceOrderDetailDTO) {
			return null;
		}
		InsuranceOrderDetailEntity entity = toInsuranceOrderDetailEntity(insuranceOrderDetailDTO);
		insuranceOrderDetailDTO = insuranceOrderDetailService.save(entity);
		return insuranceOrderDetailDTO.getId();
	}

	@Override
	public void batchSaveInsuranceOrderDetail(List<InsuranceOrderDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<InsuranceOrderDetailEntity> entities = toInsuranceOrderDetailEntities(dtos);
		insuranceOrderDetailService.batchSave(entities);
	}

	@Override
	public int updateInsuranceOrderDetail(InsuranceOrderDetailDTO insuranceOrderDetailDTO){
		insuranceOrderDetailDTO = insuranceOrderDetailService.updateBySql(insuranceOrderDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateInsuranceOrderDetail(List<InsuranceOrderDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		insuranceOrderDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteInsuranceOrderDetail(long id){
		return insuranceOrderDetailService.delete(id);
	}

	@Override
	public InsuranceOrderDetailDTO getInsuranceOrderDetail(long id){
		return insuranceOrderDetailService.get(id);
	}

	@Override
	public InsuranceOrderDetailDTO getInsuranceOrderDetail(InsuranceOrderDetailDTO insuranceOrderDetailDTO){
		return insuranceOrderDetailService.get(insuranceOrderDetailDTO);
	}

	@Override
	public List<InsuranceOrderDetailDTO> getInsuranceOrderDetailList(InsuranceOrderDetailDTO insuranceOrderDetailDTO){
		return insuranceOrderDetailService.getSimpleList(insuranceOrderDetailDTO);
	}

	@Override
	public PageList<InsuranceOrderDetailDTO> getInsuranceOrderDetailListForPage(InsuranceOrderDetailDTO insuranceOrderDetailDTO, int pageNumber, int pageSize){
		return insuranceOrderDetailService.getSimpleListForPage(insuranceOrderDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<InsuranceOrderDetailDTO> getInsuranceOrderDetailListForPage(QueryParam queryParam){
		return insuranceOrderDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public InsuranceOrderDetailEntity toInsuranceOrderDetailEntity(InsuranceOrderDetailDTO dto){
		InsuranceOrderDetailEntity entity = new InsuranceOrderDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<InsuranceOrderDetailEntity> toInsuranceOrderDetailEntities(List<InsuranceOrderDetailDTO> dtos){
		List<InsuranceOrderDetailEntity> entities = new ArrayList<>();
		for(InsuranceOrderDetailDTO dto : dtos){
			entities.add(toInsuranceOrderDetailEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveInsuranceOrder(InsuranceOrderDTO insuranceOrderDTO){
		if (null == insuranceOrderDTO) {
			return null;
		}
		InsuranceOrderEntity entity = toInsuranceOrderEntity(insuranceOrderDTO);
		insuranceOrderDTO = insuranceOrderService.save(entity);
		return insuranceOrderDTO.getIoId();
	}

	@Override
	public void batchSaveInsuranceOrder(List<InsuranceOrderDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<InsuranceOrderEntity> entities = toInsuranceOrderEntities(dtos);
		insuranceOrderService.batchSave(entities);
	}

	@Override
	public int updateInsuranceOrder(InsuranceOrderDTO insuranceOrderDTO){
		insuranceOrderDTO = insuranceOrderService.updateBySql(insuranceOrderDTO);
		return 1;
	}

	@Override
	public void batchUpdateInsuranceOrder(List<InsuranceOrderDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		insuranceOrderService.batchUpdate(dtos);
	}

	@Override
	public int deleteInsuranceOrder(long ioId){
		return insuranceOrderService.delete(ioId);
	}

	@Override
	public InsuranceOrderDTO getInsuranceOrder(long ioId){
		return insuranceOrderService.get(ioId);
	}

	@Override
	public InsuranceOrderDTO getInsuranceOrder(InsuranceOrderDTO insuranceOrderDTO){
		return insuranceOrderService.get(insuranceOrderDTO);
	}

	@Override
	public List<InsuranceOrderDTO> getInsuranceOrderList(InsuranceOrderDTO insuranceOrderDTO){
		return insuranceOrderService.getSimpleList(insuranceOrderDTO);
	}

	@Override
	public PageList<InsuranceOrderDTO> getInsuranceOrderListForPage(InsuranceOrderDTO insuranceOrderDTO, int pageNumber, int pageSize){
		return insuranceOrderService.getSimpleListForPage(insuranceOrderDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<InsuranceOrderDTO> getInsuranceOrderListForPage(QueryParam queryParam){
		return insuranceOrderService.getSimpleListForPage(queryParam);
	}

	@Override
	public InsuranceOrderEntity toInsuranceOrderEntity(InsuranceOrderDTO dto){
		InsuranceOrderEntity entity = new InsuranceOrderEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<InsuranceOrderEntity> toInsuranceOrderEntities(List<InsuranceOrderDTO> dtos){
		List<InsuranceOrderEntity> entities = new ArrayList<>();
		for(InsuranceOrderDTO dto : dtos){
			entities.add(toInsuranceOrderEntity(dto));
		}
		return entities;
	}
	
	@Override
	public Long saveCompetition(CompetitionDTO competitionDTO){
		if (null == competitionDTO) {
			return null;
		}
		CompetitionEntity entity = toCompetitionEntity(competitionDTO);
		competitionDTO = competitionService.save(entity);
		return competitionDTO.getCompetitionId();
	}

	@Override
	public void batchSaveCompetition(List<CompetitionDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<CompetitionEntity> entities = toCompetitionEntities(dtos);
		competitionService.batchSave(entities);
	}

	@Override
	public int updateCompetition(CompetitionDTO competitionDTO){
		competitionDTO = competitionService.updateBySql(competitionDTO);
		return 1;
	}

	@Override
	public void batchUpdateCompetition(List<CompetitionDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		competitionService.batchUpdate(dtos);
	}

	@Override
	public int deleteCompetition(long competitionId){
		return competitionService.delete(competitionId);
	}

	@Override
	public CompetitionDTO getCompetition(long competitionId){
		return competitionService.get(competitionId);
	}

	@Override
	public CompetitionDTO getCompetition(CompetitionDTO competitionDTO){
		return competitionService.get(competitionDTO);
	}

	@Override
	public List<CompetitionDTO> getCompetitionList(CompetitionDTO competitionDTO){
		return competitionService.getSimpleList(competitionDTO);
	}

	@Override
	public PageList<CompetitionDTO> getCompetitionListForPage(CompetitionDTO competitionDTO, int pageNumber, int pageSize){
		return competitionService.getSimpleListForPage(competitionDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<CompetitionDTO> getCompetitionListForPage(QueryParam queryParam){
		return competitionService.getSimpleListForPage(queryParam);
	}

	@Override
	public CompetitionEntity toCompetitionEntity(CompetitionDTO dto){
		CompetitionEntity entity = new CompetitionEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<CompetitionEntity> toCompetitionEntities(List<CompetitionDTO> dtos){
		List<CompetitionEntity> entities = new ArrayList<>();
		for(CompetitionDTO dto : dtos){
			entities.add(toCompetitionEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveCompetitionInsurance(CompetitionInsuranceDTO competitionInsuranceDTO){
		if (null == competitionInsuranceDTO) {
			return null;
		}
		CompetitionInsuranceEntity entity = toCompetitionInsuranceEntity(competitionInsuranceDTO);
		competitionInsuranceDTO = competitionInsuranceService.save(entity);
		return competitionInsuranceDTO.getId();
	}

	@Override
	public void batchSaveCompetitionInsurance(List<CompetitionInsuranceDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<CompetitionInsuranceEntity> entities = toCompetitionInsuranceEntities(dtos);
		competitionInsuranceService.batchSave(entities);
	}

	@Override
	public int updateCompetitionInsurance(CompetitionInsuranceDTO competitionInsuranceDTO){
		competitionInsuranceDTO = competitionInsuranceService.updateBySql(competitionInsuranceDTO);
		return 1;
	}

	@Override
	public void batchUpdateCompetitionInsurance(List<CompetitionInsuranceDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		competitionInsuranceService.batchUpdate(dtos);
	}

	@Override
	public int deleteCompetitionInsurance(long id){
		return competitionInsuranceService.delete(id);
	}

	@Override
	public CompetitionInsuranceDTO getCompetitionInsurance(long id){
		return competitionInsuranceService.get(id);
	}

	@Override
	public CompetitionInsuranceDTO getCompetitionInsurance(CompetitionInsuranceDTO competitionInsuranceDTO){
		return competitionInsuranceService.get(competitionInsuranceDTO);
	}

	@Override
	public List<CompetitionInsuranceDTO> getCompetitionInsuranceList(CompetitionInsuranceDTO competitionInsuranceDTO){
		return competitionInsuranceService.getSimpleList(competitionInsuranceDTO);
	}

	@Override
	public PageList<CompetitionInsuranceDTO> getCompetitionInsuranceListForPage(CompetitionInsuranceDTO competitionInsuranceDTO, int pageNumber, int pageSize){
		return competitionInsuranceService.getSimpleListForPage(competitionInsuranceDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<CompetitionInsuranceDTO> getCompetitionInsuranceListForPage(QueryParam queryParam){
		return competitionInsuranceService.getSimpleListForPage(queryParam);
	}

	@Override
	public CompetitionInsuranceEntity toCompetitionInsuranceEntity(CompetitionInsuranceDTO dto){
		CompetitionInsuranceEntity entity = new CompetitionInsuranceEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<CompetitionInsuranceEntity> toCompetitionInsuranceEntities(List<CompetitionInsuranceDTO> dtos){
		List<CompetitionInsuranceEntity> entities = new ArrayList<>();
		for(CompetitionInsuranceDTO dto : dtos){
			entities.add(toCompetitionInsuranceEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveCompetitionScore(CompetitionScoreDTO competitionScoreDTO){
		if (null == competitionScoreDTO) {
			return null;
		}
		CompetitionScoreEntity entity = toCompetitionScoreEntity(competitionScoreDTO);
		competitionScoreDTO = competitionScoreService.save(entity);
		return competitionScoreDTO.getId();
	}

	@Override
	public void batchSaveCompetitionScore(List<CompetitionScoreDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<CompetitionScoreEntity> entities = toCompetitionScoreEntities(dtos);
		competitionScoreService.batchSave(entities);
	}

	@Override
	public int updateCompetitionScore(CompetitionScoreDTO competitionScoreDTO){
		competitionScoreDTO = competitionScoreService.updateBySql(competitionScoreDTO);
		return 1;
	}

	@Override
	public void batchUpdateCompetitionScore(List<CompetitionScoreDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		competitionScoreService.batchUpdate(dtos);
	}

	@Override
	public int deleteCompetitionScore(long id){
		return competitionScoreService.delete(id);
	}

	@Override
	public CompetitionScoreDTO getCompetitionScore(long id){
		return competitionScoreService.get(id);
	}

	@Override
	public CompetitionScoreDTO getCompetitionScore(CompetitionScoreDTO competitionScoreDTO){
		return competitionScoreService.get(competitionScoreDTO);
	}

	@Override
	public List<CompetitionScoreDTO> getCompetitionScoreList(CompetitionScoreDTO competitionScoreDTO){
		return competitionScoreService.getSimpleList(competitionScoreDTO);
	}

	@Override
	public PageList<CompetitionScoreDTO> getCompetitionScoreListForPage(CompetitionScoreDTO competitionScoreDTO, int pageNumber, int pageSize){
		return competitionScoreService.getSimpleListForPage(competitionScoreDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<CompetitionScoreDTO> getCompetitionScoreListForPage(QueryParam queryParam){
		return competitionScoreService.getSimpleListForPage(queryParam);
	}

	@Override
	public CompetitionScoreEntity toCompetitionScoreEntity(CompetitionScoreDTO dto){
		CompetitionScoreEntity entity = new CompetitionScoreEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<CompetitionScoreEntity> toCompetitionScoreEntities(List<CompetitionScoreDTO> dtos){
		List<CompetitionScoreEntity> entities = new ArrayList<>();
		for(CompetitionScoreDTO dto : dtos){
			entities.add(toCompetitionScoreEntity(dto));
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
	public CompetitionDTO getNewCompetition() {
		return competitionService.getNewCompetition();
	}
	
	@Override
	public void deleteCompetitionScoreByCompetitionId(Long competitionId) {
		this.competitionScoreService.deleteCompetitionScoreByCompetitionId(competitionId);
	}

	@Override
	public void deleteCompetitionScoreByTeamId(Long teamId) {
		this.competitionScoreService.deleteCompetitionScoreByTeamId(teamId);
	}

	@Override
	public Integer getScore(Long competitionId, Long teamId) {
		return this.competitionScoreService.getScore(competitionId, teamId);
	}

}