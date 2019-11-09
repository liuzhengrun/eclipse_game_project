package com.lzr.cluster.login;

import com.lzr.cluster.netty.client.GameClient;

/**
 * @author lzr
 * @date 2019/7/22 0022 18:39
 */
public class ClientStart {
    public static void main(String[] args){
        GameClient.start(args,"127.0.0.1",1001);
    }
}
