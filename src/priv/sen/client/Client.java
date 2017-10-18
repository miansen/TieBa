package priv.sen.client;

import org.apache.log4j.Logger;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import priv.sen.net.MessageContexts;
import priv.sen.net.MessageObj;

/**
 * @author MiansenWang
 * 2017年9月18日
 * 下午7:27:16
 * 客户端
 */
public class Client {
	
	private static Logger logger = Logger.getLogger(Client.class);
	public static void main(String[] args) {
		new Client().connet("127.0.0.1",9999);
	}

	public void connet(String ip, int port) {
		EventLoopGroup group = null;//声明线程池
		try {
		Bootstrap b = new Bootstrap();//客户端启动对象 
		group = new NioEventLoopGroup();
		b.group(group).channel(NioSocketChannel.class)
		.option(ChannelOption.TCP_NODELAY, true)
		.handler(getHandler());
		
		/**
		 * 发送消息
		 */
			ChannelFuture sync = b.connect(ip,port).sync();
			//持久连接，不断的发送信息
			sendMessage(sync);
			sync.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			group.shutdownGracefully();
		}
	}

	
	/**
	 * TODO 发送消息
	 * 循环性向服务器发送消息
	 * @param sync
	 */
	private void sendMessage(ChannelFuture sync) {
		boolean flag = true;
		while(true){
			MessageObj peekMessage = MessageContexts.getInstance().peekMessage();
			
			if(peekMessage != null && MessageContexts.flag){
				sync.channel().writeAndFlush(peekMessage.getMessage());
				logger.debug("11111111"+peekMessage.getMessage().getData());
				MessageContexts.flag = false;
			}
		
			
		}
	}

	/**
	 * 提供通信的编码器和解码器以及通信的回调处理对象
	 * @return
	 */
	private ChannelInitializer<SocketChannel> getHandler() {
		return new ChannelInitializer<SocketChannel>() {

			/**
			 * 解码和转码
			 */
			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				NetUtils.addDe(ch);
				ch.pipeline().addLast(new MyClientHandler());
			}
		};
	}
}
