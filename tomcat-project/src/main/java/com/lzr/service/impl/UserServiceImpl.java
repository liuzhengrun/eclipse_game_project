package com.lzr.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzr.service.UserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService{

}
