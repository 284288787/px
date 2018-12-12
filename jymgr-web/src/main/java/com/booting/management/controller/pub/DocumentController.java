/**create by liuhua at 2017年6月13日 下午3:15:15**/
package com.booting.management.controller.pub;

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

import com.booting.pub.dto.DocumentDTO;
import com.booting.pub.facade.CommonFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/doc")
public class DocumentController {

	@Autowired
	private CommonFacade commonFacade;
	
	@ResponseBody
	@RequestMapping(value = "/docList", method = RequestMethod.POST)
	public Map<String, Object> list(DocumentDTO documentDTO, Integer page, Integer rows, String sord, String sidx){
		QueryParam queryParam = new QueryParam();
		queryParam.setOrderBy(sidx);
		queryParam.setOrderType(sord);
		queryParam.setPageNo(page);
		queryParam.setPageSize(rows);
		queryParam.setParam(documentDTO);
		PageList<DocumentDTO> pageList = commonFacade.getDocumentListForPage(queryParam);
		Map<String, Object> map = new HashMap<>();
		map.put("page", pageList.getPageNo());
		map.put("total", pageList.getTotalPage());
		map.put("records", pageList.getTotalRecord());
		map.put("rows", pageList.getDataList());
		return map;
	}
	
	@RequestMapping(value = "/editBefore/{docId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String editBefore(@PathVariable Long docId, Model model){
		DocumentDTO documentDTO = this.commonFacade.getDocument(docId);
		model.addAttribute("documentDTO", documentDTO);
		return "management/doc/editDoc";
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage edit(DocumentDTO documentDTO){
		ResultMessage resultMessage = null;
		try {
			this.commonFacade.updateDocument(documentDTO);
			resultMessage = new ResultMessage("ok", "");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/enabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage enabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.commonFacade.enabledDocument(ids);
			resultMessage = new ResultMessage("ok", "");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/disabled", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage disabled(@RequestParam String ids){
		ResultMessage resultMessage = null;
		try {
			this.commonFacade.disabledDocument(ids);
			resultMessage = new ResultMessage("ok", "");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", FailureCode.ERR_001);
		}
		return resultMessage;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage add(DocumentDTO documentDTO){
		ResultMessage resultMessage = null;
		try {
			this.commonFacade.saveDocument(documentDTO);
			resultMessage = new ResultMessage("ok", "");
		} catch (ArgsException e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", e.getCode(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("", FailureCode.ERR_001);
		}
		return resultMessage;
	}
}
