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

import com.booting.kindergarten.dto.ClassDTO;
import com.booting.kindergarten.dto.ClassTeacherRelationDTO;
import com.booting.kindergarten.dto.TeacherDTO;
import com.booting.service.impl.KindergartenWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;
import com.star.framework.utils.DataExportUtil;

@Controller
@RequestMapping("/teacher")
public class TeacherController{

	@Autowired
	private KindergartenWebService kindergartenWebService;
	
	@RequestMapping(value = "/setTeachers/{schoolId}_{classId}", method = RequestMethod.GET)
	public String setTeachers(@PathVariable Long schoolId, @PathVariable Long classId, Model model) {
		ClassDTO classDTO = kindergartenWebService.getClass(classId);
		model.addAttribute("classDTO", classDTO);
		String teacherIds = kindergartenWebService.getClassTeacherIds(classId);
		model.addAttribute("teacherIds", teacherIds);
		return "management/kindergartenTeacher/setTeachers";
	}

	@ResponseBody
	@RequestMapping(value = "/saveRelationByClassTeacher", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage saveRelationByClassTeacher(@RequestBody ClassTeacherRelationDTO classTeacherRelationDTO){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.saveRelationByClassTeacher(classTeacherRelationDTO);
			resultMessage = new ResultMessage("ok", "保存编辑和幼师的关系");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("保存编辑和幼师的关系", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("保存编辑和幼师的关系", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@RequestMapping(value = "/chooseTeacher", method = RequestMethod.GET)
	public String chooseTeacher(Long schoolId, Model model) {
		List<TeacherDTO> teachers = kindergartenWebService.getTeachers(schoolId);
		model.addAttribute("teachers", teachers);
		return "management/kindergartenTeacher/chooseTeacher";
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(TeacherDTO teacherDTO, Integer page, Integer rows, String sord, String sidx){
		teacherDTO.setDeleted(0);
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(teacherDTO);
		PageList<TeacherDTO> pageList = kindergartenWebService.getListForPageTeacher(queryParam, TeacherDTO.class);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}

	@RequestMapping(value = "/editBefore/{teacherId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long teacherId, Model model){
		TeacherDTO teacherDTO = this.kindergartenWebService.getTeacher(teacherId);
		model.addAttribute("teacherDTO", teacherDTO);
		return "management/kindergartenTeacher/editTeacher";
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(@RequestBody TeacherDTO teacherDTO){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.updateTeacher(teacherDTO);
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
	public ResultMessage add(@RequestBody TeacherDTO teacherDTO){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.saveTeacher(teacherDTO);
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
			this.kindergartenWebService.enabledTeacher(ids);
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
			this.kindergartenWebService.disabledTeacher(ids);
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
			this.kindergartenWebService.deleteTeacher(ids);
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