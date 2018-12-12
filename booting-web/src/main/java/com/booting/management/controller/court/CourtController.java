/**create by liuhua at 2017年6月29日 下午3:57:03**/
package com.booting.management.controller.court;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booting.common.CommonConstants.UserIdentity;
import com.booting.court.dto.CourtDTO;
import com.booting.court.dto.SiteDTO;
import com.booting.court.facade.CourtFacade;
import com.booting.service.CourtWebService;
import com.booting.system.dto.UserAccountDTO;
import com.booting.system.facade.SystemFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/court")
public class CourtController {

	@Autowired
	private SystemFacade systemFacade;
	@Autowired
	private CourtFacade courtFacade;
	@Autowired
	private CourtWebService courtWebService;
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(CourtDTO courtDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(courtDTO);
		PageList<CourtDTO> pageList = courtFacade.getCourtListForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage add(CourtDTO courtDTO){
		ResultMessage resultMessage = null;
		try {
			this.courtWebService.saveCourt(courtDTO);
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
	@RequestMapping(value = "/addSite", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage addSite(SiteDTO siteDTO){
		ResultMessage resultMessage = null;
		try {
			this.courtWebService.saveSite(siteDTO);
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
	@RequestMapping(value = "/addUserAccount", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage addUserAccount(UserAccountDTO userAccount){
		ResultMessage resultMessage = null;
		try {
			Md5PasswordEncoder encode = new Md5PasswordEncoder();
			userAccount.setPassword(encode.encodePassword(userAccount.getPassword(), null));
			userAccount.setIdentity(UserIdentity.normal.getIdentity());
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
	@RequestMapping(value = "/enabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage enabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.courtFacade.enabledCourt(ids);
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
	@RequestMapping(value = "/disabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage disabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.courtFacade.disabledCourt(ids);
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
	@RequestMapping(value = "/listSite", method = RequestMethod.POST)
	public Map<String, Object> listSite(SiteDTO siteDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(siteDTO);
		PageList<SiteDTO> pageList = courtFacade.getSiteListForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/enabledSite", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage enabledSite(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.courtFacade.enabledSite(ids);
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
	@RequestMapping(value = "/disabledSite", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage disabledSite(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.courtFacade.disabledSite(ids);
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
	
	@RequestMapping(value = "/view/{courtId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long courtId, Model model){
		SiteDTO siteDTO = new SiteDTO();
		siteDTO.setCourtId(courtId);
		List<SiteDTO> sites = this.courtFacade.getSiteList(siteDTO);
		model.addAttribute("sites", sites);
		return "management/court/viewSite";
	}
}
