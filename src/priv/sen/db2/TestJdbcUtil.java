package priv.sen.db2;

import java.util.Map;

import priv.sen.db.JDBCUtil;

/**
 * ÖÐ¶È·â×°
 * @author Administrator
 */
public class TestJdbcUtil {
	
	public static void main(String[] args) {
		String sql = "select e.* ,d.dname from memp e join mdept d on e.deptno=d.deptno";
		Map<String, Object> findAll = (Map<String, Object>) JDBCUtil.findAll(sql);
		System.out.println(findAll);
	}
}
