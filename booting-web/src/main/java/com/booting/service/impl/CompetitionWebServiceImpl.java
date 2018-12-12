/**create by liuhua at 2017年7月15日 下午4:14:13**/
package com.booting.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.common.CommonConstants;
import com.booting.common.CommonConstants.CouponIds;
import com.booting.common.CommonConstants.UserIdentity;
import com.booting.common.CommonConstants.UserService;
import com.booting.common.PushInfo;
import com.booting.competition.dto.CompetitionDTO;
import com.booting.competition.dto.CompetitionInsuranceDTO;
import com.booting.competition.dto.CompetitionScoreDTO;
import com.booting.competition.dto.InsuranceOrderDTO;
import com.booting.competition.dto.InsuranceOrderDetailDTO;
import com.booting.competition.facade.CompetitionFacade;
import com.booting.coupon.dto.UsedCouponDetailDTO;
import com.booting.coupon.dto.UserCouponDTO;
import com.booting.coupon.facade.CouponFacade;
import com.booting.court.dto.CourtDTO;
import com.booting.court.dto.SiteDTO;
import com.booting.court.dto.ZoneDTO;
import com.booting.court.dto.ZoneUseDetailDTO;
import com.booting.insurance.InsuranceResult;
import com.booting.insurance.InsuranceUtil;
import com.booting.insurance.OrderDetail;
import com.booting.pkg.dto.UseServiceDetailDTO;
import com.booting.pkg.facade.PackageFacade;
import com.booting.pub.dto.CompanyDTO;
import com.booting.pub.facade.CommonFacade;
import com.booting.service.CompetitionWebService;
import com.booting.system.dto.UserInfoDTO;
import com.booting.system.facade.SystemFacade;
import com.booting.team.dto.TeamDTO;
import com.booting.team.dto.TeamHabitCourtDTO;
import com.booting.team.dto.TeamHabitNumberDTO;
import com.booting.team.dto.TeamHabitTimeDTO;
import com.booting.team.facade.TeamFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.PageInfo;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.utils.CglibBeanUtils;

@Service("competitionWebService")
public class CompetitionWebServiceImpl extends BaseWebService implements CompetitionWebService {

	@Autowired
	private CompetitionFacade competitionFacade;
	@Autowired
	private SystemFacade systemFacade;
	@Autowired
	private TeamFacade teamFacade;
	@Autowired
	private PackageFacade packageFacade;
	@Autowired
	private CommonFacade commonFacade;
	@Autowired
	private CouponFacade couponFacade;

	private byte[] lock = new byte[0];

	@Override
	public CompetitionDTO checkCompetition(CompetitionDTO competitionDTO) throws ArgsException {
		UserInfoDTO userInfo = systemFacade.getUserInfo(competitionDTO.getInitiateUserId());
		if (null == userInfo || (userInfo.getIdentity().intValue() != UserIdentity.teamManager.getIdentity() && userInfo.getIdentity().intValue() != UserIdentity.teamManager2.getIdentity())) {
			throw new ArgsException("201", "球队管理员或副管理员才可以创建赛事");
		}
		Long teamId = getTeamIdByUserId(competitionDTO.getInitiateUserId());
		if (null == teamId) {
			throw new ArgsException("202", "没有找到球队");
		}
		TeamDTO teamDTO = teamFacade.getTeam(teamId);
		if (null == teamDTO) {
			throw new ArgsException("202", "没有找到球队信息");
		}
		CourtDTO courtDTO = courtFacade.getCourt(competitionDTO.getCourtId());
		if (null == courtDTO) {
			throw new ArgsException("203", "没有找到球场信息");
		}
		List<CompetitionInsuranceDTO> insurances = competitionDTO.getInitiatorInsuranceList();
		if (competitionDTO.getInitiatorBuyInsurance() == 1 && (null == insurances || insurances.size() == 0)) {
			throw new ArgsException("204", "不能为0个人买保险");
		}
		Date competitionTime = competitionDTO.getCompetitionTime();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 3);
		Date afterThreeDays = calendar.getTime();
		if (afterThreeDays.after(competitionTime)) {
			throw new ArgsException("207", "不能添加三天内开始的比赛");
		}
		competitionDTO.setInitiatorTeamId(teamId);
		competitionDTO.setInitiatorMobile(userInfo.getMobile());
		competitionDTO.setInitiatorTeamName(teamDTO.getTeamName());
		competitionDTO.setCourtName(courtDTO.getCourtName());
		competitionDTO.setCourtAddr(courtDTO.getAddress());
		int ticketNum = getTicketNum(competitionDTO.getType(), competitionDTO.getCompetitionType(), competitionDTO.getCompetitionFormat(), true);
		competitionDTO.setInitiatorPayMoney(ticketNum);
		if (competitionDTO.getInitiatorBuyInsurance() == 1) {
			List<UserInfoDTO> users = new ArrayList<>();
			for (CompetitionInsuranceDTO competitionInsuranceDTO : insurances) {
				competitionInsuranceDTO.setTeamId(teamId);
				competitionInsuranceDTO.setTeamName(teamDTO.getTeamName());
				UserInfoDTO user = systemFacade.getUserInfo(competitionInsuranceDTO.getUserId());
				if (null == user) {
					throw new ArgsException("205", "用户信息不存在");
				}
				competitionInsuranceDTO.setUserName(user.getName());
				user.setIdentityNo(competitionInsuranceDTO.getIdentityNo());
				users.add(user);
			}
		}
		
