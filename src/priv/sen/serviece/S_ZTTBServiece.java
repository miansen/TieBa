package priv.sen.serviece;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import priv.sen.db.JDBCUtil;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;
import priv.sen.util.SqlPingJie;

/**
 * ËÑË÷Ö÷ÌâÌù°É
 * @author sen
 *
 */
public class S_ZTTBServiece implements IMyService{
	private static Logger logger = Logger.getLogger(S_ZTTBServiece.class);

	public MyHttp handlerMessage(String...text) {
		String sqlMoHuChanXunTieBa = SqlPingJie.sqlMoHuChanXunTieBa(text[0].toString());
		logger.debug("ËÑË÷Ìù°ÉµÄsqlÓï¾äÊÇ:"+sqlMoHuChanXunTieBa);
		List<Map<String,Object>> findAll = JDBCUtil.findAll(sqlMoHuChanXunTieBa);
		MyHttp myHttp = new MyHttp();
		myHttp.setType(UrlType.NALMORE);
		myHttp.setData(findAll);
		return myHttp;
	}
}
