package com.lzr.cluster.netty;

import com.lzr.cluster.netty.server.GameServer;
import com.lzr.cluster.netty.tools.DomHandle;
import com.lzr.cluster.utils.PropertyUtil;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 开启netty
 * @author lzr
 * @date 2019/6/3 0003 13:33
 */
@Log4j2
public class NettyStart {

    public static void start(String[] args, ConfigurableApplicationContext context) {
        new Thread(new Runnable() {// 开启netty
            @Override
            public void run() {
            	String property = System.getProperty("user.dir");
            	String substring = property.substring(0, property.lastIndexOf("\\"));
                DomHandle.handle(substring+"/proto-buffer/protobuild/proto/command.xml");// 读取command.xml文件
                String port= PropertyUtil.getProperty("netty.game.port");
                try {
                    log.info("开启服务器:端口号为["+port+"]");
                    GameServer.run(args,Integer.valueOf(port));
                }catch (Exception e){
                    throw new Error("服务器启动错误,端口号:"+port);
                }
            }
        }).start();
    }

}
