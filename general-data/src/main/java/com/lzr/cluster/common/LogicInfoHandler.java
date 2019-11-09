package com.lzr.cluster.common;

import com.google.protobuf.Message;
import com.lzr.cluster.netty.basecontainer.ExitPlayerContainer;
import com.lzr.cluster.netty.basecontainer.GameContainer;
import com.lzr.cluster.netty.interfaces.GameMessage;
import com.lzr.cluster.netty.tools.MsgHandle;
import com.protobuf.common.Msg.*;
import io.netty.channel.Channel;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

/**
 * 逻辑消息处理
 * @author lzr
 * @date 2019/6/10 0010 16:39
 */
@Log4j2
public class LogicInfoHandler {

    /**
     * 消息处理类
     */
    /*public final static LogicInfoHandler logicInfoHandler = new LogicInfoHandler();*/

    /**
     * 类id对应消息类
     */
    public final static Map<Long, GameMessage> infoHandle = new HashMap<>();

    /**
     * <strong>处理逻辑消息</strong>
     */
    public static void doMessage(Channel channel, Message message){
        try {
            if (message instanceof Client){
                Client client = (Client)message;
                GameMessage gameMessage = infoHandle.get(client.getCode());
                if (gameMessage==null){
                    log.info("出现不能处理的消息号："+client.getCode());
                    return;
                }
                gameMessage.doMessage(channel, Client.parseFrom(message.toByteArray()).getBody());
            }else if (message instanceof Server){
                log.info("服务器消息, 需要解析");
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info("消息处理失败");
        }

    }

    /**
     * <strong>处理客户端退出</strong>
     */
    public static void doExit(Channel channel){
        if (!ExitPlayerContainer.containsSqueezed(channel.remoteAddress().toString())){// 是正常退出
            // 退出协议
            Client client = MsgHandle.packToClient("654321", ClientExitRequest.newBuilder().build());
            // 处理逻辑
            doMessage(channel,client);
        }else {// 被踢下线
            ExitPlayerContainer.removeSqueezed(channel.remoteAddress().toString());
        }
    }

    /**
     * 处理账号重复登录踢人
     */
    public static void doSqueezed(Channel preChannel,long id){
        MsgHandle.sendSingleMsgToClient("654321", ClientExitResponse.newBuilder().setValue("账号在其他地方被登陆").build(),preChannel);
        // 移除玩家数据
        GameContainer.removeBasePlayerData(preChannel.remoteAddress().toString());
        if (preChannel.isActive()){
            ExitPlayerContainer.addSqueezed(preChannel.remoteAddress().toString(),0);
            preChannel.close();
        }
    }

    /**
     * <strong>处理客户端连接服务器成功</strong>
     */
    public static void doConnectSuccess(Channel channel){
        String joinMsg = "服务器信息 -> " + channel.remoteAddress() + " 新链接加入";
        log.info(joinMsg);
        Server server = MsgHandle.packToServer("654321", ClientConnectSuccessResponse.newBuilder().setValue(joinMsg).build());
        // 处理逻辑
        channel.writeAndFlush(server);
    }

    /**
     * 添加信息处理类
     */
    public static void putInfoHandle(long key, GameMessage gameMessage){
        if (infoHandle.containsKey(key)){
            throw new Error("head中的value["+key+"]重复");
        }else {
            infoHandle.put(key,gameMessage);
        }
    }

}
