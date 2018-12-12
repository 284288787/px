/**create by liuhua at 2017年6月6日 上午11:42:03**/
package com.booting.management.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booting.adjunction.dto.PopularizeDTO;
import com.booting.common.CommonConstants.UserIdentity;
import com.booting.management.controller.BaseController;
import com.booting.pkg.dto.PackageDTO;
import com.booting.pkg.dto.UserPackageDTO;
import com.booting.pkg.dto.UserServiceDTO;
import com.booting.pkg.facade.PackageFacade;
import com.booting.service.SystemWebService;
import com.booting.system.dto.UserAccountDTO;
import com.booting.system.facade.SystemFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/member")
public class MemberController extends BaseController{

	@Autowired
	private SystemFacade systemFacade;
	@Autowired
	private PackageFacade packageFacade;
	@Autowired
	private SystemWebService systemWebService;
	
	@RequestMapping(value = "/textPopularize/{teamId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String textPopularize(@PathVariable Long teamId, Model model){
		try {
			PopularizeDTO popularize = systemWebService.getPopularize(teamId, 1);
			model.addAttribute("popularize", popularize);
			return "management/member/textPopularize";
		} catch (ArgsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping(value = "/videoPopularize/{teamId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String videoPopularize(@PathVariable Long teamId, Model model){
		try {
			PopularizeDTO popularize = systemWebService.getPopularize(teamId, 2);
			model.addAttribute("popularize", popularize);
			return "management/member/videoPopularize";
		} catch (ArgsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value = "/savePopularize", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage savePopularize(PopularizeDTO popularizeDTO){
		ResultMessage resultMessage = null;
		try {
			String loginUserName = getLoginUserName(); 
			popularizeDTO.setUpdateUser(loginUserName);
			this.systemWebService.savePopularize(popularizeDTO);
			resultMessage = new ResultMessage("ok", "");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@RequestMapping(value = "/becomeEnterprise/{userId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String becomeEnterprise(@PathVariable Long userId, Model model){
		UserAccountDTO userAccount = this.systemFacade.getUserAccount(userId);
		if (null == userAccount || userAccount.getIdentity() != UserIdentity.normal.getIdentity().intValue()) {
			model.addAttribute("errorCode", 1);
		}else{
			PackageDTO packageDTO = new PackageDTO();
			packageDTO.setEnabled(1);
			packageDTO.setPackageType(1);
			List<PackageDTO> packages = systemWebService.getPackage(packageDTO);
			model.addAttribute("userAccount", userAccount);
			model.addAttribute("packages", packages);
		}
		return "management/member/becomeEnterprise";
	}
	
	@RequestMapping(value = "/buyIncrementPkg/{userId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String buyIncrementPkg(@PathVariable Long userId, Model model){
		UserAccountDTO userAccount = this.systemFacade.getUserAccount(userId);
		if (null == userAccount || (userAccount.getIdentity() != UserIdentity.teamManager.getIdentity().intValue() && userAccount.getIdentity() != UserIdentity.teamManager2.getIdentity().intValue())) {
			model.addAttribute("errorCode", 1);
		}else{
			PackageDTO packageDTO = new PackageDTO();
			packageDTO.setEnabled(1);
			packageDTO.setPackageType(2);
			List<PackageDTO> packages = systemWebService.getPackage(packageDTO);
			model.addAttribute("userAccount", userAccount);
			model.addAttribute("packages", packages);
		}
		return "management/member/buyIncrementPkg";
	}
	
	@RequestMapping(value = "/viewPackages/{userId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String viewPackages(@PathVariable Long userId, Model model){
		UserAccountDTO userAccount = this.systemFacade.getUserAccount(userId);
		if (null == userAccount || (userAccount.getIdentity() != UserIdentity.teamManager.getIdentity().intValue() && userAccount.getIdentity() != UserIdentity.teamManager2.getIdentity().intValue())) {
			model.addAttribute("errorCode", 1);
		}else{
			UserPackageDTO userPackageDTO = new UserPackageDTO();
			userPackageDTO.setUserId(userId);
			List<UserPackageDTO> userPackages = this.packageFacade.getUserPackageList(userPackageDTO);
			model.addAttribute("userPackages", userPackages);
			UserServiceDTO userServiceDTO = new UserServiceDTO();
			userServiceDTO.setUserId(userId);
			List<UserServiceDTO> userServices = this.packageFacade.getUserServiceList(userServiceDTO);
			model.addAttribute("userServices", userServices);
		}
		return "management/member/viewPackages";
	}
	
	@ResponseBody
	@RequestMapping(value = "/levelup", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage levelup(Long userId, Long packageId){
		ResultMessage resultMessage = null;
		try {
			this.systemWebService.levelup(userId, packageId, 2, null);
			resultMessage = new ResultMessage("ok", "");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/buyIncrPkg", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage buyIncrPkg(Long userId, Long packageId, Integer count){
		ResultMessage resultMessage = null;
		try {
			this.systemWebService.buyIncrPkg(userId, packageId, count, 2, null);
			resultMessage = new ResultMessage("ok", "");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
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
		queryParam.addParam("identities", "1,2,3,4,5,6");
		PageList<UserAccountDTO> pageList = systemFacade.getListForPage(queryParam, UserAccountDTO.class);
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
		return "management/member/editUser";
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
}
