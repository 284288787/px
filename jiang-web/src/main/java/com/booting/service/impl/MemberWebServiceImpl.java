/**create by liuhua at 2018年2月28日 上午9:35:07**/
package com.booting.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.common.MemoryLoginCache;
import com.booting.common.PxConstants.LotteryConfig;
import com.booting.lottery.dto.GoldDetailDTO;
import com.booting.lottery.dto.GoldDrawDetailDTO;
import com.booting.lottery.dto.InvitationDetailDTO;
import com.booting.lottery.dto.LotteryConfigDTO;
import com.booting.lottery.dto.UserNumDetailDTO;
import com.booting.lottery.facade.LotteryFacade;
import com.booting.member.dto.MemberDTO;
import com.booting.member.facade.MemberFacade;
import com.booting.pub.dto.SmsIdentityDTO;
import com.booting.pub.service.SmsIdentityService;
import com.booting.vo.AccessToken;
import com.booting.vo.WxUserInfo;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service("memberWebServiceImpl")
public class MemberWebServiceImpl extends BaseWebService{

	@Autowired
	private MemberFacade memberFacade;
	@Autowired
	private WeixinWebServiceImpl weixinWebServiceImpl;
	@Autowired
	private SmsIdentityService smsIdentityService;
	@Autowired
	private LotteryFacade lotteryFacade;
	
	public Map<String, Object> wxLogin(String code) throws ArgsException, IOException{
		if (StringUtils.isBlank(code)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		AccessToken accessToken = weixinWebServiceImpl.getAccessToken(code);
		MemberDTO params = new MemberDTO();
		params.setOpenId(accessToken.getOpenid());
		MemberDTO member = memberFacade.getMember(params);
		if (null == member || null == member.getMemberId()) {
			member = new MemberDTO();
			WxUserInfo userInfo = weixinWebServiceImpl.getUserInfo(accessToken.getOpenid());
			member.setOpenId(accessToken.getOpenid());
			member.setCreateTime(new Date());
			member.setHeadPic(userInfo.getHeadimgurl());
			member.setName(userInfo.getNickname());
			this.memberFacade.saveMember(member);
		}
		member.setLoginTime(new Date());
		MemoryLoginCache.login(member);
		Map<String, Object> map = toMap(member, "yyyy-MM-dd HH:mm:ss");
		return map;
	}

	public Map<String, Object> login(String mobile, String code, String passwd) throws ArgsException {
		if (StringUtils.isBlank(mobile) || (StringUtils.isBlank(code) && StringUtils.isBlank(passwd))) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		boolean codeLogin = true;
		MemberDTO params = new MemberDTO();
		params.setMobile(mobile);
		if (StringUtils.isNotBlank(code)) {
			SmsIdentityDTO dto = new SmsIdentityDTO();
			dto.setPhone(mobile);
			dto.setTag(1);
			dto = smsIdentityService.get(dto);
			if (null == dto || ! code.equals(dto.getCode())) {
				throw new ArgsException("102", "验证码错误");
			}
		}else{
			codeLogin = false;
			params.setPassword(passwd);
		}
		MemberDTO member = memberFacade.getMember(params);
		if (null == member) {
			if (codeLogin) {
				member = new MemberDTO();
				member.setMobile(mobile);
				member.setCreateTime(new Date());
				member.setBallNums(0);
				member.setGoldNums(0);
				Long memberId = memberFacade.saveMember(member);
				member.setMemberId(memberId);
			}else{
				throw new ArgsException("104", "帐号或密码错误");
			}
		}
		member.setLoginTime(new Date());
		MemoryLoginCache.login(member);
		Map<String, Object> map = toMap(member, "yyyy-MM-dd HH:mm:ss");
		return map;
	}

	public Map<String, Object> getLoginInfo(String token) throws ArgsException {
		MemberDTO member = MemoryLoginCache.getLoginInfo(token);
		if (null == member) {
			throw new ArgsException(FailureCode.ERR_003);
		}
		Map<String, Object> map = toMap(member, "yyyy-MM-dd HH:mm:ss");
		return map;
	}

	public String getConfig(LotteryConfig lotteryConfig) {
		LotteryConfigDTO params = new LotteryConfigDTO();
		params.setKey(lotteryConfig.name());
		LotteryConfigDTO lc = lotteryFacade.getLotteryConfig(params);
		if (null != lc) {
			return lc.getValue();
		}
		return null;
	}
	
	public void addInviter(Long userId, String invitationCode) throws ArgsException {
		if (StringUtils.isBlank(invitationCode)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		int inviteeNum = Integer.parseInt(getConfig(LotteryConfig.LOTTERY_INVITEE_NUM));
		int inviterNum = Integer.parseInt(getConfig(LotteryConfig.LOTTERY_INVITER_NUM));
		MemberDTO invitee = this.memberFacade.getMember(userId);
		if (null != invitee.getInviterId()) {
			throw new ArgsException("300", "已填写过邀请码");
		}
		Long inviterUserId = getUserId(invitationCode);
		if (null == inviterUserId) {
			throw new ArgsException("302", "邀请码不存在");
		}
		if (userId.longValue() == inviterUserId) {
			throw new ArgsException("301", "自己的邀请码不能用来邀请自己");
		}
		MemberDTO inviter = this.memberFacade.getMember(inviterUserId);
		if (null == inviter) {
			throw new ArgsException("302", "邀请码不存在");
		}
		invitee.setInviterId(inviterUserId);
		invitee.setBallNums((null == invitee.getBallNums() ? 0 : invitee.getBallNums()) + inviteeNum);
		this.memberFacade.updateMember(invitee);
		MemoryLoginCache.updateBallNums(userId, invitee.getBallNums());
		
		inviter.setBallNums((null == inviter.getBallNums() ? 0 : inviter.getBallNums()) + inviterNum);
		this.memberFacade.updateMember(inviter);
		MemoryLoginCache.updateBallNums(inviterUserId, inviter.getBallNums());
		
		List<InvitationDetailDTO> details = new ArrayList<>();
		InvitationDetailDTO inviteeDetail = new InvitationDetailDTO();
		inviteeDetail.setCreateTime(new Date());
		inviteeDetail.setInviteeId(userId);
		inviteeDetail.setInviterId(inviterUserId);
		inviteeDetail.setBallNum(inviteeNum);
		details.add(inviteeDetail);
		InvitationDetailDTO inviterDetail = new InvitationDetailDTO();
		inviterDetail.setCreateTime(new Date());
		inviterDetail.setInviteeId(inviterUserId);
		inviterDetail.setInviterId(userId);
		inviterDetail.setBallNum(inviterNum);
		details.add(inviterDetail);
		this.lotteryFacade.batchSaveInvitationDetail(details);
	}

	public void updatePwd(Long userId, String password) throws ArgsException {
		if (StringUtils.isBlank(password)) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "密码不能为空");
		}
		MemberDTO member = this.memberFacade.getMember(userId);
		if (null == member) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		member.setPassword(password);
		memberFacade.updateMember(member);
		MemoryLoginCache.updatePassword(userId, password);
	}
	
