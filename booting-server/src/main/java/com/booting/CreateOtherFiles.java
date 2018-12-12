/**create by liuhua at 2017年2月9日 下午1:55:46**/
package com.booting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import com.star.framework.aop.annotation.Description;

@SuppressWarnings({"serial"})
public class CreateOtherFiles {
	
	private final String dsNameRead = "test-read";
	private final String dsNameWrite = "test-write";
	//mysql 关键字
	private static List<String> keywords = new ArrayList<String>(){{
		add("status");
		add("name");
		add("password");
	}};
	
	private String basePath;
	private String entityPath;
	private String dtoPath;
	private String servicePath;
	private String serviceImplPath;
	private String moduleName;
	private String baseClassPath;
	private String entityClassPath;
	private String dtoClassPath;
	private String serviceClassPath;
	private String sqlMapPath;
	
	public CreateOtherFiles() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String fileName = getClass().getSimpleName() + ".class";
		URL url = getClass().getResource(fileName);
		basePath = url.getPath().replace("target/classes", "src/main/java").replace(fileName, "");
		baseClassPath = "com.booting.";
	}
	
	public CreateOtherFiles(String moduleName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.moduleName = moduleName;
		String fileName = getClass().getSimpleName() + ".class";
		URL url = getClass().getResource(fileName);
		basePath = url.getPath().replace("target/classes", "src/main/java").replace(fileName, "");
		baseClassPath = "com.booting.";
	}
	
	private String getInputPath(String title) throws IOException {
		System.out.print(title);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		return str;
	}
	
	public void create() throws Exception{
		if (null == moduleName) {
			moduleName = getInputPath("输入模块名称(例如system):");
		}
		if (null == moduleName || "".equals(moduleName)) {
			throw new IOException("请输入模块名称)");
		}
		entityPath = basePath + moduleName + "/" + "entity/";
		dtoPath = basePath + moduleName + "/" + "dto/";
		servicePath = basePath + moduleName + "/" + "service/";
		serviceImplPath = servicePath + "/impl/";
		entityClassPath = baseClassPath + moduleName + "." + "entity.";
		dtoClassPath = baseClassPath + moduleName + "." + "dto.";
		serviceClassPath = baseClassPath + moduleName + "." + "service";
//		serviceImplClassPath = baseClassPath + moduleName + "." + "service";
		int idx = serviceImplPath.indexOf("src/main/java");
		sqlMapPath = serviceImplPath.substring(0, idx) + "src/main/resources/sqlMap/" + moduleName + "/";
		
		File filePath = new File(serviceImplPath);
		if (! filePath.exists()) {
			filePath.mkdirs();
		}
		filePath = new File(servicePath);
		if (! filePath.exists()) {
			filePath.mkdirs();
		}
		filePath = new File(dtoPath);
		if (! filePath.exists()) {
			filePath.mkdirs();
		}
		filePath = new File(sqlMapPath);
		if (! filePath.exists()) {
			filePath.mkdirs();
		}
		
		String[] entityClassPaths = null;
		File file = new File(entityPath);
		if (file.exists()) {
			File files[] = file.listFiles(new FilenameFilter(){
				public boolean accept(File dir, String name) {
					if (name.endsWith(".java")) {
						return true;
					}
					return false;
				}
			});
			entityClassPaths = new String[files.length];
			for (int i = 0; i < files.length; i++) {
				entityClassPaths[i] = entityClassPath + files[i].getName().replace(".java", "");
			}
		}else{
			throw new Exception("模块下必须有Entity类");
		}
		CreateDTO cf = new CreateDTO(moduleName);
		cf.create();
		for (String classPath : entityClassPaths) {
			try {
				Object object = Class.forName(classPath).newInstance();
				createService(object);
				createServiceImpl(object);
				createSQLMap(object);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void createSQLMap(Object bean) throws IOException{
		String entityName = bean.getClass().getSimpleName();
		String tabName = entityName.replace("Entity", "");
		String namespace = firstCharToLower(tabName);
		String tableName = getTableName(bean);
		String fid = getEntityId(bean, true);
		String pid = getEntityId(bean, false);
		String fileName = "sqlMap_" + tabName + ".xml";
		File sqlFile = new File(sqlMapPath + fileName);
		if(sqlFile.exists()){
			System.out.println(fileName + " 已经存在,不再生成.");
			return;
		}
		StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		sb.append("<sqlMap namespace=\"" + namespace + "\" isRead=\"true\" dsName=\"" + dsNameRead + "\">\n\n");
		
		sb.append("\t<sql id=\"insert" + tabName + "\" isRead=\"false\" dsName=\"" + dsNameWrite + "\">\n");
		sb.append("\t\t<![CDATA[\n");
		sb.append("\t\t\tINSERT INTO " + tableName + "(" + getFieldsOrProperties(bean, true, false) + ") \n\t\t\tvalues (" + getFieldsOrProperties(bean, false, false)  + ")\n");
		sb.append("\t\t]]>\n");
		sb.append("\t</sql>\n\n");
		
		sb.append("\t<sql id=\"update" + tabName + "\" isRead=\"false\" dsName=\"" + dsNameWrite + "\">\n");
		sb.append("\t\t<![CDATA[\n");
		sb.append("\t\t\tUPDATE " + tableName + " SET " + getKeyValue(bean, ", ") + "\t\t\tWHERE " + fid + " = :" + pid + "\n");
		sb.append("\t\t]]>\n");
		sb.append("\t</sql>\n\n");
		
		sb.append("\t<sql id=\"delete" + tabName + "\" isRead=\"false\" dsName=\"" + dsNameWrite + "\">\n");
		sb.append("\t\t<![CDATA[\n");
		sb.append("\t\t\tDELETE FROM " + tableName + "\n\t\t\tWHERE 1=1\n");
		sb.append("\t\t\t" + getKeyValue(bean, "AND "));
		sb.append("\t\t\t<#if " + pid + "?exists && " + pid + " != \"\">\n");
		sb.append("\t\t\t\tAND " + fid + " = :" + pid + "\n");
		sb.append("\t\t\t</#if>\n");
		sb.append("\t\t]]>\n");
		sb.append("\t</sql>\n\n");
		
		sb.append("\t<sql id=\"getSimple" + tabName + "List\">\n");
		sb.append("\t\t<![CDATA[\n");
		sb.append("\t\t\tSELECT \n\t\t\t\t" + getFieldsOrProperties(bean, true, true) + "\n\t\t\tFROM " + tableName + "\n\t\t\tWHERE 1=1\n");
		sb.append("\t\t\t" + getKeyValue(bean, "AND "));
		sb.append("\t\t\t<#if " + pid + "?exists && " + pid + " != \"\">\n");
		sb.append("\t\t\t\tAND " + fid + " = :" + pid + "\n");
		sb.append("\t\t\t</#if>\n");
		sb.append("\t\t\t<#if orderBy?exists && orderBy !=\"\" >\n");
		sb.append("\t\t\t\tORDER BY :orderBy\n");
		sb.append("\t\t\t\t<#if orderType?exists && orderType !=\"\" >\n");
		sb.append("\t\t\t\t\t:orderType\n");
		sb.append("\t\t\t\t</#if>\n");
		sb.append("\t\t\t</#if>\n");
		sb.append("\t\t\t<#if startIndex?exists && startIndex !=\"\" && maxCount?exists && maxCount !=\"\" >\n");
		sb.append("\t\t\t\tLIMIT :startIndex,:maxCount\n");
		sb.append("\t\t\t</#if>\n");
		sb.append("\t\t]]>\n");
		sb.append("\t</sql>\n\n");
		
		sb.append("\t<sql id=\"getSimple" + tabName + "ListCount\">\n");
		sb.append("\t\t<![CDATA[\n");
		sb.append("\t\t\tSELECT COUNT(1) FROM " + tableName + "\n\t\t\tWHERE 1=1\n");
		sb.append("\t\t\t" + getKeyValue(bean, "AND "));
		sb.append("\t\t\t<#if " + pid + "?exists && " + pid + " != \"\">\n");
		sb.append("\t\t\t\tAND " + fid + " = :" + pid + "\n");
		sb.append("\t\t\t</#if>\n");
		sb.append("\t\t\t<#if orderBy?exists && orderBy !=\"\" >\n");
		sb.append("\t\t\t\tORDER BY :orderBy\n");
		sb.append("\t\t\t\t<#if orderType?exists && orderType !=\"\" >\n");
		sb.append("\t\t\t\t\t:orderType\n");
		sb.append("\t\t\t\t</#if>\n");
		sb.append("\t\t\t</#if>\n");
		sb.append("\t\t]]>\n");
		sb.append("\t</sql>\n\n");

		sb.append("\t<sql id=\"get" + tabName + "List\">\n");
		sb.append("\t\t<![CDATA[\n");
		sb.append("\t\t\tSELECT \n\t\t\t\t" + getFieldsOrProperties(bean, true, true) + "\n\t\t\tFROM " + tableName + "\n\t\t\tWHERE 1=1\n");
		sb.append("\t\t\t" + getKeyValue(bean, "AND "));
		sb.append("\t\t\t<#if " + pid + "?exists && " + pid + " != \"\">\n");
		sb.append("\t\t\t\tAND " + fid + " = :" + pid + "\n");
		sb.append("\t\t\t</#if>\n");
		sb.append("\t\t\t<#if orderBy?exists && orderBy!=\"\" && orderType?exists>\n");
		sb.append("\t\t\t\tORDER BY :orderBy :orderType\n");
		sb.append("\t\t\t</#if>\n");
		sb.append("\t\t\t<#if startIndex?exists && startIndex !=\"\" && maxCount?exists && maxCount !=\"\" >\n");
		sb.append("\t\t\t\tLIMIT :startIndex,:maxCount\n");
		sb.append("\t\t\t</#if>\n");
		sb.append("\t\t]]>\n");
		sb.append("\t</sql>\n\n");

		sb.append("\t<sql id=\"get" + tabName + "ListCount\">\n");
		sb.append("\t\t<![CDATA[\n");
		sb.append("\t\t\tSELECT COUNT(1) FROM " + tableName + "\n\t\t\tWHERE 1=1\n");
		sb.append("\t\t\t" + getKeyValue(bean, "AND "));
		sb.append("\t\t\t<#if " + pid + "?exists && " + pid + " != \"\">\n");
		sb.append("\t\t\t\tAND " + fid + " = :" + pid + "\n");
		sb.append("\t\t\t</#if>\n");
		sb.append("\t\t\t<#if orderBy?exists && orderBy!=\"\" && orderType?exists>\n");
		sb.append("\t\t\t\tORDER BY :orderBy :orderType\n");
		sb.append("\t\t\t</#if>\n");
		sb.append("\t\t]]>\n");
		sb.append("\t</sql>\n\n");
		
		sb.append("</sqlMap>");
		
		createFile(sqlFile, sb);
		System.out.println(fileName + " 建立完成.");
	}

	private void createService(Object bean) throws IOException{
		String entityName = bean.getClass().getSimpleName();
		String dtoName = entityName.replace("Entity", "DTO");
		String serviceName = entityName.replace("Entity", "Service");
		String annotation = getDescription(bean, "服务");
		File serviceFile = new File(servicePath + serviceName + ".java");
		if(serviceFile.exists()){
			System.out.println(serviceName + ".java 已经存在,不再生成.");
			return;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("/** create by auto at " + sdf.format(new Date()) + "**/\n");
		sb.append(this.getClass().getPackage() + "." + moduleName + ".service;\n\n");
		sb.append("import com.star.framework.jdbc.service.BaseService;\n");
		sb.append("import " + dtoClassPath + dtoName + ";\n");
		sb.append("import " + entityClassPath + entityName + ";\n\n");
		
		sb.append("/**\n");
		sb.append(" * " + annotation + "\n");
		sb.append(" *\n");
		sb.append(" * @author auto\n");
		sb.append(" *\n");
		sb.append(" */\n");
		sb.append("public interface " + serviceName + " extends BaseService<" + entityName + ", " + dtoName + "> {\n\n");
		sb.append("}");
		
		createFile(serviceFile, sb);
		System.out.println(serviceName + ".java 建立完成.");
	}

	private String getDescription(Object bean, String content) {
		Description description = bean.getClass().getAnnotation(Description.class);
		if (null != description) {
			return description.name() + content;
		}
		return "";
	}
	
	private void createServiceImpl(Object bean) throws IOException{
		String entityName = bean.getClass().getSimpleName();
		String dtoName = entityName.replace("Entity", "DTO");
		String serviceName = entityName.replace("Entity", "Service");
		String serviceImplName = entityName.replace("Entity", "ServiceImpl");
		String tabName = entityName.replace("Entity", "");
		String entityId = getEntityId(bean, false);
		File serviceFile = new File(serviceImplPath + serviceImplName + ".java");
		if(serviceFile.exists()){
			System.out.println(serviceImplName + ".java 已经存在,不再生成.");
			return;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("/** create by auto at " + sdf.format(new Date()) + "**/\n");
		sb.append(this.getClass().getPackage() + "." + moduleName + ".service.impl;\n\n");
		sb.append("import java.util.List;\n");
		sb.append("import java.util.Map;\n\n");
		sb.append("import org.springframework.stereotype.Service;\n\n");
		sb.append("import " + dtoClassPath + dtoName + ";\n");
		sb.append("import " + entityClassPath + entityName + ";\n");
		sb.append("import " + serviceClassPath + "." + serviceName + ";\n\n");
		sb.append("import com.star.framework.jdbc.dao.JDBCSupport;\n\n");
		sb.append("import com.star.framework.jdbc.dao.result.PageList;\n\n");
		sb.append("import com.star.framework.jdbc.dao.result.QueryParam;\n\n");
		sb.append("@Service(\"" + firstCharToLower(serviceName) + "\")\n");
		
		sb.append("public class " + serviceImplName + " extends JDBCSupport<" + entityName + ", " + dtoName + "> implements " + serviceName + "{\n\n");
		sb.append("\tprivate static final long serialVersionUID = 1L;\n\n");
		
		sb.append("\t@Override\n");
		sb.append("\tpublic " + dtoName + " save(" + entityName + " " + firstCharToLower(entityName) + ") {\n");
		sb.append("\t\tlong id = this.persist(" + firstCharToLower(entityName) + ");\n");
		sb.append("\t\treturn get(id);\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@Override\n");
		sb.append("\tpublic " + dtoName + " update(" + entityName + " " + firstCharToLower(entityName) + ") {\n");
		sb.append("\t\tthis.dynamicMerge(" + firstCharToLower(entityName) + ");\n");
		sb.append("\t\treturn get(" + firstCharToLower(entityName) + ".get" + firstCharToUpper(entityId) + "());\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@Override\n");
		sb.append("\tpublic " + dtoName + " updateAll(" + entityName + " " + firstCharToLower(entityName) + ") {\n");
		sb.append("\t\tthis.merge(" + firstCharToLower(entityName) + ");\n");
		sb.append("\t\treturn get(" + firstCharToLower(entityName) + ".get" + firstCharToUpper(entityId) + "());\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@Override\n");
		sb.append("\tpublic " + dtoName + " updateBySql(" + dtoName + " " + firstCharToLower(dtoName) + ") {\n");
		sb.append("\t\tif(null == " + firstCharToLower(dtoName) + ") return null;\n");
		sb.append("\t\tthis.execute(\"" + firstCharToLower(tabName) + ".update" + tabName + "\", toMap(" + firstCharToLower(dtoName) + ", \"yyyy-MM-dd HH:mm:ss\"));\n");
		sb.append("\t\tif(null == " + firstCharToLower(dtoName) + ".get" + firstCharToUpper(entityId) + "()) return null;\n");
		sb.append("\t\treturn get(" + firstCharToLower(dtoName) + ".get" + firstCharToUpper(entityId) + "());\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@Override\n");
		sb.append("\tpublic int delete(long id) {\n");
		sb.append("\t\treturn this.del(id);\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@Override\n");
		sb.append("\tpublic void batchSave(List<" + entityName + "> entities) {\n");
		sb.append("\t\tif(null == entities || entities.isEmpty()){\n");
		sb.append("\t\t\treturn;\n");
		sb.append("\t\t}\n");
		sb.append("\t\tMap<String, Object>[] params = toMap(entities);\n");
		sb.append("\t\tthis.batch(\"" + firstCharToLower(tabName) + ".insert" + tabName + "\", params);\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@Override\n");
		sb.append("\tpublic void batchUpdate(List<" + dtoName + "> dtos) {\n");
		sb.append("\t\tif(null == dtos || dtos.isEmpty()){\n");
		sb.append("\t\t\treturn;\n");
		sb.append("\t\t}\n");
		sb.append("\t\tMap<String, Object>[] params = toMap(dtos);\n");
		sb.append("\t\tthis.batch(\"" + firstCharToLower(tabName) + ".update" + tabName + "\", params);\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@Override\n");
		sb.append("\tpublic " + dtoName + " get(long " + entityId + ") {\n");
		sb.append("\t\treturn getById(" + entityId + ");\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@Override\n");
		sb.append("\tpublic " + dtoName + " get(" + dtoName + " " + firstCharToLower(dtoName) + ") {\n");
		sb.append("\t\tif(null == " + firstCharToLower(dtoName) + ") {\n");
		sb.append("\t\t\treturn null;\n");
		sb.append("\t\t}\n");
		sb.append("\t\tMap<String, Object> param = toMap(" + firstCharToLower(dtoName) + ", \"yyyy-MM-dd HH:mm:ss\");\n");
		sb.append("\t\treturn this.queryForObject(\"" + firstCharToLower(tabName) + ".getSimple" + tabName + "List\", param);\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@Override\n");
		sb.append("\tpublic List<" + dtoName + "> getSimpleList(" + dtoName + " " + firstCharToLower(dtoName) + ") {\n");
		sb.append("\t\tMap<String, Object> param = toMap(" + firstCharToLower(dtoName) + ", \"yyyy-MM-dd HH:mm:ss\");\n");
		sb.append("\t\treturn this.queryForList(\"" + firstCharToLower(tabName) + ".getSimple" + tabName + "List\", param);\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@Override\n");
		sb.append("\tpublic PageList<" + dtoName + "> getSimpleListForPage(" + dtoName + " " + firstCharToLower(dtoName) + ", int pageNo, int pageSize) {\n");
		sb.append("\t\tQueryParam queryParam = new QueryParam();\n");
		sb.append("\t\tqueryParam.setPageNo(pageNo);\n");
		sb.append("\t\tqueryParam.setPageSize(pageSize);\n");
		sb.append("\t\tqueryParam.setParam(" + firstCharToLower(dtoName) + ");\n");
		sb.append("\t\treturn this.queryForPage(\"" + firstCharToLower(tabName) + ".getSimple" + tabName + "ListCount\", \"" + firstCharToLower(tabName) + ".getSimple" + tabName + "List\", queryParam);\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@Override\n");
		sb.append("\tpublic PageList<" + dtoName + "> getSimpleListForPage(QueryParam queryParam) {\n");
		sb.append("\t\treturn this.queryForPage(\"" + firstCharToLower(tabName) + ".getSimple" + tabName + "ListCount\", \"" + firstCharToLower(tabName) + ".getSimple" + tabName + "List\", queryParam);\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@SuppressWarnings(\"unchecked\")\n");
		sb.append("\t@Override\n");
		sb.append("\tpublic <T> List<T> getList(T dto) {\n");
		sb.append("\t\tMap<String, Object> param = toMap(dto, \"yyyy-MM-dd HH:mm:ss\");\n");
		sb.append("\t\treturn (List<T>)this.queryForList(\"" + firstCharToLower(tabName) + ".get" + tabName + "List\", param);\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@SuppressWarnings(\"unchecked\")\n");
		sb.append("\t@Override\n");
		sb.append("\tpublic <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {\n");
		sb.append("\t\tQueryParam queryParam = new QueryParam();\n");
		sb.append("\t\tqueryParam.setPageNo(pageNo);\n");
		sb.append("\t\tqueryParam.setPageSize(pageSize);\n");
		sb.append("\t\tqueryParam.setParam(dto);\n");
		sb.append("\t\treturn (PageList<T>) this.queryForPage(\"" + firstCharToLower(tabName) + ".get" + tabName + "ListCount\", \"" + firstCharToLower(tabName) + ".get" + tabName + "List\", queryParam, dto.getClass());\n");
		sb.append("\t}\n\n");
		
		sb.append("\t@Override\n");
		sb.append("\tpublic <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {\n");
		sb.append("\t\treturn (PageList<T>) this.queryForPage(\"" + firstCharToLower(tabName) + ".get" + tabName + "ListCount\", \"" + firstCharToLower(tabName) + ".get" + tabName + "List\", queryParam, clazz);\n");
		sb.append("\t}\n\n");
		
		sb.append("}");
		createFile(serviceFile, sb);
		System.out.println(serviceImplName + ".java 建立完成.");
	}
	
	@SuppressWarnings("unused")
	private void createDto(Object bean) throws IOException{//com.star.framework.drivingschool.data.Comment
		String entityName = bean.getClass().getSimpleName();
		String dtoName = entityName.replace("Entity", "DTO");
		File dtoFile = new File(dtoPath + dtoName + ".java");
		if(dtoFile.exists()){
			System.out.println(dtoName + ".java 已经存在,不再生成.");
			return;
		}
		StringBuilder head = new StringBuilder();
		StringBuilder pros = new StringBuilder();
		StringBuilder methods = new StringBuilder();
		Map<String, String> types = new HashMap<>();
		types.put("java.io.Serializable", "");
		head.append(this.getClass().getPackage() + ".dto." + moduleName + ";\n\n");
		head.append("import java.io.Serializable;\n");
		pros.append("public class " + dtoName + " implements Serializable {\n");
		pros.append("\tprivate static final long serialVersionUID = 1L;\n\n");
		
		List<Method> meths = getMethods(bean);
		for (Method f : meths) {
			
			String type = f.getReturnType().getName();
			String typeName = f.getReturnType().getSimpleName();
			String fieldName = firstCharToLower(f.getName().replace("get", ""));
			if (! types.containsKey(type) && ! type.startsWith("java.lang.")) {
				types.put(type, "");
				head.append("import " + type + ";\n");
			}
			pros.append("\tprivate " + typeName + " " + fieldName + ";\n");
			methods.append("\tpublic " + typeName + " get" + firstCharToUpper(fieldName) + "() {\n");
			methods.append("\t\treturn " + fieldName + ";\n");
			methods.append("\t}\n");
			methods.append("\tpublic void set" + firstCharToUpper(fieldName) + "(" + typeName + " " + fieldName + ") {\n");
			methods.append("\t\tthis." + fieldName + " = " + fieldName + ";\n");
			methods.append("\t}\n");
		}
		head.append("\n");
		head.append(pros);
		head.append("\n");
		head.append(methods);
		head.append("}");
		
		createFile(dtoFile, head);
		System.out.println(dtoName + ".java 建立完成.");
	}
	
	public static String getTableName(Object entity){
		try {
			Annotation[] annotations = entity.getClass().getAnnotations();
			if (null != annotations && annotations.length > 0) {
				for (int i = 0; i < annotations.length; i++) {
					Annotation annotation = annotations[i];
					if(annotation.annotationType().getName().equals("javax.persistence.Entity")){
						Method method = annotation.annotationType().getDeclaredMethod("name");
						return (String) method.invoke(annotation);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**得到entity的字段列表用逗号分隔， zx表示是否纵向**/
	public static String getFieldsOrProperties(Object entity, boolean field, boolean zx){
		String fs = "";
		String ps = "";
		int l = 2;
		List<String[]> fields = getProAndField(entity);
		if (null != fields && fields.size() > 0) {
			for (String[] ff : fields) {
				fs += ff[1] + ", ";
				ps += ":" + ff[0] + ", ";
				if (zx) {
					fs += "\n\t\t\t\t";
					ps += "\n\t\t\t\t";
					l = "\t\t\t\t".length() + 3;
				}
			}
		}else{
			return "";
		}
		if (field) {
			return fs.substring(0, fs.length() - l);
		}
		return ps.substring(0, ps.length() - l);
	}
	
	public static String getKeyValue(Object entity, String tag){
		String res = "";
		List<String[]> fields = getProAndField(entity);
		String pId = getEntityId(entity, false);
		if (null != fields && fields.size() > 0) {
			int i = 0;
			int l = tag.length();
			for (String[] ff : fields) {
				if (! pId.equals(ff[0])) {
					if (i > 0 || tag.equals("AND ")) {
						res += "\t\t\t\t<#if " + ff[0] + "?exists && " + ff[0] + " != \"\">\n";
						l = -1;
					}
					res += "\t\t\t\t\t" + tag + ff[1] + " = :" + ff[0] + "\n";
					if (i > 0 || tag.equals("AND ")) {
						res += "\t\t\t\t</#if>\n";
					}
					i ++;
				}
			}
			if (! tag.equals("AND ")) {
				l = tag.length();
			}
			return res.substring("\t\t\t\t\t".length() + l);
		}else{
			return "";
		}
	}
	
	public static List<String[]> getProAndField(Object entity){
		List<String[]> res = new ArrayList<>();
		try {
			Method[] methods = entity.getClass().getMethods();
			for (Method method : methods) {
				Annotation[] annotations = method.getAnnotations();
				for (Annotation annotation : annotations) {
					String name = annotation.annotationType().getName();
					if (name.equals("javax.persistence.Column")) {
						Method am = annotation.annotationType().getDeclaredMethod("name");
						String fieldName = (String) am.invoke(annotation);
						if (keywords.contains(fieldName)) {
							fieldName = "`" + fieldName + "`";
						}
						String proName = firstCharToLower(method.getName().replace("get", ""));
						res.add(new String[]{proName, fieldName});
					}
				}
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**得到entity的主键 db表示是字段名 还是属性名**/
	public static String getEntityId(Object entity, boolean db){
		try {
			if (null == entity) {
				return null;
			}
			String fieldName = null;
			String methodName = null;
			boolean bool = false;
			Method[] methods = entity.getClass().getMethods();
			for (Method method : methods) {
				Annotation[] annotations = method.getAnnotations();
				for (Annotation annotation : annotations) {
					String name = annotation.annotationType().getName();
					if (name.equals("javax.persistence.Id")) {
						methodName = method.getName();
						methodName = firstCharToLower(methodName = methodName.replace("get", ""));
						for (Annotation annotation2 : annotations) {
							String name2 = annotation2.annotationType().getName();
							if (name2.equals("javax.persistence.Column")) {
								Method am = annotation2.annotationType().getDeclaredMethod("name");
								fieldName = (String) am.invoke(annotation2);
							}
						}
						bool = true;
						break;
					}
				}
				if (bool) {
					break;
				}
			}
			if (db) {
				return fieldName;
			}else{
				return methodName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getEntityType(Object entity){
		if (null == entity) {
			return null;
		}
		String type = null;
		boolean bool = false;
		Method[] methods = entity.getClass().getMethods();
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			for (Annotation annotation : annotations) {
				String name = annotation.annotationType().getName();
				if (name.equals("javax.persistence.Id")) {
					type = method.getReturnType().getName();
					bool = true;
					break;
				}
			}
			if (bool) {
				break;
			}
		}
		if (null != type) {
			return type;
		}
		return null;
	}
	
	public static String firstCharToUpper(String name){
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	
	public static String allToUpper(String name){
		return name.toUpperCase();
	}
	
	public static String firstCharToLower(String name){
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}

	public static String firstCharAndLower(String name){
		return name.substring(0, 1).toLowerCase();
	}
	
	public static boolean createFile(File file, StringBuilder content){
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
	
	public static boolean createFile(String path, String content){
		try {
			File file = new File(path);
			if (file.exists()) {
				return true;
			}
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(content);
			bw.flush();
			bw.close();
			osw.close();
			fos.close();
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	private List<Method> getMethods(Object bean){
		List<Method> list = new ArrayList<>();
		Method[] methods = bean.getClass().getDeclaredMethods();
		for (Method method : methods) {
			Column column = method.getAnnotation(Column.class);
			if (null != column) {
				list.add(method);
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		try{
			CreateOtherFiles cf = new CreateOtherFiles();
			cf.create();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}