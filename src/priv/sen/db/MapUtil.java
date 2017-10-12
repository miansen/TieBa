package priv.sen.db;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import priv.sen.db2.StringUtil;

public class MapUtil {
	
	public static Object map2obj(Map<String,Object> map){
		return null;
	}
	
	public static void main(String[] args) throws Exception{
		List<Map<String,Object>> findAll = JDBCUtil.findAll("select * from menp");
		Field[] declaredFields = Menp.class.getDeclaredFields();
		Map<String, Object> map = findAll.get(0);
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		Menp menp = Menp.class.newInstance();
		
		while(iterator.hasNext()){
			Entry<String, Object> next = iterator.next();
			String xia2cape = StringUtil.xia2cape(next.getKey());
			Field field;
			try {
				field = Menp.class.getDeclaredField(xia2cape);
			} catch (Exception e) {
				e.printStackTrace();
			}
			field = Menp.class.getDeclaredField(xia2cape);
//			if(field == null ) continue;
			field.setAccessible(true);
			field.set(menp, next.getValue());
//			System.out.println(xia2cape);
			StringBuilder str = new StringBuilder();
		}
		System.out.println(menp);
	}
}
