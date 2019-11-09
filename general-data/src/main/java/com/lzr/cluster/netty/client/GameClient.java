package com.lzr.cluster.netty.client;

import com.lzr.cluster.netty.initalizer.GameClientInitializer;
import com.lzr.cluster.netty.listener.ConnectionListener;
import com.lzr.cluster.netty.tools.ServerMsgHandle;
import com.protobuf.game.GameServer.PlayerEnterGameRequest;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Log4j2
public class GameClient {
    private static Bootstrap bootstrap;
    public static String host;
    private static ChannelFuture future;
    public static int port;
    public static Channel ch = null;// 客户端与服务器的连接通道

    public static void start(String[] args,String thisHost,int thisPort){
        sendMessage();
        new Thread(new Runnable() {// 连接信息中转站
        	@Override
            public void run() {
                host = thisHost;
                port = thisPort;
                EventLoopGroup group = new NioEventLoopGroup();
                try {
                    bootstrap = new Bootstrap();
                    bootstrap.group(group)
                            .option(ChannelOption.SO_KEEPALIVE, true)//设置TCP协议的属性
                            .option(ChannelOption.TCP_NODELAY, true)
                            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)//如果不设置超时，连接会一直占用本地线程，端口，连接客户端一多，阻塞在那里，会导致本地端口用尽及CPU压力
                            //.option(ChannelOption.SO_TIMEOUT, 5000)
                            .channel(NioSocketChannel.class)
                            .handler(new GameClientInitializer());
                    // 循环连接服务端
                    boolean flag = false;
                    while (!flag){
                        try {
                            Thread.sleep(2000);
                            future = bootstrap.connect(host,port).sync();
                            flag = true;
                            log.info("连接服务器成功");
                        }catch (Exception e){
                            log.info("连接服务器失败");
                        }
                    }
                    ch = future.channel();
                } catch (Exception e){
                    e.printStackTrace();
                }finally {
                    //group.shutdownGracefully();
                }
            }
        }).start();
    }

    //  连接到服务端
    public static void doConnect() {
        log.info("客户端重新连接....");
        boolean flag = false;
        while (!flag){
            try {
                future = bootstrap.connect(host,port).awaitUninterruptibly();// 非异步请求连接，防止多次请求连接成功
                flag = true;
            } catch (Exception e) {
                log.info("连接信息传输中转站服务器失败");
            }
        }
        future.addListener(new ConnectionListener());
        ch = future.channel();// 重新赋值
    }

    // 发送数据
    private static void sendMessage(){
        //也可以用while循环
        new Thread(new Runnable() {
        	@Override
            public void run() {
                // 控制台输入
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                for (;;) {
                    try {
                        String line = in.readLine();
                        System.out.println("数据:"+line);
                        if (line == null) {
                            continue;
                        }
                        ServerMsgHandle.sendMessageToGate("123456",PlayerEnterGameRequest.newBuilder().setPlayerId(Long.valueOf(line.trim())).build());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}