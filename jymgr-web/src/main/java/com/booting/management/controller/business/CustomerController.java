/** create by auto at 2018-06-12 10:17:06**/
package com.booting.management.controller.business;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.booting.business.conf.BusinessConst;
import com.booting.business.conf.PictureChildType;
import com.booting.business.dto.CustomerDTO;
import com.booting.service.impl.CustomerWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/customer")
public class CustomerController{

	@Autowired
	private CustomerWebService customerWebService;

	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(CustomerDTO customerDTO, Integer page, Integer rows, String sord, String sidx){
		customerDTO.setDeleted(0);
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(customerDTO);
		PageList<CustomerDTO> pageList = customerWebService.getListForPageCustomer(queryParam, CustomerDTO.class);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@RequestMapping(value = "/editBefore/{memberId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long memberId, Model model){
	  CustomerDTO customerDTO = this.customerWebService.getCustomer(memberId);
	  model.addAttribute("customerDTO", customerDTO);
	  return "management/customer/editCustomer";
	}

	@RequestMapping(value = "/viewBefore/{memberId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String viewBefore(@PathVariable Long memberId, Model model){
		CustomerDTO customerDTO = this.customerWebService.getCustomer(memberId);
		model.addAttribute("customerDTO", customerDTO);
		PictureChildType[] types = BusinessConst.createTeachingCerts();
		model.addAttribute("types", types);
		return "management/customer/viewCustomer";
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(@RequestBody CustomerDTO customerDTO){
		ResultMessage resultMessage = null;
		try {
			this.customerWebService.updateCustomer(customerDTO);
			resultMessage = new ResultMessage("ok", "编辑");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑", FailureCode.ERR_001);
		}
		return resultMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage add(@RequestBody CustomerDTO customerDTO){
		ResultMessage resultMessage = null;
		try {
			this.customerWebService.saveCustomer(customerDTO);
			resultMessage = new ResultMessage("ok", "添加");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加", FailureCode.ERR_001);
		}
		return resultMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/enabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage enabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.customerWebService.enabledCustomer(ids);
			resultMessage = new ResultMessage("ok", "启用");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用", FailureCode.ERR_001);
		}
		return resultMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/disabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage disabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.customerWebService.disabledCustomer(ids);
			resultMessage = new ResultMessage("ok", "禁用");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用", FailureCode.ERR_001);
		}
		return resultMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage delete(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.customerWebService.deleteCustomer(ids);
			resultMessage = new ResultMessage("ok", "删除");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("删除", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("删除", FailureCode.ERR_001);
		}
		return resultMessage;
	}

}