/**create by liuhua at 2017年6月1日 下午4:05:57**/
package com.booting.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.star.framework.security.core.StarUserInfo;

public class MemoryLoginInfoUtil {

	private static Map<String, List<String>> loginKeys = new HashMap<>();
	private static Map<String, StarUserInfo> allLoginUser = new HashMap<>();
	private static Md5PasswordEncoder encoder = new Md5PasswordEncoder();
	private static long sessionTime = 24 * 60 * 30 * 6;
	
	public static String login(StarUserInfo userInfo){
		if (null != userInfo && null != userInfo.getUserId() && null != userInfo.getPassword()) {
			String userIdMd5 = getUserIdMD5(userInfo);
			List<String> uuids = null;
			if (loginKeys.containsKey(userIdMd5)) {
				uuids = loginKeys.get(userIdMd5);
				if (null == uuids) {
					uuids = new ArrayList<>();
				}
				if (uuids.size() >= 1) {
					String oldUUID = uuids.remove(0);
					allLoginUser.remove(oldUUID);
				}
			}else{
				uuids = new ArrayList<>();
			}
			String token = UUID.randomUUID().toString();
			uuids.add(token);
			loginKeys.put(userIdMd5, uuids);
			allLoginUser.put(token, userInfo);
			return token;
		}
		return null;
	}
	
	public static void logout(String token){
		if (null != token) {
			StarUserInfo userInfo = allLoginUser.remove(token);
			if (null != userInfo) {
				String userIdMd5 = getUserIdMD5(userInfo);
				if (loginKeys.containsKey(userIdMd5)) {
					List<String> uuids = loginKeys.get(userIdMd5);
					if (null != uuids && uuids.contains(token)) {
						for (int i = 0; i < uuids.size(); i++) {
							if (token.equals(uuids.get(i))) {
								uuids.remove(i);
								break;
							}
						}
						loginKeys.put(userIdMd5, uuids);
					}
				}
			}
		}
	}
	
	public static StarUserInfo getLoginInfo(String token){
		if (null != token) {
			StarUserInfo userInfo = allLoginUser.get(token);
			if (null != userInfo) {
				long loginTime = userInfo.getLoginTime();
				long now = System.currentTimeMillis();
				if (loginTime + sessionTime * 60 * 1000 < now) {
					logout(token);
					userInfo = null;
				}
				return userInfo;
			}
		}
		return null;
	}
	
	public static String getUserIdMD5(StarUserInfo userInfo){
		if (null != userInfo && null != userInfo.getUserId() && null != userInfo.getPassword()) {
			return encoder.encodePassword(userInfo.getUserId().toString(), userInfo.getPassword());
		}
		return null;
	}
}
