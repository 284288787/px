/** create by auto at 2018-06-21 14:29:55**/
package com.booting.management.controller.product;

import java.util.HashMap;
import java.util.List;
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

import com.booting.product.conf.ProductConst.ProductType;
import com.booting.product.dto.ProductTypeDTO;
import com.booting.service.impl.ProductTypeWebService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.ResultMessage;

@Controller
@RequestMapping("/productTypeCurriculum")
public class ProductTypeCurriculumController {

  @Autowired
  private ProductTypeWebService productTypeWebService;

  @ResponseBody
  @RequestMapping(value = "/listCurriculumType", method = RequestMethod.POST)
  public Map<String, Object> list(ProductTypeDTO productTypeDTO, Integer page, Integer rows, String sord, String sidx) {
    productTypeDTO.setDeleted(0);
    productTypeDTO.setBusiness(ProductType.curriculum.getBusiness());
    QueryParam queryParam = new QueryParam();
    queryParam.setOrderBy(sidx);
    queryParam.setOrderType(sord);
    queryParam.setPageNo(page);
    queryParam.setPageSize(rows);
    queryParam.setParam(productTypeDTO);
    PageList<ProductTypeDTO> pageList = productTypeWebService.getListForPageProductType(queryParam, ProductTypeDTO.class);
    Map<String, Object> map = new HashMap<>();
    map.put("page", pageList.getPageNo());
    map.put("total", pageList.getTotalPage());
    map.put("records", pageList.getTotalRecord());
    map.put("rows", pageList.getDataList());
    return map;
  }

  @ResponseBody
  @RequestMapping(value = "/allCurriculumType", method = RequestMethod.POST)
  public List<ProductTypeDTO> allCurriculumType() {
    ProductTypeDTO productTypeDTO = new ProductTypeDTO();
    productTypeDTO.setDeleted(0);
    productTypeDTO.setBusiness(ProductType.curriculum.getBusiness());
    List<ProductTypeDTO> list = this.productTypeWebService.getProductTypeList(productTypeDTO);
    return list;
  }

  @RequestMapping(value = "/editBefore/{typeId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
  public String editBefore(@PathVariable Long typeId, Model model) {
    ProductTypeDTO productTypeDTO = this.productTypeWebService.getProductType(typeId);
    model.addAttribute("productTypeDTO", productTypeDTO);
    return "management/producttype/editProductTypeCurriculum";
  }

  @ResponseBody
  @RequestMapping(value = "/edit", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
  public ResultMessage edit(@RequestBody ProductTypeDTO productTypeDTO) {
    ResultMessage resultMessage = null;
    try {
      this.productTypeWebService.updateProductType(productTypeDTO);
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
  public ResultMessage add(@RequestBody ProductTypeDTO productTypeDTO) {
    ResultMessage resultMessage = null;
    try {
      this.productTypeWebService.saveProductType(productTypeDTO);
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
      this.productTypeWebService.enabledProductType(ids);
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
      this.productTypeWebService.disabledProductType(ids);
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
      this.productTypeWebService.deleteProductType(ids);
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