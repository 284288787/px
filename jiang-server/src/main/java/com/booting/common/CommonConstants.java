/**create by liuhua at 2017年6月2日 上午9:26:40**/
package com.booting.common;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonConstants {

	/**是否赠送保险券**/
	public static boolean zbxq = true;
	
	public static void main(String[] args) {
		int i = standard_3_1.run10.getScore(10.2);
		System.out.println(i);
		int j = standard_3_1.balance.getScore(30);
		System.out.println(j);
	}
	
	public static Map<String, Physical> standard = new HashMap<String, Physical>(){
		private static final long serialVersionUID = 1L;
		{
			Physical physical = new Physical();
			physical.add("run10", new Temp(10000, 12.8, 1), new Temp(12.8, 10.2, 2), new Temp(10.2, 9.1, 3), new Temp(9.0, 8.0, 4), new Temp(-1, 8.0, 5));
			physical.add("jump", new Temp(-1, 30, 1), new Temp(30, 43, 2), new Temp(43, 59, 3), new Temp(59, 76, 4), new Temp(76, 10000, 5));
			physical.add("throwTennis", new Temp(-1, 2.0, 1), new Temp(2.0, 3.0, 2), new Temp(3.0, 4.0, 3), new Temp(4.0, 5.5, 4), new Temp(5.5, 10000, 5));
			physical.add("doubleJump", new Temp(10000, 19.6, 1), new Temp(19.6, 13.0, 2), new Temp(13.0, 9.1, 3), new Temp(9.1, 6.5, 4), new Temp(6.5, -1, 5));
			physical.add("sitReach", new Temp(-1, 4.9, 1), new Temp(4.9, 8.6, 2), new Temp(8.6, 11.7, 3), new Temp(11.7, 15.0, 4), new Temp(15.0, 10000, 5));
			physical.add("balance", new Temp(10000, 30.0, 1), new Temp(30.0, 16.8, 2), new Temp(16.8, 10.5, 3), new Temp(10.5, 6.5, 4), new Temp(6.5, -1, 5));
			put("3_1", physical);
		}
	};
	
	/**三岁男**/
	public enum standard_3_1 implements Standard {
		run10(new Temp(10000, 12.8, 1), new Temp(12.8, 10.2, 2), new Temp(10.2, 9.1, 3), new Temp(9.0, 8.0, 4), new Temp(-1, 8.0, 5)),
		jump(new Temp(-1, 30, 1), new Temp(30, 43, 2), new Temp(43, 59, 3), new Temp(59, 76, 4), new Temp(76, 10000, 5)),
		throwTennis(new Temp(-1, 2.0, 1), new Temp(2.0, 3.0, 2), new Temp(3.0, 4.0, 3), new Temp(4.0, 5.5, 4), new Temp(5.5, 10000, 5)),
		doubleJump(new Temp(10000, 19.6, 1), new Temp(19.6, 13.0, 2), new Temp(13.0, 9.1, 3), new Temp(9.1, 6.5, 4), new Temp(6.5, -1, 5)),
		sitReach(new Temp(-1, 4.9, 1), new Temp(4.9, 8.6, 2), new Temp(8.6, 11.7, 3), new Temp(11.7, 15.0, 4), new Temp(15.0, 10000, 5)),
		balance(new Temp(10000, 30.0, 1), new Temp(30.0, 16.8, 2), new Temp(16.8, 10.5, 3), new Temp(10.5, 6.5, 4), new Temp(6.5, -1, 5));
		
		private Temp score1;
		private Temp score2;
		private Temp score3;
		private Temp score4;
		private Temp score5;
		
		private standard_3_1(Temp score1, Temp score2, Temp score3, Temp score4, Temp score5) {
			this.score1 = score1;
			this.score2 = score2;
			this.score3 = score3;
			this.score4 = score4;
			this.score5 = score5;
		}
		
		public int getScore(double value){
			int v1 = this.score1.getScore(value);
			int v2 = this.score2.getScore(value);
			int v3 = this.score3.getScore(value);
			int v4 = this.score4.getScore(value);
			int v5 = this.score5.getScore(value);
			return v1 + v2 + v3 + v4 + v5;
		}
	}
	
	/**三岁女**/
	public enum standard_3_0 implements Standard {
		run10(new Temp(10000, 12.8, 1), new Temp(12.8, 10.2, 2), new Temp(10.2, 9.1, 3), new Temp(9.0, 8.0, 4), new Temp(-1, 8.0, 5)),
		jump(new Temp(-1, 30, 1), new Temp(30, 43, 2), new Temp(43, 59, 3), new Temp(59, 76, 4), new Temp(76, 10000, 5)),
		throwTennis(new Temp(-1, 2.0, 1), new Temp(2.0, 3.0, 2), new Temp(3.0, 4.0, 3), new Temp(4.0, 5.5, 4), new Temp(5.5, 10000, 5)),
		doubleJump(new Temp(10000, 19.6, 1), new Temp(19.6, 13.0, 2), new Temp(13.0, 9.1, 3), new Temp(9.1, 6.5, 4), new Temp(6.5, -1, 5)),
		sitReach(new Temp(-1, 4.9, 1), new Temp(4.9, 8.6, 2), new Temp(8.6, 11.7, 3), new Temp(11.7, 15.0, 4), new Temp(15.0, 10000, 5)),
		balance(new Temp(10000, 30.0, 1), new Temp(30.0, 16.8, 2), new Temp(16.8, 10.5, 3), new Temp(10.5, 6.5, 4), new Temp(6.5, -1, 5));
		
		private Temp score1;
		private Temp score2;
		private Temp score3;
		private Temp score4;
		private Temp score5;
		
		private standard_3_0(Temp score1, Temp score2, Temp score3, Temp score4, Temp score5) {
			this.score1 = score1;
			this.score2 = score2;
			this.score3 = score3;
			this.score4 = score4;
			this.score5 = score5;
		}
		
		public int getScore(double value){
			int v1 = this.score1.getScore(value);
			int v2 = this.score2.getScore(value);
			int v3 = this.score3.getScore(value);
			int v4 = this.score4.getScore(value);
			int v5 = this.score5.getScore(value);
			return v1 + v2 + v3 + v4 + v5;
		}
	}
	
	public static String getAge(Date birth){
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.setTime(birth);
		int yearBirth = calendar.get(Calendar.YEAR);
		int monthBirth = calendar.get(Calendar.MONTH);
		int dayBirth = calendar.get(Calendar.DAY_OF_MONTH);
		int age = year - yearBirth;
		String temp = "";
		if (month <= monthBirth) {
            if (month == monthBirth) {
                if (day < dayBirth) {
                	age--;
                	temp = "5";
                }
            }else{
            	if (monthBirth + 6 - 12 >= month) {
            		if (monthBirth + 6 - 12 == month) {
						if (day <= dayBirth) {
							temp = "5";
						}
					}else{
						temp = "5";
					}
				}
                age--;  
            }  
        }
		return age + temp;
	}
	
	public static Map<String, PushInfo> smsNotes = new HashMap<String, PushInfo>(){
		private static final long serialVersionUID = 1L;
		{
			put("code_sms", new PushInfo("SMS_77245049", "{\"code\":\"%s\"}"));
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
		member(1, "会员");
		
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
	
	
	public enum SmsTag{
		registration(-1, "注册"),
		findpassword(-2, "找回密码"),
		bindMobile(1, "绑定手机"),
		apply(2, "培训项目报名");
		
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
	
	public enum ParentType{
		father(1, "爸爸", "儿子", "女儿"),
		monther(2, "妈妈", "儿子", "女儿"),
		grandpa(3, "爷爷", "孙子", "孙女"),
		grandma(4, "奶奶", "孙子", "孙女"),
		grandfather(5, "外公", "外孙", "外孙女"),
		grandmother(6, "外婆", "外孙", "外孙女"),
		other(7, "其他", "长辈", "长辈");
		
		private int type;
		private String name;
		private String relation1;
		private String relation2;
		
		private ParentType(int type, String name, String relation1, String relation2) {
			this.type = type;
			this.name = name;
			this.relation1 = relation1;
			this.relation2 = relation2;
		}

		public int getType() {
			return type;
		}

		public String getName() {
			return name;
		}

		public String getRelation1() {
			return relation1;
		}

		public String getRelation2() {
			return relation2;
		}
	}
}
