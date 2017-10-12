package priv.sen.entry;

/**
 * 贴吧表类
 * @author sen
 *
 */
public class MZttb {
	
	
	/**
	 * 单例贴吧表，保证每个每个窗体中拿到的都是同一个值
	 */
	private MZttb(){
		
	}
	private static class Inner{
		private static MZttb instance = new MZttb();
	}
	public static MZttb getInstance(){
		return Inner.instance;
	}
	
	private java.lang.String u_zt;//贴吧主题

	public java.lang.String getU_zt() {
		return u_zt;
	}
	public void setU_zt (java.lang.String u_zt) { 
		this.u_zt = u_zt;
	}

	private java.lang.String u_name;//吧主名字

	public java.lang.String getU_name() {
		return u_name;
	}
	public void setU_name (java.lang.String u_name) { 
		this.u_name = u_name;
	}

	private java.sql.Timestamp u_date;//创建贴吧的时间

	public java.sql.Timestamp getU_date() {
		return u_date;
	}
	public void setU_date (java.sql.Timestamp u_date) { 
		this.u_date = u_date;
	}

	private java.lang.String u_jianjie;//贴吧简介

	public java.lang.String getU_jianjie() {
		return u_jianjie;
	}
	public void setU_jianjie (java.lang.String u_jianjie) { 
		this.u_jianjie = u_jianjie;
	}

}