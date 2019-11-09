package com.lzr.cluster.netty.annotations;

import java.lang.annotation.*;

/**
 * @author lzr
 * @date 2019/6/10 0010 22:32
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Head {

    /**
     * 消息类对应id
     */
    long value();

}
