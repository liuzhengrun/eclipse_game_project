package com.lzr.cluster.login.logic.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.lzr.cluster.login.logic.mapper.SysUserMapper;
import com.lzr.cluster.login.logic.model.SysUser;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class LoginManager {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Value("${netty.game.port}")
	private String nettyGamePort;
	
	public String getStringInfo(){
		log.info("二次处理,获得端口号："+nettyGamePort);
		log.info("开始获取所有用户信息");
		List<SysUser> allUser = sysUserMapper.getAllUser();
		System.out.println("保存数据到redis中："+allUser.toString());
		redisTemplate.opsForValue().set("lzr", allUser.toString());
		return "二次处理返回";
	}

}
