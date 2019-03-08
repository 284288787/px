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

import com.booting.pub.dto.AreaDTO;
import com.booting.service.CommonWebService;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.v2.ErrorResultMessage;
import com.star.framework.specification.result.v2.ResultMessage;
import com.star.framework.specification.result.v2.SuccessResultMessage;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.utils.QrCodeUtil;

@Controller
@RequestMapping("/common")
public class CommonController {

  @Autowired
  private CommonWebService commonWebService;

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

  @RequestMapping(value = "/ewm/{promoterId}", method = RequestMethod.GET)
  public void shopewm(@PathVariable Long promoterId, HttpServletResponse response) throws IOException {
    try {
      response.setContentType("image/jpeg");
      // 禁止图像缓存。
      response.setHeader("Pragma", "no-cache");
      response.setHeader("Cache-Control", "no-cache");
      response.setDateHeader("Expires", 0);
      String content = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx18e37c25035345e3"
          + "&redirect_uri=http%3a%2f%2fpx.jytzn.com%2fpx%2ftraining%2ftraining.html&response_type=code"
          + "&scope=snsapi_base&state=1a" + promoterId + "#wechat_redirect";
      QrCodeUtil.create("http://px.jytzn.com/photo/logo.png", content, response.getOutputStream());
    } catch (Throwable e) {
      Throwable t = e.getCause();
      if (null != t) {
        e = t;
      }
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(e.getMessage());
      response.getWriter().flush();
    }
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
            // String realPath = "e:/upload";
            String realPath = "/home/tomcat/upload/photo";
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
