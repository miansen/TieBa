package priv.sen.db;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 
 * @author MiansenWang
 * 2017年9月13日
 * 下午7:58:06
 * 查询结果处理
 */
public interface QueryVisiter {

	void handlerSet(ResultSet set) throws SQLException;

}
