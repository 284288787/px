/**create by liuhua at 2017年5月25日 上午10:26:08**/
package com.booting.management.controller.system;

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

import com.booting.system.dto.RoleDTO;
import com.booting.system.dto.RoleSourceRelationDTO;
import com.booting.system.facade.SystemFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private SystemFacade systemFacade;
	
	@ResponseBody
	@RequestMapping(value = "/roleList", method = RequestMethod.POST)
	public Map<String, Object> list(RoleDTO roleDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(roleDTO);
		PageList<RoleDTO> pageList = systemFacade.getRoleListForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/setResource/{roleId}", method = RequestMethod.POST)
	public Map<String, Object> setRole(@PathVariable Long roleId, RoleSourceRelationDTO roleSourceRelationDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		roleSourceRelationDTO.setRoleId(roleId);
		queryParam.setParam(roleSourceRelationDTO);
		PageList<RoleSourceRelationDTO> pageList = this.systemFacade.getRoleSourcesForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/viewResource/{roleId}", method = RequestMethod.POST)
	public Map<String, Object> viewResource(@PathVariable Long roleId, RoleSourceRelationDTO roleSourceRelationDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		roleSourceRelationDTO.setRoleId(roleId);
		queryParam.setParam(roleSourceRelationDTO);
		PageList<RoleSourceRelationDTO> pageList = this.systemFacade.getRoleSourceForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveSourceRelation", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage saveRoleRelation(Long roleId, String sourceIds){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.setRoleResources(roleId, sourceIds);
			resultMessage = new ResultMessage("ok", "角色分配资源");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("角色分配资源", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("角色分配资源", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@RequestMapping(value = "/editBefore/{roleId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long roleId, Model model){
		RoleDTO roleDTO = this.systemFacade.getRole(roleId);
		model.addAttribute("role", roleDTO);
		return "management/role/editRole";
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage add(RoleDTO roleDTO){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.saveRole(roleDTO);
			resultMessage = new ResultMessage("ok", "添加角色");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加角色", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("添加角色", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(RoleDTO roleDTO){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.updateRole(roleDTO);
			resultMessage = new ResultMessage("ok", "编辑角色");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑角色", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("编辑角色", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/enabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage enabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.enabledRole(ids);
			resultMessage = new ResultMessage("ok", "启用角色");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用角色", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("启用角色", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/disabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage disabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.systemFacade.disabledRole(ids);
			resultMessage = new ResultMessage("ok", "禁用角色");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用角色", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("禁用角色", FailureCode.ERR_001);
		}
		return resultMessage;
	}
}
