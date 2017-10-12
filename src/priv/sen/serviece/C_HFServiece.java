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
 *	回复帖子处理函数
 * @author sen
 *
 */
public class C_HFServiece implements IMyService{
	private static Logger logger = Logger.getLogger(C_HFServiece.class);

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
		String insert = SqlPingJie.insertHuiFu(text[0].toString(),text[1].toString(),text[2].toString(),text[3].toString(),text[4].toString());
		logger.debug("插入回复帖子的sql语句是："+insert);
		JDBCUtil.insert(insert);
		MyHttp myHttp = new MyHttp();
		myHttp.setType(UrlType.NALMORE);
//		myHttp.setData(findAll);
		return myHttp;
	}
}
