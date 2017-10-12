package priv.sen.serviece;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import priv.sen.db.JDBCUtil;
import priv.sen.entry.MTz;
import priv.sen.entry.MZttb;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;
import priv.sen.util.SqlPingJie;

/**
 * 创建贴吧
 * @author sen
 *
 */
public class C_TBServiece implements IMyService{
	private static Logger logger = Logger.getLogger(C_TBServiece.class);

	public MyHttp handlerMessage(String...text) {
//		MTz instance = MTz.getInstance();
//		String str = instance.getTbzt();//拿到用户点击的贴吧名称
//		logger.debug(str);
		
//		String insertTieBa = SqlPingJie.insertTieBa(text);
//		logger.debug("插入贴吧的sql语句是："+insertTieBa);
//		JDBCUtil.insert(insertTieBa);
//		logger.debug(findAll);
//		List<Map<String,Object>> findAll = JDBCUtil.findAll("select * from M_TZ where TBZT = '刘德华吧'");
		MZttb instance = MZttb.getInstance();
		String insertTieBa = SqlPingJie.insertTieBa(text[0].toString(),text[1].toString(),text[2].toString(),text[3].toString());
		logger.debug("创建贴吧的sql语句是："+insertTieBa);
		JDBCUtil.insert(insertTieBa);
		MyHttp myHttp = new MyHttp();
		myHttp.setType(UrlType.NALMORE);
//		myHttp.setData(findAll);
		return myHttp;
	}
}
