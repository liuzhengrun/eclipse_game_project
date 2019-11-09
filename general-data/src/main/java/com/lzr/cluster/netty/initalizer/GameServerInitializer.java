package com.lzr.cluster.netty.initalizer;

import com.lzr.cluster.netty.handler.GameServerHandler;
import com.lzr.cluster.netty.handler.HeartBeatServerHandler;
import com.protobuf.common.Msg.Client;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

//继承Netty提供的初始化类，只要复写其中的方法就可以了
public class GameServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // decoded
        //ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());//用于decode前解决半包和粘包问题（利用包头中的包含数组长度来识别半包粘包）
        //解码客户端发过来的消息
        ch.pipeline().addLast(new ProtobufDecoder(Client.getDefaultInstance()));
        // encoded
        //ch.pipeline().addLast(new LengthFieldPrepender(4));
        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());//用于在序列化的字节数组前加上一个简单的包头，只包含序列化的字节长度。
        ch.pipeline().addLast(new ProtobufEncoder());
        //初始化类一般都是先加入编码解码器来解读传输来的消息，然后加入自定义类来处理业务逻辑
        //ch.pipeline().addLast(new AckServerHandler());//处理ACK
        ch.pipeline().addLast(new IdleStateHandler(10, 0, 0,
                TimeUnit.SECONDS));//此两项为添加心跳机制,10秒查看一次在线的客户端channel是否空闲
        ch.pipeline().addLast(new HeartBeatServerHandler());// 心跳处理handler
        // 加入自定义的Handler
        ch.pipeline().addLast(new GameServerHandler());
    }
}