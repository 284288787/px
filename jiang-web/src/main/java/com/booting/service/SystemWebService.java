/**create by liuhua at 2017年6月1日 下午4:00:35**/
package com.booting.service;

import java.util.List;
import java.util.Map;

import com.booting.pkg.dto.AttributeDTO;
import com.booting.pkg.dto.PackageDTO;
import com.booting.pkg.dto.ServiceDTO;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.v2.ApiResult;

public interface SystemWebService {

	public Map<String, Object> login(String username, String password) throws ArgsException;

	public void logout(String token) throws ArgsException;

	public Map<String, Object> register(String username, String passwd, String authcode, String clientid, Integer sourceFrom) throws ArgsException;

	public void checkUsername(String username) throws ArgsException;

	public List<PackageDTO> getPackage(PackageDTO packageDTO);
	
	public PackageDTO getPackage(Long packageId);

	public void changePassword(String username, String newpasswd, String passwd) throws ArgsException;

	public void findPassword(String username, String newpasswd, String code) throws ArgsException;

	public List<ServiceDTO> getService(ServiceDTO serviceDTO);

	public List<AttributeDTO> getAttribute(AttributeDTO attributeDTO);

	public void saveServOfPackage(Long packageId, String serviceIds, String values) throws ArgsException;

	public void saveAttrOfService(Long serviceId, String attributeIds) throws ArgsException;

	public void updateClientid(Long userId, String clientid, Integer sourceFrom);

	public void buyIncrPkg(Long userId, Long packageId, Integer count, Integer sourceFrom, Long orderId) throws ArgsException;

	public Map<String, Object> getUserInfo(Long userId) throws ArgsException;

	public Map<String, Object> mineService(Long userId) throws ArgsException;

	public ApiResult serviceUsedList(QueryParam queryParam);

	public ApiResult serviceBuyList(QueryParam queryParam);

	Map<String, Object> getUserInfo(String mobile) throws ArgsException;
}
