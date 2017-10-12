package priv.sen.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import io.netty.channel.Channel;

/**
 * 
 * @author MiansenWang
 * 2017年9月22日
 * 下午2:59:58
 * TODO
 */
public class SessionContext {
	private static Logger logger = Logger.getLogger(SessionContext.class);
	private static Map<Channel,ChinaUser> map = new HashMap();
	
	/**
	 * 抛异常就删除掉
	 * @param channel
	 */
	public static void remove(Channel channel) {
		map.remove(channel);
	}

	
	public static void put(Channel channel) {
		map.put(channel, null);
	}


	public static void put(Channel channel, ChinaUser msg) {
		ChinaUser chinaUser = map.get(channel);
		if(chinaUser == null){
			map.put(channel, msg);
		}
	}


	public static void Each() {
		
	}


	public static void Each(SerssionHandler serssionHandler) {
		Set<Entry<Channel,ChinaUser>> entrySet = map.entrySet();
		Iterator<Entry<Channel, ChinaUser>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<Channel, ChinaUser> next = iterator.next();
			serssionHandler.handlerSerssion(next.getKey(),next.getValue());
		}
	}
}
