/** create by auto at 2018-01-08 15:10:03**/
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

import com.booting.kindergarten.dto.ClassDTO;
import com.booting.service.impl.KindergartenWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;
import com.star.framework.utils.DataExportUtil;

@Controller
@RequestMapping("/class")
public class ClassController{

	@Autowired
	private KindergartenWebService kindergartenWebService;

	@RequestMapping(value = "/setClasses/{schoolId}_{teacherId}", method = RequestMethod.GET)
	public String setClasses(@PathVariable Long schoolId, @PathVariable Long teacherId, Model model) {
		String classIdsByHeadTeacher = kindergartenWebService.getHeadTeacherClassIds(schoolId, teacherId);
		model.addAttribute("classIdsByHeadTeacher", classIdsByHeadTeacher);
		String classIds = kindergartenWebService.getTeacherClassIds(teacherId);
		model.addAttribute("classIds", classIds);
		return "management/kindergartenClass/setClasses";
	}
	
	@RequestMapping(value = "/chooseClass", method = RequestMethod.GET)
	public String chooseClass(Long schoolId, Model model) {
		List<ClassDTO> classes = kindergartenWebService.getClasses(schoolId);
		model.addAttribute("classes", classes);
		return "management/kindergartenClass/chooseClass";
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(ClassDTO classDTO, Integer page, Integer rows, String sord, String sidx){
		classDTO.setDeleted(0);
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(classDTO);
		PageList<ClassDTO> pageList = kindergartenWebService.getListForPageClass(queryParam, ClassDTO.class);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}

	@RequestMapping(value = "/editBefore/{classId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long classId, Model model){
		ClassDTO classDTO = this.kindergartenWebService.getClass(classId);
		model.addAttribute("classDTO", classDTO);
		return "management/kindergartenClass/editClass";
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(@RequestBody ClassDTO classDTO){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.updateClass(classDTO);
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
	public ResultMessage add(@RequestBody ClassDTO classDTO){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.saveClass(classDTO);
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
			this.kindergartenWebService.enabledClass(ids);
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
			this.kindergartenWebService.disabledClass(ids);
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
			this.kindergartenWebService.deleteClass(ids);
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
			String filename = "班级信息.xlsx";
			filename = URLEncoder.encode(filename, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			String[] titles = new String[] {"幼儿园","班主任姓名","班级名称","班级简介"};
			DataExportUtil.createXlsxExcelFile2(os, "班级信息", titles, null);
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