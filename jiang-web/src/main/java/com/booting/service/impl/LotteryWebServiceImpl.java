/**create by liuhua at 2018年3月2日 上午10:27:23**/
package com.booting.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.common.MemoryLoginCache;
import com.booting.common.PxConstants.LotteryConfig;
import com.booting.lottery.dto.GoldDetailDTO;
import com.booting.lottery.dto.LotteryAwardDetailDTO;
import com.booting.lottery.dto.LotteryAwardUserDetailDTO;
import com.booting.lottery.dto.LotteryConfigDTO;
import com.booting.lottery.dto.LotteryDTO;
import com.booting.lottery.dto.LotteryDetailDTO;
import com.booting.lottery.dto.UserNumDetailDTO;
import com.booting.lottery.facade.LotteryFacade;
import com.booting.member.dto.MemberDTO;
import com.booting.member.facade.MemberFacade;
import com.booting.question.dto.QuestionDTO;
import com.booting.question.facade.QuestionFacade;
import com.fasterxml.jackson.core.type.TypeReference;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.utils.MemoryCacheUtil;
import com.star.framework.utils.MemoryCacheUtil.CacheType;

@Service("lotteryWebServiceImpl")
public class LotteryWebServiceImpl {
	
	public static Boolean allowBuy = true;
	
	@Autowired
	private LotteryFacade lotteryFacade;
	@Autowired
	private QuestionFacade questionFacade;
	@Autowired
	private MemberFacade memberFacade;
	
