/**create by liuhua at 2017年12月21日 下午2:37:13**/
package com.booting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateControllerFile {
	
	public static void main(String[] args) throws IOException {
		String moduleName = "product";
		String controllerName = "ActivityController";
		String webServiceName = "ActivityWebService";
		String dtoName = "ActivityDTO";
		String pkIdName = "activityId";
		String jspPath = "management/activity/";
		CreateControllerFile ccf = new CreateControllerFile();
		ccf.create(moduleName, controllerName, webServiceName, dtoName, pkIdName, jspPath);
	}
	
	private String basePath, baseClassPath, moduleName, controllerName, webServiceName, dtoName, pkIdName, jspPath;
	
	public CreateControllerFile(){
		String fileName = getClass().getSimpleName() + ".class";
		URL url = getClass().getResource(fileName);
		basePath = url.getPath().replace("target/classes", "src/main/java").replace(fileName, "") + "management/controller/";
		baseClassPath = "com.booting.management.controller.";
	}
	
	private String getInputPath(String title) throws IOException {
		System.out.print(title);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		return str;
	}
	
	public void create() throws IOException{
		moduleName = getInputPath("输入模块名称(例如userinfo):");
		if (null == moduleName || "".equals(moduleName)) {
			throw new IOException("请输入模块名称)");
		}
		controllerName = getInputPath("输入Controller名称(Controller的文件名，例如：LoginController):");
		if (null == controllerName || "".equals(controllerName)) {
			throw new IOException("请输入Controller名称)");
		}
		webServiceName = getInputPath("输入webServiceName名称(webServiceName的文件名，例如：SystemWebService):");
		if (null == webServiceName || "".equals(webServiceName)) {
			throw new IOException("请输入webServiceName名称)");
		}
		dtoName = getInputPath("输入dtoName名称(dtoName的文件名，例如：UserDTO):");
		if (null == dtoName || "".equals(dtoName)) {
			throw new IOException("请输入dtoName名称)");
		}
		pkIdName = getInputPath("输入pkIdName名称(pkIdName的文件名，例如：userId):");
		if (null == pkIdName || "".equals(pkIdName)) {
			throw new IOException("请输入pkIdName名称)");
		}
		jspPath = getInputPath("输入jspPath名称(jspPath的文件名，例如：management/user/):");
		if (null == jspPath || "".equals(jspPath)) {
			throw new IOException("请输入jspPath名称)");
		}
		System.out.println(basePath);
		System.out.println(baseClassPath);
		buildController();
	}
	
	public void create(String moduleName, String controllerName, String serviceName, String dtoName, String pkIdName, String jspPath) throws IOException{
		this.moduleName = moduleName;
		this.controllerName = controllerName;
		this.webServiceName = serviceName;
		this.dtoName = dtoName;
		this.pkIdName = pkIdName;
		this.jspPath = jspPath;
		buildController();
	}
	
	private void buildController() {
		File controllerFile = new File(basePath + moduleName + "/" + controllerName + ".java");
		if(controllerFile.exists()){
			System.out.println(controllerName + ".java 已经存在,不再生成.");
			return;
		}
		String baseMapping = firstToLower(controllerName.replace("Controller", ""));
		String methodSuffix = dtoName.replace("DTO", "");
		String dtoVariable = firstToLower(dtoName);
		String serviceVariable = firstToLower(webServiceName);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder head = new StringBuilder();
		
		head.append("/** create by auto at " + sdf.format(new Date()) + "**/\n");
		head.append(this.getClass().getPackage() + ".management.controller." + moduleName + ";\n\n");
		head.append("import java.util.HashMap;\n");
		head.append("import java.util.Map;\n\n");
		head.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		head.append("import org.springframework.http.MediaType;\n");
		head.append("import org.springframework.stereotype.Controller;\n");
		head.append("import org.springframework.ui.Model;\n");
		head.append("import org.springframework.web.bind.annotation.PathVariable;\n");
		head.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
		head.append("import org.springframework.web.bind.annotation.RequestBody;\n");
		head.append("import org.springframework.web.bind.annotation.RequestMethod;\n");
		head.append("import org.springframework.web.bind.annotation.RequestParam;\n");
		head.append("import org.springframework.web.bind.annotation.ResponseBody;\n\n");
		head.append("import com.star.framework.jdbc.dao.result.PageList;\n");
		head.append("import com.star.framework.jdbc.dao.result.QueryParam;\n");
		head.append("import com.star.framework.specification.FailureCode;\n");
		head.append("import com.star.framework.specification.exception.ArgsException;\n");
		head.append("import com.star.framework.specification.result.ResultMessage;\n\n");
		head.append("@Controller\n@RequestMapping(\"/" + baseMapping + "\")\n");
		head.append("public class " + controllerName + "{\n\n");
		head.append("\t@Autowired\n");
		head.append("\tprivate " + webServiceName + " " + serviceVariable + ";\n\n");
		
		head.append("\t@ResponseBody\n");
		head.append("\t@RequestMapping(value = \"/list\", method = RequestMethod.POST)\n");
		head.append("\tpublic Map<String, Object> list(" + dtoName + " " + dtoVariable + ", Integer page, Integer rows, String sord, String sidx){\n");
		head.append("\t\t" + dtoVariable + ".setDeleted(0);\n");
		head.append("\t\tQueryParam queryParam = new QueryParam();\n");
		head.append("\t\tqueryParam.setOrderBy(sidx);\n");
		head.append("\t\tqueryParam.setOrderType(sord);\n");
		head.append("\t\tqueryParam.setPageNo(page);\n");
		head.append("\t\tqueryParam.setPageSize(rows);\n");
		head.append("\t\tqueryParam.setParam(" + dtoVariable + ");\n");
		head.append("\t\tPageList<" + dtoName + "> pageList = " + serviceVariable + ".getListForPage" + methodSuffix + "(queryParam, " + dtoName + ".class);\n");
		head.append("\t\tMap<String, Object> map = new HashMap<>();\n");
		head.append("\t\tmap.put(\"page\", pageList.getPageNo());\n");
		head.append("\t\tmap.put(\"total\", pageList.getTotalPage());\n");
		head.append("\t\tmap.put(\"records\", pageList.getTotalRecord());\n");
		head.append("\t\tmap.put(\"rows\", pageList.getDataList());\n");
		head.append("\t\treturn map;\n");
		head.append("\t}\n\n");
		
		head.append("\t@RequestMapping(value = \"/editBefore/{" + pkIdName + "}\", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)\n");
		head.append("\tpublic String editBefore(@PathVariable Long " + pkIdName + ", Model model){\n");
		head.append("\t\t" + dtoName + " " + dtoVariable + " = this." + serviceVariable + ".get" + methodSuffix + "(" + pkIdName + ");\n");
		head.append("\t\tmodel.addAttribute(\"" + dtoVariable + "\", " + dtoVariable + ");\n");
		head.append("\t\treturn \"" + jspPath + "edit" + methodSuffix + "\";\n");
		head.append("\t}\n\n");
		
		head.append("\t@ResponseBody\n");
		head.append("\t@RequestMapping(value = \"/edit\", method = RequestMethod.POST, produces = \"text/html;charset=UTF-8\")\n");
		head.append("\tpublic ResultMessage edit(@RequestBody " + dtoName + " " + dtoVariable + "){\n");
		head.append("\t\tResultMessage resultMessage = null;\n");
		head.append("\t\ttry {\n");
		head.append("\t\t\tthis." + serviceVariable + ".update" + methodSuffix + "(" + dtoVariable + ");\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"ok\", \"编辑\");\n");
		head.append("\t\t} catch (ArgsException e) {\n");
		head.append("\t\t\te.printStackTrace();\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"编辑\", e.getCode(), e.getMessage());\n");
		head.append("\t\t} catch (Exception e) {\n");
		head.append("\t\t\te.printStackTrace();\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"编辑\", FailureCode.ERR_001);\n");
		head.append("\t\t}\n");
		head.append("\t\treturn resultMessage;\n");
		head.append("\t}\n\n");
		
		head.append("\t@ResponseBody\n");
		head.append("\t@RequestMapping(value = \"/add\", method = RequestMethod.POST, produces = \"text/html;charset=UTF-8\")\n");
		head.append("\tpublic ResultMessage add(@RequestBody " + dtoName + " " + dtoVariable + "){\n");
		head.append("\t\tResultMessage resultMessage = null;\n");
		head.append("\t\ttry {\n");
		head.append("\t\t\tthis." + serviceVariable + ".save" + methodSuffix + "(" + dtoVariable + ");\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"ok\", \"添加\");\n");
		head.append("\t\t} catch (ArgsException e) {\n");
		head.append("\t\t\te.printStackTrace();\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"添加\", e.getCode(), e.getMessage());\n");
		head.append("\t\t} catch (Exception e) {\n");
		head.append("\t\t\te.printStackTrace();\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"添加\", FailureCode.ERR_001);\n");
		head.append("\t\t}\n");
		head.append("\t\treturn resultMessage;\n");
		head.append("\t}\n\n");
		
		head.append("\t@ResponseBody\n");
		head.append("\t@RequestMapping(value = \"/enabled\", method = RequestMethod.POST, produces = \"text/html;charset=UTF-8\")\n");
		head.append("\tpublic ResultMessage enabled(@RequestParam String ids){\n");
		head.append("\t\tResultMessage resultMessage = null;\n");
		head.append("\t\ttry {\n");
		head.append("\t\t\tthis." + serviceVariable + ".enabled" + methodSuffix + "(ids);\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"ok\", \"启用\");\n");
		head.append("\t\t} catch (ArgsException e) {\n");
		head.append("\t\t\te.printStackTrace();\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"启用\", e.getCode(), e.getMessage());\n");
		head.append("\t\t} catch (Exception e) {\n");
		head.append("\t\t\te.printStackTrace();\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"启用\", FailureCode.ERR_001);\n");
		head.append("\t\t}\n");
		head.append("\t\treturn resultMessage;\n");
		head.append("\t}\n\n");
		
		head.append("\t@ResponseBody\n");
		head.append("\t@RequestMapping(value = \"/disabled\", method = RequestMethod.POST, produces = \"text/html;charset=UTF-8\")\n");
		head.append("\tpublic ResultMessage disabled(@RequestParam String ids){\n");
		head.append("\t\tResultMessage resultMessage = null;\n");
		head.append("\t\ttry {\n");
		head.append("\t\t\tthis." + serviceVariable + ".disabled" + methodSuffix + "(ids);\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"ok\", \"禁用\");\n");
		head.append("\t\t} catch (ArgsException e) {\n");
		head.append("\t\t\te.printStackTrace();\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"禁用\", e.getCode(), e.getMessage());\n");
		head.append("\t\t} catch (Exception e) {\n");
		head.append("\t\t\te.printStackTrace();\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"禁用\", FailureCode.ERR_001);\n");
		head.append("\t\t}\n");
		head.append("\t\treturn resultMessage;\n");
		head.append("\t}\n\n");

		head.append("\t@ResponseBody\n");
		head.append("\t@RequestMapping(value = \"/delete\", method = RequestMethod.POST, produces = \"text/html;charset=UTF-8\")\n");
		head.append("\tpublic ResultMessage delete(@RequestParam String ids){\n");
		head.append("\t\tResultMessage resultMessage = null;\n");
		head.append("\t\ttry {\n");
		head.append("\t\t\tthis." + serviceVariable + ".delete" + methodSuffix + "(ids);\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"ok\", \"删除\");\n");
		head.append("\t\t} catch (ArgsException e) {\n");
		head.append("\t\t\te.printStackTrace();\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"删除\", e.getCode(), e.getMessage());\n");
		head.append("\t\t} catch (Exception e) {\n");
		head.append("\t\t\te.printStackTrace();\n");
		head.append("\t\t\tresultMessage = new ResultMessage(\"删除\", FailureCode.ERR_001);\n");
		head.append("\t\t}\n");
		head.append("\t\treturn resultMessage;\n");
		head.append("\t}\n\n");
		
		head.append("}");
		
		createFile(controllerFile, head);
		
		System.out.println(controllerName + ".java 建立完成.");
	}

	private String firstToLower(String str){
		String temp = str.substring(0, 1).toLowerCase() + str.substring(1);
		return temp;
	}
	
	private boolean createFile(File file, StringBuilder content){
		try {
			if (file.exists()) {
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
