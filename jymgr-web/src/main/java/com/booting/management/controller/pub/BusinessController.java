/**create by liuhua at 2017年6月23日 下午4:27:33**/
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

import com.booting.pub.dto.BusinessDTO;
import com.booting.pub.facade.CommonFacade;
import com.booting.service.CommonWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/business")
public class BusinessController {
	
	@Autowired
	private CommonFacade commonFacade;
	@Autowired
	private CommonWebService commonWebService;
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(BusinessDTO businessDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(businessDTO);
		PageList<BusinessDTO> pageList = commonFacade.getBusinessListForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@RequestMapping(value = "/editBefore/{businessId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long businessId, Model model){
		BusinessDTO businessDTO = this.commonFacade.getBusiness(businessId);
		model.addAttribute("business", businessDTO);
		return "management/business/editBusiness";
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(BusinessDTO businessDTO){
		ResultMessage resultMessage = null;
		try {
			this.commonWebService.updateBusiness(businessDTO);
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
			this.commonWebService.enabledBusiness(ids);
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
			this.commonWebService.disabledBusiness(ids);
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
	public ResultMessage add(BusinessDTO businessDTO){
		ResultMessage resultMessage = null;
		try {
			this.commonWebService.saveBusiness(businessDTO);
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
