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
import java.util.Date;

import com.star.framework.aop.annotation.Description;

public class CreateFacade {

	private String basePath;
	private String entityPath;
	private String moduleName;
	private String facadeName;
	private String baseClassPath;
	private String entityClassPath;
	private String dtoClassPath;
	private String serviceClassPath;
//	private String serviceImplClassPath;
	private String facadePath;
	private String facadeClassPath;
	private String facadeImplPath;
	
	public CreateFacade() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
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
		moduleName = getInputPath("输入模块名称(例如userinfo):");
		if (null == moduleName || "".equals(moduleName)) {
			throw new IOException("请输入模块名称)");
		}
		facadeName = getInputPath("输入Facade名称(Facade接口的文件名，例如：HnUserFacade):");
		if (null == facadeName || "".equals(facadeName)) {
			throw new IOException("请输入Facade名称)");
		}
		entityPath = basePath + moduleName + "/entity/";
		facadePath = basePath + moduleName + "/facade/";
		facadeImplPath = facadePath + "impl/";
		entityClassPath = baseClassPath + moduleName + "." + "entity.";
		dtoClassPath = baseClassPath + moduleName + "." + "dto.";
		serviceClassPath = baseClassPath + moduleName + "." + "service.";
//		serviceImplClassPath = baseClassPath + "service." + moduleName + ".";
		facadeClassPath = baseClassPath + moduleName + ".facade";
		
		CreateOtherFiles cf = new CreateOtherFiles(moduleName);
		cf.create();
		
		File filePath = new File(facadePath);
		if (! filePath.exists()) {
			filePath.mkdirs();
		}
		filePath = new File(facadeImplPath);
		if (! filePath.exists()) {
			filePath.mkdirs();
		}
		
