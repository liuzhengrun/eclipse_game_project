package com.lzr.cluster.login.logic.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lzr.cluster.login.logic.manager.LoginManager;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ComonLoginRequest {
	
	@Autowired
	private LoginManager loginManager;
	
	public String getInfo() {
		log.info("log:测试");
		return loginManager.getStringInfo();
	}

}
