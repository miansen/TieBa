package priv.sen.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import priv.sen.gui2.LogOnFrm;
import priv.sen.util.SqlPingJie;

public class JDBCUtil {

	private static Logger logger = Logger.getLogger(JDBCUtil.class);
	/**
	 * 查找表的所有数据
	 */
	public static List<Map<String, Object>> findAll(String sql, Object... args) {
		final List<Map<String, Object>> list = new ArrayList<>();

		query(sql, new QueryVisiter() {

			@Override
			public void handlerSet(ResultSet set) throws SQLException {
				ResultSetMetaData metaData = set.getMetaData();
				int columnCount = metaData.getColumnCount();
				while (set.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						map.put(metaData.getColumnName(i), set.getObject(i));// key是列名，values是数据
					}
					list.add(map);
				}
			}
		}, args);
		return list;
	}
	
	/**
	 * 只查找表的单条数据
	 * @param sql
	 * @param args
	 * @return
	 */
	public static Map<String, Object> findSingle(String sql, Object... args) {
		final HashMap<String,Object> map = new HashMap<>();
		query(sql, new QueryVisiter() {

			@Override
			public void handlerSet(ResultSet set) throws SQLException {
				ResultSetMetaData metaData = set.getMetaData();
				int columnCount = metaData.getColumnCount();
				if (set.next()) {
					for (int i = 1; i <= columnCount; i++) {
						map.put(metaData.getColumnName(i), set.getObject(i));// key是列名，values是数据
					}
				}
			}
		}, args);
		return map;
	}
	

	/**
	 * 更新数据
	 */
	public static boolean update(String sql, Object... args) {
		int set = 0;
		PreparedStatement statement = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConn();
			conn.setAutoCommit(false);// 禁止自动commit
			statement = conn.prepareStatement(sql);
			for (int i = 1; i <= args.length; i++) {
				statement.setObject(i, args[i - 1]);
			}
			set = statement.executeUpdate();
//			conn.rollback();
			return set > 0;// 判断是否执行成功
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, statement, conn);
		}
		return false;
	}

	/**
	 * 插入元素
	 */
	public static void insert(String sql,Object... obj) {
		int set = 0;
		PreparedStatement statement = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConn();
			// 3.预编译sql
			statement = conn.prepareStatement(sql);
//			for (int i = 1; i <= obj.length; i++) {
//				statement.setString(i, obj.toString());
//				
//			}
//			statement.setString(1, "XXX");
//			statement.setString(2, "dev");
//			statement.setInt(3, 999);
			// 4.执行sql获取结果
			set = statement.executeUpdate();// 返回插入的结果
			System.out.println(set);// 打印生效的条数
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, statement, conn);
		}
	}
	/**
	 * 测试代码
	 * @param args
	 */
	public static void main(String[] args) {
//		String insertTieBa = SqlPingJie.insertTieBa("GGG", "HHH");
//		logger.debug(insertTieBa);
//		insert(insertTieBa);
		
	}

	
	/**
	 * 插入元素
	 * @param sql 
	 * @param str 
	 * @return 
	 */
	public static Map<String, Object> insert2(String sql, String str) {
		int set = 0;
		PreparedStatement statement = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConn();
			// 3.预编译sql
			statement = conn.prepareStatement(sql);
			statement.setString(1, str);
//			statement.setString(2, "dev");
//			statement.setInt(3, 999);
			// 4.执行sql获取结果
			set = statement.executeUpdate();// 返回插入的结果
			System.out.println(set);// 打印生效的条数
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, statement, conn);
		}
		return null;
	}
	
	/**
	 * 查询
	 * 
	 * @param sql
	 */
	private static void query(String sql, QueryVisiter visiter, Object... obj) {
		ResultSet set = null;
		PreparedStatement statement = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConn();
			statement = conn.prepareStatement(sql);
			for (int i = 1; i <= obj.length; i++) {
				statement.setObject(i, obj[i - 1]);
			}
			set = statement.executeQuery();
			visiter.handlerSet(set);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally { // 6.关闭资源
			DBUtil.close(set, statement, conn);
		}
	}

	/**
	 * 更新数据（控制commit，rollback）
	 * 
	 * @param sql
	 * @return
	 * @return
	 */
	public static void update2() {
		ResultSet set = null;
		PreparedStatement statement = null;
		Connection conn = null;
		List<Map<String, Object>> findAll = null;
		String str = "delete MENP where deptno = 20";
		String str2 = "select * from MENP";

		try {
			conn = DBUtil.getConn();
			conn.setAutoCommit(false);// 禁止自动commit
			statement = conn.prepareStatement(str);
			statement.executeUpdate();
			PreparedStatement statement2 = conn.prepareStatement(str2);
			set = statement2.executeQuery();
			List<Map<String, Object>> select = select(set);
			conn.rollback();
			conn.commit();
			System.out.println(select);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(null, statement, conn);
		}
	}

	/**
	 * 查询
	 * 
	 * @param set
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object>> select(ResultSet set) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<>();
		int columnCount = set.getMetaData().getColumnCount();// 有多少列
		while (set.next()) {
			HashMap<String, Object> map = new HashMap<>();
			for (int i = 1; i <= columnCount; i++) {
				map.put(set.getMetaData().getColumnName(i), set.getObject(i));
			}
			list.add(map);
		}
		return list;
	}
	
//	public static void main(String[] args) {
//		List<Map<String,Object>> findAll = findAll("select * from lz_user");
//		System.out.println(findAll);
//	}
}
