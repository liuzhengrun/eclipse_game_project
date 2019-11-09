package com.lzr.cluster.login.logic.game.exit;

import com.lzr.cluster.netty.annotations.Head;
import com.lzr.cluster.netty.basecontainer.GameContainer;
import com.lzr.cluster.netty.interfaces.GameMessage;
import com.google.protobuf.ByteString;
import com.protobuf.common.Command.CodeType;
import com.protobuf.common.Msg.*;
import io.netty.channel.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * 退出游戏
 * @author lzr
 * 2019年10月31日
 */
@Log4j2
@Component
@Head(value = CodeType.CLIENT_EXIT_REQUEST_VALUE)
public class ClientExitRequestTask implements GameMessage {

    @Override
    public void doMessage(Channel channel, ByteString byteString) throws Exception {
        GameContainer.removeBasePlayerData(channel.remoteAddress().toString());
        ClientExitRequest clientExitRequest = ClientExitRequest.parseFrom(byteString);
        log.info("玩家ip:[{}]退出了游戏",channel.remoteAddress());
    }

}
