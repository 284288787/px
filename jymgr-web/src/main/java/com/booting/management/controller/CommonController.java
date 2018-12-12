/**create by liuhua at 2017年5月20日 下午3:14:15**/
package com.booting.management.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.booting.common.SpringBeanUtil;
import com.booting.pub.dto.AreaDTO;
import com.booting.service.CommonWebService;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.v2.ErrorResultMessage;
import com.star.framework.specification.result.v2.ResultMessage;
import com.star.framework.specification.result.v2.SuccessResultMessage;
import com.star.framework.specification.utils.ParamHandler;

@Controller
@RequestMapping("/common")
public class CommonController {

  @Autowired
  private CommonWebService commonWebService;

  private String realPath;
  
  @RequestMapping(value = "/{foldName}/{pathName}", method = RequestMethod.GET)
  public String path(@PathVariable String foldName, @PathVariable String pathName) {
    return foldName + "/" + pathName;
  }

  @RequestMapping(value = "/management/{foldName}/{pathName}", method = RequestMethod.GET)
  public String managementPath(@PathVariable String foldName, @PathVariable String pathName) {
    return "management/" + foldName + "/" + pathName;
  }

  @RequestMapping(value = "/chooseCity", method = RequestMethod.GET)
  public String chooseCity(Model model) {
    List<AreaDTO> areas = commonWebService.getAreas();
    model.addAttribute("areas", areas);
    return "management/common/chooseCity";
  }

  @ResponseBody
  @RequestMapping(value = "/uploadFile")
  public void uploadFile(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws IOException {
    String type = multipartRequest.getParameter("type");
    ResultMessage resultMessage = null;
    try {
      List<Object> list = new ArrayList<>();
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
        MultipartFile mf = entity.getValue();
        // 图片上传
        byte[] bs = mf.getBytes();
        if (bs == null || bs.length == 0) {
          throw new ArgsException(FailureCode.ERR_000, "上传失败");
        } else {
          if (StringUtils.isNotBlank(type) && type.equals("excel")) {
            String handle = multipartRequest.getParameter("handle");
            String errorImport = multipartRequest.getParameter("errorImport");
            Object o = this.commonWebService.importExcel(bs, mf.getName(), handle, errorImport);
            list.add(o);
          } else {
            // String realPath =
            // multipartRequest.getServletContext().getRealPath("/static/upload/images/");
//            String realPath = "e:/upload";
            // String realPath = "/opt/upload/jymgr";
            if (StringUtils.isBlank(realPath)) {
              ViewResolver viewResolver = SpringBeanUtil.getBean("viewResolver", ViewResolver.class);
              if (null != viewResolver && viewResolver instanceof InternalResourceViewResolver) {
                InternalResourceViewResolver vr = (InternalResourceViewResolver) viewResolver;
                realPath = (String) vr.getAttributesMap().get("uploadPath");
              }
            }
            File dir = new File(realPath);
            if (!dir.exists()) {
              dir.mkdirs();
            }
            String fileName = UUID.randomUUID().toString().replace("-", "") + getSuffix(mf.getOriginalFilename());
            File f = new File(dir, fileName);
            FileOutputStream fout = null;
            try {
              fout = new FileOutputStream(f);
              fout.write(bs);
              String path = "/photo/" + fileName;
              list.add(path);
            } finally {
              if (fout != null) {
                fout.close();
              }
            }
          }
        }
      }
      resultMessage = new SuccessResultMessage(list);
    } catch (ArgsException e) {
      resultMessage = new ErrorResultMessage(e.getCode(), e.getMessage());
    } catch (Exception e) {
      resultMessage = new ErrorResultMessage(FailureCode.ERR_001);
    }
    response.setContentType("application/json;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(ParamHandler.objToString(resultMessage));
    response.getWriter().close();
  }

  private static String getSuffix(String originalFilename) {
    int idx = originalFilename.lastIndexOf(".");
    if (idx != -1) {
      return originalFilename.substring(idx);
    }
    return "";
  }
}
