package priv.sen.serviece;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import priv.sen.db.JDBCUtil;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;
import priv.sen.util.SqlPingJie;

/**
 * 模糊查询帖子
 * @author sen
 *
 */
public class S_TieZiServiece implements IMyService{
	private static Logger logger = Logger.getLogger(S_TieZiServiece.class);

	public MyHttp handlerMessage(String...text) {
		String sqlMoHuChanXunTieZi = SqlPingJie.sqlMoHuChanXunTieZi(text[0].toString(), text[1].toString());
		logger.debug("搜索帖子的sql语句是:"+sqlMoHuChanXunTieZi);
		List<Map<String,Object>> findAll = JDBCUtil.findAll(sqlMoHuChanXunTieZi);
		MyHttp myHttp = new MyHttp();
		myHttp.setType(UrlType.NALMORE);
		myHttp.setData(findAll);
		return myHttp;
	}
}