	public List<GoldDetailDTO> getGoldList(Long userId, Integer pageNo, String orderBy, String orderType) {
		int pageSize = 20;
		if (null == pageNo) {
			pageNo = 1;
		}
		Integer startIndex = (pageNo - 1) * pageSize;
		Map<String, Object> params = new HashMap<>();
		params.put("startIndex", startIndex);
		params.put("maxCount", pageSize);
		params.put("userId", userId);
		params.put("s_orderBy", orderBy);
		params.put("s_orderType", orderType);
		List<GoldDetailDTO> goldDetails = this.lotteryFacade.getGoldDetailList(params);
		return goldDetails;
	}

	public List<GoldDrawDetailDTO> drawList(Long userId, Integer pageNo, String orderBy, String orderType) {
		int pageSize = 20;
		if (null == pageNo) {
			pageNo = 1;
		}
		Integer startIndex = (pageNo - 1) * pageSize;
		Map<String, Object> params = new HashMap<>();
		params.put("startIndex", startIndex);
		params.put("maxCount", pageSize);
		params.put("userId", userId);
		params.put("s_orderBy", orderBy);
		params.put("s_orderType", orderType);
		List<GoldDrawDetailDTO> drawDetails = this.lotteryFacade.getGoldDrawDetailList(params);
		return drawDetails;
	}

	public List<InvitationDetailDTO> invitationList(Long userId, Integer pageNo, String orderBy, String orderType) {
		int pageSize = 20;
		if (null == pageNo) {
			pageNo = 1;
		}
		Integer startIndex = (pageNo - 1) * pageSize;
		Map<String, Object> params = new HashMap<>();
		params.put("startIndex", startIndex);
		params.put("maxCount", pageSize);
		params.put("inviterId", userId);
		params.put("s_orderBy", orderBy);
		params.put("s_orderType", orderType);
		List<InvitationDetailDTO> invitationDetails = this.lotteryFacade.getInvitationDetailList(params);
		return invitationDetails;
	}

	public List<UserNumDetailDTO> numberList(Long userId, Integer pageNo, String orderBy, String orderType) {
		int pageSize = 20;
		if (null == pageNo) {
			pageNo = 1;
		}
		Integer startIndex = (pageNo - 1) * pageSize;
		Map<String, Object> params = new HashMap<>();
		params.put("startIndex", startIndex);
		params.put("maxCount", pageSize);
		params.put("inviterId", userId);
		params.put("s_orderBy", orderBy);
		params.put("s_orderType", orderType);
		List<UserNumDetailDTO> invitationDetails = this.lotteryFacade.getNumberList(params);
		return invitationDetails;
	}

	public void updateMember(MemberDTO member) throws ArgsException {
		if (StringUtils.isBlank(member.getHeadPic()) && StringUtils.isBlank(member.getName())) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		this.memberFacade.updateMember(member);
		if (StringUtils.isNotBlank(member.getHeadPic())) {
			MemoryLoginCache.updateHeadPic(member.getMemberId(), member.getHeadPic());
		}
		if (StringUtils.isNotBlank(member.getName())) {
			MemoryLoginCache.updateNickname(member.getMemberId(), member.getName());
		}
	}

	public PageList<MemberDTO> getListForPageMember(QueryParam queryParam, Class<MemberDTO> class1) {
		return this.memberFacade.getMemberListForPage(queryParam);
	}
}
