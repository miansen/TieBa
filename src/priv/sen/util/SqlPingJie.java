package priv.sen.util;

/**
 * sql语句拼接
 * 
 * @author sen
 *
 */
public class SqlPingJie {

	/**
	 * 模糊查询贴吧
	 * 
	 * @param str
	 * @return
	 */
	public static String sqlMoHuChanXunTieBa(String str) {
		StringBuffer sb = new StringBuffer("select * from M_ZTTB");

		sb.append(" and U_ZT like '%" + str + "%'");

		String replaceFirst = sb.toString().replaceFirst("and", "where");
		return replaceFirst;
	}

	
	/**
	 * 模糊查询帖子
	 * @param str:帖子标题
	 * @param str2:贴吧主题
	 * @return
	 */
	public static String sqlMoHuChanXunTieZi(String str,String str2) {
		StringBuffer sb = new StringBuffer("select * from fatie");

		sb.append(" and TZBT like '%" + str + "%'");
		sb.append(" and TBZT = \'" + str2 + "\'");
		String replaceFirst = sb.toString().replaceFirst("and", "where");
		return replaceFirst;
	}
	
	
	/**
	 * 查询帖子
	 * @param str
	 * @return
	 */
	public static String sqlTieBa(String str) {
		StringBuffer sb = new StringBuffer("select * from fatie");

		sb.append(" and TBZT = \'" + str + "\' order by FA_TIE_DATE");

		String replaceFirst = sb.toString().replaceFirst("and", "where");
		return replaceFirst;
	}

	/**
	 * 查询回复内容
	 * @param str
	 * @return
	 */
	public static String sqlTieZi(String str,String str2) {
		StringBuffer sb = new StringBuffer(
				"select U_NAME,HUI_FU_DATE,HF from huifu");

		sb.append(" and TBZT = \'" + str + "\'");
		sb.append(" and TZBT = \'" + str2 + "\' order by HUI_FU_DATE");
//		sb.append(" and HFNAME is not null");
	
		String replaceFirst = sb.toString().replaceFirst("and", "where");
		return replaceFirst;
	}

	
	/**
	 * 查询正文
	 * @param str
	 * @return
	 */
	public static String sqlTieZi2(String str,String str2) {
		StringBuffer sb = new StringBuffer(
				"select ZW from fatie");

		sb.append(" and TBZT = \'" + str + "\'");
		sb.append(" and TZBT = \'" + str2 + "\'");
//		sb.append(" and HFNAME is not null");
	
		String replaceFirst = sb.toString().replaceFirst("and", "where");
		return replaceFirst;
	}
	
	/**
	 * 插入用户创建的贴吧 根据用户输入的贴吧主题和贴吧简介 插入到数据库
	 * "insert into MENP (ename,job) values ('EEE','FFF')"
	 * 
	 * @param str:贴吧主题  U_ZT
	 * @param str2:吧主名字  U_NAME
	 * @param str3 :贴吧简介  U_JIANJIE
	 * @param str4 :创吧时间  U_DATE
	 * @return
	 */
	public static String insertTieBa(String str, String str2, String str3, String str4) {
		StringBuffer sb = new StringBuffer(
				"insert into M_ZTTB (U_ZT,U_NAME,U_JIANJIE,U_DATE) values ");

		sb.append("(\'" + str + "\',");
		sb.append("\'" + str2 + "\',");
		sb.append("\'" + str3 + "\',");
		sb.append("\'" + str4 + "\')");

		String replaceFirst = sb.toString();
		return replaceFirst;
	}

	/**
	 * TODO 测试代码
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String insertTieBa = sqlTieZi("1","3吧");
		System.out.println(insertTieBa);
	}

	/**
	 * 查询用户名
	 * 
	 * @param str
	 * @return
	 */
	public static String sqlYongHuMingCheng(String str) {
		StringBuffer sb = new StringBuffer("select U_NAME from M_YH");

		sb.append(" and U_NAME = \'" + str + "\'");

		String replaceFirst = sb.toString().replaceFirst("and", "where");
		return replaceFirst;
	}

	/**
	 * 插入用户发表的帖子
	 * @param str6 
	 * 
	 * @param string
	 *            :帖子主题 TBNAME
	 * @param string2
	 *            :帖子标题 TZNAME
	 * @param string3
	 *            :发帖人名字 LZNAME
	 * @param string4
	 *            :发帖时间 TZDATE
	 * @param string5
	 *            :帖子正文 TZNEIRONG
	 * @return
	 */
	public static String insertTieZi(String str, String str2,
			String str3, String str4, String str5) {
		
		StringBuffer sb = new StringBuffer(
				"insert into fatie (TBZT,TZBT,U_NAME,FA_TIE_DATE,ZW) values ");

		sb.append("(\'" + str + "\',");
		sb.append("\'" + str2 + "\',");
		sb.append("\'" + str3 + "\',");
		sb.append("\'" + str4 + "\',");
		sb.append("\'" + str5 + "\')");

		String replaceFirst = sb.toString();
		return replaceFirst;
	}

