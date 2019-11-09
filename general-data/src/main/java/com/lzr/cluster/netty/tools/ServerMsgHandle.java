package com.lzr.cluster.netty.tools;

import com.lzr.cluster.netty.client.GameClient;
import com.google.protobuf.Message;
import com.protobuf.common.Msg;
import io.netty.channel.Channel;
import lombok.extern.log4j.Log4j2;

/**
 * @author lzr
 * @date 2019/7/24 0024 17:40
 */
@Log4j2
public final class ServerMsgHandle {

    /**
     * 发送消息到信息传输中转站服务器
     */
    public static boolean sendMessageToGate(String token, Message message){
        String simpleName = message.getClass().getSimpleName();
        long code = DomHandle.getCodeType(simpleName);
        if (code<=0){
            log.warn("不存在该类[{}]对应的消息号",simpleName);
            return false;
        }
        if (GameClient.ch != null){
            GameClient.ch.writeAndFlush(Msg.Client.newBuilder().setCode(code).setToken(token).setBody(DataHandleTool.getByteString(message)).build());
        }else {
            log.warn("未连接成功，不能发送数据");
        }
        return true;
    }

}
