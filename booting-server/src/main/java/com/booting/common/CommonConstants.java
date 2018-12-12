/**create by liuhua at 2017年6月2日 上午9:26:40**/
package com.booting.common;

import java.util.HashMap;
import java.util.Map;

public class CommonConstants {

	/**是否赠送保险券**/
	public static boolean zbxq = true;
	
	public static Map<String, PushInfo> smsNotes = new HashMap<String, PushInfo>(){
		private static final long serialVersionUID = 1L;
		{
			put("code_sms", new PushInfo("SMS_77245049", "{\"code\":\"%s\"}"));
			put("team_applyMember",   new PushInfo(1, MessageType.team, "messageDetail", "messageId", "申请加入球队", "%s申请加入你的球队。", "%s申请加入你的球队。"));
			put("team_auditMember_3",   new PushInfo(1, MessageType.team, "messageDetail", "messageId", "申请加入球队审核不通过", "加入%s的申请未通过，原因：%s。", "%s申请加入你的球队。"));
			put("team_addMember_1",   new PushInfo(1, MessageType.team, "messageDetail", "messageId", true, "恭喜加入球队", "恭喜你成为%s的一员。", "恭喜你成为%s的一员。", "SMS_77380031", "{\"teamName\":\"%s\"}"));
			put("team_addMember_2",   new PushInfo(1, MessageType.team, "messageDetail", "messageId", true, "恭喜加入球队", "恭喜你成为%s的一员，使用手机号码进行登录，默认密码123456。", "恭喜你成为%s的一员，使用手机号码进行登录，默认密码123456，APP下载链接:<a href='http://t.cn/RWtNROd'>点击进入</a>", "SMS_105810037", "{\"teamName\":\"%s\"}"));
			put("team_transfer_1",    new PushInfo(1, MessageType.team, "messageDetail", "messageId"/*, true*/, "恭喜成为球队管理员", "恭喜你已成为%s的管理员。", "恭喜你已成为%s的管理员。"/*, "SMS_77185049", "{\"teamName\":\"%s\"}"*/));
			put("team_transfer_2",    new PushInfo(2, MessageType.team, "messageDetail", "messageId"/*, true*/, "球队管理员转让", "%s的管理员已转让给 %s。", "%s的管理员已转让给 %s。"/*, "SMS_77315045", "{\"teamName\":\"%s\",\"userName\":\"%s\"}"*/));
			put("team_delMember",     new PushInfo(1, MessageType.team, "messageDetail", "messageId"/*, true*/, "退出球队", "你已退出 %s。", "你已退出 %s。"/*, "SMS_77300038", "{\"teamName\":\"%s\"}"*/));
			put("team_setManager_1",  new PushInfo(1, MessageType.team, "messageDetail", "messageId"/*, true*/, "恭喜成为球队副管理员", "恭喜你已成为%s的副管理员。", "恭喜你已成为%s的副管理员。"/*, "SMS_77195062", "{\"teamName\":\"%s\"}"*/));
			put("team_setManager_2",  new PushInfo(2, MessageType.team, "messageDetail", "messageId"/*, true*/, "球队副管理员变更", "成员 %s被指定为%s的副管理员。", "成员 %s被指定为%s的副管理员。"/*, "SMS_77255044", "{\"teamName\":\"%s\",\"userName\":\"%s\"}"*/));
			
			put("game_type_0_1",  new PushInfo(2, MessageType.game, "competitionDetail", "competitionId", "你有一场邀请赛", "%s发起了一场邀请赛，请前往精茵体育app应答比赛\n时间：%s\n地点：%s。", "%s发起了一场邀请赛，请前往精茵体育app应答比赛<br>时间：%s<br>地点：%s<br><p type='competitionDetail' key='competitionId' value='%s'></p>", "SMS_95925020", "{\"teamName\":\"%s\", \"courtName\":\"%s\", \"dateTime\":\"%s\"}"));
			put("game_type_0_2",  new PushInfo(2, MessageType.game, "competitionDetail", "competitionId", "你有一场友谊赛", "%s发起了一场友谊赛，请及时前往精茵体育app应答比赛，长时间不应答系统将默认取消比赛。\n时间：%s\n地点：%s。", "%s发起了一场友谊赛，请及时前往精茵体育app应答比赛，长时间不应答系统将默认取消比赛。<br>时间：%s<br>地点：%s<br><p type='competitionDetail' key='competitionId' value='%s'></p>", "SMS_95855017", "{\"teamName\":\"%s\", \"courtName\":\"%s\", \"dateTime\":\"%s\"}"));
			put("game_type_0_3",  new PushInfo(2, MessageType.game, "competitionDetail", "competitionId", "你有一场挑战赛", "%s发起了一场挑战赛，请及时前往精茵体育app应答比赛，长时间不应答系统将默认取消比赛。\n时间：%s\n地点：%s。", "%s发起了一场挑战赛，请及时前往精茵体育app应答比赛，长时间不应答系统将默认取消比赛。<br>时间：%s<br>地点：%s<br><p type='competitionDetail' key='competitionId' value='%s'></p>"));
			
			put("game_type_1_1",  new PushInfo(2, MessageType.game, "competitionDetail", "competitionId", "你有一场邀请赛", "%s发起了一场邀请赛，请前往精茵体育APP加紧处理！\n时间：%s\n地点：%s。", "%s发起了一场邀请赛，请前往精茵体育APP加紧处理！<br>时间：%s<br>地点：%s<br><p type='competitionDetail' key='competitionId' value='%s'></p>", "SMS_95920021", "{\"teamName\":\"%s\", \"courtName\":\"%s\", \"dateTime\":\"%s\"}"));
			put("game_type_1_2",  new PushInfo(2, MessageType.game, "competitionDetail", "competitionId", "你有一场友谊赛", "%s发起了一场友谊赛，请加紧前往精茵体育app应答比赛，长时间不应答系统将默认取消比赛。\n时间：%s\n地点：%s。", "%s发起了一场友谊赛，请加紧前往精茵体育app应答比赛，长时间不应答系统将默认取消比赛。时间：%s<br>地点：%s<br><p type='competitionDetail' key='competitionId' value='%s'></p>", "SMS_95705026", "{\"teamName\":\"%s\", \"courtName\":\"%s\", \"dateTime\":\"%s\"}"));
			put("game_type_1_3",  new PushInfo(2, MessageType.game, "competitionDetail", "competitionId", "你有一场挑战赛", "%s发起了一场挑战赛，请加紧前往精茵体育app应答比赛，长时间不应答系统将默认取消比赛。\n时间：%s\n地点：%s。", "%s发起了一场挑战赛，请加紧前往精茵体育app应答比赛，长时间不应答系统将默认取消比赛。<br>时间：%s<br>地点：%s<br><p type='competitionDetail' key='competitionId' value='%s'></p>"));
			
			put("game_type_2_1",  new PushInfo(2, MessageType.game, "competitionDetail", "competitionId", "你有一场邀请赛", "%s发起了一场邀请赛，1小时后赛事将作取消处理，请及时前往精茵体育app应答比赛。\n时间：%s\n地点：%s。", "%s发起了一场邀请赛，1小时后赛事将作取消处理，请及时前往精茵体育app应答比赛。<br>时间：%s<br>地点：%s<br><p type='competitionDetail' key='competitionId' value='%s'></p>", "SMS_95875028", "{\"teamName\":\"%s\", \"courtName\":\"%s\", \"dateTime\":\"%s\"}"));
			put("game_type_2_2",  new PushInfo(2, MessageType.game, "competitionDetail", "competitionId", "你有一场友谊赛", "%s发起了一场友谊赛，1小时后赛事将作取消处理，请及时前往精茵体育app应答比赛。\n时间：%s\n地点：%s。", "%s发起了一场友谊赛，1小时后赛事将作取消处理，请及时前往精茵体育app应答比赛。<br>时间：%s<br>地点：%s<br><p type='competitionDetail' key='competitionId' value='%s'></p>", "SMS_95710023", "{\"teamName\":\"%s\", \"courtName\":\"%s\", \"dateTime\":\"%s\"}"));
			put("game_type_2_3",  new PushInfo(2, MessageType.game, "competitionDetail", "competitionId", "你有一场挑战赛", "%s发起了一场挑战赛，1小时后赛事将作取消处理，请及时前往精茵体育app应答比赛。\n时间：%s\n地点：%s。", "%s发起了一场挑战赛，1小时后赛事将作取消处理，请及时前往精茵体育app应答比赛。<br>时间：%s<br>地点：%s<br><p type='competitionDetail' key='competitionId' value='%s'></p>"));
			
			put("game_created",  new PushInfo(1, MessageType.game, "competitionDetail", "competitionId", "你的赛事已被接受", "%s接收了你发起的%s，请准时参与。", "%s接收了你发起的%s，请准时参与。<p type='competitionDetail' key='competitionId' value='%s'></p>", "SMS_95855028", "{\"teamName\":\"%s\", \"typeName\":\"%s\"}"));
			put("upload_score",  new PushInfo(1, MessageType.game, "searchCompetitionScore", "competitionId", "对方已上传成绩", "%s与你的%s，已上传成绩，可以前往查看。", "%s与你的%s，已上传成绩，可以前往查看。<p type='searchCompetitionScore' key='competitionId' value='%s'></p>"));
			put("to_courtMgr_enterCompetition",  new PushInfo(1, MessageType.court, "jumpZoneMap", "competitionTime", "有球队预定了场地", "%s在%s预定了场地", "%s在%s预定了场地，可以前往查看。<p type='jumpZoneMap' key='competitionTime' value='%s'></p>"));
			put("to_courtMgr_cancel",  new PushInfo(1, MessageType.court, "jumpZoneMap", "competitionTime", "有球队取消了场地", "%s取消了场地", "%s在%s取消了场地，可以前往查看。<p type='jumpZoneMap' key='competitionTime' value='%s'></p>"));
			put("to_courtMgr_acceptCompetiotion",  new PushInfo(1, MessageType.court, "competitionDetail", "competitionId", "有球队应答了比赛", "%s应答了%s的比赛", "%s应答了%s的比赛，可以前往查看。<p type='competitionDetail' key='competitionId' value='%s'></p>"));
		}
	};
	
