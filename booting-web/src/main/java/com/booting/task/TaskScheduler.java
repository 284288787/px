/**create by liuhua at 2017年9月4日 下午2:29:27**/
package com.booting.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.booting.common.CommonConstants;
import com.booting.common.CommonConstants.CouponIds;
import com.booting.common.CommonConstants.UserIdentity;
import com.booting.common.CommonConstants.UserService;
import com.booting.common.PushInfo;
import com.booting.competition.dto.CompetitionDTO;
import com.booting.competition.dto.CompetitionInsuranceDTO;
import com.booting.competition.dto.InsuranceOrderDTO;
import com.booting.competition.dto.InsuranceOrderDetailDTO;
import com.booting.competition.facade.CompetitionFacade;
import com.booting.coupon.dto.UsedCouponDetailDTO;
import com.booting.court.dto.CourtDTO;
import com.booting.court.dto.ZoneUseDetailDTO;
import com.booting.insurance.InsuranceResult;
import com.booting.insurance.InsuranceUtil;
import com.booting.insurance.OrderDetail;
import com.booting.pkg.dto.UseServiceDetailDTO;
import com.booting.service.impl.BaseWebService;
import com.booting.system.dto.UserInfoDTO;
import com.booting.system.facade.SystemFacade;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.utils.CglibBeanUtils;

@Service
@EnableScheduling
public class TaskScheduler extends BaseWebService{

	@Autowired
	private CompetitionFacade competitionFacade;
	@Autowired
	private SystemFacade systemFacade;

