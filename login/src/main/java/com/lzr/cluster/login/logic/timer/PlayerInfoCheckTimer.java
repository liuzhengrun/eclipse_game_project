package com.lzr.cluster.login.logic.timer;

import com.lzr.cluster.netty.basecontainer.GameContainer;
import com.lzr.cluster.netty.handler.GameServerHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lzr
 * @date 2019/6/27 0027 12:00
 */
@Log4j2
@Component
public class PlayerInfoCheckTimer {

    @Scheduled(cron = "*/30 * * * * ?")
    public void handle(){
        log.info("ipChannels=[{}],players = [{}],ipPlayers = [{}]", GameServerHandler.ipChannels.size(), GameContainer.getAllPlayer().size(),GameContainer.getAllIpPlayer().size());
    }

}
