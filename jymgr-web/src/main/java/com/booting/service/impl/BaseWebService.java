/**create by liuhua at 2017年7月7日 上午11:35:09**/
package com.booting.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.booting.common.PushInfo;
import com.booting.service.CommonWebService;
import com.booting.system.dto.UserAccountDTO;
import com.booting.system.facade.SystemFacade;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.utils.CglibBeanUtils;

public class BaseWebService {

  @Autowired
  protected SystemFacade systemFacade;
  @Autowired
  protected CommonWebService commonWebService;

  Md5PasswordEncoder encode = new Md5PasswordEncoder();
  
  public UserAccountDTO getUserAccount(Long userId) {
    if (null == userId) {
      return null;
    }
    UserAccountDTO userAccountDTO = systemFacade.getUserAccount(userId);
    return userAccountDTO;
  }
  
  public UserAccountDTO getUserAccount(String account) {
    if (StringUtils.isBlank(account)) {
      return null;
    }
    UserAccountDTO params = new UserAccountDTO();
    params.setAccount(account);
    UserAccountDTO userAccountDTO = systemFacade.getUserAccount(params);
    return userAccountDTO;
  }
  
  public boolean existsAccount(String mobile) {
    UserAccountDTO userAccountDTO = getUserAccount(mobile);
    if (null == userAccountDTO || null == userAccountDTO.getUserId()) {
      return false;
    }
    return true;
  }
  
  public void deleteAccount(String ids) throws ArgsException {
    systemFacade.deleteUserAccount(ids);
  }
  
  public Long insertUserAccount(String mobile, String name, String address, int userType) throws ArgsException {
    if (StringUtils.isBlank(mobile)) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "手机号不能为空");
    }
    UserAccountDTO userAccountDTO = new UserAccountDTO();
    userAccountDTO.setAccount(mobile);
    userAccountDTO.setPassword(encode.encodePassword(mobile.substring(7) + "666", null));
    userAccountDTO.setUserType(userType);
    userAccountDTO.setSourceFrom(3);
    userAccountDTO.setMobile(mobile);
    userAccountDTO.setName(name);
    userAccountDTO.setAddress(address);
    Long userId = this.systemFacade.saveUserAccount(userAccountDTO);
    return userId;
  }
  
  public void updateUserAccount(long userId, String mobile, String name, String address, int userType) throws ArgsException {
    if (StringUtils.isBlank(mobile)) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "手机号不能为空");
    }
    UserAccountDTO userAccountDTO = systemFacade.getUserAccount(userId);
    if (null == userAccountDTO || null == userAccountDTO.getUserId()) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "账号不存在");
    }
    userAccountDTO.setAccount(mobile);
    userAccountDTO.setMobile(mobile);
    userAccountDTO.setUserType(userType);
    userAccountDTO.setName(name);
    userAccountDTO.setAddress(address);
    userAccountDTO.setEnabled(1);
    userAccountDTO.setDeleted(0);
    this.systemFacade.updateUserAccount(userAccountDTO);
  }
  
  protected void writeMessage(PushInfo pushInfo) {
    commonWebService.writeMessage(pushInfo);
  }

  public <T> Map<String, Object> toMap(T object, String dateTimePattern) {
    if (null == object) {
      return null;
    }
    Map<String, Object> map = new HashMap<>();
    CglibBeanUtils.addToMap(object, map, dateTimePattern);
    return map;
  }
}