		CompetitionDTO competitionDTO2 = new CompetitionDTO();
		competitionDTO2.setTeamId(competitionDTO.getInitiateUserId());
		competitionDTO2.setStatus(1);
		competitionDTO2.setCompetitionType(3);
		competitionDTO2 = competitionFacade.getCompetition(competitionDTO2);
		if (null != competitionDTO2 && null != competitionDTO2.getCompetitionId()) {
			throw new ArgsException("214", "你已参与了一场挑战赛");
		}
		competitionDTO2 = new CompetitionDTO();
		competitionDTO2.setInitiateUserId(competitionDTO.getInitiateUserId());
		competitionDTO2.setStatus(1);
		competitionDTO2 = competitionFacade.getCompetition(competitionDTO2);
		if (null != competitionDTO2 && null != competitionDTO2.getCompetitionId()) {
			throw new ArgsException("206", "你有一场比赛已经发起，请等待对手挑战或取消比赛再创建");
		}
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(competitionDTO.getCompetitionTime());
		calendar2.add(Calendar.HOUR_OF_DAY, 2);
		competitionDTO2 = new CompetitionDTO();
		competitionDTO2.setTeamId(competitionDTO.getInitiatorTeamId());
		competitionDTO2.setBeginTime(competitionDTO.getCompetitionTime());
		competitionDTO2.setEndTime(calendar2.getTime());
		competitionDTO2.setStatus(2);
		competitionDTO2 = competitionFacade.getCompetition(competitionDTO2);
		if (null != competitionDTO2 && null != competitionDTO2.getCompetitionId()) {
			throw new ArgsException("211", "你有一场比赛的比赛时间与本次的比赛时间冲突");
		}
//		if (!isUsable(competitionDTO.getCourtId(), competitionDTO.getCompetitionFormat(), competitionDTO.getCompetitionTime())) {
//			throw new ArgsException("210", "场地已被使用，请重新选择");
//		}
		ZoneDTO zoneDTO = getUsableZone(competitionDTO.getZoneId(), competitionDTO.getCourtId(), competitionDTO.getCompetitionFormat(), competitionDTO.getCompetitionTime());
		competitionDTO.setZoneId(zoneDTO.getZoneId());
		competitionDTO.setZoneName(zoneDTO.getZoneName());
		checkUploadScore(teamId);
		return competitionDTO;
	}

	private void checkUploadScore(Long teamId) throws ArgsException {
		CompetitionDTO cd = new CompetitionDTO();
		cd.setTeamId(teamId);
		cd.setCompetitionType(3);
		cd.setStatus(2);
		cd.setEndTime(new Date());
		CompetitionDTO cd2 = this.competitionFacade.getCompetition(cd);
		if (null != cd2 && null != cd2.getCompetitionId()) {
			CompetitionScoreDTO competitionScoreDTO = new CompetitionScoreDTO();
			competitionScoreDTO.setTeamId(teamId);
			competitionScoreDTO.setCompetitionId(cd2.getCompetitionId());
			List<CompetitionScoreDTO> list = this.competitionFacade.getCompetitionScoreList(competitionScoreDTO);
			if (null == list || list.isEmpty()) {
				throw new ArgsException("213", "你上一场比赛的成绩还未上传");
			}
		}
	}

	@Override
	public Long enterCompetition(CompetitionDTO competitionDTO) throws ArgsException {
		UserInfoDTO userInfo = systemFacade.getUserInfo(competitionDTO.getInitiateUserId());
		if (null == userInfo || (userInfo.getIdentity().intValue() != UserIdentity.teamManager.getIdentity() && userInfo.getIdentity().intValue() != UserIdentity.teamManager2.getIdentity())) {
			throw new ArgsException("201", "球队管理员或副管理员才可以创建赛事");
		}
		Long teamId = getTeamIdByUserId(competitionDTO.getInitiateUserId());
		if (null == teamId) {
			throw new ArgsException("202", "没有找到球队");
		}
		TeamDTO teamDTO = teamFacade.getTeam(teamId);
		if (null == teamDTO) {
			throw new ArgsException("202", "没有找到球队信息");
		}
		CourtDTO courtDTO = courtFacade.getCourt(competitionDTO.getCourtId());
		if (null == courtDTO) {
			throw new ArgsException("203", "没有找到球场信息");
		}
		if (competitionDTO.getInitiatorBuyInsurance() == 1 && (null == competitionDTO.getInitiatorInsuranceList() || competitionDTO.getInitiatorInsuranceList().size() == 0)) {
			throw new ArgsException("204", "不能为0个人买保险");
		}
		CompetitionDTO competitionDTO2 = new CompetitionDTO();
		competitionDTO2.setInitiateUserId(competitionDTO.getInitiateUserId());
		competitionDTO2.setStatus(1);
		competitionDTO2 = competitionFacade.getCompetition(competitionDTO2);
		if (null != competitionDTO2 && null != competitionDTO2.getCompetitionId()) {
			throw new ArgsException("206", "你有一场比赛已经发起，请等待对手挑战或取消比赛再创建");
		}
//		if (competitionDTO.getType() == 2) { // 预定场
//			Date competitionTime = competitionDTO.getCompetitionTime();
//			Calendar calendar = Calendar.getInstance();
//			calendar.add(Calendar.DAY_OF_MONTH, 3);
//			Date afterThreeDays = calendar.getTime();
//			if (afterThreeDays.after(competitionTime)) {
//				throw new ArgsException("207", "三天内开始的比赛不能选择预定场");
//			}
//		}
		Date competitionTime = competitionDTO.getCompetitionTime();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 3);
		Date afterThreeDays = calendar.getTime();
		if (afterThreeDays.after(competitionTime)) {
			throw new ArgsException("207", "不能添加三天内开始的比赛");
		}
		competitionDTO.setInitiatorTeamId(teamId);
		competitionDTO.setInitiatorTeamName(teamDTO.getTeamName());
		competitionDTO.setInitiatorMobile(userInfo.getMobile());
		competitionDTO.setCourtName(courtDTO.getCourtName());
		competitionDTO.setCourtAddr(courtDTO.getAddress());
		competitionDTO.setStatus(1);
		int ticketNum = getTicketNum(competitionDTO.getType(), competitionDTO.getCompetitionType(), competitionDTO.getCompetitionFormat(), true);
		if (ticketNum > 0) {
			int allTicketNum = this.packageFacade.getServiceNumOfTeam(competitionDTO.getInitiatorTeamId(), UserService.ball_ticket.getServiceId());
			int usedTicketNum = this.packageFacade.getUsedServiceNumOfTeam(competitionDTO.getInitiatorTeamId(), UserService.ball_ticket.getServiceId());
			// System.out.println(competitionDTO.getInitiatorTeamId() + "
			// 的总约球券:" + allTicketNum + ", 已用：" + usedTicketNum);
			if (allTicketNum < usedTicketNum + ticketNum) {
				throw new ArgsException("208", "约球券不够");
			}
		}
		if (null != competitionDTO.getInitiatorTeach() && competitionDTO.getInitiatorTeach() == 1) {
			int allTicketNum = this.packageFacade.getServiceNumOfTeam(competitionDTO.getInitiatorTeamId(), UserService.coach_ticket.getServiceId());
			int usedTicketNum = this.packageFacade.getUsedServiceNumOfTeam(competitionDTO.getInitiatorTeamId(), UserService.coach_ticket.getServiceId());
			// System.out.println(competitionDTO.getInitiatorTeamId() + "
			// 的总教练券:" + allTicketNum + ", 已用：" + usedTicketNum);
			if (allTicketNum < usedTicketNum + 1) {
				throw new ArgsException("210", "教练券不够");
			}
		}
		competitionDTO.setInitiatorPayMoney(ticketNum);
		List<CompetitionInsuranceDTO> insurances = competitionDTO.getInitiatorInsuranceList();
		List<UserInfoDTO> users = new ArrayList<>();
		// 是否使用了赠送保险券
		boolean zcoupon = false;
		// 需要使用保险券的数量
		int couponNum = 0;
		UserCouponDTO userCouponDTO = null;
		if (null != insurances && insurances.size() > 0) {
			for (CompetitionInsuranceDTO competitionInsuranceDTO : insurances) {
				competitionInsuranceDTO.setTeamId(teamId);
				competitionInsuranceDTO.setTeamName(teamDTO.getTeamName());
				UserInfoDTO user = systemFacade.getUserInfo(competitionInsuranceDTO.getUserId());
				if (null == user) {
					throw new ArgsException("205", "用户信息不存在");
				}
				competitionInsuranceDTO.setUserName(user.getName());
				competitionInsuranceDTO.setCreateTime(new Date());
				competitionInsuranceDTO.setStatus(1);
				user.setIdentityNo(competitionInsuranceDTO.getIdentityNo());
				users.add(user);
			}
			couponNum = users.size();
			userCouponDTO = new UserCouponDTO();
			userCouponDTO.setCouponId(CouponIds.insurance_ticket_z.getCouponId());
			userCouponDTO.setStatus(1);
			userCouponDTO.setTeamId(teamId);
			userCouponDTO = this.couponFacade.getUserCoupon(userCouponDTO);
			if (null != userCouponDTO && null != userCouponDTO.getId()) {
				int usedNum = this.couponFacade.getUsedCouponCountOfTeam(teamId, CouponIds.insurance_ticket_z.getCouponId());
				int usableNum = userCouponDTO.getQuantity() - usedNum;
				if (usableNum > 0) {
					zcoupon = true;
					if (couponNum >= 10) {
						couponNum = couponNum - 10;
					}else{
						couponNum = 0;
					}
				}
			}
			if (couponNum > 0) {
				int allInsuranceNum = this.packageFacade.getServiceNumOfTeam(competitionDTO.getInitiatorTeamId(), UserService.insurance_ticket.getServiceId());
				int usedInsuranceNum = this.packageFacade.getUsedServiceNumOfTeam(competitionDTO.getInitiatorTeamId(), UserService.insurance_ticket.getServiceId());
				if (allInsuranceNum < usedInsuranceNum + couponNum) {
					// System.out.println(competitionDTO.getInitiatorTeamId() +
					// " 的总保险券:" + allInsuranceNum + ", 已用：" +
					// usedInsuranceNum);
					throw new ArgsException("209", "保险券不够");
				}
			}
		}
		Long competitionId = null;
		synchronized (lock) {
//			if (!isUsable(competitionDTO.getCourtId(), competitionDTO.getCompetitionFormat(), competitionDTO.getCompetitionTime())) {
//				throw new ArgsException("210", "场地已被使用，请重新选择");
//			}
			ZoneDTO zoneDTO = getUsableZone(competitionDTO.getZoneId(), competitionDTO.getCourtId(), competitionDTO.getCompetitionFormat(), competitionDTO.getCompetitionTime());
			competitionDTO.setZoneId(zoneDTO.getZoneId());
			competitionDTO.setZoneName(zoneDTO.getZoneName());
			competitionDTO.setCreateTime(new Date());
			competitionDTO.setRemindTimes(1);
			competitionId = this.competitionFacade.saveCompetition(competitionDTO);
			competitionDTO.setCompetitionId(competitionId);
			
			ZoneUseDetailDTO zoneUseDetailDTO = new ZoneUseDetailDTO();
			zoneUseDetailDTO.setBeginTime(competitionDTO.getCompetitionTime());
			zoneUseDetailDTO.setBeginMinute(ParamHandler.getMinute(competitionDTO.getCompetitionTime()));
			zoneUseDetailDTO.setCompetitionFormat(competitionDTO.getCompetitionFormat());
			zoneUseDetailDTO.setCompetitionId(competitionId);
			zoneUseDetailDTO.setCourtId(competitionDTO.getCourtId());
			zoneUseDetailDTO.setCreateTime(new Date());
			zoneUseDetailDTO.setEndTime(ParamHandler.getNewDate(competitionDTO.getCompetitionTime(), Calendar.HOUR_OF_DAY, 2));
			zoneUseDetailDTO.setEndMinute(ParamHandler.getMinute(zoneUseDetailDTO.getEndTime()));
			zoneUseDetailDTO.setModifyTime(zoneUseDetailDTO.getCreateTime());
			zoneUseDetailDTO.setSiteId(zoneDTO.getSiteId());
			zoneUseDetailDTO.setStatus(1);
			zoneUseDetailDTO.setTeamId(competitionDTO.getInitiatorTeamId());
			zoneUseDetailDTO.setUserId(competitionDTO.getInitiateUserId());
			zoneUseDetailDTO.setZoneId(zoneDTO.getZoneId());
			this.courtFacade.saveZoneUseDetail(zoneUseDetailDTO);
		}
		if (null != insurances && insurances.size() > 0) {
			for (CompetitionInsuranceDTO competitionInsuranceDTO : insurances) {
				competitionInsuranceDTO.setCompetitionId(competitionId);
			}
			this.competitionFacade.batchSaveCompetitionInsurance(insurances);
			this.systemFacade.batchUpdateUserInfo(users);
		}
		List<UseServiceDetailDTO> details = new ArrayList<>();
		UseServiceDetailDTO useServiceDetailDTO = new UseServiceDetailDTO();
		useServiceDetailDTO.setCreateTime(new Date());
		useServiceDetailDTO.setDescrition("发起赛事");
		useServiceDetailDTO.setNum(ticketNum);
		useServiceDetailDTO.setServiceId(UserService.ball_ticket.getServiceId());
		useServiceDetailDTO.setSubjectId(competitionId);
		useServiceDetailDTO.setUserId(competitionDTO.getInitiateUserId());
		useServiceDetailDTO.setTeamId(teamId);
		details.add(useServiceDetailDTO);
		if (couponNum > 0) {
			UseServiceDetailDTO useServiceDetailDTO2 = new UseServiceDetailDTO();
			useServiceDetailDTO2.setCreateTime(new Date());
			useServiceDetailDTO2.setDescrition("赛事保险");
			useServiceDetailDTO2.setNum(couponNum);
			useServiceDetailDTO2.setServiceId(UserService.insurance_ticket.getServiceId());
			useServiceDetailDTO2.setSubjectId(competitionId);
			useServiceDetailDTO2.setUserId(competitionDTO.getInitiateUserId());
			useServiceDetailDTO2.setTeamId(teamId);
			details.add(useServiceDetailDTO2);
		}
		if (null != competitionDTO.getInitiatorTeach() && competitionDTO.getInitiatorTeach() == 1) {
			UseServiceDetailDTO useServiceDetailDTO2 = new UseServiceDetailDTO();
			useServiceDetailDTO2.setCreateTime(new Date());
			useServiceDetailDTO2.setDescrition("赛事教练-发起");
			useServiceDetailDTO2.setNum(1);
			useServiceDetailDTO2.setServiceId(UserService.coach_ticket.getServiceId());
			useServiceDetailDTO2.setSubjectId(competitionId);
			useServiceDetailDTO2.setUserId(competitionDTO.getInitiateUserId());
			useServiceDetailDTO2.setTeamId(teamId);
			details.add(useServiceDetailDTO2);
		}
		this.packageFacade.batchSaveUseServiceDetail(details);
		if (zcoupon) {
			UsedCouponDetailDTO usedCouponDetailDTO = new UsedCouponDetailDTO();
			usedCouponDetailDTO.setCouponId(userCouponDTO.getCouponId());
			usedCouponDetailDTO.setSubjectId(competitionId);
			usedCouponDetailDTO.setTeamId(teamId);
			usedCouponDetailDTO.setUserId(competitionDTO.getInitiateUserId());
			usedCouponDetailDTO.setUseTime(new Date());
			usedCouponDetailDTO.setNum(1);
			this.couponFacade.saveUsedCouponDetail(usedCouponDetailDTO);
		}

		// 通知给相关的球队
		int open = competitionDTO.getOpen();
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setAreaId(courtDTO.getAreaId());
		if (open == 1) { // 公开，所有的球队
			userInfoDTO.setIdentity(UserIdentity.teamManager.getIdentity());
		} else {
			userInfoDTO.setIdentity(UserIdentity.teamManager.getIdentity());
			int noOpenType = competitionDTO.getNoOpenType();
			if (noOpenType == 1) {
				String teamIds = competitionDTO.getTypeValue();
				userInfoDTO.setTeamIds(teamIds);
			} else {
				String businessIds = competitionDTO.getTypeValue();
				userInfoDTO.setBusinessIds(businessIds);
			}
		}
		List<UserInfoDTO> userInfos = this.systemFacade.getUserInfoList(userInfoDTO);
		String key = "game_type_0_" + competitionDTO.getCompetitionType();
		push(userInfos, key, courtDTO, competitionDTO, competitionDTO.getInitiateUserId());
		pushCourtMgr(courtDTO, competitionDTO, "to_courtMgr_enterCompetition", ParamHandler.getDateString(competitionDTO.getCompetitionTime(), "yyyy-MM-dd HH:mm:ss"));
		return competitionId;
	}

	private void pushCourtMgr(CourtDTO courtDTO, CompetitionDTO competitionDTO, String key, Object objectValue) {
		UserInfoDTO courtUser = systemFacade.getUserInfo(courtDTO.getUserId());
		PushInfo pushInfo = CommonConstants.smsNotes.get(key);
		SimpleDateFormat sdf = new SimpleDateFormat("M月d日 H点m分");
		String timeStr = sdf.format(competitionDTO.getCompetitionTime());
		if (timeStr.endsWith("点0分")) {
			timeStr = timeStr.substring(0, timeStr.length() - 2);
		}
		pushInfo.fillContent(competitionDTO.getInitiatorTeamName(), timeStr, objectValue);
		pushInfo.fillTemplateParam(competitionDTO.getInitiatorTeamName(), timeStr);
		pushInfo.setObjectValue(objectValue);
		pushInfo.setSendMsg(true);
		pushInfo.setSendNote(true);
		pushInfo.setUserId(courtUser.getUserId());
		pushInfo.setMobiles(courtUser.getMobile());
		pushInfo.setSourceFrom(courtUser.getSourceFrom());
		pushInfo.setClientIds(courtUser.getClientid());
		this.writeMessage(pushInfo);
	}

	private void push(List<UserInfoDTO> userInfos, String key, CourtDTO courtDTO, CompetitionDTO competitionDTO, Long userId) {
		String mobiles = "";
		List<Long> userIds1 = new ArrayList<>();
		List<String> clientids1 = new ArrayList<>();
		List<Long> userIds2 = new ArrayList<>();
		List<String> clientids2 = new ArrayList<>();
		for (UserInfoDTO userInfoDTO : userInfos) {
			if (userInfoDTO.getUserId().longValue() == userId) {
				continue;
			}
			if (null != userInfoDTO.getSourceFrom()) {
				if (userInfoDTO.getSourceFrom() == 1) {
					userIds1.add(userInfoDTO.getUserId());
					clientids1.add(userInfoDTO.getClientid());
				} else {
					userIds2.add(userInfoDTO.getUserId());
					clientids2.add(userInfoDTO.getClientid());
				}
			}
			mobiles += "," + userInfoDTO.getMobile();
		}
		PushInfo pushInfo = CommonConstants.smsNotes.get(key);
		SimpleDateFormat sdf = new SimpleDateFormat("M月d日 H点m分");
		String timeStr = sdf.format(competitionDTO.getCompetitionTime());
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

	/**
	 * 获取比赛应使用券的数量
	 * 
	 * @author liuhua
	 *
	 * @param type
	 *            1锁定场 2预定场
	 * @param competitionType
	 *            1邀请赛 2友谊赛 3挑战赛
	 * @param competitionFormat
	 *            比赛赛制
	 * @param initiator
	 *            是否发起方 true是 false否
	 * @return
	 */
	public int getTicketNum(Integer type, Integer competitionType, Integer competitionFormat, boolean initiator) {
		int ticket = 0;
//		if (type == 1) {
//			ticket = CommonConstants.tickets.get(competitionFormat + "_" + competitionType + "_" + (initiator ? 1 : 0));
//		} else {
			ticket = CommonConstants.tickets2.get(competitionFormat + "_" + competitionType + "_" + (initiator ? 1 : 0));
//		}
		return ticket;
	}

	public ZoneDTO getUsableZone(Long zondeId, Long courtId, Integer competitionFormat, Date competitionTime) throws ArgsException {
		if (null == competitionFormat || null == courtId || null == competitionTime) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> params = new HashMap<>();
		params.put("zondeId", zondeId);
		params.put("courtId", courtId);
		params.put("competitionFormat", competitionFormat);
		params.put("competitionTime", sdf.format(competitionTime));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(competitionTime);
		calendar.add(Calendar.HOUR_OF_DAY, 2);
		params.put("competitionTimeEnd", sdf.format(calendar.getTime()));
		ZoneDTO zoneDTO = this.courtFacade.getUsableZone(params);
		if (null == zoneDTO) {
			throw new ArgsException("210", "没有找到合适的场地或场地已被使用，请重新选择");
		}
		return zoneDTO;
	}
	
	public boolean isUsable(Long courtId, Integer competitionFormat, Date competitionTime) throws ArgsException {
		if (null == competitionFormat || null == courtId || null == competitionTime) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, Object> params = new HashMap<>();
		params.put("courtId", courtId);
		params.put("competitionFormat", competitionFormat);
		params.put("competitionTime", sdf.format(competitionTime));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(competitionTime);
		calendar.add(Calendar.HOUR_OF_DAY, 2);
		params.put("competitionTimeEnd", sdf.format(calendar.getTime()));
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(1);
		queryParam.setPageSize(1);
		queryParam.setParam(params);
		PageList<CourtDTO> courtPageList = this.courtFacade.usableCourts(queryParam);
		return courtPageList.getDataList().size() == 1;
	}

	@Override
	public ApiResult usableCourts(QueryParam queryParam) {
		PageList<CourtDTO> courtPageList = this.courtFacade.usableCourts(queryParam);
		List<CourtDTO> courts = courtPageList.getDataList();
		List<Map<String, Object>> res = new ArrayList<>();
		if (null != courts && !courts.isEmpty()) {
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
	public ApiResult recommendTeams(Long loginUserId, Integer recommend, QueryParam queryParam) throws ArgsException {
		Long teamId = getTeamIdByUserId(loginUserId);
		// TeamDTO teamDTO = new TeamDTO();
		// teamDTO.setUserId(loginUserId);
		// teamDTO = teamFacade.getTeam(teamDTO);
		// if (null == teamDTO || null == teamDTO.getTeamId()) {
		// throw new ArgsException(FailureCode.ERR_002.getCode(),
		// "没有找到登录用户的球队");
		// }
		Map<String, Object> params = new HashMap<>();
		params.put("teamId", teamId);
		params.put("contract", 1);
		// if (null != recommend && recommend > 0) {
		// params.put("queryRecommend", recommend);
		// if (recommend == 1) {
		// params.put("queryConditionCourt",
		// getConditionByHabitCourt(teamDTO.getTeamId()));
		// }else if (recommend == 2) {
		// params.put("queryConditionTime",
		// getConditionByHabitTime(teamDTO.getTeamId()));
		// }else{
		// params.put("queryConditionNumber",
		// getConditionByHabitNumber(teamDTO.getTeamId()));
		// }
		// }
		queryParam.setParam(params);
		PageList<TeamDTO> teamPageList = this.teamFacade.recommendTeams(queryParam);
		List<TeamDTO> data = teamPageList.getDataList();
		ApiResult apiResult = new ApiResult();
		apiResult.setData(data);
		PageInfo pageInfo = new PageInfo(teamPageList.getPageNo(), teamPageList.getPageSize(), teamPageList.getTotalRecord());
		apiResult.setPageInfo(pageInfo);
		return apiResult;
	}

	/*
	 * public String getConditionByHabitCourt(Long teamId){ TeamHabitCourtDTO
	 * teamHabitCourtDTO = new TeamHabitCourtDTO();
	 * teamHabitCourtDTO.setTeamId(teamId); List<TeamHabitCourtDTO> times =
	 * teamFacade.getTeamHabitCourtList(teamHabitCourtDTO); String courtIds =
	 * ""; for (TeamHabitCourtDTO teamHabitCourtDTO2 : times) { courtIds += ","
	 * + teamHabitCourtDTO2.getCourtId(); } if (courtIds.length() > 0) {
	 * courtIds = "(" + courtIds.substring(1) + ")"; } return courtIds; }
	 * 
	 * public String getConditionByHabitTime(Long teamId){ Calendar calendar =
	 * Calendar.getInstance(); calendar.add(Calendar.DAY_OF_WEEK, -1); int week
	 * = calendar.get(Calendar.DAY_OF_WEEK); TeamHabitTimeDTO teamHabitTimeDTO =
	 * new TeamHabitTimeDTO(); teamHabitTimeDTO.setTeamId(teamId);
	 * teamHabitTimeDTO.setWeek(week); List<TeamHabitTimeDTO> times =
	 * teamFacade.getTeamHabitTimeList(teamHabitTimeDTO); String temp = ""; for
	 * (TeamHabitTimeDTO teamHabitTimeDTO2 : times) { temp +=
	 * "or (i.beginMinute = " + teamHabitTimeDTO2.getBeginMinute() +
	 * " and i.endMinute = " + teamHabitTimeDTO2.getEndMinute() + ")"; } if
	 * (temp.length() > 0) { temp = temp.substring(3); } return temp; }
	 * 
	 * public String getConditionByHabitNumber(Long teamId){ TeamHabitNumberDTO
	 * teamHabitNumberDTO = new TeamHabitNumberDTO();
	 * teamHabitNumberDTO.setTeamId(teamId); List<TeamHabitNumberDTO> times =
	 * teamFacade.getTeamHabitNumberList(teamHabitNumberDTO); String temp = "";
	 * for (TeamHabitNumberDTO teamHabitNumberDTO2 : times) { temp += "," +
	 * teamHabitNumberDTO2.getNumber(); } if (temp.length() > 0) { temp = "(" +
	 * temp.substring(1) + ")"; } return temp; }
	 */

	public String getConditionByHabitCourt2(Long courtId) {
		TeamHabitCourtDTO teamHabitCourtDTO = new TeamHabitCourtDTO();
		teamHabitCourtDTO.setCourtId(courtId);
		List<TeamHabitCourtDTO> courts = teamFacade.getTeamHabitCourtList(teamHabitCourtDTO);
		String teamIds = "";
		for (TeamHabitCourtDTO teamHabitCourtDTO2 : courts) {
			teamIds += "," + teamHabitCourtDTO2.getTeamId();
		}
		if (teamIds.length() > 0) {
			teamIds = teamIds.substring(1);
		}
		return teamIds;
	}

	public String getConditionByHabitTime2(String competitionTime, String competitionTimeEnd) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = sdf.parse(competitionTime);
		Date d2 = sdf.parse(competitionTimeEnd);
		Date d11 = sdf.parse(competitionTime.substring(0, 11) + "00:00:00");
		Date d21 = sdf.parse(competitionTimeEnd.substring(0, 11) + "00:00:00");

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_WEEK, -1);
		int week = calendar.get(Calendar.DAY_OF_WEEK);

		TeamHabitTimeDTO teamHabitTimeDTO = new TeamHabitTimeDTO();
		teamHabitTimeDTO.setBeginMinute((int) (d1.getTime() - d11.getTime()));
		teamHabitTimeDTO.setEndMinute((int) (d2.getTime() - d21.getTime()));
		teamHabitTimeDTO.setWeek(week);
		List<TeamHabitTimeDTO> times = teamFacade.getTeamHabitTimeList(teamHabitTimeDTO);
		String teamIds = "";
		for (TeamHabitTimeDTO teamHabitTimeDTO2 : times) {
			teamIds += "," + teamHabitTimeDTO2.getTeamId();
		}
		if (teamIds.length() > 0) {
			teamIds = teamIds.substring(1);
		}
		return teamIds;
	}

	public String getConditionByHabitNumber2(Integer competitionFormat) {
		TeamHabitNumberDTO teamHabitNumberDTO = new TeamHabitNumberDTO();
		teamHabitNumberDTO.setNumber(competitionFormat);
		List<TeamHabitNumberDTO> format = teamFacade.getTeamHabitNumberList(teamHabitNumberDTO);
		String teamIds = "";
		for (TeamHabitNumberDTO teamHabitNumberDTO2 : format) {
			teamIds += "," + teamHabitNumberDTO2.getTeamId();
		}
		if (teamIds.length() > 0) {
			teamIds = teamIds.substring(1);
		}
		return teamIds;
	}

	@Override
	public ApiResult recommendTeams(Long loginUserId, QueryParam queryParam) throws ArgsException, ParseException {
		Long teamId = getTeamIdByUserId(loginUserId);
		if (null == teamId) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "没有找到登录用户的球队");
		}
		Map<String, Object> params = new HashMap<>();
		params.put("teamId", teamId);
		params.put("contract", 1);
		params.put("queryConditionCourt", getConditionByHabitCourt2(queryParam.getParam("courtId")));
		params.put("queryConditionTime", getConditionByHabitTime2(queryParam.getParam("competitionTime"), queryParam.getParam("competitionTimeEnd")));
		params.put("queryConditionNumber", getConditionByHabitNumber2(queryParam.getParam("competitionFormat")));
		queryParam.setParam(params);
		PageList<TeamDTO> teamPageList = this.teamFacade.recommendTeams(queryParam);
		List<TeamDTO> teams = teamPageList.getDataList();
		if (null == teams || teams.isEmpty()) {
			queryParam.getParam().remove("queryConditionCourt");
			teamPageList = this.teamFacade.recommendTeams(queryParam);
			teams = teamPageList.getDataList();
		}
		ApiResult apiResult = new ApiResult();
		apiResult.setData(teams);
		PageInfo pageInfo = new PageInfo(teamPageList.getPageNo(), teamPageList.getPageSize(), teamPageList.getTotalRecord());
		apiResult.setPageInfo(pageInfo);
		return apiResult;
	}

	@Override
	public ApiResult mineCompetitions(Long loginUserId, QueryParam queryParam) throws ArgsException {
		Integer queryType = queryParam.getParam("queryType");
		if (queryType == 4) {
			CourtDTO courtDTO = new CourtDTO();
			courtDTO.setUserId(loginUserId);
			courtDTO.setEnabled(1);
			List<CourtDTO> list = this.courtFacade.getCourtList(courtDTO);
			if (null != list && !list.isEmpty()) {
				String courtIds = "";
				for (CourtDTO courtDTO2 : list) {
					courtIds += "," + courtDTO2.getCourtId();
				}
				courtIds = courtIds.substring(1);
				queryParam.addParam("mineCourtIds", courtIds);
			}
		}else{
			Long teamId = getTeamIdByUserId(loginUserId);
			if (null == teamId) {
				throw new ArgsException(FailureCode.ERR_002.getCode(), "没有球队");
			}
			TeamDTO team = this.teamFacade.getTeam(teamId);
			if (null == team) {
				throw new ArgsException(FailureCode.ERR_002.getCode(), "没有球队");
			}
			Long userId = team.getUserId();
			if (queryType == 1) {
				queryParam.addParam("initiateUserId", userId);
			} else if (queryType == 2) {
				queryParam.addParam("challengerUserId", userId);
			} else {
				queryParam.addParam("loginUserId", userId);
			}
		}
		PageList<CompetitionDTO> competitionPageList = this.competitionFacade.getCompetitionListForPage(queryParam);
		List<CompetitionDTO> list = competitionPageList.getDataList();
		for (CompetitionDTO competitionDTO : list) {
			if (null != competitionDTO.getInitiatorTeamId()) {
				TeamDTO teamDTO = this.teamFacade.getTeam(competitionDTO.getInitiatorTeamId());
				if (null != teamDTO) {
					competitionDTO.setInitiatorTeamLogo(teamDTO.getLogo());
				}
			}
			if (null != competitionDTO.getChallengerTeamId()) {
				TeamDTO teamDTO2 = this.teamFacade.getTeam(competitionDTO.getChallengerTeamId());
				if (null != teamDTO2) {
					competitionDTO.setChallengerTeamLogo(teamDTO2.getLogo());
				}
			}
		}
		ApiResult apiResult = new ApiResult();
		apiResult.setData(list);
		PageInfo pageInfo = new PageInfo(competitionPageList.getPageNo(), competitionPageList.getPageSize(), competitionPageList.getTotalRecord());
		apiResult.setPageInfo(pageInfo);
		return apiResult;
	}

	@Override
	public CompetitionDTO checkAcceptCompetition(CompetitionDTO competitionDTO) throws ArgsException {
		UserInfoDTO userInfo = systemFacade.getUserInfo(competitionDTO.getChallengerUserId());
		if (null == userInfo || (userInfo.getIdentity().intValue() != UserIdentity.teamManager.getIdentity() && userInfo.getIdentity().intValue() != UserIdentity.teamManager2.getIdentity())) {
			throw new ArgsException("201", "球队管理员或副管理员才可以接受赛事");
		}
		Long teamId = getTeamIdByUserId(competitionDTO.getChallengerUserId());
		if (null == teamId) {
			throw new ArgsException("202", "没有找到球队");
		}
		TeamDTO teamDTO = teamFacade.getTeam(teamId);
		if (null == teamDTO) {
			throw new ArgsException("202", "没有找到球队信息");
		}
		List<CompetitionInsuranceDTO> insurances = competitionDTO.getChallengerInsuranceList();
		if (competitionDTO.getChallengerBuyInsurance() == 1 && (null == insurances || insurances.size() == 0)) {
			throw new ArgsException("204", "不能为0个人买保险");
		}
		CompetitionDTO competition = this.competitionFacade.getCompetition(competitionDTO.getCompetitionId());
		if (null == competition) {
			throw new ArgsException("206", "比赛不存在：" + competitionDTO.getCompetitionId());
		}
		if (competition.getStatus().intValue() != 1) {
			throw new ArgsException("207", "赛事当前状态不可被应战");
		}
		if (null != competition.getInitiatorTeamId() && competition.getInitiatorTeamId().longValue() == teamId) {
			throw new ArgsException("212", "不能应战自己的比赛");
		}
		int ticketNum = getTicketNum(competition.getType(), competition.getCompetitionType(), competition.getCompetitionFormat(), false);
		competition.setChallengerBuyInsurance(competitionDTO.getChallengerBuyInsurance());
		competition.setChallengerPayMoney(ticketNum);
		competition.setChallengerPayType(competitionDTO.getChallengerPayType());
		competition.setChallengerTeamId(teamId);
		competition.setChallengerTeamName(teamDTO.getTeamName());
		competition.setChallengerMobile(userInfo.getMobile());
		competition.setChallengerUserId(competitionDTO.getChallengerUserId());
		competition.setChallengerColor(competitionDTO.getChallengerColor());
		if (competitionDTO.getChallengerBuyInsurance() == 1) {
			List<UserInfoDTO> users = new ArrayList<>();
			for (CompetitionInsuranceDTO competitionInsuranceDTO : insurances) {
				competitionInsuranceDTO.setTeamId(teamId);
				competitionInsuranceDTO.setTeamName(teamDTO.getTeamName());
				UserInfoDTO user = systemFacade.getUserInfo(competitionInsuranceDTO.getUserId());
				if (null == user) {
					throw new ArgsException("205", "用户信息不存在");
				}
				competitionInsuranceDTO.setUserName(user.getName());
				user.setIdentityNo(competitionInsuranceDTO.getIdentityNo());
				users.add(user);
			}
		}
		CompetitionDTO competitionDTO2 = new CompetitionDTO();
		competitionDTO2.setTeamId(teamId);
		competitionDTO2.setStatus(1);
		competitionDTO2.setCompetitionType(3);
		competitionDTO2 = competitionFacade.getCompetition(competitionDTO2);
		if (null != competitionDTO2 && null != competitionDTO2.getCompetitionId()) {
			throw new ArgsException("214", "你已参与了一场挑战赛");
		}
		
		competitionDTO2 = new CompetitionDTO();
		competitionDTO2.setStatus(1);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(competition.getCompetitionTime());
		calendar.add(Calendar.HOUR_OF_DAY, 2);
		competitionDTO2 = new CompetitionDTO();
		competitionDTO2.setTeamId(teamId);
		competitionDTO2.setBeginTime(competition.getCompetitionTime());
		competitionDTO2.setEndTime(calendar.getTime());
		competitionDTO2.setStatus(2);
		competitionDTO2 = competitionFacade.getCompetition(competitionDTO2);
		if (null != competitionDTO2 && null != competitionDTO2.getCompetitionId()) {
			throw new ArgsException("211", "你有一场比赛的比赛时间与本次的比赛时间冲突");
		}
		checkUploadScore(teamId);
		return competition;
	}

	@Override
	public void acceptEnterCompetition(CompetitionDTO competitionDTO) throws ArgsException {
		UserInfoDTO userInfo = systemFacade.getUserInfo(competitionDTO.getChallengerUserId());
		if (null == userInfo || (userInfo.getIdentity().intValue() != UserIdentity.teamManager.getIdentity() && userInfo.getIdentity().intValue() != UserIdentity.teamManager2.getIdentity())) {
			throw new ArgsException("201", "球队管理员或副管理员才可以接受赛事");
		}
		Long teamId = getTeamIdByUserId(competitionDTO.getChallengerUserId());
		if (null == teamId) {
			throw new ArgsException("202", "没有找到球队");
		}
		TeamDTO teamDTO = teamFacade.getTeam(teamId);
		if (null == teamDTO) {
			throw new ArgsException("202", "没有找到球队信息");
		}
		List<CompetitionInsuranceDTO> insurances = competitionDTO.getChallengerInsuranceList();
		if (competitionDTO.getChallengerBuyInsurance() == 1 && (null == insurances || insurances.size() == 0)) {
			throw new ArgsException("204", "不能为0个人买保险");
		}
		CompetitionDTO competition = this.competitionFacade.getCompetition(competitionDTO.getCompetitionId());
		if (null == competition) {
			throw new ArgsException("206", "赛事不存在：" + competitionDTO.getCompetitionId());
		}
		int ticketNum = getTicketNum(competition.getType(), competition.getCompetitionType(), competition.getCompetitionFormat(), false);
		if (ticketNum > 0) {
			int allTicketNum = this.packageFacade.getServiceNumOfTeam(teamId, UserService.ball_ticket.getServiceId());
			int usedTicketNum = this.packageFacade.getUsedServiceNumOfTeam(teamId, UserService.ball_ticket.getServiceId());
			if (allTicketNum < usedTicketNum + ticketNum) {
				throw new ArgsException("208", "约球券不够");
			}
		}
		if (null != competitionDTO.getChallengerTeach() && competitionDTO.getChallengerTeach() == 1) {
			int allTicketNum = this.packageFacade.getServiceNumOfTeam(teamId, UserService.coach_ticket.getServiceId());
			int usedTicketNum = this.packageFacade.getUsedServiceNumOfTeam(teamId, UserService.coach_ticket.getServiceId());
			if (allTicketNum < usedTicketNum + 1) {
				throw new ArgsException("210", "教练券不够");
			}
		}
		// 是否使用了赠送保险券
		boolean zcoupon = false;
		// 需要使用保险券的数量
		int couponNum = 0;
		UserCouponDTO userCouponDTO = null;
		List<UserInfoDTO> users = new ArrayList<>();
		if (competitionDTO.getChallengerBuyInsurance() == 1) {
			for (CompetitionInsuranceDTO competitionInsuranceDTO : insurances) {
				competitionInsuranceDTO.setCompetitionId(competition.getCompetitionId());
				competitionInsuranceDTO.setTeamId(teamId);
				competitionInsuranceDTO.setTeamName(teamDTO.getTeamName());
				UserInfoDTO user = systemFacade.getUserInfo(competitionInsuranceDTO.getUserId());
				if (null == user) {
					throw new ArgsException("205", "用户信息不存在");
				}
				competitionInsuranceDTO.setUserName(user.getName());
				competitionInsuranceDTO.setCreateTime(new Date());
				competitionInsuranceDTO.setStatus(1);
				user.setIdentityNo(competitionInsuranceDTO.getIdentityNo());
				users.add(user);
			}
			couponNum = users.size();
			userCouponDTO = new UserCouponDTO();
			userCouponDTO.setCouponId(CouponIds.insurance_ticket_z.getCouponId());
			userCouponDTO.setStatus(1);
			userCouponDTO.setTeamId(teamId);
			userCouponDTO = this.couponFacade.getUserCoupon(userCouponDTO);
			if (null != userCouponDTO && null != userCouponDTO.getId()) {
				int usedNum = this.couponFacade.getUsedCouponCountOfTeam(teamId, CouponIds.insurance_ticket_z.getCouponId());
				int usableNum = userCouponDTO.getQuantity() - usedNum;
				if (usableNum > 0) {
					zcoupon = true;
					couponNum = 0;
					if (couponNum > 10) {
						couponNum = couponNum - 10;
					}
				}
			}
			if (couponNum > 0) {
				int allInsuranceNum = this.packageFacade.getServiceNumOfTeam(teamId, UserService.insurance_ticket.getServiceId());
				int usedInsuranceNum = this.packageFacade.getUsedServiceNumOfTeam(teamId, UserService.insurance_ticket.getServiceId());
				if (allInsuranceNum < usedInsuranceNum + users.size()) {
					throw new ArgsException("209", "保险券不够");
				}
			}
		}
		synchronized (lock) {
			competition = this.competitionFacade.getCompetition(competitionDTO.getCompetitionId());
			if (competition.getStatus().intValue() != 1) {
				throw new ArgsException("207", "赛事当前状态不可被应战");
			}
			competition.setChallengerPayMoney(ticketNum);
			competition.setChallengerBuyInsurance(competitionDTO.getChallengerBuyInsurance());
			competition.setChallengerPayType(competitionDTO.getChallengerPayType());
			competition.setChallengerTeamId(teamId);
			competition.setChallengerTeamName(teamDTO.getTeamName());
			competition.setChallengerMobile(userInfo.getMobile());
			competition.setChallengerUserId(competitionDTO.getChallengerUserId());
			competition.setChallengerColor(competitionDTO.getChallengerColor());
			competition.setStatus(2);
			competition.setScoreStatus(1); //可上传成绩
			competition.setAcceptTime(new Date());
			this.competitionFacade.updateCompetition(competition);
		}
		if (null != insurances && insurances.size() > 0) {
			this.competitionFacade.batchSaveCompetitionInsurance(insurances);
			this.systemFacade.batchUpdateUserInfo(users);
			CompetitionInsuranceDTO competitionInsuranceDTO = new CompetitionInsuranceDTO();
			competitionInsuranceDTO.setCompetitionId(competition.getCompetitionId());
			competitionInsuranceDTO.setTeamId(teamId);
			insurances = competitionFacade.getCompetitionInsuranceList(competitionInsuranceDTO);
		}
		List<UseServiceDetailDTO> details = new ArrayList<>();
		UseServiceDetailDTO useServiceDetailDTO = new UseServiceDetailDTO();
		useServiceDetailDTO.setCreateTime(new Date());
		useServiceDetailDTO.setDescrition("应战赛事");
		useServiceDetailDTO.setNum(ticketNum);
		useServiceDetailDTO.setServiceId(UserService.ball_ticket.getServiceId());
		useServiceDetailDTO.setSubjectId(competition.getCompetitionId());
		useServiceDetailDTO.setUserId(competitionDTO.getChallengerUserId());
		useServiceDetailDTO.setTeamId(teamId);
		details.add(useServiceDetailDTO);
		if (couponNum > 0) {
			UseServiceDetailDTO useServiceDetailDTO2 = new UseServiceDetailDTO();
			useServiceDetailDTO2.setCreateTime(new Date());
			useServiceDetailDTO2.setDescrition("赛事保险");
			useServiceDetailDTO2.setNum(couponNum);
			useServiceDetailDTO2.setServiceId(UserService.insurance_ticket.getServiceId());
			useServiceDetailDTO2.setSubjectId(competition.getCompetitionId());
			useServiceDetailDTO2.setUserId(competitionDTO.getChallengerUserId());
			useServiceDetailDTO2.setTeamId(teamId);
			details.add(useServiceDetailDTO2);
		}
		if (null != competitionDTO.getChallengerTeach() && competitionDTO.getChallengerTeach() == 1) {
			UseServiceDetailDTO useServiceDetailDTO2 = new UseServiceDetailDTO();
			useServiceDetailDTO2.setCreateTime(new Date());
			useServiceDetailDTO2.setDescrition("赛事教练-应战");
			useServiceDetailDTO2.setNum(1);
			useServiceDetailDTO2.setServiceId(UserService.coach_ticket.getServiceId());
			useServiceDetailDTO2.setSubjectId(competition.getCompetitionId());
			useServiceDetailDTO2.setUserId(competitionDTO.getChallengerUserId());
			useServiceDetailDTO2.setTeamId(teamId);
			details.add(useServiceDetailDTO2);
		}
		//友谊赛 并且是预定场 发起方退一半
		if (null != competition.getInitiateUserId() && competition.getCompetitionType() == 2) {
			UseServiceDetailDTO useServiceDetailDTO2 = new UseServiceDetailDTO();
			useServiceDetailDTO2.setCreateTime(new Date());
			useServiceDetailDTO2.setDescrition("对方应战友谊赛-返回一半约球券");
			useServiceDetailDTO2.setNum(ticketNum * -1);
			useServiceDetailDTO2.setServiceId(UserService.ball_ticket.getServiceId());
			useServiceDetailDTO2.setSubjectId(competition.getCompetitionId());
			useServiceDetailDTO2.setUserId(competition.getInitiateUserId());
			useServiceDetailDTO2.setTeamId(competition.getInitiatorTeamId());
			details.add(useServiceDetailDTO2);
		}
		this.packageFacade.batchSaveUseServiceDetail(details);
		if (zcoupon) {
			UsedCouponDetailDTO usedCouponDetailDTO = new UsedCouponDetailDTO();
			usedCouponDetailDTO.setCouponId(userCouponDTO.getCouponId());
			usedCouponDetailDTO.setSubjectId(competition.getCompetitionId());
			usedCouponDetailDTO.setTeamId(teamId);
			usedCouponDetailDTO.setUserId(competitionDTO.getChallengerUserId());
			usedCouponDetailDTO.setUseTime(new Date());
			usedCouponDetailDTO.setNum(1);
			this.couponFacade.saveUsedCouponDetail(usedCouponDetailDTO);
		}
		//保险
		List<CompetitionInsuranceDTO> people = new ArrayList<>();
		if (competition.getInitiatorBuyInsurance() == 1) {
			CompetitionInsuranceDTO competitionInsuranceDTO = new CompetitionInsuranceDTO();
			competitionInsuranceDTO.setCompetitionId(competition.getCompetitionId());
			competitionInsuranceDTO.setTeamId(competitionDTO.getInitiatorTeamId());
			List<CompetitionInsuranceDTO> insurances2 = competitionFacade.getCompetitionInsuranceList(competitionInsuranceDTO);
			people.addAll(insurances2);
		}
		if (competition.getChallengerBuyInsurance() == 1 && ! insurances.isEmpty()) {
			people.addAll(insurances);
		}
		if (! people.isEmpty()) {
			InsuranceResult result = InsuranceUtil.insurance(competition.getCompetitionTime(), people);
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
					for (CompetitionInsuranceDTO cid : people) {
						cid.setStatus(2);
					}
				}else{
					for (CompetitionInsuranceDTO cid : people) {
						cid.setStatus(3);
					}
				}
				this.competitionFacade.batchUpdateCompetitionInsurance(people);
			}
		}
		// 推送
		// 1邀请赛 2友谊赛 3挑战赛
		if (null != competition.getInitiateUserId()) {
			UserInfoDTO initiator = systemFacade.getUserInfo(competition.getInitiateUserId());
			String typeName = competition.getCompetitionType() == 1 ? "邀请赛" : competition.getCompetitionType() == 2 ? "友谊赛" : "挑战赛";
			PushInfo pushInfo = CommonConstants.smsNotes.get("game_created");
			pushInfo.fillContent(competition.getChallengerTeamName(), typeName, competition.getCompetitionId());
			pushInfo.fillTemplateParam(competition.getChallengerTeamName(), typeName);
			pushInfo.setMethod("competitionDetail");
			pushInfo.setKey("competitionId");
			pushInfo.setObjectValue(competitionDTO.getCompetitionId());
			pushInfo.setSendMsg(true);
			pushInfo.setSendNote(true);
			pushInfo.setUserId(competitionDTO.getInitiateUserId());
			pushInfo.setMobiles(initiator.getMobile());
			pushInfo.setSourceFrom(initiator.getSourceFrom());
			pushInfo.setClientIds(initiator.getClientid());
			this.writeMessage(pushInfo);
		}else{
			CourtDTO courtDTO = this.courtFacade.getCourt(competition.getCourtId());
			pushCourtMgr(courtDTO, competition, "to_courtMgr_acceptCompetiotion", competition.getCompetitionId());
		}
	}

	@Override
	public CompetitionDTO competitionDetail(Long competitionId) throws ArgsException {
		CompetitionDTO competitionDTO = new CompetitionDTO();
		competitionDTO.setCompetitionId(competitionId);
		competitionDTO = this.competitionFacade.getCompetition(competitionDTO);
		if (null != competitionDTO && null != competitionDTO.getCreateTime()) {
			if (null != competitionDTO.getInitiatorTeamId()) {
				TeamDTO teamDTO1 = this.teamFacade.getTeam(competitionDTO.getInitiatorTeamId());
				if (null != teamDTO1) {
					competitionDTO.setInitiatorTeamLogo(teamDTO1.getLogo());
				}
			}
			if (null != competitionDTO.getChallengerTeamId()) {
				TeamDTO teamDTO2 = this.teamFacade.getTeam(competitionDTO.getChallengerTeamId());
				if (null != teamDTO2) {
					competitionDTO.setChallengerTeamLogo(teamDTO2.getLogo());
				}
			}
			if (competitionDTO.getOpen() == 0 && StringUtils.isNotBlank(competitionDTO.getTypeValue())) {
				TeamDTO teamDTO = new TeamDTO();
				if (competitionDTO.getNoOpenType() == 1) { // 1球队 2行业
					teamDTO.setTeamIds(competitionDTO.getTypeValue());
				} else {
					teamDTO.setBusinessIds(competitionDTO.getTypeValue());
				}
				List<TeamDTO> teams = this.teamFacade.getTeamList(teamDTO);
				competitionDTO.setTypeValueTeams(teams);
			}
			if (competitionDTO.getInitiatorBuyInsurance() == 1) {
				CompetitionInsuranceDTO competitionInsuranceDTO = new CompetitionInsuranceDTO();
				competitionInsuranceDTO.setCompetitionId(competitionId);
				competitionInsuranceDTO.setTeamId(competitionDTO.getInitiatorTeamId());
				List<CompetitionInsuranceDTO> insurances = competitionFacade.getCompetitionInsuranceList(competitionInsuranceDTO);
				competitionDTO.setInitiatorInsuranceList(insurances);
			}
			if (null != competitionDTO.getChallengerBuyInsurance() && null != competitionDTO.getChallengerTeamId() && competitionDTO.getChallengerBuyInsurance() == 1) {
				CompetitionInsuranceDTO competitionInsuranceDTO = new CompetitionInsuranceDTO();
				competitionInsuranceDTO.setCompetitionId(competitionId);
				competitionInsuranceDTO.setTeamId(competitionDTO.getChallengerTeamId());
				List<CompetitionInsuranceDTO> insurances = competitionFacade.getCompetitionInsuranceList(competitionInsuranceDTO);
				competitionDTO.setChallengerInsuranceList(insurances);
			}
		} else {
			throw new ArgsException("000", "信息不存在");
		}
		return competitionDTO;
	}

	@Override
	public void cancelCompetition(Long loginUserId, Long competitionId) throws ArgsException {
		CompetitionDTO competitionDTO = this.competitionFacade.getCompetition(competitionId);
		if (competitionDTO.getInitiateUserId().longValue() != loginUserId) {
			throw new ArgsException("200", "只能取消自己建立的赛事");
		}
		if (competitionDTO.getStatus().intValue() == 2) {
			throw new ArgsException("201", "已经建立的赛事不允许取消");
		}
		if (competitionDTO.getStatus().intValue() == 3 || competitionDTO.getStatus().intValue() == 4) {
			throw new ArgsException("202", "赛事已被取消");
		}
		boolean refund = true;
//		if (competitionDTO.getType() == 2) { // 预定场
			Date competitionTime = competitionDTO.getCompetitionTime();
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, 3);
			Date afterThreeDays = calendar.getTime();
			if (afterThreeDays.after(competitionTime)) { // 可以取消，但不会退款
				refund = false;
			}
