package com.lzr.cluster.netty.handler;

import com.google.protobuf.Message;
import com.lzr.cluster.netty.client.GameClient;
import com.lzr.cluster.netty.tools.DataHandleTool;
import com.protobuf.common.Command.CodeType;
import com.protobuf.common.Msg.Client;
import com.protobuf.common.Msg.Heart;
import com.protobuf.common.Msg.Server;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.TimeUnit;

@Log4j2
public class GameClientHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        if (msg instanceof Server){
            Server serverMsg = Server.parseFrom(msg.toByteArray());
            log.info(ctx.channel().remoteAddress() + " 服务器消息 : " +
                    "code=[" + serverMsg.getCode()+"],token=["+serverMsg.getToken()+"],body=["+serverMsg.getBody().toString("UTF-8")+"]");
        }else {
            log.info("出现了未处理的信息");
        }
    }
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接服务器....");
        Client msg = Client.newBuilder().setCode(CodeType.HEART_INFO_VALUE).setToken("123456").setBody(DataHandleTool.getByteString(Heart.newBuilder().setValue("客户端连接服务器").build())).build();
        ctx.writeAndFlush(msg);
        super.channelActive(ctx);
    }

    //当客户端掉线时要进行重新连接
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端关闭....");
        super.channelInactive(ctx);
        //重新连接服务器
        ctx.channel().eventLoop().schedule(new Runnable() {
            public void run() {
                GameClient.doConnect();
            }
        }, 2, TimeUnit.SECONDS);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //super.exceptionCaught(ctx, cause);
        log.error("异常关闭客户端");
        Channel channel = ctx.channel();
        //关闭
        if(channel.isActive())ctx.close();
    }
}