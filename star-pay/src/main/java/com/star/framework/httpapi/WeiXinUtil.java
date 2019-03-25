/**create by liuhua at 2017年12月7日 下午1:50:01**/
package com.star.framework.httpapi;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service("weiXinUtil")
public class WeiXinUtil {
	private static final Logger logger = LoggerFactory.getLogger(WeiXinUtil.class);
	
	public static final String appId = "wx18e37c25035345e3";
	private static final String appSecret = "fc37985af81059f461ccb4eb791993c1";

	private static final String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;
	private static final String jsapiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
	private static final String apiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=wx_card";
	private static final String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
	
	private static final String getCardListUrl = "https://api.weixin.qq.com/card/user/getcardlist?access_token=%s";
	private static final String modifystockUrl = "https://api.weixin.qq.com/card/modifystock?access_token=%s";
	private static final String getCardDetailUrl = "https://api.weixin.qq.com/card/get?access_token=%s";
	private static final String checkCardUrl = "https://api.weixin.qq.com/card/code/get?access_token=%s";
	private static final String consumeCardUrl = "https://api.weixin.qq.com/card/code/consume?access_token=%s";
	
	private static AccessToken accessToken = null;
	private static JsapiTicket jsapiTicket = null;
	private static ApiTicket apiTicket = null;

	private static OkHttpClient httpClient = new OkHttpClient();
	private static ObjectMapper mapper = new ObjectMapper();

	public static synchronized String getAccessToken() throws IOException {
		if (null == accessToken || !accessToken.isExpires()) {
//			System.out.println(Thread.currentThread().getName() + "请求微信。。accessToken");
			Request request = new Request.Builder().url(accessTokenUrl).build();
			Response response = httpClient.newCall(request).execute();
			String res = response.body().string();
			accessToken = mapper.readValue(res, AccessToken.class);
			response.close();
		}
		return accessToken.getAccess_token();
	}
	
	public static Map<String, Object> getWxUserInfo(String openId) throws IOException {
		Request request = new Request.Builder().url(String.format(getUserInfoUrl, getAccessToken(), openId)).build();
		Response response = httpClient.newCall(request).execute();
		String temp = response.body().string();
		System.out.println("wx_user_info:" + temp);
		Map<String, Object> map = mapper.readValue(temp, new TypeReference<Map<String, Object>>() {});
		Map<String, Object> result = new HashMap<>();
		boolean bool = false;
		Object nickname = "";
		Object headimgurl = "";
		if (null != map) {
			Object subscribe = map.get("subscribe");
			nickname = map.get("nickname");
			headimgurl = map.get("headimgurl");
			if (null != subscribe) {
				if (Double.valueOf(subscribe.toString()).intValue() != 0) {
					bool = true;
				}
			}
			if (null == nickname) {
				nickname = "";
			}
			if (null == headimgurl) {
				headimgurl = "";
			}
		}
		result.put("subscribe", bool);
		result.put("nickname", nickname);
		result.put("headimgurl", headimgurl);
		return result;
	}
	
	/**
	 * 是否可以核销
	 * @param code
	 * @throws IOException
	 */
	public static boolean canConsume(String code) throws IOException{
//		FormBody formBody = new FormBody.Builder().add("openid", openId).build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody requestBody = RequestBody.create(mediaType, "{\"code\": \""+code+"\", \"check_consume\": false}");
		Request request = new Request.Builder().post(requestBody).url(String.format(checkCardUrl, getAccessToken())).build();
		Response response = httpClient.newCall(request).execute();
		String temp = response.body().string();
		boolean bool = false;
		if (temp.indexOf("card") != -1) {
			Map<String, Object> map = mapper.readValue(temp, new TypeReference<Map<String, Object>>() {});
			bool = Boolean.parseBoolean(map.get("can_consume").toString());
		}
		return bool;
	}
	
	/**
	 * 是否可以核销
	 * return
	 * 0 无效的code
	 * 1 有效的code，并且cardId没有填
	 * 2 有效的code，并且属于cardId
	 * 3 有效的code，但不属于cardId
	 */
	public static String canConsume(String code, List<String> cardIds) throws IOException{
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody requestBody = RequestBody.create(mediaType, "{\"code\": \""+code+"\", \"check_consume\": false}");
		Request request = new Request.Builder().post(requestBody).url(String.format(checkCardUrl, getAccessToken())).build();
		Response response = httpClient.newCall(request).execute();
		String temp = response.body().string();
		String cardIdRes = "";
		@SuppressWarnings("unused")
		int res = 0;
		if (temp.indexOf("card") != -1) {
			Map<String, Object> map = mapper.readValue(temp, new TypeReference<Map<String, Object>>() {});
			boolean bool = Boolean.parseBoolean(map.get("can_consume").toString());
			if (bool) {
				res = 1;
				if (null != cardIds && ! cardIds.isEmpty()) {
					@SuppressWarnings("unchecked")
					Map<String, Object> cardObject = (Map<String, Object>) map.get("card");
					for (String cardId : cardIds) {
						if (StringUtils.isNotBlank(cardId)) {
							if (cardId.equals(cardObject.get("card_id"))) {
								res = 2;
								cardIdRes = cardId;
								break;
							}else{
								res = 3;
							}
						}
					}
				}
			}
		}
		return cardIdRes;
	}
	
