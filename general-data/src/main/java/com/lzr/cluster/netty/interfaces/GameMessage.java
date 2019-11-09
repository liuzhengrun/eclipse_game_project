package com.lzr.cluster.netty.interfaces;

import com.google.protobuf.ByteString;
import io.netty.channel.Channel;

/**
 * @author lzr
 * @date 2019/6/10 0010 15:57
 */
public interface GameMessage {

    void doMessage(Channel channel, ByteString byteString)throws Exception;

}
