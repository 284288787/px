/** create by auto at 2019-03-21 10:48:48**/
package com.booting.management.controller.training;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.booting.service.impl.KickbackDetailWebService;
import com.booting.training.dto.KickbackDetailDTO;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/kickbackDetail")
public class KickbackDetailController{

	@Autowired
	private KickbackDetailWebService kickbackDetailWebService;
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(KickbackDetailDTO kickbackDetailDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(kickbackDetailDTO);
		PageList<KickbackDetailDTO> pageList = kickbackDetailWebService.getListForPageKickbackDetail(queryParam, KickbackDetailDTO.class);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}

	@RequestMapping(value = "/editBefore/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long id, Model model){
		KickbackDetailDTO kickbackDetailDTO = this.kickbackDetailWebService.getKickbackDetail(id);
		model.addAttribute("kickbackDetailDTO", kickbackDetailDTO);
		return "management/kickbackDetail/editKickbackDetail";
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(@RequestBody KickbackDetailDTO kickbackDetailDTO){
		ResultMessage resultMessage = null;
		try {
			this.kickbackDetailWebService.updateKickbackDetail(kickbackDetailDTO);
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
	public ResultMessage add(@RequestBody KickbackDetailDTO kickbackDetailDTO){
		ResultMessage resultMessage = null;
		try {
			this.kickbackDetailWebService.saveKickbackDetail(kickbackDetailDTO);
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
}