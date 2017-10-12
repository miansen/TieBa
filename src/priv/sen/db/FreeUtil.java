package priv.sen.db;

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

/**
 * @author 
 * MiansenWang 2
 * 017年9月14日 
 * 下午3:21:37 
 * freemarker模版加载实体类
 */
public class FreeUtil {
	public static void main(String[] args) throws IOException, Exception {
		HashMap<String, Object> map = new HashMap<>();
		
		HashMap<String, String> dateMap = new HashMap<>();
		dateMap.put("int", "age");
		dateMap.put("String", "name");
		
		
		createEntryClassBody(dateMap,"Menp","priv/sen/entry".replace("/", "."));
		
		
	}

	/**
	 * 创建实体类用到的freemarker
	 * @param file2:模版路径
	 * @param dateMap:key是列名，values是值类型
	 * @param className:表名
	 * @param packageName:包名
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws TemplateException
	 */
	public static void createEntryClassBody(HashMap<String, String> dateMap, String className, String packageName)
			throws URISyntaxException, IOException, TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, TemplateException {
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("classname", className);
		map.put("packagename", packageName);
		map.put("colunms", dateMap);
		
		URL rr = FreeUtil.class.getClassLoader().getResource(".");//当前地址
		//实体类写出的地址
		String replace = rr.getFile().replace("bin", "src");//换成src地址
		replace+=packageName.replace(".", "/");
		replace+="/"+className+".java";
		System.out.println(replace);
		File file = new File(replace);// 写出到这里
		URL resource = EntryGenerator.class.getResource(".");	
		File file2 = new File(resource.toURI());
		String templateName = "entry.tpl";// 模版名称
//		String string = getString(map, file, file2, templateName);
//		System.out.println(string);
		toFile(map,file,file2,templateName);	
	}

	/**
	 * 用freemarker将结果输出到文件中
	 * @param templateName
	 * @param map:字体信息
	 * @param file:输出文件的File
	 * @param path:模版路径
	 * @param templateName:模版名称
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws TemplateException
	 */
	@SuppressWarnings("unused")
	private static void toFile(Map<String, Object> map, File file,File path, String templateName)
			throws IOException, URISyntaxException, TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, TemplateException {

		Configuration con = new Configuration(Configuration.VERSION_2_3_23);
		con.setDefaultEncoding("UTF-8");
//		URL resource = JDBC_OOP.class.getResource(".");// 路径
//		File file2 = new File(resource.toURI());
		con.setDirectoryForTemplateLoading(path);
		Template template = con.getTemplate(templateName);// 模版地址
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
//		FileWriter fileWriter = new FileWriter(file);
		template.process(map, outputStreamWriter);
	}

	/**
	 * 用freemarker将结果输出到内存数组中
	 * 获取最终合成的字符串
	 * @param map
	 * @param file
	 * @param path
	 * @param templateName
	 * @return new String(byteArray,"UTF-8");
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws TemplateException
	 */
	@SuppressWarnings("unused")
	private static String getString(Map<String, Object> map,String template)
			throws IOException, URISyntaxException, TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, TemplateException {

		Configuration con = new Configuration(Configuration.VERSION_2_3_23);
		con.setDefaultEncoding("UTF-8");
		StringTemplateLoader templateLoader = new StringTemplateLoader();
		templateLoader.putTemplate("aaa", template);
		con.setTemplateLoader(templateLoader);
		URL resource = EntryGenerator.class.getResource(".");// 路径
		File file2 = new File(resource.toURI());
//		con.setDirectoryForTemplateLoading(path);
		Template tt = con.getTemplate("aaa");// 模版地址
		ByteArrayOutputStream stream = new ByteArrayOutputStream();//写到内存里
		OutputStreamWriter writer = new OutputStreamWriter(stream);
		tt.process(map, writer);
		byte[] byteArray = stream.toByteArray();
		return new String(byteArray,"UTF-8");
	}
}
