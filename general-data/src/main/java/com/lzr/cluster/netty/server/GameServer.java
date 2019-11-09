package com.lzr.cluster.netty.server;

import com.lzr.cluster.netty.initalizer.GameServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class GameServer {
    
    public static void run(String[] args,int port) throws InterruptedException {
        //开启两个事件循环组，事件循环组会自动构建EventLoop，服务器一般开启两个，提高效率
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
           //Netty的引导类，用于简化开发
            ServerBootstrap b = new ServerBootstrap();
           //把事件循环组加入引导程序
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)//开启socket,使用tcp
                    .childHandler(new GameServerInitializer())//加入业务控制器，这里是加入一个初始化类，其中包含了很多业务控制器
                    .option(ChannelOption.SO_BACKLOG, 128)// BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
                    .childOption(ChannelOption.SO_KEEPALIVE, true);// 是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。

            // 服务器绑定端口监听
            ChannelFuture f = b.bind(port).sync();
            // 监听服务器关闭监听
            f.channel().closeFuture().sync();

            // 可以简写为
            /* b.bind(portNumber).sync().channel().closeFuture().sync(); */
        } finally {
           //Netty优雅退出
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}