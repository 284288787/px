/**create by liuhua at 2017年5月20日 下午3:10:26**/
package com.booting.management.controller.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booting.common.CommonConstants.UserIdentity;
import com.booting.system.dto.UserAccountDTO;
import com.booting.system.dto.UserRoleRelationDTO;
import com.booting.system.facade.SystemFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/userAccount")
public class UserAccountController {

	@Autowired
	private SystemFacade systemFacade;
	
	@ResponseBody
	@RequestMapping(value = "/userList", method = RequestMethod.POST)
	public Map<String, Object> list(UserAccountDTO userAccountDTO, Integer page, Integer rows, String sord, String sidx){
		userAccountDTO.setDeleted(0);
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(userAccountDTO);
		PageList<UserAccountDTO> pageList = systemFacade.getListForPage(queryParam, UserAccountDTO.class);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/viewRole/{userId}", method = RequestMethod.POST)
	public Map<String, Object> viewRole(@PathVariable Long userId, UserRoleRelationDTO userRoleRelationDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		userRoleRelationDTO.setUserId(userId);
		queryParam.setParam(userRoleRelationDTO);
		PageList<UserRoleRelationDTO> pageList = this.systemFacade.getUserRoleForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/setRole/{userId}", method = RequestMethod.POST)
	public Map<String, Object> setRole(@PathVariable Long userId, UserRoleRelationDTO userRoleRelationDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		userRoleRelationDTO.setUserId(userId);
		queryParam.setParam(userRoleRelationDTO);
		PageList<UserRoleRelationDTO> pageList = this.systemFacade.getUserRolesForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@RequestMapping(value = "/editBefore/{userId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long userId, Model model){
		UserAccountDTO userAccount = this.systemFacade.getUserAccount(userId);
		model.addAttribute("userAccount", userAccount);
		return "management/user/editUser";
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveRoleRelation", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage saveRoleRelation(Long userId, String roleIds){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.setUserRoles(userId, roleIds);
			resultMessage = new ResultMessage("ok", "用户分配角色");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("用户分配角色", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("用户分配角色", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(UserAccountDTO userAccount){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.updateUserAccount(userAccount);
			resultMessage = new ResultMessage("ok", "编辑用户");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑用户", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑用户", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage add(UserAccountDTO userAccount){
		ResultMessage resultMessage = null;
		try {
			Md5PasswordEncoder encode = new Md5PasswordEncoder();
			userAccount.setPassword(encode.encodePassword(userAccount.getPassword(), null));
			userAccount.setIdentity(UserIdentity.background.getIdentity());
			this.systemFacade.saveUserAccount(userAccount);
			resultMessage = new ResultMessage("ok", "添加用户");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加用户", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加用户", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage changePassword(UserAccountDTO userAccount){
		ResultMessage resultMessage = null;
		try {
			Md5PasswordEncoder encode = new Md5PasswordEncoder();
			userAccount.setPassword(encode.encodePassword(userAccount.getPassword(), null));
			this.systemFacade.updateUserAccountOnly(userAccount);
			resultMessage = new ResultMessage("ok", "管理员修改密码");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("管理员修改密码", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("管理员修改密码", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/enabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage enabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.enabledUserAccount(ids);
			resultMessage = new ResultMessage("ok", "启用用户");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用用户", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用用户", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/disabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage disabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.disabledUserAccount(ids);
			resultMessage = new ResultMessage("ok", "禁用用户");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用用户", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用用户", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleted", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage deleted(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.deleteUserAccount(ids);
			resultMessage = new ResultMessage("ok", "删除用户");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("删除用户", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("删除用户", FailureCode.ERR_001);
		}
		return resultMessage;
	}
}
