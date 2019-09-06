package beanInNetty;

import java.util.Timer;
import java.util.TimerTask;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


public class ClientChannelHandler extends ChannelHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Timer timer=new Timer();
		timer.schedule(new TimerTask() {			
			@Override
			public void run() {
				Student s = new Student("小红:"+ctx.channel().localAddress(), ctx.channel().id()+"班", (int)(1+Math.random()*(30-1+1)));
				ctx.writeAndFlush(s);
			}
		},1000,2000);		
		//Student s = new Student("小红", "5班", 12);
		//ctx.writeAndFlush(s);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
           System.out.println(ctx.channel().remoteAddress().toString()+msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

	}
}