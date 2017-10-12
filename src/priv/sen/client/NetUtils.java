package priv.sen.client;

import org.apache.log4j.Logger;

import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import priv.sen.net.MyDecode;
import priv.sen.net.MyEncode;

/**
 * @author MiansenWang
 * 2017年9月22日
 * 下午1:24:22
 * 编码和解码类
 */
public class NetUtils {
	private static Logger logger = Logger.getLogger(NetUtils.class);

	public static void addDe(SocketChannel ch) {
		
//		ch.pipeline().addLast(new StringDecoder());
//		ch.pipeline().addLast(new StringEncoder());
		LengthFieldBasedFrameDecoder lengthde = new LengthFieldBasedFrameDecoder(
				Integer.MAX_VALUE, 0, 4, 0, 4, true);
		ch.pipeline().addLast(lengthde);
		ch.pipeline().addLast(new MyDecode());
		ch.pipeline().addLast(new MyEncode());
	}
}
