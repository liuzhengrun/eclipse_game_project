package com.lzr.cluster.netty.listener;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.TimeUnit;

import static com.lzr.cluster.netty.client.GameClient.doConnect;

/**
 * 负责监听启动时连接失败，重新连接功能
 * @author lzr
 * @date 2019/7/29 0029 11:28
 */
@Log4j2
public class ConnectionListener implements ChannelFutureListener {
	
	@Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        //监听到连接服务器失败时，会在3秒后重新连接（执行doConnect方法）
        if (channelFuture.isSuccess()) {
            log.info("重新连接服务器成功");
        } else {
            log.info("重新连接服务器失败");
            //  3秒后重新连接
            channelFuture.channel().eventLoop().schedule(new Runnable() {
                public void run() {
                    doConnect();
                }
            }, 3, TimeUnit.SECONDS);
        }
    }
}