	public static Map<Integer, String> levelSpecification = new HashMap<Integer, String>(){
		private static final long serialVersionUID = 1L;
		{
			put(1, "11人场");
			put(2, "8人场");
			put(3, "5人场");
		}
	};
	
	public static Map<String, Integer> tickets = new HashMap<String, Integer>(){
		private static final long serialVersionUID = 1L;
		{
			//11人场 邀请赛 发起发
			put("1_1_1", 4);
			put("1_1_0", 0);
			put("1_2_1", 2);
			put("1_2_0", 2);
			put("1_3_1", 4);
			put("1_3_0", 4);
			put("2_1_1", 2);
			put("2_1_0", 0);
			put("2_2_1", 1);
			put("2_2_0", 1);
			put("2_3_1", 2);
			put("2_3_0", 2);
		}
	};
	
	public static Map<String, Integer> tickets2 = new HashMap<String, Integer>(){
		private static final long serialVersionUID = 1L;
		{
			//1：11人场    2：8人场 
			//1邀请赛 2友谊赛 3挑战赛
			//1发起方 0挑战方
			put("1_1_1", 4);
			put("1_1_0", 0);
			put("1_2_1", 4);   //赛事建立退一半
			put("1_2_0", 2);
			put("1_3_1", 4);
			put("1_3_0", 4);
			put("2_1_1", 2);
			put("2_1_0", 0);
			put("2_2_1", 2);
			put("2_2_0", 1);
			put("2_3_1", 2);
			put("2_3_0", 2);
		}
	};
	
