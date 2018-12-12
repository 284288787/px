/**create by liuhua at 2017年5月24日 下午4:47:55**/
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

import com.booting.system.dto.ResourceDTO;
import com.booting.system.facade.SystemFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private SystemFacade systemFacade;
	
	@ResponseBody
	@RequestMapping(value = "/sourceList", method = RequestMethod.POST)
	public Map<String, Object> list(ResourceDTO resourceDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(resourceDTO);
		PageList<ResourceDTO> pageList = systemFacade.getResourceListForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@RequestMapping(value = "/editBefore/{sourceId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long sourceId, Model model){
		ResourceDTO resourceDTO = this.systemFacade.getResource(sourceId);
		model.addAttribute("source", resourceDTO);
		return "management/resource/editResource";
	}
	
	@ResponseBody
	@RequestMapping(value = "/enabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage enabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.enabledResource(ids);
			resultMessage = new ResultMessage("ok", "启用资源");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用资源", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用资源", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/disabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage disabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.disabledResource(ids);
			resultMessage = new ResultMessage("ok", "禁用资源");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用资源", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用资源", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage add(ResourceDTO resourceDTO){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.saveResource(resourceDTO);
			resultMessage = new ResultMessage("ok", "添加资源");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加资源", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加资源", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(ResourceDTO resourceDTO){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.updateResource(resourceDTO);
			resultMessage = new ResultMessage("ok", "编辑资源");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑资源", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑资源", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getParentTypeBySourceType/{sourceType}", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ResultMessage getParentTypeBySourceType(@PathVariable Integer sourceType){
		ResultMessage resultMessage = null;
		try {
			Map<String, List<ResourceDTO>> data = this.systemFacade.getParentTypeBySourceType(sourceType);
			resultMessage = new ResultMessage(data, "根据资源类型得到父级资源类型");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("根据资源类型得到父级资源类型", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("根据资源类型得到父级资源类型", FailureCode.ERR_001);
		}
		return resultMessage;
	}
}