		String[] entityClassPaths = null;
		File file = new File(entityPath);
		if (file.exists()) {
			File files[] = file.listFiles(new FilenameFilter(){
				public boolean accept(File dir, String name) {
					if (name.endsWith("Entity.java")) {
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
		createFacade(entityClassPaths);
		createFacadeImpl(entityClassPaths);
	}
	
	private void createFacadeImpl(String[] entityClassPaths) throws IOException{
		File facadeFile = new File(facadeImplPath + facadeName + "Impl.java");
		if(facadeFile.exists()){
			System.out.println(facadeName + "Impl.java 已经存在,不再生成.");
			return;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder head = new StringBuilder();
		StringBuilder body = new StringBuilder();
		StringBuilder bodyMethods = new StringBuilder();
		
		head.append("/** create by auto at " + sdf.format(new Date()) + "**/\n");
		head.append(this.getClass().getPackage() + "." + moduleName + ".facade.impl;\n\n");
		head.append("import java.util.ArrayList;\n");
		head.append("import java.util.List;\n\n");
		head.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		head.append("import org.springframework.stereotype.Service;\n\n");
		head.append("import " + facadeClassPath + "." + facadeName + ";\n");
		head.append("import com.star.framework.jdbc.dao.result.PageList;\n");
		head.append("import com.star.framework.jdbc.dao.result.QueryParam;\n");
		head.append("import com.star.framework.utils.CglibBeanUtils;\n\n");
		
		body.append("@Service(\"" + firstCharToLower(facadeName) + "\")\n");
		body.append("public class " + facadeName + "Impl implements " + facadeName + " {\n");
		body.append("\tprivate static final long serialVersionUID = 1L;\n\n");
		
		for (int i = 0; i < entityClassPaths.length; i++) {
			createFacadeImplMethod(entityClassPaths[i], head, body, bodyMethods);
		}
		
		bodyMethods.append("\t@Override\n");
		bodyMethods.append("\tpublic <T> List<T> getList(T dto){\n");
		bodyMethods.append("\t\treturn null;\n");
		bodyMethods.append("\t}\n\n");

		bodyMethods.append("\t@Override\n");
		bodyMethods.append("\tpublic <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize){\n");
		bodyMethods.append("\t\treturn null;\n");
		bodyMethods.append("\t}\n\n");

		bodyMethods.append("\t@Override\n");
		bodyMethods.append("\tpublic <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz){\n");
		bodyMethods.append("\t\treturn null;\n");
		bodyMethods.append("\t}\n\n");
		
		head.append("\n");
		head.append(body);
		head.append("\n");
		head.append(bodyMethods);
		head.append("}");
		
//		System.out.println(head);
		createFile(facadeFile, head);
		System.out.println(facadeName + "Impl.java 建立完成.");
	}
	
	private void createFacadeImplMethod(String entityPath, StringBuilder head, StringBuilder body, StringBuilder bodyMethods) {
		try {
			Object entity = Class.forName(entityPath).newInstance();
			String entityName = entity.getClass().getSimpleName();
			String dtoName = entityName.replace("Entity", "DTO");
			String name = entityName.replace("Entity", "");
			String serviceName = entityName.replace("Entity", "Service");
			String serviceName2 = firstCharToLower(serviceName);
			String entityId = getEntityId(entity, false);
//			String entityIdType = getEntityType(entity);
			String dtoPath = dtoClassPath + dtoName;
			String servicePath = serviceClassPath + serviceName;
			head.append("import " + dtoPath + ";\n");
			head.append("import " + entityPath + ";\n");
			head.append("import " + servicePath + ";\n");
			
			body.append(("\t@Autowired\n"));
			body.append(("\tprivate " + serviceName + " " + serviceName2 + ";\n\n"));
			
			bodyMethods.append("\t@Override\n");
			bodyMethods.append("\tpublic Long save" + name + "(" + dtoName + " " + firstCharToLower(dtoName) + "){\n");
			bodyMethods.append("\t\tif (null == " + firstCharToLower(dtoName) + ") {\n");
			bodyMethods.append("\t\t\treturn null;\n");
			bodyMethods.append("\t\t}\n");
			bodyMethods.append("\t\t" + entityName + " entity = to" + name + "Entity(" + firstCharToLower(dtoName) + ");\n");
			bodyMethods.append("\t\t" + firstCharToLower(dtoName) + " = " + serviceName2 + ".save(entity);\n");
			bodyMethods.append("\t\treturn " + firstCharToLower(dtoName) + ".get" + firstCharToUpper(entityId) + "();\n");
			bodyMethods.append("\t}\n\n");

			bodyMethods.append("\t@Override\n");
			bodyMethods.append("\tpublic void batchSave" + name + "(List<" + dtoName + "> dtos){\n");
			bodyMethods.append("\t\tif (null == dtos || dtos.isEmpty()) {\n");
			bodyMethods.append("\t\t\treturn;\n");
			bodyMethods.append("\t\t}\n");
			bodyMethods.append("\t\tList<" + entityName + "> entities = to" + name + "Entities(dtos);\n");
			bodyMethods.append("\t\t" + serviceName2 + ".batchSave(entities);\n");
			bodyMethods.append("\t}\n\n");

			bodyMethods.append("\t@Override\n");
			bodyMethods.append("\tpublic int update" + name + "(" + dtoName + " " + firstCharToLower(dtoName) + "){\n");
			bodyMethods.append("\t\t" + firstCharToLower(dtoName) + " = " + serviceName2 + ".updateBySql(" + firstCharToLower(dtoName) + ");\n");
			bodyMethods.append("\t\treturn 1;\n");
			bodyMethods.append("\t}\n\n");

			bodyMethods.append("\t@Override\n");
			bodyMethods.append("\tpublic void batchUpdate" + name + "(List<" + dtoName + "> dtos){\n");
			bodyMethods.append("\t\tif (null == dtos || dtos.isEmpty()) {\n");
			bodyMethods.append("\t\t\treturn;\n");
			bodyMethods.append("\t\t}\n");
			bodyMethods.append("\t\t" + serviceName2 + ".batchUpdate(dtos);\n");
			bodyMethods.append("\t}\n\n");

			bodyMethods.append("\t@Override\n");
			bodyMethods.append("\tpublic int delete" + name + "(long " + entityId + "){\n");
			bodyMethods.append("\t\treturn " + serviceName2 + ".delete(" + entityId + ");\n");
			bodyMethods.append("\t}\n\n");

			bodyMethods.append("\t@Override\n");
			bodyMethods.append("\tpublic " + dtoName + " get" + name + "(long " + entityId + "){\n");
			bodyMethods.append("\t\treturn " + serviceName2 + ".get(" + entityId + ");\n");
			bodyMethods.append("\t}\n\n");

			bodyMethods.append("\t@Override\n");
			bodyMethods.append("\tpublic " + dtoName + " get" + name + "(" + dtoName + " " + firstCharToLower(dtoName) + "){\n");
			bodyMethods.append("\t\treturn " + serviceName2 + ".get(" + firstCharToLower(dtoName) + ");\n");
			bodyMethods.append("\t}\n\n");

			bodyMethods.append("\t@Override\n");
			bodyMethods.append("\tpublic List<" + dtoName + "> get" + name + "List(" + dtoName + " " + firstCharToLower(dtoName) + "){\n");
			bodyMethods.append("\t\treturn " + serviceName2 + ".getSimpleList(" + firstCharToLower(dtoName) + ");\n");
			bodyMethods.append("\t}\n\n");

			bodyMethods.append("\t@Override\n");
			bodyMethods.append("\tpublic PageList<" + dtoName + "> get" + name + "ListForPage(" + dtoName + " " + firstCharToLower(dtoName) + ", int pageNumber, int pageSize){\n");
			bodyMethods.append("\t\treturn " + serviceName2 + ".getSimpleListForPage(" + firstCharToLower(dtoName) + ", pageNumber, pageSize);\n");
			bodyMethods.append("\t}\n\n");

			bodyMethods.append("\t@Override\n");
			bodyMethods.append("\tpublic PageList<" + dtoName + "> get" + name + "ListForPage(QueryParam queryParam){\n");
			bodyMethods.append("\t\treturn " + serviceName2 + ".getSimpleListForPage(queryParam);\n");
			bodyMethods.append("\t}\n\n");

			bodyMethods.append("\t@Override\n");
			bodyMethods.append("\tpublic " + entityName + " to" + name + "Entity(" + dtoName + " dto){\n");
			bodyMethods.append("\t\t" + entityName + " entity = new " + entityName + "();\n");
			bodyMethods.append("\t\tCglibBeanUtils.copy(dto, entity);\n");
			bodyMethods.append("\t\treturn entity;\n");
			bodyMethods.append("\t}\n\n");

			bodyMethods.append("\t@Override\n");
			bodyMethods.append("\tpublic List<" + entityName + "> to" + name + "Entities(List<" + dtoName + "> dtos){\n");
			bodyMethods.append("\t\tList<" + entityName + "> entities = new ArrayList<>();\n");
			bodyMethods.append("\t\tfor(" + dtoName + " dto : dtos){\n");
			bodyMethods.append("\t\t\tentities.add(to" + name + "Entity(dto));\n");
			bodyMethods.append("\t\t}\n");
			bodyMethods.append("\t\treturn entities;\n");
			bodyMethods.append("\t}\n\n");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createFacade(String[] entityClassPaths) throws IOException{
		File facadeFile = new File(facadePath + facadeName + ".java");
		if(facadeFile.exists()){
			System.out.println(facadeName + ".java 已经存在,不再生成.");
			return;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder head = new StringBuilder();
		StringBuilder body = new StringBuilder();
		
		head.append("/** create by auto at " + sdf.format(new Date()) + "**/\n");
		head.append(this.getClass().getPackage() + "." + moduleName + ".facade;\n\n");
		head.append("import java.io.Serializable;\n");
		head.append("import java.util.List;\n\n");
		head.append("import com.star.framework.jdbc.dao.result.PageList;\n");
		head.append("import com.star.framework.jdbc.dao.result.QueryParam;\n");
		body.append("public interface " + facadeName + " extends Serializable {\n\n");
		
		for (int i = 0; i < entityClassPaths.length; i++) {
			createFacadeMethod(entityClassPaths[i], head, body);
		}
		
		body.append("\t/**\n");
		body.append("\t * 查询满足条件的 列表(多表)\n");
		body.append("\t */\n");
		body.append("\tpublic <T> List<T> getList(T dto);\n\n");
		body.append("\t/**\n");
		body.append("\t * 查询满足条件的列表(分页)(多表)\n");
		body.append("\t */\n");
		body.append("\tpublic <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize);\n\n");
		body.append("\t/**\n");
		body.append("\t * 查询满足条件的列表(分页)(多表)\n");
		body.append("\t */\n");
		body.append("\tpublic <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz);\n\n");
		
		head.append("\n");
		head.append(body);
		head.append("}");
		
		createFile(facadeFile, head);
		System.out.println(facadeName + ".java 建立完成.");
	}
	
	private void createFacadeMethod(String entityPath, StringBuilder head, StringBuilder body) {
		try {
			Object entity = Class.forName(entityPath).newInstance();
			String entityName = entity.getClass().getSimpleName();
			String dtoName = entityName.replace("Entity", "DTO");
			String name = entityName.replace("Entity", "");
			String entityId = getEntityId(entity, false);
			String dtoPath = dtoClassPath + dtoName;
			String annotation = getDescription(entity, "");
			head.append("import " + dtoPath + ";\n");
			head.append("import " + entityPath + ";\n");
			
			body.append("\t/**\n");
			body.append("\t * 新增 " + annotation + "\n");
			body.append("\t */\n");
			body.append("\tpublic Long save" + name + "(" + dtoName + " " + firstCharToLower(dtoName) + ");\n\n");
			body.append("\t/**\n");
			body.append("\t * 批量新增 " + annotation + "\n");
			body.append("\t */\n");
			body.append("\tpublic void batchSave" + name + "(List<" + dtoName + "> dtos);\n\n");
			body.append("\t/**\n");
			body.append("\t * 更新 " + annotation + "\n");
			body.append("\t */\n");
			body.append("\tpublic int update" + name + "(" + dtoName + " " + firstCharToLower(dtoName) + ");\n\n");
			body.append("\t/**\n");
			body.append("\t * 批量 " + annotation + "\n");
			body.append("\t */\n");
			body.append("\tpublic void batchUpdate" + name + "(List<" + dtoName + "> dtos);\n\n");
			body.append("\t/**\n");
			body.append("\t * 删除 " + annotation + "\n");
			body.append("\t */\n");
			body.append("\tpublic int delete" + name + "(long " + entityId + ");\n\n");
			body.append("\t/**\n");
			body.append("\t * 根据主键获取 " + annotation + "\n");
			body.append("\t */\n");
			body.append("\tpublic " + dtoName + " get" + name + "(long " + entityId + ");\n\n");
			body.append("\t/**\n");
			body.append("\t * 根据条件获取一条 " + annotation + "\n");
			body.append("\t */\n");
			body.append("\tpublic " + dtoName + " get" + name + "(" + dtoName + " " + firstCharToLower(dtoName) + ");\n\n");
			body.append("\t/**\n");
			body.append("\t * 查询满足条件的 " + annotation + " 列表(单表)\n");
			body.append("\t */\n");
			body.append("\tpublic List<" + dtoName + "> get" + name + "List(" + dtoName + " " + firstCharToLower(dtoName) + ");\n\n");
			body.append("\t/**\n");
			body.append("\t * 查询满足条件的 " + annotation + " 列表(分页)(单表)\n");
			body.append("\t */\n");
			body.append("\tpublic PageList<" + dtoName + "> get" + name + "ListForPage(" + dtoName + " " + firstCharToLower(dtoName) + ", int pageNumber, int pageSize);\n\n");
			body.append("\t/**\n");
			body.append("\t * 查询满足条件的 " + annotation + " 列表(分页)(单表)\n");
			body.append("\t */\n");
			body.append("\tpublic PageList<" + dtoName + "> get" + name + "ListForPage(QueryParam queryParam);\n\n");
			body.append("\t/**\n");
			body.append("\t * " + annotation + "DTO 转换成 Entity\n");
			body.append("\t */\n");
			body.append("\tpublic " + entityName + " to" + name + "Entity(" + dtoName + " " + firstCharToLower(dtoName) + ");\n\n");
			body.append("\t/**\n");
			body.append("\t * " + annotation + "DTOs 转换成 Entities\n");
			body.append("\t */\n");
			body.append("\tpublic List<" + entityName + "> to" + name + "Entities(List<" + dtoName + "> dtoes);\n\n");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getDescription(Object bean, String content) {
		Description description = bean.getClass().getAnnotation(Description.class);
		if (null != description) {
			return description.name() + content;
		}
		return "";
	}
	
	public static String getTableName(Object entity){
		try {
			Annotation[] annotations = entity.getClass().getAnnotations();
			if (null != annotations && annotations.length > 0) {
				for (int i = 0; i < annotations.length; i++) {
					Annotation annotation = annotations[i];
					if(annotation.annotationType().getName().equals("javax.persistence.Entity")){
						Method method = annotation.annotationType().getDeclaredMethod("name", new Class[0]);
						return (String) method.invoke(annotation, new Object[0]);
					}
				}
			}
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
								Method am = annotation2.annotationType().getDeclaredMethod("name", new Class[0]);
								fieldName = (String) am.invoke(annotation2, new Object[0]);
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
	
	public static void main(String[] args) {
		try{
			CreateFacade cf = new CreateFacade();
			cf.create();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}