	public static String getUserIdentity(Integer identity){
		if (null == identity) {
			return null;
		}
		UserIdentity[] identities = UserIdentity.values();
		for (UserIdentity userIdentity : identities) {
			if (userIdentity.getIdentity().intValue() == identity){
				return userIdentity.getCaption();
			}
		}
		return null;
	}
	
	public static String getTeanIdentity(Integer identity){
		if (null == identity) {
			return null;
		}
		TeamIdentity[] identities = TeamIdentity.values();
		for (TeamIdentity teamIdentity : identities) {
			if (teamIdentity.getIdentity().intValue() == identity){
				return teamIdentity.getCaption();
			}
		}
		return null;
	}
	
	public static String getTeanLocation(Integer location){
		if (null == location) {
			return null;
		}
		TeamLocation[] locations = TeamLocation.values();
		for (TeamLocation teamLocation : locations) {
			if (teamLocation.getLocation().intValue() == location){
				return teamLocation.getCaption();
			}
		}
		return null;
	}
	
	public static String getCompetitionFormat(Integer format){
		if (null == format) {
			return null;
		}
		CompetitionFormat[] formats = CompetitionFormat.values();
		for (CompetitionFormat competitionFormat : formats) {
			if (competitionFormat.getFormat().intValue() == format){
				return competitionFormat.getCaption();
			}
		}
		return null;
	}
	
	public enum CouponIds{
		insurance_ticket_z(1000l, "赠送保险券");
		
		private Long couponId;
		private String caption;
		
		private CouponIds(Long couponId, String caption){
			this.couponId = couponId;
			this.caption = caption;
		}

		public Long getCouponId() {
			return couponId;
		}

		public String getCaption() {
			return caption;
		}

	}
	public enum UserService{
		uniform_ticket(1000l, "球服券"),
		ball_ticket(1001l, "约球券"),
		text_popularize_ticket(1002l, "文化推广券(图文)"),
		video_popularize_ticket(1003l, "文化推广券(视频)"),
		coach_ticket(1004l, "教练券"),
		insurance_ticket(1005l, "保险券"),
		insurance_ticket_z(9999l, "保险券[赠]");
		
