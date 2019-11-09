package com.lzr.cluster.netty.interfaces;

/**
 * 玩家重连
 * @author lzr
 * @date 2019/6/10 0010 16:01
 */
public interface IReconnection<K, T> {

    void reconnect(K room, T palyer);

}
