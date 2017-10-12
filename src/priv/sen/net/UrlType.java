package priv.sen.net;

import org.apache.log4j.Logger;

public enum UrlType {
	
	/**
	 * 正常包，客户端发送请求，服务器提供效应
	 */
	NALMORE,
	/**
	 * 客户端没有主动发送消息，服务器推送消息
	 */
	GUANG_BO,
	/**
	 * 每一个客户登录服务器都可以返回一个广播消息
	 */
	LOGIN
}
