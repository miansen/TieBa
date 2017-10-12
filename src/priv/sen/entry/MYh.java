package priv.sen.entry;

/**
 * 用户类
 * 
 * @author sen
 *
 */
public class MYh {

	/**
	 * 单例用户类
	 */
	private MYh() {

	}

	private static class Inner {
		private static MYh instance = new MYh();
	}

	public static MYh getInstance() {
		return Inner.instance;
	}

	private java.lang.String u_password;//密码

	public java.lang.String getU_password() {
		return u_password;
	}

	public void setU_password(java.lang.String u_password) {
		this.u_password = u_password;
	}

	private java.lang.String u_tieba;

	public java.lang.String getU_tieba() {
		return u_tieba;
	}

	public void setU_tieba(java.lang.String u_tieba) {
		this.u_tieba = u_tieba;
	}

	private java.lang.String u_qianming;

	public java.lang.String getU_qianming() {
		return u_qianming;
	}

	public void setU_qianming(java.lang.String u_qianming) {
		this.u_qianming = u_qianming;
	}

	private java.lang.String u_id;

	public java.lang.String getU_id() {
		return u_id;
	}

	public void setU_id(java.lang.String u_id) {
		this.u_id = u_id;
	}

	private java.lang.String u_huifu;

	public java.lang.String getU_huifu() {
		return u_huifu;
	}

	public void setU_huifu(java.lang.String u_huifu) {
		this.u_huifu = u_huifu;
	}

	private java.lang.String u_name;

	public java.lang.String getU_name() {
		return u_name;
	}

	public void setU_name(java.lang.String u_name) {
		this.u_name = u_name;
	}

	@Override
	public String toString() {
		return "MYh [u_password=" + u_password + ", u_tieba=" + u_tieba
				+ ", u_qianming=" + u_qianming + ", u_id=" + u_id
				+ ", u_huifu=" + u_huifu + ", u_name=" + u_name + "]";
	}

}