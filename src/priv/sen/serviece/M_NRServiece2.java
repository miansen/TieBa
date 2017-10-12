package priv.sen.serviece;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import priv.sen.db.JDBCUtil;
import priv.sen.entry.MTz;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;
import priv.sen.util.SqlPingJie;

/**
 * 查询回复
 * @author sen
 *
 */
public class M_NRServiece2 implements IMyService{
	private static Logger logger = Logger.getLogger(M_NRServiece2.class);

	public MyHttp handlerMessage(String...text) {
//		MTz instance = MTz.getInstance();
//		String str = instance.getTbzt();//拿到用户点击的贴吧名称
//		logger.debug(str);
		
		String sqlTieZi = SqlPingJie.sqlTieZi(text[0],text[1]);
		logger.debug(sqlTieZi);
		List<Map<String,Object>> findAll = JDBCUtil.findAll(sqlTieZi);
		logger.debug(findAll);
//		List<Map<String,Object>> findAll = JDBCUtil.findAll("select * from M_TZ where TBZT = '刘德华吧'");
		MyHttp myHttp = new MyHttp();
		myHttp.setType(UrlType.NALMORE);
		myHttp.setData(findAll);
		return myHttp;
	}
}