	/**
	 * 插入回复帖子
	 * @param str: 贴吧主题 TBNAME
	 * @param str2:帖子标题 TZNAME
	 * @param str3:帖子正文内容 TZNEIRONG
	 * @param str4:回复内容  HFNEIRONG
	 * @param str5:回复人名字 HFNAME
	 * @param str6:回复时间 HFDATE
	 * @return
	 */
	public static String insertHuiFu(String str,String str2,
			String str3, String str4, String str5) {
		

		
		StringBuffer sb = new StringBuffer(
				"insert into huifu (TBZT,TZBT,U_NAME,HUI_FU_DATE,HF) values ");

		sb.append("(\'" + str + "\',");
		sb.append("\'" + str2 + "\',");
		sb.append("\'" + str3 + "\',");
		sb.append("\'" + str4 + "\',");
		sb.append("\'" + str5 + "\')");

		String replaceFirst = sb.toString();
		return replaceFirst;
	
	}
	
	
	/**
	 * 查询用户信息
	 * @param str
	 * @return
	 */
	public static String sqlMYh() {
		StringBuffer sb = new StringBuffer("select U_NAME,U_PASSWORD from M_YH");
		String replaceFirst = sb.toString();
		return replaceFirst;
	}
	
	/**
	 * 用户注册
	 * @param str:贴吧主题  U_NAME
	 * @param str2:吧主名字  U_PASSWORD
	 * @return
	 */
	public static String insertyongHu(String str, String str2) {
		StringBuffer sb = new StringBuffer(
				"insert into M_YH (U_NAME,U_PASSWORD) values ");

		sb.append("(\'" + str + "\',");
		sb.append("\'" + str2 + "\')");

		String replaceFirst = sb.toString();
		return replaceFirst;
	}

	
	/**
	 * 加载我的贴吧
	 * @param str:用户名
	 * @return
	 */
	public static String sqlJiaZaiMyTieBa(String str) {
		

		StringBuffer sb = new StringBuffer("select * from M_ZTTB");

		sb.append(" and U_NAME = \'" + str + "\' order by U_DATE");

		String replaceFirst = sb.toString().replaceFirst("and", "where");
		return replaceFirst;
	
	}


	/**
	 * 加载我的帖子
	 * @param stri
	 * @return
	 */
	public static String sqlJiaZaiMyTieZi(String str) {
		
		StringBuffer sb = new StringBuffer("select * from fatie");

		sb.append(" and U_NAME = \'" + str + "\' order by FA_TIE_DATE");

		String replaceFirst = sb.toString().replaceFirst("and", "where");
		return replaceFirst;
	
	}
	
	/**
	 * 加载我的回复
	 * @param stri
	 * @return
	 */
	public static String sqlJiaZaiMyH(String str) {
		
		StringBuffer sb = new StringBuffer("select * from huifu");

		sb.append(" and U_NAME = \'" + str + "\' order by HUI_FU_DATE");

		String replaceFirst = sb.toString().replaceFirst("and", "where");
		return replaceFirst;
	
	}
	
	/**
	 * 删除我的贴吧
	 * @param stri
	 * @return
	 */
	public static String sqlDeleteMyTieBa(String str) {
		
		StringBuffer sb = new StringBuffer("delete M_ZTTB");

		sb.append(" and U_ZT = \'" + str + "\'");

		String replaceFirst = sb.toString().replaceFirst("and", "where");
		return replaceFirst;
	
	}
	
	/**
	 * 删除我的帖子
	 * @param stri
	 * @return
	 */
	public static String sqlDeleteMyTieZi(String str) {
		
		StringBuffer sb = new StringBuffer("delete fatie");

		sb.append(" and TZBT = \'" + str + "\'");

		String replaceFirst = sb.toString().replaceFirst("and", "where");
		return replaceFirst;
	
	}
	
	/**
	 * 删除我的回复
	 * @param stri
	 * @return
	 */
	public static String sqlDeleteMyHuiFu(String str) {
		
		StringBuffer sb = new StringBuffer("delete huifu");

		sb.append(" and HF = \'" + str + "\'");

		String replaceFirst = sb.toString().replaceFirst("and", "where");
		return replaceFirst;
	
	}
	
	/**
	 * 查询详细回复
	 * 
	 * @param str
	 * @return
	 */
	public static String sqlXiangXi(String str,String str2) {
		StringBuffer sb = new StringBuffer(
				"select HF from huifu");

		sb.append(" and TBZT = \'" + str + "\'");
		sb.append(" and HF = \'" + str2 + "\'");
	
		String replaceFirst = sb.toString().replaceFirst("and", "where");
		return replaceFirst;
	}
	
}
