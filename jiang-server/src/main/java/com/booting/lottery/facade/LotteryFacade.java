/** create by auto at 2018-03-07 09:29:01**/
package com.booting.lottery.facade;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.booting.lottery.dto.GoldDetailDTO;
import com.booting.lottery.entity.GoldDetailEntity;
import com.booting.lottery.dto.GoldDrawDetailDTO;
import com.booting.lottery.entity.GoldDrawDetailEntity;
import com.booting.lottery.dto.InvitationDetailDTO;
import com.booting.lottery.entity.InvitationDetailEntity;
import com.booting.lottery.dto.LotteryAwardDetailDTO;
import com.booting.lottery.entity.LotteryAwardDetailEntity;
import com.booting.lottery.dto.LotteryAwardUserDetailDTO;
import com.booting.lottery.entity.LotteryAwardUserDetailEntity;
import com.booting.lottery.dto.LotteryConfigDTO;
import com.booting.lottery.entity.LotteryConfigEntity;
import com.booting.lottery.dto.LotteryDetailDTO;
import com.booting.lottery.entity.LotteryDetailEntity;
import com.booting.lottery.dto.LotteryDTO;
import com.booting.lottery.entity.LotteryEntity;
import com.booting.lottery.dto.UserNumDetailDTO;
import com.booting.lottery.entity.UserNumDetailEntity;

public interface LotteryFacade extends Serializable {

	/**
	 * 新增 金币获得明细
	 */
	public Long saveGoldDetail(GoldDetailDTO goldDetailDTO);

	/**
	 * 批量新增 金币获得明细
	 */
	public void batchSaveGoldDetail(List<GoldDetailDTO> dtos);

	/**
	 * 更新 金币获得明细
	 */
	public int updateGoldDetail(GoldDetailDTO goldDetailDTO);

	/**
	 * 批量 金币获得明细
	 */
	public void batchUpdateGoldDetail(List<GoldDetailDTO> dtos);

	/**
	 * 删除 金币获得明细
	 */
	public int deleteGoldDetail(long id);

	/**
	 * 根据主键获取 金币获得明细
	 */
	public GoldDetailDTO getGoldDetail(long id);

	/**
	 * 根据条件获取一条 金币获得明细
	 */
	public GoldDetailDTO getGoldDetail(GoldDetailDTO goldDetailDTO);

	/**
	 * 查询满足条件的 金币获得明细 列表(单表)
	 */
	public List<GoldDetailDTO> getGoldDetailList(GoldDetailDTO goldDetailDTO);

