package com.lzr.cluster.login;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lzr.cluster.netty.NettyStart;

import lombok.extern.log4j.Log4j2;

/**
 * 启动类
 * @author lzr
 * 2019年10月23日
 */
@Log4j2
public class App{
	
    public static void main( String[] args ){
    	//单配置文件方式(上下文配置)
    	long startTime = System.currentTimeMillis();
    	ConfigurableApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        NettyStart.start(args,context);// 开启netty
        log.info("项目启动花费时间：["+(System.currentTimeMillis()-startTime)*1.0/1000+"秒]");
    }
    
}
