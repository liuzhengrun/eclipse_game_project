package com.lzr.cluster.netty.initalizer;

import com.lzr.cluster.netty.handler.GameClientHandler;
import com.lzr.cluster.netty.handler.HeartBeatClientHandler;
import com.protobuf.common.Msg.Server;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class GameClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        /*
         * 这个地方的 必须和服务端对应上。否则无法正常解码和编码
         * 解码和编码
         */
        // decoded
        //ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());//用于decode前解决半包和粘包问题（利用包头中的包含数组长度来识别半包粘包）
        //这里是收到服务端发过来的消息,所以是对服务端的response解码
        ch.pipeline().addLast(new ProtobufDecoder(Server.getDefaultInstance()));
        // encoded
        //ch.pipeline().addLast(new LengthFieldPrepender(4));
        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());//用于在序列化的字节数组前加上一个简单的包头，只包含序列化的字节长度。
        ch.pipeline().addLast(new ProtobufEncoder());
        // 重连
        ch.pipeline().addLast(new IdleStateHandler(0,4,0, TimeUnit.SECONDS));
        ch.pipeline().addLast(new HeartBeatClientHandler());
        // 客户端的逻辑
        ch.pipeline().addLast(new GameClientHandler());

    }
}