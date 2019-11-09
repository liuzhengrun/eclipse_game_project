package com.lzr.cluster.netty.basecontainer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 退出和被挤下玩家容器
 * @author lzr
 * @date 2019/6/27 0027 17:09
 */
public class ExitPlayerContainer {

    /**
     * 被挤下ip对应的玩家id
     */
    private final static ConcurrentHashMap<String,Long> squeezedPlayers = new ConcurrentHashMap<>();

    /**
     * 是否是被挤下的玩家
     */
    public static boolean containsSqueezed(String ip){
        return squeezedPlayers.contains(ip);
    }

    /**
     * 移除被挤下的玩家的ip
     */
    public static void removeSqueezed(String ip){
        squeezedPlayers.remove(ip);
    }

    /**
     * 增加被挤下的玩家的ip
     */
    public static void addSqueezed(String ip,long id){
        squeezedPlayers.put(ip,id);
    }

}
