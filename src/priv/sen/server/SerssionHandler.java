package priv.sen.server;

import io.netty.channel.Channel;

public interface SerssionHandler {
	/**
	 * 处理session对象的方法
	 * @param channel
	 * @param chinaUser
	 */
	void handlerSerssion(Channel channel, ChinaUser chinaUser);
}
