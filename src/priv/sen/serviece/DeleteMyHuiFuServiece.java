package priv.sen.serviece;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import priv.sen.db.JDBCUtil;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;
import priv.sen.util.SqlPingJie;

/**
 * 删除我的回复
 * @author sen
 *
 */
public class DeleteMyHuiFuServiece implements IMyService{
	private static Logger logger = Logger.getLogger(DeleteMyHuiFuServiece.class);

	public MyHttp handlerMessage(String...text) {
		String sqlDeleteMyTieZi = SqlPingJie.sqlDeleteMyHuiFu(text[0].toString());
		logger.debug("删除我的回复的sql语句是:"+sqlDeleteMyTieZi);
		List<Map<String,Object>> findAll = JDBCUtil.findAll(sqlDeleteMyTieZi);
		MyHttp myHttp = new MyHttp();
		myHttp.setType(UrlType.NALMORE);
		myHttp.setData(findAll);
		return myHttp;
	}
}