	public void createLottery(){
		
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

	public void saveLottery(LotteryDTO lottery) {
		this.lotteryFacade.saveLottery(lottery);
	}
	
	public LotteryDTO getLottery(LotteryDTO param) {
		return lotteryFacade.getLottery(param);
	}

	public LotteryDTO getNowLottery() {
//		String lotteryTime = getConfig(LotteryConfig.LOTTERY_TIME);
//		if (StringUtils.isBlank(lotteryTime)) {
//			lotteryTime = "19";
//		}
//		String times[] = lotteryTime.split(",");
//		int len = times.length;
//		LocalTime now = LocalTime.now();
//		int nowHour = now.getHour();
//		for (int i = 0; i < len; i++) {
//			int hour = Integer.parseInt(times[i]);
//			int hh = -1;
//			if (hour > nowHour) {
//				hh = hour;
//			}else if(nowHour > Integer.parseInt(times[len - 1])){
//				hh = Integer.parseInt(times[0]);
//			}
//			if (hh != -1) {
//				LocalDateTime openTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(hour, 0));
//				Date openDate = new Date(openTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
//				LotteryDTO param = new LotteryDTO();
//				param.setOpenTime(openDate);
//				LotteryDTO lottery = getLottery(param);
//				return lottery;
//			}
//		}
//		return null;
		LotteryDTO lottery = this.lotteryFacade.getNewestLottery(0);
		return lottery;
	}

	public void buy(LotteryDetailDTO detail) throws ArgsException {
		if (null == detail.getUserId() || null == detail.getLun() || StringUtils.isBlank(detail.getChooseNums())) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		if (! allowBuy) {
			throw new ArgsException("900", "开奖前5分钟停止下注，请开奖后再来下注");
		}
		MemberDTO member = memberFacade.getMember(detail.getUserId());
		if (null == member) {
			throw new ArgsException(FailureCode.ERR_000.getCode(), "用户不存在");
		}
		String[] nums = detail.getChooseNums().split(",");
		int num = nums.length + 1;
		if (detail.getLun() == 0) {
			if (num > 1 && member.getBallNums() < num - 1) {
				throw new ArgsException(FailureCode.ERR_000.getCode(), "不能选择" + num + "个号码");
			}
		}
		LotteryDTO lottery = getNowLottery();
		if (null == lottery) {
			throw new ArgsException(FailureCode.ERR_001);
		}
		LotteryDetailDTO param = new LotteryDetailDTO();
		param.setLotteryId(lottery.getLotteryId());
		param.setLun(detail.getLun());
		param.setUserId(detail.getUserId());
		List<LotteryDetailDTO> details = this.lotteryFacade.getLotteryDetailList(param);
		if (null != details && details.size() > 0) {
			throw new ArgsException(FailureCode.ERR_000, "该轮已下注<br>请关注开奖结果");
		}
		for (String n : nums) {
			try {
				int nb = Integer.parseInt(n);
				if (nb < 1 || nb > 12) {
					throw new ArgsException(FailureCode.ERR_002);
				}
			} catch (Exception e) {
				throw new ArgsException(FailureCode.ERR_002);
			}
		}
		detail.setLotteryId(lottery.getLotteryId());
		detail.setChooseTime(new Date());
		if (num == 1) {
			this.lotteryFacade.saveLotteryDetail(detail);
		}else{
			List<LotteryDetailDTO> details2 = new ArrayList<>();
			for (int i = 0; i < nums.length; i++) {
				try {
					LotteryDetailDTO temp = (LotteryDetailDTO) detail.clone();
					temp.setChooseNum(Integer.parseInt(nums[i]));
					details2.add(temp);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
			this.lotteryFacade.batchSaveLotteryDetail(details2);
		}
		lottery.setPeopleTotalNum(lottery.getPeopleTotalNum() + num);
		this.lotteryFacade.updateLottery(lottery);
		if (detail.getLun() == 0) {
			if (num > 1) {
				member.setBallNums(member.getBallNums() - num + 1);
				this.memberFacade.updateMember(member);
				MemoryLoginCache.updateGoldNums(member.getMemberId(), member.getBallNums());
				UserNumDetailDTO numDetail = new UserNumDetailDTO();
				numDetail.setBusinessId(lottery.getLotteryId());
				numDetail.setBusinessType(2);
				numDetail.setCreateTime(new Date());
				numDetail.setDesc("下注[" + ParamHandler.getDateString(lottery.getOpenTime(), "yyyy-MM-dd HH:mm") + "]第一轮，共买了" + num + "个号码");
				numDetail.setNum((num  - 1) * -1);
				numDetail.setUserId(detail.getUserId());
				this.lotteryFacade.saveUserNumDetail(numDetail);
			}
		}
	}

	public Map<String, Map<String, Object>> getMemberData(Long userId) {
		LotteryDTO lottery = getNowLottery();
		LotteryDetailDTO param = new LotteryDetailDTO();
		param.setUserId(userId);
		param.setLotteryId(lottery.getLotteryId());
		List<LotteryDetailDTO> details = this.lotteryFacade.getLotteryDetailList(param);
		Collections.sort(details, (a, b) -> a.getLun().compareTo(b.getLun()));
		LotteryDetailDTO temp = new LotteryDetailDTO();
		temp.setLun(4);
		details.add(temp);
		Map<String, Map<String, Object>> map = new HashMap<>();
		String chooseNums = "";
		int lun = 99;
		for (LotteryDetailDTO detail : details) {
			if (lun != 99 && detail.getLun() != lun) {
				chooseNums = chooseNums.substring(1);
				Map<String, Object> value = new HashMap<>();
				value.put("num", chooseNums.split(",").length);
				value.put("nums", chooseNums);
				map.put("lun" + lun, value);
				lun = detail.getLun();
				chooseNums = "";
			}else if (lun == 99) {
				lun = detail.getLun();
				chooseNums = "";
			}
			String chooseNum = detail.getChooseNum() + "";
			chooseNums += "," + chooseNum;
		}
		return map;
	}

	public Map<String, Object> getQuestion() {
		Integer questionNums = Integer.parseInt(getConfig(LotteryConfig.LOTTERY_QUESTION_NUM));
		Random random = new Random();
		int questionId = random.nextInt(questionNums) + 1;
		QuestionDTO question = this.questionFacade.getQuestion(questionId);
		Map<String, Object> map = new HashMap<>();
		map.put("questionId", question.getQuestionId());
		map.put("ask", question.getAsk());
		map.put("answers", question.getAnswers());
		return map;
	}

	public Boolean questionRight(Long questionId, String answer, String mobile, String code) throws ArgsException {
		if(StringUtils.isBlank(mobile) || StringUtils.isBlank(code)){
			throw new ArgsException(FailureCode.ERR_002);
		}
		String scode = MemoryCacheUtil.get(CacheType.reg_pic_code, mobile, new TypeReference<String>() {});
		if (null != scode && scode.toLowerCase().equals(code.toLowerCase())) {
			boolean bool = false;
			QuestionDTO question = this.questionFacade.getQuestion(questionId);
			if (null != question) {
				if (answer.equals(question.getRightAnswer())) {
					bool = true;
				}
			}
			return bool;
		}else{
			throw new ArgsException(FailureCode.ERR_018);
		}
	}

	public void openAward() {
		LotteryDTO lottery = getNowLottery();
		if (lottery.getEnd() == 1) {
			return ;
		}
		String[] lmoneys = getConfig(LotteryConfig.LOTTERY_MONEY).split(",");
		String[] baseNums = getConfig(LotteryConfig.LOTTERY_BASE_NUM).split(",");
		
		List<LotteryAwardDetailDTO> awardDetails = new ArrayList<>();
		List<LotteryAwardUserDetailDTO> awardUserDetails = new ArrayList<>();
		List<GoldDetailDTO> goldDetails = new ArrayList<>();
		Map<String, Object> ballNums = new LinkedHashMap<>();
		String peopleNums = "";
		StringBuilder lastAwardUserIds = new StringBuilder();
		for (int i = 0; i < lottery.getTimes(); i++) {
			Map<String, Integer> detail = null;
			if (i == 0 || lastAwardUserIds.length() > 0) {
				detail = this.lotteryFacade.openAward(lottery, i, lastAwardUserIds.toString());
			}else{
				detail = new HashMap<>();
				detail.put("total", 0);
			}
			lastAwardUserIds = new StringBuilder();
			Integer total = detail.get("total");
			peopleNums += "," + total;
			Map<String, Integer> nums = new LinkedHashMap<>();
			Map<Integer, StringBuilder> temp = new HashMap<>();
			int minNums = Integer.MAX_VALUE;
			int awardNum = 0;
			int max = 0;
			for (int j = 1; j <= 12; j++) {
				Integer num = detail.get(j + "");
				if (null == num) {
					num = 0;
				}
				if (num < minNums) {
					minNums = num;
				}
				if (num > max) {
					max = num;
				}
				StringBuilder tem = temp.get(num);
				if (null == tem) {
					tem = new StringBuilder(j + "");
					temp.put(num, tem);
				}else{
					tem.append("," + j);
				}
				nums.put("ball" + j, num);
				awardNum += num;
			}
			nums.put("min", minNums);
			nums.put("max", max);
			ballNums.put("lun" + i, nums);
			if (minNums > 0) {
				LotteryAwardDetailDTO awardDetail = new LotteryAwardDetailDTO();
				awardDetail.setAwardNum(temp.get(minNums).toString());
				awardDetail.setBallNums(ParamHandler.gson.toJson(nums));
				awardDetail.setLotteryId(lottery.getLotteryId());
				awardDetail.setLun(i);
				int mmoney = 0;
				int money = Integer.parseInt(lmoneys[i]);
				double baseNum = Double.parseDouble(baseNums[i]);
				if (money == 0) {
					mmoney = Double.valueOf(baseNum * total * 100).intValue();
				}else{
					mmoney = money * 100;
				}
				awardDetail.setMoney(mmoney);
				awardDetail.setOpenTime(lottery.getOpenTime());
				awardDetail.setPeopleNum(awardNum);
				Long awardId = this.lotteryFacade.saveLotteryAwardDetail(awardDetail);
				awardDetails.add(awardDetail);
				LotteryDetailDTO lotteryDetailDTO = new LotteryDetailDTO();
				lotteryDetailDTO.setLotteryId(lottery.getLotteryId());
				lotteryDetailDTO.setChooseNums(awardDetail.getAwardNum());
				lotteryDetailDTO.setLun(i);
				List<LotteryDetailDTO> lotteryDetails = this.lotteryFacade.getLotteryDetailList(lotteryDetailDTO);
//				List<Long> userIds = new ArrayList<>();
				for (LotteryDetailDTO lotteryDetailDTO2 : lotteryDetails) {
//					if (userIds.contains(lotteryDetailDTO2.getUserId())) {
//						continue;
//					}
//					userIds.add(lotteryDetailDTO2.getUserId());
					LotteryAwardUserDetailDTO awardUserDetail = new LotteryAwardUserDetailDTO();
					awardUserDetail.setAwardId(awardId);
					awardUserDetail.setLotteryId(lottery.getLotteryId());
					awardUserDetail.setLun(i);
					awardUserDetail.setMoney(Double.valueOf(mmoney / 100.0 / awardNum * 100).intValue());
					awardUserDetail.setOpenTime(lottery.getOpenTime());
					awardUserDetail.setUserId(lotteryDetailDTO2.getUserId());
					Long awardDetailId = this.lotteryFacade.saveLotteryAwardUserDetail(awardUserDetail);
					awardUserDetails.add(awardUserDetail);
					
					GoldDetailDTO goldDetail = new GoldDetailDTO();
					goldDetail.setAwardDetailId(awardDetailId);
					goldDetail.setAwardId(awardId);
					goldDetail.setCreateTime(new Date());
					goldDetail.setDescription("[" + ParamHandler.getDateString(lottery.getOpenTime(), "yyyy-MM-dd HH:mm") + "]第" + (i + 1) + "轮中奖奖励金币");
					goldDetail.setGoldNum(awardUserDetail.getMoney());
					goldDetail.setLotteryId(awardUserDetail.getLotteryId());
					goldDetail.setUserId(awardUserDetail.getUserId());
					goldDetails.add(goldDetail);
					lastAwardUserIds.append(",").append(lotteryDetailDTO2.getUserId());
				}
			}
			
		}
		lottery.setPeopleNums(peopleNums.substring(1));
		lottery.setBallNums(ParamHandler.gson.toJson(ballNums));
		String moneys = "";
		for (LotteryAwardDetailDTO lotteryAwardDetailDTO : awardDetails) {
			moneys += "," + lotteryAwardDetailDTO.getMoney();
		}
		lottery.setMoneys(moneys.length() > 0 ? moneys.substring(1) : "");
		lottery.setEnd(1);
		this.lotteryFacade.updateLottery(lottery);
		if (! goldDetails.isEmpty()) {
			this.lotteryFacade.batchSaveGoldDetail(goldDetails);
		}
	}
	
	public LotteryDTO getLastLottery(){
		LotteryDTO lottery = this.lotteryFacade.getNewestLottery(1);
		return lottery;
	}

	public LotteryDTO open(){
		LotteryDTO lottery = getLastLottery();
		LotteryAwardDetailDTO lotteryAwardDetailDTO = new LotteryAwardDetailDTO();
		lotteryAwardDetailDTO.setLotteryId(lottery.getLotteryId());
		List<LotteryAwardDetailDTO> list = this.lotteryFacade.getLotteryAwardDetailList(lotteryAwardDetailDTO);
		lottery.setDetails(list);
		return lottery;
	}

	public Map<String, List<Map<String, Object>>> history() {
		List<Map<String, Object>> lun0 = lotteryFacade.getHistory(0, 12);
		List<Map<String, Object>> lun1 = lotteryFacade.getHistory(1, 12);
		List<Map<String, Object>> lun2 = lotteryFacade.getHistory(2, 12);
		Map<String, List<Map<String, Object>>> result = new LinkedHashMap<>();
		result.put("lun0", lun0);
		result.put("lun1", lun1);
		result.put("lun2", lun2);
		return result;
	}
}
