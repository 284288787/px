/** create by liuhua at 2018年9月13日 上午11:12:07 **/
package com.booting.management.controller.bracelet;

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
import com.booting.bracelet.dto.PointLevelDTO;
import com.booting.service.impl.PointWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/pointConfig")
public class PointLevelController {

  @Autowired
  private PointWebService pointWebService;

  @ResponseBody
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public Map<String, Object> list(PointLevelDTO pointLevelDTO, Integer page, Integer rows, String sord, String sidx) {
    QueryParam queryParam = new QueryParam();
    queryParam.setOrderBy(sidx);
    queryParam.setOrderType(sord);
    queryParam.setPageNo(page);
    queryParam.setPageSize(rows);
    queryParam.setParam(pointLevelDTO);
    PageList<PointLevelDTO> pageList = pointWebService.getListForPagePointLevel(queryParam, PointLevelDTO.class);
    Map<String, Object> map = new HashMap<>();
    map.put("page", pageList.getPageNo());
    map.put("total", pageList.getTotalPage());
    map.put("records", pageList.getTotalRecord());
    map.put("rows", pageList.getDataList());
    return map;
  }

  @RequestMapping(value = "/editBefore/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
  public String editBefore(@PathVariable Long id, Model model) {
    PointLevelDTO pointLevelDTO = this.pointWebService.getPointLevel(id);
    model.addAttribute("pointLevelDTO", pointLevelDTO);
    return "management/bracelet/editPointLevel";
  }

  @ResponseBody
  @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
  public ResultMessage edit(@RequestBody PointLevelDTO pointLevelDTO) {
    ResultMessage resultMessage = null;
    try {
      this.pointWebService.updatePointLevel(pointLevelDTO);
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
  public ResultMessage add(@RequestBody PointLevelDTO pointLevelDTO) {
    ResultMessage resultMessage = null;
    try {
      this.pointWebService.savePointLevel(pointLevelDTO);
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
  @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
  public ResultMessage delete(@RequestParam String ids){
      ResultMessage resultMessage = null;
      try {
          this.pointWebService.deletePointLevel(ids);
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
