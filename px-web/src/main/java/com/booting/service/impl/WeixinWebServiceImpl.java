/**create by liuhua at 2017年12月10日 下午4:56:29**/
package com.booting.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.booting.vo.AccessToken;
import com.star.framework.httpapi.WeiXinUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service("weixinWebServiceImpl")
public class WeixinWebServiceImpl {

	public static final String appId = "wx18e37c25035345e3";
	public static final String appSecret = "fc37985af81059f461ccb4eb791993c1";

	private final String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret + "&code=%s&grant_type=authorization_code";
	private final String refreshAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=" + appId + "&grant_type=refresh_token&refresh_token=%s";
	private static Map<String, AccessToken> accessTokens = new HashMap<>();
	private static OkHttpClient client = new OkHttpClient();
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String requestGet(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);
//		Headers responseHeaders = response.headers();
//		for (int i = 0; i < responseHeaders.size(); i++) {
//			System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//		}

		return response.body().string();
	}

	public AccessToken getAccessToken(String code) throws IOException {
		AccessToken accessToken = accessTokens.get(code);
		if (null == accessToken) {
			String temp = requestGet(String.format(accessTokenUrl, code));
			accessToken = mapper.readValue(temp, AccessToken.class);
			Map<String, Object> info = WeiXinUtil.getWxUserInfo(accessToken.getOpenid());
			accessToken.setSubscribe((boolean) info.get("subscribe"));
			accessToken.setNickname(info.get("nickname").toString());
			accessToken.setHeadimgurl(info.get("headimgurl").toString());
			accessTokens.put(code, accessToken);
		}else if (! accessToken.isExpires()) {
			String temp = requestGet(String.format(refreshAccessTokenUrl, accessToken.getRefresh_token()));
			accessToken = mapper.readValue(temp, AccessToken.class);
			Map<String, Object> info = WeiXinUtil.getWxUserInfo(accessToken.getOpenid());
			accessToken.setSubscribe((boolean) info.get("subscribe"));
			accessToken.setNickname(info.get("nickname").toString());
			accessToken.setHeadimgurl(info.get("headimgurl").toString());
			accessTokens.put(code, accessToken);
		}
		
		System.out.println(accessToken);
		return accessToken;
	}
}