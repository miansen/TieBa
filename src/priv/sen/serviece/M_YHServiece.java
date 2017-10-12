package priv.sen.serviece;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import priv.sen.db.JDBCUtil;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;
import priv.sen.util.SqlPingJie;

/**
 * 处理用户表（登录验证）
 * @author sen
 *
 */
public class M_YHServiece implements IMyService{
	private static Logger logger = Logger.getLogger(M_YHServiece.class);

	public MyHttp handlerMessage(String...text) {
		String sqlMYh = SqlPingJie.sqlMYh();
		logger.debug("查选用户的sql语句是："+sqlMYh);
		List<Map<String,Object>> findAll = JDBCUtil.findAll(sqlMYh);
		MyHttp myHttp = new MyHttp();
		myHttp.setType(UrlType.NALMORE);
		myHttp.setData(findAll);
		return myHttp;
	}
}
