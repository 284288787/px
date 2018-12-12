/**create by liuhua at 2018年2月28日 下午2:56:38**/
package com.booting.common;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.booting.member.dto.MemberDTO;

public class MemoryLoginCache {
	
	private static Map<Long, String> mobileToken = new HashMap<>();
	private static Map<String, MemberDTO> allLoginUser = new HashMap<>();
//	private static long days = 30; //30天
	private static String tokenKey = "sys_user_info_token_key";
	
	public static void login(MemberDTO member){
		String token = mobileToken.get(member.getMemberId());
		if (null == token) {
			token = UUID.randomUUID().toString().replace("-", "");
			allLoginUser.put(token, member);
			mobileToken.put(member.getMemberId(), token);
		}
		member.setToken(token);
	}
	
	public static MemberDTO getLoginInfo(String token){
		MemberDTO member = allLoginUser.get(token);
//		if (null != member) {
//			long loginTime = member.getLoginTime().getTime();
//			long now = System.currentTimeMillis();
//			if (loginTime + days * 24 * 60 * 60 * 1000 < now) {
//				logout(token);
//				member = null;
//			}
//		}
		return member;
	}

	public static void logout(String token) {
		MemberDTO member = allLoginUser.remove(token);
		mobileToken.remove(member.getMemberId());
	}
	
	public static void updateBallNums(Long memberId, Integer num){
		String token = mobileToken.get(memberId);
		if (null != token) {
			MemberDTO member = allLoginUser.get(token);
			if (null != member) {
				member.setBallNums(num);
			}
		}
	}
	
	public static void updateGoldNums(Long memberId, Integer gold){
		String token = mobileToken.get(memberId);
		if (null != token) {
			MemberDTO member = allLoginUser.get(token);
			if (null != member) {
				member.setGoldNums(gold);
			}
		}
	}
	
	public static void updatePassword(Long memberId, String password){
		String token = mobileToken.get(memberId);
		if (null != token) {
			MemberDTO member = allLoginUser.get(token);
			if (null != member) {
				member.setPassword(password);
			}
		}
	}
	
	public static void updateHeadPic(Long memberId, String headPic){
		String token = mobileToken.get(memberId);
		if (null != token) {
			MemberDTO member = allLoginUser.get(token);
			if (null != member) {
				member.setHeadPic(headPic);
			}
		}
	}
	
	public static void updateNickname(Long memberId, String nickname){
		String token = mobileToken.get(memberId);
		if (null != token) {
			MemberDTO member = allLoginUser.get(token);
			if (null != member) {
				member.setName(nickname);
			}
		}
	}

	public static MemberDTO getLoginInfo(HttpServletRequest request) {
		if (null == request) {
			return null;
		}
		String token = null;
		Cookie[] cookies = request.getCookies();
		if (null != cookies && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if(tokenKey.equals(cookie.getName())){
					token = cookie.getValue();
				}
			}
		}
		if (StringUtils.isNotBlank(token)) {
			return getLoginInfo(token);
		}
		return null;
	}
}
