package com.lzr.cluster.login.logic.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

@Data
@Alias(value="sysUser")
public class SysUser implements Serializable {
    /**
     * 用户自增id
     */
    private Integer userId;

    /**
     * 用户登录名称
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 性别 0:男 1:女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * qq号码
     */
    private String qq;

    /**
     * 家庭地址
     */
    private String homeAddress;

    /**
     * 头像地址
     */
    private String avatarAddress;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 当前状态 0:正常使用 -1:封停
     */
    private Integer status;

    /**
     * 处理时间
     */
    private Date handleTime;

    /**
     * 处理人
     */
    private String handleBy;

    private static final long serialVersionUID = 1L;

}