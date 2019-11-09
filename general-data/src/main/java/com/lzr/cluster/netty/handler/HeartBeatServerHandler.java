package com.lzr.cluster.netty.handler;
 
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.log4j.Log4j2;

/**
 * 心跳检测
 */
@Log4j2
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {
 
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state() == IdleState.READER_IDLE) {
				log.info("10 秒没有接收到客户端" + ctx.channel().id() + "的信息了,移除客户端");
				// 处理退出操作逻辑
				ctx.channel().close();
			}
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}

	//不做任何业务逻辑处理，让channelPipe中的下一个handler处理channelRead方法
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ctx.fireChannelRead(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
 
}
