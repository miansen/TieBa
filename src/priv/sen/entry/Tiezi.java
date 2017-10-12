package priv.sen.entry;

/**
 * 帖子表类
 * @author sen
 *
 */
public class Tiezi {
	
	/**
	 * 单例贴吧表，保证每个每个窗体中拿到的都是同一个值
	 */
	private Tiezi(){
		
	}
	private static class Inner{
		private static Tiezi instance = new Tiezi();
	}
	public static Tiezi getInstance(){
		return Inner.instance;
	}
	
	private java.lang.String lzname;//楼主昵称

	public java.lang.String getLzname() {
		return lzname;
	}
	public void setLzname (java.lang.String lzname) { 
		this.lzname = lzname;
	}

	private java.lang.String hfname;//回复人昵称

	public java.lang.String getHfname() {
		return hfname;
	}
	public void setHfname (java.lang.String hfname) { 
		this.hfname = hfname;
	}

	private java.lang.String tbname;//贴吧主题

	public java.lang.String getTbname() {
		return tbname;
	}
	public void setTbname (java.lang.String tbname) { 
		this.tbname = tbname;
	}

	private java.lang.String hfneirong;//回复内容

	public java.lang.String getHfneirong() {
		return hfneirong;
	}
	public void setHfneirong (java.lang.String hfneirong) { 
		this.hfneirong = hfneirong;
	}

	private java.lang.String hfdate;//回复时间

	public java.lang.String getHfdate() {
		return hfdate;
	}
	public void setHfdate (java.lang.String hfdate) { 
		this.hfdate = hfdate;
	}

	private java.lang.String tzname;//帖子标题

	public java.lang.String getTzname() {
		return tzname;
	}
	public void setTzname (java.lang.String tzname) { 
		this.tzname = tzname;
	}

	private java.lang.String tzdate;//帖子发表的时间

	public java.lang.String getTzdate() {
		return tzdate;
	}
	public void setTzdate (java.lang.String tzdate) { 
		this.tzdate = tzdate;
	}

	private java.lang.String tzneirong;//帖子的正文

	public java.lang.String getTzneirong() {
		return tzneirong;
	}
	public void setTzneirong (java.lang.String tzneirong) { 
		this.tzneirong = tzneirong;
	}

}