package priv.sen.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import priv.sen.db2.StringUtil;

/**
 * @author 
 * MiansenWang 
 * 2017年9月16日 
 * 下午3:14:30 
 * 实体类的生成器
 * 输入表名就能生成实体类
 */
public class EntryGenerator {

	private static final String PACKAGE_NAME = "priv.sen.entry";

	/**
	 * 测试代码
	 * @param args
	 */
	public static void main(String[] args) {

//		List<Map<String, Object>> findAll = JDBCUtil
//				.findAll("select table_name from user_tables union select view_name as table_name from user_views");
//		List<Map<String,Object>> findAll = JDBCUtil.findAll("select * from lz_user");
//		for (Map<String, Object> map : findAll) {
//			generatorEntryForTableName(map.get("TABLE_NAME").toString());
//		}
		generatorEntryForTableName("TieZi");
	}

	/**
	 * generatorEntry的重载 
	 * 根据表名和视图名创建实体类
	 * 用户只需要输入表名 
	 * sql语句自动拼接
	 * @param tableName
	 */
	public static void generatorEntryForTableName(String tableName) {
		String sql = "select * from " + tableName + " where 1=2";
		tableName = StringUtil.xia2Pascle(tableName.toLowerCase());
		generatorEntry(sql, tableName);

	}

	/**
	 * 根据sql和指定的类名创建实体类
	 * 用户只需要输入sql语句和表名就能生成数据库的实体类
	 * @param sql:sql语句
	 * @param className:表名
	 * @param PACKAGE_NAME:包名
	 */
	public static void generatorEntry(String sql, String className) {
		Connection conn = null;
		PreparedStatement Statement = null;
		ResultSet resultSet = null;
		List<Menp> list = new ArrayList<>();
		try {
			conn = DBUtil.getConn();
			Statement = conn.prepareStatement(sql);
			resultSet = Statement.executeQuery();// 查询
			ResultSetMetaData metaData = resultSet.getMetaData();// 结果集
			int columnCount = metaData.getColumnCount();// 多少行
			// 获取列名和数据类型
			HashMap<String, String> dateMap = new HashMap<>();

			for (int i = 1; i <= columnCount; i++) {
				dateMap.put(metaData.getColumnName(i), metaData.getColumnClassName(i));// 列名+值类型
			}

			FreeUtil.createEntryClassBody(dateMap, className, PACKAGE_NAME);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("正在生成实体类..."+sql + "--------" + className);
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet, Statement, conn);
		}
	}
}
