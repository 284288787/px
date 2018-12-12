/**create by liuhua at 2017年6月1日 下午4:00:35**/
package com.booting.service;

import java.util.Map;
import com.star.framework.specification.exception.ArgsException;

public interface SystemWebService {

	public Map<String, Object> login(String username, String password) throws ArgsException;

	public void logout(String token) throws ArgsException;

	public Map<String, Object> register(String username, String passwd, String authcode, String clientid, Integer sourceFrom) throws ArgsException;

	public void checkUsername(String username) throws ArgsException;

	public void changePassword(String username, String newpasswd, String passwd) throws ArgsException;

	public void findPassword(String username, String newpasswd, String code) throws ArgsException;

	public void updateClientid(Long userId, String clientid, Integer sourceFrom);

	public Map<String, Object> getUserInfo(Long userId) throws ArgsException;

	Map<String, Object> getUserInfo(String mobile) throws ArgsException;
}
