package priv.sen.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import priv.sen.client.NetUtils;

/**
 * @author MiansenWang
 * 2017年9月18日
 * 下午7:27:27
 * 服务器
 */
public class Server {

	public static void main(String[] args) {
		new Server().bind(9999);
	}

	private void bind(int i) {

		EventLoopGroup parentGroup = null;
		EventLoopGroup childGroup = null;
		try {
			// 创建netty的启动对象
			ServerBootstrap b = new ServerBootstrap();
			// 创建两个线程池对象
			parentGroup = new NioEventLoopGroup();
			childGroup = new NioEventLoopGroup();
			// netty的启动配置
			b.group(parentGroup, childGroup)//添加线程池
			.channel(NioServerSocketChannel.class)//配置线程池
			.option(ChannelOption.SO_BACKLOG, 1024)//配置接待数量
			.childHandler(new ChannelInitializer<SocketChannel>() {
						//接待对象，负责接待并且安排处理对象
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {

							NetUtils.addDe(ch);
							ch.pipeline().addLast(new MyServerHandler());
						}
					});

			ChannelFuture futer = b.bind(i).sync();//启动并等待运行结果
			futer.channel().closeFuture().sync();//服务关闭,释放资源
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			//释放线程池资源
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
		}
	}
}
