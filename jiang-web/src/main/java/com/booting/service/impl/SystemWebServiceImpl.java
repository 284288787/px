/**create by liuhua at 2017年6月1日 下午4:01:09**/
package com.booting.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.booting.common.CommonConstants;
import com.booting.common.CommonConstants.SmsTag;
import com.booting.common.CommonConstants.UserIdentity;
import com.booting.common.CommonConstants.UserService;
import com.booting.common.MemoryLoginInfoUtil;
import com.booting.pkg.dto.AttributeDTO;
import com.booting.pkg.dto.PackageAttributeValueDTO;
import com.booting.pkg.dto.PackageDTO;
import com.booting.pkg.dto.PackageServiceRelationDTO;
import com.booting.pkg.dto.ServiceAttributeRelationDTO;
import com.booting.pkg.dto.ServiceDTO;
import com.booting.pkg.dto.UseServiceDetailDTO;
import com.booting.pkg.dto.UserAttributeDTO;
import com.booting.pkg.dto.UserPackageDTO;
import com.booting.pkg.dto.UserServiceDTO;
import com.booting.pkg.entity.PackageAttributeValueEntity;
import com.booting.pkg.entity.PackageServiceRelationEntity;
import com.booting.pkg.entity.ServiceAttributeRelationEntity;
import com.booting.pkg.entity.UserAttributeEntity;
import com.booting.pkg.facade.PackageFacade;
import com.booting.pub.dto.SmsIdentityDTO;
import com.booting.pub.service.SmsIdentityService;
import com.booting.service.SystemWebService;
import com.booting.system.dto.UserAccountDTO;
import com.booting.system.dto.UserInfoDTO;
import com.booting.system.facade.SystemFacade;
import com.google.gson.reflect.TypeToken;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.security.core.StarAuthenticationProvider;
import com.star.framework.security.core.StarUserInfo;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.PageInfo;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.utils.CglibBeanUtils;

@Service("systemWebService")
public class SystemWebServiceImpl implements SystemWebService {

    private Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
	@Autowired
	private SystemFacade systemFacade;
	@Autowired
	private SmsIdentityService smsIdentityService;
	@Autowired
	private PackageFacade packageFacade;
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
	public List<PackageDTO> getPackage(PackageDTO packageDTO) {
		List<PackageDTO> list = packageFacade.getPackageList(packageDTO);
		List<Map<String, Object>> res = new ArrayList<>();
		for (PackageDTO pkg : list) {
			int count1001 = 0;
			List<ServiceDTO> services = new ArrayList<>();
			PackageServiceRelationDTO packageServiceRelationDTO = new PackageServiceRelationDTO();
			packageServiceRelationDTO.setPackageId(pkg.getPackageId());
			List<PackageServiceRelationDTO> relations = packageFacade.getPackageServiceRelationList(packageServiceRelationDTO);
			for (PackageServiceRelationDTO packageServiceRelation : relations) {
				PackageAttributeValueDTO packageAttributeValueDTO = new PackageAttributeValueDTO();
				packageAttributeValueDTO.setPackageId(packageServiceRelation.getPackageId());
				packageAttributeValueDTO.setServiceId(packageServiceRelation.getServiceId());
				ServiceDTO service = packageFacade.getService(packageServiceRelation.getServiceId());
				List<AttributeDTO> attributes = new ArrayList<>();
				List<PackageAttributeValueDTO> values = packageFacade.getPackageAttributeValueList(packageAttributeValueDTO);
				for (PackageAttributeValueDTO packageAttributeValue : values) {
					AttributeDTO attribute = packageFacade.getAttribute(packageAttributeValue.getAttributeId());
					attribute.setAttributeValue(packageAttributeValue.getAttributeValue());
					attributes.add(attribute);
				}
				if (packageServiceRelation.getServiceId().longValue() == UserService.ball_ticket.getServiceId()) {
					count1001 = packageServiceRelation.getCount();
				}
				service.setAttributes(attributes);
				service.setCount(packageServiceRelation.getCount());
				services.add(service);
			}
			//加一个优惠券，送保险券
			if (CommonConstants.zbxq) {
				ServiceDTO yh = new ServiceDTO();
				yh.setCount(count1001 * 8);
				yh.setDescription("赠送保险券数量：" + count1001 + " * 8张保险券(一次用8张)");
				yh.setEnabled(1);
				yh.setServiceId(UserService.insurance_ticket_z.getServiceId());
				yh.setServiceName("保险券[赠]");
				services.add(yh);
			}
			pkg.setServices(services);
			Map<String, Object> map = new HashMap<>();
			CglibBeanUtils.addToMap(pkg, map, "yyyy-MM-dd HH:mm:ss");
			map.remove("packageIds");
			res.add(map);
		}
		
		return list;
	}

