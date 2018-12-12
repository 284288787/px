/** create by auto at 2018-01-08 16:47:37**/
package com.booting.management.controller.kindergarten;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.booting.kindergarten.dto.ClassCoachRelationDTO;
import com.booting.kindergarten.dto.CoachDTO;
import com.booting.service.impl.KindergartenWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;
import com.star.framework.utils.DataExportUtil;

@Controller
@RequestMapping("/coach")
public class CoachController{

	@Autowired
	private KindergartenWebService kindergartenWebService;
	
	@ResponseBody
	@RequestMapping(value = "/getCoachClassIds/{coachId}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage getCoachClassIds(@PathVariable Long coachId) {
		ResultMessage resultMessage = null;
		try {
			String classIds = this.kindergartenWebService.getCoachClassIds(coachId);
			resultMessage = new ResultMessage(classIds, "保存编辑和教练的关系");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("保存编辑和教练的关系", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("保存编辑和教练的关系", FailureCode.ERR_001);
		}
		return resultMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/saveRelationByClassCoach", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage saveRelationByClassCoach(@RequestBody ClassCoachRelationDTO classCoachRelationDTO){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.saveRelationByClassCoach(classCoachRelationDTO);
			resultMessage = new ResultMessage("ok", "保存编辑和教练的关系");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("保存编辑和教练的关系", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("保存编辑和教练的关系", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@RequestMapping(value = "/chooseCoach", method = RequestMethod.GET)
	public String chooseCoach(Model model) {
		List<CoachDTO> coachs = kindergartenWebService.getCoachs();
		model.addAttribute("coachs", coachs);
		return "management/kindergartenCoach/chooseCoach";
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(CoachDTO coachDTO, Integer page, Integer rows, String sord, String sidx){
		coachDTO.setDeleted(0);
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(coachDTO);
		PageList<CoachDTO> pageList = kindergartenWebService.getListForPageCoach(queryParam, CoachDTO.class);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}

	@RequestMapping(value = "/editBefore/{coachId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long coachId, Model model){
		CoachDTO coachDTO = this.kindergartenWebService.getCoach(coachId);
		model.addAttribute("coachDTO", coachDTO);
		return "management/kindergartenCoach/editCoach";
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(@RequestBody CoachDTO coachDTO){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.updateCoach(coachDTO);
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
	public ResultMessage add(@RequestBody CoachDTO coachDTO){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.saveCoach(coachDTO);
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
			this.kindergartenWebService.enabledCoach(ids);
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
			this.kindergartenWebService.disabledCoach(ids);
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
			this.kindergartenWebService.deleteCoach(ids);
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

	@RequestMapping(value = "/exportModel", method = RequestMethod.POST)
	public void exportModel(HttpServletRequest request, HttpServletResponse response) {
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			String filename = "幼师信息.xlsx";
			filename = URLEncoder.encode(filename, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			String[] titles = new String[] {"幼儿园","幼师姓名","电话号码","幼师简介"};
			DataExportUtil.createXlsxExcelFile2(os, "幼师信息", titles, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
					os = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}