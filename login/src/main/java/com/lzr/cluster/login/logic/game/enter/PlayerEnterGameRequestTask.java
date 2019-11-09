package com.lzr.cluster.login.logic.game.enter;

import com.lzr.cluster.common.LogicInfoHandler;
import com.lzr.cluster.netty.annotations.Head;
import com.lzr.cluster.netty.basecontainer.BasePlayerData;
import com.lzr.cluster.netty.basecontainer.GameContainer;
import com.lzr.cluster.netty.interfaces.GameMessage;
import com.lzr.cluster.netty.tools.MsgHandle;
import com.google.protobuf.ByteString;
import com.protobuf.common.Command.CodeType;
import com.protobuf.game.GameServer.PlayerEnterGameResponse;
import com.protobuf.game.GameServer.PlayerEnterGameRequest;
import io.netty.channel.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * 玩家进入游戏请求
 * @author lzr
 * 2019年10月31日
 */
@Log4j2
@Component
@Head(value = CodeType.PLAYER_ENTER_GAME_REQUEST_VALUE)
public class PlayerEnterGameRequestTask implements GameMessage {

    @Override
    public void doMessage(Channel channel, ByteString byteString) throws Exception{
        // 收到消息直接打印输出
        PlayerEnterGameRequest playerEnterGameRequest = PlayerEnterGameRequest.parseFrom(byteString);
        BasePlayerData basePlayerData = new BasePlayerData();
        BasePlayerData preBasePlayerData = GameContainer.getBasePlayerData(playerEnterGameRequest.getPlayerId());
        basePlayerData.setPlayerId(playerEnterGameRequest.getPlayerId());
        basePlayerData.setClientIp(channel.remoteAddress().toString());
        if (preBasePlayerData!=null){// 已经登录过
            if (channel.remoteAddress().toString().equals(preBasePlayerData.getClientIp())){// 重新连接
                log.info("重新登录,id为: [{}]",playerEnterGameRequest.getPlayerId());
            }else {// 踢人
                Channel preChannel = MsgHandle.getChannel(preBasePlayerData.getClientIp());
                // 其余逻辑在这里写

                // 踢出
                LogicInfoHandler.doSqueezed(preChannel,preBasePlayerData.getPlayerId());
                log.info("强制登录,id为: [{}]",playerEnterGameRequest.getPlayerId());
            }
        }else {
            // 未被登录
            GameContainer.removePlayersByIp(channel.remoteAddress().toString());// 防止玩家同一ip多个账号登录
            log.info("首次登录,id为: [{}]",playerEnterGameRequest.getPlayerId());
        }
        GameContainer.saveBasePlayerData(basePlayerData);// 保存玩家数据到map中
        PlayerEnterGameResponse.Builder playerEnterGameResponse = PlayerEnterGameResponse.newBuilder();
        playerEnterGameResponse.setAvatarPath("www.google.com");
        playerEnterGameResponse.setDiamond(2000);
        playerEnterGameResponse.setGold(50000);
        playerEnterGameResponse.setPlayerId(playerEnterGameRequest.getPlayerId());
        playerEnterGameResponse.setPlayerName("柳正润");
        MsgHandle.sendSingleMsgToClient("654321", playerEnterGameResponse.build(),channel);
    }
}
