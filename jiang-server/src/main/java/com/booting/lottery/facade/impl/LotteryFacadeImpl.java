/** create by auto at 2018-03-07 09:29:01**/
package com.booting.lottery.facade.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.lottery.dto.GoldDetailDTO;
import com.booting.lottery.dto.GoldDrawDetailDTO;
import com.booting.lottery.dto.InvitationDetailDTO;
import com.booting.lottery.dto.LotteryAwardDetailDTO;
import com.booting.lottery.dto.LotteryAwardUserDetailDTO;
import com.booting.lottery.dto.LotteryConfigDTO;
import com.booting.lottery.dto.LotteryDTO;
import com.booting.lottery.dto.LotteryDetailDTO;
import com.booting.lottery.dto.UserNumDetailDTO;
import com.booting.lottery.entity.GoldDetailEntity;
import com.booting.lottery.entity.GoldDrawDetailEntity;
import com.booting.lottery.entity.InvitationDetailEntity;
import com.booting.lottery.entity.LotteryAwardDetailEntity;
import com.booting.lottery.entity.LotteryAwardUserDetailEntity;
import com.booting.lottery.entity.LotteryConfigEntity;
import com.booting.lottery.entity.LotteryDetailEntity;
import com.booting.lottery.entity.LotteryEntity;
import com.booting.lottery.entity.UserNumDetailEntity;
import com.booting.lottery.facade.LotteryFacade;
import com.booting.lottery.service.GoldDetailService;
import com.booting.lottery.service.GoldDrawDetailService;
import com.booting.lottery.service.InvitationDetailService;
import com.booting.lottery.service.LotteryAwardDetailService;
import com.booting.lottery.service.LotteryAwardUserDetailService;
import com.booting.lottery.service.LotteryConfigService;
import com.booting.lottery.service.LotteryDetailService;
import com.booting.lottery.service.LotteryService;
import com.booting.lottery.service.UserNumDetailService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;

