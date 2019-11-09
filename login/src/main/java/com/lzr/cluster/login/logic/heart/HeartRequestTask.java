package com.lzr.cluster.login.logic.heart;

import com.lzr.cluster.netty.annotations.Head;
import com.lzr.cluster.netty.interfaces.GameMessage;
import com.lzr.cluster.netty.tools.MsgHandle;
import com.google.protobuf.ByteString;
import com.protobuf.common.Command.CodeType;
import com.protobuf.common.Msg.Heart;
import io.netty.channel.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @author lzr
 * @date 2019/6/25 0025 17:35
 */
@Log4j2
@Component
@Head(value = CodeType.HEART_INFO_VALUE)
public class HeartRequestTask implements GameMessage {
    @Override
    public void doMessage(Channel channel, ByteString byteString) throws Exception {
        Heart heart = Heart.parseFrom(byteString);
        //log.info("[{}]客户端心跳处理,value值为: [{}]",channel.remoteAddress(),heart.getValue());
        MsgHandle.sendSingleMsgToClient("654321", Heart.newBuilder().setValue("服务器收到心跳").build(),channel);
    }
}
