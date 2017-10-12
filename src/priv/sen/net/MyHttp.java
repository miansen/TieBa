package priv.sen.net;

import java.io.Serializable;

import org.apache.log4j.Logger;
/**
 * 自定义协议包
 * @author MiansenWang
 * 2017年9月22日
 * 下午1:15:50
 * TODO
 */
public class MyHttp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 497096512302141369L;

	private static Logger logger = Logger.getLogger(MyHttp.class);
	
	private String myUrl;
	private Object data;
	private Object data2;
	private Object data3;
	private Object data4;
	private Object data5;
	private Object data6;
	public Object getData6() {
		return data6;
	}
	public void setData6(Object data6) {
		this.data6 = data6;
	}
	public Object getData5() {
		return data5;
	}
	public void setData5(Object data5) {
		this.data5 = data5;
	}
	public Object getData4() {
		return data4;
	}
	public void setData4(Object data4) {
		this.data4 = data4;
	}
	public Object getData2() {
		return data2;
	}
	public void setData2(Object data2) {
		this.data2 = data2;
	}
	public Object getData3() {
		return data3;
	}
	public void setData3(Object data3) {
		this.data3 = data3;
	}
	private UrlType type;
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		MyHttp.logger = logger;
	}
	public String getMyUrl() {
		return myUrl;
	}
	public void setMyUrl(String myUrl) {
		this.myUrl = myUrl;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public UrlType getType() {
		return type;
	}
	public void setType(UrlType type) {
		this.type = type;
	}
	
	
}
