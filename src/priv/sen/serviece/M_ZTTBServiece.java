package priv.sen.serviece;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import priv.sen.db.JDBCUtil;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;

/**
 * Ö÷ÌâÌù°É±í
 * @author sen
 *
 */
public class M_ZTTBServiece implements IMyService{
	private static Logger logger = Logger.getLogger(M_ZTTBServiece.class);

	public MyHttp handlerMessage(String...text) {
		List<Map<String,Object>> findAll = JDBCUtil.findAll("select * from M_ZTTB order by U_DATE");
		MyHttp myHttp = new MyHttp();
		myHttp.setType(UrlType.NALMORE);
		myHttp.setData(findAll);
		return myHttp;
	}
}
