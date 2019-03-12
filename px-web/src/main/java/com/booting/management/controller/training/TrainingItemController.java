/** create by auto at 2017-12-21 16:34:59**/
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booting.management.controller.BaseController;
import com.booting.service.impl.TrainingWebService;
import com.booting.training.dto.TrainingItemDTO;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/trainingItem")
public class TrainingItemController extends BaseController {

  @Autowired
  private TrainingWebService trainingWebService;

  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public Map<String, Object> list(TrainingItemDTO trainingItemDTO, Integer page, Integer rows, String sord, String sidx) {
    trainingItemDTO.setDeleted(0);
    QueryParam queryParam = new QueryParam();
    queryParam.setOrderBy(sidx);
    queryParam.setOrderType(sord);
    queryParam.setPageNo(page);
    queryParam.setPageSize(rows);
    queryParam.setParam(trainingItemDTO);
    PageList<TrainingItemDTO> pageList = trainingWebService.getListForPage(queryParam, TrainingItemDTO.class);
    Map<String, Object> map = new HashMap<>();
    map.put("page", pageList.getPageNo());
    map.put("total", pageList.getTotalPage());
    map.put("records", pageList.getTotalRecord());
    map.put("rows", pageList.getDataList());
    return map;
  }

  @RequestMapping(value = "/editBefore/{itemId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
  public String editBefore(@PathVariable Long itemId, Model model) {
    TrainingItemDTO trainingItemDTO = this.trainingWebService.getTrainingItem(itemId);
    model.addAttribute("trainingItemDTO", trainingItemDTO);
    return "management/training/editTrainingItem";
  }

  @RequestMapping(value = "/addBefore", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
  public String addBefore(Model model){
    model.addAttribute("items", this.trainingWebService.getApplyItems());
    return "management/training/addTrainingItem";
  }
  
  @ResponseBody
  @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
  public ResultMessage edit(@RequestBody TrainingItemDTO trainingItemDTO) {
    ResultMessage resultMessage = null;
    try {
      this.trainingWebService.updateTrainingItem(trainingItemDTO);
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
  public ResultMessage add(@RequestBody TrainingItemDTO trainingItemDTO) {
    ResultMessage resultMessage = null;
    try {
      if (null != trainingItemDTO) {
        trainingItemDTO.setCreateUser(getLoginUserName());
      }
      this.trainingWebService.saveTrainingItem(trainingItemDTO);
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
  public ResultMessage enabled(@RequestParam String ids) {
    ResultMessage resultMessage = null;
    try {
      this.trainingWebService.enabledTrainingItem(ids);
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
  public ResultMessage disabled(@RequestParam String ids) {
    ResultMessage resultMessage = null;
    try {
      this.trainingWebService.disabledTrainingItem(ids);
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
  public ResultMessage delete(@RequestParam String ids) {
    ResultMessage resultMessage = null;
    try {
      this.trainingWebService.deleteTrainingItem(ids);
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