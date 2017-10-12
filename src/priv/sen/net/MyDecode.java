package priv.sen.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.util.List;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author MiansenWang
 * 2017年9月22日
 * 下午1:30:05
 * 解码
 */
public class MyDecode extends ByteToMessageDecoder {
	private static Logger logger = Logger.getLogger(MyDecode.class);

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		byte[] dst = new byte[in.readableBytes()];
 		in.readBytes(dst);
// 		String info = new String(dst);
 		MyHttp info = fromBytes(dst);
 		out.add(info);	
	}

	private MyHttp fromBytes(byte[] dst) throws Exception {
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dst);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);)
		{	
			return (MyHttp)objectInputStream.readObject();
		}
	}	
}