	/**
	 * 查询满足条件的 金币获得明细 列表(分页)(单表)
	 */
	public PageList<GoldDetailDTO> getGoldDetailListForPage(GoldDetailDTO goldDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 金币获得明细 列表(分页)(单表)
	 */
	public PageList<GoldDetailDTO> getGoldDetailListForPage(QueryParam queryParam);

	/**
	 * 金币获得明细DTO 转换成 Entity
	 */
	public GoldDetailEntity toGoldDetailEntity(GoldDetailDTO goldDetailDTO);

	/**
	 * 金币获得明细DTOs 转换成 Entities
	 */
	public List<GoldDetailEntity> toGoldDetailEntities(List<GoldDetailDTO> dtoes);

	/**
	 * 新增 金币提现明细
	 */
	public Long saveGoldDrawDetail(GoldDrawDetailDTO goldDrawDetailDTO);

	/**
	 * 批量新增 金币提现明细
	 */
	public void batchSaveGoldDrawDetail(List<GoldDrawDetailDTO> dtos);

	/**
	 * 更新 金币提现明细
	 */
	public int updateGoldDrawDetail(GoldDrawDetailDTO goldDrawDetailDTO);

	/**
	 * 批量 金币提现明细
	 */
	public void batchUpdateGoldDrawDetail(List<GoldDrawDetailDTO> dtos);

	/**
	 * 删除 金币提现明细
	 */
	public int deleteGoldDrawDetail(long id);

	/**
	 * 根据主键获取 金币提现明细
	 */
	public GoldDrawDetailDTO getGoldDrawDetail(long id);

	/**
	 * 根据条件获取一条 金币提现明细
	 */
	public GoldDrawDetailDTO getGoldDrawDetail(GoldDrawDetailDTO goldDrawDetailDTO);

	/**
	 * 查询满足条件的 金币提现明细 列表(单表)
	 */
	public List<GoldDrawDetailDTO> getGoldDrawDetailList(GoldDrawDetailDTO goldDrawDetailDTO);

	/**
	 * 查询满足条件的 金币提现明细 列表(分页)(单表)
	 */
	public PageList<GoldDrawDetailDTO> getGoldDrawDetailListForPage(GoldDrawDetailDTO goldDrawDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 金币提现明细 列表(分页)(单表)
	 */
	public PageList<GoldDrawDetailDTO> getGoldDrawDetailListForPage(QueryParam queryParam);

	/**
	 * 金币提现明细DTO 转换成 Entity
	 */
	public GoldDrawDetailEntity toGoldDrawDetailEntity(GoldDrawDetailDTO goldDrawDetailDTO);

	/**
	 * 金币提现明细DTOs 转换成 Entities
	 */
	public List<GoldDrawDetailEntity> toGoldDrawDetailEntities(List<GoldDrawDetailDTO> dtoes);

	/**
	 * 新增 邀请明细
	 */
	public Long saveInvitationDetail(InvitationDetailDTO invitationDetailDTO);

	/**
	 * 批量新增 邀请明细
	 */
	public void batchSaveInvitationDetail(List<InvitationDetailDTO> dtos);

	/**
	 * 更新 邀请明细
	 */
	public int updateInvitationDetail(InvitationDetailDTO invitationDetailDTO);

	/**
	 * 批量 邀请明细
	 */
	public void batchUpdateInvitationDetail(List<InvitationDetailDTO> dtos);

	/**
	 * 删除 邀请明细
	 */
	public int deleteInvitationDetail(long id);

	/**
	 * 根据主键获取 邀请明细
	 */
	public InvitationDetailDTO getInvitationDetail(long id);

	/**
	 * 根据条件获取一条 邀请明细
	 */
	public InvitationDetailDTO getInvitationDetail(InvitationDetailDTO invitationDetailDTO);

	/**
	 * 查询满足条件的 邀请明细 列表(单表)
	 */
	public List<InvitationDetailDTO> getInvitationDetailList(InvitationDetailDTO invitationDetailDTO);

	/**
	 * 查询满足条件的 邀请明细 列表(分页)(单表)
	 */
	public PageList<InvitationDetailDTO> getInvitationDetailListForPage(InvitationDetailDTO invitationDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 邀请明细 列表(分页)(单表)
	 */
	public PageList<InvitationDetailDTO> getInvitationDetailListForPage(QueryParam queryParam);

	/**
	 * 邀请明细DTO 转换成 Entity
	 */
	public InvitationDetailEntity toInvitationDetailEntity(InvitationDetailDTO invitationDetailDTO);

	/**
	 * 邀请明细DTOs 转换成 Entities
	 */
	public List<InvitationDetailEntity> toInvitationDetailEntities(List<InvitationDetailDTO> dtoes);

	/**
	 * 新增 开奖明细
	 */
	public Long saveLotteryAwardDetail(LotteryAwardDetailDTO lotteryAwardDetailDTO);

	/**
	 * 批量新增 开奖明细
	 */
	public void batchSaveLotteryAwardDetail(List<LotteryAwardDetailDTO> dtos);

	/**
	 * 更新 开奖明细
	 */
	public int updateLotteryAwardDetail(LotteryAwardDetailDTO lotteryAwardDetailDTO);

	/**
	 * 批量 开奖明细
	 */
	public void batchUpdateLotteryAwardDetail(List<LotteryAwardDetailDTO> dtos);

	/**
	 * 删除 开奖明细
	 */
	public int deleteLotteryAwardDetail(long awardId);

	/**
	 * 根据主键获取 开奖明细
	 */
	public LotteryAwardDetailDTO getLotteryAwardDetail(long awardId);

	/**
	 * 根据条件获取一条 开奖明细
	 */
	public LotteryAwardDetailDTO getLotteryAwardDetail(LotteryAwardDetailDTO lotteryAwardDetailDTO);

	/**
	 * 查询满足条件的 开奖明细 列表(单表)
	 */
	public List<LotteryAwardDetailDTO> getLotteryAwardDetailList(LotteryAwardDetailDTO lotteryAwardDetailDTO);

	/**
	 * 查询满足条件的 开奖明细 列表(分页)(单表)
	 */
	public PageList<LotteryAwardDetailDTO> getLotteryAwardDetailListForPage(LotteryAwardDetailDTO lotteryAwardDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 开奖明细 列表(分页)(单表)
	 */
	public PageList<LotteryAwardDetailDTO> getLotteryAwardDetailListForPage(QueryParam queryParam);

	/**
	 * 开奖明细DTO 转换成 Entity
	 */
	public LotteryAwardDetailEntity toLotteryAwardDetailEntity(LotteryAwardDetailDTO lotteryAwardDetailDTO);

	/**
	 * 开奖明细DTOs 转换成 Entities
	 */
	public List<LotteryAwardDetailEntity> toLotteryAwardDetailEntities(List<LotteryAwardDetailDTO> dtoes);

	/**
	 * 新增 中奖人员明细
	 */
	public Long saveLotteryAwardUserDetail(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO);

	/**
	 * 批量新增 中奖人员明细
	 */
	public void batchSaveLotteryAwardUserDetail(List<LotteryAwardUserDetailDTO> dtos);

	/**
	 * 更新 中奖人员明细
	 */
	public int updateLotteryAwardUserDetail(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO);

	/**
	 * 批量 中奖人员明细
	 */
	public void batchUpdateLotteryAwardUserDetail(List<LotteryAwardUserDetailDTO> dtos);

	/**
	 * 删除 中奖人员明细
	 */
	public int deleteLotteryAwardUserDetail(long id);

	/**
	 * 根据主键获取 中奖人员明细
	 */
	public LotteryAwardUserDetailDTO getLotteryAwardUserDetail(long id);

	/**
	 * 根据条件获取一条 中奖人员明细
	 */
	public LotteryAwardUserDetailDTO getLotteryAwardUserDetail(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO);

	/**
	 * 查询满足条件的 中奖人员明细 列表(单表)
	 */
	public List<LotteryAwardUserDetailDTO> getLotteryAwardUserDetailList(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO);

	/**
	 * 查询满足条件的 中奖人员明细 列表(分页)(单表)
	 */
	public PageList<LotteryAwardUserDetailDTO> getLotteryAwardUserDetailListForPage(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 中奖人员明细 列表(分页)(单表)
	 */
	public PageList<LotteryAwardUserDetailDTO> getLotteryAwardUserDetailListForPage(QueryParam queryParam);

	/**
	 * 中奖人员明细DTO 转换成 Entity
	 */
	public LotteryAwardUserDetailEntity toLotteryAwardUserDetailEntity(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO);

	/**
	 * 中奖人员明细DTOs 转换成 Entities
	 */
	public List<LotteryAwardUserDetailEntity> toLotteryAwardUserDetailEntities(List<LotteryAwardUserDetailDTO> dtoes);

	/**
	 * 新增 配置信息
	 */
	public Long saveLotteryConfig(LotteryConfigDTO lotteryConfigDTO);

	/**
	 * 批量新增 配置信息
	 */
	public void batchSaveLotteryConfig(List<LotteryConfigDTO> dtos);

	/**
	 * 更新 配置信息
	 */
	public int updateLotteryConfig(LotteryConfigDTO lotteryConfigDTO);

	/**
	 * 批量 配置信息
	 */
	public void batchUpdateLotteryConfig(List<LotteryConfigDTO> dtos);

	/**
	 * 删除 配置信息
	 */
	public int deleteLotteryConfig(long configId);

	/**
	 * 根据主键获取 配置信息
	 */
	public LotteryConfigDTO getLotteryConfig(long configId);

	/**
	 * 根据条件获取一条 配置信息
	 */
	public LotteryConfigDTO getLotteryConfig(LotteryConfigDTO lotteryConfigDTO);

	/**
	 * 查询满足条件的 配置信息 列表(单表)
	 */
	public List<LotteryConfigDTO> getLotteryConfigList(LotteryConfigDTO lotteryConfigDTO);

	/**
	 * 查询满足条件的 配置信息 列表(分页)(单表)
	 */
	public PageList<LotteryConfigDTO> getLotteryConfigListForPage(LotteryConfigDTO lotteryConfigDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 配置信息 列表(分页)(单表)
	 */
	public PageList<LotteryConfigDTO> getLotteryConfigListForPage(QueryParam queryParam);

	/**
	 * 配置信息DTO 转换成 Entity
	 */
	public LotteryConfigEntity toLotteryConfigEntity(LotteryConfigDTO lotteryConfigDTO);

	/**
	 * 配置信息DTOs 转换成 Entities
	 */
	public List<LotteryConfigEntity> toLotteryConfigEntities(List<LotteryConfigDTO> dtoes);

	/**
	 * 新增 抽奖明细信息
	 */
	public Long saveLotteryDetail(LotteryDetailDTO lotteryDetailDTO);

	/**
	 * 批量新增 抽奖明细信息
	 */
	public void batchSaveLotteryDetail(List<LotteryDetailDTO> dtos);

	/**
	 * 更新 抽奖明细信息
	 */
	public int updateLotteryDetail(LotteryDetailDTO lotteryDetailDTO);

	/**
	 * 批量 抽奖明细信息
	 */
	public void batchUpdateLotteryDetail(List<LotteryDetailDTO> dtos);

	/**
	 * 删除 抽奖明细信息
	 */
	public int deleteLotteryDetail(long id);

	/**
	 * 根据主键获取 抽奖明细信息
	 */
	public LotteryDetailDTO getLotteryDetail(long id);

	/**
	 * 根据条件获取一条 抽奖明细信息
	 */
	public LotteryDetailDTO getLotteryDetail(LotteryDetailDTO lotteryDetailDTO);

	/**
	 * 查询满足条件的 抽奖明细信息 列表(单表)
	 */
	public List<LotteryDetailDTO> getLotteryDetailList(LotteryDetailDTO lotteryDetailDTO);

	/**
	 * 查询满足条件的 抽奖明细信息 列表(分页)(单表)
	 */
	public PageList<LotteryDetailDTO> getLotteryDetailListForPage(LotteryDetailDTO lotteryDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 抽奖明细信息 列表(分页)(单表)
	 */
	public PageList<LotteryDetailDTO> getLotteryDetailListForPage(QueryParam queryParam);

	/**
	 * 抽奖明细信息DTO 转换成 Entity
	 */
	public LotteryDetailEntity toLotteryDetailEntity(LotteryDetailDTO lotteryDetailDTO);

	/**
	 * 抽奖明细信息DTOs 转换成 Entities
	 */
	public List<LotteryDetailEntity> toLotteryDetailEntities(List<LotteryDetailDTO> dtoes);

	/**
	 * 新增 抽奖主体信息
	 */
	public Long saveLottery(LotteryDTO lotteryDTO);

	/**
	 * 批量新增 抽奖主体信息
	 */
	public void batchSaveLottery(List<LotteryDTO> dtos);

	/**
	 * 更新 抽奖主体信息
	 */
	public int updateLottery(LotteryDTO lotteryDTO);

	/**
	 * 批量 抽奖主体信息
	 */
	public void batchUpdateLottery(List<LotteryDTO> dtos);

	/**
	 * 删除 抽奖主体信息
	 */
	public int deleteLottery(long lotteryId);

	/**
	 * 根据主键获取 抽奖主体信息
	 */
	public LotteryDTO getLottery(long lotteryId);

	/**
	 * 根据条件获取一条 抽奖主体信息
	 */
	public LotteryDTO getLottery(LotteryDTO lotteryDTO);

	/**
	 * 查询满足条件的 抽奖主体信息 列表(单表)
	 */
	public List<LotteryDTO> getLotteryList(LotteryDTO lotteryDTO);

	/**
	 * 查询满足条件的 抽奖主体信息 列表(分页)(单表)
	 */
	public PageList<LotteryDTO> getLotteryListForPage(LotteryDTO lotteryDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 抽奖主体信息 列表(分页)(单表)
	 */
	public PageList<LotteryDTO> getLotteryListForPage(QueryParam queryParam);

	/**
	 * 抽奖主体信息DTO 转换成 Entity
	 */
	public LotteryEntity toLotteryEntity(LotteryDTO lotteryDTO);

	/**
	 * 抽奖主体信息DTOs 转换成 Entities
	 */
	public List<LotteryEntity> toLotteryEntities(List<LotteryDTO> dtoes);

	/**
	 * 新增 用户号码明细
	 */
	public Long saveUserNumDetail(UserNumDetailDTO userNumDetailDTO);

	/**
	 * 批量新增 用户号码明细
	 */
	public void batchSaveUserNumDetail(List<UserNumDetailDTO> dtos);

	/**
	 * 更新 用户号码明细
	 */
	public int updateUserNumDetail(UserNumDetailDTO userNumDetailDTO);

	/**
	 * 批量 用户号码明细
	 */
	public void batchUpdateUserNumDetail(List<UserNumDetailDTO> dtos);

	/**
	 * 删除 用户号码明细
	 */
	public int deleteUserNumDetail(long id);

	/**
	 * 根据主键获取 用户号码明细
	 */
	public UserNumDetailDTO getUserNumDetail(long id);

	/**
	 * 根据条件获取一条 用户号码明细
	 */
	public UserNumDetailDTO getUserNumDetail(UserNumDetailDTO userNumDetailDTO);

	/**
	 * 查询满足条件的 用户号码明细 列表(单表)
	 */
	public List<UserNumDetailDTO> getUserNumDetailList(UserNumDetailDTO userNumDetailDTO);

	/**
	 * 查询满足条件的 用户号码明细 列表(分页)(单表)
	 */
	public PageList<UserNumDetailDTO> getUserNumDetailListForPage(UserNumDetailDTO userNumDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 用户号码明细 列表(分页)(单表)
	 */
	public PageList<UserNumDetailDTO> getUserNumDetailListForPage(QueryParam queryParam);

	/**
	 * 用户号码明细DTO 转换成 Entity
	 */
	public UserNumDetailEntity toUserNumDetailEntity(UserNumDetailDTO userNumDetailDTO);

	/**
	 * 用户号码明细DTOs 转换成 Entities
	 */
	public List<UserNumDetailEntity> toUserNumDetailEntities(List<UserNumDetailDTO> dtoes);

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

	public Map<String, Integer> openAward(LotteryDTO lottery, int lun, String awardUserIds);

	/**
	 * 获取最新的一条记录
	 * @param newest 是否end
	 * @return
	 */
	public LotteryDTO getNewestLottery(int end);

	public List<GoldDetailDTO> getGoldDetailList(Map<String, Object> params);

	public List<GoldDrawDetailDTO> getGoldDrawDetailList(Map<String, Object> params);

	public List<InvitationDetailDTO> getInvitationDetailList(Map<String, Object> params);

	public List<UserNumDetailDTO> getNumberList(Map<String, Object> params);

	public List<Map<String, Object>> getHistory(int lun, int size);
}