	/**
	 * 核销卡券
	 * @param code
	 * @throws IOException
	 */
	public static boolean consumeCard(String code) throws IOException{
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody requestBody = RequestBody.create(mediaType, "{\"code\": \""+code+"\"}");
		Request request = new Request.Builder().post(requestBody).url(String.format(consumeCardUrl, getAccessToken())).build();
		Response response = httpClient.newCall(request).execute();
		String temp = response.body().string();
		System.out.println(String.format("核销卡券，code：%s，result: %s", code, temp));
		if (temp.indexOf("card") != -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 所有卡券
	 * @param openId
	 * @throws IOException
	 */
	public static CardList getCardList(String openId) throws IOException{
//		FormBody formBody = new FormBody.Builder().add("openid", openId).build();
		MediaType mediaType = MediaType.parse("application/json");
		RequestBody requestBody = RequestBody.create(mediaType, "{\"openid\": \""+openId+"\"}");
		Request request = new Request.Builder().post(requestBody).url(String.format(getCardListUrl, getAccessToken())).build();
		Response response = httpClient.newCall(request).execute();
		String temp = response.body().string();
		System.out.println(temp);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		CardList cardList = mapper.readValue(temp, new TypeReference<CardList>() {});
		return cardList;
	}
	
	/**
	 * 卡详情
	 * @param cardId
	 * @return
	 * @throws IOException
	 */
	public static Map<String, Object> getCardDetail(String cardId) throws IOException{
		Map<String, Object> res = new HashMap<>();
		MediaType mediaType = MediaType.parse("application/json");
		String data = "{\"card_id\": \""+cardId+"\"}";
		RequestBody requestBody = RequestBody.create(mediaType, data);
		Request request = new Request.Builder().post(requestBody).url(String.format(getCardDetailUrl, getAccessToken())).build();
		Response response = httpClient.newCall(request).execute();
		String temp = response.body().string();
		if (temp.indexOf("card") != -1) {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Map<String, Object> map = mapper.readValue(temp, new TypeReference<Map<String, Object>>() {});
			String cardStr = mapper.writeValueAsString(map.get("card"));
			System.out.println(cardStr);
			map = mapper.readValue(cardStr, new TypeReference<Map<String, Object>>() {});
			//团购券：GROUPON; 折扣券：DISCOUNT; 礼品券：GIFT; 代金券：CASH; 通用券：GENERAL_COUPON; 会员卡：MEMBER_CARD; 
			//景点门票：SCENIC_TICKET ；电影票：MOVIE_TICKET； 飞机票：BOARDING_PASS； 会议门票：MEETING_TICKET； 汽车票：BUS_TICKET;
			String cardType = map.get("card_type").toString();
			Object object = map.get(cardType.toLowerCase());
			if (null == object) {
				System.out.println("券详情错误：" + object);
			}
			cardStr = mapper.writeValueAsString(object);
			map = mapper.readValue(cardStr, new TypeReference<Map<String, Object>>() {});
			@SuppressWarnings("unchecked")
			Map<String, Object> obj = (Map<String, Object>) map.get("base_info");
			res.put("cardType", cardType);
			res.put("title", obj.get("title"));
			res.put("getLimit", obj.get("get_limit"));
			switch (cardType) {
			case "GROUPON":    //团购券
				String detail = map.get("deal_detail").toString();                 //团购券专用，团购详情
				res.put("keys", new String[]{"detail"});
				res.put("detail", detail);
				break;
			case "DISCOUNT":   //折扣券
				int discount = Integer.parseInt(map.get("discount").toString());   //折扣券专用，表示打折额度（百分比）。填30就是七折
				res.put("keys", new String[]{"discount"});
				res.put("discount", discount);
				break;
			case "GIFT":       //礼品券 兑换券
				String gift = map.get("gift").toString();                          //兑换券专用，填写兑换内容的名称
				res.put("keys", new String[]{"gift"});
				res.put("gift", gift);
				break;
			case "CASH":       //代金券
				int leastCost = Integer.parseInt(map.get("least_cost").toString());   //起用金额 分
				int reduceCost = Integer.parseInt(map.get("reduce_cost").toString()); //减免金额 分
				res.put("keys", new String[]{"leastCost", "reduceCost"});
				res.put("leastCost", leastCost);
				res.put("reduceCost", reduceCost);
				break;
			case "GENERAL_COUPON":   //通用券  
				String general = map.get("default_detail").toString();                  //优惠券专用，填写优惠详情
				res.put("keys", new String[]{"general"});
				res.put("general", general);
				break;
			default:
				System.out.println("不支持的卡券类型");
				break;
			}
		}
		return res;
	}
	
	/**
	 * 修改库存
	 * @param cardId
	 * @param num
	 * @throws IOException
	 */
	public static void modifystock(String cardId, int num) throws IOException{
		String key = "increase_stock_value";
		if (num < 0) {
			key = "reduce_stock_value";
		}
		MediaType mediaType = MediaType.parse("application/json");
		String data = "{\"card_id\": \""+cardId+"\", \""+key+"\": "+Math.abs(num)+"}";
		RequestBody requestBody = RequestBody.create(mediaType, data);
		Request request = new Request.Builder().post(requestBody).url(String.format(modifystockUrl, getAccessToken())).build();
		Response response = httpClient.newCall(request).execute();
		String temp = response.body().string();
		System.out.println(temp);
	}
	
	public static void main(String[] args) throws IOException {
		getCardList("ozT4zw5HIGCokurpgLIlR3ItrNWE");
//		modifystock("pzT4zw4L3dnxvatggCwa0M8FdHIY", 10);
//		Map<String, Object> map = getCardDetail("pzT4zw7dU_LVes-WuJ0s5t8BJd6E");
//		System.out.println(map);
//		System.out.println(map.get("cardType"));
//		String[] keys = (String[]) map.get("keys");
//		System.out.println(keys);
//		for (String key : keys) {
//			System.out.println(key + "\t" + map.get(key));
//		}
//		System.out.println("11->" + canConsume("999869921918", Arrays.asList("pzT4zw7dU_LVes-WuJ0s5t8BJd6E")));
	}
	private synchronized String getJsapiTicket() throws IOException {
		if (null == jsapiTicket || !jsapiTicket.isExpires()) {
			String accessToken = getAccessToken();
//			System.out.println(Thread.currentThread().getName() + "请求微信。。JsapiTicket");
			String url = String.format(jsapiTicketUrl, accessToken);
			Request request = new Request.Builder().url(url).build();
			Response response = httpClient.newCall(request).execute();
			String res = response.body().string();
			jsapiTicket = mapper.readValue(res, JsapiTicket.class);
			response.close();
		}
		return jsapiTicket.getTicket();
	}

	public synchronized String getApiTicket() throws IOException {
		if (null == apiTicket || !apiTicket.isExpires()) {
			String accessToken = getAccessToken();
//			System.out.println(Thread.currentThread().getName() + "请求微信。。JsapiTicket");
			String url = String.format(apiTicketUrl, accessToken);
			Request request = new Request.Builder().url(url).build();
			Response response = httpClient.newCall(request).execute();
			String res = response.body().string();
			apiTicket = mapper.readValue(res, ApiTicket.class);
			response.close();
		}
		return apiTicket.getTicket();
	}
	
	public WeixinConfig weixinConfig(String url) {
		try {
			String jsapiTicket = getJsapiTicket();
			String noncestr = UUID.randomUUID().toString().replace("-", "");
			long now = System.currentTimeMillis() / 1000;
			String string1 = "jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s";
			string1 = String.format(string1, jsapiTicket, noncestr, now, url);
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			String signature = byteToHex(crypt.digest());
			WeixinConfig config = new WeixinConfig(noncestr, now, signature);
			return config;
		} catch (IOException e) {
			logger.error("", e);
		} catch (NoSuchAlgorithmException e) {
			logger.error("", e);
		}
		return null;
	}

	private String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

//	public static void main(String[] args) throws IOException {
//		WeixinConfig config = WeiXinUtil.weixinConfig("http://m1.cnhnkj.cn");
//		System.out.println(config.getSignature());
//		for (int i = 0; i < 1000; i++) {
//			new Thread(new Temp()).start();
//		}
//	}
}

//class Temp implements Runnable{
//
//	@Override
//	public void run() {
//		int len = 100;
//		for (int i = 0; i < len; i++) {
//			WeixinConfig config = WeiXinUtil.weixinConfig("http://m1.cnhnkj.cn");
//		}
//	}
//}

class ApiTicket {
	private Integer errcode;
	private String errmsg;
	private String ticket;
	private Long expires_in;
	private Long createTime = System.currentTimeMillis();

	/**
	 * 是否有效 true 有效 false 无效
	 * 
	 * @return
	 */
	public boolean isExpires() {
		if (createTime + expires_in * 1000 > System.currentTimeMillis()) {
			return true;
		}
		return false;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public String getTicket() {
		return ticket;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
}

class JsapiTicket {
	private Integer errcode;
	private String errmsg;
	private String ticket;
	private Long expires_in;
	private Long createTime = System.currentTimeMillis();

	/**
	 * 是否有效 true 有效 false 无效
	 * 
	 * @return
	 */
	public boolean isExpires() {
		if (createTime + expires_in * 1000 > System.currentTimeMillis()) {
			return true;
		}
		return false;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public String getTicket() {
		return ticket;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
}

class AccessToken {
	private String access_token;
	private Long expires_in;
	private Long createTime = System.currentTimeMillis();

	/**
	 * 是否有效 true 有效 false 无效
	 * 
	 * @return
	 */
	public boolean isExpires() {
		if (createTime + expires_in * 1000 > System.currentTimeMillis()) {
			return true;
		}
		return false;
	}

	public String getAccess_token() {
		return access_token;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
}
