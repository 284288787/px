/** create by auto at 2018-01-12 09:20:36**/
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

import com.booting.kindergarten.dto.StudentDTO;
import com.booting.service.impl.KindergartenWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;
import com.star.framework.utils.DataExportUtil;

@Controller
@RequestMapping("/student")
public class StudentController{

	@Autowired
	private KindergartenWebService kindergartenWebService;

	@RequestMapping(value = "/chooseStudent", method = RequestMethod.GET)
	public String chooseStudent(Long classId, Model model) {
		List<StudentDTO> students = kindergartenWebService.getStudents(classId);
		model.addAttribute("students", students);
		return "management/kindergartenStudent/chooseStudent";
	}
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(StudentDTO studentDTO, Integer page, Integer rows, String sord, String sidx){
		studentDTO.setDeleted(0);
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(studentDTO);
		PageList<StudentDTO> pageList = kindergartenWebService.getListForPageStudent(queryParam, StudentDTO.class);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}

	@RequestMapping(value = "/editBefore/{studentId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long studentId, Model model){
		StudentDTO studentDTO = this.kindergartenWebService.getStudent(studentId);
		model.addAttribute("studentDTO", studentDTO);
		return "management/kindergartenStudent/editStudent";
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(@RequestBody StudentDTO studentDTO){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.updateStudent(studentDTO);
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
	public ResultMessage add(@RequestBody StudentDTO studentDTO){
		ResultMessage resultMessage = null;
		try {
			this.kindergartenWebService.saveStudent(studentDTO);
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
			this.kindergartenWebService.enabledStudent(ids);
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
			this.kindergartenWebService.disabledStudent(ids);
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
			this.kindergartenWebService.deleteStudent(ids);
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
			String filename = "幼儿信息.xlsx";
			filename = URLEncoder.encode(filename, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			String[] titles = new String[] {"幼儿园","班级","幼儿姓名","性别(男/女)","生日(yyyy-MM-dd)","身高(cm)","体重(kg)", "监护人姓名", "监护人电话", "与监护人关系(父亲/母亲/爷爷/奶奶/外公/外婆/其他)"};
			DataExportUtil.createXlsxExcelFile2(os, "幼儿信息", titles, null);
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