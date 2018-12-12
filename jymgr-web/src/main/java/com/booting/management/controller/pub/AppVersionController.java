/**create by liuhua at 2017年6月14日 上午11:44:34**/
package com.booting.management.controller.pub;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booting.pub.dto.AppVersionDTO;
import com.booting.pub.facade.CommonFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/appv")
public class AppVersionController {

	@Autowired
	private CommonFacade commonFacade;
	
	@ResponseBody
	@RequestMapping(value = "/appList", method = RequestMethod.POST)
	public Map<String, Object> list(AppVersionDTO appVersionDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(appVersionDTO);
		PageList<AppVersionDTO> pageList = commonFacade.getAppVersionListForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@RequestMapping(value = "/editBefore/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long id, Model model){
		AppVersionDTO appVersionDTO = this.commonFacade.getAppVersion(id);
		model.addAttribute("appVersion", appVersionDTO);
		return "management/app/editApp";
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(AppVersionDTO appVersionDTO){
		ResultMessage resultMessage = null;
		try {
			this.commonFacade.updateAppVersion(appVersionDTO);
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
	@RequestMapping(value = "/enabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage enabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.commonFacade.enabledAppVersion(ids);
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
			this.commonFacade.disabledAppVersion(ids);
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
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage add(AppVersionDTO appVersionDTO){
		ResultMessage resultMessage = null;
		try {
			this.commonFacade.saveAppVersion(appVersionDTO);
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
}
