/**create by liuhua at 2017年9月4日 下午2:29:27**/
package com.booting.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.booting.common.PxConstants.LotteryConfig;
import com.booting.lottery.dto.LotteryDTO;
import com.booting.service.impl.BaseWebService;
import com.booting.service.impl.LotteryWebServiceImpl;

@Service
@EnableScheduling
public class TaskScheduler extends BaseWebService {
	
	@Autowired
	private LotteryWebServiceImpl lotteryWebService;
	
	/**
	 * 系统启动后，判断最近一次开奖的记录是否存在，如果不存在则创建
	 */
	@PostConstruct
	public void init(){
		String lotteryTime = lotteryWebService.getConfig(LotteryConfig.LOTTERY_TIME);
		String lotteryTimes = lotteryWebService.getConfig(LotteryConfig.LOTTERY_TIMES);
		if (StringUtils.isBlank(lotteryTime)) {
			lotteryTime = "19";
		}
		if (StringUtils.isBlank(lotteryTimes)) {
			lotteryTimes = "3";
		}
		String times[] = lotteryTime.split(",");
		int len = times.length;
		LocalTime now = LocalTime.now().plusMinutes(5);
		int nowHour = now.getHour();
		for (int i = 0; i < len; i++) {
			int hour = Integer.parseInt(times[i]);
			int nextHour = -1;
			if (hour > nowHour) {
				nextHour = hour;
			}else if(nowHour > Integer.parseInt(times[len - 1])){
				nextHour = Integer.parseInt(times[0]);
			}else if(nowHour == hour){
				int temp = now.minusMinutes(5).getHour();
				if (temp < nowHour) {
					LotteryWebServiceImpl.allowBuy = false;
					lotteryWebService.openAward();
				}
			}
			if (nextHour != -1) {
				LocalDateTime openTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(nextHour, 0));
				Date openDate = new Date(openTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
				LotteryDTO param = new LotteryDTO();
				param.setOpenTime(openDate);
				LotteryDTO lottery = this.lotteryWebService.getLottery(param);
				if (null == lottery || null == lottery.getLotteryId()) {
					lottery = new LotteryDTO();
					lottery.setCreateTime(new Date());
					lottery.setOpenTime(openDate);
					lottery.setPeopleTotalNum(0);
					lottery.setTimes(Integer.parseInt(lotteryTimes));
					lottery.setEnd(0);
					this.lotteryWebService.saveLottery(lottery);
				}
				break;
			}
		}
	}
	
	/**
	 * 任务  每个整点的前5分钟执行一次
	 * 
	 * @author liuhua
	 *
	 */
	@Scheduled(cron = "0 55 * * * ?")
	public void task() {
		String lotteryTime = lotteryWebService.getConfig(LotteryConfig.LOTTERY_TIME);
		String lotteryTimes = lotteryWebService.getConfig(LotteryConfig.LOTTERY_TIMES);
		if (StringUtils.isBlank(lotteryTime)) {
			lotteryTime = "19";
		}
		if (StringUtils.isBlank(lotteryTimes)) {
			lotteryTimes = "3";
		}
		String times[] = lotteryTime.split(",");
		LocalTime now = LocalTime.now().plusMinutes(5);
		int nowHour = now.getHour();
		for (int i = 0; i < times.length; i++) {
			int hour = Integer.parseInt(times[i]);
			if (hour == nowHour) {
				//时间已到，1.停止下注，2.生成下一个时间段的奖 3.开奖
				LotteryWebServiceImpl.allowBuy = false;
				lotteryWebService.openAward();
				int nextHour = 0;
				if (i == times.length - 1) {
					nextHour = Integer.parseInt(times[0]);
				}else{
					nextHour = Integer.parseInt(times[i + 1]);
				}
				LocalDateTime openTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(nextHour, 0));
				Date openDate = new Date(openTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
				LotteryDTO param = new LotteryDTO();
				param.setOpenTime(openDate);
				LotteryDTO lottery = this.lotteryWebService.getLottery(param);
				if (null == lottery || null == lottery.getLotteryId()) {
					lottery = new LotteryDTO();
					lottery.setCreateTime(new Date());
					lottery.setOpenTime(openDate);
					lottery.setPeopleTotalNum(0);
					lottery.setTimes(Integer.parseInt(lotteryTimes));
					lottery.setEnd(0);
					this.lotteryWebService.saveLottery(lottery);
				}
				
				break;
			}
		}
	}
	
	@Scheduled(cron = "0 0 * * * ?")
	public void task2() {
		String lotteryTime = lotteryWebService.getConfig(LotteryConfig.LOTTERY_TIME);
		if (StringUtils.isBlank(lotteryTime)) {
			lotteryTime = "19";
		}
		String times[] = lotteryTime.split(",");
		LocalTime now = LocalTime.now();
		int nowHour = now.getHour();
		for (int i = 0; i < times.length; i++) {
			int hour = Integer.parseInt(times[i]);
			if (hour == nowHour) {
				LotteryWebServiceImpl.allowBuy = true;
				break;
			}
		}
	}
}
