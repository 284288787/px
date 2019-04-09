/**create by liuhua at 2017年12月21日 下午2:37:13**/
package com.booting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class CreateJspFile {

  public static void main(String[] args) throws IOException {
    CreateJspFile ccf = new CreateJspFile();
    ccf.create();
  }

  private boolean overwrite = true;

  private String basePath, jsPath;

  private String moduleName = "physicalClass";
  private String dtoName = "PhysicalClassDTO";
  private String pkIdName = "physicalClassId";
  private String controllerMappingName = "physicalClass";
  private String dtoCaption = "体测课";
  String methodSuffix = dtoName.replace("DTO", "");
  String dtoVariable = firstToLower(dtoName);

  private List<Map<String, String>> queryCols = new ArrayList<Map<String, String>>() {

    private static final long serialVersionUID = 1L;
    {
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "课程标题");
          put("name", "title");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "上课时间");
          put("name", "schoolTime");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "是否可用");
          put("name", "enabled");
          put("type", "select");
          put("options", "1:可用;0:禁用");
        }
      });
    }
  };
  private List<Map<String, String>> cols = new ArrayList<Map<String, String>>() {
    private static final long serialVersionUID = 1L;
    {
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "id");
          put("name", "physicalClassId");
          put("width", "30");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "课程标题");
          put("name", "title");
          put("width", "70");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "上课时间");
          put("name", "schoolTime");
          put("width", "50");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "报名截止时间");
          put("name", "deadlineTime");
          put("width", "50");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "价格");
          put("name", "price");
          put("width", "50");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "上课地点");
          put("name", "address");
          put("width", "200");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "是否可用");
          put("name", "enabled");
          put("width", "30");
          put("type", "select");
          put("options", "{value:'1:可用;0:禁用'}");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "创建时间");
          put("name", "createTime");
          put("width", "50");
          put("type", "text");
        }
      });
    }
  };

  private List<Map<String, String>> addCols = new ArrayList<Map<String, String>>() {
    private static final long serialVersionUID = 1L;
    {
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "课程标题");
          put("name", "title");
          put("required", "true-必填");
          put("rangelength", "2,50-标题长度范围[2,50]");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "上课时间");
          put("name", "schoolTime");
          put("required", "true-必选");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "报名截止时间");
          put("name", "deadlineTime");
          put("required", "true-必选");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "价格");
          put("name", "price");
          put("required", "true-必填");
          put("money", "true");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "上课地点");
          put("name", "address");
          put("required", "true-必填");
          put("rangelength", "8,100-标题长度范围[8,100]");
          put("type", "text");
        }
      });
    }
  };

  private List<Map<String, String>> editCols = new ArrayList<Map<String, String>>() {
    private static final long serialVersionUID = 1L;
    {
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "课程标题");
          put("name", "title");
          put("required", "true-必填");
          put("rangelength", "2,50-标题长度范围[2,50]");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "上课时间");
          put("name", "schoolTime");
          put("required", "true-必选");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "报名截止时间");
          put("name", "deadlineTime");
          put("required", "true-必选");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "价格");
          put("name", "price");
          put("required", "true-必填");
          put("money", "true");
          put("type", "text");
        }
      });
      add(new HashMap<String, String>() {
        private static final long serialVersionUID = 1L;
        {
          put("caption", "上课地点");
          put("name", "address");
          put("required", "true-必填");
          put("rangelength", "8,100-标题长度范围[8,100]");
          put("type", "text");
        }
      });
    }
  };

  public CreateJspFile() {
    String fileName = getClass().getSimpleName() + ".class";
    URL url = getClass().getResource(fileName);
    basePath = url.getPath().replace("target/classes", "src/main/webapp/WEB-INF/jsp/management/").replace(fileName, "").replace("/com/booting/", "");
    jsPath = basePath.replace("/WEB-INF/jsp", "/static/js");
  }

  public void create() throws IOException {
    buildList();
    buildListJs();
    buildAdd();
    buildAddJs();
    buildEdit();
    buildEditJs();
  }

  private void buildEditJs() {
    File listFile = new File(jsPath + moduleName + "/edit" + methodSuffix + ".js");
    if (!overwrite && listFile.exists()) {
      System.out.println("edit" + methodSuffix + ".js 已经存在,不再生成.");
      return;
    }
    StringBuilder head = new StringBuilder();
    head.append("//var kk;\n");
    head.append("var basePath=$('#basePath').val(); \n");
    head.append("var parentParams=artDialog.data('params'); \n");
    head.append("var utilsHandle = new UtilsHandle({\n");
    head.append("\tbasePath: basePath, \n");
    head.append("\tchooseCity: { \n");
    head.append("\t\t'object': $('input[name=areaName]'),\n");
    head.append("\t\t'width': '70%', \n");
    head.append("\t\t'height': '600px',\n");
    head.append("\t\t'areaId': $(':hidden[name=areaId]').val(),\n");
    head.append("\t\t'callback':function(areaId, areaName){\n");
    head.append("\t\t\t$(':hidden[name=areaId]').val(areaId);\n");
    head.append("\t\t\t$(':input[name=areaName]').val(areaName); \n");
    head.append("\t\t} \n");
    head.append("\t},\n");
    head.append("//\tkindEditor: { \n");
    head.append("//\t\tobject: 'textarea[name=notice]',\n");
    head.append("//\t\twidth: '100%',\n");
    head.append("//\t\theight: '350px',\n");
    head.append("//\t\tafterCreate: function(obj){ \n");
    head.append("//\t\t\tkk = obj; \n");
    head.append("//\t\t} \n");
    head.append("//\t},\n");
    head.append("//\tuploadFile: { \n");
    head.append("//\t\tuploadBtn: $('#uploadBtn'), \n");
    head.append("//\t\tuploadFileId: 'uploadImg',\n");
    head.append("//\t\tsuccess: function (data, textStatus) {\n");
    head.append("//\t\t\tvar pic = data.data;\n");
    head.append("//\t\t\t$('#uploadBtn').parent().append('<span style=\"position: relative;\"><img class=\"dataImg\" width=\"70px\" height=\"70px\" src=\"'+(basePath+pic)+'\" data=\"'+pic+'\"><div class=\"close\">X</div></span>'); \n");
    head.append("//\t\t},\n");
    head.append("//\t\tcomplete: function (XMLHttpRequest, textStatus) { \n");
    head.append("//\t\t\t$('.close').unbind().click(function(){\n");
    head.append("//\t\t\t\t$(this).parent().remove();\n");
    head.append("//\t\t\t}); \n");
    head.append("//\t\t\t$('.dataImg').unbind().click(function(){\n");
    head.append("//\t\t\t\tif($('#viewImg').length>0){ \n");
    head.append("//\t\t\t\t\t$('#viewImg').remove(); \n");
    head.append("//\t\t\t\t} \n");
    head.append("//\t\t\t\t$('body').append('<img id=\"viewImg\" style=\"display:none\" src=\"'+$(this).attr(\"src\")+'\">');\n");
    head.append("//\t\t\t\t$('#viewImg').load(function(){\n");
    head.append("//\t\t\t\t\tvar w=$(this).width();\n");
    head.append("//\t\t\t\t\tvar h=$(this).height(); \n");
    head.append("//\t\t\t\tartDialog.alert2('<div style=\"width:'+w+'px;height:'+h+'px\"><img src=\"'+$(this).attr(\"src\")+'\">') \n");
    head.append("//\t\t\t\t}); \n");
    head.append("//\t\t\t}); \n");
    head.append("//\t\t} \n");
    head.append("//\t} \n");
    head.append("});\n");
    head.append("$(function(){\n");
    head.append("//\t$('.close').unbind().click(function(){\n");
    head.append("//\t\t$(this).parent().remove();\n");
    head.append("//\t}); \n");
    head.append("//\t$('.dataImg').unbind().click(function(){\n");
    head.append("//\t\tif($('#viewImg').length>0){ \n");
    head.append("//\t\t\t$('#viewImg').remove(); \n");
    head.append("//\t\t} \n");
    head.append("//\t\t$('body').append('<img id=\"viewImg\" style=\"display:none\" src=\"'+$(this).attr(\"src\")+'\">');\n");
    head.append("//\t\t$('#viewImg').load(function(){\n");
    head.append("//\t\t\tvar w=$(this).width();\n");
    head.append("//\t\t\tvar h=$(this).height(); \n");
    head.append("//\t\t\t\tartDialog.alert2('<div style=\"width:'+w+'px;height:'+h+'px\"><img src=\"'+$(this).attr(\"src\")+'\">') \n");
    head.append("//\t\t}); \n");
    head.append("//\t}); \n");
    head.append("\t$('#edit" + methodSuffix + "Form').validate({ \n");
    head.append("\t\trules: {\n");
    int idx = 1;
    for (Map<String, String> col : editCols) {
      String mobile = col.get("mobile");
      String required = col.get("required");
      String rangelength = col.get("rangelength");
      String name = col.get("name");
      head.append("\t\t\t" + name + ": {\n");
      if (StringUtils.isNotBlank(required)) {
        boolean r = Boolean.parseBoolean(required.split("-")[0]);
        if (r) {
          head.append("\t\t\t\trequired: true, \n");
        }
      }
      if (StringUtils.isNoneBlank(mobile) && "true".equals(mobile)) {
        head.append("\t\t\t\tmobile: true,\n");
      }
      if (StringUtils.isNoneBlank(rangelength)) {
        String[] qj = rangelength.split("-")[0].split(",");
        head.append("\t\t\t\trangelength: [" + qj[0] + ", " + qj[1] + "]\n");
      }
      head.append("\t\t\t}");
      if (idx != editCols.size()) {
        head.append(",");
      }
      head.append("\n");
      idx++;
    }
    head.append("\t\t},\n");
    head.append("\t\tmessages: { \n");
    idx = 1;
    for (Map<String, String> col : editCols) {
      String mobile = col.get("mobile");
      String required = col.get("required");
      String rangelength = col.get("rangelength");
      String name = col.get("name");
      head.append("\t\t\t" + name + ": {\n");
      if (StringUtils.isNotBlank(required)) {
        boolean r = Boolean.parseBoolean(required.split("-")[0]);
        if (r) {
          head.append("\t\t\t\trequired: '" + required.split("-")[1] + "', \n");
        }
      }
      if (StringUtils.isNoneBlank(mobile) && "true".equals(mobile)) {
        head.append("\t\t\t\tmobile: '手机号错误',\n");
      }
      if (StringUtils.isNoneBlank(rangelength)) {
        head.append("\t\t\t\trangelength: '" + rangelength.split("-")[1] + "'\n");
      }
      head.append("\t\t\t}");
      if (idx != editCols.size()) {
        head.append(",");
      }
      head.append("\n");
      idx++;
    }

    head.append("\t\t} \n");
    head.append("\t}); \n");
    head.append("\t\n");
    head.append("\t$('#saveBtn').click(function(){ \n");
    head.append("\t\tvar flag = $('#edit" + methodSuffix + "Form').valid();\n");
    head.append("\t\tif(! flag) return;\n");
    head.append("\t\tvar data=$('#edit" + methodSuffix + "Form').serializeArray(); \n");
    head.append("\t\tvar params = {};\n");
    head.append("\t\t$.each(data, function(i, field){\n");
    head.append("\t\t\tvar name = field.name;\n");
    head.append("\t\t\tparams[name] = field.value; \n");
    head.append("\t\t\tconsole.log(name + '' + field.value)\n");
    head.append("\t\t}); \n");
    head.append("\t\tconsole.log(JSON.stringify(params));\n");
    head.append("\t\t$.ajax({\n");
    head.append("\t\t\tcontentType:'application/json', \n");
    head.append("\t\t\turl: basePath+'" + controllerMappingName + "/edit', \n");
    head.append("\t\t\tdata: JSON.stringify(params), \n");
    head.append("\t\t\ttype: 'post', \n");
    head.append("\t\t\tdataType: 'json', \n");
    head.append("\t\t\tsuccess: function(res){ \n");
    head.append("\t\t\t\tif(res.status=='SUCCESS'){\n");
    head.append("\t\t\t\t\tparentParams.query(); \n");
    head.append("\t\t\t\t\tart.dialog.close(); \n");
    head.append("\t\t\t\t}else{\n");
    head.append("\t\t\t\t\tartDialog.alert(res.errorMessage) \n");
    head.append("\t\t\t\t} \n");
    head.append("\t\t\t} \n");
    head.append("\t\t}); \n");
    head.append("\t});\n");
    head.append("});");

    createFile(listFile, head);
    System.out.println("edit" + methodSuffix + ".js 建立完成.");
  }

  private void buildEdit() {
    File listFile = new File(basePath + moduleName + "/edit" + methodSuffix + ".jsp");
    if (!overwrite && listFile.exists()) {
      System.out.println("edit" + methodSuffix + ".jsp 已经存在,不再生成.");
      return;
    }
    StringBuilder head = new StringBuilder();
    head.append("<%@ page language=\"java\" contentType=\"text/html; charset=utf-8\" pageEncoding=\"utf-8\"%>\n");
    head.append("<!DOCTYPE html> \n");
    head.append("<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\"%> \n");
    head.append("<%@ taglib uri=\"http://java.sun.com/jsp/jstl/fmt\" prefix=\"fmt\"%>\n");
    head.append("<%@ taglib uri=\"http://www.99love.net/jsp/tag/star-tags\" prefix=\"st\"%>\n");
    head.append("<%@ taglib uri=\"http://www.99love.net/jsp/tag/functions\" prefix=\"fn\"%>\n");
    head.append("<html>\n");
    head.append("<head>\n");
    head.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> \n");
    head.append("<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no\">\n");
    head.append("<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/jquery-ui-1.12.1/jquery-ui.min.css\" />\n");
    head.append("<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/jqGrid/css/ui.jqgrid.css\" />\n");
    head.append("<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/css/buttons.css\" /> \n");
    head.append("<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/js/tools/artDialog4.1.2/skins/blue.css\" />\n");
    head.append("<link rel=\"stylesheet\" href=\"${basePath}static/js/tools/kindeditor/themes/default/default.css\" /> \n");
    head.append("<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/css/editEntity.css\" />\n");
    head.append("</head> \n");
    head.append("<body>\n");
    head.append("\t<div class=\"edit-container\"> \n");
    head.append("\t\t<form action=\"\" id=\"edit" + methodSuffix + "Form\"> \n");
    head.append("\t\t<input type=\"hidden\" name=\"" + pkIdName + "\" value=\"${" + dtoVariable + "." + pkIdName + "}\">\n");
    head.append("\t\t<ul> \n");
    for (Map<String, String> col : editCols) {
      String type = col.get("type");
      String name = col.get("name");
      String caption = col.get("caption");
      switch (type) {
      case "text":
        head.append("\t\t\t<li><strong>" + caption + "：</strong><span><input type=\"text\" name=\"" + name + "\" placeholder=\"" + caption + "\" value=\"${" + dtoVariable + "." + name + "}\"></span></li>\n");
        break;
      case "password":
        head.append("\t\t\t<li><strong>" + caption + "：</strong><span><input type=\"password\" name=\"" + name + "\" placeholder=\"" + caption + "\" ></span></li> \n");
        break;
      case "area":
        String viewName = col.get("viewName");
        head.append("\t\t\t<li><strong>" + caption + "：</strong><span><input type=\"text\" name=\"" + viewName + "\" placeholder=\"选择地区\" value=\"${" + dtoVariable + "." + viewName + "}\" readonly><input type=\"hidden\" name=\"" + name + "\" value=\"${" + dtoVariable + "." + name + "}\"></span></li> \n");
        break;
      case "textarea":
        head.append("\t\t\t<li style=\"height:90px;\"><strong>" + caption + "：</strong><span> \n");
        head.append("\t\t\t\t<textarea rows=\"1\" cols=\"1\" style=\"width:800px; height:80px;\" name=\"" + name + "\" placeholder=\"" + caption + "\">${" + dtoVariable + "." + name + "}</textarea>\n");
        break;
      default:
        break;
      }
    }
    head.append("\t\t\t</span></li> \n");
    head.append("\t\t</ul>\n");
    head.append("\t\t</form>\n");
    head.append("\t\t<div class=\"btnGroup\"> \n");
    head.append("\t\t\t<input type=\"button\" value=\"保存\" class=\"button blue\" id=\"saveBtn\">\n");
    head.append("\t\t\t<input type=\"button\" value=\"关闭\" class=\"button grey\" onclick=\"art.dialog.close();\"> \n");
    head.append("\t\t</div> \n");
    head.append("\t</div> \n");
    head.append("\t<div id=\"uploadDiv\" style=\"display:none\">\n");
    head.append("\t\t<input type=\"file\" name=\"uploadImg\" id=\"uploadImg\">\n");
    head.append("\t</div> \n");
    head.append("\t<input id=\"basePath\" type=\"hidden\" value=\"${basePath}\">\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.source.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/ajaxfileupload.js\"></script>\n");
    head.append("\t<script charset=\"utf-8\" src=\"${basePath}static/js/tools/kindeditor/kindeditor.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js\"></script> \n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/jquery-validation-1.15.0/dist/additional-methods-local.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/My97DatePicker/WdatePicker.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/management/utils.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/management/" + moduleName + "/edit" + methodSuffix + ".js\"></script> \n");
    head.append("</body>\n");
    head.append("</html>");

    createFile(listFile, head);
    System.out.println("edit" + methodSuffix + ".jsp 建立完成.");
  }

  private void buildAddJs() {
    File listFile = new File(jsPath + moduleName + "/add" + methodSuffix + ".js");
    if (!overwrite && listFile.exists()) {
      System.out.println("add" + methodSuffix + ".js 已经存在,不再生成.");
      return;
    }
    StringBuilder head = new StringBuilder();
    head.append("//var kk;\n");
    head.append("var basePath=$('#basePath').val(); \n");
    head.append("var parentParams=artDialog.data('params'); \n");
    head.append("var utilsHandle = new UtilsHandle({\n");
    head.append("\tbasePath: basePath, \n");
    head.append("\tchooseCity: { \n");
    head.append("\t\t'object': $('input[name=areaName]'),\n");
    head.append("\t\t'width': '70%', \n");
    head.append("\t\t'height': '600px',\n");
    head.append("\t\t'areaId': $(':hidden[name=areaId]').val(),\n");
    head.append("\t\t'callback':function(areaId, areaName){\n");
    head.append("\t\t\t$(':hidden[name=areaId]').val(areaId);\n");
    head.append("\t\t\t$(':input[name=areaName]').val(areaName); \n");
    head.append("\t\t} \n");
    head.append("\t},\n");
    head.append("//\tkindEditor: { \n");
    head.append("//\t\tobject: 'textarea[name=notice]',\n");
    head.append("//\t\twidth: '100%',\n");
    head.append("//\t\theight: '350px',\n");
    head.append("//\t\tafterCreate: function(obj){ \n");
    head.append("//\t\t\tkk = obj; \n");
    head.append("//\t\t} \n");
    head.append("//\t},\n");
    head.append("//\tuploadFile: { \n");
    head.append("//\t\tuploadBtn: $('#uploadBtn'), \n");
    head.append("//\t\tuploadFileId: 'uploadImg',\n");
    head.append("//\t\tsuccess: function (data, textStatus) {\n");
    head.append("//\t\t\tvar pic = data.data;\n");
    head.append("//\t\t\t$('#uploadBtn').parent().append('<span style=\"position: relative;\"><img class=\"dataImg\" width=\"70px\" height=\"70px\" src=\"'+(basePath+pic)+'\" data=\"'+pic+'\"><div class=\"close\">X</div></span>'); \n");
    head.append("//\t\t},\n");
    head.append("//\t\tcomplete: function (XMLHttpRequest, textStatus) { \n");
    head.append("//\t\t\t$('.close').unbind().click(function(){\n");
    head.append("//\t\t\t\t$(this).parent().remove();\n");
    head.append("//\t\t\t}); \n");
    head.append("//\t\t\t$('.dataImg').unbind().click(function(){\n");
    head.append("//\t\t\t\tif($('#viewImg').length>0){ \n");
    head.append("//\t\t\t\t\t$('#viewImg').remove(); \n");
    head.append("//\t\t\t\t} \n");
    head.append("//\t\t\t\t$('body').append('<img id=\"viewImg\" style=\"display:none\" src=\"'+$(this).attr(\"src\")+'\">');\n");
    head.append("//\t\t\t\t$('#viewImg').load(function(){\n");
    head.append("//\t\t\t\t\tvar w=$(this).width();\n");
    head.append("//\t\t\t\t\tvar h=$(this).height(); \n");
    head.append("//\t\t\t\tartDialog.alert2('<div style=\"width:'+w+'px;height:'+h+'px\"><img src=\"'+$(this).attr(\"src\")+'\">') \n");
    head.append("//\t\t\t\t}); \n");
    head.append("//\t\t\t}); \n");
    head.append("//\t\t} \n");
    head.append("//\t} \n");
    head.append("});\n");
    head.append("$(function(){\n");
    head.append("//\t$('.close').unbind().click(function(){\n");
    head.append("//\t\t$(this).parent().remove();\n");
    head.append("//\t}); \n");
    head.append("//\t$('.dataImg').unbind().click(function(){\n");
    head.append("//\t\tif($('#viewImg').length>0){ \n");
    head.append("//\t\t\t$('#viewImg').remove(); \n");
    head.append("//\t\t} \n");
    head.append("//\t\t$('body').append('<img id=\"viewImg\" style=\"display:none\" src=\"'+$(this).attr(\"src\")+'\">');\n");
    head.append("//\t\t$('#viewImg').load(function(){\n");
    head.append("//\t\t\tvar w=$(this).width();\n");
    head.append("//\t\t\tvar h=$(this).height(); \n");
    head.append("//\t\t\t\tartDialog.alert2('<div style=\"width:'+w+'px;height:'+h+'px\"><img src=\"'+$(this).attr(\"src\")+'\">') \n");
    head.append("//\t\t}); \n");
    head.append("//\t}); \n");
    head.append("\t$('#edit" + methodSuffix + "Form').validate({ \n");
    head.append("\t\trules: {\n");
    int idx = 1;
    for (Map<String, String> col : addCols) {
      String mobile = col.get("mobile");
      String required = col.get("required");
      String rangelength = col.get("rangelength");
      String name = col.get("name");
      head.append("\t\t\t" + name + ": {\n");
      if (StringUtils.isNotBlank(required)) {
        boolean r = Boolean.parseBoolean(required.split("-")[0]);
        if (r) {
          head.append("\t\t\t\trequired: true, \n");
        }
      }
      if (StringUtils.isNoneBlank(mobile) && "true".equals(mobile)) {
        head.append("\t\t\t\tmobile: true,\n");
      }
      if (StringUtils.isNoneBlank(rangelength)) {
        String[] qj = rangelength.split("-")[0].split(",");
        head.append("\t\t\t\trangelength: [" + qj[0] + ", " + qj[1] + "]\n");
      }
      head.append("\t\t\t}");
      if (idx != addCols.size()) {
        head.append(",");
      }
      head.append("\n");
      idx++;
    }
    head.append("\t\t},\n");
    head.append("\t\tmessages: { \n");
    idx = 1;
    for (Map<String, String> col : addCols) {
      String mobile = col.get("mobile");
      String required = col.get("required");
      String rangelength = col.get("rangelength");
      String name = col.get("name");
      head.append("\t\t\t" + name + ": {\n");
      if (StringUtils.isNotBlank(required)) {
        boolean r = Boolean.parseBoolean(required.split("-")[0]);
        if (r) {
          head.append("\t\t\t\trequired: '" + required.split("-")[1] + "', \n");
        }
      }
      if (StringUtils.isNoneBlank(mobile) && "true".equals(mobile)) {
        head.append("\t\t\t\tmobile: '手机号错误',\n");
      }
      if (StringUtils.isNoneBlank(rangelength)) {
        head.append("\t\t\t\trangelength: '" + rangelength.split("-")[1] + "'\n");
      }
      head.append("\t\t\t}");
      if (idx != addCols.size()) {
        head.append(",");
      }
      head.append("\n");
      idx++;
    }
    head.append("\t\t} \n");
    head.append("\t}); \n");
    head.append("\t\n");
    head.append("\t$('#saveBtn').click(function(){ \n");
    head.append("\t\tvar flag = $('#edit" + methodSuffix + "Form').valid();\n");
    head.append("\t\tif(! flag) return;\n");
    head.append("\t\tvar data=$('#edit" + methodSuffix + "Form').serializeArray(); \n");
    head.append("\t\tvar params = {};\n");
    head.append("\t\t$.each(data, function(i, field){\n");
    head.append("\t\t\tvar name = field.name;\n");
    head.append("\t\t\tparams[name] = field.value; \n");
    head.append("\t\t\tconsole.log(name + '' + field.value)\n");
    head.append("\t\t}); \n");
    head.append("\t\tconsole.log(JSON.stringify(params));\n");
    head.append("\t\t$.ajax({\n");
    head.append("\t\t\tcontentType:'application/json', \n");
    head.append("\t\t\turl: basePath+'" + controllerMappingName + "/add', \n");
    head.append("\t\t\tdata: JSON.stringify(params), \n");
    head.append("\t\t\ttype: 'post', \n");
    head.append("\t\t\tdataType: 'json', \n");
    head.append("\t\t\tsuccess: function(res){ \n");
    head.append("\t\t\t\tif(res.status=='SUCCESS'){\n");
    head.append("\t\t\t\t\tparentParams.query(); \n");
    head.append("\t\t\t\t\tart.dialog.close(); \n");
    head.append("\t\t\t\t}else{\n");
    head.append("\t\t\t\t\tartDialog.alert(res.errorMessage) \n");
    head.append("\t\t\t\t} \n");
    head.append("\t\t\t} \n");
    head.append("\t\t}); \n");
    head.append("\t});\n");
    head.append("});");

    createFile(listFile, head);
    System.out.println("add" + methodSuffix + ".js 建立完成.");
  }

  private void buildAdd() {
    File listFile = new File(basePath + moduleName + "/add" + methodSuffix + ".jsp");
    if (!overwrite && listFile.exists()) {
      System.out.println("add" + methodSuffix + ".jsp 已经存在,不再生成.");
      return;
    }
    StringBuilder head = new StringBuilder();
    head.append("<%@ page language=\"java\" contentType=\"text/html; charset=utf-8\" pageEncoding=\"utf-8\"%>\n");
    head.append("<!DOCTYPE html> \n");
    head.append("<%@ taglib uri=\"http://java.sun.com/jsp/jstl/core\" prefix=\"c\"%> \n");
    head.append("<%@ taglib uri=\"http://java.sun.com/jsp/jstl/fmt\" prefix=\"fmt\"%>\n");
    head.append("<%@ taglib uri=\"http://www.99love.net/jsp/tag/star-tags\" prefix=\"st\"%>\n");
    head.append("<%@ taglib uri=\"http://www.99love.net/jsp/tag/functions\" prefix=\"fn\"%>\n");
    head.append("<html>\n");
    head.append("<head>\n");
    head.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> \n");
    head.append("<meta name=\"viewport\" content=\"width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no\">\n");
    head.append("<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/jquery-ui-1.12.1/jquery-ui.min.css\" />\n");
    head.append("<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/jqGrid/css/ui.jqgrid.css\" />\n");
    head.append("<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/css/buttons.css\" /> \n");
    head.append("<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/js/tools/artDialog4.1.2/skins/blue.css\" />\n");
    head.append("<link rel=\"stylesheet\" href=\"${basePath}static/js/tools/kindeditor/themes/default/default.css\" /> \n");
    head.append("<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/css/editEntity.css\" />\n");
    head.append("</head> \n");
    head.append("<body>\n");
    head.append("\t<div class=\"edit-container\"> \n");
    head.append("\t\t<form action=\"\" id=\"edit" + methodSuffix + "Form\"> \n");
    head.append("\t\t<ul> \n");
    for (Map<String, String> col : addCols) {
      String type = col.get("type");
      String name = col.get("name");
      String caption = col.get("caption");
      switch (type) {
      case "text":
        head.append("\t\t\t<li><strong>" + caption + "：</strong><span><input type=\"text\" name=\"" + name + "\" placeholder=\"" + caption + "\" ></span></li>\n");
        break;
      case "password":
        head.append("\t\t\t<li><strong>" + caption + "：</strong><span><input type=\"password\" name=\"" + name + "\" placeholder=\"" + caption + "\" ></span></li> \n");
        break;
      case "area":
        String viewName = col.get("viewName");
        head.append("\t\t\t<li><strong>" + caption + "：</strong><span><input type=\"text\" name=\"" + viewName + "\" placeholder=\"选择地区\" readonly><input type=\"hidden\" name=\"" + name + "\"></span></li> \n");
        break;
      case "textarea":
        head.append("\t\t\t<li style=\"height:90px;\"><strong>" + caption + "：</strong><span> \n");
        head.append("\t\t\t\t<textarea rows=\"1\" cols=\"1\" style=\"width:800px; height:80px;\" name=\"" + name + "\" placeholder=\"" + caption + "\"></textarea>\n");
        break;
      default:
        break;
      }
    }
    head.append("\t\t\t</span></li> \n");
    head.append("\t\t</ul>\n");
    head.append("\t\t</form>\n");
    head.append("\t\t<div class=\"btnGroup\"> \n");
    head.append("\t\t\t<input type=\"button\" value=\"保存\" class=\"button blue\" id=\"saveBtn\">\n");
    head.append("\t\t\t<input type=\"button\" value=\"关闭\" class=\"button grey\" onclick=\"art.dialog.close();\"> \n");
    head.append("\t\t</div> \n");
    head.append("\t</div> \n");
    head.append("\t<div id=\"uploadDiv\" style=\"display:none\">\n");
    head.append("\t\t<input type=\"file\" name=\"uploadImg\" id=\"uploadImg\">\n");
    head.append("\t</div> \n");
    head.append("\t<input id=\"basePath\" type=\"hidden\" value=\"${basePath}\">\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.source.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/ajaxfileupload.js\"></script>\n");
    head.append("\t<script charset=\"utf-8\" src=\"${basePath}static/js/tools/kindeditor/kindeditor.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/jquery-validation-1.15.0/dist/jquery.validate.min.js\"></script> \n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/jquery-validation-1.15.0/dist/additional-methods-local.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/My97DatePicker/WdatePicker.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/management/utils.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/management/" + moduleName + "/add" + methodSuffix + ".js\"></script> \n");
    head.append("</body>\n");
    head.append("</html>");

    createFile(listFile, head);
    System.out.println("add" + methodSuffix + ".jsp 建立完成.");
  }

  private void buildListJs() {
    File listFile = new File(jsPath + moduleName + "/list.js");
    if (!overwrite && listFile.exists()) {
      System.out.println("list.js 已经存在,不再生成.");
      return;
    }
    File filePath = new File(jsPath + moduleName);
    if (!filePath.exists()) {
      filePath.mkdirs();
    }
    StringBuilder head = new StringBuilder();
    head.append("var basePath = $('#basePath').val();\n");
    head.append("var " + moduleName + "Handle = new ListHandle({ \n");
    head.append("\tbasePath: $('#basePath').val(),\n");
    head.append("\ttableId: '#grid-table',\n");
    head.append("\tpagerId: '#grid-pager',\n");
    head.append("\tformId: '#queryForm',\n");
    head.append("\tentityName: '" + dtoCaption + "信息',\n");
    head.append("\twinWidth: '90%', \n");
    head.append("\twinHeight: '90%',\n");
    head.append("\tprimaryKey: '" + pkIdName + "',\n");
    head.append("\turls:{ \n");
    head.append("\t\tlist: basePath+'" + controllerMappingName + "/list',//列表\n");
    head.append("\t\taddBefore: basePath+'common/management/" + moduleName + "/add" + methodSuffix + "', //添加之前 \n");
    head.append("\t\teditBefore: basePath+'" + controllerMappingName + "/editBefore',//编辑之前\n");
    head.append("\t\tenabled: basePath+'" + controllerMappingName + "/enabled',//启用\n");
    head.append("\t\tdisabled: basePath+'" + controllerMappingName + "/disabled',//禁用\n");
    head.append("\t\tdeleted: basePath+'" + controllerMappingName + "/delete',//删除 \n");
    head.append("\t}\n");
    head.append("},{ \n");
    head.append("\t \n");
    head.append("}); \n");
    head.append("var utilsHandle = new UtilsHandle({ \n");
    head.append("\tbasePath: basePath,\n");
    head.append("\tchooseCity: {\n");
    head.append("\t\t'object': $('input[name=areaName]'), \n");
    head.append("\t\t'width': '70%',\n");
    head.append("\t\t'height': '600px', \n");
    head.append("\t\t'areaId': $(':hidden[name=areaId]').val(), \n");
    head.append("\t\t'callback':function(areaId, areaName){ \n");
    head.append("\t\t\t$(':hidden[name=areaId]').val(areaId); \n");
    head.append("\t\t\t$(':input[name=areaName]').val(areaName);\n");
    head.append("\t\t}\n");
    head.append("\t}, \n");
    head.append("}); \n");
    head.append("$(function(){ \n");
    head.append("\tvar colNames = [");
    String temp = "";
    for (Map<String, String> col : cols) {
      String caption = col.get("caption");
      temp += "'" + caption + "', ";
    }
    head.append(temp + "'操作'];\n");
    head.append("\tvar colModel = [ \n");
    for (Map<String, String> col : cols) {
      String name = col.get("name");
      String width = col.get("width");
      String type = col.get("type");
      switch (type) {
      case "text":
        head.append("\t\t{name: '" + name + "', index: '" + name + "', width: " + width + ", align: 'center'}, \n");
        break;
      case "select":
        String options = col.get("options");
        head.append("\t\t{name: '" + name + "', index: '" + name + "', width: " + width + ", align: 'center', formatter: 'select', editoptions:" + options + "}, \n");
        break;
      default:
        break;
      }
    }
    head.append("\t\t{width: 50, align: 'left', editable: false, sortable: false, formatter: function(cellvalue, options, rowObject){ \n");
    head.append("\t\t\tvar temp = ''; \n");
    head.append("\t\t\ttemp += '<a class=\"linetaga\" href=\"javascript: " + moduleName + "Handle.edit(\\'' + rowObject." + pkIdName + " + '\\');\" >编辑</a>'; \n");
    head.append("\t\t\tif(rowObject.enabled==1){\n");
    head.append("\t\t\t\ttemp += '<a class=\"linetaga\" href=\"javascript: " + moduleName + "Handle.disabled(\\'' + rowObject." + pkIdName + " + '\\');\" >禁用</a>'; \n");
    head.append("\t\t\t}else{ \n");
    head.append("\t\t\t\ttemp += '<a class=\"linetaga\" href=\"javascript: " + moduleName + "Handle.enabled(\\'' + rowObject." + pkIdName + " + '\\');\" >启用</a>';\n");
    head.append("\t\t\t}\n");
    head.append("\t\t\treturn '<span class=\"listBtnsSpan\">'+temp+'</span>'; \n");
    head.append("\t\t}} \n");
    head.append("\t]; \n");
    head.append("\tvar rowList = [10, 20, 30, 50];\n");
    head.append("\tvar rownumbers = true; \n");
    head.append("\tvar multiselect = true;\n");
    head.append("\tvar config={caption: '" + dtoCaption + "列表', colNames: colNames, colModel: colModel, rowList: rowList, rownumbers: rownumbers, multiselect: multiselect};\n");
    head.append("\t" + moduleName + "Handle.init(config); \n");
    head.append("}); \n");

    createFile(listFile, head);
    System.out.println("list.js 建立完成.");
  }

  private void buildList() {
    File listFile = new File(basePath + moduleName + "/list.jsp");
    if (!overwrite && listFile.exists()) {
      System.out.println("list.jsp 已经存在,不再生成.");
      return;
    }
    File filePath = new File(basePath + moduleName);
    if (!filePath.exists()) {
      filePath.mkdirs();
    }
    StringBuilder head = new StringBuilder();
    head.append("<%@ page language=\"java\" pageEncoding=\"UTF-8\"%><%@ taglib uri =\"http://www.springframework.org/security/tags\" prefix =\"sec\"%>\n");
    head.append("<!DOCTYPE html>\n");
    head.append("<html> \n");
    head.append("<head> \n");
    head.append("\t<title>列表</title>\n");
    head.append("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" /> \n");
    head.append("\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/jquery-ui-1.12.1/jquery-ui.min.css\" /> \n");
    head.append("\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/jqGrid/css/ui.jqgrid.css\" /> \n");
    head.append("\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/css/buttons.css\" />\n");
    head.append("\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/js/tools/artDialog4.1.2/skins/blue.css\" /> \n");
    head.append("\t<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"${basePath}static/css/pageList.css?1\" /> \n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/jquery-ui-1.12.1/external/jquery/jquery.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/jquery-ui-1.12.1/jquery-ui.min.js\"></script> \n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/jqGrid/js/jquery.jqGrid.min.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/jqGrid/js/i18n/grid.locale-cn.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/artDialog4.1.2/js/jquery.artDialog.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/tools/artDialog4.1.2/js/iframeTools.js\"></script> \n");
    head.append("</head>\n");
    head.append("<body> \n");
    head.append("\t<div class=\"main-container\" id=\"main-container\"> \n");
    head.append("\t\t<div class=\"search-container\"> \n");
    head.append("\t\t\t<form action=\"${basePath}\" id=\"queryForm\"> \n");
    for (Map<String, String> col : queryCols) {
      String type = col.get("type");
      String caption = col.get("caption");
      String name = col.get("name");
      switch (type) {
      case "text":
        head.append("\t\t\t\t<span>" + caption + "：<input type=\"text\" name=\"" + name + "\" placeholder=\"输入" + caption + "\" > </span> \n");
        break;
      case "area":
        head.append("\t\t\t\t<span>" + caption + "：<input type=\"text\" name=\"" + name + "\" placeholder=\"选择" + caption + "\" readonly> </span> \n");
        break;
      case "select":
        String optionstr = col.get("options");
        String[] options = optionstr.split(";");
        head.append("\t\t\t\t<span>" + caption + "：<select name=\"" + name + "\"><option value=\"\">全部</option>");
        for (String option : options) {
          String[] opt = option.split(":");
          String value = opt[0];
          String text = opt[1];
          head.append("<option value=\"" + value + "\" >" + text + "</option>");
        }
        head.append("</select> </span> \n");
        break;
      default:
        break;
      }
    }
    head.append("\t\t\t\t<a onclick=\"" + moduleName + "Handle.query()\" class=\"button blue\">查询</a> \n");
    head.append("\t\t\t\t<a onclick=\"" + moduleName + "Handle.reset()\" class=\"button grey\">清空</a> \n");
    head.append("\t\t\t</form>\n");
    head.append("\t\t</div> \n");
    head.append("\t\t<div class=\"data-container\"> \n");
    head.append("\t\t\t\t<div class=\"btnGroup\"> \n");
    head.append("\t\t\t\t<a onclick=\"" + moduleName + "Handle.addNew()\" class=\"button grey\">新增</a>\n");
    head.append("\t\t\t\t<a onclick=\"" + moduleName + "Handle.enabled()\" class=\"button blue\">启用</a> \n");
    head.append("\t\t\t\t<a onclick=\"" + moduleName + "Handle.disabled()\" class=\"button yellow\">禁用</a>\n");
    head.append("\t\t\t\t<a onclick=\"" + moduleName + "Handle.remove()\" class=\"button yellow\">删除</a>\n");
    head.append("\t\t\t\t</div> \n");
    head.append("\t\t\t<table id=\"grid-table\"></table>\n");
    head.append("\t\t\t<div id=\"grid-pager\"></div>\n");
    head.append("\t\t</div> \n");
    head.append("\t</div> \n");
    head.append("\t<input id=\"basePath\" type=\"hidden\" value=\"${basePath}\">\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/management/common.js\"></script> \n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/management/utils.js\"></script>\n");
    head.append("\t<script type=\"text/javascript\" src=\"${basePath}static/js/management/" + moduleName + "/list.js\"></script>\n");
    head.append("</body>\n");
    head.append("</html>");

    createFile(listFile, head);

    System.out.print("list.jsp 建立完成.");
    System.out.println("\t" + listFile.getAbsolutePath());
  }

  private String firstToLower(String str) {
    String temp = str.substring(0, 1).toLowerCase() + str.substring(1);
    return temp;
  }

  private boolean createFile(File file, StringBuilder content) {
    try {
      if (!overwrite && file.exists()) {
        return true;
      }
      FileOutputStream fos = new FileOutputStream(file);
      OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
      BufferedWriter bw = new BufferedWriter(osw);
      bw.write(content.toString());
      bw.flush();
      bw.close();
      osw.close();
      fos.close();
      return true;
    } catch (Exception e) {
    }
    return false;
  }
}
