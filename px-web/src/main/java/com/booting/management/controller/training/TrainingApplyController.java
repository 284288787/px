/** create by auto at 2017-12-21 16:42:03**/
package com.booting.management.controller.training;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booting.service.impl.TrainingWebService;
import com.booting.training.dto.ApplyInfoDTO;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/trainingApply")
public class TrainingApplyController {

  @Autowired
  private TrainingWebService trainingWebService;

  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public Map<String, Object> list(ApplyInfoDTO applyInfoDTO, Integer page, Integer rows, String sord, String sidx) {
    QueryParam queryParam = new QueryParam();
    queryParam.setOrderBy(sidx);
    queryParam.setOrderType(sord);
    queryParam.setPageNo(page);
    queryParam.setPageSize(rows);
    queryParam.setParam(applyInfoDTO);
    PageList<ApplyInfoDTO> pageList = trainingWebService.getListForPageApplyInfo(queryParam, ApplyInfoDTO.class);
    Map<String, Object> map = new HashMap<>();
    map.put("page", pageList.getPageNo());
    map.put("total", pageList.getTotalPage());
    map.put("records", pageList.getTotalRecord());
    map.put("rows", pageList.getDataList());
    return map;
  }

  @RequestMapping(value = "/editBefore/{applyId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
  public String editBefore(@PathVariable Long applyId, Model model) {
    ApplyInfoDTO applyInfoDTO = this.trainingWebService.getApplyInfo(applyId);
    model.addAttribute("applyInfoDTO", applyInfoDTO);
    return "management/trainingapply/editApplyInfo";
  }

  @ResponseBody
  @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
  public ResultMessage edit(ApplyInfoDTO applyInfoDTO) {
    ResultMessage resultMessage = null;
    try {
      this.trainingWebService.updateApplyInfo(applyInfoDTO);
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
  @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
  public ResultMessage delete(@RequestParam String ids) {
    ResultMessage resultMessage = null;
    try {
      this.trainingWebService.deleteTrainingApply(ids);
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

  @RequestMapping(value = "/exportRecord", method = RequestMethod.GET)
  public void exportRecord(HttpServletRequest request, HttpServletResponse response) {
    trainingWebService.exportRecord(response);
  }
}