	/**
	 * 成绩
	 * 比赛开始后三天后不允许上传成绩，并结算胜负
	 *
	 */
	public void taskCompetitionScore(){
		System.out.println(ParamHandler.getDateStringOfNow("yyyy-MM-dd HH:mm:ss") + " 执行了[成绩]任务。");
		//找到此刻之前已经结束的并且还在上传成绩的 比赛
		CompetitionDTO competition = new CompetitionDTO();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -3);
		competition.setEndTime(calendar.getTime());
		competition.setStatus(2);
		competition.setScoreStatus(1);
		List<CompetitionDTO> competitions = competitionFacade.getCompetitionList(competition);
		for (CompetitionDTO competitionDTO : competitions) {
			int initiatorScore = competitionFacade.getScore(competitionDTO.getCompetitionId(), competitionDTO.getInitiatorTeamId());
			int challengerScore = competitionFacade.getScore(competitionDTO.getCompetitionId(), competitionDTO.getChallengerTeamId());
			int winner = initiatorScore > challengerScore ? 1 : initiatorScore == challengerScore ? 3 : 2;
			competitionDTO.setScoreStatus(2);
			competitionDTO.setWinner(winner);
			competitionDTO.setInitiatorScore(initiatorScore);
			competitionDTO.setChallengerScore(challengerScore);
			if (competitionDTO.getCompetitionType() == 3) {
				int ticketNum = getTicketNum(competitionDTO.getType(), competitionDTO.getCompetitionType(), competitionDTO.getCompetitionFormat(), true);
				List<UseServiceDetailDTO> details = new ArrayList<>();
				if (winner == 1) {
					UseServiceDetailDTO useServiceDetailDTO = new UseServiceDetailDTO();
					useServiceDetailDTO.setUserId(competitionDTO.getInitiateUserId());
					useServiceDetailDTO.setTeamId(competitionDTO.getInitiatorTeamId());
					useServiceDetailDTO.setServiceId(UserService.ball_ticket.getServiceId());
					useServiceDetailDTO.setSubjectId(competitionDTO.getCompetitionId());
					useServiceDetailDTO.setNum(ticketNum * -1);
					useServiceDetailDTO.setDescrition("比赛获胜，退全部券");
					useServiceDetailDTO.setCreateTime(new Date());
					details.add(useServiceDetailDTO);
				}else if (winner == 2) {
					UseServiceDetailDTO useServiceDetailDTO = new UseServiceDetailDTO();
					useServiceDetailDTO.setUserId(competitionDTO.getChallengerUserId());
					useServiceDetailDTO.setTeamId(competitionDTO.getChallengerTeamId());
					useServiceDetailDTO.setServiceId(UserService.ball_ticket.getServiceId());
					useServiceDetailDTO.setSubjectId(competitionDTO.getCompetitionId());
					useServiceDetailDTO.setNum(ticketNum * -1);
					useServiceDetailDTO.setDescrition("比赛获胜，退全部券");
					useServiceDetailDTO.setCreateTime(new Date());
					details.add(useServiceDetailDTO);
				}else{
					UseServiceDetailDTO useServiceDetailDTO1 = new UseServiceDetailDTO();
					useServiceDetailDTO1.setUserId(competitionDTO.getInitiateUserId());
					useServiceDetailDTO1.setTeamId(competitionDTO.getInitiatorTeamId());
					useServiceDetailDTO1.setServiceId(UserService.ball_ticket.getServiceId());
					useServiceDetailDTO1.setSubjectId(competitionDTO.getCompetitionId());
					useServiceDetailDTO1.setNum(ticketNum / 2 * -1);
					useServiceDetailDTO1.setDescrition("比赛获胜，退一半");
					useServiceDetailDTO1.setCreateTime(new Date());
					details.add(useServiceDetailDTO1);
					UseServiceDetailDTO useServiceDetailDTO2 = new UseServiceDetailDTO();
					useServiceDetailDTO2.setUserId(competitionDTO.getChallengerUserId());
					useServiceDetailDTO2.setTeamId(competitionDTO.getChallengerTeamId());
					useServiceDetailDTO2.setServiceId(UserService.ball_ticket.getServiceId());
					useServiceDetailDTO2.setSubjectId(competitionDTO.getCompetitionId());
					useServiceDetailDTO2.setNum(ticketNum / 2 * -1);
					useServiceDetailDTO2.setDescrition("比赛获胜，退一半");
					useServiceDetailDTO2.setCreateTime(new Date());
					details.add(useServiceDetailDTO2);
				}
				this.packageFacade.batchSaveUseServiceDetail(details);
			}
		}
		competitionFacade.batchUpdateCompetition(competitions);
	}
	
	public int getTicketNum(Integer type, Integer competitionType, Integer competitionFormat, boolean initiator) {
		int ticket = 0;
//		if (type == 1) {
//			ticket = CommonConstants.tickets.get(competitionFormat + "_" + competitionType + "_" + (initiator ? 1 : 0));
//		} else {
			ticket = CommonConstants.tickets2.get(competitionFormat + "_" + competitionType + "_" + (initiator ? 1 : 0));
//		}
		return ticket;
	}
	
	/**
	 * 买保险
	 * 实时购买失败时，任务自动购买
	 * 0分 10分 30分 都会执行一次
	 * @author liuhua
	 *
	 */
	@Scheduled(cron = "0 0,10,30 * * * ?")
	public void taskMinute(){
		taskBuyInsurance();
		taskCompetitionScore();
	}
	
	public void taskBuyInsurance(){
		System.out.println(ParamHandler.getDateStringOfNow("yyyy-MM-dd HH:mm:ss") + " 执行了[买保险]任务。");
		CompetitionInsuranceDTO competitionInsuranceDTO = new CompetitionInsuranceDTO();
		competitionInsuranceDTO.setStatus(3);
		List<CompetitionInsuranceDTO> insurances = competitionFacade.getCompetitionInsuranceList(competitionInsuranceDTO);
		System.out.println("需要买保险的记录数：" + insurances.size());
		Map<Long, List<CompetitionInsuranceDTO>> map = new HashMap<>();
		for (CompetitionInsuranceDTO cid : insurances) {
			List<CompetitionInsuranceDTO> list = map.get(cid.getCompetitionId());
			if (null == list) {
				list = new ArrayList<>();
				map.put(cid.getCompetitionId(), list);
			}
			list.add(cid);
		}
		for (Iterator<Long> iterator = map.keySet().iterator(); iterator.hasNext();) {
			Long competitionId = iterator.next();
			CompetitionDTO competition = this.competitionFacade.getCompetition(competitionId);
			if (null != competition && competition.getStatus() == 2) {
				List<CompetitionInsuranceDTO> list = map.get(competitionId);
				InsuranceResult result = InsuranceUtil.insurance(competition.getCompetitionTime(), list);
				if (null != result) {
					if ("00".equals(result.getRtnCode())) {
						InsuranceOrderDTO order = new InsuranceOrderDTO();
						order.setCompetitionId(competition.getCompetitionId());
						order.setCreateTime(new Date());
						order.setOrderGroupId(result.getOrderGroupId());
						Long ioId = this.competitionFacade.saveInsuranceOrder(order);
						List<InsuranceOrderDetailDTO> detailDTOs = new ArrayList<>();
						List<OrderDetail> ods = result.getOrderIdList();
						for (OrderDetail orderDetail : ods) {
							InsuranceOrderDetailDTO detailDTO = new InsuranceOrderDetailDTO();
							detailDTO.setInsuredBgnTime(orderDetail.getInsuredBgnTime());
							detailDTO.setInsuredEndTime(orderDetail.getInsuredEndTime());
							detailDTO.setOrderId(orderDetail.getOrderId());
							detailDTO.setIoId(ioId);
							detailDTOs.add(detailDTO);
						}
						competitionFacade.batchSaveInsuranceOrderDetail(detailDTOs);
						for (CompetitionInsuranceDTO cid : list) {
							cid.setStatus(2);
						}
						this.competitionFacade.batchUpdateCompetitionInsurance(list);
					}
				}
			}
		}
	}
	
	/**
	 * 秒级 任务
	 * @author liuhua
	 *
	 */
	@Scheduled(cron = "0 * * * * ?")
	public void taskSeconds(){
		//取消比赛之前通知
		taskCancelCompetitionBefore();
		//取消比赛，用户自己发布的比赛
		taskCancelCompetition();
		//取消比赛，球场管理员发布的比赛
		taskCancelCompetitionByCourtMgr();
		//比赛结束后释放场地
		taskReleaseCourt();
	}

	private void taskReleaseCourt() {
		System.out.println(ParamHandler.getDateStringOfNow("yyyy-MM-dd HH:mm:ss") + " 执行了[比赛结束后释放场地]任务。");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -2);
		CompetitionDTO competition = new CompetitionDTO();
		competition.setEndTime(calendar.getTime());
		competition.setStatus(2);
		List<CompetitionDTO> competitions = competitionFacade.getCompetitionList(competition);
		List<ZoneUseDetailDTO> detailDTOs = new ArrayList<>();
		for (CompetitionDTO competitionDTO : competitions) {
			competitionDTO.setStatus(6);
			ZoneUseDetailDTO zoneUseDetailDTO = new ZoneUseDetailDTO();
			zoneUseDetailDTO.setCompetitionId(competitionDTO.getCompetitionId());
			zoneUseDetailDTO = this.courtFacade.getZoneUseDetail(zoneUseDetailDTO);
			if (null != zoneUseDetailDTO && zoneUseDetailDTO.getStatus() == 1) {
				zoneUseDetailDTO.setStatus(3);
				detailDTOs.add(zoneUseDetailDTO);
			}
		}
		this.competitionFacade.batchUpdateCompetition(competitions);
		this.courtFacade.batchUpdateZoneUseDetail(detailDTOs);
	}

	/**
	 * 自动取消比赛 前 72+1小时
	 * @author liuhua
	 *
	 */
	public void taskCancelCompetitionBefore(){
		System.out.println(ParamHandler.getDateStringOfNow("yyyy-MM-dd HH:mm:ss") + " 执行了[自动取消比赛前72+1小时]任务。");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 3);
		CompetitionDTO competition = new CompetitionDTO();
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.SECOND, 0);
		competition.setBeginTime(calendar.getTime());
		calendar.set(Calendar.SECOND, 59);
		competition.setEndTime(calendar.getTime());
		competition.setStatus(1);
		competition.setQj(0);
		List<CompetitionDTO> competitions = competitionFacade.getCompetitionList(competition);
		for (CompetitionDTO competitionDTO : competitions) {
			CourtDTO courtDTO = courtFacade.getCourt(competitionDTO.getCourtId());
			//通知给相关的球队
			int open = competitionDTO.getOpen();
			UserInfoDTO userInfoDTO = new UserInfoDTO();
			userInfoDTO.setAreaId(courtDTO.getAreaId());
			if (open == 1) { //公开，所有的球队
				userInfoDTO.setIdentity(UserIdentity.teamManager.getIdentity());
			}else{
				userInfoDTO.setIdentity(UserIdentity.teamManager.getIdentity());
				int noOpenType = competitionDTO.getNoOpenType();
				if (noOpenType == 1) {
					String teamIds = competitionDTO.getTypeValue();
					userInfoDTO.setTeamIds(teamIds);
				}else{
					String businessIds = competitionDTO.getTypeValue();
					userInfoDTO.setBusinessIds(businessIds);
				}
			}
			List<UserInfoDTO> userInfos = this.systemFacade.getUserInfoList(userInfoDTO);
			String key = "game_type_1_" + competitionDTO.getCompetitionType();
			push(userInfos, key, courtDTO, competitionDTO);
		}
	}
	
	private void push(List<UserInfoDTO> userInfos, String key, CourtDTO courtDTO, CompetitionDTO competitionDTO){
		System.out.println("competitionId: " + competitionDTO.getCompetitionId());
		String mobiles = "";
		List<Long> userIds1 = new ArrayList<>();
		List<String> clientids1 = new ArrayList<>();
		List<Long> userIds2 = new ArrayList<>();
		List<String> clientids2 = new ArrayList<>();
		for (UserInfoDTO userInfoDTO : userInfos) {
			if (userInfoDTO.getUserId().longValue() == competitionDTO.getInitiateUserId()) {
				continue;
			}
			if (null != userInfoDTO.getSourceFrom()) {
				if (userInfoDTO.getSourceFrom() == 1) {
					userIds1.add(userInfoDTO.getUserId());
					clientids1.add(userInfoDTO.getClientid());
				}else{
					userIds2.add(userInfoDTO.getUserId());
					clientids2.add(userInfoDTO.getClientid());
				}
			}
			mobiles += "," + userInfoDTO.getMobile();
		}
		PushInfo pushInfo = CommonConstants.smsNotes.get(key);
		String timeStr = ParamHandler.getDateString(competitionDTO.getCompetitionTime(), "M月d日 H点m分");
		if (timeStr.endsWith("点0分")) {
			timeStr = timeStr.substring(0, timeStr.length() - 2);
		}
		pushInfo.fillContent(competitionDTO.getInitiatorTeamName(), timeStr, courtDTO.getAddress(), competitionDTO.getCompetitionId());
		pushInfo.fillTemplateParam(competitionDTO.getInitiatorTeamName(), courtDTO.getCourtName(), timeStr);
		pushInfo.setMethod("competitionDetail");
		pushInfo.setKey("competitionId");
		pushInfo.setObjectValue(competitionDTO.getCompetitionId());
		pushInfo.setMobiles(mobiles.substring(1));
		pushInfo.setSendNote(true);
		pushInfo.setSendMsg(false);
		if (null != userIds1 && userIds1.size() > 0) {
			pushInfo.setSendMsg(true);
			pushInfo.setUserId(userIds1);
			pushInfo.setSourceFrom(1);
			pushInfo.setClientIds(clientids1);
		}
		this.writeMessage(pushInfo);
		
		if (null != userIds2 && userIds2.size() > 0) {
			pushInfo.setSendNote(false);
			pushInfo.setSendMsg(true);
			pushInfo.setUserId(userIds2);
			pushInfo.setSourceFrom(2);
			pushInfo.setClientIds(clientids2);
			this.writeMessage(pushInfo);
		}
	}
	
	private byte[] lock = new byte[0];
	
	public void taskCancelCompetitionByCourtMgr(){
		System.out.println(ParamHandler.getDateStringOfNow("yyyy-MM-dd HH:mm:ss") + " 执行了[自动取消球场发布的比赛]任务。");
		Calendar calendar = Calendar.getInstance();
		
		CompetitionDTO competitionDTO = new CompetitionDTO();
		competitionDTO.setEndTime(calendar.getTime());
		competitionDTO.setStatus(1);
		competitionDTO.setQj(1);
		List<CompetitionDTO> competitions = competitionFacade.getCompetitionList(competitionDTO);
		for (CompetitionDTO competitionDTO2 : competitions) {
			Long competitionId = competitionDTO2.getCompetitionId();
			boolean refund = false;
//			if (null != competitionDTO2.getType() && competitionDTO2.getType() == 2) { //预定场
//				Date competitionTime = competitionDTO2.getCompetitionTime();
//				Calendar calendar = Calendar.getInstance();
//				calendar.add(Calendar.DAY_OF_MONTH, 3);
//				Date afterThreeDays = calendar.getTime();
//				if (afterThreeDays.after(competitionTime)) { //可以取消，但不会退款
//					refund = false;
//				}
//			}
			synchronized (lock) {
				competitionDTO2 = this.competitionFacade.getCompetition(competitionId);
				if (competitionDTO2.getStatus().intValue() == 1) {
					competitionDTO2.setStatus(5);
					this.competitionFacade.updateCompetition(competitionDTO2);
					ZoneUseDetailDTO zoneUseDetailDTO = new ZoneUseDetailDTO();
					zoneUseDetailDTO.setZoneId(competitionDTO2.getZoneId());
					zoneUseDetailDTO.setCompetitionId(competitionId);
					zoneUseDetailDTO.setStatus(1);
					zoneUseDetailDTO = this.courtFacade.getZoneUseDetail(zoneUseDetailDTO);
					if (null != zoneUseDetailDTO && null != zoneUseDetailDTO.getId()) {
						zoneUseDetailDTO.setStatus(3);
						this.courtFacade.updateZoneUseDetail(zoneUseDetailDTO);
					}
				}
			}
			CourtDTO courtDTO = this.courtFacade.getCourt(competitionDTO2.getCourtId());
			pushCourtMgr(courtDTO, competitionDTO2, "to_courtMgr_cancel");
			if (refund) {
				UseServiceDetailDTO useServiceDetailDTO = new UseServiceDetailDTO();
				useServiceDetailDTO.setUserId(competitionDTO2.getInitiateUserId());
				useServiceDetailDTO.setSubjectId(competitionId);
				List<UseServiceDetailDTO> used = this.packageFacade.getUseServiceDetailList(useServiceDetailDTO);
				List<UseServiceDetailDTO> temp = new ArrayList<>();
				for (UseServiceDetailDTO useServiceDetailDTO2 : used) {
					if (useServiceDetailDTO2.getNum() > 0) {
						UseServiceDetailDTO t = new UseServiceDetailDTO();
						t.setCreateTime(new Date());
						t.setDescrition("取消赛事");
						t.setNum(useServiceDetailDTO2.getNum() * -1);
						t.setServiceId(useServiceDetailDTO2.getServiceId());
						t.setSubjectId(useServiceDetailDTO2.getSubjectId());
						t.setTeamId(useServiceDetailDTO2.getTeamId());
						t.setUserId(competitionDTO2.getInitiateUserId());
						temp.add(t);
					}
				}
				this.packageFacade.batchSaveUseServiceDetail(temp);
				UsedCouponDetailDTO usedCouponDetailDTO = new UsedCouponDetailDTO();
				usedCouponDetailDTO.setTeamId(competitionDTO.getInitiatorTeamId());
				usedCouponDetailDTO.setSubjectId(competitionId);
				usedCouponDetailDTO.setCouponId(CouponIds.insurance_ticket_z.getCouponId());
				usedCouponDetailDTO = this.couponFacade.getUsedCouponDetail(usedCouponDetailDTO);
				if (null != usedCouponDetailDTO && null != usedCouponDetailDTO.getId()) {
					UsedCouponDetailDTO usedCouponDetailDTO2 = new UsedCouponDetailDTO();
					CglibBeanUtils.copy(usedCouponDetailDTO, usedCouponDetailDTO2);
					usedCouponDetailDTO2.setNum(-1);
					usedCouponDetailDTO2.setId(null);
					usedCouponDetailDTO2.setUseTime(new Date());
					this.couponFacade.saveUsedCouponDetail(usedCouponDetailDTO2);
				}
			}
		}
	}
	public void taskCancelCompetition(){
		System.out.println(ParamHandler.getDateStringOfNow("yyyy-MM-dd HH:mm:ss") + " 执行了[自动取消比赛]任务。");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 3);
		
		CompetitionDTO competitionDTO = new CompetitionDTO();
		competitionDTO.setEndTime(calendar.getTime());
		competitionDTO.setStatus(1);
		competitionDTO.setQj(0);
		List<CompetitionDTO> competitions = competitionFacade.getCompetitionList(competitionDTO);
		for (CompetitionDTO competitionDTO2 : competitions) {
			Long competitionId = competitionDTO2.getCompetitionId();
			boolean refund = false;
//			if (null != competitionDTO2.getType() && competitionDTO2.getType() == 2) { //预定场
//				Date competitionTime = competitionDTO2.getCompetitionTime();
//				Calendar calendar = Calendar.getInstance();
//				calendar.add(Calendar.DAY_OF_MONTH, 3);
//				Date afterThreeDays = calendar.getTime();
//				if (afterThreeDays.after(competitionTime)) { //可以取消，但不会退款
//					refund = false;
//				}
//			}
			synchronized (lock) {
				competitionDTO2 = this.competitionFacade.getCompetition(competitionId);
				if (competitionDTO2.getStatus().intValue() == 1) {
					competitionDTO2.setStatus(5);
					this.competitionFacade.updateCompetition(competitionDTO2);
					ZoneUseDetailDTO zoneUseDetailDTO = new ZoneUseDetailDTO();
					zoneUseDetailDTO.setZoneId(competitionDTO2.getZoneId());
					zoneUseDetailDTO.setCompetitionId(competitionId);
					zoneUseDetailDTO.setStatus(1);
					zoneUseDetailDTO = this.courtFacade.getZoneUseDetail(zoneUseDetailDTO);
					if (null != zoneUseDetailDTO && null != zoneUseDetailDTO.getId()) {
						zoneUseDetailDTO.setStatus(3);
						this.courtFacade.updateZoneUseDetail(zoneUseDetailDTO);
					}
				}
			}
			CourtDTO courtDTO = this.courtFacade.getCourt(competitionDTO2.getCourtId());
			pushCourtMgr(courtDTO, competitionDTO2, "to_courtMgr_cancel");
			if (refund) {
				UseServiceDetailDTO useServiceDetailDTO = new UseServiceDetailDTO();
				useServiceDetailDTO.setUserId(competitionDTO2.getInitiateUserId());
				useServiceDetailDTO.setSubjectId(competitionId);
				List<UseServiceDetailDTO> used = this.packageFacade.getUseServiceDetailList(useServiceDetailDTO);
				List<UseServiceDetailDTO> temp = new ArrayList<>();
				for (UseServiceDetailDTO useServiceDetailDTO2 : used) {
					if (useServiceDetailDTO2.getNum() > 0) {
						UseServiceDetailDTO t = new UseServiceDetailDTO();
						t.setCreateTime(new Date());
						t.setDescrition("取消赛事");
						t.setNum(useServiceDetailDTO2.getNum() * -1);
						t.setServiceId(useServiceDetailDTO2.getServiceId());
						t.setSubjectId(useServiceDetailDTO2.getSubjectId());
						t.setTeamId(useServiceDetailDTO2.getTeamId());
						t.setUserId(competitionDTO2.getInitiateUserId());
						temp.add(t);
					}
				}
				this.packageFacade.batchSaveUseServiceDetail(temp);
				UsedCouponDetailDTO usedCouponDetailDTO = new UsedCouponDetailDTO();
				usedCouponDetailDTO.setTeamId(competitionDTO.getInitiatorTeamId());
				usedCouponDetailDTO.setSubjectId(competitionId);
				usedCouponDetailDTO.setCouponId(CouponIds.insurance_ticket_z.getCouponId());
				usedCouponDetailDTO = this.couponFacade.getUsedCouponDetail(usedCouponDetailDTO);
				if (null != usedCouponDetailDTO && null != usedCouponDetailDTO.getId()) {
					UsedCouponDetailDTO usedCouponDetailDTO2 = new UsedCouponDetailDTO();
					CglibBeanUtils.copy(usedCouponDetailDTO, usedCouponDetailDTO2);
					usedCouponDetailDTO2.setNum(-1);
					usedCouponDetailDTO2.setId(null);
					usedCouponDetailDTO2.setUseTime(new Date());
					this.couponFacade.saveUsedCouponDetail(usedCouponDetailDTO2);
				}
			}
		}
	}
	
	private void pushCourtMgr(CourtDTO courtDTO, CompetitionDTO competitionDTO, String key) {
		UserInfoDTO courtUser = systemFacade.getUserInfo(courtDTO.getUserId());
		PushInfo pushInfo = CommonConstants.smsNotes.get(key);
		String timeStr = ParamHandler.getDateString(competitionDTO.getCompetitionTime(), "M月d日 H点m分");
		if (timeStr.endsWith("点0分")) {
			timeStr = timeStr.substring(0, timeStr.length() - 2);
		}
		pushInfo.fillContent(competitionDTO.getInitiatorTeamName(), timeStr, ParamHandler.getDateString(competitionDTO.getCompetitionTime(), "yyyy-MM-dd HH:mm:ss"));
		pushInfo.fillTemplateParam(competitionDTO.getInitiatorTeamName(), timeStr);
		pushInfo.setObjectValue(ParamHandler.getDateString(competitionDTO.getCompetitionTime(), "yyyy-MM-dd HH:mm:ss"));
		pushInfo.setSendMsg(true);
		pushInfo.setSendNote(true);
		pushInfo.setUserId(courtUser.getUserId());
		pushInfo.setMobiles(courtUser.getMobile());
		pushInfo.setSourceFrom(courtUser.getSourceFrom());
		pushInfo.setClientIds(courtUser.getClientid());
		this.writeMessage(pushInfo);
	}
}
