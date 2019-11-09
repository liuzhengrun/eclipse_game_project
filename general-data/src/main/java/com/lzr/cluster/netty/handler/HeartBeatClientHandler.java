package com.lzr.cluster.netty.handler;

import com.lzr.cluster.netty.tools.DataHandleTool;
import com.protobuf.common.Command.CodeType;
import com.protobuf.common.Msg.Client;
import com.protobuf.common.Msg.Heart;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.log4j.Log4j2;

/**
 * 心跳检测
 */
@Log4j2
public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {
 
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if (evt instanceof IdleStateEvent){
			IdleStateEvent event = (IdleStateEvent)evt;
			if (event.state()== IdleState.WRITER_IDLE){
				//log.info("发送心跳");
				ctx.channel().writeAndFlush(Client.newBuilder().setCode(CodeType.HEART_INFO_VALUE).setToken("654321").setBody(DataHandleTool.getByteString(Heart.newBuilder().setValue("客户端心跳").build())).build());
			}
		}
	}

}
