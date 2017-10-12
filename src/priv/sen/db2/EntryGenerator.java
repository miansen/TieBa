package priv.sen.db2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import priv.sen.db.DBUtil;
import priv.sen.db.JDBCUtil;

public class EntryGenerator {

	private static final String PACKAGE_NAME = "priv.sen.db.entry";
	public static void main(String[] args) {
		
		List<Map<String, Object>> findAll = JDBCUtil.findAll(
				"select table_name from user_tables union select view_name as table_name  from user_views");
		for (Map<String, Object> map : findAll) {
			generatorEntryForTableName(map.get("TABLE_NAME").toString());
		}

	
	}

	/**
	 * 根据表名或视图名创建实体类
	 */
	public static void generatorEntryForTableName(String tableName) {
		String sql = "select * from "+tableName+" where 1=2";
		tableName=StringUtil.xia2Pascle(tableName.toLowerCase());
		generatorEntry(sql,tableName);
	}

	/**
	 * 根据sql和指定的类名来创建实体类
	 */
	public static void generatorEntry(String sql, String className) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			 conn = DBUtil.getConn();
			statement = conn.prepareStatement(sql);
			resultSet = statement.executeQuery();
			ResultSetMetaData metaData = resultSet.getMetaData();
			Map<String, String> dataMap = new HashMap<String, String>();
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				dataMap.put(
						metaData.getColumnName(i).toLowerCase(),metaData.getColumnClassName(i));
			}
			FreeUtil.createEntryClassBody( dataMap,className,PACKAGE_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(sql+"-----"+className);
			e.printStackTrace();
		}finally{
			DBUtil.close(resultSet, statement, conn);
		}
	}
}
