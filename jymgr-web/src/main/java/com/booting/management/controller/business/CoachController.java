/** create by auto at 2018-06-12 10:17:06**/
package com.booting.management.controller.business;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
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
import com.booting.business.dto.CoachDTO;
import com.booting.service.impl.CoachWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/coach")
public class CoachController{

	@Autowired
	private CoachWebService coachWebService;

	@ResponseBody
    @RequestMapping(value = "/getTeachingCerts", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public ResultMessage getTeachingCerts(){
        ResultMessage resultMessage = null;
        try {
          PictureChildType[] types = BusinessConst.createTeachingCerts();
          resultMessage = new ResultMessage(types, "获取证书类型");
        } catch (Exception e) {
            e.printStackTrace();
            resultMessage = new ResultMessage("获取证书类型", FailureCode.ERR_001);
        }
        return resultMessage;
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
		String certTypes = coachDTO.getCertTypes();
		if (StringUtils.isNotBlank(certTypes)) {
          coachDTO.setCertTypeArr(certTypes.split(","));
        }
		queryParam.setParam(coachDTO);
		PageList<CoachDTO> pageList = coachWebService.getListForPageCoach(queryParam, CoachDTO.class);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@RequestMapping(value = "/editBefore/{memberId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long memberId, Model model){
	  CoachDTO coachDTO = this.coachWebService.getCoach(memberId);
	  model.addAttribute("coachDTO", coachDTO);
	  PictureChildType[] types = BusinessConst.createTeachingCerts();
	  model.addAttribute("types", types);
	  return "management/coach/editCoach";
	}

	@RequestMapping(value = "/viewBefore/{memberId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String viewBefore(@PathVariable Long memberId, Model model){
		CoachDTO coachDTO = this.coachWebService.getCoach(memberId);
		model.addAttribute("coachDTO", coachDTO);
		PictureChildType[] types = BusinessConst.createTeachingCerts();
		model.addAttribute("types", types);
		return "management/coach/viewCoach";
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(@RequestBody CoachDTO coachDTO){
		ResultMessage resultMessage = null;
		try {
			this.coachWebService.updateCoach(coachDTO);
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
			this.coachWebService.saveCoach(coachDTO);
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
			this.coachWebService.enabledCoach(ids);
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
			this.coachWebService.disabledCoach(ids);
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
			this.coachWebService.deleteCoach(ids);
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