//		}
		synchronized (lock) {
			competitionDTO = this.competitionFacade.getCompetition(competitionId);
			if (competitionDTO.getStatus().intValue() == 2) {
				throw new ArgsException("201", "已经建立的赛事不允许取消");
			}
			if (competitionDTO.getStatus().intValue() == 3 || competitionDTO.getStatus().intValue() == 4) {
				throw new ArgsException("202", "赛事已被取消");
			}
			competitionDTO.setStatus(3);
			this.competitionFacade.updateCompetition(competitionDTO);
			ZoneUseDetailDTO zoneUseDetailDTO = new ZoneUseDetailDTO();
			zoneUseDetailDTO.setZoneId(competitionDTO.getZoneId());
			zoneUseDetailDTO.setCompetitionId(competitionId);
			zoneUseDetailDTO.setStatus(1);
			zoneUseDetailDTO = this.courtFacade.getZoneUseDetail(zoneUseDetailDTO);
			if (null != zoneUseDetailDTO && null != zoneUseDetailDTO.getId()) {
				zoneUseDetailDTO.setStatus(3);
				this.courtFacade.updateZoneUseDetail(zoneUseDetailDTO);
			}
		}
		if (refund) {
			UseServiceDetailDTO useServiceDetailDTO = new UseServiceDetailDTO();
			useServiceDetailDTO.setTeamId(competitionDTO.getInitiatorTeamId());
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
					t.setUserId(loginUserId);
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
		CourtDTO courtDTO = this.courtFacade.getCourt(competitionDTO.getCourtId());
		pushCourtMgr(courtDTO, competitionDTO, "to_courtMgr_cancel", ParamHandler.getDateString(competitionDTO.getCompetitionTime(), "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public void remindCompetition(Long loginUserId, Long competitionId) throws ArgsException {
		CompetitionDTO competitionDTO = this.competitionFacade.getCompetition(competitionId);
		if (null == competitionDTO || null == loginUserId || competitionDTO.getInitiateUserId().longValue() != loginUserId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		if (competitionDTO.getStatus().intValue() != 1) {
			throw new ArgsException("300", "只有待应战的赛事才可以提醒");
		}
		if (null == competitionDTO.getRemindTimes() || competitionDTO.getRemindTimes() <= 0) {
			throw new ArgsException("301", "提醒次数已用完 ");
		}
		CourtDTO courtDTO = courtFacade.getCourt(competitionDTO.getCourtId());
		// 通知给相关的球队
		int open = competitionDTO.getOpen();
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setAreaId(courtDTO.getAreaId());
		if (open == 1) { // 公开，所有的球队
			userInfoDTO.setIdentity(UserIdentity.teamManager.getIdentity());
		} else {
			userInfoDTO.setIdentity(UserIdentity.teamManager.getIdentity());
			int noOpenType = competitionDTO.getNoOpenType();
			if (noOpenType == 1) {
				String teamIds = competitionDTO.getTypeValue();
				userInfoDTO.setTeamIds(teamIds);
			} else {
				String businessIds = competitionDTO.getTypeValue();
				userInfoDTO.setBusinessIds(businessIds);
			}
		}
		List<UserInfoDTO> userInfos = this.systemFacade.getUserInfoList(userInfoDTO);
		String key = "game_type_1_" + competitionDTO.getCompetitionType();
		push(userInfos, key, courtDTO, competitionDTO, loginUserId);
		competitionDTO.setRemindTimes(competitionDTO.getRemindTimes() - 1);
		this.competitionFacade.updateCompetition(competitionDTO);
	}

	@Override
	public Map<String, Object> homePage() throws ArgsException {
		// 最新签约球队、最新约战、最活跃球队
		TeamDTO newTeam = this.teamFacade.getNewTeam();
		CompetitionDTO competitionDTO = this.competitionFacade.getNewCompetition();
		if (null != competitionDTO) {
			TeamDTO teamDTO = this.teamFacade.getTeam(competitionDTO.getInitiatorTeamId());
			if (null != teamDTO) {
				competitionDTO.setInitiatorTeamLogo(teamDTO.getLogo());
			}
			if (null != competitionDTO.getChallengerTeamId()) {
				TeamDTO teamDTO2 = this.teamFacade.getTeam(competitionDTO.getChallengerTeamId());
				if (null != teamDTO2) {
					competitionDTO.setChallengerTeamLogo(teamDTO2.getLogo());
				}
			}
		}
		List<TeamDTO> activeTeams = this.teamFacade.getActiveTeams(3);
		Map<String, Object> map = new HashMap<>();
		map.put("newTeam", newTeam);
		map.put("newCompetition", competitionDTO);
		map.put("activeTeams", activeTeams);
		return map;
	}

	@Override
	public ApiResult findCompetitions(Long loginUserId, QueryParam queryParam) throws ArgsException {
		Integer findAll = queryParam.getParam("findAll");
		if (findAll != 3) {
			Long teamId = getTeamIdByUserId(loginUserId);
			TeamDTO teamDTO3 = this.teamFacade.getTeam(teamId);
			if (null == teamDTO3) {
				throw new ArgsException(FailureCode.ERR_002.getCode(), "没有球队信息");
			}
			queryParam.addParam("mineTeamId", teamId);
			Long companyId = teamDTO3.getCompanyId();
			if (null != companyId) {
				CompanyDTO companyDTO = commonFacade.getCompany(companyId);
				if (null != companyDTO) {
					Long businessId = companyDTO.getBusinessId();
					queryParam.addParam("businessId", businessId);
				}
			}
		}
		PageList<CompetitionDTO> competitionPageList = this.competitionFacade.getCompetitionListForPage(queryParam);
		List<CompetitionDTO> list = competitionPageList.getDataList();
		for (CompetitionDTO competitionDTO : list) {
			if (null != competitionDTO.getInitiatorTeamId()) {
				TeamDTO teamDTO = this.teamFacade.getTeam(competitionDTO.getInitiatorTeamId());
				if (null != teamDTO) {
					competitionDTO.setInitiatorTeamLogo(teamDTO.getLogo());
				}
			}
			if (null != competitionDTO.getChallengerTeamId()) {
				TeamDTO teamDTO2 = this.teamFacade.getTeam(competitionDTO.getChallengerTeamId());
				if (null != teamDTO2) {
					competitionDTO.setChallengerTeamLogo(teamDTO2.getLogo());
				}
			}
		}
		ApiResult apiResult = new ApiResult();
		apiResult.setData(list);
		PageInfo pageInfo = new PageInfo(competitionPageList.getPageNo(), competitionPageList.getPageSize(), competitionPageList.getTotalRecord());
		apiResult.setPageInfo(pageInfo);
		return apiResult;
	}

	@Override
	public ApiResult searchCompetitions(QueryParam queryParam) throws ArgsException {
		PageList<CompetitionDTO> competitionPageList = this.competitionFacade.getCompetitionListForPage(queryParam);
		List<CompetitionDTO> list = competitionPageList.getDataList();
		for (CompetitionDTO competitionDTO : list) {
			if (null != competitionDTO.getInitiatorTeamId()) {
				TeamDTO teamDTO = this.teamFacade.getTeam(competitionDTO.getInitiatorTeamId());
				if (null != teamDTO) {
					competitionDTO.setInitiatorTeamLogo(teamDTO.getLogo());
				}
			}
			if (null != competitionDTO.getChallengerTeamId()) {
				TeamDTO teamDTO2 = this.teamFacade.getTeam(competitionDTO.getChallengerTeamId());
				if (null != teamDTO2) {
					competitionDTO.setChallengerTeamLogo(teamDTO2.getLogo());
				}
			}
			// if (competitionDTO.getStatus() == 2 &&
			// competitionDTO.getCompetitionTime().before(new Date())) {
			// int initiatorScore =
			// this.commonFacade.getCompetitionScore(competitionDTO.getCompetitionId(),
			// competitionDTO.getInitiatorTeamId());
			// int challengerScore =
			// this.commonFacade.getCompetitionScore(competitionDTO.getCompetitionId(),
			// competitionDTO.getInitiatorTeamId());
			// }
		}
		ApiResult apiResult = new ApiResult();
		apiResult.setData(list);
		PageInfo pageInfo = new PageInfo(competitionPageList.getPageNo(), competitionPageList.getPageSize(), competitionPageList.getTotalRecord());
		apiResult.setPageInfo(pageInfo);
		return apiResult;

	}

	@Override
	public void uploadCompetitionScore(Long loginUserId, List<CompetitionScoreDTO> scores) throws ArgsException {
		if (null == scores || scores.isEmpty()) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "没有成绩信息");
		}
		Long teamId = getTeamIdByUserId(loginUserId);
		TeamDTO teamDTO3 = this.teamFacade.getTeam(teamId);
		if (null == teamDTO3) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "没有球队信息");
		}
		UserInfoDTO userInfo = systemFacade.getUserInfo(loginUserId);
		if (null == userInfo || (userInfo.getIdentity().intValue() != UserIdentity.teamManager.getIdentity() && userInfo.getIdentity().intValue() != UserIdentity.teamManager2.getIdentity())) {
			throw new ArgsException("201", "球队管理员或副管理员才可以上传赛事成绩");
		}
		Long competitionId = scores.get(0).getCompetitionId();
		CompetitionDTO competitionDTO = this.competitionFacade.getCompetition(competitionId);
		if (null != competitionDTO && competitionDTO.getStatus().intValue() == 2 && competitionDTO.getCompetitionTime().before(new Date())) {
			if (null != competitionDTO.getScoreStatus() && competitionDTO.getScoreStatus().intValue() == 2) {
				throw new ArgsException(FailureCode.ERR_002.getCode(), "该比赛不能再上传成绩");
			}
			for (CompetitionScoreDTO competitionScoreDTO : scores) {
				competitionScoreDTO.setCreateTime(new Date());
				competitionScoreDTO.setCreateUser(userInfo.getName());
			}
			this.competitionFacade.deleteCompetitionScoreByTeamId(teamId);
			this.competitionFacade.batchSaveCompetitionScore(scores);
			Long userId = null;
			String teamName = null;
			if (competitionDTO.getInitiateUserId().longValue() == loginUserId) {
				userId = competitionDTO.getChallengerUserId();
				teamName = competitionDTO.getInitiatorTeamName();
			}else{
				userId = competitionDTO.getInitiateUserId();
				teamName = competitionDTO.getChallengerTeamName();
			}
			// 推送
			// 1邀请赛 2友谊赛 3挑战赛
			UserInfoDTO user = systemFacade.getUserInfo(userId);
			String typeName = competitionDTO.getCompetitionType() == 1 ? "邀请赛" : competitionDTO.getCompetitionType() == 2 ? "友谊赛" : "挑战赛";
			PushInfo pushInfo = CommonConstants.smsNotes.get("upload_score");
			pushInfo.fillContent(teamName, typeName, competitionDTO.getCompetitionId());
			pushInfo.fillTemplateParam(teamName, typeName);
			pushInfo.setMethod("searchCompetitionScore");
			pushInfo.setKey("competitionId");
			pushInfo.setObjectValue(competitionDTO.getCompetitionId());
			pushInfo.setSendMsg(true);
			pushInfo.setSendNote(true);
			pushInfo.setUserId(userId);
			pushInfo.setMobiles(user.getMobile());
			pushInfo.setSourceFrom(user.getSourceFrom());
			pushInfo.setClientIds(user.getClientid());
			this.writeMessage(pushInfo);
		} else {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "比赛不存在或未结束");
		}
	}

	@Override
	public ApiResult searchCompetitionScore(QueryParam queryParam) {
		PageList<CompetitionScoreDTO> competitionScorePageList = this.competitionFacade.getCompetitionScoreListForPage(queryParam);
		List<CompetitionScoreDTO> scores = competitionScorePageList.getDataList();
		for (CompetitionScoreDTO competitionScoreDTO : scores) {
			TeamDTO teamDTO = this.teamFacade.getTeam(competitionScoreDTO.getTeamId());
			competitionScoreDTO.setTeam(teamDTO);
			if (null != competitionScoreDTO.getUserId()) {
				UserInfoDTO userInfo = systemFacade.getUserInfo(competitionScoreDTO.getUserId());
				competitionScoreDTO.setUserInfo(userInfo);
			}
		}
		ApiResult apiResult = new ApiResult();
		apiResult.setData(scores);
		PageInfo pageInfo = new PageInfo(competitionScorePageList.getPageNo(), competitionScorePageList.getPageSize(), competitionScorePageList.getTotalRecord());
		apiResult.setPageInfo(pageInfo);
		return apiResult;
	}

	@Override
	public Long addCompetition(Long loginUserId, CompetitionDTO competitionDTO) throws ArgsException {
		UserInfoDTO userInfoDTO = this.systemFacade.getUserInfo(loginUserId);
		if (null == userInfoDTO) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		ZoneDTO zoneDTO = this.courtFacade.getZone(competitionDTO.getZoneId());
		if (null == zoneDTO) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "区域不存在");
		}
		SiteDTO siteDTO = this.courtFacade.getSite(zoneDTO.getSiteId());
		if (null == siteDTO) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "无效的区域，区域所属场地不存在");
		}
		CourtDTO courtDTO = this.courtFacade.getCourt(siteDTO.getCourtId());
		if (null == courtDTO) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "无效的区域，场地所属球馆不存在");
		}
		if (courtDTO.getUserId().longValue() != loginUserId) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "球馆创建者错误");
		}
		competitionDTO.setCourtId(courtDTO.getCourtId());
		competitionDTO.setCourtName(courtDTO.getCourtName());
		competitionDTO.setCourtAddr(courtDTO.getAddress());
		competitionDTO.setZoneName(zoneDTO.getZoneName());
		competitionDTO.setCompetitionType(2);
		competitionDTO.setOpen(1);
		competitionDTO.setInitiatorBuyInsurance(0);
		competitionDTO.setStatus(1);
		competitionDTO.setInitiatorTeach(0);
		competitionDTO.setCreateTime(new Date());
		Long competitionId = this.competitionFacade.saveCompetition(competitionDTO);
		ZoneUseDetailDTO zoneUseDetailDTO = new ZoneUseDetailDTO();
		zoneUseDetailDTO.setBeginTime(competitionDTO.getCompetitionTime());
		zoneUseDetailDTO.setBeginMinute(ParamHandler.getMinute(competitionDTO.getCompetitionTime()));
		zoneUseDetailDTO.setCompetitionFormat(competitionDTO.getCompetitionFormat());
		zoneUseDetailDTO.setCompetitionId(competitionId);
		zoneUseDetailDTO.setCourtId(competitionDTO.getCourtId());
		zoneUseDetailDTO.setCreateTime(new Date());
		zoneUseDetailDTO.setEndTime(ParamHandler.getNewDate(competitionDTO.getCompetitionTime(), Calendar.HOUR_OF_DAY, 2));
		zoneUseDetailDTO.setEndMinute(ParamHandler.getMinute(zoneUseDetailDTO.getEndTime()));
		zoneUseDetailDTO.setModifyTime(zoneUseDetailDTO.getCreateTime());
		zoneUseDetailDTO.setSiteId(zoneDTO.getSiteId());
		zoneUseDetailDTO.setStatus(1);
		zoneUseDetailDTO.setTeamId(competitionDTO.getInitiatorTeamId());
		zoneUseDetailDTO.setUserId(competitionDTO.getInitiateUserId());
		zoneUseDetailDTO.setZoneId(zoneDTO.getZoneId());
		this.courtFacade.saveZoneUseDetail(zoneUseDetailDTO);
		return competitionId;
	}

	@Override
	public void updateCompetition(Long loginUserId, CompetitionDTO competitionDTO) throws ArgsException {
		CompetitionDTO competition = this.competitionFacade.getCompetition(competitionDTO.getCompetitionId());
		if (null == competition) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "比赛不存在");
		}
		ZoneDTO zoneDTO = this.courtFacade.getZone(competition.getZoneId());
		if (null == zoneDTO) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "区域不存在");
		}
		SiteDTO siteDTO = this.courtFacade.getSite(zoneDTO.getSiteId());
		if (null == siteDTO) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "无效的区域，区域所属场地不存在");
		}
		CourtDTO courtDTO = this.courtFacade.getCourt(siteDTO.getCourtId());
		if (null == courtDTO) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "无效的区域，场地所属球馆不存在");
		}
		if (courtDTO.getUserId().longValue() != loginUserId) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "球馆创建者错误");
		}
		this.competitionFacade.updateCompetition(competitionDTO);
		if (null != competitionDTO.getStatus() && (competitionDTO.getStatus() == 3 || competitionDTO.getStatus() == 7)) {
			ZoneUseDetailDTO zoneUseDetailDTO = new ZoneUseDetailDTO();
			zoneUseDetailDTO.setZoneId(competitionDTO.getZoneId());
			zoneUseDetailDTO.setCompetitionId(competition.getCompetitionId());
			zoneUseDetailDTO.setStatus(1);
			zoneUseDetailDTO = this.courtFacade.getZoneUseDetail(zoneUseDetailDTO);
			if (null != zoneUseDetailDTO && null != zoneUseDetailDTO.getId()) {
				zoneUseDetailDTO.setStatus(3);
				this.courtFacade.updateZoneUseDetail(zoneUseDetailDTO);
			}
		}
	}

}
