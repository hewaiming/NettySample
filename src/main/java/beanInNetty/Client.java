package beanInNetty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    private int name;
	public Client(int name) {
		this.name=name;
	}

	public static void main(String[] args) throws Exception {
		int i;
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (i = 0; i < 300; i++) {			
			cachedThreadPool.execute(new doTask(i));
			//Thread.sleep(500);
			System.out.println("线程："+i);
		}
		// new Client().connect("127.0.0.1", 8888);		
		//cachedThreadPool.shutdown();
	}

	public void connect(String host, int port) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class);
			b.option(ChannelOption.TCP_NODELAY, true);
			b.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
					ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
					ch.pipeline().addLast(new ClientChannelHandler());
				}
			});
			ChannelFuture f = b.connect(host, port);
			f.channel().closeFuture().sync();
		} finally {
			group.shutdownGracefully();
		}
	}

}