		private Long serviceId;
		private String caption;
		
		private UserService(Long serviceId, String caption){
			this.serviceId = serviceId;
			this.caption = caption;
		}

		public Long getServiceId() {
			return serviceId;
		}

		public String getCaption() {
			return caption;
		}
	}
	
	public enum ProductType{
		pkg(1, "套餐"),
		coupon(3, "优惠券");
		private Integer type;
		private String caption;
		
		private ProductType(Integer type, String caption){
			this.type = type;
			this.caption = caption;
		}
		
		public String getCaption(){
			return this.caption;
		}
		
		public Integer getType(){
			return this.type;
		}
	}
	
	public enum CompetitionFormat{
		eleven(1, "11人制"),
		eight(2, "8人制"),
		five(3, "5人制");
		
		private Integer format;
		private String caption;
		
		private CompetitionFormat(Integer format, String caption){
			this.caption = caption;
			this.format = format;
		}
		
		public String getCaption(){
			return this.caption;
		}

		public Integer getFormat() {
			return format;
		}
	}
	
	public enum TeamLocation{
		centreForward(1, "中锋"),
		secondStriker(2, "二前锋"),
		wingForward(3, "边锋"),
		shortLoin(4, "前腰"),
		advanceGuard(5, "前卫"),
		winghalf(6, "边前卫"),
		defendingMidfielder(7, "后腰"),
		outsideDefender(8, "边后卫"),
		centerBack(9, "中后卫"),
		scavenger(10, "清道夫"),
		goalkeeper(11, "门将");
		
		private Integer location;
		private String caption;
		
		private TeamLocation(Integer location, String caption){
			this.caption = caption;
			this.location = location;
		}
		
		public String getCaption(){
			return this.caption;
		}

		public Integer getLocation() {
			return location;
		}
	}
	
	public enum MessageType{
		system(1, "系统消息"),
		game(2, "约球消息"),
		team(3, "球队消息"),
		court(4, "球场消息");
		
		private Integer type;
		private String caption;
		
		private MessageType(Integer type, String caption){
			this.caption = caption;
			this.type = type;
		}
		
		public Integer getType(){
			return this.type;
		}
		public String getCaption(){
			return this.caption;
		}
	}
	
	public enum MemberLog{
		apply(0, "申请加入球队"),
		add(1, "加入球队"),
		del(2, "离开球队"),
		paly(3, "踢一场球"),
		nopass(4, "审核不通过");
		
		private Integer operation;
		private String caption;
		
		private MemberLog(Integer operation, String caption){
			this.operation = operation;
			this.caption = caption;
		}

		public String getCaption() {
			return caption;
		}


		public Integer getOperation() {
			return operation;
		}
	}
	
	public enum DocumentType{
		banner(1, "轮播图"),
		article(2, "文章");
		
		private Integer type;
		private String caption;
		
		private DocumentType(Integer type, String caption){
			this.type = type;
			this.caption = caption;
		}

		public Integer getType() {
			return type;
		}

		public String getCaption() {
			return caption;
		}
	}
	
	public enum UserIdentity{
		background(0, "后台人员"),
		normal(1, "会员"),
		teamManager(2, "管理员"),
		teamManager2(3, "副管理员");
		
		private Integer identity;
		private String caption;
		
		private UserIdentity(Integer identity, String caption){
			this.identity = identity;
			this.caption = caption;
		}
		
		public Integer getIdentity(){
			return this.identity;
		}
		
		public String getCaption(){
			return this.caption;
		}
	}
	
	public enum TeamIdentity{
		coach(1, "教练"),
		doctor(2, "队医"),
		footballer(3, "球员"),
		cheerleaders(4, "啦啦队");
		
		private Integer identity;
		private String caption;
		
		private TeamIdentity(Integer identity, String caption){
			this.identity = identity;
			this.caption = caption;
		}
		
		public Integer getIdentity(){
			return this.identity;
		}
		
		public String getCaption(){
			return this.caption;
		}
	}
	
	public enum SmsTag{
		registration(1, "注册"),
		findpassword(2, "找回密码");
		
		private Integer tag;
		private String caption;
		
		private SmsTag(Integer tag, String caption){
			this.tag = tag;
			this.caption = caption;
		}
		
		public Integer getTag(){
			return this.tag;
		}
		
		public String getCaption(){
			return this.caption;
		}
	}
	
	public static Map<Integer, String> week = new HashMap<Integer, String>(){
		private static final long serialVersionUID = 1L;
		{
			put(1, "周一");
			put(2, "周二");
			put(3, "周三");
			put(4, "周四");
			put(5, "周五");
			put(6, "周六");
			put(7, "周日");
		}
	};
}
