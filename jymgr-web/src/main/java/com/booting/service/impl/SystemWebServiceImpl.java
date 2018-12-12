/**create by liuhua at 2017年6月1日 下午4:01:09**/
package com.booting.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.booting.common.CommonConstants.SmsTag;
import com.booting.common.CommonConstants.UserIdentity;
import com.booting.common.MemoryLoginInfoUtil;
import com.booting.pub.dto.SmsIdentityDTO;
import com.booting.pub.service.SmsIdentityService;
import com.booting.service.SystemWebService;
import com.booting.system.dto.UserAccountDTO;
import com.booting.system.dto.UserInfoDTO;
import com.booting.system.facade.SystemFacade;
import com.star.framework.security.core.StarAuthenticationProvider;
import com.star.framework.security.core.StarUserInfo;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.utils.CglibBeanUtils;

@Service("systemWebService")
public class SystemWebServiceImpl implements SystemWebService {

    private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
	@Autowired
	private SystemFacade systemFacade;
	@Autowired
	private SmsIdentityService smsIdentityService;
	@Autowired
	private StarAuthenticationProvider starAuthenticationProvider;
	
	public Map<String, Object> login(String username, String password) throws ArgsException {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, starAuthenticationProvider.getPasswordEncoder().encodePassword(password, null));
		authentication = starAuthenticationProvider.authenticate(authentication); // 登陆
		StarUserInfo userInfo = (StarUserInfo) authentication.getPrincipal();
		String token = MemoryLoginInfoUtil.login(userInfo);
		Map<String, Object> data = new HashMap<>();
		if (null != token) {
			data.put("name", userInfo.getName());
			data.put("account", userInfo.getUsername());
			data.put("identity", userInfo.getIdentity());
			data.put("packages", userInfo.getUserPackages());
			data.put("services", userInfo.getUserServices());
			data.put("token", token);
		}else{
			throw new ArgsException(FailureCode.ERR_000.getCode(), "登录失败");
		}
		return data;
	}
	
	public void logout(String token) throws ArgsException{
		if (StringUtils.isBlank(token)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		StarUserInfo userInfo = MemoryLoginInfoUtil.getLoginInfo(token);
		if (null == userInfo) {
			throw new ArgsException(FailureCode.ERR_000.getCode(), "未登录");
		}
		MemoryLoginInfoUtil.logout(token);
	}
	
	public Map<String, Object> login2(String username, String password) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, starAuthenticationProvider.getPasswordEncoder().encodePassword(password, null));
		authentication = starAuthenticationProvider.authenticate(authentication); // 登陆
		SecurityContextHolder.getContext().setAuthentication(authentication);
		StarUserInfo userInfo = (StarUserInfo) authentication.getPrincipal();
		String token = MemoryLoginInfoUtil.login(userInfo);
		Map<String, Object> data = new HashMap<>();
		if (null != token) {
			data.put("name", userInfo.getName());
			data.put("account", userInfo.getUsername());
			data.put("token", token);
		}
//      tokenBasedRememberMeServices.onLoginSuccess(request, response, token); // 使用 remember me
		// 重定向到登陆前的页面
//    	SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
		return data;
	}

	@Override
	public void checkUsername(String username) throws ArgsException {
		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setAccount(username);
		UserAccountDTO user = systemFacade.getUserAccount(userAccountDTO);
		if (null != user && null != user.getAccount()) {
			throw new ArgsException("101", "手机号已被注册");
		}
	}
	
	@Override
	public Map<String, Object> register(String username, String passwd, String code, String clientid, Integer sourceFrom) throws ArgsException {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(passwd) || StringUtils.isBlank(code) || StringUtils.isBlank(clientid) || null == sourceFrom) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		checkUsername(username);
		//校验短信
		SmsIdentityDTO dto = getSmsIdentity(username, SmsTag.registration.getTag());
		if (null == dto || ! code.equals(dto.getCode())) {
			throw new ArgsException("102", "验证码错误");
		}
		smsIdentityService.delete(dto.getId());
		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setAccount(username);
		userAccountDTO.setPassword(passwordEncoder.encodePassword(passwd, null));
		userAccountDTO.setIdentity(UserIdentity.member.getIdentity());
		Long userId = this.systemFacade.saveUserAccount(userAccountDTO);
		updateClientid(userId, clientid, sourceFrom);
		return login(username, passwd);
	}
	
	public SmsIdentityDTO getSmsIdentity(String phone, Integer tag){
		SmsIdentityDTO dto = new SmsIdentityDTO();
		dto.setPhone(phone);
		dto.setTag(tag);
		List<SmsIdentityDTO> list = smsIdentityService.getSimpleList(dto);
		if (null != list && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void changePassword(String username, String newpasswd, String passwd) throws ArgsException {
		System.out.println("\t\t" + username + " " + newpasswd + " " + passwd);
		if (StringUtils.isBlank(username) || StringUtils.isBlank(passwd) || StringUtils.isBlank(newpasswd)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setAccount(username);
		userAccountDTO.setPassword(this.passwordEncoder.encodePassword(passwd, null));
		userAccountDTO = this.systemFacade.getUserAccount(userAccountDTO);
		if (null != userAccountDTO) {
			userAccountDTO.setPassword(this.passwordEncoder.encodePassword(newpasswd, null));
			this.systemFacade.updateUserAccount(userAccountDTO);
		}else{
			throw new ArgsException("103", "账号/密码错误");
		}
	}

	@Override
	public void findPassword(String username, String newpasswd, String code) throws ArgsException {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(newpasswd) || StringUtils.isBlank(newpasswd)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		//校验短信
		SmsIdentityDTO dto = getSmsIdentity(username, SmsTag.findpassword.getTag());
		if (null == dto || ! code.equals(dto.getCode())) {
			throw new ArgsException("102", "验证码错误");
		}
		smsIdentityService.delete(dto.getId());
		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setAccount(username);
		userAccountDTO = this.systemFacade.getUserAccount(userAccountDTO);
		if (null != userAccountDTO) {
			userAccountDTO.setPassword(passwordEncoder.encodePassword(newpasswd, null));
			this.systemFacade.updateUserAccount(userAccountDTO);
		}else{
			throw new ArgsException("104", "账号不存在");
		}
	}

	@Override
	public void updateClientid(Long userId, String clientid, Integer sourceFrom) {
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setUserId(userId);
		userInfoDTO.setClientid(clientid);
		userInfoDTO.setSourceFrom(sourceFrom);
		this.systemFacade.updateUserInfo(userInfoDTO);
	}
	
	@Override
	public Map<String, Object> getUserInfo(Long userId) throws ArgsException {
		UserInfoDTO userInfoDTO = this.systemFacade.getUserInfo(userId);
		if (null == userInfoDTO) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		Map<String, Object> data = new HashMap<>();
		CglibBeanUtils.addToMap(userInfoDTO, data, "yyyy-MM-dd HH:mm:ss");
		data.remove("userIds");
		return data;
	}

	@Override
	public Map<String, Object> getUserInfo(String mobile) throws ArgsException {
		UserInfoDTO userInfo = new UserInfoDTO();
		userInfo.setMobile(mobile);
		userInfo = this.systemFacade.getUserInfo(userInfo);
		if (null == userInfo) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		Map<String, Object> data = new HashMap<>();
		CglibBeanUtils.addToMap(userInfo, data, "yyyy-MM-dd HH:mm:ss");
		data.remove("userIds");
		return data;
	}

}
