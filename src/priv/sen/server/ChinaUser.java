package priv.sen.server;

import org.apache.log4j.Logger;

public class ChinaUser {
	private static Logger logger = Logger.getLogger(ChinaUser.class);
	
	private String userName;
	private String passWorld;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWorld() {
		return passWorld;
	}
	public void setPassWorld(String passWorld) {
		this.passWorld = passWorld;
	}
}
