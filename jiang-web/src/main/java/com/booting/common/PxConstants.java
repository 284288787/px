/**create by liuhua at 2018年1月17日 下午2:39:42**/
package com.booting.common;

public class PxConstants {

	public enum LotteryConfig{
		LOTTERY_TIMES ("开奖轮次"),
		LOTTERY_BASE_NUM ("奖金基数(元)"),
		LOTTERY_MONEY ("第轮奖金金额(优先级比LOTTERY_BASE_NUM高，如果为0则按LOTTERY_BASE_NUM计算)"),
		LOTTERY_MONEY_VIEW ("每轮预计奖金"),
		LOTTERY_TIME ("每天开奖时间"),
		LOTTERY_INVITEE_NUM ("邀请好友，被邀请人可获得号码数量"),
		LOTTERY_INVITER_NUM ("邀请好友，邀请人可获得号码数量"),
		LOTTERY_WITHDRAWAL_NUM ("每天可提现次数"),
		LOTTERY_QUESTION_NUM ("题库题目数量");
		
		private String caption;
		
		private LotteryConfig(String caption){
			this.caption = caption;
		}
		
		public String getCaption(){
			return caption;
		}
	}
	
	//状态 1待支付 2已支付 3已退款
	public enum ApplyStatus{
		dfk(1),
		yzf(2),
		ytk(3);
		
		private Integer status;
		
		private ApplyStatus(Integer status){
			this.status = status;
		}
		public Integer getStatus() {
			return status;
		}
	}
}