	public PackageDTO getPackage(Long packageId){
		if (null == packageId) {
			return null;
		}
		PackageDTO packageDTO = new PackageDTO();
		packageDTO.setPackageId(packageId);
		List<PackageDTO> packages = getPackage(packageDTO);
		if (null == packages || packages.isEmpty()) {
			return null;
		}
		return packages.get(0);
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
	public List<ServiceDTO> getService(ServiceDTO serviceDTO) {
		List<ServiceDTO> services = packageFacade.getServiceList(serviceDTO);
		if (null != services && services.size() > 0) {
			for (ServiceDTO service : services) {
//				PackageServiceRelationDTO packageServiceRelationDTO = new PackageServiceRelationDTO();
//				packageServiceRelationDTO.setServiceId(service.getServiceId());
//				packageServiceRelationDTO = packageFacade.getPackageServiceRelation(packageServiceRelationDTO);
//				service.setCount(packageServiceRelationDTO.getCount());
				ServiceAttributeRelationDTO serviceAttributeRelationDTO = new ServiceAttributeRelationDTO();
				serviceAttributeRelationDTO.setServiceId(service.getServiceId());
				List<AttributeDTO> attributes = new ArrayList<>();
				List<ServiceAttributeRelationDTO> relations = packageFacade.getServiceAttributeRelationList(serviceAttributeRelationDTO);
				for (ServiceAttributeRelationDTO serviceAttributeRelation : relations) {
					AttributeDTO attribute = packageFacade.getAttribute(serviceAttributeRelation.getAttributeId());
					attributes.add(attribute);
				}
				service.setAttributes(attributes);
			}
		}
		return services;
	}

	@Override
	public List<AttributeDTO> getAttribute(AttributeDTO attributeDTO) {
		List<AttributeDTO> services = packageFacade.getAttributeList(attributeDTO);
		return services;
	}

	@Override
	public void saveServOfPackage(Long packageId, String services, String attrs) throws ArgsException {
		if (null == packageId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		PackageServiceRelationDTO dto = new PackageServiceRelationDTO();
		dto.setPackageId(packageId);
		this.packageFacade.deletePackageServiceRelationBySql(dto);
		if (StringUtils.isNotBlank(services)) {
			Type type = new TypeToken<List<PackageServiceRelationEntity>>() {}.getType();
			List<PackageServiceRelationEntity> entities = ParamHandler.gson.fromJson(services, type);
			for (PackageServiceRelationEntity packageServiceRelationEntity : entities) {
				packageServiceRelationEntity.setPackageId(packageId);
				packageServiceRelationEntity.setCreateTime(new Date());
			}
			this.packageFacade.batchSavePackageServiceRelation(entities);
		}
		if (null != attrs) {
			PackageAttributeValueDTO valueDTO = new PackageAttributeValueDTO();
			valueDTO.setPackageId(packageId);
			this.packageFacade.deletePackageAttributeValueBySql(valueDTO);
			Type type = new TypeToken<List<PackageAttributeValueEntity>>() {}.getType();
			List<PackageAttributeValueEntity> entities = ParamHandler.gson.fromJson(attrs, type);
			for (PackageAttributeValueEntity packageAttributeValueEntity : entities) {
				packageAttributeValueEntity.setEnabled(1);
				packageAttributeValueEntity.setCreateTime(new Date());
			}
			this.packageFacade.batchSavePackageAttributeValue(entities);
		}
	}

	@Override
	public void saveAttrOfService(Long serviceId, String attributeIds) throws ArgsException {
		if (null == serviceId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		ServiceAttributeRelationDTO dto = new ServiceAttributeRelationDTO();
		dto.setServiceId(serviceId);
		this.packageFacade.deleteServiceAttributeRelationBySql(dto);
		if (StringUtils.isNotBlank(attributeIds)) {
			String roleId[] = attributeIds.split(",");
			List<ServiceAttributeRelationEntity> entities = new ArrayList<>();
			for (String rId : roleId) {
				ServiceAttributeRelationEntity entity = new ServiceAttributeRelationEntity();
				entity.setCreateTime(new Date());
				entity.setAttributeId(Long.parseLong(rId));
				entity.setServiceId(serviceId);
				entities.add(entity);
			}
			this.packageFacade.batchSaveServiceAttributeRelation(entities);
		}
	}

	@Override
	public void buyIncrPkg(Long userId, Long packageId, Integer count, Integer sourceFrom, Long orderId) throws ArgsException {
		UserInfoDTO userInfoDTO = this.systemFacade.getUserInfo(userId);
		if (null == userInfoDTO) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "球队管理员才可以购买套餐");
		}
		PackageDTO packageDTO = getPackage(packageId);
		if (null == packageDTO) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		
		Calendar calendar = Calendar.getInstance();
		Date beginTime = calendar.getTime();
		calendar.add(Calendar.YEAR, 1);
		Date endTime = calendar.getTime();
		UserPackageDTO userPackage = new UserPackageDTO();
		userPackage.setUserId(userId);
		userPackage.setPackageId(packageId);
		userPackage.setPackageName(packageDTO.getPackageName());
		userPackage.setPackageType(packageDTO.getPackageType());
		userPackage.setPrice(packageDTO.getPrice());
		userPackage.setDiscount(packageDTO.getDiscount());
		userPackage.setCount(count);
		userPackage.setCreateTime(beginTime);
		userPackage.setBeginTime(null);
		userPackage.setEndTime(null);
		userPackage.setSourceFrom(sourceFrom);
		Long upId = this.packageFacade.saveUserPackage(userPackage);
		
		for (int i = 1; i <= count; i++) {
			insertServices(userId, null, packageDTO, beginTime, endTime, upId);
		}
	}

	private void insertServices(Long userId, Long teamId, PackageDTO packageDTO, Date beginTime, Date endTime, Long upId) {
		List<ServiceDTO> services = packageDTO.getServices();
		if (null != services && ! services.isEmpty()) {
			List<UserAttributeEntity> attributeEntities = new ArrayList<>();
			for (ServiceDTO service : services) {
				UserServiceDTO entity = new UserServiceDTO();
				entity.setUserId(userId);
				entity.setUpId(upId);
				entity.setServiceId(service.getServiceId());
				entity.setServiceName(service.getServiceName());
				entity.setServiceCount(service.getCount());
				entity.setCreateTime(beginTime);
				entity.setBeginTime(beginTime);
				entity.setEndTime(endTime);
				entity.setTeamId(teamId);
				Long usId = this.packageFacade.saveUserService(entity);
				List<AttributeDTO> attributes = service.getAttributes();
				if (null != attributes && ! attributes.isEmpty()) {
					for (AttributeDTO attribute : attributes) {
						UserAttributeEntity attributeEntity = new UserAttributeEntity();
						attributeEntity.setUsId(usId);
						attributeEntity.setAttributeId(attribute.getAttributeId());
						attributeEntity.setAttributeName(attribute.getAttributeName());
						attributeEntity.setAttributeValue(attribute.getAttributeValue());
						attributeEntity.setCreateTime(new Date());
						attributeEntity.setUserId(userId);
						attributeEntity.setTeamId(teamId);
						attributeEntities.add(attributeEntity);
					}
				}
			}
			this.packageFacade.batchSaveUserAttribute(attributeEntities);
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

	@Override
	public Map<String, Object> mineService(Long userId) throws ArgsException {
		List<Map<String, Object>> packages = new ArrayList<>();
		List<Map<String, Object>> services = new ArrayList<>();
		
		UserPackageDTO userPackageDTO = new UserPackageDTO();
			userPackageDTO.setUserId(userId);
			userPackageDTO.setTeamIdEmpty(1);
		List<UserPackageDTO> userPackages = this.packageFacade.getUserPackageList(userPackageDTO);
		for (UserPackageDTO userPackage : userPackages) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("usId", userPackage.getUpId());
			map.put("packageName", userPackage.getPackageName());
			map.put("packageType", userPackage.getPackageType());
			if (userPackage.getPackageType().intValue() == 1) {
				map.put("endTime", userPackage.getEndTime());
			}
			packages.add(map);
		}
		UserServiceDTO userServiceDTO = new UserServiceDTO();
			userServiceDTO.setUserId(userId);
			userServiceDTO.setTeamIdEmpty(1);
		
		List<UserServiceDTO> userServices = this.packageFacade.getUserServiceList(userServiceDTO);
		
		for (UserServiceDTO userService : userServices) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("serviceId", userService.getServiceId());
			map.put("serviceName", userService.getServiceName());
			map.put("description", userService.getDescription());
			map.put("serviceCount", userService.getServiceCount());
			List<Map<String, Object>> attributes2 = new ArrayList<>();
			UserAttributeDTO userAttributeDTO = new UserAttributeDTO();
			userAttributeDTO.setUserId(userService.getUserId());
			userAttributeDTO.setUsId(userService.getUsId());
			List<UserAttributeDTO> attributes = packageFacade.getUserAttributeList(userAttributeDTO);
			for (UserAttributeDTO userAttribute : attributes) {
				Map<String, Object> attrs = new LinkedHashMap<>();
				attrs.put("attributeId", userAttribute.getAttributeId());
				attrs.put("attributeName", userAttribute.getAttributeName());
				attrs.put("attributeValue", userAttribute.getAttributeValue());
				attributes2.add(attrs);
			}
			if (! attributes2.isEmpty()) {
				map.put("attributes", attributes2);
			}
			Integer usedNum = this.packageFacade.getUsedServiceNum(userId, userService.getServiceId());
			map.put("serviceUsedNum", usedNum);
			map.put("serviceUsableNum", userService.getServiceCount() - usedNum);
			services.add(map);
		}
		Map<String, Object> res = new HashMap<>();
		res.put("packages", packages);
		res.put("services", services);
		return res;
	}


	@Override
	public ApiResult serviceUsedList(QueryParam queryParam) {
		queryParam.setOrderBy("createTime");
		queryParam.setOrderType("desc");
		Long userId = queryParam.getParam("userId");
		queryParam.addParam("userId", userId);
		PageList<UseServiceDetailDTO> pageList = this.packageFacade.getUseServiceDetailListForPage(queryParam);
		ApiResult apiResult = new ApiResult();
		apiResult.setData(pageList.getDataList());
		PageInfo pageInfo = new PageInfo(pageList.getPageNo(), pageList.getPageSize(), pageList.getTotalRecord());
		apiResult.setPageInfo(pageInfo);
		return apiResult;
	}

	@Override
	public ApiResult serviceBuyList(QueryParam queryParam) {
		queryParam.setOrderBy("createTime");
		queryParam.setOrderType("desc");
		Long userId = queryParam.getParam("userId");
		queryParam.addParam("userId", userId);
		PageList<UserPackageDTO> pageList = this.packageFacade.getUserPackageListForPage(queryParam);
		ApiResult apiResult = new ApiResult();
		apiResult.setData(pageList.getDataList());
		PageInfo pageInfo = new PageInfo(pageList.getPageNo(), pageList.getPageSize(), pageList.getTotalRecord());
		apiResult.setPageInfo(pageInfo);
		return apiResult;
	}
}
