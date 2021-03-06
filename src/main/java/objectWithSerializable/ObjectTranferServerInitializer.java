package objectWithSerializable;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ObjectTranferServerInitializer extends ChannelInitializer<SocketChannel>{	

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline=ch.pipeline();
		pipeline.addLast(new ObjectEncoder());
		pipeline.addLast( new ObjectDecoder(Integer.MAX_VALUE,ClassResolvers.cacheDisabled(null)));
		pipeline.addLast(new ObjectTransferServerHandler());		
	}

}
