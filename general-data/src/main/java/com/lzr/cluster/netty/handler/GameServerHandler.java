package com.lzr.cluster.netty.handler;

import com.google.protobuf.Message;
import com.lzr.cluster.common.LogicInfoHandler;
import com.lzr.cluster.netty.tools.MsgHandle;
import com.protobuf.common.Msg.Client;
import com.protobuf.common.Msg.Heart;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.log4j.Log4j2;

import java.net.InetAddress;
import java.util.concurrent.ConcurrentHashMap;

//继承Netty提供的通道传入处理器类，只要复写方法就可以了，简化开发
//这个注解@Sharable，默认的4版本不能自动导入匹配的包，需要手动加入
@Log4j2
@Sharable
public class GameServerHandler extends SimpleChannelInboundHandler<Message> {

	//获取现有通道，一个通道channel就是一个socket链接在这里
	public final static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	//ip=>channel通道,连接成功才能放进去
    public final static ConcurrentHashMap<String,Channel> ipChannels = new ConcurrentHashMap<String,Channel>();

    //有新链接加入，对外发布消息
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        LogicInfoHandler.doConnectSuccess(ctx.channel());// 处理客户端连接服务器成功
        channels.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    //有链接断开
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        try {
            LogicInfoHandler.doExit(ctx.channel());// 处理客户端退出
        }catch (Exception e){
            e.printStackTrace();
        }
        ipChannels.remove(ctx.channel().remoteAddress().toString());// 移除
        channels.remove(ctx.channel());
    }
    
    //消息读取有两个方法，channelRead和channelRead0，其中channelRead0可以读取泛型，常用  
    //收到消息打印出来，并返还客户端消息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        if (msg instanceof Client){
            LogicInfoHandler.doMessage(ctx.channel(), msg);
        }else {
           log.info("出现错误的消息");
        }
    }
    
    /*
     * 覆盖 channelActive 方法 在channel被启用的时候触发 (在建立连接的时候)
     * channelActive 与 channelInActive
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("ip为 : " + ctx.channel().remoteAddress() + " 连接!");
        String welcome = "欢迎来到 " + InetAddress.getLocalHost().getHostName() + " 服务器!";
        MsgHandle.sendSingleMsgToClient("654321", Heart.newBuilder().setValue(welcome).build(),ctx.channel());
        ipChannels.put(ctx.channel().remoteAddress().toString(),ctx.channel());// 连接成功，可以发送数据
        super.channelActive(ctx);
    }

    /**
     * 异常捕获
     * @param ctx
     * @param cause
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.error("服务器发生异常");
        cause.printStackTrace();
        Channel channel = ctx.channel();
        //关闭
        if(channel.isActive()){
            ipChannels.remove(ctx.channel().remoteAddress().toString());// 移除
            channels.remove(ctx.channel());
            ctx.close();
        };
    }
}