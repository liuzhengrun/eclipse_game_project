package com.lzr.cluster.netty.basecontainer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 游戏基础容器
 * @author lzr
 * @date 2019/6/10 0010 16:07
 */
public class GameContainer {

    /**
     * 玩家id对应的玩家登录数据
     */
    private final static ConcurrentHashMap<Long,BasePlayerData> players = new ConcurrentHashMap<>();

    /**
     * ip对应的玩家登录数据
     */
    private final static ConcurrentHashMap<String,BasePlayerData> ipPlayers = new ConcurrentHashMap<>();

    /**
     * 获取所有id对应玩家
     */
    public static ConcurrentHashMap<Long,BasePlayerData> getAllPlayer(){
        return players;
    }

    /**
     * 获取所有ip对应玩家
     */
    public static ConcurrentHashMap<String,BasePlayerData> getAllIpPlayer(){
        return ipPlayers;
    }

    /**
     * 根据playerId获取玩家
     */
    public static BasePlayerData getBasePlayerData(long playerId){
        return players.get(playerId);
    }

    /**
     * 将数据存进map
     */
    public static boolean saveBasePlayerData(BasePlayerData basePlayerData){
        players.put(basePlayerData.getPlayerId(),basePlayerData);
        ipPlayers.put(basePlayerData.getClientIp(),basePlayerData);
        return true;
    }

    /**
     * 移除玩家
     */
    public static boolean removeBasePlayerData(String clientIp){
        BasePlayerData remove = ipPlayers.remove(clientIp);
        if (remove!=null){
            players.remove(remove.getPlayerId());
        }
        return true;
    }

    /**
     * 登录时移除同一ip的玩家数据
     */
    public static boolean removePlayersByIp(String clientIp){
        BasePlayerData basePlayerData = ipPlayers.get(clientIp);
        if (basePlayerData!=null){
            players.remove(basePlayerData.getPlayerId());
        }
        return true;
    }

}