@Service("lotteryFacade")
public class LotteryFacadeImpl implements LotteryFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private GoldDetailService goldDetailService;

	@Autowired
	private GoldDrawDetailService goldDrawDetailService;

	@Autowired
	private InvitationDetailService invitationDetailService;

	@Autowired
	private LotteryAwardDetailService lotteryAwardDetailService;

	@Autowired
	private LotteryAwardUserDetailService lotteryAwardUserDetailService;

	@Autowired
	private LotteryConfigService lotteryConfigService;

	@Autowired
	private LotteryDetailService lotteryDetailService;

	@Autowired
	private LotteryService lotteryService;

	@Autowired
	private UserNumDetailService userNumDetailService;


	@Override
	public Long saveGoldDetail(GoldDetailDTO goldDetailDTO){
		if (null == goldDetailDTO) {
			return null;
		}
		GoldDetailEntity entity = toGoldDetailEntity(goldDetailDTO);
		goldDetailDTO = goldDetailService.save(entity);
		return goldDetailDTO.getId();
	}

	@Override
	public void batchSaveGoldDetail(List<GoldDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<GoldDetailEntity> entities = toGoldDetailEntities(dtos);
		goldDetailService.batchSave(entities);
	}

	@Override
	public int updateGoldDetail(GoldDetailDTO goldDetailDTO){
		goldDetailDTO = goldDetailService.updateBySql(goldDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateGoldDetail(List<GoldDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		goldDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteGoldDetail(long id){
		return goldDetailService.delete(id);
	}

	@Override
	public GoldDetailDTO getGoldDetail(long id){
		return goldDetailService.get(id);
	}

	@Override
	public GoldDetailDTO getGoldDetail(GoldDetailDTO goldDetailDTO){
		return goldDetailService.get(goldDetailDTO);
	}

	@Override
	public List<GoldDetailDTO> getGoldDetailList(GoldDetailDTO goldDetailDTO){
		return goldDetailService.getSimpleList(goldDetailDTO);
	}

	@Override
	public PageList<GoldDetailDTO> getGoldDetailListForPage(GoldDetailDTO goldDetailDTO, int pageNumber, int pageSize){
		return goldDetailService.getSimpleListForPage(goldDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<GoldDetailDTO> getGoldDetailListForPage(QueryParam queryParam){
		return goldDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public GoldDetailEntity toGoldDetailEntity(GoldDetailDTO dto){
		GoldDetailEntity entity = new GoldDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<GoldDetailEntity> toGoldDetailEntities(List<GoldDetailDTO> dtos){
		List<GoldDetailEntity> entities = new ArrayList<>();
		for(GoldDetailDTO dto : dtos){
			entities.add(toGoldDetailEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveGoldDrawDetail(GoldDrawDetailDTO goldDrawDetailDTO){
		if (null == goldDrawDetailDTO) {
			return null;
		}
		GoldDrawDetailEntity entity = toGoldDrawDetailEntity(goldDrawDetailDTO);
		goldDrawDetailDTO = goldDrawDetailService.save(entity);
		return goldDrawDetailDTO.getId();
	}

	@Override
	public void batchSaveGoldDrawDetail(List<GoldDrawDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<GoldDrawDetailEntity> entities = toGoldDrawDetailEntities(dtos);
		goldDrawDetailService.batchSave(entities);
	}

	@Override
	public int updateGoldDrawDetail(GoldDrawDetailDTO goldDrawDetailDTO){
		goldDrawDetailDTO = goldDrawDetailService.updateBySql(goldDrawDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateGoldDrawDetail(List<GoldDrawDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		goldDrawDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteGoldDrawDetail(long id){
		return goldDrawDetailService.delete(id);
	}

	@Override
	public GoldDrawDetailDTO getGoldDrawDetail(long id){
		return goldDrawDetailService.get(id);
	}

	@Override
	public GoldDrawDetailDTO getGoldDrawDetail(GoldDrawDetailDTO goldDrawDetailDTO){
		return goldDrawDetailService.get(goldDrawDetailDTO);
	}

	@Override
	public List<GoldDrawDetailDTO> getGoldDrawDetailList(GoldDrawDetailDTO goldDrawDetailDTO){
		return goldDrawDetailService.getSimpleList(goldDrawDetailDTO);
	}

	@Override
	public PageList<GoldDrawDetailDTO> getGoldDrawDetailListForPage(GoldDrawDetailDTO goldDrawDetailDTO, int pageNumber, int pageSize){
		return goldDrawDetailService.getSimpleListForPage(goldDrawDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<GoldDrawDetailDTO> getGoldDrawDetailListForPage(QueryParam queryParam){
		return goldDrawDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public GoldDrawDetailEntity toGoldDrawDetailEntity(GoldDrawDetailDTO dto){
		GoldDrawDetailEntity entity = new GoldDrawDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<GoldDrawDetailEntity> toGoldDrawDetailEntities(List<GoldDrawDetailDTO> dtos){
		List<GoldDrawDetailEntity> entities = new ArrayList<>();
		for(GoldDrawDetailDTO dto : dtos){
			entities.add(toGoldDrawDetailEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveInvitationDetail(InvitationDetailDTO invitationDetailDTO){
		if (null == invitationDetailDTO) {
			return null;
		}
		InvitationDetailEntity entity = toInvitationDetailEntity(invitationDetailDTO);
		invitationDetailDTO = invitationDetailService.save(entity);
		return invitationDetailDTO.getId();
	}

	@Override
	public void batchSaveInvitationDetail(List<InvitationDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<InvitationDetailEntity> entities = toInvitationDetailEntities(dtos);
		invitationDetailService.batchSave(entities);
	}

	@Override
	public int updateInvitationDetail(InvitationDetailDTO invitationDetailDTO){
		invitationDetailDTO = invitationDetailService.updateBySql(invitationDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateInvitationDetail(List<InvitationDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		invitationDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteInvitationDetail(long id){
		return invitationDetailService.delete(id);
	}

	@Override
	public InvitationDetailDTO getInvitationDetail(long id){
		return invitationDetailService.get(id);
	}

	@Override
	public InvitationDetailDTO getInvitationDetail(InvitationDetailDTO invitationDetailDTO){
		return invitationDetailService.get(invitationDetailDTO);
	}

	@Override
	public List<InvitationDetailDTO> getInvitationDetailList(InvitationDetailDTO invitationDetailDTO){
		return invitationDetailService.getSimpleList(invitationDetailDTO);
	}

	@Override
	public PageList<InvitationDetailDTO> getInvitationDetailListForPage(InvitationDetailDTO invitationDetailDTO, int pageNumber, int pageSize){
		return invitationDetailService.getSimpleListForPage(invitationDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<InvitationDetailDTO> getInvitationDetailListForPage(QueryParam queryParam){
		return invitationDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public InvitationDetailEntity toInvitationDetailEntity(InvitationDetailDTO dto){
		InvitationDetailEntity entity = new InvitationDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<InvitationDetailEntity> toInvitationDetailEntities(List<InvitationDetailDTO> dtos){
		List<InvitationDetailEntity> entities = new ArrayList<>();
		for(InvitationDetailDTO dto : dtos){
			entities.add(toInvitationDetailEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveLotteryAwardDetail(LotteryAwardDetailDTO lotteryAwardDetailDTO){
		if (null == lotteryAwardDetailDTO) {
			return null;
		}
		LotteryAwardDetailEntity entity = toLotteryAwardDetailEntity(lotteryAwardDetailDTO);
		lotteryAwardDetailDTO = lotteryAwardDetailService.save(entity);
		return lotteryAwardDetailDTO.getAwardId();
	}

	@Override
	public void batchSaveLotteryAwardDetail(List<LotteryAwardDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<LotteryAwardDetailEntity> entities = toLotteryAwardDetailEntities(dtos);
		lotteryAwardDetailService.batchSave(entities);
	}

	@Override
	public int updateLotteryAwardDetail(LotteryAwardDetailDTO lotteryAwardDetailDTO){
		lotteryAwardDetailDTO = lotteryAwardDetailService.updateBySql(lotteryAwardDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateLotteryAwardDetail(List<LotteryAwardDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		lotteryAwardDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteLotteryAwardDetail(long awardId){
		return lotteryAwardDetailService.delete(awardId);
	}

	@Override
	public LotteryAwardDetailDTO getLotteryAwardDetail(long awardId){
		return lotteryAwardDetailService.get(awardId);
	}

	@Override
	public LotteryAwardDetailDTO getLotteryAwardDetail(LotteryAwardDetailDTO lotteryAwardDetailDTO){
		return lotteryAwardDetailService.get(lotteryAwardDetailDTO);
	}

	@Override
	public List<LotteryAwardDetailDTO> getLotteryAwardDetailList(LotteryAwardDetailDTO lotteryAwardDetailDTO){
		return lotteryAwardDetailService.getSimpleList(lotteryAwardDetailDTO);
	}

	@Override
	public PageList<LotteryAwardDetailDTO> getLotteryAwardDetailListForPage(LotteryAwardDetailDTO lotteryAwardDetailDTO, int pageNumber, int pageSize){
		return lotteryAwardDetailService.getSimpleListForPage(lotteryAwardDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<LotteryAwardDetailDTO> getLotteryAwardDetailListForPage(QueryParam queryParam){
		return lotteryAwardDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public LotteryAwardDetailEntity toLotteryAwardDetailEntity(LotteryAwardDetailDTO dto){
		LotteryAwardDetailEntity entity = new LotteryAwardDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<LotteryAwardDetailEntity> toLotteryAwardDetailEntities(List<LotteryAwardDetailDTO> dtos){
		List<LotteryAwardDetailEntity> entities = new ArrayList<>();
		for(LotteryAwardDetailDTO dto : dtos){
			entities.add(toLotteryAwardDetailEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveLotteryAwardUserDetail(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO){
		if (null == lotteryAwardUserDetailDTO) {
			return null;
		}
		LotteryAwardUserDetailEntity entity = toLotteryAwardUserDetailEntity(lotteryAwardUserDetailDTO);
		lotteryAwardUserDetailDTO = lotteryAwardUserDetailService.save(entity);
		return lotteryAwardUserDetailDTO.getId();
	}

	@Override
	public void batchSaveLotteryAwardUserDetail(List<LotteryAwardUserDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<LotteryAwardUserDetailEntity> entities = toLotteryAwardUserDetailEntities(dtos);
		lotteryAwardUserDetailService.batchSave(entities);
	}

	@Override
	public int updateLotteryAwardUserDetail(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO){
		lotteryAwardUserDetailDTO = lotteryAwardUserDetailService.updateBySql(lotteryAwardUserDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateLotteryAwardUserDetail(List<LotteryAwardUserDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		lotteryAwardUserDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteLotteryAwardUserDetail(long id){
		return lotteryAwardUserDetailService.delete(id);
	}

	@Override
	public LotteryAwardUserDetailDTO getLotteryAwardUserDetail(long id){
		return lotteryAwardUserDetailService.get(id);
	}

	@Override
	public LotteryAwardUserDetailDTO getLotteryAwardUserDetail(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO){
		return lotteryAwardUserDetailService.get(lotteryAwardUserDetailDTO);
	}

	@Override
	public List<LotteryAwardUserDetailDTO> getLotteryAwardUserDetailList(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO){
		return lotteryAwardUserDetailService.getSimpleList(lotteryAwardUserDetailDTO);
	}

	@Override
	public PageList<LotteryAwardUserDetailDTO> getLotteryAwardUserDetailListForPage(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO, int pageNumber, int pageSize){
		return lotteryAwardUserDetailService.getSimpleListForPage(lotteryAwardUserDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<LotteryAwardUserDetailDTO> getLotteryAwardUserDetailListForPage(QueryParam queryParam){
		return lotteryAwardUserDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public LotteryAwardUserDetailEntity toLotteryAwardUserDetailEntity(LotteryAwardUserDetailDTO dto){
		LotteryAwardUserDetailEntity entity = new LotteryAwardUserDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<LotteryAwardUserDetailEntity> toLotteryAwardUserDetailEntities(List<LotteryAwardUserDetailDTO> dtos){
		List<LotteryAwardUserDetailEntity> entities = new ArrayList<>();
		for(LotteryAwardUserDetailDTO dto : dtos){
			entities.add(toLotteryAwardUserDetailEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveLotteryConfig(LotteryConfigDTO lotteryConfigDTO){
		if (null == lotteryConfigDTO) {
			return null;
		}
		LotteryConfigEntity entity = toLotteryConfigEntity(lotteryConfigDTO);
		lotteryConfigDTO = lotteryConfigService.save(entity);
		return lotteryConfigDTO.getConfigId();
	}

	@Override
	public void batchSaveLotteryConfig(List<LotteryConfigDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<LotteryConfigEntity> entities = toLotteryConfigEntities(dtos);
		lotteryConfigService.batchSave(entities);
	}

	@Override
	public int updateLotteryConfig(LotteryConfigDTO lotteryConfigDTO){
		lotteryConfigDTO = lotteryConfigService.updateBySql(lotteryConfigDTO);
		return 1;
	}

	@Override
	public void batchUpdateLotteryConfig(List<LotteryConfigDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		lotteryConfigService.batchUpdate(dtos);
	}

	@Override
	public int deleteLotteryConfig(long configId){
		return lotteryConfigService.delete(configId);
	}

	@Override
	public LotteryConfigDTO getLotteryConfig(long configId){
		return lotteryConfigService.get(configId);
	}

	@Override
	public LotteryConfigDTO getLotteryConfig(LotteryConfigDTO lotteryConfigDTO){
		return lotteryConfigService.get(lotteryConfigDTO);
	}

	@Override
	public List<LotteryConfigDTO> getLotteryConfigList(LotteryConfigDTO lotteryConfigDTO){
		return lotteryConfigService.getSimpleList(lotteryConfigDTO);
	}

	@Override
	public PageList<LotteryConfigDTO> getLotteryConfigListForPage(LotteryConfigDTO lotteryConfigDTO, int pageNumber, int pageSize){
		return lotteryConfigService.getSimpleListForPage(lotteryConfigDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<LotteryConfigDTO> getLotteryConfigListForPage(QueryParam queryParam){
		return lotteryConfigService.getSimpleListForPage(queryParam);
	}

	@Override
	public LotteryConfigEntity toLotteryConfigEntity(LotteryConfigDTO dto){
		LotteryConfigEntity entity = new LotteryConfigEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<LotteryConfigEntity> toLotteryConfigEntities(List<LotteryConfigDTO> dtos){
		List<LotteryConfigEntity> entities = new ArrayList<>();
		for(LotteryConfigDTO dto : dtos){
			entities.add(toLotteryConfigEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveLotteryDetail(LotteryDetailDTO lotteryDetailDTO){
		if (null == lotteryDetailDTO) {
			return null;
		}
		LotteryDetailEntity entity = toLotteryDetailEntity(lotteryDetailDTO);
		lotteryDetailDTO = lotteryDetailService.save(entity);
		return lotteryDetailDTO.getId();
	}

	@Override
	public void batchSaveLotteryDetail(List<LotteryDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<LotteryDetailEntity> entities = toLotteryDetailEntities(dtos);
		lotteryDetailService.batchSave(entities);
	}

	@Override
	public int updateLotteryDetail(LotteryDetailDTO lotteryDetailDTO){
		lotteryDetailDTO = lotteryDetailService.updateBySql(lotteryDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateLotteryDetail(List<LotteryDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		lotteryDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteLotteryDetail(long id){
		return lotteryDetailService.delete(id);
	}

	@Override
	public LotteryDetailDTO getLotteryDetail(long id){
		return lotteryDetailService.get(id);
	}

	@Override
	public LotteryDetailDTO getLotteryDetail(LotteryDetailDTO lotteryDetailDTO){
		return lotteryDetailService.get(lotteryDetailDTO);
	}

	@Override
	public List<LotteryDetailDTO> getLotteryDetailList(LotteryDetailDTO lotteryDetailDTO){
		return lotteryDetailService.getSimpleList(lotteryDetailDTO);
	}

	@Override
	public PageList<LotteryDetailDTO> getLotteryDetailListForPage(LotteryDetailDTO lotteryDetailDTO, int pageNumber, int pageSize){
		return lotteryDetailService.getSimpleListForPage(lotteryDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<LotteryDetailDTO> getLotteryDetailListForPage(QueryParam queryParam){
		return lotteryDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public LotteryDetailEntity toLotteryDetailEntity(LotteryDetailDTO dto){
		LotteryDetailEntity entity = new LotteryDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<LotteryDetailEntity> toLotteryDetailEntities(List<LotteryDetailDTO> dtos){
		List<LotteryDetailEntity> entities = new ArrayList<>();
		for(LotteryDetailDTO dto : dtos){
			entities.add(toLotteryDetailEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveLottery(LotteryDTO lotteryDTO){
		if (null == lotteryDTO) {
			return null;
		}
		LotteryEntity entity = toLotteryEntity(lotteryDTO);
		lotteryDTO = lotteryService.save(entity);
		return lotteryDTO.getLotteryId();
	}

	@Override
	public void batchSaveLottery(List<LotteryDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<LotteryEntity> entities = toLotteryEntities(dtos);
		lotteryService.batchSave(entities);
	}

	@Override
	public int updateLottery(LotteryDTO lotteryDTO){
		lotteryDTO = lotteryService.updateBySql(lotteryDTO);
		return 1;
	}

	@Override
	public void batchUpdateLottery(List<LotteryDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		lotteryService.batchUpdate(dtos);
	}

	@Override
	public int deleteLottery(long lotteryId){
		return lotteryService.delete(lotteryId);
	}

	@Override
	public LotteryDTO getLottery(long lotteryId){
		return lotteryService.get(lotteryId);
	}

	@Override
	public LotteryDTO getLottery(LotteryDTO lotteryDTO){
		return lotteryService.get(lotteryDTO);
	}

	@Override
	public List<LotteryDTO> getLotteryList(LotteryDTO lotteryDTO){
		return lotteryService.getSimpleList(lotteryDTO);
	}

	@Override
	public PageList<LotteryDTO> getLotteryListForPage(LotteryDTO lotteryDTO, int pageNumber, int pageSize){
		return lotteryService.getSimpleListForPage(lotteryDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<LotteryDTO> getLotteryListForPage(QueryParam queryParam){
		return lotteryService.getSimpleListForPage(queryParam);
	}

	@Override
	public LotteryEntity toLotteryEntity(LotteryDTO dto){
		LotteryEntity entity = new LotteryEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<LotteryEntity> toLotteryEntities(List<LotteryDTO> dtos){
		List<LotteryEntity> entities = new ArrayList<>();
		for(LotteryDTO dto : dtos){
			entities.add(toLotteryEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveUserNumDetail(UserNumDetailDTO userNumDetailDTO){
		if (null == userNumDetailDTO) {
			return null;
		}
		UserNumDetailEntity entity = toUserNumDetailEntity(userNumDetailDTO);
		userNumDetailDTO = userNumDetailService.save(entity);
		return userNumDetailDTO.getId();
	}

	@Override
	public void batchSaveUserNumDetail(List<UserNumDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<UserNumDetailEntity> entities = toUserNumDetailEntities(dtos);
		userNumDetailService.batchSave(entities);
	}

	@Override
	public int updateUserNumDetail(UserNumDetailDTO userNumDetailDTO){
		userNumDetailDTO = userNumDetailService.updateBySql(userNumDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateUserNumDetail(List<UserNumDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		userNumDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteUserNumDetail(long id){
		return userNumDetailService.delete(id);
	}

	@Override
	public UserNumDetailDTO getUserNumDetail(long id){
		return userNumDetailService.get(id);
	}

	@Override
	public UserNumDetailDTO getUserNumDetail(UserNumDetailDTO userNumDetailDTO){
		return userNumDetailService.get(userNumDetailDTO);
	}

	@Override
	public List<UserNumDetailDTO> getUserNumDetailList(UserNumDetailDTO userNumDetailDTO){
		return userNumDetailService.getSimpleList(userNumDetailDTO);
	}

	@Override
	public PageList<UserNumDetailDTO> getUserNumDetailListForPage(UserNumDetailDTO userNumDetailDTO, int pageNumber, int pageSize){
		return userNumDetailService.getSimpleListForPage(userNumDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<UserNumDetailDTO> getUserNumDetailListForPage(QueryParam queryParam){
		return userNumDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public UserNumDetailEntity toUserNumDetailEntity(UserNumDetailDTO dto){
		UserNumDetailEntity entity = new UserNumDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<UserNumDetailEntity> toUserNumDetailEntities(List<UserNumDetailDTO> dtos){
		List<UserNumDetailEntity> entities = new ArrayList<>();
		for(UserNumDetailDTO dto : dtos){
			entities.add(toUserNumDetailEntity(dto));
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
	public Map<String, Integer> openAward(LotteryDTO lottery, int lun, String awardUserIds) {
		Map<String, Integer> list = this.lotteryDetailService.getDetail(lottery, lun, awardUserIds);
		return list;
	}

	@Override
	public LotteryDTO getNewestLottery(int end) {
		return this.lotteryService.getNewestLottery(end);
	}

	@Override
	public List<GoldDetailDTO> getGoldDetailList(Map<String, Object> params) {
		return this.goldDetailService.getGoldDetailList(params);
	}

	@Override
	public List<GoldDrawDetailDTO> getGoldDrawDetailList(Map<String, Object> params) {
		return this.goldDrawDetailService.getGoldDrawDetailList(params);
	}

	@Override
	public List<InvitationDetailDTO> getInvitationDetailList(Map<String, Object> params) {
		return this.invitationDetailService.getInvitationDetailList(params);
	}

	@Override
	public List<UserNumDetailDTO> getNumberList(Map<String, Object> params) {
		return userNumDetailService.getNumberList(params);
	}

	@Override
	public List<Map<String, Object>> getHistory(int lun, int size) {
		return this.lotteryDetailService.getHistory(lun, size);
	}
}