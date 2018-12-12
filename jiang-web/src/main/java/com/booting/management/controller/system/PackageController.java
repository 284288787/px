/**create by liuhua at 2017年6月5日 上午9:17:18**/
package com.booting.management.controller.system;

import java.util.HashMap;
import java.util.List;
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

import com.booting.pkg.dto.AttributeDTO;
import com.booting.pkg.dto.PackageDTO;
import com.booting.pkg.dto.ServiceDTO;
import com.booting.pkg.facade.PackageFacade;
import com.booting.service.SystemWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/package")
public class PackageController {
	
	@Autowired
	private PackageFacade packageFacade;
	
	@Autowired
	private SystemWebService systemWebService;
	
	@ResponseBody
	@RequestMapping(value = "/listAttribute", method = RequestMethod.POST)
	public Map<String, Object> attributeList(AttributeDTO attributeDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(attributeDTO);
		PageList<AttributeDTO> pageList = packageFacade.getAttributeListForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@RequestMapping(value = "/editAttributeBefore/{attributeId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editAttributeBefore(@PathVariable Long attributeId, Model model){
		AttributeDTO attributeDTO = this.packageFacade.getAttribute(attributeId);
		model.addAttribute("attribute", attributeDTO);
		return "management/package/editAttribute";
	}
	
	@ResponseBody
	@RequestMapping(value = "/enabledAttribute", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage enabledAttribute(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.packageFacade.enabledAttribute(ids);
			resultMessage = new ResultMessage("ok", "启用属性");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用属性", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用属性", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/disabledAttribute", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage disabledAttribute(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.packageFacade.disabledAttribute(ids);
			resultMessage = new ResultMessage("ok", "禁用属性");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用属性", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用属性", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/addAttribute", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage addAttribute(AttributeDTO attributeDTO){
		ResultMessage resultMessage = null;
		try {
			this.packageFacade.saveAttribute(attributeDTO);
			resultMessage = new ResultMessage("ok", "添加属性");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加属性", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加属性", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/editAttribute", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage editAttribute(AttributeDTO attributeDTO){
		ResultMessage resultMessage = null;
		try {
			this.packageFacade.updateAttribute(attributeDTO);
			resultMessage = new ResultMessage("ok", "编辑属性");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑属性", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑属性", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listService", method = RequestMethod.POST)
	public Map<String, Object> serviceList(ServiceDTO serviceDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(serviceDTO);
		PageList<ServiceDTO> pageList = packageFacade.getServiceListForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@RequestMapping(value = "/editServiceBefore/{serviceId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editServiceBefore(@PathVariable Long serviceId, Model model){
		ServiceDTO serviceDTO = this.packageFacade.getService(serviceId);
		model.addAttribute("service", serviceDTO);
		return "management/package/editService";
	}
	
	@ResponseBody
	@RequestMapping(value = "/enabledService", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage enabledService(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.packageFacade.enabledService(ids);
			resultMessage = new ResultMessage("ok", "启用服务");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用服务", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用服务", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/disabledService", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage disabledService(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.packageFacade.disabledService(ids);
			resultMessage = new ResultMessage("ok", "禁用服务");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用服务", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用服务", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/addService", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage addService(ServiceDTO serviceDTO){
		ResultMessage resultMessage = null;
		try {
			this.packageFacade.saveService(serviceDTO);
			resultMessage = new ResultMessage("ok", "添加服务");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加服务", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加服务", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/editService", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage editService(ServiceDTO serviceDTO){
		ResultMessage resultMessage = null;
		try {
			this.packageFacade.updateService(serviceDTO);
			resultMessage = new ResultMessage("ok", "编辑服务");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑服务", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑服务", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/listPackage", method = RequestMethod.POST)
	public Map<String, Object> packageList(PackageDTO packageDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(packageDTO);
		PageList<PackageDTO> pageList = packageFacade.getPackageListForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@RequestMapping(value = "/editPackageBefore/{packageId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editPackageBefore(@PathVariable Long packageId, Model model){
		PackageDTO packageDTO = this.packageFacade.getPackage(packageId);
		model.addAttribute("packageDTO", packageDTO);
		return "management/package/editPackage";
	}
	
	@ResponseBody
	@RequestMapping(value = "/enabledPackage", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage enabledPackage(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.packageFacade.enabledPackage(ids);
			resultMessage = new ResultMessage("ok", "启用套餐");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用套餐", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用套餐", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/disabledPackage", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage disabledPackage(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.packageFacade.disabledPackage(ids);
			resultMessage = new ResultMessage("ok", "禁用套餐");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用套餐", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用套餐", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/addPackage", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage addPackage(PackageDTO packageDTO){
		ResultMessage resultMessage = null;
		try {
			this.packageFacade.savePackage(packageDTO);
			resultMessage = new ResultMessage("ok", "添加套餐");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加套餐", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加套餐", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/editPackage", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage editPackage(PackageDTO packageDTO){
		ResultMessage resultMessage = null;
		try {
			this.packageFacade.updatePackage(packageDTO);
			resultMessage = new ResultMessage("ok", "编辑套餐");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑套餐", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑套餐", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@RequestMapping(value = "/setServOfPackage/{packageId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String setServOfPackage(@PathVariable Long packageId, Model model){
		PackageDTO packageDTO = new PackageDTO();
		packageDTO.setPackageId(packageId);
		List<PackageDTO> list = this.systemWebService.getPackage(packageDTO);
		ServiceDTO serviceDTO = new ServiceDTO();
		serviceDTO.setEnabled(1);
		List<ServiceDTO> services = this.systemWebService.getService(serviceDTO);
		model.addAttribute("services", services);
		if (null != list && list.size() > 0) {
			PackageDTO packageDTO2 = list.get(0);
			List<ServiceDTO> elements = packageDTO2.getServices();
			Map<Long, ServiceDTO> map = new HashMap<>();
			for (ServiceDTO attr : elements) {
				map.put(attr.getServiceId(), attr);
			}
			model.addAttribute("choosedServices", map);
			model.addAttribute("packageDTO", list.get(0));
		}
		return "management/package/setService";
	}
	
	@RequestMapping(value = "/setAttrOfService/{serviceId}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String setAttrOfService(@PathVariable Long serviceId, Model model){
		ServiceDTO serviceDTO = new ServiceDTO();
		serviceDTO.setServiceId(serviceId);
		List<ServiceDTO> services = this.systemWebService.getService(serviceDTO);
		AttributeDTO attributeDTO = new AttributeDTO();
		attributeDTO.setEnabled(1);
		List<AttributeDTO> attributes = this.systemWebService.getAttribute(attributeDTO);
		model.addAttribute("attributes", attributes);
		if (null != services && services.size() > 0) {
			serviceDTO = services.get(0);
			List<AttributeDTO> elements = serviceDTO.getAttributes();
			Map<Long, AttributeDTO> map = new HashMap<>();
			for (AttributeDTO attr : elements) {
				map.put(attr.getAttributeId(), attr);
			}
			model.addAttribute("choosedAttributes", map);
			model.addAttribute("serviceDTO", serviceDTO);
		}
		return "management/package/setAttribute";
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveServOfPackage", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage saveServOfPackage(Long packageId, String services, String attrs){
		ResultMessage resultMessage = null;
		try {
			this.systemWebService.saveServOfPackage(packageId, services, attrs);
			resultMessage = new ResultMessage("ok", "设置属性");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("设置属性", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("设置属性", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveAttrOfService", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage saveAttrOfService(Long serviceId, String attributeIds){
		ResultMessage resultMessage = null;
		try {
			this.systemWebService.saveAttrOfService(serviceId, attributeIds);
			resultMessage = new ResultMessage("ok", "设置属性");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("设置属性", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("设置属性", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
}
