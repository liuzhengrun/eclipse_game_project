package com.lzr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.lzr.model.SysUser;

@Mapper
public interface SysUserMapper {
	@Select("select * from sys_user")
	List<SysUser> getAllUser();
}
