/**create by liuhua at 2017年2月9日 下午1:55:46**/
package com.booting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 先 CreateOtherFiles 之前执行
 * 因为建立dto之后，才可以获取class,进行反射。
 * 
 * @author liuhua
 *
 */
public class CreateDTO {
	
	private String basePath;
	private String entityPath;
	private String dtoPath;
	private String moduleName;
	
	public CreateDTO(String moduleName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String fileName = getClass().getSimpleName() + ".class";
		URL url = getClass().getResource(fileName);
		basePath = url.getPath().replace("target/classes", "src/main/java").replace(fileName, "");
		this.moduleName = moduleName;
	}
	
	public CreateDTO() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String fileName = getClass().getSimpleName() + ".class";
		URL url = getClass().getResource(fileName);
		basePath = url.getPath().replace("target/classes", "src/main/java").replace(fileName, "");
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
		entityPath = basePath + moduleName + "/entity/";
		dtoPath = basePath + moduleName + "/dto/";
	
		File filePath = new File(dtoPath);
		if (! filePath.exists()) {
			filePath.mkdirs();
		}
		
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
			for (int i = 0; i < files.length; i++) {
				createDto2(files[i]);
			}
		}else{
			throw new Exception("模块下必须有Entity类");
		}
	}

	private void createDto2(File file){
		try {
			String dtoName = file.getName().replace(".java", "").replace("Entity", "DTO");
			File dtoFile = new File(dtoPath + dtoName + ".java");
			if(dtoFile.exists()){
				System.out.println(dtoName + ".java 已经存在,不再生成.");
				return;
			}
			FileReader freader = new FileReader(file);
			BufferedReader breader = new BufferedReader(freader);
			StringBuilder sb = new StringBuilder();
			try {
				String temp = "";
				while ((temp = breader.readLine()) != null) {
					sb.append(temp);
					sb.append('\n');
				}
				String src = sb.toString();
				src = changePackage(src);
				src = removeImport(src);
				src = removeTag(src, "@Entity");
				src = removeTagId(src, "@Id");
				src = removeTags(src, "@Column");
				src = changeName(src);
				createFile(dtoFile, new StringBuilder(src));
				System.out.println(dtoName + ".java 建立完成.");
			} catch (IOException e) {
				System.out.println("文件读取失败");
			} finally {
				breader.close();
				freader.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("文件不存在");
		} catch (IOException e) {
			System.out.println("文件读取失败");
		}
	}
	
	private String changeName(String src) {
		Pattern leftpattern = Pattern.compile("public class ");
		Matcher leftmatcher = leftpattern.matcher(src);
		Pattern rightpattern = Pattern.compile("\\{");
		Matcher rightmatcher = rightpattern.matcher(src);
		int begin = 0;
		if (leftmatcher.find(begin)) {
			rightmatcher.find(leftmatcher.start());
			String pk = src.substring(leftmatcher.start(), rightmatcher.end());
			begin = rightmatcher.end();
			src = src.replace(pk, pk.replace("Entity", "DTO"));
		}
		return src;
	}

	private String removeTags(String src, String tag) {
		Pattern leftpattern = Pattern.compile(tag);
		Matcher leftmatcher = leftpattern.matcher(src);
		Pattern rightpattern = Pattern.compile("\\)");
		Matcher rightmatcher = rightpattern.matcher(src);
		int begin = 0;
		if (leftmatcher.find(begin)) {
			rightmatcher.find(leftmatcher.start());
			String pk = src.substring(leftmatcher.start(), rightmatcher.end());
			begin = rightmatcher.end();
			src = src.replace(pk, "");
			src = removeTags(src, tag);
		}
		return src;
	}
	
	private String removeTagId(String src, String tag) {
		Pattern leftpattern = Pattern.compile(tag);
		Matcher leftmatcher = leftpattern.matcher(src);
		Pattern rightpattern = Pattern.compile("\\\n");
		Matcher rightmatcher = rightpattern.matcher(src);
		int begin = 0;
		if (leftmatcher.find(begin)) {
			rightmatcher.find(leftmatcher.start());
			String pk = src.substring(leftmatcher.start(), rightmatcher.end());
			begin = rightmatcher.end();
			src = src.replace(pk, "");
		}
		return src;
	}
	
	private String removeTag(String src, String tag) {
		Pattern leftpattern = Pattern.compile(tag);
		Matcher leftmatcher = leftpattern.matcher(src);
		Pattern rightpattern = Pattern.compile("\\)");
		Matcher rightmatcher = rightpattern.matcher(src);
		int begin = 0;
		if (leftmatcher.find(begin)) {
			rightmatcher.find(leftmatcher.start());
			String pk = src.substring(leftmatcher.start(), rightmatcher.end());
			begin = rightmatcher.end();
			src = src.replace(pk + "\n", "");
		}
		return src;
	}

	private String removeImport(String src) {
		Pattern leftpattern = Pattern.compile("import javax");
		Matcher leftmatcher = leftpattern.matcher(src);
		Pattern rightpattern = Pattern.compile(";");
		Matcher rightmatcher = rightpattern.matcher(src);
		int begin = 0;
		String pk = "";
		while (leftmatcher.find(begin)) {
			rightmatcher.find(leftmatcher.start());
			pk += src.substring(leftmatcher.start(), rightmatcher.end()) + "\n";
			begin = rightmatcher.end();
		}
		src = src.replace(pk + "\n", "");
		return src;
	}

	private String changePackage(String src) {
		Pattern leftpattern = Pattern.compile("package ");
		Matcher leftmatcher = leftpattern.matcher(src);
		Pattern rightpattern = Pattern.compile(";");
		Matcher rightmatcher = rightpattern.matcher(src);
		int begin = 0;
		if (leftmatcher.find(begin)) {
			rightmatcher.find(leftmatcher.start());
			String pk = src.substring(leftmatcher.start(), rightmatcher.end());
			begin = rightmatcher.end();
			src = src.replace(pk, pk.replace("entity", "dto"));
		}
		return src;
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
			CreateDTO cf = new CreateDTO();
			cf.create();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}