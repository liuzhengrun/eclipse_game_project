package com.lzr.cluster.netty.tools;

import com.google.protobuf.Message;
import com.lzr.cluster.netty.handler.GameServerHandler;
import com.protobuf.common.Msg.Client;
import com.protobuf.common.Msg.Server;
import io.netty.channel.Channel;
import lombok.extern.log4j.Log4j2;

import java.net.SocketAddress;

/**
 * @author lzr
 * @date 2019/6/25 0025 10:06
 */
@Log4j2
public class MsgHandle {

    /**
     * 发送单个消息到客户端
     */
    public static boolean sendSingleMsgToClient(String token, Message message, Channel channel){
        String simpleName = message.getClass().getSimpleName();
        long code = DomHandle.getCodeType(simpleName);
        if (code<=0){
            log.warn("不存在该类[{}]对应的消息号",simpleName);
            return false;
        }
        channel.writeAndFlush(Server.newBuilder().setCode(code).setToken(token).setBody(DataHandleTool.getByteString(message)).build());
        return true;
    }

    /**
     * 发送单个消息到客户端
     */
    public static boolean sendSingleMsgToClient(String token, Message message, String ip){
        String simpleName = message.getClass().getSimpleName();
        long code = DomHandle.getCodeType(simpleName);
        if (code<=0){
            log.warn("不存在该类[{}]对应的消息号",simpleName);
            return false;
        }
        Channel channel = getChannel(ip);
        if (channel!=null){
            channel.writeAndFlush(Server.newBuilder().setCode(code).setToken(token).setBody(DataHandleTool.getByteString(message)).build());
        }
        return true;
    }

    /**
     * 数据处理成client数据
     */
    public static Client packToClient(String token, Message message){
        String simpleName = message.getClass().getSimpleName();
        long code = DomHandle.getCodeType(simpleName);
        if (code<=0){
            log.warn("不存在该类[{}]对应的消息号",simpleName);
            return Client.newBuilder().setCode(code).setToken(token).build();
        }
        return Client.newBuilder().setCode(code).setToken(token).setBody(DataHandleTool.getByteString(message)).build();
    }

    /**
     * 数据处理成server数据
     */
    public static Server packToServer(String token, Message message){
        String simpleName = message.getClass().getSimpleName();
        long code = DomHandle.getCodeType(simpleName);
        if (code<=0){
            log.warn("不存在该类[{}]对应的消息号",simpleName);
            return Server.newBuilder().setCode(code).setToken(token).build();
        }
        return Server.newBuilder().setCode(code).setToken(token).setBody(DataHandleTool.getByteString(message)).build();
    }

    /**
     * 根据ip获得Channel
     * @return Channel
     */
    public static Channel getChannel(String ip){
        return GameServerHandler.ipChannels.get(ip);
    }

    /**
     * 根据address获得Channel
     * @return Channel
     */
    public static Channel getChannel(SocketAddress address){
        return GameServerHandler.ipChannels.get(address.toString());
    }

}
