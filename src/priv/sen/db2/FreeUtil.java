package priv.sen.db2;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import freemarker.cache.StringTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

public class FreeUtil {


	public static void main(String[] args) throws IOException, Exception {
		
	
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("int", "age");
		dataMap.put("String", "name");
		createEntryClassBody( dataMap,"Memp","priv/sen/db/entry".replace("/", "."));
	}

	/**
	 * 创建实体类用到的freemarker
	 * @param dataMap （className,packageName,所有的字段信息）
	 * @param className
	 * @param packageName
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws TemplateException
	 */
	public static void createEntryClassBody(
			Map<String, String> dataMap, String className, String packageName) throws URISyntaxException,
			IOException, TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, TemplateException {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("classname", className);
		map.put("packagename", packageName);
		map.put("columns", dataMap);
		
		URL rr = FreeUtil.class.getClassLoader().getResource(".");
		String replace = rr.getFile().replace("bin", "src");
		replace+=packageName.replace(".", "/");
		replace+="/"+className+".java";
		System.out.println(replace);
		File file = new File( replace);
		URL resource = EntryGenerator.class.getResource(".");
		File file2 = new File(resource.toURI());
		String templateName = "entry.tpl";
		toFile(map, file,file2,templateName);
		
	}

	/**
	 * 用freemarker将结果输出到文件中
	 * @param map
	 * @param file 输出文件的file
	 * @param path 模板路径
	 * @param templateName
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws TemplateException
	 */
	@SuppressWarnings("resource")
	public static void toFile(Map<String, Object> map, File file, File path, 
			String templateName)
			throws IOException, URISyntaxException, TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, TemplateException {
		Configuration conf = new Configuration(Configuration.VERSION_2_3_23);
		conf.setDefaultEncoding("utf-8");
		conf.setDirectoryForTemplateLoading(path);
		Template template = conf.getTemplate(templateName);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		file.createNewFile();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
		template.process(map, outputStreamWriter);
	}
	
	
	
	/**
	 * 获取最终合成的结果字符串
	 * @param map
	 * @param file
	 * @param path
	 * @param templateName
	 * @return 
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws TemplateException
	 */
	public static String getString(Map<String, Object> map, String template)
			throws IOException, URISyntaxException, TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, TemplateException {
		Configuration conf = new Configuration(Configuration.VERSION_2_3_23);
		conf.setDefaultEncoding("utf-8");
		
		StringTemplateLoader templateLoader=new StringTemplateLoader();
		templateLoader.putTemplate("aaa", template);
		conf.setTemplateLoader(templateLoader);
		Template tt = conf.getTemplate("aaa");
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(outputStream);
		tt.process(map, writer);
		byte[] byteArray = outputStream.toByteArray();
		return new String(byteArray,"utf-8");
	}
}
