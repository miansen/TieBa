package priv.sen.client;

import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import priv.sen.net.MessageContexts;
import priv.sen.net.MessageObj;
import priv.sen.net.MyHttp;
import priv.sen.net.UrlType;
import priv.sen.server.MyServerHandler;

/**
 * @author MiansenWang
 * 2017年9月18日
 * 下午8:24:49
 * 客户端处理逻辑类
 */


public class MyClientHandler extends ChannelHandlerAdapter {
	
	private static Logger logger = Logger.getLogger(MyServerHandler.class);


	/**
	 * 服务器有消息返回的处理方法
	 * 客户端读取（接消息）
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		MessageObj peekMessage = MessageContexts.getInstance().pollMessage();
		MyHttp myHttp = (MyHttp) msg;
		UrlType type = myHttp.getType();
		/**
		 * 处理广播消息
		 */
		if(type.equals(UrlType.GUANG_BO)){
			
		}
		/**
		 * 处理正常消息
		 */
		if(peekMessage != null && type.equals(UrlType.NALMORE)){
			peekMessage.handlerMessage((MyHttp)msg);
			MessageContexts.flag = true;
		}
		logger.debug(33333);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
}

	
